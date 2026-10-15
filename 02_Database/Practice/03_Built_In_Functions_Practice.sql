/* Q1)
tbl_employee 테이블에서 사원명, 이메일, 이메일의 '@' 앞부분인 아이디를 조회하시오.
(힌트: substring_index())
*/
SELECT
    emp_name,
    email,
    SUBSTRING_INDEX(email, '@', 1) as "ID"
    from tbl_employee;


/* Q2)
tbl_employee 테이블에서 직원들의 주민번호를 조회하여 사원명, 생년, 생월, 생일을 각각 분리하여 조회할 것.
(단, 컬럼의 별칭은 사원명, 생년, 생월, 생일로 한다.)
(힌트: substring())
*/
SELECT
    emp_name as "사원명",
    CONCAT("19",SUBSTRING(emp_no, 1, 2)) as "생년",
    SUBSTRING(emp_no, 3, 2) as "생월",
    SUBSTRING(emp_no, 5, 2) as "생일"
    FROM tbl_employee;

/* Q3)
tbl_employee 테이블에서 모든 직원의 입사일과 현재 날짜가 달력 기준으로 몇 개월 차이인지 계산하여 이름과 개월 수를 조회하시오.
(힌트: period_diff(), year(), month(), now())
*/
SELECT
    emp_name,
    hire_date,
    PERIOD_DIFF(
            YEAR(NOW())*100+MONTH(NOW()),
            YEAR(hire_date)*100+MONTH(hire_date))
        as "근무 개월 수"
FROM tbl_employee
ORDER BY hire_date
;


/* Q4)
tbl_employee 테이블에서 사원명, 급여를 조회하시오.
단, 급여는 '￦9,000,000' 형식으로 표시할 것.
(힌트: concat(), format())
*/
SELECT
    emp_name as "사원명",
    CONCAT("￦", FORMAT(salary,0)) as "급여"
FROM tbl_employee
ORDER BY 급여 DESC ;

/* Q5)
tbl_employee 테이블에서 2000년도 이후에 입사한 직원들의 급여를 15% 인상했을 때
인상된 급여가 300만원 이상인 직원의 사원명, 급여, 인상급여를 조회하시오.
단, 인상급여는 십만원 미만의 금액을 버릴 것.
(힌트: truncate(), year() / truncate()의 두 번째 인자에 -5를 사용한다.)
*/
SELECT
    salary as "급여",
    TRUNCATE(salary+(salary*0.15), -5) as "인상급여",
    hire_date
FROM tbl_employee
WHERE YEAR(hire_date) >=2000 AND
      salary+(salary*0.15) >= 3000000
;
