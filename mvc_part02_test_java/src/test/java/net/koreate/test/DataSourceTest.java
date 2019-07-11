package net.koreate.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.test.config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
@ContextConfiguration(classes= {RootConfig.class})
public class DataSourceTest {
	
	@Inject
	DataSource ds;
	
	@Test
	public void test() {
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
			System.out.println("database 연결 성공 : " + conn);
		} catch (SQLException e) {
			System.out.println("연결정보 오류");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}

	
}
