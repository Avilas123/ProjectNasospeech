/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.annotations;

import Speech.common.PlotwaveCommon;
import Speech.gui.MainFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.DefaultCaret;

public class Annotation extends javax.swing.JPanel {

    /**
     *
     * @author Tatapower SED
     *
     */
    /**
     * Creates new form Annotation
     */
    private MainFrame mframe;
    private int ann_startPos = 0, ann_endPos = 0;
    private String oldValue = "";
    private boolean editable = false;
    private int annotation_message_id = 0;
    private String original_message;
    private JEditorPane jEditorPane1;
    private JScrollPane jScrollPane3;
    private String ann_fileName;
    private PlotwaveCommon pwCommon;

    public Annotation(MainFrame mframe, int startPos, int endPos, String fileName, Object pwCommon) {

        this.mframe = mframe;
        this.ann_startPos = startPos;
        this.ann_endPos = endPos;
        this.ann_fileName = fileName;
        this.pwCommon = (PlotwaveCommon) pwCommon;
        jEditorPane1 = new JEditorPane();
        jScrollPane3 = new JScrollPane(jEditorPane1);
        initComponents();
        screenProperties();
        displayScreen(jComboBox2.getSelectedIndex());

    }

    private void screenProperties() {
        this.setSize(new Dimension(290, 490));
        jEditorPane1.setSize(new Dimension(290, 300));
        jEditorPane1.setPreferredSize(new Dimension(290, 300));
        jScrollPane3.setPreferredSize(new Dimension(290, 300));
        jPanel1.setLocation(0, 285);
        jPanel1.setSize(new Dimension(290, 160));
        jPanel1.setPreferredSize(new Dimension(290, 160));
        jTextField1.setLineWrap(true);
        jPanel3.setPreferredSize(new Dimension(290, 270));
        jPanel3.removeAll();
        jPanel3.setLayout(new BorderLayout());
        jPanel3.add(jScrollPane3);
        jPanel3.revalidate();
        jPanel3.repaint();

    }

    private void displayScreen(int orderby) {
        try {

            String display = "";
            display = new DisplayAnnotation().dispaly(ann_fileName, ann_startPos, ann_endPos, mframe.getConn(), mframe.getUserID(), orderby);
            jEditorPane1.setContentType("text/html");
            jEditorPane1.setText(display);
            jEditorPane1.setEditable(false);
            DefaultCaret caret = (DefaultCaret) jEditorPane1.getCaret();
            caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
            HyperlinkListener hyperlinkListener = new ActivatedHyperlinkListener();
            jEditorPane1.addHyperlinkListener(hyperlinkListener);
            jEditorPane1.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent me) {
                    //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseMoved(MouseEvent me) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    oldValue = "";

                }
            });


        } catch (Exception er) {
            Logger.getLogger(Annotation.class.getName()).log(Level.SEVERE, null, er);
        }
    }

    class ActivatedHyperlinkListener implements HyperlinkListener {

        public ActivatedHyperlinkListener() {
            oldValue = "";

        }

        public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {
            HyperlinkEvent.EventType type = hyperlinkEvent.getEventType();
            final URL url = hyperlinkEvent.getURL();
            if (type == HyperlinkEvent.EventType.ACTIVATED) {
                System.out.println("URL: " + url);
                try {
                    String userSubmission = hyperlinkEvent.getURL().toString();
                    if (userSubmission == null) {
                        return;
                    }

                    if (userSubmission.equals(oldValue)) {
                        return;
                    }
                    if (userSubmission.indexOf("~") < 0) {
                        return;
                    }

                    String urlParts[] = userSubmission.split("~");

                    if (urlParts.length < 5) {
                        return;
                    }
                    try {
                        annotation_message_id = Integer.parseInt(urlParts[2]);
                    } catch (NumberFormatException er) {
                        System.err.println(er);
                    }
                    original_message = urlParts[3];
                    if (urlParts[1].startsWith("remove")) {

                        int option = javax.swing.JOptionPane.showConfirmDialog(mframe, "Are you sure you want to delete this message", "Delete", javax.swing.JOptionPane.YES_NO_OPTION);
                        if (option == javax.swing.JOptionPane.YES_OPTION) {
                            new UpdateAnnotation(mframe.getConn()).deleteAnnotation(annotation_message_id);
                            displayScreen(jComboBox2.getSelectedIndex());
                            oldValue = userSubmission;

                        } else {
                            oldValue = userSubmission;
                        }

                    } else {
                        jTextField1.setText(urlParts[3]);
                        String level = urlParts[4];
                        System.out.println(level);
                        jComboBox1.getModel().setSelectedItem(level);
                        editable = true;



                    }
                } catch (Exception er) {
                    Logger.getLogger(Annotation.class.getName()).log(Level.SEVERE, null, er);
                }

            }
        }
    }

    private void displayScreennew() {
        try {
            String display = new DisplayAnnotation().dispaly(ann_fileName, ann_startPos, ann_endPos, mframe.getConn(), mframe.getUserID(), jComboBox2.getSelectedIndex());
            jEditorPane1.setContentType("text/html");
            jEditorPane1.setText(display);
        } catch (Exception er) {
            Logger.getLogger(Annotation.class.getName()).log(Level.SEVERE, null, er);
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

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextField1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();

        setToolTipText("Annotation");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add/Edit", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(5, 4, 238)));

        jComboBox1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Regular", "Low", "High" }));

        jTextField1.setColumns(20);
        jTextField1.setRows(5);
        jTextField1.setPreferredSize(new java.awt.Dimension(290, 50));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTextField1);

        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBox1, 0, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sort by", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(6, 6, 236)));

        jComboBox2.setFont(new java.awt.Font("DejaVu Sans", 0, 11)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Priority", "Rank", "Date & Time", "User" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                insertEditAnnotation();
            }
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                editable = false;
                jTextField1.setText("");
            }
        } catch (Exception er) {
            System.err.println(er);
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        editable = false;
        jTextField1.setText("");
        jComboBox1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void insertEditAnnotation() {
        try {
            if (!editable) {

                if (ann_startPos == 0 || ann_endPos == 0 || mframe == null) {
                    return;
                }
                if ((jTextField1.getText().trim()).length() < 1) {
                    return;
                }

                boolean insert = new InsertAnnotation(mframe.getConn()).insertNewAnnotate(ann_startPos, ann_endPos, mframe.getUserID(), ann_fileName, jTextField1.getText().trim(), jComboBox1.getSelectedItem().toString().trim());
                jTextField1.setText("");
                if (!insert) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Not able to insert", "Insert error", javax.swing.JOptionPane.ERROR_MESSAGE);
                } else {
                    displayScreen(jComboBox2.getSelectedIndex());
                    pwCommon.reorderWavePanel();
                }
            } else {

                if ((jTextField1.getText().trim()).length() < 1) {
                    return;
                }


                if (((original_message.trim().toLowerCase()).endsWith(jTextField1.getText().trim().toLowerCase()))) {
                    return;
                }

                boolean update = new UpdateAnnotation(mframe.getConn()).updateAnnotation(annotation_message_id, jTextField1.getText().trim(), original_message, mframe.getUserID(), jComboBox1.getSelectedItem().toString().trim());
                jTextField1.setText("");
                if (!update) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Not able to insert", "Insert error", javax.swing.JOptionPane.ERROR_MESSAGE);
                } else {
                    displayScreen(jComboBox2.getSelectedIndex());
                }
                editable = false;


            }
        } catch (Exception er) {
            System.err.println(er);
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        insertEditAnnotation();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            editable = false;
            jTextField1.setText("");
            jComboBox1.setSelectedIndex(1);
        }
    }//GEN-LAST:event_formKeyReleased

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        // displayScreen("");
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            displayScreen(jComboBox2.getSelectedIndex());
            // do something with object
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextField1;
    // End of variables declaration//GEN-END:variables
}
