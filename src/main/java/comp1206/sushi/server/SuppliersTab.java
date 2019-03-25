package comp1206.sushi.server;


import comp1206.sushi.common.Supplier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class SuppliersTab extends JPanel
{

    ServerInterface server;

    JPanel tablePanel = new JPanel();
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable suppliersTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(suppliersTable);


    JPanel buttonsPanel = new JPanel();
    JButton addButton = new JButton("Add new Supplier");
    JButton removeButton = new JButton("Remove Supplier");


    JPanel contentPanel = new JPanel();


    public SuppliersTab(ServerInterface server) {
        this.server = server;

        this.server = server;
        this.add(contentPanel);

        contentPanel.setLayout(new BorderLayout(5, 5));
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        contentPanel.add(buttonsPanel, BorderLayout.SOUTH);

        tableModel.addColumn("Supplier");
        tableModel.addColumn("Postcode");

        suppliersTable.setPreferredSize(new Dimension(400, 400));
        tablePanel.add(scrollPane);
        updateSuppliers();


        addButton.addActionListener(new AddButtonListener(server, this));
        removeButton.addActionListener(new RemoveButtonListener(server, this));
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);
    }

    public void updateSuppliers()
    {
        ArrayList<Supplier> supplyList = new ArrayList<Supplier>(server.getSuppliers());

        tableModel.setRowCount(0);

        for(Supplier supplier: supplyList)
        {
            Object [] row = {supplier,supplier.getPostcode().getName()};
            tableModel.addRow(row);
        }

    }
}
