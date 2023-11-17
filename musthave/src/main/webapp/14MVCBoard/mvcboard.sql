CREATE DATABASE gonsta;
USE gonsta;


CREATE TABLE board
(
    board_id    INT              NOT NULL    AUTO_INCREMENT COMMENT '게시글ID',
    writer      VARCHAR(50)      NOT NULL    COMMENT '작성자',
    title       VARCHAR(50)      NOT NULL    COMMENT '제목',
    content     VARCHAR(1000)    NOT NULL    COMMENT '내용',
    regdate     DATETIME         NOT NULL    COMMENT '등록일자',
    updatedate  DATETIME         NULL        COMMENT '수정일자',
    deletedate  DATETIME         NULL        COMMENT '삭제일자',
    PRIMARY KEY (board_id)
);


CREATE TABLE likes(
    member_idx	INT				 NOT NULL	 COMMENT '회원IDX'
        post_idx	INT				 NOT NULL	 COMMENT '게시글IDX'
        CONSTRAINT	likes_PK PRIMARY KEY (MEMBER_idx, post_idx)
);

use model1;
select * from mvcboard;

drop table if EXISTS mvcboard;
create table mvcboard (
                          idx 		int 	  primary key auto_increment,
                          name 		varchar(50) 				not null,
                          title 		varchar(200) 				not null,
                          content 	varchar(2000) 				not null,
                          postdate 	date default (current_date) not null,
                          ofile		varchar (200),
                          sfile 		varchar (30),
                          downcount 	int (5) 	default 0 		not null,
                          pass 		varchar(50) 				not null,
                          visitcount  int 		default 0 		not null
);


insert into mvcboard (name, title, content, pass) values ('김유신','자료실 제목1 입니다.','내용','1234');
insert into mvcboard (name, title, content, pass) values ('장보고','자료실 제목2 입니다.','내용','1234');
insert into mvcboard (name, title, content, pass) values ('이순신','자료실 제목3 입니다.','내용','1234');
insert into mvcboard (name, title, content, pass) values ('강감찬','자료실 제목4 입니다.','내용','1234');
insert into mvcboard (name, title, content, pass) values ('대조영','자료실 제목5 입니다.','내용','1234');
