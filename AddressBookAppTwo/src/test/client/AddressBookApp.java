package test.client;
import test.operation.AddPanel;
import test.operation.DeletePanel;
import test.operation.ModifyPanel;
import test.operation.ViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddressBookApp {
    private static JPanel cards; // 面板用于存储不同的操作页面
    private static CardLayout cardLayout;
    private static String driver = "com.mysql.cj.jdbc.Driver";  //获取mysql 8.0数据库的驱动类
    private static String url = "jdbc:mysql://localhost:3306/addressbook?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"; //连接数据库（meizu是数据库名）

    private static String name = "root";//连接mysql的用户名
    private static String pwd = "root";//连接mysql的密码
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
            Connection con = null;// 连接数据库对象
            try {
                con = DriverManager.getConnection(url,name,pwd);
                Statement statement = con.createStatement();// 创建SQL命令对象
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public static Connection getConnection(){
        try{
            Connection conn = DriverManager.getConnection(url,name,pwd);//获取连接对象
            return conn;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("个人通讯录系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // 创建主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        JButton viewButton = new JButton("查看联系人");
        JButton addButton = new JButton("添加联系人");
        JButton modifyButton = new JButton("修改联系人");
        JButton deleteButton = new JButton("删除联系人");

        // 创建右侧的操作页面面板
        cards = new JPanel();
        cardLayout = new CardLayout();
        cards.setLayout(cardLayout);

        // 添加不同的操作页面
        cards.add(new ViewPanel(), "View");
        cards.add(new AddPanel(), "Add");
        cards.add(new ModifyPanel(), "Modify");
        cards.add(new DeletePanel(), "Delete");

        // 为按钮添加点击事件监听器
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "View");
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Add");
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Modify");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Delete");
            }
        });

        // 将按钮添加到按钮面板
        buttonPanel.add(viewButton);
        buttonPanel.add(addButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);

        // 将按钮面板和操作页面面板添加到主面板
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(cards, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }
}







