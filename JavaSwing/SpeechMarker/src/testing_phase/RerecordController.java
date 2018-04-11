/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing_phase;

import designtemplate.DesignTemplate;
import designtemplate.TDNeedHelpController;
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
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.*;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author nazibur
 */

public class RerecordController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button rerecord,playRecorded,exit;
    Process p;
    String filePath;
    
    @FXML
    Label decission,phoneNoAndName;
    
    @FXML
    ImageView acceptImg,rejectImg;
    
     String str1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
     
            // TODO
            rerecord.setVisible(false);
            playRecorded.setVisible(false);
            rejectImg.setVisible(false);
            exit.setVisible(true);
            //define file path
          /*  filePath=getTestSpkrId()+"_"+getMSec()+"_VP.wav";
            System.out.println(filePath);
           // System.out.println(filePath);
            
            p=Runtime.getRuntime().exec("python src/designtemplate/shellscripts/vpExtractMfcc.py "+getTestSpkrId()+" "+filePath);
            p.waitFor();
            BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str;
            while((str=br.readLine())!=null)
            {
                System.out.println(str);
                str1=str;
                
            }
            if(str1.equals("pass"))
            {
                acceptImg.setVisible(true);
                rejectImg.setVisible(false);
                exit.setVisible(true);
            }
            else
            {
                acceptImg.setVisible(false);
                rejectImg.setVisible(false);
                decission.setText("Sorry! ");
                phoneNoAndName.setText("System could not verify you.");
               
                
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
       
        
    }    
    
    
  @FXML
  private void rerecordAudio()
  {
      try 
        {
                
            FXMLLoader loader2 = new FXMLLoader();        
            loader2.setLocation(DesignTemplate.class.getResource("/testing_phase/OvalPart_test.fxml"));           
            AnchorPane oval= (AnchorPane)loader2.load();                   
            BorderPane border2=DesignTemplate.getRoot();           
            border2.setLeft(oval);           
            OvalPart_testController controller2=loader2.getController();
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
            loader2.setLocation(DesignTemplate.class.getResource("/testing_phase/Td1record.fxml"));           
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
        
        //remove title bar
        //stage.initStyle(StageStyle.UNDECORATED);
        
                
        Scene scene = new Scene(record);
        stage.setScene(scene);
        MicTestingController controller=loader1.getController();
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
            ex.printStackTrace();
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
