/* Q1)
사원명이 '노옹철'인 직원과 같은 부서에 속한 직원의 사원명과 부서코드를 조회하시오.
(힌트: WHERE절의 서브쿼리)
*/

/* Q2)
전 직원의 평균 급여보다 많은 급여를 받는 직원의 사번, 사원명, 직급코드,
급여를 조회하고 급여를 기준으로 내림차순 정렬하시오.
(힌트: AVG를 사용한 서브쿼리)
*/

/* Q3)
부서별 급여 합계를 먼저 구한 뒤, 급여 합계가 가장 큰 부서의 부서명과
급여 합계를 조회하시오.
(힌트: FROM절 서브쿼리, GROUP BY, JOIN, ORDER BY, LIMIT)
*/

/* Q4)
부서가 배정된 직원 중 각 부서의 최고 급여를 받는 직원의 사원명, 직급명,
부서명, 급여를 조회하시오. 최고 급여가 같은 직원은 모두 조회하시오.
(힌트: 상관 서브쿼리, MAX, JOIN)
*/

/* Q5)
보너스를 받는 직원의 부서코드를 CTE로 먼저 정의하시오.
그 CTE와 EXISTS를 사용하여 보너스를 받는 직원이 한 명 이상 있는 부서의
부서코드와 부서명을 조회하고, 부서코드를 기준으로 오름차순 정렬하시오.
(힌트: WITH, EXISTS)
*/
WITH bonusDept AS (
    SELECT
        dept_code,
        emp_id
    FROM tbl_employee
    WHERE bonus IS NOT NULL
    -- AND dept_code is not NULL
)
SELECT
    td.dept_id,
    td.dept_title,
    COUNT(DISTINCT )

from tbl_department td
join bonusDept bd on bd.dept_code = td.dept_id
GROUP BY td.dept_id, td.dept_title
ORDER BY td.dept_id
;

WITH bonus_emp AS (
    SELECT emp_id, dept_code
    FROM tbl_employee
    WHERE bonus IS NOT NULL
)
SELECT
    td.dept_id,
    td.dept_title,
    COUNT(DISTINCT be.emp_id) AS bonus_emp_count
FROM tbl_department td
         JOIN bonus_emp be ON be.dept_code = td.dept_id
GROUP BY td.dept_id, td.dept_title
ORDER BY td.dept_id;