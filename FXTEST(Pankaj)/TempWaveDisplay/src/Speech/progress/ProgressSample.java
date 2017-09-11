package Speech.progress;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
//Laishram Rahul
// This class shows a indeterminent progress bar i.e a dummy progress bar.
//We do not know the exact thime the process will take to execute. The progess shows until the execution completes
public class ProgressSample {

    JFrame frame = new JFrame("Processing...");
//This method starts the brogress bar
    public  void indeterminentProgress() {
        JProgressBar aJProgressBar = new JProgressBar(JProgressBar.HORIZONTAL);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        aJProgressBar.setStringPainted(false);
        aJProgressBar.setIndeterminate(true);
        frame.setLocationRelativeTo(null);
        frame.add(aJProgressBar, BorderLayout.CENTER);
        frame.setSize(400, 70);
        frame.setVisible(true);
    }
// This method closes teh brogress bar
    public void closeProgressBar() {
        frame.dispose();
        System.gc();
    }
}
