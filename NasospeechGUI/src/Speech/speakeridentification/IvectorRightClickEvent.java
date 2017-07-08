/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.speakeridentification;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import java.awt.Color;

import java.awt.Font;
import javax.swing.JMenu;

/**
 *
 * @author Tatapower SED
 *
 */
public class IvectorRightClickEvent {

    private Ivector iVector;
    private int startSam, endSam;
    private JMenuItem item;
    private JMenu manageMenus;
    private JMenu sortMenus;
    private JMenu settingMenus;

    public IvectorRightClickEvent(Ivector iVector) {
        this.iVector = iVector;
        manageMenus = new JMenu("Manage");
        manageMenus.setFont(new Font("Courier New", Font.PLAIN, 14));
        manageMenus.setBackground(Color.white);
        sortMenus = new JMenu("Sort by");
        sortMenus.setFont(new Font("Courier New", Font.PLAIN, 14));
        sortMenus.setBackground(Color.white);
        settingMenus = new JMenu("Settings");
        settingMenus.setFont(new Font("Courier New", Font.PLAIN, 14));
        settingMenus.setBackground(Color.white);
    }

    public void addMenuItems() {
        iVector.menu.removeAll();

        if (iVector.mframe.pWave.audioInputStream != null) {
            item = new JMenuItem(" Train ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    iVector.mframe.createSIDTrainFrame(iVector.mframe.pWave.fileName, iVector);

                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            iVector.menu.add(item);

            item = new JMenuItem(" Test ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    iVector.mframe.createSIDTestFrame(iVector.mframe.pWave.fileName, iVector);

                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            iVector.menu.add(item);



            item = new JMenuItem(" Clear ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    iVector.clearAll();

                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            iVector.menu.add(item);
        }
        //[paste]


    }
}
