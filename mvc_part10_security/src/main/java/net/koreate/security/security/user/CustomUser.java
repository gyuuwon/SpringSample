package net.koreate.security.security.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import net.koreate.security.vo.AuthVO;
import net.koreate.security.vo.MemberVO;
// principal
// principal.member
@Getter
public class CustomUser extends User{
	
	private MemberVO member;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(MemberVO vo) {
		super(vo.getUid(),vo.getUpw(),customAuthories(vo.getAuthList()));
		this.member = vo;
	}
	
	public static 
	Collection<? extends GrantedAuthority> 
	customAuthories(List<AuthVO> list){
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(AuthVO auth : list) {
			authorities.add(new SimpleGrantedAuthority(auth.getAuth()));
		}
		return authorities;
	}
	
	
}
