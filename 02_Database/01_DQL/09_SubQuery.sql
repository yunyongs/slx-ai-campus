use menudb;

-- SUBQUERY
-- 다른 SQL문 안에 작성하는 select 문
-- 서브쿼리의 결과를 메인쿼리의 조건이나 조회 대상
-- FROM 절에 서브쿼리를 작성할 경우 별칭 필수


SELECT
    category_code
FROM tbl_menu
WHERE menu_name = '민트미역국'
;


-- 비상관 서브쿼리
SELECT
    *
FROM tbl_menu
WHERE category_code =
      (SELECT
           category_code
       FROM tbl_menu
       WHERE menu_name = '민트미역국')
;

-- 상관 서브쿼리


-- 다양한 위치에서 서브쿼리의 활용
-- 1. SELECT 절: 스칼라 서브쿼리
-- 한 행, 한 컬럼의 값을 반환하며 조회의 결과를 컬럼처럼 사용
SELECT
    a.menu_name,
    a.menu_code,
    a.menu_price,
    (select
         b.category_name
     from tbl_category b
     WHERE (b.category_code = a.category_code) as category_name
     )
FROM tbl_menu a
;

-- 2. FROM 절: 인라인 뷰(파생테이블)
-- 서브쿼리의 조회 결과를 하나의 테이블처럼 사용(별칭을 꼭 지정)
SELECT
    category_summary.category_code
FROM (
    SELECT
        category_code,
        COUNT(*),
        FLOOR(avg(menu_price))
        from tbl_menu
        group by category_code
     ) as category_summary
;

-- WHERE 절: 중첩 서브쿼리
-- 서브쿼리의 결과를 바깥쪽(메인) 쿼리의 조건으로 사용
SELECT
   menu_code,
   menu_name,
   menu_price

from tbl_menu
WHERE menu_price > (
SELECT avg(menu_price)
FROM tbl_menu
);


-- HAVING 절
/*SELECT
    category_code,
    FLOOR(avg(menu_price))) as avg_price
FROM tbl_menu
group by category_code*/


-- 상관 서브쿼리
-- 내부 쿼리가 외부 쿼리의 값을 참조하는 형태
-- 카테고리별로 평균 가격보다 높은 가격의 메뉴 조회
SELECT
    menu_code,
    menu_name,
    menu_price,
    category_code,
    orderable_status

from tbl_menu a
WHERE menu_price > (SELECT
                        avg(menu_price)employeedb
                    FROM tbl_menu
                    WHERE category_code =a.category_code
                    )
;
-- 메인 쿼리의 내용을 서브쿼리에서 가져다 쓰는 것: 상관 서브 쿼리

-- EXISTS()
-- 서브쿼리의 결과가 있으면 true
-- 없으면 false
SELECT
    *
FROM tbl_category a
where EXISTS(
    SELECT 1 from tbl_menu b
             WHERE b.category_code= a.category_code
);

-- common table expression
-- 파생테이블과 비슷한 개념이고, 코드의 재사용성을 위해

SELECT
    category_code
FROM tbl_category a
where EXISTS(
    SELECT 1 from tbl_menu b
    WHERE b.category_code= a.category_code
);

-- CTE
SELECT
    category_code
FROM tbl_category a
where EXISTS(
    SELECT 1 from tbl_menu b
    WHERE b.category_code= a.category_code
);

/*with menucate as (
    se(SELECT SQL_BIG_RESULT )
)*/