package com.mycompany.hotelreservationsystem;
import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Emmanuel Girin
 * Created: 7/6
 * Object for creating a database connection / querying
 * Code modified from https://www.tutorialspoint.com/jdbc/jdbc-insert-records.htm
 */
public class Database {  
   
    //DBC driver name and database URL
    static final String driver = "com.mysql.jdbc.Driver"; 
    static final String url = "jdbc:mysql://localhost/";

    //Credentials
    static final String user = "root";
    static final String pass = "Cr@ckfun18";
    
    
    /**
     * Modified from 
     * @throws ClassNotFoundException 
     */
    public Database() throws ClassNotFoundException{
        //register driver
        Class.forName(driver);
    }
    
    //Objects for Database
    Connection conn = null;
    Statement stmt = null;
  
    public void connectDatabase() throws SQLException{
        //open connection to database
        conn = DriverManager.getConnection(url, user, pass);
    }
   
    
    /**
     * Created by EG on 7/6
     * Attempts to insert data into database based on query String
     * @param sql
     * @return ResultSet of the query
     * @throws SQLException 
     */
    public ResultSet queryDatabase(String sql) throws SQLException{
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
        
    }
    
    /**
     * Created by EG on 7/6
     * Attempts to insert data into database based on query String
     * @param sql
     * @throws SQLException 
     */
    public void insertData(String sql) throws SQLException{
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }
    
    /**
     * Created by EG on 7/6
     * Attempts to Close connection to database
     * @throws SQLException 
     */
    public void closeConnection() throws SQLException{
        if(stmt != null)
            stmt.close();
        if(conn != null)
            conn.close();
    }
}
