package net.koreate.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:context/root/root-context.xml", 
	"classpath:context/root/security/security-context.xml"
})
public class MemberAuthTest {
	
	@Inject
	DataSource ds;
	
	@Test
	public void testInsertAuth() {
		String sql = "INSERT INTO security_member_auth(uid,auth) VALUES(?,?)";
		
		for(int i=0; i<100; i++) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = 
					con.prepareStatement(sql);
				
				if(i < 80) {
					pstmt.setString(1, "user"+i);
					pstmt.setString(2, "ROLE_USER");
				}else if(i < 90) {
					pstmt.setString(1, "manager"+i);
					pstmt.setString(2, "ROLE_MEMBERSHIP");
				}else {
					pstmt.setString(1, "admin"+i);
					pstmt.setString(2, "ROLE_MASTER");
				}
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException e) {}
				
			}
		}
	}
	
}



