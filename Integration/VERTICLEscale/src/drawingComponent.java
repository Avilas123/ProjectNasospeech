
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DoD
 */



public class drawingComponent extends JComponent {
    
 
    
    
    
   
    
    @Override
    public void paintComponent(Graphics g){
        
        Graphics2D g2 = (Graphics2D) g;
        
        Prob object = new Prob();
        double xxx = object.getProb();
        int pixel = object.probToPixel();
        System.out.println("xxx = "+xxx  + "  pixel = "+object.probToPixel());
        //////////////**********THE RECTANGULAR DIVISIONS*********/////////
        Rectangle rect1  = new Rectangle(60, 450, 100, 150); 
        //g2.setColor(new Color(0,0,255));                            //(4) BOTTOM//
        g2.setColor(new Color(17,122,101)); 
        g2.fill(rect1);
        
         Rectangle rect2  = new Rectangle(60, 300, 100, 150); 
        //g2.setColor(new Color(0,0,255));                            //(3)//
        g2.setColor(new Color(52, 152, 219));  
        g2.fill(rect2);
        
        Rectangle rect3  = new Rectangle(60,150, 100, 150); 
        g2.setColor(new Color(241, 196, 15));                         //(2)//
        //g2.setColor(Color.RED);                                   
        g2.fill(rect3);
        
        
         Rectangle rect4  = new Rectangle(60, 10, 100, 150); 
        g2.setColor(new Color(231, 76, 60));                            //(1) TOP//
        //g2.setColor(Color.RED); 
        g2.fill(rect4);
        ////////////////(***********THE RECTANGULAR DIVISIONS******/////////
        
        
          
          ///////////*****AXIS numbers*****////////
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawString("1.0",30, 15);
        g2.drawString("0.9",30,65);
        g2.drawString("0.8",30, 125);
        g2.drawString("0.7",30, 185);
        g2.drawString("0.6",30, 245);
        g2.drawString("0.5",30, 305);
        g2.drawString("0.4",30, 365);
        g2.drawString("0.3",30, 425);
        g2.drawString("0.2",30, 485);
        g2.drawString("0.1",30, 545);
        g2.drawString("0.0",30, 605);
        
        ////////////********AXIS numbers*****///////////
        
        
        
         /////////////////the AXIS//////////
        
         Point2D.Double pa = new Point2D.Double(60,10);
        Point2D.Double pb = new Point2D.Double(60,600);
        
        g2.setColor(Color.BLACK);
        Line2D.Double la = new Line2D.Double(pa,pb);
        g2.draw(la);
        
        Point2D.Double pc = new Point2D.Double(60, 600);
        Point2D.Double pd = new Point2D.Double(160,600);
        
        g2.setColor(Color.BLACK);
        Line2D.Double lb = new Line2D.Double(pc,pd);
        g2.draw(lb);
        
         Point2D.Double pe = new Point2D.Double(160, 10);
        Point2D.Double pf = new Point2D.Double(160,600);
        
        g2.setColor(Color.BLACK);
        Line2D.Double lc = new Line2D.Double(pe,pf);
        g2.draw(lc);
        
         Point2D.Double pg = new Point2D.Double(60, 10);
        Point2D.Double ph = new Point2D.Double(160,10);
        
        g2.setColor(Color.BLACK);
        Line2D.Double ld = new Line2D.Double(pg,ph);
        g2.draw(ld);
        //////////////////THE axis//////////
        
        
        //// call the prob to pixel func
       // int pixel = probToPixel();
      //  System.out.println("pixel = "+pixel);
        //////////////******** THE MARKER**********///////////////
        {
             Line2D.Double L0 = new Line2D.Double(70, pixel-3, 150 , pixel-3);
            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.WHITE);
            g2.draw(L0);
            
            Line2D.Double L1 = new Line2D.Double(70, pixel,150 , pixel);
            g2.setStroke(new BasicStroke(4));
            g2.setColor(new Color(44,62,80));
            g2.draw(L1);
            
             Line2D.Double L2 = new Line2D.Double(70,pixel+3, 150 , pixel+3);
            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.WHITE);
            g2.draw(L2);
            
             Line2D.Double L3 = new Line2D.Double(85, pixel, 135 , pixel);
            g2.setStroke(new BasicStroke(8));
            g2.setColor(Color.BLACK);
            g2.draw(L3);
            
            
            Line2D.Double L4 = new Line2D.Double(60,pixel,160 , pixel);
            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.BLACK);
            g2.draw(L4);
            
             /*Line2D.Double L1 = new Line2D.Double(66, 5,66 , 105);
            g2.setStroke(new BasicStroke(4));
            g2.setColor(Color.BLACK);
            g2.draw(L1);
            
             Line2D.Double L1 = new Line2D.Double(66, 5,66 , 105);
            g2.setStroke(new BasicStroke(4));
            g2.setColor(Color.BLACK);
            g2.draw(L1);*/
            
            
            Ellipse2D.Double E2 = new Ellipse2D.Double(193, 50, 10, 10);
            //g2.fill(E2);
            Ellipse2D.Double E3 = new Ellipse2D.Double(179, 50, 10, 10);
            //g2.fill(E3);
        }
        
        //////////***********RESULT()************////////////
        
        
        
        
        
        ////////////THE AXIS MARKS(small)///////
        
        Point2D.Double p3 = new Point2D.Double(55,10);
        Point2D.Double p4 = new Point2D.Double(65,10);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l3 = new Line2D.Double(p3,p4);
        g2.draw(l3);
        
        
        Point2D.Double p5 = new Point2D.Double(55,60);
        Point2D.Double p6 = new Point2D.Double(65,60);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l4 = new Line2D.Double(p5,p6);
        g2.draw(l4);
        
        
        
       Point2D.Double p7 = new Point2D.Double(55,120);
        Point2D.Double p8 = new Point2D.Double(65,120);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l5 = new Line2D.Double(p7,p8);
        g2.draw(l5);
        
        
        Point2D.Double p9 = new Point2D.Double(55,180);
        Point2D.Double p10 = new Point2D.Double(65,180);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l6 = new Line2D.Double(p9,p10);
        g2.draw(l6);
        
        
        
        Point2D.Double p11 = new Point2D.Double(55,240);
        Point2D.Double p12 = new Point2D.Double(65,240);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l7 = new Line2D.Double(p11,p12);
        g2.draw(l7);
        
        
        
        Point2D.Double p13 = new Point2D.Double(55,300);
        Point2D.Double p14 = new Point2D.Double(65,300);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l8 = new Line2D.Double(p13,p14);
        g2.draw(l8);
        
        
        
        Point2D.Double p15 = new Point2D.Double(55,360);
        Point2D.Double p16 = new Point2D.Double(65,360);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l9 = new Line2D.Double(p15,p16);
        g2.draw(l9);
        
        
        Point2D.Double p17 = new Point2D.Double(55, 420);
        Point2D.Double p18 = new Point2D.Double(65,420);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l10 = new Line2D.Double(p17,p18);
        g2.draw(l10);
        
        Point2D.Double p19 = new Point2D.Double(55,480);
        Point2D.Double p20 = new Point2D.Double(65,480);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l11 = new Line2D.Double(p19,p20);
        g2.draw(l11);
        
         Point2D.Double p21 = new Point2D.Double(55,540);
        Point2D.Double p22 = new Point2D.Double(65,540);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l12 = new Line2D.Double(p21,p22);
        g2.draw(l12);
        
         Point2D.Double p23 = new Point2D.Double(55,600);
        Point2D.Double p24= new Point2D.Double(65,600);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l13 = new Line2D.Double(p23,p24);
        g2.draw(l13);
        ///////////////THE AXIS MARKS(small)////////
        
        
        
        
        ///////////THE SURROUNDING RECTANGLE/////////////
        
        
        
          Rectangle rect5  = new Rectangle(50, 365, 120, 150); 
        //g2.setColor(new Color(0,0,255));                            
        g2.setColor(Color.BLACK); 
        //g2.setStroke(new BasicStroke(2));
        g2.draw(rect5);
        
        
        
        
    }
   
}


