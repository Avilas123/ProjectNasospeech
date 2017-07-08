/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.filebrowser;

/**
 *
 * @author Tatapower SED
 *
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Speech.annotations.UpdateAnnotation;
import Speech.gui.MainFrame;
import Speech.rmi.VrasiClientPDS;
import Speech.rmi.VrasiClientSID;



/**
 *
 * @author Lok
 */
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;

import javax.swing.tree.*;
import javax.xml.ws.soap.MTOM;

//=============================================================
public class ServerFileBrowser extends JPanel {

    protected JTree m_tree;
    protected DefaultTreeModel m_model;
    protected JTextField m_display;
    private MainFrame mainFrame;
    public static String sfile;

    public ServerFileBrowser(final MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        //super("Directories Tree");


    }

    public JPanel CreateServerFileStructure() {
        try {

            setSize(new Dimension(235, 270));

            //setBorder(BorderFactory.createLineBorder(Color.black, 2));

            VrasiClientSID vClient;
            DefaultMutableTreeNode root = null;
            try {
                //new KeywordExe(inputWave, language);

                vClient = new VrasiClientSID();

                if (!vClient.setRemoteConnection(mainFrame.getUserID())) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Server could not find due to missing some resource");
                    return this;
                }
                root = vClient.getFileStructure();


            } catch (Exception er) {
                System.out.println(er);
            }


            if (root == null) {
                return this;
            }
            //FileTransferMaster fileMaster = new FileTransferMaster(mainFrame.getConn());
            // DefaultMutableTreeNode root = fileMaster.getAllServerFiles();




            m_tree = new JTree(root);
            m_tree.setCellRenderer(new FileTreeCellRenderer());
            JScrollPane s = new JScrollPane();
            s.setPreferredSize(new Dimension(230, 265));

            JPanel panel1 = new JPanel(new BorderLayout());
            panel1.setSize(235, 300);
            panel1.add(s);
            m_tree.setDragEnabled(true);
            m_tree.setDropMode(DropMode.ON_OR_INSERT);
            m_tree.setTransferHandler(new TreeTransferHandler(m_tree));
            m_tree.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (SwingUtilities.isRightMouseButton(evt)) {
                        try {
                            int row = m_tree.getClosestRowForLocation(evt.getX(), evt.getY());
                            System.out.println("this " + row);
                            m_tree.setSelectionRow(row);


                            TreePath[] paths = m_tree.getSelectionPaths();

                            // Iterate through all affected nodes
                            for (int i = 0; i < paths.length; i++) {



                                // This node has been selected
                                if (paths[i].getLastPathComponent() == null) {
                                    break;
                                }

                                DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) paths[i].getLastPathComponent();
                                int count = dmtn.getLeafCount();
                                if (count == 1) {

                                    String value = null;
                                    value = paths[i].getLastPathComponent().toString();

                                    String selectedFile = "";
                                    for (int p = 0; p < paths[i].getPath().length; p++) {
                                        if (p == 0) {
                                            selectedFile = paths[i].getPath()[p].toString();
                                            continue;
                                        }
                                        selectedFile = selectedFile + "/" + paths[i].getPath()[p].toString();



                                    }

                                    //  System.out.println(selectedFile);


                                    if (selectedFile.length() > 0 && ((selectedFile.toLowerCase()).endsWith(".wav") || (selectedFile.toLowerCase()).endsWith(".mp3") || (selectedFile.toLowerCase()).endsWith(".wma")) && (!selectedFile.contains("WorkSpace"))) {

                                        if (mainFrame.pWave.audioInputStream != null) {
                                            javax.swing.JOptionPane.showMessageDialog(mainFrame, "please close existing files");
                                            return;
                                        }

                                        VrasiClientPDS vraspds = new VrasiClientPDS();
                                        String fileName = selectedFile.substring(selectedFile.lastIndexOf("/") + 1, selectedFile.length());
                                        boolean getFile = vraspds.getfileFromRemoteServer(selectedFile, fileName);
                                        if (getFile) {
                                            File loadFile = new File("conf/workspace/" + fileName);
                                            if (loadFile.exists()) {
                                                if ((loadFile.getName().toLowerCase()).endsWith(".wav")) {
                                                    mainFrame.pWave.createAudioInputStream(loadFile, null, true);
                                                }
                                            }
                                        }



                                    }
                                }
                            }
                        } catch (Exception er) {
                            System.out.println(er);
                        }
                    }
                }

                public void mouseEntered(java.awt.event.MouseEvent evt) {
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                }

                public void mousePressed(java.awt.event.MouseEvent evt) {
                }

                public void mouseReleased(java.awt.event.MouseEvent evt) {
                }
            });


            m_tree.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (!(e.getKeyCode() == KeyEvent.VK_DELETE)) {
                        return;
                    }
                    if (m_tree.getSelectionPaths() == null) {
                        return;
                    }

                    TreePath[] paths = m_tree.getSelectionPaths();

                    // Iterate through all affected nodes
                    for (int i = 0; i < paths.length; i++) {



                        // This node has been selected
                        if (paths[i].getLastPathComponent() == null) {
                            break;
                        }

                        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) paths[i].getLastPathComponent();
                        int count = dmtn.getLeafCount();
                        if (count == 1) {

                            String value = null;
                            value = paths[i].getLastPathComponent().toString();

                            String selectedFile = "";
                            for (int p = 0; p < paths[i].getPath().length; p++) {
                                if (p == 0) {
                                    selectedFile = paths[i].getPath()[p].toString();
                                    continue;
                                }
                                selectedFile = selectedFile + "/" + paths[i].getPath()[p].toString();

                            }

                            // System.out.println("Key press " + selectedFile);

                            if (selectedFile != null) {
                                try {
                                    String hashValue = new VrasiClientSID().deleteServerFile(selectedFile);
                                    if (hashValue == null) {
                                        javax.swing.JOptionPane.showMessageDialog(mainFrame, selectedFile + " is not able to delete");
                                    } else {
                                        new UpdateAnnotation(mainFrame.getConn()).deleteServerFileAnnotation(hashValue);
                                    }
                                } catch (Exception er) {
                                }
                            }

                        }
                    }


                    mainFrame.fileBrowser("");
                    m_tree.expandRow(2);




                }
            });

            s.getViewport().add(m_tree);

            m_tree.addTreeSelectionListener(new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent evt) {
                    // Get all nodes whose selection status has changed
             /*       try {

                     TreePath[] paths = evt.getPaths();

                     // Iterate through all affected nodes
                     for (int i = 0; i < paths.length; i++) {

                     if (evt.isAddedPath(i)) {

                     // This node has been selected
                     if (paths[i].getLastPathComponent() == null) {
                     break;
                     }

                     DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) paths[i].getLastPathComponent();
                     int count = dmtn.getLeafCount();
                     if (count == 1) {

                     String value = null;
                     value = paths[i].getLastPathComponent().toString();

                     String selectedFile = "";
                     for (int p = 0; p < paths[i].getPath().length; p++) {
                     if (p == 0) {
                     selectedFile = paths[i].getPath()[p].toString();
                     continue;
                     }
                     selectedFile = selectedFile + "/" + paths[i].getPath()[p].toString();



                     }

                     System.out.println(selectedFile);




                     }
                     }
                     }
                     } catch (Exception er) {
                     System.out.println(er);
                     }*/
                }
            });


            m_display = new JTextField();

            m_display.setEditable(
                    false);

            panel1.setVisible(
                    true);

            // panel1.add(m_display, BorderLayout.NORTH);

            this.add(panel1);

            // this.validate();
            // this.repaint();
        } catch (Exception er) {
            System.err.println(er);
        }
        return this;
    }

    class FileTreeCellRenderer extends DefaultTreeCellRenderer {

        private FileSystemView fileSystemView;
        private JLabel label;

        FileTreeCellRenderer() {
            label = new JLabel();
            label.setOpaque(true);
            fileSystemView = FileSystemView.getFileSystemView();
        }

        @Override
        public Component getTreeCellRendererComponent(
                JTree tree,
                Object value,
                boolean selected,
                boolean expanded,
                boolean leaf,
                int row,
                boolean hasFocus) {
            try {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                if (node.getUserObject().toString().endsWith(".wav") || node.getUserObject().toString().endsWith(".mp3") || node.getUserObject().toString().endsWith(".wma")) {
                    File file = new File("conf/buf/server.wav");
                    label.setIcon(fileSystemView.getSystemIcon(file));
                    label.setText(node.getUserObject().toString());
                    label.setToolTipText(file.getPath());

                    if (selected) {
                        label.setBackground(backgroundSelectionColor);
                    } else {
                        label.setBackground(backgroundNonSelectionColor);
                    }
                } else {
                    File file = new File("conf");
                    label.setIcon(fileSystemView.getSystemIcon(file));
                    label.setText(node.getUserObject().toString());
                    label.setToolTipText(file.getPath());

                    if (selected) {
                        label.setBackground(backgroundSelectionColor);
                    } else {
                        label.setBackground(backgroundNonSelectionColor);
                    }
                }
            } catch (Exception er) {
            }
            return label;
        }
    }
}
//==================================================

