-- View

-- select문을 이름과 함께 저장해서 테이블처럼 사용하는 가상

-- 장점
-- 복잡한 select 문을 단순하게 재사용 가능하다
-- 사용자에게 필요한 컬럼과 행만 공개할 수 있다.
USE menudb;
CREATE VIEW hansik AS
    SELECT menu_code,
           menu_name,
           menu_price,
           category_code,
           orderable_status
from tbl_menu
WHERE category_code = 4
WITH CHECK OPTION ;

SELECT * from hansik;

INSERT into tbl_menu (menu_name, menu_price, category_code, orderable_status)
VALUES ('닭칼국수 김밥', 5000, 4,'Y');

SELECT * from hansik;

set @menu_code=LAST_INSERT_ID();

DELETE FROM tbl_menu
    WHERE menu_code = @menu_code;


-- View를 통해 DML
INSERT INTO tbl_menu (menu_name, menu_price, category_code, orderable_status)
VALUES
    ('김밥', 5000, 4, 'Y')
;
set @menu_code=LAST_INSERT_ID();


SELECT * from hansik
WHERE menu_code = LAST_INSERT_ID();

--   VIEW 조건을 만족하지 못해 생성불가 (위 view에서 where=category_code = 4 )
INSERT INTO hansik (menu_name, menu_price, category_code, orderable_status)
VALUES
    ('김밥', 5000, 5, 'Y')


-- DML문이 제한되는 view
-- distinct, groupby (having), aggregate functions, union, calculation
-- 여러 테이블을 join했을 경우 (VIEW 정의했느냐, VIEW에서 어떤 컬럼을 변경하려고 하느냐)
