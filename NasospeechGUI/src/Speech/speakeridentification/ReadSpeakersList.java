/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.speakeridentification;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tatapower SED
 *
 */
public class ReadSpeakersList {

    public List readSpeakers(String fileName) {
        List fileData = new ArrayList();
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

                if (strLine.startsWith("#") || strLine.startsWith("\"") || strLine.startsWith(".") || strLine.startsWith(",")) {
                    continue;
                }

                //  System.out.println(strLine);

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
