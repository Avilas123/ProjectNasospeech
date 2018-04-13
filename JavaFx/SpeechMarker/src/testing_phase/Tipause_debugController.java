/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing_phase;

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
import javafx.scene.image.ImageView;
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
    @FXML
    Label tstTI;
    @FXML
    ImageView processingDebug;
     
    Thread thrd2,thrd1;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        processingDebug.setVisible(false);
        progress5.setVisible(false);
        
         if(getTISectnce()==1)
            tstTI.setText("\"From the practical point of view, it should receive a great boost. English serves as a window to the world. Don't trust other people to do important things for you. You have to do things yourself to control the quality of results.\"");
        else if(getTISectnce()==2)
            tstTI.setText("\"In a multilingual state of ours, English may wonderfully serve as an effective auxiliary language. The entire spectrum of education, science and technology has been benefited through the introduction of English language\"");
        else if(getTISectnce()==3)
            tstTI.setText("\"Education indicates a degree of sophistication. Formal education is an important vehicle of socialization.To live a creative life, we must lose our fear of being wrong\"");
        else if(getTISectnce()==4)
            tstTI.setText("\"An ideal student must be an ideal symbol of honesty, duty and also take active part in different social activities.The ones who are crazy enough to think they can change the world, are the ones who do.\"");
        else if(getTISectnce()==5)
            tstTI.setText("\"Sometimes it's important to know when to give up and run away, instead of always acting brave and maybe getting hurt. Try not to become a person of success, but rather try to become a person of value.\"");
        else if(getTISectnce()==6)
            tstTI.setText("\"If something takes time to finish, don't watch it too closely because it will seem like it's taking forever. A successful man is one who can lay a firm foundation with the bricks others have thrown at him\"");
        else if(getTISectnce()==7)
            tstTI.setText("\"Don't try to improve something that already works fairly well. You'll probably end up causing new problems.The ones who are crazy enough to think they can change the world, are the ones who do.\"");
        else if(getTISectnce()==8)
            tstTI.setText("\"Don't trust other people to do important things for you. You have to do things yourself to control the quality of results.Try not to become a person of success, but rather try to become a person of value.\"");
        else if(getTISectnce()==9)
            tstTI.setText("\"The entire spectrum of education, science and technology has been benefited through the introduction of English language.Try not to become a person of success, but rather try to become a person of value.\"");
        else if(getTISectnce()==10)
            tstTI.setText("\"Strong people don't give up when they come across challenges. They just work harder. A successful man is one who can lay a firm foundation with the bricks others have thrown at him\"");
         else if(getTISectnce()==11)
            tstTI.setText("\"Great minds discuss ideas; average minds discuss events; small minds discuss people.A successful man is one who can lay a firm foundation with the bricks others have thrown at him\"");


        
        
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
    public void txtStopDebug1()
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
    public void txtStopDebug()
    {
        try 
        {
            
            cancelTimer();
            stopWavFile();
            getPW().stopRecord();
            FXMLLoader loader2 = new FXMLLoader();        
            loader2.setLocation(DesignTemplate.class.getResource("/testing_phase/Tirecord.fxml"));           
            AnchorPane pause= (AnchorPane)loader2.load();                   
            BorderPane border2=DesignTemplate.getRoot();           
            border2.setLeft(pause);           
            TirecordController controller2=loader2.getController();
            //((Label)DesignTemplate.getRoot().lookup("#complete1")).setText("Recording on hold");
         }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
