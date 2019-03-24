package comp1206.sushi.server;

import comp1206.sushi.common.Postcode;
import comp1206.sushi.common.Supplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    JTextField inputField = new JTextField();

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

        addButton.addActionListener(new AddButtonListener());
        removeButton.addActionListener(new DeleteButtonListener());
        buttons.add(addButton);
        buttons.add(removeButton);

        buttonsPanel.setLayout(new GridLayout(3,1));
        buttonsPanel.add(inputField);
        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);



        this.add(tablePanel);
        this.add(buttonsPanel);
    }

    class AddButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String input = inputField.getText();
            server.addPostcode(input);
            inputField.setText("");
            updatePostCodes();
        }
    }

    class DeleteButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Postcode selectedValue = listModel.getElementAt(table.getSelectedIndex());
            ArrayList<Supplier> supplierList = new ArrayList<>(server.getSuppliers());
            boolean isUsed = false;
            for(Supplier supplier: supplierList)
            {
                if(selectedValue == supplier.getPostcode())
                {
                    isUsed = true;
                    break;
                }
            }
            if(isUsed)
            {
                System.out.println("Cannot delete. Postcode is being used by a supplier");
            }
            else
                {

                try {
                    server.removePostcode(selectedValue);
                } catch (ServerInterface.UnableToDeleteException exception) {
                    System.out.println("Unable to delete. No such element");
                }
                updatePostCodes();
            }
        }
    }


}
