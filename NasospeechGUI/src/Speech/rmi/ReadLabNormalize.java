package Speech.rmi;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tatapower SED
 *
 */
public class ReadLabNormalize {

    String userID;
    String keyStartTime;
    String keyEndTime;
    String keyword;
    String keywordScore;
    double normalizedScore;
    public static final float CONST = 100000;
    int count = 1;
    //int v1,v2;
    BigInteger v1;
    BigInteger v2;
    float v4;
    static String previous_keyword;
    static float previous_score;
    static BigInteger old_v2 = new BigInteger("0");

    ReadLabNormalize(String userID) throws IOException, InterruptedException {
        //sortFile();
        //readSortedFile();
        this.userID = userID;
    }

    public void sortFile() throws IOException, InterruptedException {
        String cmd1 = "cat kwspotter/misc/temp_" + userID + ".lab | sort -n > kwspotter/misc/sorted_temp" + userID + ".lab";
        // this is the command to execute in the Unix shell
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd1);
        pb.redirectErrorStream(true); // use this to capture messages sent to stderr
        Process shell = pb.start();
        InputStream shellIn = shell.getInputStream(); // this captures the output from the command
        int shellExitStatus = shell.waitFor(); // wait for the shell to finish and get the return code
        // at this point you can process the output issued by the command
        // for instance, this reads the output and writes it to System.out:
        int c;
        while ((c = shellIn.read()) != -1) {
            System.out.write(c);
        }
        // close the stream
        try {
            shellIn.close();
        } catch (IOException ignoreMe) {
        }

    }

    //-------------------------------
    public void readSortedFile() {
        try {
            // Open the file that is the first
            // command line parameter

            FileInputStream fstream = null;
            try {
                fstream = new FileInputStream("kwspotter/misc/sorted_temp" + userID + ".lab");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReadLabNormalize.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            try {
                //Read File Line By Line
                FileWriter fstreamout = new FileWriter("kwspotter/misc/new_temp" + userID + ".lab");
                BufferedWriter out = new BufferedWriter(fstreamout);
                while ((strLine = br.readLine()) != null) {
                    // Print the content on the console

                    strLine = strLine.trim();
                    if (strLine.startsWith("#") || strLine.startsWith("\"") || strLine.startsWith(".") || strLine.contains("sil")) {
                        continue;
                    }

                    if (strLine.length() > 0) {
                        StringTokenizer st = new StringTokenizer(strLine, " ");
                        if (st.hasMoreElements()) {
                            keyStartTime = st.nextElement().toString();
                            v1 = new BigInteger(keyStartTime);
                        }
                        if (st.hasMoreElements()) {
                            keyEndTime = st.nextElement().toString();
                            v2 = new BigInteger(keyEndTime);
                        }
                        if (st.hasMoreElements()) {
                            keyword = st.nextElement().toString();
                        }

                        if (st.hasMoreElements()) {
                            keywordScore = st.nextElement().toString();
                            //--check if score <=-20.000000 ---
                            v4 = Float.valueOf(keywordScore.trim()).floatValue();
                            if (v4 < -21) {
                                continue;
                            }

                        }

                        out.write(keyStartTime + " " + keyEndTime + " " + keyword + " " + keywordScore);
                        out.newLine();
                        old_v2 = v2;
                    }
                }
                System.out.println("Lab file written successfully !");
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadLabNormalize.class.getName()).log(Level.SEVERE, null, ex);
            }

            in.close();

        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }


    }

    //============
    public void deleteFile(String filePath) {
        File f = new File(filePath);
        if (f.exists()) {
            f.delete();
        }

    }

    public static void main(String args[]) throws IOException, InterruptedException {
        new ReadLabNormalize("");
    }
}