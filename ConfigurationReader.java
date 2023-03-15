package javalearn.third;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author JLS
 * @date 2023 2023/2/19 20:07
 * @email 2796315439@qq.com
 */
public class ConfigurationReader {

    private static Map<String,String> map=new HashMap();
    static {
        Properties pro=new Properties();
        //线程获取获取当前线程对象通过类加载器去寻找像数据流的文件
        //这个方式获取文件不必要相对路径，是一个通用写法。
        InputStream im= Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.properties");
        try {
            pro.load(im);
            //加载完成后，数据就存到了pro集合里面了。
            Enumeration enumeration = pro.propertyNames();
            /**
             * 迭代器          propertyNames()方法返回值    JDBC结果集合
             * iterator       Enumeration        ResulSet
             * hasNext()      hasMoreElements     next()
             * next()          nextElement()      getXXX()int String??..
             *遍历pro内容存入hashmap中。
             */

            while (enumeration.hasMoreElements()){
                String key = (String) enumeration.nextElement();
              String value= pro.getProperty(key);
              map.put(key,value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //此方法获取map里面内容
    }
    public static String getValue(String key){
    return  map.get(key);
    }
    public static int getIntValue(String key){
       return Integer.parseInt( map.get(key));
    }
}
