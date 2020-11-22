# Dump of table Admission_Ticket
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Admission_Ticket`;

CREATE TABLE `Admission_Ticket` (
  `objectid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `contact_id` bigint(20) NOT NULL,
  `play_id` int(5) NOT NULL,
  `theater_id` int(5) NOT NULL,
  PRIMARY KEY (`objectid`),
  UNIQUE KEY `objectid` (`objectid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

LOCK TABLES `Admission_Ticket` WRITE;
/*!40000 ALTER TABLE `Admission_Ticket` DISABLE KEYS */;

INSERT INTO `Admission_Ticket` (`objectid`, `contact_id`, `play_id`, `theater_id`)
VALUES
	(1,1,2,2),
	(2,1,3,2),
	(3,6,3,2),
	(4,6,4,2),
	(5,2,0,0),
	(6,1,1,1),
	(7,2,1,1),
	(8,4,1,1),
	(9,2,1,1),
	(10,2,2,1),
	(11,3,1,1),
	(12,2,3,1),
	(13,2,4,1),
	(14,3,1,1),
	(15,1,1,1),
	(16,10,1,1),
	(17,10,1,1),
	(18,10,11,2),
	(19,14,1,1),
	(20,14,8,2);

/*!40000 ALTER TABLE `Admission_Ticket` ENABLE KEYS */;
UNLOCK TABLES;

DELIMITER ;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`project3`@`localhost` */ /*!50003 TRIGGER `admission_ticket_bi` BEFORE INSERT ON `Admission_Ticket` FOR EACH ROW UPDATE `Seats`
    SET seats = seats - 1
    WHERE theater_id = new.theater_id
    	AND play_id = new.play_id */;;
DELIMITER ;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;


# Dump of table Company
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Company`;

CREATE TABLE `Company` (
  `objectid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `company_name` varchar(64) NOT NULL,
  `description` varchar(120) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`objectid`),
  UNIQUE KEY `objectid` (`objectid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

LOCK TABLES `Company` WRITE;
/*!40000 ALTER TABLE `Company` DISABLE KEYS */;

INSERT INTO `Company` (`objectid`, `company_name`, `description`, `created_date`, `modified_date`)
VALUES
	(1,'Circus','Small production run by Muhlenberg Students','2016-04-24 14:18:22','2016-05-07 22:44:53'),
	(2,'Mad Dog','Professional theater production company.','2016-04-24 14:18:22',NULL),
	(3,'Bad Robot','Professional Hollywood production company.','2016-04-24 14:18:22',NULL),
	(7,'Apple','They wany to take over theater now','2016-05-08 22:53:26','2016-05-08 22:53:47'),
	(8,'Picardo Inc.','Making investments wisely','2016-05-08 22:54:19','2016-05-08 22:55:01'),
	(9,'Microsoft Inc.','We are the theater business','2016-05-08 22:56:04',NULL);

/*!40000 ALTER TABLE `Company` ENABLE KEYS */;
UNLOCK TABLES;

DELIMITER ;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`project3`@`localhost` */ /*!50003 TRIGGER `company_bi` BEFORE INSERT ON `Company` FOR EACH ROW BEGIN
    SET NEW.created_date = CURRENT_TIMESTAMP();
END */;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`project3`@`localhost` */ /*!50003 TRIGGER `company_bu` BEFORE UPDATE ON `Company` FOR EACH ROW BEGIN
    SET NEW.modified_date = CURRENT_TIMESTAMP();
END */;;
DELIMITER ;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;


# Dump of table Company_Members
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Company_Members`;

CREATE TABLE `Company_Members` (
  `objectid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `contact_id` bigint(20) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `bio` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`objectid`),
  UNIQUE KEY `objectid` (`objectid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;



# Dump of table Contact
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Contact`;

CREATE TABLE `Contact` (
  `objectid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(64) DEFAULT NULL,
  `lname` varchar(64) DEFAULT NULL,
  `uname` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `location_id` bigint(20) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `isSponsor` bit(1) NOT NULL DEFAULT b'0',
  `isPatron` bit(1) NOT NULL DEFAULT b'0',
  `accountType` varchar(64) NOT NULL DEFAULT '''Regular''',
  PRIMARY KEY (`objectid`),
  UNIQUE KEY `objectid` (`objectid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

LOCK TABLES `Contact` WRITE;
/*!40000 ALTER TABLE `Contact` DISABLE KEYS */;

INSERT INTO `Contact` (`objectid`, `fname`, `lname`, `uname`, `password`, `location_id`, `phone_number`, `created_date`, `modified_date`, `isSponsor`, `isPatron`, `accountType`)
VALUES
	(2,'Jonathan','Goldberg','jg1995','12345',2,'11122233335','2016-04-24 14:18:21','2016-05-08 22:50:54',b'0',b'1','Administrator'),
	(3,'Ron','Gerschel','rg12345','iloveapple',3,'3333333333','2016-04-24 14:18:21','2016-05-08 21:34:00',b'0',b'1','Regular'),
	(4,'Jay','Sean','js1967','smith12',4,'4444444444','2016-04-24 14:18:22','2016-05-08 21:39:13',b'1',b'0','Regular'),
	(5,'John','Doe','jd1982','janedoe123',5,'5555555555','2016-04-24 14:18:22','2016-05-08 21:34:19',b'0',b'0','Regular'),
	(7,'Harold','Gold','hg1990','55555',26,'8889990000','2016-04-28 09:06:57','2016-05-07 22:48:09',b'0',b'0','Regular'),
	(8,'Larry','Marcus','lm1967','67890',27,'9990001234','2016-04-28 09:20:44','2016-05-07 22:48:09',b'1',b'0','Regular'),
	(10,'John','Smith','admin','hacker',29,'4444444444','2016-05-03 10:15:36','2016-05-08 20:51:54',b'0',b'1','Administrator'),
	(11,'George','Benjamin','gb','gb12345',30,'99922227777','2016-05-03 16:27:23','2016-05-08 20:50:27',b'0',b'1','Regular'),
	(12,'Adrian','Picardo','ap67890','13579',1,'1111111111','2016-05-08 22:17:11','2016-05-09 01:51:05',b'0',b'0','Regular'),
	(14,'Victor','Lora','vlora','12345',40,'2012891233','2016-05-09 01:52:29','2016-05-09 01:54:44',b'1',b'0','Administrator'),
	(15,'Jane','Smith','jsmith','Smith',41,'2013880234','2016-05-09 02:02:05',NULL,b'0',b'0','Regular');

/*!40000 ALTER TABLE `Contact` ENABLE KEYS */;
UNLOCK TABLES;

DELIMITER ;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`project3`@`localhost` */ /*!50003 TRIGGER `contact_bi` BEFORE INSERT ON `Contact` FOR EACH ROW BEGIN
    SET NEW.created_date = CURRENT_TIMESTAMP();
END */;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`project3`@`localhost` */ /*!50003 TRIGGER `contact_bu` BEFORE UPDATE ON `Contact` FOR EACH ROW BEGIN
    SET NEW.modified_date = CURRENT_TIMESTAMP();
END */;;
DELIMITER ;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;


# Dump of table Corporation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Corporation`;

CREATE TABLE `Corporation` (
  `objectid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `location_id` bigint(20) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`objectid`),
  UNIQUE KEY `objectid` (`objectid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

LOCK TABLES `Corporation` WRITE;
/*!40000 ALTER TABLE `Corporation` DISABLE KEYS */;

INSERT INTO `Corporation` (`objectid`, `name`, `location_id`, `phone_number`, `created_date`, `modified_date`)
VALUES
	(1,'Coca-Cola',10,'18007654321','2016-05-05 21:40:27','2016-05-05 21:40:27'),
	(2,'Nivea',2,'18008765432','2016-05-05 21:41:55','2016-05-05 21:41:55'),
	(3,'Arduino',2,'18009876543','2016-05-05 21:42:12','2016-05-05 21:42:12'),
	(4,'Apple',3,'18000987654','2016-05-05 21:42:29','2016-05-05 21:42:29'),
	(5,'Jalals Sewage',5,'18001234567','2016-05-05 21:42:49','2016-05-05 21:42:49'),
	(8,'Joe\'s Pizza',0,'2012891355','2016-05-08 18:56:48',NULL);

/*!40000 ALTER TABLE `Corporation` ENABLE KEYS */;
UNLOCK TABLES;

DELIMITER ;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`project3`@`localhost` */ /*!50003 TRIGGER `corporation_bi` BEFORE INSERT ON `Corporation` FOR EACH ROW BEGIN
    SET NEW.created_date = CURRENT_TIMESTAMP();
END */;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`project3`@`localhost` */ /*!50003 TRIGGER `corporation_bu` BEFORE UPDATE ON `Corporation` FOR EACH ROW BEGIN
    SET NEW.modified_date = CURRENT_TIMESTAMP();
END */;;
DELIMITER ;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;


# Dump of table Dues
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Dues`;

CREATE TABLE `Dues` (
  `objectid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `contact_id` bigint(20) DEFAULT NULL,
  `amount` decimal(20,2) DEFAULT NULL,
  `isPaid` bit(1) NOT NULL DEFAULT b'0',
  `type` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`objectid`),
  UNIQUE KEY `objectid` (`objectid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

LOCK TABLES `Dues` WRITE;
/*!40000 ALTER TABLE `Dues` DISABLE KEYS */;

INSERT INTO `Dues` (`objectid`, `contact_id`, `amount`, `isPaid`, `type`, `description`, `created_date`, `modified_date`)
VALUES
	(1,1,50.00,b'0','Fee','Annual Member Fee','2016-05-05 21:32:39','2016-05-03 21:32:39'),
	(2,2,50.00,b'0','Fee','Annual Member Fee','2016-05-05 21:32:53','2016-04-02 21:32:53'),
	(3,3,50.00,b'0','Fee','Annual Member Fee','2016-05-05 21:33:03','2016-01-02 21:33:03'),
	(4,3,25.00,b'0','Late Fee','Late Annual Member Fee','2016-05-05 21:33:21','2016-03-05 21:33:21'),
	(5,4,25.00,b'0','Ticket','Ticket Fee','2016-05-05 21:33:39','2016-02-02 21:33:39'),
	(6,5,100.00,b'0','Premium Member','Premium Member Fee','2016-05-05 21:34:06','2016-05-05 21:34:06'),
	(7,10,75.00,b'1','Admin Member','Admin Member Fee','2016-05-05 21:35:26','2016-05-05 21:35:26'),
	(8,11,100.00,b'0','Fee2','Not 2016','2015-05-05 21:35:26','2015-05-05 21:35:26');

/*!40000 ALTER TABLE `Dues` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Expenses
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Expenses`;

CREATE TABLE `Expenses` (
  `objectid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `amount` decimal(20,2) NOT NULL,
  `description` varchar(120) NOT NULL,
  `play_id` int(5) DEFAULT NULL,
  `company_member_id` int(5) DEFAULT NULL,
  PRIMARY KEY (`objectid`),
  UNIQUE KEY `objectid` (`objectid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

LOCK TABLES `Expenses` WRITE;
/*!40000 ALTER TABLE `Expenses` DISABLE KEYS */;

INSERT INTO `Expenses` (`objectid`, `amount`, `description`, `play_id`, `company_member_id`)
VALUES
	(1,50.00,'Prop Pipe',1,1),
	(2,1000.00,'Lumber',2,3),
	(3,500.00,'Lamp Post',1,4),
	(4,892.00,'HPL575',3,2),
	(5,5890.25,'Musicians',3,2);

/*!40000 ALTER TABLE `Expenses` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Location
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Location`;

CREATE TABLE `Location` (
  `objectid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `street` varchar(64) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `country` varchar(64) DEFAULT NULL,
  `zip_code` int(5) DEFAULT NULL,
  PRIMARY KEY (`objectid`),
  UNIQUE KEY `objectid` (`objectid`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;

LOCK TABLES `Location` WRITE;
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;

INSERT INTO `Location` (`objectid`, `street`, `city`, `state`, `country`, `zip_code`)
VALUES
	(1,'2400 Chew Street','Allentown','PA','USA',18104),
	(2,'123 Haupt Strasse','Zurich','AL','CH',8002),
	(3,'456 Lei Street','Ptown','NY','USA',85720),
	(4,'789 Grove Street','Rfield','NJ','USA',98344),
	(5,'248 Gordon Street ','Allentown ','PA','USA ',18104),
	(6,'1501 DollHouse Street','Allentown','PA','USA',18104),
	(7,'6130 Oneway Street','Ardsley','NY','USA',10502),
	(8,'1212 Bright Street','Los Angeles','CA','USA',90009),
	(9,'333 Park Avenue','Harrisburg','NY','USA',52345),
	(10,'333 Park Avenue','Harrisburg','NY','USA',52345),
	(11,'333 Park Avenue','Harrisburg','NY','USA',52345),
	(12,'76 East 56 Street','New York','NY','USA',67890),
	(13,'500 Randomhouse Rd','Dobbs Ferry','NY','USA',10553),
	(14,'500 Randomhouse Rd','Dobbs Ferry','NY','USA',10553),
	(15,'500 Randomhouse Rd','Dobbs Ferry','NY','USA',10553),
	(16,'500 Randomhouse Rd','Dobbs Ferry','NY','USA',10553),
	(17,'500 Randomhouse Rd','Dobbs Ferry','NY','USA',10553),
	(18,'500 Randomhouse Rd','Dobbs Ferry','NY','USA',10553),
	(19,'500 Randomhouse Rd','Dobbs Ferry','NY','USA',10553),
	(20,'500 Randomhouse Rd','Dobbs Ferry','NY','USA',10553),
	(21,'12 Carrot Way','East Brunswick','NJ','USA',56789),
	(22,'12 Carrot Way','East Brunswick','NJ','USA',56789),
	(23,'12 Carrot Way','East Brunswick','NJ','USA',56789),
	(24,'12 Carrot Way','East Brunswick','NJ','USA',56789),
	(25,'12 Carrot Way','East Brunswick','NJ','USA',56789),
	(26,'15 Carrot Way','North Brunswick','NY','USA',44444),
	(27,'Random Street','City','CA','USA',90865),
	(29,'2400 Chew Street','Allentown','PA','USA',18104),
	(30,'123 Random Street','Chicago','IL','USA',98765),
	(31,'1500 Grove Drive','Allentown','PA','USA',18104),
	(32,'1500 Grove Drive','Allentown','PA','USA',18104),
	(33,'1500 Grove Drive','Allentown','PA','USA',18104),
	(34,'15 East Street','New York','NY','USA',7660),
	(35,'15 Street','New York','NY','USA',11111),
	(36,'135dlfja','fadslfl','AZ','USA',112231),
	(37,'15 Street','New York','CO','USA',111111),
	(38,'15 Palm Road','New York','NY','USA',7661),
	(39,'15 Street','New York','NY','USA',11113),
	(40,'15 Palm Street','New York','NY','USA',170839),
	(41,'15 Grove Street','Chicago','AZ','USA',138484);

/*!40000 ALTER TABLE `Location` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Play
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Play`;

CREATE TABLE `Play` (
  `objectid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `theater_id` bigint(20) NOT NULL,
  `title` varchar(64) DEFAULT NULL,
  `author` varchar(64) DEFAULT NULL,
  `genre` varchar(64) DEFAULT NULL,
  `number_of_acts` int(2) DEFAULT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`objectid`),
  UNIQUE KEY `objectid` (`objectid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

LOCK TABLES `Play` WRITE;
/*!40000 ALTER TABLE `Play` DISABLE KEYS */;

INSERT INTO `Play` (`objectid`, `company_id`, `theater_id`, `title`, `author`, `genre`, `number_of_acts`, `active`)
VALUES
	(1,1,1,'Romeo & Juliet','Shakespeare','Tragedy',2,b'1'),
	(2,1,2,'Hello Dolly','Jane Doe','Comedy',2,b'1'),
	(3,3,3,'Streetcar Named Desire','SomeGuy','Eh',3,b'1'),
	(4,2,6,'Les Miserable','Tom Hooper','Tragedy',3,b'1'),
	(5,1,3,'Jalal Sewer: The Musical','Jon','Comedy',2,b'1'),
	(7,3,5,'Victor Musical','John','Sad',2,b'1'),
	(8,3,2,'Victor Musical','John','Sad',2,b'1'),
	(9,2,4,'Jesus Christ Superstar','Andrew Loyd Webber','Drama',2,b'1'),
	(10,2,4,'Spamalot','Eric Idle','Comedy',2,b'1'),
	(11,3,2,'My Fair Lady','Alan Jay Lerner','Comedy',2,b'1');

/*!40000 ALTER TABLE `Play` ENABLE KEYS */;
UNLOCK TABLES;

DELIMITER ;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`project3`@`localhost` */ /*!50003 TRIGGER `NewPlay` AFTER INSERT ON `Play` FOR EACH ROW INSERT INTO Seats (theater_id, play_id)
VALUES
(new.theater_id, new.objectid) */;;
DELIMITER ;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;


# Dump of table Program
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Program`;

CREATE TABLE `Program` (
  `objectid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `play_id` bigint(20) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`objectid`),
  UNIQUE KEY `objectid` (`objectid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

LOCK TABLES `Program` WRITE;
/*!40000 ALTER TABLE `Program` DISABLE KEYS */;

INSERT INTO `Program` (`objectid`, `play_id`, `created_date`, `modified_date`)
VALUES
	(1,1,'2016-05-05 20:48:31','2016-05-05 20:48:31'),
	(2,2,'2016-05-05 20:48:41','2016-05-05 20:48:41'),
	(3,3,'2016-05-05 20:48:45','2016-05-05 20:48:45'),
	(4,4,'2016-05-05 20:48:46','2016-05-05 20:48:46'),
	(5,1,'2016-05-05 20:48:59','2016-05-05 20:48:59');

/*!40000 ALTER TABLE `Program` ENABLE KEYS */;
UNLOCK TABLES;

DELIMITER ;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`project3`@`localhost` */ /*!50003 TRIGGER `program_bi` BEFORE INSERT ON `Program` FOR EACH ROW BEGIN
    SET NEW.created_date = CURRENT_TIMESTAMP();
END */;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION" */;;
/*!50003 CREATE */ /*!50017 DEFINER=`project3`@`localhost` */ /*!50003 TRIGGER `program_bu` BEFORE UPDATE ON `Program` FOR EACH ROW BEGIN
    SET NEW.modified_date = CURRENT_TIMESTAMP();
END */;;
DELIMITER ;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;


# Dump of table Seats
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Seats`;

CREATE TABLE `Seats` (
  `objectid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `theater_id` bigint(20) NOT NULL,
  `play_id` bigint(20) NOT NULL,
  `seats` int(11) DEFAULT '500',
  PRIMARY KEY (`objectid`),
  UNIQUE KEY `objectid` (`objectid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

LOCK TABLES `Seats` WRITE;
/*!40000 ALTER TABLE `Seats` DISABLE KEYS */;

INSERT INTO `Seats` (`objectid`, `theater_id`, `play_id`, `seats`)
VALUES
	(1,1,1,487),
	(2,2,1,500),
	(3,1,2,500),
	(4,3,1,500),
	(5,1,3,300),
	(6,4,4,0),
	(8,5,0,500),
	(9,2,8,499),
	(10,4,9,500),
	(11,4,10,500),
	(12,2,11,499);

/*!40000 ALTER TABLE `Seats` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Sponsorship
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Sponsorship`;

CREATE TABLE `Sponsorship` (
  `objectid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `contact_id` bigint(20) DEFAULT NULL,
  `corporation_id` bigint(20) DEFAULT NULL,
  `donation_type` varchar(64) DEFAULT NULL,
  `amount` decimal(20,2) DEFAULT NULL,
  `description` varchar(120) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`objectid`),
  UNIQUE KEY `objectid` (`objectid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

LOCK TABLES `Sponsorship` WRITE;
/*!40000 ALTER TABLE `Sponsorship` DISABLE KEYS */;

INSERT INTO `Sponsorship` (`objectid`, `contact_id`, `corporation_id`, `donation_type`, `amount`, `description`, `created_date`, `modified_date`)
VALUES
	(1,1,1,'Cash',1000.00,'Annual','2016-05-05 20:41:01','2016-05-05 20:41:01'),
	(2,8,4,'Check',3000.50,NULL,'2016-05-05 20:42:03','2016-05-05 20:42:03'),
	(3,5,6,'Credit',20.00,NULL,'2016-05-05 20:42:49','2016-05-05 20:42:49'),
	(4,11,5,'Love',1000000.00,'This is a great project','2016-05-05 20:43:49','2016-05-05 20:43:49'),
	(5,8,4,'Check',3000.50,'','2016-05-05 21:19:40','2016-05-05 21:19:40'),
	(6,8,4,'Check',3000.50,'','2016-05-05 21:20:05','2016-05-05 21:20:05');

/*!40000 ALTER TABLE `Sponsorship` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Sponsorship_Play_Link
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Sponsorship_Play_Link`;

CREATE TABLE `Sponsorship_Play_Link` (
  `sponsorship_id` bigint(20) unsigned NOT NULL,
  `play_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`sponsorship_id`,`play_id`),
  KEY `play_id_fk3` (`play_id`),
  CONSTRAINT `play_id_fk3` FOREIGN KEY (`play_id`) REFERENCES `Play` (`objectid`) ON DELETE CASCADE,
  CONSTRAINT `sponsor_id_fk` FOREIGN KEY (`sponsorship_id`) REFERENCES `Sponsorship` (`objectid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Sponsorship_Play_Link` WRITE;
/*!40000 ALTER TABLE `Sponsorship_Play_Link` DISABLE KEYS */;

INSERT INTO `Sponsorship_Play_Link` (`sponsorship_id`, `play_id`)
VALUES
	(1,1),
	(2,1),
	(3,1),
	(3,2),
	(4,2),
	(3,4);

/*!40000 ALTER TABLE `Sponsorship_Play_Link` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Theater
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Theater`;

CREATE TABLE `Theater` (
  `objectid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`objectid`),
  UNIQUE KEY `objectid` (`objectid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

LOCK TABLES `Theater` WRITE;
/*!40000 ALTER TABLE `Theater` DISABLE KEYS */;

INSERT INTO `Theater` (`objectid`, `name`)
VALUES
	(1,'Dorothy Hess Baker'),
	(2,'Paul C. Empie'),
	(3,'McAneny'),
	(4,'Jon F Kennedy'),
	(5,'Jalal S Poo'),
	(6,'Victor P Lorax');

/*!40000 ALTER TABLE `Theater` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Works_On
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Works_On`;

CREATE TABLE `Works_On` (
  `company_member_id` bigint(20) unsigned NOT NULL,
  `play_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`company_member_id`,`play_id`),
  KEY `play_id_fk` (`play_id`),
  CONSTRAINT `company_member_id_fk` FOREIGN KEY (`company_member_id`) REFERENCES `Company_Members` (`objectid`) ON DELETE CASCADE,
  CONSTRAINT `play_id_fk` FOREIGN KEY (`play_id`) REFERENCES `Play` (`objectid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
