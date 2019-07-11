package net.koreate.comment.service;

import java.util.List;

import net.koreate.comment.util.Criteria;
import net.koreate.comment.util.PageMaker;
import net.koreate.comment.vo.CommentVO;

public interface CommentService {

	// comment 삽입(작성)
	void addComment(CommentVO vo) throws Exception;
	
	// comment 목록
	List<CommentVO> commentList(int bno) throws Exception;
	
	// comment 수정
	void modifyComment(CommentVO vo) throws Exception;
	
	// comment 삭제
	void removeComment(int cno) throws Exception;
	
	// pageMaker 정보를 제공
	PageMaker getPageMaker(int bno, int page) throws Exception;
	
	// 페이징 처리된 댓글 목록
	List<CommentVO> commentListPage(int bno, Criteria cri) throws Exception;
}
