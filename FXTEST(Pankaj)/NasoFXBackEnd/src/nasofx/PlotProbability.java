/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author user
 */
public class PlotProbability  {
    double valuec;    
    String file="xxx";
    //public String file1="xxx";
    
   // public PlotWave pWave;
    
  /*  public PlotProbability() 
    {
        this.valuec = RightClickEvent.getvaluefromc();
      //  this.file=
        System.out.print("got the value"+this.valuec);
    }
*/
     public PlotProbability(NasoFX naso) 
     {  
         String file,file1;
      //  this.file= pWave.ReturnFilename();
        file1=this.file;
        System.out.print("got pankaj\n"+this.file);
        //System.out.print("got pankaj\n"+this.file);
        this.valuec=naso.getvaluefromc();
         System.out.print("got the value"+this.valuec);
      }

   /* PlotProbability() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  */
     
    // public void getfille(PlotWave pWave)
     //{  
       //  System.out.print("got the new file\n"+pWave.ReturnFilename());
    // }
//     PlotProbability plot=new  PlotProbability(pWave);
   public void plotfunction()
   { 
      //PlotProbability object=new PlotProbability(pWave); 
       //System.out.print("object printed"+object.file);
       JFrame window = new JFrame();
        
        //JPanel panel = new JPanel();
        //panel.setBackground(Color.BLACK);
       //llllwindow.getContentPane().add(panel);
     //   getfille(pWave);
        window.setSize(785,785);
        window.setTitle("Hypernasality");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        //window.setBackground(Color.WHITE);
        //String file;
       // file = PlotProbability.this.file;
       // System.out.print("file name printed in plotprobability"+file);
        Prob newProb = new  Prob();
        newProb.setProb(this.valuec);
        
        drawingComponent DC = new drawingComponent(); 
        window.add(DC);
        window.setDefaultCloseOperation(1);
        String file1 = getfile();
        drawingComponent DC1 = new drawingComponent(this);
        window.add(DC1);
       // window.setDefaultCloseOperation(1);
        
   }
   public void plotfunction1()
   {
       //Returnfilename rt=new Returnfilename();
       // rt.setFile(this.file);
      //  getfille(pWave);
   }
    public String getfile()
   {
       file = PlotProbability.this.file;
      System.out.print("filename in the getfile method"+file);
      return file;
      
   }
    
     
}
