package net.koreate.service;

import java.util.List;


import net.koreate.util.PageMaker;
import net.koreate.util.SearchCriteria;
import net.koreate.vo.BoardVO;

public interface BoardService {

	// 게시물 작성
	void registReply(BoardVO board) throws Exception;
	
	// 검색 결과에 따른 페이징 처리된 게시물 리스트
	List<BoardVO> listReplyCriteria(SearchCriteria cri) throws Exception;
	
	// 페이징 블럭 정보
	PageMaker getPageMaker(SearchCriteria cri) throws Exception;
	
	// 조회수 증가
	void updateCnt(int bno) throws Exception;
	
	// 게시물 정보
	BoardVO readReply(int bno) throws Exception;
	
	// 첨부파일 목록
	List<String> getAttach(int bno) throws Exception;
	
	// 게시물 삭제
	void remove(int bno) throws Exception;
	
	// 게시물 수정
	void modify(BoardVO vo) throws Exception;
	
	// 답글 등록
	void replyRegister(BoardVO vo) throws Exception;
}
