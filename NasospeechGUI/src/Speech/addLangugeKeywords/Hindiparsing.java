/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * For rmanization of devanagiri characters Indian Language Speech sound Label set (ILSL12) is used
 * modifications done by atin in the algo--
 * 1.marking full sounds F,half sound H unknown U and M for the consonants followed by matra,matra for matras,halant for halants
 * 2.8th step is modified as- mark H if precedded by F (not by matra) and followed by F or U (not by M){in the original algo consonant with matra are considered as F}
 */
package Speech.addLangugeKeywords;

import java.util.Arrays;
import java.util.HashMap;
import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author SWN TATA PWR PC3
 */
public class Hindiparsing {

    /**
     * @param args the command line arguments
     */
    
    //------------variable declaration section------------------------//
    String fullVowels[]={"अ","ऑ","आ","इ","ई","उ","ऊ","ए","ऐ","ओ","औ","ऋ","ॠ","अः","ं","ँ"};
    
    String matras[] = {"ा","ि","ी","ू","ु","े","ै","ो","ौ","ः","ृ","ॅ","़"};
    String consonants[] = {"क","ख","ग","घ","ङ","च","छ","ज","झ","ञ","ट","ठ","ड","ढ","ण","त","थ","द","ध","न","प","फ","ब","भ","म","य","र","ल","व","श","ष","स","ह","क्ष","त्र","ज्ञ","क़","ख़","ग़","ज़","ड़","ढ़","फ़","य़","ऩ","ऱ"};
 
  //  private Map<Integer,String> markingMap =  new HashMap<Integer,String>();
  String markingMap[];
   // private Map<String,String> itransMap = new HashMap<String,String>();//---for mapping devanagiri to roman alphabets
    String romanized="";
  private Map<String,String> ILSLMap = new HashMap<String,String>();//---for mapping devanagiri to roman alphabets  
   
    String halant = "्";
    String schwa = "अ";
    String output="";
  
    //------------variable declaration section ends-------------------------//
    
    public Hindiparsing()
    {
    ILSLMaping();
    }
    
    
    
    
    /*
     this method maps devanagiri characters to roman characters using Indian Language Speech sound Label set (ILSL12)
     */
    public void ILSLMaping()
    {
        //--vowels
    ILSLMap.put("अ","a");
    ILSLMap.put("ऑ","ax");
    ILSLMap.put("आ","aa");
    ILSLMap.put("इ","i");
    ILSLMap.put("ई","ii");
    ILSLMap.put("उ","u");
    ILSLMap.put("ऊ","uu");
    
    ILSLMap.put("ॠ","rrq");
    ILSLMap.put("ए","ee");
    ILSLMap.put("ए","ei");
    ILSLMap.put("ओ","o");
    ILSLMap.put("ऍ","ae");
    ILSLMap.put("औ","ou");
   
    
//      //----matras
    ILSLMap.put("ा", "aa");
    ILSLMap.put("ि", "i");
    ILSLMap.put("ी", "ii");
    ILSLMap.put("ू", "uu");
    ILSLMap.put("ु", "u");
    ILSLMap.put("े", "ee");
    ILSLMap.put("ै", "ei");
    ILSLMap.put("ो", "o");
    ILSLMap.put("ौ", "ou");
    ILSLMap.put("ृ", "rq");
    ILSLMap.put("ॅ","ax");
    
     ILSLMap.put("ं","q");
    ILSLMap.put("ः","hq");
    ILSLMap.put("ँ","mq");
     ILSLMap.put("़","q");
    
    
    
    ILSLMap.put("क","k");
    ILSLMap.put("ख","kh");
    ILSLMap.put("ग","g");
    ILSLMap.put("घ","gh");
    ILSLMap.put("ङ","ng");
    ILSLMap.put("च","c");
    ILSLMap.put("छ","ch");
    ILSLMap.put("ज","j");
    ILSLMap.put("झ","jh");
    ILSLMap.put("ञ","nj");
      
    ILSLMap.put("ट","tx");
    ILSLMap.put("ठ","txh");
    ILSLMap.put("ड","dx");
    ILSLMap.put("ढ","dxh");
    ILSLMap.put("ण","nx");
    ILSLMap.put("त","t");
    ILSLMap.put("थ","th");
    ILSLMap.put("द","d");
    ILSLMap.put("ध","dh");
    ILSLMap.put("न","n");  
    ILSLMap.put("प","p");
    ILSLMap.put("फ","ph");
    ILSLMap.put("ब","b");
    ILSLMap.put("भ","bh");
    ILSLMap.put("म","m");
    ILSLMap.put("य","y");
    ILSLMap.put("र","r");
    ILSLMap.put("ल","l");
    ILSLMap.put("व","w");
    ILSLMap.put("श","sh");
     ILSLMap.put(halant,"halant");
    
     ILSLMap.put("ष","sx");
    ILSLMap.put("स","s");
    ILSLMap.put("ह","h");
    //--special consonants--
    ILSLMap.put("क़","kq");
    ILSLMap.put("ख़","khq");
    ILSLMap.put("ग़","gq");
    ILSLMap.put("ज़","z");
    ILSLMap.put("झ़","jhq");
    ILSLMap.put("ड़","dxq");
    ILSLMap.put("ढ़","dxhq");  
    ILSLMap.put("फ़","f");
    ILSLMap.put("य़","yq");
    ILSLMap.put("ऩ","nq");
    ILSLMap.put("ऱ","rx");
   
    
    }
    
//    public String getKeyFormValue(Map hm,Object value)
//    {
//    String keyval="";
//     for (Object o : hm.keySet()) {
//      if (hm.get(o).equals(value)) {
//        keyval = (String)o;
//      }
//    }
//    return keyval;
//    }
//    

    
    /* implementing the delete_schwa algorithm--
     U-unknown
     * F-full sound
     * H-half sound
     * Added by atin
     * M-consonant followed by matras
     */
    public String deleteSchwa(String word)
    {
        //Arrays.asList(markingMap).clear();
     
     
    String wordToParse = word;
   
    String curChar = "";
    String highVowel[] = {"ि","ी","ऋ","ू","ु"};
    String returnString = "";
   //------------1st for 1st rule--------------//
    
    
     //----replace special consonants by corresponding consonant+nukta
//     wordToParse = wordToParse.replace("क़", "क़");
//     wordToParse = wordToParse.replace("ख़", "ख़");
//     wordToParse = wordToParse.replace("ग़", "ग़");
//     wordToParse = wordToParse.replace("ज़", "ज़");
//     wordToParse = wordToParse.replace("झ़", "झ़");
//     wordToParse = wordToParse.replace("ड़", "ड़");
//     wordToParse = wordToParse.replace("ढ़", "ढ़");
//     wordToParse = wordToParse.replace("फ़", "फ़");
//     wordToParse = wordToParse.replace("य़", "य़");
//     wordToParse = wordToParse.replace("ऩ", "झ़");
//     wordToParse = wordToParse.replace("ऱ", "ऱ");
//     System.out.println(wordToParse);
      markingMap = new String[wordToParse.length()];
    /*Mark all the full vowels and consonants followed by vowels other than the inherent
schwas (i.e. consonants with mAtrAs) and all the hs in the word as F unless it is explicitly
marked as half by use of halant. Mark all the consonants immediately followed by
consonants or halants (i.e. consonants of conjugate syllables) as H. Mark all the remaining
consonants, which are followed by implicit schwas as U.
added by atin
* if consonant is followed by a matra than mark it M
*/
       for(int i=0;i<wordToParse.length();i++)
    {
    curChar = String.valueOf(wordToParse.charAt(i));
    if(Arrays.asList(fullVowels).contains(curChar))
    {
    markingMap[i]="F";
    }
   
    else if(i<wordToParse.length()-1)
    {
        if(String.valueOf(wordToParse.charAt(i+1)).equals(halant))
         {
         markingMap[i]="H";
          markingMap[i+1] = "halant";//mark as halant
         i=i+1;//skip one iteration i.e. for halant
         
         }
        else if(Arrays.asList(matras).contains(String.valueOf(wordToParse.charAt(i+1))))
        {
         markingMap[i]="M";
          markingMap[i+1] = "matra";//--mark as matra
         i=i+1;//skip one iteration i.e. for matra
        
        }
         else if(curChar.equals("ह"))//mark all ह full..e.g.int the समुह 
         {
        markingMap[i]="F";
         }
        else
        {
        markingMap[i]="U";
        }
    }
    else
    {
     markingMap[i]="U";
    }
    }
    //--------------1st for ends----//
       
       //----2nd for starts------------------//
       /*
        2.If in the word, y is marked U and preceded by i, I, RRi, u or U mark it F (context 2).
        */
       for(int i=0;i<wordToParse.length();i++)
    {
//      if(i<wordToParse.length()-1)
//      {
//        if(String.valueOf(wordToParse.charAt(i+1)).equals(halant) || Arrays.asList(matras).contains(String.valueOf(wordToParse.charAt(i+1))))
//         {
//           continue;  //--skip for halant and matras
//         }
//      else
//        {
      curChar = String.valueOf(wordToParse.charAt(i));
         if(i>0)
        {
            if(curChar.equals("य") && markingMap[i].equals("U"))
            {
                if(Arrays.asList(highVowel).contains(String.valueOf(wordToParse.charAt(i-1))))
                {
                markingMap[i]= "F";
                }
            }
       // }
        //}
      }
    }
       
       //---------2nd for ends--------------------//
       
  //----------------3rd for starts------------------//
    /*
     3. If y, r, l or v are marked U and preceded by consonants marked H, then mark them F
(context 3).
     */
       
       for(int i=0;i<wordToParse.length();i++)
    {
      curChar = String.valueOf(wordToParse.charAt(i));
        if(i>0)
        {
             if(curChar.equals("य")|| curChar.equals("र") || curChar.equals("ल") || curChar.equals("व"))
                {
                    if(markingMap[i].equals("U"))
                    {
                      if(markingMap[i-1].equals("H") || markingMap[i-1].equals("halant"))///--as consonants followed by halant are H
                      {
                        markingMap[i] = "F";
                      }
                
                    }
                }
        }
    }   
  //--------------3rd for ends-----------------------------//
       
       
 //---------------4th for starts-------------//
   /*
    * If a consonant marked U is followed by a full vowel, then mark that consonant as F
(context 4).
    */     
    for(int i=0;i<wordToParse.length();i++)
    {
       curChar = String.valueOf(wordToParse.charAt(i));
       if(i<wordToParse.length()-1)
       {
           if(markingMap[i].equals("U"))
           {
               if(Arrays.asList(fullVowels).contains(String.valueOf(wordToParse.charAt(i+1))))
               {
              markingMap[i] = "F"; 
               }
           }
       }
    }
       
//-----------4th for ends-----------------------//
       
//-----5th for starts--------------------------------//
    /*
     5.While traversing the word from left to right, if a consonant marked U is encountered
before any consonant or vowel marked F, then mark that consonant as F (context 6).
     */
      boolean ifFFound = false;
    for(int i=0;i<wordToParse.length();i++)
    {
      
        if(markingMap[i].equals("F") || markingMap[i].equals("M"))
        {
        ifFFound = true;
        break;
        }
        if(ifFFound == false)
        {
            if(markingMap[i].equals("U") && Arrays.asList(consonants).contains(String.valueOf(wordToParse.charAt(i))))
            {
            markingMap[i] = "F";
            break;
            }
        }
    }    
//------------------5th for ends----------------------------------//
       
    
//---------------6th for starss------------------------------------//
    /*6.If the last consonant is marked U, mark it H (context 7).*/
    for(int i=wordToParse.length()-1;i>=0;i--)
    {
        if(Arrays.asList(consonants).contains(String.valueOf(wordToParse.charAt(i))))
        {
            if(markingMap[i].equals("U"))
            {
             markingMap[i] = "H";
            }
            break;
        }
         
    }
//-----------6th for ends---------------------------------------//
       
//-----------7th for starts----------------------------//
    /*
    7. If any consonant marked U is immediately followed by a consonant marked H, mark it
F (context 1).
     */
      for(int i=0;i<wordToParse.length();i++)
    {
      if(i<wordToParse.length()-1) 
      {
         if(Arrays.asList(consonants).contains(String.valueOf(wordToParse.charAt(i))))
         {
             if(markingMap[i].equals("U") && Arrays.asList(consonants).contains(String.valueOf(wordToParse.charAt(i+1))) && markingMap[i+1].equals("H"))
             {
             markingMap[i] = "F";
             }
         }
      }
    }
    
//-------------7th for ends==----------------------------//
 
      
 //----8th for starts------------------------------//
      /*
      8. While traversing the word from left to right, for every consonant marked U, mark it H
if it is preceded by F and followed by F or U otherwise mark it F.
* added by atin - mark H if precedded by F (not by matra) and followed by F or U (not by M)
       */
    for(int i=0;i<wordToParse.length();i++)
    {
        if(i>0 && i<wordToParse.length()-1)
        {
            if(Arrays.asList(consonants).contains(String.valueOf(wordToParse.charAt(i))))
            {
                if(markingMap[i].equals("U"))
                {
                    if((markingMap[i-1].equals("F") ) && (markingMap[i+1].equals("F") || markingMap[i+1].equals("U")) )
                    {
                    markingMap[i] = "H";
                    }
                    else
                    {
                    markingMap[i] = "F";
                    }
                
                }
            }
        }
        else if(Arrays.asList(matras).contains(String.valueOf(wordToParse.charAt(i))) && markingMap[i].equals("U"))///for some special cases where matra appears after nukta e.g.मोड़ा
        {
        markingMap[i] = "H";
        }
    }   
//------------8th for ends-----------------------------//
      
  //------make returnstring according to H U F M markings-----------//
 /*
  added by Atin--
  * 1.if marked F add schwa (a)
  * 2. if markrd M dont add a but merge it with following matra
  * 3.if marked M and followed by nukta then again followed by matra then last matra is added e.g. फ़िदाईन
  */   
    for(int i=0;i<wordToParse.length();i++ )
    {
      // System.out.println(i);  
        if(markingMap[i].equals("F"))
        {
            if(Arrays.asList(fullVowels).contains(String.valueOf(wordToParse.charAt(i))))
            {
                returnString = returnString+String.valueOf(wordToParse.charAt(i));
                output = output+" "+ILSLMap.get(String.valueOf(wordToParse.charAt(i)));
            }
                        else
            {
        returnString = returnString+String.valueOf(wordToParse.charAt(i))+schwa;
        output = output+" "+ILSLMap.get(String.valueOf(wordToParse.charAt(i))) +" a";
            }
        }
        else if(markingMap[i].equals("M"))
        {
            if((i+2)< (wordToParse.length()) && Arrays.asList(matras).contains(String.valueOf(wordToParse.charAt(i+2))))
            {
             
                 
                returnString = returnString+String.valueOf(wordToParse.charAt(i))+String.valueOf(wordToParse.charAt(i+1))+String.valueOf(wordToParse.charAt(i+2));
 
        output = output+" "+ILSLMap.get(String.valueOf(wordToParse.charAt(i)))+" "+ILSLMap.get(String.valueOf(wordToParse.charAt(i+1)))+" "+ILSLMap.get(String.valueOf(wordToParse.charAt(i+2)));
             i=i+2;
             
            
            }
            else
            {
        returnString = returnString+String.valueOf(wordToParse.charAt(i))+String.valueOf(wordToParse.charAt(i+1));
 
        output = output+" "+ILSLMap.get(String.valueOf(wordToParse.charAt(i)))+" "+ILSLMap.get(String.valueOf(wordToParse.charAt(i+1)));
         i=i+1;
           }
       
        }
        else if(markingMap[i].equals("H"))
        {
        returnString = returnString+String.valueOf(wordToParse.charAt(i));
          output = output+" "+ILSLMap.get(String.valueOf(wordToParse.charAt(i)));
        }
    }
// //-----------------------------------------------------------------------//
//    returnString="";
//   for(int i =0; i<markingMap.length;i++)
//   {
//   returnString = returnString+ markingMap[i];
//   }
//    returnString="";
//     for(int i=0;i<wordToParse.length();i++ )
//    {
//    returnString = returnString+"\n"+String.valueOf(wordToParse.charAt(i));
//    }
          System.out.println("processed word in devanagiri-"+returnString);
    return output;
    }
  
    
    public static void main(String[] args) {
        // TODO code application logic here
        String hs = new String(" प्रधानमंत्री ");
       Hindiparsing s = new Hindiparsing();
     //  String ilslWord = s.convetToILSL(hs);
       System.out.println("original word "+hs);
     // System.out.println("romanized word using ILSL-"+ilslWord);
      
     // System.out.println("converting back to hindi "+s.convrtToHindi(ilslWord)); 
      System.out.println(" processed word in Roman using ILSL  -"+s.deleteSchwa(hs));

    }
}
