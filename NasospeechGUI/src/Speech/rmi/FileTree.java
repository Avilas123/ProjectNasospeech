/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

/**
 *
 * @author Tatapower SED
 *
 */
import java.io.File;
import java.util.Collections;
import java.util.Vector;


import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Display a file system in a JTree view
 *
 * @version $Id: FileTree.java,v 1.9 2004/02/23 03:39:22 ian Exp $
 * @author Ian Darwin
 */
public class FileTree {

    /**
     * Construct a FileTree
     */
    private static String fnodeName;

    /**
     * Add nodes from under "dir" into curTop. Highly recursive.
     */
    public static DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
        String curPath = dir.getPath();
        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(dir.getName());
        if (curTop != null) { // should only be null at root
            curTop.add(curDir);
        }
        Vector ol = new Vector();
        String[] tmp = dir.list();
        for (int i = 0; i < tmp.length; i++) {
            ol.addElement(tmp[i]);
        }
        Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
        File f;
        Vector files = new Vector();
        // Make two passes, one for Dirs and one for Files. This is #1.
        for (int i = 0; i < ol.size(); i++) {
            String thisObject = (String) ol.elementAt(i);
            String newPath;
            if (curPath.equals(".")) {
                newPath = thisObject;
            } else {
                newPath = curPath + File.separator + thisObject;
            }
            if ((f = new File(newPath)).isDirectory()) {
                addNodes(curDir, f);
            } else {
                files.addElement(thisObject);
            }
        }
        // Pass two: for files.
        for (int fnum = 0; fnum < files.size(); fnum++) {
            fnodeName = (files.elementAt(fnum).toString()).toLowerCase().trim();
            if (fnodeName.endsWith(".wav") || fnodeName.endsWith(".mp3") || fnodeName.endsWith(".wma")) {
                curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
            }
        }
        return curDir;
    }
}
