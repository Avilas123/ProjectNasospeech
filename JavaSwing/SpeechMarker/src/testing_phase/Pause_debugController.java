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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javax.swing.Timer;
import javafx.event.ActionEvent;
import javafx.event.EventType;
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
import javafx.scene.image.ImageView;

import javafx.scene.control.ProgressBar;
import plotwavform.PlotWave;

/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class Pause_debugController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
   
     //Timer timer;
     //int counter=10;
    @FXML
    AnchorPane anchorPane;
    @FXML
    ProgressBar progress1;
    @FXML
    ImageView processingDebug;
    public MicStatus ms=new MicStatus();
    Process p;
   // PlotWave pw;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          //  stopRecord();
            // TODO
            //  PlotWave pw=new PlotWave(null);
            processingDebug.setVisible(false);
            progress1.setVisible(false);
            
            final SwingNode swingNode = new SwingNode();
            createAndSetSwingContent(swingNode);
            anchorPane.getChildren().add(swingNode);
          /*  ms.startRecording();
            SwingNode swingNode = new SwingNode();
            createSwingContent(swingNode);
            anchorPane.getChildren().add(swingNode);*/
        
          
       
             
             
           
          
    }
    
     private void createAndSetSwingContent(final SwingNode swingNode) 
     {
         pw=new PlotWave(null);
             SwingUtilities.invokeLater(new Runnable() 
             {
                  
                 @Override
                 public void run() 
                 {
                    
                     swingNode.setContent(pw);
                     System.out.println("After plotting wav panel");
                     pw.recordSound();
                   
                 }
                  
             });
             // pw.recordSound();
     }
     
      private void createSwingContent(final SwingNode swingNode) {
         
         
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() 
            {
                swingNode.setContent(ms.contentPane);    
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
            getPW().stopRecord();
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
                  loader2.setLocation(DesignTemplate.class.getResource("/testing_phase/Rerecord_debug.fxml"));           
                  AnchorPane record1= (AnchorPane)loader2.load();                   
                  BorderPane border=DesignTemplate.getRoot();           
                  border.setLeft(record1);
                  Rerecord_debugController controller=loader2.getController();
              }
          
           }
           catch(Exception e){
               e.printStackTrace();
           }
       
    }
     @FXML
    public void micCheck() throws IOException
    {
        /*
        Thread mic=new Thread(){
            public void run(){
                playAudio("/home/NetBeansProjects/DesignTemplate_tmp/src/audio/please.wav");
                try {
                    recordWav("/home/Desktop/check.wav",6000);
                } catch (IOException ex) {
                    Logger.getLogger(OvalPart_testController.class.getName()).log(Level.SEVERE, null, ex);
                }
                playAudio("/home/Desktop/check.wav");
                playAudio("/home/NetBeansProjects/DesignTemplate_tmp/src/audio/if_you.wav");
            }
        };mic.start();
        */
        
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
