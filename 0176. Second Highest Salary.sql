/*
176. Second Highest Salary
*/
# Write your MySQL query statement below
select (select distinct salary from employee
order by salary desc
limit 1 offset 1) SecondHighestSalary

# Write your MySQL query statement below
select max(salary) SecondHighestSalary from employee where salary != (select max(salary) from employee)

/* Write your T-SQL query statement below */
select isnull((
select distinct salary
from employee
order by salary desc
offset 1 rows
fetch next 1 rows only), null) SecondHighestSalary

/* Write your PL/SQL query statement below */
SELECT NVL(A.SALARY, B.SALARY) SecondHighestSalary
FROM
(
    SELECT MAX(SALARY) SALARY
    FROM EMPLOYEE
    WHERE SALARY <> (
        SELECT MAX(SALARY)
        FROM EMPLOYEE
    )
) A,
(
    SELECT NULL SALARY
    FROM DUAL
) B