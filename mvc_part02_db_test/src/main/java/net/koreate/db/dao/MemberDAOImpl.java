package net.koreate.db.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.koreate.db.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Inject
	private SqlSession session;
	
	private String nameSpace = "net.koreate.mappers.MemberMapper";
	
	@Override
	public void insertMember(MemberVO memberVO) {
		System.out.println("insertMember : "+memberVO);
		session.insert(nameSpace+".insertMember",memberVO);
	}

	@Override
	public MemberVO readMember(String userId) {
		return null;
	}

	

}
