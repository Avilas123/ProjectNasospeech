/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tatapower SED
 *
 */
public class SidFileCopy_MultiThread {

    public SidFileCopy_MultiThread() {
    }

    public boolean copyAll(ArrayList<String> fileList, String userName) {
        BufferedWriter br = null;
        try {
            if (fileList == null) {
                return false;
            }

            CommonFunction cfun = new CommonFunction();
            File fn = new File("spkrRec/ivect/sidTest/fn");
            if (fn.exists() && fn.isDirectory()) {
                cfun.deleteDir(fn);
                fn.mkdir();
            } else {
                fn.mkdir();
            }


            File misc = new File("spkrRec/misc");

            if (!misc.exists()) {
                return false;
            }

            if (fileList.size() < 50) {


                File refList = new File(misc.getAbsolutePath() + "/refList_" + userName);
                br = new BufferedWriter(new FileWriter(refList));
                for (String fname : fileList) {
                    try {
                        File ufileX_Sor = new File("spkrRec/data/sidDatabase/" + fname + "/" + fname + ".x");
                        File ufileFN_Sor = new File("spkrRec/data/sidDatabase/" + fname + "/" + fname + ".fn");
                        File ufileX_Des = new File("spkrRec/ivect/sidTest/fn/" + fname + ".x");
                        File ufileFN_Des = new File("spkrRec/ivect/sidTest/fn/" + fname + ".fn");

                        if (ufileX_Sor.exists() && ufileFN_Sor.exists()) {
                            try {
                                cfun.copyFile(ufileX_Sor, ufileX_Des);
                                cfun.copyFile(ufileFN_Sor, ufileFN_Des);
                                br.write(ufileX_Des.getAbsolutePath());
                                br.newLine();
                            } catch (IOException ex) {
                                Logger.getLogger(SidFileCopy.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } catch (Exception er) {
                        Logger.getLogger(SidFileCopy.class.getName()).log(Level.SEVERE, null, er);
                    }
                }

            } else {

                int arrayLength = fileList.size();
                int numjob = (int) arrayLength / 5;
                int balance = arrayLength % numjob;
                ArrayList<Thread> threadPoll = new ArrayList<>();
                int from = 0, to = numjob;
                for (int i = 1; i < 6; i++) {
                    to = numjob * i;
                    Thread t = new Thread(new doFileCopy(fileList, from, to, misc.getAbsolutePath() + "/refList_" + userName + i));
                    t.start();
                    threadPoll.add(t);
                    from = to;

                }
                if (balance > 0) {
                    Thread t = new Thread(new doFileCopy(fileList, numjob * 5, arrayLength, misc.getAbsolutePath() + "/refList_" + userName + "6"));
                    t.start();
                    threadPoll.add(t);
                }

                for (Thread t : threadPoll) {
                    try {
                        t.join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SidFileCopy.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                File refList = new File(misc.getAbsolutePath() + "/refList_" + userName);
                br = new BufferedWriter(new FileWriter(refList));
                for (int i = 1; i <= threadPoll.size(); i++) {
                    FileReader fr = null;
                    BufferedReader buff = null;
                    try {
                        File gFile = new File(misc.getAbsolutePath() + "/refList_" + userName + i);
                        fr = new FileReader(gFile);
                        buff = new BufferedReader(fr);
                        String strLine;
                        while ((strLine = buff.readLine()) != null) {
                            br.write(strLine);
                            br.newLine();
                        }
                        buff.close();
                        fr.close();
                    } catch (Exception er) {
                    }

                }
                br.close();


            }
            System.out.println("Files are copied");
            return true;
        } catch (FileNotFoundException er) {
            Logger.getLogger(SidFileCopy.class.getName()).log(Level.SEVERE, null, er);
        } catch (Exception er) {
            Logger.getLogger(SidFileCopy.class.getName()).log(Level.SEVERE, null, er);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(SidFileCopy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    class doFileCopy implements Runnable {

        private ArrayList fileList;
        private int from = 0, to = 0;
        private String filename = "";

        public doFileCopy(ArrayList fileList, int from, int to, String filename) {
            this.fileList = fileList;
            this.from = from;
            this.to = to;
            this.filename = filename;
        }

        @Override
        public void run() {
            CommonFunction cfun = new CommonFunction();
            try {
                BufferedWriter br = null;
                br = new BufferedWriter(new FileWriter(new File(filename)));

                for (int i = from; i < to; i++) {
                    if (fileList.get(i) == null) {
                        continue;
                    }

                    String fname = fileList.get(i).toString();
                    try {
                        File ufileX_Sor = new File("spkrRec/data/sidDatabase/" + fname + "/" + fname + ".x");
                        File ufileFN_Sor = new File("spkrRec/data/sidDatabase/" + fname + "/" + fname + ".fn");
                        File ufileX_Des = new File("spkrRec/ivect/sidTest/fn/" + fname + ".x");
                        File ufileFN_Des = new File("spkrRec/ivect/sidTest/fn/" + fname + ".fn");

                        if (ufileX_Sor.exists() && ufileFN_Sor.exists()) {
                            try {
                                cfun.copyFile(ufileX_Sor, ufileX_Des);
                                cfun.copyFile(ufileFN_Sor, ufileFN_Des);
                                br.write(ufileX_Des.getAbsolutePath());
                                br.newLine();
                            } catch (IOException ex) {
                                Logger.getLogger(SidFileCopy.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } catch (Exception er) {
                        Logger.getLogger(SidFileCopy.class.getName()).log(Level.SEVERE, null, er);
                    }
                }
            } catch (Exception er) {
                Logger.getLogger(SidFileCopy.class.getName()).log(Level.SEVERE, null, er);
            }
        }
    }
}
