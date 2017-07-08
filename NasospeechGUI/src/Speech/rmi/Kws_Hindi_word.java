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
public class Kws_Hindi_word {

    private String userID;
    private String rootPath;
    private String no_of_split = "4";
    private ArrayList<Thread> threadList = null;

    public Kws_Hindi_word(String userID, String rootPath) {
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
            FileWriter fstream = new FileWriter("kws/word/Hindi/" + userID + "_gram.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(preStr);
            System.out.println(keyList.size() + " KW SIZE");
            for (int i = 0; i < keyList.size(); i++) {
                out.write((keyList.get(i).toString()).trim() + " | ");
                //Close the output stream

            }

            out.write("garbage | sil ;");
            out.newLine();
            out.write(postStr);
            out.close();
            //End create gram file


            File gramFile = new File("kws/word/Hindi/" + userID + "_gram.txt");
            System.out.println(gramFile.getPath());
            if (!gramFile.exists()) {
                System.out.println("Nahi Hai");
                return false;
            }

            File waveFile = new File(rootPath + "/" + userID + "/" + userID + ".wav");



            /*
             phoneRec("HParse kws/word/Hindi/" + userID + "_gram.txt kws/word/Hindi/" + userID + "_wdnet.txt");
             phoneRec("HCopy -T 1 -A -D -C kws/word/Hindi/analysis.conf " + rootPath + "/" + userID + "/" + userID + ".wav kws/word/Hindi/" + userID + ".mfcc");
             phoneRec("HVite -T 1 -D -A -V -H kws/word/Hindi/Models/macros -H kws/word/Hindi/Models/hmmdefs -C kws/word/Hindi/analysis_train.conf -i kws/word/Hindi/" + userID + "recout.mlf -w kws/word/Hindi/" + userID + "_wdnet.txt -p -10.0 -s 0 kws/word/Hindi/dict.txt kws/word/Hindi/hmmlist.txt kws/word/Hindi/" + userID + ".mfcc");
             yy            

             RunCommand("./kws/splitfile/splitWaveFile.sh " + waveFile.getAbsolutePath() + " " + no_of_split + " " + gramFile.getAbsolutePath() + " Hindi word");
             */


            WaveProcess waveProcess = new WaveProcess();
            String fileSplitLength = waveProcess.getLength(waveFile.getAbsolutePath(), no_of_split);
            if (waveProcess.getSeconds() > 3600) {
                no_of_split = "8";
            }
            waveProcess = null;

            //36072799265

            RunCommand("HParse " + gramFile.getAbsolutePath() + " kws/word/Hindi/" + userID + "_wdnet.txt");


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
            File resultPath = new File("kws/word/Hindi");

            new JoinKwsResult().joinSplitFiles(userID, resultPath.getAbsolutePath(), fileSplitLength, Integer.parseInt(no_of_split));


        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return false;
    }

    public void runCommandWithOutKeyword() {
        try {

            RunCommand("HCopy -C Kws/Phone_Hindi/analysis.conf " + rootPath + "/" + userID + "/phenome.wav Kws/Phone_Hindi/" + userID + "phoneme.mfcc");

            RunCommand("HVite -T 1 -D -A -H Kws/Phone_Hindi/macros -H Kws/Phone_Hindi/hmmdefs -C Kws/Phone_Hindi/analysis_train.conf -i Kws/Phone_Hindi/" + userID + "recout.mlf -o SW -w Kws/Phone_Hindi/" + userID + "wdnet.txt -p -10.0 -s 0 Kws/Phone_Hindi/dict_kws.txt Kws/Phone_Hindi/hmmlist.txt Kws/Phone_Hindi/" + userID + "phoneme.mfcc");

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


        ArrayList fileData = new ArrayList();
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("kws/word/Hindi/" + userID + "recout.mlf");
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
            RunCommand("rm kws/word/Hindi/" + userID + "*");
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
            RunCommand("./kws/splitfile/splitWaveFile.sh " + this.wavefile + " " + no_of_split + " " + this.gram + " Hindi word");

        }
    }
}
