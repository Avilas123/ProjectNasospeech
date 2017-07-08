/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.kws.KwsTranscription;
import Speech.kws.PlotWaveKws;

/**
 *
 * @author Tatapower SED
 *
 */
public class KwsTranscriptionFrame {

    private MainFrame mainFrame;

    public KwsTranscriptionFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void createKwsTransFrame(PlotWaveKws kwsPloat) {

        KwsTranscription kwsTranscription = new KwsTranscription(kwsPloat);
        SubFunctionInternalFrame transcription = new SubFunctionInternalFrame(false, true, true);
        transcription.setTitle("Transcription");
        transcription.setVisible(true);
        transcription.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
        mainFrame.screenproperty.addSoundImprovePanel(transcription);
        transcription.add(kwsTranscription);
        mainFrame.jDesktopPane1.add(transcription);
        mainFrame.screenproperty.resizeScreen(mainFrame);
        try {
            transcription.setSelected(true);
        } catch (final java.beans.PropertyVetoException e) {
        }
    }
}
