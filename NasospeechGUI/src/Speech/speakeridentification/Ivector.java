/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Ivector.java
 *
 * Created on Jan 8, 2013, 3:59:46 PM
 */
package Speech.speakeridentification;

import Speech.gui.MainFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import Speech.sqlconnection.MysqlConnect;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author Tatapower SED
 *
 */
public class Ivector extends javax.swing.JPanel {

    private Connection connect = null;
    private Statement statement = null;
    public JPopupMenu menu;
    private IvectorRightClickEvent rightClick;
    public MainFrame mframe;
    public ListofSpeakers lSpeakers;
    private Ivector ivector = null;

    /**
     * Creates new form Ivector
     */
    public Ivector(MainFrame mfrFrame, ListofSpeakers lspeakers) {
        try {
            initComponents();
            this.mframe = mfrFrame;
            this.lSpeakers = lspeakers;
            menu = new JPopupMenu("Popup");
            rightClick = new IvectorRightClickEvent(this);
            screenProperty();
            this.connect = (Connection) new MysqlConnect().getDBConnection();
            ivector = this;

            // jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/sid2.png")));
            clearAll();
        } catch (Exception ex) {
            Logger.getLogger(Ivector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void screenProperty() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = (int) tk.getScreenSize().getWidth();
        int ySize = 260;
        xSize = xSize - 300;
        jPanel1.setSize(new Dimension((xSize), ySize));
        jPanel1.setPreferredSize(new Dimension((xSize), ySize));
        jPanel2.setSize(new Dimension((xSize), ySize));
        jPanel2.setPreferredSize(new Dimension((xSize), ySize));
        this.setSize(new Dimension((xSize), 300));
        this.setPreferredSize(new Dimension((xSize), ySize));

        addButtonMenu();
    }

    private void addButtonMenu() {
        JMenuBar buttonMenu = new JMenuBar();
        try {
            JMenu sort = new JMenu("Train  ");
            sort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/train.png")));
            sort.setFont(new Font("Courier New", Font.PLAIN, 12));
            sort.setBackground(Color.white);
            sort.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    if (mframe.pWave.audioInputStream != null) {
                        mframe.createSIDTrainFrame(mframe.pWave.fileName, ivector);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            buttonMenu.add(sort);

            sort = new JMenu("Test ");
            sort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/testing.png")));
            sort.setFont(new Font("Courier New", Font.PLAIN, 12));
            sort.setBackground(Color.white);
            sort.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    if (mframe.pWave.audioInputStream != null) {
                        mframe.createSIDTestFrame(mframe.pWave.fileName, ivector);
                    }

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });

            buttonMenu.add(sort);

            sort = new JMenu("Settings ");
            sort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/testing.png")));
            sort.setFont(new Font("Courier New", Font.PLAIN, 12));
            sort.setBackground(Color.white);
            sort.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    JDialog dialog = new JDialog();
                    dialog.add(new SidSettings(dialog));
                    dialog.setAlwaysOnTop(false);
                    dialog.setModal(true);
                    dialog.setResizable(false);
                    dialog.setTitle("settings");
                    dialog.setLayout(new BorderLayout());
                    JPanel pane = (JPanel) dialog.getContentPane();
                    pane.setPreferredSize(new Dimension(390, 350));
                    dialog.setLocation(400, 200);
                    dialog.pack();
                    dialog.setVisible(true);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });



            buttonMenu.add(sort);



        } catch (Exception ex) {
            Logger.getLogger(ListofSpeakers.class.getName()).log(Level.SEVERE, null, ex);
        }
        jToolBar1.add(buttonMenu);
    }

    public void setInformation(ArrayList<String> speackList) {

        if (speackList == null || speackList.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "No Record found");
            return;
        }
        if (speackList.size() == 1) {
            if (speackList.get(0).trim().toLowerCase().startsWith("unknown")) {
                javax.swing.JOptionPane.showMessageDialog(this, "No Record found");
                return;
            }
        }
        ArrayList<JPanel> parentPanel = loadPanelObjects();
        SpeakerMaster spkMaster = new SpeakerMaster(connect);
        for (int i = 0; i < speackList.size(); i++) {

            ArrayList<JLabel> jlableObjList = loadLableObjects(parentPanel.get(i));
            String spkeakerInfo = speackList.get(i);
            if (spkeakerInfo == null || spkeakerInfo.isEmpty()) {
                continue;
            }
            String spkNames = null, spkValues = null;
            StringTokenizer strTok = new StringTokenizer(spkeakerInfo, "~");
            if (strTok.hasMoreElements()) {
                spkNames = strTok.nextElement().toString();
            }
            if (strTok.hasMoreElements()) {
                spkValues = strTok.nextElement().toString();
            }
            if (spkNames == null || spkValues == null || spkNames.isEmpty()) {
                continue;
            }
            spkNames = (spkNames.replace(".x", "")).trim();

            String[][] spkAllInfo = spkMaster.getAllSpkInfo(spkNames);

            for (JLabel jable : jlableObjList) {

                if (jable.getName().trim().startsWith("score")) {
                    jable.setText(": " + spkValues);
                    continue;
                }

                if (jable.getName().trim().startsWith("verifyimage")) {
                    jable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/mans.PNG")));
                    continue;
                }

                if (jable.getName().trim().startsWith("image")) {
                    for (String[] spkObj : spkAllInfo) {

                        if (spkObj[0].trim().toLowerCase().startsWith("gender")) {
                            if (spkObj[1] != null) {
                                ImageIcon imgIcon = ((loadIcons().get(spkObj[1].trim().toLowerCase()) != null) ? loadIcons().get(spkObj[1].trim().toLowerCase()) : loadIcons().get("default"));
                                jable.setIcon(imgIcon);
                            } else {
                                jable.setIcon(loadIcons().get("default"));
                            }
                        }
                    }

                    continue;
                }

                for (String[] spkObj : spkAllInfo) {

                    if (jable.getName().trim().startsWith(spkObj[0].trim())) {
                        jable.setText(": " + spkObj[1]);
                    }
                }
            }
        }

        //sidName.setText(userName);
        // sidGender.setText("");




    }

    private ArrayList<JLabel> loadLableObjects(JPanel panel) {
        ArrayList<JLabel> jlablePool = new ArrayList<>();
        Component[] comList = panel.getComponents();
        for (Component com : comList) {

            if (com instanceof JLabel) {
                JLabel jlablecom = (JLabel) com;
                if (jlablecom.getName() != null && !(jlablecom.getName().trim().equals(""))) {
                    jlablePool.add((JLabel) com);
                }
            }
        }
        return jlablePool;
    }

    private ArrayList<JPanel> loadPanelObjects() {
        ArrayList<JPanel> jpanelPool = new ArrayList<>();
        jpanelPool.add(jpanle_sp1);
        jpanelPool.add(jpanel_sp2);
        jpanelPool.add(jpanel_sp3);
        return jpanelPool;
    }

    private HashMap<String, ImageIcon> loadIcons() {
        HashMap<String, ImageIcon> image = new HashMap<>();
        image.put("default", new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/Icon-users.png")));
        image.put("male", new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/expoM.png")));
        image.put("female", new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/expoFM.png")));
        return image;
    }

    public void clearAll() {
        for (int i = 0; i < 3; i++) {
            ArrayList<JLabel> jlableObjList = loadLableObjects(loadPanelObjects().get(i));
            for (JLabel jable : jlableObjList) {
                if (jable.getName().trim().startsWith("image") || jable.getName().trim().startsWith("verifyimage")) {
                    jable.setIcon(null);
                    continue;
                }
                jable.setText(": ");
            }
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
        jToolBar1 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        jpanle_sp1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_sp1_name = new javax.swing.JLabel();
        txt_sp1_score = new javax.swing.JLabel();
        txt_sp1_gender = new javax.swing.JLabel();
        txt_sp1_language = new javax.swing.JLabel();
        txt_sp1_group = new javax.swing.JLabel();
        txt_sp1_network = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jpanel_sp2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txt_sp2_name = new javax.swing.JLabel();
        txt_sp2_score = new javax.swing.JLabel();
        txt_sp2_gender = new javax.swing.JLabel();
        txt_sp2_language = new javax.swing.JLabel();
        txt_sp2_group = new javax.swing.JLabel();
        txt_sp2_network = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jpanel_sp3 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txt_sp3_name = new javax.swing.JLabel();
        txt_sp3_score = new javax.swing.JLabel();
        txt_sp3_gender = new javax.swing.JLabel();
        txt_sp3_language = new javax.swing.JLabel();
        txt_sp3_group = new javax.swing.JLabel();
        txt_sp3_network = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jToolBar1.setRollover(true);
        jToolBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jToolBar1MousePressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jpanle_sp1.setBackground(java.awt.Color.white);
        jpanle_sp1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/sid2.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel1.setName("image"); // NOI18N

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel4.setText("Name / ID");

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel5.setText("Score       ");

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel6.setText("Gender     ");

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel7.setText("Language");

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel8.setText("Group");

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel9.setText("Network");

        txt_sp1_name.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp1_name.setForeground(java.awt.Color.blue);
        txt_sp1_name.setName("filename"); // NOI18N

        txt_sp1_score.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp1_score.setName("score"); // NOI18N

        txt_sp1_gender.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp1_gender.setName("gender"); // NOI18N

        txt_sp1_language.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp1_language.setName("language"); // NOI18N

        txt_sp1_group.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp1_group.setName("groupname"); // NOI18N

        txt_sp1_network.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp1_network.setName("network"); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/mans.PNG"))); // NOI18N
        jLabel2.setToolTipText("Verify");
        jLabel2.setName("verifyimage"); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jpanle_sp1Layout = new javax.swing.GroupLayout(jpanle_sp1);
        jpanle_sp1.setLayout(jpanle_sp1Layout);
        jpanle_sp1Layout.setHorizontalGroup(
            jpanle_sp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanle_sp1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanle_sp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanle_sp1Layout.createSequentialGroup()
                        .addGroup(jpanle_sp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanle_sp1Layout.createSequentialGroup()
                                .addGroup(jpanle_sp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpanle_sp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_sp1_language, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_sp1_group, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_sp1_network, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)))
                            .addGroup(jpanle_sp1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_sp1_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpanle_sp1Layout.createSequentialGroup()
                                .addGroup(jpanle_sp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpanle_sp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_sp1_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_sp1_score, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanle_sp1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        jpanle_sp1Layout.setVerticalGroup(
            jpanle_sp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanle_sp1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanle_sp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanle_sp1Layout.createSequentialGroup()
                        .addGroup(jpanle_sp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_sp1_name))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanle_sp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_sp1_score))
                        .addGap(10, 10, 10)
                        .addGroup(jpanle_sp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_sp1_gender))))
                .addGap(10, 10, 10)
                .addGroup(jpanle_sp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_sp1_language))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanle_sp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_sp1_group))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanle_sp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_sp1_network))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        jpanel_sp2.setBackground(java.awt.Color.white);
        jpanel_sp2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/sid3.png"))); // NOI18N
        jLabel22.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel22.setName("image"); // NOI18N

        jLabel23.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel23.setText("Name / ID");

        jLabel24.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel24.setText("Score       ");

        jLabel25.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel25.setText("Gender     ");

        jLabel26.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel26.setText("Language");

        jLabel27.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel27.setText("Group");

        jLabel28.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel28.setText("Network");

        txt_sp2_name.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp2_name.setForeground(java.awt.Color.blue);
        txt_sp2_name.setName("filename"); // NOI18N

        txt_sp2_score.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp2_score.setName("score"); // NOI18N

        txt_sp2_gender.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp2_gender.setName("gender"); // NOI18N

        txt_sp2_language.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp2_language.setName("language"); // NOI18N

        txt_sp2_group.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp2_group.setName("groupname"); // NOI18N

        txt_sp2_network.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp2_network.setName("network"); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/mans.PNG"))); // NOI18N
        jLabel10.setToolTipText("Verify");
        jLabel10.setName("verifyimage"); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel10MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jpanel_sp2Layout = new javax.swing.GroupLayout(jpanel_sp2);
        jpanel_sp2.setLayout(jpanel_sp2Layout);
        jpanel_sp2Layout.setHorizontalGroup(
            jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_sp2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel_sp2Layout.createSequentialGroup()
                        .addGroup(jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanel_sp2Layout.createSequentialGroup()
                                .addGroup(jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_sp2_language, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_sp2_group, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_sp2_network, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)))
                            .addGroup(jpanel_sp2Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_sp2_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpanel_sp2Layout.createSequentialGroup()
                                .addGroup(jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_sp2_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_sp2_score, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel_sp2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jpanel_sp2Layout.setVerticalGroup(
            jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_sp2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanel_sp2Layout.createSequentialGroup()
                        .addGroup(jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txt_sp2_name))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txt_sp2_score))
                        .addGap(10, 10, 10)
                        .addGroup(jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txt_sp2_gender))))
                .addGroup(jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel_sp2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(txt_sp2_language))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txt_sp2_group))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanel_sp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txt_sp2_network))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(jpanel_sp2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)))
                .addContainerGap())
        );

        jpanel_sp3.setBackground(java.awt.Color.white);
        jpanel_sp3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/sid4.png"))); // NOI18N
        jLabel29.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel29.setName("image"); // NOI18N

        jLabel30.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel30.setText("Name / ID");

        jLabel31.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel31.setText("Score       ");

        jLabel32.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel32.setText("Gender     ");

        jLabel33.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel33.setText("Language");

        jLabel34.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel34.setText("Group");

        jLabel35.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel35.setText("Network");

        txt_sp3_name.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp3_name.setForeground(java.awt.Color.blue);
        txt_sp3_name.setName("filename"); // NOI18N

        txt_sp3_score.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp3_score.setName("score"); // NOI18N

        txt_sp3_gender.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp3_gender.setName("gender"); // NOI18N

        txt_sp3_language.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp3_language.setName("language"); // NOI18N

        txt_sp3_group.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp3_group.setName("groupname"); // NOI18N

        txt_sp3_network.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        txt_sp3_network.setName("network"); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/mans.PNG"))); // NOI18N
        jLabel11.setToolTipText("Verify");
        jLabel11.setName("verifyimage"); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jpanel_sp3Layout = new javax.swing.GroupLayout(jpanel_sp3);
        jpanel_sp3.setLayout(jpanel_sp3Layout);
        jpanel_sp3Layout.setHorizontalGroup(
            jpanel_sp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_sp3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel_sp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel_sp3Layout.createSequentialGroup()
                        .addGroup(jpanel_sp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanel_sp3Layout.createSequentialGroup()
                                .addGroup(jpanel_sp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpanel_sp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_sp3_language, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_sp3_group, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_sp3_network, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)))
                            .addGroup(jpanel_sp3Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_sp3_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpanel_sp3Layout.createSequentialGroup()
                                .addGroup(jpanel_sp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpanel_sp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_sp3_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_sp3_score, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel_sp3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11)))
                .addContainerGap())
        );
        jpanel_sp3Layout.setVerticalGroup(
            jpanel_sp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_sp3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel_sp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanel_sp3Layout.createSequentialGroup()
                        .addGroup(jpanel_sp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txt_sp3_name))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanel_sp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txt_sp3_score))
                        .addGap(10, 10, 10)
                        .addGroup(jpanel_sp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(txt_sp3_gender))))
                .addGap(10, 10, 10)
                .addGroup(jpanel_sp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txt_sp3_language))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanel_sp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txt_sp3_group))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanel_sp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txt_sp3_network))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jpanle_sp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpanel_sp2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpanel_sp3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpanle_sp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpanel_sp2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpanel_sp3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formMousePressed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == MouseEvent.BUTTON3) {
            if (mframe.pWave.streamBytes.getCurrent() != null) {
                rightClick.addMenuItems();
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_jPanel2MouseClicked

    private void speakerVerification(String selectedfile) {

        selectedfile = selectedfile.replace(":", "");
        selectedfile = selectedfile.trim();

        String newFileName = mframe.pWave.fileName;
        if (new SpeakerMaster(connect).getInsertFileInfo(selectedfile, newFileName)) {
            javax.swing.JOptionPane.showMessageDialog(mframe, "File already available");
            return;
        }
        if (newFileName == null || newFileName.isEmpty()) {
            return;
        }

        mframe.createSIDVerificationFrame(selectedfile, newFileName, ivector);





        //clearAll();
    }

    private void loadOldDetails() {
    }

    private void jToolBar1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToolBar1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToolBar1MousePressed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        // TODO add your handling code here:
        speakerVerification(txt_sp1_name.getText());
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MousePressed
        // TODO add your handling code here:
        speakerVerification(txt_sp2_name.getText());
    }//GEN-LAST:event_jLabel10MousePressed

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MousePressed
        // TODO add your handling code here:
        speakerVerification(txt_sp3_name.getText());
    }//GEN-LAST:event_jLabel11MousePressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel jpanel_sp2;
    private javax.swing.JPanel jpanel_sp3;
    private javax.swing.JPanel jpanle_sp1;
    private javax.swing.JLabel txt_sp1_gender;
    private javax.swing.JLabel txt_sp1_group;
    private javax.swing.JLabel txt_sp1_language;
    private javax.swing.JLabel txt_sp1_name;
    private javax.swing.JLabel txt_sp1_network;
    private javax.swing.JLabel txt_sp1_score;
    private javax.swing.JLabel txt_sp2_gender;
    private javax.swing.JLabel txt_sp2_group;
    private javax.swing.JLabel txt_sp2_language;
    private javax.swing.JLabel txt_sp2_name;
    private javax.swing.JLabel txt_sp2_network;
    private javax.swing.JLabel txt_sp2_score;
    private javax.swing.JLabel txt_sp3_gender;
    private javax.swing.JLabel txt_sp3_group;
    private javax.swing.JLabel txt_sp3_language;
    private javax.swing.JLabel txt_sp3_name;
    private javax.swing.JLabel txt_sp3_network;
    private javax.swing.JLabel txt_sp3_score;
    // End of variables declaration//GEN-END:variables

    public static void main(String args[]) {
        System.out.println("\n" + "BUILD STOPPED (total time: 37 minutes 34 seconds)");
    }
}
