package view;

import dao.AddressDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DeletePanel extends JPanel {
    public DeletePanel() {
        initUI();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 设置间距

        JLabel nameLabel = new JLabel("联系人姓名:");
        JTextField nameField = new JTextField(20);

        JButton deleteButton = new JButton("删除联系人");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();

                try {
                    deleteContactFromDatabase(name);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                nameField.setText("");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);

        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(deleteButton, gbc);
    }

    private void deleteContactFromDatabase(String name) throws Exception {
        AddressDao addressDao = new AddressDao();
        addressDao.dele(name);
    }
}
