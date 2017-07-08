/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

//import Speech.kws.PlotWaveKws;
import Speech.phoneme.PlotWavePhoneme;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Tatapower SED
 *
 */
public class PhonemeFrame {

    private MainFrame mFrame;
    private AudioInputStream audioInputStream;
    private String language;
    private String panelName;
    public static DisplayPhoneProgressBar displayphonepro;
    public static DoPhoneProcess phoneProcess;

    public PhonemeFrame(MainFrame mFrame) {
        this.mFrame = mFrame;
    }

    public void createFrame(String panelName, AudioInputStream audioInputStream, String language, ArrayList processedList) {


        try {

            if (mFrame.rPhoneStatus) {
                javax.swing.JOptionPane.showMessageDialog(mFrame, "Process already running... ");
                return;
            }
            this.audioInputStream = audioInputStream;
            this.language = language;
            this.panelName = panelName;
            displayphonepro = new DisplayPhoneProgressBar();
            phoneProcess = new DoPhoneProcess(processedList);
            displayphonepro.start();
            phoneProcess.start();
            mFrame.screenproperty.resizeScreen(mFrame);

        } catch (Exception er) {
            System.out.println(er);
        }


    }

    public class DoPhoneProcess implements Runnable {

        private Thread thread = null;
        private ArrayList processedList;

        public DoPhoneProcess(ArrayList processedList) {
            this.processedList = processedList;
        }

        public void start() {
            thread = new Thread(this);
            thread.setName("ProgressBar");
            thread.start();
        }

        public void stop() {
            if (thread != null) {
                thread.interrupt();
            }
            thread = null;
        }

        public void run() {
            try {
                long start = System.currentTimeMillis();
                try {
                    mFrame.rPhoneStatus = true;
                    JDesktopPane newPane = new JDesktopPane();
                    PlotWavePhoneme pWaveKws = new PlotWavePhoneme(mFrame, audioInputStream, language, processedList);
                    //Sound Kws Main Frame
                    InternalFrame soundKws = new InternalFrame(false, true, true, pWaveKws);
                    soundKws.setTitle(panelName+"                                                                                    "
                            + "                                                                                                      "
                            + "                                                                                                      "
                            + "                                                      ");
                    soundKws.setVisible(true);
                    soundKws.setBorder(new EmptyBorder(0, 0, 0, 0));
                    soundKws.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/btn_ns_logo.png")));
                    mFrame.screenproperty.addSoundImprovePanel(soundKws);
                    soundKws.add(newPane);
                    //Left Frame
                    InternalFrame soundKwsleft = new InternalFrame(false, true, true, pWaveKws);
                    soundKwsleft.setTitle("List of keywordswwwwwwwwwww");
                    soundKwsleft.setVisible(false);
                    soundKwsleft.setLocation(0, 0);
                    soundKwsleft.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/btn_ns_logo.png")));
                    mFrame.screenproperty.addResultKeywordPanel(soundKwsleft);
                    JScrollPane scrollPane = new JScrollPane();
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    scrollPane.getVerticalScrollBar().setUnitIncrement(10);
                    scrollPane.getViewport().add(pWaveKws.keyDisplay);

                    soundKwsleft.add(scrollPane);
                    //newPane.add(soundKwsleft);
                    //End Left Frame

                    //Right Frame

                    //JPanel rightPanel = new JPanel();
                    //rightPanel.setVisible(true);
                    //mFrame.screenproperty.addSoundKwsPanel(rightPanel);
                    //rightPanel.add(pWaveKws);
                    //newPane.add(rightPanel);
                    //End Right Frame
                    
                    //mehbub
                    
                    
                    //mFrame.screenproperty.addSoundKwsPanel(rightPanel);
                    //rightPanel.add(pWaveKws);
                    //newPane.add(rightPanel);
                    
                    newPane.add(pWaveKws);
                    
                    //End Right Frame
                    
                    //end of mehbub
                    
                    mFrame.jDesktopPane1.add(soundKws);


                    try {
                        soundKws.setSelected(true);
                    } catch (final java.beans.PropertyVetoException e) {
                    }
                } catch (Exception er) {
                    System.err.println(er);
                }
                if (displayphonepro != null) {
                    displayphonepro.stop();
                }
                //-- modified by Lok
                double elapsed = (System.currentTimeMillis() - start) / 1000.0;
                System.out.println("Execution Time: " + elapsed);
                String exTime = String.valueOf(elapsed);
                mFrame.pWave.setExeutionTime("Exec Time: " + exTime + " Sec");
                //--
            } catch (Exception er) {
                System.err.println(er);
            }
            mFrame.rPhoneStatus = false;

            if (mFrame.rKwsStatus || mFrame.rPdsStatus || mFrame.rPhoneStatus || mFrame.rSidStatus) {
                mFrame.jProgressToolBar.setVisible(true);
            } else {
                mFrame.jProgressToolBar.setVisible(false);
            }

            phoneProcess = null;
        }
    }

    public class DisplayPhoneProgressBar implements Runnable {

        private Thread thread = null;

        public void start() {
            thread = new Thread(this);
            thread.setName("ProgressBar");
            thread.start();
        }

        public void stop() {
            if (thread != null) {
                thread.interrupt();
            }
            thread = null;
        }

        public void run() {
            try {
                mFrame.jProgressToolBar.setVisible(true);
                mFrame.phonemeButtonStatus(true);
                while (thread != null) {
                    for (int i = 0; i <= 100; i = i + 3) {
                        if (thread == null) {
                            break;
                        }

                        //Progressively increment variable i
                        mFrame.jphoneProgressBar1.setValue(i);
                        mFrame.jphoneProgressBar1.repaint(); //Refresh graphics
                        try {
                            Thread.sleep(50);
                        } //Sleep 50 milliseconds
                        catch (InterruptedException err) {
                        }
                    }
                }
                mFrame.phonemeButtonStatus(false);
                mFrame.jphoneProgressBar1.setValue(0);
                mFrame.jphoneProgressBar1.repaint();
            } catch (Exception er) {
                System.err.println(er);
            }
            displayphonepro = null;

        }
    }

    public static void stopPhoneProcess() {

        if (phoneProcess != null) {
            phoneProcess.stop();
            if (!(phoneProcess.thread.isAlive())) {
                displayphonepro.stop();
                displayphonepro = null;
                phoneProcess = null;
            }
        }
        if (phoneProcess == null && displayphonepro != null) {
            displayphonepro.stop();
            displayphonepro = null;
        }

    }
}
