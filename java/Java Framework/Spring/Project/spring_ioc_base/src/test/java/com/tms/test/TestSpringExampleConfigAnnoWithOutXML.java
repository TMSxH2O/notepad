package com.tms.test;

import com.tms.test.config.SpringExampleConfig;
import com.tms.test.domain.Account;
import com.tms.test.service.IAccountExampleService;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/28
 * Description:
 * Version: V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringExampleConfig.class)
public class TestSpringExampleConfigAnnoWithOutXML {

    @Autowired
    private IAccountExampleService accountService;

//    @Before
//    public void init(){
//        // 1.获取容器
//        // 应该用注解配置文件的方式
//        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringExampleConfig.class);
//        // 2.得到业务层对象
//        accountService = ac.getBean("accountServiceExampleAnnotationImpl", IAccountExampleService.class);
//    }

    @Test
    public void testFindAll() {
        // 3.执行方法
        List<Account> accounts = accountService.findAllAccount();
        for(Account account : accounts)
            System.out.println(account);
    }

    @Test
    public void testFindAccountById() {
        Account account = accountService.findAccountById(2);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("testSpringExample");
        account.setMoney(9999.9f);
        accountService.saveAccount(account);
    }

    @Test
    public void testUpdate() {
        Account account = new Account();
        account.setId(5);
        account.setName("testUpdate");
        account.setMoney(2222.2f);
        accountService.updateAccount(account);
    }

    @Test
    public void testDelete() {
        accountService.deleteAccount(5);
    }

    @Test
    public void testQueryRunner(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringExampleConfig.class);
        QueryRunner runner1 = ac.getBean("runner", QueryRunner.class);
        QueryRunner runner2 = ac.getBean("runner", QueryRunner.class);
        // false证明此处的QueryRunner使用的时多例而非单例
        System.out.println(runner1 == runner2);
    }

}
