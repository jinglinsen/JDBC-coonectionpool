package javalearn.third;

import com.mysql.cj.jdbc.Driver;

import java.awt.print.Paper;
import java.sql.*;

/**
 * @author JLS
 * @date 2023 2023/2/16 17:14
 * @email 2796315439@qq.com
 */
public class JdbcConnection {

//    public static void main(String[] args)  {
//
//        //1.加载并注册驱动
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        //2.通过DriverManger获取数据库连接
//        String url="jdbc:mysql://localhost:3306/mydatabase?serverTimezone=Asia/Shanghai&useSSL=false";
//        String User="root";
//        String Password="root";
//        try {
//            Connection coon=DriverManager.getConnection(url,User,Password);
//
//
//            //3.通过Connection对象获取Statement对象
//            String sql="SELECT * FROM `users`";
////            String  sql2="INSERT into users values (\"li甫\",525);";
//            PreparedStatement statement = coon.prepareStatement(sql);
//            //4.通过Statement执行sql语句
//            /**
//             * executeQuery();执行查询
//             * executeUpdate();执行DDL语句
//             * execute();执行所有sql语句
//             */
//
////            statement.execute(sql2);
//            ResultSet rs = statement.executeQuery(sql);
//
//            //操作rs结果集
//            while (rs.next()){
//                String user=rs.getString("username");
//                Integer password = rs.getInt("password");
//                System.out.println(user+"---"+password);
//            }
//            //关闭连接
//            rs.close();
//            statement.close();
//            coon.close();
//
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally { //关闭连接
//
//        }
//
//    }


    public static void main(String[] args) throws SQLException {
        ConnectionPool cp=new ConnectionPool();
        MyConnection mc = cp.getCoon();
        Connection coon=mc.getCoon();
        String sql="SELECT * FROM `users`";
        PreparedStatement statement = coon.prepareStatement(sql);
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
                String user=rs.getString("username");
                Integer password = rs.getInt("password");
                System.out.println(user+"---"+password);
            }
        rs.close();
        statement.close();
        mc.setUesd(false);
    }
}