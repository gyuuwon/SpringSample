package net.koreate.db.dao;

import net.koreate.db.vo.MemberVO;

public interface MemberDAO {
	
	public void insertMember(MemberVO memberVO);
	
	public MemberVO readMember(String userId);
	
}
