package uk.org.filscentia;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;



public class JTableExamples {

    private JFrame jframe;
    private JTable jtable;

    // Constructor
    JTableExamples() {
        // Frame initialization
        this.jframe = new JFrame();

        // Frame Title
        this.jframe.setTitle("JTable Example");

        // Data to be displayed in the JTable
        String[][] data = {
                { "Alice", "4031", "CSE" },
                { "Bob", "6014", "IT" }
        };

        // Column Names
        String[] columnNames = { "Name", "ID Number", "Department" };

        // set the cells to not be editable; changes from double click being 
        // edit and can be picked in the mouse listener below
        final DefaultTableModel model = new DefaultTableModel(data,columnNames){

            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
            
        };

        // Initializing the JTable
        jtable = new JTable(model);
        jtable.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(jtable);
        this.jframe.add(sp);
        this.jframe.setSize(500, 200);
        this.jframe.setVisible(true);

        // allow only rows to be selected and only one row to be selected at once
        jtable.setRowSelectionAllowed(true);
        jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final ListSelectionModel lsm = jtable.getSelectionModel();
        
        // This will show events on how the user interacts with the table
        // it will fire two events when the user starts making the selection
        // and when it finishes.  eg. if draging over multiple rows
        // if you only want to respond to a click on a row, best to use
        // the mouse listener see below.
        lsm.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("Row selection " + e);
            }

        });

        // specfically listen for events from the mouse
        // get a clicked event for 
        jtable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = jtable.rowAtPoint(e.getPoint());

                if (row >= 0) {
                    // This code runs every time a row is clicked, 
                    // even if it was already selected.
                    System.out.println("Row clicked: " + row);

                    // If you need to check for double-clicks specifically:
                    if (e.getClickCount() == 2) {
                        System.out.println("Row double-clicked!");
                    }
                }
            }
        });
    }

}