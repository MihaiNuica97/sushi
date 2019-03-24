package comp1206.sushi.server;

import comp1206.sushi.common.Staff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StaffTab extends JPanel{

    ServerInterface server;

    JPanel tablePanel = new JPanel();
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable staffTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(staffTable);


    JPanel buttonsPanel = new JPanel();
    JButton testButton = new JButton("TEST");

    JPanel contentPanel = new JPanel();
    public StaffTab(ServerInterface server)
    {
        this.server = server;
        this.add(contentPanel);

        contentPanel.setLayout(new BorderLayout(5,5));
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        contentPanel.add(buttonsPanel, BorderLayout.SOUTH);

        tableModel.addColumn("Name");
        tableModel.addColumn("Fatigue");
        tableModel.addColumn("Status");

        staffTable.setPreferredSize(new Dimension(400,400));
        tablePanel.add(scrollPane);
        updateStaff();

        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(testButton);

    }

    private void updateStaff()
    {
        ArrayList<Staff> staffList = new ArrayList<Staff>(server.getStaff());
        tableModel.setRowCount(0);

        for(Staff staff: staffList)
        {
            Object [] row = {staff.getName(),staff.getFatigue(),server.getStaffStatus(staff)};
            tableModel.addRow(row);
        }
    }
}
