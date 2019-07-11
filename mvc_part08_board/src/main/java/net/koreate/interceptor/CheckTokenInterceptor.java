package net.koreate.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CheckTokenInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("인증 토큰 확인");
		System.out.println("CheckTokenInterceptor");
		System.out.println(request.getMethod());
		if(request.getMethod().equals("POST")) {
			HttpSession session = request.getSession();
			String token = (String) session.getAttribute("csrf_token");
			System.out.println("session token : " + token);
			
			String requestToken = request.getParameter("csrf_token");
			System.out.println("request token : " + requestToken);
			
			if(requestToken == null || requestToken == "" || !requestToken.equals(token)) {
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('잘못된 접근입니다.');");
				out.print("history.go(-1);");
				out.print("</script>");
				return false;
			}
		}
		return true;
	}
	
	

}
