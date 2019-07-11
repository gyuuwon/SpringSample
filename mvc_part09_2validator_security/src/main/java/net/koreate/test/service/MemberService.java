package net.koreate.test.service;

import java.util.List;

import net.koreate.test.vo.AuthVO;
import net.koreate.test.vo.ValidationMemberVO;

public interface MemberService {
	// 회원가입
	void memberJoin(ValidationMemberVO vo) throws Exception;
	
	// 아이디로 사용자 정보 확인
	boolean getMemberByID(String u_id) throws Exception;
	
	// 최종 방문일 수정
	void updateVisitDate(String u_id);

	// 사용자 리스트 정보
	List<ValidationMemberVO> getMemberList() throws Exception;

	
	List<AuthVO> updateAuth(AuthVO auth) throws Exception;

	// 활성화 여부
	void deleteYN(ValidationMemberVO vo);
}
