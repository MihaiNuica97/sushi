package comp1206.sushi.server;

import comp1206.sushi.common.Drone;
import comp1206.sushi.common.Staff;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveButtonListener implements ActionListener
{
    ServerInterface server;
    JPanel containingPanel;
    public RemoveButtonListener(ServerInterface server, JPanel containingPanel)
    {
        this.server = server;
        this.containingPanel = containingPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (containingPanel.getClass().toString())
        {
            case"class comp1206.sushi.server.DronesTab":
                {

                    DronesTab dronesPanel = (DronesTab) containingPanel;

                    if (dronesPanel.dronesTable.getSelectedRow() != -1) {
                        Drone selectedDrone = (Drone) dronesPanel.tableModel.getValueAt(dronesPanel.dronesTable.getSelectedRow(), 0);
                        try {
                            server.removeDrone(selectedDrone);
                        } catch (ServerInterface.UnableToDeleteException ex) {
                            ex.printStackTrace();
                        }
                        dronesPanel.updateDrones();
                    }
                    break;
                }

            case"class comp1206.sushi.server.StaffTab":
            {
                StaffTab staffTab = (StaffTab)containingPanel;

                if (staffTab.staffTable.getSelectedRow() != -1)
            {
                Staff selectedStaff = (Staff)staffTab.tableModel.getValueAt(staffTab.staffTable.getSelectedRow(), 0);
                try
                {
                    server.removeStaff(selectedStaff);
                }
                catch (ServerInterface.UnableToDeleteException ex)
                {
                    ex.printStackTrace();
                }
                staffTab.updateStaff();
            }

                break;
            }
        }

    }
}
