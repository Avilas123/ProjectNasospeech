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
import Speech.WavePanel.*;
import Speech.common.PixcelConversion;
import Speech.common.CutAudioInputStream;
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
import Speech.WavePanel.RightClickEvent;
import static Speech.WavePanel.RightClickEvent.valuefromc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.WindowFocusListener;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import Speech.WavePanel.PlotProbability;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.event.InternalFrameEvent;
/**
 *
 * @author Tatapower SED
 *
 */
public class MainFrame extends javax.swing.JFrame {
    public PlotProbability plot;
    public ScreenProperties screenproperty;
    public PlotWave pWave;
    private String userID;
    public boolean rSidStatus = false;
    public boolean rPdsStatus = false;
    public boolean rKwsStatus = false;
    public boolean rPhoneStatus = false;
    private String userRoll = "";
    int timeRun;
    public RightClickEvent right;
    private int copy_from_ms = 0, copy_to_ms = 0, ann_fLength, ann_oldfLength;
    
 public String filenamedummy="";
 
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
        plot=new PlotProbability(pWave);
       
    }
  MainFrame(PlotWave pwave){
    super();
    this.pWave=pwave;
    String substring=pWave.abbfilePath;//.substring(76);
    System.out.println("filenametabbed"+substring);
       
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

    public void screenSettings() {
        //this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/btn_ns_logo.png")).getImage());
       // jInternalFrame1.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/btn_ns_logo.png")));
        //jInternalFrame2.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/export_logo.png")));
        ((javax.swing.plaf.basic.BasicInternalFrameUI)jInternalFrame1.getUI()).setNorthPane(null);
       // ((javax.swing.plaf.basic.BasicInternalFrameUI)jInternalFrame2.getUI()).setNorthPane(null);
        screenproperty.mainFrameProperty();
//        screenproperty.toolBar(jToolBar1);
        //screenproperty.filePanel(jInternalFrame2);
        screenproperty.WavePanel(jInternalFrame1);
       jInternalFrame1.add(pWave);
     
      String xx=this.dummyfileopenfunction();
      String xx1=xx.substring(75);
      String xx2="Hypernasality";
      System.out.println("got the filename in mainframe 1st"+xx1);
     //  jInternalFrame1.setTitle(xx);
       jTabbedPane2.add(xx1, jInternalFrame1);
       jTabbedPane2.add(xx2, jInternalFrame2);
         
     //   jInternalFrame2.add(PlotProbability.this.file);
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
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        Mainmenubar = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        jNew = new javax.swing.JMenuItem();
        jOpen = new javax.swing.JMenuItem();
        jClosethisfile = new javax.swing.JMenuItem();
        jSaveas = new javax.swing.JMenuItem();
        jExit = new javax.swing.JMenuItem();
        Edit = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        Analyse = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        selectandanalysemenu = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        result = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        View = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        About = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nasospeech");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ClosedObjects(evt);
            }
        });

        jTabbedPane2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jInternalFrame1.setBackground(new java.awt.Color(255, 255, 255));
        jInternalFrame1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jInternalFrame1.setClosable(true);
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
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("", jInternalFrame1);
        try {
            jInternalFrame1.setMaximum(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        jInternalFrame2.setVisible(true);

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("abc", jInternalFrame2);

        jDesktopPane1.add(jTabbedPane2);
        jTabbedPane2.setBounds(0, 0, 1210, 630);

        Mainmenubar.setFocusable(false);
        Mainmenubar.setRequestFocusEnabled(false);
        Mainmenubar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MainmenubarMouseExited(evt);
            }
        });

        File.setText("File");

        jNew.setBackground(new java.awt.Color(246, 246, 246));
        jNew.setText("New");
        jNew.setDoubleBuffered(true);
        jNew.setEnabled(false);
        jNew.setFocusCycleRoot(true);
        jNew.setFocusPainted(true);
        jNew.setFocusTraversalPolicyProvider(true);
        jNew.setFocusable(true);
        jNew.setOpaque(true);
        jNew.setRolloverEnabled(true);
        jNew.setSelected(true);
        jNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNewActionPerformed(evt);
            }
        });
        File.add(jNew);

        jOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jOpen.setText("Open");
        jOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOpenActionPerformed(evt);
            }
        });
        File.add(jOpen);

        jClosethisfile.setBackground(new java.awt.Color(246, 246, 246));
        jClosethisfile.setText("Close this file");
        jClosethisfile.setFocusable(true);
        jClosethisfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClosethisfileActionPerformed(evt);
            }
        });
        File.add(jClosethisfile);

        jSaveas.setBackground(new java.awt.Color(246, 246, 246));
        jSaveas.setText("Save as");
        jSaveas.setEnabled(false);
        jSaveas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveasActionPerformed(evt);
            }
        });
        File.add(jSaveas);

        jExit.setText("Exit");
        jExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExitActionPerformed(evt);
            }
        });
        File.add(jExit);

        Mainmenubar.add(File);

        Edit.setText("Edit");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setBackground(new java.awt.Color(246, 246, 246));
        jMenuItem1.setText("Select all");
        jMenuItem1.setEnabled(false);
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Edit.add(jMenuItem1);

        jMenuItem2.setBackground(new java.awt.Color(246, 246, 246));
        jMenuItem2.setText("Select Inverse");
        jMenuItem2.setEnabled(false);
        Edit.add(jMenuItem2);

        jMenuItem3.setBackground(new java.awt.Color(246, 246, 246));
        jMenuItem3.setText("Cut");
        jMenuItem3.setEnabled(false);
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        Edit.add(jMenuItem3);

        jMenuItem4.setBackground(new java.awt.Color(246, 246, 246));
        jMenuItem4.setText("Copy");
        jMenuItem4.setEnabled(false);
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        Edit.add(jMenuItem4);

        jMenuItem5.setBackground(new java.awt.Color(246, 246, 246));
        jMenuItem5.setText("Paste");
        jMenuItem5.setEnabled(false);
        Edit.add(jMenuItem5);

        Mainmenubar.add(Edit);

        Analyse.setText("Analyse");
        Analyse.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Analyse.setFocusPainted(true);
        Analyse.setFocusable(false);
        Analyse.setRequestFocusEnabled(false);
        Analyse.setRolloverEnabled(false);
        Analyse.setVerifyInputWhenFocusTarget(false);
        Analyse.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                AnalyseFocusLost(evt);
            }
        });
        Analyse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AnalyseMouseExited(evt);
            }
        });

        jMenuItem8.setBackground(new java.awt.Color(246, 246, 246));
        jMenuItem8.setText("Analyse all");
        jMenuItem8.setEnabled(false);
        Analyse.add(jMenuItem8);

        selectandanalysemenu.setText("Select and Analyse");
        selectandanalysemenu.setContentAreaFilled(false);
        selectandanalysemenu.setFocusable(false);
        selectandanalysemenu.setRequestFocusEnabled(false);
        selectandanalysemenu.setRolloverEnabled(false);
        selectandanalysemenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                selectandanalysemenuMouseExited(evt);
            }
        });

        jMenuItem9.setText("Hypernasility");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        selectandanalysemenu.add(jMenuItem9);

        jMenuItem10.setBackground(new java.awt.Color(246, 246, 246));
        jMenuItem10.setText("Articulation error");
        jMenuItem10.setEnabled(false);
        selectandanalysemenu.add(jMenuItem10);

        jMenuItem11.setBackground(new java.awt.Color(246, 246, 246));
        jMenuItem11.setText("Intelligibility");
        jMenuItem11.setEnabled(false);
        selectandanalysemenu.add(jMenuItem11);

        jMenuItem12.setBackground(new java.awt.Color(246, 246, 246));
        jMenuItem12.setText("Voicing error");
        jMenuItem12.setEnabled(false);
        selectandanalysemenu.add(jMenuItem12);

        Analyse.add(selectandanalysemenu);

        Mainmenubar.add(Analyse);

        result.setText("Result");

        jMenuItem19.setBackground(new java.awt.Color(246, 246, 246));
        jMenuItem19.setText("Show Result");
        jMenuItem19.setEnabled(false);
        result.add(jMenuItem19);

        jMenuItem20.setBackground(new java.awt.Color(246, 246, 246));
        jMenuItem20.setText("Print Result");
        jMenuItem20.setEnabled(false);
        result.add(jMenuItem20);

        Mainmenubar.add(result);

        View.setText("View");

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ADD, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem15.setText("Zoom In");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        View.add(jMenuItem15);

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SUBTRACT, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem16.setText("Zoom Out");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        View.add(jMenuItem16);

        jMenuItem17.setText("Fit to window");
        View.add(jMenuItem17);

        Mainmenubar.add(View);

        About.setText("About");

        jMenuItem13.setText("Nasospeech");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        About.add(jMenuItem13);

        jMenuItem14.setBackground(new java.awt.Color(246, 246, 246));
        jMenuItem14.setText("Help");
        jMenuItem14.setEnabled(false);
        About.add(jMenuItem14);

        Mainmenubar.add(About);

        setJMenuBar(Mainmenubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1208, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ClosedObjects(WindowEvent evt) {//GEN-FIRST:event_ClosedObjects
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

    
      public String dummyfileopenfunction()
    {   
        String filename = pWave.fileOpenMethod();//  fileOpenMethod();
        
      
    return filename;

    }
    
    
    
    
    
    
    
    
    private void jNewActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jNewActionPerformed
                    // TODO add your handling code here:
           
                    new NewFrameOnClickNewButton().setVisible(true);
                    
    }//GEN-LAST:event_jNewActionPerformed

    private void jSaveasActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jSaveasActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jSaveasActionPerformed

    private void jClosethisfileActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jClosethisfileActionPerformed
        // TODO add your handling code here:
        pWave.rightClick.functionclose();
    }//GEN-LAST:event_jClosethisfileActionPerformed

    private void jMenuItem1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        pWave.rightClick.cutWaveFile(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem15ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
         pWave.setZoomIn();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
pWave.setZoomOut();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jOpenActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jOpenActionPerformed
        // TODO add your handling code here:
      filenamedummy=pWave.fileOpenMethod();
    }//GEN-LAST:event_jOpenActionPerformed

    private void jExitActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jExitActionPerformed

    private void jMenuItem3ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        
        
     right.cutWaveFile(true);   
        
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        
        
            //Copy

            if (pWave.mousePosX1 != 0 && pWave.mousePosX2 != 0 && pWave.audioInputStream != null) 
            {
                
                        try {
                            //  cutWave();
                            if (pWave.mousePosX1 == 0 && pWave.mousePosX2 == 0) {
                                return;
                            }
                            copy_from_ms = 0;
                            copy_to_ms = 0;
                            right.getSamplingPositions();
                            int startSample = right.getStartSamples();
                            int endSample = right.getEndSamples();

                            System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWave.frames_per_pixel);

                            CutAudioWave cutW = new CutAudioWave();
                            cutW.cutPortion(pWave.streamBytes.getCurrent(), startSample, endSample);

                            right.calculatePixcel();
                            PixcelConversion pixConversion = new PixcelConversion();
                            copy_from_ms = pixConversion.pixcelToMillisecond(right.getStartPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                            copy_to_ms = pixConversion.pixcelToMillisecond(right.getendPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());


                            if (cutW.getresultByteArray() == null) {
                                return;
                            }



                        } catch (Exception ex) {
                            Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                        }
           }
                
            //End copy
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        



        
        
        
        

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem9ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed

       
                       
              
                        String currentDir = System.getProperty("user.dir");
                        System.out.println("cu");
                        String cexedir = currentDir + "\\cexe\\";
                       // JFrame jf = new JFrame("test");
                        //String name = JOptionPane.showInputDialog(jf,
                        //currentDir, null);
                      try {
                          Process p1;
                          System.out.println("getting filename"+pWave.abbfilePath);
                          filenamedummy = pWave.abbfilePath;
                          ProcessBuilder pb1=new ProcessBuilder
                            (cexedir+"mfcc_final_version_working",
                                    filenamedummy,
                                    "1001",
                                    cexedir+"start.txt",
                                    cexedir+"end.txt",
                                    cexedir+"vunv.txt",
                                    cexedir+"spfr.txt",
                                    cexedir+"avg.txt",
                                    cexedir+"N.txt",
                                    cexedir+"F.txt",
                                    cexedir+"mfcc_output_13dim.txt"
                            );
                          
                          
                          p1 = pb1.start();
                          
                          
                          p1.waitFor();
                                  
                                  
                          LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(cexedir+"mfcc_output_13dim.txt")));
                            lnr.skip(Long.MAX_VALUE);
                            System.out.println(lnr.getLineNumber() + 1); //Add 1 because line index starts at 0
                                    // Finally, the LineNumberReader object should be closed to prevent resource leak
                            int numFrames = lnr.getLineNumber();
                            lnr.close();
                            System.out.println("num frames = "+numFrames);
                                  ProcessBuilder pb = new ProcessBuilder(cexedir+"posteriorcomputation" ,
                                          cexedir+"mfcc_output_13dim.txt",
                                          cexedir+"mean_norm.txt",
                                          cexedir+"var_norm.txt",
                                          cexedir+"weight_norm.txt",
                                          cexedir+"mean_clp.txt",
                                          cexedir+"var_clp.txt",
                                          cexedir+"weight_clp.txt",
                                          cexedir+"output_norm.txt",
                                          cexedir+"output_clp.txt", "16", "13", Integer.toString(numFrames));
                                  
                                  //  ProcessBuilder pb = new ProcessBuilder("tree");
                                  
                                  
                                  try {
                                      Process p = pb.start();
                                      /*try {
                                      pb.wait(0);
                                      } catch (InterruptedException ex) {
                                      Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                                      */
                                       p.waitFor();
                                      BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                      
                                      valuefromc = Double.parseDouble(br.readLine());
                                      System.out.println(" i am  getting this value from c---->"+valuefromc);
                                      //br.readLine();
                                      
                                      //  System.out.println(" i am  getting this value from c---->");
                                      
                                      //System.out.println("value getting"+br);//br.readLine());
                                      // String probability =br.readLine();
                                      //double probnew = Double.parseDouble(probability);
                                      
                                                               
                                      //System.out.println(" i am  getting this value after converting from double---->"+valuefromc);
                                      PlotProbability plot=new  PlotProbability(pWave);
                                      plot.plotfunction();
                                      //pWave.mainFrame.createIvectorInternalFrame("Speaker Identification", "word/Assamese/part2");
                                  }
                                  catch (IOException ex)
                                  {
                                      Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                                  }
                                  
                                  
                      }
                      catch (IOException ex) 
                         {
                        Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                         } catch (InterruptedException ex) {
                        Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                    }

                
            
        
        
        
        
        
        
        
        
        
        
        
        
        



        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void AnalyseFocusLost(FocusEvent evt) {//GEN-FIRST:event_AnalyseFocusLost
      
      //

// TODO add your handling code here:
    }//GEN-LAST:event_AnalyseFocusLost

    private void MainmenubarMouseExited(MouseEvent evt) {//GEN-FIRST:event_MainmenubarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_MainmenubarMouseExited

    private void selectandanalysemenuMouseExited(MouseEvent evt) {//GEN-FIRST:event_selectandanalysemenuMouseExited
        //  selectandanalysemenu.setVisible(false);
       //   jMenuItem8.setVisible(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_selectandanalysemenuMouseExited

    private void AnalyseMouseExited(MouseEvent evt) {//GEN-FIRST:event_AnalyseMouseExited
       
        

    }//GEN-LAST:event_AnalyseMouseExited

    private void jMenuItem13ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        
        try {
            java.awt.Desktop.getDesktop().browse(new URL("http://www.iitg.ac.in/").toURI());
// TODO add your handling code here:
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jInternalFrame1KeyPressed(KeyEvent evt) {//GEN-FIRST:event_jInternalFrame1KeyPressed
        // TODO add your handling code here:
        // javax.swing.JOptionPane.showMessageDialog(this, "hello");
    }//GEN-LAST:event_jInternalFrame1KeyPressed

    private void jInternalFrame1MousePressed(MouseEvent evt) {//GEN-FIRST:event_jInternalFrame1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jInternalFrame1MousePressed

    private void internalframeactived(InternalFrameEvent evt) {//GEN-FIRST:event_internalframeactived
        // TODO add your handling code here:
        DisplayObjects.displayStaticPanle();
    }//GEN-LAST:event_internalframeactived

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    
    {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new LoginForm().setVisible(true);
                 //new Mainpopup().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu About;
    private javax.swing.JMenu Analyse;
    private javax.swing.JMenu Edit;
    private javax.swing.JMenu File;
    private javax.swing.JMenuBar Mainmenubar;
    private javax.swing.JMenu View;
    private javax.swing.JMenuItem jClosethisfile;
    private javax.swing.JComboBox jComboBox1;
    public javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenuItem jExit;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jNew;
    private javax.swing.JMenuItem jOpen;
    private javax.swing.JMenuItem jSaveas;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JMenu result;
    private javax.swing.JMenu selectandanalysemenu;
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
