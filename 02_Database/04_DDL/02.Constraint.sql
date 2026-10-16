-- 제약조건

-- 컬럼레벨
-- 테이블 레벨
-- 복합키나 제약조건 이름을 명시할 때는 테이블레벨 정의를 쓴다.

-- NOT NULL : NULL SAVE CONSTRAINT
-- UNIQUE   : DUPLICATING CONSTRAINT

DROP TABLE IF EXISTS user_notnull;

CREATE TABLE user_notnull
(
    user_no   INT,
    user_id   VARCHAR(255) NOT NULL,
    user_pwd  VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    gender    VARCHAR(3),
    phone     VARCHAR(255) NOT NULL,
    email     VARCHAR(255),
    -- CONSTRAINT uq_user_unique UNIQUE (phone),  -- TABLE LEVEL CONSTRAINT
    CONSTRAINT PRIMARY KEY (user_no)
);

INSERT INTO user_notnull
(user_no, user_id, user_pwd, user_name, gender, phone, email)
VALUES
    (1, 'user01', 'pass01', '홍길동', '남', '010-1234-5678', 'hong123@gmail.com'),
    (2, 'user02', 'pass02', '유관순', '여', '010-777-7777', 'yu77@gmail.com');

INSERT INTO user_notnull
(user_no, user_id, user_pwd, user_name, gender, phone, email)
VALUES
    (3, 'user01', 'pass01', '홍길동', '남', '010-1234-5678', 'hong123@gmail.com');

-- FOREIGN KEY
-- foreign key of child table is only can match with key from parent table



DROP TABLE IF EXISTS user_grade;
CREATE TABLE IF NOT EXISTS user_grade (
                                          grade_code INT UNIQUE,
                                          grade_name VARCHAR(255) NOT NULL
) ENGINE=INNODB;

INSERT INTO user_grade
VALUES
    (10, '일반회원'),
    (20, '우수회원'),
    (30, '특별회원');

SELECT * FROM user_grade;


-- RESTRICT : 자식이 참조중이면 부모의 수정-삭제를 거부한다.
-- CASCADE  : 부모의 변경을 자식에도 반영
-- SET NULL : 부모가 수정, 삭제되면 자식의 외래키를 null로 변경 ::위험할 수 있어 잘 사용되지 않음
create table user_fk_table
(
    user_no int primary key ,
    user_id varchar(50) not null ,
    user_name varchar(50) not null,
    grade_code int null,
    constraint foreign key (grade_code)
    references user_grade (grade_code)
    on update set null -- 부모가 수정되면 외래키를 null로 변경
    on DELETE set null -- 부모가 삭제되면 외래키를 null로 변경
);

create table user_grade
(
    grade_code int primary key,
    grade_name varchar(50) not null
);

insert into user_grade
values
    (10, '일반회원'),
    (20, '우수회원'),
    (30, '특별회원');

-- 참조 무결성
-- 자식 테이블의 foreign key에는 부모 테이블에 존재하는 값만 저장 가능
insert into user_fk_table
values
    (1, 'user01', '홍길동', 10),
    (2, 'user02', '유관순', 20);

UPDATE user_grade
set grade_code = 11
WHERE grade_code = 10;

DELETE FROM user_grade
WHERE grade_code = 20;


-- CASCADE :: 부모가 변경되면 자식도 바뀌어서 간다.
DROP TABLE IF EXISTS user_grade_2;

create table IF NOT EXISTS user_grade_2
(
    grade_code int primary key,
    grade_name varchar(50) not null
);

INSERT INTO user_grade_2
VALUES
    (10, '일반회원'),
    (20, '우수회원'),
    (30, '특별회원');

create table IF NOT EXISTS user_fk_table_2
(
    user_no int primary key ,
    user_id varchar(50) not null ,
    user_name varchar(50) not null,
    grade_code int null,
    constraint foreign key (grade_code)
        references user_grade_2 (grade_code)
        on update CASCADE -- 부모의 키가 변경되면 자식키도 변경
        on delete CASCADE -- 부모의 키가 삭제되면 자식키도 삭제
);

insert into user_fk_table_2
values
    (1, 'user01', '홍길동',10),
    (2, 'user02', '유관순', 20);

-- grade_code 10 -> 11 로 변경
update user_grade_2
set grade_code = 11
where grade_code = 10;

delete from user_grade_2
where grade_code = 20;

select * from user_grade_2;
select * from user_fk_table_2;