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
import javafx.scene.Scene;
//import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.control.ProgressIndicator;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Hyperlink;

/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class MainPageController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    Stage dialogStage = new Stage();
    Process p;
    
    DesignTemplate dp;
    @FXML
    TextField txtPhoneno,txtName,txtlName,tstSpkrId,searchTxt;
    @FXML
    Label debugLabel,usrDebugToggle,markAttendance;
    @FXML
    Button searchID;
    @FXML
    ProgressIndicator progressIndicator;
    @FXML
    Hyperlink forgotID;
    
    String match;
    int MaxLength;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
      
        tstSpkrId.setPromptText("Your SPEAKER ID");
       // searchTxt.setVisible(false);
        searchID.setVisible(false);
     //   progressIndicator.setVisible(false);
       MaxLength=10;
       txtPhoneno.textProperty().addListener(new ChangeListener(txtPhoneno,MaxLength));
       tstSpkrId.textProperty().addListener(new ChangeListener(tstSpkrId,MaxLength));
        
        if(usrmode)
        {
            debugLabel.setTextFill(Color.web("#00b4cc"));
            usrDebugToggle.setText("to debug");
           
        }
        else
        {
            debugLabel.setTextFill(Color.web("#0076a3"));
            usrDebugToggle.setText("to user");
           
        }
        //
         vpResult=tdResult=tiResult=false;
         repeat=0;
    }  
    
  
    public void setMainApp(DesignTemplate dp) {
        this.dp = dp;
    }
    
    @FXML
    private void debugMode()
    {
        if(usrmode)
        {
            debugLabel.setTextFill(Color.web("#0076a3"));
            usrmode=false;
            usrDebugToggle.setText("to user");
        }
        else
        {
            debugLabel.setTextFill(Color.web("#00b4cc"));
            usrmode=true;
            usrDebugToggle.setText("to debug");
        }
    }
 
   
   
    @FXML
    private void markAttendance()
    {
      
        try 
                {
                    if(tstSpkrId.getText()==null||tstSpkrId.getText().length()==0||tstSpkrId.getText().length()!=4)
                    {
                     //  Alert alert = new Alert(Alert.AlertType.WARNING);
                      // alert.initOwner(getDP().getPrimaryStage());
                      // alert.setTitle("Empty Field"); 
                      // alert.setContentText("Enter your 4-digit ID");
                      // alert.showAndWait();
                    }
                    else
                    {
                        testID=tstSpkrId.getText();
                        
                        if(isValidSpkrId())
                        {
                            System.out.println("Test speaker ID "+testID);
                            FXMLLoader loader = new FXMLLoader();        
                            loader.setLocation(DesignTemplate.class.getResource("/testing_phase/OvalPart_test.fxml"));           
                            AnchorPane oval = (AnchorPane) loader.load();                   
                            BorderPane border=DesignTemplate.getRoot();           
                            border.setLeft(oval);
                        }
                        else
                        {
                            //Alert alert = new Alert(Alert.AlertType.WARNING);
                            //alert.initOwner(getDP().getPrimaryStage());
                           // alert.setTitle("Wrong speaker ID"); 
                           //alert.setContentText("Enter your 4-digit ID");
                           // alert.showAndWait();
                        }
                        
                        
                    }
                }
            
            catch(Exception e)
            {
                e.printStackTrace();
            }
        
    }
    
    public boolean isValidSpkrId()
    {
        boolean status=false;
        try {
            p=Runtime.getRuntime().exec("python /home/scripts/validSpkrId.py "+getTestSpkrId());
            p.waitFor();
            BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
            String register;
            while((register=br.readLine())!=null)
            {
                if(register.equals("found"))
                {
                    System.out.println(register);
                   status=true;
                }
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
        
    }
    
    @FXML
    private void Register() throws IOException
    {
      //  try 
       // {
        userNumber = txtPhoneno.getText();
        System.out.println(userNumber);
            if(txtPhoneno.getText()==null||txtName.getText()==null||txtlName.getText()==null||txtPhoneno.getText().length()==0||txtName.getText().length()==0||txtlName.getText().length()==0)
            {
                //Alert alert = new Alert(Alert.AlertType.WARNING);
               // alert.initOwner(getDP().getPrimaryStage());
              //  alert.setTitle("Empty Fields");
               // alert.setHeaderText("No field filled");
               // alert.setContentText("Please enter your details.");
               // alert.showAndWait();
            }
            else if(txtPhoneno.getText().length()!=10)
            {
               // Alert alert = new Alert(Alert.AlertType.WARNING);
              //  alert.initOwner(getDP().getPrimaryStage());
               // alert.setTitle("Wrong number");
               // alert.setHeaderText("No field filled");
              //  alert.setContentText("Please enter 10-digits mobile no.");
              //  alert.showAndWait();
            }
            else
            {
                String phoneNumber=txtPhoneno.getText();
                if(isNumeric(phoneNumber)==false||isRegistered())
                {
                   // Alert alert = new Alert(Alert.AlertType.WARNING);
                   // alert.initOwner(getDP().getPrimaryStage());
                   // alert.setTitle("Wrong number");
               // alert.setHeaderText("No field filled");
                   // alert.setContentText("Please enter valid mobile no.");
                   // alert.showAndWait();
                }
                else
                {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(DesignTemplate.class.getResource("StartPopup.fxml"));
                    AnchorPane page = (AnchorPane) loader.load();
                 
                    System.out.println(phoneNumber);
                 // Create the dialog Stage.
                    Stage dialogStage = new Stage();
                // set dialogStage
                    DesignTemplate.dialogStage=dialogStage;
                    dialogStage.setTitle("Register");
                    dialogStage.initModality(Modality.APPLICATION_MODAL);
            //dialogStage.initOwner(dp.getPrimaryStage());
                    dialogStage.initOwner(getDP().getPrimaryStage());
                    Scene scene = new Scene(page);
                    dialogStage.setScene(scene);
           
            /// set the userNumber variable
                    userNumber = txtPhoneno.getText();
                    fName=txtName.getText();
                    lName=txtlName.getText();
                    
                 
                    StartPopupController controller=loader.getController();
                    controller.setdialogStage(dialogStage);
                    controller.setDP(dp);
                    controller.setPhoneNumber(txtPhoneno);
                    dialogStage.showAndWait();
                }
            }
    }
    
    
    public static boolean isNumeric(String number)
    {
       // System.out.println(number);
        return number.matches("^\\d+$");
    }
    
    public boolean isRegistered()
    {
        boolean status = false;
        try {
            Process p=Runtime.getRuntime().exec("python /home/scripts/IsRegistered.py "+getUserNumber());
            p.waitFor();
            BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
            String register;
            while((register=br.readLine())!=null)
            {
                if(register.equals("found"))
                {
                    status=true;
                       System.out.println("inside while loop");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return status;
    }
    
    @FXML
    public Stage getdialogStage()
    {
        
        return dialogStage;
    }
    
    @FXML
    public void searchSpkrId()
    {
      //  searchTxt.setVisible(true);
      //  searchTxt.setPromptText("Enter your mobile no.");
        searchID.setVisible(true);
        tstSpkrId.setPromptText("Enter your mobile no.");
        forgotID.setVisible(false);
        markAttendance.setStyle("-fx-background-color: #9f9f9f;");
        markAttendance.setDisable(true);
        
    }
    
    @FXML
    public void searchButton() throws IOException, InterruptedException
    {
      if(tstSpkrId.getText()==null||tstSpkrId.getText().length()==0||tstSpkrId.getText().length()!=10)
      {
          //Alert alert=new Alert(Alert.AlertType.ERROR);
          //alert.initOwner(getDP().getPrimaryStage());
          //alert.setTitle("Mobile Number");
         // alert.setContentText("Enter your mobile no");
         // alert.showAndWait();
      }
      else
      {
          String phoneNumber=tstSpkrId.getText();
          p=Runtime.getRuntime().exec("python /home/scripts/searchSpeakerID.py "+" "+phoneNumber);
          p.waitFor();
          
          BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
          String str,str1 = null;
          while((str=br.readLine())!=null)
          {
              System.out.println(str);
              str1=str;
          }
          System.out.println(str1);
          System.out.println(str);
          
          if(str1==null)
          {
             // tstSpkrId.setStyle("-fx-font-size:13;");
              tstSpkrId.setText("Sorry! Could not find your ID");
          }
          
          else if(str1.length()==4)
          {
              tstSpkrId.setText(str1);
              searchID.setVisible(false);
              forgotID.setVisible(true);
              markAttendance.setStyle("-fx-background-color: #fc5d00;");
              markAttendance.setDisable(false);
              
          }
         
              
              
      }
    }
}
 /*
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
             alert.showAndWait();
            System.out.println("clicking register button");
            
            DesignTemplate.txtName=((TextField)DesignTemplate.getRoot().lookup("#txtName")).getText();
            DesignTemplate.txtlName=((TextField)DesignTemplate.getRoot().lookup("#txtlName")).getText();
            DesignTemplate.txtPhoneno=((TextField)DesignTemplate.getRoot().lookup("#txtPhoneno")).getText();
            System.out.print(DesignTemplate.txtName);
            FXMLLoader loader = new FXMLLoader();        
            loader.setLocation(DesignTemplate.class.getResource("OvalPart.fxml"));           
            AnchorPane oval = (AnchorPane) loader.load();                   
            BorderPane border=DesignTemplate.getRoot();           
            border.setLeft(oval);           
            OvalPartController controller1 =(OvalPartController) loader.getController();*/
            //((Label)DesignTemplate.getRoot().lookup("#vptimer")).setText("pallavi");
            
        /*    
         }
        catch(IOException e)
        {
            e.printStackTrace();
        }*/
        
