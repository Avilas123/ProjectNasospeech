/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designtemplate;

import static designtemplate.DesignTemplate.userNumber;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javax.sound.sampled.LineUnavailableException;

/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class TipauseController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    TiStrings tiStr;
    
    public boolean pause=false;
    int recordTime;
    String recordPath;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void txtStop() throws IOException, InterruptedException, LineUnavailableException
    {
       
           if(((Button)DesignTemplate.getRoot().lookup("#txtTrnTI")).getText().equals("|| PAUSE"))
           {
               cancelTimerTi();
               stopWavFile();
               
               System.out.println(recordTime);
               ((Button)DesignTemplate.getRoot().lookup("#txtTrnTI")).setText("|| RESUME");
               recordTime=DesignTemplate.countDownTi-1;
               System.out.println("RecordTime="+recordTime);
           }
           else
           {
               String tiTmpPath="/home/training_module/Text-independent/tmp/";
               String miliSec=Long.toString(System.currentTimeMillis());
               
               ((Button)DesignTemplate.getRoot().lookup("#txtTrnTI")).setText("|| PAUSE");
               String str5="Ticonfirmed.fxml";
               countDowntimer2("#tiTimeleft",recordTime,str5,"#progress5");
               //repead reocrding 
               Thread thrd=new Thread()
               {
                   public void run()
                   {
                       try {
                           recordWavFile(tiTmpPath+userNumber+ "_TI_" + miliSec +  ".wav",recordTime);
                           if(getrepeatLoop()==1 && DesignTemplate.countDownTi==-1)
                           {
                               ((TextArea)DesignTemplate.getRoot().lookup("#txtArea3")).setText("After some time, he met Dharmabuddhi, \"My friend, we need to earn money to provide for ourselves when we grow old. Let us travel to some other kingdom to earn money. Besides, unless we travel to far-off kingdoms, we will not have any stories to tell our grand children! .\"Dharmabuddhi agreed to his plan, and took the blessings of his parents and teachers to travel to a distant kingdom.On an auspicious day, they began their journey.");
                           }
                           if(getrepeatLoop()==2 && DesignTemplate.countDownTi==-1)
                           {
                               ((TextArea)DesignTemplate.getRoot().lookup("#txtArea3")).setText("The two of them made a lot of money due to Dharmabuddhi's skills and knowledge. After some time, pleased with their earnings, they planned to return home. On their way to their home, as they approached their village, Papabuddhi said to Dharmabuddhi, \"Friend, It will be improper for us to carry so much money home as all our friends and relatives will start requesting us money. Besides, there is danger of theft.");
                           }
                       } catch (Exception ex) {
                           Logger.getLogger(TipauseController.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
               };
                       thrd.start();
               /*recordWav(tiTmpPath+userNumber+ "_TI_" + miliSec +  ".wav",recordTime);
               
                    if(getrepeatLoop()==1 && DesignTemplate.countDownTi==-1)
                    {
                        ((TextArea)DesignTemplate.getRoot().lookup("#txtArea3")).setText("He thought, If I can deprive him of all the earnings, I can have all the money for myself and live happily. After some time, he met Dharmabuddhi, \"My friend, we need to earn money to provide for ourselves when we grow old. Let us travel to some other kingdom to earn money. Besides, unless we travel to far-off kingdoms, we will not have any stories to tell our grand children! .");
                     
                    }
                 
                if(getrepeatLoop()==2 && DesignTemplate.countDownTi==-1)
                {
                     ((TextArea)DesignTemplate.getRoot().lookup("#txtArea3")).setText("After some time, pleased with their earnings, they planned to return home. Dharmabuddhi agreed to his plan, and took the blessings of his parents and teachers to travel to a distant kingdom.On an auspicious day, they began their journey.The two of them made a lot of money due to Dharmabuddhi's skills and knowledge. After some time, pleased with their earnings, they planned to return home.");
                   
                }*/
           }
              
               
               
               
    
           
       
            
            
        
            /*FXMLLoader loader2 = new FXMLLoader();        
            loader2.setLocation(DesignTemplate.class.getResource("Ticonfirmed.fxml"));           
            AnchorPane renext= (AnchorPane)loader2.load();                   
            BorderPane border2=DesignTemplate.getRoot();           
            border2.setLeft(renext); 
            TiconfirmedController control=loader2.getController();
            ((Label)DesignTemplate.getRoot().lookup("#complete5")).setText("Recording on hold");*/
            
       //  }
        
    
    
}
}
