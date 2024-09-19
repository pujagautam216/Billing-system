import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillingSystem {

    public static void main(String[] args) {
      
        JFrame frame = new JFrame("Billing System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblProdName = new JLabel("NAME");
        lblProdName.setBounds(30, 20, 100, 20);
        frame.add(lblProdName);

        JTextField txtProdName = new JTextField();
        txtProdName.setBounds(150, 20, 150, 20);
        frame.add(txtProdName);

        JLabel lblQuantity = new JLabel("QUANTITY");
        lblQuantity.setBounds(320, 20, 100, 20);
        frame.add(lblQuantity);

        JTextField txtQuantity = new JTextField();
        txtQuantity.setBounds(420, 20, 150, 20);
        frame.add(txtQuantity);

        JLabel lblPrice = new JLabel("PRICE");
        lblPrice.setBounds(30, 50, 100, 20);
        frame.add(lblPrice);

        JTextField txtPrice = new JTextField();
        txtPrice.setBounds(150, 50, 150, 20);
        frame.add(txtPrice);
        
        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(150, 80, 80, 30);
        frame.add(btnAdd);

        JButton btnRemoveLatest = new JButton("Remove Latest");
        btnRemoveLatest.setBounds(250, 80, 130, 30);
        frame.add(btnRemoveLatest);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(400, 80, 80, 30);
        frame.add(btnDelete);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(500, 80, 80, 30);
        frame.add(btnClear);

        JButton btnTotal = new JButton("Total");
        btnTotal.setBounds(150, 320, 80, 30);
        frame.add(btnTotal);

        JLabel lblTotal = new JLabel("Total: ");
        lblTotal.setBounds(250, 320, 200, 30);
        frame.add(lblTotal);

        String[] columnNames = {"Product Name", "Quantity", "Price", "Total Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 130, 540, 150);
        frame.add(scrollPane);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String prodName = txtProdName.getText();
                String prodQty = txtQuantity.getText();
                String prodPrice = txtPrice.getText();

                try {
                    int quantity = Integer.parseInt(prodQty);
                    double price = Double.parseDouble(prodPrice);
                    double totalPrice = quantity * price;
                    model.addRow(new Object[]{prodName, quantity, price, totalPrice});
                    txtProdName.setText("");
                    txtQuantity.setText("");
                    txtPrice.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for Quantity and Price.");
                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                txtProdName.setText("");
                txtQuantity.setText("");
                txtPrice.setText("");

                model.setRowCount(0);
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete.");
                }
            }
        });

        btnRemoveLatest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int rowCount = model.getRowCount();
                if (rowCount > 0) {
                    model.removeRow(rowCount - 1);
                } else {
                    JOptionPane.showMessageDialog(null, "No rows available to remove.");
                }
            }
        });

        btnTotal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double total = 0;
                for (int i = 0; i < model.getRowCount(); i++) {
                    total += (double) model.getValueAt(i, 3);  // Sum the Total Price column
                }
                lblTotal.setText("Total: " + total);
            }
        });

        frame.setVisible(true);
    }
}
