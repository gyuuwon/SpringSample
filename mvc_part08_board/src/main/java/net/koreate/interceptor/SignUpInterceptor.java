package net.koreate.interceptor;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.koreate.service.UserService;
import net.koreate.vo.UserVO;

public class SignUpInterceptor extends HandlerInterceptorAdapter{
	
	@Inject
	UserService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("Sign Up Interceptor preHandle");
		
		String upw = request.getParameter("upw");
		String repw = request.getParameter("repw");
		
		RequestDispatcher rd = request.getRequestDispatcher("/user/signUp");
		String message = "";
		if(!upw.equals(repw)) {
			message = "비밀번호가 일치하지 않습니다.";
			request.setAttribute("message", message);
			rd.forward(request, response);
			return false;
		}
		
		String uid = request.getParameter("uid");
		System.out.println("uid : " + uid);
		UserVO vo = service.getUserById(uid);
		if(vo != null) {
			message = uid + "는 이미 존재하는 아이디 입니다.";
			request.setAttribute("message", message);
			rd.forward(request, response);
			return false;
		}
		
		return true;
	}

	
}
