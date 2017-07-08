/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ResultKeyWordPanel.java
 *
 * Created on 4 Jan, 2013, 12:39:04 PM
 */
package Speech.kws;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.IconUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Tatapower SED
 *
 */
public class ResultKeyWordPanel extends javax.swing.JPanel {

    // private JTree tree;
    public PlotWaveKws pWave;
    private HashMap keyColor;
    private DefaultMutableTreeNode parent = null;
    private String language;

    /**
     * Creates new form ResultKeyWordPanel
     */
    public ResultKeyWordPanel(PlotWaveKws pWave, String language) {
        initComponents();
        this.language = language;
        this.pWave = pWave;

        this.setSize(new Dimension(240, 200));
        this.setPreferredSize(new Dimension(240, 200));
        this.revalidate();
        this.repaint();
        treeBuilder();
        loadSorbyMenu();
    }

    public void treeBuilder() {
        List keyWord = pWave.keyBuilder.getKeywordsList();
        List startTime = pWave.keyBuilder.getStartTimeList();
        List endTime = pWave.keyBuilder.getEndTimeList();
        if (keyWord == null || startTime == null || endTime == null) {
            return;
        }
        Icon empty = new TreeIcon();
        UIManager.put("Tree.closedIcon", empty);
        UIManager.put("Tree.openIcon", empty);
        UIManager.put("Tree.leafIcon", empty);
        UIManager.put("Tree.collapsedIcon", new IconUIResource(new NodeIcon('+')));
        UIManager.put("Tree.expandedIcon", new IconUIResource(new NodeIcon('-')));
        parent = new DefaultMutableTreeNode("KeyWord List", true);
        boolean isKeyAlready = false;
        DefaultMutableTreeNode key;
        DefaultMutableTreeNode time;
        List indexPos = new ArrayList();
        for (int i = 0; i < keyWord.size(); i++) {
            String keyStr = keyWord.get(i).toString();
            for (int k = 0; k < indexPos.size(); k++) {
                if (keyStr.toLowerCase().equals(indexPos.get(k).toString())) {
                    isKeyAlready = true;
                    break;
                }
            }
            if (isKeyAlready) {
                isKeyAlready = false;
                continue;
            }
            key = new DefaultMutableTreeNode(keyStr.toUpperCase());

            try {

                BigDecimal startMS = new BigDecimal(startTime.get(i).toString());
                BigDecimal endMS = new BigDecimal(endTime.get(i).toString());
                BigDecimal divided = new BigDecimal("10000000");

                time = new DefaultMutableTreeNode((startMS.divide(divided)).toString() + " - " + (endMS.divide(divided)).toString(), true);
                key.add(time);

                for (int j = i + 1; j < keyWord.size(); j++) {
                    if ((keyStr.toLowerCase()).equals((keyWord.get(j).toString()).toLowerCase())) {
                        startMS = new BigDecimal(startTime.get(j).toString());
                        endMS = new BigDecimal(endTime.get(j).toString());
                        time = new DefaultMutableTreeNode((startMS.divide(divided)).toString() + " - " + (endMS.divide(divided)).toString(), true);

                        key.add(time);
                        indexPos.add(keyStr.toLowerCase());
                    }
                }
            } catch (NumberFormatException er) {
                System.err.println(er);
            }
            key.setUserObject(key.getUserObject() + " (" + key.getChildCount() + ") ");
            parent.add(key);

        }
        if (parent == null) {
            return;
        }

        jPanel1.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setSize(new Dimension(240, 205));
        scrollPane.setPreferredSize(new Dimension(240, 205));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getViewport().add(new TreeView(sortOccurrence(sortOccurrence(parent)))).setFont(new Font("Serif", Font.PLAIN, 13));

        jPanel1.add(scrollPane);
        jPanel1.revalidate();
        jPanel1.repaint();

    }

    private void loadSorbyMenu() {
        JMenuBar sortBy = new JMenuBar();
        try {
            sortBy.add(addMenuIteam());


        } catch (Exception ex) {
            Logger.getLogger(ReadKeywordsFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        jToolBar1.add(sortBy);


    }

    public static DefaultMutableTreeNode sortOccurrence(DefaultMutableTreeNode root) {
        {
            try {
                for (int i = 0; i < root.getChildCount() - 1; i++) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(i);
                    String nt = node.getUserObject().toString();
                    int ntCount = node.getChildCount();
                    for (int j = i + 1; j < root.getChildCount(); j++) {
                        DefaultMutableTreeNode prevNode = (DefaultMutableTreeNode) root.getChildAt(j);
                        String np = prevNode.getUserObject().toString();
                        int prevNodeCount = prevNode.getChildCount();
                        if (prevNodeCount > ntCount) {
                            root.insert(node, j);
                            root.insert(prevNode, i);
                            ntCount = prevNodeCount;
                        }
                    }

                }
            } catch (Exception er) {
                Logger.getLogger(ResultKeyWordPanel.class.getName()).log(Level.SEVERE, null, er);
            }
            return root;
        }
    }

    public DefaultMutableTreeNode sortAZ(DefaultMutableTreeNode node) {
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
            Logger.getLogger(ResultKeyWordPanel.class.getName()).log(Level.SEVERE, null, er);
        }

        return node;

    }

    public DefaultMutableTreeNode sortPriority(DefaultMutableTreeNode node) {
        try {
            CreateKeywordModel cKeyModel = new CreateKeywordModel(pWave.mainFrame.getConn());
            //sort alfebetically
            for (int i = 0; i < node.getChildCount() - 1; i++) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
                String nt = child.getUserObject().toString();

                if (nt.indexOf("(") > 0) {
                    nt = (nt.substring(0, nt.indexOf("("))).trim();
                }
                int nt_priority = cKeyModel.getPriority(nt, language);

                for (int j = i + 1; j < node.getChildCount(); j++) {
                    DefaultMutableTreeNode prevNode = (DefaultMutableTreeNode) node.getChildAt(j);
                    String np = prevNode.getUserObject().toString();

                    if (np.indexOf("(") > 0) {
                        np = (np.substring(0, np.indexOf("("))).trim();
                    }
                    int np_priority = cKeyModel.getPriority(np, language);

                    if (np_priority < nt_priority) {
                        node.insert(child, j);
                        node.insert(prevNode, i);
                        child = (DefaultMutableTreeNode) node.getChildAt(i);
                        nt = child.getUserObject().toString();
                        if (nt.indexOf("(") > 0) {
                            nt = (nt.substring(0, nt.indexOf("("))).trim();
                        }
                        nt_priority = cKeyModel.getPriority(nt, language);
                    }
                }

            }

        } catch (Exception er) {
            Logger.getLogger(ResultKeyWordPanel.class.getName()).log(Level.SEVERE, null, er);
        }

        return node;

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

        jPanel1.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );

        jToolBar1.setRollover(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    private JMenu addMenuIteam() {

        JMenu sort = new JMenu("Sort by");
        sort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/sort_down.png")));
        sort.setFont(new Font("Courier New", Font.PLAIN, 13));
        sort.setBackground(Color.white);



        JMenuItem item = new JMenuItem(" Priority ");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (parent == null) {
                    return;
                }

                jPanel1.removeAll();
                jPanel1.setLayout(new BorderLayout());
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setSize(new Dimension(240, 205));
                scrollPane.setPreferredSize(new Dimension(240, 205));
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.getVerticalScrollBar().setUnitIncrement(10);
                scrollPane.getViewport().add(new TreeView(sortPriority(parent)));
                jPanel1.add(scrollPane);
                jPanel1.revalidate();
                jPanel1.repaint();

            }
        });
        item.setFont(new Font("Courier New", Font.PLAIN, 15));
        item.setBackground(Color.white);
        sort.add(item);



        item = new JMenuItem(" Occurrence ");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (parent == null) {
                    return;
                }

                jPanel1.removeAll();
                jPanel1.setLayout(new BorderLayout());
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setSize(new Dimension(240, 205));
                scrollPane.setPreferredSize(new Dimension(240, 205));
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.getVerticalScrollBar().setUnitIncrement(10);
                scrollPane.getViewport().add(new TreeView(sortOccurrence(sortOccurrence(parent))));
                jPanel1.add(scrollPane);
                jPanel1.revalidate();
                jPanel1.repaint();

            }
        });
        item.setFont(new Font("Courier New", Font.PLAIN, 15));
        item.setBackground(Color.white);
        sort.add(item);



        item = new JMenuItem(" A - Z  ");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (parent == null) {
                    return;
                }
                jPanel1.removeAll();
                jPanel1.setLayout(new BorderLayout());
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setSize(new Dimension(240, 205));
                scrollPane.setPreferredSize(new Dimension(240, 205));
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.getVerticalScrollBar().setUnitIncrement(10);
                scrollPane.getViewport().add(new TreeView(sortAZ(parent)));
                jPanel1.add(scrollPane);
                jPanel1.revalidate();
                jPanel1.repaint();
            }
        });
        item.setFont(new Font("Courier New", Font.PLAIN, 15));
        item.setBackground(Color.white);
        sort.add(item);
        return sort;

    }

    class TreeView extends JTree implements MouseListener, TreeSelectionListener {

        public TreeView(DefaultMutableTreeNode node) {
            super(node);
            setFont(new Font("Courier New", Font.PLAIN, 13));
            addMouseListener(this);
            addTreeSelectionListener(this);

        }

        @Override
        public void mouseClicked(MouseEvent evt) {
            if (SwingUtilities.isRightMouseButton(evt)) {
                JPopupMenu menu = new JPopupMenu("Popup");
                menu.add(addMenuIteam());
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
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

        @Override
        public void valueChanged(TreeSelectionEvent evt) {
            try {

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
                        if (count == 1 && dmtn.isLeaf()) {

                            String value = null, keyStart = null, keyEnd = null;
                            value = paths[i].getLastPathComponent().toString();
                            StringTokenizer st = new StringTokenizer(value, "-");
                            if (st.hasMoreElements()) {
                                keyStart = st.nextElement().toString();
                            }
                            if (st.hasMoreElements()) {
                                keyEnd = st.nextElement().toString();
                            }
                            if (keyEnd == null || keyStart == null) {
                                break;
                            }
                            keyStart = keyStart.trim();
                            keyEnd = keyEnd.trim();

                            try {
                                float keySt = new Float(keyStart);
                                keySt = keySt * 1000;
                                pWave.playWaveFromRemote((int) keySt);

                            } catch (NumberFormatException er) {
                                System.err.println(er);
                            }
                            break;
                        } else {

                            //System.out.println(paths[i].getLastPathComponent().toString());
                            if ((paths[i].getLastPathComponent().toString()).equals("KeyWord List")) {
                                try {
                                    pWave.rightClick.setDefaultKeywordColor();
                                } catch (Exception er) {
                                    System.err.println(er);
                                }
                            } else {
                                try {
                                    keyColor = new HashMap();
                                    String selKey = paths[i].getLastPathComponent().toString().toLowerCase();
                                    if (selKey.indexOf("(") > 0) {
                                        selKey = selKey.substring(0, selKey.indexOf("("));
                                        selKey = selKey.trim();

                                    }

                                    keyColor.put(selKey, new Color(0, 128, 0));
                                    pWave.keyBuilder.setKeywordsColor(keyColor);
                                    pWave.samplingGraph.repaint();
                                } catch (Exception er) {
                                    System.err.println(er.getMessage());
                                }
                            }
                            break;

                        }
                    } else {

                        // This node has been deselected
                        break;
                    }
                }

            } catch (Exception er) {
                System.err.println(er);
            }
        }
    }
}
