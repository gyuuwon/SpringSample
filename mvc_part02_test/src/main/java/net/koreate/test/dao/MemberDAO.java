package net.koreate.test.dao;

import java.util.List;

import net.koreate.test.vo.MemberVO;

public interface MemberDAO {

	public void insertMember(MemberVO memberVO);

	public MemberVO readMember(String userid);
	
	public MemberVO readMemberWithPass(String userid,String userpass);
	
	public List<MemberVO> readMemberList();
}
