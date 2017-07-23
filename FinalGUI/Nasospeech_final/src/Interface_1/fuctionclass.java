/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface_1;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import Interface_1.NewPlotWavePanel;
//**
 //*
 //* @author user
 public class fuctionclass {
     public String abbfilePath = null;
      final int bufSize = 16384;
    public int frames_per_pixel;
    //FormatControls formatControls = new FormatControls();
              
    //Playback playback = new Playback();
    AudioInputStream audioInputStream;
   public NewPlotWavePanel.SamplingGraph samplingGraph;
    //private SubSamplingGraph subsamplingGraph;
    String errStr;
    double duration, seconds;
    File file;
    String fileName = "untitled";
    Vector lines = new Vector();
    Vector sublines = new Vector();
    private Toolkit tk;
    public double mousePosX1, mousePosX2, mouseMoveX1, mouseMoveY1, mousePosY1;
    public JPopupMenu menu;
    public StreamBytes streamBytes;
    public MainGuI mainFrame;
    //private SamplingGraph sg;
    //private SubSamplingGraph subsg;
    private int graphFromScreen = 10, graphVerticalSize = 210, normalPixcel = 60;
    public boolean selectedPlay = false;
    //public StreamVariables streamVariable;
    //public RightClickEventKws rightClick;
    //public ResultKeyWordPanel keyDisplay;
    //public KeyWordBuilder keyBuilder;
   // public KeyColorHandler keyColorHandler;
    public String language;
    public String[][] annotationPos;
    String fileHashValue;
    private boolean buffStatus = true;
    private boolean lineStatus = false;
    public void fileOpenMethod()
    {

        try 
        {
            File fileDir = new File(System.getProperty("user.dir"));
            JFileChooser fc = new JFileChooser(fileDir);
            fc.setFileFilter(new javax.swing.filechooser.FileFilter() {
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    String name = f.getName();
                    if (name.endsWith(".wav")) {
                        return true;
                    }
                    return false;
                }

                public String getDescription() {
                    return ".wav";
                }
            });
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                createAudioInputStream(fc.getSelectedFile(), null, true);
            }
        } 
        catch (SecurityException ex) 
        {
            // JavaSound.showInfoDialog();
            ex.printStackTrace();
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        }
        
    //End File Load
    
    
    //capture class code u may have to remove these lines
    /* class Capture implements Runnable {

        TargetDataLine line;
        Thread thread;

        public void start() {
            errStr = null;
            thread = new Thread(this);
            thread.setName("Capture");
            thread.start();
        }

        public void stop() {
            thread = null;
        }

        private void shutDown(String message) {
            if ((errStr = message) != null && thread != null) {
                thread = null;
                samplingGraph.stop();
                //  loadB.setEnabled(true);
                playB.setEnabled(true);
                pausB.setEnabled(false);

                waveB.setEnabled(true);
                samplingGraph.repaint();
            }
        }
    
    */

    
    
    
    
    
    public void createAudioInputStream(File file, AudioInputStream audioStreamArray, boolean updateComponents)
    {

        if (file != null && file.isFile()) {
            try {

                if (!(file.getName().toLowerCase().endsWith(".wav"))) {
                   // javax.swing.JOptionPane.showMessageDialog(mainframe, "This is not correct formate");
                 // JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",JOptionPane.ERROR_MESSAGE);
                   JOptionPane.showMessageDialog(null, "Please select a valid File", "Error",
                                    JOptionPane.ERROR_MESSAGE);//you may have to change this after gui runs.
                    return;
                }

                this.file = file;
                this.audioInputStream = AudioSystem.getAudioInputStream(file);
                fileName = file.getName();
                abbfilePath = file.getAbsolutePath();

            } catch (UnsupportedAudioFileException ex) {
               // Logger.getLogger(PlotWave.class.getName()).log(Level.SEVERE, null, ex);
               // javax.swing.JOptionPane.showMessageDialog(this, "Can not open the file", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            } catch (IOException ex) {
              //  Logger.getLogger(PlotWave.class.getName()).log(Level.SEVERE, null, ex);
                //javax.swing.JOptionPane.showMessageDialog(this, "Can not open the file", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (audioStreamArray != null) {
            try {
                if (audioStreamArray.available() < 1) {
                    return;
                }

                this.audioInputStream = audioStreamArray;
                fileName = "Wave1.wav";

            } catch (IOException ex) {
               // Logger.getLogger(PlotWave.class.getName()).log(Level.SEVERE, null, ex);
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
                   // Logger.getLogger(PlotWave.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
            }
            errStr = null;
         //Ask do we need Thread?   if (capture.thread == null) {
             //what is playB?   playB.setEnabled(true);
            }
        catch(Exception e)
        {
            System.out.print(e);
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

            //int widthScreen = setNormalScreen(audioInputStream);
            int widthScreen = 1492;
            System.out.println("width screen = "+widthScreen);
            setGrphSizeinScreen(widthScreen);
            long milliseconds = (long) ((audioInputStream.getFrameLength() * 1000) / audioInputStream.getFormat().getFrameRate());
            duration = milliseconds / 1000.0;

            waveB.setEnabled(true);
            if (updateComponents) {
                samplingGraph.createWaveForm(streamBytes.getCurrent());
            }
    
        } catch (Exception ex) {
            System.out.println("Error1 " + ex);
          //Make a pop up to display error.          reportStatus(ex.toString());
        }
        
        
        
        
        
    }//End Audio Input stream
    

