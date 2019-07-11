package net.koreate.dao;

import java.util.List;

import net.koreate.vo.MessageVO;

public interface MessageDAO {
	
	// messsage 삽입
	void create(MessageVO vo) throws Exception;
	// opendate 수정
	void updateMessage(int mno) throws Exception;
	// message 정보 
	MessageVO readMessage(int mno) throws Exception;
	// 전체 message 정보
	List<MessageVO> list() throws Exception;

}
