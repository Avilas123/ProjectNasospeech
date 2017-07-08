/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

import java.io.File;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author Tatapower SED
 *
 */
public class DeleteServerFiles {

    private Connection conn = null;

    public String delete(String fileName) {
        try {
            File sfile = new File(fileName);
            if (sfile.exists()) {
                if (sfile.isFile()) {
                    String hashvalue = Hash.getHashValue(AudioSystem.getAudioInputStream(sfile));
                    boolean deleted = sfile.delete();
                    if (deleted) {
                        return hashvalue;
                    }
                }
            }
        } catch (Exception er) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, er);
        }
        return null;
    }
}
