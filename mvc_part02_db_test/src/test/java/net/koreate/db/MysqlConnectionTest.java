package net.koreate.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class MysqlConnectionTest {

	String driver ="com.mysql.cj.jdbc.Driver";
	String url ="jdbc:mysql://localhost:3306/mydata?serverTimezone=Asia/Seoul";
	String username ="java";
	String password ="java";
	
	@Test
	public void testConnection() {
		
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			System.out.println("DRIVER 연결성공 : " + con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버를 찾을 수 없음");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("계정 정보 오류");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
