package comp1206.sushi.server;


import comp1206.sushi.common.Dish;
import comp1206.sushi.common.Ingredient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

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
    JButton showRecipe = new JButton("Show Recipes");

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
        showRecipe.addActionListener(new ShowRecipeListener());
        buttonsPanel.setLayout(new BorderLayout());
        buttonsPanel.add(addButton, BorderLayout.WEST);
        buttonsPanel.add(removeButton, BorderLayout.EAST);
        buttonsPanel.add(showRecipe, BorderLayout.SOUTH);



    }


    public void updateDishes()
    {
        ArrayList<Dish> dishesList = new ArrayList<Dish>(server.getDishes());

        tableModel.setRowCount(0);

        for(Dish dish: dishesList)
        {
            Object [] row = {dish,dish.getDescription(),dish.getPrice()};
            tableModel.addRow(row);
        }
    }

    private void updateRecipes(DefaultTableModel model, Dish dish)
    {
        ArrayList<Dish> dishesList = new ArrayList<Dish>(server.getDishes());

        model.setRowCount(0);

        for (Map.Entry<Ingredient,Number> entry : dish.getRecipe().entrySet())
            {
                Object [] row = {entry.getKey(),entry.getValue().toString() +
                                    " "+entry.getKey().getUnit()};
                model.addRow(row);
            }
    }
    class ShowRecipeListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //popup window
            JFrame addFrame = new JFrame("Recipes");

            //panel for inputs
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BorderLayout());
            JComboBox<Dish> dishes = new JComboBox();
            for(Dish dish: server.getDishes())
            {
                dishes.addItem(dish);
            }

            inputPanel.add(dishes, BorderLayout.NORTH);


            //generating table
            JPanel recipePanel = new JPanel();
            DefaultTableModel recipeModel = new DefaultTableModel();
            JTable recipeTable = new JTable(recipeModel);
            recipeTable.setPreferredSize(new Dimension(400,400));
            JScrollPane recScrollPane = new JScrollPane(recipeTable);
            recipePanel.add(recScrollPane);
            recipeModel.addColumn("Ingredient");
            recipeModel.addColumn("Amount");

            //populating table
            updateRecipes(recipeModel, (Dish)dishes.getSelectedItem());
            dishes.addActionListener((new ActionListener () {
                public void actionPerformed(ActionEvent e) {
                    updateRecipes(recipeModel,(Dish)dishes.getSelectedItem());
                }
            }));

            //editing
            JButton editRecipe = new JButton("Save Recipe");
            inputPanel.add(editRecipe, BorderLayout.CENTER);
            editRecipe.addActionListener((new ActionListener () {
                public void actionPerformed(ActionEvent e)
                {
                    if (recipeTable.isEditing())
                    {
                        recipeTable.getCellEditor().stopCellEditing();
                    }
                    HashMap<Ingredient,Number> recipe = new HashMap();
                    for(int i = 0; i < recipeModel.getRowCount(); i++)
                    {
                        String qString = recipeModel.getValueAt(i,1).toString().split(" ")[0];

                        recipe.put((Ingredient)recipeModel.getValueAt(i,0),Integer.parseInt(qString));
                    }
                    server.setRecipe((Dish)dishes.getSelectedItem(),recipe);
                    addFrame.setVisible(false);
                }
            }));

            //window styling
            addFrame.setLayout(new BorderLayout());
            addFrame.add(recipePanel, BorderLayout.CENTER);
            addFrame.add(inputPanel, BorderLayout.SOUTH);


            addFrame.setSize(500, 700);
            addFrame.setLocationRelativeTo(null);
            addFrame.pack();
            addFrame.setVisible(true);
        }
    }
}
