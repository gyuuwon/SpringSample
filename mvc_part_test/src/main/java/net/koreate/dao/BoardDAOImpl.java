package net.koreate.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.koreate.util.Criteria;
import net.koreate.util.SearchCriteria;
import net.koreate.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Inject
	SqlSession session;
	
	String namespace = "net.koreate.mappers.BoardMapper";
	

	@Override
	public int create(BoardVO board) {
		int result = session.insert(namespace+".create",board);
		System.out.println("result : " + result);
		return result;
	}

	@Override
	public List<BoardVO> listAll() {
		return session.selectList(namespace+".listAll");
	}

	@Override
	public BoardVO read(int bno) {
		BoardVO board = session.selectOne(namespace+".read",bno);
		return board;
	}

	@Override
	public void updateViewCnt(int bno) {
		session.update(namespace+".updateViewCnt", bno);
		
	}

	@Override
	public int update(BoardVO board) {
		int result = session.update(namespace+".update",board);
		return result;
	}

	@Override
	public int remove(int bno) {
		int result = session.delete(namespace+".remove",bno);
		System.out.println("삭제결과 : " + result);
		return result;
	}

	@Override
	public List<BoardVO> listCri(Criteria cri) {
		List<BoardVO> list = session.selectList(namespace+".listCri",cri);
		return list;
	}

	@Override
	public int totalCount() {
		return session.selectOne(namespace+".totalCount");
	}

	@Override
	public int searchListCount(SearchCriteria cri) {
		return 0;
	}

	
}
