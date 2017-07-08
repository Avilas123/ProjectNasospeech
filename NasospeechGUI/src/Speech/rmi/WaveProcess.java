/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

/**
 *
 * @author Tatapower SED
 *
 */
import java.io.File;
import java.math.BigDecimal;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class WaveProcess {

    private int seconds = 0;

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getLength(String path, String noSplit) throws Exception {
        AudioInputStream stream = null;
        String fileDur = "";
        try {
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("File Not exists " + path);
                return "";
            }

            stream = AudioSystem.getAudioInputStream(file);
            double fileLength = stream.getFrameLength() / stream.getFormat().getFrameRate();
            setSeconds((int) fileLength);
            BigDecimal fileLen = new BigDecimal(fileLength);
            fileLen = fileLen.multiply(new BigDecimal("10000000"));
            fileDur = ((fileLen.divide(new BigDecimal(noSplit))).toBigInteger()).toString();
            stream.close();
        } catch (Exception e) {
            System.out.println("File Not exists " + e);
            return "";
        } finally {
            try {
                stream.close();
            } catch (Exception ex) {
            }
        }
        return fileDur;
    }

    /* public String getLength(String path, String noSplit) throws Exception {
     String fileDur = "";
     try {
     AudioInputStream stream;
     File file = new File(path);
     stream = AudioSystem.getAudioInputStream(file);
     AudioFormat format = stream.getFormat();
     if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
     format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format
     .getSampleRate(), format.getSampleSizeInBits() * 2, format
     .getChannels(), format.getFrameSize() * 2, format
     .getFrameRate(), true); // big endian
     stream = AudioSystem.getAudioInputStream(format, stream);
     }
     DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat(),
     ((int) stream.getFrameLength() * format.getFrameSize()));
     Clip clip = (Clip) AudioSystem.getLine(info);
     clip.close();
     double fileLength = clip.getBufferSize()
     / (clip.getFormat().getFrameSize() * clip.getFormat()
     .getFrameRate());
    
     BigDecimal fileLen = new BigDecimal(fileLength);
     fileLen = fileLen.multiply(new BigDecimal("10000000"));
     fileDur = ((fileLen.divide(new BigDecimal(noSplit))).toBigInteger()).toString();
     } catch (NumberFormatException e) {
     System.err.println("Number Formate Error");
    
     } catch (Exception er) {
     System.err.println(er);
     }
     return fileDur;
     }*/
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 4; i++) {
                System.out.println(new WaveProcess().getLength("tyu.wav", "4"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
