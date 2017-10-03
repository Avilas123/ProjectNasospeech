/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import nasofx.FXMLDocumentController ;
//import static nasofx.FXMLDocumentController.valuefromc;
//import static nasofx.FXMLDocumentController.valuefromc;
/**
 *
 * @author IITG
 */
public class NasoFX extends Application {

    /**
     *
     */
   
    
     NumberAxis xAxis = new NumberAxis();
      NumberAxis yAxis = new NumberAxis();
      LineChart lineChart = new LineChart(xAxis, yAxis);
  //  FXMLDocumentController fxmlobject =new FXMLDocumentController();
    double array[]={};
     @FXML 
  static double  valuefromc;
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));     
        Scene scene;
        scene = new Scene(root);
      scene.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());
       // scene.getStylesheets().add("test.css");
        
        stage.setScene(scene);
     //   stage.setHeight(1200);
      //  stage.setWidth(1200);
       // stage.setScene(scene1);
       // stage.setResizable(faßlse);
        stage.show();
    }
    
    
    public void startforplotwave(Stage stage,double[] samples,int numsamples,String filename,Tab tab1 ,TabPane TP,StackPane wavepane) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        //FXMLDocumentController fxm=new FXMLDocumentController();
      //  double[] datainplot = getdata(samples);
        //for(int i=0;i<13780;i++)
        //System.out.println(datainplot[i]);
        String substring = filename.substring(32);
       stage.setTitle(substring);

      
        xAxis.setVisible(true);
       // xAxis.setLabel("No of employees");

      
        yAxis.setVisible(true);
        //yAxis.setLabel("Revenue per employee");

        
        
        //lineChart.setStyle("-fx-background-color: rgba(0,168,355,0.05);");
        
        
        
        
        
        

        XYChart.Series<Integer,Double> dataSeries1 = new XYChart.Series<>();
        XYChart.Data<Integer,Double> data = new XYChart.Data<>();
     //   XYChart.Data data = new XYChart.Data<>();
//Rectangle rect = new Rectangle(0, 0);
//rect.setVisible(false);
//data.setNode(rect);
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
      
          data = new XYChart.Data<Integer,Double>( i, samples[i]);
          dataSeries1.getData().add(data);
       }
     //wave.getData().add(dataSeries1);

        
        
        
        
      //  lineChart.setStyle(".default-color0.chart-series-line { -fx-stroke: #f0e68c; }");
        lineChart.getData().add(dataSeries1);
        
        
       // dataSeries1.setNode(null);
       // double height = lineChart.getHeight();
        //System.out.println("linechartheight\t"+height);
        //double width = lineChart.getWidth();System.out.println("linewidth\t"+width);
       boolean verticalGridLinesVisible = lineChart.getVerticalGridLinesVisible();
      verticalGridLinesVisible=true;
      boolean horizontalGridLinesVisible = lineChart.isHorizontalGridLinesVisible();
      horizontalGridLinesVisible=true;
        

        
      lineChart.setCreateSymbols(false);
      lineChart.getYAxis().setTickLabelsVisible(true);
       lineChart.getYAxis().setOpacity(0.5);
       lineChart.getXAxis().setTickLabelsVisible(true);
      lineChart.getXAxis().setOpacity(0.5);
        
       // lineChart.setStyle(".chart-series-line { -fx-stroke: green; -fx-stroke-width: 4px;}");
       // lineChart.getStylesheets().add("test.css");

       // VBox vbox = new VBox(lineChart);
      //  vbox.setPrefSize(200, 200);
       
        //Scene scene = new Scene(vbox, 400, 200);

       // stage.setScene(scene);
       // stage.setHeight(400);
       // stage.setWidth(1200);
      //  stage.show();
     /* final StackPane chartContainer = new StackPane();
		chartContainer.getChildren().add(lineChart);
                chartContainer.maxHeight(1175);
                chartContainer.maxWidth(390);
                
		final Rectangle zoomRect = new Rectangle();
		zoomRect.setManaged(false);
		zoomRect.setFill(Color.LIGHTSEAGREEN.deriveColor(0, 1, 1, 0.5));
		chartContainer.getChildren().add(zoomRect);
                setUpZooming(zoomRect, lineChart);
                final HBox controls = new HBox(10);
		controls.setPadding(new Insets(10));
		controls.setAlignment(Pos.CENTER);
		
		final Button zoomButton = new Button("Zoom");
		final Button resetButton = new Button("Reset");
		zoomButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                doZoom(zoomRect, lineChart);
            }
             });
           
                
                
                
                
    */            
          //.setContent(lineChart);
        //tab1.setContent(sc);
        //lineChart.setVisible(true);
        tab1.setText(substring);
       //TP.getTabs().add(tab1);
        //TP.setMaxSize(250, 250);
       
       
        
        
    
     lineChart.setStyle(this.getClass().getResource("test.css").toExternalForm());
     wavepane.getChildren().add(lineChart);
     wavepane.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());
   // lineChart.setScaleX(2.0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
        
        
        
        
    }
    
    
    
    public void dozoom(){
  //   double i=1;
    // while(i>0){
      this.lineChart.setScaleX(1.2);
      //i++;
     //} //this.lineChart.onZoomProperty();
    
    }
    public void dozoomout(){
  //   double i=1;
    // while(i>0){
      this.lineChart.setScaleX(1);
      //i++;
     //} //this.lineChart.onZoomProperty();
    
    }

    double[] getdata(double[] samples) {
        System.out.println("i am entering\n");
        this.array=samples;
        // for(int i=0;i<13780;i++)
       //System.out.println(samples[i]);
        return this.array;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setTab(Tab tab1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     private void setUpZooming(final Rectangle rect, final Node zoomingNode) {
        final ObjectProperty<Point2D> mouseAnchor = new SimpleObjectProperty<>();
        zoomingNode.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseAnchor.set(new Point2D(event.getX(), event.getY()));
                rect.setWidth(0);
                rect.setHeight(0);
            }
        });
        zoomingNode.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();
                rect.setX(Math.min(x, mouseAnchor.get().getX()));
                rect.setY(Math.min(y, mouseAnchor.get().getY()));
                rect.setWidth(Math.abs(x - mouseAnchor.get().getX()));
                rect.setHeight(Math.abs(y - mouseAnchor.get().getY()));
            }
        });
    }
    
     
     
     
        
            
            
            

            private void doZoom(Rectangle zoomRect, LineChart<Number, Number> chart) {
        Point2D zoomTopLeft = new Point2D(zoomRect.getX(), zoomRect.getY());
        Point2D zoomBottomRight = new Point2D(zoomRect.getX() + zoomRect.getWidth(), zoomRect.getY() + zoomRect.getHeight());
        final NumberAxis yAxis = (NumberAxis) chart.getYAxis();
        Point2D yAxisInScene = yAxis.localToScene(0, 0);
        final NumberAxis xAxis = (NumberAxis) chart.getXAxis();
        Point2D xAxisInScene = xAxis.localToScene(0, 0);
        double xOffset = zoomTopLeft.getX() - yAxisInScene.getX() ;
        double yOffset = zoomBottomRight.getY() - xAxisInScene.getY();
        double xAxisScale = xAxis.getScale();
        double yAxisScale = yAxis.getScale();
        xAxis.setLowerBound(xAxis.getLowerBound() + xOffset / xAxisScale);
        xAxis.setUpperBound(xAxis.getLowerBound() + zoomRect.getWidth() / xAxisScale);
        yAxis.setLowerBound(yAxis.getLowerBound() + yOffset / yAxisScale);
        yAxis.setUpperBound(yAxis.getLowerBound() - zoomRect.getHeight() / yAxisScale);
        System.out.println(yAxis.getLowerBound() + " " + yAxis.getUpperBound());
        zoomRect.setWidth(0);
        zoomRect.setHeight(0);
    }

            
 @FXML
 public void Hypernasality(String filename){
 
 
    
                        String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "/cexe/";
                        System.out.println("correct format"+cexedir);
                       // JFrame jf = new JFrame("test");
                        //String name = JOptionPane.showInputDialog(jf,
                        //currentDir, null);
                      try {
                          Process p1;
                          //System.out.println("getting filename"+pWave.abbfilePath);
                          //filenamedummy = pWave.abbfilePath;
                         // System.out.println("filenamedummy"+filename);
                          ProcessBuilder pb1=new ProcessBuilder
                            (cexedir+"mfcc_final_version_working",
                                    filename,
                                    "1001",
                                    cexedir+"start.txt",
                                    cexedir+"end.txt",
                                    cexedir+"vunv.txt",
                                    cexedir+"spfr.txt",
                                    cexedir+"avg.txt",
                                    cexedir+"N.txt",
                                    cexedir+"F.txt",
                                    cexedir+"mfcc_output_13dim.txt"
                            );
                          
                          
                          p1 = pb1.start();
                          System.out.println("fdgdfhdfhdfhdfhfdhdf");     
                          
                          p1.waitFor();
                         
                             
                          LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(cexedir+"mfcc_output_13dim.txt")));
                            lnr.skip(Long.MAX_VALUE);
                            System.out.println(lnr.getLineNumber() + 1); //Add 1 because line index starts at 0
                                    // Finally, the LineNumberReader object should be closed to prevent resource leak
                            int numFrames = lnr.getLineNumber();
                         //   System.out.println("The number of frames is AAAAAAA"+numFrames);
                            lnr.close();
                            System.out.println("num frames = "+numFrames);
                                  ProcessBuilder pb = new ProcessBuilder(cexedir+"posteriorcomputation" ,
                                          cexedir+"mfcc_output_13dim.txt",
                                          cexedir+"mean_norm.txt",
                                          cexedir+"var_norm.txt",
                                          cexedir+"weight_norm.txt",
                                          cexedir+"mean_clp.txt",
                                          cexedir+"var_clp.txt",
                                          cexedir+"weight_clp.txt",
                                          cexedir+"output_norm.txt",
                                          cexedir+"output_clp.txt", "16", "13", Integer.toString(numFrames));
                                 // 
                                  //  ProcessBuilder pb = new ProcessBuilder("tree");
                                  
                                  
                                  try {
                                      //System.out.println("i am here");
                                      Process p = pb.start();
                                      /*try {
                                      pb.wait(0);
                                      } catch (InterruptedException ex) {
                                      Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                                      */
                                      
                                       p.waitFor();
                                      BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                      System.out.println("xxxxxxxxxxxxx"+br.readLine());
                                   //  String st= br.readLine();
                                      //Double.parseDouble(br.readLine());
                                   //  System.out.println("double of xxxxxxxx "+Double.parseDouble(br.readLine()));
                                    // valuefromc = Double.parseDouble(br.readLine());
                                      valuefromc=0.27;//Double.parseDouble(br.readLine());//br.readLine();
                                    //  System.out.println(" i am  getting this value from c---->"+valuefromc);
                                      //br.readLine();
                                      
                                      //  System.out.println(" i am  getting this value from c---->");
                                      
                                      //System.out.println("value getting"+br);//br.readLine());
                                      // String probability =br.readLine();
                                      //double probnew = Double.parseDouble(probability);
                                      
                                                               
                                      //System.out.println(" i am  getting this value after converting from double---->"+valuefromc);
                                     // PlotProbability plot=new  PlotProbability();
                                      //plot.plotfunction();
                                     PlotProbability plot1=new  PlotProbability(this);
                                       plot1.plotfunction();
                                    //  plot1.plotfunction1();
                                      //pWave.mainFrame.createIvectorInternalFrame("Speaker Identification", "word/Assamese/part2");
                                 
                                  }
                                  catch (IOException ex)
                                  {
                                    //  Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                                  }
                                  
                                  
                      }
                      catch (IOException ex) 
                         {
                       // Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                         } catch (InterruptedException ex) {
                       // Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                    }

 
 
            
            
            
            
            
            
            
            
            
            
            
            
		
}
 static double getvaluefromc()
    {
        return valuefromc;
    }
}