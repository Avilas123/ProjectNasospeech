/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.MFCC;

import Speech.phoneme.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tatapower SED
 *
 */
public class ExportPhoneme {

    public void startProcess(List<String> phones, String locPath, String hashvalue, String language) {
        BufferedWriter bWrite = null;
        if (hashvalue == null || locPath == null || phones == null || language == null) {
            return;
        }
        if (locPath.trim().isEmpty() || hashvalue.trim().isEmpty() || language.trim().isEmpty()) {
            return;
        }


        try {
            bWrite = new BufferedWriter(new FileWriter(new File(locPath)));

            bWrite.write("<" + hashvalue + "%" + language + ">");
            bWrite.newLine();
            for (String lines : phones) {
                try {
                    bWrite.write(lines);
                    bWrite.newLine();
                } catch (Exception er) {
                }
            }


        } catch (IOException ex) {
            Logger.getLogger(ExportPhoneme.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (bWrite != null) {
                    bWrite.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ExportPhoneme.class.getName()).log(Level.SEVERE, null, ex);
            }
        }




    }
}
