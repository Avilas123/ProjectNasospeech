/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.WavePanel;

/**
 *
 * @author user
 */
public class Returnfilename
{
    public PlotWave pWave;
    public String file="";
   /* public Returnfilename(PlotWave pWave)
    {
    this.pWave=pWave;
    
    }
           
       public String gettingfilename()
    {
         file = pWave.ReturnFilename();
        System.out.print("finallly gettting yupeeeeeee\n"+file);
        return file;
    }

     */  
        public void setFile(String file)
        {
            this.file = file;
            //System.out.println("proba =" + probability);
        }
       
        String getFile()
        {
            return file;
        }
        
   
}