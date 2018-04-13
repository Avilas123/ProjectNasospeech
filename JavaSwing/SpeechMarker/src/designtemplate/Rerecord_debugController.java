/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designtemplate;

import designtemplate.DesignTemplate;
import static designtemplate.DesignTemplate.userNumber;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.SwingUtilities;
import plotwavform.PlotWave;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class Rerecord_debugController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
   @FXML
    AnchorPane anchorPane;
   // PlotWave pw;
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            // TODO
           //  PlotWave pw=new PlotWave(null);
          
            // File fileName=new File("/home/Database_v0/Voice-password/Train/1034/1034_trn_vp_a_1.wav");
            getPW().stopRecord();
           //   ms.isRunning=false;
            final SwingNode swingNode = new SwingNode();
            createAndSetSwingContent(swingNode);
            anchorPane.getChildren().add(swingNode);
        
    }
    
     private void createAndSetSwingContent(final SwingNode swingNode) 
     {
             SwingUtilities.invokeLater(new Runnable() 
             {
                 @Override
                 public void run() 
                 {
                     int iter = getrepeatLoop() + 1;
                     pw=new PlotWave(null);
                     swingNode.setContent(pw);
                     File fileName=new File("/home/training_module/Voice-password/"+userNumber+"/"+userNumber+ "_VP_" + iter +  ".wav");
                     pw.createAudioInputStream(fileName, null, true);
                 }
             });
     }
       
  @FXML
  private void rerecordAudio()
  {
      try 
        {
                
            FXMLLoader loader2 = new FXMLLoader();        
            loader2.setLocation(DesignTemplate.class.getResource("OvalPart.fxml"));           
            AnchorPane oval= (AnchorPane)loader2.load();                   
            BorderPane border2=DesignTemplate.getRoot();           
            border2.setLeft(oval);           
            OvalPartController controller2=loader2.getController();
         }
        catch(IOException e)
        {
            e.printStackTrace();
        }
  }
  
  @FXML
  private void Next()
  {
      try 
        {
                
            FXMLLoader loader2 = new FXMLLoader();        
            loader2.setLocation(DesignTemplate.class.getResource("Td1record.fxml"));           
            AnchorPane renext= (AnchorPane)loader2.load();                   
            BorderPane border2=DesignTemplate.getRoot();           
            border2.setLeft(renext);           
           
            Td1recordController control=loader2.getController();
         }
        catch(IOException e)
        {
            e.printStackTrace();
        }
      
      
  }
  
  @FXML
  public void playRecorded()
  {
      int iter = getrepeatLoop() + 1;
      getPW().playSoundAll();
     // playAudio("/home/training_module/Voice-password/"+userNumber+"/"+userNumber+ "_VP_" + iter +  ".wav");
  }
  
   @FXML
    public void micCheck() throws IOException
    {
       
        FXMLLoader loader1 = new FXMLLoader();        
        loader1.setLocation(DesignTemplate.class.getResource("micDebugMode.fxml"));           
        AnchorPane record= (AnchorPane)loader1.load();
        Platform.setImplicitExit(false);
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Mic Testing");
        stage.initOwner(getDP().getPrimaryStage());
        
        //remove title bar
        //stage.initStyle(StageStyle.UNDECORATED);
        
                
        Scene scene = new Scene(record);
        stage.setScene(scene);
        micDebugModeController controller=loader1.getController();
        controller.setdialogStage(stage);
       // stage.onCloseRequestProperty();
        stage.showAndWait();
        
    }
     @FXML
    public void toHome()
    {
        try {
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(DesignTemplate.class.getResource("MainPage.fxml"));
            AnchorPane record= (AnchorPane)loader1.load();
            BorderPane border1=DesignTemplate.getRoot();
            border1.setLeft(record);
        } catch (IOException ex) {
            Logger.getLogger(OvalPartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
        @FXML
    public void NeedHelp()
    {
        try {
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(DesignTemplate.class.getResource("VPNeedHelp.fxml"));
            AnchorPane record= (AnchorPane)loader1.load();
            Platform.setImplicitExit(false);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("VP help");
            stage.initOwner(getDP().getPrimaryStage());
            Scene scene = new Scene(record);
            stage.setScene(scene);
            VPNeedHelpController controller=loader1.getController();
            controller.setdialogStage(stage);
       // stage.onCloseRequestProperty();
            stage.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
