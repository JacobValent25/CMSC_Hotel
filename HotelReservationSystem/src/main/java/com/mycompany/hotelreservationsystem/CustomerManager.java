/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Emmanuel Girin
 * Project: Hotel Reservation System
 * Date: 06/23/2020
 * Modified: By EG 7/6 created body of each method, and constructors
 * Description: Creates a new Customer Object, Looks up an Existing Customer
 *              Connects to Database to query and update Customer Records
 */
class CustomerManager {
    //data
    Customer currentCustomer;
    Database dBase;
    

    /**
     * Created by EG 7/6 - Constructor
     * Initializes currentCustomer to a blank Customer
     */
    public CustomerManager(Database dbs) {
        currentCustomer = new Customer();
        dBase = dbs;
    }
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Modified by Emmanuel G 7/6 added body of method
     * Creates a new Customer Object with a passed array of strings
     * Create new Customer in Database Customer Records
     * Retrieves Unique Customer ID from records to update Customer Object
     * @param customerData 
     * @throws SQLException
     */
    void createCustomer(String[] customerData) throws SQLException{
        //creates customer object with data
        currentCustomer = new Customer(customerData);
        
        //Attempt to open connection to database
        dBase.connectDatabase();
        
        //create Query String from array
        String sql = "INSERT INTO CustomerRecord " +
                     "VALUES (";
        for(int i = 0; i < customerData.length; i++){
            //add data values from array seperated by comma
            if (i < (customerData.length - 1))
                sql += customerData[i] + ", ";
            //for last value add closing ')'
            else
                sql += customerData[i] + ")";
        }
        
        //Attempt to Insert Data into Table
        dBase.insertData(sql);
        
        //Attempt to close Connection
        dBase.closeConnection();
    }
    
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Modified 7/6 by EG to add body of method
     * Connects to Database to retrieve Customer by name return true if found
     * @param firstName
     * @param lastName
     * @return Default is false, true if look up is found
     * 
     */
    boolean lookUpCustomerByName(String firstName, String lastName) throws SQLException{
        boolean dataFound = false;
        //open connection to database
        dBase.connectDatabase();
        
        //create Query String from parameters
        String sql = "SElECT * " +
                      "FROM customerRecords " +
                      "WHERE firstName = '" + firstName + "'" +
                      "AND lastName = " + "'" + lastName + "'";
        
        //Query database, which returns a result set
        ResultSet rs = dBase.queryDatabase(sql);
        
        //if rows are returned plug data into an array and create customer object
        if(rs.isBeforeFirst()){
                //move cursor to the row returned
                rs.next();
                
                //build data array
                String[] custData = new String[9];
                custData[0] = rs.getString("ID");
                custData[1] = rs.getString("firstName");
                custData[2] = rs.getString("lastName");
                custData[3] = rs.getString("Street");
                custData[4] = rs.getString("City");
                custData[5] = rs.getString("State");
                custData[6] = rs.getString("Country");
                custData[7] = rs.getString("phone");
                custData[8] = rs.getString("email");
                custData[9] = rs.getString("DOB");
                
                //create new customer
                currentCustomer = new Customer(custData);
                
                
                //set Flag to True
                dataFound = true;
        }
        
        dBase.closeConnection();
        
        return dataFound;
    }
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Modified 7/6 by EG to add body of method
     * Connects to Database to retrieve Customer by phone return true if found
     * @param phoneNumber
     * @return Default is false, true if look up is found
     * 
     */
    boolean lookUpCustomerByPhone(String phoneNumber) throws SQLException{
        boolean dataFound = false;
        
        //open connection to database
        dBase.connectDatabase();
        
        //create Query String from parameters
        String sql = "SElECT * " +
                      "FROM customerRecords " +
                      "WHERE phone = '" + phoneNumber + "'";
        
        //Query database, which returns a result set
        ResultSet rs = dBase.queryDatabase(sql);
        
        //if rows are returned plug data into an array and create customer object
        if(rs.isBeforeFirst()){
                //move cursor to the row returned
                rs.next();
                
                //build data array
                String[] custData = new String[9];
                custData[0] = rs.getString("ID");
                custData[1] = rs.getString("firstName");
                custData[2] = rs.getString("lastName");
                custData[3] = rs.getString("Street");
                custData[4] = rs.getString("City");
                custData[5] = rs.getString("State");
                custData[6] = rs.getString("Country");
                custData[7] = rs.getString("phone");
                custData[8] = rs.getString("email");
                custData[9] = rs.getString("DOB");
                
                //create new customer
                currentCustomer = new Customer(custData);

                //set Flag to True
                dataFound = true;
        }
        
        dBase.closeConnection();
        
        return dataFound;
    }
    
    /**
     * Created by Emmanuel Girin 6/25 - basic structure
     * Modified 7/6 by EG to add body of method
     * Connects to Database to retrieve Customer by email return true if found
     * @param email
     * @return Default is false, true if look up is found
     * 
     */
    boolean lookUpCustomerByName(String email) throws SQLException{
        boolean dataFound = false;
        
        //open connection to database
        dBase.connectDatabase();
        
        //create Query String from parameters
        String sql = "SElECT * " +
                      "FROM customerRecords " +
                      "WHERE phone = '" + email + "'";
        
        //Query database, which returns a result set
        ResultSet rs = dBase.queryDatabase(sql);
        
        //if rows are returned plug data into an array and create customer object
        if(rs.isBeforeFirst()){
                //move cursor to the row returned
                rs.next();
                
                //build data array
                String[] custData = new String[9];
                custData[0] = rs.getString("ID");
                custData[1] = rs.getString("firstName");
                custData[2] = rs.getString("lastName");
                custData[3] = rs.getString("Street");
                custData[4] = rs.getString("City");
                custData[5] = rs.getString("State");
                custData[6] = rs.getString("Country");
                custData[7] = rs.getString("phone");
                custData[8] = rs.getString("email");
                custData[9] = rs.getString("DOB");
                
                //create new customer
                currentCustomer = new Customer(custData);
                
                //set Flag to True
                dataFound = true;
        }
        
        dBase.closeConnection();
        
        return dataFound;
    }
}
