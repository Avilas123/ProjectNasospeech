


import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.Scanner;
import javax.swing.JPanel;


public class VERTICLEscale extends drawingComponent{
    
    public static void main(String[] args){
        
        //Graphic2D g1 = (Graphic2D) p;
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
        //DC.paintComponent(P);
        //Line2D.Double E = new Line2D.Double(35,35,66,66);
        //g1.draw(E);
        
    }
}
        
   