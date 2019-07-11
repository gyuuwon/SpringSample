package net.koreate.security.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	private String uid;
	private String upw;
	private String uname;
	private Date regdate;
	private Date updatedate;
	
	private List<AuthVO> authList;
}
