/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.addLangugeKeywords;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emst
 */
public class IndianEnglish {
    
    
    
    
    public String getTranscription(String word){
        
        String transcription="";
        
        
        writeTofile("tfile.scm", "(set! v (lex.lookup \""+word +"\" ))\n"+"(set! v1 (cdr (cdr v)))\n"+"(format t \"%l\" v1)");
        
        
        String cmd="/usr/bin/festival"+ " -b " + "tfile.scm";
        
        String trans1=execCmd(cmd);
        
        String trans2=trans1.replaceAll("[^a-zA-Z\\s]", "").toString().toUpperCase().trim();
        
        System.out.println(trans1);
        System.out.println(trans2);
        
        String trans3=mapPhonemeToIndianEnglish(trans2);
       
        
        transcription=trans3;
        
        return transcription;
    
    }
    
    public static String execCmd(String cmd)  {
        java.util.Scanner s = null;
        try {
            s = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
        } catch (IOException ex) {
            Logger.getLogger(IndianEnglish.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s.hasNext() ? s.next() : "";
    }
    
    public static void writeTofile(String fileName,String txt ){
        
        try {
 
			String content = txt;
 
			File file = new File(fileName);
 
			// if file doesnt exists, then create it
			
			file.createNewFile();
		
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
 
			//System.out.println("Done");
 
		} catch (IOException e) {
				e.printStackTrace();
		}
    }
    
    public static String mapPhonemeToIndianEnglish(String transValue){
        
        String trans="";
        
        String indianTranscr="";
        
        
        String [] transcript = transValue.split(" ");
        String [] transcript1= transValue.split(" ");
                        
        int i=0;
                        
                       
        
        for (i=0;i<transcript.length;i++){
           
            switch (transcript[i]) {
                case "AA":
                    transcript1[i]="a";
                    break;
                case "AE":
                    transcript1[i]="ae";
                    break;
                case "AH":
                    transcript1[i]="a";
                    break;
                case "AX":
                    transcript1[i]="a";
                    break;
                case "AO":
                    transcript1[i]="ax";
                    break;
                case "AW":
                    transcript1[i]="au";
                    break;
                case "AY":
                    transcript1[i]="ai";
                    break;
                case "B":
                    transcript1[i]="b";
                    break;
                case "CH":
                    transcript1[i]="c";
                    break;
                case "D":
                    transcript1[i]="d";
                    break;
                case "EH":
                    transcript1[i]="ea";
                    break;
                case "ER":
                    transcript1[i]="ar";
                    break;
                case "EY":
                    transcript1[i]="ei";
                    break;
                case "F":
                    transcript1[i]="f";
                    break;
                case "G":
                    transcript1[i]="g";
                    break;
                case "HH":
                    transcript1[i]="h";
                    break;
                case "IH":
                    transcript1[i]="i";
                    break;
                case "IY":
                    transcript1[i]="i";
                    break;
                case "JH":
                    transcript1[i]="j";
                    break;
                case "K":
                    transcript1[i]="k";
                    break;
                case "L":
                    transcript1[i]="l";
                    break;
                case "M":
                    transcript1[i]="m";
                    break;
                case "N":
                    transcript1[i]="n";
                    break;
                case "NG":
                    transcript1[i]="ng";
                    break;
                case "OW":
                    transcript1[i]="ou";
                    break;
                case "OY":
                    transcript1[i]="oi";
                    break;
                case "P":
                    transcript1[i]="p";
                    break;
                case "R":
                    transcript1[i]="r";
                    break;
                case "S":
                    transcript1[i]="s";
                    break;
                case "SH":
                    transcript1[i]="sh";
                    break;
                case "T":
                    transcript1[i]="t";
                    break;
                case "TH":
                    transcript1[i]="th";
                    break;
                case "UH":
                    transcript1[i]="u";
                    break;
                case "UW":
                    transcript1[i]="u";
                    break;
                case "V":
                    transcript1[i]="w";
                    break;
                case "W":
                    transcript1[i]="w";
                    break;
                case "Y":
                    transcript1[i]="y";
                    break;
                case "Z":
                    transcript1[i]="z";
                    break;
                case "ZH":
                    transcript1[i]="zh";
                    break;
                default:
                    transcript1[i]="";
                    break;
            }
        }
                        
                        
        for (i=0;i<transcript1.length;i++){
                            
            indianTranscr=indianTranscr+ " "+ transcript1[i];
                            
        }
                        
       /*  
        transValue=transValue.replaceAll("AA", "a");
        transValue=transValue.replaceAll("AE", "ae");
        transValue=transValue.replaceAll("AH", "a");
        transValue=transValue.replaceAll("AX", "a");
        transValue=transValue.replaceAll("AO", "ax");
        transValue=transValue.replaceAll("AW", "au");
        transValue=transValue.replaceAll("AY", "ai");
        transValue=transValue.replaceAll("B", "b");
        transValue=transValue.replaceAll("CH", "c");
        transValue=transValue.replaceAll("D", "d");
        transValue=transValue.replaceAll("DH", "d");
        transValue=transValue.replaceAll("EH", "ea");
        transValue=transValue.replaceAll("ER", "ar");
        transValue=transValue.replaceAll("F", "f");
        transValue=transValue.replaceAll("G", "g");
        transValue=transValue.replaceAll("HH", "h");
        transValue=transValue.replaceAll("IH", "i");
        transValue=transValue.replaceAll("IY", "i");
        transValue=transValue.replaceAll("JH", "j");
        transValue=transValue.replaceAll("K", "k");
        transValue=transValue.replaceAll("L", "l");
        transValue=transValue.replaceAll("M", "m");
        transValue=transValue.replaceAll("N", "n");
        transValue=transValue.replaceAll("NG", "ng");
        transValue=transValue.replaceAll("OW", "ou");
        transValue=transValue.replaceAll("OY", "oi");
        transValue=transValue.replaceAll("P", "p");
        transValue=transValue.replaceAll("R", "r");
        transValue=transValue.replaceAll("S", "s");
        transValue=transValue.replaceAll("SH", "sh");
        transValue=transValue.replaceAll("T", "t");
        transValue=transValue.replaceAll("TH", "th");
        transValue=transValue.replaceAll("UH", "u");
        transValue=transValue.replaceAll("UW", "u");
        transValue=transValue.replaceAll("V", "w");
        transValue=transValue.replaceAll("W", "w");
        transValue=transValue.replaceAll("Y", "y");
        transValue=transValue.replaceAll("Z", "z");
        transValue=transValue.replaceAll("ZH", "zh");
        */
                        
        trans=indianTranscr;
        
      return trans;  
    }
}
