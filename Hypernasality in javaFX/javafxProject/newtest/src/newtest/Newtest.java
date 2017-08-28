/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtest;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author IITG
 */
public class Newtest extends Application 
{
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        //Parent root1 = FXMLLoader.load(getClass().getResource("Alert.fxml"));
        
        Scene scene = new Scene(root);
        //Scene scene1 = new Scene(root1);
        stage.setTitle("This is first Frame ");
        stage.setScene(scene);
        
        stage.show();
        //stage.setScene(scene1);
        //stage.show();
    }
    
/*void displayalert() throws IOException
{   
    Parent root1 = FXMLLoader.load(getClass().getResource("Alert.fxml"));
    Scene scene1 = new Scene(root1);
   try{
       
   // stage.setScene(scene1);
    
      }
   catch(Exception e)
      {
          System.out.println("error here");    
      }
   try {
      
       
       }catch(Exception e)
      {
          System.out.println("error in displayalert"+e);    
      }

   
   
}
 */   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
       // Application.launch(AlertController.class, args);
    
  //  Application.launch(AlertController.class, args);
    }
    
}
