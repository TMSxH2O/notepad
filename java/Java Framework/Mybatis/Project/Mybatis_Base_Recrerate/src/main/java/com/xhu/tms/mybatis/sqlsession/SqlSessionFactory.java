package com.xhu.tms.mybatis.sqlsession;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/19
 * Description:
 * Version: V1.0
 */
public interface SqlSessionFactory {
    /**
     * @Author TMS_H2O
     * @Description 用于打开一个新的SqlSession对象
     * @Date 17:11 2020/4/19
     * @Param []
     * @return SqlSession
     **/
    SqlSession openSession();
}
