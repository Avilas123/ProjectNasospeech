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
public class Kws_Assamese_word {

    private String userID;
    private String rootPath;
    private String no_of_split = "4";
    private ArrayList<Thread> threadList = null;

    public Kws_Assamese_word(String userID, String rootPath) {
        this.userID = userID;
        this.rootPath = rootPath;
        this.threadList = new ArrayList<>();
    }

    public boolean runCommandWithKeyword(ArrayList keyList) {
        if (keyList == null) {
            return false;
        }

        try {
            // Create file gram file
            String preStr = "$word = ";
            String postStr = "(<$word>)";
            FileWriter fstream = new FileWriter("kws/word/Assamese/" + userID + "_gram.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(preStr);
            for (int i = 0; i < keyList.size(); i++) {
                out.write((keyList.get(i).toString()).trim() + " | ");
                //Close the output stream
            }
            System.out.println("DONE TRANSEFER");
            out.write("garbage | sil ;");
            out.newLine();
            out.write(postStr);
            out.close();

            //End create gram file

            File gramFile = new File("kws/word/Assamese/" + userID + "_gram.txt");
            if (!gramFile.exists()) {
                return false;
            }

            File waveFile = new File(rootPath + "/" + userID + "/" + userID + ".wav");

            /*        // Single Process changed on 23/03/2013
             * 
             * 

             phoneRec("HParse kws/word/Assamese/" + userID + "_gram.txt kws/word/Assamese/" + userID + "_wdnet.txt");

             phoneRec("HCopy -T 1 -A -D -C kws/word/Assamese/analysis.conf " + rootPath + "/" + userID + "/" + userID + ".wav kws/word/Assamese/" + userID + ".mfcc");

             phoneRec("HVite -T 1 -D -A -V -H kws/word/Assamese/Models/macros -H kws/word/Assamese/Models/hmmdefs -C kws/word/Assamese/analysis_train.conf -i kws/word/Assamese/" + userID + "recout.mlf -w kws/word/Assamese/" + userID + "_wdnet.txt -p -10.0 -s 0 kws/word/Assamese/dict.txt kws/word/Assamese/hmmlist.txt kws/word/Assamese/" + userID + ".mfcc");
             // RunCommand("./kws/splitfile/splitWaveFile.sh " + waveFile.getAbsolutePath() + " " + no_of_split + " " + gramFile.getAbsolutePath() + " Assamese word");
             */

            WaveProcess waveProcess = new WaveProcess();
            String fileSplitLength = waveProcess.getLength(waveFile.getAbsolutePath(), no_of_split);
            if (waveProcess.getSeconds() > 3600) {
                no_of_split = "8";
            }
            waveProcess = null;
            RunCommand("HParse " + gramFile.getAbsolutePath() + " kws/word/Assamese/" + userID + "_wdnet.txt");

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


            //Multi Process


            if (fileSplitLength == null) {
                return false;
            }
            if (fileSplitLength.length() < 1) {
                return false;
            }

            File resultPath = new File("kws/word/Assamese");

            new JoinKwsResult().joinSplitFiles(userID, resultPath.getAbsolutePath(), fileSplitLength, Integer.parseInt(no_of_split));


        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return false;
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


        ArrayList fileData = new ArrayList();
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("kws/word/Assamese/" + userID + "recout.mlf");
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

        try {
            RunCommand("rm kws/word/Assamese/" + userID + "*");
            RunCommand("rm " + userID + "*");
        } catch (Exception er) {
            System.err.println(er);
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
            RunCommand("./kws/splitfile/splitWaveFile.sh " + this.wavefile + " " + no_of_split + " " + this.gram + " Assamese word");

        }
    }
}
