-- Created by EG on 7/11
DROP DATABASE IF EXISTS hotelmanager;
CREATE DATABASE IF NOT EXISTS hotelmanager;

USE hotelmanager;

-- Create user hotel employee with default password set to password
CREATE USER IF NOT EXISTS 'hotelemployee'@'localhost' IDENTIFIED BY 'password';
GRANT SELECT, INSERT, DELETE ON hotelManager.* TO hotelemployee@'localhost';
FLUSH PRIVILEGES;


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
  PRIMARY KEY (`customerID`),
  UNIQUE KEY `customerID_UNIQUE` (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerrecords`
--

LOCK TABLES `customerrecords` WRITE;
/*!40000 ALTER TABLE `customerrecords` DISABLE KEYS */;
INSERT INTO `customerrecords` VALUES (1,'leleo','Joins','street','city','MD','USA','4477558784','test@umuc.edu');
/*!40000 ALTER TABLE `customerrecords` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservationrecords`
--

LOCK TABLES `reservationrecords` WRITE;
/*!40000 ALTER TABLE `reservationrecords` DISABLE KEYS */;
INSERT INTO `reservationrecords` VALUES (1,1,'1, 2','2017-06-15','2017-06-17',0,4,2),(2,1,'2, 1','2020-07-30','2020-08-05',200.5,2,2);
/*!40000 ALTER TABLE `reservationrecords` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `roombookings`
--

LOCK TABLES `roombookings` WRITE;
/*!40000 ALTER TABLE `roombookings` DISABLE KEYS */;
INSERT INTO `roombookings` VALUES (1,1,1,'2017-06-15','2017-06-17'),(2,2,1,'2017-06-15','2017-06-17'),(4,2,1,'2020-07-30','2020-08-05');
/*!40000 ALTER TABLE `roombookings` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `roomrecords`
--

LOCK TABLES `roomrecords` WRITE;
/*!40000 ALTER TABLE `roomrecords` DISABLE KEYS */;
INSERT INTO `roomrecords` VALUES (1,204,'testHotel','Single',24.5),(2,205,'testHotel','Suite',74.5);
/*!40000 ALTER TABLE `roomrecords` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `userrecords`
--

LOCK TABLES `userrecords` WRITE;
/*!40000 ALTER TABLE `userrecords` DISABLE KEYS */;
INSERT INTO `userrecords` VALUES (140111,'1455444','John','Candy');
/*!40000 ALTER TABLE `userrecords` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-11 11:50:44
