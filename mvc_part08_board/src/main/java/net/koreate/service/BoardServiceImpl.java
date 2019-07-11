package net.koreate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.koreate.dao.BoardDAO;
import net.koreate.dao.CommentDAO;
import net.koreate.util.PageMaker;
import net.koreate.util.SearchCriteria;
import net.koreate.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	BoardDAO dao;
	
	@Inject
	CommentDAO commentDAO;

	@Override
	public void registReply(BoardVO board) throws Exception {
		// 게시물 등록 / origin update / 첨부파일 등록
		dao.register(board);
		dao.updateOrigin();

		String[] files = board.getFiles();

		if (files == null) {
			return;
		}

		for (String fullName : files) {
			dao.addAttach(fullName);
		}
		System.out.println("register 작업 완료");
	}

	@Override
	public List<BoardVO> listReplyCriteria(SearchCriteria cri) throws Exception {
		List<BoardVO> list = dao.listReplyCriteria(cri);
		
		for(BoardVO board : list) {
			board.setCommentCnt(commentDAO.totalCount(board.getBno()));
		}
		
		return list;
	}
	
	@Override
	@Transactional
	public void modify(BoardVO vo) throws Exception {
		// 게시물 정보 갱신
		dao.update(vo);
		
		// 첨부된 파일 정보 갱신
		dao.deleteAttach(vo.getBno());
		
		String[] files = vo.getFiles();
		if(files == null) {return;}
		
		for(String fullName : files) {
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("bno",vo.getBno());
			paramMap.put("fullName",fullName);
			dao.replaceAttach(paramMap);
		}
	}

	@Override
	@Transactional
	public void remove(int bno) throws Exception {
		// 게시글 삭제
		dao.delete(bno);
		// 첨부파일 삭제
		dao.deleteAttach(bno);
		// 댓글 삭제
		commentDAO.deleteComments(bno);
	}

	@Override
	public PageMaker getPageMaker(SearchCriteria cri) throws Exception {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(dao.listReplyCount(cri));
		return pageMaker;
	}

	@Override
	public void updateCnt(int bno) throws Exception {
		dao.updateCnt(bno);
	}

	@Override
	public BoardVO readReply(int bno) throws Exception {
		return dao.readReply(bno);
	}
	
	@Transactional
	@Override
	public void replyRegister(BoardVO vo) throws Exception {
		// seq 오름 차순이기 때문에 기존글의 seq 값을 높여준다
		
		// 기존 글의 정렬 순서 수정
		dao.updateReply(vo);
		
		System.out.println("넘어온 값 : "+vo);
		// 원본글 보다 아래로 내려가고 원본글의 답글인걸 표현 하기 위해 깊이 값을 수정
		vo.setDepth(vo.getDepth()+1);
		vo.setSeq(vo.getSeq()+1);
		
		System.out.println("등록된 값 : "+vo);
		// 게시물 등록
		dao.replyRegister(vo);
	}

	@Override
	public List<String> getAttach(int bno) throws Exception {
		return dao.getAttach(bno);
	}

}
