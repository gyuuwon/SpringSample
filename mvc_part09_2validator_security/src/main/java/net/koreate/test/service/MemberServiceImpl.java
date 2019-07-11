package net.koreate.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.koreate.test.dao.MemberDAO;
import net.koreate.test.vo.AuthVO;
import net.koreate.test.vo.ValidationMemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Inject 
	MemberDAO dao;
	
	@Inject
	PasswordEncoder encoder;
	
	@Override
	public boolean getMemberByID(String u_id) throws Exception {
		return dao.getMemberByID(u_id) == null ? true : false;
	}

	@Override
	public void updateVisitDate(String u_id) {
		dao.updateVisitDate(u_id);
	}




	@Transactional
	@Override
	public void memberJoin(ValidationMemberVO vo) throws Exception {
		// 회원등록   , 회원 기본 권한 제공(ROLE_USER) 일반 사용자
		
		// 회원등록
		System.out.println("before pass: "+vo.getU_pw());
		vo.setU_pw(encoder.encode(vo.getU_pw()));
		System.out.println("adter pass: "+vo.getU_pw());
		dao.memberJoin(vo);
		
		// 권한 부여
		dao.insertAuth(vo.getU_id());		
		
	}

	@Override
	public List<ValidationMemberVO> getMemberList() throws Exception {
		return dao.getMemberList();
	}

	@Override
	public List<AuthVO> updateAuth(AuthVO auth) throws Exception {
		// 기존 권한 목록 호출
		ArrayList<AuthVO> beforeList = dao.getAuthList(auth.getU_id());
		
		// 권한 존재 시 권한 삭제
		boolean isNull = true;
		for(AuthVO a : beforeList) {
			if(auth.getU_auth().equals(a.getU_auth())) {
				dao.deleteAuth(a);
				isNull = false;
				break;
			}
		}
		
		// 권한 없을 시 삽입
		if(isNull) {
			// 권한삽입
			dao.insertMemberAuth(auth);
		}
		// 권한 목록 재호출
		ArrayList<AuthVO> afterList = dao.getAuthList(auth.getU_id());
		return afterList;
	}

	@Override
	public void deleteYN(ValidationMemberVO vo) {
		dao.deleteYN(vo);
	}
	
}
