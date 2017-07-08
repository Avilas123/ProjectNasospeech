/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Tatapower SED
 *
 */
public class PDS_Combined {

    private String userID;
    private String rootPath;

    public PDS_Combined(String userID, String rootPath) {
        this.userID = userID;
        this.rootPath = rootPath;
    }

    public boolean commandWithAnnotation(ArrayList annotationList, String threshold) {

        try {
            // Create file 
            File wavefile = new File(rootPath + "/" + userID + "/" + userID + ".wav");
            if (!wavefile.exists()) {
                return false;
            }

            try {
                File dir = new File(rootPath + "/" + userID + "/SilRem");
                dir.mkdir();
            } catch (Exception er) {
                System.err.println(er);
            }

            String fullPath = "";

            try {
                File dir = new File(rootPath + "/" + userID);
                fullPath = dir.getAbsolutePath();
            } catch (Exception er) {
                System.err.println(er);
            }


            FileWriter fstream = new FileWriter(rootPath + "/" + userID + "/" + userID + ".txt");
            BufferedWriter out = new BufferedWriter(fstream);
            if (annotationList != null) {
                for (int i = 0; i < annotationList.size(); i++) {
                    out.write((annotationList.get(i).toString()).trim());
                    out.newLine();
                }
            }
            out.close();



            File annotationFile = new File(rootPath + "/" + userID + "/" + userID + ".txt");
            if (!annotationFile.exists()) {
                return false;
            }


            phoneRec("./PDS/pds/Executables/spenhancecombined " + fullPath + " " + userID + ".wav " + threshold);
            phoneRec("./PDS/pds/Executables/fixcontour " + fullPath + " " + userID + ".wav");

            return true;
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return false;
    }

    private void phoneRec(String cmd) {
        // this is the command to execute in the Unix shell
        // create a process for the shell
        try {
            System.out.println(cmd + "\n\n");
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
            pb.redirectErrorStream(true); // use this to capture messages sent to stderr
            Process shell = null;
            try {
                shell = pb.start();//The start() method creates a new Process instance
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


        ArrayList fileData = new ArrayList();
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(rootPath + "/" + userID + "/" + userID + ".wav.txt");
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

    public ArrayList readResultAnnotationFile() {


        ArrayList fileData = new ArrayList();
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(rootPath + "/" + userID + "/" + userID + ".wav.txt");
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
}
