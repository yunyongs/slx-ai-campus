/* Q1)
전체 직원 수와 전 직원 급여의 합계를 조회하시오.
(힌트: COUNT, SUM)
*/

/* Q2)
부서코드별로 부서코드, 급여 합계, 평균 급여(소수점 이하 버림), 직원 수를 조회하시오.
부서가 배정되지 않은 직원도 하나의 그룹으로 포함하고,
부서코드를 기준으로 오름차순 정렬하시오.
(힌트: GROUP BY, SUM, AVG, FLOOR, COUNT)
*/
SELECT
    dept_code,
   FORMAT( sum(salary),0),
   FORMAT( FLOOR(AVG(salary)), 0),
   COUNT(*)
FROM tbl_employee
GROUP BY dept_code
ORDER BY dept_code
;


/* Q3)
보너스를 받는 직원만 대상으로 직급코드별 직원 수를 조회하시오.
직급코드를 기준으로 오름차순 정렬하시오.
(힌트: WHERE, GROUP BY, COUNT)
*/
SELECT
    job_code,
    COUNT(*)

FROM tbl_employee
WHERE bonus IS NOT NULL
GROUP BY job_code
;


/* Q4)
부서별 평균 급여가 300만원 이상인 부서의 부서명, 직원 수,
평균 급여(소수점 이하 버림)를 조회하시오.
평균 급여를 기준으로 내림차순 정렬하시오.
(힌트: JOIN, GROUP BY, HAVING)
*/
SELECT
    dept_title,
    COUNT(emp_id) as 'emp_count',
    FLOOR( AVG(salary)) as avg_sal
FROM tbl_employee
join tbl_department on tbl_employee.dept_code = tbl_department.dept_id
GROUP BY dept_code
HAVING avg_sal >= 3000000
ORDER BY avg_sal
;

/* Q5)
직급별 직급명, 직원 수, 급여 합계를 조회하고 마지막 행에 전체 직원 수와
전체 급여 합계가 표시되도록 하시오.
(힌트: JOIN, GROUP BY, WITH ROLLUP, COALESCE)
*/
SELECT
    COALESCE(tj.job_name, '합계') "직급명",
    COUNT(te.emp_id) "직원수",
    SUM(te.salary) "급여 합계"
FROM tbl_employee te
JOIN tbl_job tj ON te.job_code = tj.job_code
GROUP BY tj.job_name
WITH ROLLUP;

