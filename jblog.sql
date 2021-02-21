--계정생성 
CREATE USER jblog IDENTIFIED BY jblog;
grant resource, connect to jblog;

--drop
drop table blog;
drop SEQUENCE seq_users_no;

commit;
rollback;

--////////////////////////////////
--users

create table users(
    userNo number,
    id VARCHAR2(50) not null unique,
    userName VARCHAR2(100) not null,
    password VARCHAR2(50) not null,
    joinDate date not null,
    PRIMARY KEY (userNo)
);

create SEQUENCE seq_users_no
INCREMENT by 1
start with 1
nocache;

SELECT
    *
FROM users;

--삭제
delete from users
where userNo = 14;

--입력 userNo id userName password joinDate
insert into users
values(seq_users_no.nextval, '아이디', '이름', '비번', sysdate);

--로그인
SELECT  id,
        userNo,
        userName
FROM users
where id='aaa'
and password='111';

--------------------------------
--blog 

create table blog(
    id VARCHAR2(50),
    blogTitle VARCHAR2(200) not null,
    logoFile VARCHAR2(200),
    PRIMARY KEY (id),
    CONSTRAINT blog_fk FOREIGN KEY (id)
    REFERENCES users(id)
);

--출력

SELECT  id,
        blogTitle,
        logoFile
FROM blog;

SELECT  u.id,
        u.userName,
        b.blogTitle,
        b.logoFile
FROM blog b, users u
where b.id = u.id
and u.id='qqq';

--입력 id blogTitle logoFile
insert into blog
values('bbb', '블로그명', 'jblog/assets/images/spring-logo.jpg');

--logoFile 수정
update blog
set logoFile='spring-logo.jpg'
where id = 'www';

--basic 변경1 : 제목
update blog
set blogTitle='제목 수정1'
where id = 'www';

--basic 변경2 : 제목, 로고파일
update blog
set blogTitle='제목 수정2',
    logoFile='로고 수정'
where id = 'www';

--------------------------------
--category

create table category(
    cateNo number,
    id VARCHAR2(50),
    cateName VARCHAR2(200) not null,
    description VARCHAR2(500),
    regDate date not null,
    PRIMARY KEY (cateNo),
    CONSTRAINT category_fk FOREIGN KEY (id)
    REFERENCES blog(id)
);

create SEQUENCE seq_category_no
INCREMENT by 1
start with 1
nocache;

SELECT
    *
FROM category;

--------------------------------
--post

create table post(
    postNo number,
    cateNo number,
    postTitle VARCHAR2(300) not null,
    postContent VARCHAR2(4000),
    regDate date not null,
    PRIMARY KEY (postNo),
    CONSTRAINT post_fk FOREIGN KEY (cateNo)
    REFERENCES category(cateNo)
);

create SEQUENCE seq_post_no
INCREMENT by 1
start with 1
nocache;

SELECT
    *
FROM post;

--------------------------------
--comments

create table comments(
    cmtNo number,
    postNo number,
    userNo number,
    cmtContent VARCHAR2(1000) not null,
    regDate date not null,
    PRIMARY KEY (cmtNo),
    CONSTRAINT comments_fk FOREIGN KEY (postNo)
    REFERENCES post(postNo),
    CONSTRAINT comments_fk2 FOREIGN KEY (userNo)
    REFERENCES users(userNo)
);

create SEQUENCE seq_comments_no
INCREMENT by 1
start with 1
nocache;

SELECT
    *
FROM comments;