/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Tatapower SED
 *
 */
public class PhoneReg_Assames {

    private String userID;

    public PhoneReg_Assames(String userID) {
        this.userID = userID;
    }

    public void runCommand(String rootPath) {
        try {
            File wavefile = new File(rootPath + "/" + userID + "/" + userID + ".wav");
            if (!wavefile.exists()) {
                return;

            }

            //phoneRec("./kwspotter/scripts/phoneRecnew2.sh " + wavefile.getAbsolutePath() + " assamese " + userID);
            phoneRec("./energyCalculation/runEnergy.sh " + wavefile.getAbsolutePath());

            
            
        } catch (Exception er) {
            System.err.println(er);
        }

    }

    private void phoneRec(String cmd) {
        // this is the command to execute in the Unix shell
        // create a process for the shell
        try {
            System.out.println(cmd);
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
            pb.redirectErrorStream(true); // use this to capture messages sent to stderr
            Process shell = null;
            try {
                shell = pb.start();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            InputStream shellIn = shell.getInputStream(); // this captures the output from the command
            try {
                int shellExitStatus = shell.waitFor();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // wait for the shell to finish and get the return code
            // at this point you can process the output issued by the command
            // for instance, this reads the output and writes it to System.out:	
            int c;
            try {
                while ((c = shellIn.read()) != -1) {
                    System.out.write(c);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // close the stream
            try {
                shellIn.close();
            } catch (IOException ignoreMe) {
            }
        } catch (Exception er) {
            System.err.println(er);
        }
    }

    public ArrayList readResultFile() {


        ArrayList fileData = null;
        try {
            fileData = new ArrayList();
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("energyCalculation/output.txt");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                strLine = strLine.trim();

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
        if (fileData.size() == 0) {
            return null;
        }
        return fileData;


    }

    void runCommandDU(String rootPath) {
        try {
            File wavefile = new File(rootPath + "/" + userID + "/" + userID + ".wav");
            if (!wavefile.exists()) {
                return;

            }

            phoneRec("./kwspotter/scripts/phoneRecnew2.sh " + wavefile.getAbsolutePath() + " assamese " + userID);

        } catch (Exception er) {
            System.err.println(er);
        }
    }

    String readResultFileDU() {

        String fileData = "";
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("kwspotter/DUTESTDATA/resfile");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                strLine = strLine.trim();
                fileData += strLine;
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return fileData;
    }
}
