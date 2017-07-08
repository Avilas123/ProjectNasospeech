/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Tatapower SED
 *
 */
public class VrasImplimentation implements VrasInterface {

    private AudioInputStream audioStream;
    private CreateSession session;
    private String rootDir = "UserSessionDir";

    public VrasImplimentation() throws RemoteException {
        session = new CreateSession();
    }

    public boolean updateDictionary(String language, String keyword, String transcription)
    {    
        updateDictionary updict  = new updateDictionary(language, rootDir);
        return updict.runCmd(language, keyword, transcription);
    }
    public String generateIndianEnglishDic(String keyword)
    {    
        generateIndianEnglishDic getdict  = new generateIndianEnglishDic("english",rootDir);
        return getdict.getTranscription(keyword);
    }

   
    public boolean setRemoteVras(String userName, FilePacket filePacket) throws RemoteException {
        try {

            if (session.setUserSessionDir(userName, rootDir)) {
                filePacket.writeTo(new FileOutputStream(rootDir + "/" + userName + "/" + filePacket.getName()));
                return true;
            }

            return false;

        } catch (Exception ex) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean setRemoteVras(String userName) throws RemoteException {
        try {

            return true;

        } catch (Exception ex) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList processPDSProcess(String processName, ArrayList annotation, String userName, String threshold) throws RemoteException {
        ArrayList resultList = null;
        if (processName.startsWith("Remove silence")) {
            PDS_SilenceRemove pdsSilence = new PDS_SilenceRemove(userName, rootDir);
            boolean result = pdsSilence.commandWithAnnotation(annotation, threshold);
            if (result) {
                resultList = pdsSilence.readResultFile();
            }

        } else if (processName.startsWith("Remove noise")) {
            System.out.println("hello this is correct");
            PDS_NoiseRemove pdsSilence = new PDS_NoiseRemove(userName, rootDir);
            boolean result = pdsSilence.commandWithAnnotation(annotation, threshold);
            if (result) {
                resultList = pdsSilence.readResultFile();
            }

        } else if (processName.startsWith("Enhance speech")) {
            PDS_EnhancedSpeech pdsEnhance = new PDS_EnhancedSpeech(userName, rootDir);
            boolean result = pdsEnhance.commandWithAnnotation(annotation, threshold);


        } else {
            PDS_Combined pdsCombined = new PDS_Combined(userName, rootDir);
            boolean result = pdsCombined.commandWithAnnotation(annotation, threshold);
            if (result) {
                resultList = pdsCombined.readResultFile();
            }
        }
        return resultList;
    }

    @Override
    public FilePacket getPDSProcessedAudio(String processName, String userName) throws RemoteException {
        FilePacket filePacket = null;
        try {
            if (processName.startsWith("Remove silence")) {
                File file = new File(rootDir + "/" + userName + "/SilRem/" + userName + "_silrem.wav");
                if (!file.exists()) {
                    return filePacket;
                }
                filePacket = new FilePacket(rootDir + "/" + userName + "/SilRem/" + userName + "_silrem.wav");
                filePacket.readIn();
            } else if (processName.startsWith("Remove noise")) {
                File file = new File(rootDir + "/" + userName + "/SilRem/" + userName + "_noisilrem.wav");
                if (!file.exists()) {
                    return filePacket;
                }
                filePacket = new FilePacket(rootDir + "/" + userName + "/SilRem/" + userName + "_noisilrem.wav");
                filePacket.readIn();
            } else if (processName.startsWith("Enhance speech")) {
                File file = new File(rootDir + "/" + userName + "/SilRem/" + userName + "_spEnhnc.wav");
                if (!file.exists()) {
                    return filePacket;
                }
                filePacket = new FilePacket(rootDir + "/" + userName + "/SilRem/" + userName + "_spEnhnc.wav");
                filePacket.readIn();
            } else {
                File file = new File(rootDir + "/" + userName + "/SilRem/" + userName + "_spEnhnc.wav");
                if (!file.exists()) {
                    return filePacket;
                }
                filePacket = new FilePacket(rootDir + "/" + userName + "/SilRem/" + userName + "_spEnhnc.wav");
                filePacket.readIn();
            }
        } catch (Exception er) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, er);
        }
        return filePacket;


    }

    @Override
    public ArrayList processKeyWordSpotting(String language, ArrayList listofKeyword, String userName) throws RemoteException {
        ArrayList resultList = null;
        try {

            if (language.endsWith("Assamese_part1")) {

                Kws_Assames_phone kwsHindi = new Kws_Assames_phone(userName, rootDir);
                kwsHindi.runCommandWithList(listofKeyword);
                resultList = kwsHindi.readResultFile();

            } else if (language.endsWith("Assamese_part2")) {
                Kws_Assamese_word kwsAssamese = new Kws_Assamese_word(userName, rootDir);
                kwsAssamese.runCommandWithKeyword(listofKeyword);
                resultList = kwsAssamese.readResultFile();

            } else if (language.endsWith("Hindi_part1")) {

                Kws_Hindi_phone kwsHindi = new Kws_Hindi_phone(userName, rootDir);
                kwsHindi.runCommandWithkeyword(listofKeyword);
                resultList = kwsHindi.readResultFile();

            } else if (language.endsWith("Hindi_part1")) {
                Kws_Hindi_word kwsHindi = new Kws_Hindi_word(userName, rootDir);
                if (listofKeyword == null) {
                    kwsHindi.runCommandWithOutKeyword();
                } else {
                    kwsHindi.runCommandWithKeyword(listofKeyword);
                }
                resultList = kwsHindi.readResultFile();
            } else if (language.endsWith("Telugu")) {
                Kws_Telugu_phone kwsTelugu = new Kws_Telugu_phone(userName);
                kwsTelugu.runCommandWithKeyword(rootDir, listofKeyword);
                resultList = kwsTelugu.readResultFile();

            } else {
                //Kws_English_phone kwsEng = new Kws_English_phone(userName, rootDir);
                Kws_English_phone kwsEng = new Kws_English_phone(userName);
                //kwsEng.runCommandWithList(listofKeyword);
                kwsEng.runCommandWithKeyword(rootDir, listofKeyword);
                resultList = kwsEng.readResultFile();
            }



        } catch (Exception er) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, er);
        }
        return resultList;
    }

    @Override
    public ArrayList processPhoneRecoganize(String language, String userName) throws RemoteException {
        ArrayList resultList = null;
        try {
            if (language.equals("Nasospeech")) {
                PhoneReg_Assames phoneAssames = new PhoneReg_Assames(userName);
                phoneAssames.runCommand(rootDir);
                resultList = phoneAssames.readResultFile();
            } else if (language.equals("Hindi")) {
                PhoneReg_Hindi phoneHindi = new PhoneReg_Hindi(userName);
                phoneHindi.runCommand(rootDir);
                resultList = phoneHindi.readResultFile(userName);
            } else if (language.equals("Telugu")) {
                PhoneReg_Telugu phoneTelugu = new PhoneReg_Telugu(userName);
                phoneTelugu.runCommand(rootDir);
                resultList = phoneTelugu.readResultFile();
            } else {
                PhoneReg_English phoneEnglish = new PhoneReg_English(userName);
                phoneEnglish.runCommand(rootDir);
                // modified by Lok : 9-sep-2013
                phoneEnglish.normalizeScore(); // step 1 - normalize the output file
                phoneEnglish.sortNormalizedFile(); //step 2 - sort the normalized output file by score
                phoneEnglish.FilterSortedFile();   // step 3 - filter the normalized sorted file
                phoneEnglish.sortFile();//step 4 - sort by time frame
                resultList = phoneEnglish.readResultFile();// read the file generated at step 4
            }
        } catch (Exception er) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, er);
        }

        return resultList;
    }
    
    
    ///// for MFCC  /////////////////
    @Override
    public ArrayList processMFCC(String language, String userName) throws RemoteException {
        ArrayList resultList = null;
        try {
            if (language.equals("Nasospeech")) {
                MFCCExtract mfccExtract = new MFCCExtract(userName);
                mfccExtract.runCommand(rootDir);
                resultList = mfccExtract.readResultFile();
            }
        } catch (Exception er) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, er);
        }

        return resultList;
    }
    
    

    @Override
    public FilePacket getSIDFile(String fileName) throws RemoteException {
        FilePacket filePacket = null;
        try {

            File file = new File("spkrRec/data/sidDatabase/" + fileName);
            if (!file.exists()) {
                System.out.println("Here File is not exisit " + "spkrRec/data/sidDatabase/" + fileName);
                return filePacket;
            }
            System.out.println("Here File is Avail " + "spkrRec/data/sidDatabase/" + fileName);
            filePacket = new FilePacket("spkrRec/data/sidDatabase/" + fileName);
            filePacket.readIn();
        } catch (Exception er) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, er);
        }
        return filePacket;
    }

    @Override
    public ArrayList processSIDTesting(FilePacket waveFilepacket, ArrayList groupName, String threshold, String userName) throws RemoteException {
        ArrayList result = null;
        String waveFile = null;
        SIDTest sidTest = new SIDTest(userName);
        try {
            waveFilepacket.writeTo(new FileOutputStream("spkrRec/data/GuiTest/" + waveFilepacket.getName()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, ex);
        }

        File file = new File("spkrRec/data/GuiTest/" + waveFilepacket.getName());
        if (!file.exists()) {
            return null;
        }

        if (!new SidFileCopy().copyAll(groupName, userName)) {
            return null;
        }

        waveFile = file.getAbsolutePath();
        sidTest.runCommand(waveFile, "sidTest", threshold);
        result = sidTest.readResultFile();
        return result;
    }

    @Override
    public String processSIDTraining(FilePacket waveFilepacket, String userName, String trainFileName) {
        try {
            String waveFile = null;
            SIDTraining sidTrain = new SIDTraining(userName);
            try {
                File userDir = new File("spkrRec/data/sidDatabase/" + userName);
                if (!userDir.mkdir()) {
                    return null;
                }
                File featDir = new File("spkrRec/feat/sidDatabase/" + userName);
                if (!featDir.mkdir()) {
                    return null;
                }
                waveFilepacket.writeTo(new FileOutputStream("spkrRec/data/sidDatabase/" + userName + "/" + trainFileName));

                waveFilepacket = null;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, ex);
            }

            File file = new File("spkrRec/data/sidDatabase/" + userName + "/" + trainFileName);
            if (!file.exists()) {
                return null;
            }
            waveFile = file.getAbsolutePath();
            sidTrain.runCommand(waveFile, "sidTest");
            return "working";
        } catch (Exception er) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, er);
        }
        return null;
    }

    @Override
    public boolean processSIDTestFileChange(String fileFrom, String fileTo) throws RemoteException {
        try {
            File fileSource = new File("spkrRec/data/GuiTest/" + fileFrom);
            if (!fileSource.exists()) {
                return false;
            }
            File fileTarget = new File("spkrRec/data/Demo/test/" + fileTo);
            CommonFunction cfun = new CommonFunction();
            cfun.copyFile(fileSource, fileTarget);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean fileSentToRemoteVras(String fileLocation, String fileName, FilePacket filePacket) {
        try {
            File locDir = new File("SERVER/VRAS/" + fileLocation);
            if (!locDir.exists()) {
                locDir.mkdir();
            }

            filePacket.writeTo(new FileOutputStream("SERVER/VRAS/" + fileLocation + "/" + fileName));
            System.out.println("SERVER/" + fileLocation + "/VRAS/" + fileName);
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception er) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, er);
        }
        return false;
    }

    @Override
    public FilePacket getfileFromServer(String fileName) throws RemoteException {
        FilePacket filePacket = null;
        try {

            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File is not found : " + fileName);
                return filePacket;
            }

            filePacket = new FilePacket(fileName);
            filePacket.readIn();
        } catch (Exception er) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, er);
        }
        return filePacket;
    }

    @Override
    public String processSIDTestingDU(FilePacket waveFilepacket, String groupName, String threshold, String userName) throws RemoteException {
        String result = null, waveFile = null;
        SIDTest sidTest = new SIDTest(userName);
        try {
            waveFilepacket.writeTo(new FileOutputStream("spkrRec/data/GuiTest/" + waveFilepacket.getName()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, ex);
        }

        File file = new File("spkrRec/data/GuiTest/" + waveFilepacket.getName());
        if (!file.exists()) {
            return null;
        }
        waveFile = file.getAbsolutePath();
        sidTest.runCommandSIDDU();
        result = sidTest.readResultFileSID("sid");
        System.out.println(result);
        return result;
    }

    @Override
    public String processPDSProcessDU(Object object, String tata, String st) throws RemoteException {
        boolean result;
//        if (processName.startsWith("Remove silence")) {
        PDS_SilenceRemove pdsSilence = new PDS_SilenceRemove("tata", rootDir);
        result = pdsSilence.commandWithAnnotationDU(null, "1.1");
//            if (result) {
//                resultList = pdsSilence.readResultFile();
//            }
        System.out.println("res " + result);
//        } else if (processName.startsWith("Enhance speech")) {
//            PDS_EnhancedSpeech pdsEnhance = new PDS_EnhancedSpeech(userName, rootDir);
//            boolean result = pdsEnhance.commandWithAnnotation(annotation, threshold);
//
//
//        } else {
//            PDS_Combined pdsCombined = new PDS_Combined(userName, rootDir);
//            boolean result = pdsCombined.commandWithAnnotation(annotation, threshold);
//            if (result) {
//                resultList = pdsCombined.readResultFile();
//            }
//        }
        if (result) {
            return "true";
        } else {
            return "false";
        }
    }

    @Override
    public String processPhoneRecoganizeDU(String userName) {
        String resultList = null;
        try {
            PhoneReg_Assames phoneAssames = new PhoneReg_Assames(userName);
//                phoneAssames.runCommandDU(rootDir);
//                resultList = phoneAssames.readResultFileDU();
        } catch (Exception er) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, er);
        }

        return null;
    }

    /*  public boolean ClearWorkSpace() {
     try {
     File workspace = new File("SERVER/WorkSpace");
     new CommonFunction().deleteDir(workspace);
     workspace.mkdir();
     } catch (Exception er) {
     Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, er);
     }
     return true;
     }*/
    public DefaultMutableTreeNode getFileStructure(String useage) {
        if (useage.toLowerCase().startsWith("filebrowser")) {
            return FileTree.addNodes(null, new File("/home/sid/remote/SERVER/"));
        } else {
            return FileTree.addNodes(null, new File("/home/sid/remote/spkrRec/data/sidDatabase/"));
        }

    }

    /*  public boolean copyWorkSpaceToLocation(String location, String fileName) {
     try {
     File source = new File("SERVER/WorkSpace/" + fileName);
     if (source.exists()) {
     new CommonFunction().copyFile(source, new File("SERVER/VRAS/" + location + "/" + fileName));
     }
     } catch (Exception er) {
     Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, er);
     }
     return true;
     }*/
    public boolean moveServerFile(String source, String destination) {

        return new CopyServerFiles().copy(source, destination);
    }

    public String deleteServerFile(String filelocation) {

        return new DeleteServerFiles().delete(filelocation);
    }

    public boolean sidVerfication(String userName, String deslocation, String fileName) {
        File sourceFile = new File("spkrRec/data/GuiTest/" + userName + ".wav");
        if (!sourceFile.exists()) {
            System.out.println("Fail 1");
            return false;
        }

        File desDir = new File("spkrRec/data/sidDatabase/" + deslocation);
        if (!desDir.exists()) {
            System.out.println("Fail 2 " + "spkrRec/data/sidDatabase/" + deslocation);
            return false;
        }
        File desFile = new File("spkrRec/data/sidDatabase/" + deslocation + "/" + fileName);

        try {
            new CommonFunction().copyFile(sourceFile, desFile);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean sidReTrainFiles(ArrayList fileList, String userName) {
        try {

            SIDTraining sidTrain = new SIDTraining(userName);
            File userDir = new File("spkrRec/data/sidDatabase/" + userName);
            if (!userDir.exists()) {
                System.out.println("No file at " + "spkrRec/data/sidDatabase/" + userName);
                return false;
            }

            if (!new JoinAudioFile().doJoin(fileList, userDir.getAbsolutePath(), userName)) {
                return false;
            }


            File file = new File("spkrRec/data/sidDatabase/" + userName + "/" + userName + ".wav");
            if (!file.exists()) {
                System.out.println("Finally no file " + "spkrRec/data/sidDatabase/" + userName + "/" + userName + ".wav");
                return false;
            }
            sidTrain.runCommand(file.getAbsolutePath(), "sidTest");
            try {
                file.delete();
            } catch (Exception er) {
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean deleteSidFilesFromServer(String path) {
        try {
            File sidPath = new File("spkrRec/data/sidDatabase/" + path);
            if (!sidPath.exists()) {
                return false;
            }

            if (sidPath.isFile()) {
                return sidPath.delete();
            } else {
                new CommonFunction().deleteDir(sidPath);
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }
}
