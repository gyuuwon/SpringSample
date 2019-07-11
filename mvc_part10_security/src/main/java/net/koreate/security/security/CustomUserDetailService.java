package net.koreate.security.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.koreate.security.dao.MemberDAO;
import net.koreate.security.security.user.CustomUser;
import net.koreate.security.vo.MemberVO;

public class CustomUserDetailService implements UserDetailsService{
	
	@Inject
	MemberDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO vo = null;
				
		System.out.println("loadUserByUsername");
		
		try {
			vo = dao.read(username);
			System.out.println("quired by Member : " + vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo == null ? null : new CustomUser(vo);
	}
	
}
