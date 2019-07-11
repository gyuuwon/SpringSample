package net.koreate.test.dao;

import java.util.ArrayList;
import java.util.List;

import net.koreate.test.vo.AuthVO;
import net.koreate.test.vo.ValidationMemberVO;

public interface MemberDAO {
	
	ValidationMemberVO getMemberByID(String u_id);
	
	void memberJoin(ValidationMemberVO vo);

	void insertAuth(String u_id);

	void updateVisitDate(String u_id);

	List<ValidationMemberVO> getMemberList();

	ArrayList<AuthVO> getAuthList(String u_id);

	// 권한 삭제
	void deleteAuth(AuthVO auth);

	// 권한 삽입
	void insertMemberAuth(AuthVO auth);

	// 활성화 여부 수정
	void deleteYN(ValidationMemberVO vo);



}
