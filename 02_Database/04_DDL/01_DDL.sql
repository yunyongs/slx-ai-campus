-- DDL (Data Definition Language) 데이터 정의어
-- 데이터베이스의 구조를 생성, 변경, 초기화, 삭제할 때 사용


-- CREATE   :
-- ALTER    :
-- TRUNCATE :
-- DROP     :

CREATE TABLE IF NOT EXISTS tb1
(
    pk int,
    fk int,
    col1 VARCHAR(255),
    CHECK (col1 in ('Y', 'N')  ) -- 제약조건
);

DESCRIBE tb1; -- 구조 확인

INSERT INTO tb1
VALUES (1, 10, 'test'); -- check 조건을 위반함

INSERT INTO tb1
VALUES (1, 10, 'Y'); -- check 조건을 만족

SELECT * FROM tb1;

-- tb2
-- auto_increment : 행을 추가할 때 자동으로 번호를 생성해 주는 기능
-- Primary Key 또는 unique key가 설정된 컬럼에 사용

CREATE TABLE IF NOT EXISTS tb2
(
    pk int AUTO_INCREMENT PRIMARY KEY ,
    fk int,
    col1 VARCHAR(255),
    CHECK (col1 in ('Y', 'N')  ) -- 제약조건
);

DESCRIBE tb2;

INSERT INTO tb2
VALUES
    (null,10, 'Y'),
    (null,10, 'Y')
;

SELECT * from tb2;

-- 수정
ALTER TABLE tb2
ADD COLUMN col2 int NOT NULL DEFAULT 0;
DESCRIBE tb2;

-- drop (컬럼 삭제)
ALTER TABLE tb2
DROP COLUMN col2;

-- 컬럼 수정
-- 이름, 컬럼정의 (데이터타입, 제약조건) 수정
alter TABLE tb2
CHANGE column fk change_fk BIGINT not NULL ;
DESCRIBE tb2;

alter TABLE tb2
    MODIFY column change_fk INT ; -- modify는 이름은 변경하지 않고, 데이터 타입, 제약조건(null, not null 등) 다시 작성. 이 코드에서 not null을 입력하지 않았기 때문에 nullable이 된다.


ALTER TABLE tb2
RENAME COLUMN change_fk to fk; -- rename은 컬럼 이름만 변경
DESCRIBE tb2;

-- primary key 제약조건 삭제 및 추가
-- autoincrement 컬럼: key로 추가가 되어 있어야 함.
ALTER TABLE tb2 DROP PRIMARY KEY ; -- 오류 발생

ALTER TABLE tb2
MODIFY COLUMN pk int not null; -- primary key 조건을 변경함
ALTER TABLE tb2 DROP PRIMARY KEY ; -- 정상 실행
DESCRIBE tb2;

ALTER TABLE tb2
add PRIMARY KEY (pk);
DESCRIBE tb2;

ALTER TABLE tb2
MODIFY COLUMN pk int NOT NULL AUTO_INCREMENT;
DESCRIBE tb2;


-- 삭제
CREATE TABLE IF NOT EXISTS tb3
(
    pk INT AUTO_INCREMENT PRIMARY KEY ,
    fk int null,
    col1 VARCHAR(255),
    CHECK (col1 in ('Y', 'N')  ) -- 제약조건
) ENGINE = INNODB
COMMENT '삭제할 3번 테이블임'
;

DESCRIBE tb3;



CREATE TABLE IF NOT EXISTS tb3
(
    pk INT AUTO_INCREMENT PRIMARY KEY ,
    fk int null,
    col1 VARCHAR(255),
    CHECK (col1 in ('Y', 'N')  ) -- 제약조건
)
;
CREATE TABLE IF NOT EXISTS tb4
(
    pk INT AUTO_INCREMENT PRIMARY KEY ,
    fk int null,
    col1 VARCHAR(255),
    CHECK (col1 in ('Y', 'N')  ) -- 제약조건
)
;
CREATE TABLE IF NOT EXISTS tb5
(
    pk INT AUTO_INCREMENT PRIMARY KEY ,
    fk int null,
    col1 VARCHAR(255),
    CHECK (col1 in ('Y', 'N')  ) -- 제약조건
)
;

DROP TABLE IF EXISTS tb3, tb4, tb5;

INSERT INTO tb3
VALUES
    (NULL, 10, 'Y'),
    (NULL, 20, 'Y'),
    (NULL, 30, 'Y'),
    (NULL, 40, 'Y'),
    (NULL, 50, 'Y');

DELETE from tb3
WHERE pk = 5;

INSERT INTO tb3 (fk, col1)
VALUES (50, 'Y');

SELECT * FROM tb3;

TRUNCATE TABLE tb3;

-- DELETE / TRUNCATE / DROP
-- DELETE : 선택한 행 삭제하기 위해
-- TRUNCATE: 테이블 구조를 초기화해 행을 삭제하기 위해
-- DROP: 모든 행과 구조 삭제
