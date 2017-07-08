/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ResultKeyWordPanel.java
 *
 * Created on 4 Jan, 2013, 12:39:04 PM
 */
package Speech.speakeridentification;

import Speech.gui.InternalFrame;
import Speech.gui.MainFrame;
import Speech.rmi.VrasiClientSID;
import Speech.staticobjects.DisplayObjects;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Tatapower SED
 *
 */
public class ListofSpeakers extends javax.swing.JPanel {

    private JTree tree;
    private HashMap keyColor;
    private MainFrame mFrame;
    private SpeakerMaster speakerMas;
    private InternalFrame internalframe;
    private SIDList sidList;
    private DefaultMutableTreeNode rootTreeNode = null;
    private JPopupMenu menu;
    private JTree m_tree;
    private static DisplaySIDProgressBar displaysidpro;
    private static DoSIDProcess sidProcess;

    /**
     * Creates new form ResultKeyWordPanel
     */
    public ListofSpeakers(MainFrame mFrame, InternalFrame internalframe) {
        initComponents();
        this.mFrame = mFrame;
        this.internalframe = internalframe;
        menu = new JPopupMenu("Popup");
        speakerMas = new SpeakerMaster(mFrame.getConn());

        loadFileStruct();
        loadSorbyMenu();
    }

    public void loadFileStruct() {
        Thread thread = new Thread() {
            public void run() {
                listOfSpeakersTree();
            }
        };
        thread.start();
    }

    public void listOfSpeakersTree() {
        try {
            VrasiClientSID vrasSid = new VrasiClientSID();
            vrasSid.setRemoteConnection(mFrame.getUserID());
            rootTreeNode = vrasSid.getSidFileStructure();
            // rootTreeNode = treeNodeColor(rootTreeNode);

            treeBuilder(treeOrderBy("groupname", "filename", "Groups", rootTreeNode));
        } catch (Exception er) {
            Logger.getLogger(ListofSpeakers.class.getName()).log(Level.SEVERE, null, er);
        }
    }

    public void treeBuilder(DefaultMutableTreeNode modelNode) {

        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(new JScrollPane(m_tree = new TreeView(sortAZ(modelNode))));
        jPanel1.revalidate();
        jPanel1.repaint();

    }

    private void loadSorbyMenu() {
        JMenuBar sortBy = new JMenuBar();
        try {
            sortBy.add(addSortByMenuIteam());
            sortBy.add(new JMenu("                         "));
            JMenu refresh = new JMenu("");
            refresh.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    loadFileStruct();
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


            refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/view-refresh.png")));


            sortBy.add(refresh);
        } catch (Exception ex) {
            Logger.getLogger(ListofSpeakers.class.getName()).log(Level.SEVERE, null, ex);
        }
        jToolBar1.add(sortBy);


    }

    public void selectedSpeaker(String fileName, String groupName) {

        try {
            groupName = groupName.trim();
            fileName = fileName.replaceAll("<html><font color =\"red\">", "");
            fileName = fileName.replaceAll("<html><font color =\"green\">", "");
            fileName = fileName.replaceAll("</font></html>", "");

            if (fileName.length() > 0 && ((fileName.toLowerCase()).endsWith(".wav") || (fileName.toLowerCase()).endsWith(".mp3") || (fileName.toLowerCase()).endsWith(".wma")) && (!fileName.contains("WorkSpace"))) {

                if (mFrame.pWave.audioInputStream != null) {
                    javax.swing.JOptionPane.showMessageDialog(mFrame, "please close existing files");
                    m_tree.setSelectionRow(0);
                    return;
                }



                VrasiClientSID vClient = new VrasiClientSID();
                if (!vClient.setRemoteConnection(mFrame.getUserID())) {
                    javax.swing.JOptionPane.showMessageDialog(mFrame, "Server Could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

                    return;
                }

                boolean resWave = vClient.getSIDWavefile(fileName, groupName);
                if (!resWave) {
                    javax.swing.JOptionPane.showMessageDialog(mFrame, "Server Could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
                File resWaveFile = new File("conf/workspace/" + fileName);
                if (!resWaveFile.exists()) {
                    return;
                }
                mFrame.pWave.createAudioInputStream(resWaveFile, null, true);
                vClient = null;
            }


        } catch (Exception er) {
            System.err.println(er);
        }
    }

    private synchronized DefaultMutableTreeNode treeNodeColor(DefaultMutableTreeNode node) {
        try {
            // System.out.println("Total Elements are "+node.getChildCount());
            for (int i = 0; i < node.getChildCount(); i++) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
                if (child.isLeaf()) {
                    String nodeName = child.getUserObject().toString();
                    String mode = speakerMas.getTypeName("mode", "wavefile", nodeName);
                    if (mode == null) {
                        continue;
                    }
                    if (mode.toLowerCase().startsWith("train")) {
                        child.setUserObject("<html><font color =\"red\">" + nodeName + "</font></html>");
                    } else {
                        child.setUserObject("<html><font color =\"green\">" + nodeName + "</font></html>");
                    }

                } else {
                    treeNodeColor((DefaultMutableTreeNode) node.getChildAt(i));


                }
            }
        } catch (Exception er) {
            Logger.getLogger(ListofSpeakers.class
                    .getName()).log(Level.SEVERE, null, er);
        }
        return node;
    }

    private synchronized DefaultMutableTreeNode treeOrderBy(String fileName, String whereAs, String displayName, DefaultMutableTreeNode node) {
        try {
            HashMap<String, DefaultMutableTreeNode> nodeGroup = new HashMap<String, DefaultMutableTreeNode>();
            if (node == null) {
                return null;
            }
            ArrayList<String> groupName = speakerMas.getAllTypeNames(fileName);
            if (groupName == null) {
                groupName = new ArrayList<>();
            }
            groupName.add("Default");
            for (String gName : groupName) {
                if (gName.toLowerCase().endsWith("null")) {
                    continue;
                }
                nodeGroup.put(gName, new DefaultMutableTreeNode(gName, true));
            }

            for (int icount = 0; icount < node.getChildCount(); icount++) {
                try {

                    DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) node.getChildAt(icount);
                    if (childNode == null) {
                        continue;
                    }
                    String newGroup = null;
                    try {
                        if (childNode.getUserObject() == null) {
                            continue;
                        }

                        /*new Child */

                        ArrayList<String> childgroupNameList = speakerMas.getTypeNameAll(fileName, whereAs, childNode.getUserObject().toString());
                        if (childgroupNameList == null) {
                            childgroupNameList = new ArrayList<>();
                        }

                        childgroupNameList.add("Default");
                        HashMap<String, DefaultMutableTreeNode> childnodeGroup = new HashMap<String, DefaultMutableTreeNode>();
                        for (String childgName : childgroupNameList) {
                            if (childgName.toLowerCase().endsWith("null")) {
                                continue;
                            }
                            childnodeGroup.put(childgName, new DefaultMutableTreeNode(childNode.getUserObject().toString(), true));
                        }


                        for (int ch = 0; ch < childNode.getChildCount(); ch++) {
                            String nodeValue = ((DefaultMutableTreeNode) childNode.getChildAt(ch)).getUserObject().toString();

                            newGroup = new SpeakerMaster(mFrame.getConn()).getTypeName(fileName, whereAs, childNode.getUserObject().toString(), nodeValue);
                            String mode = speakerMas.getTypeName("mode", "filename", childNode.getUserObject().toString(), nodeValue);

                            if (mode == null) {
                                continue;
                            }
                            DefaultMutableTreeNode chChild = null;
                            if (mode.toLowerCase().startsWith("train")) {
                                chChild = new DefaultMutableTreeNode("<html><font color =\"red\">" + nodeValue + "</font></html>");

                            } else {
                                chChild = new DefaultMutableTreeNode("<html><font color =\"green\">" + nodeValue + "</font></html>");

                            }


                            if (newGroup == null) {
                                childnodeGroup.get("Default").add(chChild);
                                continue;
                            }


                            if (childnodeGroup.get(newGroup) != null) {

                                childnodeGroup.get(newGroup).add(chChild);

                            } else {
                                childnodeGroup.get("Default").add(chChild);


                            }



                        }



                        for (String childKey : childnodeGroup.keySet()) {
                            if (nodeGroup.get(childKey) == null) {
                                nodeGroup.get("Default").add(childnodeGroup.get(childKey));
                            } else {
                                nodeGroup.get(childKey).add(childnodeGroup.get(childKey));
                            }

                        }



                    } catch (Exception ex) {
                        Logger.getLogger(ListofSpeakers.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }



                } catch (Exception er) {
                    Logger.getLogger(ListofSpeakers.class
                            .getName()).log(Level.SEVERE, null, er);
                }

            }


            DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(displayName);
            for (String key : nodeGroup.keySet()) {
                DefaultMutableTreeNode nTreeNo = (DefaultMutableTreeNode) nodeGroup.get(key);
                int count = nTreeNo.getChildCount();
                String usedName = nTreeNo.getUserObject().toString();
                nTreeNo.setUserObject(usedName + " (" + count + ")");
                rootNode.add(nTreeNo);
            }
            return rootNode;


        } catch (Exception er) {
            Logger.getLogger(ListofSpeakers.class
                    .getName()).log(Level.SEVERE, null, er);
        }
        return node;

    }

    public JMenu addSortByMenuIteam() throws Exception {


        JMenu sort = new JMenu("Sort by");
        sort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/sort_down.png")));
        sort.setFont(new Font("Courier New", Font.PLAIN, 13));
        sort.setBackground(Color.white);



        JMenuItem item = new JMenuItem(" Group ");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rootTreeNode == null) {
                    return;
                }
                Thread thread = new Thread() {
                    public void run() {
                        jPanel1.removeAll();
                        jPanel1.setLayout(new BorderLayout());
                        jPanel1.add(new JScrollPane(m_tree = new TreeView(sortAZ(treeOrderBy("groupname", "filename", "Groups", rootTreeNode)))));
                        jPanel1.revalidate();
                        jPanel1.repaint();
                    }
                };
                thread.start();

            }
        });
        item.setFont(new Font("Courier New", Font.PLAIN, 15));
        item.setBackground(Color.white);
        sort.add(item);



        item = new JMenuItem(" Date ");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rootTreeNode == null) {
                    return;
                }
                Thread thread = new Thread() {
                    public void run() {
                        jPanel1.removeAll();
                        jPanel1.setLayout(new BorderLayout());
                        jPanel1.add(new JScrollPane(m_tree = new TreeView(sortAZ(treeOrderBy("date", "filename", "Date", rootTreeNode)))));
                        jPanel1.revalidate();
                        jPanel1.repaint();
                    }
                };
                thread.start();
            }
        });
        item.setFont(new Font("Courier New", Font.PLAIN, 15));
        item.setBackground(Color.white);
        sort.add(item);



        item = new JMenuItem(" Gender  ");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rootTreeNode == null) {
                    return;
                }
                Thread thread = new Thread() {
                    public void run() {
                        jPanel1.removeAll();
                        jPanel1.setLayout(new BorderLayout());
                        jPanel1.add(new JScrollPane(m_tree = new TreeView(sortAZ(treeOrderBy("gender", "filename", "Gender", rootTreeNode)))));
                        jPanel1.revalidate();
                        jPanel1.repaint();
                    }
                };
                thread.start();
            }
        });
        item.setFont(new Font("Courier New", Font.PLAIN, 15));
        item.setBackground(Color.white);
        sort.add(item);


        item = new JMenuItem(" Language  ");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rootTreeNode == null) {
                    return;
                }
                Thread thread = new Thread() {
                    public void run() {
                        jPanel1.removeAll();
                        jPanel1.setLayout(new BorderLayout());
                        jPanel1.add(new JScrollPane(m_tree = new TreeView(sortAZ(treeOrderBy("language", "filename", "Language", rootTreeNode)))));
                        jPanel1.revalidate();
                        jPanel1.repaint();
                    }
                };
                thread.start();
            }
        });
        item.setFont(new Font("Courier New", Font.PLAIN, 15));
        item.setBackground(Color.white);
        sort.add(item);


        item = new JMenuItem(" Nationalilty ");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rootTreeNode == null) {
                    return;
                }
                Thread thread = new Thread() {
                    public void run() {
                        jPanel1.removeAll();
                        jPanel1.setLayout(new BorderLayout());
                        jPanel1.add(new JScrollPane(m_tree = new TreeView(sortAZ(treeOrderBy("nationality", "filename", "Nationality", rootTreeNode)))));
                        jPanel1.revalidate();
                        jPanel1.repaint();
                    }
                };
                thread.start();
            }
        });
        item.setFont(new Font("Courier New", Font.PLAIN, 15));
        item.setBackground(Color.white);
        sort.add(item);


        item = new JMenuItem(" Channel  ");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rootTreeNode == null) {
                    return;
                }
                Thread thread = new Thread() {
                    public void run() {
                        jPanel1.removeAll();
                        jPanel1.setLayout(new BorderLayout());
                        jPanel1.add(new JScrollPane(m_tree = new TreeView(sortAZ(treeOrderBy("channel", "filename", "Chennal", rootTreeNode)))));
                        jPanel1.revalidate();
                        jPanel1.repaint();
                    }
                };
                thread.start();
            }
        });
        item.setFont(new Font("Courier New", Font.PLAIN, 15));
        item.setBackground(Color.white);
        sort.add(item);

        return sort;




    }

    public synchronized DefaultMutableTreeNode sortAZ(DefaultMutableTreeNode node) {
        try {
            //sort alfebetically
            for (int i = 0; i < node.getChildCount() - 1; i++) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
                String nt = child.getUserObject().toString();

                for (int j = i + 1; j < node.getChildCount(); j++) {
                    DefaultMutableTreeNode prevNode = (DefaultMutableTreeNode) node.getChildAt(j);
                    String np = prevNode.getUserObject().toString();

                    if (nt.compareToIgnoreCase(np) > 0) {
                        node.insert(child, j);
                        node.insert(prevNode, i);
                        child = (DefaultMutableTreeNode) node.getChildAt(i);
                        nt = child.getUserObject().toString();
                    }
                }

            }

        } catch (Exception er) {
            Logger.getLogger(ListofSpeakers.class.getName()).log(Level.SEVERE, null, er);
        }

        return node;

    }

    class TreeView extends JTree implements MouseListener {

        private JTree jtreeClassObj = null;

        public TreeView(DefaultMutableTreeNode node) {
            super(node);

            setFont(new Font("Courier New", Font.PLAIN, 13));
            addMouseListener(this);
            jtreeClassObj = this;

        }

        @Override
        public void mouseClicked(MouseEvent evt) {
            if (SwingUtilities.isRightMouseButton(evt)) {

                JPopupMenu sort = new JPopupMenu();
                sort.setBackground(Color.white);
                rightCilckMenuSingle(sort);
                sort.show(evt.getComponent(), evt.getX(), evt.getY());


            } else {
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void rightCilckMenuSingle(JPopupMenu sort) {

            boolean userDirectory = false, userFile = false;
            String tempGroup = "";
            final ArrayList<String> selectedFileList = new ArrayList<>();
            final ArrayList<String> selectedUserList = new ArrayList<>();
            try {
                TreePath[] paths = this.getSelectionPaths();

                // Iterate through all affected nodes
                for (int i = 0; i < paths.length; i++) {

                    // This node has been selected
                    if (paths[i].getLastPathComponent() == null) {
                        break;
                    }
                    DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) paths[i].getLastPathComponent();
                    int count = dmtn.getLeafCount();
                    if (count == 1 && dmtn.isLeaf()) {
                        if (paths[i].getLastPathComponent() == null || paths[i].getParentPath().getLastPathComponent() == null) {
                            return;
                        }

                        String fileName = paths[i].getLastPathComponent().toString().trim();
                        String groupName = (paths[i].getParentPath().getLastPathComponent().toString()).trim();
                        if (fileName.isEmpty() || groupName.isEmpty()) {
                            continue;
                        }
                        if (i == 0) {
                            tempGroup = groupName;
                        } else {
                            if (!(groupName.startsWith(tempGroup))) {
                                javax.swing.JOptionPane.showMessageDialog(mFrame, "select one group at a time");
                                return;
                            }
                        }
                        fileName = fileName.replaceAll("<html><font color =\"green\">", "");
                        fileName = fileName.replaceAll("<html><font color =\"red\">", "");
                        fileName = fileName.replaceAll("</font></html>", "");

                        selectedFileList.add(fileName);
                        userFile = true;


                    } else {
                        if (dmtn.getLevel() == 2) {
                            String fileName = paths[i].getLastPathComponent().toString().trim();
                            if (!fileName.isEmpty()) {
                                selectedUserList.add(fileName);
                            }
                            userDirectory = true;
                        }
                    }


                }

                if ((!userFile) && (!userDirectory)) {
                    return;
                }

                if (userFile && userDirectory) {
                    javax.swing.JOptionPane.showMessageDialog(mFrame, "Either you select file or directory");
                    return;
                }

                if (userDirectory) {

                    JMenuItem item = new JMenuItem(" Remove ");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (!selectedUserList.isEmpty()) {
                                int option = JOptionPane.showConfirmDialog(mFrame, "Are you sure you want to delete ", "Delete", JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.YES_OPTION) {
                                    removeSpkAll(selectedUserList);
                                }
                            }
                        }
                    });
                    item.setFont(new Font("Courier New", Font.PLAIN, 15));
                    item.setBackground(Color.white);
                    sort.add(item);
                }

            } catch (Exception er) {
                System.err.println(er);
            }

            final String allArgsGroup = tempGroup;


            if ((!selectedFileList.isEmpty()) && (selectedFileList.size() == 1)) {
                JMenuItem item = new JMenuItem(" Load file ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!selectedFileList.isEmpty()) {
                            for (String fileName : selectedFileList) {
                                selectedSpeaker(fileName, allArgsGroup);
                            }
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                sort.add(item);

            }
            if ((!selectedFileList.isEmpty())) {
                JMenuItem item = new JMenuItem(" Train ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (!selectedFileList.isEmpty()) {
                            reTrainSpkFile(selectedFileList, allArgsGroup);
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                sort.add(item);
            }

            if ((!selectedFileList.isEmpty()) && (selectedFileList.size() == 1)) {
                JMenuItem item = new JMenuItem(" Edit speaker data");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!selectedFileList.isEmpty()) {
                            for (String fileName : selectedFileList) {
                                mFrame.createSIDUpdateFileFrame(allArgsGroup, fileName);

                            }
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                sort.add(item);

            }
            if ((!selectedFileList.isEmpty())) {
                JMenuItem item = new JMenuItem(" Remove ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!selectedFileList.isEmpty()) {
                            int option = JOptionPane.showConfirmDialog(mFrame, "Are you sure you want to delete ", "Delete", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                removeTrainSpkFile(selectedFileList, allArgsGroup);
                            }
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                sort.add(item);
            }



        }
    }

    private void removeSpkAll(ArrayList<String> fileNames) {
        try {
            VrasiClientSID vClient = new VrasiClientSID();
            if (!vClient.setRemoteConnection(mFrame.getUserID())) {
                javax.swing.JOptionPane.showMessageDialog(mFrame, "Server Could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            SpeakerMaster spkMaster = new SpeakerMaster(mFrame.getConn());
            for (String filename : fileNames) {
                if ((vClient.deleteSidServerFile(filename))) {
                    String query = "delete from sidmaster where filename ='" + filename + "'";
                    spkMaster.deleteSidFile(query);
                }
            }
            loadFileStruct();
        } catch (Exception er) {
            Logger.getLogger(ListofSpeakers.class.getName()).log(Level.SEVERE, null, er);
        }

    }

    private void reTrainSpkFile(ArrayList<String> fileNames, String groupName) {

        try {
            displaysidpro = new DisplaySIDProgressBar();
            sidProcess = new DoSIDProcess(fileNames, groupName);
            displaysidpro.start();
            sidProcess.start();

        } catch (Exception er) {
            Logger.getLogger(ListofSpeakers.class.getName()).log(Level.SEVERE, null, er);
        }


    }

    private void removeTrainSpkFile(ArrayList<String> fileNames, String groupName) {
        try {
            VrasiClientSID vClient = new VrasiClientSID();
            if (!vClient.setRemoteConnection(mFrame.getUserID())) {
                javax.swing.JOptionPane.showMessageDialog(mFrame, "Server Could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            SpeakerMaster spkMaster = new SpeakerMaster(mFrame.getConn());
            for (String filename : fileNames) {
                if ((vClient.deleteSidServerFile(groupName + "/" + filename))) {
                    String query = "delete from sidmaster where filename ='" + groupName + "' and wavefile = '" + filename + "'";
                    spkMaster.deleteSidFile(query);
                }
            }
            loadFileStruct();
        } catch (Exception er) {
            Logger.getLogger(ListofSpeakers.class.getName()).log(Level.SEVERE, null, er);
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

        setBackground(java.awt.Color.white);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(null);
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 146, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );

        jToolBar1.setRollover(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        DisplayObjects.displayStaticPanle();
    }//GEN-LAST:event_jPanel1MousePressed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        DisplayObjects.displayStaticPanle();
    }//GEN-LAST:event_formMousePressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    public class DoSIDProcess implements Runnable {

        private Thread thread = null;
        private ArrayList fileList = null;
        private String userDir = null;

        public DoSIDProcess(ArrayList fileList, String userDir) {
            this.fileList = fileList;
            this.userDir = userDir;
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

                    mFrame.rSidStatus = true;



                    boolean serverFind = true;


                    VrasiClientSID vClient = new VrasiClientSID();
                    if (!vClient.setRemoteConnection(mFrame.getUserID())) {
                        javax.swing.JOptionPane.showMessageDialog(mFrame, "Server Could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                        serverFind = false;
                    }

                    if (serverFind) {
                        boolean trained = vClient.speakerReTrain(fileList, userDir);

                        if (!trained) {
                            javax.swing.JOptionPane.showMessageDialog(mFrame, "Server Could not find", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                            serverFind = false;
                        }

                        if (serverFind) {
                            if (!new SpeakerMaster(mFrame.getConn()).updateTrainedStatus(userDir, fileList)) {
                                javax.swing.JOptionPane.showMessageDialog(mFrame, "Unable to update data base", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                            } else {
                                loadFileStruct();
                                javax.swing.JOptionPane.showMessageDialog(mFrame, "File has trained successfully");
                            }
                        }
                    }
                    //-----
                    double elapsed = (System.currentTimeMillis() - start) / 1000.0;
                    System.out.println("Execution Time: " + elapsed);
                    String exTime = String.valueOf(elapsed);
                    mFrame.pWave.setExeutionTime("Exec Time: " + exTime + " Sec");
                    //-----





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
        new ListofSpeakers(null, null);
    }
}
