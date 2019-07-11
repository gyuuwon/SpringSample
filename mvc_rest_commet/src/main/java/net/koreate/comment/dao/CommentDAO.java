package net.koreate.comment.dao;

import java.util.List;

import net.koreate.comment.vo.CommentVO;

public interface CommentDAO {
	
	void addComment(CommentVO vo) throws Exception;
	
	List<CommentVO> list(int bno) throws Exception;
	
	void update(CommentVO vo) throws Exception;
	
	void delete(int cno) throws Exception;
	
	int count(int bno) throws Exception;

	
}
