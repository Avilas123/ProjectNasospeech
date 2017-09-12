/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import java.awt.Image;
import java.awt.geom.GeneralPath;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

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
   
for (int i = 0; i <600; i+=10)
{
    Line line1 = new Line(i, 0, i, 600);
    line1.setStroke(Color.LIGHTGRAY);
    Line line2 = new Line(0, i, 600, i);
    line2.setStroke(Color.LIGHTGRAY);
    tab1.addAll(line1, line2);
}


   
   
    
 
    }    
    
}
