/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designtemplate;

import designtemplate.DesignTemplate;
import static designtemplate.DesignTemplate.getPW;
import static designtemplate.DesignTemplate.miliSec;
import static designtemplate.DesignTemplate.userNumber;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.SwingUtilities;
import plotwavform.PlotWave;

/**
 * FXML Controller class
 *
 * @author nazibur
 */
public class Tipause_debugController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
    
   // TiStrings tiStr;
    
    public boolean pause=false;
    int recordTime;
    String recordPath;
    @FXML
     ProgressBar progress5;
     @FXML
    AnchorPane anchorPane;
    Thread thrd2,thrd1;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        progress5.setVisible(false);
        final SwingNode swingNode = new SwingNode();
             createAndSetSwingContent(swingNode);
             anchorPane.getChildren().add(swingNode);
    }
    
    private void createAndSetSwingContent(final SwingNode swingNode) 
    {
             SwingUtilities.invokeLater(new Runnable() {
                 @Override
                 public void run() {
                     pw=new PlotWave(null);
                     swingNode.setContent(pw);
                     pw.recordSound();
                 }
             });
    }
    
    /*
       
    @FXML
    public void txtStop() throws IOException, InterruptedException, LineUnavailableException
    {
       
           if(((Button)DesignTemplate.getRoot().lookup("#txtTrnTI")).getText().equals("|| PAUSE"))
           {
               cancelTimer();
               stopRecord();
               
               System.out.println(recordTime);
               ((Button)DesignTemplate.getRoot().lookup("#txtTrnTI")).setText("|| RESUME");
               recordTime=DesignTemplate.countDown-1;
               System.out.println("RecordTime="+recordTime);
           }
           else
           {
               String tiTmpPath="/home/training_module/Text-independent/tmp/";
               miliSec=Long.toString(System.currentTimeMillis());
               
               ((Button)DesignTemplate.getRoot().lookup("#txtTrnTI")).setText("|| PAUSE");
               String str5="Ticonfirmed.fxml";
               countDowntimer2("#tiTimeleft",recordTime,str5,"#progress5");
               //repead reocrding 
               recordWavFile(tiTmpPath+userNumber+ "_TI_" + miliSec +  ".wav",recordTime);
               
                    if(getrepeatLoop()==1 && DesignTemplate.countDown==-1)
                    {
                        ((TextArea)DesignTemplate.getRoot().lookup("#txtArea3")).setText("He thought, If I can deprive him of all the earnings, I can have all the money for myself and live happily. After some time, he met Dharmabuddhi, \"My friend, we need to earn money to provide for ourselves when we grow old. Let us travel to some other kingdom to earn money. Besides, unless we travel to far-off kingdoms, we will not have any stories to tell our grand children! .");
                     
                    }
                 
                if(getrepeatLoop()==2 && DesignTemplate.countDown==-1)
                {
                     ((TextArea)DesignTemplate.getRoot().lookup("#txtArea3")).setText("After some time, pleased with their earnings, they planned to return home. Dharmabuddhi agreed to his plan, and took the blessings of his parents and teachers to travel to a distant kingdom.On an auspicious day, they began their journey.The two of them made a lot of money due to Dharmabuddhi's skills and knowledge. After some time, pleased with their earnings, they planned to return home.");
                   
                }
           }
    }
    */
    
    
    @FXML
    public void txtStopDebug()
    {
        if(((Button)DesignTemplate.getRoot().lookup("#txtTrnTI")).getText().equals("|| PAUSE"))
           {
               cancelTimerTi();
               stopWavFile();
               getPW().pauseSound();//pause recording
              /* thrd2=new Thread()
               {
                 public void run()
                 {
                     if(thrd1.isAlive())
                         thrd1.stop();
                    getPW().pauseSound();//pause recording
                 }
               };
               thrd2.start();
               */
              // getPW().pauseSound();
              // System.out.println(recordTime);
               ((Button)DesignTemplate.getRoot().lookup("#txtTrnTI")).setText("|| RESUME");
               recordTime=DesignTemplate.countDownTi-1;
               System.out.println("RecordTime="+recordTime);
           }
           else
           {
               String tiTmpPath="/home/training_module/Text-independent/tmp/";
               miliSec=Long.toString(System.currentTimeMillis());
               
               System.out.println("Resume clicked");
               ((Button)DesignTemplate.getRoot().lookup("#txtTrnTI")).setText("|| PAUSE");
               String str5="Ticonfirmed_debug.fxml";
               countDowntimer2("#tiTimeleft",recordTime,str5,"#progress5");
               //repead reocrding 
               Thread thrd=new Thread()
               {
                 public void run()
                 {
                      recordWavFile(tiTmpPath+userNumber+ "_TI_" + miliSec +  ".wav",recordTime);
                      getPW().resumeSound();//resume recording
                
                      if(getrepeatLoop()==1 && DesignTemplate.countDownTi==-1)
                      {
                          ((TextArea)DesignTemplate.getRoot().lookup("#txtArea3")).setText("He thought, If I can deprive him of all the earnings, I can have all the money for myself and live happily. After some time, he met Dharmabuddhi, \"My friend, we need to earn money to provide for ourselves when we grow old. Let us travel to some other kingdom to earn money. Besides, unless we travel to far-off kingdoms, we will not have any stories to tell our grand children! .");
                      }
                      if(getrepeatLoop()==2 && DesignTemplate.countDownTi==-1)
                      {
                          ((TextArea)DesignTemplate.getRoot().lookup("#txtArea3")).setText("After some time, pleased with their earnings, they planned to return home. Dharmabuddhi agreed to his plan, and took the blessings of his parents and teachers to travel to a distant kingdom.On an auspicious day, they began their journey.The two of them made a lot of money due to Dharmabuddhi's skills and knowledge. After some time, pleased with their earnings, they planned to return home.");
                      }
                 }
               };
               thrd.start();
               
               
               thrd1=new Thread()
               {
                 public void run()
                 {
                     getPW().resumeSound();//resume recording
                 }
               };
               thrd1.start();
              /* recordWavFile(tiTmpPath+userNumber+ "_TI_" + miliSec +  ".wav",recordTime);
               
                getPW().resumeSound();//resume recording
                
                    if(getrepeatLoop()==1 && DesignTemplate.countDownTi==-1)
                    {
                        ((TextArea)DesignTemplate.getRoot().lookup("#txtArea3")).setText("He thought, If I can deprive him of all the earnings, I can have all the money for myself and live happily. After some time, he met Dharmabuddhi, \"My friend, we need to earn money to provide for ourselves when we grow old. Let us travel to some other kingdom to earn money. Besides, unless we travel to far-off kingdoms, we will not have any stories to tell our grand children! .");
                     
                    }
                 
                if(getrepeatLoop()==2 && DesignTemplate.countDownTi==-1)
                {
                     ((TextArea)DesignTemplate.getRoot().lookup("#txtArea3")).setText("After some time, pleased with their earnings, they planned to return home. Dharmabuddhi agreed to his plan, and took the blessings of his parents and teachers to travel to a distant kingdom.On an auspicious day, they began their journey.The two of them made a lot of money due to Dharmabuddhi's skills and knowledge. After some time, pleased with their earnings, they planned to return home.");
                   
                }*/
           }
    }
}
