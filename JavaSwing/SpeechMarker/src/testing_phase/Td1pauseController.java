/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing_phase;

import designtemplate.DesignTemplate;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class Td1pauseController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label tdPrompt;
    @FXML
    ImageView processing;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        processing.setVisible(false);
        if(getPromptNo()==1)
            tdPrompt.setText("'LOVELY PICTURES CAN ONLY BE DRAWN'");
        else if(getPromptNo()==2)
            tdPrompt.setText("'GET INTO THE HOLE OF TUNNELS'");
        
    }
    
    @FXML
    public void td1Pause()
    {
        try 
        {
            cancelTimer(); 
            stopWavFile();
            FXMLLoader loader2 = new FXMLLoader();        
            loader2.setLocation(DesignTemplate.class.getResource("/testing_phase/Td1record.fxml"));           
            AnchorPane renext= (AnchorPane)loader2.load();                   
            BorderPane border2=DesignTemplate.getRoot();           
            border2.setLeft(renext);           
           
            Td1recordController control=loader2.getController();
           // ((Label)DesignTemplate.getRoot().lookup("#complete2")).setText("Recording on hold");
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
            loader.setLocation(DesignTemplate.class.getResource("/testing_phase/OvalPart.fxml"));           
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
    
    
    }
    
    

       
