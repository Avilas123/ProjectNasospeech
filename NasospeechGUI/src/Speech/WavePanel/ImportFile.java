/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.WavePanel;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;

/**
 *
 *  @author Tatapower SED
 *  
 */
public class ImportFile {

    private String hashvalue = null;
    private String language = null;

    public String getHashvalue() {
        return hashvalue;
    }

    public void setHashvalue(String hashvalue) {
        this.hashvalue = hashvalue;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ArrayList phonemeReg(PlotWave pWave) {

        File phonemefile = importFileOpen(".pho");
        if (!phonemefile.exists()) {
            javax.swing.JOptionPane.showMessageDialog(null, "File not available");
            return null;
        }
        if (!phonemefile.getName().endsWith(".pho")) {
            javax.swing.JOptionPane.showMessageDialog(null, "File not available");
            return null;
        }


        String waveFile = phonemefile.getAbsolutePath().replace(".pho", ".wav");
        File audio = new File(waveFile);
        if (!audio.exists()) {
            javax.swing.JOptionPane.showMessageDialog(null, "File not available");
            return null;
        }
        try {
            pWave.createAudioInputStream(audio, null, true);
        } catch (Exception er) {
        }
        ArrayList fileData = null;
        try {
            fileData = new ArrayList();
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(phonemefile);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                strLine = strLine.trim();

                if (strLine.startsWith("<")) {
                    strLine = strLine.replace("<", "");
                    strLine = strLine.replace(">", "");
                    StringTokenizer strTok = new StringTokenizer(strLine, "%");
                    if (strTok.hasMoreTokens()) {
                        setHashvalue(strTok.nextToken());
                    }
                    if (strTok.hasMoreTokens()) {
                        setLanguage(strTok.nextToken());
                    }
                    continue;
                }

                if (strLine.startsWith("#") || strLine.startsWith("\"") || strLine.startsWith(".")) {
                    continue;
                }

                if (strLine.length() > 0) {
                    fileData.add(strLine);
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        if (fileData == null) {
            return null;
        }
        if (fileData.isEmpty()) {
            return null;
        }
        return fileData;

    }

    public ArrayList keywordSpotting(PlotWave pWave) {

        File phonemefile = importFileOpen(".kws");
        if (!phonemefile.exists()) {
            javax.swing.JOptionPane.showMessageDialog(null, "File not available");
            return null;
        }
        if (!phonemefile.getName().endsWith(".kws")) {
            javax.swing.JOptionPane.showMessageDialog(null, "File not available");
            return null;
        }

        String waveFile = phonemefile.getAbsolutePath().replace(".kws", ".wav");
        File audio = new File(waveFile);
        if (!audio.exists()) {
            javax.swing.JOptionPane.showMessageDialog(null, "File not available");
            return null;
        }
        try {
            pWave.createAudioInputStream(audio, null, true);
        } catch (Exception er) {
        }


        ArrayList fileData = null;
        try {
            fileData = new ArrayList();
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(phonemefile);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                strLine = strLine.trim();

                if (strLine.startsWith("<")) {
                    strLine = strLine.replace("<", "");
                    strLine = strLine.replace(">", "");
                    StringTokenizer strTok = new StringTokenizer(strLine, "%");
                    if (strTok.hasMoreTokens()) {
                        setHashvalue(strTok.nextToken());
                    }
                    if (strTok.hasMoreTokens()) {
                        setLanguage(strTok.nextToken());
                    }
                    continue;
                }

                if (strLine.startsWith("#") || strLine.startsWith("\"") || strLine.startsWith(".")) {
                    continue;
                }

                if (strLine.length() > 0) {
                    fileData.add(strLine);
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        if (fileData == null) {
            return null;
        }
        if (fileData.isEmpty()) {
            return null;
        }
        return fileData;

    }

    private File importFileOpen(final String accept) {

        File selectedFile = null;
        try {

            File fileDir = new File(System.getProperty("user.dir"));
            JFileChooser fc = new JFileChooser(fileDir);

            fc.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File file) {

                    if (file.isDirectory()) {
                        return true;
                    }
                    String name = file.getName().toLowerCase();
                    if (name.endsWith(accept)) {
                        return true;
                    }
                    return false;
                }

                @Override
                public String getDescription() {
                    return accept;
                }
            });


            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                selectedFile = fc.getSelectedFile();

            }
        } catch (SecurityException ex) {
            // JavaSound.showInfoDialog();
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return selectedFile;
    }
}
