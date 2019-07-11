package net.koreate.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestInterceptor extends HandlerInterceptorAdapter{

	// Controller 요청 처리전
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		System.out.println("preHandle START");
		HandlerMethod method =(HandlerMethod)handler;
		Method methodObj = method.getMethod();
		
		String command = 
			request.getRequestURI().substring(request.getContextPath().length()+1);
		System.out.println("preHandler command : " + command);
		if(command.equals("test1")) {
			response.sendRedirect("test2");
			return false;
		}
		System.out.println("Bean : " + method.getBean());
		System.out.println("method : " + methodObj);
		System.out.println("preHandle END");
		//return true;
		return true;
	}

	// Controller 요청 처리 후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle START");
		
		Map<String,Object> map = modelAndView.getModel();
		System.out.println("postHandle : "+map.size());
		for(String key : map.keySet()) {
			System.out.println("key : " + key);
			System.out.println("value : " + map.get(key));
		}
		
		Object result = modelAndView.getModel().get("result");
		
		System.out.println("view : " + modelAndView.getViewName());
		
		if(modelAndView.getViewName().equals("result")) {
			modelAndView.setViewName("home");
		}
		
		if(result == null) {
			modelAndView.addObject("result","postHandle job");
		}
		System.out.println("postHandle END");
	}

	// DispatcherServlet 화면 전송 완료 후 
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion start");
		System.out.println("afterCompletion end");
	}
	
	
	

}
