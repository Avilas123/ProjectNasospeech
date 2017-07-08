/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.kws;
import Speech.progress.*;
import Speech.gui.KeyWordSpottingFrame;
import Speech.gui.MainFrame;
import Speech.gui.ProgressBar;
import static Speech.kws.KeywordPanel.displayprogressbar;
import static Speech.kws.KeywordPanel.kwsProcess;
import Speech.kws.newTreeCode.NewKWSelectionPane;
import Speech.staticobjects.DisplayObjects;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Tatapower SED
 *
 */
public class KeywordPanel extends javax.swing.JPanel {

    /**
     * Creates new form AddKeyword
     */
    private Connection connect = null;
    private String language = null;
    private DefaultComboBoxModel comboModel;
    //private CreateKeywordModel cModel = null;
    private CreateKeywordModel cModel[], cModelAll;//this variable - 'cModel' was restructured from single variable to array on 20/5/14
    private SelectedKeywordTree selectkeyTree = null;
    public KeyWordSpottingFrame keyFrame = null;
    private ArrayList selectedKeywordList = null;
    public ProgressBar progressBarObj;
    public static DoKWSProcess kwsProcess;
    public static DisplayProgressBar displayprogressbar;
    //public JTreeExample jTreeEx;
    private NewKWSelectionPane nj;
    private MainFrame mFrame;
     ProgressSample progObj=new ProgressSample();

    public KeywordPanel(Connection connect, String language, KeyWordSpottingFrame keyFrame, MainFrame mFrame) {
        initComponents();
        this.connect = connect;
        this.language = language;
        this.progressBarObj = new ProgressBar(keyFrame.mFrame);
        this.keyFrame = keyFrame;
        this.mFrame = mFrame;
        
        /* This part was modiied today, 20/5/14. Purpose: create 5 different model for 5 different lists. 
         * Earlier only one model was used due to which all the keywords per priority appeared in each of the lists*/
        cModel=new CreateKeywordModel[5];
        cModel[0] = new CreateKeywordModel(this.connect);
        cModel[1] = new CreateKeywordModel(this.connect);
        cModel[2] = new CreateKeywordModel(this.connect);
        cModel[3] = new CreateKeywordModel(this.connect);
        cModel[4] = new CreateKeywordModel(this.connect);
        cModelAll=new CreateKeywordModel(this.connect);
        screenProperties();
        loadAllKeyword();
        kwsProcess = new DoKWSProcess();
        displayprogressbar = new DisplayProgressBar();

    }

    private void screenProperties() {
        this.setSize(new Dimension(1005, 565));
        this.setPreferredSize(new Dimension(1005, 565));

        jTabbedPane2.setSize(new Dimension(1005, 560));
        jTabbedPane2.setPreferredSize(new Dimension(1005, 560));

        jPanel1.setSize(new Dimension(315, 550));
        jPanel1.setPreferredSize(new Dimension(315, 550));

        jPanel2.setSize(new Dimension(315, 550));
        jPanel2.setPreferredSize(new Dimension(315, 550));

        jPanel3.setSize(new Dimension(315, 400));
        jPanel3.setPreferredSize(new Dimension(315, 400));

        jPanel6.setSize(new Dimension(455, 490));
        jPanel6.setPreferredSize(new Dimension(455, 490));

        jPanel5.setSize(new Dimension(315, 130));
        jPanel5.setPreferredSize(new Dimension(315, 130));
        jPanel5.setLocation(0, 420);

        jPanel7.setSize(new Dimension(315, 50));
        jPanel7.setPreferredSize(new Dimension(315, 50));
        jPanel7.setLocation(0, 480);


    }

       public void loadAllKeyword() {
           String all="all";
           //System.out.println("\nInside KP:lAK \n");
        try {
                       
            //System.out.println("\nInside KP:lAK:try:pass-value-to-gKM \n");
            
            DefaultMutableTreeNode keyModelAll = cModelAll.getKeywordModel(language,all);
            
            //if (cModelAll.getGroupName() != null) {                
                //comboModel = new DefaultComboBoxModel((Vector) cModelAll.getGroupName());
                //jComboBox2.removeAll();
                //jComboBox2.setModel(comboModel);
              //}
            jPanel3.removeAll();
            jPanel3.setLayout(new BorderLayout());
            jPanel3.add(new KeywordMaster().getContent(keyModelAll, this));
            jPanel3.revalidate();

                
            //this loop was added on 20/5 /14 to iterate over each of the lists and to display the content for each list
            for(int i=0;i<5;i++){
                String list="list "+(i+1);               
                DefaultMutableTreeNode keyModel = cModel[i].getKeywordModel(language,list);
                if (keyModel == null) {
                    return;
                }
                if (cModel[i].getGroupTree() == null) {
                    return;
                }
            
                if (cModel[i].getGroupTree() == null) {
                    return;
               }    
            }    
                   
           
            selectkeyTree = new SelectedKeywordTree();
            jPanel6.removeAll();
            jPanel6.setLayout(new BorderLayout());
//            jPanel6.add(selectkeyTree.getSelectedKeyword(cModel.getGroupTree()));
//            nj = new KWSelectionPane(cModel.getGroupTree(),cModel.getGroupTree(),cModel.getGroupTree(),cModel.getGroupTree(),cModel.getGroupTree(),language);
           //the following was edited on 20/5/14
            
            System.out.println("kws list"+language);
            nj = new NewKWSelectionPane(cModel[0].getGroupTree(), cModel[1].getGroupTree(), cModel[2].getGroupTree(), cModel[3].getGroupTree(), cModel[4].getGroupTree(), language);

            jPanel6.add(nj);
            jPanel6.revalidate();

        } catch (Exception er) {
            System.err.println(er);
        }
    }



    public void updateKeywordOnDrag(String keyword, String group) {
        try {
            if (keyword == null || group == null) {
                return;
            }
            if (group.indexOf("(") > 0) {
                group = group.substring(0, group.indexOf("("));
            }
            group = group.trim();
            boolean updated = cModelAll.updateKeywordList(language, keyword, group);
            if (updated) {
                loadAllKeyword();
            }
        } catch (Exception er) {
            System.out.println("Error " + er.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1000, 500));

        jTabbedPane2.setPreferredSize(new java.awt.Dimension(1005, 466));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton3.setText("Submit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Close");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(270, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(544, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Keyword selection", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Keyword : ");

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Group :");

        jComboBox2.setFont(new java.awt.Font("Times New Roman", 0, 14));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "priority 1", "priority 2", "priority 3", "priority 4", "priority 5", "priority 6", "priority 7", "priority 8", "priority 9", "priority 10" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Transc.   :");

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("List :");

        jComboBox1.setFont(new java.awt.Font("Times New Roman", 0, 14));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "list 1", "list 2", "list 3", "list 4", "list 5" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(39, 39, 39)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(544, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Add or edit keyword", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            String newKeyword = jTextField1.getText().trim();
            if (newKeyword.length() < 1) {
                javax.swing.JOptionPane.showMessageDialog(this, "Keyword field cannot be left blank");
                return;
            }
            boolean inserted = cModelAll.addNewKeyword(language, newKeyword, jComboBox1.getSelectedItem().toString(), jComboBox2.getSelectedItem().toString());
            if (!inserted) {
                javax.swing.JOptionPane.showMessageDialog(this, "Unable to insert");
            }
            else{
                   javax.swing.JOptionPane.showMessageDialog(this, "Keyword added Successfully");
            }
            jTextField1.setText("");
            jTextField2.setText("");
            jComboBox2.setSelectedIndex(0);
            loadAllKeyword();
        } catch (Exception er) {
            System.out.println("Error " + er.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTextField1.setText("");
        jTextField2.setText("");
        jComboBox2.setSelectedIndex(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:

            nj.saveTempHotList();
            selectedKeywordList = nj.getKWHotList();
            if (selectedKeywordList.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(keyFrame.mFrame, "Hot list is empty");
                return;
            }

        } catch (Exception ex) {
            Logger.getLogger(KeywordPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (keyFrame.mFrame.rKwsStatus) {
            javax.swing.JOptionPane.showMessageDialog(keyFrame.mFrame, "Process already running... ");
            keyFrame.KwsDisplay.dispose();
            return;
        }
       try{
           
          
           progObj.indeterminentProgress();
       }catch(Exception e){
           System.err.append("Error "+e);
       }

        try {
            displayprogressbar.start();
            kwsProcess.start();
            jButton3.setEnabled(false);
            DisplayObjects.setIsKwsSelected(false);
            DisplayObjects.setKwsSelectedObj(null);
            keyFrame.KwsDisplay.dispose();
        } catch (Exception er) {
            System.out.println("Error " + er.getMessage());
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            DisplayObjects.setIsKwsSelected(false);
            DisplayObjects.setKwsSelectedObj(null);
            keyFrame.KwsDisplay.dispose();
        } catch (Exception er) {
            System.out.println("Error " + er.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    public class DoKWSProcess implements Runnable {

        private Thread thread = null;

        public void start() {
            thread = new Thread(this);
            thread.setName("ProgressBar");
            thread.start();
        }

        public void stop() {
            if (thread != null) {
                thread.interrupt();
            }
            thread = null;
        }

        public void run() {
            try {
                long start = System.currentTimeMillis();
                try {
                    keyFrame.mFrame.rKwsStatus = true;
                    keyFrame.processSelectedKeywordSpotting(selectedKeywordList);
                } catch (Exception er) {
                    System.err.println(er);
                }
                if (displayprogressbar != null) {
                    displayprogressbar.stop();
                }
                
              progObj.closeProgressBar();
                double elapsed = (System.currentTimeMillis() - start) / 1000.0;
                System.out.println("Execution Time: " + elapsed);
                String exTime = String.valueOf(elapsed);
                mFrame.pWave.setExeutionTime("Exec Time: " + exTime + " Sec");
                
                
            } catch (Exception er) {
                System.err.println(er);
            }
            keyFrame.mFrame.rKwsStatus = false;

            if (keyFrame.mFrame.rKwsStatus || keyFrame.mFrame.rPdsStatus || keyFrame.mFrame.rPhoneStatus || keyFrame.mFrame.rSidStatus) {
                keyFrame.mFrame.jProgressToolBar.setVisible(true);
            } else {
                keyFrame.mFrame.jProgressToolBar.setVisible(false);
            }

            kwsProcess = null;
        }
    }

    public class DisplayProgressBar implements Runnable {

        private Thread thread = null;

        public void start() {
            thread = new Thread(this);
            thread.setName("ProgressBar");
            thread.start();
        }

        public void stop() {
            if (thread != null) {
                thread.interrupt();
            }
            thread = null;
        }

        public void run() {
            try {
                keyFrame.mFrame.jProgressToolBar.setVisible(true);
                keyFrame.mFrame.kwsButtonStatus(true);
                while (thread != null) {
                    for (int i = 0; i <= 100; i = i + 3) {
                        if (thread == null) {
                            break;
                        }

                        //Progressively increment variable i
                        keyFrame.mFrame.jkwsProgressBar.setValue(i);
                        keyFrame.mFrame.jkwsProgressBar.repaint(); //Refresh graphics
                        try {
                            Thread.sleep(50);
                        } //Sleep 50 milliseconds
                        catch (InterruptedException err) {
                        }
                    }
                }
                keyFrame.mFrame.kwsButtonStatus(false);
                keyFrame.mFrame.jkwsProgressBar.setValue(0);
                keyFrame.mFrame.jkwsProgressBar.repaint();
            } catch (Exception er) {
                System.err.println(er);
            }
            displayprogressbar = null;

        }
    }

    public static void stopKwsProcess() {

        if (kwsProcess != null) {
            kwsProcess.stop();
            if (!(kwsProcess.thread.isAlive())) {
                displayprogressbar.stop();
                displayprogressbar = null;
                kwsProcess = null;
            }
        }
        if (kwsProcess == null && displayprogressbar != null) {
            displayprogressbar.stop();
            displayprogressbar = null;
        }

    }
}


/*package Speech.kws;

 import Speech.gui.KeyWordSpottingFrame;
 import Speech.gui.ProgressBar;
 import Speech.staticobjects.DisplayObjects;
 import java.awt.BorderLayout;
 import java.awt.Dimension;
 import java.sql.Connection;
 import java.util.ArrayList;
 import java.util.Vector;
 import javax.swing.DefaultComboBoxModel;
 import javax.swing.JScrollPane;
 import javax.swing.tree.DefaultMutableTreeNode;

 /**
 *
 * @author speechware_2
 */
//public class KeywordPanel extends javax.swing.JPanel {
/**
 * Creates new form AddKeyword
 */
/*    private Connection connect = null;
 private String language = null;
 private DefaultComboBoxModel comboModel;
 private CreateKeywordModel cModel = null;
 private SelectedKeywordTree selectkeyTree = null;
 public KeyWordSpottingFrame keyFrame = null;
 private ArrayList selectedKeywordList = null;
 public ProgressBar progressBarObj;
 public static KeywordPanel.DoKWSProcess kwsProcess;
 public static KeywordPanel.DisplayProgressBar displayprogressbar;

 public KeywordPanel(Connection connect, String language, KeyWordSpottingFrame keyFrame) {
 initComponents();
 this.connect = connect;
 this.language = language;
 this.progressBarObj = new ProgressBar(keyFrame.mFrame);
 this.keyFrame = keyFrame;
 cModel = new CreateKeywordModel(this.connect);
 screenProperties();
 loadAllKeyword();
 kwsProcess = new KeywordPanel.DoKWSProcess();
 displayprogressbar = new KeywordPanel.DisplayProgressBar();


 }

 private void screenProperties() {
 this.setSize(new Dimension(320, 565));
 this.setPreferredSize(new Dimension(320, 565));

 jTabbedPane2.setSize(new Dimension(315, 560));
 jTabbedPane2.setPreferredSize(new Dimension(315, 560));

 jPanel1.setSize(new Dimension(315, 550));
 jPanel1.setPreferredSize(new Dimension(315, 550));

 jPanel2.setSize(new Dimension(315, 550));
 jPanel2.setPreferredSize(new Dimension(315, 550));

 jPanel3.setSize(new Dimension(315, 400));
 jPanel3.setPreferredSize(new Dimension(315, 400));

 jPanel6.setSize(new Dimension(315, 480));
 jPanel6.setPreferredSize(new Dimension(315, 480));

 jPanel5.setSize(new Dimension(315, 130));
 jPanel5.setPreferredSize(new Dimension(315, 130));
 jPanel5.setLocation(0, 420);

 jPanel7.setSize(new Dimension(315, 50));
 jPanel7.setPreferredSize(new Dimension(315, 50));
 jPanel7.setLocation(0, 480);
        

 }

 public void loadAllKeyword() {
 try {

 DefaultMutableTreeNode keyModel = cModel.getKeywordModel(language);
 if (cModel.getGroupName() != null) {
 comboModel = new DefaultComboBoxModel((Vector) cModel.getGroupName());
 jComboBox1.removeAll();
 jComboBox1.setModel(comboModel);
 }

 if (keyModel == null) {
 return;
 }
 jPanel3.removeAll();
 jPanel3.setLayout(new BorderLayout());
 jPanel3.add(new KeywordMaster().getContent(keyModel, this));
 jPanel3.revalidate();

 if (cModel.getGroupTree() == null) {
 return;
 }

 selectkeyTree = new SelectedKeywordTree();
 //            jPanel6.removeAll();
 jPanel6.setLayout(new BorderLayout());
 JScrollPane jp1 = selectkeyTree.getSelectedKeyword(cModel.getGroupTree());

 //JScrollPane jp2 = selectkeyTree.getSelectedKeyword(cModel.getGroupTree());
 //          jPanel6.add(jp1);
 //          jPanel6.add(jp1);
 //            jPanel6.add(selectkeyTree.getSelectedKeyword(cModel.getGroupTree()));
 jPanel6.revalidate();
 //            jScrollPane1.revalidate();

 } catch (Exception er) {
 System.err.println(er);
 }
 }

 public void updateKeywordOnDrag(String keyword, String group) {
 try {
 if (keyword == null || group == null) {
 return;
 }
 if (group.indexOf("(") > 0) {
 group = group.substring(0, group.indexOf("("));
 }
 group = group.trim();
 boolean updated = cModel.updateKeywordList(language, keyword, group);
 if (updated) {
 loadAllKeyword();
 }
 } catch (Exception er) {
 System.out.println("Error " + er.getMessage());
 }
 }

 /**
 * This method is called from within the constructor to initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is always
 * regenerated by the Form Editor.
 */
/* //  @SuppressWarnings("unchecked")
 // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
 private void initComponents() {

 jPanel4 = new javax.swing.JPanel();
 jTabbedPane2 = new javax.swing.JTabbedPane();
 jPanel1 = new javax.swing.JPanel();
 jPanel6 = new javax.swing.JPanel();
 jPanel7 = new javax.swing.JPanel();
 jButton3 = new javax.swing.JButton();
 jButton4 = new javax.swing.JButton();
 jPanel2 = new javax.swing.JPanel();
 jPanel3 = new javax.swing.JPanel();
 jPanel5 = new javax.swing.JPanel();
 jLabel1 = new javax.swing.JLabel();
 jTextField1 = new javax.swing.JTextField();
 jLabel2 = new javax.swing.JLabel();
 jComboBox1 = new javax.swing.JComboBox();
 jButton1 = new javax.swing.JButton();
 jButton2 = new javax.swing.JButton();

 javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
 jPanel4.setLayout(jPanel4Layout);
 jPanel4Layout.setHorizontalGroup(
 jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addGap(0, 100, Short.MAX_VALUE)
 );
 jPanel4Layout.setVerticalGroup(
 jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addGap(0, 100, Short.MAX_VALUE)
 );

 setBackground(new java.awt.Color(255, 255, 255));

 jPanel1.setBackground(new java.awt.Color(255, 255, 255));

 jPanel6.setBackground(new java.awt.Color(51, 255, 255));

 javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
 jPanel6.setLayout(jPanel6Layout);
 jPanel6Layout.setHorizontalGroup(
 jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addGap(0, 446, Short.MAX_VALUE)
 );
 jPanel6Layout.setVerticalGroup(
 jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addGap(0, 320, Short.MAX_VALUE)
 );

 jPanel7.setBackground(new java.awt.Color(255, 255, 255));

 jButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
 jButton3.setText("Submit");
 jButton3.addActionListener(new java.awt.event.ActionListener() {
 public void actionPerformed(java.awt.event.ActionEvent evt) {
 jButton3ActionPerformed(evt);
 }
 });

 jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
 jButton4.setText("Close");
 jButton4.addActionListener(new java.awt.event.ActionListener() {
 public void actionPerformed(java.awt.event.ActionEvent evt) {
 jButton4ActionPerformed(evt);
 }
 });

 javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
 jPanel7.setLayout(jPanel7Layout);
 jPanel7Layout.setHorizontalGroup(
 jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addGroup(jPanel7Layout.createSequentialGroup()
 .addContainerGap()
 .addComponent(jButton3)
 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
 .addComponent(jButton4)
 .addContainerGap(270, Short.MAX_VALUE))
 );
 jPanel7Layout.setVerticalGroup(
 jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
 .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
 .addComponent(jButton3)
 .addComponent(jButton4))
 .addContainerGap())
 );

 javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
 jPanel1.setLayout(jPanel1Layout);
 jPanel1Layout.setHorizontalGroup(
 jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addGroup(jPanel1Layout.createSequentialGroup()
 .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
 .addGroup(jPanel1Layout.createSequentialGroup()
 .addContainerGap()
 .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
 .addContainerGap(20, Short.MAX_VALUE))
 );
 jPanel1Layout.setVerticalGroup(
 jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addGroup(jPanel1Layout.createSequentialGroup()
 .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
 .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
 .addContainerGap(27, Short.MAX_VALUE))
 );

 jTabbedPane2.addTab("Keyword selection", jPanel1);

 jPanel2.setBackground(new java.awt.Color(255, 255, 255));

 jPanel3.setBackground(new java.awt.Color(153, 255, 255));
 jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

 javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
 jPanel3.setLayout(jPanel3Layout);
 jPanel3Layout.setHorizontalGroup(
 jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addGap(0, 440, Short.MAX_VALUE)
 );
 jPanel3Layout.setVerticalGroup(
 jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addGap(0, 249, Short.MAX_VALUE)
 );

 jPanel5.setBackground(new java.awt.Color(255, 255, 255));

 jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
 jLabel1.setText("Keyword : ");

 jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

 jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
 jLabel2.setText("Group     :");

 jComboBox1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

 jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
 jButton1.setText("Add");
 jButton1.addActionListener(new java.awt.event.ActionListener() {
 public void actionPerformed(java.awt.event.ActionEvent evt) {
 jButton1ActionPerformed(evt);
 }
 });

 jButton2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
 jButton2.setText("Reset");
 jButton2.addActionListener(new java.awt.event.ActionListener() {
 public void actionPerformed(java.awt.event.ActionEvent evt) {
 jButton2ActionPerformed(evt);
 }
 });

 javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
 jPanel5.setLayout(jPanel5Layout);
 jPanel5Layout.setHorizontalGroup(
 jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addGroup(jPanel5Layout.createSequentialGroup()
 .addContainerGap()
 .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
 .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
 .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
 .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
 .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
 .addComponent(jButton2)
 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
 .addComponent(jButton1))
 .addComponent(jTextField1)
 .addComponent(jComboBox1, 0, 207, Short.MAX_VALUE))
 .addContainerGap(162, Short.MAX_VALUE))
 );
 jPanel5Layout.setVerticalGroup(
 jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addGroup(jPanel5Layout.createSequentialGroup()
 .addContainerGap()
 .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
 .addComponent(jLabel1)
 .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
 .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
 .addComponent(jLabel2)
 .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
 .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
 .addComponent(jButton1)
 .addComponent(jButton2))
 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
 );

 javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
 jPanel2.setLayout(jPanel2Layout);
 jPanel2Layout.setHorizontalGroup(
 jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addGroup(jPanel2Layout.createSequentialGroup()
 .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
 .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
 .addContainerGap(14, Short.MAX_VALUE))
 );
 jPanel2Layout.setVerticalGroup(
 jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addGroup(jPanel2Layout.createSequentialGroup()
 .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
 .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
 .addContainerGap(14, Short.MAX_VALUE))
 );

 jTabbedPane2.addTab("Add or edit keyword", jPanel2);

 javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
 this.setLayout(layout);
 layout.setHorizontalGroup(
 layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
 );
 layout.setVerticalGroup(
 layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
 .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
 );
 }// </editor-fold>                        

 private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
 // TODO add your handling code here:
 try {
 String newKeyword = jTextField1.getText().trim();
 if (newKeyword.length() < 1) {
 return;
 }
 boolean inserted = cModel.addNewKeyword(language, newKeyword, jComboBox1.getSelectedItem().toString());
 if (!inserted) {
 javax.swing.JOptionPane.showMessageDialog(this, "Unable to insert");
 }
 jTextField1.setText("");
 jComboBox1.setSelectedIndex(0);
 loadAllKeyword();
 } catch (Exception er) {
 System.out.println("Error " + er.getMessage());
 }
 }                                        

 private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
 // TODO add your handling code here:
 jTextField1.setText("");
 jComboBox1.setSelectedIndex(0);
 }                                        

 private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
 // TODO add your handling code here:

 if (keyFrame.mFrame.rKwsStatus) {
 javax.swing.JOptionPane.showMessageDialog(keyFrame.mFrame, "Process already running... ");
 keyFrame.KwsDisplay.dispose();
 return;
 }

 try {
 if (selectkeyTree.rootNode == null) {
 return;
 }

 DefaultMutableTreeNode rootNode = selectkeyTree.rootNode;
 selectedKeywordList = new ArrayList();
 for (int i = 0; i < rootNode.getChildCount(); i++) {
 for (int j = 0; j < rootNode.getChildAt(i).getChildCount(); j++) {
 for (int k = 0; k < rootNode.getChildAt(i).getChildAt(j).getChildCount(); k++) {
 DefaultMutableTreeNode node = (DefaultMutableTreeNode) (rootNode.getChildAt(i)).getChildAt(j).getChildAt(k);
 CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();
 if (cbn.isSelected()) {
 String value = rootNode.getChildAt(i).getChildAt(j).getChildAt(k).toString();
 if (value == null) {
 continue;
 }
 if (value.indexOf("[") < 1 || value.indexOf("]") < 1) {
 continue;
 }
 if ((value.indexOf("[") + 1) < (value.length() - 1)) {
 value = value.substring(value.indexOf("[") + 1, value.indexOf("]"));
 if (value.indexOf("/") < 1) {
 continue;
 }
 value = value.substring(0, value.indexOf("/"));
 selectedKeywordList.add(value);
 }
 }
 }
 }
 }
 if (selectedKeywordList.isEmpty()) {
 javax.swing.JOptionPane.showMessageDialog(this, "select keywords");
 return;
 }

 displayprogressbar.start();
 kwsProcess.start();
 jButton3.setEnabled(false);
 DisplayObjects.setIsKwsSelected(false);
 DisplayObjects.setKwsSelectedObj(null);
 keyFrame.KwsDisplay.dispose();
 } catch (Exception er) {
 System.out.println("Error " + er.getMessage());
 }

 }                                        

 private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
 // TODO add your handling code here:
 try {
 DisplayObjects.setIsKwsSelected(false);
 DisplayObjects.setKwsSelectedObj(null);
 keyFrame.KwsDisplay.dispose();
 } catch (Exception er) {
 System.out.println("Error " + er.getMessage());
 }
 }                                        
 // Variables declaration - do not modify                     
 private javax.swing.JButton jButton1;
 private javax.swing.JButton jButton2;
 public javax.swing.JButton jButton3;
 private javax.swing.JButton jButton4;
 private javax.swing.JComboBox jComboBox1;
 private javax.swing.JLabel jLabel1;
 private javax.swing.JLabel jLabel2;
 private javax.swing.JPanel jPanel1;
 private javax.swing.JPanel jPanel2;
 private javax.swing.JPanel jPanel3;
 private javax.swing.JPanel jPanel4;
 private javax.swing.JPanel jPanel5;
 private javax.swing.JPanel jPanel6;
 private javax.swing.JPanel jPanel7;
 private javax.swing.JTabbedPane jTabbedPane2;
 private javax.swing.JTextField jTextField1;
 // End of variables declaration                   

 public class DoKWSProcess implements Runnable {

 private Thread thread = null;

 public void start() {
 thread = new Thread(this);
 thread.setName("ProgressBar");
 thread.start();
 }

 public void stop() {
 if (thread != null) {
 thread.interrupt();
 }
 thread = null;
 }

 public void run() {
 try {
 try {
 keyFrame.mFrame.rKwsStatus = true;
 keyFrame.processSelectedKeywordSpotting(selectedKeywordList);
 } catch (Exception er) {
 System.err.println(er);
 }
 if (displayprogressbar != null) {
 displayprogressbar.stop();
 }
 } catch (Exception er) {
 System.err.println(er);
 }
 keyFrame.mFrame.rKwsStatus = false;

 if (keyFrame.mFrame.rKwsStatus || keyFrame.mFrame.rPdsStatus || keyFrame.mFrame.rPhoneStatus || keyFrame.mFrame.rSidStatus) {
 keyFrame.mFrame.jProgressToolBar.setVisible(true);
 } else {
 keyFrame.mFrame.jProgressToolBar.setVisible(false);
 }

 kwsProcess = null;
 }
 }

 public class DisplayProgressBar implements Runnable {

 private Thread thread = null;

 public void start() {
 thread = new Thread(this);
 thread.setName("ProgressBar");
 thread.start();
 }

 public void stop() {
 if (thread != null) {
 thread.interrupt();
 }
 thread = null;
 }

 public void run() {
 try {
 keyFrame.mFrame.jProgressToolBar.setVisible(true);
 keyFrame.mFrame.kwsButtonStatus(true);
 while (thread != null) {
 for (int i = 0; i <= 100; i = i + 3) {
 if (thread == null) {
 break;
 }

 //Progressively increment variable i
 keyFrame.mFrame.jkwsProgressBar.setValue(i);
 keyFrame.mFrame.jkwsProgressBar.repaint(); //Refresh graphics
 try {
 Thread.sleep(50);
 } //Sleep 50 milliseconds
 catch (InterruptedException err) {
 }
 }
 }
 keyFrame.mFrame.kwsButtonStatus(false);
 keyFrame.mFrame.jkwsProgressBar.setValue(0);
 keyFrame.mFrame.jkwsProgressBar.repaint();
 } catch (Exception er) {
 System.err.println(er);
 }
 displayprogressbar = null;

 }
 }

 public static void stopKwsProcess() {

 if (kwsProcess != null) {
 kwsProcess.stop();
 if (!(kwsProcess.thread.isAlive())) {
 displayprogressbar.stop();
 displayprogressbar = null;
 kwsProcess = null;
 }
 }
 if (kwsProcess == null && displayprogressbar != null) {
 displayprogressbar.stop();
 displayprogressbar = null;
 }

 }
 }
 */
