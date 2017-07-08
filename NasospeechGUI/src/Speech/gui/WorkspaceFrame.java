/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.workspace.WorkspacePanel;

/**
 *
 * @author Tatapower SED
 *
 */
public class WorkspaceFrame {

    private MainFrame mFrame;

    public WorkspaceFrame(MainFrame mframe) {
        this.mFrame = mframe;
    }

    public void createWorkSapceFrame(String fileName, String hashvalue, boolean closed) {
        try {
            System.out.println("Hello object selected");

            SubFunctionInternalFrame subFun = new SubFunctionInternalFrame(false, false, false, true);
            WorkspacePanel workspace = new WorkspacePanel(fileName, this.mFrame, hashvalue, subFun, closed);

            subFun.setTitle("Save to server");
            subFun.setVisible(true);
            subFun.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
            mFrame.screenproperty.workSpace(subFun);
            subFun.add(workspace);
            mFrame.jDesktopPane1.add(subFun);
            mFrame.screenproperty.resizeScreen(mFrame);

            try {
                subFun.setSelected(true);
            } catch (final java.beans.PropertyVetoException e) {
            }
        } catch (Exception er) {
            System.out.println(er);
        }
    }
}
