/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

//import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.paint.Color.color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPopupMenu;
import javazoom.jl.decoder.JavaLayerException;
/**
 *
 * @author IITG
 */
public class FXMLDocumentController extends Application {
    @FXML
    private MenuItem copy;
    @FXML
    private MenuItem pasteitem;
       @FXML
    private Slider slider;
        @FXML
    private Tab tab1;
           @FXML
    private TabPane TP;
         @FXML
    private ScrollPane wavepane;
          @FXML
    private Button playbtn;
 
    @FXML
    private AnchorPane tab1ap;
    @FXML
    private MenuItem hypernasality;
    @FXML
    private MenuItem scorecard;
    
        @FXML
    private MenuItem saveas;
     @FXML
    public ImageView marker;
     
       @FXML
    private AnchorPane counterap;
        @FXML
    private Label hour;

    @FXML
    private Label min;

    @FXML
    private Label sec;

    @FXML
    private Label milisec;
    @FXML
    private MenuItem closebtn;   
      @FXML
    private Button stopicon;

      
      public  Media pick;  
       public MediaPlayer player ;
      public  TranslateTransition trans;
       
       @FXML
    private MenuItem open_menu_item;
         @FXML
    private Button fwdbtn;
         
    @FXML
    private Button rewindbtn;
    @FXML
    private Button stopbtn;

    @FXML
    private Button recordbtn;
     @FXML
    private MenuItem selectall;

  
    final Tooltip playtip = new Tooltip();
    final Tooltip pausetip = new Tooltip();
    final Tooltip forwardtip = new Tooltip();
    final Tooltip rewindtip = new Tooltip();
    final Tooltip stoptip = new Tooltip();
    final Tooltip recordtip = new Tooltip();
    
    
    
     
     double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
     @FXML
     final int bufSize = 16384;
    public  double frames_per_pixel;
    
    String errStr;
    double duration, seconds;
    File file;
    public String fileName = "untitled";
    public String abbfilePath = null;
    Vector lines = new Vector();
    private Toolkit tk;
    public double mousePosX1, mousePosX2, mouseMoveX1, mousePosY1;
    public JPopupMenu menu;
  public StreamBytes streamBytes;
  //  public MainFrame mainFrame;
   // public Mainpopup mainpopup;
//    private SamplingGraph sg;
    private int graphFromScreen = 5, graphVerticalSize = 240, normalPixcel = 60;
    public boolean selectedPlay = false;
  //  public StreamVariables streamVariable;
 //   public RightClickEvent rightClick;
    public String[][] annotationPos;
    public ByteArrayOutputStream capOut;
    private int samplingpanelSize;
    List<List<Integer>> dummyList;
  //  public ServerFinder serverStatus;
    public int xSize;
    public String fileHashValue;
    private double record_duration;
    private boolean buffStatus = true;
    private boolean lineStatus = false;
    public static String filenamefortab;
    public static Stage classStage = new Stage();
    private Thread thread;
    private AudioFormat format,format1;  
    private int[] audioDataNormalize;
    private int normalizedValue = 3000;
    public SignalProc sigProc;
    public static String dummy;
    Capture cap = new Capture();
     TranslateTransition trans1= new TranslateTransition();
     TranslateTransition trans2= new TranslateTransition();
 // public  LineChart lineChart ;
      boolean variable=false;
      XYChart.Data<String,Double> dd;
    @FXML
    private LineChart wave;
    
    @FXML
    private NumberAxis recordxaxis;
    @FXML
    private LineChart<? ,? > recordinglinechart;

    NasoFX nfx=new NasoFX();
    double factor;
     int numSamples;
     float frameRate;
      int frameSize;
      int actual_frames_per_pixel;
      Stage getstage(){
      return classStage;
      }
      
      @FXML
      void shutdown(){
          System.out.println("hjkgvkhgvkhgkhlhl");
      }
      
      
      
      
    @FXML
    void Zoomfunction(MouseEvent event) {
        
        float value = (float) slider.getValue();
        nfx.dozoom(value,wavepane,duration);
        
        
        

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
          // System.out.println("\nThe offset is :"+ newTranslateX);
           
           if(newTranslateX>0.0 && newTranslateX<1140.11){
             
            ((ImageView)(event.getSource())).setTranslateX(newTranslateX);
            //((ImageView)(event.getSource())).setTranslateY(newTranslateY);
           }
           
          /* double intial_second = ((newTranslateX * (this.frames_per_pixel*2)));// / this.audioInputStream.getFormat().getFrameRate()) ;
          //  System.out.println("initial seconds"+intial_second+"\tmarkerx1\t"+orgTranslateX+"\tframespersecond\t"+frames_per_pixel);
                            double x=intial_second*1000; 
                          //  System.out.println("time type cast\t"+x);
           String timeConversion2 = timeConversion2(x);
           //System.out.println("totaltime:\t"+timeConversion2);
           //System.out.println("hour"+timeConversion2.substring(0, 2));
          // System.out.println("minute"+timeConversion2.substring(2, 5));
          // System.out.println("second"+timeConversion2.substring(5, 8));
          // System.out.println("millisecond"+timeConversion2.substring(8, 12));
           hour.setText(timeConversion2.substring(0, 2));
           min.setText(timeConversion2.substring(2, 5));
           sec.setText(timeConversion2.substring(5, 8));
           milisec.setText(timeConversion2.substring(8, 12));
           */
          
          hour.setText("00");
          min.setText("00");
          double width=1135;
          double factor=(frames_per_pixel*1000)/width;
          double movefactor=newTranslateX*factor;
          int milli=(int)movefactor%1000;
          String mm=String.valueOf(milli);
          
          int second=(int)movefactor/1000;
          String ss=String.valueOf(second);
          
          if(ss.length()<2){
          ss= "0"+ss;
          }
          if(mm.length()==1){
          mm= "00"+mm;
          }
          if (mm.length()==2){
          mm= "0"+mm;
          }
          
          
          if(milli>=0 && movefactor < (frames_per_pixel*1000))
          {
              
          milisec.setText(mm);
          sec.setText(ss);
          
          }
           
           
           
           
           
           
           
           
           
    }
    
   
    @FXML
    void closesystem(ActionEvent event) {
        System.exit(0);

    }
    
    
     
 


    

    
  //  @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
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
  
   
for (i = 3,a=0; i <2100; i+=50,a+=1)
{
    //double a = 0.1;
   String number = Double.toString(a);
    Text t = new Text(i, 23,number);
     double strokeWidth = t.getStrokeWidth();
      System.out.println("the stroke width is  :  "+strokeWidth);
    t.setStrokeWidth(0.1);
    t.setStyle("-fx-text-fill:white; -fx-font-size:10;");
       
      
        t.setStrokeWidth(0.5);
        
       t.setStrokeWidth(0.1);
     t.setStroke(Color.rgb(204, 204, 204));
  
        System.out.println("the new  stroke width is  :  "+strokeWidth);
   
    
    
    
    Line line1 = new Line(i, 20, i, 322);
     line1.setStrokeWidth(0.1);
     
    line1.setStroke(Color.rgb(204, 204, 204));
    
   
    Line line2 = new Line(0, i, 600, i);
    line2.setStroke(Color.LIGHTGRAY); 

    tab1ap.getChildren().addAll(line1,t);
    
}

        Line redLine = new Line(0, 5, 2100, 5);

    redLine.setStroke(Color.rgb(221, 221, 221));
    redLine.setStrokeWidth(10);
    
    tab1ap.getChildren().addAll(redLine);
    
  // marker.toFront();

//counterap.setStyle("-fx-border-color: black");
       
 //nfx.setTab(tab1);
 
  TP.getTabs().addAll(tab1);
  
    
 
    }    

    /**
     *
     */
    
    
    
    
    
    
    
    
   
    
    
   
    
    
    
    
    
    @FXML
    
    public void open_button_Event(ActionEvent event) throws Exception
    { 
        //Tab tab1=new Tab();
        //TP.getTabs().add(tab1);
        Plotwave plot=new Plotwave();
        
        String filename;
        filename=plot.fileopenmethod();//fileopenmethod();
        System.out.println("filename_in 1st time load-------->>>>>>\t"+filename);
        pick = new Media(new File(filename).toURI().toURL().toExternalForm());
        player = new MediaPlayer(pick);
        trans= new TranslateTransition();
      
        double[] samples = plot.readWaveData(filename);
    
        sendfilename(filename);
        duration=plot.getduration();
        factor=plot.getsampfrq();
        numSamples=plot.getnumsamples();
        nfx.tempplot(classStage,filename,samples, numSamples, tab1, TP, wavepane, factor, duration);
  
       
    }
 /////////////////////conversion/////////////
  @FXML
    public String SampleRateConversion(File SourceFile, float dSampleRate) throws UnsupportedAudioFileException, IOException
            
    { 
        float fTargetSampleRate = dSampleRate;
        AudioFileFormat SourceFileFormat = AudioSystem.getAudioFileFormat(SourceFile);
        AudioFileFormat.Type TargetFileType = SourceFileFormat.getType();
        AudioInputStream SourceStream = null;
        SourceStream = AudioSystem.getAudioInputStream(SourceFile);
        AudioFormat SourceFormat = SourceStream.getFormat();
        float fTargetFrameRate = fTargetSampleRate;
        
        AudioFormat TargetFormat = new AudioFormat(
                                        SourceFormat.getEncoding(),
                                        fTargetSampleRate,
                                        SourceFormat.getSampleSizeInBits(),
                                        SourceFormat.getChannels(),
                                        SourceFormat.getFrameSize(),
                                        fTargetFrameRate,
                                        SourceFormat.isBigEndian()
        
                                    );
                
        
        AudioInputStream TargetStream = AudioSystem.getAudioInputStream(TargetFormat, SourceStream);
        //OutputStream targetFile = null;
        File targetFile = new File("converted.wav");
        
        int nWrittenBytes = AudioSystem.write(TargetStream, TargetFileType, targetFile);
    
    
      return targetFile.getAbsolutePath();
    
    
    }
   

   
    
  @FXML
    void convert(ActionEvent event) throws UnsupportedAudioFileException, IOException {
      String  filename = getfilename();
      File sendfile = new File(filename);
     
      String newFilename = null;
      
      newFilename = SampleRateConversion(sendfile, (float) 8000.0);
        System.out.println("The converted file is "+ newFilename);

    }   
    
    
    
 ///////////////////////////////////////////    
    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void sendfilename(String filename) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        this.dummy=filename;
        }
    String getfilename(){
        return dummy;
    
    }

    private String timeConversion2(double time1) {
        
        String hD = "", mD = "", sD = "", msd="", totalTD = "";
        try {
            int time=(int)time1;
            int hour = time / (1000*3600);
            int hour_balance = time % (1000*3600);
            int min = hour_balance / (60*1000);
            int min_balance = hour_balance % (60*1000);
            int sec=min_balance/(1000);
            int sec_balance=min_balance% (1000);
           // System.out.println("time\t"+time+"sec\t"+sec+"hour"+hour_balance+"minute"+min);
            if (hour < 10*1000) {
                hD = "0" + hour;
            } else {
                hD = hD + hour;
            }
            if (min < 10*1000) {
                mD = "0" + min;
            } else {
                mD = mD + min;
            }
            if (sec < 60*1000) {
                sD = "0" + sec;
            } else {
                sD = sD + sec;
            }
            if(sec_balance<10){
               msd= "00"+sec_balance;          
             }else{
            msd=msd+sec_balance;
            }

            totalTD = hD + ":" + mD + ":" + sD+":"+msd;
           // System.out.println("timeformat"+totalTD);
        } catch (Exception er) {
          //  Logger.getLogger(PlotWave.class.getName()).log(Level.SEVERE, null, er);
        }
        return totalTD;
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 
  
   
 @FXML
    void hypernalasityclick(ActionEvent event) {
        Tab tab = new Tab();
          tab.setText("Hypernasality  ");
           String filename = this.getfilename(); //fileName);
           
            TP.getTabs().add(tab);
            TP.getSelectionModel().select(tab);
            TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
            double probability = nfx.Hypernasality(filename);
          
     System.out.println("The probability value is:"+probability);
     
     
    Rectangle rect = new Rectangle(40,100,100 ,40);
    rect.setX(50);
    rect.setY(50);
    
    rect.setFill(Color.rgb(0, 156, 73));

    rect.setStroke(Color.BLACK);
    
    Text t = new Text();
    String value = Double.toString(probability);
    t.setText("The Hypernasality Score is :"+value);
    t.setFont(Font.font ("Verdana", 20));
    t.setFill(Color.RED);
   
    tab.setContent(t);
   
    }
 
 
    @FXML
    void playsound(ActionEvent event) throws FileNotFoundException, IOException, JavaLayerException, UnsupportedAudioFileException, LineUnavailableException {
        String filename = this.getfilename();
        System.out.println("filename in playsound"+filename);
        Image pause = new Image("file:\\C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEnd\\src\\Icons\\pausebtn.png",27,27,false,false);
       Image playimage = new Image("file:\\C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEnd\\src\\Icons\\play-arrow-(1).png",15,17,false,false);
        MediaPlayer.Status status = player.getStatus();
        
           double d = pick.getDuration().toMillis();
          // System.out.println("The duration of the file is:"+d);
          // double aaa =Double.parseDouble(d);
          // double translate = 1147.77/d;
            //System.out.println("The translate of the file is:"+translate);   
           // TranslateTransition trans= new TranslateTransition();
           
          
            trans.setDuration(Duration.millis(d));
            trans.setToX(1140);
            trans.setNode(marker);
            
          // Animation.Status  tttt= trans.getStatus();
           //System.out.println("The animation status is :"+tttt);
           if(!(status == MediaPlayer.Status.PLAYING) && !(status == MediaPlayer.Status.PAUSED)) {
                player.play();
               
                player.currentTimeProperty().addListener(new ChangeListener<Duration> () {
   @Override
   //is usually updated every 100 ms
   public void changed(ObservableValue<? extends Duration> observable,
     Duration oldValue, Duration newValue) {
       
      double a = trans.getCurrentTime().toMillis();
      int aa = (int)a;
      
       int milli=(int)aa%1000;
          String mm=String.valueOf(milli);
          
          int second=(int)aa/1000;
          String ss=String.valueOf(second);
          if(ss.length()<2){
          ss= "0"+ss;
          }
          if(mm.length()==1){
          mm= "00"+mm;
          }
          if (mm.length()==2){
          mm= "0"+mm;
          }
          
              
          milisec.setText(mm);
          sec.setText(ss);
          
           
   }
        });
                trans.play();
                playtip.setText("pause");
                playbtn.setTooltip(playtip);
                playbtn.setGraphic(new ImageView(pause));
             player.setOnEndOfMedia(new Runnable() {
            @Override public void run() {
                try {
                       resetmedia();
                       playtip.setText("play");
                       playbtn.setTooltip(playtip);
                       playbtn.setGraphic(new ImageView(playimage));
                       
                } catch (MalformedURLException ex) {
                       Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                 });
        
         }
            
         else if (status == MediaPlayer.Status.PLAYING )
         {
         player.pause();
         trans.pause();
         playtip.setText("play");
         playbtn.setTooltip(playtip);
         playbtn.setGraphic(new ImageView(pause));
         playbtn.setGraphic(new ImageView(playimage));
         
         }
         else if(status == MediaPlayer.Status.PAUSED){
          player.play();
          player.currentTimeProperty().addListener(new ChangeListener<Duration> () {
   @Override
   //is usually updated every 100 ms
   public void changed(ObservableValue<? extends Duration> observable,
     Duration oldValue, Duration newValue) {
       
      double a = player.getCurrentTime().toMillis();
      int aa = (int)a;
       
       int milli=(int)aa%1000;
          String mm=String.valueOf(milli);
          
          int second=(int)aa/1000;
          String ss=String.valueOf(second);
          if(ss.length()<2){
          ss= "0"+ss;
          }
          if(mm.length()==1){
          mm= "00"+mm;
          }
          if (mm.length()==2){
          mm= "0"+mm;
          }
              
          milisec.setText(mm);
          sec.setText(ss);
          
           
   }
        });
                trans.play();
                
                playtip.setText("pause");
                playbtn.setTooltip(playtip);
                playbtn.setGraphic(new ImageView(pause));
               player.setOnEndOfMedia(new Runnable() {
            @Override public void run() {
                try {
                       resetmedia();
                       System.out.println("M ON");
                       playtip.setText("play");
                       playbtn.setTooltip(playtip);
                       playbtn.setGraphic(new ImageView(playimage));
                       
                } catch (MalformedURLException ex) {
                       Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                 });
                 }
         
     }
    
  @FXML
    void stopsound(ActionEvent event) throws MalformedURLException {
         MediaPlayer.Status status = player.getStatus();
                 
         if (status == MediaPlayer.Status.PLAYING )
         {
              Image playimage = new Image("file:\\C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEnd\\src\\Icons\\play-arrow-(1).png",15,17,false,false);
              player.stop();
              trans.stop();
              resetmedia();
              playtip.setText("play");
              playbtn.setTooltip(playtip);
              playbtn.setGraphic(new ImageView(playimage));
              
         }

    }
    
     @FXML
    void forwardseek(ActionEvent event) {
        
        player.seek(player.getCurrentTime().multiply(1.5));
        trans.playFrom(trans.getCurrentTime().multiply(1.5));
    }
    
    
    @FXML
    void rewindseek(ActionEvent event) {
        player.seek(player.getCurrentTime().divide(1.5));
        trans.playFrom(trans.getCurrentTime().divide(1.5));
    }
    
    
       @FXML
    void rewindenter(MouseEvent event) {
        rewindtip.setText("rewind");
        rewindbtn.setTooltip(rewindtip);

    }
      @FXML
    void fwdenter(MouseEvent event) {
        forwardtip.setText("forward");
        fwdbtn.setTooltip(forwardtip);
    }
      @FXML
    void playenter(MouseEvent event) {
        playtip.setText("play");
        playbtn.setTooltip(playtip);
    }
      @FXML
    void stopenter(MouseEvent event) {
        stoptip.setText("stop");
        stopbtn.setTooltip(stoptip);
    }
     @FXML                                                          
    void recordenter(MouseEvent event) {
        recordtip.setText("record");
        recordbtn.setTooltip(recordtip);
    }
    
    void resetmedia() throws MalformedURLException{
      String  filename = getfilename();
      File file = new File(filename);
          pick = new Media(file.toURI().toURL().toExternalForm());
          player = new MediaPlayer(pick);
         // trans= new TranslateTransition();
          trans.stop();
      
           marker.setTranslateX(0);
           milisec.setText("000");
           sec.setText("00");
         
         
         // System.out.println("The marker translate is :"+marker.getTranslateX());
          //System.out.println("The getX of the marker is :"+marker.getLayoutX());
    }
    
    
      @FXML
    void selectallfunction(ActionEvent event) 
    {
           //Graphics g;
        
       // this.paint(g);
       
        nfx.selectAll(numSamples, frames_per_pixel);
        
        
        
        
        
        

    }
    //@FXML
   

                        //End Selection
        
        
        /////////////saveas function/////////
        
        @FXML
     void saveas(ActionEvent event)
             
        { // Plotwave plot=new Plotwave();
            //System.out.println("sampling positions"+plot.getSamplingPositions());
    
          // byte[] current = streamBytes.getCurrent();
         //   System.out.println("current"+Arrays.toString(current));
//do        nfx.saveas(this.audioInputStream,frameSize, actual_frames_per_pixel);
        }
        
     
        @FXML
     void copy(ActionEvent event)
        { Plotwave plot=new Plotwave();
          // byte[] current = streamBytes.getCurrent();
         //   System.out.println("current"+Arrays.toString(current));
       // nfx.saveas(this.audioInputStream,numSamples,frames_per_pixel);
///do            nfx.copy(this.audioInputStream, frameSize , actual_frames_per_pixel, frameRate);
         
            
            System.out.println("copy start sample"+plot.getStartSample()+"sample in copy"+plot.getSamplingPositions());
        }
        @FXML
        void paste(ActionEvent event) throws Exception
        {
           
            
            Tab tab1 = new Tab();
            tab1.setText("untitled ");
           //String filename = this.getfilename(); //fileName);
           ScrollPane wavepane1=new ScrollPane();
           tab1.setContent(wavepane1);
           
            TP.getTabs().add(tab1);
            TP.getSelectionModel().select(tab1);
            TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
            
            
            
            
////do            nfx.paste(this.audioInputStream,tab1,TP,wavepane1);
            
        
        }
     
            @FXML
             void record(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception
             {   nfx.Is_record_done=true;
                 variable=true;              
                 cap.start();
                 trans1.setDuration(Duration.millis(5000));
                 trans1.setToX(1140);
                 trans1.setNode(marker);
                 trans1.play();
  /*       
                 //  NumberAxis xAxis= new NumberAxis("",0d,100,0.05);
               //  NumberAxis yAxis = new NumberAxis("", 0, 1000, 1);
                // recordinglinechart=new LineChart(xAxis,yAxis);
                //Thread.sleep(1000);
                XYChart.Series dataSeries1 = new XYChart.Series();
        //dataSeries1.setName("2014");
      

        dataSeries1.getData().add(new XYChart.Data( "1",40 ));
        dataSeries1.getData().add(new XYChart.Data( "5", 50));
        dataSeries1.getData().add(new XYChart.Data("10", 60));
        dataSeries1.getData().add(new XYChart.Data("20", 70));
        dataSeries1.getData().add(new XYChart.Data("40", 80));
        dataSeries1.getData().add(new XYChart.Data("80", 80));
        
 
        recordinglinechart.getData().add(dataSeries1);
       
        trans2.setNode(recordinglinechart);
        //trans2.setDuration(Duration.millis(10000));
        trans2.play();
      */ 
     /*
         Plotwave plot=new Plotwave();
        String filenamedis="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\recordfile.wav";
        double[] samples = plot.readWaveData(filenamedis);
        duration=plot.getduration();
        factor=plot.getsampfrq();
        numSamples=plot.getnumsamples();
        final   ObservableList<XYChart.Data<String,Double>> data = FXCollections.<XYChart.Data<String,Double>>observableArrayList();    
        for(int i=0;i<numSamples;i++)
        {
        dd = new XYChart.Data<>(Double.toString(i/factor),samples[i]);
        data.add(dd);
        }
        XYChart.Series series = new XYChart.Series(data);
        recordinglinechart.getData().add(series);
        trans2.setNode(recordinglinechart);
        trans2.play();
       */ 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
             
      
        /*         while(variable!=false)
                 {
        Thread.sleep(1000);
        String filenamedis="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\recordfile.wav";
        double[] samples = plot.readWaveData(filenamedis);
        duration=plot.getduration();
        factor=plot.getsampfrq();
        numSamples=plot.getnumsamples();
        final   ObservableList<XYChart.Data<Double,Double>> data = FXCollections.<XYChart.Data<Double,Double>>observableArrayList();    
        for(int i=0;i<numSamples;i++)
        {
        dd = new XYChart.Data<>(i/factor,samples[i]);
        data.add(dd);
        }
        XYChart.Series series = new XYChart.Series(data);
        recordinglinechart.getData().add(series);
        trans2.setNode(recordinglinechart);
        trans2.play();
                 }
          */ 
           }
             @FXML
            void marker_play_during_record(){
            trans1.setDuration(Duration.millis(1000));
            trans1.setToX(1140);
            trans1.setNode(marker);
            trans1.play();
           
             }
            @FXML
            void stopRecord(ActionEvent event) throws Exception
            {
                 cap.stop();
                 variable=false;
                 trans1.stop();
                 marker.setTranslateX(0);
                 nfx.Is_record_done=true;
               //  trans1.pause();
                 //playbtn.setDisable(false);
                // stopbtn.setDisable(true);
                // recordtip.setText("record");
                // recordbtn.setTooltip(recordtip);
           ///      double[] samplesfromrecord = cap.getsamplesfromrecord();
     //   int numsamples = cap.getnumsamples();
       // double duration1 = cap.getduration();
        //double factor=cap.getsampfrq();
        //double[] samplesfromrecord1 = cap.getsamplesfromrecord();
          //      System.out.println("numsample"+numsamples+"duration1"+duration1+"factor"+factor);
        //nfx.tempplot(classStage, samplesfromrecord, numsamples, tab1, TP, wavepane, factor, duration1);
      
       // Thread.sleep(1000);
        Plotwave plot=new Plotwave();
        Thread.sleep(2000);
       
        String filename="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\untitled.wav";
        
        double[] samples = plot.readWaveData(filename);
        pick = new Media(new File(filename).toURI().toURL().toExternalForm());
        player = new MediaPlayer(pick);
        trans= new TranslateTransition();
        sendfilename(filename);
        duration=plot.getduration();
        factor=plot.getsampfrq();
        numSamples=plot.getnumsamples();
        nfx.tempplot(classStage,filename, samples, numSamples, tab1, TP, wavepane, factor, duration);
        
        
    /*    
        String saveLocation = nfx.saveLocation();
          AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        //AudioSystem.write(audioInputStream, , saveLocation);
       int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
                System.out.println("size of the arrary"+size);
       StreamConverter.streamTowavefile(saveLocation, audioInputStream);
       sendfilename(saveLocation);
       double[] samples1 = plot.readWaveData(saveLocation);
       duration=plot.getduration();
       factor=plot.getsampfrq();
       numSamples=plot.getnumsamples();
       System.out.println("duration"+duration+" factor"+factor+" numSamples"+numSamples);
       nfx.tempplot(classStage,saveLocation, samples1, numSamples, tab1, TP, wavepane, factor, duration);
       */ 
       
      
       
       
       
}
}
 

 
 
 

 
     
    
    
    
    


