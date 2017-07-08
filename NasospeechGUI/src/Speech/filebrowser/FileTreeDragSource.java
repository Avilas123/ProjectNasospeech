/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.filebrowser;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Tatapower SED
 *
 */
class FileTreeDragSource implements DragGestureListener, DragSourceListener {

    public FileTreeDragSource(FileTree tree) {
        this.tree = tree;

        // Use the default DragSource
        DragSource dragSource = DragSource.getDefaultDragSource();

        // Create a DragGestureRecognizer and
        // register as the listener
        dragSource.createDefaultDragGestureRecognizer(tree,
                DnDConstants.ACTION_COPY_OR_MOVE, this);
    }

    // Implementation of DragGestureListener interface.
    public void dragGestureRecognized(DragGestureEvent dge) {
        // Get the mouse location and convert it to
        // a location within the tree.
        Point location = dge.getDragOrigin();
        TreePath dragPath = tree.getPathForLocation(location.x, location.y);
        if (dragPath != null && tree.isPathSelected(dragPath)) {
            // Get the list of selected files and create a Transferable
            // The list of files and the is saved for use when
            // the drop completes.
            paths = tree.getSelectionPaths();
            if (paths != null && paths.length > 0) {
                dragFiles = new File[paths.length];
                for (int i = 0; i < paths.length; i++) {
                    String pathName = tree.getPathName(paths[i]);
                    dragFiles[i] = new File(pathName);
                }

                Transferable transferable = new FileListTransferable(dragFiles);
                dge.startDrag(null, transferable, this);
            }
        }
    }

    // Implementation of DragSourceListener interface
    public void dragEnter(DragSourceDragEvent dsde) {
        DnDUtils.debugPrintln("Drag Source: dragEnter, drop action = "
                + DnDUtils.showActions(dsde.getDropAction()));
    }

    public void dragOver(DragSourceDragEvent dsde) {
        DnDUtils.debugPrintln("Drag Source: dragOver, drop action = "
                + DnDUtils.showActions(dsde.getDropAction()));
    }

    public void dragExit(DragSourceEvent dse) {
        DnDUtils.debugPrintln("Drag Source: dragExit");
    }

    public void dropActionChanged(DragSourceDragEvent dsde) {
        DnDUtils.debugPrintln("Drag Source: dropActionChanged, drop action = "
                + DnDUtils.showActions(dsde.getDropAction()));
    }

    public void dragDropEnd(DragSourceDropEvent dsde) {
        DnDUtils.debugPrintln("Drag Source: drop completed, drop action = "
                + DnDUtils.showActions(dsde.getDropAction()) + ", success: "
                + dsde.getDropSuccess());
        // If the drop action was ACTION_MOVE,
        // the tree might need to be updated.
        if (dsde.getDropAction() == DnDConstants.ACTION_MOVE) {
            final File[] draggedFiles = dragFiles;
            final TreePath[] draggedPaths = paths;

            Timer tm = new Timer(200, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    // Check whether each of the dragged files exists.
                    // If it does not, we need to remove the node
                    // that represents it from the tree.
                    for (int i = 0; i < draggedFiles.length; i++) {
                        if (draggedFiles[i].exists() == false) {
                            // Remove this node
                            DefaultMutableTreeNode node = (DefaultMutableTreeNode) draggedPaths[i]
                                    .getLastPathComponent();
                            ((DefaultTreeModel) tree.getModel())
                                    .removeNodeFromParent(node);
                        }
                    }
                }
            });
            tm.setRepeats(false);
            tm.start();
        }
    }
    protected FileTree tree; // The associated tree
    protected File[] dragFiles; // Dragged files
    protected TreePath[] paths; // Dragged paths
}

class FileListTransferable implements Transferable {

    public FileListTransferable(File[] files) {
        fileList = new ArrayList();
        for (int i = 0; i < files.length; i++) {
            fileList.add(files[i]);
        }
    }

    // Implementation of the Transferable interface
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.javaFileListFlavor};
    }

    public boolean isDataFlavorSupported(DataFlavor fl) {
        return fl.equals(DataFlavor.javaFileListFlavor);
    }

    public Object getTransferData(DataFlavor fl) {
        if (!isDataFlavorSupported(fl)) {
            return null;
        }

        return fileList;
    }
    List fileList; // The list of files
}
