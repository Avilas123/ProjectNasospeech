package plotwave;

//import java.io.File;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

public class FXMLDocumentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button single_file_button;

    @FXML
    private Button Multi_file_button;

    @FXML
    private ListView show_list;

    @FXML
    void handleButtonAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert single_file_button != null : "fx:id=\"single_file_button\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert Multi_file_button != null : "fx:id=\"Multi_file_button\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert show_list != null : "fx:id=\"show_list\" was not injected: check your FXML file 'FXMLDocument.fxml'.";

    }
    
    public void single_file_button_Event(ActionEvent event){
    FileChooser fc=new FileChooser();
    File selectedfile=fc.showOpenDialog(null);
    if(selectedfile!=null){
    show_list.getItems().add(selectedfile.getName());
    }
    else{
        System.out.println("file not valid");
    }    
    }
    
    public void Multi_file_button_Event(ActionEvent event){
    
    
    
    
    
    
    
    
}
}