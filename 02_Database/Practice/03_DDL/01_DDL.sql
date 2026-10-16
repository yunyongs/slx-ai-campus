/* Q1)
tb1 테이블을 생성하고, pk, fk, col1 컬럼을 추가하시오.
col1 컬럼에 'Y'와 'N'만 허용되도록 CHECK 제약조건을 추가하시오.
*/

CREATE TABLE IF NOT EXISTS tb1
(
    pk int AUTO_INCREMENT,
    fk int not NULL,
    col1 VARCHAR(255),
    CHECK ( col1 in ('Y', 'N')),
    PRIMARY KEY (pk)
);

/* Q2)
tb2 테이블을 생성하고, pk, fk, col1 컬럼을 추가하시오.
그리고 pk 컬럼을 AUTO_INCREMENT로 설정하시오.
*/

/* Q3)
위에서 생성한 tb2 테이블에 col2 컬럼을 추가하시오.
col2는 INT형이고, NOT NULL 제약조건이 적용되도록 하시오.
그리고 변경된 tb2 테이블의 구조를 확인하시오.
*/
    CREATE TABLE IF NOT EXISTS tb2
        (
        pk INT AUTO_INCREMENT PRIMARY KEY ,
        fk INT,
        col1  int NOT NULL
    );
    describe tb2;

/* Q4)
위에서 tb2 테이블에 생성한 col2 컬럼을 삭제하시오.
그리고 변경된 tb2 테이블의 구조를 확인하시오.
*/
ALTER TABLE tb2
DROP COLUMN col1;
DESCRIBE tb2;
/* Q5)
위에서 생성한 tb2 테이블에서 fk 컬럼의 이름을 change_fk로 변경하고, NOT NULL 제약조건을 추가하시오.
*/
ALTER TABLE tb2
CHANGE fk change_fk INT NOT NULL;