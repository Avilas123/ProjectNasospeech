package newtest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AlertController extends Application {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label messageLabel;

    @FXML
    private Label detailsLabel;

    @FXML
    private HBox actionParent;

    @FXML
    private Button actionButton;

    @FXML
    private Button cancelButton;

    @FXML
    private HBox okParent;

    @FXML
    private Button okButton;

    @FXML
    void initialize() {
        assert messageLabel != null : "fx:id=\"messageLabel\" was not injected: check your FXML file 'Alert.fxml'.";
        assert detailsLabel != null : "fx:id=\"detailsLabel\" was not injected: check your FXML file 'Alert.fxml'.";
        assert actionParent != null : "fx:id=\"actionParent\" was not injected: check your FXML file 'Alert.fxml'.";
        assert actionButton != null : "fx:id=\"actionButton\" was not injected: check your FXML file 'Alert.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'Alert.fxml'.";
        assert okParent != null : "fx:id=\"okParent\" was not injected: check your FXML file 'Alert.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'Alert.fxml'.";

    }
     static Stage classStage = new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception {
        classStage=primaryStage;
         Parent root = FXMLLoader.load(getClass().getResource("Alert.fxml"));
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
