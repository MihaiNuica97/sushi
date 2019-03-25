package comp1206.sushi.server;

import comp1206.sushi.common.Ingredient;
import comp1206.sushi.common.Postcode;
import comp1206.sushi.common.Staff;
import comp1206.sushi.common.Supplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonListener implements ActionListener
{
    ServerInterface server;
    JPanel containingPanel;
    public AddButtonListener(ServerInterface server, JPanel containingPanel)
    {
        this.server = server;
        this.containingPanel = containingPanel;
    }
    public void actionPerformed(ActionEvent e)
    {

        System.out.println(containingPanel.getClass().toString());
        switch(containingPanel.getClass().toString()) {
            case "class comp1206.sushi.server.DronesTab": {
                DronesTab dronesPanel = (DronesTab) containingPanel;
                JFrame addDroneFrame = new JFrame("Add New Drone");
                JPanel dronePanel = new JPanel();
                addDroneFrame.add(dronePanel);
                addDroneFrame.setSize(500, 300);
                addDroneFrame.setLocationRelativeTo(null);

                JPanel inputPanel = new JPanel();

                inputPanel.add(new JLabel("Please specify drone speed:"));

                JTextField inputField = new JTextField();
                inputPanel.add(inputField);
                inputField.setPreferredSize(new Dimension(50, 20));

                JButton confirmAddition = new JButton("Confirm");
                confirmAddition.addActionListener(buttonPressed ->
                {

                    server.addDrone(Integer.parseInt(inputField.getText()));
                    dronesPanel.updateDrones();
                    addDroneFrame.setVisible(false);

                });
                //clicks submit button on enter keypress
                inputField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmAddition.doClick();
                    }
                });
                dronePanel.add(inputPanel);
                dronePanel.add(confirmAddition);
                addDroneFrame.pack();
                addDroneFrame.setVisible(true);

                break;
            }
            case "class comp1206.sushi.server.StaffTab": {
                StaffTab staffTab = (StaffTab) containingPanel;

                JFrame addFrame = new JFrame("Add New Staff Member");
                JPanel addPanel = new JPanel();
                addFrame.add(addPanel);
                addFrame.setSize(500, 300);
                addFrame.setLocationRelativeTo(null);

                JPanel inputPanel = new JPanel();

                inputPanel.add(new JLabel("Please specify staff name:"));

                JTextField inputField = new JTextField();
                inputPanel.add(inputField);
                inputField.setPreferredSize(new Dimension(100, 20));

                JButton confirmAddition = new JButton("Confirm");
                //actionListener for the confirm button
                confirmAddition.addActionListener(buttonPressed ->
                {
                    server.addStaff(inputField.getText());
                    staffTab.updateStaff();
                    addFrame.setVisible(false);
                });

                //clicks submit button on enter keypress
                inputField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmAddition.doClick();
                    }
                });

                addPanel.add(inputPanel);
                addPanel.add(confirmAddition);
                addFrame.setVisible(true);

                addFrame.pack();

                break;
            }

            case "class comp1206.sushi.server.SuppliersTab": {
                SuppliersTab suppliersTab = (SuppliersTab) containingPanel;

                JFrame addFrame = new JFrame("Add New Supplier");
                JPanel addPanel = new JPanel();
                addFrame.add(addPanel);
                addFrame.setSize(500, 300);
                addFrame.setLocationRelativeTo(null);

                JPanel inputPanel = new JPanel();

                inputPanel.add(new JLabel("Please specify name and Postcode:"));

                JTextField inputField = new JTextField();
                inputPanel.add(inputField);
                inputField.setPreferredSize(new Dimension(100, 20));

                JComboBox<Postcode> poDropdown = new JComboBox<>();

                poDropdown.removeAllItems();
                for (Postcode postcode : server.getPostcodes()) {
                    poDropdown.addItem(postcode);
                }



                JButton confirmAddition = new JButton("Confirm");
                //actionListener for the confirm button
                confirmAddition.addActionListener(buttonPressed ->
                {
                    server.addSupplier(inputField.getText(),(Postcode)poDropdown.getSelectedItem());
                    suppliersTab.updateSuppliers();
                    addFrame.setVisible(false);
                });

                //clicks submit button on enter keypress
                inputField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmAddition.doClick();
                    }
                });

                addPanel.add(inputPanel);
                addPanel.add(poDropdown);
                addPanel.add(confirmAddition);
                addFrame.setVisible(true);

                addFrame.pack();

                break;
            }

            case "class comp1206.sushi.server.DishesTab": {
                DishesTab dishesTab = (DishesTab) containingPanel;

                //popup window
                JFrame addFrame = new JFrame("Add New Dish");
                JPanel addPanel = new JPanel();
                addFrame.add(addPanel);
                addFrame.setSize(500, 300);
                addFrame.setLocationRelativeTo(null);

                //panel for inputs
                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new GridLayout(5,2));


                JLabel nameLabel = new JLabel("Name: ");
                nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                JTextField nameInput = new JTextField();
                inputPanel.add(nameLabel);
                inputPanel.add(nameInput);

                JLabel descLabel = new JLabel("Description: ");
                descLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                JTextField descInput = new JTextField();
                inputPanel.add(descLabel);
                inputPanel.add(descInput);

                JLabel priceLabel = new JLabel("Price: ");
                priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                JTextField priceInput = new JTextField();
                inputPanel.add(priceLabel);
                inputPanel.add(priceInput);


                JLabel restockTLabel = new JLabel("Restock Threshhold: ");
                restockTLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                JTextField restockTInput = new JTextField();
                inputPanel.add(restockTLabel);
                inputPanel.add(restockTInput);

                JLabel restockALabel = new JLabel("Restock Amount: ");
                restockALabel.setHorizontalAlignment(SwingConstants.RIGHT);
                JTextField restockAInput = new JTextField();
                inputPanel.add(restockALabel);
                inputPanel.add(restockAInput);

                nameInput.setPreferredSize(new Dimension(100, 20));
                descInput.setPreferredSize(new Dimension(100, 20));
                priceInput.setPreferredSize(new Dimension(100, 20));
                restockTInput.setPreferredSize(new Dimension(100, 20));
                restockAInput.setPreferredSize(new Dimension(100, 20));



                JButton confirmAddition = new JButton("Confirm");

                //actionListener for the confirm button
                confirmAddition.addActionListener(buttonPressed ->
                {
                    server.addDish(nameInput.getText(),
                            descInput.getText(),
                            Integer.parseInt(priceInput.getText()),
                            Integer.parseInt(restockTInput.getText()),
                            Integer.parseInt(restockAInput.getText()));
                    dishesTab.updateDishes();
                    addFrame.setVisible(false);
                });

                //clicks submit button on enter keypress
                nameInput.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmAddition.doClick();
                    }
                });

                addPanel.add(inputPanel);
                addPanel.add(confirmAddition);
                addFrame.setVisible(true);

                addFrame.pack();

                break;
            }

            case "class comp1206.sushi.server.IngredientsTab": {
                IngredientsTab ingredientsTab = (IngredientsTab) containingPanel;

                //popup window
                JFrame addFrame = new JFrame("Add New Supplier");
                JPanel addPanel = new JPanel();
                addFrame.add(addPanel);
                addFrame.setSize(500, 300);
                addFrame.setLocationRelativeTo(null);

                //panel for inputs
                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new GridLayout(5,2));


                JLabel nameLabel = new JLabel("Name: ");
                nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                JTextField nameInput = new JTextField();
                inputPanel.add(nameLabel);

                inputPanel.add(nameInput);

                JLabel unitLabel = new JLabel("Unit: ");
                unitLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                JTextField unitInput = new JTextField();
                inputPanel.add(unitLabel);
                inputPanel.add(unitInput);

                JComboBox<Supplier> poDropdown = new JComboBox<>();
                JLabel poLabel = new JLabel("Supplier: ");
                poLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                poDropdown.removeAllItems();
                for (Supplier supplier : server.getSuppliers()) {
                    poDropdown.addItem(supplier);
                }
                inputPanel.add(poLabel);
                inputPanel.add(poDropdown);

                JLabel restockTLabel = new JLabel("Restock Threshhold: ");
                restockTLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                JTextField restockTInput = new JTextField();
                inputPanel.add(restockTLabel);
                inputPanel.add(restockTInput);

                JLabel restockALabel = new JLabel("Restock Amount: ");
                restockALabel.setHorizontalAlignment(SwingConstants.RIGHT);
                JTextField restockAInput = new JTextField();
                inputPanel.add(restockALabel);
                inputPanel.add(restockAInput);

                nameInput.setPreferredSize(new Dimension(100, 20));
                unitInput.setPreferredSize(new Dimension(100, 20));
                restockTInput.setPreferredSize(new Dimension(100, 20));
                restockAInput.setPreferredSize(new Dimension(100, 20));



                JButton confirmAddition = new JButton("Confirm");

                //actionListener for the confirm button
                confirmAddition.addActionListener(buttonPressed ->
                {
                    server.addIngredient(nameInput.getText(),
                            unitInput.getText(),
                            (Supplier) poDropdown.getSelectedItem(),
                            Integer.parseInt(restockTInput.getText()),
                            Integer.parseInt(restockAInput.getText()));
                    ingredientsTab.updateIngredients();
                    addFrame.setVisible(false);
                });

                //clicks submit button on enter keypress
                nameInput.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmAddition.doClick();
                    }
                });

                addPanel.add(inputPanel);
                addPanel.add(confirmAddition);
                addFrame.setVisible(true);

                addFrame.pack();

                break;
            }



        }
    }
}
