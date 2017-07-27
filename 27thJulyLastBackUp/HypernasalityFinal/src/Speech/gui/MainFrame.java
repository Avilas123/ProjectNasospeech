/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on 3 Jan, 2013, 2:40:26 PM
 */
package Speech.gui;


import Speech.WavePanel.PlotWave;
import Speech.annotations.FindAnnotated;
import Speech.annotations.Hash;
import Speech.annotations.UpdateAnnotation;

import Speech.common.StreamConverter;

import Speech.staticobjects.DisplayObjects;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Tatapower SED
 *
 */
public class MainFrame extends javax.swing.JFrame {

    public ScreenProperties screenproperty;
    public PlotWave pWave;
    private String userID;
    public boolean rSidStatus = false;
    public boolean rPdsStatus = false;
    public boolean rKwsStatus = false;
    public boolean rPhoneStatus = false;
    private String userRoll = "";
    int timeRun;

    /**
     * Creates new form MainFrame
     */
    /*  function for displaying clock */
    public MainFrame(String userID, String userRoll) {
        initComponents();
        new Thread()
        {
            public void run()
            {
               while(timeRun == 0)
               {
                   Calendar cal = new GregorianCalendar();
                   int hour = cal.get(Calendar.HOUR);
                   int min = cal.get(Calendar.MINUTE);
                    int sec = cal.get(Calendar.SECOND);
                    int AM_PM = cal.get(Calendar.AM_PM);
                     String day_night ="";
                     if(AM_PM==1)
                     {
                         day_night="PM";
                     }
                     else
                     {
                         day_night = "AM";
                     }
                     String time = hour+":"+min+":"+sec+""+AM_PM;
                     //clock.setText(time);
               }
            }
        }.start();
       /* end of function clock*/
         
        this.userID = userID;
        this.userRoll = userRoll;
        //jLabelUserName.setText(userID);
        this.setTitle("NasoSpeech");
        screenproperty = new ScreenProperties(this);
        pWave = new PlotWave(this, null);
        screenSettings();
        fileBrowser("");
        heepSize();
    }

    public int getUserRoll() {
        int userRolls = 0;
        try {
            userRolls = Integer.parseInt(userRoll);
        } catch (NumberFormatException er) {
            System.err.println(er);
        }

        return userRolls;
    }

    public void windowClosing(WindowEvent e) {
        //exit();
    }

    private void screenSettings() {
        //this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/btn_ns_logo.png")).getImage());
       // jInternalFrame1.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/btn_ns_logo.png")));
        //jInternalFrame2.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/export_logo.png")));
        ((javax.swing.plaf.basic.BasicInternalFrameUI)jInternalFrame1.getUI()).setNorthPane(null);
        screenproperty.mainFrameProperty();
//        screenproperty.toolBar(jToolBar1);
        //screenproperty.filePanel(jInternalFrame2);
        screenproperty.WavePanel(jInternalFrame1);
        jInternalFrame1.add(pWave);
       // pdsButtonStatus(false);
        //sidButtonStatus(false);
       // phonemeButtonStatus(false);
       // kwsButtonStatus(false);
//        jProgressToolBar.setVisible(false);


    }
      //...setting file path.......
    
        public void fileBrowser(String filePath) {
        filePath = "/home/VRS/";
        if (filePath == null) {
            return;
        }
        filePath = filePath.trim();
        if (filePath.length() < 1) {
            return;
        }
        //fileBrowserPanel.setPreferredSize(new Dimension(300, 280));
        //fileBrowserPanel.removeAll();
        //fileBrowserPanel.setLayout(new BorderLayout());
      //  FileManager filem = new FileManager(this);
        //fileBrowserPanel.add(filem.getGui());
        //fileBrowserPanel.revalidate();
        //fileBrowserPanel.repaint();
        //serverfilebrowserpanel.removeAll();
        //serverfilebrowserpanel.setPreferredSize(new Dimension(300, 280));

        // fileBrowserPanel.setLayout(new BorderLayout());
        //serverfilebrowserpanel.add(new ServerFileBrowser(this).CreateServerFileStructure());
        //serverfilebrowserpanel.revalidate();
        //serverfilebrowserpanel.repaint();

    }
       //-----Initialization --------------------------------------------
    public void createSoundImprovePanel(String panelName, AudioInputStream audioInputStream) {
        heepSize();
        //new PDSFrame(this, panelName, audioInputStream).createPDSInternalFrame();

    }

    

    


    
   
    
    
    
    
    
    

 

    
    
    //public void createPhenomeInternalFrame(String panelName, AudioInputStream AudioInputStream, String language, ArrayList processedList) {
  
  
    
    
    

   



   

   
   


  

    public void displayProgressBar(String fileName) {
        // new ProgressBar(this).createPDSInternalFrame(fileName);
    }

    // ----connection to database-------------------------------------------
 

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void heepSize() {
        /*    int mb = 1024 * 1024;
     
         //Getting the runtime reference from system
         Runtime runtime = Runtime.getRuntime();


   
      
         System.out.println("Used Memory:"
         + (runtime.totalMemory() - runtime.freeMemory()) / mb);

         System.out.println("Free Memory:"
         + runtime.freeMemory() / mb);
         
     
         System.out.println("Total Memory:" + runtime.totalMemory() / mb);
 
      
         System.out.println("Max Memory:" + runtime.maxMemory() / mb);
  
         */
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jOpenMenu = new javax.swing.JMenuItem();
        jSaveMenu = new javax.swing.JMenuItem();
        jSaveAsMenu = new javax.swing.JMenuItem();
        jexitMenu = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItemChangePassword = new javax.swing.JMenuItem();
        jMenuItemServerConf = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("TataSed");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ClosedObjects(evt);
            }
        });

        jInternalFrame1.setBackground(new java.awt.Color(255, 255, 255));
        jInternalFrame1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jInternalFrame1.setToolTipText("");
        jInternalFrame1.setAutoscrolls(true);
        jInternalFrame1.setVisible(true);
        jInternalFrame1.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                internalframeactived(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        jInternalFrame1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jInternalFrame1MousePressed(evt);
            }
        });
        jInternalFrame1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jInternalFrame1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 491, Short.MAX_VALUE)
        );

        jDesktopPane1.add(jInternalFrame1);
        jInternalFrame1.setBounds(0, 10, 460, 480);
        try {
            jInternalFrame1.setMaximum(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        jMenu1.setText("File");

        jOpenMenu.setText("Open");
        jOpenMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOpenMenuActionPerformed(evt);
            }
        });
        jMenu1.add(jOpenMenu);

        jSaveMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jSaveMenu.setText("Save");
        jMenu1.add(jSaveMenu);

        jSaveAsMenu.setText("Save as");
        jSaveAsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveAsMenuActionPerformed(evt);
            }
        });
        jMenu1.add(jSaveAsMenu);

        jexitMenu.setText("Exit");
        jexitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jexitMenuActionPerformed(evt);
            }
        });
        jMenu1.add(jexitMenu);

        jMenuBar1.add(jMenu1);

        jMenu8.setText("Settings");
        jMenu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu8MouseClicked(evt);
            }
        });

        jMenuItemChangePassword.setText("Change Password");
        jMenuItemChangePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItemChangePasswordMouseClicked(evt);
            }
        });
        jMenuItemChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemChangePasswordActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItemChangePassword);

        jMenuItemServerConf.setText("Server Configuration");
        jMenuItemServerConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemServerConfActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItemServerConf);

        jMenuItem18.setText("Create User");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem18);

        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu8MouseClicked

    private void jMenuItemChangePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemChangePasswordMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemChangePasswordMouseClicked

    private void jMenuItemChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemChangePasswordActionPerformed
        // TODO add your handling code here:
     //   createChangePasswordFrame();

    }//GEN-LAST:event_jMenuItemChangePasswordActionPerformed

    private void jMenuItemServerConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemServerConfActionPerformed
        // TODO add your handling code here:
        //createServerConfFrame();
    }//GEN-LAST:event_jMenuItemServerConfActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // TODO add your handling code here:
      //  new SettingsFrame(this).userCreationFrame();
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void ClosedObjects(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ClosedObjects
        // TODO add your handling code here:
        try {
            if (pWave.getfileNameColor().equals(Color.red)) {

                int option = javax.swing.JOptionPane.showConfirmDialog(pWave.mainFrame, "Do you want to save changes to " + pWave.fileName, "Save", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    pWave.rightClick.saveFile("saveAS");
                }
            }
            if (pWave.rightClick.sourceAvailValidation()) {
                String curr_hash_name = Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
            //    boolean find = new FindAnnotated(pWave.mainFrame.getConn(), curr_hash_name, 0, 0).isAnnotationFileinServer();

              /*  if (!find) {
                    int option = javax.swing.JOptionPane.showConfirmDialog(pWave.mainFrame, "" + pWave.fileName.substring(0, pWave.fileName.length()) + " is annotated, if not saved in server changes will be removed, Do you want to send to server", "Conform", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {

                        StreamConverter.byteTowavefile(pWave.streamBytes.getCurrent(), pWave.audioInputStream, "conf/workspace/" + pWave.fileName);
                      
                        File workspacefile = new File("conf/workspace/" + pWave.fileName);
                        if (workspacefile.exists()) {
                            String current_hash_name = Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
                            pWave.mainFrame.createSendServerFile("conf/workspace/" + pWave.fileName, current_hash_name, true);

                        } else {
                            new UpdateAnnotation(pWave.mainFrame.getConn()).deleteTempFileAnnotation();
                            System.exit(0);
                        }
                    } else {
                        new UpdateAnnotation(pWave.mainFrame.getConn()).deleteTempFileAnnotation();
                        System.exit(0);
                    }
                } else {
                    new UpdateAnnotation(pWave.mainFrame.getConn()).deleteTempFileAnnotation();
                    System.exit(0);
                }*/
            } else {
               // new UpdateAnnotation(pWave.mainFrame.getConn()).deleteTempFileAnnotation();
                System.exit(0);
            }

            //new WorkspaceFrame(this).createWorkSapceFrame(true, pWave.fileName);


        } catch (Exception er) {
            System.exit(0);
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, er);
        }
    }//GEN-LAST:event_ClosedObjects

    private void jOpenMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOpenMenuActionPerformed
                    // TODO add your handling code here:
            pWave.fileOpenMethod();
    }//GEN-LAST:event_jOpenMenuActionPerformed

    private void jexitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jexitMenuActionPerformed
        // TODO add your handling code here:
        try {
            System.exit(0);
        } catch (Exception er) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, er);
        }
    }//GEN-LAST:event_jexitMenuActionPerformed

    private void jInternalFrame1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jInternalFrame1KeyPressed
        // TODO add your handling code here:
        // javax.swing.JOptionPane.showMessageDialog(this, "hello");
    }//GEN-LAST:event_jInternalFrame1KeyPressed

    private void jInternalFrame1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInternalFrame1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jInternalFrame1MousePressed

    private void internalframeactived(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_internalframeactived
        // TODO add your handling code here:
        DisplayObjects.displayStaticPanle();
    }//GEN-LAST:event_internalframeactived

    private void jSaveAsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveAsMenuActionPerformed
        // TODO add your handling code here:
        pWave.rightClick.saveFile("SaveAs");
    }//GEN-LAST:event_jSaveAsMenuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 new Mainpopup().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    public javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItemChangePassword;
    private javax.swing.JMenuItem jMenuItemServerConf;
    private javax.swing.JMenuItem jOpenMenu;
    private javax.swing.JMenuItem jSaveAsMenu;
    private javax.swing.JMenuItem jSaveMenu;
    private javax.swing.JMenuItem jexitMenu;
    // End of variables declaration//GEN-END:variables
    // function for status button
  /*  public void kwsButtonStatus(boolean status) {

        jkwsProgressBar.setVisible(status);
        jkwscancelLabel.setVisible(status);
        jkwsnameLabel.setVisible(status);
        jkwsspaceLabel1.setVisible(status);

    }

    public void phonemeButtonStatus(boolean status) {

        jphoneLabel.setVisible(status);
        jphoneNameLabel.setVisible(status);
        jphoneProgressBar1.setVisible(status);
        jphoneSpaceLabel1.setVisible(status);

    }

    public void sidButtonStatus(boolean status) {
        jsidLabel.setVisible(status);
        jsidProgressBar.setVisible(status);
        jsidnameLabel.setVisible(status);

    }

    public void pdsButtonStatus(boolean status) {
        jpdsProgressBar.setVisible(status);
        jpdscancelLabel.setVisible(status);
        jpdsnameLabel.setVisible(status);
        jpdsspaceLabel1.setVisible(status);

    }*/


}
