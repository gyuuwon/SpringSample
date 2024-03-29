package net.koreate.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/*")
public class TestController {

	@GetMapping("all")
	public void all() {
		System.out.println("전체 이용가능");
	}
	
	@GetMapping("memberShip")
	public void memberShip() {
		System.out.println("회원 이용가능");
	}
	
	
	@GetMapping("master")
	public void master() {
		System.out.println("관리자 이용가능");
	}
	
}
