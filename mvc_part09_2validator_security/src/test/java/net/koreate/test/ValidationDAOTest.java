package net.koreate.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import net.koreate.test.dao.MemberDAO;
import net.koreate.test.vo.ValidationMemberVO;

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:context/root/root-context.xml"})
public class ValidationDAOTest {
	
	@Inject
	private MemberDAO dao;
	
	@Test
	public void testGetMemberById() {
		ValidationMemberVO vo = dao.getMemberByID("aaa@aaa.com");
		if(vo != null) {
			System.out.println(vo);
		}else {
			System.out.println("존재 하지 않음");
		}
	}
	
}




