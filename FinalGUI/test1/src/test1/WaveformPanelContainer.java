/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;
import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import java.awt.*;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author user
 */
public class WaveformPanelContainer extends JPanel {
    private ArrayList singleChannelWaveformPanels = new ArrayList();
    private AudioInfo audioInfo = null;

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
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(waveformPanel, BorderLayout.CENTER);

        JLabel label = new JLabel("Channel " + ++index);
        panel.add(label, BorderLayout.NORTH);
        //JButton button=new JButton("xxxxxxxxx");
       // panel.add(button);to dssign add your code here
        
        
        
        
        
        
        
        
        

        return panel;
    }


}