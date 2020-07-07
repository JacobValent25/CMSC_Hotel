-- CREATE TABLES
-- Removed roomRecords EG 7/6 redundant table

-- Create table USER RECORDS
-- Edited 7/6 by EG to remove message, boolean replace with firstname lastname
CREATE table userRecords
(
    empID int,
    password VARCHAR(25),
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    PRIMARY KEY(empID)
);

-- Create Customer table
-- Edited 7/6 by EG auto incrementing primary key
CREATE table customerRecords
(
    customerID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    streetAddress VARCHAR(50),
    city VARCHAR(50),
    state VARCHAR(2),
    country VARCHAR(50),
    phone VARCHAR(50),
    email VARCHAR(50)
);

-- Create Table Rooms
-- Edited 7/6 by EG auto incrementing primary key
CREATE table roomRecords
(
    roomID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    roomNumber int,
    hotelName VARCHAR(50),
    type VARCHAR(50),
    price FLOAT
);

  

-- Create table reservation record
-- Edited by EG 7/6 changed roomIDs to be a String of varchar(250). Room IDS will be listed and seperated by , to support multiple rooms
-- Removed reservation number, because it will be auto generated as the reservationID
CREATE table reservationRecords
(
    reservationID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    roomID int,
    customerID int,
    roomsBookedByID VARCHAR(250),
    checkin DATE,
    checkOut DATE,
    preferedType int,
    price FLOAT,
    numGuests int,
    numRooms int,
    CONSTRAINT customerID
    FOREIGN KEY
(customerID)
    REFERENCES cmsc495.customerRecords
(customerID)
);

-- Create Table RoomBookings
-- Created by EG 7/7 for keeping track of available rooms
CREATE table roomBookings
(
    BookingID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    roomID int,
    reservationID int,
	CONSTRAINT roomID
    FOREIGN KEY (roomID) REFERENCES cmsc495.roomRecords(roomID),
    CONSTRAINT reservationID
    FOREIGN KEY (reservationID) REFERENCES cmsc495.reservationRecords(reservationID),
    checkIn Date,
    checkOut Date
);

-- TESTING 

-- INSERT
-- populate records
-- Edited 7/6 by EG auto incrementing primary key, removed Dates booked
INSERT into roomRecords
    (
    roomNumber,
    hotelName,
    type,
    price
    )
VALUES(204, 'testHotel', 'Single', 24.50),
      (205, 'testHotel', 'Suite', 74.50);

-- populate Customer
-- Edited 7/6 by EG auto incrementing primary key
INSERT into customerRecords
    (
    firstName,
    lastName,
    streetAddress,
    city,
    state,
    country,
    phone,
    email
    )
VALUES('leleo', 'Joins', 'street', 'city', 'MD', 'USA' , '4477558784', 'test@umuc.edu');

-- populate user records
-- Edited 7/6 by EG to reflect changes in table
INSERT into userRecords
    ( empID,
    password,
    )
VALUES(140111, '1455444', 'John', 'Candy');

-- populate reservation Records
-- Edited 7/6 by EG to reflect changes in table
INSERT into reservationRecords
    (
    customerID,
    checkin,
    roomsBookedByID
    checkOut,
    preferedType,
    numGuests,
    numRooms
    )
-- Changed to reflect 2 rooms and roomIDs of 1, 2
VALUES(1, '2017-06-15', '1, 2', '2017-06-17', 0, 4, 2);

-- Created a Test 
INSERT into roomBookings
    (
    roomID,
    reservationID
    checkIn
    checkOut
    )
VALUES(1, 1, '2017-6-15', '2017-6-17'),
      (2, 1, '2017-6-15', '2017-6-17');

-- SELECT 
-- EG modified tests to reflect renamed tables and additional tables - 6/7
-- TEST select 
select *
from userRecords

-- TEST select 
select *
from roomRecords

-- TEST select
SELECT *
from customerRecords

-- TEST select
SELECT *
from reservationRecords

-- Test select
SELECT *
from roomBookings
