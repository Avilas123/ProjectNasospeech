/*  a
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.kws.newTreeCode;

import java.awt.BorderLayout;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Tatapower SED
 *
 */
public class NewKWSelectionPane extends javax.swing.JPanel {

    /**
     * Creates new form NewKWSelectionPane
     */
    public NewKWSelectionPane(Vector groupTree, Vector groupTree0, Vector groupTree1, Vector groupTree2, Vector groupTree3, String language) {
        initComponents();


        lVector1 = new Vector(groupTree);
        lVector2 = new Vector(groupTree0);
        lVector3 = new Vector(groupTree1);
        lVector4 = new Vector(groupTree2);
        lVector5 = new Vector(groupTree3);
        this.language = language;
        genratTreeForAllLists();

        NewKWSpane njp = new NewKWSpane(treeList1, treeList2, treeList3, treeList4, treeList5, hotListTree);
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(new JScrollPane(njp.getComponent()));
        jPanel1.revalidate();

    }

    private void genratTreeForAllLists() {


        DnDNode rFNL1 = new DnDNode("Priority List");
        addElementsFromGroutTreeToPL(rFNL1, lVector1);

        treeList1 = new DnDJTree(rFNL1);



        DnDNode rFNL2 = new DnDNode("Priority List");
        addElementsFromGroutTreeToPL(rFNL2, lVector2);

        treeList2 = new DnDJTree(rFNL2);



        DnDNode rFNL3 = new DnDNode("Priority List");
        addElementsFromGroutTreeToPL(rFNL3, lVector3);

        treeList3 = new DnDJTree(rFNL3);



        DnDNode rFNL4 = new DnDNode("Priority List");
        addElementsFromGroutTreeToPL(rFNL4, lVector4);

        treeList4 = new DnDJTree(rFNL4);



        DnDNode rFNL5 = new DnDNode("Priority List");
        addElementsFromGroutTreeToPL(rFNL5, lVector5);

        treeList5 = new DnDJTree(rFNL5);
        try {
            hotListTree = getHotListTree();
            jPanel2.removeAll();
            jPanel2.setLayout(new BorderLayout());
            jPanel2.add(new JScrollPane(hotListTree));
            jPanel2.revalidate();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewKWSelectionPane.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void addElementsFromGroutTreeToPL(DnDNode sNode, Vector vector) {

        for (int i = 0; i < vector.size(); i++) {
            DnDNode node = new DnDNode(((Vector) vector.elementAt(i)).elementAt(0).toString());
            for (int j = 1; j < ((Vector) vector.elementAt(i)).size(); j++) {
                node.add(new DnDNode(((Vector) vector.elementAt(i)).elementAt(j).toString()));
            }
            sNode.add(node);
            node = null;
        }

    }

    public void saveHotList() throws FileNotFoundException {
        try (XMLEncoder xe = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("tree." + language + ".xml")))) {
            xe.writeObject(hotListTree.getModel());
        }

    }

    public void saveTempHotList() throws FileNotFoundException {
        try (XMLEncoder xe = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("tree_temp.xml")))) {
            xe.writeObject(hotListTree.getModel());
        }

    }

    private DnDJTree getHotListTree() throws FileNotFoundException {
        DefaultTreeModel model = null;
        File f = new File("tree." + language + ".xml");
        if (f.exists()) {
            try (XMLDecoder xd = new XMLDecoder(new BufferedInputStream(new FileInputStream(f)))) {
                model = (DefaultTreeModel) xd.readObject();
            }
        }

        DnDJTree jt = null;

        if (model != null) {
            jt = new DnDJTree(model);
        } else {
            DnDNode dnd = new DnDNode("Hot List");
            dnd.add(new DnDNode("Drop Here"));
            jt = new DnDJTree(dnd);
        }
        return jt;
    }

    public ArrayList getKWHotList() {
        //DefaultMutableTreeNode rootNode = selectkeyTree.rootNode;
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) hotListTree.getModel().getRoot();
        ArrayList selectedKeywordList = new ArrayList();
        // JOptionPane.showMessageDialog(null, rootNode.getChildCount());

//        traversTree(rootNode);
        ArrayList al = new ArrayList();
        for (int i = 0; i < rootNode.getChildCount(); i++) {
            TreeNode tn = rootNode.getChildAt(i);
            if (tn.isLeaf()) {
                al.add(tn.toString());
                continue;
            }
            System.out.println(rootNode.getChildAt(i));
            for (int j = 0; j < rootNode.getChildAt(i).getChildCount(); j++) {
                tn = rootNode.getChildAt(i).getChildAt(j);
                if (tn.isLeaf()) {
                    al.add(tn.toString());
                    continue;
                }
                for (int k = 0; k < rootNode.getChildAt(i).getChildAt(j).getChildCount(); k++) {
                    tn = rootNode.getChildAt(i).getChildAt(j).getChildAt(k);
                    if (tn.isLeaf()) {
                        al.add(tn.toString());
                        continue;
                    }
                    tn = null;
                }
            }
        }
        al.remove(al.size() - 1);

        for (int i = 0; i < al.size(); i++) {
            System.out.println(i + "  " + al.get(i));
        }
        return al;
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
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 490));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 210, 450));

        jButton1.setText("Remove Selected");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 460, 130, -1));

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, 80, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            saveHotList();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewKWSelectionPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (hotListTree != null) {
                TreePath pathlist[] = hotListTree.getSelectionPaths();
                for (TreePath path : pathlist) {
                    if (path != null) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        if (node != hotListTree.getModel().getRoot()) {
                            DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
                            if (node.toString().compareTo("Drop Here") != 0) {
                                parent.remove(node);
                            }
                            hotListTree.updateUI();
                        }
                    }
                }
            }

        } catch (Exception er) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
    private Vector lVector1;
    private Vector lVector2;
    private Vector lVector3;
    private Vector lVector4;
    private Vector lVector5;
    private DnDJTree hotListTree;
    public ArrayList kwList;
    private String language;
    private DnDJTree treeList1;
    private DnDJTree treeList2;
    private DnDJTree treeList3;
    private DnDJTree treeList4;
    private DnDJTree treeList5;
}
