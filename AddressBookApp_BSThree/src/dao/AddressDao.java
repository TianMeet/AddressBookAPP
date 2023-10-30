package dao;

import util.Jdbc;

import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class AddressDao extends Component {
    Connection conn = null;
    Statement statement = null;
    ResultSet rs = null;
    //查看
    private JTable contactTable;
    private DefaultTableModel tableModel;
    public void look() throws Exception {
        try {
            conn = Jdbc.getCon();
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
        } catch (Exception e) {
            throw new RuntimeException(e);
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
    //新增
    public void add(String name, String tele, String address) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = Jdbc.getCon();
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
    //修改
    public void modify(String id, String name, String tele, String address) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = Jdbc.getCon();
            String sql = "UPDATE address_book_contact SET name=?, tele=?, address=? WHERE id=?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, name);
            psmt.setString(2, tele);
            psmt.setString(3, address);
            psmt.setString(4, id);
            psmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "联系人修改成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "修改联系人失败！", "错误", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭资源
            try {
                if (psmt != null) psmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
    //删除
    public void dele(String name) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = Jdbc.getCon();
            String sql = "DELETE FROM address_book_contact WHERE name=?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, name);
            psmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "联系人删除成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "删除联系人失败！", "错误", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
