package comp1206.sushi.server;

import comp1206.sushi.common.Ingredient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class IngredientsTab extends JPanel
{
    ServerInterface server;

    JPanel tablePanel = new JPanel();
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable ingredientsTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(ingredientsTable);


    JPanel buttonsPanel = new JPanel();
    JButton addButton = new JButton("Add new Ingredient");
    JButton removeButton = new JButton("Remove Ingredient");

    JPanel contentPanel = new JPanel();

    public IngredientsTab(ServerInterface server)
    {
        this.server = server;
        this.add(contentPanel);

        contentPanel.setLayout(new BorderLayout(5,5));
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        contentPanel.add(buttonsPanel, BorderLayout.SOUTH);

        tableModel.addColumn("Ingredient");
        tableModel.addColumn("Stock");

        ingredientsTable.setPreferredSize(new Dimension(400,400));
        tablePanel.add(scrollPane);
        updateIngredients();

        addButton.addActionListener(new AddButtonListener(server,this));
        removeButton.addActionListener(new RemoveButtonListener(server, this));

        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);

    }


    public void updateIngredients()
    {
        ArrayList<Ingredient> ingredientsList = new ArrayList<Ingredient>(server.getIngredients());

        tableModel.setRowCount(0);

        for(Ingredient ingredient: ingredientsList)
        {
            Object [] row = {ingredient,(server.getIngredientStockLevels().get(ingredient) + " " + ingredient.getUnit())};
            tableModel.addRow(row);
        }
    }
}

