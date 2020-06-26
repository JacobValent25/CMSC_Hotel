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
 * Date Created: 06/25/2020
 * Description: Class queries database to verify employee login and password
 * 
 */

class Login {
    //data
    private String message = "";
    
    
    /**
     * Emmanuel Girin created 6/25
     * @param id
     * @param pass
     * Queries Database to validate userID and password
     * updates Message if either are incorrect
     * @return true if credentials are valid, default is false
     */
    boolean validateCredentials(int id, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Emmanuel Girin created 6/25
     * @return message is returned for any invalid usernames or passwords 
     */
    String getMessage(){
            return message;
    }
    
}
