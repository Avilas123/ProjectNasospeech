
    /*
       An applet showing a red square and a blue square that the user
       can drag with the mouse.   The user can drag the squares off
       the applet and drop them.  There is no way of getting them back.
    */
    
    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;
    
    
    public class DragTwoSquares extends JApplet
                    implements MouseListener, MouseMotionListener {
       
    
       int x1, y1;   // Coords of top-left corner of the red square.
       int x2, y2;   // Coords of top-left corner of the blue square.
       
       /* Some variables used during dragging */
       
       boolean dragging;      // Set to true when a drag is in progress.
       
       boolean dragRedSquare; // True if red square is being dragged, false
                              //    if blue square is being dragged.
                              
       int offsetX, offsetY;  // Offset of mouse-click coordinates from 
                              //   top-left corner of the square that was
                              //   clicked.
                              
    
       JPanel drawSurface;  // This is the panel on which the actual
                            // drawing is done.  It is used as the
                            // content pane of the applet.  It actually
                            // belongs to an anonymous class which is
                            // defined in place in the init() method.
    
       public void init() {
             // Initialize the applet by putting the squares in a
             // starting position and creating the drawing surface
             // and installing it as the content pane of the applet.

          x1 = 10;  // Set up initial positions of the squares.
          y1 = 10;
          x2 = 50;
          y2 = 10;
          
          drawSurface = new JPanel() {
                    // This anonymous inner class defines the drawing
                    // surface for the applet.
                public void paintComponent(Graphics g) {
                       // Draw the two squares and a black frame 
                       // around the panel.
                   super.paintComponent(g);  // Fill with background color.
                   g.setColor(Color.red);
                   g.fillRect(x1, y1, 30, 30);
                   g.setColor(Color.blue);
                   g.fillRect(x2, y2, 30, 30);
                   g.setColor(Color.black);
                   g.drawRect(0,0,getSize().width-1,getSize().height-1);
                }
             };

          drawSurface.setBackground(Color.lightGray);
          drawSurface.addMouseListener(this);
          drawSurface.addMouseMotionListener(this);
          
          setContentPane(drawSurface);

       } // end init();
       
                    
       public void mousePressed(MouseEvent evt) { 
              // Respond when the user presses the mouse on the panel.
              // Check which square the user clicked, if any, and start
              // dragging that square.
       
          if (dragging)  // Exit if a drag is already in progress.
             return;
             
          int x = evt.getX();  // Location where user clicked.
          int y = evt.getY();
          
          if (x >= x2 && x < x2+30 && y >= y2 && y < y2+30) {
                 // It's the blue square (which should be checked first,
                 // since it's in front of the red square.)
             dragging = true;
             dragRedSquare = false;
             offsetX = x - x2;  // Distance from corner of square to (x,y).
             offsetY = y - y2;
          }
          else if (x >= x1 && x < x1+30 && y >= y1 && y < y1+30) {
                 // It's the red square.
             dragging = true;
             dragRedSquare = true;
             offsetX = x - x1;  // Distance from corner of square to (x,y).
             offsetY = y - y1;
          }
    
       }
    
    
       public void mouseReleased(MouseEvent evt) { 
              // Dragging stops when user releases the mouse button.
           dragging = false;
       }
    
    
       public void mouseDragged(MouseEvent evt) { 
               // Respond when the user drags the mouse.  If a square is 
               // not being dragged, then exit. Otherwise, change the position
               // of the square that is being dragged to match the position
               // of the mouse.  Note that the corner of the square is placed
               // in the same position with respect to the mouse that it had
               // when the user started dragging it.
           if (dragging == false)  
             return;
           int x = evt.getX();
           int y = evt.getY();
           if (dragRedSquare) {  // Move the red square.
              x1 = x - offsetX;
              y1 = y - offsetY;
           }
           else {   // Move the blue square.
              x2 = x - offsetX;
              y2 = y - offsetY;
           }
           drawSurface.repaint();
       }
    
    
       public void mouseMoved(MouseEvent evt) { }
       public void mouseClicked(MouseEvent evt) { }
       public void mouseEntered(MouseEvent evt) { }
       public void mouseExited(MouseEvent evt) { }
    
    
    } 