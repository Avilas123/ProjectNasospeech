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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class Td3recordController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
    Process p;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void td3Record() throws InterruptedException
    {
        if(usrmode)
        {
            try
            {
                FXMLLoader loader2 = new FXMLLoader();        
                loader2.setLocation(DesignTemplate.class.getResource("Td3pause.fxml"));           
                AnchorPane renext= (AnchorPane)loader2.load();                   
                BorderPane border2=DesignTemplate.getRoot();           
                border2.setLeft(renext); 
                Td3pauseController control=loader2.getController();
                String str3="Td3confirmed.fxml";
                countDowntimer("#td3Timeleft",4,str3,"#progress4");
            
            
                String mfccPath="/home/training_module/Mfcc/Text-dependent/";
                String otherFilePath="/home/training_module/Otherfiles/";
            
                Thread thd=new Thread()
                {
                    public void run()
                    {
                        try 
                        {
                            int iter = getrepeatLoop() + 1;
                            recordWavFile("/home/training_module/Text-dependent/"+userNumber+"/"+userNumber+"_TD3_" + iter +  ".wav",4000);
//System.out.println("phone number is "+phoneNumber);
                       /* remove extension from the wave file*/
                            File extn=new File("/home/training_module/Text-dependent/"+userNumber+"/"+userNumber+ "_TD3_" + iter +  ".wav");
                            String str1=extn.getName();
// vp_wav=str1;//assign string name to another variable
                            int index=str1.indexOf("_");
                            String str=str1.substring(0,index);
                            System.out.printf(str);
                            /*end of */
                        //    p=Runtime.getRuntime().exec("src/designtemplate/object_file/mfccNStatComp_39 "+extn+" "+str+" "+otherFilePath+userNumber+"_strt"+" "+otherFilePath+userNumber+"_end"+" "+otherFilePath+userNumber+"_vunv"+" "+otherFilePath+userNumber+"_speech"+" "+otherFilePath+userNumber+"_avg"+" "+otherFilePath+userNumber+"_N"+" "+otherFilePath+userNumber+"_F"+" "+mfccPath+userNumber+"/"+userNumber+".mfcc");
                         //   p.waitFor();
                        } 
                        catch (Exception ex) {
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
                FXMLLoader loader2 = new FXMLLoader();        
                loader2.setLocation(DesignTemplate.class.getResource("Td3pause_debug.fxml"));           
                AnchorPane renext= (AnchorPane)loader2.load();                   
                BorderPane border2=DesignTemplate.getRoot();           
                border2.setLeft(renext); 
                Td3pause_debugController control=loader2.getController();
                String str3="Td3confirmed_debug.fxml";
                countDowntimer("#td3Timeleft",4,str3,"#progress4");
            
            
                String mfccPath="/home/training_module/Mfcc/Text-dependent/";
                String otherFilePath="/home/training_module/Otherfiles/";
            
                Thread thd=new Thread()
                {
                    public void run()
                    {
                        try 
                        {
                            int iter = getrepeatLoop() + 1;
                            recordWavFile("/home/training_module/Text-dependent/"+userNumber+"/"+userNumber+"_TD3_" + iter +  ".wav",4000);
//System.out.println("phone number is "+phoneNumber);
                       /* remove extension from the wave file*/
                            File extn=new File("/home/training_module/Text-dependent/"+userNumber+"/"+userNumber+ "_TD3_" + iter +  ".wav");
                            String str1=extn.getName();
// vp_wav=str1;//assign string name to another variable
                            int index=str1.indexOf("_");
                            String str=str1.substring(0,index);
                            System.out.printf(str);
                            /*end of */
                         //   p=Runtime.getRuntime().exec("src/designtemplate/object_file/mfccNStatComp_39 "+extn+" "+str+" "+otherFilePath+userNumber+"_strt"+" "+otherFilePath+userNumber+"_end"+" "+otherFilePath+userNumber+"_vunv"+" "+otherFilePath+userNumber+"_speech"+" "+otherFilePath+userNumber+"_avg"+" "+otherFilePath+userNumber+"_N"+" "+otherFilePath+userNumber+"_F"+" "+mfccPath+userNumber+"/"+userNumber+".mfcc");
                         //   p.waitFor();
                        } 
                        catch (Exception ex) {
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
        
    }
    
    @FXML
    public void loadVP()
    {
        try 
        {
             
            FXMLLoader loader = new FXMLLoader();        
            loader.setLocation(DesignTemplate.class.getResource("OvalPart.fxml"));           
            AnchorPane oval = (AnchorPane) loader.load();                   
            BorderPane border=DesignTemplate.getRoot();           
            border.setLeft(oval);           
            OvalPartController controller1 =(OvalPartController) loader.getController();
            //((Label)DesignTemplate.getRoot().lookup("#vptimer")).setText("pallavi");
            
            
         }
        catch(IOException e)
        {
            e.printStackTrace();
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
        };
        
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
            loader1.setLocation(DesignTemplate.class.getResource("TDNeedHelp.fxml"));
            AnchorPane record= (AnchorPane)loader1.load();
            Platform.setImplicitExit(false);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("TD help");
            stage.initOwner(getDP().getPrimaryStage());
            Scene scene = new Scene(record);
            stage.setScene(scene);
            TDNeedHelpController controller=loader1.getController();
            controller.setdialogStage(stage);
       // stage.onCloseRequestProperty();
            stage.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
