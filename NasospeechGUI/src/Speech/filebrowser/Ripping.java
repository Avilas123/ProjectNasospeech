/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.filebrowser;

import Speech.common.GetMessage;
import Speech.gui.MainFrame;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Tatapower SED
 *
 */
public class Ripping {

    private MainFrame mframe;
    private DoRiPP_Process rippPro;

    public Ripping(MainFrame mframe) {
        this.mframe = mframe;
        GetMessage msg = new GetMessage();
        msg.setMessage("Ripping under process..");
        rippPro = new DoRiPP_Process();
    }

    public void startRipp() {
        rippPro.start();
    }

    public void stopRipp() {
        rippPro.stop();
    }

    private void deleteDir(File file) {
        try {
            if (file.isDirectory()) {

                //directory is empty, then delete it
                if (file.list().length == 0) {

                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());

                } else {

                    //list all the directory contents
                    String files[] = file.list();

                    for (String temp : files) {
                        //construct the file structure
                        File fileDelete = new File(file, temp);

                        //recursive delete
                        deleteDir(fileDelete);
                    }

                    //check the directory again, if empty then delete it
                    if (file.list().length == 0) {
                        file.delete();
                        System.out.println("Directory is deleted : "
                                + file.getAbsolutePath());
                    }
                }

            } else {
                //if file, then delete it
                file.delete();
                System.out.println("File is deleted : " + file.getAbsolutePath());
            }
        } catch (Exception er) {
            System.err.println(er);
        }
    }

    public class DoRiPP_Process implements Runnable {

        private Thread thread = null;

        public void start() {
            thread = new Thread(this);
            thread.setName("uicontrols");
            thread.start();
        }

        public void stop() {
            if (thread != null) {
                thread.interrupt();
            }
            thread = null;
        }

        public void run() {

            try {

                Runtime rt = Runtime.getRuntime();
                File outFile = new File("conf/ripped");
                System.out.println("hi");
                if (!outFile.exists()) {
                    outFile.mkdir();
                } else {
                    deleteDir(outFile);
                    outFile.mkdir();
                }
                String s = ("ripping/Ripp.exe " + outFile.getAbsolutePath() + " 44100 16 2");
                System.out.println(s);
                Process p = rt.exec(s);
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                ArrayList rippedFiles = new FileEnumerator().getListOfRippedFiles(outFile.getAbsolutePath());
                if (rippedFiles != null) {
                    if (rippedFiles.size() > 0) {
                        mframe.createFileBrowser(rippedFiles);
                    }
                }

            } catch (Exception er) {
                System.err.println(er);
            }
        }
    }
}
