/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author Lok Bahadur Chetri<lok.b.chetri@gmail.com>
 * Modified by Laishram Rahul
 * 
 */
public class updateDictionary {
    private String userID;   // loging userid
    private String rootPath; // home/sid/remote/UserSessionDirectory

    public updateDictionary(String userID, String rootPath) {
        this.userID = userID;
        this.rootPath = rootPath;
    }

    public boolean runCmd(String language, String keyword, String transcription) {
        try{
            language=language.toLowerCase();
            if(language.equals("hindi")){                  //Since Hindi script(devanagari) cannot be recognized by HTK the transcription and the keyword are updated in the dictionary as same
                //so that HTK can generate the gramare and wordnet later using the dictionary and used for keyword spotting.
                String temp=keyword;
                keyword=transcription.replaceAll("[^a-zA-Z\\s]", "");
                phoneRec("./kwspotter/scripts/hindiEng.sh " + language + " " + temp + " "+keyword);
            }
            System.out.println("LANGUAGE_UD: "+language);
            phoneRec("./kwspotter/scripts/updateDictionary.sh " + language + " " + keyword + " "+transcription);
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


    
}
