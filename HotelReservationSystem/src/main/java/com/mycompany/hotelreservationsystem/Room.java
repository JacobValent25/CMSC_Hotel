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
    private int roomType; // 1 = Single, 2 = Double Beds, 3 = King , 4 = Suite
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
    public Room(String[] roomData) throws NumberFormatException, DatabaseException{
        roomID = Integer.parseInt(roomData[0]);
        hotelName = roomData[1];
        room_Number = Integer.parseInt(roomData[2]);
        switch (roomData[3]) {
            case "Single":
                roomType = 1;
                break;
            case "Double Beds":
                roomType = 2;
                break;
            case "King":
                roomType = 3;
                break;
            case "Suite":
                roomType = 4;
                break;
            default :
                throw new DatabaseException("Error: Invalid Data in Database");
        } 
        
        nightlyPrice = Double.parseDouble(roomData[4]);
    }

    public Room() {
           roomID = -1;
           hotelName = "default";
           room_Number = -1;
           roomType = -1;
           nightlyPrice = 0;
           
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
    
    //getters created 7/7 by EG

    public String getHotelName() {
        return hotelName;
    }

    public int getRoom_Number() {
        return room_Number;
    }

    public int getRoomID() {
        return roomID;
    }

    public int getRoomType() {
        return roomType;
    }
    
    public String getTypeAsString() {
        String str;
        switch (roomType) {
                case 1:
                    str = "Single";
                    break;
                case 2:
                    str = "Double";
                    break;
                case 3:
                    str = "King";
                    break;
                default:
                    str = "Suite";     
        }
        return str;
    }

    public double getNightlyPrice() {
        return nightlyPrice;
    } 
}
