package testing_phase;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import designtemplate.DesignTemplate;
import static designtemplate.DesignTemplate.usrmode;
import designtemplate.VPNeedHelpController;
import designtemplate.micDebugModeController;
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

/**
 * FXML Controller class
 *
 * @author nazibur
 */

public class CheckFrameController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
  @FXML
  private void rerecordAudio()
  {
      String loadFile;
      if(Mode.equals("VP"))
      {
         loadFile= "/testing_phase/OvalPart_test.fxml";
      }
      else if(Mode.equals("TD"))
      {
         loadFile= "/testing_phase/Td1record.fxml"; 
      }
      else
      {
          loadFile= "/testing_phase/Tirecord.fxml"; 
      }
      try 
        {
                
            FXMLLoader loader2 = new FXMLLoader();        
            loader2.setLocation(DesignTemplate.class.getResource(loadFile));           
            AnchorPane oval= (AnchorPane)loader2.load();                   
            BorderPane border2=DesignTemplate.getRoot();           
            border2.setLeft(oval);           
//            OvalPart_testController controller2=loader2.getController();
         }
        catch(IOException e)
        {
            e.printStackTrace();
        }
  }
 
  
    
}
