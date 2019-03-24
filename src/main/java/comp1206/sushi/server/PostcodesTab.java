package comp1206.sushi.server;

import comp1206.sushi.common.Postcode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PostcodesTab extends JPanel
{
    ServerInterface server;

    JPanel tablePanel = new JPanel();
    DefaultListModel<Postcode> listModel = new DefaultListModel<Postcode>();
    JList table = new JList(listModel);

    JPanel buttonsPanel = new JPanel();
    ButtonGroup buttons = new ButtonGroup();
    JButton addButton = new JButton("Add");
    JButton removeButton = new JButton("Remove");

    private void updatePostCodes()
    {
        ArrayList<Postcode> postCodeList = new ArrayList<Postcode>(server.getPostcodes());

        listModel.clear();

        for (Postcode postcode: postCodeList)
        {
          listModel.addElement(postcode);
        }
    }

    public PostcodesTab(ServerInterface server)
    {
        this.server = server;
        this.setLayout(new FlowLayout());

        tablePanel.setLayout(new BorderLayout(5,5));
        tablePanel.setPreferredSize(new Dimension(100,300));
        table.setPreferredSize(new Dimension(100,100));
        tablePanel.add(table,BorderLayout.CENTER);
        updatePostCodes();

        buttons.add(addButton);
        buttons.add(removeButton);
        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);


        this.add(tablePanel);
        this.add(buttonsPanel);
    }



}
