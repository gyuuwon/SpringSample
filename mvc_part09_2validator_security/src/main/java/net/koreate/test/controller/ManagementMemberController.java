package net.koreate.test.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.koreate.test.service.MemberService;
import net.koreate.test.vo.AuthVO;
import net.koreate.test.vo.ValidationMemberVO;

@Controller
@RequestMapping("/mngt")
public class ManagementMemberController {

	@Inject
	MemberService service;
	
	@GetMapping("main")
	public void main(Model model) throws Exception{
		// 회원정보 리스트
		model.addAttribute("memberList", service.getMemberList());
	}
	
	@PostMapping("/user/changeAuth")
	@ResponseBody
	public List<AuthVO> changeAuth(AuthVO auth) throws Exception{
		System.out.println(auth);
		List<AuthVO> authList = service.updateAuth(auth);
		return authList;
	}
	
	@PostMapping("user/delete")
	@ResponseBody
	public String deleteYN(ValidationMemberVO vo) {
		System.out.println(vo);
		service.deleteYN(vo);
		return vo.getU_withdraw();
	}
}
