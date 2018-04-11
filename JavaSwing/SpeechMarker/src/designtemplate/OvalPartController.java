/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designtemplate;

import static designtemplate.DesignTemplate.userNumber;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.embed.swing.SwingNode;
import javax.swing.*;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author nazibur
 */

public class OvalPartController extends DesignTemplate implements Initializable {
  

    /**
     * Initializes the controller class.
     */
    Process p;
    DesignTemplate dp;
    TextField Phoneno;
    @FXML
    AnchorPane recordPane;
    Button doneButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   
    }    
    
    
     public void setDP(DesignTemplate dp)
    {
        this.dp=dp;
    }
     
    public void setPhone(TextField number)
    {
        this.Phoneno = number;
    }
    
    @FXML
    private void recordAudio() 
    {
        if(usrmode)
        {
            try 
            {
                System.out.println("design template oval controller");
                FXMLLoader loader1 = new FXMLLoader();        
                loader1.setLocation(DesignTemplate.class.getResource("Pause.fxml"));           
                AnchorPane record= (AnchorPane)loader1.load();                   
                BorderPane border1=DesignTemplate.getRoot();           
                border1.setLeft(record);
                
                String pathMfcc="/home/training_module/Mfcc/Voice-password/";
                String OtherFilesPath="/home/training_module/Otherfiles/";
                String str="Rerecord.fxml";
                countDowntimer("#timeleft",6,str,"#progress1");
                Thread thd=new Thread(){
                    public void run()
                    {
                        try {
                            int iter = getrepeatLoop() + 1;
                            p=Runtime.getRuntime().exec("mkdir -p "+"/home/training_module/Voice-password/"+userNumber);
                            p=Runtime.getRuntime().exec("mkdir -p "+"/home/training_module/Mfcc/Voice-password/"+userNumber);
                            p.waitFor();
                            recordWavFile("/home/training_module/Voice-password/"+userNumber+"/"+userNumber+ "_VP_" + iter +  ".wav",6000);
                            /* remove extension from the wave file*/
                            File extn=new File("/home/training_module/Voice-password/"+userNumber+"/" +userNumber+"_VP_" + iter +".wav");
                            String str1=extn.getName();
                            int index=str1.indexOf("_");
                            String str=str1.substring(0,index);
                            System.out.println(extn);
                            /* -------end------*/
                          //  p=Runtime.getRuntime().exec("src/designtemplate/object_file/mfccNStatComp_39 "+extn+" "+str+" "+OtherFilesPath+userNumber+"_strt"+" "+OtherFilesPath+userNumber+"_end"+" "+OtherFilesPath+userNumber+"_vunv"+" "+OtherFilesPath+userNumber+"_speech"+" "+OtherFilesPath+userNumber+"_avg"+" "+OtherFilesPath+userNumber+"_N"+" "+OtherFilesPath+userNumber+"_F"+" "+pathMfcc+userNumber+"/"+userNumber+".mfcc");
                          //  p.waitFor();
                        } 
                        catch (Exception ex) 
                        {
                            ex.printStackTrace();
                        }
                    }
                };
                thd.start();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try 
            {
                FXMLLoader loader1 = new FXMLLoader();        
                loader1.setLocation(DesignTemplate.class.getResource("Pause_debug.fxml"));           
                AnchorPane record= (AnchorPane)loader1.load();                   
                BorderPane border1=DesignTemplate.getRoot();           
                border1.setLeft(record);
                
                String pathMfcc="/home/training_module/Mfcc/Voice-password/";
                String OtherFilesPath="/home/training_module/Otherfiles/";
                String str="Rerecord_debug.fxml";
               // countDowntimer("#timeleft",6,str,"#progress1");
                Thread thd=new Thread(){
                    public void run()
                    {
                        try {
                            int iter = getrepeatLoop() + 1;
                            p=Runtime.getRuntime().exec("mkdir -p "+"/home/training_module/Voice-password/"+userNumber);
                            p=Runtime.getRuntime().exec("mkdir -p "+"/home/training_module/Mfcc/Voice-password/"+userNumber);
                            p.waitFor();
                             recordWavFile("/home/training_module/Voice-password/"+userNumber+"/"+userNumber+ "_VP_" + iter +  ".wav",6000);
                            /* remove extension from the wave file*/
                            File extn=new File("/home/training_module/Voice-password/"+userNumber+"/" +userNumber+"_VP_" + iter +".wav");
                            String str1=extn.getName();
                            int index=str1.indexOf("_");
                            String str=str1.substring(0,index);
                            System.out.println(extn);
                            /* -------end------*/
                          //  p=Runtime.getRuntime().exec("src/designtemplate/object_file/mfccNStatComp_39 "+extn+" "+str+" "+OtherFilesPath+userNumber+"_strt"+" "+OtherFilesPath+userNumber+"_end"+" "+OtherFilesPath+userNumber+"_vunv"+" "+OtherFilesPath+userNumber+"_speech"+" "+OtherFilesPath+userNumber+"_avg"+" "+OtherFilesPath+userNumber+"_N"+" "+OtherFilesPath+userNumber+"_F"+" "+pathMfcc+userNumber+"/"+userNumber+".mfcc");
                          //  p.waitFor();
                        } 
                        catch (Exception ex) 
                        {
                            ex.printStackTrace();
                        }
                    }
                };thd.start();
                 countDowntimer("#timeleft",6,str,"#progress1");
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
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
    
    
    @FXML
    public void micCheck() throws IOException
    {
        if(usrmode)
        {
            FXMLLoader loader1 = new FXMLLoader();        
            loader1.setLocation(DesignTemplate.class.getResource("MicTesting.fxml"));           
            AnchorPane record= (AnchorPane)loader1.load();
            Platform.setImplicitExit(false);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Mic Testing");
            stage.initOwner(getDP().getPrimaryStage());
        
        //remove the title bar
       // stage.initStyle(StageStyle.UNDECORATED);
            
            Scene scene = new Scene(record);
            stage.setScene(scene);
            MicTestingController controller=loader1.getController();
            controller.setdialogStage(stage);
       // stage.onCloseRequestProperty();
            stage.showAndWait();
        }
        else
        {
            FXMLLoader loader1 = new FXMLLoader();        
            loader1.setLocation(DesignTemplate.class.getResource("micDebugMode.fxml"));           
            AnchorPane record= (AnchorPane)loader1.load();
            Platform.setImplicitExit(false);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Mic Testing");
            stage.initOwner(getDP().getPrimaryStage());
        
        //remove the title bar
       // stage.initStyle(StageStyle.UNDECORATED);
                
            Scene scene = new Scene(record);
            stage.setScene(scene);
            micDebugModeController controller=loader1.getController();
            controller.setdialogStage(stage);
       // stage.onCloseRequestProperty();
            stage.showAndWait();
        }
        
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
    
    
    
   /* public void startCountDown() 
    {
      timer.schedule(new TimerTask() 
       {
         @Override
         public void run() 
         {
           Platform.runLater(new Runnable() 
            {
             public void run() 
              {
                countDown--;
                ((Label)DesignTemplate.getRoot().lookup("#timeleft")).setText(Integer.toString(countDown));
                 //countDownText.setText("Time left:" + countDown);
                 if (countDown <= 0)
                 {
                    countDown=10;
                    timer.cancel();
                 }
              }
            });
          }
        }, 1000,1000); //Every 1 second
    }
 */   
}
