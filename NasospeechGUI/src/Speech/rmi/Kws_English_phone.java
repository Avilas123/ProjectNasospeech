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
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tatapower SED
 *
 **/
public class Kws_English_phone {

    private String userID;
    private HashMap teluguKwsList;
    
    //--
        
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
   
    BigInteger interval;
    float interval_in_sec;
    
    float v4;
    float normalized_score;
    
    static String previous_keyword;
    static float previous_score;
    



    public Kws_English_phone(String userID) {
        this.userID = userID;
    }

    public void runCommandWithKeyword(String rootPath, ArrayList selectedKeyword) {
        try {

            String fullPath = "";
            CommonFunction cfun = new CommonFunction();
            try {
                File dir = new File("kwspotter/english/englishkeywordList.txt");
                fullPath = dir.getAbsolutePath();
            } catch (Exception er) {
                System.err.println(er);
            }
            loadTotalKeywords(fullPath);

            if (selectedKeyword.isEmpty() || teluguKwsList.isEmpty()) {

                File kwsSource = new File("kwspotter/english/keywordList");
                File kwsDes = new File(rootPath + "/" + userID + "/" + userID + "keywordList");
                cfun.copyFile(kwsSource, kwsDes);

                File lexSource = new File("kwspotter/english/lexicon");
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

                    if (teluguKwsList.get(selectedKws) != null) {

                        kwsout.write((selectedKws).trim());

                        kwsout.newLine();
                        lexout.write(((selectedKws).trim()) + "\t" + (teluguKwsList.get(selectedKws).toString()).trim());
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


            phoneRec("./kwspotter/scripts/kwspotnew2.sh " + wavefile.getAbsolutePath() + " english " + seKws.getAbsolutePath() + " " + leKws.getAbsolutePath() + " " + this.userID);

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
            phoneRec("./kwspotter/scripts/kwspot.sh " + wavefile.getAbsolutePath() + " english");
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
      
    //--start output manipulation --
        // read the 'temp_<userid>.lab' file and manipulate the score
        public void normalizeScore() {
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = null;
            try {
                fstream = new FileInputStream("kwspotter/misc/temp_" + userID + ".lab");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReadLabNormalize.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            try {
                //Read File Line By Line
                FileWriter fstreamout = new FileWriter("kwspotter/misc/score_norm_" + userID + ".lab");
               
                BufferedWriter out = new BufferedWriter(fstreamout);
                while ((strLine = br.readLine()) != null) {
                    // Print the content on the console

                    strLine = strLine.trim();
                    if (strLine.startsWith("#") || strLine.startsWith("\"") || strLine.startsWith(".")|| strLine.startsWith("sil")) {
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
                            v4 = Float.valueOf(keywordScore.trim()).floatValue();
                        }
                         
                        /* manipulate/normalize the score of the generated lab file 
                         formula for new score : v4=v4/(v2-v1/10000000)
                         i.e. find the length of keyword(v2-v1) in sec and divide the score by it
                         
                         -------- note : conversion of keyword length to sec ----------
                         a=v2-v1 (in hundred nano sec) 
                         b=a*100 (in nano sec)
                         c=b/1000000 (in milli sec)
                         d=c/1000 (in sec)
                         diff_in_sec=(v2-v1)*100/1000000000 or diff_in_sec=(v2-v1)/10000000 <-- it will give length of keyword in sec
                         -------------------------------
                         */
                         
                         /* 
                        */
                        
                        interval=v2.subtract(v1); //v2-v1 , lenght in 100 of nano sec
                        float interval_f=(float)interval.floatValue()/10000000; //interval or length in sec
                        normalized_score=v4/interval_f; // new score
                        
                        // eliminate the keywords whose length is less then 0.5 sec
                        if(interval_f <.50)
                        {
                            continue;
                        }
                        
                        //out.write(keyStartTime + " " + keyEndTime + " " + keyword + " " + keywordScore);
                        out.write(keyStartTime + " " + keyEndTime + " " + keyword + " " + normalized_score);
                        out.newLine();
                        
                    }
                }
                System.out.println("1. Score normalization & keyword length filter done !");
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadLabNormalize.class.getName()).log(Level.SEVERE, null, ex);
            }

            in.close();
            
            
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }



        // sort the normalized & score filtered output lab file by 4th column ie. score
        public void sortNormalizedFile() throws IOException, InterruptedException {
        	
        String cmd1 = "cat kwspotter/misc/score_norm_" + userID + ".lab | sort -nk4 > kwspotter/misc/score_norm_sorted_" + userID + ".lab";
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
        System.out.println("2. Normalized file sorting done. Sorted by : score ");
    }

    //-------------------------------
    // Filter normalized sorted file by score , taking some threshold
    public void FilterSortedFile() {
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = null;
            try {
                fstream = new FileInputStream("kwspotter/misc/score_norm_sorted_" + userID + ".lab");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReadLabNormalize.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            try {
                //Read File Line By Line
                FileWriter fstreamout = new FileWriter("kwspotter/misc/score_norm_sorted_filtered_s_" + userID + ".lab");
               
                BufferedWriter out = new BufferedWriter(fstreamout);
                int i=1;
                while ((strLine = br.readLine()) != null) {
                    // Print the content on the console
                    
                    strLine = strLine.trim();
                    if (strLine.startsWith("#") || strLine.startsWith("\"") || strLine.startsWith(".")) {
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
                            if (v4 < -85) 
                            { // eliminate all rows where v4 value is less than -85, ie. values like -86, -87 etc will be eliminated.
                                continue;
                            }
                        }
                        
                       //out.write(keyStartTime + " " + keyEndTime + " " + keyword + " " + keywordScore);
                        out.write(keyStartTime + " " + keyEndTime + " " + keyword + " " + keywordScore);
                        out.newLine();
                        //old_v2 = v2;
                    }
                }
                System.out.println("3. Normalized file filtering done ! Threshold : -85 ");
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadLabNormalize.class.getName()).log(Level.SEVERE, null, ex);
            }

            in.close();
            
            
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }


		// filter overlapping keywords
        public void filterOverlappingKeywords() { 
        try {
            // Open the file that is the first
            // command line parameter
            BigInteger prev_v1 = new BigInteger("0");
            BigInteger prev_v2 = new BigInteger("0");
                                   
            double prev_score=0;
            
            double l1,u1,l2,u2,s1,s2;
            FileInputStream fstream = null;
            
            try {
                fstream = new FileInputStream("kwspotter/misc/score_norm_sorted_filtered_s_" + userID + ".lab");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReadLabNormalize.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            try {
                //Read File Line By Line
                FileWriter fstreamout = new FileWriter("kwspotter/misc/score_norm_sorted_filtered_ovlp_" + userID + ".lab");
               
                BufferedWriter out = new BufferedWriter(fstreamout);
                while ((strLine = br.readLine()) != null) {
                    // Print the content on the console

                    strLine = strLine.trim();
                    if (strLine.startsWith("#") || strLine.startsWith("\"") || strLine.startsWith(".")) {
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
                            
                            
                            if(keyword.equalsIgnoreCase("sil"))
                            {
                                continue;
                            }
							

                            /* --lab file format--
                            -------------------------------------------------
                             v1			v2	    v3(keyword)		 v4(score)	
                            -------------------------------------------------
                            5600000(l1) 	11700000(u1) 	shehadyour 	-19.67213
                            11700000(l2) 	17900000(u2) 	darksuit 	-33.32602
                            23800000 	30300000 	washwater 	-56.54476
                            32600000 	38400000 	allyear 	-38.02063
                            */
								
                            l2 = v1.doubleValue();// current rows lower limit
                            u2 = v2.doubleValue(); // current rows upper limit
                            l1 = prev_v1.doubleValue(); // previous rows lower limit
                            u1 = prev_v2.doubleValue(); //previous rows upper limit
                            s2 = v4;// current rows score
                            s1 = prev_score; //previous rows score

                            double diff_l,diff_u, diff;

                            if(l2 < u1) // there is some overlapping
                            {
                                diff_u = u2 - u1; // difference of upper limits of current & prev rows
                                diff_l = l2 - l1; // difference of lower limits of current & prev rows
                                diff = u1 - l1;   // difference of time interval of prev keyword
                                diff*= 0.7;
                                if(diff_u - diff_l >= diff)
                                {
                                    if(v4 < prev_score) // if prev score > current score, eliminate the row
                                    {continue;}
                                }    

                            }
                            
                        }
                        
                       //out.write(keyStartTime + " " + keyEndTime + " " + keyword + " " + keywordScore);
                        out.write(keyStartTime + " " + keyEndTime + " " + keyword + " " + keywordScore);
                        out.newLine();
                        prev_v1=v1;
                        prev_v2=v2;
                        prev_score=v4;
                        System.out.println(keyStartTime + " " + keyEndTime + " " + keyword + " " + keywordScore);
                    }
                }
                System.out.println("4. Overlapping filtering done !");
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadLabNormalize.class.getName()).log(Level.SEVERE, null, ex);
            }

            in.close();
            
            
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
   
        
        // sort the output lab file by start time
        public void sortFile() throws IOException, InterruptedException {
        String cmd1 = "cat kwspotter/misc/score_norm_sorted_filtered_ovlp_" + userID + ".lab | sort -n > kwspotter/misc/final_" + userID + ".lab";
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
            System.out.println("5. Final output lab file generation done !");
            shellIn.close();
            phoneRec("./kwspotter/scripts/normalize.sh  kwspotter/misc/final_" + userID + ".lab");

        } catch (IOException ignoreMe) {
        }

    }

    //-- end output manipulation --

    public ArrayList readResultFile() {


        ArrayList fileData = null;
        try {
            fileData = new ArrayList();
            // Open the file that is the first
            // command line parameter

            /*ReadLabNormalize readNorm = new ReadLabNormalize(userID);    // commented by lok       
              readNorm.sortFile();
              readNorm.readSortedFile();*/
            
            // modified on 21-09-2013 by lok | score manipulation/normalization and sorting by score and time interval
            
            //Following functions are added ..
            
            // 1. Normalize and filter by keyword length the output file produced by phoneRec 
            normalizeScore(); //# INPUT -> temp_<userid>.lab , OUTPUT -> score_norm_<userid>.lab
            
            // 2. Sort the normalized output file by score
            sortNormalizedFile();// # INPUT -> score_norm_<userid>.lab , OUTPUT -> score_norm_sorted_<userid>.lab 
            
            // 3. filter the sorted normalize file by score, based on some threshold 
            FilterSortedFile();//# INPUT -> score_norm_sorted_<userid>.lab , OUTPUT -> score_norm_sorted_filtered_<userid>.lab
            
            // 4. Filter overlapping keywords
            filterOverlappingKeywords(); 
            
            // 5. Sort by time interval
            sortFile();//  # INPUT -> score_norm_sorted_filtered_ovlp_<userid>.lab , OUTPUT -> final_<userid>.lab
           
            //---
            
            // finally the output from final_<userid>.lab will be read and displayed in the front end/GUI
            //FileInputStream fstream = new FileInputStream("kwspotter/misc/final_" + userID + ".lab");
             FileInputStream fstream = new FileInputStream("kwspotter/misc/out.lab");
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
        teluguKwsList = new HashMap();
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
                        teluguKwsList.put(word, phoneme);
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

        //Kws_Telugu_phone kwsTelugu = new Kws_Telugu_phone(null);
       // kwsEn.loadTotalKeywords("E:/telugukeywordList.txt");

    }
}
