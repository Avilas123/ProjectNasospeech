/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tatapower SED
 *
 */
public class CopyServerFiles {

    public boolean copy(String source, String destination) {
        try {
            File sfile = new File(source);
            File dfile = new File(destination);
            if (sfile.exists() && dfile.exists()) {
                if (sfile.isFile() && dfile.isDirectory()) {
                    dfile = new File(destination + "/" + sfile.getName());
                    new CommonFunction().copyFile(sfile, dfile);
                    sfile.delete();
                    return true;
                }
            }
        } catch (Exception er) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, er);
        }
        return false;
    }
}
