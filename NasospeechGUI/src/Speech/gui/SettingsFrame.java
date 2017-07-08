/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

import Speech.settings.ChangePassword;
import Speech.settings.ServerConfiguration;
import Speech.user.CreateNewUsers;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 *  @author Tatapower SED
 *  
 */

public class SettingsFrame {

    private MainFrame mainFrame;

    public SettingsFrame(MainFrame mFrame) {
        this.mainFrame = mFrame;
    }

    public void createFrame() {
        SubFunctionInternalFrame settings = new SubFunctionInternalFrame(false, true, false);
        settings.setTitle("Settings");
        settings.setVisible(true);
        settings.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
        mainFrame.screenproperty.addSettings(settings);
        //settings.add(new BasicSettings());
        mainFrame.jDesktopPane1.add(settings);
        mainFrame.screenproperty.resizeScreen(mainFrame);
        try {
            settings.setSelected(true);
        } catch (final java.beans.PropertyVetoException e) {
        }

    }
    
    public void ChangePasswordFrame() {
        try {
            SubFunctionInternalFrame settings = new SubFunctionInternalFrame(false, true, false);
            settings.setTitle("Change Password");
            settings.setVisible(true);
            settings.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
            mainFrame.screenproperty.addSettings(settings);
            settings.add(new ChangePassword());
            mainFrame.jDesktopPane1.add(settings);
            mainFrame.screenproperty.resizeScreen(mainFrame);
            try {
                settings.setSelected(true);
            } catch (final java.beans.PropertyVetoException e) {
            }

        } catch (Exception ex) {
            Logger.getLogger(SettingsFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void ServerCongigurationFrame() {
        SubFunctionInternalFrame settings = new SubFunctionInternalFrame(false, true, false);
        settings.setTitle("Server Configuration");
        settings.setVisible(true);
        settings.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
        mainFrame.screenproperty.serverConfSettings(settings);
        settings.add(new ServerConfiguration());
        mainFrame.jDesktopPane1.add(settings);
        mainFrame.screenproperty.resizeScreen(mainFrame);
        try {
            settings.setSelected(true);
        } catch (final java.beans.PropertyVetoException e) {
        }

    }
    
     public void userCreationFrame() {
        SubFunctionInternalFrame settings = new SubFunctionInternalFrame(false, true, false);
        settings.setTitle("New Account");
        settings.setVisible(true);
        settings.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")));
        mainFrame.screenproperty.userCreationFram(settings);
        settings.add(new CreateNewUsers(mainFrame));
        mainFrame.jDesktopPane1.add(settings);
        mainFrame.screenproperty.resizeScreen(mainFrame);
        try {
            settings.setSelected(true);
        } catch (final java.beans.PropertyVetoException e) {
        }

    }
    
}
