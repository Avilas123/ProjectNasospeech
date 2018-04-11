/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.panels.NewPlotWavePanel;
import javax.sound.sampled.AudioInputStream;

/**
 *
 * @author Tatapower SED
 *
 */
public class CopyPanelFrame {

    private MainFrame mainFrame;

    public CopyPanelFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void createCopyInternalFrame(String panelName, AudioInputStream audioInputStream) {
        try {
            NewPlotWavePanel pWaveSda = new NewPlotWavePanel(mainFrame, audioInputStream, panelName);
            InternalFrame soundImprove = new InternalFrame(false, true, true, mainFrame);
            soundImprove.setTitle(panelName);
            soundImprove.setVisible(true);
            soundImprove.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
          //  mainFrame.screenproperty.addSoundImprovePanel(soundImprove);
            soundImprove.add(pWaveSda);
            mainFrame.jDesktopPane1.add(soundImprove);
          //  mainFrame.screenproperty.resizeScreen(mainFrame);
            try {
                soundImprove.setSelected(true);
            } catch (final java.beans.PropertyVetoException e) {
            }
        } catch (Exception er) {
            System.out.println(er);
        }
    }
}
