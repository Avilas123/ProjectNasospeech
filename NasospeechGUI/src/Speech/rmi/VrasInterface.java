/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Tatapower SED
 *
 */
public interface VrasInterface extends Remote {

    public boolean setRemoteVras(String userName, FilePacket filePacket) throws RemoteException;

    public boolean setRemoteVras(String userName) throws RemoteException;

    public ArrayList processPDSProcess(String processName, ArrayList annotation, String userName, String threshold) throws RemoteException;

    public FilePacket getPDSProcessedAudio(String processName, String userName) throws RemoteException;

    public ArrayList processKeyWordSpotting(String language, ArrayList listofKeyword, String userName) throws RemoteException;

    public ArrayList processPhoneRecoganize(String language, String userName) throws RemoteException;
    
    
    public ArrayList processMFCC(String language, String userName) throws RemoteException;

    public FilePacket getSIDFile(String fileName) throws RemoteException;

    public ArrayList processSIDTesting(FilePacket waveFile, ArrayList groupName, String threshold, String userName) throws RemoteException;

    public String processSIDTraining(FilePacket waveFile, String userName, String trainfileName) throws RemoteException;

    public boolean processSIDTestFileChange(String fileFrom, String fileTo) throws RemoteException;

    public boolean fileSentToRemoteVras(String fileLocation, String fileName, FilePacket filePacket) throws RemoteException;

    public FilePacket getfileFromServer(String fileName) throws RemoteException;

    public String processSIDTestingDU(FilePacket filePacket, String groupName, String threshold, String userID) throws RemoteException;

    public String processPDSProcessDU(Object object, String tata, String st) throws RemoteException;

    public String processPhoneRecoganizeDU(String userID) throws RemoteException;

    public DefaultMutableTreeNode getFileStructure(String useage) throws RemoteException;

    public String deleteServerFile(String filelocation) throws RemoteException;

    public boolean moveServerFile(String source, String destination) throws RemoteException;

    public boolean sidVerfication(String userName, String deslocation, String fileName) throws RemoteException;

    public boolean sidReTrainFiles(ArrayList fileList, String userName) throws RemoteException;

    public boolean deleteSidFilesFromServer(String path) throws RemoteException;
    public boolean updateDictionary(String language, String keyword, String transcription) throws RemoteException;
    public String generateIndianEnglishDic(String keyword) throws RemoteException;
}
