package net.koreate.service;

import java.util.Map;

import net.koreate.vo.CommentVO;

public interface CommentService {
	
	// 댓글 등록
	void addComment(CommentVO vo) throws Exception;
	
	// 댓글 목록 & 페이징 처리 정보 (List<CommentVO> , PageMaker)
	Map<String,Object> listPage(int bno, int page) throws Exception;
	
	// 댓글 수정
	void modifyComment(CommentVO vo) throws Exception;
	
	// 댓글 삭제
	void removeComment(int cno) throws Exception;
}
