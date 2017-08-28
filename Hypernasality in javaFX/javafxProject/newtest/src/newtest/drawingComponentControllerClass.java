package newtest;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class drawingComponentControllerClass{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane displayfilenameherelabel;

    @FXML
    private Rectangle displaycolorrectangle;

    @FXML
    private Label displayratingvaluelabel;

    @FXML
    private Label displayratinglabel;

    @FXML
    private Label displayfilenamelhereabel;

    @FXML
    void initialize() {
        assert displayfilenameherelabel != null : "fx:id=\"displayfilenameherelabel\" was not injected: check your FXML file 'drawingComponent.fxml'.";
        assert displaycolorrectangle != null : "fx:id=\"displaycolorrectangle\" was not injected: check your FXML file 'drawingComponent.fxml'.";
        assert displayratingvaluelabel != null : "fx:id=\"displayratingvaluelabel\" was not injected: check your FXML file 'drawingComponent.fxml'.";
        assert displayratinglabel != null : "fx:id=\"displayratinglabel\" was not injected: check your FXML file 'drawingComponent.fxml'.";
        assert displayfilenamelhereabel != null : "fx:id=\"displayfilenamelhereabel\" was not injected: check your FXML file 'drawingComponent.fxml'.";

    }
    static Stage classStage = new Stage();
    public void start(Stage primaryStage) throws Exception {
        classStage=primaryStage;
         Parent root = FXMLLoader.load(getClass().getResource("drawingComponent.fxml"));
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
}
