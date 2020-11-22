
-- Author: Victor A. Lora
-- AIC: 
-- Course: CSI-305
-- Date: 03/29/16
-- Description: HW7


-- A.) 	Print the employees' first and last names, and the project numbers they
-- 		work on for those employees that DON'T work on a project with the 
--		employee whose last name is 'Wong'
SELECT 	e.fname, e.lname, wo.pno
FROM	Works_On wo
			INNER JOIN Employee e ON wo.essn = e.ssn
WHERE	wo.pno NOT IN ( SELECT wo.pno
						FROM Works_On wo
								INNER JOIN Employee e ON wo.essn = e.ssn
						WHERE e.lname = 'Wong')
ORDER BY e.fname, e.lname, wo.pno;

-- B.)	Print the name of all employees that do have dependents. Also print the
--		number of dependents each has.
SELECT 	e.fname, e.lname, COUNT(d.essn)
FROM	Dependent d
			INNER JOIN Employee e ON d.essn = e.ssn
GROUP BY d.essn
ORDER BY e.fname, e.lname, COUNT(d.essn);

-- C.)	Retrieve the first and alst names of all employees who work on any
--		project in department number 4.
SELECT 	e.fname, e.lname
FROM	Employee e
			INNER JOIN Works_On wo ON wo.essn = e.ssn
			INNER JOIN Project p ON wo.pno = p.pnumber
WHERE	p.dnum = 4
ORDER BY e.fname, e.lname;

-- D.)	List the first and last names of managers that have at least one 
--		dependent
SELECT 	e.fname, e.lname
FROM	Employee e
			INNER JOIN Dependent d ON d.essn = e.ssn
WHERE	e.super_ssn IS NULL
GROUP BY d.essn
HAVING COUNT(d.essn) > 1
ORDER BY e.fname, e.lname;

-- E.)	Retrieve the names of all employees who work in the department that has
--		the employees with the highest salary among all employees. Also display
--		the department name.
SELECT 	e.fname, e.lname, d.dname
FROM	Employee e
			INNER JOIN Department d ON e.dno = d.dnumber
WHERE	e.dno = (SELECT dno
				 FROM	Employee e
				 HAVING MAX(salary))
ORDER BY e.fname, e.lname, d.dname;

-- F.)	Retrieve the names of all employees whose supervisors' supervisor has
--		888665555 for ssn.
SELECT e1.fname, e1.lname
FROM Employee e1
		INNER JOIN Employee e2 ON e1.super_ssn = e2.ssn
WHERE e2.super_ssn = 888665555
ORDER BY e1.fname, e1.lname;
