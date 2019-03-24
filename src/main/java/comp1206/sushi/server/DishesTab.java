package comp1206.sushi.server;


import comp1206.sushi.common.Dish;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DishesTab extends JPanel
{
    ServerInterface server;

    JPanel tablePanel = new JPanel();
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable dishesTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(dishesTable);


    JPanel buttonsPanel = new JPanel();
    JButton testButton = new JButton("TEST");

    JPanel contentPanel = new JPanel();

    public DishesTab(ServerInterface server)
    {
        this.server = server;
        this.add(contentPanel);

        contentPanel.setLayout(new BorderLayout(5,5));
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        contentPanel.add(buttonsPanel, BorderLayout.SOUTH);

        tableModel.addColumn("Dish");
        tableModel.addColumn("Description");
        tableModel.addColumn("Price");

        dishesTable.setPreferredSize(new Dimension(400,400));
        tablePanel.add(scrollPane);
        updateDishes();

        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(testButton);

    }


    private void updateDishes()
    {
        ArrayList<Dish> ingredientsList = new ArrayList<Dish>(server.getDishes());

        tableModel.setRowCount(0);

        for(Dish dish: ingredientsList)
        {
            Object [] row = {dish.getName(),dish.getDescription(),dish.getPrice()};
            tableModel.addRow(row);
        }
    }
}
