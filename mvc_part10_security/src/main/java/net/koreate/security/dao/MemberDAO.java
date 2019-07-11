package net.koreate.security.dao;

import net.koreate.security.vo.MemberVO;

public interface MemberDAO {

	MemberVO read(String uid) throws Exception;
}
