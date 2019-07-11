package net.koreate.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.koreate.vo.BanIPVO;
import net.koreate.vo.LoginDTO;
import net.koreate.vo.UserVO;

public interface UserDAO {

	// 회원 정보 삽입
	@Insert("INSERT INTO tbl_user(uid,upw,uname) VALUES(#{uid},#{upw},#{uname})")
	void signUp(UserVO vo) throws Exception;

	// 아이디로 회원정보 확인
	@Select("SELECT * FROM tbl_user WHERE uid = #{uid}")
	UserVO getUserById(String uid) throws Exception;

	// 아이디와 비밀번호로 사용자 정보 확인
	@Select("SELECT * FROM tbl_user WHERE uid = #{uid} AND upw = #{upw}")
	UserVO signIn(LoginDTO dto) throws Exception;

	
	// ban IP
	@Select("SELECT * FROM ban_ip WHERE ip = #{ip}")
	BanIPVO getBanIPVO(String ip) throws Exception;

	@Insert("INSERT INTO ban_ip(ip) VALUES(#{ip})")
	void signInFail(String ip)throws Exception;

	@Update("UPDATE ban_ip SET cnt = cnt + 1, bandate = now() WHERE ip = #{ip}")
	void updateBanIpCnt(String ip) throws Exception;

	@Delete("DELETE FROM ban_ip WHERE ip = #{ip}")
	void removeBanIP(String ip) throws Exception;

}
