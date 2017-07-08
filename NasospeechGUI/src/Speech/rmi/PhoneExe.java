package Speech.rmi;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 *
 * @author Tatapower SED
 *
 */
public class PhoneExe {

    public PhoneExe(String wavfile, String language) {
        try {
            String path;
            if (language == null) {
                return;
            }
            if (language.length() < 1) {
                return;
            }
            path = language + "/";
            File outPut = new File(path + "recout.mlf");    //transcription file
            if (outPut.exists()) {
                outPut.delete();
            }

            Runtime rt = Runtime.getRuntime();
            Runtime rt1 = Runtime.getRuntime();
            String s0 = ("HTK/HCopy.exe -C " + path + "analysis.conf " + wavfile + " " + path + "phoneme.mfcc");
            System.out.println(s0);
            rt.exec(s0);
            String testModel = "HTK/HVite.exe -T 1 -H " + path + "hmmdefs -C " + path + "analysis_train.conf -i " + path + "recout.mlf -w " + path + "wdnet.txt " + path + "dict.txt " + path + "hmmlist.txt " + path + "phoneme.mfcc";
            Process p = rt1.exec(testModel);  //run window's command from java
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                //System.out.println(line);
            }

        } catch (Throwable t) {
            System.out.print(t.getMessage());
        }
    }
}
