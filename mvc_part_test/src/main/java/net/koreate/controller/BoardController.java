package net.koreate.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.service.BoardService;
import net.koreate.util.Criteria;
import net.koreate.util.PageMaker;
import net.koreate.vo.BoardVO;



@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	BoardService service;
	
	@RequestMapping(value="/register" , method=RequestMethod.GET)
	public void register() {}
	
	@RequestMapping(value="/register" , method=RequestMethod.POST)
	public String register(BoardVO boardVO,RedirectAttributes rttr) {
		String msg = service.register(boardVO);
		System.out.println(msg);
		rttr.addFlashAttribute("result",msg);
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@ModelAttribute("page") int page,
			int bno, 
			Model model) {
		System.out.println("수정페이지 요청 : " + bno);
		model.addAttribute("board",service.read(bno));
		return "/board/modify";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(BoardVO board,
			@ModelAttribute("page") int page,
			RedirectAttributes rttr) {
		System.out.println("게시물 수정 요청 : " + board);
		String msg = service.modify(board);
		rttr.addFlashAttribute("result",msg);
		return "redirect:/board/readDetail?bno="+board.getBno()+"&page="+page;
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(int bno , RedirectAttributes rttr) {
		System.out.println("게시물 삭제 요청 : " +  bno);
		String msg = service.remove(bno);
		rttr.addFlashAttribute("result",msg);
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/listPage" , method=RequestMethod.GET)
	public String listPage(Criteria cri, Model model) {
		System.out.println(cri);
		List<BoardVO> boardList = service.listCri(cri);
		model.addAttribute("boardList",boardList);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.totalCount());
		model.addAttribute("pageMaker",pageMaker);
		System.out.println(pageMaker);
		return "board/listPage";
	}
	
	
	@RequestMapping(value="/readPage",method=RequestMethod.GET)
	public String readPage(
			@RequestParam("page") int page,
			@RequestParam("bno") int bno,
			RedirectAttributes rttr) {
		System.out.println(page);
		System.out.println(bno);
		
		service.updateViewCnt(bno);
		rttr.addFlashAttribute("bno", bno);
		rttr.addFlashAttribute("page", page);
		return "redirect:/board/readDetail?bno="+bno+"&page="+page;
	}
	
	@RequestMapping(value="/readDetail" , method=RequestMethod.GET)
	public String readDetail(Model model,
			@ModelAttribute("bno") int bno,
			@ModelAttribute("page") int page) {
		BoardVO board = service.read(bno);
		model.addAttribute("board", board);
		return "/board/readPage";
	}
	
}
