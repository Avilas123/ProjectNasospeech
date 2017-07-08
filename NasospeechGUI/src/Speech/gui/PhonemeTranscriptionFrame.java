/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.phoneme.PhonemeTranscription;
import Speech.phoneme.PlotWavePhoneme;

/**
 *
 * @author Tatapower SED
 *
 */
public class PhonemeTranscriptionFrame {

    private MainFrame mainFrame;

    public PhonemeTranscriptionFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void createKwsTransFrame(PlotWavePhoneme phoneWave) {
        PhonemeTranscription phonemeTranscription = new PhonemeTranscription(phoneWave);
        SubFunctionInternalFrame transcription = new SubFunctionInternalFrame(false, true, true);
        transcription.setTitle("Transcription");
        transcription.setVisible(true);
        transcription.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
        mainFrame.screenproperty.addSoundImprovePanel(transcription);
        transcription.add(phonemeTranscription);
        mainFrame.jDesktopPane1.add(transcription);
        mainFrame.screenproperty.resizeScreen(mainFrame);
        try {
            transcription.setSelected(true);
        } catch (final java.beans.PropertyVetoException e) {
        }

    }
}
