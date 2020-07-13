/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private static final long serialVersionUID = 1L;

    
    //Non GUI Data members
    private Login login;
    private ReservationManager resMGR;
    private CustomerManager custMGR;
    private RoomManager roomMGR;
    private Database dbase;
    private int  logonAttempts = 0;
    private boolean cameFromNewCustomer = true;

    
    //GUI variables
    private final int MAIN_X = 676;
    private final int MAIN_Y = 614;
    
    private CardLayout cl;
                  
    private javax.swing.JPanel NewCustomerPanel;
    private javax.swing.JFormattedTextField cdBirthField;
    private javax.swing.JTextField cdCityField;
    private javax.swing.JButton cdClearBTN;
    private javax.swing.JTextField cdCountryField;
    private javax.swing.JTextField cdEmailField;
    private javax.swing.JTextField cdFirstNameField;
    private javax.swing.JTextField cdLastNameField;
    private javax.swing.JFormattedTextField cdPhoneField;
    private javax.swing.JTextField cdStateField;
    private javax.swing.JTextField cdStreetField;
    private javax.swing.JButton cdSubmitBTN;
    private javax.swing.JLabel cdTitleLabel;
    private javax.swing.JPanel customerDisplayPanel;
    private javax.swing.JPanel erSearchByNamePanel;
    private javax.swing.JPanel erSearchByNumberPanel;
    private javax.swing.JButton erSearchNameBTN;
    private javax.swing.JButton erSearchNumberBTN;
    private javax.swing.JPanel emailPanel;
    private javax.swing.JTextField employeeIDField;
    private javax.swing.JFormattedTextField erCheckInField;
    private javax.swing.JTextField erFirstNameField;
    private javax.swing.JTextField erLastNameField;
    private javax.swing.JTextField erNumberField;
    private javax.swing.JLabel erTitleLabel;
    private javax.swing.JButton existingCustomerBTN;
    private javax.swing.JPanel existingReservationPanel;
    private javax.swing.JPanel finalizeReservationPanel;
    private javax.swing.JButton frCancelBTN;
    private javax.swing.JTextField frCheckInField;
    private javax.swing.JTextField frCheckOutField;
    private javax.swing.JTextField frCostField;
    private javax.swing.JButton frFinalizeBTN;
    private javax.swing.JTextField frFirstNameField;
    private javax.swing.JTextField frGuestsField;
    private javax.swing.JTextField frLastNameField;
    private javax.swing.JTextField frPhoneField;
    private javax.swing.JTextField frResNumField;
    private javax.swing.JTextField frRoomNumbersField;
    private javax.swing.JTextField frRoomTypeField;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainCustomerPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel mainReservationPanel;
    private javax.swing.JPanel modifyReservationPanel;
    private javax.swing.JTextField mrCheckInField;
    private javax.swing.JTextField mrCheckOutField;
    private javax.swing.JTextField mrCostField;
    private javax.swing.JButton mrDeleteBTN;
    private javax.swing.JButton mainExistingResBTN;
    private javax.swing.JTextField mrFNameField;
    private javax.swing.JTextField mrGuestsField;
    private javax.swing.JTextField mrLNameField;
    private javax.swing.JButton mrModifyBTN;
    private javax.swing.JButton mainNewResBTN;
    private javax.swing.JTextField mrPhoneField;
    private javax.swing.JTextField mrReservationNumberField;
    private javax.swing.JTextField mrRoomNumbersField;
    private javax.swing.JTextField mrRoomTypeField;
    private javax.swing.JPanel namePanel;
    private javax.swing.JFormattedTextField ncBirthField;
    private javax.swing.JTextField ncCityField;
    private javax.swing.JButton ncClearButton;
    private javax.swing.JButton nrClearButton;
    private javax.swing.JTextField ncCountryField;
    private javax.swing.JTextField ncEmailField;
    private javax.swing.JTextField ncFirstNameField;
    private javax.swing.JTextField ncLastNameField;
    private javax.swing.JFormattedTextField ncPhoneField;
    private javax.swing.JTextField ncStateField;
    private javax.swing.JTextField ncStreetField;
    private javax.swing.JButton ncSubmitBTN;
    private javax.swing.JButton nrSubmitBTN;
    private javax.swing.JLabel ncTitleLabel;
    private javax.swing.JButton newCustomerBTN;
    private javax.swing.JPanel newReservationPanel;
    private javax.swing.JFormattedTextField nrCheckInField;
    private javax.swing.JFormattedTextField nrCheckOutField;
    private javax.swing.JTextField nrGuestField;
    private javax.swing.JTextField nrRoomsField;
    private javax.swing.JLabel nrTitleLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPanel phonePanel;
    private javax.swing.JButton returnCustomerBTN;
    private javax.swing.JComboBox<String> roomTypeComboBox;
    private javax.swing.JTextField searchCustomerEmailField;
    private javax.swing.JTextField searchCustomerFirstNameField;
    private javax.swing.JTextField searchCustomerLastNameField;
    private javax.swing.JPanel searchCustomerPanel;
    private javax.swing.JFormattedTextField searchCustomerPhoneField;
    private javax.swing.JButton searchEmailBTN;
    private javax.swing.JButton searchNameBTN;
    private javax.swing.JButton searchPhoneBTN;
    private javax.swing.JButton validateCredentialsBTN;
   
    public HotelReservationProgram(){
        super("Hotel Reservation System");
        initializeMainDisplay();
        connectDatabase();
        
    }
      
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
          java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HotelReservationProgram().setVisible(true);
            }
        });
    }

    
     /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Modified 7/6 by EG to create Database Object
     * Connects to the local SQL database on client machine
     * Catches and handles a database connection error
     */
    private void connectDatabase() {
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
     * Created 7/9 EG
     * Displays Login Screen Resets Fields to ""
     */
    private void displayLogin() {
            employeeIDField.setText("");
            passwordField.setText("");
            
            //show Login Screen
            cl.show(mainPanel, "card11");
             
            logoutButton.setEnabled(false);
            returnCustomerBTN.setEnabled(false);
         
    }
    
    /**
     * Created 7/9 EG
     * Validates Credentials from Login Screen
     */
    private void validateCredentials() {
            
            if(logonAttempts < 4){
                //if the fields have data in them check them out, else display msg
                if(!employeeIDField.getText().equals("") & passwordField.getText() != null){
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
                            
                            //Clear Text Fields
                            employeeIDField.setText("");
                            passwordField.setText("");
                            
                            //Enable logout button
                            logoutButton.setEnabled(true);
                            
                            //create new Customer Manager
                            custMGR = new CustomerManager(dbase);
                            
                            //show main Customer Panel
                            cl.show(mainPanel, "card2");
                        }
                        else {
                            //display error message with count of login attempts
                            displayMessageToUser(login.getMessage() + "\n Attempt #:  " 
                                                + logonAttempts + " of 5");
                            //reset to blank
                            employeeIDField.setText("");
                            passwordField.setText("");
                        }
                    }   
                    catch (SQLException ex) {
                        //error handling display SQL Error Message to User
                        if(ex.getMessage() != null) {
                            displayMessageToUser("SQL Error: " + ex.getLocalizedMessage());
                        }
                    } 
                }
                else {
                    displayMessageToUser("Error: One of the Fields is Blank");
                }
                }
            else {
                //Message displayed to USER
                displayMessageToUser("Error: User exceeded Logon Attempts. LOCKED OUT");
                
                //Disable fields and button
                employeeIDField.setText("");
                passwordField.setText("");
                employeeIDField.setEnabled(false);
                passwordField.setEnabled(false);
                validateCredentialsBTN.setEnabled(false);
            }                                       
    }
    
    
    /**
     * Created: EG 7/12
     * Displays Main Customer Screen
     * @param evt 
     */
    private void displayMainCustomer() {
        //disable button
        returnCustomerBTN.setEnabled(false);

        //show customer select screen
        cl.show(mainPanel, "card2");
    }
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Modified: 7/10 by EG to include body, logic, and GUI elements
     * Display Login GUI prompting user for ID and Password
     * Validate Credentials
     */
   
    /**
     * Created 7/12 by EG
     * Displays new Customer creation Screen
     * @param evt 
     */
    private void displayNewCustomer() {                                               
        //show create new customer screen
        cl.show(mainPanel, "card4");
        
        ncFirstNameField.setText("");
        ncLastNameField.setText("");
        ncBirthField.setText("0001-01-01");
        ncPhoneField.setText("000-000-0000");
        ncStreetField.setText("");
        ncCityField.setText("");
        ncStateField.setText("");
        ncCountryField.setText("");
        ncEmailField.setText("");
        
        //let program know that we are creating an new customer
        cameFromNewCustomer = true;
        
        //reenable return to customer select button
        returnCustomerBTN.setEnabled(true);
    }
    
    
    private void displayCustomerDetails() {
        //display Screen
        cl.show(mainPanel, "card10");
        
        if(cameFromNewCustomer){
            //create Customer Data into String Array
            String[] custData = new String[10];
            //set ID to Default
            custData[0] = "-1";
            custData[1] = ncFirstNameField.getText();
            custData[2] = ncLastNameField.getText();
            custData[3] = ncStreetField.getText();
            custData[4] = ncCityField.getText();
            custData[5] = ncStateField.getText();
            custData[6] = ncCountryField.getText();
            custData[7] = ncPhoneField.getText();
            custData[8] = ncEmailField.getText();
            custData[9] = ncBirthField.getText();
            
            try {
                //create Customer in SQL
                custMGR.createCustomer(custData);
            } catch (SQLException ex) {
                displayMessageToUser("ERROR: Customer could not be Created");
                System.out.println(ex.getMessage());
            }
        }
        
        Customer c = custMGR.getCustomer();
        
        //Display Data
        cdFirstNameField.setText(c.getFirstName());
        cdLastNameField.setText(c.getLastName());
        cdBirthField.setText(c.getDateOfBirth().toString());
        cdPhoneField.setText(c.getPhone());
        cdStreetField.setText(c.getStreet());
        cdCityField.setText(c.getCity());
        cdStateField.setText(c.getState());
        cdCountryField.setText(c.getCountry());
        cdEmailField.setText(c.getEmail());
    }

    
    /**
     * Created: 7/12 by EG
     * Displays Existing Customer Look up Screen
     * @param evt 
     */
    private void displayExistingCustomer() {
        //set all textfields to blank if they have data in them
        searchCustomerEmailField.setText("");
        searchCustomerFirstNameField.setText("");
        searchCustomerLastNameField.setText("");
        searchCustomerPhoneField.setText("");
        
        //show existing customer screen
        cl.show(mainPanel, "card3");
        
        //reenable return to customer select button
        returnCustomerBTN.setEnabled(true);
    }
    
    private void displayMainReservation(){
        frFinalizeBTN.setEnabled(true);
        frCancelBTN.setEnabled(true);
        
        
        //create new reservationMGR
        resMGR = new ReservationManager(dbase);
        
        //navigate to MainReservation
        cl.show(mainPanel, "card5");
    }
    
    private void displayNewReservation(){
        //scrub data if there is any
        nrCheckInField.setText("0001-01-01");
        nrCheckOutField.setText("0001-01-01");
        nrRoomsField.setText("1");
        nrGuestField.setText("1");
        
        //show the panel
        cl.show(mainPanel, "card6");
    }
    
    private void displayExistingReservation(){
        //clear data
        erCheckInField.setText("0001-01-01");
        erFirstNameField.setText("");
        erLastNameField.setText("");
        erNumberField.setText("000-000-0000");
        
        //display Panel
        cl.show(mainPanel, "card7");
    }
    
    private void searchForAvailableRooms(){
        roomMGR = new RoomManager(dbase);
    }
    
    /**
     * Created: 7/12 by EG
     * Looks up Customer by Name
     * If found sends user to display customer info screen
     * @param evt 
     */
    private void searchNameBTNActionPerformed() {
        String fName = searchCustomerFirstNameField.getText();
        String lName = searchCustomerLastNameField.getText();
        if(!"".equals(fName) && !"".equals(lName)){
            boolean isFound = false;
            try {
                isFound = custMGR.lookUpCustomerByName(fName, lName);
            } catch (SQLException ex) {
                displayMessageToUser("ERROR: Database Error Try Again!");
                System.out.println(ex.getMessage());
            }
            if(isFound){
                //set boolean to false
                cameFromNewCustomer = false;

                //display Reservation Manager
                displayCustomerDetails();
            }
            else {
                //try again message
                displayMessageToUser("Look up Failed. Try Again!");
            }
        }
        else {
            displayMessageToUser("Fill out BOTH Name Fields!");
        }
    }
    
    /**
     * Created: 7/12 by EG
     * Looks up Customer by Email
     * If found sends user to display customer info screen
     * @param evt 
     */
    private void searchEmailBTNActionPerformed(ActionEvent evt){
        String str = searchCustomerEmailField.getText();
        if(!"".equals(str)){
            boolean isFound = false;
            try {
                isFound = custMGR.lookUpCustomerByEmail(str);
            } catch (SQLException ex) {
                displayMessageToUser("ERROR: Database Error Try Again!");
                System.out.println(ex.getMessage());
            }
            if(isFound){
                //set boolean to false
                cameFromNewCustomer = false;

                //display Reservation Manager
                displayCustomerDetails();
            }
            else {
                //try again message
                displayMessageToUser("Look up Failed. Try Again!");
            }
        }
        else
            //Email is not filled
            displayMessageToUser("Fill out Email Field");
    }
    
    /**
     * Created: 7/12 by EG
     * Looks up Customer by Phone Number
     * If found sends user to display customer info screen
     * @param evt 
     */
    private void searchPhoneBTNActionPerformed(ActionEvent evt) {
        String str = searchCustomerPhoneField.getText();
        if(!"".equals(str)){
            boolean isFound = false;
            try {
                isFound = custMGR.lookUpCustomerByPhone(str);
            } catch (SQLException ex) {
                displayMessageToUser("ERROR: Database Error Try Again!");
                System.out.println(ex.getMessage());
            }
            if(isFound){
                //set boolean to false
                cameFromNewCustomer = false;
                
                
                //display Reservation Manager
                displayCustomerDetails();
            }
            else {
                //try again message
                displayMessageToUser("Look up Failed. Try Again!");
            }
        }
        else {
            //Email is not filled
            displayMessageToUser("Fill out Phone Field");
        }  
    }
    
    private void deleteReservation(){
        //Delete Reservation
        try {
            resMGR.deleteReservation();
        } catch (SQLException ex) {
            displayMessageToUser("ERROR: Database Error Try Again!");
            System.out.println(ex.getMessage());
        }
    }
    
    
    private void displayReservationDetails() {
        //navigate to finalize screen
        
        Reservation r = resMGR.getCurrentReservation();
        Customer c = custMGR.getCustomer();
        
        System.out.println(r.toString());
        System.out.println(c.toString());
       
        try {
            int[] roomIDs = roomMGR.getSelectedRoomIDs();
            int j = roomIDs.length;
            System.out.println(Arrays.toString(roomIDs));
            System.out.println(roomIDs.length);
 
            String roomNumbers = "";
            String roomTypes = "";

            for (int i = 0; i < j; i++){
                try {
                    System.out.println(roomIDs.length);
                    Room room = roomMGR.lookUpRoom(roomIDs[i]);
                    System.out.println(room.toString());
               
                if(i == (j-1)){
                    roomNumbers += room.getRoomID();
                    roomTypes += room.getTypeAsString();
                }
                else{
                    roomNumbers += room.getRoomID() + ", ";
                    roomTypes += room.getTypeAsString() + ", ";
                    
                }
                } catch (SQLException | DatabaseException ex) {
                   Logger.getLogger(HotelReservationProgram.class.getName()).log(Level.SEVERE, null, ex);
                   displayMessageToUser(ex.getMessage());
                   System.out.println(ex.getMessage());
                   
                }
            }

            //display data to panel
            frCheckInField.setText(r.getCheckIn().toString());
            frCheckOutField.setText(r.getCheckOut().toString());
            frCostField.setText("$" + String.format("%.2f", r.getTotalCost()));
            frResNumField.setText("To be Determined");
            frRoomNumbersField.setText(roomNumbers);
            frRoomTypeField.setText(roomTypes);
            frPhoneField.setText(c.getPhone());
            frFirstNameField.setText(c.getFirstName());
            frLastNameField.setText(c.getLastName());
            frGuestsField.setText("" + r.getNumOfGuests());
            
            //display Finalize Reservation
            cl.show(mainPanel, "card9");
            
        
        } catch (DatabaseException ex) {
            Logger.getLogger(HotelReservationProgram.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void finalizeReservation(){
        try {
            resMGR.finalizeReservation(roomMGR.getSelectedRoomIDs());
            
            //Set Reservation ID
            frResNumField.setText("" + resMGR.getCurrentReservation().getReservationID());
            
                    
            frFinalizeBTN.setEnabled(false);
            frCancelBTN.setEnabled(false);
            
            displayMessageToUser("Reservation Succesfully Created");
        } catch (DatabaseException | SQLException ex) {
            frFinalizeBTN.setEnabled(false);
            frCancelBTN.setEnabled(true);
            displayMessageToUser("Error: Finalizing Reservation");
            System.out.println(ex.getMessage());
        }
            
    }
    
    private void displayModifyReservation() {
        Reservation r = resMGR.getCurrentReservation();
        Customer c = custMGR.getCustomer();
        r.getRoomIDs();
        
        
        mrCheckInField.setText(r.getCheckIn().toString());
        mrCheckOutField.setText(r.getCheckOut().toString());
        mrCostField.setText("$" + String.format("%.2f", r.getTotalCost()));
        mrReservationNumberField.setText("" + r.getReservationID());
        mrRoomNumbersField.setText(r.getRoomIDs());
        mrRoomTypeField.setText("ANY");
        mrPhoneField.setText(c.getPhone());
        mrFNameField.setText(c.getFirstName());
        mrLNameField.setText(c.getLastName());
        mrGuestsField.setText("" + r.getNumOfGuests());
        
        cl.show(mainPanel, "card8");
    
    }


 
  
    private void initializeMainDisplay(){
        
        mainPanel = new javax.swing.JPanel();
        mainCustomerPanel = new javax.swing.JPanel();
        loginPanel = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        employeeIDField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        validateCredentialsBTN = new javax.swing.JButton();
        newCustomerBTN = new javax.swing.JButton();
        existingCustomerBTN = new javax.swing.JButton();
        searchCustomerPanel = new javax.swing.JPanel();
        searchNameBTN = new javax.swing.JButton();
        searchPhoneBTN = new javax.swing.JButton();
        searchEmailBTN = new javax.swing.JButton();
        namePanel = new javax.swing.JPanel();
        searchCustomerLastNameField = new javax.swing.JTextField();
        searchCustomerFirstNameField = new javax.swing.JTextField();
        phonePanel = new javax.swing.JPanel();
        searchCustomerPhoneField = new javax.swing.JFormattedTextField();
        emailPanel = new javax.swing.JPanel();
        searchCustomerEmailField = new javax.swing.JTextField();
        NewCustomerPanel = new javax.swing.JPanel();
        ncPhoneField = new javax.swing.JFormattedTextField();
        ncFirstNameField = new javax.swing.JTextField();
        ncLastNameField = new javax.swing.JTextField();
        ncCityField = new javax.swing.JTextField();
        ncBirthField = new javax.swing.JFormattedTextField();
        ncStreetField = new javax.swing.JTextField();
        ncEmailField = new javax.swing.JTextField();
        ncCountryField = new javax.swing.JTextField();
        ncStateField = new javax.swing.JTextField();
        ncTitleLabel = new javax.swing.JLabel();
        ncClearButton = new javax.swing.JButton();
        ncSubmitBTN = new javax.swing.JButton();
        mainReservationPanel = new javax.swing.JPanel();
        mainNewResBTN = new javax.swing.JButton();
        mainExistingResBTN = new javax.swing.JButton();
        newReservationPanel = new javax.swing.JPanel();
        nrCheckOutField = new javax.swing.JFormattedTextField();
        nrTitleLabel = new javax.swing.JLabel();
        nrClearButton = new javax.swing.JButton();
        nrSubmitBTN = new javax.swing.JButton();
        nrRoomsField = new javax.swing.JTextField();
        nrCheckInField = new javax.swing.JFormattedTextField();
        roomTypeComboBox = new javax.swing.JComboBox<>();
        nrGuestField = new javax.swing.JTextField();
        existingReservationPanel = new javax.swing.JPanel();
        erSearchByNumberPanel = new javax.swing.JPanel();
        erNumberField = new javax.swing.JTextField();
        erTitleLabel = new javax.swing.JLabel();
        erSearchByNamePanel = new javax.swing.JPanel();
        erFirstNameField = new javax.swing.JTextField();
        erLastNameField = new javax.swing.JTextField();
        erCheckInField = new javax.swing.JFormattedTextField();
        erSearchNameBTN = new javax.swing.JButton();
        erSearchNumberBTN = new javax.swing.JButton();
        customerDisplayPanel = new javax.swing.JPanel();
        cdPhoneField = new javax.swing.JFormattedTextField();
        cdFirstNameField = new javax.swing.JTextField();
        cdLastNameField = new javax.swing.JTextField();
        cdCityField = new javax.swing.JTextField();
        cdBirthField = new javax.swing.JFormattedTextField();
        cdStreetField = new javax.swing.JTextField();
        cdEmailField = new javax.swing.JTextField();
        cdCountryField = new javax.swing.JTextField();
        cdStateField = new javax.swing.JTextField();
        cdTitleLabel = new javax.swing.JLabel();
        cdClearBTN = new javax.swing.JButton();
        cdSubmitBTN = new javax.swing.JButton();
        modifyReservationPanel = new javax.swing.JPanel();
        mrCheckOutField = new javax.swing.JTextField();
        mrReservationNumberField = new javax.swing.JTextField();
        mrLNameField = new javax.swing.JTextField();
        mrFNameField = new javax.swing.JTextField();
        mrCheckInField = new javax.swing.JTextField();
        mrPhoneField = new javax.swing.JTextField();
        mrRoomTypeField = new javax.swing.JTextField();
        mrRoomNumbersField = new javax.swing.JTextField();
        mrGuestsField = new javax.swing.JTextField();
        mrCostField = new javax.swing.JTextField();
        mrDeleteBTN = new javax.swing.JButton();
        mrModifyBTN = new javax.swing.JButton();
        finalizeReservationPanel = new javax.swing.JPanel();
        frCheckOutField = new javax.swing.JTextField();
        frResNumField = new javax.swing.JTextField();
        frLastNameField = new javax.swing.JTextField();
        frFirstNameField = new javax.swing.JTextField();
        frCheckInField = new javax.swing.JTextField();
        frPhoneField = new javax.swing.JTextField();
        frRoomTypeField = new javax.swing.JTextField();
        frRoomNumbersField = new javax.swing.JTextField();
        frGuestsField = new javax.swing.JTextField();
        frCostField = new javax.swing.JTextField();
        frCancelBTN = new javax.swing.JButton();
        frFinalizeBTN = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        returnCustomerBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hotel Manager Program");
        
        cl = new java.awt.CardLayout();
        
        mainPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mainPanel.setLayout(cl);
        
        loginLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        loginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginLabel.setText("Login");
        loginLabel.setToolTipText("");

        employeeIDField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employee ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        passwordField.setText("jPasswordField1");
        passwordField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        validateCredentialsBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        validateCredentialsBTN.setText("Validate Credentials");
        validateCredentialsBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateCredentials();
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap(171, Short.MAX_VALUE)
                .addComponent(loginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160))
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(validateCredentialsBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(employeeIDField, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                            .addComponent(passwordField))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(loginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(employeeIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(validateCredentialsBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );

        mainPanel.add(loginPanel, "card11");

        existingCustomerBTN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        existingCustomerBTN.setText("Existing Customer");
        existingCustomerBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayExistingCustomer();
            }
        });

        newCustomerBTN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        newCustomerBTN.setText("New Customer");
        newCustomerBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayNewCustomer();
            }
        });

        javax.swing.GroupLayout mainCustomerPanelLayout = new javax.swing.GroupLayout(mainCustomerPanel);
        mainCustomerPanel.setLayout(mainCustomerPanelLayout);
        mainCustomerPanelLayout.setHorizontalGroup(
            mainCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainCustomerPanelLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(newCustomerBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(existingCustomerBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        mainCustomerPanelLayout.setVerticalGroup(
            mainCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainCustomerPanelLayout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addGroup(mainCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(existingCustomerBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newCustomerBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(319, Short.MAX_VALUE))
        );

        mainPanel.add(mainCustomerPanel, "card2");

        searchNameBTN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchNameBTN.setText("Search Name");
        searchNameBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchNameBTNActionPerformed();
            }
        });

        searchEmailBTN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchEmailBTN.setText("Search Email");
        searchEmailBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchEmailBTNActionPerformed(evt);
            }
        });

        searchPhoneBTN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchPhoneBTN.setText("Search Phone");
        searchPhoneBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPhoneBTNActionPerformed(evt);
            }
        });

        namePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search by Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        namePanel.setPreferredSize(new java.awt.Dimension(175, 25));

        searchCustomerLastNameField.setBorder(javax.swing.BorderFactory.createTitledBorder("Last Name"));

        searchCustomerFirstNameField.setBorder(javax.swing.BorderFactory.createTitledBorder("First Name"));

        javax.swing.GroupLayout namePanelLayout = new javax.swing.GroupLayout(namePanel);
        namePanel.setLayout(namePanelLayout);
        namePanelLayout.setHorizontalGroup(
            namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchCustomerLastNameField)
                    .addComponent(searchCustomerFirstNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addContainerGap())
        );
        namePanelLayout.setVerticalGroup(
            namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, namePanelLayout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addComponent(searchCustomerFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchCustomerLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
        );

        phonePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search by Phone", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        searchCustomerPhoneField.setBorder(javax.swing.BorderFactory.createTitledBorder("Phone"));
        try {
            searchCustomerPhoneField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###-####")));
        } catch (java.text.ParseException ex) {
            displayMessageToUser("ERROR: Incorrect Phone Number");
        }

        javax.swing.GroupLayout phonePanelLayout = new javax.swing.GroupLayout(phonePanel);
        phonePanel.setLayout(phonePanelLayout);
        phonePanelLayout.setHorizontalGroup(
            phonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phonePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchCustomerPhoneField, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );
        phonePanelLayout.setVerticalGroup(
            phonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phonePanelLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(searchCustomerPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        emailPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search by Email", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        emailPanel.setPreferredSize(new java.awt.Dimension(175, 22));

        searchCustomerEmailField.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));

        javax.swing.GroupLayout emailPanelLayout = new javax.swing.GroupLayout(emailPanel);
        emailPanel.setLayout(emailPanelLayout);
        emailPanelLayout.setHorizontalGroup(
            emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchCustomerEmailField, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );
        emailPanelLayout.setVerticalGroup(
            emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emailPanelLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(searchCustomerEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout searchCustomerPanelLayout = new javax.swing.GroupLayout(searchCustomerPanel);
        searchCustomerPanel.setLayout(searchCustomerPanelLayout);
        searchCustomerPanelLayout.setHorizontalGroup(
            searchCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchCustomerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(namePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(phonePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(emailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(searchCustomerPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(searchNameBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchPhoneBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(searchEmailBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        searchCustomerPanelLayout.setVerticalGroup(
            searchCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchCustomerPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(searchCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(namePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                    .addComponent(emailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                    .addComponent(phonePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(searchCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchEmailBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchNameBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPhoneBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        mainPanel.add(searchCustomerPanel, "card3");

        NewCustomerPanel.setName(""); // NOI18N

        ncPhoneField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phone Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        try {
            ncPhoneField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###-####")));
        } catch (java.text.ParseException ex){
            //display Error
            displayMessageToUser("ERROR: Incorrect Phone Number Format");
        }

        ncFirstNameField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "First Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        ncLastNameField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Last Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        ncLastNameField.setMinimumSize(new java.awt.Dimension(15, 40));

        ncCityField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "City", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        ncBirthField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Date of Birth", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        ncBirthField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-M-d"))));
        ncBirthField.setPreferredSize(new java.awt.Dimension(15, 40));

        ncStreetField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Street", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        ncEmailField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Email", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        ncCountryField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Country", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        ncStateField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "State", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        ncTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ncTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ncTitleLabel.setText("New Customer Information");

        ncClearButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ncClearButton.setText("Clear");
        ncClearButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayNewCustomer();
            }
        });

        ncSubmitBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ncSubmitBTN.setText("Submit");
        ncSubmitBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boolean dataIsEntered = true;
                //check data
                if(ncBirthField.getText().equals("0000-01-01"))
                    dataIsEntered = false;
                if(ncPhoneField.getText().equals("000-000-0000"))
                    dataIsEntered = false;
                if(ncFirstNameField.getText().equals(""))
                    dataIsEntered = false;
                if(ncLastNameField.getText().equals(""))
                    dataIsEntered = false;
                if(ncEmailField.getText().equals(""))
                    dataIsEntered = false;
                
                //proceed to next screen or display error
                if(dataIsEntered)
                    displayCustomerDetails();
                else
                    displayMessageToUser("Error: Missing Field");
            }
        });

        javax.swing.GroupLayout NewCustomerPanelLayout = new javax.swing.GroupLayout(NewCustomerPanel);
        NewCustomerPanel.setLayout(NewCustomerPanelLayout);
        NewCustomerPanelLayout.setHorizontalGroup(
            NewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewCustomerPanelLayout.createSequentialGroup()
                .addGroup(NewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NewCustomerPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(NewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(NewCustomerPanelLayout.createSequentialGroup()
                                .addComponent(ncFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ncLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(132, 132, 132)
                                .addComponent(ncBirthField, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(NewCustomerPanelLayout.createSequentialGroup()
                                .addGroup(NewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ncStreetField)
                                    .addGroup(NewCustomerPanelLayout.createSequentialGroup()
                                        .addComponent(ncCityField, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ncStateField, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(39, 39, 39)
                                .addComponent(ncCountryField, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, NewCustomerPanelLayout.createSequentialGroup()
                                .addGroup(NewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ncPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ncSubmitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(NewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ncClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ncEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(NewCustomerPanelLayout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(ncTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        NewCustomerPanelLayout.setVerticalGroup(
            NewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewCustomerPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(ncTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(NewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ncFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ncLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ncBirthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(ncStreetField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(NewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ncCityField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ncStateField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ncCountryField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(NewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ncEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ncPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addGroup(NewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ncClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ncSubmitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        
        mainPanel.add(NewCustomerPanel, "card4");

        mainNewResBTN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        mainNewResBTN.setText("New Reservation");
        mainNewResBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //display New Reservation Panel
                displayNewReservation();
            }
        });

        mainExistingResBTN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        mainExistingResBTN.setText("Existing Reservation");
        mainExistingResBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //display Existing Reservation Panel
                displayExistingReservation();
            }
        });

        javax.swing.GroupLayout mainReservationPanelLayout = new javax.swing.GroupLayout(mainReservationPanel);
        mainReservationPanel.setLayout(mainReservationPanelLayout);
        mainReservationPanelLayout.setHorizontalGroup(mainReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainReservationPanelLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(mainNewResBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(mainExistingResBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        mainReservationPanelLayout.setVerticalGroup(mainReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainReservationPanelLayout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addGroup(mainReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainExistingResBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainNewResBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(319, Short.MAX_VALUE))
        );

        mainPanel.add(mainReservationPanel, "card5");

        nrCheckOutField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Check Out (yyyy-mm-dd)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        try {
            nrCheckOutField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            //display Error Message
            displayMessageToUser("ERROR: Incorrect Input in CheckOut");
        }
        nrCheckOutField.setPreferredSize(new java.awt.Dimension(15, 40));

        nrTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        nrTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nrTitleLabel.setText("New Reservation Information");

        nrClearButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nrClearButton.setText("Clear");
        nrClearButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //display Existing Reservation Panel
                displayNewReservation();
            }
        });

        nrSubmitBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nrSubmitBTN.setText("Submit");
        nrSubmitBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boolean fieldsFilled = true;
              
                //check if data is filled
                if (nrCheckInField.getText().equals("0001-01-01"))
                    fieldsFilled = false;
                if (nrCheckOutField.getText().equals("0001-01-01"))
                    fieldsFilled = false;
                if (nrRoomsField.getText().equals(""))
                    fieldsFilled = false;
                if (nrGuestField.getText().equals(""))
                    fieldsFilled = false;
                
                
                
                if (fieldsFilled){
                    String checkIn = nrCheckInField.getText();
                    String checkOut = nrCheckOutField.getText();
                    int numRooms = Integer.parseInt(nrRoomsField.getText());
                    int prefType = roomTypeComboBox.getSelectedIndex();
                    System.out.println(roomTypeComboBox.getSelectedIndex());
                    roomMGR = new RoomManager(dbase, checkIn, checkOut, prefType, numRooms);
             
                
                    try {
                        if(roomMGR.searchAvailableRooms()){
                            displayMessageToUser("Rooms Available!");
                            int customerID = custMGR.getCustomer().getCustomerID();
                            Date checkIn1 = Date.valueOf(nrCheckInField.getText());
                            Date checkOut2 = Date.valueOf(nrCheckOutField.getText());
                            int numGuests = Integer.parseInt(nrGuestField.getText());

                            System.out.println("Customer ID " + customerID + " Dates " + checkIn.toString() + checkOut.toString() + " Guests: " + numGuests);

                            //create new Reservation
                            resMGR.createReservation(customerID, checkIn1, checkOut2, numGuests, roomMGR.getSelectedRoomIDs());

                            //display Existing Reservation Panel
                            displayReservationDetails();
                        } else
                            displayMessageToUser("No Rooms Found. Try Different Search");
                    } catch (Exception ex) {
                        displayMessageToUser("Error: SQL " + ex.getMessage());
                        System.out.println(ex.getMessage());
                    }
                }
                else
                    displayMessageToUser("Fill out Check in and Check Out Dates");
                
            }
        });

        nrRoomsField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "# of Rooms", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        nrCheckInField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Check In (yyyy-mm-dd)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        try {
            nrCheckInField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            displayMessageToUser("Error: Incorrect Date");
        }
        nrCheckInField.setPreferredSize(new java.awt.Dimension(15, 40));
        
        roomTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "Single", "Double", "King", "Suite" }));
        roomTypeComboBox.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Room Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        nrGuestField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "# of Guests", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        javax.swing.GroupLayout newReservationPanelLayout = new javax.swing.GroupLayout(newReservationPanel);
        newReservationPanel.setLayout(newReservationPanelLayout);
        newReservationPanelLayout.setHorizontalGroup(newReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newReservationPanelLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(newReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(newReservationPanelLayout.createSequentialGroup()
                        .addComponent(roomTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(nrRoomsField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(nrGuestField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(newReservationPanelLayout.createSequentialGroup()
                        .addComponent(nrCheckInField, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(nrCheckOutField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(newReservationPanelLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(nrSubmitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(nrClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(newReservationPanelLayout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(nrTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        newReservationPanelLayout.setVerticalGroup(newReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newReservationPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(nrTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(newReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nrCheckInField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nrCheckOutField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(newReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nrRoomsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nrGuestField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(149, 149, 149)
                .addGroup(newReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nrClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nrSubmitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        mainPanel.add(newReservationPanel, "card6");

        erSearchByNumberPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search by Reservation Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        erNumberField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reservation #", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        javax.swing.GroupLayout ecSearchByNumberPanelLayout = new javax.swing.GroupLayout(erSearchByNumberPanel);
        erSearchByNumberPanel.setLayout(ecSearchByNumberPanelLayout);
        ecSearchByNumberPanelLayout.setHorizontalGroup(
            ecSearchByNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ecSearchByNumberPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(erNumberField, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );
        ecSearchByNumberPanelLayout.setVerticalGroup(
            ecSearchByNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ecSearchByNumberPanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(erNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        erTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        erTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        erTitleLabel.setText("Existing Reservation Lookup");

        erSearchByNamePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search by Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        erFirstNameField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "First Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        erLastNameField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Last Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        erLastNameField.setMinimumSize(new java.awt.Dimension(15, 40));

        erCheckInField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Check In (yyyy-mm-dd)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        try {
            erCheckInField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            displayMessageToUser("ERROR: Incorrect Date");
        }
        erCheckInField.setPreferredSize(new java.awt.Dimension(15, 40));

        javax.swing.GroupLayout ecSearchByNamePanelLayout = new javax.swing.GroupLayout(erSearchByNamePanel);
        erSearchByNamePanel.setLayout(ecSearchByNamePanelLayout);
        ecSearchByNamePanelLayout.setHorizontalGroup(
            ecSearchByNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ecSearchByNamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ecSearchByNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ecSearchByNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(erFirstNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                        .addComponent(erLastNameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(erCheckInField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        ecSearchByNamePanelLayout.setVerticalGroup(
            ecSearchByNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ecSearchByNamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(erFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(erLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(erCheckInField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        erSearchNameBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        erSearchNameBTN.setText("Search Name");
        erSearchNameBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    Date d = Date.valueOf(erCheckInField.getText());
                    if(resMGR.lookUpByCustomer(custMGR.getCustomer().getCustomerID(), d)){
                        displayModifyReservation();
                    }
                    else
                        displayMessageToUser("Reservation Look up did not return results");
                } catch (SQLException ex) {
                    displayMessageToUser("Error: SQL ERROR TRY AGAIN!");
                    System.out.println(ex.getMessage());
                } catch (IllegalArgumentException ex){
                    displayMessageToUser("Date is not in correct format");
                }
            }
        });

        erSearchNumberBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        erSearchNumberBTN.setText("Search Number");
        erSearchNumberBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                try {
                    int i = Integer.parseInt(erNumberField.getText());
                    if(resMGR.lookUpByID(i)){
                        displayModifyReservation();
                    }
                    else
                        displayMessageToUser("Reservation Look up did not return results");
                } catch (SQLException ex ) {
                    displayMessageToUser("Error: SQL ERROR TRY AGAIN!");
                    System.out.println(ex.getMessage());
                }catch (NumberFormatException ex) {
                    displayMessageToUser("Error: Invalid Reservation ID");
                }
            }
        });

        javax.swing.GroupLayout existingReservationPanelLayout = new javax.swing.GroupLayout(existingReservationPanel);
        existingReservationPanel.setLayout(existingReservationPanelLayout);
        existingReservationPanelLayout.setHorizontalGroup(existingReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(existingReservationPanelLayout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(erTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(existingReservationPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(erSearchByNumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(erSearchByNamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, existingReservationPanelLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(erSearchNumberBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(erSearchNameBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );
        existingReservationPanelLayout.setVerticalGroup(existingReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(existingReservationPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(erTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addGroup(existingReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(erSearchByNumberPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(erSearchByNamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(existingReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(erSearchNumberBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(erSearchNameBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        mainPanel.add(existingReservationPanel, "card7");

        customerDisplayPanel.setName(""); // NOI18N

        cdPhoneField.setEditable(false);
        cdPhoneField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phone Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        try {
            cdPhoneField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###-####")));
        } catch (java.text.ParseException ex) {
            displayMessageToUser("ERROR: Incorrect Phone Number");
        }

        cdFirstNameField.setEditable(false);
        cdFirstNameField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "First Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        cdLastNameField.setEditable(false);
        cdLastNameField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Last Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        cdLastNameField.setMinimumSize(new java.awt.Dimension(15, 40));

        cdCityField.setEditable(false);
        cdCityField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "City", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        cdBirthField.setEditable(false);
        cdBirthField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Date of Birth", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        cdBirthField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-M-d"))));
        cdBirthField.setPreferredSize(new java.awt.Dimension(15, 40));

        cdStreetField.setEditable(false);
        cdStreetField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Street", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        cdEmailField.setEditable(false);
        cdEmailField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Email", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        cdCountryField.setEditable(false);
        cdCountryField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Country", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        cdStateField.setEditable(false);
        cdStateField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "State", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        cdTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cdTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cdTitleLabel.setText("Customer Information");

        cdClearBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cdClearBTN.setText("Cancel");
        cdClearBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //Goes back to Main Customer Panel
                displayMainCustomer();
            }
            });

        cdSubmitBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cdSubmitBTN.setText("Select Customer");
        cdSubmitBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //Redisplays Panel
                displayMainReservation();
            }
        });

        javax.swing.GroupLayout customerDisplayPanelLayout = new javax.swing.GroupLayout(customerDisplayPanel);
        customerDisplayPanel.setLayout(customerDisplayPanelLayout);
        customerDisplayPanelLayout.setHorizontalGroup(
            customerDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerDisplayPanelLayout.createSequentialGroup()
                .addGroup(customerDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customerDisplayPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(customerDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(customerDisplayPanelLayout.createSequentialGroup()
                                .addComponent(cdFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cdLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(132, 132, 132)
                                .addComponent(cdBirthField, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(customerDisplayPanelLayout.createSequentialGroup()
                                .addGroup(customerDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cdStreetField)
                                    .addGroup(customerDisplayPanelLayout.createSequentialGroup()
                                        .addComponent(cdCityField, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cdStateField, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(39, 39, 39)
                                .addComponent(cdCountryField, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, customerDisplayPanelLayout.createSequentialGroup()
                                .addGroup(customerDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cdPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cdSubmitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(customerDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cdClearBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cdEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(customerDisplayPanelLayout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(cdTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        customerDisplayPanelLayout.setVerticalGroup(
            customerDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerDisplayPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(cdTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(customerDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cdFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cdLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cdBirthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(cdStreetField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(customerDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cdCityField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cdStateField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cdCountryField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(customerDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cdEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cdPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addGroup(customerDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cdClearBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cdSubmitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        mainPanel.add(customerDisplayPanel, "card10");

        mrCheckOutField.setEditable(false);
        mrCheckOutField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Check Out", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        mrReservationNumberField.setEditable(false);
        mrReservationNumberField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mrReservationNumberField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reservation Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        mrLNameField.setEditable(false);
        mrLNameField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Last Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        mrFNameField.setEditable(false);
        mrFNameField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "First Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        mrCheckInField.setEditable(false);
        mrCheckInField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Check In", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        mrPhoneField.setEditable(false);
        mrPhoneField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phone", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        mrRoomTypeField.setEditable(false);
        mrRoomTypeField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Type of Room(s)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        mrRoomNumbersField.setEditable(false);
        mrRoomNumbersField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Room(s) Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        mrGuestsField.setEditable(false);
        mrGuestsField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "# of Guests", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        mrCostField.setEditable(false);
        mrCostField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mrCostField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cost", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        mrDeleteBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mrDeleteBTN.setText("Delete Reservation");
        mrDeleteBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //Redisplays Panel
                deleteReservation();
                displayMainReservation();
            }
        });
        
        mrModifyBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mrModifyBTN.setText("Modify Reservation");
        mrModifyBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteReservation();
                displayNewReservation();
                
            }
        });

        javax.swing.GroupLayout modifyReservationPanelLayout = new javax.swing.GroupLayout(modifyReservationPanel);
        modifyReservationPanel.setLayout(modifyReservationPanelLayout);
        modifyReservationPanelLayout.setHorizontalGroup(
            modifyReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modifyReservationPanelLayout.createSequentialGroup()
                .addGroup(modifyReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, modifyReservationPanelLayout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(mrReservationNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, modifyReservationPanelLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(modifyReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modifyReservationPanelLayout.createSequentialGroup()
                                .addComponent(mrFNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(mrLNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                                .addComponent(mrPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modifyReservationPanelLayout.createSequentialGroup()
                                .addGroup(modifyReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mrCostField, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(modifyReservationPanelLayout.createSequentialGroup()
                                        .addComponent(mrCheckInField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(mrCheckOutField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(modifyReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mrGuestsField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(modifyReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(mrRoomTypeField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                        .addComponent(mrRoomNumbersField, javax.swing.GroupLayout.Alignment.TRAILING))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modifyReservationPanelLayout.createSequentialGroup()
                                .addComponent(mrModifyBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mrDeleteBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(56, 56, 56))
        );
        modifyReservationPanelLayout.setVerticalGroup(
            modifyReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyReservationPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(mrReservationNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(modifyReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mrFNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mrLNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mrPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(modifyReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mrCheckInField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mrCheckOutField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mrGuestsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(mrRoomTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(modifyReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mrCostField)
                    .addComponent(mrRoomNumbersField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(modifyReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mrDeleteBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mrModifyBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        mainPanel.add(modifyReservationPanel, "card8");

        frCheckOutField.setEditable(false);
        frCheckOutField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Check Out", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        frResNumField.setEditable(false);
        frResNumField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        frResNumField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reservation Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        frLastNameField.setEditable(false);
        frLastNameField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Last Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        frFirstNameField.setEditable(false);
        frFirstNameField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "First Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        frCheckInField.setEditable(false);
        frCheckInField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Check In", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        frPhoneField.setEditable(false);
        frPhoneField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phone", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        frRoomTypeField.setEditable(false);
        frRoomTypeField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Type of Room(s)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        frRoomNumbersField.setEditable(false);
        frRoomNumbersField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Room(s) Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        frGuestsField.setEditable(false);
        frGuestsField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "# of Guests", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        frCostField.setEditable(false);
        frCostField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        frCostField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cost", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        frCancelBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        frCancelBTN.setText("Cancel Reservation");
        frCancelBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //Redisplays Panel
                displayMainReservation();
            }
        });

        frFinalizeBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        frFinalizeBTN.setText("Finalize Reservation");
        frFinalizeBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //Redisplays Panel
                finalizeReservation();
            }
        });

        javax.swing.GroupLayout finalizeReservationPanelLayout = new javax.swing.GroupLayout(finalizeReservationPanel);
        finalizeReservationPanel.setLayout(finalizeReservationPanelLayout);
        finalizeReservationPanelLayout.setHorizontalGroup(
            finalizeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, finalizeReservationPanelLayout.createSequentialGroup()
                .addGroup(finalizeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, finalizeReservationPanelLayout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(frResNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, finalizeReservationPanelLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(finalizeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(finalizeReservationPanelLayout.createSequentialGroup()
                                .addComponent(frFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(frLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                                .addComponent(frPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(finalizeReservationPanelLayout.createSequentialGroup()
                                .addGroup(finalizeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(frCostField, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(finalizeReservationPanelLayout.createSequentialGroup()
                                        .addComponent(frCheckInField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(frCheckOutField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(finalizeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(frGuestsField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(finalizeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(frRoomTypeField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                        .addComponent(frRoomNumbersField, javax.swing.GroupLayout.Alignment.TRAILING))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, finalizeReservationPanelLayout.createSequentialGroup()
                                .addComponent(frFinalizeBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(frCancelBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(56, 56, 56))
        );
        finalizeReservationPanelLayout.setVerticalGroup(
            finalizeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(finalizeReservationPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(frResNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(finalizeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(finalizeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frCheckInField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frCheckOutField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frGuestsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(frRoomTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(finalizeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(frCostField)
                    .addComponent(frRoomNumbersField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(finalizeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frCancelBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frFinalizeBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        mainPanel.add(finalizeReservationPanel, "card9");

        logoutButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        logoutButton.setText("LOGOUT");
        logoutButton.setEnabled(false);
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayLogin();
            }

        });

        returnCustomerBTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        returnCustomerBTN.setEnabled(false);
        returnCustomerBTN.setText("Return to Customer Select");
        returnCustomerBTN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayMainCustomer();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(returnCustomerBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(returnCustomerBTN)
                    .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getID());
    }
}
    



