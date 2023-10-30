package view;

import dao.AddressDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyPanel extends JPanel {
    public ModifyPanel() {
        initUI();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 设置间距

        JLabel idLabel = new JLabel("联系人编号:");
        JTextField idField = new JTextField(20);

        JLabel nameLabel = new JLabel("修改后姓名:");
        JTextField nameField = new JTextField(20);

        JLabel teleLabel = new JLabel("修改后电话:");
        JTextField teleField = new JTextField(20);

        JLabel addressLabel = new JLabel("修改后地址:");
        JTextField addressField = new JTextField(20);

        JButton modifyButton = new JButton("修改联系人");

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String tele = teleField.getText();
                String address = addressField.getText();

                try {
                    modifyContactInDatabase(id, name, tele, address);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                idField.setText("");
                nameField.setText("");
                teleField.setText("");
                addressField.setText("");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(idLabel, gbc);

        gbc.gridx = 1;
        add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nameLabel, gbc);

        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(teleLabel, gbc);

        gbc.gridx = 1;
        add(teleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(addressLabel, gbc);

        gbc.gridx = 1;
        add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(modifyButton, gbc);
    }

    private void modifyContactInDatabase(String id, String name, String tele, String address) throws Exception {
        AddressDao addressDao = new AddressDao();
        addressDao.modify(id,name,tele,address);
    }
}
