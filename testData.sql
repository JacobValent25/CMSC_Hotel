
CREATE TABLE `customerRecords` (
  `customerID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `streetAddress` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`customerID`),
  UNIQUE KEY `customerID_UNIQUE` (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `reservationrecords`
--
CREATE TABLE `reservationrecords` (
  `reservationID` int NOT NULL AUTO_INCREMENT,
  `customerID` int NOT NULL,
  `roomsBookedByID` varchar(250) NOT NULL,
  `checkin` date NOT NULL,
  `checkout` date NOT NULL,
  `price` float NOT NULL,
  `numGuests` int NOT NULL,
  `numRooms` int NOT NULL,
  PRIMARY KEY (`reservationID`),
  UNIQUE KEY `reservationID_UNIQUE` (`reservationID`),
  KEY `res_customerID` (`customerID`),
  CONSTRAINT `res_customerID` FOREIGN KEY (`customerID`) REFERENCES `customerRecords` (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `roomRecords`
--
CREATE TABLE `roomRecords` (
  `roomID` int NOT NULL AUTO_INCREMENT,
  `roomNumber` int DEFAULT NULL,
  `hotelName` varchar(50) DEFAULT NULL,
  `roomType` varchar(50) DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`roomID`),
  UNIQUE KEY `roomID_UNIQUE` (`roomID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- Table structure for table `roomBookings`
--

CREATE TABLE `roomBookings` (
  `bookingID` int NOT NULL AUTO_INCREMENT,
  `roomID` int DEFAULT NULL,
  `reservationID` int DEFAULT NULL,
  `checkIn` date DEFAULT NULL,
  `checkOut` date DEFAULT NULL,
  PRIMARY KEY (`bookingID`),
  UNIQUE KEY `bookingID_UNIQUE` (`bookingID`),
  KEY `reservationID` (`reservationID`),
  KEY `roomID` (`roomID`),
  CONSTRAINT `reservationID` FOREIGN KEY (`reservationID`) REFERENCES `reservationRecords` (`reservationID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `roomID` FOREIGN KEY (`roomID`) REFERENCES `roomRecords` (`roomID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `userRecords`
--
CREATE TABLE `userRecords` (
  `empID` int NOT NULL,
  `pass` varchar(25) DEFAULT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`empID`),
  UNIQUE KEY `empID_UNIQUE` (`empID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- USER RECORDS
INSERT INTO `userRecords` VALUES (140111,'1455444','John','Candy');

-- Customer Records
INSERT INTO `customerRecords` VALUES 
    (1,'leleo','Joins','street','city','MD','USA','4477558784','test@umuc.edu'),
    (2,'Val','Junior','street','city','MD','USA','44455577789','test2@umuc.edu'),
    (3,'Caio','Gran','street','city','MD','USA','7778889994','test3@umuc.edu'),
    (4,'Ronald','MT','street','city','MD','USA','7774441112','test3@umuc.edu'),
    (5,'Indi','Ora','street','city','MD','USA','11122233364','test3@umuc.edu'),
    (6,'Pedro','Span','street','city','MD','USA','44477252123','test3@umuc.edu'),
    (7,'Marcos','BR','street','city','MD','USA','78999544565','test3@umuc.edu');

-- Room Records 
INSERT INTO `roomRecords` VALUES 
    (1,101,'CMSC HOTEL','Single',24.5),
    (2,102,'CMSC HOTEL','Single',24.5),
    (3,103,'CMSC HOTEL','Single',24.5),
    (4,104,'CMSC HOTEL','Single',24.5),
    (5,105,'CMSC HOTEL','Suite',74.5),
    (6,106,'CMSC HOTEL','Suite',74.5),
    (7,107,'CMSC HOTEL','Suite',74.5),
    (8,108,'CMSC HOTEL','Suite',74.5),
    (9,201,'CMSC HOTEL','Single',24.5),
    (10,202,'CMSC HOTEL','Single',24.5),
    (11,203,'CMSC HOTEL','Single',24.5),
    (12,204,'CMSC HOTEL','Single',24.5),
    (13,205,'CMSC HOTEL','Suite',74.5),
    (14,206,'CMSC HOTEL','Suite',74.5),
    (15,207,'CMSC HOTEL','Suite',74.5),
    (16,208,'CMSC HOTEL','Suite',74.5),
    (17,301,'CMSC HOTEL','Single',24.5),
    (18,302,'CMSC HOTEL','Single',24.5),
    (19,303,'CMSC HOTEL','Single',24.5),
    (20,304,'CMSC HOTEL','Single',24.5),
    (21,305,'CMSC HOTEL','Suite',74.5),
    (22,306,'CMSC HOTEL','Suite',74.5),
    (23,307,'CMSC HOTEL','Suite',74.5),
    (24,308,'CMSC HOTEL','Suite',74.5),
    (25,401,'CMSC HOTEL','Single',24.5),
    (26,402,'CMSC HOTEL','Single',24.5),
    (27,403,'CMSC HOTEL','Single',24.5),
    (28,404,'CMSC HOTEL','Single',24.5),
    (29,405,'CMSC HOTEL','Suite',74.5),
    (30,406,'CMSC HOTEL','Suite',74.5),
    (31,407,'CMSC HOTEL','Suite',74.5),
    (32,408,'CMSC HOTEL','Suite',74.5);


-- Reservation Records
INSERT INTO `reservationrecords` VALUES 
    (1,2,'1, 8','2019-11-15','2019-11-17',0,4,2),
    (2,4,'2, 9','2019-11-15','2019-11-17',0,4,2),
    (3,6,'3, 10','2019-11-15','2019-11-17',0,4,2),
    (4,7,'4, 11','2019-11-15','2019-11-17',0,4,2),
    (5,1,'5','2020-04-15','2020-04-17',0,4,2),
    (6,3,'6','2020-04-15','2020-04-17',0,4,2),
    (7,5,'7','2020-04-15','2020-04-17',0,4,2);

-- Room Bookings
INSERT INTO `roomBookings` VALUES 
  (1,1,1,'2019-11-15','2019-11-17'),
  (2,10,2,'2019-11-15','2019-11-17'),
  (3,7,3,'2019-11-15','2019-11-17'),
  (4,8,4,'2019-11-15','2019-11-17'),
  (5,6,5,'2020-04-15','2020-04-17'),
  (6,20,6,'2020-04-15','2020-04-17'),
  (7,3,7,'2020-04-15','2020-04-17');

-- SELECT TEST
select * from userRecords;
select * from customerRecords;
select * from roomRecords;
select * from reservationrecords;
select * from roomBookings;


