package net.koreate.struct;

import org.springframework.stereotype.Controller;
/*import org.springframework.stereotype.Service;*/
import org.springframework.web.bind.annotation.RequestMapping;

/*
@Component
@Service
@Repository*/

@Controller
public class SampleController {
	
	@RequestMapping("doA")
	public void doA() {
		System.out.println("doA CALL");
	}
	
	@RequestMapping("doB")
	public String doB() {
		System.out.println("doB");
		return "home";
	}
}
