package net.koreate.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;
import net.koreate.service.MessageService;
import net.koreate.vo.MessageVO;
import net.koreate.vo.UserVO;

@RestController
@RequestMapping("/messages")
@Log
public class MessageController {
	
	@Inject
	MessageService ms;
	
	@PostMapping("/add")
	public ResponseEntity<String> addMessage(MessageVO vo){
		log.info("/messages/add : "+vo.toString());
		ResponseEntity<String> entity = null;
		
		try {
			ms.addMessage(vo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = 
				new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<MessageVO>> readList(){
		ResponseEntity<List<MessageVO>> entity =null;
		try {
			entity = new ResponseEntity<List<MessageVO>>(ms.list(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
		return entity;
	}
	
	@PatchMapping({"/read/{mno}/{uid}","/read/{mno}"})
	public ResponseEntity<Object> readMessage(
			@RequestBody(required=false) UserVO uVo,
			@PathVariable("mno") int mno,
			@PathVariable(name="uid",required=false) String uid){
		System.out.println("readMessage controller : "+uVo);
		System.out.println("readMessage controller : "+mno + " / " + uid);
		
		if(uid == null || uid.equals("")) {
			uid = uVo.getUid();
		}
		System.out.println("uid : "+uid);
		ResponseEntity<Object> entity = null;
		
		try {
			entity = new ResponseEntity<Object>(ms.readMessage(uid, mno),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	
}
