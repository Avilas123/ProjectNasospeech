/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing_phase;

import designtemplate.DesignTemplate;
import static designtemplate.DesignTemplate.usrmode;
import designtemplate.MainPageController;
import designtemplate.TDNeedHelpController;
import designtemplate.TINeedHelpController;
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
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nazibur
 */

public class UserLastPageController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    Label vpPass,tiPass;
    @FXML
    Label tdPass;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        System.out.println("getVpResult "+getVpResult()+" "+getTdResult()+" "+getTiResult());
        if(getVpResult())
        {
            vpPass.setText(" Passed in Voice-password");
        }
        else
        {
            vpPass.setText(" Failed in Voice-password");
        }
        
        if(getTdResult())
        {
            tdPass.setText(" Passed in Text-dependent");
        }
        else
        {
            tdPass.setText(" Failed in Text-dependent");
        }
        
        if(getTiResult())
        {
            tiPass.setText(" Passed in Text-independent");
        }
        else
        {
            tiPass.setText(" Failed in Text-independent");
        }
        
    }  
    
    @FXML
    private void ExitFrmTstUsrMode()
    {
        try 
        {
            
            repeat=0;
            FXMLLoader loader = new FXMLLoader();        
            loader.setLocation(DesignTemplate.class.getResource("MainPage.fxml"));           
            AnchorPane oval = (AnchorPane) loader.load();                   
            BorderPane border=DesignTemplate.getRoot();           
            border.setLeft(oval);           
            MainPageController controller1 =(MainPageController) loader.getController();
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
            loader1.setLocation(DesignTemplate.class.getResource("TINeedHelp.fxml"));
            AnchorPane record= (AnchorPane)loader1.load();
            Platform.setImplicitExit(false);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Help");
            stage.initOwner(getDP().getPrimaryStage());
            Scene scene = new Scene(record);
            stage.setScene(scene);
            TINeedHelpController controller=loader1.getController();
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
    
    
}
