package comp1206.sushi.server;

import comp1206.sushi.common.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class OrdersTab extends JPanel
{
    ServerInterface server;

    JPanel tablePanel = new JPanel();
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable ordersTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(ordersTable);


    JPanel buttonsPanel = new JPanel();
    JButton testButton = new JButton("TEST");

    JPanel contentPanel = new JPanel();

    public OrdersTab(ServerInterface server)
    {
        this.server = server;
        this.add(contentPanel);

        contentPanel.setLayout(new BorderLayout(5,5));
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        contentPanel.add(buttonsPanel, BorderLayout.SOUTH);

        tableModel.addColumn("Order");
        tableModel.addColumn("Status");
        tableModel.addColumn("Distance");
        tableModel.addColumn("Cost");

        ordersTable.setPreferredSize(new Dimension(400,400));
        tablePanel.add(scrollPane);
        updateOrders();

        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(testButton);

    }


    private void updateOrders()
    {
        ArrayList<Order> ordersList = new ArrayList<>(server.getOrders());

        tableModel.setRowCount(0);

        for(Order order: ordersList)
        {
            Object [] row = {order.getName(),server.getOrderStatus(order),server.getOrderDistance(order),server.getOrderCost(order)};
            tableModel.addRow(row);
        }
    }
}

