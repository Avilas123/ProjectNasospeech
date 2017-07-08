/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author Tatapower SED
 *
 */
public class StreamConverter {

    public StreamConverter() {
    }

    public void byteTowavefile(byte[] audioBytes, AudioInputStream inputStream, String fileName) {
        if (audioBytes == null || inputStream == null || fileName == null) {
            return;
        }

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
            AudioInputStream audioInputStream = new AudioInputStream(bais, inputStream.getFormat(), audioBytes.length / inputStream.getFormat().getFrameSize());
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File(fileName));
        } catch (Exception ex) {
            Logger.getLogger(StreamConverter.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
