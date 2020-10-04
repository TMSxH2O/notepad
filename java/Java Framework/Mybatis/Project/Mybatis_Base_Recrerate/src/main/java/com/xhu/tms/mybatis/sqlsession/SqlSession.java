package com.xhu.tms.mybatis.sqlsession;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/19
 * Description: 自定义Mybatis中和数据库交互的核心类
 *              它里面可以创建dao接口的代理对象
 * Version: V1.0
 */
public interface SqlSession {
    /**
     * @Author TMS_H2O
     * @Description 根据参数创建一个代理对象
     * @Date 17:15 2020/4/19
     * @Param [daoInterfaceClass]
     * @return T
     **/
    <T> T getMapper(Class<T> daoInterfaceClass);
    
    /**
     * @Author TMS_H2O
     * @Description 释放资源
     * @Date 17:24 2020/4/19
     * @Param []
     * @return void
     **/
    void close();
}
