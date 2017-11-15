/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import java.io.File;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Naso
 */
public class RecordTask extends Task<ObservableList<XYChart.Data<String,Double>>>{
    XYChart.Data<String,Double> dd; 
    int i;
    XYChart.Series series ;
    @Override
    protected ObservableList<XYChart.Data<String,Double>> call() throws Exception {
       for( i=0;i<5;i++){
      Thread.sleep(1000);
      
       //this.updateProgress(i,5);
       this.updateMessage(String.valueOf(i));
      // this.updateValue(series);
       
       
       }
final ObservableList<XYChart.Data<String,Double>> data = FXCollections.<XYChart.Data<String,Double>>observableArrayList();
        for(int i=0;i<10;i++)
        {
        dd = new XYChart.Data<>(String.valueOf(i),i*1.5);
         data.add(dd);
        // XYChart.Series series = new XYChart.Series(data);
         this.updateValue(data);
       } 
           
       
      
return data;


// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
