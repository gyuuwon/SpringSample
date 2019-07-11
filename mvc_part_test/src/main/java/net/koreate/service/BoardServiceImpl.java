package net.koreate.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.BoardDAO;
import net.koreate.util.Criteria;
import net.koreate.util.PageMaker;
import net.koreate.util.SearchCriteria;
import net.koreate.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	BoardDAO dao;

	@Override
	public String register(BoardVO board) {
		int result = dao.create(board);
		return getResult(result);
	}

	@Override
	public List<BoardVO> listAll() {
		return dao.listAll();
	}

	@Override
	public BoardVO read(int bno) {
		return dao.read(bno);
	}

	@Override
	public void updateViewCnt(int bno) {
		dao.updateViewCnt(bno);
	}

	@Override
	public String modify(BoardVO board) {
		int result = dao.update(board);
		String msg = getResult(result);
		return msg;
	}

	@Override
	public String remove(int bno) {
		int result  = dao.remove(bno);
		String msg = getResult(result);
		return msg;
	}
	
	@Override
	public List<BoardVO> listCri(Criteria cri) {
		return dao.listCri(cri);
	}

	@Override
	public int totalCount() {
		return dao.totalCount();
	}
	

	@Override
	public int searchListCount(SearchCriteria cri) {
		return 0;
	}
	
	@Override
	public PageMaker getPageMaker(Criteria cri) {
		int totalCount = dao.totalCount();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		return pageMaker;
	}
	
	public String getResult(int result) {
		String msg = "FAIL";
		if(result > 0) {
			msg = "SUCCESS";
		}
		return msg;
	}

	@Override
	public List<BoardVO> searchList(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
