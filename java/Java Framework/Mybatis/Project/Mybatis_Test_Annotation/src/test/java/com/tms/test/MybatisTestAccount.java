package com.tms.test;

import com.tms.test.dao.AccountDao;
import com.tms.test.dao.UserDao;
import com.tms.test.domain.Account;
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
public class MybatisTestAccount {

    private InputStream in;
    private SqlSession session;
    private AccountDao accountDao;

    @Before
    public void Init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        accountDao = session.getMapper(AccountDao.class);
    }

    @After
    public void destroy() throws Exception {
        session.close();
        in.close();
    }

    @Test
    public void TestFindAll(){
        List<Account> accounts = accountDao.FindAll();
        for(Account account : accounts) {
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    @Test
    public void TestSelectAll(){
        List<Account> accounts = accountDao.SelectAll();
        for(Account account : accounts) {
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }
}
