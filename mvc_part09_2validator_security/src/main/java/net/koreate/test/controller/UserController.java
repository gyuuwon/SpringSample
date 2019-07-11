package net.koreate.test.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.koreate.test.service.MemberService;
import net.koreate.test.vo.ValidationMemberVO;

@Controller
public class UserController {
	
	@Inject
	MemberService ms;
	
	@GetMapping("/user/login")
	public String login(String message, Model model) {
		model.addAttribute("message", message);
		return "/user/login";
	}
	
	@GetMapping("/user/join")
	public String join() {
		return "/user/join";
	}
	
	@GetMapping("/user/joinVal")
	public String joinVal() {
		return "/user/joinVal";
	}
	
	@PostMapping("/user/joinPost")
	public String join(ValidationMemberVO vo) throws Exception{
		System.out.println("joinPost : "+vo);
		ms.memberJoin(vo);
		return "redirect:/user/login";
	}
	
	@GetMapping("/user/logout")
	public String logout() {
		return "/user/logout";
	}
	
	@PostMapping("/user/uIdCheck")
	@ResponseBody
	public boolean uIdCheck(String u_id) throws Exception{
		return ms.getMemberByID(u_id);
	}
}
