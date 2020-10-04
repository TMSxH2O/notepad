package com.tms.test.utils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/30
 * Description: 连接的工具类，用于从数据源中，获取一个连接，并且实现与线程的绑定
 * Version: V1.0
 */
public class ConnectionUtils {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //获取当前线程上的连接
    public Connection getThreadConnection(){
        // 1.先从ThreadLocal上获取
        Connection conn = threadLocal.get();
        // 2.判断当前线程上是否有连接
        try {
            if (conn == null) {
                // 3.从数据源中获取一个连接，并且存入ThreadLocal中
                conn = dataSource.getConnection();
                threadLocal.set(conn);
            }
            // 4.返回当前线程上的连接
            return conn;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author TMS_H2O
     * @Description 把连接和线程解绑
     * @Date 23:05 2020/4/30
     * @Param []
     * @return void
     **/
    public void removeConnection(){
        threadLocal.remove();
    }
}
