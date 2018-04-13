/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designtemplate;

import debugmode.MicStatusDebug;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javax.swing.SwingUtilities;
import debugmode.PlotWave;
import java.util.Optional;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
//import javafx.scene.control.Alert;
import javafx.scene.control.Label;
//import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class micDebugModeController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
        public Stage stage;
        @FXML
        AnchorPane wavPane;
        @FXML
        Label micLabel;
        
        public PlotWave pw=new PlotWave(null);
        
       public MicStatusDebug ms=new MicStatusDebug();
        
         
    Thread thrd;
    
    Boolean isMicCheckRunning=true;
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       micLabel.setText("");
       ms.startRecording();
       final SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);
        wavPane.getChildren().add(swingNode);
        checkMicStatus();
    }   
    
    
    
    
     public void setdialogStage(Stage stage)
    {
        this.stage=stage;
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                // consume event
               
                     pw.stopRecord();
                     ms.isRunning=false;
                     isMicCheckRunning=false;
                     stage.close();
                }
        });

       
    }
   
      
      
       Task<Void> timerTaskDebug = new Task<Void>()
        {
           
            @Override
            public Void call() throws Exception{
                while(isMicCheckRunning)
                {
                    //System.out.println("getMicStatusValue() "+getMicStatusValue());
                   
                   if(true)
                   {
                       updateMessage("SNR Value: "+getNSRValue());
                   }
                    
                   
               }
                if (isMicCheckRunning==false)
                {
                    this.cancel();
                }

                return null;
            }
     };
     
       
        
      private void checkMicStatus()
     {
        thrd=new Thread(timerTaskDebug);
        thrd.start();
         //label.textProperty().bind(task.messageProperty());
        micLabel.textProperty().bind(timerTaskDebug.messageProperty());
        //((Label)DesignTemplate.getRoot().lookup("#micLabel")).textProperty().bind(timerTask.messageProperty());
       
       
    }
      
     
     
    private void createSwingContent(final SwingNode swingNode) 
    {
         
         
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() 
            {
                swingNode.setContent(pw);    
                pw.recordSound();
            }
            
        });
    }
    
    @FXML
    public void doneButton()
    {
        stage.close();
        pw.stopRecord();
        ms.isRunning=false;
        isMicCheckRunning=false;
    }
}
