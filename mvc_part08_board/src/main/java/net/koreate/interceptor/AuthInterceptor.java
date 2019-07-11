package net.koreate.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.koreate.service.BoardService;
import net.koreate.vo.BoardVO;
import net.koreate.vo.UserVO;

public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	@Inject
	BoardService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("AuthInterceptor pre handle");
		String requestUri = request.getRequestURI();
		System.out.println(requestUri);
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("userInfo");
		
		if(obj == null) {
			System.out.println("사용자 정보가 없음");
			response.sendRedirect("/user/signIn");
			return false;
		}else {
			
			if(requestUri.equals("/sboard/register")) {
				System.out.println("새글 작성");
				return true;
			}
			
			String num = request.getParameter("bno");
			System.out.println("게시물 번호 : " +  num);
			
			UserVO user = (UserVO)obj;
			
			if(num != null && !num.equals("")) {
				int bno = Integer.parseInt(num);
				
				if(requestUri.equals("/sboard/replyRegister")) {
					System.out.println("답글 작성");
					return true;
				}
				
				BoardVO board = service.readReply(bno);
				System.out.println("수정&삭제 요청시");
				
				if(board.getUno() == user.getUno()) {
					System.out.println("인증 완료 동일 사용자");
					return true;
				}else {
					System.out.println("동일 사용자 아님");
					response.sendRedirect("/sboard/read?bno="+bno);
					return false;
				}
			}
		}
		System.out.println("AuthInterceptor pre handle");
		return true;
	}
	
	

}
