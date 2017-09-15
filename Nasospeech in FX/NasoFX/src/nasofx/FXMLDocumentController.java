/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Image;
//import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
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
import javafx.scene.input.MouseEvent;

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
     
     double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
 

@FXML
    void hypernalasityclick(ActionEvent event) {
        Tab tab = new Tab();
            tab.setText("Hypernasality  ");
            TP.getTabs().add(tab);
             TP.getSelectionModel().select(tab);
         

    }
    
    @FXML
    void scorecardclick(ActionEvent event) {
        Tab tab1= new Tab();
            tab1.setText("Scorecard  ");
            TP.getTabs().add(tab1);
            TP.getSelectionModel().select(tab1);

    }
    
    
    @FXML
    void markerpress(MouseEvent event) {
        orgSceneX = event.getSceneX();//event.getX();
            //orgSceneY = event.getY();
            orgTranslateX = ((ImageView)(event.getSource())).getTranslateX();
          //  orgTranslateY = ((ImageView)(event.getSource())).getTranslateY();
    }
    
    
    @FXML
    void markerdrag(MouseEvent event) {
        double offsetX = event.getSceneX()- orgSceneX;
        
           // double offsetY = event.getY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
           // double newTranslateY = orgTranslateY + offsetY;
           //System.out.println("\nThe offset is :"+ newTranslateX);
           
           if(newTranslateX>-579.0 && newTranslateX<610.0){
             
            ((ImageView)(event.getSource())).setTranslateX(newTranslateX);
            //((ImageView)(event.getSource())).setTranslateY(newTranslateY);
           }
    }
    
   
     @FXML
    void slidermouseenter(MouseEvent event) {
       
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
  
   
for (i = 3,a=0; i <1200; i+=50,a+=1)
{
    //double a = 0.1;
    String number = Double.toString(a);
    Text t = new Text(i, 23,number);
        double strokeWidth = t.getStrokeWidth();
        System.out.println("the stroke width is  :  "+strokeWidth);
        t.setStrokeWidth(0.5);
  
        System.out.println("the new  stroke width is  :  "+strokeWidth);
   
    
    
    
    Line line1 = new Line(i, 20, i, 322);
     line1.setStrokeWidth(0.1);
    line1.setStroke(Color.rgb(204, 204, 204));
    
   
    //Line line2 = new Line(0, i, 600, i);
    //line2.setStroke(Color.LIGHTGRAY);

    tab1ap.getChildren().addAll(line1,t);
    
}

        Line redLine = new Line(0, 5, 1300, 5);

    redLine.setStroke(Color.rgb(221, 221, 221));
    redLine.setStrokeWidth(10);
    tab1ap.getChildren().addAll(redLine);
    
marker.toFront();

   
   
    
 
    }    
    
}
