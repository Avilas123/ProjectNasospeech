/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author IITG
 */
public class FXMLDocumentController implements Initializable {
    
       @FXML
    private Slider slider;
        @FXML
    private Tab tab1;
           @FXML
    private TabPane TP;
 
    @FXML
    private AnchorPane tab1ap;
    @FXML
    private MenuItem hypernasality;
    @FXML
    private MenuItem scorecard;
    
     @FXML
    private ImageView marker;
 

@FXML
    void hypernalasityclick(ActionEvent event) {
        Tab tab = new Tab();
            tab.setText("Hypernasality");
            TP.getTabs().add(tab);
             TP.getSelectionModel().select(tab);
         

    }
    
    @FXML
    void scorecardclick(ActionEvent event) {
        Tab tab1= new Tab();
            tab1.setText("Scorecard");
            TP.getTabs().add(tab1);
            TP.getSelectionModel().select(tab1);

    }
    
    


     
   


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    slider.setMin(0);
    slider.setMax(100);
    slider.setValue(40);
    //slider.setShowTickLabels(true);
    slider.setShowTickMarks(true);
    slider.setMajorTickUnit(50);
    slider.setMinorTickCount(5);
    slider.setBlockIncrement(10);
    
   TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
   double a = 0.1;
   int i=0;
  
   
for (i = 0,a=0; i <1200; i+=50,a+=1)
{
    //double a = 0.1;
    String number = Double.toString(a);
    Text t = new Text(i, 10,number);
        double strokeWidth = t.getStrokeWidth();
        System.out.println("the stroke width is  :  "+strokeWidth);
        t.setStrokeWidth(0.5);
  
        System.out.println("the new  stroke width is  :  "+strokeWidth);
   
    
    
    
    Line line1 = new Line(i, 15, i, 322);
     line1.setStrokeWidth(0.1);
    line1.setStroke(Color.BLACK);
    
   
    //Line line2 = new Line(0, i, 600, i);
    //line2.setStroke(Color.LIGHTGRAY);

    tab1ap.getChildren().addAll(line1,t);
    
}

        //Line redLine = new Line(422, 20, 422, 322);

   // redLine.setStroke(Color.RED);
   // redLine.setStrokeWidth(1);
   // tab1ap.getChildren().addAll(redLine);
    
marker.toFront();

   
   
    
 
    }    
    
}
