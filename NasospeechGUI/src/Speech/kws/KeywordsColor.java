/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.kws;

/**
 *
 * @author Tatapower SED
 *
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * This is like TableDemo, except that it substitutes a Favorite Color column
 * for the Last Name column and specifies a custom cell renderer and editor for
 * the color data.
 */
public class KeywordsColor extends JPanel {

    private boolean DEBUG = false;
    public JTable table;
    private HashMap keyColor;
    private PlotWaveKws pWave;
    private static JFrame frame;

    public KeywordsColor(List keyWord, PlotWaveKws pWave) {
        super(new GridLayout(1, 0));

        this.pWave = pWave;
        keyColor = new HashMap();
        table = new JTable(new MyTableModel(keyWord));
        table.setPreferredScrollableViewportSize(new Dimension(200, 200));
        frame.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn22.png")).getImage());
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Set up renderer and editor for the Favorite Color column.
        table.setDefaultRenderer(Color.class,
                new ColorRenderer(true));
        table.setDefaultEditor(Color.class,
                new ColorEditor());

        //Add the scroll pane to this panel.
        add(scrollPane);


        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                System.out.println("Selected " + row);

            }
        });
    }

    class MyTableModel extends AbstractTableModel {

        private String[] columnNames = {"Keywor Name",
            "  Color  ",};
        private Object[][] data;

        public MyTableModel(List keyWord) {
            if (keyWord == null) {
                return;
            }
            data = new Object[keyWord.size()][2];
            for (int i = 0; i < keyWord.size(); i++) {
                if (keyWord.get(i) == null) {
                    return;
                }
                data[i][0] = keyWord.get(i);
                if (pWave.keyBuilder.getKeywordsColor() == null) {
                    data[i][1] = new Color(165, 42, 42);
                } else {
                    if (pWave.keyBuilder.getKeywordsColor().get(keyWord.get(i)) == null) {
                        data[i][1] = new Color(165, 42, 42);
                    } else {
                        data[i][1] = pWave.keyBuilder.getKeywordsColor().get(keyWord.get(i));
                    }
                }
            }
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                        + " to " + value
                        + " (an instance of "
                        + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i = 0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j = 0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    public static void createAndShowGUI(List keyWord, PlotWaveKws pWave) {
        //Make sure we have nice window decorations.
        if (pWave == null) {
            return;
        }
        if (pWave.keyBuilder == null) {
            return;
        }

        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        frame = new JFrame("Keyword Color");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(10, 100);
        frame.setResizable(false);

        frame.addWindowListener(getWindowAdapter());
        //Create and set up the content pane.
        JComponent newContentPane = new KeywordsColor(keyWord, pWave);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);


        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private static WindowAdapter getWindowAdapter() {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {//overrode to show message
                super.windowClosing(we);

                //JOptionPane.showMessageDialog(frame, "Cant Exit");
            }

            @Override
            public void windowIconified(WindowEvent we) {
                frame.setState(JFrame.NORMAL);
                JOptionPane.showMessageDialog(frame, "Cant Minimize");
            }
        };
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
      /*  javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
         createAndShowGUI();
         }
         });*/
        List a = new ArrayList();
        a.add("key1");
        a.add("key2");

        KeywordsColor.createAndShowGUI(a, null);
    }


    /*
     * ColorRenderer.java (compiles with releases 1.2, 1.3, and 1.4) is used by
     * TableDialogEditDemo.java.
     */
    class ColorRenderer extends JLabel
            implements TableCellRenderer {

        Border unselectedBorder = null;
        Border selectedBorder = null;
        boolean isBordered = true;

        public ColorRenderer(boolean isBordered) {
            this.isBordered = isBordered;
            setOpaque(true); //MUST do this for background to show up.
        }

        public Component getTableCellRendererComponent(
                JTable table, Object color,
                boolean isSelected, boolean hasFocus,
                int row, int column) {
            keyColor.put(table.getModel().getValueAt(row, 0).toString().toLowerCase(), color);
            pWave.keyBuilder.setKeywordsColor(keyColor);
            pWave.samplingGraph.repaint();
            Color newColor = (Color) color;
            setBackground(newColor);
            if (isBordered) {
                if (isSelected) {
                    if (selectedBorder == null) {
                        selectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5,
                                table.getSelectionBackground());
                    }
                    setBorder(selectedBorder);
                } else {
                    if (unselectedBorder == null) {
                        unselectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5,
                                table.getBackground());
                    }
                    setBorder(unselectedBorder);
                }
            }

            setToolTipText("RGB value: " + newColor.getRed() + ", "
                    + newColor.getGreen() + ", "
                    + newColor.getBlue());
            return this;
        }
    }

    /*
     * ColorEditor.java (compiles with releases 1.3 and 1.4) is used by
     * TableDialogEditDemo.java.
     */
    class ColorEditor extends AbstractCellEditor
            implements TableCellEditor,
            ActionListener {

        Color currentColor;
        JButton button;
        JColorChooser colorChooser;
        JDialog dialog;
        protected static final String EDIT = "edit";

        public ColorEditor() {
            //Set up the editor (from the table's point of view),
            //which is a button.
            //This button brings up the color chooser dialog,
            //which is the editor from the user's point of view.
            button = new JButton();
            button.setActionCommand(EDIT);
            button.addActionListener(this);
            button.setBorderPainted(false);

            //Set up the dialog that the button brings up.
            colorChooser = new JColorChooser();
            dialog = JColorChooser.createDialog(button,
                    "Pick a Color",
                    true, //modal
                    colorChooser,
                    this, //OK button handler
                    null); //no CANCEL button handler
        }

        /**
         * Handles events from the editor button and from the dialog's OK
         * button.
         */
        public void actionPerformed(ActionEvent e) {
            if (EDIT.equals(e.getActionCommand())) {
                //The user has clicked the cell, so
                //bring up the dialog.
                button.setBackground(currentColor);

                colorChooser.setColor(currentColor);
                dialog.setVisible(true);

                //Make the renderer reappear.
                fireEditingStopped();
                //System.out.println(table.getSelectedRow());
            } else { //User pressed dialog's "OK" button.
                currentColor = colorChooser.getColor();

            }
        }

        //Implement the one CellEditor method that AbstractCellEditor doesn't.
        public Object getCellEditorValue() {
            return currentColor;
        }

        //Implement the one method defined by TableCellEditor.
        public Component getTableCellEditorComponent(JTable table,
                Object value,
                boolean isSelected,
                int row,
                int column) {
            currentColor = (Color) value;
            return button;
        }
    }
}
