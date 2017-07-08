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
public class Kws_Hindi_phone {

    private String userID;
    private String rootPath;
    private String no_of_split = "4";
    private ArrayList<Thread> threadList = null;

    public Kws_Hindi_phone(String userID, String rootPath) {
        this.userID = userID;
        this.rootPath = rootPath;
        this.threadList = new ArrayList<>();
    }

    public boolean runCommandWithkeyword(ArrayList keyList) {
        if (keyList == null) {
            return false;
        }

        try {
            // Create file 
            String preStr = "$phones = a | b | ch | d | e | g | h | i | j | k | l | m | n | o | p | r | r' | s | sil | t | u | v | y | ";
            String postStr = "(<$phones>)";
            FileWriter fstream = new FileWriter("kws/phone/hindi/" + userID + "gram.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(preStr);
            for (int i = 0; i < keyList.size() - 1; i++) {
                out.write((keyList.get(i).toString()).trim() + " | ");
                //Close the output stream
            }
            if (keyList.get(keyList.size() - 1) != null) {
                out.write((keyList.get(keyList.size() - 1).toString()).trim() + " ;");
            }
            out.newLine();
            out.write(postStr);
            out.close();

            File gramFile = new File("kws/phone/hindi/" + userID + "gram.txt");
            if (!gramFile.exists()) {
                return false;
            }

            File waveFile = new File(rootPath + "/" + userID + "/" + userID + ".wav");
            //End gram file
            // End create Gram.txt
            // Single Process changed on 23/03/2013
     /*
             phoneRec("HParse kws/phone/hindi/" + userID + "gram.txt kws/phone/hindi/" + userID + "wdnet.txt");

             phoneRec("HCopy -C kws/phone/hindi/analysis.conf " + rootPath + "/" + userID + "/" + userID + ".wav kws/phone/hindi/" + userID + ".mfcc");

             phoneRec("HVite -T 1 -D -A -H kws/phone/hindi/macros -H kws/phone/hindi/hmmdefs -C kws/phone/hindi/analysis_train.conf -i kws/phone/hindi/" + userID + "recout.mlf -o SW -w kws/phone/hindi/" + userID + "wdnet.txt -p -10.0 -s 0 kws/phone/hindi/dict_kws.txt kws/phone/hindi/hmmlist.txt kws/phone/hindi/" + userID + ".mfcc");

             *   RunCommand("./kws/splitfile/splitWaveFile.sh " + waveFile.getAbsolutePath() + " " + no_of_split + " " + gramFile.getAbsolutePath() + " hindi phone");
             * 
             */

            WaveProcess waveProcess = new WaveProcess();
            String fileSplitLength = waveProcess.getLength(waveFile.getAbsolutePath(), no_of_split);
            if (waveProcess.getSeconds() > 3600) {
                no_of_split = "8";
            }
            waveProcess = null;

            //36072799265

            RunCommand("HParse " + gramFile.getAbsolutePath() + " kws/phone/hindi/" + userID + "_wdnet.txt");


            RunCommand("./kws/splitfile/wavsplitter " + waveFile.getAbsolutePath() + " " + no_of_split);

            for (int k = 1; k <= Integer.parseInt(no_of_split); k++) {
                File splitfile = new File(rootPath + "/" + userID + "/" + userID + "_P" + k + ".wav");
                Thread thread = new Thread(new Decoder(splitfile.getAbsolutePath(), gramFile.getAbsolutePath()));
                thread.start();
                threadList.add(thread);
            }

            for (Thread thread : threadList) {
                thread.join();
            }



            if (fileSplitLength == null) {
                return false;
            }
            if (fileSplitLength.length() < 1) {
                return false;
            }

            File resultPath = new File("kws/phone/hindi");

            new JoinKwsResult().joinSplitFiles(userID, resultPath.getAbsolutePath(), fileSplitLength, Integer.parseInt(no_of_split));


        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return false;
    }

    public void runCommand(String userID, String rootPath) {
        try {
            RunCommand("HCopy -C Kws/Phone_Hindi/analysis.conf " + rootPath + "/" + userID + "/phenome.wav Kws/Phone_Hindi/" + userID + "phoneme.mfcc");

            RunCommand("HVite -T 1 -D -A -H Kws/Phone_Hindi/macros -H Kws/Phone_Hindi/hmmdefs -C Kws/Phone_Hindi/analysis_train.conf -i Kws/Phone_Hindi/" + userID + "recout.mlf -o SW -w Kws/Phone_Hindi/" + userID + "wdnet.txt -p -10.0 -s 0 Kws/Phone_Hindi/dict_kws.txt Kws/Phone_Hindi/hmmlist.txt Kws/Phone_Hindi/" + userID + "phoneme.mfcc");
      
            RunCommand("/home/sid/remote/kws/phone/hindi/replace_eng_with_hindi.sh"+" "+userID+"recout.mlf");
        } catch (Exception er) {
            System.err.println(er);
        }

    }

    private void RunCommand(String cmd) {
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

    public ArrayList readResultFile() {


        ArrayList fileData = null;
        try {
            fileData = new ArrayList();
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("kws/phone/hindi/" + userID + "recout.mlf");
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

            try {
                RunCommand("rm kws/phone/hindi/" + userID + "*");
                RunCommand("rm " + userID + "*");
            } catch (Exception er) {
                System.err.println(er);
            }

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

    class Decoder implements Runnable {

        private String wavefile;
        private String gram;

        public Decoder(String wavefile, String gram) {
            this.wavefile = wavefile;
            this.gram = gram;
        }

        @Override
        public void run() {
            RunCommand("./kws/splitfile/splitWaveFile.sh " + this.wavefile + " " + no_of_split + " " + this.gram + " hindi phone");

        }
    }
}
