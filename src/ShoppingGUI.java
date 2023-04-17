import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

/**
 * GUI for the shopping list.
 *
 * <P>Various attributes and methods to create graphical user interface
 * for the user.</P>
 *
 * @author Jose Karsikas
 * @version 1.1
 * @since 1.1
 */
public class ShoppingGUI extends JFrame {

    /**
     * Two dimensional array to store data and display in JTable.
     */
    private Object rowData[][] = { { "", "" } };

    /**
     * Array that contains headers of each column.
     */
    private Object columnNames[] = { "Quantity:", "Item Name:" };

    /**
     * Used to set model of JTable, @see DefaultTableModel.
     */
    private DefaultTableModel model;

    /**
     * Used to create @Link JTable.
     */
    private JTable table;

    /**
     * List of items in Shopping list.
     */
    private MyLinkedList myList;

    /**
     * Class constructor.
     *
     * @param newList list of items from shopping list.
     * @param shoppingList reference to ShoppingList object.
     */
    public ShoppingGUI(MyLinkedList newList, ShoppingList shoppingList) {
        myList = newList;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        model = new DefaultTableModel(rowData, columnNames);
        table = new JTable();

        table.setModel(model);
        table.setPreferredScrollableViewportSize(new Dimension(300, 300));

        JScrollPane scrollPanel = new JScrollPane(table);

        JButton addButton = new JButton("Add New Product");
        addButton.setPreferredSize(new Dimension(300, 30));
        addButton.addActionListener(e -> model.addRow(rowData[0]));

        JButton removeButton = new JButton("Remove");
        removeButton.setPreferredSize(new Dimension(147, 30));
        removeButton.addActionListener(e -> {
            if (table.getSelectedRow() != -1) {
                model.removeRow(table.getSelectedRow());
            }
        });

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(147, 30));
        saveButton.addActionListener(e -> {

            // Re-write the list
            myList.resetAll();

            for (int i = 0; i < table.getRowCount(); i++) {
                int qty = Integer.parseInt(table.getValueAt(i, 0).toString());
                String name = table.getValueAt(i, 1).toString();
                ShoppingItem item = new ShoppingItem(name, qty);
                myList.add(item);
            }

            // Save to file
            try {
                shoppingList.saveList();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout());
        tablePanel.add(scrollPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(saveButton);

        add(tablePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setTitle("Shopping List");
        setSize(350, 455);
        setVisible(true);
    }

    /**
     * Updates JTable with items from MyLinkedList.
     */
    public void updateTable() {

        while (model.getRowCount() < myList.length()) {
            model.addRow(new Object[]{"", ""});
        }

        while (model.getRowCount() > myList.length()) {
            model.removeRow(model.getRowCount()-1);
        }

        for (int i = 0; i < myList.length(); i++) {
            table.setValueAt(myList.get(i).getItemQuantity(), i, 0);
            table.setValueAt(myList.get(i).getItemName(), i, 1);
        }
    }
}
