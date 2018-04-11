/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testing_phase;



import designtemplate.DesignTemplate;
import static designtemplate.DesignTemplate.countDown;
import static designtemplate.DesignTemplate.stopTimer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
import javax.swing.JPanel;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
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
    
    Thread thd;
    
    Boolean isMicCheckRunning=true;
    
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
     
     
    Task<Void> timerTask = new Task<Void>()
    {
           
            @Override
            public Void call() throws Exception{
                while(isMicCheckRunning)
                {
                    //System.out.println("getMicStatusValue() "+getMicStatusValue());
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
        thd=new Thread(timerTask);
        thd.start();
         //label.textProperty().bind(task.messageProperty());
         micLabel.textProperty().bind(timerTask.messageProperty());
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
                // show close dialog
             /*   Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Close Confirmation");
                alert.setHeaderText("Do you really want to quit?");
                alert.initOwner(stage);
                
                
                
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)
                {
                     event.consume();
                    //Platform.exit();
                     ms.isRunning=false;
                     isMicCheckRunning=false;
                     stage.close();
                }
                else
                {
                    System.out.println("");
                }*/
            }
        });
    }
    
    
    @FXML
    
    public void doneButton()
    {
        stage.close();
        ms.isRunning=false;
        isMicCheckRunning=false;
    }

 
  
}
