CREATE TABLE tbl_comment(
	cno INT PRIMARY KEY auto_increment,
	bno INT NOT NULL default 0,
	commentText TEXT NOT NULL,
	commentAuth VARCHAR(50) NOT NULL,
	regdate TIMESTAMP NOT NULL DEFAULT now(),
	updatedate TIMESTAMP NOT NULL DEFAULT now()
);

SELECT * FROM tbl_comment;

INSERT INTO tbl_comment(bno,commentText,commentAuth)
SELECT bno,commentText,commentAuth FROM tbl_comment;