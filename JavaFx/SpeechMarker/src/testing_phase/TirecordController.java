/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing_phase;

import designtemplate.DesignTemplate;
import static designtemplate.DesignTemplate.testID;
import designtemplate.TDNeedHelpController;
import designtemplate.TINeedHelpController;
import designtemplate.micDebugModeController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;
import java.util.Random;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author nazibur
 */

public class TirecordController extends DesignTemplate implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    String ti;
    Process p;
    
    @FXML
    Label tstTI;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Random num=new Random();
        int randomInt=num.nextInt(12);
        TISectnce=randomInt;
        
        if(randomInt==1)
            tstTI.setText("\"From the practical point of view, it should receive a great boost. English serves as a window to the world. Don't trust other people to do important things for you. You have to do things yourself to control the quality of results.\"");
        else if(randomInt==2)
            tstTI.setText("\"In a multilingual state of ours, English may wonderfully serve as an effective auxiliary language. The entire spectrum of education, science and technology has been benefited through the introduction of English language\"");
        else if(randomInt==3)
            tstTI.setText("\"Education indicates a degree of sophistication. Formal education is an important vehicle of socialization.To live a creative life, we must lose our fear of being wrong\"");
        else if(randomInt==4)
            tstTI.setText("\"An ideal student must be an ideal symbol of honesty, duty and also take active part in different social activities.The ones who are crazy enough to think they can change the world, are the ones who do.\"");
        else if(randomInt==5)
            tstTI.setText("\"Sometimes it's important to know when to give up and run away, instead of always acting brave and maybe getting hurt. Try not to become a person of success, but rather try to become a person of value.\"");
        else if(randomInt==6)
            tstTI.setText("\"If something takes time to finish, don't watch it too closely because it will seem like it's taking forever. A successful man is one who can lay a firm foundation with the bricks others have thrown at him\"");
        else if(randomInt==7)
            tstTI.setText("\"Don't try to improve something that already works fairly well. You'll probably end up causing new problems.The ones who are crazy enough to think they can change the world, are the ones who do.\"");
        else if(randomInt==8)
            tstTI.setText("\"Don't trust other people to do important things for you. You have to do things yourself to control the quality of results.Try not to become a person of success, but rather try to become a person of value.\"");
        else if(randomInt==9)
            tstTI.setText("\"The entire spectrum of education, science and technology has been benefited through the introduction of English language.Try not to become a person of success, but rather try to become a person of value.\"");
        else if(randomInt==10)
            tstTI.setText("\"Strong people don't give up when they come across challenges. They just work harder. A successful man is one who can lay a firm foundation with the bricks others have thrown at him\"");
         else if(randomInt==11)
            tstTI.setText("\"Great minds discuss ideas; average minds discuss events; small minds discuss people.A successful man is one who can lay a firm foundation with the bricks others have thrown at him\"");
      /*  else if(randomInt==12)
            tstTI.setText("\"A successful man is one who can lay a firm foundation with the bricks others have thrown at him.\"");
        else if(randomInt==13)
            tstTI.setText("\"Knowledge is being aware of what you can do. Wisdom is knowing when not to do it.\"");
        else if(randomInt==14)
            tstTI.setText("\"When I dare to be powerful, to use my strength in the service of my vision, then it becomes less and less important whether I am afraid.\"");
        else if(randomInt==15)
            tstTI.setText("\"If you want to make a permanent change, stop focusing on the size of your problems and start focusing on the size of you.\"");
        else if(randomInt==16)
            tstTI.setText("\"Things work out best for those who make the best of how things work out.\"");
        else if(randomInt==17)
            tstTI.setText("\"To live a creative life, we must lose our fear of being wrong.\"");
        else if(randomInt==18)
            tstTI.setText("\"If you are not willing to risk the usual you will have to settle for the ordinary.\"");
        else if(randomInt==19)
            tstTI.setText("\"Trust because you are willing to accept the risk, not because it's safe or certain.\"");
        else if(randomInt==20)
            tstTI.setText("\"All our dreams can come true if we have the courage to pursue them.\"");
        else if(randomInt==21)
            tstTI.setText("\"Good things come to people who wait, but better things come to those who go out and get them.\"");
        else if(randomInt==22)
            tstTI.setText("\"Success is walking from failure to failure with no loss of enthusiasm.\"");
        else if(randomInt==23)
            tstTI.setText("\"Just when the caterpillar thought the world was ending, he turned into a butterfly.\"");
        else if(randomInt==24)
            tstTI.setText("\"Successful entrepreneurs are givers and not takers of positive energy.\"");
        else if(randomInt==25)
            tstTI.setText("\"Whenever you see a successful person you only see the public glories, never the private sacrifices to reach them.\"");
        else if(randomInt==26)
            tstTI.setText("\"Try not to become a person of success, but rather try to become a person of value.\"");
        else if(randomInt==27)
            tstTI.setText("\"Great minds discuss ideas; average minds discuss events; small minds discuss people.\"");
        else if(randomInt==28)
            tstTI.setText("\"If you don't value your time, neither will others. Stop giving away your time and talentsstart charging for it.\"");
        else if(randomInt==29)
            tstTI.setText("\"A successful man is one who can lay a firm foundation with the bricks others have thrown at him.\"");
        else if(randomInt==20)
            tstTI.setText("\"The whole secret of a successful life is to find out what is one's destiny to do, and then do it.\"");
        else if(randomInt==31)
            tstTI.setText("\"The ones who are crazy enough to think they can change the world, are the ones who do.\"");
        else if(randomInt==32)
            tstTI.setText("\"What seems to us as bitter trials are often blessings in disguise.\"");
        else if(randomInt==33)
            tstTI.setText("\"The meaning of life is to find your gift. The purpose of life is to give it away.\"");
        else if(randomInt==34)
            tstTI.setText("\"The distance between insanity and genius is measured only by success.\"");
        else if(randomInt==35)
            tstTI.setText("\"When you stop chasing the wrong things, you give the right things a chance to catch you.\"");
        else if(randomInt==36)
            tstTI.setText("\"I believe that the only courage anybody ever needs is the courage to follow your own dreams.\"");
        else if(randomInt==37)
            tstTI.setText("\"If you can't explain it simply, you don't understand it well enough.\"");
        else if(randomInt==38)
            tstTI.setText("\"Blessed are those who can give without remembering and take without forgetting.\"");
        else if(randomInt==39)
            tstTI.setText("\"What's the point of being alive if you don't at least try to do something remarkable.\"");
        else if(randomInt==40)
            tstTI.setText("\"Life is not about finding yourself. Life is about creating yourself.\"");
        else if(randomInt==41)
            tstTI.setText("\"Nothing in the world is more common than unsuccessful people with talent.\"");
        else if(randomInt==42)
            tstTI.setText("\"Knowledge is being aware of what you can do. Wisdom is knowing when not to do it.\"");
        else if(randomInt==43)
            tstTI.setText("\"Thinking should become your capital asset, no matter whatever ups and downs you come across in your life.\"");
        else if(randomInt==44)
            tstTI.setText("\"If you want to achieve excellence, you can get there today. As of this second, quit doing less-than-excellent work.\"");
        else if(randomInt==45)
            tstTI.setText("\"You may only succeed if you desire succeeding; you may only fail if you do not mind failing.\"");
        else if(randomInt==46)
            tstTI.setText("\"Courage is resistance to fear, mastery of fearnot absence of fear.\"");
        else if(randomInt==47)
            tstTI.setText("\"Only put off until tomorrow what you are willing to die having left undone.\"");
        else if(randomInt==48)
            tstTI.setText("\"People often say that motivation doesn't last. Well, neither does bathingthat's why we recommend it daily.\"");
        else if(randomInt==49)
            tstTI.setText("\"We become what we think about most of the time, and that's the strangest secret.\"");
        else if(randomInt==50)
            tstTI.setText("\"You've got to get up every morning with determination if you're going to go to bed with satisfaction.\"");
        else if(randomInt==51)
            tstTI.setText("\"The first step toward success is taken when you refuse to be a captive of the environment in which you first find yourself.\"");
        else if(randomInt==52)
            tstTI.setText("\"Our greatest fear should not be of failure but of succeeding at things in life that don't really matter.\"");
        else if(randomInt==53)
            tstTI.setText("\"The number one reason people fail in life is because they listen to their friends, family, and neighbors.\"");
        else if(randomInt==54)
            tstTI.setText("\"There is no chance, no destiny, no fate, that can hinder or control the firm resolve of a determined soul.\"");
        else if(randomInt==55)
            tstTI.setText("\"To accomplish great things, we must not only act, but also dream, not only plan, but also believe.\"");
        else if(randomInt==56)
            tstTI.setText("\"Develop success from failures. Discouragement and failure are two of the surest stepping stones to success.\"");
        else if(randomInt==57)
            tstTI.setText("\"Two roads diverged in a wood and I  took the one less traveled by, and that made all the difference.\"");
        else if(randomInt==58)
            tstTI.setText("\"The reason most people never reach their goals is that they don't define them, or ever seriously consider them as believable or achievable.\"");
        else if(randomInt==59)
            tstTI.setText("\"Winners can tell you where they are going, what they plan to do along the way, and who will be sharing the adventure with them.\"");
        else if(randomInt==60)
            tstTI.setText("\"In my experience, there is only one motivation, and that is desire. No reasons or principle contain it or stand against it.\"");
*/
    } 
    
    
    @FXML
    public void txtIndependent() throws InterruptedException
    {
          ti=getTestSpkrId()+"_"+getMSec()+"_TI.wav";
        if(usrmode)
        {
            try
            {
                FXMLLoader loader2 = new FXMLLoader();        
                loader2.setLocation(DesignTemplate.class.getResource("/testing_phase/Tipause.fxml"));           
                AnchorPane renext= (AnchorPane)loader2.load();                   
                BorderPane border2=DesignTemplate.getRoot();           
                border2.setLeft(renext); 
                TipauseController control=loader2.getController();
                String str5="/testing_phase/Ticonfirmed.fxml";
                String str4="/testing_phase/UserLastPage.fxml";
                countDowntimerTest("#tiTimeleft",10,str5,str4,"#progress5",ti,"TI");
               // countDowntimer("#tiTimeleft",10,str5,"#progress5");
                System.out.println("Repeat"+repeat);
               
                String tiPath="/home/testing_module/Text-independent/";
                String OtherFilesPath="/home/training_module/Otherfiles/";
                String pathMfcc="/home/testing_module/Mfcc/Text-independent/";
                
            
                Thread thd=new Thread()
                {
                    public void run()
                    {
                        try 
                        {
                             miliSec=Long.toString(System.currentTimeMillis());
                             p=Runtime.getRuntime().exec("mkdir -p /home/testing_module/Text-independent/"+testID);
                             p=Runtime.getRuntime().exec("mkdir -p /home/testing_module/Mfcc/Text-independent/"+testID);
                             p.waitFor();
                             p=Runtime.getRuntime().exec("sh src/designtemplate/shellscripts/exportLib.sh ");
                             p.waitFor();
                           // int iter = getrepeatLoop() + 1;
                            recordWavFile(tiPath+testID+"/"+ti,10000);
                            
                            
                            ((ImageView)DesignTemplate.getRoot().lookup("#processing")).setVisible(true);
                            
                          //  p=Runtime.getRuntime().exec("src/designtemplate/shellscripts/exportLib.sh ");
                          //  p.waitFor();
                            
                           // System.out.println("done");
                       
                          /*  while(DesignTemplate.countDownTi==-1)
                            {
                                
                                File extn=new File("/home/testing_module/Text-independent/"+getTestSpkrId()+"/"+ti);
                                String str1=extn.getName();
// vp_wav=str1;//assign string name to another variable
                                int index=str1.indexOf("_");
                                String str=str1.substring(0,index);
                               // System.out.printf(str);
                              
                                p=Runtime.getRuntime().exec("export LD_LIBRARY_PATH=/opt/intel/composerxe-2011/lib/ia32:/opt/intel/mkl/lib/ia32");
                                p.waitFor();
                                p=Runtime.getRuntime().exec("python src/designtemplate/shellscripts/ivect_testing.py  "+getTestSpkrId()+" "+ti);
                                p.waitFor();
                                BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
                                String assign;
                                while((assign=br.readLine())!=null)
                                {
                                   if(assign.equals("pass"))
                                   {
                                       tiResult=true;
                                   }
                                }
                               
                            }*/
                        } 
                        catch (Exception ex) 
                        {
                            ex.printStackTrace();
                        }
                    }
                };
                thd.start();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                FXMLLoader loader2 = new FXMLLoader();        
                loader2.setLocation(DesignTemplate.class.getResource("/testing_phase/Tipause_debug.fxml"));           
                AnchorPane renext= (AnchorPane)loader2.load();                   
                BorderPane border2=DesignTemplate.getRoot();           
                border2.setLeft(renext); 
                Tipause_debugController control=loader2.getController();
                String str5="/testing_phase/Ticonfirmed_debug.fxml";
                String str4="/testing_phase/UserLastPage.fxml";
                //countDowntimer("#tiTimeleft",10,str5,"#progress5");
                 countDowntimerDebug("#tiTimeleft",10,str5,"#progress5",ti,"TI");
                
                
                System.out.println("Repeat"+repeat);
             
                String tiPath="/home/testing_module/Text-independent/";
                String OtherFilesPath="/home/training_module/Otherfiles/";
                String pathMfcc="/home/testing_module/Mfcc/Text-independent/";
            
                Thread thd=new Thread()
                {
                    public void run()
                    {
                        try 
                        {
                            //miliSec=Long.toString(System.currentTimeMillis());
                            p=Runtime.getRuntime().exec("mkdir -p /home/testing_module/Text-independent/"+testID);
                            p=Runtime.getRuntime().exec("mkdir -p /home/testing_module/Mfcc/Text-independent/"+testID);
                            p.waitFor();
                            //int iter = getrepeatLoop() + 1;
                            recordWavFile(tiPath+testID+"/"+ti,10000);
                            
                            p=Runtime.getRuntime().exec("sh src/designtemplate/shellscripts/exportLib.sh ");
                            p.waitFor();
                            
                             ((ImageView)DesignTemplate.getRoot().lookup("#processingDebug")).setVisible(true);
                           
                        } 
                        catch (Exception ex) 
                        {
                            ex.printStackTrace();
                        }
                    }
                };
                thd.start();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    
    @FXML
    public void loadVP()
    {
        try 
        {
             
            FXMLLoader loader = new FXMLLoader();        
            loader.setLocation(DesignTemplate.class.getResource("/testing_phase/OvalPart_test.fxml"));           
            AnchorPane oval = (AnchorPane) loader.load();                   
            BorderPane border=DesignTemplate.getRoot();           
            border.setLeft(oval);           
            OvalPart_testController controller1 =(OvalPart_testController) loader.getController();
            //((Label)DesignTemplate.getRoot().lookup("#vptimer")).setText("pallavi");
            
            
         }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
       @FXML
    public void loadTD()
    {
        try 
        {
             
            FXMLLoader loader = new FXMLLoader();        
            loader.setLocation(DesignTemplate.class.getResource("/testing_phase/Td1record.fxml"));           
            AnchorPane oval = (AnchorPane) loader.load();                   
            BorderPane border=DesignTemplate.getRoot();           
            border.setLeft(oval);           
            Td1recordController controller1 =(Td1recordController) loader.getController();
            //((Label)DesignTemplate.getRoot().lookup("#vptimer")).setText("pallavi");
            
            
         }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    
     @FXML
    public void NeedHelp()
    {
        try {
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(DesignTemplate.class.getResource("TINeedHelp.fxml"));
            AnchorPane record= (AnchorPane)loader1.load();
            Platform.setImplicitExit(false);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("TI help");
            stage.initOwner(getDP().getPrimaryStage());
            Scene scene = new Scene(record);
            stage.setScene(scene);
            TINeedHelpController controller=loader1.getController();
            controller.setdialogStage(stage);
       // stage.onCloseRequestProperty();
            stage.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
       
    @FXML
    public void toHome()
    {
        try {
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(DesignTemplate.class.getResource("MainPage.fxml"));
            AnchorPane record= (AnchorPane)loader1.load();
            BorderPane border1=DesignTemplate.getRoot();
            border1.setLeft(record);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
     @FXML
    public void micCheck() throws IOException
    {
        if(usrmode)
        {
            FXMLLoader loader1 = new FXMLLoader();        
            loader1.setLocation(DesignTemplate.class.getResource("/testing_phase/MicTesting.fxml"));           
            AnchorPane record= (AnchorPane)loader1.load();
            Platform.setImplicitExit(false);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Mic Testing");
            stage.initOwner(getDP().getPrimaryStage());
        
        //remove title bar
        //stage.initStyle(StageStyle.UNDECORATED);
        
            Scene scene = new Scene(record);
            stage.setScene(scene);
            MicTestingController controller=loader1.getController();
            controller.setdialogStage(stage);
       // stage.onCloseRequestProperty();
            stage.showAndWait();
        }
        else
        {
            FXMLLoader loader1 = new FXMLLoader();        
            loader1.setLocation(DesignTemplate.class.getResource("micDebugMode.fxml"));           
            AnchorPane record= (AnchorPane)loader1.load();
            Platform.setImplicitExit(false);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Mic Testing");
            stage.initOwner(getDP().getPrimaryStage());
        
        //remove title bar
        //stage.initStyle(StageStyle.UNDECORATED);
        
            
            Scene scene = new Scene(record);
            stage.setScene(scene);
            micDebugModeController controller=loader1.getController();
            controller.setdialogStage(stage);
       // stage.onCloseRequestProperty();
            stage.showAndWait();
        }
        
        
    }
    
}
