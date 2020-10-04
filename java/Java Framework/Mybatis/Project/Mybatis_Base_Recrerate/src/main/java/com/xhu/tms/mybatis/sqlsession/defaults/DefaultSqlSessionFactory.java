package com.xhu.tms.mybatis.sqlsession.defaults;

import com.xhu.tms.mybatis.cfg.Configuration;
import com.xhu.tms.mybatis.sqlsession.SqlSession;
import com.xhu.tms.mybatis.sqlsession.SqlSessionFactory;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/19
 * Description: SqlSessionFactory接口的实现类
 * Version: V1.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    /**
     * @return com.xhu.tms.mybatis.sqlsession.SqlSession
     * @Author TMS_H2O
     * @Description 用于创建一个新的操作数据库对象/核心对象
     * @Date 17:52 2020/4/19
     * @Param []
     **/
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
