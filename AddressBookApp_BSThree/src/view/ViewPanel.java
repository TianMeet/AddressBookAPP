package view;

import dao.AddressDao;
import util.Jdbc;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPanel extends JPanel {
    private JTable contactTable;    
    private DefaultTableModel tableModel;

    public ViewPanel() throws Exception {
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
                try {
                    refreshData();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
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

    private void refreshData() throws Exception {
        AddressDao addressDao = new AddressDao();
        addressDao.look();
    }
}
