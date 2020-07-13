-- Created by EG on 7/11
DROP DATABASE IF EXISTS hotelmanager;
CREATE DATABASE IF NOT EXISTS hotelmanager;

USE hotelmanager;



-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: hotelmanager
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customerrecords`
--

DROP TABLE IF EXISTS `customerrecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerrecords` (
  `customerID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `streetAddress` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  PRIMARY KEY (`customerID`),
  UNIQUE KEY `customerID_UNIQUE` (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reservationrecords`
--

DROP TABLE IF EXISTS `reservationrecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `res_customerID` FOREIGN KEY (`customerID`) REFERENCES `customerrecords` (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roombookings`
--

DROP TABLE IF EXISTS `roombookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roombookings` (
  `bookingID` int NOT NULL AUTO_INCREMENT,
  `roomID` int DEFAULT NULL,
  `reservationID` int DEFAULT NULL,
  `checkIn` date DEFAULT NULL,
  `checkOut` date DEFAULT NULL,
  PRIMARY KEY (`bookingID`),
  UNIQUE KEY `bookingID_UNIQUE` (`bookingID`),
  KEY `reservationID` (`reservationID`),
  KEY `roomID` (`roomID`),
  CONSTRAINT `reservationID` FOREIGN KEY (`reservationID`) REFERENCES `reservationrecords` (`reservationID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `roomID` FOREIGN KEY (`roomID`) REFERENCES `roomrecords` (`roomID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roomrecords`
--

DROP TABLE IF EXISTS `roomrecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomrecords` (
  `roomID` int NOT NULL AUTO_INCREMENT,
  `roomNumber` int DEFAULT NULL,
  `hotelName` varchar(50) DEFAULT NULL,
  `roomType` varchar(50) DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`roomID`),
  UNIQUE KEY `roomID_UNIQUE` (`roomID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userrecords`
--

DROP TABLE IF EXISTS `userrecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userrecords` (
  `empID` int NOT NULL,
  `pass` varchar(25) DEFAULT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`empID`),
  UNIQUE KEY `empID_UNIQUE` (`empID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Create user hotel employee with default password set to password
CREATE USER IF NOT EXISTS 'hotelemployee'@'localhost' IDENTIFIED BY 'password';
GRANT SELECT, INSERT, DELETE ON hotelManager.* TO hotelemployee@'localhost';
FLUSH PRIVILEGES;

-- OPTIONAL Add Data for Testing
-- USER RECORDS
INSERT INTO `userRecords` VALUES 
  (140111,'1455444','John','Candy'),
  ('1','1','John','BarleyCorn');

-- Customer Records
INSERT INTO `customerRecords` VALUES 
    (1,'leleo','Joins','street','city','MD','USA','447-755-8784','test@umuc.edu','1999-01-01' ),
    (2,'Val','Junior','street','city','MD','USA','444-555-7789','test2@umuc.edu','1992-04-01'),
    (3,'Caio','Gran','street','city','MD','USA','777-888-9994','test3@umuc.edu','1955-01-05'),
    (4,'Ronald','MT','street','city','MD','USA','777-444-1112','test3@umuc.edu', '1999-02-01'),
    (5,'Indi','Ora','street','city','MD','USA','111-222-3364','test3@umuc.edu', '1999-01-02'),
    (6,'Pedro','Span','street','city','MD','USA','444-772-2123','test3@umuc.edu', '1999-01-30'),
    (7,'Marcos','BR','street','city','MD','USA','789-995-4565','test3@umuc.edu', '1985-01-01');

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


-- Dump completed on 2020-07-12 23:18:30
