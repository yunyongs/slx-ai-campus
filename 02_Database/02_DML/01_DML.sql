-- DML: Data Manipulation language
-- 테이블의 데이터를 추가, 수정, 삭제, 조회(DQL)할 때 사용

-- INSERT: 추가
INSERT INTO
        tbl_menu
VALUES (null, '바나나해장국', 8500, 4, 'Y')
    ;

INSERT INTO
tbl_menu(menu_name, menu_price, category_code, orderable_status)
VALUES
    ('김치맛아이스크림', 8500, 4, 'Y'),
    ('참치맛아이스크림', 8500, 4, 'Y'),
    ('소시시맛아이스크림', 8500, 4, 'Y')
;
-- UPDATE: 수정
UPDATE tbl_menu
SET orderable_status = 'N'
WHERE menu_name = '소시지맛아이스크림'
;

UPDATE tbl_menu
SET
    orderable_status = 'N',
    menu_name = '김치맛아아'

WHERE menu_code = 39
;
-- DELETE: 삭제
DELETE FROM tbl_menu
WHERE menu_code = 39;

-- 가장 가격이 저렴한 메뉴 2가지 삭제
DELETE FROM tbl_menu
       WHERE menu_price > 5000
ORDER BY menu_price ASC
LIMIT 2;


DELETE FROM tbl_menu
WHERE menu_code in (
    SELECT menu_code
    FROM tbl_menu
    ORDER BY menu_price
    limit 2
    );


-- REPLACE
-- 데이터를 입력할 때 중복된 데이터는 덮어 쓸 수 있도록 허용해주는 SQL문
-- into 생략가능
REPLACE INTO
    tbl_menu
VALUES (1, '바나나해장국', 8500, 4, 'Y')
;


