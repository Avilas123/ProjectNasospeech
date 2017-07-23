package test1;

import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.awt.*;
import java.io.*;
import javax.swing.JFileChooser;
import java.io.File;  

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author user
 */
public class WaveformDisplaySimulator {

    public static void main(String[] args) {
        try {
           // MainGuI mainframe=new MainGuI();
            //mainframe.setVisible(true);
     
            JFrame frame = new JFrame("Waveform Display Simulator");
            frame.setBounds(200,200, 500, 350);
            JFileChooser fc = new JFileChooser();
            int selectedFile = fc.showOpenDialog(null);
            if(selectedFile == JFileChooser.APPROVE_OPTION){
            
             File f =fc.getSelectedFile();

           // File file = new File(args[0]);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream (new FileInputStream (f)));

            WaveformPanelContainer container = new WaveformPanelContainer();
            container.setAudioToDisplay(audioInputStream);

            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(container, BorderLayout.CENTER);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.show();
            frame.validate();
            frame.repaint();
        }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

    

