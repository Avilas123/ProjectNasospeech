/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;
//package Icons;
import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.border.LineBorder;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author user
 */
public class WaveformPanelContainer extends JPanel {
    private ArrayList singleChannelWaveformPanels = new ArrayList();
    private AudioInfo audioInfo = null;
    //MainGuI main=new MainGuI();
    //main.
    

    public WaveformPanelContainer() {
        setLayout(new GridLayout(0,1));
    }

    public void setAudioToDisplay(AudioInputStream audioInputStream){
        singleChannelWaveformPanels = new ArrayList();
        audioInfo = new AudioInfo(audioInputStream);
        for (int t=0; t<audioInfo.getNumberOfChannels(); t++){
            SingleWaveformPanel waveformPanel
                    = new SingleWaveformPanel(audioInfo, t);
            singleChannelWaveformPanels.add(waveformPanel);
            add(createChannelDisplay(waveformPanel, t));
        }
    }

    private JComponent createChannelDisplay(SingleWaveformPanel waveformPanel, int index) {
        JScrollPane jScrollPane = new JScrollPane();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(waveformPanel, BorderLayout.CENTER);
        JMenuBar menubar =new JMenuBar();
        JMenu menu1 =new JMenu("File");
        JMenuItem menuitem1=new JMenuItem("Open");
        JMenuItem menuitem2=new JMenuItem("Save");
        JMenuItem menuitem3=new JMenuItem("Save as");
        JMenuItem menuitem4=new JMenuItem("Quit");
        menu1.add(menuitem1);
        menu1.add(menuitem2);
        menu1.add(menuitem3);
        menu1.add(menuitem4);
        
        JMenu menu2 =new JMenu("Edit");
        JMenuItem menuitem5=new JMenuItem("Play");
        JMenuItem menuitem6=new JMenuItem("Pause");
        JMenuItem menuitem7=new JMenuItem("Stop");
        JMenuItem menuitem8=new JMenuItem("Record");
        JMenuItem menuitem9=new JMenuItem("Crop");
        menu2.add(menuitem5);
        menu2.add(menuitem6);
        menu2.add(menuitem7);
        menu2.add(menuitem8);
        menu2.add(menuitem9);
        
        
        
        JMenu menu3 =new JMenu("Analyse");
        JMenuItem menuitem10=new JMenuItem("Analyse all");
        JMenu menu6 =new JMenu("Select and Analyse");
        menu3.add(menuitem10);
        menu3.add(menu6);
        JMenuItem menuitem11=new JMenuItem("Nasality");
        JMenuItem menuitem12=new JMenuItem("Articulation error");
        JMenuItem menuitem13=new JMenuItem("Intelligibility");
        JMenuItem menuitem14=new JMenuItem("Voicing error");
        menu6.add(menuitem11);
        menu6.add(menuitem12);
        menu6.add(menuitem13);
        menu6.add(menuitem14);
        
        
        
        JMenu menu4 =new JMenu("Result");
        
        
        JMenu menu7 =new JMenu("View");
        JMenuItem menuitem17=new JMenuItem("Zoom In");
        JMenuItem menuitem18 =new JMenuItem("Zoom Out");
        JMenuItem menuitem19 =new JMenuItem("Fit to Window");
        menu7.add(menuitem17);
        menu7.add(menuitem18);
        menu7.add(menuitem19);
        
        JMenu menu5 =new JMenu("About");
        JMenuItem menuitem15=new JMenuItem("Nasospeech");
        JMenuItem menuitem16=new JMenuItem("Help");
        menu5.add(menuitem15);
        menu5.add(menuitem16);
        
       JToolBar toolbar1=new JToolBar( );
       JButton fittoscreen =new JButton();
       fittoscreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Fit to Width.png")));
       fittoscreen.setFocusable(false);
       fittoscreen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fittoscreen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar1.add(fittoscreen);
        toolbar1.setFloatable(false);
       
       
       JButton zoomin =new JButton();
       zoomin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Zoom In.png")));
       zoomin.setFocusable(false);
       zoomin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
       zoomin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
       
       
       
       JButton zoomout =new JButton();
       zoomout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Zoom Out.png")));
       zoomout.setFocusable(false);
       zoomout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
       zoomout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
       toolbar1.add(zoomout); 
       toolbar1.add(zoomin);
        
        menubar.add(menu1);
        menubar.add(menu2);
        menubar.add(menu3);
        menubar.add(menu4);
        menubar.add(menu7);        
        menubar.add(menu5);
        //menubar.add(fittoscreen);
        //menubar.add(zoomin);        
        //menubar.add(zoomout);
        
        //JLabel label = new JLabel("Channel " + ++index);
       panel.add(menubar, BorderLayout.NORTH);
       JToolBar toolbar=new JToolBar( );
       JButton playb =new JButton();
       playb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/play-button.png")));
       playb.setFocusable(false);
        playb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(playb);
       
       JButton pauseb =new JButton();
       pauseb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/pause.png"))); // NOI18N
       pauseb.setFocusable(false);
       pauseb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
       pauseb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
       toolbar.add(pauseb); 
        
       JButton recordb =new JButton();
       recordb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/record.png"))); // NOI18N
        recordb.setFocusable(false);
        recordb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        recordb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
       toolbar.add(recordb); 
       
       
       
       
       JButton cropb =new JButton();
        cropb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/crop-tool-button.png"))); // NOI18N
        cropb.setFocusable(false);
        cropb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cropb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
       
      
       toolbar.add(cropb);
       toolbar.addSeparator(new Dimension(150, 0));
       toolbar.setFloatable(false);
       
       
       
       JPanel panel1 = new JPanel(new BorderLayout());
       JPanel panel2 = new JPanel(new BorderLayout());
       panel1.add(toolbar,BorderLayout.LINE_END);
       panel.add(panel1,BorderLayout.SOUTH);
       panel2.add(toolbar1,BorderLayout.NORTH);
       panel.add(panel2,BorderLayout.EAST);
       
       jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       jScrollPane.setViewportBorder(new LineBorder(Color.RED));
      // jScrollPane.add(panel, BorderLayout.CENTER);
       //waveformPanel.add(jScrollPane);
       //jScrollPane.getViewport().add(panel, null);
       
       
       
       
       
       
       
       
       
       
       
        //panel.add(label, BorderLayout.NORTH);
       // JButton button =new JButton();
        //button.setBackground(Color.yellow);
       // panel.add(button);
        //panel.add(button,BorderLayout.SOUTH);
        //to dssign add your code here
      
        
        
        
        
        
        
        
        

        return panel;
    }
    
    private void playSound() 
{
  try
  {
    // get the sound file as a resource out of my jar file;
    // the sound file must be in the same directory as this class file.
    // the input stream portion of this recipe comes from a javaworld.com article.
 //   InputStream inputStream = getClass().getResourceAsStream();
   // AudioStream audioStream = new AudioStream(inputStream);
    //AudioPlayer.player.start(audioStream);
      JFileChooser fc = new JFileChooser();
      File f =fc.getSelectedFile();

           // File file = new File(args[0]);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream (new FileInputStream (f)));
            AudioPlayer.player.start(audioInputStream);
  }
  catch (Exception e)
  {
    // a special way i'm handling logging in this application
   // if (debugFileWriter!=null) e.printStackTrace(debugFileWriter);
  System.out.print(e);
  }
}
    
    
    
    public void playbActionPerformed(java.awt.event.ActionEvent evt) {
        
       
       
       }


}
