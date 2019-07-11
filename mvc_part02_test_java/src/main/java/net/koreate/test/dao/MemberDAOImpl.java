package net.koreate.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.koreate.test.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Inject
	private SqlSession session;
	
	private String namespace = "net.koreate.mappers.MemberMapper";
	
	
	@Override
	public void insertMember(MemberVO memberVO) {
		//session.insert("net.koreate.mappers.MemberMapper.insertMember",memberVO);
		System.out.println("MemberVO");
		session.insert(namespace+".insertMember", memberVO);
		System.out.println("삽입완료");
	}
	
	@Override
	public MemberVO readMember(String userid) {
		MemberVO member = session.selectOne(namespace+".readMember",userid);
		return member;
	}
	
	@Override
	public MemberVO readMemberWithPass(String userid, String userpass) {
		
		Map<String, String> mapobject = new HashMap<String, String>();
		mapobject.put("userid", userid);
		mapobject.put("userpw", userpass);
		MemberVO memberpass = session.selectOne(namespace+".readMemberWithPass",mapobject);
		return memberpass;
	}
	
	@Override
	public List<MemberVO> readMemberList() {
		List<MemberVO> memberList = session.selectList(namespace+".readMemberList");
		return memberList;
	}
}
