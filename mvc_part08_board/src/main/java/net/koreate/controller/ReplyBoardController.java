package net.koreate.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.service.BoardService;
import net.koreate.util.SearchCriteria;
import net.koreate.vo.BoardVO;

@Controller
@RequestMapping("/sboard/*")
public class ReplyBoardController {

	@Inject
	BoardService service;

	@GetMapping("register")
	public String registerGET() {
		return "sboard/register";
	}

	@PostMapping("register")
	public String registerPost(BoardVO board) throws Exception {
		System.out.println("registerPost : " + board);
		service.registReply(board);
		return "redirect:/sboard/listReply";
	}

	@GetMapping("listReply")
	public String listReply(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		// 검색 조건에 맞는 페이징 처리된 게시물 항목(한번에 client에 보여줄 게시물)
		model.addAttribute("list", service.listReplyCriteria(cri));
		// 페이징 블럭 정보
		model.addAttribute("pageMaker", service.getPageMaker(cri));
		return "sboard/listReply";
	}

	@GetMapping("readPage")
	public String readPage(
			SearchCriteria cri,
			@RequestParam("bno") int bno,
			RedirectAttributes rttr) throws Exception {
		// 조회수 증가
		System.out.println("readPage : " + cri);
		service.updateCnt(bno);
		rttr.addAttribute("bno", bno);
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		// return "redirect:/sboard/read?bno="+bno;
		return "redirect:/sboard/read";
	}

	@GetMapping("read")
	public String readPage(
			@ModelAttribute("cri") SearchCriteria cri,
			Model model, 
			@RequestParam("bno") int bno) throws Exception {
		// 게시물 정보
		System.out.println("read : " + cri);
		model.addAttribute("boardVO", service.readReply(bno));
		return "sboard/readPage";
	}
	
	@GetMapping("modifyPage")
	public String modifyPage(
			@RequestParam("bno") int bno,
			Model model) throws Exception{
		model.addAttribute("boardVO", service.readReply(bno));		
		return "sboard/modifyPage";
	}
	
	@PostMapping("modifyPage")
	public String modifyPage(
			BoardVO vo,
			RedirectAttributes rttr) throws Exception{
		System.out.println("modifyPage : " + vo);
		
		service.modify(vo);
		
		rttr.addAttribute("bno",vo.getBno());
		return "redirect:/sboard/read";
	}
	
	
	@PostMapping("remove")
	public String remove(@RequestParam("bno") int bno) throws Exception{
		service.remove(bno);
		return "redirect:/sboard/listReply";
	}
	
	@GetMapping("replyRegister")
	public String replyRegister(
			@RequestParam("bno") int bno,
			@ModelAttribute("cri") SearchCriteria cri,
			Model model) throws Exception{
		System.out.println("답글 작성 페이지 요청"+bno +" cri : " + cri);
		
		model.addAttribute("boardVO", service.readReply(bno));
		
		return "sboard/replyRegister";
	}
	
	// 답글 등록 요청
	@PostMapping("replyRegister")
	public String replyRegister(
			SearchCriteria cri,
			BoardVO board,
			RedirectAttributes rttr)  throws Exception{
		System.out.println("답글 등록 요청");
		System.out.println("board : "+ board);
		
		//답글 등록
		service.replyRegister(board);
		
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		return "redirect:/sboard/listReply";
	}
	
	
	
	// /sboard/getAttach/bno
	@GetMapping("/getAttach/{bno}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("bno") int bno) throws Exception {
		System.out.println(bno);
		return service.getAttach(bno);
	}

}
