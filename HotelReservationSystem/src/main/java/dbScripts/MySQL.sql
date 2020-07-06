-- CREATE TABLES
-- create table room records
create table roomRecords
(
    roomID int,
    roomNumber int,
    hotelName varChar(50),
    type varchar(50),
    price FLOAT,
    datesBooked DATE,
    PRIMARY KEY(roomID)
);

-- Create table USER RECORDS
CREATE table userRecords
(
    empID int,
    password VARCHAR(25),
    validateCredentials BOOLEAN,
    message VARCHAR(255),
    PRIMARY KEY(empID)
);

-- Create Customer table
CREATE table customer
(
    customerID int,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    streetAddress VARCHAR(50),
    city VARCHAR(50),
    state VARCHAR(2),
    country VARCHAR(50),
    phone VARCHAR(50),
    email VARCHAR(50),
    PRIMARY KEY(customerID)
);

-- Create Table Rooms
CREATE table room
(
    roomID int,
    roomNumber int,
    hotelName VARCHAR(50),
    type VARCHAR(50),
    price FLOAT,
    datesBooked Date,
    isAvailable BOOLEAN,
    PRIMARY KEY(roomID)
);

-- Create table reservation record
CREATE table reservationRecords
(
    id int,
    customerID int,
    roomID int,
    checkin DATE,
    reservNumber int,
    checkOut DATE,
    preferedType int,
    price FLOAT,
    numGuests int,
    numRooms int,
    PRIMARY KEY(id),
    CONSTRAINT customerID
    FOREIGN KEY
(customerID)
    REFERENCES cmsc495.customer
(customerID),
    CONSTRAINT room
    FOREIGN KEY
(roomID)
    REFERENCES cmsc495.room
(roomID)
);


-- TESTING 

-- INSERT
-- populate rocrods
INSERT into roomRecords
    (
    roomID,
    roomNumber,
    hotelName,
    type,
    price,
    datesBooked
    )
VALUES(1, 204, 'testHotel', 'test', 24.50, '2017-06-15');

-- populate Customer
INSERT into customer
    (
    customerID,
    firstName,
    lastName,
    streetAddress,
    city,
    state,
    country,
    phone,
    email
    )
VALUES(14477895, 'leleo', 'Joins', 'street', 'city', 'MD', 'USA' , '4477558784', 'test@umuc.edu');

-- populate user records
INSERT into userRecords
    ( empID,
    password,
    validateCredentials,
    message
    )
VALUES(140111, '1455444', true, 'testing message');

-- populate reservation Records
INSERT into reservationRecords
    (
    id,
    customerID,
    checkin,
    reservNumber,
    checkOut,
    preferedType,
    numGuests,
    numRooms
    )
VALUES(001, 14477895, '2017-06-15', 478946546, '2017-06-15', 556, 4, 1);


-- SELECT 

-- TEST select 
select *
from userRecords

-- TEST select 
select *
from roomrecords

-- TEST select
SELECT *
from customer

-- TEST select
SELECT *
from reservationRecords