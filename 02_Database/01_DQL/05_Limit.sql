use menudb;

-- limit
-- 조회할 행의 갯수를 제한할 때 사용
-- limit (갯수): 처음부터 지정한 갯수만큼 조회
-- limit (시작위치), (갯수): 시작위치부터 지정한 갯수만큼 조회

SELECT
    menu_code,
    menu_name,
    menu_price
    from tbl_menu
ORDER BY
    menu_price -- 특정 갯수를 보고 싶을 때는 정렬을 먼저 해줘야 한다. 그래야 의미있는 데이터확인이 가능
LIMIT 5;

SELECT
    menu_code,
    menu_name,
    menu_price
from tbl_menu
ORDER BY
    menu_price -- 특정 갯수를 보고 싶을 때는 정렬을 먼저 해줘야 한다. 그래야 의미있는 데이터확인이 가능
LIMIT 1, 4;

