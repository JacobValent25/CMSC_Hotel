/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
 * @author Jacob Valentine further flesh out the gui panels. Divided the Customer
 * and Reservation Manager screens into seperate Panels dedicated to specific functions
 * for better input/output clarity.
 * @date: 7/07/2020
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
 * 
 * Modified by Jacob Valentine 7/07 - Added additional Swing variables, divided 
 * the customer Manager panel into seperate panels for select, lookup, and make options.
 *
 * Modified by Emmanuel Girin 7/10 - Starting Filling out methods and tweaking GUI to build program, added method diplayMessagerToUser(str)
 * 
 * Modified by Jacob Valentine 7/10 - Filled in Variables for the Resevation option menu's Option, Select, and lookup panel.
 */

public class HotelReservationProgram extends JFrame implements ActionListener {
    //Non GUI Data members
    private Login login;
    private ReservationManager resMGR;
    private CustomerManager custMGR;
    private RoomManager roomMGR;
    private Database dbase;
    private int  logonAttempts = 0;
    
    //GUI data members
    JPanel loginPanel;
    JPanel customerFormPanel;
    JPanel reservationFormPanel;
    
    private final JLabel activeEmployeeIDLabel = new JLabel("Employee ID: ");
    private final JLabel userTypeLabel = new JLabel("Current User Type: ");
    private final JLabel activeCustomerLabel = new JLabel("Current Customer: ");
    //private Customer activeCustomer;
    
    
    
    
    
    //Login Screen GUI Data members
    private final int LOGIN_X = 250; //The default width of the Login window
    private final int LOGIN_Y = 200; //The default hieght of the Login window
    private final JLabel loginScreenLabel = new JLabel("Login");
    private final JLabel employeeIDLabel = new JLabel("Employee ID: ");
    private final JTextField employeeIDField = new JTextField();
    private final JLabel passwordLabel = new JLabel("Password: ");
    private final JPasswordField passwordField = new JPasswordField(); //EG 7/10 changed to passwordField
    private final JButton loginButton = new JButton("Login");
    
    //CustomerFormPanel GUI Data members
    private final int CUSTOMER_X = 400; //The default width of the CustomerManger window
    private final int CUSTOMER_Y = 600; //The default hieght of the CustomerManager window
    private final JButton logoutCustomerMangerButton = new JButton("Logout");
    
    
    //CustomerOptionPanel GUI Data members
    private JPanel customerOptionPanel = new JPanel();
    private final Box customerOptionBox = Box.createVerticalBox();
    private final ButtonGroup customerOptionGroup = new ButtonGroup();
    private final JRadioButton customerSelectRadioButton = new JRadioButton("Select Customer");
    private final JRadioButton customerLookupRadioButton = new JRadioButton("Lookup Customer");
    private final JRadioButton customerMakeRadioButton = new JRadioButton("Make New Customer Account");
    
    //SelectCustomerPanel Data members
    private JPanel selectCustomerPanel;
    private final JLabel selectHeaderLabel = new JLabel("Select Customer");
    private final JLabel selectCustomerIDLabel = new JLabel("Customer ID: ");
    private final JTextField selectCustomerIDField = new JTextField();
    private final JButton selectLookupCustomerButton = new JButton("Lookup");
    private final Box customerConfirmationBox = Box.createVerticalBox();
    private final JTextField customerConfirmationTextField = new JTextField();
    private final JButton customerConfirmationButton = new JButton("Select");
    
    //LookupCustomerPanel Data members
    private JPanel lookupCustomerPanel = new JPanel();
    private final JLabel lookupCustomerIDLabel = new JLabel("CustomerID: ");
    private final JTextField lookupCustomerIDField = new JTextField();
    private final JLabel lookupCustomerFirstNameLabel = new JLabel("First Name: ");
    private final JTextField lookupCustomerFirstNameField = new JTextField();
    private final JLabel lookupCustomerLastNameLabel = new JLabel("Last Name: ");
    private final JTextField lookupCustomerLastNameField = new JTextField();
    private final JLabel lookupCustomerStreetLabel = new JLabel("Street Address: ");
    private final JTextField lookupCustomerStreetField = new JTextField();
    private final JLabel lookupCustomerCityLabel = new JLabel("City: ");
    private final JTextField lookupCustomerCityField = new JTextField();
    private final JLabel lookupCustomerStateLabel = new JLabel("State: ");
    private final JTextField lookupCustomerStateField = new JTextField();
    private final JLabel lookupCustomerCountryLabel = new JLabel("Country: ");
    private final JTextField lookupCustomerCountryField = new JTextField();
    private final JLabel lookupCustomerPhoneLabel = new JLabel("Phone Number: ");
    private final JTextField lookupCustomerPhoneField = new JTextField();
    private final JLabel lookupCustomerEmailLabel = new JLabel("Email: ");
    private final JTextField lookupCustomerEmailField = new JTextField();
    private final JButton lookupCustomerButton = new JButton("Lookup Customer");
    
    
    //LookupList Data Members
    private JFrame lookupListFrame;
    private JScrollPane lookupListScrollPane = new JScrollPane();
    private JPanel lookupListScrollPanel = new JPanel();
    private final JTextField lookupListField = new JTextField();
    
    
    //MakeCustomerPanel Data members
    private JPanel makeCustomerPanel = new JPanel();
    private final JLabel makeCustomerFistNameLabel = new JLabel("First Name: ");
    private final JTextField makeCustomerFirstNameField = new JTextField();
    private final JLabel makeCustomerLastNameLabel = new JLabel("Last Name: ");
    private final JTextField makeCustomerLastNameField = new JTextField();
    private final JLabel makeCustomerStreetLabel = new JLabel("Street Address: ");
    private final JTextField makeCustomerStreetField = new JTextField();
    private final JLabel makeCustomerCityLabel = new JLabel("City: ");
    private final JTextField makeCustomerCityField = new JTextField();
    private final JLabel makeCustomerStateLabel = new JLabel("State: ");
    private final JTextField makeCustomerStateField = new JTextField();
    private final JLabel makeCustomerCountryLabel = new JLabel("Country: ");
    private final JTextField makeCustomerCountryField = new JTextField();
    private final JLabel makeCustomerPhoneLabel = new JLabel("Phone Number: ");
    private final JTextField makeCustomerPhoneField = new JTextField();
    private final JLabel makeCustomerEmailLabel = new JLabel("Email: ");
    private final JTextField makeCustomerEmailField = new JTextField();
    private final JButton makeCustomerButton = new JButton("Make Customer"); 
    
    //ReservationFormPanel GUI Data Members
    private final int RESERVATION_X = 400; //The default width of the ReservationManager window
    private final int RESERVATION_Y = 600; //The default hieght of the ReservationManager window
    
    //ReservationOptionPanel GUI Data Members
    private JPanel reservationOptionPanel = new JPanel();
    private final Box reservationOptionBox = Box.createVerticalBox();
    private final ButtonGroup reservationOptionGroup = new ButtonGroup();
    private final JRadioButton reservationSelectRadioButton = new JRadioButton("Select/Edit/Delete Reservation");
    private final JRadioButton reservationLookupRadioButton = new JRadioButton("Lookup Reservation");
    private final JRadioButton reservationMakeRadioButton = new JRadioButton("Make New Reservation");
    private final JButton reservationSeeReservedButton = new JButton("See Active Reservation for this Customer");
    
    //ReservationSelectionPanel GUI Data Members
    private JPanel reservationSelectionPanel = new JPanel();
    private JScrollPane reservationSelectionScrollPane = new JScrollPane();
    private JPanel reservationSelectionSubPanel = new JPanel();
    private final JLabel selectReservationIDLabel = new JLabel("Reservation ID: ");
    private final JTextField selectReservationIDField = new JTextField("");
    
    //LookupReservationPanel GUI Data Members
    //private JPanel lookupReservationPanel = new JPanel();
    //private JScrollPane lookupReservationScrollPane = new JScrollPane();
    //private JPanel lookupReservationSubPanel = new JPanel();
    //private final JLabel lookupReservationIDLabel = new JLabel("Reservation ID: ");
    //private final JTextField lookupReservationIDField = new JTextField();
    
    //MakeReservationPanel GUI Data Members
    
    
    //ReservationActiveListPanel GUI Data Members
    private JFrame reservationActiveListPanel = new JFrame();
    private final JScrollPane reservationActiveListPane = new JScrollPane();
    private final JTextField reservationActiveListField = new JTextField();
    
    //private final JLabel reservationIDLabel = new JLabel("Reservation ID: ");
    //private final JTextField reservationIDField = new JTextField();
    //private final JButton selectReservationButton = new JButton("Select Reservation");
    //private final JLabel checkinDateLabel = new JLabel("Checkin Date: ");
    //private final JTextField checkinDateField = new JTextField();
    
    
    

    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HotelReservationProgram a = new HotelReservationProgram();
        a.initializePanels();
        a.connectDatabase();
        a.displayLogin();
        //a.displayCustomerManager();
    }
    
    
    public HotelReservationProgram() {
        //create JFrame
        super("Hotel Reservation System");
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
            displayMessageToUser("Database Connection Error: ");
            Logger.getLogger(HotelReservationProgram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//end method
    
    /**
     * Created: 7/9 EG
     * Displays Messages to users, can be used for errors or general messaging
     * @param str 
     */
    void displayMessageToUser(String str){
        JOptionPane.showMessageDialog(null, str);
    }
   
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Modified: 7/10 by EG to include body, logic, and GUI elements
     * Display Login GUI prompting user for ID and Password
     * Validate Credentials
     */
    public void displayLogin() {
         
        //anonymous loginButton moved to here, so it could be implemented
        loginButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            //As long 
            if(logonAttempts <= 4){
                //if the fields have data in them check them out, else display msg
                if(!employeeIDField.getText().equals("") & !passwordField.getText().equals("")){
                    try {
                        //create a new login object and pass it the dbase
                        login = new Login(dbase);
                        
                        
                        //set proceed = to whether or not the credentials are valid
                        boolean proceed = login.validateCredentials(employeeIDField.getText(), passwordField.getText());
                        
                        //increment counter of login attempts
                        logonAttempts++;
                        
                        //if valid proceed to next screen
                        if(proceed){
                            //reset counter
                            logonAttempts = 0;
                            displayCustomerManager();
                        }
                        else {
                            //display error message with count of login attempts
                            displayMessageToUser(login.getMessage() + "/n Attempt #:  " 
                                                + logonAttempts + "of 5");
                        }
                    }   
                    catch (SQLException ex) {
                        Logger.getLogger(HotelReservationProgram.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
                else displayMessageToUser("Error: One of the Fields is Blank");
                }
            else
                displayMessageToUser("Error: User exceeded Logon Attempts");
         };
        });
        
        initializePanels();
        setSize(LOGIN_X, LOGIN_Y);
        add(loginPanel);
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * @param
     * Displays GUI to choose from new Customer or Existing Customer
     * User Navigates to next Screen based on choice
     */
    void displayCustomerManager(){
        //initialize CustomerManager
        custMGR = new CustomerManager(dbase);
        remove(this.getContentPane());
        setContentPane(customerFormPanel);
        setMinimumSize(new Dimension(CUSTOMER_X, CUSTOMER_Y));
        pack();
        setResizable(true);
        setVisible(true);
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
    public void actionPerformed(ActionEvent e)
    {

    }
    
    /**
     * Created by Jacob Valentine 6/30
     * initializes all the panels used for the different management screens.
     * Modified by Jacob Valentine 7/07
     * further added more panels to intialize: Customer Manager form panel and Customer Select Panels
     * Modified by Jacob Valentine 7/10
     * Addded further panel initialization for Customer Select and Lookup
     */
    private void initializePanels(){
        
        //Initialize the login panel.
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        loginPanel.add(loginScreenLabel, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        loginPanel.add(employeeIDLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        loginPanel.add(employeeIDField, c);
        c.gridx = 0;
        c.gridy = 2;
        loginPanel.add(passwordLabel, c);
        c.gridx = 1;
        c.gridy = 2;
        loginPanel.add(passwordField, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        loginPanel.add(loginButton, c);
        employeeIDField.setEditable(true);
        passwordField.setEditable(true);
        loginButton.addActionListener(this);
        
        //Initialize the Customer Option Panel
        customerOptionPanel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        customerOptionPanel.add(activeEmployeeIDLabel, c);
        c.gridx = 0;
        c.gridy = 1;
        customerOptionPanel.add(userTypeLabel, c);
        c.gridx = 0;
        c.gridy = 2;
        customerOptionPanel.add(logoutCustomerMangerButton, c);
        customerOptionGroup.add(customerSelectRadioButton);
        customerOptionGroup.add(customerLookupRadioButton);
        customerOptionGroup.add(customerMakeRadioButton);
        customerSelectRadioButton.setSelected(true);
        customerOptionBox.add(customerSelectRadioButton);
        customerOptionBox.add(customerLookupRadioButton);
        customerOptionBox.add(customerMakeRadioButton);
        customerOptionBox.setBorder(BorderFactory.createTitledBorder("Options"));
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        customerOptionPanel.add(customerOptionBox, c);        
        
        //Initialize the SelectCustomerPanel
        selectCustomerPanel = new JPanel();
        selectCustomerPanel.setLayout(new BoxLayout(selectCustomerPanel, BoxLayout.LINE_AXIS));
        selectCustomerPanel.add(selectHeaderLabel);
        selectCustomerPanel.add(selectCustomerIDLabel);
        selectCustomerPanel.add(selectCustomerIDField);
        selectCustomerPanel.add(selectLookupCustomerButton);
        customerConfirmationBox.add(customerConfirmationTextField);
        customerConfirmationBox.setBorder(BorderFactory.createTitledBorder("Confirmation"));
        selectCustomerPanel.add(customerConfirmationBox);
        selectCustomerPanel.add(customerConfirmationButton);
        selectCustomerIDField.addActionListener(this);
        selectCustomerIDField.setEditable(true);
        customerConfirmationTextField.setEditable(false);
        customerConfirmationButton.addActionListener(this);
        
        //Initialize the customer manager screen panel
        customerFormPanel = new JPanel(new GridLayout(1,2));
        customerFormPanel.add(customerOptionPanel);
        customerFormPanel.add(selectCustomerPanel);
        
        
        //Initialize the LookupCustomerPanel
        lookupCustomerPanel = new JPanel();

        lookupListScrollPanel = new JPanel();
        lookupListScrollPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        lookupListScrollPanel.add(lookupCustomerIDLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        lookupListScrollPanel.add(lookupCustomerIDField, c);
        c.gridx = 0;
        c.gridy = 1;
        lookupListScrollPanel.add(lookupCustomerFirstNameLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        lookupListScrollPanel.add(lookupCustomerFirstNameField, c);
        c.gridx = 0;
        c.gridy = 2;
        lookupListScrollPanel.add(lookupCustomerLastNameLabel, c);
        c.gridx = 1;
        c.gridy = 2;
        lookupListScrollPanel.add(lookupCustomerLastNameField, c);
        c.gridx = 0;
        c.gridy = 3;
        lookupListScrollPanel.add(lookupCustomerStreetLabel, c);
        c.gridx = 1;
        c.gridy = 3;
        lookupListScrollPanel.add(lookupCustomerStreetField, c);
        c.gridx = 0;
        c.gridy = 4;
        lookupListScrollPanel.add(lookupCustomerCityLabel, c);
        c.gridx = 1;
        c.gridy = 4;
        lookupListScrollPanel.add(lookupCustomerCityField, c);
        c.gridx = 0;
        c.gridy = 5;
        lookupListScrollPanel.add(lookupCustomerStateLabel, c);
        c.gridx = 1;
        c.gridy = 5;
        lookupListScrollPanel.add(lookupCustomerStateField, c);
        c.gridx = 0;
        c.gridy = 6;
        lookupListScrollPanel.add(lookupCustomerCountryLabel, c);
        c.gridx = 1;
        c.gridy = 6;
        lookupListScrollPanel.add(lookupCustomerCountryField, c);
        c.gridx = 0;
        c.gridy = 7;
        lookupListScrollPanel.add(lookupCustomerPhoneLabel, c);
        c.gridx = 1;
        c.gridy = 7;
        lookupListScrollPanel.add(lookupCustomerPhoneField, c);
        c.gridx = 0;
        c.gridy = 8;
        lookupListScrollPanel.add(lookupCustomerEmailLabel, c);
        c.gridx = 1;
        c.gridy = 8;
        lookupListScrollPanel.add(lookupCustomerEmailField, c);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 9;
        lookupListScrollPanel.add(lookupCustomerButton, c);
        lookupListScrollPane = new JScrollPane(lookupListScrollPanel);
        lookupCustomerPanel.add(lookupListScrollPane);
        lookupCustomerIDField.setEditable(true);
        lookupCustomerFirstNameField.setEditable(true);
        lookupCustomerLastNameField.setEditable(true);
        lookupCustomerStreetField.setEditable(true);
        lookupCustomerCityField.setEditable(true);
        lookupCustomerStateField.setEditable(true);
        lookupCustomerCountryField.setEditable(true);
        lookupCustomerPhoneField.setEditable(true);
        lookupCustomerEmailField.setEditable(true);
        lookupCustomerButton.addActionListener(this);
        
        //Initialize the reservation manager screen panel
        //TODO
        
        

    }
    
    public static void changeScreen(int currentScreenID, int targetScreenID)
    {
    
    }
    
    
}
