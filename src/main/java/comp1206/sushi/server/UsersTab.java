package comp1206.sushi.server;

import comp1206.sushi.common.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class UsersTab extends JPanel
{
    ServerInterface server;

    JPanel tablePanel = new JPanel();
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable usersTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(usersTable);


    JPanel buttonsPanel = new JPanel();
    JButton testButton = new JButton("TEST");

    JPanel contentPanel = new JPanel();

    public UsersTab(ServerInterface server)
    {
        this.server = server;
        this.add(contentPanel);

        contentPanel.setLayout(new BorderLayout(5,5));
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        contentPanel.add(buttonsPanel, BorderLayout.SOUTH);

        tableModel.addColumn("User Name");
        tableModel.addColumn("Postcode");

        usersTable.setPreferredSize(new Dimension(400,400));
        tablePanel.add(scrollPane);
        updateUsers();

        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(testButton);

    }


    private void updateUsers()
    {
        ArrayList<User> userList = new ArrayList<>(server.getUsers());

        tableModel.setRowCount(0);

        for(User user: userList)
        {
            Object [] row = {user.getName(),user.getPostcode().getName()};
            tableModel.addRow(row);
        }
    }
}

