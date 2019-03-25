package comp1206.sushi.server;

import comp1206.sushi.common.Staff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StaffTab extends JPanel{

    ServerInterface server;

    JPanel tablePanel = new JPanel();
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable staffTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(staffTable);


    JPanel buttonsPanel = new JPanel();
    JButton addButton = new JButton("Add Staff");
    JButton removeButton = new JButton("Remove Staff");
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

        addButton.addActionListener(new AddButtonListener(server, this));
        removeButton.addActionListener(new RemoveButtonListener(server, this));
        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);

    }

    public void updateStaff()
    {
        ArrayList<Staff> staffList = new ArrayList<Staff>(server.getStaff());
        tableModel.setRowCount(0);

        for(Staff staff: staffList)
        {
            Object [] row = {staff,staff.getFatigue(),server.getStaffStatus(staff)};
            tableModel.addRow(row);
        }
    }

}
