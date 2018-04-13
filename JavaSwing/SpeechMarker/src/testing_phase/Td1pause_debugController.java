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
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javax.swing.SwingUtilities;
import plotwavform.PlotWave;

/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class Td1pause_debugController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    ProgressBar progress2;
    @FXML
    AnchorPane anchorPane;
    @FXML
    ImageView processingDebug;
   
    @FXML
    Label tdPrompt;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        processingDebug.setVisible(false);
        progress2.setVisible(false);
        //random prompt
        if(getPromptNo()==1)
            tdPrompt.setText("'LOVELY PICTURES CAN ONLY BE DRAWN'");
        else if(getPromptNo()==2)
            tdPrompt.setText("'GET INTO THE HOLE OF TUNNELS'");
        //debug window
        
        final SwingNode swingNode = new SwingNode();
        createAndSetSwingContent(swingNode);
        anchorPane.getChildren().add(swingNode);
    }
    
    private void createAndSetSwingContent(final SwingNode swingNode) 
    {
             SwingUtilities.invokeLater(new Runnable() {
                 @Override
                 public void run() {
                     pw=new PlotWave(null);
                     swingNode.setContent(pw);
                     pw.recordSound();
                 }
             });
    }
    
    @FXML
    public void td1Pause()
    {
        try 
        {
            cancelTimer(); 
            stopWavFile();
            getPW().stopRecord();
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
    
    
    }
    
    

       
