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
 * Date: 06/23/2020
 * Description: Class holds data for new or existing reservations.
 *              This data can be displayed or retrieved to be updated
 *              to a database
 * 
 */
public class Reservation {
    //Reservation data
    private int reservationID, customerID, numOfGuests;
    private double totalCost;
    private int[] roomIDs;
    private Date checkIn, checkOut;
    private final double taxRate = .10;

    
    
   
    /**
     * Returns a string of the reservation Data
     */
     @Override
    public String toString() {
        return "Reservation{" + "reservationID=" + reservationID + ", customerID=" + customerID + ", numOfGuests=" + numOfGuests + ", totalCost=" + totalCost + ", roomIDs=" + roomIDs + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", taxRate=" + taxRate + '}';
    }
    
    //get Methods for reservation Data
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

    public int[] getRoomIDs() {
        return roomIDs;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }
    
    
}
