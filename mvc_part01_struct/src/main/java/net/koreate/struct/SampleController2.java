package net.koreate.struct;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.koreate.struct.vo.ProductVO;

@Controller
public class SampleController2 {

	@RequestMapping("doC")
	public String doC(Model model) {
		model.addAttribute("msg","doC Message");
		return "result";
	}
	
	@RequestMapping(value="doD",method= {RequestMethod.POST,RequestMethod.GET})
	public String doD(
			HttpServletRequest request,
			@RequestParam("msg") String message,
			@ModelAttribute("msg") String msg
		) {
		String requestMsg = request.getParameter("msg");
		System.out.println("getParameter msg : " + requestMsg);
		System.out.println("request param msg : " + message);
		return "result";
	}
	
	@RequestMapping(value="doE")
	public String doE(Model model) {
		ProductVO product = new ProductVO("sample",10000);
		model.addAttribute("product",product);
		
		ProductVO product1 = new ProductVO("sample",2000);
		model.addAttribute(product1);
		
		
		return "product";
	}
	
	@RequestMapping("doF")
	public ModelAndView doF() {
		ModelAndView mav = new ModelAndView();
		ProductVO product = new ProductVO("doF sample",5000);
		mav.addObject("product",product);
		mav.addObject(product);
		mav.setViewName("product");
		return mav;
	}
}
