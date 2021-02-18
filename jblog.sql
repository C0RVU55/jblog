--계정생성 
CREATE USER jblog IDENTIFIED BY jblog;
grant resource, connect to jblog;

--drop
drop table comments;
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

SELECT
    *
FROM blog;

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