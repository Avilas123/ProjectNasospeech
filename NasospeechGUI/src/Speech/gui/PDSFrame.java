/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.pds.PlotWavePDS;
import Speech.pds.Threshold;
import Speech.staticobjects.DisplayObjects;
import javax.sound.sampled.AudioInputStream;

/**
 *
 * @author Tatapower SED
 *
 */
public class PDSFrame {

    private MainFrame mFrame;
    private String panelName;
    private AudioInputStream audioInputStream;
    public static DisplayPdsProgressBar displaypdspro;
    public static DoPdsProcess pdsProcess;
    public String threshold;

    public PDSFrame(MainFrame mainFrame, String panelName, AudioInputStream audioInputStream) {
        this.mFrame = mainFrame;
        this.audioInputStream = audioInputStream;
        this.panelName = panelName;
    }

    public void createPDSInternalFrame() {

        try {
            if (mFrame.rPdsStatus) {
                javax.swing.JOptionPane.showMessageDialog(mFrame, "Process already running... ");
                return;
            }

            if (DisplayObjects.processStatus()) {
                return;
            }

            SubFunctionInternalFrame subFun = new SubFunctionInternalFrame(false, true, false);
            Threshold pdsThreshold = new Threshold(subFun, this);

            subFun.setTitle(panelName);
            subFun.setVisible(true);
            subFun.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
            mFrame.screenproperty.pdsThreshold(subFun);
            subFun.add(pdsThreshold);
            mFrame.jDesktopPane1.add(subFun);
            mFrame.screenproperty.resizeScreen(mFrame);
            DisplayObjects.setIsPdsThreshold(true);
            DisplayObjects.setPdsThresholdObj(subFun);
            try {
                subFun.setSelected(true);
            } catch (final java.beans.PropertyVetoException e) {
            }
        } catch (Exception er) {
            System.out.println(er);
        }
    }

    public void startPds(String threshold) {
        this.threshold = threshold;
        displaypdspro = new DisplayPdsProgressBar();
        pdsProcess = new DoPdsProcess();
        displaypdspro.start();
        pdsProcess.start();

    }

    public class DoPdsProcess implements Runnable {

        private Thread thread = null;
        long start = System.currentTimeMillis();

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

                mFrame.rPdsStatus = true;
                PlotWavePDS pWaveSda = new PlotWavePDS(mFrame, audioInputStream, panelName, threshold);
                InternalFrame soundImprove = new InternalFrame(false, true, true, pWaveSda);
                soundImprove.setTitle(panelName);
                soundImprove.setVisible(true);
                soundImprove.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
                mFrame.screenproperty.addSoundImprovePanel(soundImprove);
                soundImprove.add(pWaveSda);
                mFrame.jDesktopPane1.add(soundImprove);
                mFrame.screenproperty.resizeScreen(mFrame);

                try {
                    soundImprove.setSelected(true);
                } catch (final java.beans.PropertyVetoException e) {
                }
                if (displaypdspro != null) {
                    displaypdspro.stop();
                }
            } catch (Exception er) {
                System.err.println(er);
            }
            mFrame.rPdsStatus = false;

            if (mFrame.rKwsStatus || mFrame.rPdsStatus || mFrame.rPhoneStatus || mFrame.rSidStatus) {
                mFrame.jProgressToolBar.setVisible(true);
            } else {
                mFrame.jProgressToolBar.setVisible(false);
            }

            pdsProcess = null;

            double elapsed = (System.currentTimeMillis() - start) / 1000.0;
            System.out.println("Execution Time: " + elapsed);
            String exTime = String.valueOf(elapsed);
            mFrame.pWave.setExeutionTime("Exec Time: " + exTime + " Sec");
        }
    }

    public class DisplayPdsProgressBar implements Runnable {

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
                mFrame.pdsButtonStatus(true);
                while (thread != null) {
                    for (int i = 0; i <= 100; i = i + 3) {
                        if (thread == null) {
                            break;
                        }

                        //Progressively increment variable i
                        mFrame.jpdsProgressBar.setValue(i);
                        mFrame.jpdsProgressBar.repaint(); //Refresh graphics
                        try {
                            Thread.sleep(50);
                        } //Sleep 50 milliseconds
                        catch (InterruptedException err) {
                        }
                    }
                }
                mFrame.pdsButtonStatus(false);
                mFrame.jpdsProgressBar.setValue(0);
                mFrame.jpdsProgressBar.repaint();
            } catch (Exception er) {
                System.err.println(er);
            }
            displaypdspro = null;

        }
    }

    public static void stopPdsProcess() {

        if (pdsProcess != null) {
            pdsProcess.stop();
            if (!(pdsProcess.thread.isAlive())) {
                displaypdspro.stop();
                displaypdspro = null;
                pdsProcess = null;
            }
        }
        if (pdsProcess == null && displaypdspro != null) {
            displaypdspro.stop();
            displaypdspro = null;
        }

    }
}
