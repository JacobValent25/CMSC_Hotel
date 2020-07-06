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
 * Description: Receives data from GUI interface
 *              Creates new reservations & modify existing reservations
 *              Update and Query Database Reservation Records
 */
class ReservationManager {
    //data
    Reservation currentReservation;
    Database dBase;
    
    
    //methods
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Create a new Reservation Object with data
     * Set the current Reservation to this new object
     * @param checkIn
     * @param checkOut
     * @param numGuests
     * @param roomIDs 
     */
    void createReservation(Date checkIn, Date checkOut, int numGuests, int[] roomIDs){
        
    }
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Updates the database Reservation records 
     * with the finalized reservation details
     */
    void finalizeReservation(){
        
    }
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Query database for reservation delete row if found else
     * Throw an exception if database cannot find the current reservation
     * @throws databaseLookUpException 
     */
    void deleteReservation() throws databaseLookUpException {
        
    }
    
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Look up existing reservation in database 
     * create new reservation object if found 
     * @param customerID
     * @param checkInDate 
     */
    void lookUpByCustomer(int customerID, Date checkInDate){
        
    }
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Look up existing reservation in database 
     * create new reservation object if found 
     * @param reservationNumber 
     */
    void lookUpByID (int reservationNumber) {
        
    }
    
    /**
     *Created by Emmanuel Girin 6/25
     * @return currentReservation object
     */
    public Reservation getCurrentReservation() {
        return currentReservation;
    }
    
    
    
}
