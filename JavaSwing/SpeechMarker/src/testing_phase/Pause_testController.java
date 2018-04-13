/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing_phase;

import designtemplate.DesignTemplate;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javax.swing.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import plotwavform.PlotWave;
import javafx.scene.image.ImageView;


/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class Pause_testController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
   
     //Timer timer;
     //int counter=10;
    @FXML
    AnchorPane anchorPane;
    @FXML
    ImageView processing;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         processing.setVisible(false);
       //  checkCountDown();
        
        // final SwingNode swingNode = new SwingNode();
         //    createAndSetSwingContent(swingNode);
         //    anchorPane.getChildren().add(swingNode);
            
           
          
    }
    
    public boolean showImage()
    {
        processing.setVisible(true);
        return true;
    }
    
     private void createAndSetSwingContent(final SwingNode swingNode) {
             SwingUtilities.invokeLater(new Runnable() {
                 @Override
                 public void run() {
                     swingNode.setContent(new PlotWave(null));
                 }
             });
         }
    
     
     
    @FXML
    private void pauseAudio() throws InterruptedException
    {
        try 
        {
            
            cancelTimer();
            stopWavFile();
            FXMLLoader loader2 = new FXMLLoader();        
            loader2.setLocation(DesignTemplate.class.getResource("/testing_phase/OvalPart_test.fxml"));           
            AnchorPane pause= (AnchorPane)loader2.load();                   
            BorderPane border2=DesignTemplate.getRoot();           
            border2.setLeft(pause);           
            OvalPart_testController controller2=loader2.getController();
            //((Label)DesignTemplate.getRoot().lookup("#complete1")).setText("Recording on hold");
         }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    
    
    @FXML
    private void monitorTimer()
    {
         try
           {
          
              if(DesignTemplate.countDown==0)
              {
                  FXMLLoader loader2 = new FXMLLoader();        
                  loader2.setLocation(DesignTemplate.class.getResource("/testing_phase/Rerecord.fxml"));           
                  AnchorPane record1= (AnchorPane)loader2.load();                   
                  BorderPane border=DesignTemplate.getRoot();           
                  border.setLeft(record1);
                  RerecordController controller=loader2.getController();
              }
          
           }
           catch(Exception e){
               e.printStackTrace();
           }
       
    }
     @FXML
    public void micCheck() throws IOException
    {
       
        
        FXMLLoader loader1 = new FXMLLoader();        
        loader1.setLocation(DesignTemplate.class.getResource("/testing_phase/MicTesting.fxml"));           
        AnchorPane record= (AnchorPane)loader1.load();
        Platform.setImplicitExit(false);
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Mic Testing");
        stage.initOwner(getDP().getPrimaryStage());
        
                
        Scene scene = new Scene(record);
        stage.setScene(scene);
        MicTestingController controller=loader1.getController();
        controller.setdialogStage(stage);
       // stage.onCloseRequestProperty();
        stage.showAndWait();
        
    }
    
}
