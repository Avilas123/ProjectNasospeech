/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designtemplate;

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
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class TiconfirmedController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
    Stage dialogStage;
    String tiTmpPath="/home/training_module/Text-independent/tmp/";
    Process p;
    
    @FXML
    ImageView processing;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        processing.setVisible(false);
    }  
    
    
    public void setDialog(Stage dialogStage)
    {
        this.dialogStage=dialogStage;
    }
    
    
    
    
    @FXML
    public void txtRerecord()
    {
        try 
        {
                
            FXMLLoader loader2 = new FXMLLoader();        
            loader2.setLocation(DesignTemplate.class.getResource("Tirecord.fxml"));           
            AnchorPane renext= (AnchorPane)loader2.load();                   
            BorderPane border2=DesignTemplate.getRoot();           
            border2.setLeft(renext); 
            TirecordController control=loader2.getController();
         }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void txtNext() throws InterruptedException
    {
        try 
        {
             repeat++;
             System.out.println("Repeat"+repeat);
            if(getrepeatLoop()==3)
            {
               /////  generate the ID for the current session and then display it
               //userID.setText(generateUserID());
                //processing.setVisible(true);
                
                p=Runtime.getRuntime().exec("python /home/scripts/joinWav_extract_ivect_demo.py "+" "+userNumber);
                p.waitFor();
                id = generateUserID();
                ///////Writing the user details to the enroll user list
               writeToEnrollList(id);
               System.out.println("id = "+id);
               FXMLLoader loader2 = new FXMLLoader();        
               loader2.setLocation(DesignTemplate.class.getResource("Speakerid.fxml"));           
               AnchorPane renext= (AnchorPane)loader2.load();                   
               BorderPane border2=DesignTemplate.getRoot();           
               border2.setLeft(renext); 
               SpeakeridController control=loader2.getController(); 
            }
            else
            {
                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(DesignTemplate.class.getResource("OvalPart.fxml"));
                AnchorPane renext= (AnchorPane)loader2.load();
                BorderPane border2=DesignTemplate.getRoot();
                border2.setLeft(renext);
                OvalPartController control=loader2.getController();
                //control.getDP();
            }
                
            
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
    public void loadTD()
    {
        try 
        {
             
            FXMLLoader loader = new FXMLLoader();        
            loader.setLocation(DesignTemplate.class.getResource("Td1record.fxml"));           
            AnchorPane oval = (AnchorPane) loader.load();                   
            BorderPane border=DesignTemplate.getRoot();           
            border.setLeft(oval);           
            Td1recordController controller1 =(Td1recordController) loader.getController();
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
      playAudio(tiTmpPath+userNumber+ "_TI_" + getMiliSec() +  ".wav");
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
    
    public String generateUserID() throws IOException
    {
        
        String idFileName ="/home/scripts/id.txt";
        String id=null;
        int nextID=0;
        try {
            FileReader fileReader = new FileReader(idFileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            id = bufferedReader.readLine();
            bufferedReader.close();
            
            /// increment the id
            nextID=Integer.parseInt(id) +1;
            System.out.println(nextID);
            //// write this id to the file
            FileWriter fileWriter = new FileWriter(idFileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(nextID));
            bufferedWriter.close();
           
            
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(TiconfirmedController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
         return Integer.toString(nextID);
    }
    private void writeToEnrollList(String id) throws IOException
    {
        String fileName="/home/scripts/enrolledUserList.txt";
        FileWriter fileWriter = new FileWriter(fileName,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.append(getFName() + " " + getLName() + " " + getUserNumber() + " " + id +"\n");
        bufferedWriter.close();
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
            loader1.setLocation(DesignTemplate.class.getResource("TINeedHelp.fxml"));
            AnchorPane record= (AnchorPane)loader1.load();
            Platform.setImplicitExit(false);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("TI help");
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
}
