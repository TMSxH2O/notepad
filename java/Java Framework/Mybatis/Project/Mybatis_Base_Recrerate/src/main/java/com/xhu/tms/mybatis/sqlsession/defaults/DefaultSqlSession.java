package com.xhu.tms.mybatis.sqlsession.defaults;

import com.xhu.tms.mybatis.cfg.Configuration;
import com.xhu.tms.mybatis.sqlsession.SqlSession;
import com.xhu.tms.mybatis.sqlsession.proxy.MapperProxy;
import com.xhu.tms.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/19
 * Description:
 * Version: V1.0
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        this.connection = DataSourceUtil.getConnection(cfg);
    }

    /**
     * @return T
     * @Author TMS_H2O
     * @Description 用于创建代理对象
     * @Date 17:54 2020/4/19
     * @Param [daoInterfaceClass]
     **/
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        /*
         * 代理：
         * 1.代理谁就用谁的类加载器
         * 2.代理谁就要用与对方相同的接口（此处本身就是一个接口，因此直接使用）
         * 3.何如代理
         */
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{ daoInterfaceClass }, new MapperProxy(cfg.getMappers(), connection));
    }

    /**
     * @return void
     * @Author TMS_H2O
     * @Description 用于释放资源
     * @Date 17:54 2020/4/19
     * @Param []
     **/
    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
