/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.addLangugeKeywords.AddLangKeyword;
import Speech.phoneme.AddKeyword;
import Speech.staticobjects.DisplayObjects;
import java.awt.Dimension;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JDesktopPane;

/**
 *
 * @author Lok Chetri 
 *
 */
public class AddKeywordFrame {

    private MainFrame mFrame;
    private AudioInputStream audioInputStream;

    public AddKeywordFrame(MainFrame mFrame) {
        this.mFrame = mFrame;
    }

    public void createFrame(String lang, String panelName, int startPos, int endPos, String filename, AudioInputStream audioStream) {
        try {

          
            if (DisplayObjects.processStatus()) {
                //return;
           }
            JDesktopPane newPane = new JDesktopPane();
            AddKeyword addkw = new AddKeyword(lang, mFrame,startPos, endPos, filename, audioStream);
            //Sound Kws Main Frame
            SubFunctionInternalFrame annotateIF = new SubFunctionInternalFrame(false, true, false);
            annotateIF.setTitle(panelName);
            annotateIF.setVisible(true);
            annotateIF.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
            mFrame.screenproperty.addKwPannel(annotateIF);
            
            annotateIF.add(newPane);
            newPane.add(addkw);
            mFrame.jDesktopPane1.add(annotateIF);
            annotateIF.setVisible(true);
            DisplayObjects.setisAddKeyword(true);
            DisplayObjects.setKwsSelectedObj(annotateIF);
            //System.out.println("Hello from Lok");
            mFrame.screenproperty.resizeScreen(mFrame);
            try {
                annotateIF.setSelected(true);
            } catch (final java.beans.PropertyVetoException e) {
            }
        } catch (Exception er) {
            System.out.println(er);
        }
    }
    
    public void createFrame(String lang, String panelName)
    {
      try {

          
            if (DisplayObjects.processStatus()) {
                //return;
           }
            JDesktopPane newPane = new JDesktopPane();
           // AddKeyword addkw = new AddKeyword(lang, mFrame);
             
            AddLangKeyword addkw = new AddLangKeyword(lang, mFrame);
            //Sound Kws Main Frame
            SubFunctionInternalFrame annotateIF = new SubFunctionInternalFrame(false, true, false);
            annotateIF.setTitle(panelName);
            annotateIF.setVisible(true);
            annotateIF.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
            mFrame.screenproperty.addKwPannel(annotateIF);
            annotateIF.add(newPane);
           newPane.add(addkw);
            mFrame.jDesktopPane1.add(annotateIF);
            annotateIF.setVisible(true);
            DisplayObjects.setisAddKeyword(true);
            DisplayObjects.setKwsSelectedObj(annotateIF);
            //System.out.println("Hello from Lok");
            mFrame.screenproperty.resizeScreen(mFrame);
            try {
                annotateIF.setSelected(true);
            } catch (final java.beans.PropertyVetoException e) {
            }
        } catch (Exception er) {
            System.out.println(er);
        }
    
    }
    
}
