package net.koreate.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.koreate.dao.UserDAO;
import net.koreate.service.UserService;
import net.koreate.vo.BanIPVO;
import net.koreate.vo.LoginDTO;
import net.koreate.vo.UserVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Inject
	UserService service;
	
	@Inject
	UserDAO dao;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();

		// session에 로그인된 회원정보 userInfo로 저장
		if (session.getAttribute("userInfo") != null) {
			session.removeAttribute("userInfo");
		}
		
		String ip = getIp(request);
		System.out.println("preHandle ip : " + ip);
		
		BanIPVO banVO = dao.getBanIPVO(ip);
		System.out.println("preHandle vo : " + banVO);
		
		if(banVO != null && banVO.getCnt() >= 5) {
			long saveTime = getTime(banVO.getBandate());
			
			if(saveTime > 0) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
				String now = sdf.format(new Date(saveTime));
				
			    RequestDispatcher rd = request.getRequestDispatcher("/user/signIn");
			    request.setAttribute("message", "일정시간동안 로그인 할 수 없습니다. 남은시간 : " + now);
			    rd.forward(request, response);
			    return false;
				
			}else {
				System.out.println("제한 시간 초기화");
				dao.removeBanIP(ip);
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		ModelMap modelObj = modelAndView.getModelMap();
		LoginDTO dto = (LoginDTO) modelObj.get("loginDTO");

		System.out.println("LoginInterceptor postHandle : " + dto);

		UserVO vo = service.signIn(dto);
		
		String ip = getIp(request);
		System.out.println("post handle : " + ip);
		BanIPVO banVO = dao.getBanIPVO(ip);

		if (vo != null) {
			request.getSession().setAttribute("userInfo", vo);
			
			if(banVO != null) {
				dao.removeBanIP(ip);
				System.out.println("ban_ip 로그인 성공 초기화");
			}

			if (dto.isUserCookie()) {
				Cookie cookie = new Cookie("signInCookie", vo.getUid());
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 15);
				response.addCookie(cookie);
			}
		}else {
			
			int count = 5;
			String message = "";
			
			if(banVO == null) {
				System.out.println("최초실패");
				// ban ip 정보 생성
				dao.signInFail(ip);
				count = count - 1;
			}else {
				System.out.println(banVO);
				// 시도 횟수 업데이트
				dao.updateBanIpCnt(ip);
				count = count - (banVO.getCnt()+1);
			}
			
			if(count > 0) {
				message = "회원정보가 일치하지 않습니다. 남은 시도 횟수 : " + count;
			}else {
				message = "너무 많은 시도로 인해 30분 동안 IP가 차단됩니다.";
			}
			/*modelAndView.addObject("message","회원정보가 일치하지 않습니다.");*/
			modelAndView.addObject("message",message);
			modelAndView.setViewName("/user/signIn");
		}
	}
	
	public long getTime(Date banDate) {
		int limit = 1000*60*30;
		System.out.println("limit : " + limit);
		return limit-(System.currentTimeMillis() - banDate.getTime());
	}
	
	
	private String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-forworded-For");
		System.out.println("X-forworded-For" + ip);
		
		if(ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
			System.out.println(ip);
		}
		if(ip == null) {
			ip = request.getHeader("HTTP_X_FORWORDED_FOR");
			System.out.println("HTTP_X_FORWORDED_FOR" + ip);
		}
		if(ip == null) {
			ip = request.getRemoteAddr();
			System.out.println("getRemoteAddr" + ip);
		}
		return ip;
	}

}
