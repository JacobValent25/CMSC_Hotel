/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Emmanuel Girin
 * Project: Hotel Reservation System
 * Date: 06/25/2020
 * Description: Class performs available Room Look Ups for hotel reservations
 *              query and update database of Room Records
 * Modified: 7/7 filled body of all methods
 */
class RoomManager {
    //data
    private ArrayList<Room> availableRooms = new ArrayList<>();
    private Date checkIn = Date.valueOf("0001-1-1");
    private Date checkOut = Date.valueOf("0001-1-1");
    private int preferredType = 0; //defaults to 0, which is any type of room 
    private int numRoomsNeeded = 1; //defaults to 1
    private final Database dBase;
    
    
    /**
     * Created: 7/7 by EG
     * Constructor that takes parameters for data members
     * @param dateIn
     * @param dateOut
     * @param prefType
     * @param numRooms 
     */
    public RoomManager(Database dbs, String dateIn, String dateOut, int prefType, int numRooms){
        dBase = dbs;
        checkIn = Date.valueOf(dateIn);
        checkOut = Date.valueOf(dateOut);
        preferredType = prefType;
        numRoomsNeeded = numRooms;
        availableRooms = new ArrayList<>();
    }
    
    public RoomManager(Database dbs){
        dBase = dbs;
    }
    
    /**
     * Created By Emmanuel Girin 6/25
     * Query Database Room Records to see if number and type of rooms are available
     * during the date range.
     * Modified: 7/7 added body, still need the SQL query, changed to boolean
     * @throws ExceedsCapacityException
     * @throws unavailableReservationsException 
     */
    boolean searchAvailableRooms() throws SQLException, Exception {
        boolean dataFound = false;
        //open connection to database
        dBase.connectDatabase();
        
        String roomType;
        switch (preferredType){
            case 1: 
                roomType = "Single"; 
                break;
            case 2:
                roomType = "Double";
                break;
            case 3:
                roomType = "King";
                break;
            default:
                roomType = "ALL";   
        }
        
        String sql;
        
        //create Query String from parameters if no room type selected
        if (roomType.matches("ALL")){
            sql = "SELECT * " +
            " FROM roomRecords AS r" +
            " WHERE r.roomID NOT IN(" +
            "SELECT b.roomID " +
            " FROM roomBookings AS b" +
            " WHERE b.checkIn <= CAST('" + checkIn.toString() + 
            " ' AS DateTime)" +
            " AND b.checkOut >=  CAST('" + checkOut.toString() + 
            "' AS DateTime))"
            ;
        }
        else //search for specific type of room
        {
            sql = "SELECT * " +
            " FROM roomRecords AS r" +
            " WHERE r.RoomType = '" + roomType + "' AND" +
            " r.roomID NOT IN(" +
            " SELECT b.roomID " +
            " FROM roomBookings AS b" +
            " WHERE b.checkIn <= CAST('" + checkIn.toString() + 
            " ' AS DateTime)" +
            " AND b.checkOut >=  CAST('" + checkOut.toString() + 
            "' AS DateTime))"
            ;
        }
        
            
        System.out.println(sql);
        
        try{
            //Query database, which returns a result set
            ResultSet rs = dBase.queryDatabase(sql);
            //if rows are returned plug data into an array and create customer object
            if(rs.isBeforeFirst()){
                int numRowsReturned = 0;
                
                Room r;
                
                String[] roomData = new String[5];
               
                //create array and count of all the rooms available during date range
                while(rs.next())
                {
                    //count rooms returned
                    numRowsReturned++;
                    roomData[0] = rs.getString("roomID");
                    roomData[2] = rs.getString("roomNumber");
                    roomData[1] = rs.getString("hotelName");
                    roomData[3] = rs.getString("roomType");
                    roomData[4] = rs.getString("price");
                    
                    //create room
                    r = new Room(roomData);
                    
                    //add it to list
                    availableRooms.add(r);
                    System.out.println("Added Room to Available ROoms");
                }
                
                
                //Only return True if there are enough rooms
                if(numRowsReturned >= numRoomsNeeded){
                    //set Flag to True
                    dataFound = true;
                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("# of Rooms Added: " + availableRooms.size());
        
        dBase.closeConnection();
        
        return dataFound;
    }
    
    /**
     * Created by Emmanuel Girin 6/25
     * Looks up room in database room Records by unique key room ID
     * Modified 7/7 to return a room if found or null if not,
     * added queries and body of method
     * @param roomID
     * @throws databaseLookUpException 
     */
    public Room lookUpRoom(int roomID) throws SQLException, DatabaseException {
        //open connection to database
        dBase.connectDatabase();
        
        //Initialize to null
        Room r = null;
        
        //create Query String from parameters
        String sql = "SElECT * " +
                      "From roomrecords " +
                      "WHERE roomID = '" + roomID + 
                      "'";    
        
        System.out.println(sql);
        
        //Query database, which returns a result set
        ResultSet rs = dBase.queryDatabase(sql);
        
        //if rows are returned plug data into an array and create customer object
        if(rs.isBeforeFirst()){
                        
            
            String[] roomData = new String[5];
            
            //move cursor to the row returned
            rs.next();
                
            //build data array
            roomData[0] = rs.getString("roomID");
            roomData[1] = rs.getString("hotelName");
            roomData[2] = rs.getString("roomNumber");
            roomData[3] = rs.getString("roomType");
            roomData[4] = rs.getString("price");
            
            //create room Object
            r = new Room(roomData);  
            
            System.out.println(r.toString());
            
        }else {
            throw new DatabaseException("Lookup by Room ID Failed");
        }
        
        
        dBase.closeConnection();
        
        return r;
    }
    
    /**
     * Created by Emmanuel Girin 6/25
     * @return array of rooms available rooms
     */
    ArrayList<Room> getAvailableRooms(){
        return availableRooms;
    }
    
    /**
     * Created by Emmanuel Girin 6/25
     * Modified: 7/7 by EG completed body of method
     * Takes the first available rooms based on # needed retrieves roomID
     * adds the room IDs in an array and returns that array
     * @return int array of room IDs
     */
    int[] getSelectedRoomIDs() throws DatabaseException{
        Room r;
        System.out.println("Number of Rooms needed: " + numRoomsNeeded);
        int[] selectedRoomIDs = new int[numRoomsNeeded];
        
        if(numRoomsNeeded <= availableRooms.size()){
            //Return an Array of size rooms Needed of the first available rooms
            for(int i = 0; i < numRoomsNeeded ; i++){
                r = availableRooms.get(i);
                System.out.println("Room: " + r.toString());
                System.out.println(i);
                selectedRoomIDs[i] = r.getRoomID();
            }
            return selectedRoomIDs;
        }
        else
            throw new DatabaseException("Not Enough Rooms");
    }
    
    
}
