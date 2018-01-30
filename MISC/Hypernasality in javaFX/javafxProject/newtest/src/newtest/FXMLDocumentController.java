/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtest;
//import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author IITG
 */
public class FXMLDocumentController implements Initializable {
    
   
    
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
       System.out.println("1st handle U clicked me");  
       // label.setText("U clicked me");
    }

    /**
     *
     * @param event
     * @param stage
     * @throws IOException
     * @throws Exception
     */
    @FXML
   public void handleButtonActionnew(ActionEvent event)throws IOException,Exception
    {
    /*System.out.println("2nd handle U clicked me");
     Newtest object=new Newtest();
     object.displayalert();
    */
        //AlertController.launch(appClass, args);
        //alert.start(primaryStage);
         //Application.launch(AlertController.class,"newframe");
        //AlertController alert=new AlertController();
        //alert.start(AlertController.classStage);
        drawingComponentControllerClass DC=new drawingComponentControllerClass();
        DC.start(drawingComponentControllerClass.classStage);
        
    }

    @FXML
    void initialize(ActionEvent event) throws IOException, Exception
    {
      // System.out.println("U clicked me"); 
        // Parent root = FXMLLoader.load(getClass().getResource("Alert.fxml"));
        
       // Scene scene = new Scene(root);
        // stage.setTitle("This is first Frame ");
        //stage.setScene(scene);
        //stage.show();
        //Newtest object=new Newtest();
        //Stage stage = null;
       // try{
        //object.displayalert();
        
        //}catch(Exception e)
        //{ 
          //  System.out.println("error"+e);

        System.out.println("different initiallize executed");
        AlertController alert=new AlertController();
        try{
        alert.initialize();
        }
        catch(Exception e)
        {
       System.out.println("error is in 1st initiallize"+e);
    }
    }
    @FXML
    void initialize(ActionEvent event,Stage stage)throws IOException,Exception
    { 
   // Newtest object=new Newtest();
   // try{
     //   object.displayalert(stage);
        
       // }catch(IOException e)
        //{ 
          //  System.out.println("error"+e);
       // }
    }
            
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
