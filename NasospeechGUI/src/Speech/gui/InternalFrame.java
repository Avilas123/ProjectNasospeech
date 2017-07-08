/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.kws.PlotWaveKws;
import Speech.pds.PlotWavePDS;
import Speech.phoneme.PlotWavePhoneme;
import Speech.MFCC.PlotWaveMFCC;
import Speech.staticobjects.DisplayObjects;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author Tatapower SED
 *
 */
public class InternalFrame extends JInternalFrame implements InternalFrameListener {

    static final int xPosition = 30, yPosition = 30;
    private MainFrame mainframe;
    private PlotWavePDS pdsWave = null;
    private PlotWaveKws kwsWave = null;
    private PlotWavePhoneme phoneWave = null;
    private PlotWaveMFCC mfccWave = null;
    
    private ArrayList dummyList;

    public InternalFrame(boolean resizable, boolean closable, boolean iconifiable, MainFrame mainFrame) {

        //super("IFrame #" + (++openFrameCount), true, // resizable
        super("IFrame #", resizable, // resizable
                closable, // closable
                false, // maximizable
                iconifiable);// iconifiable
        setSize(300, 300);

        setBackground(Color.white);
        setLocation(100, 100);

        setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);

        this.addInternalFrameListener(this);
        this.mainframe = mainFrame;

    }

    public InternalFrame(boolean resizable, boolean closable, boolean iconifiable, PlotWavePDS pdsFrame) {

        //super("IFrame #" + (++openFrameCount), true, // resizable
        super("IFrame #", resizable, // resizable
                closable, // closable
                false, // maximizable
                iconifiable);// iconifiable
        setSize(300, 300);


        setLocation(100, 100);


        setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);

        this.addInternalFrameListener(this);
        this.pdsWave = pdsFrame;

    }

    public InternalFrame(boolean resizable, boolean closable, boolean iconifiable, PlotWavePhoneme pdsFrame) {

        //super("IFrame #" + (++openFrameCount), true, // resizable
        super("IFrame #", resizable, // resizable
                closable, // closable
                false, // maximizable
                iconifiable);// iconifiable
        setSize(300, 300);


        setLocation(100, 100);


        setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);

        this.addInternalFrameListener(this);
        this.phoneWave = pdsFrame;

    }
    
    
     public InternalFrame(boolean resizable, boolean closable, boolean iconifiable, PlotWaveKws pdsFrame) {

        //super("IFrame #" + (++openFrameCount), true, // resizable
        super("IFrame #", resizable, // resizable
                closable, // closable
                false, // maximizable
                iconifiable);// iconifiable
        setSize(300, 300);


        setLocation(100, 100);


        setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);

        this.addInternalFrameListener(this);
        this.kwsWave = pdsFrame;

    }
    
    
  

    public InternalFrame(boolean resizable, boolean closable, boolean iconifiable, PlotWaveMFCC mfccFrame) {

        //super("IFrame #" + (++openFrameCount), true, // resizable
        super("IFrame #", resizable, // resizable
                closable, // closable
                false, // maximizable
                iconifiable);// iconifiable
        setSize(300, 300);


        setLocation(100, 100);


        setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);

        this.addInternalFrameListener(this);
        this.mfccWave = mfccFrame;

    }

    public void setDummyList(ArrayList dummy) {
        this.dummyList = dummy;
    }

    public void internalFrameOpened(InternalFrameEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void internalFrameClosing(InternalFrameEvent event) {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to close ", "Exit", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                if ((this.getTitle().trim().toLowerCase()).startsWith("remove") || (this.getTitle().trim().toLowerCase()).startsWith("enhance") || (this.getTitle().trim().toLowerCase()).startsWith("combined")) {
                    if (pdsWave != null) {
                        try {
                            pdsWave.stopSound();
                            pdsWave.setVisible(false);
                            pdsWave.close();
                            pdsWave = null;

                        } catch (Exception er) {
                        }
                    }
                }

                if ((this.getTitle().trim().toLowerCase()).startsWith("keyword")) {
                    if (kwsWave != null) {
                        try {
                            kwsWave.stopSound();
                            kwsWave.setVisible(false);
                            kwsWave.close();
                            kwsWave = null;

                        } catch (Exception er) {
                        }
                    }
                }

                if ((this.getTitle().trim().toLowerCase()).startsWith("phenome")) {
                    if (phoneWave != null) {
                        try {
                            phoneWave.stopSound();
                            phoneWave.setVisible(false);
                            phoneWave.close();
                            phoneWave = null;

                        } catch (Exception er) {
                        }
                    }
                }
            } catch (Exception er) {
            }
            dispose();

            if (dummyList != null) {
                if (dummyList.size() > 0) {
                    dummyList.clear();

                }
            }

        }
    }

    public void internalFrameClosed(InternalFrameEvent event) {

        System.out.println("Frame closed !");

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
