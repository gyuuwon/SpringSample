package net.koreate.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.test.vo.ProductVO;

@Controller
public class MainController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String main() {
		return "main";
	}
	/*@RequestMapping(value="sendMsg", method=RequestMethod.GET)
	public String sendMsg() {
		return "sendMsg";
	}*/
	
	// redirect test
	@RequestMapping(value="sendMsg", method=RequestMethod.GET)
	public String sendMsg(Model model, HttpSession session) {
		
		if(session.getAttribute("productVO") != null) {
			model.addAttribute("productVO", session.getAttribute("productVO"));
			session.removeAttribute("productVO");
		}
		return "sendMsg";
	}
	
	@RequestMapping(value="sendMsg", method=RequestMethod.POST)
	public String sendMsg(String msg, Model model) {
		System.out.println(msg);
		model.addAttribute("msg", msg);
		return "result";
	}
	@RequestMapping(value="registerProduct", method=RequestMethod.GET)
	public void registerProduct() {}
	
/*	@RequestMapping(value="registerProduct", method=RequestMethod.POST)
	public String registerProduct(Model model, @RequestParam("name") String name, @RequestParam(name="price",required=false,defaultValue="0") int price, ProductVO productVO) {
		System.out.println("register product 호출");
		
		System.out.println("상품이름 : " + name);
		System.out.println("상품가격 : " + price);
		
		ProductVO product = new ProductVO(name, price);
		
		// productVO
		model.addAttribute(product);
		// model.addAttribute("productVO",product); => 동일한 표현
		
		System.out.println(productVO.getName()+" // " + productVO.getPrice());
		return "result";
	} */
	
	@RequestMapping(value="registerProduct",method=RequestMethod.POST)
	public String registerProduct(@ModelAttribute("productVO") ProductVO productVO, RedirectAttributes rttr) {
		System.out.println("registerProduct 호출");
		System.out.println(productVO);
		rttr.addFlashAttribute("productVO", productVO);
		return "redirect:sendMsg";
	}
	
	/*
	@RequestMapping(value="registerProduct",method=RequestMethod.POST)
	public String registerProduct(@ModelAttribute("productVO") ProductVO productVO, HttpSession session) {
		System.out.println("registerProduct 호출");
		System.out.println(productVO);
		
		session.setAttribute("productVO", productVO);
		
		// return "result";
		// return "redirect:/";
		return "redirect:sendMsg";
	}	*/
	
	
	
	
	
}
