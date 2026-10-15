use menudb;

-- GROUP BY
-- 같은 값을 가진 행을 하나의 그룹으로 묶을 때
-- 통계, 집계를 계산할 때 사용
-- HAVING: 그룹화 한 이후에 결과를 필터링
-- WHERE: 그룹화를 하기전에 데이터를 필터링

SELECT
    category_code,
    COUNT(*)
FROM tbl_menu
GROUP BY category_code;

SELECT
    category_code,
    COUNT(*),
    FORMAT( sum(menu_price)/1000, 0) "가격(천)",
    FORMAT(  AVG(menu_price)/1000, 0) "평균가(천)"
FROM tbl_menu
GROUP BY category_code;

SELECT
    category_code,
    COUNT(*)
FROM tbl_menu
GROUP BY category_code
HAVING category_code BETWEEN 5 and 8;

-- ROLLUP
-- 각 그룹에 대한 소계나 총계를 계산하여 resultset에 포함
SELECT
    tbl_menu.category_code,
    SUM(tbl_menu.category_code)
FROM tbl_menu
GROUP BY category_code, menu_price
WITH ROLLUP ; -- 총합도 출력됨

SELECT
    menu_price,
    SUM(menu_price),
    category_code,
    sum(category_code)
FROM tbl_menu
GROUP BY menu_price, category_code -- 두 개를 기준으로 그룹화
WITH ROLLUP
;