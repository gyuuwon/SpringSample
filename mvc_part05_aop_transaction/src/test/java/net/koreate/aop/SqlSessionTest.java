package net.koreate.aop;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.dao.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"classpath:context/root-context.xml"
})
public class SqlSessionTest {
	
	@Inject
	SqlSession session;
	
	@Inject
	UserDAO dao;
	
	@Test
	public void testSqlSession() {
		System.out.println("session connection : "+session.getConnection());
		System.out.println("UserDAO : "+dao);
	}
}
