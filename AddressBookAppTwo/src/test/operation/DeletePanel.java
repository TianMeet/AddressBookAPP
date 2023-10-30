package test.operation;

import test.client.AddressBookApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

                deleteContactFromDatabase(name);

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

    private void deleteContactFromDatabase(String name) {
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = AddressBookApp.getConnection();
            String sql = "DELETE FROM address_book_contact WHERE name=?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, name);
            psmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "联系人删除成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "删除联系人失败！", "错误", JOptionPane.ERROR_MESSAGE);
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
