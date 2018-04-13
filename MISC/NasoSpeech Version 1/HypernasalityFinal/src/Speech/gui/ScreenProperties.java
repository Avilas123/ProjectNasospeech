/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.gui.MainFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.*;
import javax.swing.JToolBar;

/**
 *
 * @author Tatapower SED
 *
 */
public class ScreenProperties {

    private MainFrame mf;
    private Toolkit tk;

    public ScreenProperties(MainFrame mf) {
        this.mf = mf;
        this.tk = Toolkit.getDefaultToolkit();
    }

    public void mainFrameProperty() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) ((getScreenWidth()) - 560);
        int ySize = 450;
         
        mf.setSize(xSize, ySize);
      //  mf.jProgressToolBar.setPreferredSize(new Dimension((xSize - 200), 20));
        //mf.jProgressToolBar.setLocation((int) (xSize * .57), (getScreenHeight() - 91));
        //mf.jButton1.setLocation((xSize - 37), (getScreenHeight() - 86));
       mf.setLocation(dim.width / 2 - mf.getSize().width / 2, 200);
       // mf.jMenuBar1.setBackground(Color.yellow);
    }

    public void desktopPaneProperty(JDesktopPane deskPane) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
         
        int xSize = (int) ((getScreenWidth()) - 10);
        int ySize = 450;
       
        deskPane.setSize(xSize, ySize);

    }

    public void filePanel(JInternalFrame filePanel) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = 250;
        int ySize = 335;
        filePanel.setSize(xSize, ySize);
        filePanel.setLocation(5, 35);
    }

    public void WavePanel(JInternalFrame wavePanel) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) ((getScreenWidth()) - 280);
        int ySize = 335;
        wavePanel.setSize(xSize, ySize);
        wavePanel.setLocation(260, 35);
    }

    public void toolBar(JToolBar toolBar) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) ((getScreenWidth()) - 10);
        int ySize = (int) (30);
        toolBar.setSize(xSize, ySize);
        toolBar.setLocation(0, 0);
    }

    public void addSoundImprovePanel(JInternalFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) ((getScreenWidth()) - 25);
        int ySize = 300;
        frame.setSize(xSize, ySize);
        frame.setLocation(5, 375);

    }

    public void pdsThreshold(JInternalFrame frame) {
        int xSize = (int) ((getScreenWidth()) - 25);
        int ySize = 120;
        frame.setSize(220, ySize);
        frame.setLocation((getScreenWidth() - 110) / 2, (getScreenHeight() - 120) / 2);
    }

    public void workSpace(JInternalFrame frame) {
        int xSize = (int) ((getScreenWidth()) - 25);
        int ySize = 150;
        frame.setSize(315, ySize);
        frame.setLocation((getScreenWidth() - 315) / 2, (getScreenHeight() - 200) / 2);
    }

    public void progressPanel(JInternalFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = 350;
        int ySize = 210;
        frame.setSize(xSize, ySize);
        frame.setLocation((getScreenWidth() - 150) / 2, (getScreenHeight() - 200) / 2);

    }

    //--
    public void addSettings(JInternalFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) (350);
        int ySize = 220;
        frame.setSize(xSize, ySize);
        frame.setLocation((getScreenWidth() - 300) / 2, (getScreenHeight() - 300) / 2);
    } //--

    //--
    public void serverConfSettings(JInternalFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) (350);
        int ySize = 180;
        frame.setSize(xSize, ySize);
        frame.setLocation((getScreenWidth() - 300) / 2, (getScreenHeight() - 300) / 2);
    } //--

    public void userCreationFram(JInternalFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) (350);
        int ySize = 220;
        frame.setSize(xSize, ySize);
        frame.setLocation((getScreenWidth() - 300) / 2, (getScreenHeight() - 300) / 2);
    } //--

    public void addSIDTrainFrame(JInternalFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) (240);
        int ySize = 390;
        frame.setSize(xSize, ySize);
        frame.setLocation((getScreenWidth() - 300) / 2, (getScreenHeight() - 300) / 2);
    }

    public void addSIDVerificationFrame(JInternalFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) (230);
        int ySize = 430;
        frame.setSize(xSize, ySize);
        frame.setLocation((getScreenWidth() - 300) / 2, (getScreenHeight() - 300) / 2);
    }

    public void addSIDTestFrame(JInternalFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) (800);
        int ySize = 580;
        frame.setSize(xSize, ySize);
        frame.setLocation((getScreenWidth() - 800) / 2, (getScreenHeight() - 660) / 2);
    }

    public void addSoundKwsPanel(JPanel panel) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) ((getScreenWidth()) - 280);
        int ySize = 270;
        panel.setSize(xSize, ySize);
        panel.setLocation(250, 0);

    }

    public void addSpeakerID(JPanel panel) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) ((getScreenWidth()) - 280);
        int ySize = 270;
        panel.setSize(xSize, ySize);
        panel.setLocation(250, 0);

    }

    public void addResultKeywordPanel(JInternalFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = 245;
        int ySize = 270;
        frame.setSize(xSize, ySize);
        frame.setLocation(0, 0);

    }

    public void keywordSelectionFrame(JInternalFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) (490);
        int ySize = 600;
        frame.setSize(xSize, ySize);
        frame.setLocation(((getScreenWidth()) - 505), 35);
    }

    public void keywordSelectionPanel(JPanel panle) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) (330);
        int ySize = 600;
        panle.setSize(xSize, ySize);
        panle.setLocation(((getScreenWidth()) - 360), 35);
    }

    public void annotationPannel(JInternalFrame wavePanel) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) (300);
        int ySize = 520;
        wavePanel.setSize(xSize, ySize);
        wavePanel.setLocation(((getScreenWidth()) - 280) / 2, (getScreenHeight() - 500) / 2);
    }

        public void addKwPannel(JInternalFrame wavePanel) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) (350);
        int ySize = 280;
        wavePanel.setSize(xSize, ySize);
        wavePanel.setLocation(((getScreenWidth()) - 280) / 2, (getScreenHeight() - 500) / 2);
    }

    public void fileTransfer(JInternalFrame wavePanel) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) (510);
        int ySize = 312;
        wavePanel.setSize(xSize, ySize);
        wavePanel.setLocation(((getScreenWidth()) - 510) / 2, (getScreenHeight() - 312) / 2);
    }

    public void resizeScreen(MainFrame mFrame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int xSize = (int) ((getScreenWidth()) - 10);
        int ySize = (int) ((getScreenHeight()) - 10);
        mFrame.setSize(xSize, ySize);

        //mFrame.setLocation(dim.width / 2 - mFrame.getSize().width / 2, dim.height / 2 - mFrame.getSize().height / 2);
    }

    public int getScreenWidth() {
        return ((int) tk.getScreenSize().getWidth());
    }

    public int getScreenHeight() {
        return ((int) tk.getScreenSize().getHeight());
    }
}
