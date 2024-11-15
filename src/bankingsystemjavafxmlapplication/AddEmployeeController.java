/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bankingsystemjavafxmlapplication;

import java.net.URL;
import java.sql.*;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fish
 */
public class AddEmployeeController implements Initializable {
    private static final String sqlDriverPath = "com.mysql.cj.jdbc.Driver";
    private static final String databaseURL = "jdbc:mysql://localhost/bank_system_data";
    private static final String databaseUserName = "root";
    private static final String databasePassword = "12345678";
    
    
    private static String firstName = null;
    private static String lastName =null;
    private static Double salary = null;
    private static String position = null;
    private Connection connection;
    private PreparedStatement preparedStatement;

//    public static BankSystemEngine bankSystemEngine;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtSalary;

    @FXML
    void addEmployee(ActionEvent event) {
//        bankSystemEngine = new BankSystemEngine();
        if(firstName == null|| lastName ==null || salary == null || position == null){
            System.out.println("please enter all values");
        }
        else{
           try{
                String sql = "INSERT into employee(firstname,lastname,salary, position,password) values(?,?,?,?,?)"; 
//          System.out.println("please enter all values");
           PreparedStatement preparedStatement = connection.prepareStatement(sql); 
           preparedStatement.setString(1, firstName);
           preparedStatement.setString(2, lastName);
           preparedStatement.setDouble(3, salary);
           preparedStatement.setString(4, position);
           preparedStatement.setDouble(5, 12345678);
////         preparedStatement.setString(3, salary);

            preparedStatement.executeUpdate();
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION); 
            successAlert.setTitle("SUCCESSFULL"); successAlert.setHeaderText(null); 
            successAlert.setContentText("employee successfuly registered."); 
            successAlert.showAndWait();
            Stage stage = (Stage) btnAdd.getScene().getWindow();
               stage.close();
              
           }catch(SQLException e){
               e.printStackTrace();
           }
            
          
        }
    }

    @FXML
    void deleteEmployee(ActionEvent event) {

    }

    @FXML
    void updateEoployee(ActionEvent event) {

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
            Alert errorAlert = new Alert(Alert.AlertType.ERROR); 
            errorAlert.setTitle("Error Dialog"); errorAlert.setHeaderText(null); 
            errorAlert.setContentText("This is an error alert."); 
            errorAlert.showAndWait();

        }
    }
    
         
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //first name handler
        connect();
         txtFirstName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                firstName = newValue;
                System.out.println("firstname changed from " + oldValue + " to " + newValue);
            }
        } 
        );
         
         // last name handler
          txtLastName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                lastName = newValue;
                System.out.println("firstname changed from " + oldValue + " to " + newValue);
            }
        } 
        );
          
           
          
          
//            salary handler
          txtSalary.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                salary = Double.parseDouble(newValue);
                System.out.println("salary changed from " + oldValue + " to " + newValue);
            }
        } 
        );
          
          
           // position handler
          txtPosition.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                position = newValue;
                System.out.println("position changed from " + oldValue + " to " + newValue);
            }
        } 
        );
    }    
    
}
