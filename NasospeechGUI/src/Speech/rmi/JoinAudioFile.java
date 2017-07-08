/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

import java.io.File;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author Tatapower SED
 *
 */
public class JoinAudioFile {

    public boolean doJoin(ArrayList<String> fileList, String userDirPath, String userName) {

        try {
            Vector<AudioInputStream> audioInputStreamList = new Vector<>();
            AudioInputStream audioInputStream = null;
            long totalFileLength = 0;

            for (String fileName : fileList) {
                try {
                    File sfile = new File(userDirPath + "/" + fileName);
                    if (!sfile.exists()) {
                        System.out.println("No file" + userDirPath + "/" + fileName);
                        continue;
                    }
                    audioInputStream = AudioSystem.getAudioInputStream(sfile);
                    totalFileLength = totalFileLength + audioInputStream.getFrameLength();
                    audioInputStreamList.add(audioInputStream);

                } catch (Exception er) {
                    java.util.logging.Logger.getLogger(JoinAudioFile.class.getName()).log(Level.SEVERE, null, er);
                }
            }

            if ((audioInputStreamList.isEmpty()) || (audioInputStream == null) || (totalFileLength == 0)) {
                return false;
            }
            userDirPath = userDirPath + "/" + userName + ".wav";
            return audioFileJoin(audioInputStreamList, userDirPath, audioInputStream, totalFileLength);

        } catch (Exception er) {
            java.util.logging.Logger.getLogger(JoinAudioFile.class.getName()).log(Level.SEVERE, null, er);
        }

        return false;
    }

    private boolean audioFileJoin(Vector<AudioInputStream> listofStreams, String outputPath, AudioInputStream audioStream, long length) {

        try {
            Enumeration fileStreams = listofStreams.elements();
            AudioInputStream appendedFile = new AudioInputStream(new SequenceInputStream(fileStreams), audioStream.getFormat(), length);
            AudioSystem.write(appendedFile, AudioFileFormat.Type.WAVE, new File(outputPath));
            return true;
        } catch (Exception er) {
            java.util.logging.Logger.getLogger(JoinAudioFile.class.getName()).log(Level.SEVERE, null, er);
        }

        return false;
    }
}
