/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing_phase;

import designtemplate.DesignTemplate;
import static designtemplate.DesignTemplate.getPW;
import static designtemplate.DesignTemplate.miliSec;
import static designtemplate.DesignTemplate.pw;
import static designtemplate.DesignTemplate.testID;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.scene.control.Label;
import javax.swing.SwingUtilities;
import plotwavform.PlotWave;
import java.lang.Math;
import java.util.ArrayList;
import javafx.scene.chart.LineChart;

/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class Ticonfirmed_debugController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public LineChart <Number,Number> GenImpScores;
    ArrayList<Double> Xvalue1=new ArrayList<Double>();
    ArrayList<Double> Yvalue1=new ArrayList<Double>();
    ArrayList<Double> Xvalue2=new ArrayList<Double>();
    ArrayList<Double> Yvalue2=new ArrayList<Double>();
    
    
    
    Stage dialogStage;
    String ti;
   
     @FXML
     AnchorPane anchorPane;
     
     Process p;
     double result=0.0;
     
    @FXML
    Label ivect_score;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         getPW().stopRecord();
         ti=getTestSpkrId()+"_"+getMSec()+"_TI.wav";
        final SwingNode swingNode = new SwingNode();
        createAndSetSwingContent(swingNode);
        anchorPane.getChildren().add(swingNode);
        
        //ivector distribution
        genDist();
        
        //ivector score
        ivect_score.setText(Double.toString(getIvectScore()));
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
                     File fileName=new File("/home/testing_module/Text-independent/"+testID+"/"+ti);
                     pw.createAudioInputStream(fileName, null, true);
                     System.out.println("File Name"+fileName);
                 }
             });
    }  
    
    
   private double getIvectScore()
    {
        try
        {
            p=Runtime.getRuntime().exec("cat /home/scripts/score.txt");
            p.waitFor();
            BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str;
            while((str=br.readLine())!=null)
            {
                result=Double.parseDouble(str);
                result=Math.round(result*100);
                result=result/100;
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        ivectorScore=result;
        
        return result;
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
            loader2.setLocation(DesignTemplate.class.getResource("/testing_phase/Tirecord.fxml"));           
            AnchorPane renext= (AnchorPane)loader2.load();                   
            BorderPane border2=DesignTemplate.getRoot();           
            border2.setLeft(renext); 
           // TirecordController control=loader2.getController();
         }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void txtNext()
    {
        try 
        {
            
               FXMLLoader loader2 = new FXMLLoader();        
               loader2.setLocation(DesignTemplate.class.getResource("/testing_phase/UserLastPage.fxml"));           
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
    public void loadTD()
    {
        try 
        {
             
            FXMLLoader loader = new FXMLLoader();        
            loader.setLocation(DesignTemplate.class.getResource("/testing_phase/Td1record.fxml"));           
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
    public void playRecorded()
    {
        int iter=getrepeatLoop()+1;
   //   System.out.println("Inside Play recorded");
        getPW().playSoundAll();
        //playAudio("/home/nazibur/training_module/Text-independent/"+testID+"/"+testID +"_TI.wav");
    }
    
    
    public void genDist()
    {
        GenImpScores.setTitle("Scores Distribution");
        GenImpScores.getXAxis().setLabel("Scores");
        GenImpScores.getYAxis().setLabel("Frequency");
        //GenImpScores.getYAxis().setTickLabelsVisible(false);
       // GenImpScores.getYAxis().setOpacity(0);
        
        int i=0;
        
        LineChart.Series series=new LineChart.Series();
        LineChart.Series series1=new LineChart.Series();
        LineChart.Series series2=new LineChart.Series();
        LineChart.Series series3=new LineChart.Series();
        series.setName("Genuine Scores");
        series1.setName("Impostor Scores");
        series2.setName("Current I-vector Score");
        series3.setName("Threshold Position");
        
        String filePath="/home/scripts/";
        readFile(filePath+"gen_dist.txt",filePath+"imp_dist.txt");
        
        // Current i-vector position
        series2.getData().add(new LineChart.Data(getIvectScore(), 0));
        series2.getData().add(new LineChart.Data(getIvectScore(),1000));
        
        // Threshold position
        series3.getData().add(new LineChart.Data(0.48,0));
        series3.getData().add(new LineChart.Data(0.48,1000));
        
        for(i=0;i<Xvalue1.size();i++)
        {
            
            LineChart.Data data2 = new LineChart.Data(Xvalue1.get(i),Yvalue1.get(i));
            series.getData().add(data2);
        }
        
        for(i=0;i<Xvalue2.size();i++)
        {
            
            LineChart.Data data3 = new LineChart.Data(Xvalue2.get(i),Yvalue2.get(i));
            series1.getData().add(data3);
        }
        
        GenImpScores.setCreateSymbols(false);
        GenImpScores.getData().addAll(series,series1,series2,series3);
    }
    
    
     public void readFile(String arg1,String arg2)
    {
        InputStream in1 = null;
        InputStream in2 = null;
        
        BufferedReader br1;
        BufferedReader br2;
        
        
        try {
            in1 = new FileInputStream(new File(arg1));
            in2 = new FileInputStream(new File(arg2));
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         br1=new BufferedReader(new InputStreamReader(in1));
         br2=new BufferedReader(new InputStreamReader(in2));
        
        int i=0;
        String out;
        try {
            while((out=br1.readLine())!=null)
            {
                String [] temp=out.split(" ");
                
                Xvalue1.add(Double.parseDouble(temp[0]));
                Yvalue1.add(Double.parseDouble(temp[1]));
                
            }
            while((out=br2.readLine())!=null)
            {
                String [] temp=out.split(" ");
                
                Xvalue2.add(Double.parseDouble(temp[0]));
                Yvalue2.add(Double.parseDouble(temp[1]));
                
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void popUpScoreDist()
    {
         try {
             FXMLLoader loader1 = new FXMLLoader();
             loader1.setLocation(DesignTemplate.class.getResource("/testing_phase/IvectorScoreDist.fxml"));
             AnchorPane record= (AnchorPane)loader1.load();
             Platform.setImplicitExit(false);
             Stage stage=new Stage();
             stage.initModality(Modality.APPLICATION_MODAL);
             stage.setTitle("Genuine Impostor Score Distribution");
             stage.initOwner(getDP().getPrimaryStage());
             
             //remove title bar
             //stage.initStyle(StageStyle.UNDECORATED);
             
             
             Scene scene = new Scene(record);
             stage.setScene(scene);
             IvectorScoreDistController controller=loader1.getController();
             controller.setdialogStage(stage);
             // stage.onCloseRequestProperty();
             stage.showAndWait();
         } catch (IOException ex) {
             Logger.getLogger(Rerecord_debugController.class.getName()).log(Level.SEVERE, null, ex);
         }
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
            Logger.getLogger(Ticonfirmed_debugController.class.getName()).log(Level.SEVERE, null, ex);
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
}
