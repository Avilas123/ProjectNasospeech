/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nasofx.FXMLDocumentController ;
/**
 *
 * @author IITG
 */
public class NasoFX extends Application {
    
    double array[]={};
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));     
        Scene scene = new Scene(root);
       
        scene.getStylesheets().add("NasoCSS.css");
        
        stage.setScene(scene);
       // stage.setScene(scene1);
       // stage.setResizable(false);
        stage.show();
    }
    
    
    public void startforplotwave(Stage stage,double[] samples,int numsamples,String filename) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        //FXMLDocumentController fxm=new FXMLDocumentController();
        double[] datainplot = getdata(samples);
        //for(int i=0;i<13780;i++)
        //System.out.println(datainplot[i]);
        String substring = filename.substring(32);
       stage.setTitle(substring);

        NumberAxis xAxis = new NumberAxis();
        //xAxis.setLabel("No of employees");

        NumberAxis yAxis = new NumberAxis();
        //yAxis.setLabel("Revenue per employee");

        LineChart lineChart = new LineChart(xAxis, yAxis);
        
        
        
        
        
        
        
        

        XYChart.Series dataSeries1 = new XYChart.Series();
        //dataSeries1.setName("2014");
         
       /* dataSeries1.getData().add(new XYChart.Data( 1, 567));
        dataSeries1.getData().add(new XYChart.Data( 5, 612));
        dataSeries1.getData().add(new XYChart.Data(10, 800));
        dataSeries1.getData().add(new XYChart.Data(20, 780));
        dataSeries1.getData().add(new XYChart.Data(40, 810));
        dataSeries1.getData().add(new XYChart.Data(80, 850));
        */
      //   int size = (int) fxm.audioInputStream.getFrameLength()*2;
        // byte audioData [] = new byte[size];
        
        
      //double samples[]=  fxm.readAudioData(audioData);
      for(int i=0;i<numsamples;i++){
      dataSeries1.getData().add(new XYChart.Data( i, datainplot[i]));
       }
     //wave.getData().add(dataSeries1);

        
        
        
        
        
        lineChart.getData().add(dataSeries1);

        VBox vbox = new VBox(lineChart);

        Scene scene = new Scene(vbox, 400, 200);

        stage.setScene(scene);
        stage.setHeight(400);
        stage.setWidth(1200);
        stage.show();
    
    
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
        
        
        
        
    }

    double[] getdata(double[] samples) {
        System.out.println("i am entering\n");
        this.array=samples;
        // for(int i=0;i<13780;i++)
       //System.out.println(samples[i]);
        return this.array;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
