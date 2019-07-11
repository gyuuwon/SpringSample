CREATE TABLE security_member(
	uid VARCHAR(50) PRIMARY KEY,
	upw VARCHAR(100) NOT NULL,
	uname VARCHAR(100) NOT NULL,
	regdate TIMESTAMP DEFAULT now(),
	updatedate TIMESTAMP DEFAULT now()
);

CREATE TABLE security_member_auth(
	uid VARCHAR(50) NOT NULL,
	auth VARCHAR(50) NOT NULL,
	CONSTRAINT fk_memberauth FOREIGN KEY(uid) REFERENCES security_member(uid)
);

INSERT INTO security_member_auth(uid,auth) VALUES('admin99','ROLE_MEMBERSHIP');

SELECT * FROM security_member;

SELECT * FROM security_member_auth;
DELETE FROM security_member_auth;


SELECT mem.uid, upw, uname,regdate, updatedate, auth 
FROM security_member mem LEFT OUTER JOIN security_member_auth mauth 
on mem.uid = mauth.uid 
WHERE mem.uid = 'admin99';

