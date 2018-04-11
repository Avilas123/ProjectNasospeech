/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plotwave;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JPopupMenu;

public class plotwavecontroller extends Application
{
    
    
    
    
    
    
    @FXML
    private TabPane Tap;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
       final int bufSize = 16384;
    public int frames_per_pixel;
    //FormatControls formatControls = new FormatControls();
  //  Capture capture;
  //  RecordWaveGraph recordGraph;
  //  public Playback playback;
    public AudioInputStream audioInputStream;
  //  public SamplingGraph samplingGraph;
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
 //   public MainFrame mainFrame;
   // public Mainpopup mainpopup;
 //   private SamplingGraph sg;
    private int graphFromScreen = 5, graphVerticalSize = 240, normalPixcel = 60;
    public boolean selectedPlay = false;
   // public StreamVariables streamVariable;
  //  public RightClickEvent rightClick;
    public String[][] annotationPos;
    public ByteArrayOutputStream capOut;
    private int samplingpanelSize;
    List<List<Integer>> dummyList;
 //   public ServerFinder serverStatus;
    public int xSize;
    public String fileHashValue;
    private double record_duration;
    private boolean buffStatus = true;
    private boolean lineStatus = false;
    public static String filenamefortab;
     private int tabNum = 0;
    public FXMLDocumentController fc;

    
    
    
    
    
    
    
    
    
    
    

    @FXML
    void initialize() 
    {

    }
    @FXML
   static Stage classStage = new Stage();
   
   @FXML
    public void start(Stage primaryStage) throws Exception {
        
        
        
        addtab();
        classStage=primaryStage;
         Parent root = FXMLLoader.load(getClass().getResource("plotwavefxml.fxml"));
        //Parent root1 = FXMLLoader.load(getClass().getResource("Alert.fxml"));
        
         Scene scene = new Scene(root);
        //Scene scene1 = new Scene(root1);
          
         classStage.setTitle("This is second Frame ");//setTitle("This is first Frame ");
       // stage.setScene(scene);
        classStage.setScene(scene);
        classStage.show();
        //stage.show();
        
        
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
 public Tab addtab() throws Exception
 {
     ActionEvent event = null;
    String filename=fc.single_file_button_Event( event);
   Tab tab = new Tab(filename);
   StackPane tabLayout = new StackPane();
   Tap.getTabs().add(tab);
   Tap.getSelectionModel().selectLast();
   tab.setContent(tabLayout);
   return tab;
 
 }
   
    
    
    
    
    
    
    
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }





public class TabPaneSample extends Application {
   
 
    private int tabNum = 0;
    //private static final Random random = new Random(42);
 
    @Override
    public void start(Stage stage) throws Exception {
        TabPane tabPane = new TabPane();
        tabPane.setPrefSize(200, 150);
 
        VBox layout = new VBox(10, 
                createTabControls(tabPane), 
                tabPane
        );
     //   layout.setPadding(new Insets(10));
        VBox.setVgrow(tabPane, Priority.ALWAYS);
 
        final Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }
 
    private Button createTabControls(TabPane tabPane) {
        Button addTab = new Button("New Tab");
        addTab.setOnAction(event -> {
           tabPane.getTabs().add(
                    createTab()
            );
            tabPane.getSelectionModel().selectLast();
        });
        addTab.setMinSize(
                addTab.USE_PREF_SIZE,
                addTab.USE_PREF_SIZE
        );
        return addTab;
    }
 
    private Tab createTab() {
        tabNum++;
        Tab tab = new Tab("Tab: " + tabNum);
 
        StackPane tabLayout = new StackPane();
      //  tabLayout.setStyle("-fx-background-color: " + randomRgbColorString());
        Label tabText = new Label("" + tabNum);
        tabText.setStyle("-fx-font-size: 40px;");
        tabLayout.getChildren().add(tabText);
 
        tab.setContent(tabLayout);
 
        return tab;
    }
 
   /* private String randomRgbColorString() {
        return "rgb("
                  + random.nextInt(255) + ", "
                    + random.nextInt(255) + ", "
                    + random.nextInt(255) + 
                ");";
    }*/
}


}