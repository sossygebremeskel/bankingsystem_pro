/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package bankingsystemjavafxmlapplication;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuButton;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import javafx.scene.control.TableRow;

/**
 *
 * @author fish
 */
public class FXMLDocumentController implements Initializable {
   
    @FXML
    private Button btnAddCustomer;
    
    @FXML
    private Button btnAddEmployee;
    
    @FXML
    private Button btnDelete;


    @FXML
    private Button btnDeposit;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnTransfer;
    
    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnWithdraw;

    @FXML
    private Label label;
    
     @FXML
    private Label labelAccountBalance;

    @FXML
    private Label labelAccountType;

    @FXML
    private Label labelInterestRate;

    @FXML
    private Label lableAccountNumber;

    @FXML
    private MenuButton menuAccountType;

     @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDateOfBirth;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtSSN;
    
    @FXML
    private TableColumn<?, ?> clmnAccountNumber;

    @FXML
    private TableColumn<?, ?> clmnAmmount;

    @FXML
    private TableColumn<?, ?> clmnID;

    @FXML
    private TableColumn<?, ?> clmnTransactionDate;

    @FXML
    private TableColumn<?, ?> clmnTransactionType;
    
    @FXML
    private TableColumn<Customer, String> columnID;

    @FXML
    private TableColumn<Customer, String> columnAddress;

    @FXML
    private TableColumn<Customer, Number> columnBalance;

    @FXML
    private TableColumn<Customer, String> columnFirstName;

    @FXML
    private TableColumn<Customer, String> columnLastName;

    @FXML
    private TableColumn<Customer, String> columnPhoneNumber;
    
    @FXML
    private TableView<Customer> tableCustomer;

    @FXML
    private TableView<?> tableTransaction;
    
    
    

    @FXML
    void addCustomer(ActionEvent event) throws Exception {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddCustomers.fxml"));
        primaryStage.setTitle("Add Customer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnHidden(e ->{
                table();
            });
        
    }
    
     @FXML
    void addEmployee(ActionEvent event) throws Exception {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddEmployee.fxml"));
        primaryStage.setTitle("Add Employee");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnHidden(e ->{
                table();
            });
    }
    
    

    @FXML
    void logout(ActionEvent event) throws Exception{
        
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();
        System.out.println("Logged out");
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

     @FXML
    void deposit(ActionEvent event) throws Exception {
        Stage primaryStage = null;
        System.out.println("customer id :" + cusID);
        if(myIndex != -1){
             primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Deposit.fxml"));
            primaryStage.setTitle("Deposit Cash");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            primaryStage.setOnHidden(e ->{
                table();
            });
        }
        else{
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION); 
            errorAlert.setTitle("Caution!"); errorAlert.setHeaderText(null); 
            errorAlert.setContentText("please select aacount holder first."); 
            errorAlert.showAndWait();
        }
        
        
    }
    
    @FXML
    void transfer(ActionEvent event) {

    }

    @FXML
    void withdraw(ActionEvent event) throws Exception {
         Stage primaryStage = null;
        System.out.println("customer id :" + cusID);
        if(myIndex != -1){
             primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Withdraw.fxml"));
            primaryStage.setTitle("Withdraw Cash");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            primaryStage.setOnHidden(e ->{
                table();
            });
        }
        else{
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION); 
            errorAlert.setTitle("Caution!"); errorAlert.setHeaderText(null); 
            errorAlert.setContentText("please select account holder first on the table."); 
            errorAlert.showAndWait();
        }
        
       
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
  
    
    
    
    public Connection con;
     public PreparedStatement pst;
    public int myIndex = -1;
    public int id;
    public static int cusID = -1;
    
    
    
    
    
    public  void table()
      {
          
          connect();
          ObservableList<Customer> customers = FXCollections.observableArrayList();
          ResultSet rs;
       try 
       {
           pst = con.prepareStatement("select customer.customerID,customer.firstname,customer.lastname,customer.password,customer.dateOfBirth,customer.address,customer.phoneNumber,"
                   + "account.balance, account.accountType, account.accountNumber,account.interestRate "
                   + "from customer join account on customer.customerID = account.customerID");  
           rs = pst.executeQuery();
      {
          int count = 1;
        while (rs.next())
        {
            System.out.print(rs);
            Customer st = new Customer();
            st.setCustomerID(rs.getString("customerID"));
//            st.setCustomerID(count.toString());
            st.setFirstName(rs.getString("firstname"));
            st.setLastName(rs.getString("lastname"));
            st.setPassword(rs.getString("password"));
            st.setAddress(rs.getString("address"));
            st.setPhoneNumber(rs.getString("phoneNumber"));
            st.setBalance(rs.getDouble("balance"));
            st.setDateOfBirth(rs.getString("dateOfBirth"));
            
            st.setAccountNumber(rs.getString("accountNumber"));
            st.setAccountType(rs.getString("accountType"));
            st.setInterestRate(rs.getInt("interestRate"));
            customers.add(st);
       }
    } 
                tableCustomer.setItems(customers);
                columnID.setCellValueFactory(f -> f.getValue().customerIDProperty());
                columnAddress.setCellValueFactory(f -> f.getValue().addressProperty());
                columnFirstName.setCellValueFactory(f -> f.getValue().firstNameProperty());
                columnLastName.setCellValueFactory(f -> f.getValue().lastNameProperty());
                columnPhoneNumber.setCellValueFactory(f -> f.getValue().phoneNumberProperty());
                columnBalance.setCellValueFactory(f -> f.getValue().balanceProperty());
                
               

       }
       
       catch (SQLException ex) 
       {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }

                tableCustomer.setRowFactory( tv -> {
             TableRow<Customer> myRow = new TableRow<>();
             
             myRow.setOnMouseClicked (event -> 
             {System.out.print("customer table clicked.");
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tableCustomer.getSelectionModel().getSelectedIndex();
       

    //add all these characters to the customer class and make query.
    
    
                  
//                 id = Integer.parseInt(String.valueOf(tableCustomer.getItems().get(myIndex).getCustomerID()));
                   
                          
//                  System.out.print(tableCustomer.getItems().get(myIndex).getFirstName());
                  cusID = Integer.parseInt(String.valueOf(tableCustomer.getItems().get(myIndex).getCustomerID()));
                  txtFirstName.setText(tableCustomer.getItems().get(myIndex).getFirstName());
                  txtLastName.setText(tableCustomer.getItems().get(myIndex).getLastName());
                  txtPassword.setText(tableCustomer.getItems().get(myIndex).getPassword());
//                  txtDateOfBirth.setText(tableCustomer.getItems().get(myIndex).getDateOfBirth());
                  txtPhoneNumber.setText(tableCustomer.getItems().get(myIndex).getPhoneNumber());
                  txtAddress.setText(tableCustomer.getItems().get(myIndex).getAddress());
//                  txtSSN.setText(tableCustomer.getItems().get(myIndex).getSsn());

                  labelAccountBalance.setText(String.valueOf(tableCustomer.getItems().get(myIndex).getBalance()));
              labelAccountType.setText(tableCustomer.getItems().get(myIndex).getAccountType());
              labelInterestRate.setText(String.valueOf(tableCustomer.getItems().get(myIndex).getInterestRate()));
               lableAccountNumber.setText(tableCustomer.getItems().get(myIndex).getAccountNumber());

// set data for account detail
          
    
                           
                }
             });
                return myRow;
                   });
    
    
      }
    
    
     public void connect()
    {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
           
            con = DriverManager.getConnection("jdbc:mysql://localhost/bank_system_data","root","12345678");
            System.out.println("data connected successfully.");
        } catch (ClassNotFoundException ex) {
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connect();
        table();
    }    
    
}
