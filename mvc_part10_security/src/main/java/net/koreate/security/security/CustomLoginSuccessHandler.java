package net.koreate.security.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("Login Success");
		
		List<String> roleNames = new ArrayList<>();
		
		for(GrantedAuthority auth  : authentication.getAuthorities()) {
			System.out.println("authority : " + auth.getAuthority());
			roleNames.add(auth.getAuthority());
		}
		/*		
		User user = (User)authentication;
		
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		*/
		System.out.println("roleNames : " + roleNames);
		
		if(roleNames.contains("ROLE_MASTER")) {
			System.out.println("ROLE_MASTER 권한");
			response.sendRedirect("/test/master");
			return;
		}
		
		if(roleNames.contains("ROLE_MEMBERSHIP")) {
			System.out.println("ROLE_MEMBERSHIP 권한");
			response.sendRedirect("/test/memberShip");
			return;
		}
		
		response.sendRedirect("/");
		System.out.println("권한 없음");
		
	}
}
