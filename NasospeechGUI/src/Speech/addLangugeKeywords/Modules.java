package Speech.addLangugeKeywords;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Modules 
{
   String fullVowels[]={"अ","ऑ","आ","इ","ई","उ","ऊ","ए","ऐ","ओ","औ","ऋ","ॠ","अः","ं"};
    
    String matras[] = {"ा","ि","ी","ू","ु","े","ै","ो","ौ","ः","ृ","ॅ","़","ँ"};
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
    
    public Modules()
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
    ILSLMap.put("ऋ","rq");
    ILSLMap.put("ॠ","rrq");
    ILSLMap.put("ए","ee");
    ILSLMap.put("ऐ","ei");
    ILSLMap.put("ओ","o");
    ILSLMap.put("ऍ","ae");
    ILSLMap.put("औ","ou");
    ILSLMap.put("अं","aq");
    ILSLMap.put("अः","ahq");
    
//      //----matras
    ILSLMap.put("ा", "aa");
    ILSLMap.put("ि", "i");
    ILSLMap.put("ी", "ii");
    ILSLMap.put("ू", "u");
    ILSLMap.put("ु", "uu");
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
        output = output+" "+ILSLMap.get(String.valueOf(wordToParse.charAt(i)))+"  a ";
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
       //  System.out.println("processed word in devanagiri-"+returnString);
        return output;
    }
  
    public String deleteSchwa1(String word)
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
                //output = output+ILSLMap.get(String.valueOf(wordToParse.charAt(i)));
            }
                        else
            {
        returnString = returnString+String.valueOf(wordToParse.charAt(i))+schwa;
        output = output+""+ILSLMap.get(String.valueOf(wordToParse.charAt(i)));
        // output = output+ILSLMap.get(String.valueOf(wordToParse.charAt(i)));
            }
        }
        else if(markingMap[i].equals("M"))
        {
            if((i+2)< (wordToParse.length()) && Arrays.asList(matras).contains(String.valueOf(wordToParse.charAt(i+2))))
            {
             
                 
                returnString = returnString+String.valueOf(wordToParse.charAt(i))+String.valueOf(wordToParse.charAt(i+1))+String.valueOf(wordToParse.charAt(i+2));
 
       output = output+" "+ILSLMap.get(String.valueOf(wordToParse.charAt(i)))+" "+ILSLMap.get(String.valueOf(wordToParse.charAt(i+1)))+" "+ILSLMap.get(String.valueOf(wordToParse.charAt(i+2)));
      //  output = output+ILSLMap.get(String.valueOf(wordToParse.charAt(i)))+ILSLMap.get(String.valueOf(wordToParse.charAt(i+1)))+ILSLMap.get(String.valueOf(wordToParse.charAt(i+2)));
        
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
          //System.out.println("processed word in devanagiri1-"+returnString);
    return output;
    }
    
    
     public static void main(String[] args) 
    {
        // TODO code application logic here
        
     String hs = new String("प्रधानमंत्री");//original word
     // String hs = new String("हटाना");
      // String hs = new String("डराना");
      //String hs = new String("चबाना");
      //String hs = new String("कामयाब");
       //String hs = new String("पराया");
       //String hs = new String("आतंकवाद");
       
        Modules  s = new Modules ();//instances of the class
        Modules s2 = new Modules();

      
     //  String ilslWord = s.convetToILSL(hs);
        System.out.println("original word "+hs);
     // System.out.println("romanized word using ILSL-"+ilslWord);
      
     // System.out.println("converting back to hindi "+s.convrtToHindi(ilslWord)); 
       // System.out.println(" processed word in Roman using ILSL  -"+s.deleteSchwa(hs));

        // Regeneration from english transcription  to Hindi keyword for the first algorithm
        String s1 = s.deleteSchwa(hs);
        
        System.out.println("transcript" +s1);
         String indianTranscr="";
        String [] transcript = s1.split(" ");

         String [] transcript1= s1.split(" ");
  
         int i=0;
                        
                       
                        for (i=0;i<transcript.length;i++){
                            
                            
                              
                           if (transcript[i].equals("a"))
                           {
                              // System.out.println("me works"+i);
                                transcript1[i]="अ";                                                    
                           }
                           if (transcript[i].equals("ax"))
                           {
                                transcript1[i]="ऑ";                                                    
                           }
                           if (transcript[i].equals("aa"))
                           {
                                transcript1[i]="आ";                                                    
                           }
                            if (transcript[i].equals("i")){
                                transcript1[i]="इ";                                                    
                           }
                           if (transcript[i].equals("i")){
                                transcript1[i]="ई";                                                    
                           }
                           if (transcript[i].equals("aat"))
                           {
                               transcript1[i]="अत";
                           }
                           if (transcript[i].equals("u")){
                                transcript1[i]="उ";                                                    
                           }
                            if(transcript[i].equals("pr"))
                            {
                                transcript1[i]="पर";
                            }
                           if (transcript[i].equals("uu")){
                                transcript1[i]="ऊ";                                                    
                           }
                           
                           if (transcript[i].equals("rrq")){
                                transcript1[i]="ॠ";                                                    
                           }
                           if (transcript[i].equals("ee")){
                                transcript1[i]="ए";                                                    
                           }
                           if (transcript[i].equals("ei")){
                                transcript1[i]="ए";                                                    
                           }
                           if (transcript[i].equals("o")){
                                transcript1[i]="ओ";                                                    
                           }
                           if (transcript[i].equals("ae")){
                                transcript1[i]="ऍ";                                                    
                           }
                           if (transcript[i].equals("ou")){
                                transcript1[i]="औ";                                                    
                           }
                           if (transcript[i].equals("aa")){
                                transcript1[i]="ा";                                                    
                           }
                           if (transcript[i].equals("i")){
                                transcript1[i]="ि";                                                    
                           }
                           if (transcript[i].equals("ii")){
                                transcript1[i]="ी";                                                    
                           }
                           if (transcript[i].equals("uu")){
                                transcript1[i]="ू";                                                    
                           }
                           if (transcript[i].equals("u" )){
                                transcript1[i]="ु";                                                    
                           }
                           if (transcript[i].equals("ee")){
                                transcript1[i]="े";                                                    
                           }
                           if (transcript[i].equals("ei")){
                                transcript1[i]="ै";                                                    
                           }
                           if (transcript[i].equals("o")){
                                transcript1[i]="ो";                                                    
                           }
                           if (transcript[i].equals("ou")){
                                transcript1[i]="ौ";                                                    
                           }
                           if (transcript[i].equals(("rq"))){
                                transcript1[i]="ृ";                                                    
                           }
                           if (transcript[i].equals("ax")){
                                transcript1[i]="ॅ";                                                    
                           }
                           if (transcript[i].equals("q")){
                                transcript1[i]="ं";                                                    
                           }
                           if (transcript[i].equals("hq")){
                                transcript1[i]="ः";                                                    
                           }
                           if (transcript[i].equals("mq")){
                                transcript1[i]="ँ";                                                    
                           }
                           if (transcript[i].equals("q")){
                                transcript1[i]="़";                                                    
                           }
                           if (transcript[i].equals("k")){
                                transcript1[i]="क";                                                    
                           }
                           if (transcript[i].equals("kh")){
                                transcript1[i]="ख";                                                    
                           }
                           if (transcript[i].equals("g")){
                                transcript1[i]="ग";                                                    
                           }
                           if (transcript[i].equals("gh")){
                                transcript1[i]="घ";                                                    
                           }
                           if (transcript[i].equals("ng")){
                                transcript1[i]="ङ";                                                    
                           }
                          // if (transcript[i].equals("c")){
                           //     transcript1[i]="c";                                                    
                           //}
                           if (transcript[i].equals("c")){
                                transcript1[i]="च";                                                    
                           }
                           if (transcript[i].equals("j")){
                                transcript1[i]="ज";                                                    
                           }
                            if (transcript[i].equals("jh")){
                                transcript1[i]="झ";                                                    
                           }
                             if (transcript[i].equals("nj")){
                                transcript1[i]="ञ";                                                    
                           }
                              if (transcript[i].equals("tx")){
                                transcript1[i]="ट";                                                    
                           }
                              
                                 if (transcript[i].equals("txh")){
                                transcript1[i]="ठ";          
                                 }
                              if (transcript[i].equals("dx")){
                                transcript1[i]="ड";          
                                 }
                              if (transcript[i].equals("dxh")){
                                transcript1[i]="ढ";          
                                 }
                              if (transcript[i].equals("nx")){
                                transcript1[i]="ण";          
                                 }
                             if (transcript[i].equals("t")){
                                transcript1[i]="त";          
                                 }
                              if (transcript[i].equals("th")){
                                transcript1[i]="थ";          
                                 }
                                if (transcript[i].equals("d")){
                                transcript1[i]="द";          
                                 }
                                 if (transcript[i].equals("dh")){
                                transcript1[i]="ध";          
                                 }
                                  if (transcript[i].equals("n"))
                                  {
                                transcript1[i]="न";          
                                 }
                                 if (transcript[i].equals("p"))
                                  {
                                transcript1[i]="प";          
                                 }   
                                   if (transcript[i].equals("ph"))
                                  {
                                transcript1[i]="फ";          
                                 }   
                                    if (transcript[i].equals("b"))
                                  {
                                transcript1[i]="ब";          
                                 }   
                                      if (transcript[i].equals("bh"))
                                  {
                                transcript1[i]="भ";          
                                 }   
                                        if (transcript[i].equals("m"))
                                  {
                                transcript1[i]="म";          
                                 }   
                                          if (transcript[i].equals("y"))
                                  {
                                transcript1[i]="य";          
                                 }   
                                            if (transcript[i].equals("r"))
                                  {
                                transcript1[i]="र";          
                                 }   
                                              if (transcript[i].equals("l"))
                                  {
                                transcript1[i]="ल";          
                                 }   
                                                if (transcript[i].equals("w"))
                                  {
                                transcript1[i]="व";          
                                 }   
                                                  if (transcript[i].equals("sh"))
                                  {
                                transcript1[i]="श";          
                                 }   
                                  
                                                  if (transcript[i].equals("sx"))
                                  {
                                transcript1[i]="ष";          
                                 }   
                                                  
                                                  if (transcript[i].equals("s"))
                                  {
                                transcript1[i]="स";          
                                 }   
                                                  
                                                  if (transcript[i].equals("h"))
                                  {
                                transcript1[i]="ह";          
                                 }   
                                                  
                                                  if (transcript[i].equals("kq"))
                                  {
                                transcript1[i]="क़";          
                                 }   
                                                 if (transcript[i].equals("khq"))
                                  {
                                transcript1[i]="ख़";          
                                 }   
                                                 if (transcript[i].equals("gq"))
                                  {
                                transcript1[i]="ग़";          
                                 }   
                                                 if (transcript[i].equals("z"))
                                  {
                                transcript1[i]="ज़";          
                                 }   
                                                 
                                
                                                 if (transcript[i].equals("jhq"))
                                  {
                                transcript1[i]="झ़";          
                                 }   
                                                 
                                                 if (transcript[i].equals("dxq"))
                                  {
                                transcript1[i]="ड़";          
                                 }   
                                           if (transcript[i].equals("dxhq"))
                                  {
                                transcript1[i]="ढ़";          
                                 }    
                                
                                    if (transcript[i].equals("f"))
                                  {
                                transcript1[i]="फ़";          
                                 }            
                                     if (transcript[i].equals("yq"))
                                  {
                                transcript1[i]="य़";          
                                 }            
                                     
                                          if (transcript[i].equals("nq"))
                                  {
                                transcript1[i]="ऩ";    
                                
                                 }  
                                            if (transcript[i].equals("rx"))
                                  {
                                transcript1[i]="ऱ";    
                                
                                 }  
                                          
                                           
                                           
                                }
                        
                       // converted word
                        for (i=0;i<transcript1.length;i++)
                        {
                            
                            indianTranscr=indianTranscr+ transcript1[i];
                        
                          
                            
                        }
                       System.out.println("converted word"+indianTranscr); 
                       
                       //check whether the word is correct or not(i.e it matches with the original word or not)
                       if(hs.equals(indianTranscr))
                       {
                           System.out.println("Correct");
                           
                       }
                       else
                       {
                           System.out.println("Ïncorrect");
                       }
                  
                        
       
        
        
       // System.out.println("processed word in Roman using ILSL   -"+s.deleteSchwa1(hs));
        
        
       
        
        
        //Regeneration from english transcription to hindi word for the second algorithm
         //String s5 = s2.deleteSchwa(hs);
         //System.out.println(s5);
        String s5 = s2.deleteSchwa1(hs);
         System.out.println("transcript" +s5);
         String indianTranscr1="";
         String [] transcript3 = s5.split(" ");
         String [] transcript4= s5.split(" ");
         int j=0;
                        
                       
                        for (j=0;j<transcript3.length;j++)
                        {
                            
                            
                              
                           if (transcript3[j].equals("a"))
                           {
                              // System.out.println("me works"+i);
                                transcript4[j]="अ";                                                    
                           }
                           if (transcript3[j].equals("ax"))
                           {
                                transcript4[j]="ऑ";                                                    
                           }
                           if (transcript3[j].equals("aa"))
                           {
                                transcript4[j]="आ";                                                    
                           }
                            if (transcript3[j].equals("i")){
                                transcript4[j]="इ";                                                    
                           }
                           if (transcript3[j].equals("i")){
                                transcript4[j]="ई";                                                    
                           }
                           if (transcript3[j].equals("u")){
                                transcript4[j]="उ";                                                    
                           }
                           if (transcript3[j].equals("aam"))
                           {
                               transcript4[j]="ाम";
                           }
                           
                           if (transcript3[j].equals("uu")){
                                transcript4[j]="ऊ";                                                    
                           }
                           
                           if (transcript3[j].equals("rrq")){
                                transcript4[j]="ॠ";                                                    
                           }
                           if (transcript3[j].equals("ee")){
                                transcript4[j]="ए";                                                    
                           }
                           if (transcript3[j].equals("ei")){
                                transcript4[j]="ए";                                                    
                           }
                           if (transcript3[j].equals("o")){
                                transcript4[j]="ओ";                                                    
                           }
                           if (transcript3[j].equals("ae")){
                                transcript4[j]="ऍ";                                                    
                           }
                           if (transcript3[j].equals("ou")){
                                transcript4[j]="औ";                                                    
                           }
                           if (transcript3[j].equals("aat"))
                           {
                               transcript4[j]="आत";
                           }
                           if (transcript3[j].equals("aa")){
                                transcript4[j]="ा";                                                    
                           }
                           if (transcript3[j].equals("qk"))
                           {
                               transcript4[j]="ंक";
                           }
                           if (transcript3[j].equals("i")){
                                transcript4[j]="ि";                                                    
                           }
                           if (transcript3[j].equals("ii")){
                                transcript4[j]="ी";                                                    
                           }
                           if (transcript3[j].equals("uu")){
                                transcript4[j]="ू";                                                    
                           }
                           if (transcript3[j].equals("u" )){
                                transcript4[j]="ु";                                                    
                           }
                           if (transcript3[j].equals("ee")){
                                transcript4[j]="े";                                                    
                           }
                           if (transcript3[j].equals("ei")){
                                transcript4[j]="ै";                                                    
                           }
                           if (transcript3[j].equals("o")){
                                transcript4[j]="ो";                                                    
                           }
                           if (transcript3[j].equals("ou")){
                                transcript4[j]="ौ";                                                    
                           }
                           if (transcript3[j].equals(("rq"))){
                                transcript4[j]="ृ";                                                    
                           }
                           if (transcript3[j].equals("ax")){
                                transcript4[j]="ॅ";                                                    
                           }
                           if (transcript3[j].equals("q")){
                                transcript4[j]="ं";                                                    
                           }
                           if (transcript3[j].equals("hq")){
                                transcript4[j]="ः";                                                    
                           }
                           if (transcript3[j].equals("mq")){
                                transcript4[j]="ँ";                                                    
                           }
                           if (transcript3[j].equals("q")){
                                transcript4[j]="़";                                                    
                           }
                           if (transcript3[j].equals("k")){
                                transcript4[j]="क";                                                    
                           }
                           if (transcript3[j].equals("kh")){
                                transcript4[j]="ख";                                                    
                           }
                           if (transcript3[j].equals("g")){
                                transcript4[j]="ग";                                                    
                           }
                           if (transcript3[j].equals("gh")){
                                transcript4[j]="घ";                                                    
                           }
                           if (transcript3[j].equals("ng")){
                                transcript4[j]="ङ";                                                    
                           }
                          // if (transcript3[j].equals("c")){
                           //     transcript4[j]="c";                                                    
                          // }
                           if (transcript3[j].equals("c")){
                                transcript4[j]="च";                                                    
                           }
                           if (transcript3[j].equals("j")){
                                transcript4[j]="ज";                                                    
                           }
                            if (transcript3[j].equals("jh")){
                                transcript4[j]="झ";                                                    
                           }
                             if (transcript3[j].equals("nj")){
                                transcript4[j]="ञ";                                                    
                           }
                              if (transcript3[j].equals("tx")){
                                transcript4[j]="ट";                                                    
                           }
                              
                                 if (transcript3[j].equals("txh")){
                                transcript4[j]="ठ";          
                                 }
                              if (transcript3[j].equals("dx")){
                                transcript4[j]="ड";          
                                 }
                              if (transcript3[j].equals("dxh")){
                                transcript4[j]="ढ";          
                                 }
                              if (transcript3[j].equals("nx")){
                                transcript4[j]="ण";          
                                 }
                             if (transcript3[j].equals("t")){
                                transcript4[j]="त";          
                                 }
                              if (transcript3[j].equals("th")){
                                transcript4[j]="थ";          
                                 }
                                if (transcript3[j].equals("d")){
                                transcript4[j]="द";          
                                 }
                                 if (transcript3[j].equals("dh")){
                                transcript4[j]="ध";          
                                 }
                                  if (transcript3[j].equals("n"))
                                  {
                                transcript4[j]="न";          
                                 }
                                 if (transcript3[j].equals("p"))
                                  {
                                transcript4[j]="प";          
                                 }   
                                   if (transcript3[j].equals("ph"))
                                  {
                                transcript4[j]="फ";          
                                 }   
                                    if (transcript3[j].equals("b"))
                                  {
                                transcript4[j]="ब";          
                                 }   
                                      if (transcript3[j].equals("bh"))
                                  {
                                transcript4[j]="भ";          
                                 }   
                                        if (transcript3[j].equals("m"))
                                  {
                                transcript4[j]="म";          
                                 }   
                                          if (transcript3[j].equals("y"))
                                  {
                                transcript4[j]="य";          
                                 }   
                                            if (transcript3[j].equals("r"))
                                  {
                                transcript4[j]="र";          
                                 }   
                                              if (transcript3[j].equals("l"))
                                  {
                                transcript4[j]="ल";          
                                 }   
                                                if (transcript3[j].equals("w"))
                                  {
                                transcript4[j]="व";          
                                 }   
                                                  if (transcript3[j].equals("sh"))
                                  {
                                transcript4[j]="श";          
                                 }   
                                  
                                                  if (transcript3[j].equals("sx"))
                                  {
                                transcript4[j]="ष";          
                                 }   
                                                  
                                                  if (transcript3[j].equals("s"))
                                  {
                                transcript4[j]="स";          
                                 }   
                                                  
                                                  if (transcript3[j].equals("h"))
                                  {
                                transcript4[j]="ह";          
                                 }   
                                                  
                                                  if (transcript3[j].equals("kq"))
                                  {
                                transcript4[j]="क़";          
                                 }   
                                                 if (transcript3[j].equals("khq"))
                                  {
                                transcript4[j]="ख़";          
                                 }   
                                                 if (transcript3[j].equals("gq"))
                                  {
                                transcript4[j]="ग़";          
                                 }   
                                                 if (transcript3[j].equals("z"))
                                  {
                                transcript4[j]="ज़";          
                                 }   
                                                 
                                
                                                 if (transcript3[j].equals("jhq"))
                                  {
                                transcript4[j]="झ़";          
                                 }   
                                                 
                                                 if (transcript3[j].equals("dxq"))
                                  {
                                transcript4[j]="ड़";          
                                 }   
                                           if (transcript3[j].equals("dxhq"))
                                  {
                                transcript4[j]="ढ़";          
                                 }    
                                
                                    if (transcript3[j].equals("f"))
                                  {
                                transcript4[j]="फ़";          
                                 }            
                                     if (transcript3[j].equals("yq"))
                                  {
                                transcript4[j]="य़";          
                                 }            
                                     
                                          if (transcript3[j].equals("nq"))
                                  {
                                transcript4[j]="ऩ";    
                                
                                 }  
                                            if (transcript3[j].equals("rx"))
                                  {
                                transcript4[j]="ऱ";    
                                
                                 }  
                                          
                                           
                                           
                                }
                        
                        //converted word
                        for (j=0;j<transcript4.length;j++)
                        {
                            
                            indianTranscr1=indianTranscr1+ transcript4[j];
                        
                          
                            
                        }
                       System.out.println("converted word  " +indianTranscr1); 
                       
                       //check whether the word is correct or not(i.e it matches with the original word or not)
                   
                       if(hs.equals(indianTranscr1))
                       {
                           System.out.println("Correct");
                           
                       }
                       else
                       {
                           System.out.println("Ïncorrect");
                       }
                        
       
       
    
                

    }
}
