/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.filebrowser.FileTransfered;
import Speech.staticobjects.DisplayObjects;
import java.util.ArrayList;
import javax.swing.JDesktopPane;

/**
 *
 * @author Tatapower SED
 *
 */
public class ClientToServerfile {

    private MainFrame mFrame;

    public ClientToServerfile(MainFrame mFrame) {
        this.mFrame = mFrame;
    }

    public void createFrame(ArrayList fileList) {
        try {


            JDesktopPane newPane = new JDesktopPane();
            if (DisplayObjects.processStatus()) {
                return;
            }
            //Sound Kws Main Frame
            SubFunctionInternalFrame fileIF = new SubFunctionInternalFrame(false, true, false);
            FileTransfered fileBrowser = new FileTransfered(fileList, fileIF, mFrame);
            //fileIF.setTitle("File Browser");
            fileIF.setVisible(true);
            fileIF.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn2.png")));
            mFrame.screenproperty.fileTransfer(fileIF);
            fileIF.add(newPane);
            newPane.add(fileBrowser);
            mFrame.jDesktopPane1.add(fileIF);
            fileIF.setVisible(true);
            DisplayObjects.setIsFileBrowser(true);
            DisplayObjects.setFileBrowser(fileIF);
            mFrame.screenproperty.resizeScreen(mFrame);
            try {
                fileIF.setSelected(true);
            } catch (final java.beans.PropertyVetoException e) {
            }
        } catch (Exception er) {
            System.out.println(er);
        }
    }
}
