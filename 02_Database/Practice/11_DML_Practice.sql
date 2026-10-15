/* Q1)
사원 테이블(tbl_employee)에 새로운 사원을 추가하시오.
사번: 901, 이름: 이지영, 주민등록번호: 910123-1234567, 이메일: lee_jy@gmail.com,
전화번호: 01055551234, 부서코드: D2, 직급코드: J2, 급여등급: S3,
급여: 3500000, 보너스: 0.15, 관리자사번: 201, 입사일: 현재 날짜,
퇴사일: NULL, 퇴직여부: N
(힌트: INSERT INTO, VALUES, CURDATE())
*/
INSERT INTO tbl_employee
VALUES
    (901, "이지영", "910123-1234567", "lee_jy@gmail.com", "01055551234", "D2", "J2", "S3", 3500000, 0.15,201, CURDATE(), NULL, 'N');


/* Q2)
사원 테이블(tbl_employee)에서 방명수 사원의 급여와 보너스를 유재식 사원과 동일하게 변경하시오.
(힌트: UPDATE, 서브쿼리, JOIN을 이용한 임시 테이블)
*/

-- 우선 사원 이름의 값이 하나밖에 없는지 점검 - 동명이인이 있는지
SELECT emp_name, salary, bonus
FROM tbl_employee
WHERE emp_name IN ('유재식', '방명수');

UPDATE tbl_employee te
join (
    SELECT tbl_employee.salary, tbl_employee.bonus
    from tbl_employee
    WHERE emp_name = '유재식'
        ) yu
SET
    te.salary = yu.salary,
    te.bonus = yu.bonus
WHERE emp_name = '방명수'
;



/* Q3)
사원 테이블(tbl_employee)에서 노옹철, 전형돈, 정중하, 하동운 사원의 급여와 보너스를 유재식 사원과 동일하게 변경하시오.
(힌트: UPDATE, 서브쿼리, JOIN을 이용한 임시 테이블, IN)
*/

SELECT emp_name, salary, bonus
FROM tbl_employee
WHERE emp_name IN ('유재식', '노옹철', '전형돈', '정중하', '하동운');


UPDATE tbl_employee te
join (
    SELECT salary, bonus
    from tbl_employee
    WHERE emp_name = '유재식'
    )yu
SET te.salary = yu.salary, te.bonus = yu.bonus
WHERE emp_name IN ('노옹철', '전형돈', '정중하', '하동운')
;


/* Q4)
사원 테이블(tbl_employee)에서 사번이 901인 사원을 삭제하시오.
(힌트: DELETE FROM, WHERE)
*/
DELETE
FROM tbl_employee
WHERE emp_id = 901
;
/* Q5)
사원 테이블(tbl_employee)에서 부서코드가 D1인 모든 사원을 삭제하시오.
(힌트: DELETE FROM, WHERE)
*/
DELETE
FROM tbl_employee
WHERE dept_code = 'D1'
;

