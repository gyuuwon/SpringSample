package net.koreate.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.service.BoardService;
import net.koreate.util.PageMaker;
import net.koreate.util.SearchCriteria;
import net.koreate.vo.BoardVO;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	
	@Inject
	BoardService service;
	
	@GetMapping("/list")
	/*@RequestParam(name="searchType", required=false) String searchType, 
	@RequestParam(name="keyword", required=false) String keyword
	*/
	public String list(SearchCriteria cri, Model model) {
		System.out.println(cri);
		List<BoardVO> list = service.searchList(cri);
		model.addAttribute("boardList",list);
		model.addAttribute("cri",cri);
		
		PageMaker pageMaker = service.getPageMaker(cri); 
		model.addAttribute("pageMaker",pageMaker);
		return "sboard/listPage";
	}
	
	@GetMapping("/readPage")
	public String readPage(
			SearchCriteria cri,
			int bno,
			RedirectAttributes rttr) {
		System.out.println("조회수 증가");
		service.updateViewCnt(bno);
		
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("bno",bno);
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		return "redirect:/sboard/readDetail";
	}
	
	@GetMapping("readDetail")
	public String readPage(
			@ModelAttribute("cri") SearchCriteria cri,
			int bno,
			Model model) {
		BoardVO board = service.read(bno);
		model.addAttribute("board",board);
		return "sboard/readPage";
	}
	
	@GetMapping("register")
	public void register() {}
	
	@PostMapping("register")
	public String register(BoardVO board,RedirectAttributes rttr) {
		System.out.println("sboard 게시글 작성 요청");
		System.out.println(board);
		String msg = service.register(board);
		rttr.addFlashAttribute("result",msg);
		return "redirect:/sboard/list";
	}
	
	@GetMapping("modifyPage")
	public String modify(
			int bno,
			Model model,
			@ModelAttribute("cri") SearchCriteria cri) {
		System.out.println(cri);
		
		BoardVO board = service.read(bno);
		model.addAttribute("board",board);
		return "sboard/modify";
	}
	
	@PostMapping("modifyPage")
	public String modifyPost(
				BoardVO board,
				SearchCriteria cri,
				RedirectAttributes rttr) {
		
		System.out.println(board);
		System.out.println(cri);
		
		String msg = service.modify(board);
		rttr.addFlashAttribute("result",msg);
		rttr.addAttribute("bno",board.getBno());
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		return "redirect:/sboard/readDetail";
	}
	
	@PostMapping("remove")
	public String remove(int bno,
			SearchCriteria cri,
			RedirectAttributes rttr) {
		
		System.out.println("게시물 삭제요청 : " + bno);
		String msg = service.remove(bno);
		
		rttr.addFlashAttribute("result",msg);
		
		rttr.addAttribute("page",cri.getPage());
		rttr.addAttribute("perPageNum",cri.getPerPageNum());
		rttr.addAttribute("searchType",cri.getSearchType());
		rttr.addAttribute("keyword",cri.getKeyword());
		return "redirect:/sboard/list";
	}
	
}







