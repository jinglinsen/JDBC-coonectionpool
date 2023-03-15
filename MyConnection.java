package javalearn.third;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author JLS
 * @date 2023 2023/2/19 18:07
 * @email 2796315439@qq.com
 * 目的，将一个真实可用连接，和一个状态绑定在一起
 *   两个属性
 *      Connection
 *  isUsed    boolean
 */
public class MyConnection extends AbstractConnection{
    private Connection coon;
    private boolean uesd = false;//表示连接空闲
    private static String forName =ConfigurationReader.getValue("forName");
    private String url = ConfigurationReader.getValue("url");
    private String User = ConfigurationReader.getValue("User");
    private String Password = ConfigurationReader.getValue("Password");


    /**
     *加载并注册驱动.static静态代码块是为了去给加载驱动只加载一次。
     */
    static {
        try {
            Class.forName(forName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**用代码块是为了给coon赋值，但是赋值需要三个属性所以用代码块来赋值
     */
    {
        try {
            //加载并注册驱动
            coon = DriverManager.getConnection(url, User, Password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getCoon() {
        return coon;
    }

    public boolean isUesd() { //获取连接状态
        return uesd;
    }

    public void setUesd(boolean uesd) {//连接状态切换
        this.uesd = uesd;
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return this.coon.prepareStatement(sql);
    }

    @Override
    public void close() throws SQLException {
        this.uesd = false;
    }
}
