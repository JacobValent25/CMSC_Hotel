/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/**
 *
 * @author Emmanuel Girin
 * Project: Hotel Reservation System
 * Date: 06/23/2020
 * Description: Receives data from GUI interface
 *              Creates new reservations & modify existing reservations
 *              Update and Query Database Reservation Records
 * Modified: 7/7 by EG create constructor, fill in body of methods
 */
class ReservationManager {
    //data
    private Reservation currentReservation;
    private final Database dBase;
    private final double taxRate = .10;
    
    
    /**
     * Created: 7/6 by EG
     * Public Constructor, with database object passed 
     * initialization of data
     * @param dbs 
     */
    public ReservationManager(Database dbs){
        currentReservation = null;
        dBase = dbs;
    }
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Create a new Reservation Object with data
     * Set the current Reservation to this new object
     * @param checkIn
     * @param checkOut
     * @param numGuests
     * @param roomIDs 
     */
    void createReservation(int custID, Date checkIn, Date checkOut, int numGuests, int[] roomIDs) throws SQLException, DatabaseException{
        
        System.out.println(Arrays.toString(roomIDs));
        System.out.println("ID LENGTH in createReservation: " +  roomIDs.length);
        //find out total cost of rooms
        double totalPrice = 0;
        long totalNights = ChronoUnit.DAYS.between(checkIn.toLocalDate(), checkOut.toLocalDate());
        System.out.println("" + totalNights);
        if(totalNights <= 0){
            throw new SQLException("Check Out Date must be greater than Check in Date");
        }
        String roomIDstr = "";
        RoomManager rmgr = new RoomManager(dBase);
        //get cost of each room
        for(int i = 0; i< roomIDs.length; i++){
            //add cost of each room
            
            totalPrice += rmgr.lookUpRoom(roomIDs[i]).getNightlyPrice();
            
            //build str of room IDs separated by , to pass to reservation
            if(i == (roomIDs.length -1)) {
                roomIDstr += roomIDs[i];
                //for last element only add the element
            } else {
                roomIDstr += roomIDs[i] + ", ";
            }
        }
        
        //Final Cost
        totalPrice = totalNights * totalPrice * (1 + taxRate);
        
        String[] resData = new String[8];
        //put default -1 for ReservationID until it is created in Database
        resData[0] = "-1";
        resData[1] = Integer.toString(custID);
        resData[2] = Integer.toString(numGuests);
        resData[3] = Integer.toString(roomIDs.length);
        resData[4] = Double.toString(totalPrice);
        resData[5] = roomIDstr;
        resData[6] = checkIn.toString();
        resData[7] = checkOut.toString();
        
        //create new Reservation
        currentReservation = new Reservation(resData);
        
    }
    
    
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Modified: 7/7 by EG added body of method to insert into database
     * Updates the database Reservation records 
     * with the finalized reservation details
     * 
     */
    void finalizeReservation(int[] roomIDs) throws SQLException, DatabaseException{
        
        //Attempt to open connection to database
        dBase.connectDatabase();
        
        //create Query String from reservation Object
        String sql = "INSERT INTO reservationrecords"+ 
                    " (customerID, roomsBookedByID, checkin, checkout, price, numGuests, numRooms)" +
                     " VALUES ('" + currentReservation.getCustomerID() +
                        "', '" + currentReservation.getRoomIDs() + "', '" +
                        currentReservation.getCheckIn() + "', '" +
                        currentReservation.getCheckOut() + "', " +
                        currentReservation.getTotalCost() + ", " +
                        currentReservation.getNumOfGuests() + ", " +
                        currentReservation.getNumOfRooms() + ") ;";

       try{
            //Attempt to Insert Data into Table
            System.out.println("SQL for Reservation Insert: " + sql);
            dBase.insertData(sql);
        }
        catch(SQLException ex){
            System.out.println("Inside try catch for create reservation");
            throw new SQLException(ex.getMessage());
        }
        
        //Attempt to close Connection
        dBase.closeConnection();
        
        //retrieve Customer
        try{
            lookUpByCustomer(currentReservation.getCustomerID(), currentReservation.getCheckIn());
        }
        catch (SQLException e){
            System.out.println("Error Looking up New Reservation");
            System.out.print(e.getMessage());
        }
        try{
            insertRoomBookings(roomIDs);
        }
        catch (SQLException e){
            System.out.println("Error inserting room bookings");
            System.out.print(e.getMessage());
        }
        
   
    }
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Query database for reservation delete row if found else
     * Modified: 7/7 by EG added body, and changed to boolean
     * @return true if delete completes else reservation cannot be found 
     */
    boolean deleteReservation() throws SQLException {
        boolean dataFound = false;
        
        //only proceed if a currentReservation has been found
        if(currentReservation == null)
            return dataFound;
        
        //open connection to database
        dBase.connectDatabase();
        
        //create Query String from parameters
        String sql = "DELETE " +
                      "From reservationrecords " +
                      "WHERE reservationID = '" + currentReservation.getReservationID() + "'";
        
        //insert Query into database software
        dBase.insertData(sql);
        
        //Succesfully Deleted
        dataFound = true;
     
                
        dBase.closeConnection();
        
        return dataFound;    
    }
    
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Look up existing reservation in database 
     * create new reservation object if found 
     * Modified: 7/7 by EG include body of method and changed to boolean
     * @param customerID
     * @param checkInDate 
     * @return true if reservation is found
     */
    boolean lookUpByCustomer(int customerID, Date checkInDate) throws SQLException{
        boolean dataFound = false;
        
        //open connection to database
        dBase.connectDatabase();
        
        //create Query String from parameters
        String sql = "SElECT * " +
                      "From reservationrecords " +
                      "WHERE customerID = '" + customerID + 
                      "' AND checkin = CAST('" + checkInDate.toString() 
                      + "' AS DateTime)";
        
        System.out.println(sql);
        
        //Query database, which returns a result set
        ResultSet rs = dBase.queryDatabase(sql);
        
        //if rows are returned plug data into an array and create customer object
        if(rs.isBeforeFirst()){
                //move cursor to the row returned
                rs.next();
                
                //build data array
                String[] resData = new String[9];
                resData[0] = rs.getString("reservationID");
                resData[1] = rs.getString("customerID");
                resData[2] = rs.getString("numGuests");
                resData[3] = rs.getString("numRooms");
                resData[4] = rs.getString("price");
                resData[5] = rs.getString("roomsBookedByID");
                resData[6] = rs.getString("checkin");
                resData[7] = rs.getString("checkout");

                //create new customer
                currentReservation = new Reservation(resData);
                
                //set Flag to True
                dataFound = true;
        }
        
        dBase.closeConnection();
        
        return dataFound;
    }
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Modified: 7/7 include body of method, changed to boolean
     * Look up existing reservation in database 
     * create new reservation object if found 
     * @param reservationID 
     * @return true if reservation is found
     */
    boolean lookUpByID (int reservationID) throws SQLException {
        boolean dataFound = false;
        
        //open connection to database
        dBase.connectDatabase();
        
        //create Query String from parameters
        String sql = "SElECT * " +
                      "From reservationrecords " +
                      "WHERE reservationID = '" + reservationID + "'";
        
        System.out.println(sql);
        
        //Query database, which returns a result set
        ResultSet rs = dBase.queryDatabase(sql);
        
        //if rows are returned plug data into an array and create customer object
        if(rs.isBeforeFirst()){
                //move cursor to the row returned
                rs.next();
                
                //build data array
                String[] resData = new String[9];
                resData[0] = rs.getString("reservationID");
                resData[1] = rs.getString("customerID");
                resData[2] = rs.getString("numGuests");
                resData[3] = rs.getString("numRooms");
                resData[4] = rs.getString("price");
                resData[5] = rs.getString("roomsBookedByID");
                resData[6] = rs.getString("checkin");
                resData[7] = rs.getString("checkout");

                //create new customer
                currentReservation = new Reservation(resData);
                
                //set Flag to True
                dataFound = true;
        }
        
        dBase.closeConnection();
        
        return dataFound;

    }
    
    /**
     *Created by Emmanuel Girin 6/25
     * @return currentReservation object
     */
    public Reservation getCurrentReservation() {
        return currentReservation;
    }

    private void insertRoomBookings(int [] roomIDs) throws SQLException {
                
        dBase.connectDatabase();
        
        String sql = "";
        //insert RoomBookings
        for (int i = 0; i < roomIDs.length; i ++){
            sql = "INSERT INTO roombookings (roomID, reservationID, checkIn, checkOut)" 
                    + " VALUES('" + roomIDs[i] +
                    "', '" + currentReservation.getReservationID() +
                    "', CAST('" + currentReservation.getCheckIn().toString() 
                            + "' AS DateTime)" +
                    ", CAST('" + currentReservation.getCheckOut().toString() 
                            + "' AS DateTime)";

            System.out.println();
              dBase.insertData(sql);
        }


        
        //Attempt to close Connection
        dBase.closeConnection();   
    }
    
    
    
}
