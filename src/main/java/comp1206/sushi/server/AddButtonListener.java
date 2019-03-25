package comp1206.sushi.server;

import comp1206.sushi.common.Staff;

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
        }
    }
}
