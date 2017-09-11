/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.annotations;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author VRS
 */
public class Test {
    
    
    public static void main(String args[]){
    File a = new File("tata.wav");
    File b = new File("/home/VRS/lrmakode/TATA-15/Kumarsenthil.wav");
    a.renameTo(b);
    }
}
