package net.koreate.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.koreate.vo.SampleVO;

@Controller
public class SampleController {
	
	@GetMapping("/testJSon")
	public String testJson(Model model) {
		model.addAttribute("hello hihihi");
		return "JSON";
	}

	@GetMapping("/sendSampleVO")
	public String sendSampleVO(Model model) {
		SampleVO sample = new SampleVO();
		sample.setName("피카츄");
		sample.setAge(100);
		model.addAttribute("sample",sample);
		return "JSON";
	}
	
	@GetMapping("/getSample")
	@ResponseBody
	public SampleVO getSample(SampleVO sample) {
		System.out.println("getSample 요청");
		System.out.println(sample);
		return sample;
	}
	
	@PostMapping("/getSample")
	@ResponseBody
	public List<SampleVO> getSamplePost(@RequestBody SampleVO sample){
		System.out.println("POST getSample 요청");
		System.out.println(sample);
		List<SampleVO> sampleList = new ArrayList<>();
		sampleList.add(sample);
		
		for(int i = 0; i < 10; i++) {
			SampleVO s = new SampleVO();
			s.setName("name"+i);
			s.setAge(i);
			sampleList.add(s);
		}
		return sampleList;
	}
	
	@PutMapping("/getSample2")
	@ResponseBody
	// @RequestMapping(value="/getSample2" , method=RequestMethod.PUT) => 위의 문장과 같은 의미 
	public SampleVO getSample2(@RequestBody SampleVO sample) {
		System.out.println("PUT 요청" + sample);
		return sample;
	}
}

