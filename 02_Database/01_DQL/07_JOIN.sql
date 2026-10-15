-- JOIN
-- 관련 컬럼을 기준으로 여러 테이블의 데이터를 조회할 때 사용

-- JOIN의 종류
-- INNER JOIN
    -- INNER 생략 가능


SELECT
    tc.category_name,
    tm.menu_name
FROM tbl_menu as tm
INNER JOIN tbl_category as tc
on
    tm.category_code = tc.category_code
;

-- LEFT JOIN
SELECT
    tc.category_name,
    tm.menu_name
FROM tbl_category tc
LEFT JOIN tbl_menu tm
on tc.category_code = tm.category_code;

-- RIGHT JOIN
SELECT
    tc.category_name,
    tm.menu_name
FROM tbl_category tc
         RIGHT JOIN tbl_menu tm
                   on tc.category_code = tm.category_code;


-- CROSS JOIN: 모든 조합
SELECT
    tm.menu_name,
    tc.category_name
FROM tbl_menu tm
         CROSS JOIN tbl_category tc
                    on tc.category_code = tm.category_code;


-- USING: 같은 이름의 컬럼을 연결하여 조회할 수 있음.
SELECT
    tm.menu_name,
    tc.category_name
FROM tbl_menu tm
         CROSS JOIN tbl_category tc
using (category_code); -- 다른 경우, 같아야 하지만 (foreign key - primary key 조합이어야 하지만)

-- self join: 자신의 테이블을 자신이 JOIN
SELECT
    tc.category_name as "Category Name",
    tc_ref.category_name as "Higher Category Name"
FROM tbl_category tc
JOIN tbl_category tc_ref
on tc.ref_category_code = tc_ref.category_code
;


