-- autocommit 조회
SELECT @@autocommit;


set AUTOCOMMIT = 0;
# SET AUTOCOMMIT = off;

# SET AUTOCOMMIT =1;
SET AUTOCOMMIT =on;

USE menudb;

SELECT * from tbl_menu;

-- 트랜잭션으로 여러개의 쿼리를 작업 단위로 묶음, 커밋을 하게 되면 db에 반영, rollback하면 반영 안 됨
-- 트랜잭션 내에서는 작업한 내용 조회 가능
-- transaction scope def
START TRANSACTION ; -- transaction 시작하게 되면 commit전까지 반영이 안 됨.

INSERT INTO tbl_menu
VALUES    (NULL, '바나나해장국', 8500, 4, 'Y');

UPDATE tbl_menu set menu_name = '수정된 메뉴' WHERE menu_code = LAST_INSERT_ID();

SELECT * FROM tbl_menu WHERE menu_code = LAST_INSERT_ID();

# commit; -- transaction ends : 변경 사항을 확정할 때
# rollback; -- transaction ends : 아직 커밋되지 않은 내용을 취소한다. , 단 커밋 후 롤백은 커밋을 적용받기 때문에 되돌리지 못한다.

