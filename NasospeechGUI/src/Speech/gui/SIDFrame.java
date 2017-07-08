/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.speakeridentification.Ivector;
import Speech.speakeridentification.SIDFileUpdate;
import Speech.speakeridentification.SIDTrain;
import Speech.speakeridentification.SIDVerfication;
import Speech.speakeridentification.SidTesting;

/**
 *
 * @author Tatapower SED
 *
 */
public class SIDFrame {

    private MainFrame mainFrame;

    public SIDFrame(MainFrame mframe) {
        this.mainFrame = mframe;

    }

    public void createSIDTrainFrame(String fileName, Ivector ivector) {



        SubFunctionInternalFrame sidFrame = new SubFunctionInternalFrame(false, true, false);
        sidFrame.setTitle("SID Train");
        sidFrame.setVisible(true);
        sidFrame.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
        mainFrame.screenproperty.addSIDTrainFrame(sidFrame);
        sidFrame.add(new SIDTrain(mainFrame, fileName, sidFrame, ivector));
        mainFrame.jDesktopPane1.add(sidFrame);

        mainFrame.screenproperty.resizeScreen(mainFrame);
        try {
            sidFrame.setSelected(true);
        } catch (final java.beans.PropertyVetoException e) {
        }

    }

    public void createSIDTestFrame(String fileName, Ivector ivector) {


        SubFunctionInternalFrame sidFrame = new SubFunctionInternalFrame(false, true, false);
        sidFrame.setTitle("SID Test");
        sidFrame.setVisible(true);        
        sidFrame.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
        mainFrame.screenproperty.addSIDTestFrame(sidFrame);
        // sidFrame.add(new SidTesting());
        sidFrame.add(new SidTesting(mainFrame, fileName, sidFrame, ivector));
        mainFrame.jDesktopPane1.add(sidFrame);

        mainFrame.screenproperty.resizeScreen(mainFrame);
        try {
            sidFrame.setSelected(true);
        } catch (final java.beans.PropertyVetoException e) {
        }

    }

    public void createSIDVerficationFrame(String userDir, String fileName, Ivector ivector) {


        SubFunctionInternalFrame sidFrame = new SubFunctionInternalFrame(false, true, false);
        sidFrame.setTitle("SID Verification");
        sidFrame.setVisible(true);      
        sidFrame.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
        mainFrame.screenproperty.addSIDVerificationFrame(sidFrame);
        sidFrame.add(new SIDVerfication(mainFrame, userDir, fileName, sidFrame, ivector));
        mainFrame.jDesktopPane1.add(sidFrame);

        mainFrame.screenproperty.resizeScreen(mainFrame);
        try {
            sidFrame.setSelected(true);
        } catch (final java.beans.PropertyVetoException e) {
        }

    }

    public void createSIDFileUpdateFrame(String userDir, String fileName) {


        SubFunctionInternalFrame sidFrame = new SubFunctionInternalFrame(false, true, false);
        sidFrame.setTitle("SID Verification");
        sidFrame.setVisible(true);        
        sidFrame.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
        mainFrame.screenproperty.addSIDVerificationFrame(sidFrame);
        sidFrame.add(new SIDFileUpdate(mainFrame, userDir, fileName, sidFrame));
        mainFrame.jDesktopPane1.add(sidFrame);

        mainFrame.screenproperty.resizeScreen(mainFrame);
        try {
            sidFrame.setSelected(true);
        } catch (final java.beans.PropertyVetoException e) {
        }

    }
}
