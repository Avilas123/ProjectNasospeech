/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//Used in RightClickEvent.java and RightClickEventPhoneme.java
package Speech.addLangugeKeywords;

import Speech.WavePanel.CutAudioWave;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import Speech.gui.MainFrame;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
/**
 *
 * @author SWN TATA PWR PC3
 */
public class AddKeywordMenu {
    private JMenuItem item;
   private MainFrame mainFrame; 
 public AddKeywordMenu(javax.swing.JPopupMenu parentMenu,MainFrame mf)
 {
 JMenu addKeywordMenu = new JMenu(" Add Keyword ");
           
            mainFrame = mf;
//             item = new JMenuItem(" Assamese ");
//            item.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    addKeywordNew("Assamese", mainFrame);
//                    
//                }
//            });
//            item.setFont(new Font("Courier New", Font.PLAIN, 15));
//            item.setBackground(Color.white);
//            addKeywordMenu.add(item);                    
//            
            item = new JMenuItem(" Hindi ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addKeywordNew("Hindi", mainFrame);
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            addKeywordMenu.add(item);            
            
//            item = new JMenuItem(" Telugu  ");
//            item.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    addKeywordNew("Telugu", mainFrame);
//                    
//                }
//            });
//            item.setFont(new Font("Courier New", Font.PLAIN, 15));
//            item.setBackground(Color.white);
//            addKeywordMenu.add(item);
//            
            item = new JMenuItem(" English ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {                             
                    addKeywordNew("English",mainFrame);
                    
                    
               }
            });
             item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            addKeywordMenu.add(item);
            
            addKeywordMenu.setFont(new Font("Courier New", Font.PLAIN, 15));
            parentMenu.add(addKeywordMenu);
 
 }   
 
 
  public void addKeywordNew(String lang, MainFrame mf) {
      

         try {
    mf.createAddNewInternalFrame("Add New Keyword ("+lang+")",lang);
   
            }
        catch (Exception er) {
            System.err.println(er);
        }

    }
 
}
