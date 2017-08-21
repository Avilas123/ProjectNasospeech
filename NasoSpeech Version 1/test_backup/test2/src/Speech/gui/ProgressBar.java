/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.progress.ProgressBarPanel;

/**
 *
 * @author Tatapower SED
 *
 */
public class ProgressBar {

    private MainFrame mainFrame;
    private ProgressBarPanel progressObj;
  //  private InternalFrame proInternal;

    public ProgressBar(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public ProgressBar() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void createProgressInternalFrame(String panelName, String headTitle, String processTitle) {
        progressObj = new ProgressBarPanel(headTitle, processTitle);
      //  proInternal = new InternalFrame(false, false, false, mainFrame);
      //  proInternal.setTitle("");
      //  proInternal.setVisible(true);
     //   proInternal.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn2.png")));
      //  mainFrame.screenproperty.progressPanel(proInternal);
     //   proInternal.add(progressObj);
     //   mainFrame.jDesktopPane1.add(proInternal);
    //    mainFrame.screenproperty.resizeScreen(mainFrame);
    }

    public void closeProgressBar() {
        progressObj.progress.stop();
       // proInternal.dispose();
    }
}
