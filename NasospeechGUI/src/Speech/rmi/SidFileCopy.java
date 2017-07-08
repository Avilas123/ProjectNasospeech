/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tatapower SED
 *
 */
public class SidFileCopy {

    public SidFileCopy() {
    }

    public boolean copyAll(ArrayList<String> fileList, String userName) {
        BufferedWriter br = null;
        try {
            if (fileList == null) {
                return false;
            }

            File fn = new File("spkrRec/ivect/sidTest/fn");
            if (!fn.exists()) {
                return false;
            }

            File misc = new File("spkrRec/misc");

            if (!misc.exists()) {
                return false;
            }

            File refList = new File(misc.getAbsolutePath() + "/refList_" + userName);
            br = new BufferedWriter(new FileWriter(refList));
            for (String fname : fileList) {
                try {

                    File ufileX_Des = new File("spkrRec/ivect/sidTest/fn/" + fname + ".x");
                    File ufileFN_Des = new File("spkrRec/ivect/sidTest/fn/" + fname + ".fn");

                    if (ufileX_Des.exists() && ufileFN_Des.exists()) {
                        try {

                            br.write(ufileX_Des.getAbsolutePath());
                            br.newLine();
                        } catch (IOException ex) {
                            Logger.getLogger(SidFileCopy.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (Exception er) {
                    Logger.getLogger(SidFileCopy.class.getName()).log(Level.SEVERE, null, er);
                }
            }
            System.out.println("Files are copied");
            return true;
        } catch (FileNotFoundException er) {
            Logger.getLogger(SidFileCopy.class.getName()).log(Level.SEVERE, null, er);
        } catch (Exception er) {
            Logger.getLogger(SidFileCopy.class.getName()).log(Level.SEVERE, null, er);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(SidFileCopy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
