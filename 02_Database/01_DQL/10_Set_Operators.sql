-- SET
-- 여러 select 결과를 결합할 때 사용
-- 컬럼의 갯수와 같은 위치의 데이터 타입 호환되어야 함
-- union: 중복을 제거
-- union all : 중복을 유지

SELECT
    *
from tbl_menu
WHERE category_code = 10

UNION

SELECT
    *
from tbl_menu
WHERE menu_price < 9000;


SELECT
    *
from tbl_menu
WHERE category_code = 10

UNION ALL

SELECT
    *
from tbl_menu
WHERE menu_price < 9000;


SELECT
    a.menu_code,
    a.menu_name,
    a.menu_price,
    a.category_code,
    a.orderable_status
    from tbl_menu a
JOIN (
    SELECT
        menu_code,
        menu_name,
        menu_price,
        category_code,
        orderable_status
    from tbl_menu
    WHERE menu_price < 9000)
    b on (a.menu_code = b.menu_code)
;

SELECT * from tbl_menu
WHERE menu_price < 9000;
