DROP TABLE IF EXISTS member;
CREATE TABLE member(
	id varchar(10) NOT NULL,
	pass varchar(10) NOT NULL,
	name varchar(10) NOT NULL,
	register date DEFAULT (current_date) NOT NULL,
	PRIMARY key(id)
);

DROP TABLE IF EXISTS board;
CREATE TABLE board(
	num int PRIMARY KEY AUTO_INCREMENT,
	title varchar(20) NOT NULL,
	content varchar(200) NOT NULL,
	id varchar(10) NOT NULL,
	postate date DEFAULT (current_date) NOT NULL,
	visitcount decimal(6)
);

ALTER TABLE board
  ADD CONSTRAINT board_mem_fk FOREIGN KEY (id)
  REFERENCES MEMBER (id);

INSERT INTO member(id, pass, name) values('musthave', '1234', '머스트해브');
SELECT * FROM MEMBER;


INSERT INTO board(title, content, id, visitcount) values('제목1입니다.', '내용1입니다.','musthave', 0);
 SELECT * FROM board;

