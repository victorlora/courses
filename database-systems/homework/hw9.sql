-- Author: Victor A. Lora
-- AIC: 
-- Course: CSI-305
-- Date: 04/12/16
-- Description: HW9

-- DROP tables/triggers
DROP TRIGGER removeDeath;
DROP TABLE myjobdata;
DROP TABLE retiree_hist;
DROP TABLE emplinfo;

-- Create myjobdata
CREATE TABLE myjobdata
AS
SELECT *
FROM company.jobdata;

-- Create retiree_hist 
CREATE TABLE retiree_hist
(
	emplid decimal(6,0) NOT NULL,
	hire_date date,
	retire_date date,
	death_date date,
	last_department decimal(3,0),
	title char(15),
	pay_rate decimal(6,2),
	PRIMARY KEY(emplid)
);

-- CREATE emplinfo
CREATE TABLE emplinfo
AS
SELECT DISTINCT mjd.emplid 'EmplId', hire_data.effdt as 'Hire_Date',
				ret_data.effdt as 'Retire_Date',
				TIMESTAMPDIFF(YEAR, hire_data.effdt, ret_data.effdt) as 
				'YearsOnJob', ret_data.title as 'Title', ret_data.payrate,
				ret_data.dept as 'Last_Department'
FROM 	myjobdata mjd, myjobdata hire_data, myjobdata ret_data
WHERE 	mjd.emplid = hire_data.emplid
		AND mjd.emplid = ret_data.emplid
		AND hire_data.action = 'HIR'
		AND ret_data.action = 'RET';

-- DISPLAY myjobdata
SELECT *
FROM myjobdata;

-- DISPLAY emplinfo
SELECT *
FROM emplinfo;

-- CREATE trigger
DELIMITER $$ 
CREATE TRIGGER removeDeath
AFTER INSERT ON retiree_hist FOR EACH ROW
BEGIN
	DELETE FROM myjobdata
	WHERE emplid = NEW.emplid;
END;
$$
DELIMITER ;

-- INSERT INTO retiree_hist
INSERT INTO retiree_hist
SELECT 	DISTINCT ei.emplid, ei.hire_date, ei.retire_date, curdate(), ei.last_department,
		ei.title, ei.payrate
FROM	emplinfo ei
WHERE	ei.emplid = 54321;

-- TEST INSERT
SELECT *
FROM retiree_hist;

-- TEST TRIGGER
SELECT *
FROM myjobdata;

-- DISPLAY retiree_hist
SELECT *
FROM retiree_hist;
