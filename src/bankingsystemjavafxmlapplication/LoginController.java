
package bankingsystemjavafxmlapplication;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
//import javafx.scene.control.Menu; 
import javafx.scene.control.MenuItem;
import javafx.beans.value.ChangeListener; 
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;


public class LoginController implements Initializable {

    
    private static String username;
    private static String password;
    private static String usertype;
    public static BankSystemEngine bankSystemEngine;
   

    
   
    
    @FXML
    private Button btnLogin;
    
    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private MenuButton menuUserType;

    @FXML private MenuItem menuItemCustomer; 
    @FXML private MenuItem menuItemEmployee; 
    @FXML private MenuItem menuItemManager;
    
  
     
    
    @FXML
    void onLogin(ActionEvent event) {
       
        System.out.println("Login clicked user enterd: ");
        System.out.println("user name: "+ username );
        System.out.println("password: " + password);
        System.out.println("user type: " + usertype);
         try{
           String sql ;
           sql = "SELECT firstname, password FROM employee WHERE firstname = ? and password = ?"; 
//             if(usertype == "manager"){
//                 sql = "SELECT firstname, password FROM employee WHERE firstname = ? and password = ?"; 
//             }else if(usertype == "employee"){
//                 sql = "SELECT firstname, password FROM employee WHERE firstname = ? and password = ?"; 
//             }
//             else {
//                 sql = "SELECT firstname, password FROM customer WHERE firstname = ? and password = ?"; 
//             }
            
//           String sql = "SELECT firstname, password FROM customer WHERE firstname = ? and password = ?"; 
           PreparedStatement preparedStatement = con.prepareStatement(sql); 
//           preparedStatement.setString(1, "customer");
           preparedStatement.setString(1, username);
           preparedStatement.setString(2, password);

           ResultSet resultSet = preparedStatement.executeQuery();
 
           if (!resultSet.isBeforeFirst()) 
               { 
                   Alert errorAlert = new Alert(Alert.AlertType.ERROR); 
            errorAlert.setTitle("ERROR"); errorAlert.setHeaderText(null); 
            errorAlert.setContentText("user name and password does't match."); 
            errorAlert.showAndWait();
             } else 
           { while (resultSet.next()) { 
               String retrievedFirstname = resultSet.getString("firstname"); 
               String retrievedPassword = resultSet.getString("password"); 
               System.out.println("Firstname: " + retrievedFirstname + ", Password: " + retrievedPassword); 
               
               Stage stage = (Stage) btnLogin.getScene().getWindow();
               stage.close();
               Stage primaryStage = new Stage();
               Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
               primaryStage.setTitle("Add Customer");
               primaryStage.setScene(new Scene(root));
               primaryStage.show();
               break;
}
           }
          
          
       } 
       catch (SQLException ex) 
       {
            System.out.println("there is problem in login");
       }
         catch(  Exception e){
             e.printStackTrace();
         }
       
          
          
    }
    

    
//    void handleMenuSelection(MenuButton item) { System.out.println("Selected: " + item.getText()); }
 
    private void handleMenuSelection(String userType) {
        usertype = userType;
        menuUserType.setText(userType);
        System.out.println(userType);
         }
    
    
    
    
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    
    
    
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
        
          
         Connect();
        txtUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                username = newValue;
                System.out.println("username changed from " + oldValue + " to " + newValue);
            }
        } 
        );
        
        txtPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                password = newValue;
                System.out.println("pasword changed from " + oldValue + " to " + newValue);
            }
        } 
        );
        menuItemCustomer.setOnAction(e -> handleMenuSelection("customer")); 
        menuItemEmployee.setOnAction(e -> handleMenuSelection("employee")); 
        menuItemManager.setOnAction(e -> handleMenuSelection("manager"));
        
        
                
//        txtUsername.setOnInputMethodTextChanged(value -> system.out.println(value));

    }    
    
}
