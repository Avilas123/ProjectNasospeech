/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing_phase;

import designtemplate.DesignTemplate;
import static designtemplate.DesignTemplate.getMSec;
import static designtemplate.DesignTemplate.testID;
import static designtemplate.DesignTemplate.usrmode;
import designtemplate.TDNeedHelpController;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.util.Random;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class Td1recordController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
    Process p;
    @FXML
    Label tdPrompt;
    String td;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Random num=new Random();
        int randomInt=num.nextInt(3);
        //int randomInt = 1;
        no=randomInt;
        if(randomInt==1)
            tdPrompt.setText("'LOVELY PICTURES CAN ONLY BE DRAWN'");
        else if(randomInt==2)
            tdPrompt.setText("'GET INTO THE HOLE OF TUNNELS'");
    }    
    @FXML
  private void td1Record() throws InterruptedException
  {
      td=getTestSpkrId()+"_"+getMSec()+"_TD"+(getPromptNo()+1)+".wav";
      
      if(usrmode)
      {
          try 
          {
              FXMLLoader loader2 = new FXMLLoader();        
              loader2.setLocation(DesignTemplate.class.getResource("/testing_phase/Td1pause.fxml"));           
              AnchorPane renext= (AnchorPane)loader2.load();                   
              BorderPane border2=DesignTemplate.getRoot();           
              border2.setLeft(renext);
              Td1pauseController control=loader2.getController();
// changeLayout("Td1pause.fxml");
              String str1="/testing_phase/Td1confirmed.fxml";
              String str2="/testing_phase/Tirecord.fxml";
              
              countDowntimerTest("#td1Timeleft",4,str1,str2,"#progress2",td,"TD");
            
              String mfccPath="/home/testing_module/Mfcc/Text-dependent/";
              String otherFilePath="/home/training_module/Otherfiles/";
            
            
              Thread thd=new Thread()
              {
                  public void run()
                  {
                      try 
                      {
                          int iter = getrepeatLoop() + 1;
                          p=Runtime.getRuntime().exec("mkdir -p "+"/home/testing_module/Text-dependent/"+testID);
                         // p=Runtime.getRuntime().exec("mkdir -p "+"/home/testing_module/Otherfiles/"+testID);
                          p=Runtime.getRuntime().exec("mkdir -p "+"/home/testing_module/Mfcc/Text-dependent/"+testID);
                          p.waitFor();
                          recordWavFile("/home/testing_module/Text-dependent/"+testID+"/"+td,4000);
                       
                          
                       /* remove extension from the wave file*/
                          File extn=new File("/home/testing_module/Text-dependent/"+testID+"/"+td);
                          String str1=extn.getName();
                      
                          
                          int index=str1.indexOf(".");
                          String str=str1.substring(0,index);
                          System.out.printf(str);
                          /*end of */
                          
                          ((ImageView)DesignTemplate.getRoot().lookup("#processing")).setVisible(true);
                          
                         // p=Runtime.getRuntime().exec("src/designtemplate/object_file/mfccNStatComp_39 "+extn+" "+str+" "+otherFilePath+testID+"_strt"+" "+otherFilePath+testID+"_end"+" "+otherFilePath+testID+"_vunv"+" "+otherFilePath+testID+"_speech"+" "+otherFilePath+testID+"_avg"+" "+otherFilePath+testID+"_N"+" "+otherFilePath+testID+"_F"+" "+mfccPath+testID+"/"+testID+".mfcc");
                         // p.waitFor();
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
      else // this part for debug mode
      {
          try 
          {
              FXMLLoader loader2 = new FXMLLoader();        
              loader2.setLocation(DesignTemplate.class.getResource("/testing_phase/Td1pause_debug.fxml"));           
              AnchorPane renext= (AnchorPane)loader2.load();                   
              BorderPane border2=DesignTemplate.getRoot();           
              border2.setLeft(renext);
              Td1pause_debugController control=loader2.getController();

              String str1="/testing_phase/Td1confirmed_debug.fxml";
             // countDowntimer("#td1Timeleft",4,str1,"#progress2");
              
              countDowntimerDebug("#td1Timeleft",4,str1,"#progress2",td,"TD");
              
              String mfccPath="/home/testing_module/Mfcc/Text-dependent/";
              String otherFilePath="/home/training_module/Otherfiles/";
            
            
              Thread thd=new Thread()
              {
                  public void run()
                  {
                      try 
                      {
                          int iter = getrepeatLoop() + 1;
                          p=Runtime.getRuntime().exec("mkdir -p "+"/home/testing_module/Text-dependent/"+testID);
                        //  p=Runtime.getRuntime().exec("mkdir -p "+"/home/training_module/Otherfiles/"+testID);
                          p=Runtime.getRuntime().exec("mkdir -p "+"/home/testing_module/Mfcc/Text-dependent/"+testID);
                          p.waitFor();
                          recordWavFile("/home/testing_module/Text-dependent/"+testID+"/"+td,4000);
                       
                          
                       /* remove extension from the wave file*/
                          File extn=new File("/home/testing_module/Text-dependent/"+testID+"/"+td);
                          String str1=extn.getName();
                      
                          
                          int index=str1.indexOf(".");
                          String str=str1.substring(0,index);
                          System.out.printf(str);
                          /*end of */
                          ((ImageView)DesignTemplate.getRoot().lookup("#processingDebug")).setVisible(true);
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
            loader.setLocation(DesignTemplate.class.getResource("/testing_phase/OvalPart_test.fxml"));           
            AnchorPane oval = (AnchorPane) loader.load();                   
            BorderPane border=DesignTemplate.getRoot();           
            border.setLeft(oval);           
            OvalPart_testController controller1 =(OvalPart_testController) loader.getController();
            //((Label)DesignTemplate.getRoot().lookup("#vptimer")).setText("pallavi");
            
            
         }
        catch(IOException e)
        {
            e.printStackTrace();
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
    
    
    @FXML
    public void micCheck() throws IOException
    {
        if(usrmode)
        {
            FXMLLoader loader1 = new FXMLLoader();        
            loader1.setLocation(DesignTemplate.class.getResource("/testing_phase/MicTesting.fxml"));           
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
        
        //remove title bar
        //stage.initStyle(StageStyle.UNDECORATED);
        
            
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
