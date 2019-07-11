package net.koreate.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
	

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("test1")
	public String test1() {
		System.out.println("HOME CONTROLLER test1 요청");
		System.out.println("HOME CONTROLLER test1 처리완료");
		return "home";
	}
	
	@GetMapping("test2")
	public String test2(Model model) {
		System.out.println("HOME CONTROLLER test2 요청");
		model.addAttribute("result", "test2 job");
		System.out.println("HOME CONTROLLER test2 처리완료");
		return "home";
	}
	
	@GetMapping("test3")
	public String test3() {
		System.out.println("HOME CONTROLLER test3 요청");
		System.out.println("HOME CONTROLLER test3 처리완료");
		return "result";
	}
}
