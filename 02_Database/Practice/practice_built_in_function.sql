-- BUILT-IN FUNCTIONS
-- MySQL이 제공하는 함수로 값을 가공할 때 사용

-- 1. 문자열 관련 함수

-- ASCII(아스키 코드), CHAR(숫자)
-- ASCII: 아스키 코드 값 추출
-- CHAR: 아스키 코드로 문자 추출
SELECT
    ASCII('A'),
    CHAR(65);
-- (Workbench의 버그로 CHAR(65)의 결과가 'BLOB'으로 보일 수 있는데 일반 명령형 모드에서는 정상적으로 'A'로 출력된다.
-- 'BLOB' 글자에서 마우스 오른쪽 버튼 클릭하고 'Open Value in Viewer' 선택 후 Text 탭에서 확인할 수 있다.)

-- BIT_LENGTH(문자열), CHAR_LENGTH(문자열), LENGTH(문자열)
-- BIT_LENGTH: 할당된 비트 크기 반환
-- CHAR_LENGTH: 문자열의 길이 반환
-- LENGTH: 할당된 BYTE 크기 반환
-- [추가 설명] 영어, 숫자, 특수 기호를 제외한 나머지 문자는 모두 3byte 크기가 할당됨.
SELECT
    BIT_LENGTH('pie'),
    CHAR_LENGTH('pie'),
    LENGTH('pie'),
LENGTH('한국어');

SELECT *
FROM tbl_employee
LIMIT 5;

SELECT
    CONCAT_WS('_', emp_id, emp_name) as "직원번호_이름",
    FORMAT(salary/1000, 0) as "급여(천원)",
    FORMAT(salary/1000*IFNULL(bonus, 0), 0) as "보너스(천원)",
    FORMAT((salary/1000) + (salary/1000*IFNULL(bonus,0)), 0) as "총 수령액(천원)",
    LEFT(emp_name,1) as "직원 성",
    RIGHT(emp_name,2) as "직원 이름"
from tbl_employee
ORDER BY salary * (1+IFNULL(bonus, 0)) DESC ;




