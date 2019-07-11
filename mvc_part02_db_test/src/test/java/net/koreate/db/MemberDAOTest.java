package net.koreate.db;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.db.dao.MemberDAO;
import net.koreate.db.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class MemberDAOTest {
	
	@Inject
	MemberDAO dao;
	
	@Test
	public void testInsertMember() {
		System.out.println("dao instance : "+dao);
		MemberVO member = new MemberVO();
		member.setUserid("user01");
		member.setUserpw("pass01");
		member.setUsername("홍길동");
		dao.insertMember(member);
	}

}
