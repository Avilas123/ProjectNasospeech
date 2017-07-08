/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.pds.PlotWavePDS;
import Speech.staticobjects.DisplayObjects;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author Tatapower SED
 *
 */
public class SubFunctionInternalFrame extends JInternalFrame implements InternalFrameListener {

    static final int xPosition = 30, yPosition = 30;
    private MainFrame mainframe;
    private PlotWavePDS pds = null;

    public SubFunctionInternalFrame(boolean resizable, boolean closable, boolean iconifiable) {

        //super("IFrame #" + (++openFrameCount), true, // resizable
        super("IFrame #", resizable, // resizable
                closable, // closable
                false, // maximizable
                iconifiable);// iconifiable
        setSize(300, 300);

        setLocation(100, 100);


        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        this.addInternalFrameListener(this);



    }

    public SubFunctionInternalFrame(boolean resizable, boolean closable, boolean iconifiable, boolean selectable) {

        //super("IFrame #" + (++openFrameCount), true, // resizable
        super("IFrame #", resizable, // resizable
                closable, // closable
                false, // maximizable
                iconifiable);// iconifiable
        setSize(300, 300);

        setLocation(100, 100);


        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        this.addInternalFrameListener(this);
        try {
            setSelected(selectable);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(SubFunctionInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void internalFrameOpened(InternalFrameEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void internalFrameClosing(InternalFrameEvent event) {

         if (this.getTitle().startsWith("Add New Keyword")) {
            DisplayObjects.setisAddKeyword(false);
            DisplayObjects.setAddKeywordObj(null);
            

        }else if (this.getTitle().startsWith("Annotation")) {
            DisplayObjects.setIsAnnotation(false);
            DisplayObjects.setAnnotationObj(null);
        } else if (this.getTitle().startsWith("Remove silence") || this.getTitle().startsWith("Remove noise") || this.getTitle().startsWith("Enhance speech") || this.getTitle().startsWith("Combined")) {
            DisplayObjects.setIsPdsThreshold(false);
            DisplayObjects.setPdsThresholdObj(null);
        } else if (this.getTitle().startsWith("Keyword")) {
            DisplayObjects.setIsKwsSelected(false);
            DisplayObjects.setKwsSelectedObj(null);
        } else {
        }
        if (this.getTitle().startsWith("SID Train")) {
            DisplayObjects.setIsSidTrain(false);
            DisplayObjects.setSidTrainObj(null);
        }
        if (this.getTitle().startsWith("SID Test")) {
            DisplayObjects.setIsSidTest(false);
            DisplayObjects.setSidTestObj(null);

        }
        
        if (this.getTitle().startsWith("File")) {
            DisplayObjects.setIsFileBrowser(false);
            DisplayObjects.setFileBrowser(null);
        }
        dispose();


    }

    public void internalFrameClosed(InternalFrameEvent event) {

       System.out.println("Frame closed SFIF !");
        
    }

    public void internalFrameIconified(InternalFrameEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void internalFrameDeiconified(InternalFrameEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void internalFrameActivated(InternalFrameEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
        DisplayObjects.displayStaticPanle();
    }

    public void internalFrameDeactivated(InternalFrameEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
