-- Author(s):   Victor A. Lora, Ron Gerschel, Jon Goldberg
-- Course:      CSI-305
-- Date:        04/10/15
-- Description: Theater Group Project: Initialization Queries

-- DROP EXISTING TRIGGERS --
DROP TRIGGER IF EXISTS `contact_bi`;
DROP TRIGGER IF EXISTS `contact_bu`;
DROP TRIGGER IF EXISTS `corporation_bi`;
DROP TRIGGER IF EXISTS `corporation_bu`;
DROP TRIGGER IF EXISTS `sponsorship_bi`;
DROP TRIGGER IF EXISTS `sponsorship_bu`;
DROP TRIGGER IF EXISTS `program_bi`;
DROP TRIGGER IF EXISTS `program_bu`;
DROP TRIGGER IF EXISTS `company_bi`;
DROP TRIGGER IF EXISTS `company_bu`;
DROP TRIGGER IF EXISTS `dues_bi`;
DROP TRIGGER IF EXISTS `dues_bu`;
DROP TRIGGER IF EXISTS `admission_ticket_bi`;

-- DROP FOREIGN KEY CONSTRAINTS --
ALTER TABLE `Works_On` DROP FOREIGN KEY `wo_company_member_id_fk`;
ALTER TABLE `Works_On` DROP FOREIGN KEY `wo_play_id_fk`;
ALTER TABLE `Sponsorship_Play_Link` DROP FOREIGN KEY `spl_play_id_fk`;
ALTER TABLE `Sponsorship_Play_Link` DROP FOREIGN KEY `spl_sponsor_id_fk`;

--
-- DROP EXISTING TABLES --
--
DROP TABLE IF EXISTS Contact;
DROP TABLE IF EXISTS Corporation;
DROP TABLE IF EXISTS Sponsorship;
DROP TABLE IF EXISTS Location;
DROP TABLE IF EXISTS Play;
DROP TABLE IF EXISTS Program;
DROP TABLE IF EXISTS Company_Members;
DROP TABLE IF EXISTS Company;
DROP TABLE IF EXISTS Expenses;
DROP TABLE IF EXISTS Works_On;
DROP TABLE IF EXISTS Dues;
DROP TABLE IF EXISTS Sponsorship_Play_Link;
DROP TABLE IF EXISTS Company_Members;
DROP TABLE IF EXISTS Expenses;
DROP TABLE IF EXISTS Admission_Ticket;
DROP TABLE IF EXISTS Expenses;
DROP TABLE IF EXISTS Theater;
DROP TABLE IF EXISTS Seats;

--
-- BEGIN OF TABLE CREATION SCRIPT --
--
CREATE TABLE Contact
(
    objectid serial PRIMARY KEY,
    fname varchar(64),
    lname varchar(64),
    accountType varchar(64) DEFAULT 'Regular' NOT NULL,
    uname varchar(64) UNIQUE,
    password varchar(64),
    location_id bigint,
    phone_number varchar(15),
    created_date datetime,
    modified_date datetime,
    isSponsor bit(1) DEFAULT 0 NOT NULL,
    isPatron bit(1) DEFAULT 0 NOT NULL
);

CREATE TABLE Corporation
(
    objectid serial PRIMARY KEY,
    name varchar(64),
    location_id bigint,
    phone_number varchar(15),
    created_date datetime,
    modified_date datetime
);

CREATE TABLE Location
(
    objectid serial PRIMARY KEY,
    street varchar(64),
    city varchar(64),
    state varchar(2),
    country varchar(64),
    zip_code int(5)
);

CREATE TABLE Sponsorship
(
    objectid serial PRIMARY KEY,
    contact_id bigint,
    corporation_id bigint,
    donation_type varchar(64),
    amount decimal(20,2),
    description varchar(120),
    created_date datetime,
    modified_date datetime
);

CREATE TABLE Play
(
    objectid serial PRIMARY KEY,
    company_id bigint NOT NULL,
    theater_id bigint NOT NULL,
    title varchar(64),
    author varchar(64),
    genre varchar(64),
    number_of_acts int(2),
    active bit(1) DEFAULT 1 NOT NULL
);

CREATE TABLE Sponsorship_Play_Link
(
    sponsorship_id bigint UNSIGNED NOT NULL,
    play_id bigint UNSIGNED NOT NULL,
    PRIMARY KEY (sponsorship_id, play_id)
);

CREATE TABLE Company_Members (
    objectid serial PRIMARY KEY,
    contact_id bigint NOT NULL,
    company_id bigint NOT NULL,
    bio varchar(240)
);

CREATE TABLE Program
(
    objectid serial PRIMARY KEY,
    play_id bigint NOT NULL,
    created_date datetime,
    modified_date datetime
);

CREATE TABLE Works_On
(
    company_member_id bigint UNSIGNED NOT NULL,
    play_id bigint UNSIGNED NOT NULL,
    PRIMARY KEY (company_member_id, play_id)
);

CREATE TABLE Company 
(
    objectid serial PRIMARY KEY,
    company_name varchar(64) NOT NULL,
    description varchar(120),
    created_date datetime,
    modified_date datetime
);

CREATE TABLE Expenses 
(
    objectid serial PRIMARY KEY,
    amount decimal(20,2),
    description varchar(120),
    play_id int(5),
    cast_member_id int(5),
    created_date datetime,
    modified_date datetime
);

CREATE TABLE Dues 
(
    objectid serial PRIMARY KEY,
    contact_id bigint,
    amount decimal(20,2),
    isPaid bit(1) DEFAULT 0 NOT NULL,
    type varchar(64),
    description varchar(128),
    created_date datetime,
    modified_date datetime
);

CREATE TABLE Admission_Ticket (
    objectid serial PRIMARY KEY,
    contact_id bigint NOT NULL,
    play_id int(5) NOT NULL, 
    theater_id int(5) NOT NULL
);

CREATE TABLE Theater (
    objectid serial PRIMARY KEY,
    name varchar(50)
);

CREATE TABLE Seats(
    objectid serial PRIMARY KEY,
    theater_id bigint NOT NULL,
    play_id bigint NOT NULL,
    seats int DEFAULT 500
);

-- END TABLE CREATION SCRIPT -- 

-- BEGIN TRIGGER SCRIPT --
--
-- Contact: Created_Date Trigger
--
DELIMITER ;;
CREATE TRIGGER `contact_bi` BEFORE INSERT ON `Contact` FOR EACH ROW
BEGIN
    SET NEW.created_date = CURRENT_TIMESTAMP();
END;;
DELIMITER ;
--
-- Contact: Modified_Date Trigger
--
DELIMITER ;;
CREATE TRIGGER `contact_bu` BEFORE UPDATE ON `Contact` FOR EACH ROW
BEGIN
    SET NEW.modified_date = CURRENT_TIMESTAMP();
END;;
DELIMITER ;
--
-- Corporation: Created_Date Trigger
--
DELIMITER ;;
CREATE TRIGGER `corporation_bi` BEFORE INSERT ON `Corporation` FOR EACH ROW
BEGIN
    SET NEW.created_date = CURRENT_TIMESTAMP();
END;;
DELIMITER ;
--
-- Corporation: Modified_Date Trigger
--
DELIMITER ;;
CREATE TRIGGER `corporation_bu` BEFORE UPDATE ON `Corporation` FOR EACH ROW
BEGIN
    SET NEW.modified_date = CURRENT_TIMESTAMP();
END;;
DELIMITER ;
--
-- Program: Created_Date Trigger
--
DELIMITER ;;
CREATE TRIGGER `program_bi` BEFORE INSERT ON `Program` FOR EACH ROW
BEGIN
    SET NEW.created_date = CURRENT_TIMESTAMP();
END;;
DELIMITER ;
--
-- Program: Modified_Date Trigger
--
DELIMITER ;;
CREATE TRIGGER `program_bu` BEFORE UPDATE ON `Program` FOR EACH ROW
BEGIN
    SET NEW.modified_date = CURRENT_TIMESTAMP();
END;;
DELIMITER ;
--
-- Company: Created_Date Trigger
--
DELIMITER ;;
CREATE TRIGGER `company_bi` BEFORE INSERT ON `Company` FOR EACH ROW
BEGIN
    SET NEW.created_date = CURRENT_TIMESTAMP();
END;;
DELIMITER ;
--
-- Company: Modified_Date Trigger
--
DELIMITER ;;
CREATE TRIGGER `company_bu` BEFORE UPDATE ON `Company` FOR EACH ROW
BEGIN
    SET NEW.modified_date = CURRENT_TIMESTAMP();
END;;
DELIMITER ;
--
-- Dues: Created_Date Trigger
--
DELIMITER ;;
CREATE TRIGGER `dues_bi` BEFORE INSERT ON `Dues` FOR EACH ROW
BEGIN
    SET NEW.created_date = CURRENT_TIMESTAMP();
END;;
DELIMITER ;
--
-- Dues: Modified_Date Trigger
--
DELIMITER ;;
CREATE TRIGGER `dues_bu` BEFORE UPDATE ON `Dues` FOR EACH ROW
BEGIN
    SET NEW.modified_date = CURRENT_TIMESTAMP();
END;;
DELIMITER ;
--
-- Admission Ticket: Decrement seat count trigger
--
DELIMITER ;;
CREATE TRIGGER `admission_ticket_bi` BEFORE INSERT ON `Admission_Ticket` FOR EACH ROW
BEGIN
    UPDATE `Seats`
    SET seats = seats - 1
    WHERE theater_id = new.theater_id
        AND play_id = new.play_id;
END;;
DELIMITER ;
--
-- Play Addition: Add Play Listing to Seats Table for Seat Count
--
DELIMITER ;;
CREATE TRIGGER `play_addition_ai` AFTER INSERT ON `Play` FOR EACH ROW
BEGIN
    INSERT INTO Seats (theater_id, play_id)
    VALUES
    (new.theater_id, new.objectid);
END;;
DELIMITER ;


-- END TRIGGER SCRIPT -- 

-- BEGIN CONSTRAINT SCRIPT --
ALTER TABLE `Works_On` 
ADD CONSTRAINT `wo_company_member_id_fk` 
FOREIGN KEY (`company_member_id`) 
REFERENCES `Company_Members` (`objectid`)
ON DELETE CASCADE;

ALTER TABLE `Works_On` 
ADD CONSTRAINT `wo_play_id_fk` 
FOREIGN KEY (`play_id`) 
REFERENCES `Play` (`objectid`)
ON DELETE CASCADE;
--
-- FK CONSTRAINT: Sponsorship_Play_Link
--
ALTER TABLE `Sponsorship_Play_Link` 
ADD CONSTRAINT `spl_play_id_fk` 
FOREIGN KEY (`play_id`) 
REFERENCES `Play` (`objectid`)
ON DELETE CASCADE;

ALTER TABLE `Sponsorship_Play_Link` 
ADD CONSTRAINT `spl_sponsor_id_fk` 
FOREIGN KEY (`sponsorship_id`) 
REFERENCES `Sponsorship` (`objectid`)
ON DELETE CASCADE;

-- END CONSTRAINT SCRIPT --

-- BEGIN INSERT SCRIPT --
--
-- Insert into Contact
--
INSERT INTO Contact
(fname, lname, uname, password, location_id, phone_number)
VALUES
('Victor', 'Lora', 'vlora', 'password2', 1, '1111111111');

INSERT INTO Contact
(fname, lname, uname, password, location_id, phone_number)
VALUES
('Jon', 'Goldberg', 'jgoldberg', 'password2', 1, '2222222222');

INSERT INTO Contact
(fname, lname, uname, password, location_id, phone_number)
VALUES
('Ron', 'Gerschel', 'rgerschel', 'password2', 1, '3333333333');

INSERT INTO Contact
(fname, lname, uname, password, location_id, phone_number)
VALUES
('George', 'Benjamin', 'gbenjamin', 'passwordgb1', 1, '4444444444');

INSERT INTO Contact
(fname, lname, location_id, phone_number)
VALUES
('John', 'Doe', 1, '5555555555');

INSERT INTO Contact
(fname, lname, location_id, phone_number)
VALUES
('Jane', 'Doe', 1, '6666666666');

INSERT INTO Contact
(fname, lname, accountType, uname, password, location_id, phone_number)
VALUES
('Admin', 'Admin', 'Administrator', 'admin', 'swordfish', 1, '1115558888');
--
-- Insert into Location
--
INSERT INTO Location
(street, city, state, country, zip_code)
VALUES
('2400 Chew Street', 'Allentown', 'PA', 'USA', 18104);

INSERT INTO Location
(street, city, state, country, zip_code)
VALUES
('123 Main Street', 'Allentown', 'PA', 'USA', 18104);

INSERT INTO Location
(street, city, state, country, zip_code)
VALUES
('456 Lei Street', 'Allentown', 'PA', 'USA', 18104);

INSERT INTO Location
(street, city, state, country, zip_code)
VALUES
('789 Grove Street', 'Allentown', 'PA', 'USA', 18104);
--
-- Insert into Company
--
INSERT INTO Company
(company_name, description)
VALUES
('Circus', 'Small production run by Muhlenberg Students.');

INSERT INTO Company
(company_name, description)
VALUES
('Mad Dog', 'Professional theater production company.');

INSERT INTO Company
(company_name, description)
VALUES
('Bad Robot', 'Professional Hollywood production company.');
--
-- Insert into Company_Members
--
INSERT INTO Company_Members
(contact_id, company_id, bio)
VALUES
(1, 1, 'Junior, Theater major at Muhlenberg College. Numerous productions');

INSERT INTO Company_Members
(contact_id, company_id, bio)
VALUES
(2, 1, 'Senior, Theater major at Muhlenberg College.');

INSERT INTO Company_Members
(contact_id, company_id, bio)
VALUES
(3, 2, 'Senior, Theater major at Lehigh University.');

INSERT INTO Company_Members
(contact_id, company_id, bio)
VALUES
(4, 2, 'Freshmen, Theater major at Muhlenberg College.');

INSERT INTO Company_Members
(contact_id, company_id, bio)
VALUES
(5, 2, 'Sophomore, Theater major at DeSales University.');
--
-- Insert into Play
--
INSERT INTO Play
(company_id, theater_id, title, author, genre, number_of_acts)
VALUES
(1, 1, 'Romeo & Juliet', 'Shakespeare', 'Tragedy', 2);

INSERT INTO Play
(company_id, theater_id, title, author, genre, number_of_acts)
VALUES
(1, 1, 'Hello Dolly', 'Jane Doe', 'Comedy', 2);

INSERT INTO Play
(company_id, theater_id, title, author, genre, number_of_acts)
VALUES
(1, 1, 'Streetcar Named Desire', 'SomeGuy', 'Eh', 3);

INSERT INTO Play
(company_id, theater_id, title, author, genre, number_of_acts)
VALUES
(2, 1, 'Les Miserable', 'Tom Hooper', 'Tragedy', 3);
--
-- Insert into Works_On
--
INSERT INTO Works_On
(company_member_id, play_id)
VALUES
(1,1);

INSERT INTO Works_On
(company_member_id, play_id)
VALUES
(2,1);

INSERT INTO Works_On
(company_member_id, play_id)
VALUES
(1,2);

INSERT INTO Works_On
(company_member_id, play_id)
VALUES
(3,1);

INSERT INTO Works_On
(company_member_id, play_id)
VALUES
(4,2);
--
-- Insert into Theater
--
INSERT INTO Theater
(name)
VALUES
('Dorothy Hess Baker');

INSERT INTO Theater
(name)
VALUES
('Paul C. Empie');

INSERT INTO Theater
(name)
VALUES
('McAneny');


INSERT INTO Theater
(name)
VALUES
('Jon F Kennedy');

INSERT INTO Theater
(name)
VALUES
('Jalal S Poo');

INSERT INTO Theater
(name)
VALUES
('Victor P Lorax');
-- 
-- Insert into Sponsorship
--
INSERT INTO Sponsorship
(contact_id, corporation_id, donation_type, amount, description, created_date, modified_date)
VALUES
(1, 1, 'Cash', 1000, 'Annual' , CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Sponsorship
(contact_id, corporation_id, donation_type, amount, description, created_date, modified_date)
VALUES
(8, 4, 'Check', 3000.5, '' , CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Sponsorship
(contact_id, corporation_id, donation_type, amount, description, created_date, modified_date)
VALUES
(5, 6, 'Credit', 20, NULL , CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Sponsorship
(contact_id, corporation_id, donation_type, amount, description, created_date, modified_date)
VALUES
(11, 5, 'Love', 1000000, 'This is a great project' ,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--
-- Insert into Program
--
INSERT INTO Program
(play_id, created_date, modified_date)
VALUES
(1 ,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Program
(play_id, created_date, modified_date)
VALUES
(2 ,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Program
(play_id, created_date, modified_date)
VALUES
(3 ,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Program
(play_id, created_date, modified_date)
VALUES
(4 ,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Program
(play_id, created_date, modified_date)
VALUES
(1 ,CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--
-- Insert into Expenses
--
INSERT INTO Expenses
(amount, description, play_id, cast_member_id)
VALUES
(50, 'Prop Pipe', 1 , 1);

INSERT INTO Expenses
(amount, description, play_id, cast_member_id)
VALUES
(1000, 'Lumber', 2 , 3);

INSERT INTO Expenses
(amount, description, play_id, cast_member_id)
VALUES
(500, 'Lamp Post', 1 , 4);

INSERT INTO Expenses
(amount, description, play_id, cast_member_id)
VALUES
(500, 'Lamp Post', 1 , 4);

INSERT INTO Expenses
(amount, description, play_id, cast_member_id)
VALUES
(5890.25, 'Musicians', 3 , 2);
--
-- Insert into Sponsor_Play_Link
--
INSERT INTO Sponsorship_Play_Link
(sponsorship_id, play_id)
VALUES
(1,1);

INSERT INTO Sponsorship_Play_Link
(sponsorship_id, play_id)
VALUES
(2,1);

INSERT INTO Sponsorship_Play_Link
(sponsorship_id, play_id)
VALUES
(3,1);

INSERT INTO Sponsorship_Play_Link
(sponsorship_id, play_id)
VALUES
(3,2);

INSERT INTO Sponsorship_Play_Link
(sponsorship_id, play_id)
VALUES
(4,2);

INSERT INTO Sponsorship_Play_Link
(sponsorship_id, play_id)
VALUES
(3,4);
--
-- Insert into Dues
--
INSERT INTO Dues
(contact_id, amount, type, description, created_date, modified_date)
VALUES
(1, 50, 'Fee', 'Annual Member Fee', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Dues
(contact_id, amount, type, description, created_date, modified_date)
VALUES
(2, 50, 'Fee', 'Annual Member Fee', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Dues
(contact_id, amount, type, description, created_date, modified_date)
VALUES
(3, 50, 'Fee', 'Annual Member Fee', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Dues
(contact_id, amount, type, description, created_date, modified_date)
VALUES
(3, 25, 'Late Fee', 'Late Annual Member Fee', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Dues
(contact_id, amount, type, description, created_date, modified_date)
VALUES
(4, 25, 'Ticket', 'Ticket Fee', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Dues
(contact_id, amount, type, description, created_date, modified_date)
VALUES
(5, 100, 'Premium Member', 'Premium Member Fee', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Dues
(contact_id, amount, isPaid, type, description, created_date, modified_date)
VALUES
(6, 75, 1, 'Admin Member', 'Admin Member Fee', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--
-- Insert into Corporation
--
INSERT INTO Corporation
(name, location_id, phone_number, created_date, modified_date)
VALUES
('Coca-Cola', 10, 18007654321, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Corporation
(name, location_id, phone_number, created_date, modified_date)
VALUES
('Nivea', 2, 18008765432, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Corporation
(name, location_id, phone_number, created_date, modified_date)
VALUES
('Jalals Sewage', 5, 18001234567, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Corporation
(name, location_id, phone_number, created_date, modified_date)
VALUES
('Apple', 3, 18000987654, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO Corporation
(name, location_id, phone_number, created_date, modified_date)
VALUES
('Arduino', 2, 18009876543, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--
-- Insert into Admission_Ticket
--
INSERT INTO Admission_Ticket
(contact_id, play_id, theater_id)
VALUES
(1, 2, 2);

INSERT INTO Admission_Ticket
(contact_id, play_id, theater_id)
VALUES
(1, 3, 2);

INSERT INTO Admission_Ticket
(contact_id, play_id, theater_id)
VALUES
(6, 3, 2);

INSERT INTO Admission_Ticket
(contact_id, play_id, theater_id)
VALUES
(6, 4, 2);

--
-- Insert into Seats
--
INSERT INTO Seats
(play_id, theater_id)
VALUES
(1,1);

INSERT INTO Seats
(play_id, theater_id)
VALUES
(1,2);

INSERT INTO Seats
(play_id, theater_id)
VALUES
(2,1);

INSERT INTO Seats
(play_id, theater_id)
VALUES
(1,3);

INSERT INTO Seats
(play_id, theater_id)
VALUES
(3,1);


-- END INSERT SCRIPT