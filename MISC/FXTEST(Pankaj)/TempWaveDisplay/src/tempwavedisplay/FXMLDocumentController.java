/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempwavedisplay;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.swing.JFileChooser;

/**
 *
 * @author DoD
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
       @FXML
   public void handleButtonActionnew(ActionEvent event)throws IOException,Exception
    {
    /*System.out.println("2nd handle U clicked me");
     Newtest object=new Newtest();
     object.displayalert();
    */
        //AlertController.launch(appClass, args);
        //alert.start(primaryStage);
         //Application.launch(AlertController.class,"newframe");
      //  AlertController alert=new AlertController();
        //alert.start(AlertController.classStage);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        //label.setText("Hello World!");
         /*wavePanel wv=new wavePanel();
        wv.setVisible(true);
      */
                String filename="";
        try {
             
            File fileDir = new File(System.getProperty("user.dir"));
            JFileChooser fc = new JFileChooser(fileDir);

            fc.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File file) {

                    if (file.isDirectory()) {
                        return true;
                    }
                    String name = file.getName().toLowerCase();
                    if (name.endsWith(".wav") || name.endsWith(".mp3") || name.endsWith(".wma")) {
                        return true;
                    }
                    return false;
                }

                @Override
                public String getDescription() {
                    return ".wav, .mp3, .wma";
                }
            });


            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                long start = System.currentTimeMillis();

                String source_file_name = (fc.getSelectedFile().getName()).toLowerCase();//eg. test.mp3

                int idx = source_file_name.lastIndexOf('.');
                String wav_file_name = source_file_name.substring(0, idx) + ".wav";

                File convertedFile = new File("conf/converted");
                convertedFile.mkdir();
                File soxExefile = new File("sox/sox.exe");
                File allToWavExefile = new File("sox/AlltowavCmd.exe");
                Process pr;
                String sox_path = soxExefile.getAbsolutePath();
                String allToWav_path = allToWavExefile.getAbsolutePath();

                String source_file_path = fc.getSelectedFile().getAbsolutePath();
                String wav_file_path = convertedFile.getAbsolutePath() + "/" + wav_file_name;
                 filename=wav_file_path;
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

                //        createAudioInputStream(wave, null, true);
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

                  //      createAudioInputStream(wave, null, true);
                    //    loadB.setEnabled(false);
                        //lblloading.setText("Done");

                    } else {
                   //     createAudioInputStream(fc.getSelectedFile(), null, true);
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
        } catch (SecurityException ex) {
            // JavaSound.showInfoDialog();
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       
      //mainFrame.setVisible(true);
     System.out.println("filename === "+ filename);
        
         
         
         
         
         
         
         
         
         
         
         
         
         
         
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
