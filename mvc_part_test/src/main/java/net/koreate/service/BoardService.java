package net.koreate.service;

import java.util.List;

import net.koreate.util.Criteria;
import net.koreate.util.PageMaker;
import net.koreate.util.SearchCriteria;
/*import net.koreate.util.Criteria;
import net.koreate.util.PageMaker;
import net.koreate.util.SearchCriteria;*/
import net.koreate.vo.BoardVO;

public interface BoardService {

	// 게시물 등록
		String register(BoardVO board);
		
		// 전체 게시물 목록 
		List<BoardVO> listAll();
		
		// 게시물 상세보기
		BoardVO read(int bno);
		
		// 조회수 증가
		void updateViewCnt(int bno);
		
		// 게시물 수정
		String modify(BoardVO board);
		
		// 게시물 삭제
		String remove(int bno);
		
		// Cri 게시물
		List<BoardVO> listCri(Criteria cri);

		int totalCount();
		
		// 페이지 블럭 처리를 위한 전체 게시물 개수
		int searchListCount(SearchCriteria cri);
		
		// 페이징 처리된 검색 게시물 
		List<BoardVO> searchList(SearchCriteria cri);

		PageMaker getPageMaker(Criteria cri);

	}

