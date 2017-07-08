/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.kws;

/**
 *
 * @author Tatapower SED
 *
 */
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.EventObject;
import java.util.Vector;
import javax.swing.AbstractCellEditor;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;

import javax.swing.tree.TreePath;

//----------------------------------
public class SelectedKeywordTree {

    public DefaultMutableTreeNode rootNode;
    public DefaultMutableTreeNode subRootNode;
    public JTree tree;

    public JScrollPane getSelectedKeyword(Vector groupVector) {
        rootNode = new DefaultMutableTreeNode(new CheckBoxNode("List of keywords", false), true);
        subRootNode = new DefaultMutableTreeNode(new CheckBoxNode("List of keywords", false), true);
        for (int i = 0; i < groupVector.size(); i++) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(new CheckBoxNode(((Vector) groupVector.elementAt(i)).elementAt(0).toString(), false));
            for (int j = 1; j < ((Vector) groupVector.elementAt(i)).size(); j++) {
                node.add(new DefaultMutableTreeNode(new CheckBoxNode(((Vector) groupVector.elementAt(i)).elementAt(j).toString(), false)));
            }
            subRootNode.add(node);
        }
        rootNode.add(subRootNode);
        tree = new JTree(rootNode);
        tree.setToggleClickCount(1);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
        tree.setCellRenderer(renderer);
        tree.setCellEditor(new CheckBoxNodeEditor(tree));
        tree.setEditable(true);

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Submit")) {
                    for (int i = 0; i < rootNode.getChildCount(); i++) {
                        for (int j = 0; j < rootNode.getChildAt(i).getChildCount(); j++) {
                            for (int k = 0; k < rootNode.getChildAt(i).getChildAt(j).getChildCount(); k++) {
                                DefaultMutableTreeNode node = (DefaultMutableTreeNode) (rootNode.getChildAt(i)).getChildAt(j).getChildAt(k);
                                CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();
                                if (cbn.isSelected()) {
                                    System.out.println("1 " + rootNode.getChildAt(i).getChildAt(j).getChildAt(k).toString());

                                }
                            }
                        }
                    }
                }
            }
        });



        tree.setModel(new DefaultTreeModel(rootNode) {
            public void valueForPathChanged(TreePath path, Object newValue) {
                Object currNode = path.getLastPathComponent();
                boolean isAllChiledSelectedfull = true;
                super.valueForPathChanged(path, newValue);
                try {
                    if ((currNode != null) && (currNode instanceof DefaultMutableTreeNode)) {
                        DefaultMutableTreeNode editedNode = (DefaultMutableTreeNode) currNode;
                        CheckBoxNode newCBN = (CheckBoxNode) newValue;

                        if (!editedNode.isLeaf()) {
                            for (int i = 0; i < editedNode.getChildCount(); i++) {
                                DefaultMutableTreeNode node = (DefaultMutableTreeNode) editedNode.getChildAt(i);
                                CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();
                                cbn.setSelected(newCBN.isSelected());
                                for (int j = 0; j < editedNode.getChildAt(i).getChildCount(); j++) {
                                    node = (DefaultMutableTreeNode) (editedNode.getChildAt(i)).getChildAt(j);
                                    cbn = (CheckBoxNode) node.getUserObject();
                                    cbn.setSelected(newCBN.isSelected());
                                }


                            }

                            for (int i = 0; i < editedNode.getParent().getChildCount(); i++) {
                                DefaultMutableTreeNode node = (DefaultMutableTreeNode) editedNode.getParent().getChildAt(i);
                                CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();

                                if (!cbn.isSelected()) {
                                    isAllChiledSelectedfull = false;
                                    // System.out.println("2 " +editedNode.getParent().getChildAt(i));
                                }
                            }
                            if (isAllChiledSelectedfull) {
                                DefaultMutableTreeNode node = (DefaultMutableTreeNode) editedNode.getParent();
                                CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();
                                cbn.setSelected(isAllChiledSelectedfull);
                                // System.out.println("3 " +editedNode.getParent().getChildAt(0));
                            }

                        } else {
                            boolean isAllChiledSelected = true;

                            for (int i = 0; i < editedNode.getParent().getChildCount(); i++) {
                                DefaultMutableTreeNode node = (DefaultMutableTreeNode) editedNode.getParent().getChildAt(i);
                                CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();

                                if (!cbn.isSelected()) {
                                    isAllChiledSelected = false;

                                    try {
                                        if (editedNode.getParent().getParent() != null) {
                                            DefaultMutableTreeNode nodeP = (DefaultMutableTreeNode) editedNode.getParent().getParent();
                                            CheckBoxNode cbnP = (CheckBoxNode) nodeP.getUserObject();
                                            cbnP.setSelected(isAllChiledSelected);
                                        }

                                    } catch (Exception er) {
                                        System.err.println(er);
                                    }

                                    // System.out.println("2 " +editedNode.getParent().getChildAt(i));
                                }
                            }

                            if (isAllChiledSelected) {
                                DefaultMutableTreeNode node = (DefaultMutableTreeNode) editedNode.getParent();
                                CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();
                                cbn.setSelected(isAllChiledSelected);


                                try {
                                    if (editedNode.getParent().getParent() != null) {
                                        boolean parenSelected = true;
                                        for (int i = 0; i < editedNode.getParent().getParent().getChildCount(); i++) {
                                            DefaultMutableTreeNode nodeA = (DefaultMutableTreeNode) editedNode.getParent().getParent().getChildAt(i);
                                            CheckBoxNode cbnA = (CheckBoxNode) nodeA.getUserObject();

                                            if (!cbnA.isSelected()) {
                                                parenSelected = false;
                                            }
                                        }
                                        if (parenSelected) {
                                            DefaultMutableTreeNode nodeP = (DefaultMutableTreeNode) editedNode.getParent().getParent();
                                            CheckBoxNode cbnP = (CheckBoxNode) nodeP.getUserObject();
                                            cbnP.setSelected(isAllChiledSelected);
                                        }
                                    }

                                } catch (Exception er) {
                                    System.err.println(er);
                                }
                                // System.out.println("3 " +editedNode.getParent().getChildAt(0));
                            }
                        }

                        if (!newCBN.isSelected()) {
                            DefaultMutableTreeNode node = (DefaultMutableTreeNode) editedNode.getParent();
                            if (node.getUserObject() instanceof CheckBoxNode) {
                                ((CheckBoxNode) node.getUserObject()).setSelected(false);
                            }
                        }
                    }
                } catch (Exception er) {
                    System.err.println(er);
                }
            }
        });

        return new JScrollPane(tree);
    }

    JTree getSelectedKeywordTree(Vector groupTree) {
        rootNode = new DefaultMutableTreeNode(new CheckBoxNode("List of keywords", false), true);
        subRootNode = new DefaultMutableTreeNode(new CheckBoxNode("List of keywords", false), true);
        for (int i = 0; i < groupTree.size(); i++) {
//            DefaultMutableTreeNode node = new DefaultMutableTreeNode(new CheckBoxNode(((Vector) groupTree.elementAt(i)).elementAt(0).toString(), false));
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(((Vector) groupTree.elementAt(i)).elementAt(0).toString());
            for (int j = 1; j < ((Vector) groupTree.elementAt(i)).size(); j++) {
//                node.add(new DefaultMutableTreeNode(new CheckBoxNode(((Vector) groupTree.elementAt(i)).elementAt(j).toString(), false)));
                node.add(new DefaultMutableTreeNode(((Vector) groupTree.elementAt(i)).elementAt(j).toString()));
//                        /*new CheckBoxNode(((Vector) groupTree.elementAt(i)).elementAt(j).toString(), false))*/);
            }
            subRootNode.add(node);
        }
        rootNode.add(subRootNode);
        tree = new JTree(rootNode);
        tree.setToggleClickCount(1);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        //    CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
        //     tree.setCellRenderer(renderer);
        //     tree.setCellEditor(new CheckBoxNodeEditor(tree));
        tree.setEditable(true);
        return tree;
        /*JButton submit = new JButton("Submit");
         submit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         if (e.getActionCommand().equals("Submit")) {
         for (int i = 0; i < rootNode.getChildCount(); i++) {
         for (int j = 0; j < rootNode.getChildAt(i).getChildCount(); j++) {
         for (int k = 0; k < rootNode.getChildAt(i).getChildAt(j).getChildCount(); k++) {
         DefaultMutableTreeNode node = (DefaultMutableTreeNode) (rootNode.getChildAt(i)).getChildAt(j).getChildAt(k);
         CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();
         if (cbn.isSelected()) {
         System.out.println("1 " + rootNode.getChildAt(i).getChildAt(j).getChildAt(k).toString());
                              
         }
         }
         }
         }
         }
         }
         });

       

         tree.setModel(new DefaultTreeModel(rootNode) {
         public void valueForPathChanged(TreePath path, Object newValue) {
         Object currNode = path.getLastPathComponent();
         boolean isAllChiledSelectedfull = true;
         super.valueForPathChanged(path, newValue);
         try {
         if ((currNode != null) && (currNode instanceof DefaultMutableTreeNode)) {
         DefaultMutableTreeNode editedNode = (DefaultMutableTreeNode) currNode;
         CheckBoxNode newCBN = (CheckBoxNode) newValue;

         if (!editedNode.isLeaf()) {
         for (int i = 0; i < editedNode.getChildCount(); i++) {
         DefaultMutableTreeNode node = (DefaultMutableTreeNode) editedNode.getChildAt(i);
         CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();
         cbn.setSelected(newCBN.isSelected());
         for (int j = 0; j < editedNode.getChildAt(i).getChildCount(); j++) {
         node = (DefaultMutableTreeNode) (editedNode.getChildAt(i)).getChildAt(j);
         cbn = (CheckBoxNode) node.getUserObject();
         cbn.setSelected(newCBN.isSelected());
         }


         }

         for (int i = 0; i < editedNode.getParent().getChildCount(); i++) {
         DefaultMutableTreeNode node = (DefaultMutableTreeNode) editedNode.getParent().getChildAt(i);
         CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();

         if (!cbn.isSelected()) {
         isAllChiledSelectedfull = false;
         // System.out.println("2 " +editedNode.getParent().getChildAt(i));
         }
         }
         if (isAllChiledSelectedfull) {
         DefaultMutableTreeNode node = (DefaultMutableTreeNode) editedNode.getParent();
         CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();
         cbn.setSelected(isAllChiledSelectedfull);
         // System.out.println("3 " +editedNode.getParent().getChildAt(0));
         }

         } else {
         boolean isAllChiledSelected = true;

         for (int i = 0; i < editedNode.getParent().getChildCount(); i++) {
         DefaultMutableTreeNode node = (DefaultMutableTreeNode) editedNode.getParent().getChildAt(i);
         CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();

         if (!cbn.isSelected()) {
         isAllChiledSelected = false;

         try {
         if (editedNode.getParent().getParent() != null) {
         DefaultMutableTreeNode nodeP = (DefaultMutableTreeNode) editedNode.getParent().getParent();
         CheckBoxNode cbnP = (CheckBoxNode) nodeP.getUserObject();
         cbnP.setSelected(isAllChiledSelected);
         }

         } catch (Exception er) {
         System.err.println(er);
         }

         // System.out.println("2 " +editedNode.getParent().getChildAt(i));
         }
         }

         if (isAllChiledSelected) {
         DefaultMutableTreeNode node = (DefaultMutableTreeNode) editedNode.getParent();
         CheckBoxNode cbn = (CheckBoxNode) node.getUserObject();
         cbn.setSelected(isAllChiledSelected);


         try {
         if (editedNode.getParent().getParent() != null) {
         boolean parenSelected = true;
         for (int i = 0; i < editedNode.getParent().getParent().getChildCount(); i++) {
         DefaultMutableTreeNode nodeA = (DefaultMutableTreeNode) editedNode.getParent().getParent().getChildAt(i);
         CheckBoxNode cbnA = (CheckBoxNode) nodeA.getUserObject();

         if (!cbnA.isSelected()) {
         parenSelected = false;
         }
         }
         if (parenSelected) {
         DefaultMutableTreeNode nodeP = (DefaultMutableTreeNode) editedNode.getParent().getParent();
         CheckBoxNode cbnP = (CheckBoxNode) nodeP.getUserObject();
         cbnP.setSelected(isAllChiledSelected);
         }
         }

         } catch (Exception er) {
         System.err.println(er);
         }
         // System.out.println("3 " +editedNode.getParent().getChildAt(0));
         }
         }

         if (!newCBN.isSelected()) {
         DefaultMutableTreeNode node = (DefaultMutableTreeNode) editedNode.getParent();
         if (node.getUserObject() instanceof CheckBoxNode) {
         ((CheckBoxNode) node.getUserObject()).setSelected(false);
         }
         }
         }
         } catch (Exception er) {
         System.err.println(er);
         }
         }
         });
        
         return tree;*/
    }
}

class CheckBoxNodeRenderer implements TreeCellRenderer {

    private JCheckBox leafRenderer = new JCheckBox();
    private DefaultTreeCellRenderer nonLeafRenderer = new DefaultTreeCellRenderer();
    Color selectionBorderColor, selectionForeground, selectionBackground,
            textForeground, textBackground;

    protected JCheckBox getLeafRenderer() {
        return leafRenderer;
    }

    public CheckBoxNodeRenderer() {
        Font fontValue;
        fontValue = UIManager.getFont("Tree.font");
        if (fontValue != null) {
            leafRenderer.setFont(fontValue);
        }
        Boolean booleanValue = (Boolean) UIManager
                .get("Tree.drawsFocusBorderAroundIcon");
        leafRenderer.setFocusPainted((booleanValue != null)
                && (booleanValue.booleanValue()));

        selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
        selectionForeground = UIManager.getColor("Tree.selectionForeground");
        selectionBackground = UIManager.getColor("Tree.selectionBackground");
        textForeground = UIManager.getColor("Tree.textForeground");
        textBackground = UIManager.getColor("Tree.textBackground");
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {

        Component returnValue;

        String stringValue = tree.convertValueToText(value, selected,
                expanded, leaf, row, false);
        leafRenderer.setText(stringValue);
        leafRenderer.setSelected(false);

        leafRenderer.setEnabled(tree.isEnabled());

        if (selected) {
            leafRenderer.setForeground(selectionForeground);
            leafRenderer.setBackground(selectionBackground);

        } else {
            leafRenderer.setForeground(textForeground);
            leafRenderer.setBackground(textBackground);

        }

        if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
            Object userObject = ((DefaultMutableTreeNode) value)
                    .getUserObject();
            if (userObject instanceof CheckBoxNode) {
                CheckBoxNode node = (CheckBoxNode) userObject;
                leafRenderer.setText(node.getText());
                leafRenderer.setSelected(node.isSelected());
            }
        }
        returnValue = leafRenderer;
        return returnValue;
    }
}
//-------------------------------------------------
class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor {

    CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
    ChangeEvent changeEvent = null;
    JTree tree1;
    DefaultMutableTreeNode editedNode;

    public CheckBoxNodeEditor(JTree tree) {
        this.tree1 = tree;
    }

    public Object getCellEditorValue() {
        JCheckBox checkbox = renderer.getLeafRenderer();
        CheckBoxNode checkBoxNode = new CheckBoxNode(checkbox.getText(),
                checkbox.isSelected());
        return checkBoxNode;
    }

    public boolean isCellEditable(EventObject event) {
        boolean returnValue = false;
        if (event instanceof MouseEvent) {
            MouseEvent mouseEvent = (MouseEvent) event;
            TreePath path = tree1.getPathForLocation(mouseEvent.getX(),
                    mouseEvent.getY());
            if (path != null) {
                Object node = path.getLastPathComponent();
                if ((node != null) && (node instanceof DefaultMutableTreeNode)) {
                    editedNode = (DefaultMutableTreeNode) node;
                    Object userObject = editedNode.getUserObject();
                    Rectangle r = tree1.getPathBounds(path);
                    int x = mouseEvent.getX() - r.x;
                    int y = mouseEvent.getY() - r.y;
                    JCheckBox checkbox = renderer.getLeafRenderer();
                    checkbox.setText("");
                    returnValue = userObject instanceof CheckBoxNode && x > 0 && x < checkbox.getPreferredSize().width;
                }
            }
        }
        return returnValue;
    }

    public Component getTreeCellEditorComponent(final JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row) {

        Component editor = renderer.getTreeCellRendererComponent(tree, value,
                true, expanded, leaf, row, true);

        ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                tree.repaint();
                fireEditingStopped();
            }
        };

        if (editor instanceof JCheckBox) {
            ((JCheckBox) editor).addItemListener(itemListener);
        }

        return editor;
    }
}

//-----------------------------------------------
class CheckBoxNode {

    String text;
    boolean selected;

    public CheckBoxNode(String text, boolean selected) {
        this.text = text;
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean newValue) {
        selected = newValue;
    }

    public String getText() {
        return text;
    }

    public void setText(String newValue) {
        text = newValue;
    }

    public String toString() {
        return getClass().getName() + "[" + text + "/" + selected + "]";
    }
}