/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.annotations.Annotation;
import Speech.staticobjects.DisplayObjects;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JDesktopPane;

/**
 *
 * @author Tatapower SED
 *
 */
public class AnnotationFrame {

    private MainFrame mFrame;
    private AudioInputStream audioInputStream;

    public AnnotationFrame(MainFrame mFrame) {
        this.mFrame = mFrame;
    }

    public void createFrame(String panelName, int startPos, int endPos, String filename, Object pwCommon) {
        try {

            if (DisplayObjects.processStatus()) {
                return;
            }
            JDesktopPane newPane = new JDesktopPane();
            Annotation annotate = new Annotation(mFrame, startPos, endPos, filename, pwCommon);
            //Sound Kws Main Frame
            SubFunctionInternalFrame annotateIF = new SubFunctionInternalFrame(false, true, false);
            annotateIF.setTitle(panelName);
            annotateIF.setVisible(true);
            annotateIF.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
            mFrame.screenproperty.annotationPannel(annotateIF);
            annotateIF.add(newPane);
            newPane.add(annotate);
            mFrame.jDesktopPane1.add(annotateIF);
            annotateIF.setVisible(true);
            DisplayObjects.setIsAnnotation(true);
            DisplayObjects.setAnnotationObj(annotateIF);

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
