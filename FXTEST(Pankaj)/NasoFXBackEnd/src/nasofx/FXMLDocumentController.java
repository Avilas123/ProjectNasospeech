/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

//import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPopupMenu;
import nasofx.SignalProc;
/**
 *
 * @author IITG
 */
public class FXMLDocumentController extends Application {
    
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
    private AnchorPane counterap;
       @FXML
    private MenuItem open_menu_item;
    
     
     double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
     @FXML
     final int bufSize = 16384;
    public int frames_per_pixel;
    //FormatControls formatControls = new FormatControls();
  Capture capture;
   // RecordWaveGraph recordGraph;
   // public Playback playback;
    public AudioInputStream audioInputStream;
   // public SamplingGraph samplingGraph;
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
    static Stage classStage = new Stage();
    private Thread thread;
    private AudioFormat format;  
    private int[] audioDataNormalize;
    private int normalizedValue = 3000;
    public SignalProc sigProc;
    
    @FXML
    private LineChart wave;
    
    NasoFX nfx=new NasoFX();
    
    
 

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
    
    
   


     
   


    
//    @Override
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
  
   
for (i = 3,a=0; i <2100; i+=50,a+=1)
{
    //double a = 0.1;
    String number = Double.toString(a);
    Text t = new Text(i, 23,number);
   //  double strokeWidth = t.getStrokeWidth();
     // System.out.println("the stroke width is  :  "+strokeWidth);
   // t.setStrokeWidth(0.1);
    t.setStyle("-fx-text-fill:white; -fx-font-size:10;");
       
      
       // t.setStrokeWidth(0.5);
        
       //t.setStrokeWidth(0.1);
     t.setStroke(Color.rgb(204, 204, 204));
  
       // System.out.println("the new  stroke width is  :  "+strokeWidth);
   
    
    
    
    Line line1 = new Line(i, 20, i, 322);
     line1.setStrokeWidth(0.1);
     
    line1.setStroke(Color.rgb(204, 204, 204));
    
   
    //Line line2 = new Line(0, i, 600, i);
    //line2.setStroke(Color.LIGHTGRAY);

    tab1ap.getChildren().addAll(line1,t);
    
}

        Line redLine = new Line(0, 5, 2100, 5);

    redLine.setStroke(Color.rgb(221, 221, 221));
    redLine.setStrokeWidth(10);
    tab1ap.getChildren().addAll(redLine);
    
marker.toFront();

//counterap.setStyle("-fx-border-color: black");

   
   
    
 
    }    
    
   
    
    @FXML
    
    public String open_button_Event(ActionEvent event) throws Exception
    {
        //show_list.getItems().add(selectedfile.getAbsolutePath());
    String filename;
    filename=this.fileopenmethod();//fileopenmethod();
    System.out.println("filename_Final-------->>>>>>\t"+filename);   
    

    this.audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
    int size = (int) this.audioInputStream.getFrameLength()*2;
    byte audioData [] = new byte[size];
    
    System.out.println("ffffff = "+  this.audioInputStream.getFrameLength() + "  " + this.audioInputStream.getFormat());
    int bytesRead = this.audioInputStream.read(audioData);
        System.out.println("bytes read = "+ bytesRead);
    format = audioInputStream.getFormat();
    int numSamples = (int) this.audioInputStream.getFrameLength();
    double samples[] = readAudioData(audioData);
           double[] data = nfx.getdata(samples);
           //for(int i=0;i<numSamples;i++)
           //  System.out.println(samples[i]);
    
        
      //for(int i=0;i<10;i++){
       // dataSeries1.getData().add(new XYChart.Data( i, samples[i]));
       //   }
     //wave.getData().add(dataSeries1);
    nfx.startforplotwave(classStage,samples,numSamples,filename);
    
   /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information Dialog displaying the filename");
    alert.setHeaderText(null);
    alert.setContentText(filename);
    alert.showAndWait();
    */
    
    
  
    // plotwavecontroller pwave=new plotwavecontroller();
     //pwave.start(plotwavecontroller.classStage);
   //  TabPaneSample tab=new TabPaneSample();
    
    
    return filename;
    
    }
    //@FXML
    //static Stage classStage = new Stage();
    
  //  @FXML
   // public void plotwave()
    
    
    
    
    
    @FXML
    public String fileopenmethod()
    {        
        
   String filename="";
   File fileDir = new File(System.getProperty("user.dir"));      
   FileChooser fc=new FileChooser();
   fc.setInitialDirectory(fileDir);
   fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("wave files","*.wav","*.wma","*.mp3"));
    
              //  File convertedFile = new File("conf/converted");
               // convertedFile.mkdir();           
   
    File selectedfile=fc.showOpenDialog(null);
    /*if(selectedfile!=null)
    {
              //  show_list.getItems().add(selectedfile.getAbsolutePath());
                 filename= selectedfile.getAbsolutePath();
               long start = System.currentTimeMillis();

                String source_file_name = (selectedfile.getName()).toLowerCase();//eg. test.mp3
                 System.out.println("source_file_name\t"+source_file_name);               
                int idx = source_file_name.lastIndexOf('.');
                String wav_file_name = source_file_name.substring(0, idx) + ".wav";
                System.out.println("wav_file_name\t"+wav_file_name);
                File convertedFile = new File("conf/converted");
                convertedFile.mkdir();
                File soxExefile = new File("sox/sox.exe");
                File allToWavExefile = new File("sox/AlltowavCmd.exe");
                Process pr;
                String sox_path = soxExefile.getAbsolutePath();
                String allToWav_path = allToWavExefile.getAbsolutePath();

                String source_file_path = selectedfile.getAbsolutePath();
                String wav_file_path = convertedFile.getAbsolutePath() + "/" + wav_file_name;
                 filename=wav_file_path;
                 System.out.println("filename ready to return \t"+filename);
                 
                 
                String sox_command = sox_path + " " + source_file_path + " -r 8k " + convertedFile.getAbsolutePath() + "/" + wav_file_name;
                //String mystring1 = sox_path+" -r 8k -e signed -b 8 -c 1 "+source_file_path+" d:\\converted\\"+newFileName;
                String AllToWav_command = allToWav_path + " " + source_file_path + " -O" + convertedFile.getAbsolutePath() + "/" + wav_file_name + " -S8000";
                try {
                    if ((source_file_name).endsWith(".mp3")) {


                        Process t = Runtime.getRuntime().exec(sox_command);
                        t.waitFor();


                        System.out.println("sox command: " + sox_command);
                        File wave = new File(wav_file_path);

                        if (!wave.exists()) {
                            System.out.println("Error : File not found !");
                        }

//comment1                        createAudioInputStream(wave, null, true);
                      //  loadB.setEnabled(false);
                        //lblloading.setText("Done");
                    } else if ((source_file_name).endsWith(".wma")) {
                        Process t = Runtime.getRuntime().exec(AllToWav_command);
                        t.waitFor();


                        System.out.println("All to wave command: " + AllToWav_command);
                        File wave = new File(wav_file_path);

                        if (!wave.exists()) {
                            System.out.println("Error: File not found !");
                        }

//comment2                        createAudioInputStream(wave, null, true);
                    //    loadB.setEnabled(false);
                        //lblloading.setText("Done");

                    } else { System.out.println("createaudioinputstream\t"+selectedfile);
                       createAudioInputStream(new File(selectedfile.getAbsolutePath()), null, true);
//                        loadB.setEnabled(false);
                    }
                    //--
                    double elapsed = (System.currentTimeMillis() - start) / 1000.0;
                    //lblprocTime.setText("Processing Time: " + elapsed + " sec");
                    //System.out.println("Processing Time: " + elapsed + " sec");
                } catch (IOException ex) {
                    //Logger.getLogger(this.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex1) {
                    // Logger.getLogger(this.class.getName()).log(Level.SEVERE, null, ex1);
                }
    
    
    }*/
    /*else
    {
        System.out.println("file not valid");
    } */      
   return selectedfile.getAbsolutePath();  
   
}   
   
    
    
    
    
    
    
        //Create Audio InputStream Method
    @FXML
    public void createAudioInputStream(File file, AudioInputStream audioStreamArray, boolean updateComponents) {
        System.out.println("Inside createaudioinputstream--->"+file);
        if (file != null && file.isFile()) {
            try {

                if (!(file.getName().toLowerCase().endsWith(".wav"))) {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("Not the required file format");
                   alert.showAndWait();

                    return;
                }

                this.file = file;
                this.audioInputStream = AudioSystem.getAudioInputStream(file);
                fileName = file.getName();
                abbfilePath = file.getAbsolutePath();

            } catch (UnsupportedAudioFileException ex) {
               // Logger.getLogger(PlotWave.class.getName()).log(Level.SEVERE, null, ex);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("Can Not Open The File");
                   alert.showAndWait();
                return;
            } catch (IOException ex) {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("Can Not Open The File");
                   alert.showAndWait();
                return;
            }
        } else if (audioStreamArray != null) {
            try {
                if (audioStreamArray.available() < 1) {
                    return;
                }

                this.audioInputStream = audioStreamArray;
                fileName = "Wave1.wav";

            } catch (IOException ex) {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("Can Not Open The File");
                   alert.showAndWait();
            }
        } else {
            // reportStatus("Audio file required.");
            return;
        }
        try {
            if (this.audioInputStream != null) {
                try {
                    if (this.audioInputStream.available() < 1) {
                        return;
                    }


                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("Can Not Open The File");
                   alert.showAndWait();
                    return;
                }
            }
            errStr = null;
       //     if (capture.thread == null) {
         //       playBtn.setEnabled(true);
          //  }

            //Set Bytes and Generated
            byte[] audioBytes = StreamConverter.streamTobyte(audioInputStream);
            //streamBytes.setOriginal(audioBytes);
            if (audioBytes == null) {
                audioInputStream = null;
                return;
            }
            streamBytes.setCurrent(audioBytes);
            audioBytes = null;
            audioInputStream = StreamConverter.byteTostream(streamBytes.getCurrent(), audioInputStream);
            //fileHashValue = Hash.getHashValue(audioInputStream);

           // int widthScreen = setNormalScreen(audioInputStream);
            //int widthScreen = 1492;
            //System.out.println("width screen = "+widthScreen);
          //  setGrphSizeinScreen(widthScreen);
            long milliseconds = (long) ((audioInputStream.getFrameLength() * 1000) / audioInputStream.getFormat().getFrameRate());
            duration = milliseconds / 1000.0;
             System.out.println("duration--------->"+duration);
//            waveB.setEnabled(true);
            if (updateComponents) {
                int [] createWaveForm_return_value = this.createWaveForm(streamBytes.getCurrent());
                System.out.println("createWaveForm_return_value---->"+createWaveForm_return_value);
            }
        } catch (Exception ex) {
            System.out.println("Error1 " + ex);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("Error1"+ex.toString());
                   alert.showAndWait();
        }
    }//End Audio Input stream

    
    public int[]createWaveForm(byte[] audiobyte){
    byte my_byte = 0;
            double y_last = 0;
            int[] audioData = null;
          //  private Thread thread;
        //private AudioFormat format;
       
            format = audioInputStream.getFormat();
            int numChannels = format.getChannels();
            long framesize = audioInputStream.getFrameLength();
            System.out.println("frame length = " + framesize);
            normalizedValue = 5000;
           //  Dimension d = getSize();
           // d = this.getSize();
          //  int w = d.width;
          //  int h = d.height;
       try {
                //Read Bytes from current Bytes
                if (audiobyte == null) {
                    try {
                        audiobyte = streamBytes.getCurrent();
                    } catch (Exception ex) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("Error1"+ex.toString());
                   alert.showAndWait();
                        //return;
                    }
                }

                try {
                    long milliseconds = (long) ((audioInputStream.getFrameLength() * 1000) / audioInputStream.getFormat().getFrameRate());
                    System.out.println("actual length  " +audioInputStream.getFrameLength());
                    duration = milliseconds / 1000.0;
                } catch (Exception er) {
                    System.err.println(er.getMessage());
                }





                //pix rate

                //frames_per_pixel = audiobyte.length / format.getFrameSize() / w;
               
             
                //End Normalized


                //Postion taken

               // int endPaint = jScrollPane1.getSize().width;
              //  System.out.println(endPaint);
               // System.out.println("jscrollPanel = "+jScrollPane1.getSize().width + "    horizontal = "+ jScrollPane1.getHorizontalScrollBar().getValue());
               // int startPaint = jScrollPane1.getHorizontalScrollBar().getValue();
               // System.out.println("pankaj tested\t"+startPaint);
               // endPaint = (startPaint + endPaint);
                
                
                int constantWidth = 1595;
                //System.out.println(w);
            //    int divFactor = w/constantWidth;
                
             //   frames_per_pixel = audiobyte.length /endPaint/2/divFactor;
                
                
                int startbytes = 0;//startPaint * frames_per_pixel*2 ;
                int endbytes = 2000;//endPaint * frames_per_pixel *2;
               
              //  jScrollPane1.getViewport().setViewPosition(new java.awt.Point((int) mousePosX1 - 50, 0));
               
               // System.out.println("end paint = "+endPaint + "  start paint = "+startPaint + " bytes "+audiobyte.length / format.getFrameSize() ) ;

                byte[] audioBytes = new byte[endbytes - startbytes];
                int newby = 0;
                for (int by = startbytes; by < endbytes - 10; by++) {
                    if (by < (audiobyte.length - 10)) {
                        audioBytes[newby++] = audiobyte[by];
                    }
                }


                //End read
//System.err.println(audioInputStream.getFormat().getFrameRate());
//Bit Calculation

                if (format.getSampleSizeInBits() == 16) {
                    int nlengthInSamples = audioBytes.length / 2;
                    audioData = new int[nlengthInSamples];
                    if (format.isBigEndian()) {
                        for (int i = 0; i < nlengthInSamples; i++) {
                            /* First byte is MSB (high order) */
                            int MSB = (int) audioBytes[2 * i];
                            /* Second byte is LSB (low order) */
                            int LSB = (int) audioBytes[2 * i + 1];
                            audioData[i] = MSB << 8 | (255 & LSB);
                        }
                    } else {
                        for (int i = 0; i < nlengthInSamples; i++) {
                            /* First byte is LSB (low order) */
                            int LSB = (int) audioBytes[2 * i];
                            /* Second byte is MSB (high order) */
                            int MSB = (int) audioBytes[2 * i + 1];
                            audioData[i] = MSB << 8 | (255 & LSB);
                            
                            
                           /* File fi=new File("C:\\TATA-V-42\\src\\Speech\\WavePanel\\Au.txt");
	                    FileWriter fw=new FileWriter(fi,true);
                            BufferedWriter tout = new BufferedWriter(fw);
	                    tout.write(String.valueOf(audioData[i]));
                            tout.newLine();
                            tout.flush();
                            tout.close();  */
                            //System.out.println(audioData[i]);
                        }
                        
//calculating the maximum and minimum amplitude 
                           int maximum = audioData[0];   // start with the first value
                           for (int m=1; m<audioData.length; m++) {
                           if (audioData[m] > maximum) {
                           maximum = audioData[m];   // new maximum
                           
                            }
                        }
                           
                         
                           int minimum = audioData[0];   // start with the first value
                           for (int k=1; k<audioData.length; k++) {
                           if (audioData[k] < minimum) {
                           minimum = audioData[k];   // new maximum
                           
                            }
                        }
                           
                            }
                        }
                                
                                     
                                
                            
                           
                 else if (format.getSampleSizeInBits() == 8) {
                    int nlengthInSamples = audioBytes.length;
                    audioData = new int[nlengthInSamples];
                    if (format.getEncoding().toString().startsWith("PCM_SIGN")) {
                        for (int i = 0; i < audioBytes.length; i++) {
                            audioData[i] = audioBytes[i];

                        }
                    } else {
                        for (int i = 0; i < audioBytes.length; i++) {
                            audioData[i] = audioBytes[i] - 128;

                        }
                    }
                }



//End Bit calcution
                if (capture.thread == null) {
                    //Normalized Bit
                    sigProc = new SignalProc();
                    audioDataNormalize = sigProc.doubleToInt(sigProc.normalize(sigProc.intToDouble(audioData), 1400));
                    //frames_per_pixel = audioBytes.length / format.getFrameSize() / w;
                    //End Normalized

                    //Start Normalized audio bytes

                } else {
                    sigProc = new SignalProc();
                    audioDataNormalize = sigProc.doubleToInt(sigProc.normalize(sigProc.intToDouble(audioData), 1400));
                    //  frames_per_pixel = audioBytes.length / format.getFrameSize() / w;
                }
                lines.removeAllElements();
                double inc = 0.02;
                // Calculate Screen Pixels                   
               /* for (double x = startPaint; x < endPaint && audioDataNormalize != null; x = x + 0.02) {
                    inc = inc + 0.02;
                    int idx = (int) (frames_per_pixel * numChannels * inc);
                    

                    if (idx >= audioDataNormalize.length) {
                        break;
                    }
                    if (format.getSampleSizeInBits() == 8) {

                        my_byte = (byte) audioDataNormalize[idx];
                    } else {
                        my_byte = (byte) (2100 * audioDataNormalize[idx] / 32000);
                    }
                   // double y_new = (double) (h * (100 - my_byte) / 240);
                    lines.add(new Line2D.Double(x, y_last, x, y_new));
                    y_last = y_new;

                }

                //End Calculation

*/


                if (capture.thread == null) {
                    //Create Annotation
                    try {
                       /* Speech.annotations.GetAnnotation annObj = new Speech.annotations.GetAnnotation(mainFrame.getConn());

                        //System.out.println(fileHashValue);
                        Thread.sleep(100);
                        annotationPos = annObj.getAll(fileHashValue);
*/

                    } catch (Exception er) {
                        System.err.println(er);
                    }
                }
                //End to create Annotation
                audioDataNormalize = null;
                audiobyte = null;
                audioData = null;
                audioBytes = null;
                sigProc = null;
               // repaint();
            } catch (Exception er) {
                System.err.println(er);
            }
    
    return   audioDataNormalize;
    
    
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        class Capture implements Runnable {

        TargetDataLine line;
        Thread thread;

        public void start() {
            errStr = null;
            thread = new Thread(this);
            thread.setName("Capture");
            thread.start();
        }

        public void stop() {
            thread = null;
        }

        private void shutDown(String message) {
            try {
                if ((errStr = message) != null && thread != null) {
                    thread = null;
                    //samplingGraph.stop();
//                    loadB.setEnabled(true);
                   // playBtn.setEnabled(true);
                   // pausBtn.setEnabled(false);

//                    waveB.setEnabled(true);
                 // captBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech.icons1/record.png")));
                  //  captBtn.setToolTipText("record");
                    System.err.println(errStr);
                    //samplingGraph.repaint();
                }
            } catch (Exception er) {
            }
        }

        private AudioFormat getAudioFormat() {
            float sampleRate = 8000.0F;
            //8000,11025,16000,22050,44100
            int sampleSizeInBits = 16;
            //8,16
            int channels = 1;
            //1,2
            boolean signed = true;
            //true,false
            boolean bigEndian = false;
            //true,false

            return new AudioFormat(sampleRate,
                    sampleSizeInBits,
                    channels,
                    signed,
                    bigEndian);
        }//end getAudioFormat

        @Override
        public void run() {
            try {
                duration = 0;
                audioInputStream = null;

                // define the required attributes for our line,
                // and make sure a compatible line is supported.

                try {
                    AudioFormat format = null;
                    DataLine.Info info = null;
                    try {

                        format = getAudioFormat();

                        info = new DataLine.Info(TargetDataLine.class,
                                format);
                        AudioSystem.getLine(info);

                        if (!AudioSystem.isLineSupported(info)) {
                            shutDown("Line matching " + info + " not supported.");
                            return;
                        }

                    } catch (Exception er) {
                    }

                    // get and open the target data line for capture.

                    try {
                        line = (TargetDataLine) AudioSystem.getLine(info);
                        line.open(format, line.getBufferSize());
                    } catch (LineUnavailableException ex) {
                        shutDown("Unable to open the line: " + ex);
                        return;
                    } catch (SecurityException ex) {
                        shutDown(ex.toString());
                        //JavaSound.showInfoDialog();
                        return;
                    } catch (Exception ex) {
                        shutDown(ex.toString());
                        return;
                    }

                    // play back the captured audio data
                    capOut = new ByteArrayOutputStream();
                    int frameSizeInBytes = format.getFrameSize();
                    int bufferLengthInFrames = line.getBufferSize() / 8;
                    int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
                    byte[] data = new byte[bufferLengthInBytes];
                    int numBytesRead, noofwrites = 1;


                    line.start();

                    while (thread != null) {
                        if ((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
                            break;
                        }


                        try {

                            FileOutputStream fos2 = new FileOutputStream("record/out2.wav", true);
                            fos2.write(data);
                            fos2.close();
                        } catch (Exception e1) {//Catch exception if any
                            System.err.println("Error: " + e1.getMessage());
                        }
                        capOut.write(data, 0, numBytesRead);
                    }

                    // we reached the end of the stream.  stop and close the line.
                    line.stop();
                    line.close();
                    line = null;

                    // stop and close the output stream
                    try {
                        capOut.flush();
                        capOut.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    // load bytes into the audio input stream for playback

                    byte audioBytes[] = capOut.toByteArray();


                    ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
                    audioInputStream = new AudioInputStream(bais, format, audioBytes.length / frameSizeInBytes);

                    String recordFile = "";//rightClick.saveLocation();       later add record here
                       if (recordFile == null) {
                        recordFile = "temp.wav";
                    }
                    StreamConverter.streamTowavefile(recordFile, audioInputStream);
                    audioInputStream = null;
                    File recordFileName = new File(recordFile);
                    if (recordFileName.exists()) {
                        createAudioInputStream(recordFileName, null, true);
                    } else {
                        //javax.swing.JOptionPane.showConfirmDialog(mainFrame, "Unable to create file");
                    }
                    //streamBytes.setOriginal(audioBytes);
              /*  streamBytes.setCurrent(audioBytes);
                     int widthScreen = setNormalScreen(audioInputStream);
                     setGrphSizeinScreen(widthScreen);
                     long milliseconds = (long) ((audioInputStream.getFrameLength() * 1000) / format.getFrameRate());
                     duration = milliseconds / 1000.0;


                     audioInputStream.reset();*/
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
                // saveToFile("saidesh", AudioFileFormat.Type.WAVE);
                // samplingGraph.createWaveForm(audioBytes);
            } catch (Exception er) {
                System.err.println(er);
            }
        }
    } // End class Capture

    
    
    
    
 double[] readAudioData(byte audioBytes[]){
     
     
      int audioData[]  = {};
      if (format.getSampleSizeInBits() == 16) {
                    int nlengthInSamples = audioBytes.length / 2;
                    audioData = new int[nlengthInSamples];
                    if (format.isBigEndian()) {
                        for (int i = 0; i < nlengthInSamples; i++) {
                            /* First byte is MSB (high order) */
                            int MSB = (int) audioBytes[2 * i];
                            /* Second byte is LSB (low order) */
                            int LSB = (int) audioBytes[2 * i + 1];
                            audioData[i] = MSB << 8 | (255 & LSB);
                        }
                    } else {
                        for (int i = 0; i < nlengthInSamples; i++) {
                            /* First byte is LSB (low order) */
                            int LSB = (int) audioBytes[2 * i];
                            /* Second byte is MSB (high order) */
                            int MSB = (int) audioBytes[2 * i + 1];
                            audioData[i] = MSB << 8 | (255 & LSB);
                            
                            
                           /* File fi=new File("C:\\TATA-V-42\\src\\Speech\\WavePanel\\Au.txt");
	                    FileWriter fw=new FileWriter(fi,true);
                            BufferedWriter tout = new BufferedWriter(fw);
	                    tout.write(String.valueOf(audioData[i]));
                            tout.newLine();
                            tout.flush();
                            tout.close();  */
                            //System.out.println(audioData[i]);
                        }
                        
//calculating the maximum and minimum amplitude 
                           int maximum = audioData[0];   // start with the first value
                           for (int m=1; m<audioData.length; m++) {
                           if (audioData[m] > maximum) {
                           maximum = audioData[m];   // new maximum
                           
                            }
                        }
                           
                         
                           int minimum = audioData[0];   // start with the first value
                           for (int k=1; k<audioData.length; k++) {
                           if (audioData[k] < minimum) {
                           minimum = audioData[k];   // new maximum
                           
                            }
                        }
                           
                            }
                        }
                                
                                     
                                
                            
                           
                 else if (format.getSampleSizeInBits() == 8) {
                    int nlengthInSamples = audioBytes.length;
                    audioData = new int[nlengthInSamples];
                    if (format.getEncoding().toString().startsWith("PCM_SIGN")) {
                        for (int i = 0; i < audioBytes.length; i++) {
                            audioData[i] = audioBytes[i];

                        }
                    } else {
                        for (int i = 0; i < audioBytes.length; i++) {
                            audioData[i] = audioBytes[i] - 128;

                        }
                    }
                }
      
                    sigProc = new SignalProc();
                    double[] audioDataNormalize1 = sigProc.normalize(sigProc.intToDouble(audioData), 1400);
      
      return audioDataNormalize1;
 }
    
    
    
    
    
    
    

}