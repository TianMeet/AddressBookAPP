package test.operation;


import test.client.AddressBookApp;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPanel extends JPanel {
    public AddPanel() {
        initUI();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 设置间距

        JLabel nameLabel = new JLabel("姓名:");
        JTextField nameField = new JTextField(20);

        JLabel teleLabel = new JLabel("电话:");
        JTextField teleField = new JTextField(20);

        JLabel addressLabel = new JLabel("地址:");
        JTextField addressField = new JTextField(20);

        JButton addButton = new JButton("添加联系人");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String tele = teleField.getText();
                String address = addressField.getText();

                addContactToDatabase(name, tele, address);

                nameField.setText("");
                teleField.setText("");
                addressField.setText("");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);

        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(teleLabel, gbc);

        gbc.gridx = 1;
        add(teleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(addressLabel, gbc);

        gbc.gridx = 1;
        add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(addButton, gbc);
    }

    private void addContactToDatabase(String name, String tele, String address) {
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = AddressBookApp.getConnection();
            String sql = "INSERT INTO address_book_contact (name, tele, address) VALUES (?, ?, ?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, name);
            psmt.setString(2, tele);
            psmt.setString(3, address);
            psmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "联系人添加成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "添加联系人失败！", "错误", JOptionPane.ERROR_MESSAGE);
        } finally {
            // 关闭资源
            try {
                if (psmt != null) psmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
