/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem;

/**
 *
 * @author Emmanuel Girin
 * Project: Hotel Reservation System
 * Date: 06/23/2020
 * Description: Class holds data for new or existing reservations.
 *              This data can be displayed or retrieved to be 
 *              updated to a database
 * 
 */

/**
 * Modified by Emmanuel 6/27 to include constructor and data values
 * 
 */
public class Room {
    private String hotelName;
    private int room_Number, roomID; //RoomID is the database unique key
    private int roomType; //0, 1, 2, 3, 4
    private double nightlyPrice;
    
    /**
     * Created By Emmanuel Girin 6/27
     * Constructor for a Room
     * @param id
     * @param hotel
     * @param roomNum
     * @param type
     * @param price 
     */
    public Room(int id, String hotel, int roomNum, int type, double price){
        roomID = id;
        hotelName = hotel;
        roomType = type;
        room_Number = roomNum;
        nightlyPrice = price;
    }

    
    /**
     * Created by Emmanuel Girin 6/27
     * Needs to be modified for GUI Display purposes
     * @return String value of a Room 
     */
    @Override
    public String toString() {
        return "Room{" + "hotelName=" + hotelName + ", room_Number=" + room_Number + ", roomID=" + roomID + ", roomType=" + roomType + ", nightlyPrice=" + nightlyPrice + '}';
    }
    
    
}
