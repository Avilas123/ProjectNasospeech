/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing_phase;

import designtemplate.DesignTemplate;
import static designtemplate.DesignTemplate.testID;
import designtemplate.VPNeedHelpController;
import designtemplate.micDebugModeController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Alert;
import java.util.Optional;
//import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;


/**
 * FXML Controller class
 *
 * @author nazibur
 */

public class OvalPart_testController extends DesignTemplate implements Initializable {
  

    /**
     * Initializes the controller class.
     */
    
    Process p;
    DesignTemplate dp;
    TextField Phoneno;
    @FXML
    AnchorPane recordPane;
    Button doneButton;
    String vp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        msec=Long.toString(System.currentTimeMillis());
        
   
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
        vp=getTestSpkrId()+"_"+getMSec()+"_VP.wav";
        if(usrmode)
        {
            try 
            {
                //System.out.println("testing phase oval controller");
                FXMLLoader loader1 = new FXMLLoader();        
                loader1.setLocation(DesignTemplate.class.getResource("/testing_phase/Pause_test.fxml"));           
                AnchorPane record= (AnchorPane)loader1.load();                   
                BorderPane border1=DesignTemplate.getRoot();           
                border1.setLeft(record);
                
                String pathMfcc="/home/testing_module/Mfcc/Voice-password/";
                String OtherFilesPath="/home/testing_module/Otherfiles/";
                String str="/testing_phase/Rerecord.fxml";
                String str1="/testing_phase/Td1record.fxml";
                countDowntimerTest("#timeleft",6,str,str1,"#progress1",vp,"VP");
                Thread thd=new Thread(){
                    public void run()
                    {
                        try {
                            int iter = getrepeatLoop() + 1;
                            p=Runtime.getRuntime().exec("mkdir -p "+"/home/testing_module/Voice-password/"+testID);
                            p=Runtime.getRuntime().exec("mkdir -p "+"/home/testing_module/Mfcc/Voice-password/"+testID);
                            p.waitFor();
                            p=Runtime.getRuntime().exec("/home/scripts/exportLib.sh");
                            p.waitFor();
                            recordWavFile("/home/testing_module/Voice-password/"+testID+"/"+vp,6000);
                            /* remove extension from the wave file*/
                            File extn=new File("/home/testing_module/Voice-password/"+testID+"/" +vp);
                            String str1=extn.getName();
                            int index=str1.indexOf("_");
                            String str=str1.substring(0,index);
                            System.out.println(extn);
                            
                            // show the procesing image in the pause.fxml file after completion of recording 
                            
                            ((ImageView)DesignTemplate.getRoot().lookup("#processing")).setVisible(true);
                            /* -------end------*/
                           // p=Runtime.getRuntime().exec("src/designtemplate/object_file/mfccNStatComp_39 "+extn+" "+str+" "+OtherFilesPath+testID+"_strt"+" "+OtherFilesPath+testID+"_end"+" "+OtherFilesPath+testID+"_vunv"+" "+OtherFilesPath+testID+"_speech"+" "+OtherFilesPath+testID+"_avg"+" "+OtherFilesPath+testID+"_N"+" "+OtherFilesPath+testID+"_F"+" "+pathMfcc+testID+"/"+testID+".mfcc");
                           // p.waitFor();
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
                loader1.setLocation(DesignTemplate.class.getResource("/testing_phase/Pause_debug.fxml"));           
                AnchorPane record= (AnchorPane)loader1.load();                   
                BorderPane border1=DesignTemplate.getRoot();           
                border1.setLeft(record);
                
                String pathMfcc="/home/testing_module/Mfcc/Voice-password/";
                String OtherFilesPath="/home/testing_module/Otherfiles/";
                String str="/testing_phase/Rerecord_debug.fxml";
                
                countDowntimerDebug("#timeleft",6,str,"#progress1",vp,"VP");
               // countDowntimer("#timeleft",6,str,"#progress1");
                Thread thd=new Thread(){
                    public void run()
                    {
                        try {
                            int iter = getrepeatLoop() + 1;
                            p=Runtime.getRuntime().exec("mkdir -p "+"/home/testing_module/Voice-password/"+testID);
                            p=Runtime.getRuntime().exec("mkdir -p "+"/home/testing_module/Mfcc/Voice-password/"+testID);
                            p.waitFor();
                             recordWavFile("/home/testing_module/Voice-password/"+testID+"/"+vp,6000);
                            /* remove extension from the wave file*/
                            File extn=new File("/home/testing_module/Voice-password/"+testID+"/"+vp);
                            String str1=extn.getName();
                            int index=str1.indexOf("_");
                            String str=str1.substring(0,index);
                            System.out.println(extn);
                            /* -------end------*/
                            
                            ((ImageView)DesignTemplate.getRoot().lookup("#processingDebug")).setVisible(true);
                            
                            p=Runtime.getRuntime().exec("src/designtemplate/object_file/mfccNStatComp_39 "+extn+" "+str+" "+OtherFilesPath+testID+"_strt"+" "+OtherFilesPath+testID+"_end"+" "+OtherFilesPath+testID+"_vunv"+" "+OtherFilesPath+testID+"_speech"+" "+OtherFilesPath+testID+"_avg"+" "+OtherFilesPath+testID+"_N"+" "+OtherFilesPath+testID+"_F"+" "+pathMfcc+testID+"/"+testID+".mfcc");
                            p.waitFor();
                            
                            p=Runtime.getRuntime().exec("python src/designtemplate/shellscripts/vpExtractMfcc.py "+getTestSpkrId()+" "+vp);
                            p.waitFor();
                            
                        } 
                        catch (Exception ex) 
                        {
                            ex.printStackTrace();
                        }
                    }
                };thd.start();
               //  countDowntimer("#timeleft",6,str,"#progress1");
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
            loader1.setLocation(DesignTemplate.class.getResource("/testing_phase/MicTesting.fxml"));           
//loader1.setLocation(DesignTemplate.class.getResource("micDebugMode.fxml"));           
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
        //loader1.setLocation(DesignTemplate.class.getResource("/testing_phase/MicTesting.fxml"));           
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
            ex.printStackTrace();
        }
    }
    
    
     
}
