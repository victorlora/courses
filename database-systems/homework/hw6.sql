
-- Author: Victor A. Lora
-- AIC: 
-- Course: CSI-305
-- Date: 03/24/16
-- Description: HW6

-- A.) 	Retrieve the names of all employees in department 5 who work more than
--	10 hours per week on the ProductX project.
SELECT 	concat(fname, ' ', minit, ' ', lname) as Employee
FROM 	Employee e
	    INNER JOIN Works_On wo
		ON e.SSN = wo.essn
	    INNER JOIN Project p
		ON wo.PNO = p.Pnumber
WHERE 	Pname = 'ProductX'
	AND wo.HOURS > 10
	AND e.DNO = 5;

-- B.) 	List the names of all employees who have a dependent with the same first
--	name as themselves.
SELECT concat(fname, ' ', minit, ' ', lname) as Employee
FROM Employee e
	INNER JOIN Dependent d
		ON e.ssn = d.essn
WHERE e.fname = d.dependent_name;


-- C.) 	List the names of all employees who are directly supervised by 
-- 	'Franklin Wong'
SELECT concat(fname, ' ', minit, ' ', lname) as Employee
FROM Employee e
WHERE e.super_ssn = (SELECT ssn
		     FROM Employee
		     WHERE fname = 'Franklin'
			   AND lname = 'Wong' );

-- D.)	For each project, list the project name and the total hours per week
--	(by all employees) spend on that project
SELECT p.Pname, SUM(wo.hours)
FROM Project p
	INNER JOIN Works_On wo
		ON p.Pnumber = wo.pno
GROUP BY p.Pname;


-- E.)	Retrieve the names of all employees who work on every project.
SELECT concat(fname, ' ', minit, ' ', lname) as Employee_Name
FROM Employee e
WHERE NOT EXISTS (SELECT pnumber
		  FROM Project p
		  WHERE NOT EXISTS (SELECT pno
				    FROM Works_On wo
				    WHERE e.ssn = wo.essn
					  AND wo.pno = p.pnumber));

-- F.)	Retrieve the names of all employees who do not work on any project.
SELECT concat(fname, ' ', minit, ' ', lname) as Employee_Name
FROM Employee e
WHERE e.ssn NOT IN (SELECT wo.essn
		    FROM Works_On wo);


-- G.) 	For each department, rerieve the department name and the average
--	salary for all employees working that department.
SELECT d.dname, AVG(e.salary)
FROM Department d
	INNER JOIN Employee e
	    ON e.dno = d.dnumber
GROUP BY d.dnumber;


-- H.)	Retrieve the average salary for all female employees
SELECT AVG(e.salary)
FROM Employee e
WHERE e.Sex = 'F';


-- I.)	Find the names and addresses of all employees who work on at least one 
-- 	project located in Houston but whose department has no location in 
--	Houston.
SELECT concat(e.fname, ' ', e.minit, ' ', e.lname) as Employee_Name, e.Address
FROM Employee e
	INNER JOIN Works_On wo
	    ON e.ssn = wo.essn
WHERE wo.pno IN (SELECT pnumber
		 FROM Project p
		 WHERE p.plocation = 'Houston')
	  	       AND e.dno NOT IN (SELECT d.dnumber
	  				 FROM Department d
	  				          INNER JOIN Dept_Locations dl
	  				  	      ON d.dnumber = dl.dnumber
	  				 WHERE dl.dlocation = 'Houston');


-- J.) List the last names of al department managers who have no dependents.
SELECT lname 'Last Name'
FROM    Employee
WHERE 	super_ssn IS NULL
      	AND ssn NOT IN (SELECT essn
			FROM Dependent);
