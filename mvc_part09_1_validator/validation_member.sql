CREATE TABLE validation_member(
	u_no INT PRIMARY KEY auto_increment,			-- 회원번호
	u_id VARCHAR(50) NOT NULL UNIQUE,				-- 회원아이디(email)
	u_pw VARCHAR(50) NOT NULL,						-- 비밀번호
	u_phone VARCHAR(20) NOT NULL,					-- 전화번호
	u_birth VARCHAR(20) NOT NULL,					-- 생년월일
	u_name VARCHAR(20) NOT NULL,					-- 이름
	u_addr VARCHAR(20),								-- 주소
	u_addr_detail VARCHAR(20),						-- 상세주소
	u_addr_post VARCHAR(20),						-- 우편번호
	u_point INT DEFAULT 0,							-- 포인트
	u_info CHAR(1) DEFAULT 'Y',						-- 개인정보 이용 동의
	u_date TIMESTAMP NOT NULL DEFAULT now(),		-- 계정 생성일
	u_visit_date TIMESTAMP NOT NULL DEFAULT now(),	-- 최종 방문일(마지막 로그인)
	u_withdraw CHAR(1) DEFAULT 'N'					-- 회원정보숨김(탈퇴)
);

SELECT * FROM validation_member;