package net.koreate.service;

import net.koreate.vo.LoginDTO;
import net.koreate.vo.UserVO;

public interface UserService {

	// 회원가입
	void signUp(UserVO vo) throws Exception;
	
	// 로그인 처리
	UserVO signIn(LoginDTO dto) throws Exception;

	// 아이디 확인
	UserVO getUserById(String uid) throws Exception;
}
