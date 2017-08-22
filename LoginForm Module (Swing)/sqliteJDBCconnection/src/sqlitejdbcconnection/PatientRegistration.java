/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlitejdbcconnection;

import java.awt.Color;
import static java.awt.Color.blue;
import static java.awt.Color.red;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

/**
 *
 * @author IITG
 * 
 */


public class PatientRegistration extends javax.swing.JFrame {

    /**
     * Creates new form RegisterForm
     */
    
    
    Border bred = BorderFactory.createLineBorder(Color.RED,1);
    Border bblue = BorderFactory.createLineBorder(Color.BLUE,1);
    Border bgrey = BorderFactory.createLineBorder(Color.GRAY,1);
    
    Connection conn = null;
    private PreparedStatement pst;
    private PreparedStatement pst1;
    private PreparedStatement pst2;
    public PatientRegistration() {
        initComponents();
        conn = SqliteJDBCconnection.connect();
        System.out.println("connection successfull");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pidtxt = new javax.swing.JTextField();
        fnametxt = new javax.swing.JTextField();
        mnametxt = new javax.swing.JTextField();
        fdrnametxt = new javax.swing.JTextField();
        phnumtxt = new javax.swing.JTextField();
        gendercbox = new javax.swing.JComboBox<>();
        ntrtxt = new javax.swing.JTextField();
        fvtxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabelntr = new javax.swing.JLabel();
        addtxt = new javax.swing.JTextArea();
        jLabelpid = new javax.swing.JLabel();
        jLabelmname = new javax.swing.JLabel();
        jLabelfname = new javax.swing.JLabel();
        jLabellname = new javax.swing.JLabel();
        jLabelfdrname = new javax.swing.JLabel();
        jLabelphnum = new javax.swing.JLabel();
        jLabeladd = new javax.swing.JLabel();
        jLabelgender = new javax.swing.JLabel();
        jLabelfv = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        lnametxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Register");
        setBackground(new java.awt.Color(246, 246, 246));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Patient ID*");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("First Name*");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Middle Name*");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Nature of Disorder");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Folder Name*");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Contact Number*");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Address*");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Gender*");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("First Visit*");

        pidtxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pidtxtMouseClicked(evt);
            }
        });
        pidtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pidtxtActionPerformed(evt);
            }
        });

        fnametxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fnametxtMouseClicked(evt);
            }
        });
        fnametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnametxtActionPerformed(evt);
            }
        });

        mnametxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnametxtMouseClicked(evt);
            }
        });

        fdrnametxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fdrnametxtMouseClicked(evt);
            }
        });
        fdrnametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fdrnametxtActionPerformed(evt);
            }
        });

        phnumtxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                phnumtxtMouseClicked(evt);
            }
        });

        gendercbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Others" }));
        gendercbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gendercboxMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gendercboxMouseEntered(evt);
            }
        });
        gendercbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gendercboxActionPerformed(evt);
            }
        });

        ntrtxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ntrtxtMouseClicked(evt);
            }
        });

        fvtxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fvtxtMouseClicked(evt);
            }
        });
        fvtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fvtxtActionPerformed(evt);
            }
        });

        jButton1.setText("Register");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setPreferredSize(new java.awt.Dimension(90, 28));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelntr.setForeground(new java.awt.Color(244, 0, 0));

        addtxt.setColumns(20);
        addtxt.setRows(5);
        addtxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addtxtMouseClicked(evt);
            }
        });

        jLabelpid.setForeground(new java.awt.Color(250, 0, 0));

        jLabelmname.setForeground(new java.awt.Color(252, 0, 0));

        jLabelfname.setForeground(new java.awt.Color(253, 0, 0));

        jLabellname.setForeground(new java.awt.Color(252, 0, 0));

        jLabelfdrname.setForeground(new java.awt.Color(255, 0, 0));

        jLabelphnum.setForeground(new java.awt.Color(252, 0, 0));

        jLabeladd.setForeground(new java.awt.Color(255, 0, 0));

        jLabelgender.setForeground(new java.awt.Color(252, 0, 0));

        jLabelfv.setForeground(new java.awt.Color(255, 0, 0));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Create patient account");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("(Fields marked with * are mandatory)");

        jButton2.setText("Reset");
        jButton2.setPreferredSize(new java.awt.Dimension(90, 28));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Last Name*");

        lnametxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lnametxtMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(146, 146, 146)
                                        .addComponent(jLabel8)
                                        .addGap(96, 96, 96)
                                        .addComponent(jLabel6))
                                    .addComponent(jLabel15)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pidtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(91, 91, 91)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(fnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(mnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelpid, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelfname, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelmname, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(lnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel11))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabellname, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabelgender, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(gendercbox, javax.swing.GroupLayout.Alignment.LEADING, 0, 175, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(phnumtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ntrtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fvtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fdrnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelfdrname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabeladd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addtxt, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(195, 195, 195)
                                        .addComponent(jLabelphnum, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(16, 16, 16)
                                .addComponent(jLabelntr, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelfv, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pidtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelpid, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelfname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabellname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(8, 8, 8)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8)
                                .addComponent(jLabel11))
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gendercbox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phnumtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ntrtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fvtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelmname, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelntr, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelgender, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelphnum, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addComponent(jLabelfv, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fdrnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabelfdrname, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel9)
                .addGap(19, 19, 19)
                .addComponent(addtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabeladd, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pidtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pidtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pidtxtActionPerformed

    private void gendercboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gendercboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gendercboxActionPerformed

    private void fnametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnametxtActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        if(pidtxt.getText().equals("")){
            pidtxt.setBorder(bred);
            jLabelpid.setText("You can't leave this empty! ");
        } 
        else if(fnametxt.getText().equals("")) {
            fnametxt.setBorder(bred);
            jLabelfname.setText("You can't leave this empty!");
            
        }
        
        else if(fdrnametxt.getText().equals("")){
        fdrnametxt.setBorder(bred);
        jLabelfdrname.setText("You can't leave this empty!");
        }
        else if(phnumtxt.getText().equals("")){
            phnumtxt.setBorder(bred);
            jLabelphnum.setText("You can't leave this empty!");
        
        }
        else if(addtxt.getText().equals("")){
            addtxt.setBorder(bred);
            jLabeladd.setText("You can't leave this empty!");
        
        }
        else if(gendercbox.getSelectedItem().equals("")){
            gendercbox.setBorder(bred);
            jLabelgender.setText("You can't leave this empty!");
            
        }
        else if(ntrtxt.getText().equals("")){
            ntrtxt.setBorder(bred);
            jLabelfv.setText("You can't leave this empty!");
        
        }
        else if(fvtxt.getText().equals("")){
            jLabelfv.setText("You can't leave this empty!");
        
        }
        else{
        
        try{
           // conn = SqliteJDBCconnection.connect();
        
        
        String query1 = "INSERT INTO Patient_Register_Table(Pid,FName,MName,LName,Ph_Num,Address,Gender,Ntr_of_Disorder,First_Visit,Folder_id)values(?,?,?,?,?,?,?,?,?,?)";
        pst1 = conn.prepareStatement(query1);
        pst1.setString(1,pidtxt.getText());
        pst1.setString(2,fnametxt.getText());
        pst1.setString(3,mnametxt.getText());
        pst1.setString(4,lnametxt.getText());
        pst1.setString(5,phnumtxt.getText());
        pst1.setString(6,addtxt.getText());
        pst1.setString(7, gendercbox.getSelectedItem().toString());
        pst1.setString(8,ntrtxt.getText());
        pst1.setString(9,fvtxt.getText());
        pst1.setString(10,fdrnametxt.getText());
        
        
        pst1.execute();
        
        JOptionPane.showMessageDialog(null,"Registered Successfully");
        this.dispose();
        new LoginForm().setVisible(true);
            
        }
        catch (SQLException e){
        e.printStackTrace();
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void fdrnametxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fdrnametxtMouseClicked
        // TODO add your handling code here:
        fdrnametxt.setBorder(bblue);
        jLabelfdrname.setText("");
     if(fvtxt.getText().equals("")){
            fvtxt.setBorder(bred);
            jLabelfv.setText("You can't leave this empty! ");
        } else {
            fvtxt.setBorder(bgrey);
            
        }
        
    }//GEN-LAST:event_fdrnametxtMouseClicked

    private void fnametxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fnametxtMouseClicked
        // TODO add your handling code here:
       // Border border = BorderFactory.createLineBorder(Color.RED,1);
        fnametxt.setBorder(bblue);
        jLabelfname.setText("");
        if(pidtxt.getText().equals("")){
            pidtxt.setBorder(bred);
            jLabelpid.setText("You can't leave this empty! ");
        } else {
            pidtxt.setBorder(bgrey);
            
        }
    }//GEN-LAST:event_fnametxtMouseClicked

    private void pidtxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pidtxtMouseClicked
        // TODO add your handling code here:
        pidtxt.setBorder(bblue);
        fnametxt.setBorder(bgrey);
        mnametxt.setBorder(bgrey);
        jLabelpid.setText("");
    }//GEN-LAST:event_pidtxtMouseClicked

    private void mnametxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnametxtMouseClicked
        // TODO add your handling code here:
        mnametxt.setBorder(bblue);
        fnametxt.setBorder(bgrey);
        lnametxt.setBorder(bgrey);
        jLabelmname.setText("");
        if(fnametxt.getText().equals("")){
            fnametxt.setBorder(bred);
            jLabelfname.setText("You can't leave this empty! ");
        } else {
            fnametxt.setBorder(bgrey);
            
        }
    }//GEN-LAST:event_mnametxtMouseClicked

    private void fdrnametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fdrnametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fdrnametxtActionPerformed

    private void phnumtxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phnumtxtMouseClicked
        // TODO add your handling code here:
        
    
        phnumtxt.setBorder(bblue);
        fvtxt.setBorder(bgrey);
        jLabelphnum.setText("");
        
        
    }//GEN-LAST:event_phnumtxtMouseClicked

    private void addtxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addtxtMouseClicked
        // TODO add your handling code here:
        jLabeladd.setText("");
        addtxt.setBorder(bblue);
       
        if(fdrnametxt.getText().equals("")){
            fdrnametxt.setBorder(bred);
            jLabelfdrname.setText("You can't leave this empty!");
        
        }
        else{
        fdrnametxt.setBorder(bgrey);
        }
        
    }//GEN-LAST:event_addtxtMouseClicked

    private void gendercboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gendercboxMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_gendercboxMouseClicked

    private void gendercboxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gendercboxMouseEntered
        // TODO add your handling code here:
        ntrtxt.setBorder(bgrey);
        gendercbox.setBorder(null);
        addtxt.setBorder(bgrey);
        jLabelgender.setText("");
        if(lnametxt.getText().equals("")){
            lnametxt.setBorder(bred);
            jLabellname.setText("You can't leave this empty!");
        
        }
    }//GEN-LAST:event_gendercboxMouseEntered

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        pidtxt.setText("");
        fnametxt.setText("");
        mnametxt.setText("");
        lnametxt.setText("");
        fdrnametxt.setText("");
        phnumtxt.setText("");
        addtxt.setText("");
        fvtxt.setText("");
        ntrtxt.setText("");
        gendercbox.setSelectedItem("Male");
        
        pidtxt.setBorder(bgrey);
        fnametxt.setBorder(bgrey);
        mnametxt.setBorder(bgrey);
        lnametxt.setBorder(bgrey);
        fdrnametxt.setBorder(bgrey);
        phnumtxt.setBorder(bgrey);
        addtxt.setBorder(bgrey);
        fvtxt.setBorder(bgrey);
        ntrtxt.setBorder(bgrey);
        
        jLabelpid.setText("");
        jLabelmname.setText("");
        jLabelfname.setText("");
        jLabellname.setText("");
        jLabelntr.setText("");
        jLabelfdrname.setText("");
        jLabelphnum.setText("");
        jLabeladd.setText("");
        jLabelfv.setText("");
        jLabelgender.setText("");
        
        
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void fvtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fvtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fvtxtActionPerformed

    private void fvtxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fvtxtMouseClicked
        // TODO add your handling code here:
        jLabelfv.setText("");
        fvtxt.setBorder(bblue);
        ntrtxt.setBorder(bgrey);
        if(ntrtxt.getText().equals("")){
            ntrtxt.setBorder(bred);
            jLabelntr.setText("You can't leave this empty!");

        }
    }//GEN-LAST:event_fvtxtMouseClicked

    private void ntrtxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ntrtxtMouseClicked
        // TODO add your handling code here:
        addtxt.setBorder(bgrey);
        jLabelntr.setText("");
        ntrtxt.setBorder(bblue);
        if(phnumtxt.getText().equals("")){
            phnumtxt.setBorder(bred);
            jLabelphnum.setText("You can't leave this empty!");

        }

    }//GEN-LAST:event_ntrtxtMouseClicked

    private void lnametxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lnametxtMouseClicked
        // TODO add your handling code here:
        lnametxt.setBorder(bblue);
        jLabellname.setText("");
        if(fnametxt.getText().equals("")){
         fnametxt.setBorder(bred);
         jLabelfname.setText("You can't leave this empty!");   
        
        }
    }//GEN-LAST:event_lnametxtMouseClicked

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PatientRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientRegistration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea addtxt;
    private javax.swing.JTextField fdrnametxt;
    private javax.swing.JTextField fnametxt;
    private javax.swing.JTextField fvtxt;
    private javax.swing.JComboBox<String> gendercbox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabeladd;
    private javax.swing.JLabel jLabelfdrname;
    private javax.swing.JLabel jLabelfname;
    private javax.swing.JLabel jLabelfv;
    private javax.swing.JLabel jLabelgender;
    private javax.swing.JLabel jLabellname;
    private javax.swing.JLabel jLabelmname;
    private javax.swing.JLabel jLabelntr;
    private javax.swing.JLabel jLabelphnum;
    private javax.swing.JLabel jLabelpid;
    private javax.swing.JTextField lnametxt;
    private javax.swing.JTextField mnametxt;
    private javax.swing.JTextField ntrtxt;
    private javax.swing.JTextField phnumtxt;
    private javax.swing.JTextField pidtxt;
    // End of variables declaration//GEN-END:variables
}
