CREATE TABLE tbl_user(
	uno int PRIMARY KEY auto_increment,
	uid VARCHAR(50) NOT NULL UNIQUE,
	upw VARCHAR(50) NOT NULL,
	uname VARCHAR(50) NOT NULL,
	upoint int NOT NULL DEFAULT 0
);

CREATE TABLE tbl_message(
	mno int PRIMARY KEY auto_increment,
	targetid VARCHAR(50) NOT NULL,
	sender VARCHAR(50) NOT NULL,
	message text NOT NULL,
	opendate TIMESTAMP,
	senddate TIMESTAMP NOT NULL DEFAULT now()
);

ALTER TABLE tbl_message ADD CONSTRAINT fk_user_targetid
FOREIGN KEY(targetid) REFERENCES tbl_user(uid);

ALTER TABLE tbl_message ADD CONSTRAINT fk_user_sender
FOREIGN KEY(sender) REFERENCES tbl_user(uid);

INSERT INTO tbl_user(uid,upw,uname)
VALUES('id001','id001','PIKACHU');

INSERT INTO tbl_user(uid,upw,uname)
VALUES('id002','id002','PIKACHU2');

INSERT INTO tbl_user(uid,upw,uname)
VALUES('id003','id003','PIKACHU3');

SELECT * FROM tbl_user;

===========================================================================================

INSERT INTO tbl_message(targetid,sender,message)
VALUES('id001','id002','PIKAPIKA');

UPDATE tbl_user SET upoint = upoint + 10 WHERE uid = 'id002';

UPDATE tbl_message SET opendate = now() WHERE targetid = 'id001';

UPDATE tbl_user SET upoint = upoint + 5 WHERE uid = 'id001';

============================================================================================

SELECT * FROM tbl_message;
