package com.tms.test;

import com.tms.test.dao.UserDao;
import com.tms.test.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/25
 * Description:
 * Version: V1.0
 */
public class MybatisTestUser {

    private InputStream in;
    private SqlSession session;
    private UserDao userDao;

    @Before
    public void Init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void destroy() throws Exception {
        session.close();
        in.close();
    }

    @Test
    public void TestFindAll(){
        List<User> users = userDao.FindAll();
        for(User user : users)
            System.out.println(user);
    }

    @Test
    public void TestFindUserAndAccount(){
        List<User> users = userDao.FindUserAndAccount();
        for(User user : users){
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    @Test
    public void TestFirstLevelCache(){
        User user = userDao.FindUserById(51);
        System.out.println(user);

        User user1 = userDao.FindUserById(51);
        System.out.println(user1);

        System.out.println(user == user1);
    }

}
