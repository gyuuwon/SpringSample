DROP TABLE validation_member;

CREATE TABLE validation_member(
	u_no INT PRIMARY KEY auto_increment,			-- 회원번호
	u_id VARCHAR(50) NOT NULL UNIQUE,				-- 회원아이디(email)
	u_pw VARCHAR(200) NOT NULL,						-- 비밀번호
	u_phone VARCHAR(20) NOT NULL,					-- 전화번호
	u_birth VARCHAR(20) NOT NULL,					-- 생년월일
	u_name VARCHAR(20) NOT NULL,					-- 이름
	u_addr VARCHAR(100),							-- 주소
	u_addr_detail VARCHAR(200),						-- 상세주소
	u_addr_post VARCHAR(20),						-- 우편번호
	u_point int default 0,							-- 포인트
	u_info char(1) default 'y',						-- 개인정보 이용 동의
	u_date TIMESTAMP NOT NULL default now(),		-- 계정 생성일
	u_visit_date TIMESTAMP NOT NULL default now(),	-- 최종 방문일(마지막 로그인)
	u_withdraw char(1) default 'n'					-- 회원정보숨김(탈퇴)
);


CREATE TABLE validation_member_auth(
	u_id VARCHAR(50) NOT NULL,
	u_auth VARCHAR(50) NOT NULL,
	constraint fk_member_auth FOREIGN KEY (u_id)
	REFERENCES validation_member(u_id) 
);

DROP TABLE validation_member_auth;


SELECT * FROM validation_member;
SELECT * FROM validation_member_auth;

INSERT INTO validation_member_auth(u_id,u_auth) 
VALUES('id001@gmail.com','ROLE_MASTER')

DELETE FROM validation_member_auth;

DELETE FROM validation_member;
