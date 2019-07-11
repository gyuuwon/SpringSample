package net.koreate.comment.service;

import java.util.List;

import net.koreate.comment.vo.CommentVO;

public interface CommentService {
	
	void addComment(CommentVO vo) throws Exception;
	
	List<CommentVO> commentList(int bno) throws Exception;
	
	void modifyComment(CommentVO vo) throws Exception;
	
	void removeComment(int cno) throws Exception;
	
}
