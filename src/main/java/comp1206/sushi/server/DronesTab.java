package comp1206.sushi.server;

import comp1206.sushi.common.Drone;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DronesTab extends JPanel
{
    ServerInterface server;

    JPanel tablePanel = new JPanel();
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable dronesTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(dronesTable);


    JPanel buttonsPanel = new JPanel();
    JButton addButton = new JButton("Add New Drone");
    JButton removeBtn = new JButton("Remove Drone");
    JPanel contentPanel = new JPanel();

    public DronesTab(ServerInterface server)
    {
        this.server = server;
        this.add(contentPanel);

        contentPanel.setLayout(new BorderLayout(5,5));
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        contentPanel.add(buttonsPanel, BorderLayout.SOUTH);

        tableModel.addColumn("Drone");
        tableModel.addColumn("Speed");
        tableModel.addColumn("Status");

        dronesTable.setPreferredSize(new Dimension(400,400));
        tablePanel.add(scrollPane);
        updateDrones();

        addButton.addActionListener(new AddButtonListener());
        removeBtn.addActionListener(new DeleteButtonListener());
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(addButton);
        buttonsPanel.add(removeBtn);


    }

    private void updateDrones()
    {
        ArrayList<Drone> droneList = new ArrayList<Drone>(server.getDrones());

        tableModel.setRowCount(0);
        for(Drone drone: droneList)
        {
            Object [] row = {drone,drone.getSpeed(),server.getDroneStatus(drone)};
            tableModel.addRow(row);
        }
    }


    //adds new drones
    class AddButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JFrame addDroneFrame = new JFrame("Add New Drone");
            JPanel dronePanel = new JPanel();
            addDroneFrame.add(dronePanel);
            addDroneFrame.setVisible(true);
            addDroneFrame.setSize(500,300);
            addDroneFrame.setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel();

            inputPanel.add(new JLabel("Please specify drone speed:"));

            JTextField inputField = new JTextField();
            inputPanel.add(inputField);
            inputField.setPreferredSize(new Dimension(50,20));

            JButton confirmAddition = new JButton("Confirm");
            confirmAddition.addActionListener(buttonPressed ->
            {
                server.addDrone(Integer.parseInt(inputField.getText()));
                updateDrones();
                addDroneFrame.setVisible(false);
            });
            dronePanel.add(inputPanel);
            dronePanel.add(confirmAddition);

            addDroneFrame.pack();

        }
    }

    class DeleteButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (dronesTable.getSelectedRow() != -1)
            {
                Drone selectedDrone = (Drone)tableModel.getValueAt(dronesTable.getSelectedRow(), 0);
                try
                {
                    server.removeDrone(selectedDrone);
                }
                catch (ServerInterface.UnableToDeleteException ex)
                {
                    ex.printStackTrace();
                }
                updateDrones();
            }
        }
    }
}
