CREATE TABLE board_test(
	bno int PRIMARY KEY auto_increment,
	title VARCHAR(200) NOT NULL,
	content text NULL,
	writer VARCHAR(50) NOT NULL,
	regdate TIMESTAMP NOT NULL DEFAULT now(),
	viewcnt INT DEFAULT 0
);

SELECT count(*) FROM board_test;

INSERT INTO board_test(title,content,writer)
SELECT title,content,writer FROM board_test;

SELECT * FROM board_test ORDER BY bno DESC limit 0, 10;

INSERT INTO board_test(title,content,writer)
VALUES ('test1','test','test');
INSERT INTO board_test(title,content,writer)
VALUES ('test2','test','test');
INSERT INTO board_test(title,content,writer)
VALUES ('test3','test','test');
INSERT INTO board_test(title,content,writer)
VALUES ('test4','test','test');
INSERT INTO board_test(title,content,writer)
VALUES ('test5','test','test');
INSERT INTO board_test(title,content,writer)
VALUES ('test6','test','test');
INSERT INTO board_test(title,content,writer)
VALUES ('test7','test','test');
INSERT INTO board_test(title,content,writer)
VALUES ('test8','test','test');
INSERT INTO board_test(title,content,writer)
VALUES ('test9','test','test');
INSERT INTO board_test(title,content,writer)
VALUES ('test10','test','test');



SELECT * FROM board_test;