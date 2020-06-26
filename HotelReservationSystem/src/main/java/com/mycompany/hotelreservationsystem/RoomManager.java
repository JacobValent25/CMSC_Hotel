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
 * Description: Class performs available Room Look Ups for hotel reservations
 *              query and update database of Room Records
 */
class RoomManager {
    //data
    private Room[] availableRooms;
    Date checkIn, checkOut;
    int PreferredType = 0; //defaults to 0, which is any type of room 
    int numRoomsNeeded = 1; //defaults to 1
    
    
    /**
     * Created By Emmanuel Girin 6/25
     * Query Database Room Records to see if number and type of rooms are available
     * during the date range.
     * @throws ExceedsCapacityException
     * @throws unavailableReservationsException 
     */
    void searchAvailableRooms() throws ExceedsCapacityException, unavailableReservationsException {
        
    }
    
    /**
     * Created by Emmanuel Girin 6/25
     * Looks up room in database room Records by unique key room ID
     * @param roomID
     * @throws databaseLookUpException 
     */
    void lookUpRoom(int roomID) throws databaseLookUpException {
        
    }
    
    /**
     * Created by Emmanuel Girin 6/25
     * @return array of rooms available rooms
     */
    Room[] getAvailableRooms(){
        return availableRooms;
    }
    
    /**
     * Created by Emmanuel Girin 6/25
     * Takes the first available rooms based on # needed retrieves roomID
     * adds the room IDs in an array and returns that array
     * @return int array of room IDs
     */
    int[] getSelectedRoomIDs(){
        int[] selectedRoomIDs = new int[numRoomsNeeded];
        return selectedRoomIDs;
    }
    
    
}
