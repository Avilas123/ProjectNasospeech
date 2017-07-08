/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.kws;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

/**
 *
 * @author Tatapower SED
 *
 */
public class TreeIcon implements Icon {

    private static int SIZE = 0;

    public TreeIcon() {
    }

    public int getIconWidth() {
        return SIZE;
    }

    public int getIconHeight() {
        return SIZE;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        // System.out.println(c.getWidth() + " " + c.getHeight() + " " + x + " " + y);
    }
}