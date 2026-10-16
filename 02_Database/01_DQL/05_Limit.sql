use menudb;

-- limit
-- 조회할 행의 개수를 제한할 때 사용
-- limit (개수): 처음부터 지정한 개수만큼 조회
-- limit (시작위치), (개수): 시작위치부터 지정한 개수만큼 조회

SELECT
    menu_code,
    menu_name,
    menu_price
    from tbl_menu
ORDER BY
    menu_price -- 특정 개수를 보고 싶을 때는 정렬을 먼저 해줘야 한다. 그래야 의미있는 데이터확인이 가능
LIMIT 5;

SELECT
    menu_code,
    menu_name,
    menu_price
from tbl_menu
ORDER BY
    menu_price -- 특정 개수를 보고 싶을 때는 정렬을 먼저 해줘야 한다. 그래야 의미있는 데이터확인이 가능
LIMIT 1, 4;

