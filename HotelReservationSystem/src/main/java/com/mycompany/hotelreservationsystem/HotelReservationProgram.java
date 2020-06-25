/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem;

import java.awt.Frame;
import java.sql.Date;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Group 1 Members: Emmanuel Girin, Jacob Valentine
 * @date: 6/25/2020
 * 
 * This class  calls the main function for the program,
 * provides the GUI interface along with the input output for a hotel
 * reservation system. It also establishes a connection to a local SQLite
 * database. 
 */
public class HotelReservationProgram {
    //GUI data members
    Frame frame;
    JPanel mainPanel;
    JPanel customerFormPanel;
    JPanel reservationFormPanel;
    
    //Non Gui Data members
    ReservationManager reservMgr;
    CustomerManager custMgr;
    RoomManager roomMgr;
    Login logon;
    int  LogonAttemps = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HotelReservationProgram a = new HotelReservationProgram();
        a.connectDatabase();
    }
    
    
    /**
     * Connects to the local SQL database on client machine
     * Catches and handles a database connection error
     */
    public void connectDatabase() {
        //establish connection to Database
    }
    
   
    /**
     * Display Login GUI prompting user for ID and Password
     * Validate Credentials
     */
    public void displayLogin(){
        //Initaliaze to Default
        int userID = -1;
        String password = "";
      
        logon.validateCredentials(userID, password);
        
    }
    
    /**
     * @param
     * Displays GUI to choose from new Customer or Existing Customer
     * User Navigates to next Screen based on choice
     */
    void displayCustomerManager(){
        //GUI datamembers
        JButton newCustomer, existingCustomer;
        
        //initialize CustomerManager
        custMgr = new CustomerManager();
    }
    
    /**
     * Display new customer form to get customer info
     * Stores value into a string array
     * calls method contained in CustomerManager class
     */
    void displayNewCustomerForm(){
        //GUI datamembers
        
        //NonGUI datamembers
        String[] customerData;
        
    }
    
    /**
     * Asks User for data to lookup a Customer
     * User chooses button to perform look up by phone, name, email
     * Look up is performed to retrieve Customer Data
     * Calls methods contained in CustomerManager() class
     */
    void displayExistingCustomerForm(){
        //GUI data
       
        //Non-GUI data
    }
    
    /**
     * Displays 2 Buttons to User
     * User chooses to select an existing reservation
     * or a create new reservation.
     */
    void displayReservationManager(){
        //GUI data
        JButton newReservation, existingReservation;
        
        //Non-GUI data
        
        
    }
    
    /**
     * GUI is updated to display choice to User
     * User Selects buttons to LookUpReservation by name or by Number
     * calls corresponding ReservationManager lookup method
     * if the reservation is found calls displayReservationData()
     */
    void displayExistingReservation(){
        //GUI data
       
        //Non-GUI data
    }
    
    /**
     * Updates GUI to Display Existing Reservation Data to User
     * Provides Choice with Buttons to Modify Existing, Delete Existing, Cancel
     * calls corresponding method based on choice
     */
    void displayReservationData(){
   
         //GUI data
       
        //Non-GUI data       
    }
    
    /**
     * GUI is updated to capture new Reservation data
     * data is stored and used to call RoomManager search for available rooms
     * if rooms are found brings user to the finalize Reservation GUI
     */
     void displayNewReservation(){
         //GUI data
       
        //Non-GUI data       
        Date checkIn;
        Date checkOut;
        int numOfGUests;
        int roomType;
        int numOfRoomsNeeded;
        
        
    }

    /**
     * GUI is updated to display Reservation Data
     * User chooses to finalize the reservation or cancel through buttons
     * Methods are called based on choice
     */
    void displayFinalizeReservation(){
         //GUI data
       
        //Non-GUI data       
    }
    
    
    
}
