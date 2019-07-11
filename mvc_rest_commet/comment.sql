CREATE TABLE tbl_comment_test(
	cno INT PRIMARY KEY auto_increment,
	bno INT NOT NULL,
	commentText TEXT NOT NULL,
	commentAuth VARCHAR(50) NOT NULL,
	regdate TIMESTAMP NOT NULL DEFAULT now(),
	updatedate TIMESTAMP NOT NULL DEFAULT now()
);

SELECT * FROM tbl_comment_test;

INSERT INTO tbl_comment_test(bno,commentText,commentAuth) SELECT bno,commentText,commentAuth FROM tbl_comment_test;