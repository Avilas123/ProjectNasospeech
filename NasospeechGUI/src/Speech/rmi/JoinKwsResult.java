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
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 *
 * @author Tatapower SED
 *
 */
public class JoinKwsResult {

    public JoinKwsResult() {
    }

    public void joinSplitFiles(String fileName, String filePath, String fileLength, int noSplit) {

        if (fileName == null || fileLength == null) {
            return;
        }



        try {

            File file = new File(filePath + "/" + fileName + "recout.mlf");
            File filePart1 = new File(filePath + "/" + fileName + "_P1.mlf");
            filePart1.renameTo(file);

            //if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter(filePath + "/" + fileName + "recout.mlf", true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

            // Open the file that is the first
            // command line parameter
            for (int i = 2; i <= noSplit; i++) {
                FileInputStream fstream = new FileInputStream(filePath + "/" + fileName + "_P" + i + ".mlf");
                // Get the object of DataInputStream
                BigInteger fileStart = new BigInteger(fileLength);
                fileStart = fileStart.multiply(new BigInteger(Integer.toString(i - 1)));
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

                    if (strLine.length() < 1) {
                        continue;
                    }
                    String keyStart = null, keyEnd = null, keyName = null;
                    StringTokenizer st = new StringTokenizer(strLine, " ");
                    if (st.hasMoreElements()) {
                        keyStart = st.nextElement().toString();
                    }
                    if (st.hasMoreElements()) {
                        keyEnd = st.nextElement().toString();
                    }
                    if (st.hasMoreElements()) {
                        keyName = st.nextElement().toString();
                    }

                    if (keyName != null && keyStart != null && keyEnd != null) {
                        keyName = keyName.trim();
                        keyStart = keyStart.trim();
                        keyEnd = keyEnd.trim();
                        if (keyName.length() > 0 && keyStart.length() > 0 && keyEnd.length() > 0) {
                            BigInteger startTime = new BigInteger(keyStart);
                            startTime = startTime.add(fileStart);
                            BigInteger endTime = new BigInteger(keyEnd);
                            endTime = endTime.add(fileStart);
                            System.out.println(startTime + " " + endTime + " " + keyName);

                            bufferWritter.write(startTime + " " + endTime + " " + keyName);
                            bufferWritter.newLine();
                        }
                    }


                }
                in.close();
            }
            bufferWritter.close();
            //Close the input stream
        } catch (IOException e) {
            e.printStackTrace();

        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

    }

    public static void main(String args[]) {

        new JoinKwsResult().joinSplitFiles("tata", "E:/add", "460000000", 4);



    }
}
