package net.koreate.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:context/root/root-context.xml", 
	"classpath:context/root/security/security-context.xml"
})
public class DataSourceTest {
	
	@Inject
	DataSource ds;
	
	@Inject
	PasswordEncoder encoder;	
	
	@Test
	public void testInsert() {
		System.out.println("datasource : " + ds);
		/*
		System.out.println("password encode : "+encoder.encode("member"));
		System.out.println("$2a$10$xogrwaKCKMMjB86Ku1pd7Ovlb73E0b3xmKe1DHMx/3RfbXUdmyrbi");*/
		
		for(int i=0; i<100; i++) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = "INSERT INTO security_member(uid,upw,uname) VALUES(?,?,?)";
			try {
				con = ds.getConnection();
				pstmt = 
					con.prepareStatement(sql);
				
				pstmt.setString(2, encoder.encode("pw"+i));
				
				if(i < 80) {
					pstmt.setString(1, "user"+i);
					pstmt.setString(3, "일반사용자"+i);
				}else if(i < 90) {
					pstmt.setString(1, "manager"+i);
					pstmt.setString(3, "운영자"+i);
				}else {
					pstmt.setString(1, "admin"+i);
					pstmt.setString(3, "관리자"+i);
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







