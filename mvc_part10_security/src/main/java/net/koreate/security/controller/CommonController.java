package net.koreate.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {
	
	@GetMapping("/errorForbidden")
	public void errorForbidden(Authentication auth, Model model) {
		System.out.println("error Forbidden : " + auth);
		model.addAttribute("msg","접근 권한이 없습니다.");
	}
	
	@GetMapping("/login")
	public ModelAndView login(ModelAndView mav) {
		mav.setViewName("/user/login");
		return mav;
	}
	
	@GetMapping("/logout")
	public String logOutGET() {
		System.out.println("get CUSTOM logout");
		return "/user/logout";
	}
}
