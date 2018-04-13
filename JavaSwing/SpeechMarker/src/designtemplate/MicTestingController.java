/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package designtemplate;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javafx.scene.control.Button;
import javax.swing.JPanel;
import javafx.scene.control.Label;
import javafx.stage.WindowEvent;




/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class MicTestingController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    Stage stage;
    
    @FXML
    AnchorPane recordPane;
   @FXML
    AnchorPane MicTestingPane;
    @FXML
    Label micLabel;
    Boolean isMicCheckRunning=true;
    
    Thread thrd;
     
    public MicStatus ms=new MicStatus();
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        micLabel.setText("");
        
        ms.startRecording();
        SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);
        recordPane.getChildren().add(swingNode);
         
        //check mic status
        checkMicStatus();
        
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
    
     
    Task<Void> timerTaskTrn = new Task<Void>()
    {
           
            @Override
            public Void call() throws Exception{
                while(isMicCheckRunning)
                {
                   System.out.println("getMicStatusValue() "+getMicStatusValue());
                   if(getMicStatusValue()==-1)
                   {
                       updateMessage("Low energy");
                   }
                   else if(getMicStatusValue()==0)
                   {
                       updateMessage("Sufficient energy");
                   }
                   else
                   {
                       updateMessage("Clipped");
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
        thrd=new Thread(timerTaskTrn);
        thrd.start();
         //label.textProperty().bind(task.messageProperty());
         micLabel.textProperty().bind(timerTaskTrn.messageProperty());
        //((Label)DesignTemplate.getRoot().lookup("#micLabel")).textProperty().bind(timerTask.messageProperty());
       
       
    }
     
    public void setdialogStage(Stage stage)
    {
        this.stage=stage;
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                // consume event
               
                     ms.isRunning=false;
                     isMicCheckRunning=false;
                     stage.close();
                }
        });
        
    }
    
    
    @FXML
    public void doneButton()
    {
        stage.close();
        ms.isRunning=false;
    }

 
  
}
