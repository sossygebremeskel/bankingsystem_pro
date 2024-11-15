
package bankingsystemjavafxmlapplication;

import java.net.URL;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.Random;

public class AddCustomersController implements Initializable {

    //cutomer table data ssn is temporarlly null
    private static String firstName = "";
    private static String lastName ="";
    private static String password ="";
    private static String address = "";
    private static LocalDate dateOfBirth = null;
    private static String phoneNumber = "";
    
    public static FXMLDocumentController control = null;
    
    //account table datas
    private static String accountNumber = null;
    private static String accountType = "SAVING";
    private static Double firstDeposit =50.0;
    private static int interestRate = 2;
    private static int customerID = -1;
    
    
   @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;
    
     @FXML
    private Label lblAccountNumber;

    @FXML
    private Label lblAccountType;

    @FXML
    private Label lblInterestRate;

    @FXML
    private TextField txtAddress;

    @FXML
    private DatePicker txtDateOfBirth;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPhoneNumber;
    
     @FXML
    private TextField txtFirstDeposit;
     
     @FXML
    private TextField txtPassword;

    @FXML
    void addCustomer(ActionEvent event) {
         control = new FXMLDocumentController();
           
        if(validateData()== true){
             try{
                String sql = "INSERT into customer(firstname,lastname,address, password,phoneNumber,dateOfBirth,accountType) values(?,?,?,?,?,?,?)"; 
//          System.out.println("please enter all values");
                PreparedStatement preparedStatement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); 
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, address);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, phoneNumber);
                preparedStatement.setObject(6, dateOfBirth);
                preparedStatement.setString(7, accountType);


            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected>0){
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        int generatedID = generatedKeys.getInt(1);
                        customerID = generatedID;
                        addAccount(customerID);
                    }
                }
                catch(Exception e){
                     Alert errorAlert = new Alert(Alert.AlertType.INFORMATION); 
                     errorAlert.setTitle("ERROR"); errorAlert.setHeaderText(null); 
                     errorAlert.setContentText("ERROE HAS OCCURED PLEASE LET US KNOW IF YOU SEE THIS ALERT."); 
                     errorAlert.showAndWait();
                     Stage stage = (Stage) btnAdd.getScene().getWindow();
                     stage.close();
                }
            }
//            control.table();
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION); 
            successAlert.setTitle("SUCCESSFULL"); successAlert.setHeaderText(null); 
            successAlert.setContentText("customer successfuly registered."); 
            successAlert.showAndWait();
            Stage stage = (Stage) btnAdd.getScene().getWindow();
               stage.close();
              
           }catch(SQLException e){
               e.printStackTrace();
           }
        }
  
    }

    @FXML
    void deleteCustomer(ActionEvent event) {

    }

    @FXML
    void updateCustomer(ActionEvent event) {

    }
    

    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    
    
    public boolean validateData(){
     
       firstName = txtFirstName.getText();
       lastName = txtLastName.getText();
       address = txtAddress.getText();
       password = txtPassword.getText();
       phoneNumber = txtPhoneNumber.getText();
       dateOfBirth = txtDateOfBirth.getValue();
       
       //firstDeposit = Double.parseDouble(txtFirstDeposit.getText());
       String errorString = "";
       
       //check firstname, lastname, address, password, phone number,
       if(firstName.isBlank()|| lastName.isBlank() || address.isBlank() || password.isBlank()){
           errorString = errorString + "enter all datas to procede.\n";
       }
       //check if first deposit is numeric and greater than 50
       String fDeposit = txtFirstDeposit.getText();
       boolean isNumeric = fDeposit.matches("-?\\d+(\\.\\d+)?");
        if (isNumeric){
            if(Double.parseDouble(txtFirstDeposit.getText()) >= 50){
                firstDeposit = Double.parseDouble(txtFirstDeposit.getText());
            }
            else{errorString = errorString + "first deposit must be grater than 50.\n";}
        }
        else{errorString = errorString + "enter numberic data for first deposit.\n";}
        
       if(errorString.length()== 0){
            
            return true;
       }
       
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION); 
            errorAlert.setTitle("ERROR"); errorAlert.setHeaderText(null); 
            errorAlert.setContentText(errorString); 
            errorAlert.showAndWait();
           return false;
       
   
    }
    
    public void addAccount(int customerID){
        try{
                final int MIN_VALUE = 10000000;
                final int MAX_VALUE = 99999999;
                Random random = new Random();
                int randomNumber = MIN_VALUE + random.nextInt(MAX_VALUE - MIN_VALUE + 1);
                accountNumber = String.valueOf(randomNumber);
                String sql = "INSERT into account(accountNumber,accountType,interestRate,customerID,balance) values(?,?,?,?,?)"; 
//          System.out.println("please enter all values");
                PreparedStatement preparedStatement = con.prepareStatement(sql); 
                preparedStatement.setString(1, accountNumber);
                preparedStatement.setString(2, accountType);
                preparedStatement.setInt(3, interestRate);
                preparedStatement.setInt(4, customerID);
                preparedStatement.setDouble(5, firstDeposit);
               
            int rowsAffected = preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
     public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/bank_system_data","root","12345678");
        } catch (ClassNotFoundException ex) {
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connect();
        
         //first name handler
        
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
          txtAddress.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                address = newValue;
                System.out.println("address changed from " + oldValue + " to " + newValue);
            }
        } 
        );
          
          
           // position handler
          txtPhoneNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                phoneNumber = newValue;
                System.out.println("phone number changed from " + oldValue + " to " + newValue);
            }
        } 
        );
          
          
           // position handler
//          txtDateOfBirth.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                phoneNumber = newValue;
//                System.out.println("phone number changed from " + oldValue + " to " + newValue);
//            }
//        } 
//        );
        
    }    
    
}
