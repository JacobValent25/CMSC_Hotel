/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Emmanuel Girin
 * Project: Hotel Reservation System
 * Date Created: 06/25/2020
 * Modified 7/6 by EG to create body of methods and Constructor
 * Description: Class queries database to verify employee login and password
 * 
 */

class Login {
    //data
    private String message = "";
    private final Database dBase;
    
    
    /**
     * Created: 7/6 by Emmanuel Girin
     * Constructor for Login Object, which passes the database to query
     * @param dbs 
     */
    public Login(Database dbs){
        dBase = dbs;
    }
    
    /**
     * Emmanuel Girin created 6/25
     * Modified: 7/6 by EG added body of method 
     * @param id
     * @param pass
     * Queries Database to validate userID and password
     * updates Message if either are incorrect
     * @return true if credentials are valid, default is false
     */
    boolean validateCredentials(String id, String pass) throws SQLException {
        boolean loginFound = false;
        
        //open connection to database
        dBase.connectDatabase();
        
        //create Query String from parameters
        String sql = "SElECT * " +
                      "From userRecords " +
                      "WHERE empID = '" + id + "'" +
                      "AND pass = " + "'" + pass + "'";
        
        //Query database, which returns a result set
        ResultSet rs = dBase.queryDatabase(sql);
        
        //if rows are returned then ID and password match
        if(rs.isBeforeFirst()){
                loginFound = true;
        } 
        else {
            
            //See if the ID matchces
            sql = "SElECT * " +
                  "From userRecords " +
                  "WHERE empID = '" + id + "'";
            
            //Query Database
            rs = dBase.queryDatabase(sql);
            
            //if rows are returned then password is incorrect
            if(rs.isBeforeFirst())
                message = "Error: Incorrect Password";
            else
                message = "Error: Incorrect Employee ID";
        }
        
        return loginFound;
    }
    
    /**
     * Emmanuel Girin created 6/25
     * @return message is returned for any invalid usernames or passwords 
     */
    String getMessage(){
            return message;
    }
    
}
