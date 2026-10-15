/* Q1)
직원의 사번, 사원명, 직급명을 조회하시오.
사번을 기준으로 오름차순 정렬하시오.
[테이블 정보]
- tbl_employee: 사번(emp_id), 사원명(emp_name), 직급코드(job_code)
- tbl_job: 직급코드(job_code), 직급명(job_name)
(힌트: INNER JOIN)
*/

SELECT
    te.emp_id,
    te.emp_name,
    tj.job_name

FROM tbl_employee te
JOIN employeedb.tbl_job tj ON tj.job_code = te.job_code
ORDER BY emp_id
;



/* Q2)
모든 직원의 사번, 사원명, 부서명을 조회하시오.
부서가 배정되지 않은 직원도 결과에 포함하고, 사번을 기준으로 오름차순 정렬하시오.
[테이블 정보]
- tbl_employee: 사번(emp_id), 사원명(emp_name), 부서코드(dept_code)
- tbl_department: 부서코드(dept_id), 부서명(dept_title)
(힌트: LEFT JOIN)
*/
SELECT
    emp_id,
    emp_name,
    dept_title

FROM tbl_employee te
LEFT OUTER JOIN employeedb.tbl_department td ON td.dept_id = te.dept_code
ORDER BY te.emp_id
;


/* Q3)
보너스를 받는 모든 직원의 사원명, 보너스포인트, 부서명, 근무지역명을 조회하시오.
부서가 배정되지 않은 직원도 결과에 포함하시오.
[테이블 정보]
- tbl_employee: 사원명(emp_name), 보너스포인트(bonus), 부서코드(dept_code)
- tbl_department: 부서코드(dept_id), 부서명(dept_title), 지역코드(location_id)
- tbl_location: 지역코드(local_code), 근무지역명(local_name)
(힌트: IS NOT NULL, LEFT JOIN)
*/
SELECT
    te.emp_name,
    te.bonus,
    td.dept_title,
    tl.local_name
FROM tbl_employee te
LEFT JOIN tbl_department td ON td.dept_id = te.dept_code
LEFT JOIN tbl_location tl ON td.location_id = tl.local_code
WHERE te.bonus is not NULL
;


/* Q4)
한국(KO)과 일본(JP)에 근무하는 직원의 사원명, 부서명, 지역명, 국가명을 조회하시오.
국가코드, 부서코드, 사번 순으로 오름차순 정렬하시오.
[테이블 정보]
- tbl_employee: 사번(emp_id), 사원명(emp_name), 부서코드(dept_code)
- tbl_department: 부서코드(dept_id), 부서명(dept_title), 지역코드(location_id)
- tbl_location: 지역코드(local_code), 지역명(local_name), 국가코드(national_code)
- tbl_national: 국가코드(national_code), 국가명(national_name)
(힌트: 여러 테이블 JOIN, IN)
*/
SELECT
    emp_name,
    dept_title,
    local_name,
    national_name

FROM tbl_employee te
LEFT JOIN tbl_department td on td.dept_id = te.dept_code
LEFT JOIN tbl_location tl on tl.local_code = td.location_id
LEFT JOIN tbl_national tn on tn.national_code = tl.national_code
-- WHERE tl.national_code = "KO" or tl.national_code = "JP"
ORDER BY tl.national_code, dept_code, emp_id
;
/* Q5)
모든 직원의 사번, 사원명, 관리자명을 조회하시오.
관리자가 없거나 관리자 정보가 등록되지 않은 직원도 결과에 포함하고,
사번을 기준으로 오름차순 정렬하시오.
[테이블 정보]
- tbl_employee: 사번(emp_id), 사원명(emp_name), 관리자사번(manager_id)
- 같은 tbl_employee 테이블을 서로 다른 별칭으로 두 번 사용한다.
(힌트: SELF JOIN, LEFT JOIN)
*/
SELECT
    te.emp_id,
    te.emp_name,
    te_m.emp_name as "관리자명"

FROM tbl_employee te
left join tbl_employee te_m
on te.emp_id = te_m.manager_id


