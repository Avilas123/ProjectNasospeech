package Speech.rmi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory;

/**
 *
 * @author Tatapower SED
 *
 */
public class VrasiClientKWS { 

    private static VrasInterface stub = null;
    private String userID;

    public VrasiClientKWS() {
    }

    private boolean setConection() {
        try {

            ServerConnectionProperties sconn = new ServerConnectionProperties();
            if (!sconn.getServerProperties()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Server configuration error", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return false;
            }
            try {
                InetAddress address = InetAddress.getByName(sconn.getKswServer());
                if (!address.isReachable(10000)) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Unable to lookup " + sconn.getKswServer(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (UnknownHostException e) {
                javax.swing.JOptionPane.showMessageDialog(null, "Unable to lookup " + sconn.getKswServer(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return false;
            } catch (IOException e) {

                javax.swing.JOptionPane.showMessageDialog(null, "Unable to reach " + sconn.getKswServer(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return false;

            }
            //Registry reg = LocateRegistry.getRegistry("localhost");
            Registry reg = LocateRegistry.getRegistry(sconn.getKswServer());
            stub = (VrasInterface) reg.lookup("rmivras");
            return true;
        } catch (RemoteException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "KWS Server could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (NotBoundException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "KWS Server could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            System.err.println(e.toString());
            javax.swing.JOptionPane.showMessageDialog(null, "KWS Server could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return false;

    }

    public boolean getConnectionStatus() {
        try {

            ServerConnectionProperties sconn = new ServerConnectionProperties();
            if (!sconn.getServerProperties()) {
                return false;
            }
            try {
                InetAddress address = InetAddress.getByName(sconn.getKswServer());
                if (!address.isReachable(10000)) {
                    return false;
                }
            } catch (UnknownHostException e) {
                return false;
            } catch (IOException e) {
                return false;

            }
            //Registry reg = LocateRegistry.getRegistry("localhost");
            Registry reg = LocateRegistry.getRegistry(sconn.getKswServer());
            stub = (VrasInterface) reg.lookup("rmivras");
            return true;
        } catch (RemoteException e) {
        } catch (NotBoundException e) {
        } catch (Exception e) {
        }
        return false;

    }

    public static void main(String[] args) {

        //FilePacket filePacket = new FilePacket("PDS.wav");
        // filePacket.readIn();


        try {

            VrasiClientKWS vClient = new VrasiClientKWS();
            boolean test = vClient.setRemoteConnection("sdfsd", "phenome.wav");
            System.err.println(test);
        } catch (Exception ex) {
            Logger.getLogger(VrasiClientKWS.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    public boolean setRemoteConnection(String userName, String fileName) {
        if (!setConection()) {
            return false;
        }
        this.userID = userName;
        File file = new File(fileName);
        if (!file.exists()) {
            return false;
        }

        FilePacket filePacket = new FilePacket(fileName);
        filePacket.readIn();

        try {
            if (stub.setRemoteVras(userName, filePacket)) {
                return true;
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Server permission denied for creating directory");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(VrasiClientPDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VrasiClientPDS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean sentToRemote(String fileLocation, String fileName) {
        if (!setConection()) {
            return false;
        }
        File file = new File(fileName);
        if (!file.exists()) {
            return false;
        }

        FilePacket filePacket = new FilePacket(fileName);
        filePacket.readIn();

        try {
            if (stub.fileSentToRemoteVras(fileLocation, file.getName(), filePacket)) {
                return true;
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Server permission denied for creating directory");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(VrasiClientPDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VrasiClientPDS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean getfileFromRemoteServer(String fileLocation, String fileName) {
        if (!setConection()) {
            return false;
        }

        try {

            FilePacket filePacket = stub.getfileFromServer(fileLocation);
            if (filePacket == null) {
                javax.swing.JOptionPane.showMessageDialog(null, "Server could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return false;
            }
            filePacket.writeTo(new FileOutputStream("conf/workspace/" + fileName));

            return true;

        } catch (Exception ex) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean setRemoteConnection(String userName) {
        if (!setConection()) {
            return false;
        }
        this.userID = userName;
        try {
            if (stub.setRemoteVras(userName)) {

                return true;
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Server permission denied for creating directory");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(VrasiClientPDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VrasiClientPDS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getSIDWavefile(String waveFile) {
        try {

            FilePacket filePacket = stub.getSIDFile(waveFile);
            if (filePacket == null) {
                javax.swing.JOptionPane.showMessageDialog(null, "PDS Server could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return null;
            }
            filePacket.writeTo(new FileOutputStream("speakerIdentification/SidTest.wav"));
            return "SidTest.wav";

        } catch (Exception ex) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String processSIDTest(String waveFile, String groupName, String threshold) {
        try {

            File file = new File(waveFile);
            if (!file.exists()) {
                return "UNKNOWN";
            }

            FilePacket filePacket = new FilePacket(waveFile);
            filePacket.readIn();

            // return stub.processSIDTesting(filePacket, groupName, threshold, this.userID);
        } catch (Exception er) {
            System.err.println(er);
        }
        return null;
    }

    public String processSIDTraining(String waveFile, String groupName) {
        try {

            File file = new File(waveFile);
            if (!file.exists()) {
                return "UNKNOWN";
            }

            FilePacket filePacket = new FilePacket(waveFile);
            filePacket.readIn();

            return stub.processSIDTraining(filePacket, groupName, this.userID);
        } catch (Exception er) {
            System.err.println(er);
        }
        return null;
    }

    public boolean processSIDChangeTestFile(String fileFrom, String fileTo) {
        try {
            return stub.processSIDTestFileChange(fileFrom, fileTo);
        } catch (Exception er) {
            System.err.println(er);
        }
        return false;
    }
    public boolean updateDictionary(String language, String keyword, String transcription)
    {
        boolean result=false;
        try {
            result = stub.updateDictionary(language, keyword, transcription);

        } catch (Exception er) {
            Logger.getLogger(VrasiClientPDS.class.getName()).log(Level.SEVERE, null, er);
        }
        return result;
         
    }
    public String generateIndianEnglishDic(String keyword)
    {
        System.out.println("called vras client kws");
        String result1="aa";
        boolean result=false;
        try {
            result1 = stub.generateIndianEnglishDic(keyword);
            
                System.out.println("called vras client kws 2");
        } catch (Exception er) {
            Logger.getLogger(VrasiClientPDS.class.getName()).log(Level.SEVERE, null, er);
        }
        return result1;
         
    }
    public List processPhoneReg(String language) {
    //  System.out.println("LANGUAGE PRINTING fron VCkws: "+language);
        ArrayList result = null;
        try {
            result = stub.processPhoneRecoganize(language, this.userID);

        } catch (Exception er) {
            Logger.getLogger(VrasiClientPDS.class.getName()).log(Level.SEVERE, null, er);
        }
        return (List) result;
    }

    public List processKwsReg(String language, ArrayList selectedKeyword) {

        ArrayList result = null;
        try {
            result = stub.processKeyWordSpotting(language, selectedKeyword, this.userID);
            if (result == null) {
                return null;
            }

        } catch (Exception er) {
            Logger.getLogger(VrasiClientPDS.class.getName()).log(Level.SEVERE, null, er);
        }
        return (List) result;
    }

    public List processPDS(String method, ArrayList annotation, String threshold) {

        ArrayList result = null;
        try {

            if (method.startsWith("Remove silence")) {
                result = stub.processPDSProcess(method, annotation, this.userID, threshold);
                if (result != null) {
                    FilePacket filePacket = stub.getPDSProcessedAudio(method, this.userID);
                    if (filePacket == null) {
                        return result;
                    }
                    filePacket.writeTo(new FileOutputStream("PDS/pds.wav"));
                    return result;
                }
            } else if (method.startsWith("Enhance speech")) {
                result = stub.processPDSProcess(method, annotation, this.userID, threshold);
                FilePacket filePacket = stub.getPDSProcessedAudio(method, this.userID);
                if (filePacket == null) {
                    return result;
                }
                filePacket.writeTo(new FileOutputStream("PDS/pds.wav"));
                return result;

            } else {
                result = stub.processPDSProcess(method, annotation, this.userID, threshold);
                FilePacket filePacket = stub.getPDSProcessedAudio(method, this.userID);
                if (filePacket == null) {
                    return result;
                }
                filePacket.writeTo(new FileOutputStream("PDS/pds.wav"));
                return result;
            }
        } catch (Exception er) {
            Logger.getLogger(VrasiClientPDS.class.getName()).log(Level.SEVERE, null, er);
        }
        return (List) result;
    }

    public String processPhoneRegDU() {
        String result = null;
        try {
            //result = stub.processPhoneRecoganizeDU(this.userID);
        } catch (Exception er) {
            Logger.getLogger(VrasiClientPDS.class.getName()).log(Level.SEVERE, null, er);
        }
        return result;
    }
}
