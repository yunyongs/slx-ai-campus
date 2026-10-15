-- DISTINCT
-- 조회한 결과에서 중복된 행을 제거할 때 사용
-- 여러 컬럼을 조회하면 컬럼값의 중복을 기준으로 중복을 판단



SELECT
    category_code
from tbl_menu;

SELECT
    DISTINCT category_code
from tbl_menu;

-- null도 중복제거가 된다. null의 유일한 값만 반환
SELECT
    DISTINCT ref_category_code
from tbl_category;

-- 열이 여러개인 경우 distinct
-- 여러 컬럼이 같은 경우 중복 제거
SELECT
    DISTINCT
    category_code, orderable_status
FROM tbl_menu
ORDER BY category_code;



