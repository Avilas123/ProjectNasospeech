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
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Tatapower SED
 *
 */
public class VrasiClientSID {

    private static VrasInterface stub = null;
    private String userID;

    public VrasiClientSID() {
    }

    private boolean setConection() {
        try {

            ServerConnectionProperties sconn = new ServerConnectionProperties();
            if (!sconn.getServerProperties()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Server configuration error", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return false;
            }
            try {
                InetAddress address = InetAddress.getByName(sconn.getSidServer());
                if (!address.isReachable(10000)) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Unable to lookup " + sconn.getSidServer(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (UnknownHostException e) {
                javax.swing.JOptionPane.showMessageDialog(null, "Unable to lookup " + sconn.getSidServer(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return false;
            } catch (IOException e) {

                javax.swing.JOptionPane.showMessageDialog(null, "Unable to reach " + sconn.getSidServer(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return false;

            }

            //Registry reg = LocateRegistry.getRegistry("localhost");
            Registry reg = LocateRegistry.getRegistry(sconn.getSidServer());
            stub = (VrasInterface) reg.lookup("rmivras");
            return true;
        } catch (RemoteException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "SID Server could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (NotBoundException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "SID Server could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            System.err.println(e.toString());
            javax.swing.JOptionPane.showMessageDialog(null, "SID Server could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
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
                InetAddress address = InetAddress.getByName(sconn.getSidServer());
                if (!address.isReachable(10000)) {
                    return false;
                }
            } catch (UnknownHostException e) {
                return false;
            } catch (IOException e) {
                return false;

            }
            //Registry reg = LocateRegistry.getRegistry("localhost");
            Registry reg = LocateRegistry.getRegistry(sconn.getSidServer());
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

            VrasiClientSID vClient = new VrasiClientSID();
            boolean test = vClient.setRemoteConnection("tata", "tata.wav");
            System.err.println(test);
        } catch (Exception ex) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean sentToRemote(String fileLocation, String filepath, String fileName) {
        if (!setConection()) {
            return false;
        }
        File file = new File(filepath);
        if (!file.exists()) {
            return false;
        }

        FilePacket filePacket = new FilePacket(filepath);
        filePacket.readIn();



        try {
            if (stub.fileSentToRemoteVras(fileLocation, fileName, filePacket)) {
                return true;
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Server permission denied for creating directory");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean getSIDWavefile(String waveFile, String group) {
        try {

            FilePacket filePacket = stub.getSIDFile(group + "/" + waveFile);
            //System.out.println("Path: "+group + "/" + waveFile);
            if (filePacket == null) {
                javax.swing.JOptionPane.showMessageDialog(null, "PDS Server could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return false;
            }
            filePacket.writeTo(new FileOutputStream("conf/workspace/" + waveFile));
            return true;

        } catch (Exception ex) {
            Logger.getLogger(VrasImplimentation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public ArrayList processSIDTest(String waveFile, ArrayList groupName, String threshold) {
        try {

            File file = new File(waveFile);
            if (!file.exists()) {
                return null;
            }

            FilePacket filePacket = new FilePacket(waveFile);
            filePacket.readIn();

            return stub.processSIDTesting(filePacket, groupName, threshold, this.userID);
        } catch (Exception er) {
            System.err.println(er);
        }
        return null;
    }

    public String processSIDTestDU(String waveFile, String groupName, String threshold) {
        try {

            File file = new File(waveFile);
            if (!file.exists()) {
                return "UNKNOWN";
            }

            FilePacket filePacket = new FilePacket(waveFile);
            filePacket.readIn();

            return stub.processSIDTestingDU(filePacket, groupName, threshold, this.userID);
        } catch (Exception er) {
            System.err.println(er);
        }
        return null;
    }

    public String processSIDTraining(String waveFile, String userName, String trainfile) {
        try {

            File file = new File(waveFile);
            if (!file.exists()) {
                return "UNKNOWN";
            }

            FilePacket filePacket = new FilePacket(waveFile);
            filePacket.readIn();

            return stub.processSIDTraining(filePacket, userName, trainfile);
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

    public List processPhoneReg(String language) {
        ArrayList result = null;
        try {
            result = stub.processPhoneRecoganize(language, this.userID);

        } catch (Exception er) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, er);
        }
        return (List) result;
    }

    public List processKwsReg(String language, ArrayList selectedKeyword) {

        ArrayList result = null;
        try {
            result = stub.processKeyWordSpotting(language, selectedKeyword, this.userID);

        } catch (Exception er) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, er);
        }
        return (List) result;
    }

    public DefaultMutableTreeNode getFileStructure() {
        DefaultMutableTreeNode fileStruct = null;
        try {
            fileStruct = stub.getFileStructure("filebrowser");
        } catch (RemoteException ex) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileStruct;
    }

    public DefaultMutableTreeNode getSidFileStructure() {
        DefaultMutableTreeNode fileStruct = null;
        try {
            fileStruct = stub.getFileStructure("sidfiles");
        } catch (RemoteException ex) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileStruct;
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
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, er);
        }
        return (List) result;
    }

    public String deleteServerFile(String location) {
        if (!setConection()) {
            return null;
        }

        try {

            return stub.deleteServerFile(location);

        } catch (RemoteException ex) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean moveServerFile(String source, String destination) {
        if (!setConection()) {
            return false;
        }

        try {

            return stub.moveServerFile(source, destination);

        } catch (RemoteException ex) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean speakerIDVerfication(String desLocation, String filName) {
        try {

            return stub.sidVerfication(this.userID, desLocation, filName);


        } catch (Exception ex) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean speakerReTrain(ArrayList fileList, String userDir) {
        try {
            return stub.sidReTrainFiles(fileList, userDir);

        } catch (Exception ex) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean deleteSidServerFile(String filepath) {

        try {
            return stub.deleteSidFilesFromServer(filepath);

        } catch (Exception ex) {
            Logger.getLogger(VrasiClientSID.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }
}
