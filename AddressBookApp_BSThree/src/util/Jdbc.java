package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Jdbc {
    private static String driver = "com.mysql.cj.jdbc.Driver";  //获取mysql 8.0数据库的驱动类
    private static String url = "jdbc:mysql://localhost:3306/addressbook?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"; //连接数据库（meizu是数据库名）
    private static String name = "root";//连接mysql的用户名
    private static String pwd = "root";//连接mysql的密码

    ///数据库连接
    public static Connection getCon()throws Exception{
        Class.forName(driver);
        Connection con= DriverManager.getConnection(url, name, pwd);
        return con;
    }

    ///数据库关闭
    public void closeCon(Connection con)throws Exception{
        if(con!=null){
            con.close();
        }
    }
//		///测试数据库是否连接
//		public static void main(String[] args) {
//			jdbc dbu=new jdbc();
//			try {
//				dbu.getCon();
//				System.out.println("数据库连接成功");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
}
