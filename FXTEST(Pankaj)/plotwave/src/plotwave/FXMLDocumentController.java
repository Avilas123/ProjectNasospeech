package plotwave;

//import java.io.File;
//import java.awt.List;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
//import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;
import javax.swing.JPopupMenu;
import plotwave.plotwavecontroller.TabPaneSample;

public class FXMLDocumentController extends Application{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button single_file_button;

   

    @FXML
    private ListView show_list;
    //declaratio of the required attributes
    @FXML
     final int bufSize = 16384;
    public int frames_per_pixel;
    //FormatControls formatControls = new FormatControls();
  //  Capture capture;
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

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        //Parent root1 = FXMLLoader.load(getClass().getResource("Alert.fxml"));
        
        Scene scene = new Scene(root);
        //Scene scene1 = new Scene(root1);
        stage.setTitle("This is first Frame ");
        stage.setScene(scene);
        
        stage.show();
       
    }
    
    
    
    
    
    

    @FXML
    void handleButtonAction(ActionEvent event) {

    }

    @FXML
    void initialize() 
    {
        assert single_file_button != null : "fx:id=\"single_file_button\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
      //  assert Multi_file_button != null : "fx:id=\"Multi_file_button\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert show_list != null : "fx:id=\"show_list\" was not injected: check your FXML file 'FXMLDocument.fxml'.";

    }
    @FXML
    
    public String single_file_button_Event(ActionEvent event) throws Exception
    {
        //show_list.getItems().add(selectedfile.getAbsolutePath());
    String filename;
    filename=this.fileopenmethod();//fileopenmethod();
   System.out.println("filename_Final-------->>>>>>\t"+filename);   
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information Dialog displaying the filename");
    alert.setHeaderText(null);
    alert.setContentText(filename);
    alert.showAndWait();
  
     plotwavecontroller pwave=new plotwavecontroller();
     pwave.start(plotwavecontroller.classStage);
   //  TabPaneSample tab=new TabPaneSample();
    
    
    return filename;
    
    }
  
    
    @FXML
    public String fileopenmethod(){
        
        
        
        
        
    
    String filename="";
   File fileDir = new File(System.getProperty("user.dir"));      
   FileChooser fc=new FileChooser();
   fc.setInitialDirectory(fileDir);
   fc.getExtensionFilters().addAll(new ExtensionFilter("wave files","*.wav","*.wma","*.mp3"));
    
              //  File convertedFile = new File("conf/converted");
               // convertedFile.mkdir();           
   
    File selectedfile=fc.showOpenDialog(null);
    if(selectedfile!=null)
    {
                show_list.getItems().add(selectedfile.getAbsolutePath());
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
                String wav_file_path = convertedFile.getAbsolutePath() + "\\" + wav_file_name;
                 filename=wav_file_path;
                 System.out.println("filename ready to return \t"+filename);
                 
                 
                String sox_command = sox_path + " " + source_file_path + " -r 8k " + convertedFile.getAbsolutePath() + "\\" + wav_file_name;
                //String mystring1 = sox_path+" -r 8k -e signed -b 8 -c 1 "+source_file_path+" d:\\converted\\"+newFileName;
                String AllToWav_command = allToWav_path + " " + source_file_path + " -O" + convertedFile.getAbsolutePath() + "\\" + wav_file_name + " -S8000";
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
//comment3                        createAudioInputStream(fc.getSelectedFile(), null, true);
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
    
    
    }
    else
    {
        System.out.println("file not valid");
    }       
   return filename;
    
   
   
   
   
   
   
   
   
   
   
   
   
}       
    



    //Create Audio InputStream Method
    @FXML
    public void createAudioInputStream(File file, AudioInputStream audioStreamArray, boolean updateComponents) {
        System.out.println("Inside createaudioinputstream--->"+file);
        if (file != null && file.isFile()) {
            try {

                if (!(file.getName().toLowerCase().endsWith(".wav"))) {
                   Alert alert = new Alert(AlertType.INFORMATION);
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
                 Alert alert = new Alert(AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("Can Not Open The File");
                   alert.showAndWait();
                return;
            } catch (IOException ex) {
                 Alert alert = new Alert(AlertType.INFORMATION);
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
                 Alert alert = new Alert(AlertType.INFORMATION);
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
                    Alert alert = new Alert(AlertType.INFORMATION);
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
            fileHashValue = Hash.getHashValue(audioInputStream);

           // int widthScreen = setNormalScreen(audioInputStream);
            int widthScreen = 1492;
            System.out.println("width screen = "+widthScreen);
          //  setGrphSizeinScreen(widthScreen);
            long milliseconds = (long) ((audioInputStream.getFrameLength() * 1000) / audioInputStream.getFormat().getFrameRate());
            duration = milliseconds / 1000.0;
             System.out.println("duration--------->"+duration);
//            waveB.setEnabled(true);
            if (updateComponents) {
               // samplingGraph.createWaveForm(streamBytes.getCurrent());
            }
        } catch (Exception ex) {
            System.out.println("Error1 " + ex);
             Alert alert = new Alert(AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("Error1"+ex.toString());
                   alert.showAndWait();
        }
    }//End Audio Input stream


    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
 
    
    
    
    
    
    
    
    
    
    
    
    
    

}