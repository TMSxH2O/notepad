package com.xhu.tms.mybatis.sqlsession;

import com.xhu.tms.mybatis.cfg.Configuration;
import com.xhu.tms.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.xhu.tms.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/19
 * Description: 用于创建一个SqlSessionFactoryBuilder对象
 * Version: V1.0
 */
public class SqlSessionFactoryBuilder {
    /**
     * @Author TMS_H2O
     * @Description 根据参数的字节输入流，来构建一个SqlSessionFactory工厂
     * @Date 17:19 2020/4/19
     * @Param [config]
     * @return com.xhu.tms.mybatis.sqlsession.SqlSessionFactory
     **/
    public SqlSessionFactory build(InputStream config){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }
}
