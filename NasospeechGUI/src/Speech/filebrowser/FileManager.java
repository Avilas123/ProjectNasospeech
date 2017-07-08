/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.filebrowser;

import Speech.gui.MainFrame;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tatapower SED
 *
 */
public class FileManager {

    private MainFrame mainFrame;
    /**
     * Used to open/edit/print files.
     */
    /**
     * Provides nice icons and names for files.
     */
    private FileSystemView fileSystemView;
    /**
     * Main GUI container
     */
    private JPanel gui;
    /**
     * File-system tree. Built Lazily
     */
    // private JTree tree;
    private FileTree fileTree;
    private String extendvalue;

    /**
     * Directory listing
     */
    public FileManager(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public Container getGui() {
        if (gui == null) {
            gui = new JPanel(new BorderLayout(3, 3));
            gui.setBorder(new EmptyBorder(5, 5, 5, 5));
            fileSystemView = FileSystemView.getFileSystemView();
            DefaultMutableTreeNode root = new DefaultMutableTreeNode();

            File[] roots = fileSystemView.getRoots();
            for (File fileSystemRoot : roots) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
                root.add(node);
                File[] files = fileSystemView.getFiles(fileSystemRoot, true);
                for (File file : files) {
                    node.add(new DefaultMutableTreeNode(file));
                }
            }
            TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent tse) {
                    DefaultMutableTreeNode node =
                            (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
                    addChildren(node);
                    setFileDetails((File) node.getUserObject());
                }
            };
            try {
                fileTree = new FileTree("/home/");


//                  fileTree.addTreeSelectionListener(treeSelectionListener);
                //fileTree.add
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            JScrollPane treeScroll = new JScrollPane(fileTree);
            FileTreeDropTarget target = new FileTreeDropTarget(fileTree);
            FileTreeDragSource source = new FileTreeDragSource(fileTree);
            // as per trashgod tip


            fileTree.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (SwingUtilities.isRightMouseButton(evt)) {
                        try {
                            int row = fileTree.getClosestRowForLocation(evt.getX(), evt.getY());

                            fileTree.setSelectionRow(row);


                            TreePath[] paths = fileTree.getSelectionPaths();

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

                                    System.out.println(selectedFile);


                                    if (selectedFile.length() > 0 && ((selectedFile.toLowerCase()).endsWith(".wav") || (selectedFile.toLowerCase()).endsWith(".mp3") || (selectedFile.toLowerCase()).endsWith(".wma")) && (!selectedFile.contains("WorkSpace"))) {
                                        if (mainFrame.pWave.audioInputStream != null) {
                                            javax.swing.JOptionPane.showMessageDialog(mainFrame, "please close existing files");
                                            return;
                                        }
                                        File selectFile = new File(selectedFile);
                                        if (selectFile.exists()) {
                                            mainFrame.pWave.createAudioInputStream(selectFile, null, true);
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

            if (fileTree.getCellRenderer() instanceof DefaultTreeCellRenderer) {
                final DefaultTreeCellRenderer renderer =
                        (DefaultTreeCellRenderer) (fileTree.getCellRenderer());

                renderer.setTextNonSelectionColor(Color.RED);
                renderer.setTextSelectionColor(Color.BLUE);
            } else {
                System.err.println("Sorry, no special colors today.");
            }

            fileTree.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (!(e.getKeyCode() == KeyEvent.VK_DELETE)) {
                        return;
                    }
                    if (fileTree.getSelectionPaths() == null) {
                        return;
                    }

                    TreePath[] paths = fileTree.getSelectionPaths();

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

                                    File selectFile = new File(selectedFile);
                                    if (selectFile.exists()) {
                                        if (selectFile.isFile()) {
                                            selectFile.delete();
                                        }
                                    }

                                } catch (Exception er) {
                                }
                            }

                        }
                    }


                    mainFrame.fileBrowser("");
                    fileTree.expandRow(2);




                }
            });


            fileTree.setEditable(true);
            fileTree.setVisibleRowCount(15);

            Dimension preferredSize = treeScroll.getPreferredSize();
            Dimension widePreferred = new Dimension(
                    250,
                    (int) preferredSize.getHeight());
            treeScroll.setPreferredSize(widePreferred);

            //flags.setBorder(new TitledBorder("Flags"));              
            gui.add(treeScroll);
        }
        return gui;
    }

    /**
     * Add the files that are contained within the directory of this node.
     */
    private void addChildren(final DefaultMutableTreeNode node) {
        SwingWorker worker = new SwingWorker() {
            @Override
            public String doInBackground() {

                File file = (File) node.getUserObject();
                if (file.isDirectory()) {
                    if (!node.getUserObject().toString().startsWith("Network")) {
                        File[] files = fileSystemView.getFiles(file, true);
                        if (node.isLeaf()) {
                            for (File child : files) {
                                //if(child.getName().toLowerCase().endsWith(".wav") || child.getName().toLowerCase().endsWith(".mp3") || child.getName().toLowerCase().endsWith(".wma"))
                                node.add(new DefaultMutableTreeNode(child));
                            }
                        }
                    }
                }

                return "done";
            }
        };
        worker.execute();
    }

    /**
     * Update the File details view with the details of this File.
     */
    private void setFileDetails(File file) {

        if ((file.getName().toLowerCase()).endsWith(".wav") || (file.getName().toLowerCase()).endsWith(".mp3") || (file.getName().toLowerCase()).endsWith(".wma")) {
            if (playBackStatus()) {
                try {
                    File inputFile = new File(file.getAbsolutePath());

                    long start = System.currentTimeMillis();

                    String source_file_name = inputFile.getName();//eg. test.mp3

                    int idx = source_file_name.lastIndexOf('.');
                    String wav_file_name = source_file_name.substring(0, idx) + ".wav";

                    File convertedFile = new File("conf/converted");
                    convertedFile.mkdir();
                    File soxExefile = new File("sox/sox.exe");
                    File allToWavExefile = new File("sox/AlltowavCmd.exe");
                    Process pr;
                    String sox_path = soxExefile.getAbsolutePath();
                    String allToWav_path = allToWavExefile.getAbsolutePath();

                    String source_file_path = inputFile.getAbsolutePath();
                    String wav_file_path = convertedFile.getAbsolutePath() + "\\" + wav_file_name;

                    String sox_command = sox_path + " " + source_file_path + " -r 8k " + convertedFile.getAbsolutePath() + "\\" + wav_file_name;
                    //String mystring1 = sox_path+" -r 8k -e signed -b 8 -c 1 "+source_file_path+" d:\\converted\\"+newFileName;
                    String AllToWav_command = allToWav_path + " " + source_file_path + " -O" + convertedFile.getAbsolutePath() + "\\" + wav_file_name + " -S8000";
                    try {
                        if ((source_file_name.toLowerCase()).endsWith(".mp3")) {


                            Process t = Runtime.getRuntime().exec(sox_command);
                            t.waitFor();


                            System.out.println("sox command: " + sox_command);
                            File wave = new File(wav_file_path);

                            if (!wave.exists()) {
                                System.out.println("Testing Mode : File is not ");
                                return;
                            }
                            mainFrame.pWave.createAudioInputStream(wave, null, true);

                            //lblloading.setText("Done");
                        } else if ((source_file_name.toLowerCase()).endsWith(".wma")) {
                            Process t = Runtime.getRuntime().exec(AllToWav_command);
                            t.waitFor();


                            System.out.println("All to wave command: " + AllToWav_command);
                            File wave = new File(wav_file_path);

                            if (!wave.exists()) {
                                System.out.println("Testing Mode : File is not ");
                                return;
                            }
                            mainFrame.pWave.createAudioInputStream(wave, null, true);

                            //lblloading.setText("Done");

                        } else {

                            mainFrame.pWave.createAudioInputStream(inputFile, null, true);

                        }
                        //--
                        double elapsed = (System.currentTimeMillis() - start) / 1000.0;
                        //lblprocTime.setText("Processing Time: " + elapsed + " sec");
                        System.out.println("Processing Time: " + elapsed + " sec");
                    } catch (IOException ex) {
                        //Logger.getLogger(this.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex1) {
                        // Logger.getLogger(this.class.getName()).log(Level.SEVERE, null, ex1);
                    }







                } catch (Exception er) {
                    System.err.println(er);
                }
            }
        }

        gui.repaint();
    }

    private boolean playBackStatus() {
        try {
            if (mainFrame == null) {
                return false;
            }
            if (mainFrame.pWave == null) {
                return false;
            }
            if (mainFrame.pWave.playback == null) {
                return false;
            }
            if (mainFrame.pWave.playback.line == null) {
                return true;
            }
            if (mainFrame.pWave.playback.line.isActive()) {
                return false;
            }
            if (mainFrame.pWave.playback.line.isRunning()) {
                return false;
            }
            if (mainFrame.pWave.playback.line.isOpen()) {
                return false;
            }
            return true;
        } catch (Exception err) {
            System.err.println(err);
        }
        return false;
    }

    /**
     * A TableModel to hold File[].
     */
    /**
     * A TreeCellRenderer for a File.
     */
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

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            File file = (File) node.getUserObject();
            label.setIcon(fileSystemView.getSystemIcon(file));
            label.setText(fileSystemView.getSystemDisplayName(file));
            label.setToolTipText(file.getPath());

            if (selected) {
                label.setBackground(backgroundSelectionColor);
            } else {
                label.setBackground(backgroundNonSelectionColor);
            }

            return label;
        }
    }
}
