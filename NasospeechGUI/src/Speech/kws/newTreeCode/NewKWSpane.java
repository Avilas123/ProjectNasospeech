package Speech.kws.newTreeCode;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 *
 * @author Tatapower SED
 *
 */
public class NewKWSpane extends MouseAdapter {

    ActionPanel[] aps;
    JPanel[] panels;

    public NewKWSpane() {
        assembleActionPanels();
        assemblePanels();
    }

    public NewKWSpane(DnDJTree treeList1, DnDJTree treeList2, DnDJTree treeList3, DnDJTree treeList4, DnDJTree treeList5, DnDJTree hotlist) {

        this.treeList1 = treeList1;
        this.treeList2 = treeList2;
        this.treeList3 = treeList3;
        this.treeList4 = treeList4;
        this.treeList5 = treeList5;
        this.hotlist = hotlist;
        assembleActionPanels();
        assemblePanels();

    }

    public void mousePressed(MouseEvent e) {
        ActionPanel ap = (ActionPanel) e.getSource();
        //if (ap.target.contains(e.getPoint())) {
        ap.toggleSelection();
        togglePanelVisibility(ap);
        // }


    }

    private void togglePanelVisibility(ActionPanel ap) {
        int index = getPanelIndex(ap);
        if (panels[index].isShowing()) {
            panels[index].setVisible(false);
        } else {
            panels[index].setVisible(true);
            for (int i = 0; i < panels.length; i++) {
                if (i != index && panels[i].isVisible()) {
                    panels[i].setVisible(false);
                }
            }
        }
        ap.getParent().validate();
    }

    private int getPanelIndex(ActionPanel ap) {
        for (int j = 0; j < aps.length; j++) {
            if (ap == aps[j]) {
                return j;
            }
        }
        return -1;
    }

    private void assembleActionPanels() {
        String[] ids = {"List 1", "List 2", "List 3", "List 4", "List 5"};
        aps = new ActionPanel[ids.length];
        for (int j = 0; j < aps.length; j++) {
            aps[j] = new ActionPanel(ids[j], this);
        }
    }

    private void assemblePanels() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 1, 2, 1);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JPanel p1 = new JPanel(new BorderLayout());
        p1.add(new JScrollPane(treeList1));

        JPanel p2 = new JPanel(new BorderLayout());
        p2.add(new JScrollPane(treeList2));

        JPanel p3 = new JPanel(new BorderLayout());
        p3.add(new JScrollPane(treeList3));

        JPanel p4 = new JPanel(new BorderLayout());
        p4.add(new JScrollPane(treeList4));

        JPanel p5 = new JPanel(new BorderLayout());
        p5.add(new JScrollPane(treeList5));

        panels = new JPanel[]{p1, p2, p3, p4, p5};
    }

    private void addComponents(Component c1, Component c2, Container c,
            GridBagConstraints gbc) {
        gbc.anchor = gbc.EAST;
        gbc.gridwidth = gbc.RELATIVE;
        c.add(c1, gbc);
        gbc.anchor = gbc.WEST;
        gbc.gridwidth = gbc.REMAINDER;
        c.add(c2, gbc);
        gbc.anchor = gbc.CENTER;
    }

    public JPanel getComponent() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 3, 0, 3);
        gbc.weightx = 1.0;
        gbc.fill = gbc.HORIZONTAL;
        gbc.gridwidth = gbc.REMAINDER;
        for (int j = 0; j < aps.length; j++) {
            panel.add(aps[j], gbc);
            panel.add(panels[j], gbc);
            panels[j].setVisible(false);
        }
        JLabel padding = new JLabel();
        gbc.weighty = 1.0;
        panel.add(padding, gbc);
        return panel;
    }

    public static void main(String[] args) {
        NewKWSpane test = new NewKWSpane();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new JScrollPane(test.getComponent()));
        f.setSize(360, 500);
        f.setLocation(200, 100);
        f.setVisible(true);
    }
    private DnDJTree treeList1;
    private DnDJTree treeList2;
    private DnDJTree treeList3;
    private DnDJTree treeList4;
    private DnDJTree treeList5;
    private DnDJTree hotlist;
}

class ActionPanel extends JPanel {

    String text;
    Font font;
    private boolean selected;
    BufferedImage open, closed;
    Rectangle target;
    final int OFFSET = 30,
            PAD = 5;

    public ActionPanel(String text, MouseListener ml) {
        this.text = text;
        addMouseListener(ml);
        font = new Font("sans-serif", Font.PLAIN, 12);
        selected = false;
        setBackground(new Color(200, 200, 220));
        setPreferredSize(new Dimension(200, 20));
        setBorder(BorderFactory.createRaisedBevelBorder());
        setPreferredSize(new Dimension(200, 20));
        createImages();
        setRequestFocusEnabled(true);
    }

    public void toggleSelection() {
        selected = !selected;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        if (selected) {
            g2.drawImage(closed, PAD, 0, this);
        } else {
            g2.drawImage(closed, PAD, 0, this);
        }
        g2.setFont(font);
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics(text, frc);
        float height = lm.getAscent() + lm.getDescent();
        float x = OFFSET;
        float y = (h + height) / 2 - lm.getDescent();
        g2.drawString(text, x, y);
    }

    private void createImages() {
        int w = 20;
        int h = getPreferredSize().height;
        target = new Rectangle(2, 0, 20, 18);
        open = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = open.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(getBackground());
        g2.fillRect(0, 0, w, h);
        int[] x = {2, w / 2, 18};
        int[] y = {4, 15, 4};
        Polygon p = new Polygon(x, y, 3);
        g2.setPaint(Color.green.brighter());
        g2.fill(p);
        g2.setPaint(Color.blue.brighter());
        g2.draw(p);
        g2.dispose();
        closed = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        g2 = closed.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(getBackground());
        g2.fillRect(0, 0, w, h);
        x = new int[]{3, 13, 3};
        y = new int[]{4, h / 2, 16};
        p = new Polygon(x, y, 3);
        g2.setPaint(Color.red);
        g2.fill(p);
        g2.setPaint(Color.blue.brighter());
        g2.draw(p);
        g2.dispose();
    }
}
