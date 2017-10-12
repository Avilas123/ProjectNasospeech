/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Paths;
import java.util.Random;
//import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
//import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
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
   
    public StreamBytes streamBytes=new StreamBytes();
    public int startSam, endSam;
     
      public double mousePosX1, mousePosX2, mouseMoveX1, mousePosY1;
      
      LineChart lineChart ;
       NumberAxis xAxis ;
  //  FXMLDocumentController fxmlobject =new FXMLDocumentController();
    double array[]={};
     @FXML 
 static  double  valuefromc;
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));     
        Scene scene;
        scene = new Scene(root);
     // scene.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());
       // scene.getStylesheets().add("test.css");
        
      stage.setResizable(false);
       stage.setMaxWidth(1190);
       stage.setMaxHeight(630);
      stage.setTitle("NasoSpeech");
      
        stage.setScene(scene);
         stage.centerOnScreen();
        stage.show();
    }
    
    
    public void startforplotwave(Stage stage,double[] samples,int numsamples,String filename,Tab tab1 ,TabPane TP,ScrollPane wavepane,Double sam_freq,double duration) throws Exception {
     xAxis= new NumberAxis("",0d,duration,0.1);
       NumberAxis yAxis = new NumberAxis("", -1d, 1d, 1);
       lineChart= new LineChart(xAxis, yAxis);
         java.nio.file.Path p=Paths.get(filename);
      
       String substring= p.getFileName().toString();
       stage.setTitle(substring);

        
        yAxis.tickMarkVisibleProperty();
       
        
        //XYChart.Series<Integer,Double> dataSeries1 = new XYChart.Series<>();
      //  XYChart.Data<Integer,Double> data = new XYChart.Data<>();
       
   final   ObservableList<XYChart.Data<Double,Double>> data = FXCollections.<XYChart.Data<Double,Double>>observableArrayList();
   
     
      
      
       for(int i=0;i<numsamples;i++){
      
         // data = new XYChart.Data<Integer,Double>( i, samples[i]);
         
         
         XYChart.Data<Double,Double> dd = new XYChart.Data<>(i/sam_freq,samples[i]);
         
         //dd.setNode(new HoveredThresholdNode(samples[i]));
         data.add(dd);
         // data.add(new XYChart.Data<>(i/sam_freq, samples[i]).setNode(new HoveredThresholdNode(samples[i])));
          
         
          //dataSeries1.getData().add(data);
         // System.out.println(samples[i]);
     
       }
     //dataSeries1.getData().add(data);
     XYChart.Series series = new XYChart.Series(data);
  
    // XYChart.Series=new XYChart.Series<>
     //xAxis.setUpperBound(2000);
     //xAxis.setLayoutX(factor);
     //series.setName("yyyyyy");
    // xAxis.autosize();
    xAxis.setSide(Side.TOP);
   
     lineChart.setCreateSymbols(false);
     
     lineChart.getData().clear();
     //lineChart.getData().add(10, series);
    // int index=346/2;
    // lineChart.getData().add(150, series);
     lineChart.getData().add(series);  
     
     
     
     
        System.out.println("upper   "+yAxis.getUpperBound()+"lower     "+yAxis.getLowerBound());
    
     lineChart.setLegendVisible(false);
       boolean verticalGridLinesVisible = lineChart.getVerticalGridLinesVisible();
      verticalGridLinesVisible=true;
      boolean horizontalGridLinesVisible = lineChart.isHorizontalGridLinesVisible();
      horizontalGridLinesVisible=true;
        
      lineChart.setCreateSymbols(false);
      lineChart.setAnimated(false);
     // lineChart.verticalGridLinesVisibleProperty(true){
    // public final void setVerticalGridLinesVisible(boolean value) { verticalGridLinesVisible.set(value); }
    
  //  }
      // xAxis.setTickUnit(factor);
     // lineChart.getYAxis().setTickLabelsVisible(false);
    //  lineChart.getYAxis().setTickMarkVisible(false);
    //lineChart.getXAxis().toString();
     //lineChart.getXAxis().setTickMarkVisible(false);  
     // lineChart.getXAxis().setTickLabelsVisible(false);
    //  lineChart.getXAxis().setOpacity(0);
      //lineChart.getYAxis().lookup(".axis-minor-tick-mark").setVisible(false);
     // lineChart.getXAxis().lookup(".axis-minor-tick-mark").setVisible(false);
    //  lineChart.getYAxis().setVisible(false);
     // lineChart.getYAxis().setTickLabelsVisible(false);
   //  lineChart.getYAxis().setOpacity(0);
        tab1.setText(substring);
        TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
     
   //lineChart.setPrefHeight(402);
     lineChart.setPrefWidth(1180);
     lineChart.setMinWidth(1180);
         double height = lineChart.getHeight();//System.out.println("linechartheight"+height);
     //lineChart.setMaxSize(1300, 402);
   // lineChart.setMinSize(1300,402);
    lineChart.setStyle(this.getClass().getResource("test.css").toExternalForm());
   // wavepane.setStyle(this.getClass().getResource("test.css").toExternalForm());
    // wavepane.setPannable(true);
    // wavepane.setStyle(".scroll-pane > .viewport{-fx-background-color:#232323 ;\n" +
     //"-fx-control-inner-background: transparent;}");
     wavepane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
     wavepane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
     wavepane.setFitToWidth(true);
     wavepane.setFitToHeight(true);
     wavepane.setContent(lineChart);
     
     lineChart.setOnMouseClicked(new EventHandler<MouseEvent>() 
     {
      @Override public void handle(MouseEvent mouseEvent) {
          System.out.println("xaxis value print-->"+
          String.format(
            "x = %.2f",
          xAxis.getValueForDisplay(mouseEvent.getX())
          )
        );
         // System.out.println("series data-->"+series.getData().toString());////////////////gives the data
      }
    });
     lineChart.setOnDragOver(new EventHandler<DragEvent>() {
    public void handle(DragEvent event) {
        /* data is dragged over the target */
        /* accept it only if it is not dragged from the same node 
         * and if it has a string data */
       /* if (event.getGestureSource() != target &&
                event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses 
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        */
      
       
       
      
       
       
        event.consume();
    }
});
/*
    xAxis.setOnMouseMoved(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
          System.out.println("xaxis value print-->"+
          String.format(
            "x = %.2f",
            xAxis.getValueForDisplay(mouseEvent.getX())
          )
        );
      }
    });
     
  */   
     
     
     
     
     
 /*    
     lineChart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                mousePosX1=e.getX();
                mousePosY1=e.getY();
                
                
                
                
                
                
               getSamplingPositions(e.getX(),e.getY(),numsamples,duration);
               int startSample = getStartSamples();
                            int endSample = getEndSamples();
                            System.out.print("StartSample\t"+startSample+"endSample\t"+endSample);
                                CutAudioWave cutW = new CutAudioWave();
                                cutW.cutPortion(streamBytes.getCurrent(), startSample, endSample);

                                if (cutW.getresultByteArray() == null) {
                                     System.out.println("i have entered");
                                     return;
                                }
                                if (CutAudioInputStream.getCutWave() == null) {
                                     System.out.println("i have entered");
                                     return;
                                }
                //System.out.println("mousePosX1--->"+e.getX());
               // System.out.println("mousePosY1---->"+e.getY());
               // series.getData().add(new XYChart.Data(series.getData().size() * 10, 30 + 50 * new Random().nextDouble()));
                //Coords();
            }
        });*/
     // System.out.println("mousePosX1--->"+mousePosX1);
     //System.out.println("mousePosX1--->"+e.getX());
     //wavepane.setBackground(new Background(Array(new BackgroundFill(Color.DARKCYAN,new CornerRadii(0),Insets(0)))));
    // wavepane.setContent().removeAll(lineChart);
    /// wavepane.getChildren().add(lineChart);
     
     
     

    wavepane.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());
    /* scrollbar.valueProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> ov,
            Number old_val, Number new_val) {
            
         //  double val= scrollbar.getValue();
            
          //  lineChart.setTranslateX((-val)*lineChart.getScaleX()*2);
            
          //  System.out.println(lineChart.getScaleX());
            
            double trans = lineChart.getBaselineOffset();
                lineChart.setTranslateX((-new_val.doubleValue()*20));
                System.out.println(trans);
                
        }
    });
   */  
     
     
     
     
   // lineChart.setScaleX(2.0);
    }
    public void showHover(Float value)
    {  
    
        
        
        
        
    
    
    }
    
    public void getSamplingPositions(double mousePosX1,double mousePosX2,int numsamples,double duration) {
        try {
            int width = 1189;//pWave.samplingGraph.getSize().width;
            int inPos = (int) mousePosX1, enPos = (int) mousePosX2;

            if (inPos < 1) {
                inPos = 1;
            }
            if (enPos > (width - 10)) {
                enPos = width - 10;
            }
            if (enPos < 1) {
                enPos = 1;
            }
            if (inPos > (width - 10)) {
                inPos = width - 10;
            }

            int startTime = (int)((inPos * duration) * numsamples);
            int endTime = (int)((enPos * duration) * numsamples);
            int startSample, endSample;
            if (startTime > endTime) {
                startSample = endTime;
                endSample = startTime;
            } else {
                startSample = startTime;
                endSample = endTime;
            }
            System.out.println("startSample inside"+startSample);
            System.out.println("endSample inside"+endSample);
            setStartSamples(startSample);
            setEndSamples(endSample);
        } catch (Exception er) {
            System.err.println(er);
        }

    }
    
     private void setStartSamples(int start) {
        this.startSam = start;
    }

    private void setEndSamples(int end) {
        this.endSam = end;
    }
    public int getStartSamples() {
        return this.startSam;
    }

    public int getEndSamples() {
        return this.endSam;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public double[] normalize(double []samples, int numsamples){
        double norm_arr[] = new double[numsamples] ;
        double max = Math.abs(samples[0]);
        /// find the max value
        for(int itr=1;itr<numsamples;itr++){
            if(Math.abs(samples[itr])> max )
                max = Math.abs(samples[itr]);
        }
        
        /// normalize with max value
        for(int itr=0;itr<numsamples;itr++){
            norm_arr[itr] = samples[itr]/max;
        }
        
        System.out.println("max = "+max   + "samples 0 = " + samples[0]  + "norm 0 = "+ norm_arr[0]);
        
        
        return norm_arr;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void dozoom(Float value,ScrollPane wavepane,double duration){
  //double i=1;
    // while(i>0){
    //lineChart.setStyle(this.getClass().getResource("zoom.css").toExternalForm());
   // wavepane.setStyle(this.getClass().getResource("zoom.css").toExternalForm());
   
    if(value<10)
    {
    this.lineChart.setMinWidth(1180);
    xAxis.setTickUnit(.1);
    
    //xAxis= new NumberAxis("", 0d, duration, .001);
    
    
    //scrollbar.setVisibleAmount(100);
    //scrollbar.setValue(50); 
      
 
    }
    else if(value>=10&&value<30){
     this.lineChart.setMinWidth(1180+value*250);
    //xAxis.setTickUnit(value*.1);////////////////set the value
     xAxis.setTickUnit(.08);
     
     wavepane.setHvalue(50);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
    
    }
    else if(value>=30&&value<50){
     this.lineChart.setMinWidth(1180+value*250);
    //xAxis.setTickUnit(value*.1);////////////////set the value
     xAxis.setTickUnit(.05);
     
     wavepane.setHvalue(40);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
    
    }
    else if(value>=50&&value<70){
     this.lineChart.setMinWidth(1180+value*250);
    //xAxis.setTickUnit(value*.1);////////////////set the value
     xAxis.setTickUnit(.03);
     
     wavepane.setHvalue(30);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
    
    }
    else if(value>=70&&value<90){
     this.lineChart.setMinWidth(1180+value*250);
    //xAxis.setTickUnit(value*.1);////////////////set the value
     xAxis.setTickUnit(.02);
     
     wavepane.setHvalue(20);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
    
    }
    else if(value>90)
            {
                this.lineChart.setMinWidth(1180+value*250);
    //xAxis.setTickUnit(value*.1);////////////////set the value
     xAxis.setTickUnit(.01);
     
     wavepane.setHvalue(10);
    }
    }
   /* public void dozoomout(){
  //   double i=1;
    // while(i>0){
   
      this.lineChart.setScaleX(1);
       xAxis.setTickUnit(1);
      //i++;
     //} //this.lineChart.onZoomProperty();
    
    }
*/
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
                              // s p.waitFor();
                              int waitFor = p.waitFor();
                              p.isAlive();
                                      System.out.println("wait for value"+waitFor);
                                   //   BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                      Scanner sc=new Scanner(p.getInputStream());
                                     valuefromc= Double.parseDouble(sc.next());
                                     
                                    // System.out.println("xxxxxxxxxxxxx---->"+br.readLine());
                                     //String st= br.readLine();
                                      //Double.parseDouble(br.readLine());
                                      //valuefromc=Float.parseFloat(br.readLine());
                                    //System.out.println("float of xxxxxxxx----->"+Double.parseDouble(br.readLine()));
                                    // valuefromc = Double.parseDouble(br.readLine());
                                      //(float) 0.27;//Double.parseDouble(br.readLine());//br.readLine();
                                     //System.out.println("%f i am  getting this value from c---->"+valuefromc);
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
class HoveredThresholdNode extends StackPane {
    HoveredThresholdNode(double value) {
      setPrefSize(15, 15);

      final Label label = createDataThresholdLabel(value);

      setOnMouseEntered(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          getChildren().setAll(label);
          setCursor(Cursor.NONE);
          toFront();
        }
      });
      setOnMouseExited(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          getChildren().clear();
          setCursor(Cursor.CROSSHAIR);
        }
      });
      
      
  }
     private Label createDataThresholdLabel(double value) {
      final Label label = new Label(value + "");
      label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
      label.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

     /*if (priorValue == 0) {
       label.setTextFill(Color.DARKGRAY);
      } else if (value > priorValue) {
        label.setTextFill(Color.FORESTGREEN);
      } else {
        label.setTextFill(Color.FIREBRICK);
      }

      label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
      
    }*/
    
    return label;
    
    
    
}

      
      
}    
      
      
    