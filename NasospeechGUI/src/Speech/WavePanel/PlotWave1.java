package Speech.WavePanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import Speech.gui.*;
import Speech.WavePanel.PlotWave;
//import Speech.WavePanel.PlotWave.Capture;
//import Speech.WavePanel.PlotWave.SamplingGraph;
import Speech.WavePanel.RightClickEvent;
import Speech.WavePanel.StreamBytes;
import Speech.annotations.Hash;
import Speech.common.PlotwaveCommon;
import Speech.common.StreamConverter;
import Speech.signalProcessing.SignalProc;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Line2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

             //To change body of generated methods, choose Tools | Templates.
        
/**
 *
 * @author saikat
 */
public class PlotWave1 extends javax.swing.JFrame implements ActionListener, PlotwaveCommon {

      public MainFrame mainFrame;
       public int frames_per_pixel;
         final int bufSize = 16384;
      Capture capture;
       File file;
       public String fileName = "untitled";
    public String abbfilePath = null;
     public String fileHashValue;
      public AudioInputStream audioInputStream;
      String errStr;
      double duration, seconds;
      public String[][] annotationPos;
       public ByteArrayOutputStream capOut;
       public RightClickEvent rightClick;
       public StreamBytes streamBytes;
         public SamplingGraph samplingGraph;
        // public ServerFinder serverStatus;
           private int graphFromScreen = 5, graphVerticalSize = 240, normalPixcel = 60;
            private Toolkit tk;
              private SamplingGraph sg;
               public PlotWave.Playback playback;
                 public boolean selectedPlay = false;
               Vector lines = new Vector();
       public int xSize;
       private int samplingpanelSize;
        public double mousePosX1, mousePosX2, mouseMoveX1, mousePosY1;
    private final AudioInputStream audioStreamArray;
   
 
   public PlotWave1(MainFrame mainFrame, AudioInputStream audioStreamArray) {
        initComponents();
        capture = new Capture();
        streamBytes = new StreamBytes();
        this.mainFrame = mainFrame;
        EmptyBorder eb= new EmptyBorder(10,20,20,20);
        EmptyBorder subeb = new EmptyBorder(10,20,20,20);
        SoftBevelBorder sbb= new SoftBevelBorder(SoftBevelBorder.LOWERED);
        SoftBevelBorder subsbb= new SoftBevelBorder(SoftBevelBorder.LOWERED);
        sg= new SamplingGraph();
      //  serverStatus = new ServerFinder();
        //streamVariable = new streamVariable();
        this.tk= Toolkit.getDefaultToolkit();
        xSize=(int) tk.getScreenSize().getHeight();
        int ySize= (int) tk.getScreenSize().getWidth();
        xSize= xSize-290;
        this.setSize(new Dimension((xSize), 320));
        samplingpanelSize= xSize;
        samplingpanel.setBorder(new CompoundBorder(eb,sbb));
        sg.setSize(new Dimension((xSize- graphFromScreen), graphVerticalSize));
        samplingpanel.setPreferredSize(new java.awt.Dimension(xSize - graphFromScreen,graphVerticalSize));
        jScrollPane1.setSize(new java.awt.Dimension((xSize - graphFromScreen), graphVerticalSize));
        jScrollPane1.setPreferredSize(new java.awt.Dimension((xSize - graphFromScreen), graphVerticalSize));
        jScrollPane1.setMinimumSize(new java.awt.Dimension((xSize - graphFromScreen), graphVerticalSize));
        
         jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.getHorizontalScrollBar().addAdjustmentListener(adjustmentListener);
        jScrollPane1.getHorizontalScrollBar().setUnitIncrement(100);
        this.mainFrame = mainFrame;
        samplingpanel.add(samplingGraph =sg);
        pausB.setEnabled(false);
        //serverStatus.start();
        try
        {
            new UIControls(mainFrame).startControlProcess();
         }
        catch(Exception ex)
        {
           System.out.println("error");
            
        }
        this.audioStreamArray = audioStreamArray;
    }

   
        
        //To change body of generated methods, choose Tools | Templates.
    
   
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar2 = new javax.swing.JToolBar();
        loadB1 = new javax.swing.JButton();
        close = new javax.swing.JButton();
        playB1 = new javax.swing.JButton();
        pausB = new javax.swing.JButton();
        samplingpanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar2.setBackground(new java.awt.Color(204, 255, 204));
        jToolBar2.setRollover(true);

        loadB1.setText("open");
        loadB1.setFocusable(false);
        loadB1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loadB1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        loadB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadB1ActionPerformed(evt);
            }
        });
        jToolBar2.add(loadB1);

        close.setText("close");
        close.setFocusable(false);
        close.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        close.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        jToolBar2.add(close);

        playB1.setText("play");
        playB1.setFocusable(false);
        playB1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playB1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(playB1);

        pausB.setText("pausB");
        pausB.setFocusable(false);
        pausB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pausB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pausB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pausBActionPerformed(evt);
            }
        });
        jToolBar2.add(pausB);

        samplingpanel.setMinimumSize(new java.awt.Dimension(300, 400));

        javax.swing.GroupLayout samplingpanelLayout = new javax.swing.GroupLayout(samplingpanel);
        samplingpanel.setLayout(samplingpanelLayout);
        samplingpanelLayout.setHorizontalGroup(
            samplingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );
        samplingpanelLayout.setVerticalGroup(
            samplingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(samplingpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(samplingpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
      AdjustmentListener adjustmentListener = new AdjustmentListener() {
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            if (audioInputStream != null) {
                samplingGraph.createWaveForm(null);
            }
        }
    };
    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
      try
      {
          System.exit(0);
      }
      catch(Exception ex)
      {
          Logger.getLogger(PlotWave1.class.getName()).log(Level.SEVERE,null,ex);
      }
              
        
    }//GEN-LAST:event_closeActionPerformed

    private void loadB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadB1ActionPerformed
     fileOpenMethod();
    }//GEN-LAST:event_loadB1ActionPerformed

    private void pausBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pausBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pausBActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void PlotWave1(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PlotWave1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlotWave1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlotWave1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlotWave1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
              //  new PlotWave1().setVisible(true);
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton close;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar2;
    javax.swing.JButton loadB1;
    javax.swing.JButton pausB;
    javax.swing.JButton playB1;
    javax.swing.JPanel samplingpanel;
    // End of variables declaration//GEN-END:variables

    private void fileOpenMethod() {
         try
         {System.out.println(",l.,;, ;: ");
              File fileDir = new File(System.getProperty("user.dir"));
            JFileChooser fc = new JFileChooser(fileDir);
            fc.setFileFilter(new javax.swing.filechooser.FileFilter() {

                  @Override
                  public boolean accept(File file) {
                       if (file.isDirectory()) {
                        return true;
                    } String name = file.getName().toLowerCase();
                    if (name.endsWith(".wav") || name.endsWith(".mp3") || name.endsWith(".wma")) {
                        return true;
                    }
                    return false;
                    //To change body of generated methods, choose Tools | Templates.
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
                String wav_file_path = convertedFile.getAbsolutePath() + "\\" + wav_file_name;
                
                String sox_command = sox_path + " " + source_file_path + " -r 8k " + convertedFile.getAbsolutePath() + "\\" + wav_file_name;
                //String mystring1 = sox_path+" -r 8k -e signed -b 8 -c 1 "+source_file_path+" d:\\converted\\"+newFileName;
                String AllToWav_command = allToWav_path + " " + source_file_path + " -O" + convertedFile.getAbsolutePath() + "\\" + wav_file_name + " -S8000";
                try {
                    if ((source_file_name).endsWith(".mp3")) {


                        Process t = Runtime.getRuntime().exec(sox_command);
                        t.waitFor();


                        System.out.println("sox command: " + sox_command);
                        File wave = new File(wav_file_path);

                        if (!wave.exists()) {
                            System.out.println("Error : File not found !");
                        }

                        createAudioInputStream(wave, null, true);
                        loadB1.setEnabled(false);
                        //lblloading.setText("Done");
                    } else if ((source_file_name).endsWith(".wma")) {
                        Process t = Runtime.getRuntime().exec(AllToWav_command);
                        t.waitFor();


                        System.out.println("All to wave command: " + AllToWav_command);
                        File wave = new File(wav_file_path);

                        if (!wave.exists()) {
                            System.out.println("Error: File not found !");
                        }
                        
                createAudioInputStream(wave, null, true);
                        loadB1.setEnabled(false);
                        //lblloading.setText("Done");

                    } else {
                        createAudioInputStream(fc.getSelectedFile(), null, true);
                        loadB1.setEnabled(false);
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

    }//End File Load
         

           

    private void createAudioInputStream(File file, AudioInputStream audioStreamArray, boolean updateComponents) 
    {
        if (file != null && file.isFile()) {
            try {

                if (!(file.getName().toLowerCase().endsWith(".wav"))) {
                    javax.swing.JOptionPane.showMessageDialog(mainFrame, "This is not correct formate");
                    return;
                }
                this.file = file;
                this.audioInputStream = AudioSystem.getAudioInputStream(file);
                fileName = file.getName();
                abbfilePath = file.getAbsolutePath();

            }
            catch (UnsupportedAudioFileException ex)
                {
                Logger.getLogger(PlotWave.class.getName()).log(Level.SEVERE, null, ex);
                javax.swing.JOptionPane.showMessageDialog(this, "Can not open the file", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
                 }
            catch (IOException ex) 
            {
                Logger.getLogger(PlotWave.class.getName()).log(Level.SEVERE, null, ex);
                javax.swing.JOptionPane.showMessageDialog(this, "Can not open the file", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }//To change body of generated methods, choose Tools | Templates.
    } else if (audioStreamArray != null) {
            try {
                if (audioStreamArray.available() < 1) {
                    return;
                }

                this.audioInputStream = audioStreamArray;
                fileName = "Wave1.wav";

            } catch (IOException ex) {
                Logger.getLogger(PlotWave.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // reportStatus("Audio file required.");
            return;
        }
        try {
            if (this.audioInputStream != null) {
                try {
                    if (this.audioInputStream.available() < 1) {
                        return;
                    }


                } catch (IOException ex) {
                    Logger.getLogger(PlotWave.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
            }
            errStr = null;
            if (capture.thread == null) {
                playB1.setEnabled(true);
            }

            //Set Bytes and Generated
            byte[] audioBytes = StreamConverter.streamTobyte(audioInputStream);
            //streamBytes.setOriginal(audioBytes);
            if (audioBytes == null) {
                audioInputStream = null;
                return;
            }
            streamBytes.setCurrent(audioBytes);
            audioBytes = null;
            audioInputStream = StreamConverter.byteTostream(streamBytes.getCurrent(), audioInputStream);
            fileHashValue = Hash.getHashValue(audioInputStream);
             int widthScreen = setNormalScreen(audioInputStream);
            setGrphSizeinScreen(widthScreen);
            long milliseconds = (long) ((audioInputStream.getFrameLength() * 1000) / audioInputStream.getFormat().getFrameRate());
            duration = milliseconds / 1000.0;
             pausB.setEnabled(true);
            if (updateComponents) {
                samplingGraph.createWaveForm(streamBytes.getCurrent());
            }
        } catch (Exception ex) {
            System.out.println("Error1 " + ex);
            reportStatus(ex.toString());
        }
    }

    private int setNormalScreen(AudioInputStream audioInput) {
      int pixcelPerscreen = 0;
        try {
            if (audioInput == null) {
                return 0;
            }
            int audioLenth = (int) audioInput.getFrameLength();
            pixcelPerscreen = (audioLenth / normalPixcel);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return pixcelPerscreen; //To change body of generated methods, choose Tools | Templates.
    }

    private void setGrphSizeinScreen(int screenWidth) {
        try {
            int xSize = (int) tk.getScreenSize().getWidth();
            if (screenWidth < (xSize - graphFromScreen)) {
                screenWidth = xSize - graphFromScreen;
            }
            if (streamBytes.getCurrent() != null) {
                if (screenWidth > ((streamBytes.getCurrent().length) / 2)) {
                    screenWidth = ((streamBytes.getCurrent().length) / 2);
                }
            }
            sg.setSize(new Dimension(screenWidth, graphVerticalSize));
            samplingpanel.setSize(new Dimension(screenWidth, graphVerticalSize));
            samplingpanel.setPreferredSize(new java.awt.Dimension(screenWidth, graphVerticalSize));
            this.revalidate();
            this.repaint();
        } catch (Exception er) {
            System.err.println(er);
        } //To change body of generated methods, choose Tools | Templates.
    }

    private void reportStatus(String mgs) {
       if ((errStr = mgs) != null) {
            System.out.println(errStr);
            //samplingGraph.repaint();
        }
    } //To change body of generated methods, choose Tools | Templates.

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void open() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reorderWavePanel() {
       samplingGraph.createWaveForm(null); //To change body of generated methods, choose Tools | Templates.
    }

  

     class Capture implements Runnable
     {
        TargetDataLine line;
        Thread thread;

        public void start() {
            errStr = null;
            thread = new Thread(this);
            thread.setName("Capture");
            thread.start();
        }

     @Override
  public void run() 
  {
                     try { duration = 0;
                            audioInputStream = null;
                     try { AudioFormat format = null;
                            DataLine.Info info = null;
                    try {   format = getAudioFormat();
                            info = new DataLine.Info(TargetDataLine.class,format);
                            AudioSystem.getLine(info);
                        if (!AudioSystem.isLineSupported(info)) 
                        { shutDown("Line matching " + info + " not supported.");
                            return; }
                        }
                    catch (Exception er) {}
                    try {
                        line = (TargetDataLine) AudioSystem.getLine(info);
                        line.open(format, line.getBufferSize());
                         } catch (LineUnavailableException ex) {
                        shutDown("Unable to open the line: " + ex);
                        return; } 
                        catch (SecurityException ex) 
                         {
                           shutDown(ex.toString());
                           return;//To change body of generated methods, choose Tools | Templates.
                         } 
                        catch (Exception ex) 
                        { shutDown(ex.toString());
                        return;
                        }
                     capOut = new ByteArrayOutputStream();
                    int frameSizeInBytes = format.getFrameSize();
                    int bufferLengthInFrames = line.getBufferSize() / 8;
                    int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
                    byte[] data = new byte[bufferLengthInBytes];
                    int numBytesRead, noofwrites = 1;
                    
                     line.start();

                    while (thread != null) {
                        if ((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
                            break;
                        }
                        
                         try {

                            FileOutputStream fos2 = new FileOutputStream("record/out2.wav", true);
                            fos2.write(data);
                            fos2.close();
                        } catch (Exception e1) {//Catch exception if any
                            System.err.println("Error: " + e1.getMessage());
                        }
                        capOut.write(data, 0, numBytesRead);
                    }
                    line.stop();
                    line.close();
                    line = null;
                    
                    try {
                        capOut.flush();
                        capOut.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    
                    byte audioBytes[] = capOut.toByteArray();


                    ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
                    audioInputStream = new AudioInputStream(bais, format, audioBytes.length / frameSizeInBytes);

                    String recordFile = rightClick.saveLocation();
                    if (recordFile == null) {
                        recordFile = "temp.wav";
                    }
                    StreamConverter.streamTowavefile(recordFile, audioInputStream);
                    audioInputStream = null;
                    File recordFileName = new File(recordFile);
                    if (recordFileName.exists()) {
                        createAudioInputStream(recordFileName, null, true);
                    } else {
                        javax.swing.JOptionPane.showConfirmDialog(mainFrame, "Unable to create file");
                    }
                     } catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
                // saveToFile("saidesh", AudioFileFormat.Type.WAVE);
                // samplingGraph.createWaveForm(audioBytes);
            } catch (Exception er) {
                System.err.println(er);
            }
        }

                private void shutDown(String message) {
                    if ((errStr = message) != null) {
                System.err.println(errStr);
                samplingGraph.repaint();
            }
            if (thread != null) {
                thread = null;
                samplingGraph.stop();
            //    captB.setEnabled(true);
                //pausB.setEnabled(false);
                playB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/play.jpg")));
                playB1.setToolTipText("play");
            } //To change body of generated methods, choose Tools | Templates.
                }
    }

       
    

                private AudioFormat getAudioFormat() {
                     float sampleRate = 8000.0F;
            //8000,11025,16000,22050,44100
            int sampleSizeInBits = 16;
            //8,16
            int channels = 1;
            //1,2
            boolean signed = true;
            //true,false
            boolean bigEndian = false;
            //true,false

            return new AudioFormat(sampleRate,
                    sampleSizeInBits,
                    channels,
                    signed,
                    bigEndian);
        }
    class  SamplingGraph extends JPanel implements Runnable
                {
                    private Thread thread;
                    public AudioFormat format;
                    private Font font10= new Font("serif", Font.PLAIN,10 );
                    private Font font12 = new Font("serif", Font.PLAIN,12);
                     Color jfcBlue = new Color(204, 204, 255);
                     Color pink = new Color(139, 0, 0);
                      private int[] audioDataNormalize;
                      private int normalizedValue = 3000;
                      Speech.signalProcessing.SignalProc sigProc;
                      
                      public SamplingGraph()
                      {
                          setBackground(new Color(255,255,255,255));
                          
                      }
                    //To change body of generated methods, choose Tools | Templates.

     

        private void createWaveForm(byte[] audiobyte) {
            
            if (audioInputStream == null) {
                return;
            }

            // Declation part
            Dimension d = getSize();
            d = this.getSize();
            int w = d.width;
            int h = d.height;
            byte my_byte = 0;
            double y_last = 0;
            int[] audioData = null;
            format = audioInputStream.getFormat();
            int numChannels = format.getChannels();
            normalizedValue = 5000;
            try {
                //Read Bytes from current Bytes
                if (audiobyte == null) {
                    try {
                        audiobyte = streamBytes.getCurrent();
                    } catch (Exception ex) {
                        reportStatus(ex.toString());
                        return;
                    }
                }
                try {
                    long milliseconds = (long) ((audioInputStream.getFrameLength() * 1000) / audioInputStream.getFormat().getFrameRate());
                    System.out.println("actual length" +audioInputStream.getFrameLength());
                    duration = milliseconds / 1000.0;
                } catch (Exception er) {
                    System.err.println(er.getMessage());
                }
                
                frames_per_pixel = audiobyte.length / format.getFrameSize() / w;
                //End Normalized


                //Postion taken

                int endPaint = jScrollPane1.getSize().width;
                int startPaint = jScrollPane1.getHorizontalScrollBar().getValue();
                endPaint = (startPaint + endPaint);
                int startbytes = startPaint * frames_per_pixel * 2;
                int endbytes = endPaint * frames_per_pixel * 2;


                byte[] audioBytes = new byte[endbytes - startbytes];
                int newby = 0;
                for (int by = startbytes; by < endbytes - 10; by++) {
                    if (by < (audiobyte.length - 10)) {
                        audioBytes[newby++] = audiobyte[by];
                    }
                }
                
                  if (format.getSampleSizeInBits() == 16) {
                    int nlengthInSamples = audioBytes.length / 2;
                    audioData = new int[nlengthInSamples];
                    if (format.isBigEndian()) {
                        for (int i = 0; i < nlengthInSamples; i++) {
                            /* First byte is MSB (high order) */
                            int MSB = (int) audioBytes[2 * i];
                            /* Second byte is LSB (low order) */
                            int LSB = (int) audioBytes[2 * i + 1];
                            audioData[i] = MSB << 8 | (255 & LSB);
                        }
                    } else {
                        for (int i = 0; i < nlengthInSamples; i++) {
                            /* First byte is LSB (low order) */
                            int LSB = (int) audioBytes[2 * i];
                            /* Second byte is MSB (high order) */
                            int MSB = (int) audioBytes[2 * i + 1];
                            audioData[i] = MSB << 8 | (255 & LSB);
                            
                             int maximum = audioData[0];   // start with the first value
                           for (int m=1; m<audioData.length; m++) {
                           if (audioData[m] > maximum) {
                           maximum = audioData[m];   // new maximum
                           
                            }
                        }
                           System.out.println("Maximum=" +maximum);
                         
                           int minimum = audioData[0];   // start with the first value
                           for (int k=1; k<audioData.length; k++) {
                           if (audioData[k] < minimum) {
                           minimum = audioData[k];   // new maximum
                           
                            }
                        }
                           System.out.println("Minimum=" +minimum);
                            }
                    }}
                           
                 else if (format.getSampleSizeInBits() == 8) {
                    int nlengthInSamples = audioBytes.length;
                    audioData = new int[nlengthInSamples];
                    if (format.getEncoding().toString().startsWith("PCM_SIGN")) {
                        for (int i = 0; i < audioBytes.length; i++) {
                            audioData[i] = audioBytes[i];

                        }
                    } else {
                        for (int i = 0; i < audioBytes.length; i++) {
                            audioData[i] = audioBytes[i] - 128;

                        }
                    }
                }
                  
                  if (capture.thread == null) {
                    //Normalized Bit
                    sigProc = new SignalProc();
                    audioDataNormalize = sigProc.doubleToInt(sigProc.normalize(sigProc.intToDouble(audioData), 1400));
                    //frames_per_pixel = audioBytes.length / format.getFrameSize() / w;
                    //End Normalized

                    //Start Normalized audio bytes

                } else {
                    sigProc = new SignalProc();
                    audioDataNormalize = sigProc.doubleToInt(sigProc.normalize(sigProc.intToDouble(audioData), 1400));
                    //  frames_per_pixel = audioBytes.length / format.getFrameSize() / w;
                }
                lines.removeAllElements();
                double inc = 0.02;
                // Calculate Screen Pixels                   
                for (double x = startPaint; x < endPaint && audioDataNormalize != null; x = x + 0.02) {
                    inc = inc + 0.02;
                    int idx = (int) (frames_per_pixel * numChannels * inc);

                    if (idx >= audioDataNormalize.length) {
                        break;
                    }
                    if (format.getSampleSizeInBits() == 8) {

                        my_byte = (byte) audioDataNormalize[idx];
                    } else {
                        my_byte = (byte) (2100 * audioDataNormalize[idx] / 32000);
                    }
                    double y_new = (double) (h * (100 - my_byte) / 240);
                    lines.add(new Line2D.Double(x, y_last, x, y_new));
                    y_last = y_new;

                }

                if (capture.thread == null) {
                    //Create Annotation
                    try {
                        Speech.annotations.GetAnnotation annObj = new Speech.annotations.GetAnnotation(mainFrame.getConn());

                        //System.out.println(fileHashValue);
                        Thread.sleep(100);
                        annotationPos = annObj.getAll(fileHashValue);


                    } catch (Exception er) {
                        System.err.println(er);
                    }
                }
                //End to create Annotation
                audioDataNormalize = null;
                audiobyte = null;
                audioData = null;
                audioBytes = null;
                sigProc = null;
                repaint();
            } catch (Exception er) {
                System.err.println(er);
            }
        }

 //To change body of generated methods, choose Tools | Templates.

      

        
            public void stop() {
            if (thread != null) {
                thread.interrupt();
            }
            thread = null;
        }//To change body of generated methods, choose Tools | Templates.
        

        @Override
        public void paint(Graphics g) {
            try
            {
                mainFrame.heepSize();
                Dimension d = this.getSize();
                int w = d.width;
                int h = d.height - 20;
                int INFOPAD = 15;
                
                
                Graphics2D g2 = (Graphics2D) g;
                g2.setBackground(getBackground());
                g2.clearRect(0, 0, w, h);
                g2.setColor(Color.white);
                g2.fillRect(0, h - INFOPAD, w, INFOPAD);
                
                
                if (errStr != null) {
                    g2.setColor(jfcBlue);
                    g2.setFont(new Font("serif", Font.BOLD, 18));
                    g2.drawString("ERROR", 5, 20);
                    AttributedString as = new AttributedString(errStr);
                    as.addAttribute(TextAttribute.FONT, font12, 0, errStr.length());
                    AttributedCharacterIterator aci = as.getIterator();
                    FontRenderContext frc = g2.getFontRenderContext();
                    LineBreakMeasurer lbm = new LineBreakMeasurer(aci, frc);
                    float x = 5, y = 25;
                    lbm.setPosition(0);
                    
                     while (lbm.getPosition() < errStr.length()) {
                        TextLayout tl = lbm.nextLayout(w - x - 5);
                        if (!tl.isLeftToRight()) {
                            x = w - tl.getAdvance();
                        }
                        tl.draw(g2, x, y += tl.getAscent());
                        y += tl.getDescent() + tl.getLeading();
                    }
                }
                else {
                    g2.setColor(Color.black);
                    g2.setFont(font12);
                    String fileNameDis = "";
                    int startPaint = jScrollPane1.getHorizontalScrollBar().getValue();
                    if (fileName.length() > 15) {

                        try {
                            fileNameDis = fileName.substring(0, 15);
                        } catch (Exception er) {
                            System.err.println(er);
                        }
                    } else {
                        fileNameDis = fileName;
                    }
                   
   if (audioInputStream != null && format != null) {

                        //Declaration Part
                        byte my_byte;
                        double y_last = 0;
                        int numChannels = format.getChannels();
                        int endPaint = jScrollPane1.getSize().width;
                        endPaint = (startPaint + endPaint);
                        
                        
                        //End Declaration


                        //Draw Selection portion 
                       if (capture.thread == null) {

                            for (int t = startPaint; t < endPaint; t++) {


                                if ((t * frames_per_pixel) % format.getFrameRate() < frames_per_pixel) {
                                    g2.setColor(Color.RED);
                                    int timeLine = (int) ((t * frames_per_pixel) / format.getFrameRate());
                                    if (frames_per_pixel > 4000) {
                                        if (timeLine % 300 != 0) {
                                            continue;
                                        }
                                    } else if (frames_per_pixel > 2000) {
                                        if (timeLine % 60 != 0) {
                                            continue;
                                        }
                                    } else if (frames_per_pixel > 250) {
                                        if (timeLine % 30 != 0) {
                                            continue;
                                        }
                                    }
                                    if (frames_per_pixel > 220) {
                                        if (timeLine % 10 != 0) {
                                            continue;
                                        }
                                    } else if (frames_per_pixel > 110) {
                                        if (timeLine % 5 != 0) {
                                            continue;
                                        }
                                    } else {
                                    }
                                    String hD = "", mD = "", sD = "", totalTD = "";
                                    int hour = timeLine / 3600;
                                    int hour_balance = timeLine % 3600;
                                    int min = hour_balance / 60;
                                    int min_balance = hour_balance % 60;

                                    if (hour < 10) {
                                        hD = "0" + hour;
                                    } else {
                                        hD = hD + hour;
                                    }
                                    if (min < 10) {
                                        mD = "0" + min;
                                    } else {
                                        mD = mD + min;
                                    }
                                    if (min_balance < 10) {
                                        sD = "0" + min_balance;
                                    } else {
                                        sD = sD + min_balance;
                                    }

                                    totalTD = hD + ":" + mD + ":" + sD;
                                    g2.draw((Line2D) new Line2D.Double(t, h - 30, t, h - 20));
                                    g2.setColor(Color.black);
                                    g2.drawString(totalTD, t - 20, h - 7);

                                }


                            }
                            g2.setColor(Color.RED);
                            g2.drawLine(startPaint, h - 20, endPaint, h - 20);
                        }
                        for (int t = startPaint; t < endPaint; t++) {
                          g2.setColor(Color.BLACK);
                            
                          int value = 32768;
                      
                          g2.drawString(String.valueOf(value),3,10);
                          g2.drawString("-32767",3, (float) ((((int)getHeight())*3)/3.7)); 
                        }
                        
                         Color linecolor = new Color(128, 0, 128);
                        g2.setColor(linecolor);
                        for (int i = 0; i < lines.size(); i++) {

                            // Draw Signal Graph
                            try {
                                if (((Line2D) lines.get(i)).getY2() != 0 && ((Line2D) lines.get(i)).getY1() != 0) {
                                    g2.draw((Line2D) lines.get(i));
                                }
                            } catch (Exception er) {
                            }

                            //End Draw Singal 


                        }
                        
                        Color mouseMovement = new Color(220, 20, 60);
                        g2.setColor(mouseMovement);
                        if (mouseMoveX1 != 0) {
                            g2.fillRect((int) mouseMoveX1, 0, (int) 2, h - INFOPAD);
                        }
                        //End Selection and mouse movement


                        // .. draw current position .. & Playing cursor

                        if (seconds != 0 && !selectedPlay) {
                            double loc = seconds / duration * w;
                            g2.setColor(pink);
                            g2.setStroke(new BasicStroke(3));
                            if ((((int) loc % (jScrollPane1.getWidth())) < 50) && loc != 0 && ((loc + jScrollPane1.getWidth()) < (samplingGraph.getWidth() - 10))) {
                                jScrollPane1.getViewport().setViewPosition(new java.awt.Point((int) loc, 0));
                            } else {
                                if (endPaint < loc) {
                                    jScrollPane1.getViewport().setViewPosition(new java.awt.Point((int) loc, 0));
                                }
                            }
                            g2.draw(new Line2D.Double(loc, 0, loc, h - INFOPAD - 2));
                        } else {
                            if (mousePosX1 != 0 && mousePosX2 != 0) {

                                int widthPos = (int) (mousePosX1 - mousePosX2);
                                int minValue = (int) (widthPos < 0 ? mousePosX1 : mousePosX2);
                                widthPos = (widthPos < 0 ? -1 * widthPos : widthPos);
                                double loc = (seconds / duration * widthPos) + minValue;
                                g2.setColor(pink);
                                g2.setStroke(new BasicStroke(3));
                                g2.draw(new Line2D.Double(loc, 0, loc, h - INFOPAD - 2));
                            } else if (mousePosX1 != 0 && mousePosX2 == 0) {
                                int widthPos = (int) (mousePosX1 - (int) ((samplingGraph.getSize().width) - 10));
                                int minValue = (int) (widthPos < 0 ? mousePosX1 : (int) ((samplingGraph.getSize().width) - 10));
                                widthPos = (widthPos < 0 ? -1 * widthPos : widthPos);
                                double loc = (seconds / duration * widthPos) + minValue;
                                g2.setColor(pink);
                                g2.setStroke(new BasicStroke(3));
                                if ((((int) loc % (jScrollPane1.getWidth())) < 50) && loc != 0 && ((loc + jScrollPane1.getWidth()) < (samplingGraph.getWidth() - 10))) {
                                    jScrollPane1.getViewport().setViewPosition(new java.awt.Point((int) loc, 0));
                                } else {
                                    if (endPaint < loc) {
                                        jScrollPane1.getViewport().setViewPosition(new java.awt.Point((int) loc, 0));
                                    }
                                }
                                g2.draw(new Line2D.Double(loc, 0, loc, h - INFOPAD - 2));
                            }
                        }
                    } else {
                        if (capture.thread == null) {
                           //BufferedImage image = ImageIO.read(new File("conf/img/sed-logo.png"));
                           // g2.drawImage(image, (int) (xSize - 250) / 2, (int) (h - 90) / 2, null);
                        }

                    }

                    //End initial condision
                }
                //End else part
            } catch (Exception er) {
                System.err.println(er);
            }
        }
        public void start() {
            thread = new Thread(this);
            thread.setName("SamplingGraph");
            thread.start();
            seconds = 0;
        }

        @Override
        public void run() {
            try {
                seconds = 0;
                while (thread != null) {

                    if ((playback.line != null) && (playback.line.isOpen())) {

                        long milliseconds = (long) (playback.line.getMicrosecondPosition() / 1000);
                        seconds = milliseconds / 1000.0;
                    } else if ((capture.line != null) && (capture.line.isActive())) {

                        long milliseconds = (long) (capture.line.getMicrosecondPosition() / 1000);
                        seconds = milliseconds / 1000.0;
                    }
                     try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        break;
                    }

                    repaint();
 while ((capture.line != null && !capture.line.isActive())
                            || (playback.line != null && !playback.line.isOpen())) {
                        try {
                            thread.sleep(10);

                        } catch (Exception e) {
                            break;
                        }
                    }
                } seconds = 0;
                selectedPlay = false;
                repaint();
            } catch (Exception er) {
                System.err.println(er);
            }
        }
    }//To change body of generated methods, choose Tools | Templates.
        }
       


        
            
