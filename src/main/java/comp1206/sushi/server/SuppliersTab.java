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
    JButton testButton = new JButton("TEST");

    JPanel contentPanel = new JPanel();


    public SuppliersTab(ServerInterface server)
    {
        this.server = server;

        this.server = server;
        this.add(contentPanel);

        contentPanel.setLayout(new BorderLayout(5,5));
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        contentPanel.add(buttonsPanel, BorderLayout.SOUTH);

        tableModel.addColumn("Supplier");
        tableModel.addColumn("Postcode");

        suppliersTable.setPreferredSize(new Dimension(400,400));
        tablePanel.add(scrollPane);
        updateSuppliers();

        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(testButton);
    }

    private void updateSuppliers()
    {
        ArrayList<Supplier> supplyList = new ArrayList<Supplier>(server.getSuppliers());

        tableModel.setRowCount(0);

        for(Supplier supplier: supplyList)
        {
            Object [] row = {supplier.getName(),supplier.getPostcode().getName()};
            tableModel.addRow(row);
        }

    }
}
