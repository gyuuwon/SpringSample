package net.koreate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.CommentDAO;
import net.koreate.util.Criteria;
import net.koreate.util.PageMaker;
import net.koreate.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Inject
	CommentDAO dao;

	@Override
	public void addComment(CommentVO vo) throws Exception {
		dao.addComment(vo);
	}

	@Override
	public Map<String, Object> listPage(int bno, int page) throws Exception {
		Map<String, Object> map = new HashMap<>();
		PageMaker pageMaker = getPageMaker(bno,page);
		map.put("pageMaker", pageMaker);
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bno", bno);
		paramMap.put("cri", pageMaker.getCri());
	
		List<CommentVO> list = dao.listPage(paramMap);
		map.put("list", list);
		return map;
	}
	
	@Override
	public void modifyComment(CommentVO vo) throws Exception {
		dao.modifyComment(vo);
	}
	
	@Override
	public void removeComment(int cno) throws Exception {
		dao.removeComment(cno);
	}
	
	PageMaker getPageMaker(int bno, int page) throws Exception{
		PageMaker pageMaker = new PageMaker();
		
		Criteria cri = new Criteria();
		cri.setPage(page);
		
		pageMaker.setCri(cri);
		int totalCount = dao.totalCount(bno);
		pageMaker.setTotalCount(totalCount);
		return pageMaker;
	}

	

	
}
