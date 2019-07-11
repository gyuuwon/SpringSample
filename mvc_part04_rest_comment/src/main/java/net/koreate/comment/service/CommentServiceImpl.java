package net.koreate.comment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.comment.dao.CommentDAO;
import net.koreate.comment.util.Criteria;
import net.koreate.comment.util.PageMaker;
import net.koreate.comment.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Inject
	CommentDAO dao;

	@Override
	public void addComment(CommentVO vo) throws Exception {
		dao.addComment(vo);
	}

	@Override
	public List<CommentVO> commentList(int bno) throws Exception {
		return dao.list(bno);
	}

	@Override
	public void modifyComment(CommentVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void removeComment(int cno) throws Exception {
		dao.delete(cno);
	}

	@Override
	public PageMaker getPageMaker(int bno, int page) throws Exception {
		Criteria cri = new Criteria();
		cri.setPage(page);
		cri.setPerPageNum(20);
		
		int commentListCount = dao.count(bno);
		System.out.println(commentListCount);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(commentListCount);
		return pageMaker;
	}

	@Override
	public List<CommentVO> commentListPage(int bno, Criteria cri) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("cri", cri);
		return dao.listPage(map);
	}
	
	
	
	
	
	
}
