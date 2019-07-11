package net.koreate.security;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.security.dao.MemberDAO;
import net.koreate.security.vo.AuthVO;
import net.koreate.security.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:context/root/root-context.xml", 
})

public class MemberDAOTest {

	@Inject
	MemberDAO dao;
	
	@Test
	public void testRead() throws Exception {
		MemberVO vo = dao.read("admin99");
		System.out.println(vo);
		for(AuthVO auth : vo.getAuthList()) {
			System.out.println(auth);
		}
	}
}
