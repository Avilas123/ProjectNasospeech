/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.kws.newTreeCode;

import java.awt.datatransfer.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.tree.TreePath;

/**
 *
 * @author Tatapower SED
 *
 */
public class DnDTreeList implements Transferable, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1270874212613332692L;
    /**
     * Data flavor that allows a DnDTreeList to be extracted from a transferable
     * object
     */
    public final static DataFlavor DnDTreeList_FLAVOR = new DataFlavor(DnDTreeList.class,
            "Drag and drop list");
    /**
     * List of flavors this DnDTreeList can be retrieved as. Currently only
     * supports DnDTreeList_FLAVOR
     */
    protected static DataFlavor[] flavors = {DnDTreeList.DnDTreeList_FLAVOR};
    /**
     * Nodes to transfer
     */
    protected ArrayList<TreePath> nodes;

    /**
     * @param selection
     */
    public DnDTreeList(ArrayList<TreePath> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<TreePath> getNodes() {
        return this.nodes;
    }

    /**
     * @param flavor
     * @return
     * @throws UnsupportedFlavorException
     * @throws IOException
	 *
     */
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (this.isDataFlavorSupported(flavor)) {
            return this;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }

    /**
     * @return
	 *
     */
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        // TODO Auto-generated method stub
        return DnDTreeList.flavors;
    }

    /**
     * @param flavor
     * @return
	 *
     */
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        DataFlavor[] flavs = this.getTransferDataFlavors();
        for (int i = 0; i < flavs.length; i++) {
            if (flavs[i].equals(flavor)) {
                return true;
            }
        }
        return false;
    }
}