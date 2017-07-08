/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.speakeridentification;

/**
 *
 * @author speechware_2
 */
import Speech.kws.NodeIcon;
import Speech.kws.TreeIcon;
import java.awt.*;
import java.awt.datatransfer.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.IconUIResource;
import javax.swing.tree.*;

/**
 *
 * @author Tatapower SED
 *
 */
public class SIDList {

    String sn, sg;

    public SIDList() {
    }

    public JScrollPane getContent(DefaultMutableTreeNode model, final ListofSpeakers sl) {
        Icon empty = new TreeIcon();
        UIManager.put("Tree.closedIcon", empty);
        UIManager.put("Tree.openIcon", empty);
        UIManager.put("Tree.leafIcon", empty);
        UIManager.put("Tree.collapsedIcon", new IconUIResource(new NodeIcon('+')));
        UIManager.put("Tree.expandedIcon", new IconUIResource(new NodeIcon('-')));
        JTree tree = new JTree(model);
        tree.setFont(new Font("Courier New", Font.PLAIN, 14));
        tree.setDragEnabled(true);
        tree.setDropMode(DropMode.ON_OR_INSERT);
        tree.setTransferHandler(new TreeTransferHandler(sl));
        tree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent evt) {
                // Get all nodes whose selection status has changed
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
                            if (count == 1) {
                                if (paths[i].getLastPathComponent().toString() == null || paths[i].getParentPath().getLastPathComponent().toString() == null) {
                                    return;
                                }
                                String userName = paths[i].getLastPathComponent().toString();
                                String groupName = (paths[i].getParentPath().getLastPathComponent().toString()).trim();
                                sn = userName;
                                sg = groupName;
                                sl.selectedSpeaker(userName, groupName);
                            } else {
                                //System.out.println(paths[i].getLastPathComponent().toString()+"-"+paths[i].getParentPath().getPathComponent(0).toString());
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
        });
        return new JScrollPane(tree);
    }

    private void expandTree(JTree tree) {
        DefaultMutableTreeNode root =
                (DefaultMutableTreeNode) tree.getModel().getRoot();
        Enumeration e = root.breadthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node =
                    (DefaultMutableTreeNode) e.nextElement();
            if (node.isLeaf()) {
                continue;
            }
            int row = tree.getRowForPath(new TreePath(node.getPath()));
            tree.expandRow(row);
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DefaultMutableTreeNode parent = new DefaultMutableTreeNode("Color", true);
        // f.add(new KeywordMaster().getContent(parent));
        f.setSize(400, 400);
        f.setLocation(200, 200);
        f.setVisible(true);
    }
}

class TreeTransferHandler extends TransferHandler {

    DataFlavor nodesFlavor;
    DataFlavor[] flavors = new DataFlavor[1];
    DefaultMutableTreeNode[] nodesToRemove;
    ListofSpeakers sl = null;
    String speakerName, groupName;

    public TreeTransferHandler(ListofSpeakers sl) {
        try {
            this.sl = sl;
            String mimeType = DataFlavor.javaJVMLocalObjectMimeType
                    + ";class=\""
                    + javax.swing.tree.DefaultMutableTreeNode[].class.getName()
                    + "\"";

            nodesFlavor = new DataFlavor(mimeType);
            flavors[0] = nodesFlavor;
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound: " + e.getMessage());
        }
    }

    public boolean canImport(TransferHandler.TransferSupport support) {
        if (!support.isDrop()) {
            return false;
        }
        support.setShowDropLocation(true);
        if (!support.isDataFlavorSupported(nodesFlavor)) {
            return false;
        }

        // Do not allow a drop on the drag source selections.  
        JTree.DropLocation dl =
                (JTree.DropLocation) support.getDropLocation();
        JTree tree = (JTree) support.getComponent();
        int dropRow = tree.getRowForPath(dl.getPath());
        int[] selRows = tree.getSelectionRows();

        for (int i = 0; i < selRows.length; i++) {

            if (selRows[i] == dropRow) {
                return false;
            }
            if (dl.getPath().getPathCount() != 2) {
                return false;
            }
        }
        // Do not allow MOVE-action drops if a non-leaf node is  
        // selected unless all of its children are also selected.  
        int action = support.getDropAction();
        if (action == MOVE) {
            return haveCompleteNode(tree);
        }
        // Do not allow a non-leaf node to be copied to a level  
        // which is less than its source level.  
        TreePath dest = dl.getPath();
        DefaultMutableTreeNode target =
                (DefaultMutableTreeNode) dest.getLastPathComponent();
        TreePath path = tree.getPathForRow(selRows[0]);
        DefaultMutableTreeNode firstNode =
                (DefaultMutableTreeNode) path.getLastPathComponent();

        if (firstNode.getChildCount() > 0
                && target.getLevel() < firstNode.getLevel()) {
            return false;
        }
        return true;
    }

    private boolean haveCompleteNode(JTree tree) {
        int[] selRows = tree.getSelectionRows();
        TreePath path = tree.getPathForRow(selRows[0]);
        DefaultMutableTreeNode first =
                (DefaultMutableTreeNode) path.getLastPathComponent();
        int childCount = first.getChildCount();
        // first has children and no children are selected.  
        if (childCount > 0 && selRows.length == 1) {
            return false;
        }
        // first may have children.  
        for (int i = 1; i < selRows.length; i++) {
            path = tree.getPathForRow(selRows[i]);
            DefaultMutableTreeNode next =
                    (DefaultMutableTreeNode) path.getLastPathComponent();
            if (first.isNodeChild(next)) {
                // Found a child of first.  
                if (childCount > selRows.length - 1) {
                    // Not all children of first are selected.  
                    return false;
                }
            }
        }
        return true;
    }

    protected Transferable createTransferable(JComponent c) {
        JTree tree = (JTree) c;
        TreePath[] paths = tree.getSelectionPaths();
        if (paths != null) {
            // Make up a node array of copies for transfer and  
            // another for/of the nodes that will be removed in  
            // exportDone after a successful drop.  
            List<DefaultMutableTreeNode> copies =
                    new ArrayList<DefaultMutableTreeNode>();
            List<DefaultMutableTreeNode> toRemove =
                    new ArrayList<DefaultMutableTreeNode>();
            DefaultMutableTreeNode node =
                    (DefaultMutableTreeNode) paths[0].getLastPathComponent();
            DefaultMutableTreeNode copy = copy(node);
            //System.out.println("Tr " + copy + "," + node);
            copies.add(copy);
            toRemove.add(node);
            for (int i = 1; i < paths.length; i++) {
                DefaultMutableTreeNode next =
                        (DefaultMutableTreeNode) paths[i].getLastPathComponent();
                // Do not allow higher level nodes to be added to list.  
                if (next.getLevel() < node.getLevel()) {
                    break;
                } else if (next.getLevel() > node.getLevel()) {  // child node  
                    copy.add(copy(next));

                    // node already contains child  
                } else {
                    // sibling  

                    copies.add(copy(next));
                    toRemove.add(next);
                }
            }
            DefaultMutableTreeNode[] nodes =
                    copies.toArray(new DefaultMutableTreeNode[copies.size()]);
            nodesToRemove =
                    toRemove.toArray(new DefaultMutableTreeNode[toRemove.size()]);
            return new TreeTransferHandler.NodesTransferable(nodes);
        }
        return null;
    }

    /**
     * Defensive copy used in createTransferable.
     */
    private DefaultMutableTreeNode copy(TreeNode node) {
        return new DefaultMutableTreeNode(node);
    }

    protected void exportDone(JComponent source, Transferable data, int action) {
        if ((action & MOVE) == MOVE) {
            JTree tree = (JTree) source;
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            // Remove nodes saved in nodesToRemove in createTransferable.
            try {
                for (int i = 0; i < nodesToRemove.length; i++) {
                    if (nodesToRemove[i].getParent() != null) {
                        //  sl.changeSpeakerGroup(speakerName, groupName, nodesToRemove[i].getParent().toString());
                        model.removeNodeFromParent(nodesToRemove[i]);
                    }
                }
            } catch (Exception er) {
                System.err.println(er);
            }
        }
    }

    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    public boolean importData(TransferHandler.TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }
        // Extract transfer data.  
        DefaultMutableTreeNode[] nodes = null;
        try {
            Transferable t = support.getTransferable();
            nodes = (DefaultMutableTreeNode[]) t.getTransferData(nodesFlavor);
        } catch (UnsupportedFlavorException ufe) {
            System.out.println("UnsupportedFlavor: " + ufe.getMessage());
        } catch (java.io.IOException ioe) {
            System.out.println("I/O error: " + ioe.getMessage());
        }
        // Get drop location info.  
        JTree.DropLocation dl =
                (JTree.DropLocation) support.getDropLocation();
        int childIndex = dl.getChildIndex();
        TreePath dest = dl.getPath();
        DefaultMutableTreeNode parent =
                (DefaultMutableTreeNode) dest.getLastPathComponent();
        JTree tree = (JTree) support.getComponent();
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        // Configure for drop mode.  
        int index = childIndex;    // DropMode.INSERT  
        if (childIndex == -1) {     // DropMode.ON  
            index = parent.getChildCount();
        }
        // Add data to model.  
        try {
            for (int i = 0; i < nodes.length; i++) {
                model.insertNodeInto(nodes[i], parent, index++);
                speakerName = nodes[i].toString();
                groupName = parent.toString();

            }
        } catch (Exception er) {
            System.err.println(er);
        }
        return true;
    }

    public String toString() {
        return getClass().getName();
    }

    public class NodesTransferable implements Transferable {

        DefaultMutableTreeNode[] nodes;

        public NodesTransferable(DefaultMutableTreeNode[] nodes) {
            this.nodes = nodes;
        }

        public Object getTransferData(DataFlavor flavor)
                throws UnsupportedFlavorException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return nodes;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return flavors;
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return nodesFlavor.equals(flavor);
        }
    }
}
