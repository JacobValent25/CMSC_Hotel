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
 * Description: Creates a new Customer Object, Looks up an Existing Customer
 *              Connects to Database to query and update Customer Records
 */
class CustomerManager {
    //data
    Customer currentCustomer;
    
    /**
     * Creates a new Customer Object with a passed array of strings
     * Create new Customer in Database Customer Records
     * Retrieves Unique Customer ID from records to update Customer Object
     * @param customerData 
     */
    void createCustomer(String[] customerData){
        
    }
    
    /**
     * Connects to Database to retrieve Customer by name return true if found
     * @param firstName
     * @param lastName
     * @return Default is false, true if look up is found
     * 
     */
    boolean lookUpCustomerByName(String firstName, String lastName){
        return false;
    }
    
    /**
     * Connects to Database to retrieve Customer by phone return true if found
     * @param phoneNumber
     * @return Default is false, true if look up is found
     * 
     */
    boolean lookUpCustomerByPhone(String phoneNumber){
        return false;
    }
    
    /**
     * Connects to Database to retrieve Customer by email return true if found
     * @param email
     * @return Default is false, true if look up is found
     * 
     */
    boolean lookUpCustomerByName(String email){
        return false;
    }
}
