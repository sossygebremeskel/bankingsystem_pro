/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystemjavafxmlapplication;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert; 
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author fish
 */
public class BankSystemEngine  {
    
    public static final String sqlDriverPath = "com.mysql.cj.jdbc.Driver";
    public static final String databaseURL = "jdbc:mysql://localhost/bank_system_data";
    public static final String databaseUserName = "root";
    public static final String databasePassword = "12345678";
    
    private Connection connection;
    
    
    int myIndex;
    int id;
    
    

    public boolean startDatabaseService(){
//        Statement statement = connection.createStatement();
//        if(doesTableExist(connection, "customer"))
//        PreparedStatement preparedStatement;
         try{
             connection.setAutoCommit(false);
//        String createDatabase = "CREATE DATABASE bank_system_data";
        String createTableCustomer = "CREATE TABLE  IF NOT EXISTS customer ("
                + "customerID INT AUTO_INCREMENT PRIMARY KEY,"
                + "firstname varchar(100),"
                + "lastname varchar(100),"
                + "password varchar(50),"
                + "address varchar(100),"
                + "phoneNumber varchar(100),"
                + "dateOfBirth date,"
                + "ssn varchar(50),"
                + "accountType varchar(50)"
                + ")";
        String createTableAccount = "create table  IF NOT EXISTS account ("
                + "accountNumber varchar(100),"
                + "accountType varchar(50),"
                + "interestRate int,"
                + "customerID int,"
                + "balance decimal(10, 2),"
                + "FOREIGN KEY (customerID) REFERENCES customer(customerID)"
                
                + ")";
        String createTableEmployee = "create table  IF NOT EXISTS employee ("
                 + "employeeID INT AUTO_INCREMENT PRIMARY KEY,"
                + "firstname varchar(100),"
                + "lastname varchar(100),"
                + "password varchar(50),"
                + "position varchar(100),"
                + "salary decimal(10,2)"
                + ")";
        String createTableTransaction= "create table IF NOT EXISTS  transaction ("
                 + "transactionID INT AUTO_INCREMENT PRIMARY KEY,"
                + "transactionDate date,"
                + "transactionType varchar(100),"
                + "ammount decimal(10,2),"
                + "description varchar(100),"
                + "fromAccountNumber varchar(100),"
                + "toAccountNumber varchar(100)"
                
                + ")";
        
        String insertEmployee = ""
                + "insert into employee (firstname, lastname, password, position, salary)"
                + "values('root','root','12345678','accountant',15000.00)";
        
        
      
       
             
//        preparedStatement.addBatch(createDatabase);
Statement statment = connection.createStatement();
//   PreparedStatement preparedStatement = connection.prepareStatement(createTableCustomer);
        statment.addBatch(createTableCustomer);
        statment.addBatch(createTableAccount);
        statment.addBatch(createTableEmployee);
        statment.addBatch(createTableTransaction);
        statment.addBatch(insertEmployee);
        
        statment.executeBatch();
        connection.commit();
        return true;
         }catch(Exception e){
             System.out.print("error on crating database tables.");
             e.printStackTrace();
             return false;
         }
        
    }
    public void connect()
    {
        try {
//          Class.forName("com.mysql.jdbc.Driver");
            Class.forName(sqlDriverPath);
      
            connection = DriverManager.getConnection(databaseURL,databaseUserName,databasePassword);
            System.out.println("data connected successfully.");
        } catch (ClassNotFoundException ex) {
           System.out.println("there is problem in loading database driver");
          ex.printStackTrace();
         
        } catch (SQLException ex) {
            System.out.println("there is problem in connecting to the database :");
            ex.printStackTrace();
            Alert errorAlert = new Alert(AlertType.ERROR); 
            errorAlert.setTitle("Error Dialog"); errorAlert.setHeaderText(null); 
            errorAlert.setContentText("This is an error alert."); 
            errorAlert.showAndWait();

        }
    }
    
    
    
    public ResultSet doLogin(String username, String password, String databaseTable){

         try 
       {
           String sql = "SELECT firstname, password FROM ? WHERE firstname = ? and password = ?"; 
           PreparedStatement preparedStatement = connection.prepareStatement(sql); 
           preparedStatement.setString(1, databaseTable);
           preparedStatement.setString(2, username);
           preparedStatement.setString(3, password);

           ResultSet resultSet = preparedStatement.executeQuery();
           return resultSet;  
       } 
       catch (SQLException ex) 
       {
           ex.printStackTrace();
       }
         return null;
    }
    
    
    
    public Connection getConnection(){
        
        return connection;
    }
    
    
    
}
