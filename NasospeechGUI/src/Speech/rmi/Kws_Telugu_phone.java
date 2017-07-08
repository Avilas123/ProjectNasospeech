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
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author Tatapower SED
 *
 */
public class Kws_Telugu_phone {

    private String userID;
    private HashMap englishKwsList;

    public Kws_Telugu_phone(String userID) {
        this.userID = userID;
    }

    public void runCommandWithKeyword(String rootPath, ArrayList selectedKeyword) {
        try {

            String fullPath = "";
            CommonFunction cfun = new CommonFunction();
            try {
                File dir = new File("kwspotter/telugu/telugukeywordList.txt");
                fullPath = dir.getAbsolutePath();
            } catch (Exception er) {
                System.err.println(er);
            }
            loadTotalKeywords(fullPath);

            if (selectedKeyword.isEmpty() || englishKwsList.isEmpty()) {

                File kwsSource = new File("kwspotter/telugu/keywordList");
                File kwsDes = new File(rootPath + "/" + userID + "/" + userID + "keywordList");
                cfun.copyFile(kwsSource, kwsDes);

                File lexSource = new File("kwspotter/telugu/lexicon");
                File lesDes = new File(rootPath + "/" + userID + "/" + userID + "lexicon");
                cfun.copyFile(lexSource, lesDes);

            } else {

                FileWriter kwsfstream = new FileWriter(rootPath + "/" + userID + "/" + userID + "keywordList");
                BufferedWriter kwsout = new BufferedWriter(kwsfstream);
                FileWriter lexfstream = new FileWriter(rootPath + "/" + userID + "/" + userID + "lexicon");
                BufferedWriter lexout = new BufferedWriter(lexfstream);
                for (int i = 0; i < selectedKeyword.size(); i++) {
                    if (selectedKeyword.get(i) == null) {
                        continue;
                    }
                    String selectedKws = selectedKeyword.get(i).toString();
                    selectedKws = selectedKws.trim();
                    selectedKws = selectedKws.replaceAll("\\s+", "");

                    if (englishKwsList.get(selectedKws) != null) {

                        kwsout.write((selectedKws).trim());

                        kwsout.newLine();
                        lexout.write(((selectedKws).trim()) + "\t" + (englishKwsList.get(selectedKws).toString()).trim());
                        lexout.newLine();
                    }

                }
                kwsout.write("sil");
                lexout.write("sil\tsil");
                kwsout.close();
                lexout.close();

            }


            File wavefile = new File(rootPath + "/" + userID + "/" + userID + ".wav");
            if (!wavefile.exists()) {
                return;
            }

            File seKws = new File(rootPath + "/" + userID + "/" + userID + "keywordList");
            if (!seKws.exists()) {
                return;
            }

            File leKws = new File(rootPath + "/" + userID + "/" + userID + "lexicon");
            if (!leKws.exists()) {
                return;
            }


            phoneRec("./kwspotter/scripts/kwspotnew2.sh " + wavefile.getAbsolutePath() + " telugu " + seKws.getAbsolutePath() + " " + leKws.getAbsolutePath() + " " + this.userID);

        } catch (Exception er) {
            System.err.println(er.getMessage());
        }

    }

    public void runCommandWithOutKeyword(String rootPath) {
        try {
            File wavefile = new File(rootPath + "/" + userID + "/" + userID + ".wav");
            if (!wavefile.exists()) {
                return;
            }
            phoneRec("./kwspotter/scripts/kwspot.sh " + wavefile.getAbsolutePath() + " telugu");
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

            ReadLabNormalize readNorm = new ReadLabNormalize(userID);
            readNorm.sortFile();
            readNorm.readSortedFile();
            FileInputStream fstream = new FileInputStream("kwspotter/misc/new_temp" + userID + ".lab");
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

    private void loadTotalKeywords(String fileName) {
        englishKwsList = new HashMap();
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(fileName);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                strLine = strLine.trim();


                // System.out.println(strLine);

                if (strLine.length() > 0) {
                    String word = null, phoneme = null;
                    StringTokenizer st = new StringTokenizer(strLine, "\t");
                    if (st.hasMoreElements()) {
                        word = st.nextElement().toString();
                    }
                    if (st.hasMoreElements()) {
                        phoneme = st.nextElement().toString();
                    }
                    if (word != null && phoneme != null) {
                        word = word.trim();
                        word = word.replaceAll("\\s+", "");
                        englishKwsList.put(word, phoneme);
                    }
                }
            }
            //Close the input stream
            in.close();



        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String agrs[]) {

        Kws_Telugu_phone kwsTelugu = new Kws_Telugu_phone(null);
        kwsTelugu.loadTotalKeywords("E:/telugukeywordList.txt");

    }
}
