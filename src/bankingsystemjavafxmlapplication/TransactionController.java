/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bankingsystemjavafxmlapplication;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class TransactionController implements Initializable {

    
    @FXML
    private Button btnDeposit;
    
    @FXML
    private Button btnWithdraw;

    @FXML
    private Label labelCurrentBalance;

    @FXML
    private Label lableAccountHolderName;

    @FXML
    private TextField txtDeposit;
    
    @FXML
    void onDeposit(ActionEvent event) {
        System.out.print("customer id from deposit : ");
        String input = txtDeposit.getText();
        boolean isNumeric = input.matches("-?\\d+(\\.\\d+)?");
        try{
             if (isNumeric){
            Double value = Double.parseDouble(input);
            newBalance = oldBalance + value;
            String sql = "update account set balance = ? where customerID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql); 
            preparedStatement.setDouble(1,newBalance);
            preparedStatement.setInt(2,cusID);
            int rowsUpdated = preparedStatement.executeUpdate();
            if(rowsUpdated > 0){
             Alert successAlert = new Alert(Alert.AlertType.INFORMATION); 
            successAlert.setTitle("SUCCESSFULL"); successAlert.setHeaderText(null); 
            successAlert.setContentText("you have deposited successfuly."); 
            successAlert.showAndWait();
            }
            
            
//            controller.table();
            Stage stage = (Stage) btnDeposit.getScene().getWindow();
            stage.close();
            

        }else{
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION); 
            errorAlert.setTitle("Error!"); errorAlert.setHeaderText(null); 
            errorAlert.setContentText("please enter a numeric value."); 
            errorAlert.showAndWait();
             }
        }
        catch(Exception e){
                e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION); 
            errorAlert.setTitle("Error!"); errorAlert.setHeaderText(null); 
            errorAlert.setContentText("something wrong happend please let us know."); 
            errorAlert.showAndWait();
            Stage stage = (Stage) btnDeposit.getScene().getWindow();
            stage.close();

                }
       
           
    }
    
    @FXML
    void onWithdraw(ActionEvent event) {
        System.out.print("customer id from deposit : ");
        String input = txtDeposit.getText();
        boolean isNumeric = input.matches("-?\\d+(\\.\\d+)?");
        try{
             if (isNumeric){
            Double value = Double.parseDouble(input);
            newBalance = oldBalance - value;
            if(newBalance >= 0) {
                 String sql = "update account set balance = ? where customerID = ?";
                 PreparedStatement preparedStatement = con.prepareStatement(sql); 
                 preparedStatement.setDouble(1,newBalance);
                 preparedStatement.setInt(2,cusID);
                 int rowsUpdated = preparedStatement.executeUpdate();
                 if(rowsUpdated > 0){
                     Alert successAlert = new Alert(Alert.AlertType.INFORMATION); 
                     successAlert.setTitle("SUCCESSFULL"); successAlert.setHeaderText(null); 
                      successAlert.setContentText("you have withdrawed successfuly."); 
                      successAlert.showAndWait();
                     Stage stage = (Stage) btnWithdraw.getScene().getWindow();
                       stage.close();
                 } //end of rowsupdated check    
             }//end of newbalance check
            else{
               Alert successAlert = new Alert(Alert.AlertType.INFORMATION); 
               successAlert.setTitle("Caution"); successAlert.setHeaderText(null); 
               successAlert.setContentText("you have limitted ammount entere other value."); 
               successAlert.showAndWait();
            }
           
            

        }// end of is numeric ckeck
             else{
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION); 
            errorAlert.setTitle("Error!"); errorAlert.setHeaderText(null); 
            errorAlert.setContentText("please enter a numeric value."); 
            errorAlert.showAndWait();
             }
        }
        catch(Exception e){
                e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION); 
            errorAlert.setTitle("Error!"); errorAlert.setHeaderText(null); 
            errorAlert.setContentText("something wrong happend please let us know."); 
            errorAlert.showAndWait();
            Stage stage = (Stage) btnDeposit.getScene().getWindow();
            stage.close();

                }
    }
     
  
    public static FXMLDocumentController controller;
    static Connection con;
    static PreparedStatement pst;
     int cusID;
    double oldBalance;
    double newBalance;
    int myIndex;
    int id;
    
    
    
     public void connect()
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
        connect();
        System.out.print("customer id from depositt : ");
        controller = new FXMLDocumentController();
        cusID = controller.cusID;
         try{

           String sql = "select customer.firstname, account.balance from customer join account on customer.customerID = account.customerID where customer.customerID = ?"; 
                
              PreparedStatement preparedStatement = con.prepareStatement(sql); 
              preparedStatement.setInt(1,cusID);
              ResultSet rs = preparedStatement.executeQuery();
      
         
        while (rs.next())
        {
            System.out.print("first name :" + rs.getString("firstname"));
            System.out.print("balance : " + rs.getDouble("balance"));
            oldBalance = rs.getDouble("balance");
            lableAccountHolderName.setText(rs.getString("firstname"));
            labelCurrentBalance.setText(String.valueOf(rs.getDouble("balance")));
            break;
            
       }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }    
    
}
