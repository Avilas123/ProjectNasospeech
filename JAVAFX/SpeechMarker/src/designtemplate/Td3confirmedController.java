/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designtemplate;

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
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class Td3confirmedController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
    public String[] arryStr=new String [10];
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    public void td3Next()
    {
        try 
        {
            
           // if(repeatLoop()==3)
           // {
                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(DesignTemplate.class.getResource("Tirecord.fxml"));
                AnchorPane renext= (AnchorPane)loader2.load();
                BorderPane border2=DesignTemplate.getRoot();
                border2.setLeft(renext);
                TirecordController control=loader2.getController();
                System.out.println(arryStr[0]);
                if(getrepeatLoop()==1)
                 ((TextArea)DesignTemplate.getRoot().lookup("#textArea")).setText("After some time, he met Dharmabuddhi, \"My friend, we need to earn money to provide for ourselves when we grow old. Let us travel to some other kingdom to earn money. Besides, unless we travel to far-off kingdoms, we will not have any stories to tell our grand children! .\"Dharmabuddhi agreed to his plan, and took the blessings of his parents and teachers to travel to a distant kingdom.On an auspicious day, they began their journey.");
                if(getrepeatLoop()==2)
                    ((TextArea)DesignTemplate.getRoot().lookup("#textArea")).setText("The two of them made a lot of money due to Dharmabuddhi's skills and knowledge. After some time, pleased with their earnings, they planned to return home. On their way to their home, as they approached their village, Papabuddhi said to Dharmabuddhi, \"Friend, It will be improper for us to carry so much money home as all our friends and relatives will start requesting us money. Besides, there is danger of theft.");
        
         }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void td3Rerecord()
    {
        try 
        {
                
            FXMLLoader loader2 = new FXMLLoader();        
            loader2.setLocation(DesignTemplate.class.getResource("Td3record.fxml"));           
            AnchorPane renext= (AnchorPane)loader2.load();                   
            BorderPane border2=DesignTemplate.getRoot();           
            border2.setLeft(renext);           
           
            Td3recordController control=loader2.getController();
         }
        catch(IOException e)
        {
            e.printStackTrace();
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
  public void playRecorded()
  {
      int iter=getrepeatLoop()+1;
   //   System.out.println("Inside Play recorded");
      playAudio("/home/training_module/Text-dependent/"+userNumber+"/"+userNumber+"_TD3_" + iter +  ".wav");
  }
  
     @FXML
    public void micCheck() throws IOException
    {
        
        FXMLLoader loader1 = new FXMLLoader();        
        loader1.setLocation(DesignTemplate.class.getResource("MicTesting.fxml"));           
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
