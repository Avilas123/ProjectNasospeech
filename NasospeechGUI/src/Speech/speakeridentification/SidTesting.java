 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.speakeridentification;

import Speech.common.DatePicker;
import Speech.common.StreamConverter;
import Speech.gui.MainFrame;
import Speech.rmi.VrasiClientSID;
import Speech.staticobjects.DisplayObjects;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tatapower SED
 *
 */
public class SidTesting extends javax.swing.JPanel {

    private MainFrame mFrame;
    private JInternalFrame internalFrame;
    private Ivector ivector;
    private ArrayList<JTable> tableObjects = null;
    private static DisplaySIDProgressBar displaysidpro;
    private static DoSIDProcess sidProcess;

    /**
     * Creates new form SidTesting
     */
    public SidTesting(MainFrame mFrame, String fileName, JInternalFrame internalFrame, Ivector ivector) {
        initComponents();
        this.ivector = ivector;
        this.mFrame = mFrame;
        this.internalFrame = internalFrame;
        this.setSize(new Dimension(800, 660));

        tableObjects = new ArrayList<>();
        addObjects();
        setDataModels();
        try {
            File audioFile = new File(mFrame.getUserID() + ".wav");
            if (audioFile.exists()) {
                audioFile.delete();

            }
            StreamConverter.byteTowavefile(mFrame.pWave.streamBytes.getCurrent(), mFrame.pWave.audioInputStream, mFrame.getUserID() + ".wav");


        } catch (Exception er) {
            java.util.logging.Logger.getLogger(SidTesting.class.getName()).log(Level.SEVERE, null, er);
        }

    }

    private void addObjects() {
        try {
            tableObjects.add(table_Group);
            tableObjects.add(table_Gender);
            tableObjects.add(table_Language);
            tableObjects.add(table_Nationality);
            tableObjects.add(table_Channel);
            tableObjects.add(table_Network);
        } catch (Exception er) {
            java.util.logging.Logger.getLogger(SidTesting.class.getName()).log(Level.SEVERE, null, er);
        }
    }

    private void setTableProperties() {
        try {
            for (JTable jTableobj : tableObjects) {
                //jTableobj = jTable7;
                jTableobj.setBorder(BorderFactory.createEmptyBorder());
                jTableobj.setShowGrid(false);
                jTableobj.setShowHorizontalLines(false);
                jTableobj.setShowVerticalLines(false);
                jTableobj.setTableHeader(null);
                jTableobj.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                jTableobj.getColumnModel().getColumn(0).setMinWidth(0);
                jTableobj.getColumnModel().getColumn(0).setMaxWidth(0);
                for (int i = 2; i <= jTableobj.getColumnCount(); i++) {
                    if (i % 2 == 0) {
                        jTableobj.getColumnModel().getColumn(i - 1).setMinWidth(20);
                        jTableobj.getColumnModel().getColumn(i - 1).setMaxWidth(20);
                    } else {
                        jTableobj.getColumnModel().getColumn(i - 1).setMinWidth(120);
                        jTableobj.getColumnModel().getColumn(i - 1).setMaxWidth(120);
                    }
                }
                jTableobj.revalidate();
                jTableobj.repaint();
            }

            jSlider1.setMajorTickSpacing(1);
            jSlider1.setMinorTickSpacing(1);
            jSlider1.setPaintTicks(true);
            jSlider1.setPaintLabels(true);
            jSlider1.setValue(1);
            jSlider1.revalidate();
            jSlider1.repaint();
        } catch (Exception er) {
            java.util.logging.Logger.getLogger(SidTesting.class.getName()).log(Level.SEVERE, null, er);
        }
    }

    private void setDataModels() {
        try {
            SpeakerMaster sMaster = new SpeakerMaster(mFrame.getConn());
            String[] fieldArr = sMaster.getFields("nationality");
            if (fieldArr == null) {
                return;
            }
            table_Nationality.setModel(new SidTableModel(getColumnName(fieldArr.length), getRowDate(fieldArr)));

            fieldArr = sMaster.getFields("gender");
            if (fieldArr == null) {
                return;
            }
            table_Gender.setModel(new SidTableModel(getColumnName(fieldArr.length), getRowDate(fieldArr)));

            fieldArr = sMaster.getFields("language");
            if (fieldArr == null) {
                return;
            }
            table_Language.setModel(new SidTableModel(getColumnName(fieldArr.length), getRowDate(fieldArr)));

            fieldArr = sMaster.getFields("channel");
            if (fieldArr == null) {
                return;
            }
            table_Channel.setModel(new SidTableModel(getColumnName(fieldArr.length), getRowDate(fieldArr)));

            fieldArr = sMaster.getFields("network");
            if (fieldArr == null) {
                return;
            }
            table_Network.setModel(new SidTableModel(getColumnName(fieldArr.length), getRowDate(fieldArr)));

            fieldArr = sMaster.getFields("Group");
            if (fieldArr == null) {
                return;
            }

            table_Group.setModel(new SidTableModel(getColumnName(fieldArr.length), getRowDate(fieldArr)));

            setTableProperties();
        } catch (Exception er) {
            java.util.logging.Logger.getLogger(SidTesting.class.getName()).log(Level.SEVERE, null, er);
        }
    }

    private Object[][] getRowDate(String[] rows) {
        Object[][] rowDate = null;
        try {

            if (rows == null) {
                return null;
            }
            rowDate = new Object[1][((rows.length) * 2) + 1];
            int rowValue = 0;
            rowDate[0][rowValue++] = "";
            for (String str : rows) {
                rowDate[0][rowValue++] = false;
                rowDate[0][rowValue++] = str;
            }
        } catch (Exception er) {
            java.util.logging.Logger.getLogger(SidTesting.class.getName()).log(Level.SEVERE, null, er);
        }
        return rowDate;
    }

    private String[] getColumnName(int rows) {
        String[] rowDate = null;
        try {
            rowDate = new String[rows * 2 + 1];
            for (int col = 0; col < rows * 2 + 1; col++) {
                rowDate[col] = "";
            }
        } catch (Exception er) {
            java.util.logging.Logger.getLogger(SidTesting.class.getName()).log(Level.SEVERE, null, er);
        }
        return rowDate;
    }

    private void jComboChecked(JTable tableObj, JCheckBox checkObj) {
        try {
            if (tableObj.getColumnCount() < 2) {
                checkObj.setSelected(false);
                return;
            }

            for (int i = 1; i < tableObj.getColumnCount(); i++) {
                if (i % 2 != 0) {
                    tableObj.getModel().setValueAt(checkObj.isSelected(), 0, i);
                }
            }
            if (checkObj.isSelected()) {
                checkObj.setText("Clear All  ");
            } else {
                checkObj.setText("Select All");
            }
            tableObj.revalidate();
            tableObj.repaint();
        } catch (Exception er) {
            java.util.logging.Logger.getLogger(SidTesting.class.getName()).log(Level.SEVERE, null, er);
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

        jPanel10 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_Language = new javax.swing.JTable();
        cb_Language = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        table_Group = new javax.swing.JTable();
        cb_Group = new javax.swing.JCheckBox();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        table_Nationality = new javax.swing.JTable();
        cb_Nationality = new javax.swing.JCheckBox();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        table_Gender = new javax.swing.JTable();
        cb_Gender = new javax.swing.JCheckBox();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        table_Channel = new javax.swing.JTable();
        cb_Channel = new javax.swing.JCheckBox();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        table_Network = new javax.swing.JTable();
        cb_Network = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Group", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 0, 14))); // NOI18N

        jScrollPane10.setPreferredSize(new java.awt.Dimension(454, 73));

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jTable9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable9MouseReleased(evt);
            }
        });
        jScrollPane10.setViewportView(jTable9);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        setBackground(java.awt.Color.white);

        jPanel4.setBackground(java.awt.Color.white);
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Language", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 0, 14), new java.awt.Color(255, 0, 6))); // NOI18N

        jScrollPane4.setPreferredSize(new java.awt.Dimension(454, 73));

        table_Language.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        table_Language.setName("language"); // NOI18N
        table_Language.setPreferredSize(new java.awt.Dimension(1200, 18));
        table_Language.setSelectionBackground(java.awt.Color.white);
        table_Language.setSelectionForeground(java.awt.Color.black);
        table_Language.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                table_LanguageMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(table_Language);

        cb_Language.setBackground(java.awt.Color.white);
        cb_Language.setText("Select All");
        cb_Language.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_LanguageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(cb_Language)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(1, 1, 1))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(cb_Language)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(java.awt.Color.white);
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Group", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 0, 14), new java.awt.Color(255, 0, 6))); // NOI18N

        jScrollPane8.setBackground(java.awt.Color.white);
        jScrollPane8.setPreferredSize(new java.awt.Dimension(454, 73));

        table_Group.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        table_Group.setGridColor(java.awt.Color.white);
        table_Group.setName("groupname"); // NOI18N
        table_Group.setPreferredSize(new java.awt.Dimension(1500, 18));
        table_Group.setSelectionBackground(java.awt.Color.white);
        table_Group.setSelectionForeground(java.awt.Color.black);
        table_Group.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                table_GroupMouseReleased(evt);
            }
        });
        jScrollPane8.setViewportView(table_Group);

        cb_Group.setBackground(java.awt.Color.white);
        cb_Group.setText("Select All");
        cb_Group.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_GroupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(cb_Group)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(1, 1, 1))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(cb_Group)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(java.awt.Color.white);
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nationality", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 0, 14), new java.awt.Color(255, 0, 6))); // NOI18N

        jScrollPane12.setPreferredSize(new java.awt.Dimension(454, 73));

        table_Nationality.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        table_Nationality.setMinimumSize(new java.awt.Dimension(800, 18));
        table_Nationality.setName("nationality"); // NOI18N
        table_Nationality.setPreferredSize(new java.awt.Dimension(800, 18));
        table_Nationality.setSelectionBackground(java.awt.Color.white);
        table_Nationality.setSelectionForeground(java.awt.Color.black);
        table_Nationality.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                table_NationalityMouseReleased(evt);
            }
        });
        jScrollPane12.setViewportView(table_Nationality);

        cb_Nationality.setBackground(java.awt.Color.white);
        cb_Nationality.setText("Select All");
        cb_Nationality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_NationalityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(cb_Nationality)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(1, 1, 1))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(cb_Nationality)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(java.awt.Color.white);
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gender", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 0, 14), new java.awt.Color(255, 0, 6))); // NOI18N

        jScrollPane15.setBackground(java.awt.Color.white);
        jScrollPane15.setPreferredSize(new java.awt.Dimension(454, 50));

        table_Gender.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        table_Gender.setGridColor(java.awt.Color.white);
        table_Gender.setName("gender"); // NOI18N
        table_Gender.setPreferredSize(new java.awt.Dimension(1000, 18));
        table_Gender.setSelectionBackground(java.awt.Color.white);
        table_Gender.setSelectionForeground(java.awt.Color.black);
        table_Gender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                table_GenderMouseReleased(evt);
            }
        });
        jScrollPane15.setViewportView(table_Gender);

        cb_Gender.setBackground(java.awt.Color.white);
        cb_Gender.setText("Select All");
        cb_Gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_GenderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(cb_Gender)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(1, 1, 1))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(cb_Gender)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(java.awt.Color.white);
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Channel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 0, 14), new java.awt.Color(255, 0, 6))); // NOI18N

        jScrollPane16.setPreferredSize(new java.awt.Dimension(454, 73));

        table_Channel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        table_Channel.setGridColor(java.awt.Color.white);
        table_Channel.setName("channel"); // NOI18N
        table_Channel.setPreferredSize(new java.awt.Dimension(1000, 18));
        table_Channel.setSelectionBackground(java.awt.Color.white);
        table_Channel.setSelectionForeground(java.awt.Color.black);
        table_Channel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                table_ChannelMouseReleased(evt);
            }
        });
        jScrollPane16.setViewportView(table_Channel);

        cb_Channel.setBackground(java.awt.Color.white);
        cb_Channel.setText("Select All");
        cb_Channel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_ChannelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(cb_Channel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(1, 1, 1))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(cb_Channel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBackground(java.awt.Color.white);
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Network", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 0, 14), new java.awt.Color(255, 0, 6))); // NOI18N

        jScrollPane17.setPreferredSize(new java.awt.Dimension(454, 73));

        table_Network.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        table_Network.setGridColor(java.awt.Color.white);
        table_Network.setName("network"); // NOI18N
        table_Network.setPreferredSize(new java.awt.Dimension(1000, 18));
        table_Network.setSelectionBackground(java.awt.Color.white);
        table_Network.setSelectionForeground(java.awt.Color.black);
        table_Network.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                table_NetworkMouseReleased(evt);
            }
        });
        jScrollPane17.setViewportView(table_Network);

        cb_Network.setBackground(java.awt.Color.white);
        cb_Network.setText("Select All");
        cb_Network.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_NetworkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addComponent(cb_Network)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(1, 1, 1))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(cb_Network)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Date & Threshold", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Abyssinica SIL", 0, 14), new java.awt.Color(255, 0, 6))); // NOI18N

        jLabel2.setBackground(java.awt.Color.white);
        jLabel2.setText("Date  ");

        txtDate.setEditable(false);
        txtDate.setBackground(java.awt.Color.white);
        txtDate.setName("date"); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/calender-icon.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        jLabel4.setBackground(java.awt.Color.white);
        jLabel4.setText("Threshold");

        jSlider1.setBackground(java.awt.Color.white);
        jSlider1.setMaximum(10);
        jSlider1.setMinimum(1);
        jSlider1.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addComponent(jLabel4))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Clear All");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Submit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Select All");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void table_LanguageMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_LanguageMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_table_LanguageMouseReleased

    private void jTable9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable9MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable9MouseReleased

    private void table_GroupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_GroupMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_table_GroupMouseReleased

    private void table_NationalityMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_NationalityMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_table_NationalityMouseReleased

    private void table_GenderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_GenderMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_table_GenderMouseReleased

    private void table_ChannelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_ChannelMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_table_ChannelMouseReleased

    private void table_NetworkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_NetworkMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_table_NetworkMouseReleased

    private void cb_GroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_GroupActionPerformed
        // TODO add your handling code here:       
        jComboChecked(table_Group, cb_Group);
    }//GEN-LAST:event_cb_GroupActionPerformed

    private void cb_GenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_GenderActionPerformed
        // TODO add your handling code here:      
        jComboChecked(table_Gender, cb_Gender);

    }//GEN-LAST:event_cb_GenderActionPerformed

    private void cb_LanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_LanguageActionPerformed
        // TODO add your handling code here:
        jComboChecked(table_Language, cb_Language);
    }//GEN-LAST:event_cb_LanguageActionPerformed

    private void cb_NationalityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_NationalityActionPerformed
        // TODO add your handling code here:
        jComboChecked(table_Nationality, cb_Nationality);
    }//GEN-LAST:event_cb_NationalityActionPerformed

    private void cb_ChannelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_ChannelActionPerformed
        // TODO add your handling code here:

        jComboChecked(table_Channel, cb_Channel);
    }//GEN-LAST:event_cb_ChannelActionPerformed

    private void cb_NetworkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_NetworkActionPerformed
        // TODO add your handling code here:      

        jComboChecked(table_Network, cb_Network);
    }//GEN-LAST:event_cb_NetworkActionPerformed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        // TODO add your handling code here:
        try {
            Point point = new Point(this.getLocationOnScreen().x + 195, this.getLocationOnScreen().y + 280);
            txtDate.setText(new DatePicker(point).setPickedDate());
        } catch (Exception er) {
            java.util.logging.Logger.getLogger(SidTesting.class.getName()).log(Level.SEVERE, null, er);
        }
    }//GEN-LAST:event_jLabel3MousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            double throsold = (jSlider1.getValue() / 10.0) + 1.0;
            String query = generateQuery();
            if (query.length() < 1) {
                javax.swing.JOptionPane.showMessageDialog(this, "No record found");
                return;
            }
            System.out.println(query);
            ArrayList userRecord = new SpeakerMaster(mFrame.getConn()).getAllUserRecords(query);
            if (userRecord == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "No record found");
                return;
            }
            if (userRecord.size() < 1) {
                javax.swing.JOptionPane.showMessageDialog(this, "No record found");
                return;
            }

            try {
                if (mFrame.rSidStatus) {
                    javax.swing.JOptionPane.showMessageDialog(mFrame, "Process already running... ");
                    return;
                }
                displaysidpro = new DisplaySIDProgressBar();
                sidProcess = new DoSIDProcess(userRecord, Double.toString(throsold));
                displaysidpro.start();
                sidProcess.start();
            } catch (Exception er) {
                System.err.println(er);
            }
        } catch (Exception er) {
            java.util.logging.Logger.getLogger(SidTesting.class.getName()).log(Level.SEVERE, null, er);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            cb_Channel.setSelected(false);
            jComboChecked(table_Channel, cb_Channel);
            cb_Gender.setSelected(false);
            jComboChecked(table_Gender, cb_Gender);
            cb_Group.setSelected(false);
            jComboChecked(table_Group, cb_Group);
            cb_Language.setSelected(false);
            jComboChecked(table_Language, cb_Language);
            cb_Nationality.setSelected(false);
            jComboChecked(table_Nationality, cb_Nationality);
            cb_Network.setSelected(false);
            jComboChecked(table_Network, cb_Network);
            txtDate.setText("");
            jSlider1.setValue(1);
        } catch (Exception er) {
            java.util.logging.Logger.getLogger(SidTesting.class.getName()).log(Level.SEVERE, null, er);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        internalFrame.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            cb_Channel.setSelected(true);
            jComboChecked(table_Channel, cb_Channel);
            cb_Gender.setSelected(true);
            jComboChecked(table_Gender, cb_Gender);
            cb_Group.setSelected(true);
            jComboChecked(table_Group, cb_Group);
            cb_Language.setSelected(true);
            jComboChecked(table_Language, cb_Language);
            cb_Nationality.setSelected(true);
            jComboChecked(table_Nationality, cb_Nationality);
            cb_Network.setSelected(true);
            jComboChecked(table_Network, cb_Network);
        } catch (Exception er) {
            java.util.logging.Logger.getLogger(SidTesting.class.getName()).log(Level.SEVERE, null, er);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private String generateQuery() {

        String query = "";
        try {
            for (JTable tableObj : tableObjects) {

                for (int col = 1; col < tableObj.getColumnCount();) {
          //          if (tableObj.getModel().getValueAt(0, col) == true) {
                        if (tableObj.getModel().getValueAt(0, ++col) != null) {
                            if (query.length() == 0) {
                                query = tableObj.getName() + " = '" + tableObj.getModel().getValueAt(0, col).toString() + "'";
                            } else {
                                query = query + " or " + tableObj.getName() + " = '" + tableObj.getModel().getValueAt(0, col).toString() + "'";
                            }
                        }
                    //}
                    ++col;
                }

            }
            if ((txtDate.getText().trim()).length() > 0) {
                if (query.length() == 0) {
                    query = txtDate.getName() + " = '" + txtDate.getText() + "'";
                } else {
                    query = query + " or " + txtDate.getName() + " = '" + txtDate.getText() + "'";
                }
            }

            if ((query = query.trim()).length() > 0) {
                query = "select distinct(filename) from sidmaster where " + query;
            }
        } catch (Exception er) {
            java.util.logging.Logger.getLogger(SidTesting.class.getName()).log(Level.SEVERE, null, er);
        }
        return query;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cb_Channel;
    private javax.swing.JCheckBox cb_Gender;
    private javax.swing.JCheckBox cb_Group;
    private javax.swing.JCheckBox cb_Language;
    private javax.swing.JCheckBox cb_Nationality;
    private javax.swing.JCheckBox cb_Network;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTable jTable9;
    private javax.swing.JTable table_Channel;
    private javax.swing.JTable table_Gender;
    private javax.swing.JTable table_Group;
    private javax.swing.JTable table_Language;
    private javax.swing.JTable table_Nationality;
    private javax.swing.JTable table_Network;
    private javax.swing.JTextField txtDate;
    // End of variables declaration//GEN-END:variables

    class SidTableModel extends AbstractTableModel {

        private Object[][] rowData;
        private String[] columnNames;

        public SidTableModel(String[] columnNames, Object[][] rowData) {
            this.columnNames = columnNames;
            this.rowData = rowData;
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public String getColumnName(int column) {
            return columnNames[column];
        }

        public int getRowCount() {
            return rowData.length;
        }

        public Object getValueAt(int row, int column) {
            return rowData[row][column];
        }

        public Class getColumnClass(int column) {
            return (getValueAt(0, column).getClass());
        }

        public void setValueAt(Object value, int row, int column) {
            rowData[row][column] = value;
        }

        public boolean isCellEditable(int row, int column) {
            return (column != 0);
        }
    }

    public class DoSIDProcess implements Runnable {

        private Thread thread = null;
        private ArrayList userList = null;
        private String comThreshold = null;

        public DoSIDProcess(ArrayList userList, String comThreshold) {
            this.userList = userList;
            this.comThreshold = comThreshold;
        }

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
                    long start = System.currentTimeMillis();
                    DisplayObjects.setIsSidTest(false);
                    DisplayObjects.setSidTestObj(null);
                    internalFrame.dispose();
                    mFrame.rSidStatus = true;

                    ivector.clearAll();

                    boolean serverFind = true;


                    VrasiClientSID vClient = new VrasiClientSID();
                    if (!vClient.setRemoteConnection(mFrame.getUserID())) {
                        javax.swing.JOptionPane.showMessageDialog(mFrame, "Server Could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                        serverFind = false;
                    }
                    ArrayList result = vClient.processSIDTest(mFrame.getUserID() + ".wav", userList, comThreshold);


                    if (result == null) {
                        javax.swing.JOptionPane.showMessageDialog(mFrame, "Server Could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                        serverFind = false;
                    }
                    //-----
                    double elapsed = (System.currentTimeMillis() - start) / 1000.0;
                    System.out.println("Execution Time: " + elapsed);
                    String exTime = String.valueOf(elapsed);
                    mFrame.pWave.setExeutionTime("Exec Time: " + exTime + " Sec");
                    //-----


                    ivector.setInformation(result);


                } catch (Exception er) {
                    System.out.println(er.getMessage());

                }


                if (displaysidpro != null) {
                    displaysidpro.stop();
                }
            } catch (Exception er) {
                System.err.println(er);
            }
            mFrame.rSidStatus = false;

            if (mFrame.rKwsStatus || mFrame.rPdsStatus || mFrame.rPhoneStatus || mFrame.rSidStatus) {
                mFrame.jProgressToolBar.setVisible(true);
            } else {
                mFrame.jProgressToolBar.setVisible(false);
            }

            sidProcess = null;
        }
    }

    public class DisplaySIDProgressBar implements Runnable {

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
                mFrame.jProgressToolBar.setVisible(true);
                mFrame.sidButtonStatus(true);
                while (thread != null) {
                    for (int i = 0; i <= 100; i = i + 3) {
                        if (thread == null) {
                            break;
                        }

                        //Progressively increment variable i
                        mFrame.jsidProgressBar.setValue(i);
                        mFrame.jsidProgressBar.repaint(); //Refresh graphics
                        try {
                            Thread.sleep(50);
                        } //Sleep 50 milliseconds
                        catch (InterruptedException err) {
                        }
                    }
                }
                mFrame.sidButtonStatus(false);
                mFrame.jsidProgressBar.setValue(0);
                mFrame.jsidProgressBar.repaint();
            } catch (Exception er) {
                System.err.println(er);
            }
            displaysidpro = null;

        }
    }

    public static void stopPhoneProcess() {
        try {
            if (sidProcess != null) {
                sidProcess.stop();
                if (!(sidProcess.thread.isAlive())) {
                    displaysidpro.stop();
                    displaysidpro = null;
                    sidProcess = null;
                }
            }
            if (sidProcess == null && displaysidpro != null) {
                displaysidpro.stop();
                displaysidpro = null;
            }
        } catch (Exception er) {
            java.util.logging.Logger.getLogger(SidTesting.class.getName()).log(Level.SEVERE, null, er);
        }

    }

    public static void main(String args[]) {
        // new SidTesting();
    }
}
