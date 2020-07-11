/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem;

import java.sql.Date;

/**
 *
 * @author Emmanuel Girin
 * Project: Hotel Reservation System
 * Date: 06/25/2020
 * Description: Class holds data for new or existing reservations.
 *              This data can be displayed or retrieved to be updated
 *              to a database
 * 
 */

//Created by Emmanuel Girin: 6/25
public class Reservation {
    //Reservation data
    //default values set
    private int reservationID = -1;
    private int customerID = -1; 
    private int numOfGuests = 1;
    private int numOfRoomsNeeded = 1;
    private float totalCost = 0;
    private final String roomIDs;
    private Date checkIn = Date.valueOf("0001-1-1");
    private Date checkOut = Date.valueOf("0001-1-1");
    
    
    /**
     * Created 7/7 by EG
     * Takes a String[] and assigns values to object data 
     * @param data 
     */
    public Reservation(String[] data) throws NumberFormatException{
        reservationID = Integer.parseInt(data[0]);
        customerID = Integer.parseInt(data[1]);
        numOfGuests = Integer.parseInt(data[2]);
        numOfRoomsNeeded = Integer.parseInt(data[3]);
        totalCost = Float.parseFloat(data[4]);
        roomIDs = data[5];
        checkIn = Date.valueOf(data[6]);
        checkOut = Date.valueOf(data[7]);
    }
    
   
    /**
     * Created: Emmanuel Girin 6/25
     * Returns a string of the reservation Data
     */
     @Override
    public String toString() {
        return "Reservation{" + "reservationID=" + reservationID + ", customerID=" + customerID + ", numOfGuests=" + numOfGuests + ", totalCost=" + totalCost + ", roomIDs=" + roomIDs + ", checkIn=" + checkIn + ", checkOut=" + checkOut + '}';
    }
    
    /**
     * Created all get Methods: Emmanuel Girin 6/25
     * get Methods for reservation Data + 1 set method to get reservationID
     * 
     */
    
    public void setReservationID(int rID){
        reservationID = rID;
    }
    
    public int getReservationID() {
        return reservationID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getNumOfGuests() {
        return numOfGuests;
    }

    public double getTotalCost() {
        return totalCost;
    }
    
    public int getNumOfRooms() {
        return numOfRoomsNeeded;
    }

    public String getRoomIDs() {
        return roomIDs;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }
}
