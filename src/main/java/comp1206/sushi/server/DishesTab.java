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
    JButton addButton = new JButton("Add new Dish");
    JButton removeButton = new JButton("Remove Dish");

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

        addButton.addActionListener(new AddButtonListener(server, this));
        removeButton.addActionListener(new RemoveButtonListener(server, this));
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);

    }


    public void updateDishes()
    {
        ArrayList<Dish> ingredientsList = new ArrayList<Dish>(server.getDishes());

        tableModel.setRowCount(0);

        for(Dish dish: ingredientsList)
        {
            Object [] row = {dish,dish.getDescription(),dish.getPrice()};
            tableModel.addRow(row);
        }
    }
}
