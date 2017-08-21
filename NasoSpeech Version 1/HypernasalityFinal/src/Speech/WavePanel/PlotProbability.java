/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.WavePanel;

import javax.swing.JFrame;

/**
 *
 * @author user
 */
public class PlotProbability {
   public void plotfunction(){  
    JFrame window = new JFrame();
        
        //JPanel panel = new JPanel();
        //panel.setBackground(Color.BLACK);
       //llllwindow.getContentPane().add(panel);
        
        window.setSize(640, 640);
        window.setTitle("This is a test");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    
        
        Prob newProb = new  Prob();
        newProb.setProb(0.75);
        drawingComponent DC = new drawingComponent(); 
        window.add(DC);
   }
    
    
    
    
}
