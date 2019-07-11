package net.koreate.test.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import net.koreate.test.service.MemberService;
import net.koreate.test.vo.ValidationMemberVO;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Inject
	MemberService ms;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		System.out.println("Login SUCCESS");
		
		CustomMember member = (CustomMember)auth.getPrincipal();
		ValidationMemberVO vo = member.getMember();
		System.out.println("login member : "+vo);
				
		System.out.println(member.getUsername());
		System.out.println(member.getPassword());
		System.out.println(member.getAuthorities());
		
		ms.updateVisitDate(vo.getU_id());
		response.sendRedirect("/");
	}
	
	

}
