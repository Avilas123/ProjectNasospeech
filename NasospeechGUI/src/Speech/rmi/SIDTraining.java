/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Tatapower SED
 *
 */
public class SIDTraining {

    private String userID;

    public SIDTraining(String UserID) {
        this.userID = UserID;
    }

    public void runCommand(String fileName, String groupName) {
        try {
            phoneRec("./spkrRec/newScripts/train_main_multiuser.sh " + fileName + " " + userID + " " + groupName);
        } catch (Exception er) {
            System.err.println(er);
        }


    }
    // execShellScript(String command);
    private void phoneRec(String cmd) {
        // this is the command to execute in the Unix shell
        // create a process for the shell
        try {
            System.out.println(cmd + "\n\n");
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

    public String readResultFile() {


        String result = null;
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("spkrRec/misc/" + userID);
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
                    result = strLine;
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        return result;

    }
}
