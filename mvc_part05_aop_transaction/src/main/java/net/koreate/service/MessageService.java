package net.koreate.service;

import java.util.List;

import net.koreate.vo.MessageVO;

public interface MessageService {
	
	void addMessage(MessageVO vo) throws Exception;
	
	MessageVO readMessage(String uid, int mno) throws Exception;
	
	List<MessageVO> list() throws Exception;
	

}
