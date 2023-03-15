package javalearn.third;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JLS
 * @date 2023 2023/2/19 21:09
 * @email 2796315439@qq.com
 * 此类是一个管理类，目的是管理可用的连接(MyConnection)
 * Arraylist是当前类的属性
 */
public class ConnectionPool {
    private List<MyConnection> list=new ArrayList();
    private int usingCount=ConfigurationReader.getIntValue("usingcount");

    {
        //创建连接时给数组初始化创建MyConnection对象
        for (int i = 0; i <usingCount ; i++) {
            list.add(new MyConnection());
        }
    }
    //提供一个方法去给用户获取可用连接。
    public synchronized MyConnection getCoon(){
        MyConnection result=null;
        for (int i = 0; i < list.size(); i++) {
            MyConnection mc=list.get(i);
            //!mc,isuesd 证明可用
            if (!mc.isUesd()){
                mc.setUesd(true);
                result=mc;
                break;
            }
        }
        return result;
    }
    //自己单独设计一个排队等待的机制(不是唯一)
    public Connection getConnection(){//方法执行 栈 临时执行空间
        Connection result = this.getCoon();
        //5秒的等待机制
        int count = 0;//记录找寻连接的次数(100毫秒找一次)
        while(result==null && count<=50){
            try {
                Thread.sleep(100);//50次
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = this.getCoon();
        }
        if(result==null){
            //5秒钟没有人释放连接    系统比较繁忙
            //自定义异常告知
            throw new ConnectionPoolBusyException("连接池繁忙，请刷新重试");
        }
        return result;
    }
}
