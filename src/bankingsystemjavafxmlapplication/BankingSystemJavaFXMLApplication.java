/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package bankingsystemjavafxmlapplication;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author fish
 */
public class BankingSystemJavaFXMLApplication extends Application {
    
//    private static boolean isLoggedIn = false;
    public static BankSystemEngine bankSystemEngine;
    static Thread bankEngineThread;
    
    @Override
    public void start(Stage stage) throws Exception {
        
             Parent root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
             Scene scene = new Scene(root);
             stage.setScene(scene);
//             stage.setMaximized(true);
             stage.show();
        
       
    }

  
    
    public void startDatabaseService(){
        
    }
    public static void main(String[] args) {
        // starting bank engin thread
      //  bankSystemEngine = new BankSystemEngine();
        //bankEngineThread = new Thread(bankSystemEngine);
     //   bankSystemEngine.connect();
//        bankEngineThread.start();
bankSystemEngine  = new BankSystemEngine();
         bankSystemEngine.connect();
         bankSystemEngine.startDatabaseService();
        launch(args);
       
        
        
    }
    
}
