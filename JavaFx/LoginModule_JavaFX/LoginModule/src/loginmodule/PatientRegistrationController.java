/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginmodule;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author IITG
 */
public class PatientRegistrationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    
   ObservableList<String> genderitems = FXCollections.observableArrayList("Male","Female","Others"); 
    
    
    @FXML
    private TextField pidtxt;

    @FXML
    private TextField fnametxt;

    @FXML
    private TextField mnametxt;

    @FXML
    private TextField lnametxt;

    @FXML
    private ComboBox<String> gcbox;

    @FXML
    private TextField phnumtxt;

    @FXML
    private TextField ntrtxt;

    @FXML
    private TextField fdrtxt;

    @FXML
    private TextArea addtxt;

    @FXML
    private DatePicker fvtxt;
    
    @FXML
    private Label pidlbl;

    @FXML
    private Label fnamelbl;

    @FXML
    private Label lnamelbl;

    @FXML
    private Label phnumlbl;

    @FXML
    private Label ntrlbl;

    @FXML
    private Label fvlbl;

    @FXML
    private Label fdrlbl;

    @FXML
    private Label addlbl;
    
    
    @FXML
    private Button resetbtn;

    @FXML
    private Button regbtn;
    
     

    @FXML
    void fnameclick(MouseEvent event) {
             fnametxt.setStyle("-fx-border-color: transparent;");
             fnamelbl.setText(" ");
             if(pidtxt.getText().equals("")){
                pidtxt.setStyle("-fx-border-color: red;");
                pidlbl.setStyle("-fx-text-fill: red;");
                pidlbl.setText("You cant leave this empty!");
        
        
        }

    }

    @FXML
    void fnclick(MouseEvent event) {
        fdrtxt.setStyle("-fx-border-color: transparent;");
             fdrlbl.setText(" ");
             if(fvtxt.getValue()==null){
                fvtxt.setStyle("-fx-border-color: red;");
                fvlbl.setStyle("-fx-text-fill: red;");
                fvlbl.setText("You cant leave this empty!");
             }

    }

       @FXML
    void fvclick(MouseEvent event) {


             fvtxt.setStyle("-fx-border-color: transparent;");
             fvlbl.setText(" ");
             if(ntrtxt.getText().equals("")){
                ntrtxt.setStyle("-fx-border-color: red;");
                ntrlbl.setStyle("-fx-text-fill: red;");
                ntrlbl.setText("You cant leave this empty!");
        
        
        }

    }

    @FXML
    void lnameclick(MouseEvent event) {
             lnametxt.setStyle("-fx-border-color: transparent;");
             lnamelbl.setText(" ");
             if(fnametxt.getText().equals("")){
                fnametxt.setStyle("-fx-border-color: red;");
                fnamelbl.setStyle("-fx-text-fill: red;");
                fnamelbl.setText("You cant leave this empty!");
        
        
        }

    }

    @FXML
    void mnameclick(MouseEvent event) {
        mnametxt.setStyle("-fx-border-color: transparent;");
             
             if(fnametxt.getText().equals("")){
                fnametxt.setStyle("-fx-border-color: red;");
                fnamelbl.setStyle("-fx-text-fill: red;");
                fnamelbl.setText("You cant leave this empty!");
        
        
        }

    }

    @FXML
    void ntrclick(MouseEvent event) {
             ntrtxt.setStyle("-fx-border-color: transparent;");
             ntrlbl.setText(" ");
             if(phnumtxt.getText().equals("")){
                phnumtxt.setStyle("-fx-border-color: red;");
                phnumlbl.setStyle("-fx-text-fill: red;");
                phnumlbl.setText("You cant leave this empty!");
        
        
        }

    }

    @FXML
    void phnumclick(MouseEvent event) {
             phnumtxt.setStyle("-fx-border-color: transparent;");
             phnumlbl.setText(" ");
             if(lnametxt.getText().equals("")){
                lnametxt.setStyle("-fx-border-color: red;");
                lnamelbl.setStyle("-fx-text-fill: red;");
                lnamelbl.setText("You cant leave this empty!");
        
        
        }

    }

    @FXML
    void pidclick(MouseEvent event) {
        
        pidtxt.setStyle("-fx-border-color: transparent;");
             pidlbl.setText(" ");

    }

    
   
            

  @FXML
    void addclick(MouseEvent event) {
         addtxt.setStyle("-fx-border-color: transparent;");
         addlbl.setText(" ");
             if(fdrtxt.getText().equals("")){
                fdrtxt.setStyle("-fx-border-color: red;");
                fdrlbl.setStyle("-fx-text-fill: red;");
                fdrlbl.setText("You cant leave this empty!");
        
        
        }

    }
    
    
    
    @FXML
    void resetclick(ActionEvent event) {
        fnametxt.setText("");
        mnametxt.setText("");
        lnametxt.setText("");
        pidtxt.setText("");
        phnumtxt.setText("");
        ntrtxt.setText("");
        fdrtxt.setText("");
        fvtxt.setValue(null);
       
        addtxt.setText("");
        gcbox.getSelectionModel().selectFirst();
        
        fnamelbl.setText("");
        lnamelbl.setText("");
        pidlbl.setText("");
        
        phnumlbl.setText("");
        ntrlbl.setText("");
        fdrlbl.setText("");
        addlbl.setText("");
        fvlbl.setText("");
        
        
        fnametxt.setStyle("-fx-border-color: transparent;");
        lnametxt.setStyle("-fx-border-color: transparent;");
        mnametxt.setStyle("-fx-border-color: transparent;");
        pidtxt.setStyle("-fx-border-color: transparent;");
        ntrtxt.setStyle("-fx-border-color: transparent;");
        fdrtxt.setStyle("-fx-border-color: transparent;");
        phnumtxt.setStyle("-fx-border-color: transparent;");
        fvtxt.setStyle("-fx-border-color: transparent;");
       
        addtxt.setStyle("-fx-border-color: transparent;");

    }
     private PreparedStatement pst1;
    
    @FXML
    void regclick(ActionEvent event) {
        if(addtxt.getText().equals("")){
                addtxt.setStyle("-fx-border-color: red;");
                addlbl.setStyle("-fx-text-fill: red;");
                addlbl.setText("You cant leave this empty!");
        
        
        }
        else if(pidtxt.getText().equals("")){
                pidtxt.setStyle("-fx-border-color: red;");
                pidlbl.setStyle("-fx-text-fill: red;");
                pidlbl.setText("You cant leave this empty!");
        
        
        }
        
        else  if(fvtxt.getValue()==null){
                fvtxt.setStyle("-fx-border-color: red;");
                fvlbl.setStyle("-fx-text-fill: red;");
                fvlbl.setText("You cant leave this empty!");
             }
        else if(ntrtxt.getText().equals("")){
                ntrtxt.setStyle("-fx-border-color: red;");
                ntrlbl.setStyle("-fx-text-fill: red;");
                ntrlbl.setText("You cant leave this empty!");
        
        
        }
        else  if(fnametxt.getText().equals("")){
                fnametxt.setStyle("-fx-border-color: red;");
                fnamelbl.setStyle("-fx-text-fill: red;");
                fnamelbl.setText("You cant leave this empty!");
        
        
        }
        else  if(phnumtxt.getText().equals("")){
                phnumtxt.setStyle("-fx-border-color: red;");
                phnumlbl.setStyle("-fx-text-fill: red;");
                phnumlbl.setText("You cant leave this empty!");
        
        
        }
        else  if(fdrtxt.getText().equals("")){
                fdrtxt.setStyle("-fx-border-color: red;");
                fdrlbl.setStyle("-fx-text-fill: red;");
                fdrlbl.setText("You cant leave this empty!");
        
        
        }
        
        
        
        
        
        else{
         try{
           Connection conn = LoginModule.connect();
        
        
        String query1 = "INSERT INTO Patient_Register_Table(Pid,FName,MName,LName,Ph_Num,Address,Gender,Ntr_of_Disorder,First_Visit,Folder_id)values(?,?,?,?,?,?,?,?,?,?)";
        pst1 = conn.prepareStatement(query1);
        pst1.setString(1,pidtxt.getText());
        pst1.setString(2,fnametxt.getText());
        pst1.setString(3,mnametxt.getText());
        pst1.setString(4,lnametxt.getText());
        pst1.setString(5,phnumtxt.getText());
        pst1.setString(6,addtxt.getText());
        pst1.setString(7, gcbox.getValue());
        pst1.setString(8,ntrtxt.getText());
        pst1.setString(9,fvtxt.getValue().toString());
        pst1.setString(10,fdrtxt.getText());
        
        
        pst1.execute();
        
        JOptionPane.showMessageDialog(null,"Registered Successfully");
        
       
            
        }
        catch (SQLException e){
        e.printStackTrace();
        }
        }

    }
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gcbox.setItems(genderitems);
       gcbox.getSelectionModel().selectFirst();
    }    
    
}
