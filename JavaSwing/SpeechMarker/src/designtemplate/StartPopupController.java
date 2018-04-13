/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designtemplate;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class StartPopupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    Stage dialogStage ;
    DesignTemplate dp;
    TextField phoneNumber;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void setDP(DesignTemplate dp)
    {
        this.dp=dp;
    }
    
    public void setPhoneNumber(TextField number){
        this.phoneNumber = number;
    }
    
    public void setdialogStage(Stage dialogStage)
    {
        this.dialogStage=dialogStage;
        System.out.print("in the startup controlller");
    }
    
    @FXML
    private void backTorecordTrain() throws IOException
    {
        System.out.print(dialogStage);
            dialogStage.close();
            
            FXMLLoader loader = new FXMLLoader();        
            loader.setLocation(DesignTemplate.class.getResource("OvalPart.fxml"));           
            AnchorPane oval = (AnchorPane) loader.load();                   
            BorderPane border=DesignTemplate.getRoot();           
            border.setLeft(oval);           
            OvalPartController controller1 =(OvalPartController) loader.getController();
            controller1.setDP(dp);
            System.out.println("phpup "+phoneNumber.getText());
            controller1.setPhone(phoneNumber);
            
    }
    
}
