-- Author(s):   Victor A. Lora, Ron Gerschel, Jon Goldberg
-- Course:      CSI-305
-- Date:        04/24/15
-- Description: Theater Group Project: Selection Queries

-- Contact Select
SELECT 	*
FROM 	Contact c
	INNER JOIN Location l ON c.location_id = l.objectid
WHERE c.objectid = <objectid>;
-- OR
WHERE 	fname = <fname>
	AND lname = <lname>;

-- Corporation Select
SELECT 	*
FROM 	Corporation c
	INNER JOIN Location l ON c.location_id = l.objectid
WHERE c.objectid = <objectid>;
-- OR
WHERE 	name = <name>;