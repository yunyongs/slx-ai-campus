select * from tbl_menu;

SELECT 5+5;

SELECT NOW();

SELECT CONCAT('B', 'ear');

SELECT NOW() as time;

SELECT menu_name, menu_price FROM tbl_menu ORDER BY menu_price DESC LIMIT 1;

SELECT * FROM tbl_menu LIMIT 5;

SELECT
    tbl_menu.menu_code,
    tbl_menu.menu_name,
    tbl_menu.menu_price
FROM tbl_menu
ORDER BY menu_price DESC,
         menu_code ASC ;

SELECT
    tbl_menu.menu_code,
    tbl_menu.menu_name,
    ROUND(menu_price / 1000, 0) as menu_price_천원
FROM tbl_menu
ORDER BY
    menu_price_천원 DESC ;

SELECT FIELD('B','A','B','C');

-- N인 메뉴를 먼저 출력, 다음에 Y인 메뉴 출력
SELECT *
FROM tbl_menu
ORDER BY
    FIELD(orderable_status, 'N', 'Y');

