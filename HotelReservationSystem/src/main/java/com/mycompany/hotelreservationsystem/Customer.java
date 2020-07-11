/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem;
import java.sql.Date;

/**
 *
 * @author Emmanuel Girin
 * Project: Hotel Reservation System
 * Date: 06/23/2020
 * Modified: 7/6 by EG added constructors, initialized values, and finished class, get / set methods
 * Description: Class holds data for new or existing reservations.
 *              This data can be displayed or retrieved to be 
 *              updated to a database.
 */


public class Customer {
    //Customer data, default
    private int customerID;
    private String firstName, lastName;
    private String street, city, state, country;
    private String phone, email;
    private Date dateOfBirth;
    

    /** 
     * Created by: EG
     * Date: 7/6
     * Default Constructor for Customer initializes everything to -1
     */
    public Customer(){
        customerID = -1;
        firstName = "";
        lastName = "";
        street = "";
        city = "";
        state = "";
        country = "";
        phone = "";
        email = "";
        dateOfBirth = Date.valueOf("0000-1-1");
    }
    
    /**
     * Created by: EG
     * Date: 7/6
     * MODIFIED: 7/11 removed customerID field out of constructor
     * Constructor takes Array of Data to parse customer data
     * @param customerData 
     * 
     */
    public Customer(String[] customerData){
        customerID = Integer.parseInt(customerData[0]);
        firstName = customerData[1];
        lastName = customerData[2];
        street = customerData[3];
        city = customerData[4];
        state = customerData[5];
        country = customerData[6];
        phone = customerData[7];
        email = customerData[8];
        //Date must be in format "YYYY-[m]m-[d]d"
        dateOfBirth = Date.valueOf(customerData[9]);
        
    }
    
    
    /**
     * Created by: EG
     * Date: 7/6
     * @return Data values of Customer Object minus customerID
     */
    @Override
    public String toString() {
        return "Customer{" + "firstName=" + firstName + ", lastName=" + lastName 
                + ", street=" + street + ", city=" + city + ", state=" + state 
                + ", country=" + country + ", phone=" + phone + ", email=" 
                + email + ", dateOfBirth=" + dateOfBirth + '}';
    }
   
    
    //All getter and setter methods created by Emmanuel Girin 6/25
    //Getter and Setter Methods for Customer Data
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dOB) {
        this.dateOfBirth = dOB;
    }
    
    
}
