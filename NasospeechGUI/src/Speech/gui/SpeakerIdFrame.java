/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.speakeridentification.Ivector;
import Speech.speakeridentification.ListofSpeakers;
import java.awt.Color;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Tatapower SED
 *
 */
public class SpeakerIdFrame {

    private MainFrame mFrame;
    private AudioInputStream audioInputStream;

    public SpeakerIdFrame(MainFrame mFrame) {
        this.mFrame = mFrame;
    }

    public void createFrame(String panelName, String language) {
        try {
            JDesktopPane newPane = new JDesktopPane();
            InternalFrame soundKwsleft = new InternalFrame(false, false, false, mFrame);
            ListofSpeakers lSpeakers = new ListofSpeakers(mFrame, soundKwsleft);
            Ivector ivector = new Ivector(mFrame, lSpeakers);

            //Sound Kws Main Frame
            InternalFrame soundKws = new InternalFrame(false, true, true, mFrame);
            soundKws.setTitle(panelName);
            soundKws.setVisible(true);
            soundKws.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
            mFrame.screenproperty.addSoundImprovePanel(soundKws);
            soundKws.add(newPane);
            //Left Frame

            soundKwsleft.setTitle("List of speakers");
            soundKwsleft.setVisible(true);
            soundKwsleft.setLocation(0, 0);
            soundKwsleft.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
            mFrame.screenproperty.addResultKeywordPanel(soundKwsleft);
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.getVerticalScrollBar().setUnitIncrement(10);
            scrollPane.getViewport().add(lSpeakers);
            scrollPane.setBackground(Color.WHITE);
            soundKwsleft.add(lSpeakers);
            newPane.add(soundKwsleft);
            //End Left Frame

            //Right Frame

            JPanel rightPanel = new JPanel();

            rightPanel.setVisible(true);
            mFrame.screenproperty.addSpeakerID(rightPanel);
            rightPanel.add(ivector);
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
}
