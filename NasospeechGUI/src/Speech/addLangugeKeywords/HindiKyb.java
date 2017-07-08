/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.addLangugeKeywords;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author SWN TATA PWR PC3
 */
public class HindiKyb extends JFrame {
    
    //-------------------Number of rows and columns in both array must be same to work properly
    //--keys without pressing shift key
	private static  String[][] key={
	{"'","१","२","३","४","५","६","७","८","९","०","-","ृ"},
        {"ौ","ै","ा","ी","ू","ब","ह","ग","द","ज","ड","़","ॉ"},
        {"ो","े","्","ि","ु","प","र","क","त","च","ट","Back"},
        {"Shift","","ं","म","न","व","ल","स",",",".","य","Shift"}
	} ;
        
        
         //--keys after pressing shift key
        private static  String[][] keyShifted={
	{"~","ऍ","ॅ","्","र्","ज्ञ","त्र","क्ष","श्र","(",")","ः","ऋ"},
        {"औ","ऐ","आ","ई","ऊ","भ","ङ","घ","ध","झ","ढ","ञ","ऑ"},
        {"ओ","ए","अ","इ","उ","फ","ऱ","ख","थ","छ","ठ","Back"},
        {"Shift"," ","ँ","ण","","","ळ","श","ष","|","?","Shift"}
	} ;
        
     
//        
//        private static String[][] phoneticKey={
//        {"ॆ","१","२","३","४","५","६","७","८","९","०","-","ो"},
//        {"ओ","ट","े","र","त","य","ु","ि","ो","प","ड","ऋ"},
//        {"ा","स","द","्","ग","ह","ज","क","ल","इ","उ","Back"},
//        {"Shift","ङ","ष","च","व","ब","न","म",",","|","ए","Shift"}
//        };
//        
//         private static String[][] phoneticKeyShifted={
//        {"ऎ","ऍ","ॅ","ऑ","ॉ","ञ","ज्ञ","&","श्र","(",")","_","+","ऒ"},
//        {"औ","ठ","ै","ृ","थ","य़","ू","ी","ौ","फ","ढ","ऱ"},
//        {"आ","श","ध","अ","घ","ः","झ","ख","ळ","ई","ऊ","Back"},
//        {"Shift","ँ","क्ष","छ","ऴ","भ","ण","ं","ऩ","़","ऐ","Shift"}
//        };
        
        private static boolean isShiftPressed = false;
       private static JButton lBtn;
        private static int lastRow;
       private static int lColumn;
       private  static int row;
       private static int column;
         private static HindiKyb curInstance;
        private static AddLangKeyword s;
        private static String kybType = "inscript";
      // static  AddLangKeyword s = AddLangKeyword.getInstance();
  static  Map<String, JButton> currentComponents;
  
  
  
  //------------set kEy for inscript--------------//
  public static void setPhoneticKey()
  {
    
//  Arrays.asList(key).clear();
//  Arrays.asList(keyShifted).clear();
//  Arrays.fill(key, null);
//  Arrays.fill(keyShifted, null);
//  Arrays.fill(key,phoneticKey);
//   Arrays.fill(keyShifted,phoneticKeyShifted);
//   javax.swing.JOptionPane.showMessageDialog(null,key[0][1] , "InfoBox: ", javax.swing.JOptionPane.INFORMATION_MESSAGE);
      
      key = new String[][]{
        {"ॆ","१","२","३","४","५","६","७","८","९","०","-","ो"},
        {"ओ","ट","े","र","त","य","ु","ि","ो","प","ड","ऋ"},
        {"ा","स","द","्","ग","ह","ज","क","ल","इ","उ","Back"},
        {"Shift","ङ","ष","च","व","ब","न","म",",","|","ए","Shift"}
        };
    keyShifted = new String[][]{
        {"ऎ","ऍ","ॅ","ऑ","ॉ","ञ","ज्ञ","&","श्र","(",")","_","+","ऒ"},
        {"औ","ठ","ै","ृ","थ","य़","ू","ी","ौ","फ","ढ","ऱ"},
        {"आ","श","ध","अ","घ","ः","झ","ख","ळ","ई","ऊ","Back"},
        {"Shift","ँ","क्ष","छ","ऴ","भ","ण","ं","ऩ","़","ऐ","Shift"}
        }; 
  changeKeys();
  }
  //===============================================//
  
  public static void setInscriptKey()
  {
  key= new String[][]{
	{"'","१","२","३","४","५","६","७","८","९","०","-","ृ"},
        {"ौ","ै","ा","ी","ू","ब","ह","ग","द","ज","ड","़","ॉ"},
        {"ो","े","्","ि","ु","प","र","क","त","च","ट","Back"},
        {"Shift","","ं","म","न","व","ल","स",",",".","य","Shift"}
	} ;
        
        
         //--keys after pressing shift key
         keyShifted= new String[][]{
	{"~","ऍ","ॅ","्","र्","ज्ञ","त्र","क्ष","श्र","(",")","ः","ऋ"},
        {"औ","ऐ","आ","ई","ऊ","भ","ङ","घ","ध","झ","ढ","ञ","ऑ"},
        {"ओ","ए","अ","इ","उ","फ","ऱ","ख","थ","छ","ठ","Back"},
        {"Shift"," ","ँ","ण","","","ळ","श","ष","|","?","Shift"}
	} ;
  changeKeys();
  }
  
  
  
  //---return current instance---for making singleton class
   public static HindiKyb getInstance(AddLangKeyword addLangCurInstance)
    {
    if(curInstance == null)
    {
        return new HindiKyb("Hindi Keyboard",addLangCurInstance);
    }
    else
    {
       s = addLangCurInstance;
        return curInstance;
    }
    }
  
   private HindiKyb(String fname,AddLangKeyword addLangCurInstance) {
       super(fname);
       this.s = addLangCurInstance;
       try
       {  
           System.setProperty("file.encoding", "UTF-8");
       createAndShowGUI();
      
        
        curInstance=this;
        this.setAlwaysOnTop(true);
       }
       catch(Exception ex)
       {
       System.out.println(ex.getMessage());
       }
    }
  //------------------adding first time-------------
 private static void addComponentsToPane(Container pane) {

        JButton jbnButton;
        //----hashmap for mapping buttons to corressponding names
         currentComponents = new HashMap<String,JButton>();
        pane.setLayout(new GridBagLayout());
        
        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.HORIZONTAL;

        int x=0;
        int y=0;
      //---add keys for the first time-----------//  
        for( row=0;row<key.length-1;row++)
        {
       
        for( column=0;column<key[row].length;column++)
        {
            
      JButton aBtn = new JButton(key[row][column]);
      aBtn.setFont(new Font("",Font.PLAIN,17));
      aBtn.setBorder(new RoundedBorder(5));
       gBC.gridx = x;
        gBC.gridy = y;
       gBC.insets = new Insets(1,1,1,3);///external padding for spacing between cells
      
         if(key[row][column].equals("Back"))//different size for back keys
        {
              aBtn.setPreferredSize(new Dimension(80,25));
       gBC.gridwidth=2;
       
        aBtn.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
             int carPos = s.getContentArea().getCaretPosition();
                
             if(!s.getjta().equals("")&& carPos>0)
            {
          String  rtrn = s.getjta().substring(0,carPos-1)+s.getjta().substring(carPos);
            s.addTojta(rtrn,carPos-1);
            }  
                
            }
        }); 
        }

         else
         {
              aBtn.setPreferredSize(new Dimension(45,25));
gBC.gridwidth = 1;


 aBtn.addActionListener(new ActionListener() {
     @Override      
     public void actionPerformed(ActionEvent evt) {
                int carPos = s.getContentArea().getCaretPosition();
               // javax.swing.JOptionPane.showMessageDialog(null, carPos, "InfoBox: ", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            String val = ((JButton)evt.getSource()).getText();
String rtrn = new StringBuffer(s.getjta()).insert(carPos, val).toString();

s.addTojta(rtrn,carPos+1); 
s.getContentArea().requestFocus();
//s.getContentArea().setCaretPosition(carPos);
            }
        }); 
         }
      
        pane.add(aBtn, gBC);
        currentComponents.put("btn"+String.valueOf(row)+String.valueOf(column), aBtn);
      // aBtn.setName("btn"+row+column);      
            
        x++;
        }
        x=0;
        y++;
        }
      //------------------------add keys ends---------------------  
        
        
      //--------------adding the last line using another jpanel to adjust shift keys widths  
         lastRow = key.length-1;
        
        JPanel lastLine = new JPanel();
        lastLine.setBackground(Color.white);
            lColumn = 0;
          for( lColumn=0;lColumn<key[lastRow].length;lColumn++)
        {
       
          lBtn = new JButton(key[lastRow][lColumn]);
          lBtn.setFont(new Font("",Font.PLAIN,17));
     lBtn.setBorder(new RoundedBorder(5));
       currentComponents.put("btn"+String.valueOf(lastRow)+String.valueOf(lColumn), lBtn);
         if(key[lastRow][lColumn].equals("Shift"))   //--for shift buttons
         {
        lBtn.setPreferredSize(new Dimension(73,25));
         lBtn.addActionListener(new ActionListener() {
           @Override
             public void actionPerformed(ActionEvent evt) {
             
              changeKeys();
   
            }
        }); 
         }
         else
         {
          lBtn.setPreferredSize(new Dimension(43,25));
          lBtn.addActionListener(new ActionListener() {
          @Override
              public void actionPerformed(ActionEvent evt) {
               int carPos = s.getContentArea().getCaretPosition();
             String val = ((JButton)evt.getSource()).getText();

String rtrn = new StringBuffer(s.getjta()).insert(carPos, val).toString();

s.addTojta(rtrn,carPos+1);
   s.getContentArea().requestFocus(true);
            }
        }); 
          
         }
         lastLine.add(lBtn);
            
            
        }
           //gBC.insets = new Insets(-2,1,1,1);
          gBC.ipadx=-1;
           gBC.gridwidth = 13;
           gBC.gridy = y+1;
        gBC.gridx = 0;
      // gBC.weightx=1;
      
        lastLine.setBorder(new EmptyBorder(-4,-4,-4,-4));
        pane.add(lastLine,gBC);
        //----------------------------------------------------------------------------------------------//

    }
//-----------------------adding for first time ends---------------------------------
 
 //------------------------setting key texts when shift is pressed-------------
 private static void setShifted()
 {
  for(int row=0;row<keyShifted.length;row++)
        {
             
        for(int column=0;column<keyShifted[row].length;column++)
        {
   if(currentComponents.containsKey("btn"+String.valueOf(row)+String.valueOf(column)))
   {
  currentComponents.get("btn"+String.valueOf(row)+String.valueOf(column)).setText(keyShifted[row][column]);
  if(keyShifted[row][column] == "Shift")
  {
  currentComponents.get("btn"+String.valueOf(row)+String.valueOf(column)).setBackground(Color.LIGHT_GRAY);
  }
   }
       
        }
       
        }
 
 }
 //-----------------------------------------------------------------------------------------------------
 
 //----------------keys without preessing shift key
 private static void setUnshifted()
 {
    for(int row=0;row<key.length;row++)
        {
             
        for(int column=0;column<key[row].length;column++)
        {
   if(currentComponents.containsKey("btn"+String.valueOf(row)+String.valueOf(column)))
   {
  currentComponents.get("btn"+String.valueOf(row)+String.valueOf(column)).setText(key[row][column]);
  if(keyShifted[row][column] == "Shift")
  {
  currentComponents.get("btn"+String.valueOf(row)+String.valueOf(column)).setBackground(UIManager.getColor("Button.background"));
  }
   }
       
        }
       
        }  
 }
 //--------------------------------------------------------------
 
 private static void changeKeys()
 {

        if(isShiftPressed == false)
         {
         isShiftPressed = true;
         setShifted();
         
         }
        else
        {
        isShiftPressed = false;
        setUnshifted();
        }
        
 }
 
 
    private   void createAndShowGUI() {

        this.setDefaultLookAndFeelDecorated(true);
       // JFrame frame = new JFrame("GridBagLayout Source Demo");
        this.setMinimumSize(new Dimension(500,165));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        javax.swing.JPanel cP = new javax.swing.JPanel();
       
       
this.getContentPane().setBackground(Color.white);
        //Set up the content pane.
        addComponentsToPane(this.getContentPane());

        this.pack();
        //this.setVisible(true);
          Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         this.setLocation(dim.width/2-(this.getSize().width/2)+160, dim.height/2-(this.getSize().height/2)+170);
    }
    
    
  //------------rounded border  
    
    private static class RoundedBorder implements javax.swing.border.Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }
       @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

@Override
        public boolean isBorderOpaque() {
            return true;
        }

@Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
           @Override
             public void run() {
              //  createAndShowGUI();
            }
        });
    }
}
