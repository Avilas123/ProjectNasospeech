/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing_phase;

import designtemplate.DesignTemplate;
import static designtemplate.DesignTemplate.getMSec;
import static designtemplate.DesignTemplate.testID;
import designtemplate.TDNeedHelpController;
import designtemplate.micDebugModeController;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;
import plotwavform.PlotWave;

/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class Td1confirmed_debugController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
    public LineChart <Number,Number> WP_id;
    
     // ArrayList<Integer> Xvalue=new ArrayList<Integer>();
      //ArrayList<Integer> Yvalue=new ArrayList<Integer>();
     
      ArrayList<Integer> Xvalue1=new ArrayList<>();
      ArrayList<Integer> Yvalue1=new ArrayList<>();
      ArrayList<Integer> Xvalue2=new ArrayList<>();
      ArrayList<Integer> Yvalue2=new ArrayList<>();
      ArrayList<Integer> Xvalue3=new ArrayList<>();
      ArrayList<Integer> Yvalue3=new ArrayList<>();
      
    @FXML
    AnchorPane anchorPane;
    @FXML
    Label tdPrompt;
    
    String td;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getPW().stopRecord();
        
        //random prompt
        if(getPromptNo()==1)
            tdPrompt.setText("'LOVELY PICTURES CAN ONLY BE DRAWN'");
        else if(getPromptNo()==2)
            tdPrompt.setText("'GET INTO THE HOLE OF TUNNELS'");
        
        td=getTestSpkrId()+"_"+getMSec()+"_TD"+(getPromptNo()+1)+".wav";
        
        //warping path
        genWP();
        //debug window
        final SwingNode swingNode = new SwingNode();
        createAndSetSwingContent(swingNode);
        anchorPane.getChildren().add(swingNode);
    } 
    
    private void createAndSetSwingContent(final SwingNode swingNode) 
    {
             SwingUtilities.invokeLater(new Runnable() {
                 @Override
                 public void run() 
                 {
                     int iter = getrepeatLoop() + 1;
                     pw=new PlotWave(null);
                     swingNode.setContent(pw);
                      File fileName=new File("/home/testing_module/Text-dependent/"+testID+"/"+td);
                     pw.createAudioInputStream(fileName, null, true);
                     
                 }
             });
    }
    
    @FXML
    public void td1Next()
    {
        try 
        {
                
            FXMLLoader loader2 = new FXMLLoader();        
            loader2.setLocation(DesignTemplate.class.getResource("/testing_phase/Tirecord.fxml"));           
            AnchorPane renext= (AnchorPane)loader2.load();                   
            BorderPane border2=DesignTemplate.getRoot();           
            border2.setLeft(renext);           
           
         }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void td1Rerecord()
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
    public void popUpWP()
    {
         try {
             FXMLLoader loader1 = new FXMLLoader();
             loader1.setLocation(DesignTemplate.class.getResource("/testing_phase/PopUpWP.fxml"));
             AnchorPane record= (AnchorPane)loader1.load();
             Platform.setImplicitExit(false);
             Stage stage=new Stage();
             stage.initModality(Modality.APPLICATION_MODAL);
             stage.setTitle("Warping path");
             stage.initOwner(getDP().getPrimaryStage());
             
             //remove title bar
             //stage.initStyle(StageStyle.UNDECORATED);
             
             
             Scene scene = new Scene(record);
             stage.setScene(scene);
             PopUpWPController controller=loader1.getController();
             controller.setdialogStage(stage);
             // stage.onCloseRequestProperty();
             stage.showAndWait();
         } catch (IOException ex) {
             Logger.getLogger(Rerecord_debugController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    @FXML
    public void micCheck() throws IOException
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
    
     @FXML
    public void playRecorded()
    {
        int iter=getrepeatLoop()+1;
   //   System.out.println("Inside Play recorded");
        getPW().playSoundAll();
        //playAudio("/home/training_module/Text-dependent/"+testID+"/"+testID+ "_TD1_" + iter +  ".wav");
    }
    
    public void genWP()
    {   
       
        
        WP_id.setTitle("Warping Path");
        WP_id.getXAxis().setLabel("Train Frames");
        WP_id.getYAxis().setLabel("Test Frames");
        
        // Read values from input file
        String filePath="/home/scripts/";
        readFile(filePath+"outfile1",filePath+"outfile2",filePath+"outfile3");
        
      
        //plot warping path
        int i=0;
       
         LineChart.Series series2 = new LineChart.Series();
         series2.setName("WP1");
         LineChart.Series series3 = new LineChart.Series();
         series3.setName("WP2");
         LineChart.Series series4 = new LineChart.Series();
         series4.setName("WP3");
        
        for(i=0;i<Xvalue1.size();i++)
        {
            
            LineChart.Data data2 = new LineChart.Data(Xvalue1.get(i),Yvalue1.get(i));
            series2.getData().add(data2);
        }
        
        for(i=0;i<Xvalue2.size();i++)
        {
            
            LineChart.Data data3 = new LineChart.Data(Xvalue2.get(i),Yvalue2.get(i));
            series3.getData().add(data3);
        }
        
        for(i=0;i<Xvalue3.size();i++)
        {
            
            LineChart.Data data4 = new LineChart.Data(Xvalue3.get(i),Yvalue3.get(i));
            series4.getData().add(data4);
        }
        
        
       // WP_id.getData().add(series2);
        
        WP_id.setCreateSymbols(false);
        WP_id.getData().addAll(series2,series3,series4);
        
        
         
    }
    public void readFile(String arg1,String arg2,String arg3)
    {
        InputStream in1 = null;
        InputStream in2 = null;
        InputStream in3 = null;
        BufferedReader br1;
        BufferedReader br2;
        BufferedReader br3;
        
        try {
            in1 = new FileInputStream(new File(arg1));
            in2 = new FileInputStream(new File(arg2));
            in3 = new FileInputStream(new File(arg3));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         br1=new BufferedReader(new InputStreamReader(in1));
         br2=new BufferedReader(new InputStreamReader(in2));
         br3=new BufferedReader(new InputStreamReader(in3));
        int i=0;
        String out;
        try {
            while((out=br1.readLine())!=null)
            {
                String [] temp=out.split(" ");
                
                Xvalue1.add(Integer.parseInt(temp[0]));
                Yvalue1.add(Integer.parseInt(temp[1]));
                
            }
            while((out=br2.readLine())!=null)
            {
                String [] temp=out.split(" ");
                
                Xvalue2.add(Integer.parseInt(temp[0]));
                Yvalue2.add(Integer.parseInt(temp[1]));
                
            }
            while((out=br3.readLine())!=null)
            {
                String [] temp=out.split(" ");
                
                Xvalue3.add(Integer.parseInt(temp[0]));
                Yvalue3.add(Integer.parseInt(temp[1]));
                
            }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
