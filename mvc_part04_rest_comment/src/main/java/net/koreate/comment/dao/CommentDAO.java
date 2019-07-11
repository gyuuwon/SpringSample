package net.koreate.comment.dao;

import java.util.List;
import java.util.Map;

import net.koreate.comment.vo.CommentVO;

public interface CommentDAO {
	
	// 삽입
	void addComment(CommentVO vo) throws Exception;
	
	// 댓글 목록
	List<CommentVO> list(int bno) throws Exception;
	
	// 수정
	void update(CommentVO vo)throws Exception;
	
	// 삭제
	void delete(int cno) throws Exception;
	
	// 전체게시물 갯수
	int count(int bno) throws Exception;
	
	// 페이징 처리된 게시물의 목록
	List<CommentVO> listPage(Map<String,Object> map) throws Exception;
	
	
}








