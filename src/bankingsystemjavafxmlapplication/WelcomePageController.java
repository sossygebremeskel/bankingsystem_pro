/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bankingsystemjavafxmlapplication;

import static bankingsystemjavafxmlapplication.BankingSystemJavaFXMLApplication.bankSystemEngine;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomePageController implements Initializable {

    @FXML
    private Button btnStart;

    @FXML
    void onStart(ActionEvent event){
        
       try{
         bankSystemEngine  = new BankSystemEngine();
         bankSystemEngine.connect();
         if(bankSystemEngine.startDatabaseService()){
//             Stage stage = new Stage();
             Stage stage = (Stage) btnStart.getScene().getWindow();
             stage.close();
             Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
             Scene scene = new Scene(root);
             stage.setScene(scene);
//             stage.setMaximized(true);
             stage.show();
         }
         else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR); 
            errorAlert.setTitle("ERROR"); errorAlert.setHeaderText(null); 
            errorAlert.setContentText("please read the instractions and apply them,\nfor critical issue contact us."); 
            errorAlert.showAndWait();
         }
         
    
       }catch(Exception e){
           Alert errorAlert = new Alert(Alert.AlertType.ERROR); 
            errorAlert.setTitle("ERROR"); errorAlert.setHeaderText(null); 
            errorAlert.setContentText("something exceptional has happend please let us know."); 
            errorAlert.showAndWait();
       }
    }
    
    
    public static BankSystemEngine bankSystemEngine;
    static Thread bankEngineThread;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        
      
    
}}
