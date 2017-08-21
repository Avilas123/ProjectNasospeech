/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Prob{
        
        static double probability;
        public void setProb(double prob){
            this.probability = 1- prob;
          //  System.out.println("proba =" + probability);
        }
        
        double getProb(){
            return probability;
        }
        
        
        public int probToPixel(){
        Double pixel = probability*600;
        int returnValue = Integer.valueOf(pixel.intValue());
        
        return returnValue;
        }
    }
