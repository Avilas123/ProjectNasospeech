/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.gui;

/**
 *
 * @author Tatapower SED
 *
 */
import java.awt.*;


import java.io.*;

import java.util.*;

import javax.swing.*;

import javax.swing.tree.*;

import javax.swing.event.*;

//=============================================================
public class FileBrowserTree extends JPanel {

    public static final ImageIcon ICON_COMPUTER = new ImageIcon("computer.gif");
    public static final ImageIcon ICON_DISK = new ImageIcon("disk.gif");
    public static final ImageIcon ICON_FOLDER = new ImageIcon("folder.gif");
    public static final ImageIcon ICON_EXPANDEDFOLDER = new ImageIcon("expandedfolder.gif");
    protected JTree m_tree;
    protected DefaultTreeModel m_model;
    protected JTextField m_display;
    private MainFrame mainFrame;

    public FileBrowserTree(MainFrame mainFrame) {

        //super("Directories Tree");
        this.mainFrame = mainFrame;
        setSize(new Dimension(235, 270));

        //setBorder(BorderFactory.createLineBorder(Color.black, 2));


        DefaultMutableTreeNode top = new DefaultMutableTreeNode(
                new IconData(ICON_COMPUTER, null, "Computer"));

        DefaultMutableTreeNode node;

//        UIManager.put("Tree.expandedIcon",  new ImageIcon(getClass().getResource("/Speech/Icons/treeMinus.gif")).getImage());
//        UIManager.put("Tree.collapsedIcon", new ImageIcon(getClass().getResource("/Speech/Icons/treePlus.gif")).getImage());

        File[] roots = File.listRoots(); // find all drives


        for (int k = 0; k < roots.length; k++) {

            node = new DefaultMutableTreeNode(new IconData(ICON_DISK,
                    null, new FileNode(roots[k])));

            top.add(node);

            node.add(new DefaultMutableTreeNode(
                    new Boolean(true)));

        }

        m_model = new DefaultTreeModel(top);

        m_tree = new JTree(m_model);

        m_tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        m_tree.putClientProperty("JTree.lineStyle", "Angled");

        TreeCellRenderer renderer = new IconCellRenderer();

        m_tree.setCellRenderer(renderer);

        m_tree.addTreeExpansionListener(new DirExpansionListener());

        m_tree.addTreeSelectionListener(new DirSelectionListener());

        m_tree.setShowsRootHandles(true);

        m_tree.setEditable(false);
        m_tree.setFont(new Font("Courier New", Font.PLAIN, 12));

        UIManager.put("m_tree.expandedIcon",
                new ImageIcon("treeMinus.gif"));
        UIManager.put("m_tree.collapsedIcon",
                new ImageIcon("treePlus.gif"));

        JScrollPane s = new JScrollPane();
        s.setPreferredSize(new Dimension(230, 265));

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setSize(235, 300);
        panel1.add(s);
        s.getViewport().add(m_tree);



        m_display = new JTextField();

        m_display.setEditable(false);

        panel1.setVisible(true);

        // panel1.add(m_display, BorderLayout.NORTH);

        this.add(panel1);
        // this.validate();
        // this.repaint();


    }

    DefaultMutableTreeNode getTreeNode(TreePath path) {

        return (DefaultMutableTreeNode) (path.getLastPathComponent());

    }

    FileNode getFileNode(DefaultMutableTreeNode node) {

        if (node == null) {
            return null;
        }

        Object obj = node.getUserObject();

        if (obj instanceof IconData) {
            obj = ((IconData) obj).getObject();
        }

        if (obj instanceof FileNode) {
            return (FileNode) obj;
        } else {
            return null;
        }

    }

    // Make sure expansion is threaded and updating the tree model
    // only occurs within the event dispatching thread.
    class DirExpansionListener implements TreeExpansionListener {

        public void treeExpanded(TreeExpansionEvent event) {

            final DefaultMutableTreeNode node = getTreeNode(
                    event.getPath());

            final FileNode fnode = getFileNode(node);

            Thread runner = new Thread() {
                public void run() {

                    if (fnode != null && fnode.expand(node)) {

                        Runnable runnable = new Runnable() {
                            public void run() {

                                m_model.reload(node);

                            }
                        };

                        SwingUtilities.invokeLater(runnable);

                    }

                }
            };

            runner.start();

        }

        public void treeCollapsed(TreeExpansionEvent event) {
        }
    }

//==========================================
    class DirSelectionListener implements TreeSelectionListener {

        public void valueChanged(TreeSelectionEvent event) {

            DefaultMutableTreeNode node = getTreeNode(event.getPath());

            FileNode fnode = getFileNode(node);

            if (fnode != null) {
                m_display.setText(fnode.getFile().getAbsolutePath());
                try {
                    if (fnode.getFile().getAbsolutePath() == null) {
                        return;
                    }
                    if (fnode.getFile().getAbsolutePath().toString().length() < 1) {
                        return;
                    }

                    System.out.println(fnode.getFile().getAbsolutePath());
                    if (playBackStatus()) {
                        try {
                            File inputFile = new File(fnode.getFile().getAbsolutePath());

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
                                if ((source_file_name).endsWith(".mp3")) {


                                    Process t = Runtime.getRuntime().exec(sox_command);
                                    t.waitFor();


                                    System.out.println("sox command: " + sox_command);
                                    File wave = new File(wav_file_path);

                                    if (!wave.exists()) {
                                        System.out.println("Testing Mode : File is not ");
                                    }
                                    mainFrame.pWave.createAudioInputStream(wave, null, true);

                                    //lblloading.setText("Done");
                                } else if ((source_file_name).endsWith(".wma")) {
                                    Process t = Runtime.getRuntime().exec(AllToWav_command);
                                    t.waitFor();


                                    System.out.println("All to wave command: " + AllToWav_command);
                                    File wave = new File(wav_file_path);

                                    if (!wave.exists()) {
                                        System.out.println("Testing Mode : File is not ");
                                    }
                                    mainFrame.pWave.createAudioInputStream(wave, null, true);

                                    //lblloading.setText("Done");

                                } else {
                                    if (fnode.getFile().getAbsolutePath().toString().endsWith(".wav") || fnode.getFile().getAbsolutePath().toString().endsWith("WAV")) {
                                        mainFrame.pWave.createAudioInputStream(inputFile, null, true);
                                    }
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

                } catch (Exception er) {
                    System.err.println(er);
                }
            } else {
                m_display.setText("");
            }

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
    }

    //===================main method===========================
    public static void main(String argv[]) {
        new FileBrowserTree(null);
    }
}

class IconCellRenderer extends JLabel implements TreeCellRenderer {

    protected Color m_textSelectionColor;
    protected Color m_textNonSelectionColor;
    protected Color m_bkSelectionColor;
    protected Color m_bkNonSelectionColor;
    protected Color m_borderSelectionColor;
    protected boolean m_selected;

    public IconCellRenderer() {

        super();

        m_textSelectionColor = UIManager.getColor(
                "Tree.selectionForeground");

        m_textNonSelectionColor = UIManager.getColor(
                "Tree.textForeground");

        m_bkSelectionColor = UIManager.getColor(
                "Tree.selectionBackground");

        m_bkNonSelectionColor = UIManager.getColor(
                "Tree.textBackground");

        m_borderSelectionColor = UIManager.getColor(
                "Tree.selectionBorderColor");

        setOpaque(false);

    }

    public Component getTreeCellRendererComponent(JTree tree,
            Object value, boolean sel, boolean expanded, boolean leaf,
            int row, boolean hasFocus) {

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        Object obj = node.getUserObject();

        setText(obj.toString());

        if (obj instanceof Boolean) {
            setText("Retrieving data...");
        }

        if (obj instanceof IconData) {

            IconData idata = (IconData) obj;

            if (expanded) {
                setIcon(idata.getExpandedIcon());
            } else {
                setIcon(idata.getIcon());
            }

        } else {
            setIcon(null);
        }

        setFont(tree.getFont());

        setForeground(sel ? m_textSelectionColor
                : m_textNonSelectionColor);

        setBackground(sel ? m_bkSelectionColor
                : m_bkNonSelectionColor);

        m_selected = sel;

        return this;

    }

    public void paintComponent(Graphics g) {

        Color bColor = getBackground();

        Icon icon = getIcon();

        g.setColor(bColor);

        int offset = 0;

        if (icon != null && getText() != null) {
            offset = (icon.getIconWidth() + getIconTextGap());
        }

        g.fillRect(offset, 0, getWidth() - 1 - offset,
                getHeight() - 1);

        if (m_selected) {

            g.setColor(m_borderSelectionColor);

            g.drawRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1);

        }

        super.paintComponent(g);

    }
}

//=============================================
class IconData {

    protected Icon m_icon;
    protected Icon m_expandedIcon;
    protected Object m_data;

    public IconData(Icon icon, Object data) {

        m_icon = icon;

        m_expandedIcon = null;

        m_data = data;

    }

    public IconData(Icon icon, Icon expandedIcon, Object data) {

        m_icon = icon;

        m_expandedIcon = expandedIcon;

        m_data = data;

    }

    public Icon getIcon() {
        return m_icon;
    }

    public Icon getExpandedIcon() {

        return m_expandedIcon != null ? m_expandedIcon : m_icon;

    }

    public Object getObject() {
        return m_data;
    }

    public String toString() {
        return m_data.toString();
    }
}

//==================================================
class FileNode {

    protected File m_file;

    public FileNode(File file) {
        m_file = file;
    }

    public File getFile() {
        return m_file;
    }

    public String toString() {

        return m_file.getName().length() > 0 ? m_file.getName()
                : m_file.getPath();

    }

    public boolean isWavFile(File s_file) {
        if (s_file.getName().toLowerCase().endsWith(".wav")) {
            return true;
        } else {
            return false;
        }


    }

    public boolean expand(DefaultMutableTreeNode parent) {

        DefaultMutableTreeNode flag =
                (DefaultMutableTreeNode) parent.getFirstChild();

        if (flag == null) // No flag
        {
            return false;
        }

        Object obj = flag.getUserObject();

        if (!(obj instanceof Boolean)) {
            return false;      // Already expanded
        }
        parent.removeAllChildren();  // Remove Flag

        File[] files = listFiles();

        if (files == null) {
            return true;
        }

        Vector v = new Vector();

        for (int k = 0; k < files.length; k++) {

            File f = files[k];

            if (!(f.isDirectory())) {
                //continue;
            }



            FileNode newNode = new FileNode(f);

            boolean isAdded = false;


            for (int i = 0; i < v.size(); i++) {

                FileNode nd = (FileNode) v.elementAt(i);

                if (newNode.compareTo(nd) < 0) {
                    v.insertElementAt(newNode, i);
                    isAdded = true;
                    break;
                }

            }

            if (!isAdded) {
                v.addElement(newNode);
            }

        }

        for (int i = 0; i < v.size(); i++) {

            FileNode nd = (FileNode) v.elementAt(i);
            File selFile = new File(nd.getFile().getAbsolutePath());

            IconData idata = new IconData(FileBrowserTree.ICON_FOLDER,
                    FileBrowserTree.ICON_EXPANDEDFOLDER, nd);

            DefaultMutableTreeNode node = new DefaultMutableTreeNode(idata);

            //-------- add directories and wav files only--

            parent.add(node);
            if (selFile.isDirectory()) {
                //System.out.println(node+"is Dir");
                node.add(new DefaultMutableTreeNode(new Boolean(true)));
            }
        }

        return true;

    }

    public boolean hasSubDirs() {

        File[] files = listFiles();

        if (files == null) {
            return false;
        }

        for (int k = 0; k < files.length; k++) {

            if (files[k].isDirectory()) {
                return true;
            }

        }

        return false;

    }

    //----Ureka !!!
    public boolean isItFile() {

        File[] files = listFiles();

        if (files == null) {
            return false;
        }

        for (int k = 0; k < files.length; k++) {

            if (files[k].isFile()) {
                return true;
            }

        }

        return false;

    }
    //---------

    public int compareTo(FileNode toCompare) {

        return m_file.getName().compareToIgnoreCase(
                toCompare.m_file.getName());

    }

    protected File[] listFiles() {

        if (!m_file.isDirectory()) {
            return null;
        }

        try {

            return m_file.listFiles();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null,
                    "Error reading directory " + m_file.getAbsolutePath(),
                    "Warning", JOptionPane.WARNING_MESSAGE);

            return null;

        }

    }
}
