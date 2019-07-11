package net.koreate.db;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class MybatisTest {
	
	@Inject
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testSessionFactory() {
		SqlSession session = sqlSessionFactory.openSession();
		System.out.println("연결정보 생성완료 : " + session);
		System.out.println("Connection sqlSession : " + session.getConnection());
	}
	
}
