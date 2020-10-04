package com.xhu.tms.test;

import com.xhu.tms.dao.IUserDao;
import com.xhu.tms.domain.User;
import com.xhu.tms.mybatis.io.Resources;
import com.xhu.tms.mybatis.sqlsession.SqlSession;
import com.xhu.tms.mybatis.sqlsession.SqlSessionFactory;
import com.xhu.tms.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/17
 * Description: Mybatis的入门案例
 * Version: V1.0
 */
public class MybatisMainTest {
    /**
     * @Author TMS_H2O
     * @Description 入门案例实现方法
     * @Date 17:44 2020/4/17
     * @Param [args]
     * @return void
     **/
    public static void main(String[] args) throws Exception {
//          1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
//          2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
//          3.使用工厂生产一个SqlSession对象
        SqlSession session = factory.openSession();
//          4.使用SqlSession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
//          5.使用代理对象执行方法
        List<User> users1 = userDao.findAll();
        List<User> users2 = userDao.selectAll();
        System.out.println("user1:");
        for (User user: users1)
            System.out.println(user);
        System.out.println("\nuser2:");
        for (User user: users2)
            System.out.println(user);
//          6.释放资源
        session.close();
        in.close();
    }
}
