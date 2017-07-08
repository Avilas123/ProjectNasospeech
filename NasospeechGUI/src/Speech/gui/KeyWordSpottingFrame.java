/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.kws.KeywordPanel;
import Speech.kws.PlotWaveKws;
import Speech.staticobjects.DisplayObjects;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;

/**
 *
 * @author Tatapower SED
 *
 */
public class KeyWordSpottingFrame {

    public MainFrame mFrame;
    private AudioInputStream audioInputStream = null;
    private String panelName = null;
    private String language = null;
    public SubFunctionInternalFrame KwsDisplay = null;

    public KeyWordSpottingFrame(MainFrame mFrame, String panelName, AudioInputStream audioInputStream, String language) {
        this.mFrame = mFrame;
        this.panelName = panelName;
        this.audioInputStream = audioInputStream;
        this.language = language;

    }

    public void createFrame() {
        try {

            if (mFrame.rKwsStatus) {
                javax.swing.JOptionPane.showMessageDialog(mFrame, "Process already running... ");
                return;
            }

            if (DisplayObjects.processStatus()) {
                return;
            }


            JDesktopPane newPane = new JDesktopPane();

            //Select Keyword and Submit
            KwsDisplay = new SubFunctionInternalFrame(false, true, false);
            KeywordPanel kPanel = new KeywordPanel(mFrame.getConn(), language, this, mFrame);
            KwsDisplay.setTitle(panelName);
            KwsDisplay.setVisible(true);
            KwsDisplay.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
            KwsDisplay.setOpaque(true);
            mFrame.screenproperty.keywordSelectionFrame(KwsDisplay);
            KwsDisplay.add(newPane);


            JPanel rightPanel = new JPanel();

            rightPanel.setVisible(true);
            mFrame.screenproperty.keywordSelectionPanel(rightPanel);
            rightPanel.add(kPanel);
            newPane.add(kPanel);
            DisplayObjects.setIsKwsSelected(true);
            DisplayObjects.setKwsSelectedObj(KwsDisplay);
            mFrame.jDesktopPane1.add(KwsDisplay);
            mFrame.screenproperty.resizeScreen(mFrame);
            try {
                KwsDisplay.setSelected(true);
            } catch (final java.beans.PropertyVetoException e) {
            }

        } catch (Exception er) {
            System.out.println(er);
        }

    }

    public void processKeywordSpotting() {
        //End Select keyword
        try {
            JDesktopPane newPane = new JDesktopPane();
            PlotWaveKws pWaveKws = new PlotWaveKws(mFrame, audioInputStream, language, null, null);
            //Sound Kws Main Frame
            InternalFrame soundKws = new InternalFrame(false, true, true, pWaveKws);
            soundKws.setTitle(panelName);
            soundKws.setVisible(true);
            soundKws.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
            mFrame.screenproperty.addSoundImprovePanel(soundKws);
            soundKws.add(newPane);
            //Left Frame
            InternalFrame soundKwsleft = new InternalFrame(false, false, false, pWaveKws);
            soundKwsleft.setTitle("List of keywords");
            soundKwsleft.setVisible(true);
            soundKwsleft.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
            soundKwsleft.setLocation(0, 0);
            mFrame.screenproperty.addResultKeywordPanel(soundKwsleft);

            // scrollPane.getViewport().add(pWaveKws.keyDisplay);

            soundKwsleft.add(pWaveKws.keyDisplay);
            newPane.add(soundKwsleft);
            //End Left Frame

            //Right Frame

            JPanel rightPanel = new JPanel();

            rightPanel.setVisible(true);
            mFrame.screenproperty.addSoundKwsPanel(rightPanel);
            rightPanel.add(pWaveKws);
            newPane.add(rightPanel);
            //End Right Frame


            mFrame.jDesktopPane1.add(soundKws);
            mFrame.screenproperty.resizeScreen(mFrame);
            try {
                soundKws.setSelected(true);
            } catch (final java.beans.PropertyVetoException e) {
            }
        } catch (Exception er) {
            System.out.println(er);
        }
    }

    public void processSelectedKeywordSpotting(ArrayList selectedKeyword) {
        try {
            //End Select keyword          

            JDesktopPane newPane = new JDesktopPane();
            PlotWaveKws pWaveKws = new PlotWaveKws(mFrame, audioInputStream, language, selectedKeyword, null);
            //Sound Kws Main Frame
            InternalFrame soundKws = new InternalFrame(false, true, true, pWaveKws);
            soundKws.setTitle(panelName);
            soundKws.setVisible(true);
            soundKws.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
            mFrame.screenproperty.addSoundImprovePanel(soundKws);
            soundKws.add(newPane);
            //Left Frame
            InternalFrame soundKwsleft = new InternalFrame(false, false, false, pWaveKws);
            soundKwsleft.setTitle("List of keywords");
            soundKwsleft.setVisible(true);
            soundKwsleft.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
            soundKwsleft.setLocation(0, 0);
            mFrame.screenproperty.addResultKeywordPanel(soundKwsleft);
            // JScrollPane scrollPane = new JScrollPane();
            // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            //scrollPane.getVerticalScrollBar().setUnitIncrement(10);
            //  scrollPane.getViewport().add(pWaveKws.keyDisplay);

            soundKwsleft.add(pWaveKws.keyDisplay);
            newPane.add(soundKwsleft);
            //End Left Frame

            //Right Frame

            JPanel rightPanel = new JPanel();

            rightPanel.setVisible(true);
            mFrame.screenproperty.addSoundKwsPanel(rightPanel);
            rightPanel.add(pWaveKws);
            newPane.add(rightPanel);
            //End Right Frame


            mFrame.jDesktopPane1.add(soundKws);
            // mFrame.screenproperty.resizeScreen(mFrame);
            try {
                soundKws.setSelected(true);
            } catch (final java.beans.PropertyVetoException e) {
            }
        } catch (Exception ex) {
            Logger.getLogger(KeyWordSpottingFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void processedSelectedKeywordSpotting(ArrayList processedList) {
        try {
            //End Select keyword          

            JDesktopPane newPane = new JDesktopPane();
            PlotWaveKws pWaveKws = new PlotWaveKws(mFrame, audioInputStream, language, null, processedList);
            //Sound Kws Main Frame
            InternalFrame soundKws = new InternalFrame(false, true, true, pWaveKws);
            soundKws.setTitle(panelName);
            soundKws.setVisible(true);
            soundKws.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
            mFrame.screenproperty.addSoundImprovePanel(soundKws);
            soundKws.add(newPane);
            //Left Frame
            InternalFrame soundKwsleft = new InternalFrame(false, false, false, pWaveKws);
            soundKwsleft.setTitle("List of keywords");
            soundKwsleft.setVisible(true);
            soundKwsleft.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
            soundKwsleft.setLocation(0, 0);
            mFrame.screenproperty.addResultKeywordPanel(soundKwsleft);
            // JScrollPane scrollPane = new JScrollPane();
            // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            //scrollPane.getVerticalScrollBar().setUnitIncrement(10);
            //  scrollPane.getViewport().add(pWaveKws.keyDisplay);

            soundKwsleft.add(pWaveKws.keyDisplay);
            newPane.add(soundKwsleft);
            //End Left Frame

            //Right Frame

            JPanel rightPanel = new JPanel();

            rightPanel.setVisible(true);
            mFrame.screenproperty.addSoundKwsPanel(rightPanel);
            rightPanel.add(pWaveKws);
            newPane.add(rightPanel);
            //End Right Frame


            mFrame.jDesktopPane1.add(soundKws);
            mFrame.screenproperty.resizeScreen(mFrame);
            try {
                soundKws.setSelected(true);
            } catch (final java.beans.PropertyVetoException e) {
            }
        } catch (Exception ex) {
            Logger.getLogger(KeyWordSpottingFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
