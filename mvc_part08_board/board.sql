-- 답변형 게시판
ALTER TABLE re_tbl_board ADD CONSTRAINT fk_re_tbl_board_uno
FOREIGN KEY(uno) REFERENCES tbl_user(uno);


CREATE TABLE re_tbl_board(
	bno INT PRIMARY KEY auto_increment,
	title VARCHAR(200) NOT NULL,
	content TEXT NOT NULL,
	writer VARCHAR(45) NOT NULL,
	origin INT NULL DEFAULT 0,
	depth INT NULL DEFAULT 0,
	seq INT NULL DEFAULT 0,
	regdate TIMESTAMP NULL DEFAULT now(),
	updatedate TIMESTAMP NULL DEFAULT now(),
	viewcnt INT NULL DEFAULT 0,
	showboard VARCHAR(10) NULL DEFAULT 'y',
	uno INT NOT NULL
);




ALTER TABLE re_tbl_board add constraint fk_re_tbl_board_uno 
FOREIGN KEY(uno) REFERENCES tbl_user(uno);

ALTER TABLE tbl_comment 
ADD COLUMN uno int not null default 1 AFTER updatedate;


SELECT * FROM re_tbl_board;

DELETE FROM tbl_comment;

ALTER TABLE tbl_comment ADD CONSTRAINT fk_tbl_comment_bno
FOREIGN KEY(bno) REFERENCES re_tbl_board(bno);







commit

-- 첨부파일
CREATE TABLE tbl_attach(
	fullname VARCHAR(200) NOT NULL,
	bno INT NOT NULL,
	regdate TIMESTAMP NULL DEFAULT now(),
	CONSTRAINT fk_tbl_attach FOREIGN KEY(bno) REFERENCES re_tbl_board(bno)
);

DELETE FROM tbl_attach;

SELECT * FROM tbl_attach WHERE bno = 10;
SELECT * FROM tbl_comment WHERE bno = 10;
SELECT * FROM tbl_user;
SELECT * FROM re_tbl_board WHERE bno = 10;
SELECT * FROM tbl_comment;


-- 아이피 차단
DROP TABLE ban_ip;

CREATE TABLE ban_ip(
	ip VARCHAR(50) PRIMARY KEY,
	cnt INT DEFAULT 1,
	bandate TIMESTAMP DEFAULT now()
);






