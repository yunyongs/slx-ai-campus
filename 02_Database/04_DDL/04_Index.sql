DROP TABLE IF EXISTS phone;

CREATE TABLE phone
(
    phone_code  INT,
    phone_name  VARCHAR(100) NOT NULL,
    phone_price DECIMAL(10, 2) NOT NULL,
    company     VARCHAR(30) NOT NULL,
    CONSTRAINT pk_phone PRIMARY KEY (phone_code)
);


INSERT INTO phone (phone_code, phone_name, phone_price, company)
VALUES
    (1, 'GalaxyS26',          1250000, 'Samsung'),
    (2, 'iPhone17',           1580000, 'Apple'),
    (3, 'GalaxyFold8',        2300000, 'Samsung'),
    (4, 'GalaxyS26Ultra',     1850000, 'Samsung'),
    (5, 'iPhone17Ultra',      1980000, 'Apple'),
    (6, 'GalaxyZFold8Ultra',  1800000, 'Samsung'),
    (7, 'Pixel11',            1350000, 'Google'),
    (8, 'Pixel11Pro',         1650000, 'Google');

show INDEX from phone;

-- explain
-- select가 어떻게 실행될지 옵티마이저가 세운 계획을 보여줌
-- 옵티마이저:SQL문을 가장 효율적으로 실행할 방법을 선택하는 MYSQL 내부 기능
EXPLAIN
SELECT * from phone
WHERE phone_name = 'GalaxyS26';

-- 단일 컬럼 인덱스
-- 하나의 컬럼으로 구성한 인덱스
CREATE INDEX idx_phone_name
on phone(phone_name);

show INDEX from phone;


-- 데이터가 많아질수록 인덱스로 인해 발생하는 효율이 증가
EXPLAIN
SELECT * from phone
WHERE phone_name = 'GalaxyS26';

DROP INDEX idx_phone_name on phone;

CREATE INDEX ind_phone_name_price
on phone (phone_name, phone_price);

show INDEX from phone;

-- 커버링 인덱스
-- 필요한 컬럼이 모두 인덱스에 있으면 테이블 행을 추가로 읽지 않음(실행계획: using index)
EXPLAIN
SELECT *
FROM phone
WHERE phone_name = 'iPhone17';

EXPLAIN
SELECT
    phone_name,
    phone_price
FROM phone
WHERE phone_price = 185000;


ANALYZE TABLE phone; -- 인덱스 통계 갱신
OPTIMIZE TABLE phone; -- 테이블 공간을 재구성