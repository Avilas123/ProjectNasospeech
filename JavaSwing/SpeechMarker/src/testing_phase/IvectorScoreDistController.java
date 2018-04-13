/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing_phase;

import designtemplate.DesignTemplate;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author chafidul
 */
public class IvectorScoreDistController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public LineChart <Number,Number> GenImpScores;
    ArrayList<Double> Xvalue1=new ArrayList<Double>();
    ArrayList<Double> Yvalue1=new ArrayList<Double>();
    ArrayList<Double> Xvalue2=new ArrayList<Double>();
    ArrayList<Double> Yvalue2=new ArrayList<Double>();
      
    public Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        genDist();
    }   
    
    public void genDist()
    {
        GenImpScores.setTitle("Scores Distribution");
        GenImpScores.getXAxis().setLabel("Scores");
        GenImpScores.getYAxis().setLabel("Frequency");
       // GenImpScores.getYAxis().setTickLabelsVisible(false);
        //GenImpScores.getYAxis().setOpacity(0);
        
        int i=0;
        
        LineChart.Series series=new LineChart.Series();
        LineChart.Series series1=new LineChart.Series();
        LineChart.Series series2=new LineChart.Series();
        LineChart.Series series3=new LineChart.Series();
        
       
         
        series.setName("Genuine Scores");
        series1.setName("Impostor Scores");
        series2.setName("Current I-vector Score");
        series3.setName("Threshold Position");
        
           // Current i-vector position
        series2.getData().add(new LineChart.Data(getIvectorScore(), 0));
        series2.getData().add(new LineChart.Data(getIvectorScore(),1000));
        
         // Threshold position
        series3.getData().add(new LineChart.Data(0.5,0));
        series3.getData().add(new LineChart.Data(0.5,1000));
        
        String filePath="/home/scripts/";
        readFile(filePath+"gen_dist.txt",filePath+"imp_dist.txt");
        
                
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
     
     
     public void setdialogStage(Stage stage)
     {
         this.stage=stage;
         stage.setOnCloseRequest(new EventHandler<WindowEvent>() 
        {
            @Override
            public void handle(WindowEvent event) {
                // consume event
                stage.close();
            }
        });
     }
}
