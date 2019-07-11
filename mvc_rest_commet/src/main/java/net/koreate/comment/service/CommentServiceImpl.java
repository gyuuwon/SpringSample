package net.koreate.comment.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.comment.dao.CommentDAO;
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
	
	
}
