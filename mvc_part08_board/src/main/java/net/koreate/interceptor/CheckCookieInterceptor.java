package net.koreate.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import net.koreate.service.UserService;
import net.koreate.vo.UserVO;

public class CheckCookieInterceptor extends HandlerInterceptorAdapter{
	
	@Inject
	UserService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("CheckCookie 시작");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") != null) return true;
			/*Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(int i = 0; i < cookies.length; i++) {
					if(cookies[i].getName().equals("signInCookie")) {
						
						String uid = cookies[i].getValue();
						UserVO uv = service.getUserById(uid);
						if(uv != null) {
							session.setAttribute("userInfo", uv);
						}
					}
				}
			}*/
			Cookie cookie = WebUtils.getCookie(request, "signInCookie");
			if(cookie != null) {
				UserVO uv = service.getUserById(cookie.getValue());
				System.out.println("cookie user : " + uv);
				if(uv != null) {
					session.setAttribute("userInfo", uv);
				}
			}
			
			
			System.out.println("Check Cookie 종료");
			return true;
	}
	
	
	
}
