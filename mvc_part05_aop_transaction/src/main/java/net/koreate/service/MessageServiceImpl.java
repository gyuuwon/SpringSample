package net.koreate.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.koreate.dao.MessageDAO;
import net.koreate.dao.UserDAO;
import net.koreate.vo.MessageVO;
import net.koreate.vo.UserVO;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Inject
	MessageDAO messageDao;
	
	@Inject
	UserDAO userDAO;
	

	@Override
	@Transactional
	public void addMessage(MessageVO vo) throws Exception {
		System.out.println("add message Service 호출");
		System.out.println("add message Service : "+ vo);
		// 발신 사용자 포인트 10 증가
		// targetid id008
		// sender id002
		// 안녕하세요
		UserVO uv = new UserVO();
		uv.setUid(vo.getSender());
		uv.setUpoint(10);
		System.out.println(uv);
		userDAO.updatePoint(uv);
		//메시지 등록
		messageDao.create(vo);
		System.out.println("add message Service 종료");
	}

	@Override
	public MessageVO readMessage(String uid, int mno) throws Exception {
		System.out.println("readMessage Service 호출");
		System.out.println("readMessage Service "+uid+"/"+mno);
		// opendate 읽은 현재시간으로 수정
		messageDao.updateMessage(mno);
		
		// targetid (메시지 읽은 대상) + 5point
		UserVO uv = new UserVO();
		uv.setUid(uid);
		uv.setUpoint(5);
		userDAO.updatePoint(uv);
		
		// 수정된 Message 정보 
		MessageVO message = messageDao.readMessage(mno);
		
		System.out.println("readMessage Service 종료");
		return message;
	}

	@Override
	public List<MessageVO> list() throws Exception {
		return messageDao.list();
	}
	
}
