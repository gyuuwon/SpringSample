package net.koreate.test.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import net.koreate.test.vo.AuthVO;
import net.koreate.test.vo.ValidationMemberVO;

@Getter
public class CustomMember extends User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ValidationMemberVO member;

	public CustomMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomMember(ValidationMemberVO vo) {
		super(vo.getU_id(), vo.getU_pw(), authorities(vo.getAuthList()));
		this.member = vo;
	}

	public static Collection<? extends GrantedAuthority> authorities(List<AuthVO> list){
		List<GrantedAuthority> glist = new ArrayList<>();
		for(AuthVO auth : list) {
			glist.add(new SimpleGrantedAuthority(auth.getU_auth()));
		}
		return glist;
	}
	
}
