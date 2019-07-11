package net.koreate.test.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.koreate.test.dao.MemberDAO;
import net.koreate.test.vo.ValidationMemberVO;

public class CustomUserDetailsService implements UserDetailsService{
	
	@Inject
	MemberDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ValidationMemberVO vo = dao.getMemberByID(username);
		return vo == null ? null : new CustomMember(vo);
	}
}
