/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.*;

/**
 *
 * @author Emmanuel Girin created overall structure
 * @date: 6/25/2020
 * 
 * @author Jacob Valentine added swing variables to begin fleshing out
 * the GUI panels.
 * @date: 6/30/2020
 * 
 * This class  calls the main function for the program,
 * provides the GUI interface along with the input output for a hotel
 * reservation system. It also establishes a connection to a local SQLite
 * database. 
 */

/**
 * Created by Emmanuel Girin 6/25 - basic structure added methods, some data values
 * Modified by Jacob Valentine 6/20 - Added additional Swing variables, made 
 * HotelReservationProgram an extension of JFrame, and implemented an actionListenr
 */

public class HotelReservationProgram extends JFrame implements ActionListener {
    //GUI data members
    Database dbase;
    Frame frame;
    JPanel mainPanel;
    JPanel customerFormPanel;
    JPanel reservationFormPanel;
    
    private final JLabel activeEmployeeIDLabel = new JLabel("Employee ID: ");
    private final JLabel userTypeLabel = new JLabel("Current User Type: ");
    private final JLabel activeCustomerLabel = new JLabel("Current Customer: ");
    
    
    
    
    //Login Screen GUI Data members
    private final int LOGIN_X = 500; //The default width of the Login window
    private final int LOGIN_Y = 300; //The default hieght of the Login window
    private final JLabel loginScreenLabel = new JLabel("Login");
    private final JLabel employeeIDLabel = new JLabel("Employee ID: ");
    private final JTextField employeeIDField = new JTextField();
    private final JLabel passwordLabel = new JLabel("Password: ");
    private final JTextField passwordField = new JTextField();
    private final JButton loginButton = new JButton("Login");
    
    //CustomerFormPanel GUI Data members
    private final int CUSTOMER_X = 400; //The default width of the CustomerManger window
    private final int CUSTOMER_Y = 600; //The default hieght of the CustomerManager window
    private final JButton logoutCustomerMangerButton = new JButton("Logout");
    private final JLabel customerNameLabel = new JLabel("Customer Full Name: ");
    private final JTextField customerNameField = new JTextField();
    private final JLabel customerPhoneLabel = new JLabel("Customer Phone Number: ");
    private final JTextField customerPhoneField = new JTextField();
    private final JButton lookupCustomerButton = new JButton("Lookup Customer");
    private final JButton updateCustomerButton = new JButton("Update Customer Account");
    private final JButton createCustomerButton = new JButton("Create New Customer Account");  
    
    //ReservationFormPanel GUI Data Members
    private final int RESERVATION_X = 400; //The default width of the CustomerManger window
    private final int RESERVATION_Y = 600; //The default hieght of the CustomerManager window
    private final JLabel reservationIDLabel = new JLabel("Reservation ID: ");
    private final JTextField reservationIDField = new JTextField();
    private final JButton selectReservationButton = new JButton("Select Reservation");
    private final JLabel checkinDateLabel = new JLabel("Checkin Date: ");
    private final JTextField checkinDateField = new JTextField();
    
    //Non Gui Data members
    //ReservationManager reservMgr;
    //CustomerManager custMgr;
    //RoomManager roomMgr;
    //Login logon;
    int  LogonAttemps = 0;

    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //HotelReservationProgram a = new HotelReservationProgram();
        //a.connectDatabase();
    }
    
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Modified 7/6 by EG to create Database Object
     * Connects to the local SQL database on client machine
     * Catches and handles a database connection error
     */
    public void connectDatabase() {
        try {
            //establish connection to Database
            dbase = new Database();
            
        //Error needs Modification to display message to user
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HotelReservationProgram.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Display Login GUI prompting user for ID and Password
     * Validate Credentials
     */
    public void displayLogin(){
        //Initaliaze to Default
        int userID = -1;
        String password = "";
      
        //logon.validateCredentials(userID, password);
        
    }
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * @param
     * Displays GUI to choose from new Customer or Existing Customer
     * User Navigates to next Screen based on choice
     */
    void displayCustomerManager(){
        //GUI datamembers
        //JButton newCustomer, existingCustomer;
        
        //initialize CustomerManager
        //custMgr = new CustomerManager();
    }
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
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
     * Created by Emmanuel Girin 6/25 - basic structure
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
     * Created by Emmanuel Girin 6/25 - basic structure
     * Displays 2 Buttons to User
     * User chooses to select an existing reservation
     * or a create new reservation.
     */
    void displayReservationManager(){
        //GUI data
        //JButton newReservation, existingReservation;
        
        //Non-GUI data
        
        
    }
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
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
     * Created by Emmanuel Girin 6/25 - basic structure
     * Updates GUI to Display Existing Reservation Data to User
     * Provides Choice with Buttons to Modify Existing, Delete Existing, Cancel
     * calls corresponding method based on choice
     */
    void displayReservationData(){
   
         //GUI data
       
        //Non-GUI data       
    }
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * GUI is updated to capture new Reservation data
     * data is stored and used to call RoomManager search for available rooms
     * if rooms are found brings user to the finalize Reservation GUI
     */
     void displayNewReservation(){
         //GUI data
       
        //Non-GUI data       
        //Date checkIn;
        //Date checkOut;
        //int numOfGUests;
        //int roomType;
        //int numOfRoomsNeeded;
        
        
    }

    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * GUI is updated to display Reservation Data
     * User chooses to finalize the reservation or cancel through buttons
     * Methods are called based on choice
     */
    void displayFinalizeReservation(){
         //GUI data
       
        //Non-GUI data       
    }
    
    /**
     * Created by Jacob Valentine 6/30
     * Registers basic actions done to the GUI, such as clicking a button or
     * typing in information into a text field, and triggers relevant methods.
     * 
     * @param e 
     */
    public void actionPerformed(ActionEvent e){
        
    }
    
    /**
     * Created by Jacob Valentine 6/30
     * initializes all the panels used for the different management screens.
     */
    public void initializePanels(){
        //Initialize the login screen panel.
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(loginScreenLabel, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(employeeIDLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        mainPanel.add(employeeIDField, c);
        c.gridx = 0;
        c.gridy = 2;
        mainPanel.add(passwordLabel, c);
        c.gridx = 1;
        c.gridy = 2;
        mainPanel.add(passwordField, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        mainPanel.add(loginButton, c);
        employeeIDField.setEditable(true);
        passwordField.setEditable(true);
        loginButton.addActionListener(this);
        
        //Initialize the customer manager screen panel.
        customerFormPanel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        customerFormPanel.add(activeEmployeeIDLabel, c);
        c.gridx = 0;
        c.gridy = 1;
        customerFormPanel.add(userTypeLabel, c);
        c.gridx = 0;
        c.gridy = 2;
        customerFormPanel.add(logoutCustomerMangerButton, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        customerFormPanel.add(customerNameLabel, c);
        c.gridx = 2;
        c.gridy = 3;
        customerFormPanel.add(customerNameField, c);
        c.gridx = 0;
        c.gridy = 4;
        customerFormPanel.add(customerPhoneLabel, c);
        c.gridx = 1;
        c.gridy = 4;
        customerFormPanel.add(customerPhoneField, c);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 5;
        customerFormPanel.add(lookupCustomerButton, c);
        c.gridx = 0;
        c.gridy = 6;
        customerFormPanel.add(updateCustomerButton, c);
        c.gridx = 0;
        c.gridy = 7;
        customerFormPanel.add(createCustomerButton, c);
        lookupCustomerButton.addActionListener(this);
        updateCustomerButton.addActionListener(this);
        createCustomerButton.addActionListener(this);
        
        //Initialize the reservation manager screen panel
        //TODO
    }
    
    
}
