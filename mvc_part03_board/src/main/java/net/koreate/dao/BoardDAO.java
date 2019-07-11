package net.koreate.dao;

import net.koreate.util.Criteria;
import net.koreate.util.SearchCriteria;
import net.koreate.vo.BoardVO;
import java.util.List;

public interface BoardDAO {
	
	// 게시물 등록
	int create(BoardVO board);
	
	// 전체 게시물 목록
	List<BoardVO> listAll();
	
	// 게시물 상세보기
	BoardVO read(int bno);
	
	// 조회수 증가
	void updateViewCnt(int bno);
	
	// 게시물 수정
	int update(BoardVO board);
	
	// 게시물 삭제
	int remove(int bno);

	List<BoardVO> listCri(Criteria cri);

	int totalCount();
	
	int searchListCount(SearchCriteria cri);
	
	List<BoardVO> searchList(SearchCriteria cri);
	
}




