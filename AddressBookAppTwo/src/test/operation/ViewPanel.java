package test.operation;

import test.client.AddressBookApp;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewPanel extends JPanel {
    private JTable contactTable;
    private DefaultTableModel tableModel;

    public ViewPanel() {
        initUI();
        refreshData();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // 创建表格
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("姓名");
        tableModel.addColumn("电话");
        tableModel.addColumn("地址");

        contactTable = new JTable(tableModel);
        customizeTableAppearance(contactTable);

        JScrollPane scrollPane = new JScrollPane(contactTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 添加 padding

        // 添加表格到面板
        add(scrollPane, BorderLayout.CENTER);

        // 添加刷新按钮
        JButton refreshButton = new JButton("刷新");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshData();
            }
        });

        // 添加刷新按钮到面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void customizeTableAppearance(JTable table) {
        // 居中对齐
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // 设置表头居中
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        // 设置行高
        table.setRowHeight(30);

        // 设置表格边框
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void refreshData() {
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            conn = AddressBookApp.getConnection();
            statement = conn.createStatement();
            String sql = "SELECT * FROM address_book_contact";
            rs = statement.executeQuery(sql);

            // 清空表格
            tableModel.setRowCount(0);

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String tele = rs.getString("tele");
                String address = rs.getString("address");

                // 向表格添加行数据
                tableModel.addRow(new Object[]{id, name, tele, address});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
