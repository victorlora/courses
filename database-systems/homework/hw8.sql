
-- Author: Victor A. Lora
-- AIC: 
-- Course: CSI-305
-- Date: 03/24/16
-- Description: HW8


-- 1.)	Create your own copy of jobdata in your data db07
CREATE TABLE myjobdata
AS
SELECT *
FROM company.jobdata;

-- 2.)	Having created your own copy of the table, create a VIEW that displays:
--		EmplId, Hire Date, Retire Date, Years on Job, Final Title
CREATE VIEW jobdata_question2
AS
SELECT DISTINCT mjd.emplid 'EmplId', hire_data.effdt as 'Hire Date',
				ret_data.effdt as 'Retire Date',
				TIMESTAMPDIFF(YEAR, hire_data.effdt, ret_data.effdt) as 
				'Years on Job', ret_data.title as 'Final Title'
FROM 	myjobdata mjd, myjobdata hire_data, myjobdata ret_data
WHERE 	mjd.emplid = hire_data.emplid
		AND mjd.emplid = ret_data.emplid
		AND hire_data.action = 'HIR'
		AND ret_data.action = 'RET';

-- 3.)	Create another view that displays:
--		Title, Dept, Number of Active Employees, Average PayRate
--		Group By title, dpet, order by dept
CREATE VIEW jobdata_question3
AS
SELECT 	title, dept, COUNT(emplid), AVG(payrate)
FROM	myjobdata mjd1
WHERE	status = 'A'
		AND effdt = (SELECT MAX(effdt)
					 FROM myjobdata mjd2
					 WHERE mjd2.emplid = mjd1.emplid)
GROUP BY title, dept
ORDER BY dept;

-- 4.) Test your VIEWs by selecting all tuples from them.
-- Test myjobdata_question2
SELECT *
FROM jobdata_question2;

-- Test myjobdata_question3
SELECT *
FROM jobdata_question3;

-- 5a.) To see that your views update correctly, insert a RET action for
--		employee with EmplId = 99999 with the sql statement
INSERT INTO myjobdata
	(EmplId, EffDt, Action, Status, Dept, Title, Payrate)
VALUES
	(99999, '1999-01-25', 'RET', 'I', 100, 'Accountant', 10.00);

-- 5b.)	Select all tuples from the views again to see that they retrieve the
--		updated information
-- Test myjobdata_question2
SELECT *
FROM jobdata_question2;

-- Test myjobdata_question3
SELECT *
FROM jobdata_question3;

-- DROP myjobdata and VIEWs
DROP TABLE myjobdata;
DROP VIEW jobdata_question2;
DROP VIEW jobdata_question3;
