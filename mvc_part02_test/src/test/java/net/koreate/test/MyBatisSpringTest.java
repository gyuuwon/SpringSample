package net.koreate.test;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MyBatisSpringTest {

	@Inject
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testConnection() {
		try(SqlSession session = sqlSessionFactory.openSession()){
			System.out.println("연결 정보 객체 생성 완료 : " + session);
			System.out.println("con : " + session.getConnection());
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("객체 생성 오류");
		}
	}
	
	
}
