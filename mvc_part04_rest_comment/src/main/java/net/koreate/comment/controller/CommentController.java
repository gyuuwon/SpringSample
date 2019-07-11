package net.koreate.comment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.koreate.comment.service.CommentService;
import net.koreate.comment.util.Criteria;
import net.koreate.comment.util.PageMaker;
import net.koreate.comment.vo.CommentVO;

@RestController
@RequestMapping("/comments")
public class CommentController {
	
	@Inject
	CommentService cs;
	
	@PostMapping("")
	public ResponseEntity<String> register(@RequestBody CommentVO comment){
		ResponseEntity<String> entity = null;
		System.out.println("comments 요청 : "+comment);
		try {
			cs.addComment(comment);
			entity = new ResponseEntity<>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}	
		return entity;
	}
	
	@GetMapping("/all/{bno}")
	public ResponseEntity<List<CommentVO>> list(@PathVariable("bno") int bno){
		System.out.println("list bno : " + bno);
		ResponseEntity<List<CommentVO>> entity = null;
		
		try {	
			List<CommentVO> list = cs.commentList(bno);
			entity = new ResponseEntity<List<CommentVO>>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	@PatchMapping("/{cno}")
	public ResponseEntity<String> update(
			@PathVariable("cno") int cno,
			@RequestBody CommentVO comment){
		ResponseEntity<String> entity = null;
		try {
			comment.setCno(cno);
			System.out.println(comment);
			cs.modifyComment(comment);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@DeleteMapping("/{cno}")
	public ResponseEntity<String> delete(@PathVariable("cno") int cno){
		ResponseEntity<String> entity = null;
		
		try {
			cs.removeComment(cno);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@GetMapping("/{bno}/{page}")
	public ResponseEntity<Map<String,Object>> listPage(
			@PathVariable("bno") int bno,
			@PathVariable("page") int page){
		ResponseEntity<Map<String,Object>> entity = null;
		
		try {
			Map<String,Object> map = new HashMap<>();
			PageMaker pageMaker = cs.getPageMaker(bno, page);
			Criteria cri = new Criteria();
			cri.setPage(page);
			cri.setPerPageNum(20);
			List<CommentVO> list = cs.commentListPage(bno, cri);
			map.put("pageMaker", pageMaker);
			map.put("list", list);
			entity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}



















