-- Author(s):   Victor A. Lora, Ron Gerschel, Jon Goldberg
-- Course:      CSI-305
-- Date:        04/24/15
-- Description: Theater Group Project: Management Queries

-- Contact Table --
--
-- INSERT
--
INSERT INTO Contact
(fname, lname, location_id, phone_number, isSponsor, isPatron)
VALUES
(<fname>, ,<lname>, <location_id>, <phone_number>, <isSponsor(0/1)>, <isPatron(0/1)>);
--
-- NOTE: For contact inserts, you'll want to insert the location first and then use 
--	the info in the following link (http://php.net/manual/en/mysqli.insert-id.php) to 
--	get the object id of the location insert and use that as the location_id in contact.
--	Ask me if you have any questions.
--
-- UPDATE
--
UPDATE 	Contact
SET		fname 	 = <fname>,
		lname  	 = <lname>,
		phone_number = <phone_number>,
		isSponsor	 = <isSponsor(0/1)>,
		isPatron	 = <isPatron(0/1)>
WHERE   objectid = <objectid>
-- Other WHERE possibilities
WHERE	fname = <fname>,
	AND lname  = <lname>;
--
-- DELETE
--
DELETE FROM Contact
WHERE   objectid = <objectid>
-- Other WHERE possibilities
WHERE	fname = <fname>,
	AND lname  = <lname>;

-- Corporation Table --
--
-- INSERT
--
INSERT INTO Corporation
(name, location_id, phone_number)
VALUES
(<name>, ,<location_id>, <phone_number>);
--
-- UPDATE
--
UPDATE  Corporation
SET 	name = <name>,
		phone_number = <phone_number>
WHERE	name = <name>
-- Other WHERE possibilities
WHERE	objectid = <objectid>
--
-- DELETE
--
DELETE FROM Corporation
WHERE name = <name>
-- Other WHERE possibilities
WHERE	objectid = <objectid>

-- Location Table --
--
-- INSERT
--
INSERT INTO Location
(street, city, state, country, zip_code)
VALUES
(<street>, ,<city>, <state>, <country>, <zip_code>);
--
-- UPDATE
--
UPDATE 	Location
SET		street 	 = <street>,
		city  	 = <city>,
		state    = <state>,
		country	 = <country>,
		zip_code = <zip_code>
WHERE   objectid = <objectid>
-- Other WHERE possibilities
WHERE	objectid = (SELECT 	c.location_id
					FROM	Contact c
					WHERE	fname = <fname>,
						AND lname  = <lname>);
--
WHERE	objectid = (SELECT 	c.location_id
					FROM	Corporation c
					WHERE	name = <name>);
--
WHERE	objectid = (SELECT 	c.location_id
					FROM	Contact/Corporation c
					WHERE	c.objectid = <contact_id>;
--
-- DELETE
--
DELETE FROM Location
WHERE   objectid = <objectid>
-- Other WHERE possibilities
WHERE	objectid = (SELECT 	c.location_id
					FROM	Contact c
					WHERE	fname = <fname>,
						AND lname  = <lname>);
--
WHERE	objectid = (SELECT 	c.location_id
					FROM	Contact c
					WHERE	c.objectid = <contact_id>;

-- Sponsorship --
--
-- INSERT
--
INSERT INTO Sponsorship
(contact_id/corporation_id, donation_type, amount, description, donation_date)
VALUES
(<contact_id/corporation_id>, <donation_type>, <amount>, <description>, <donation_date>);
--
-- UPDATE
--
UPDATE  Sponsorship
SET 	donation_type = <donation_type>,
		amount        = <amount>,
		description   = <description>
WHERE	contact_id/corporation_id = <contact_id/corporation_id>
-- Other WHERE possibilities
WHERE	objectid = <objectid>
--
-- DELETE
--
DELETE FROM Sponsorship
WHERE	contact_id/corporation_id = <contact_id/corporation_id> -- May delete multiple rows 
	AND	created_date = <created_date>							-- -> Narrow down by created date
-- Other WHERE possibilities
WHERE	objectid = <objectid>

-- Expenses Table --

-- INSERT
INSERT INTO Expenses
	(amount, description, play_id, company_member_id)
VALUES
	(<amount>, ,<description>, <play_id>, <company_member_id>);

-- UPDATE ** Should only be available as ADMIN **
UPDATE Expenses
Set 	amount = <amount>
		description = <description>
		play_id = <play_id>
		company_member_id = <company_member_id>
WHERE objectid = <objectid>;

-- DELETE
DELETE FROM Expenses
WHERE   objectid = <objectid>;



-- Admission_Ticket Table --

-- INSERT 
INSERT INTO Admission_Ticket
	(contact_id, play_id, theater_id, seat_id)
VALUES
	(<contact_id>, <play_id>, <theater_id>, <seat_id>);


-- UPDATE
UPDATE Admission_Ticket
Set 	contact_id = <contact_id>
		play_id = <play_id>
		theater_id = <theater_id>
		seat_id = <seat_id>
WHERE objectid = <objectid>;


-- DELETE
DELETE FROM Admission_Ticket
WHERE objectid = <objectid>;

-- Theater Table

-- INSERT
INSERT INTO Theater
	(name)
VALUES
	(<name>);

-- UPDATE
UPDATE Theater
Set 	name = <name>
WHERE	objectid = <objectid>

-- DELETE
DELETE FROM Theater
WHERE objectid = <objectid>

