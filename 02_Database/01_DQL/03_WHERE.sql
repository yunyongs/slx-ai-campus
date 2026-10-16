-- WHERE
-- select where only meets the condition

-- BETWEEN, IN, LIKE

SELECT
    tbl_menu.menu_name,
    tbl_menu.menu_price,
    tbl_menu.orderable_status
FROM tbl_menu
WHERE orderable_status = 'Y';


-- menu_
SELECT
    tbl_menu.menu_name,
    tbl_menu.menu_price,
    tbl_menu.orderable_status
FROM tbl_menu
WHERE menu_price = 13000
ORDER BY menu_code DESC ;


SELECT
    tbl_menu.menu_name,
    tbl_menu.menu_price,
    tbl_menu.orderable_status
FROM tbl_menu
WHERE menu_price > 13000
ORDER BY menu_price DESC ;


SELECT
    tbl_menu.menu_name,
    tbl_menu.menu_price,
    tbl_menu.orderable_status
FROM tbl_menu
WHERE menu_price != 13000;


-- 논리 연산 AND, OR
SELECT
    category_code,
    orderable_status,
    tbl_menu.menu_name,
    tbl_menu.menu_price
FROM tbl_menu
WHERE
    orderable_status = 'Y' AND
    category_code = 10;

SELECT
    category_code,
    orderable_status,
    tbl_menu.menu_name,
    tbl_menu.menu_price
FROM tbl_menu
WHERE
    orderable_status = 'Y' OR
    category_code = 10;

SELECT 1 and 0;
SELECT 1 or 0;
SELECT 1 or 0 and 0; -- 기본적으로 and 연산이 먼저 수행.

-- 메뉴 가격이 10,000 이상 25,000이하 이고, menu 가격 순으로 정렬해서 출력
SELECT
    category_code,
    tbl_menu.menu_name,
    tbl_menu.menu_price
FROM tbl_menu
WHERE
    menu_price >= 10000 AND menu_price <=25000
ORDER BY menu_price;

-- between연산자로 표현 가능
SELECT
    tbl_menu.menu_name,
    tbl_menu.menu_price,
    category_code
FROM tbl_menu
WHERE
    menu_price BETWEEN 10000 AND 25000
ORDER BY menu_price;

-- 부정표현 not
SELECT
    tbl_menu.menu_name,
    tbl_menu.menu_price,
    category_code
FROM tbl_menu
WHERE
    menu_price NOT BETWEEN 10000 AND 25000
ORDER BY menu_price;

-- like 연산자
-- 와일드 카드
-- _ 딱 한글자
-- % 글자수 제한 없음
SELECT tbl_menu.menu_name
from tbl_menu
WHERE menu_name like '%마늘%'

-- 생으로 시작하는 모든 메뉴 이름을 찾아라.
SELECT tbl_menu.menu_name
from tbl_menu
WHERE menu_name like '생%';

-- IN : 여러 값중에 하나라도 일치하는 지 확인하는 연산자
SELECT tbl_menu.menu_name, tbl_menu.category_code
FROM tbl_menu
WHERE category_code not in (4,5,6)
ORDER BY category_code;

SELECT
    tbl_menu.menu_name,
    tbl_menu.category_code
FROM tbl_menu
WHERE category_code not in (4,5,6)
ORDER BY category_code;

-- IS NULL : NULL 값을 확인하는 메서드
SELECT category_name,
       COUNT(ref_category_code)
from tbl_category
GROUP BY category_name;


