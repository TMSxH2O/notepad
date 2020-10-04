package com.tms.test;

import com.tms.test.domain.Account;
import com.tms.test.service.IAccountExampleService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/28
 * Description: Spring的实例（1）
 * Version: V1.0
 */
public class TestSpringExampleConfig2 {

    private IAccountExampleService accountService;

    @Before
    public void init(){
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("beanExampleAnno.xml");
        // 2.得到业务层对象
        accountService = ac.getBean("accountServiceExampleAnnotationImpl", IAccountExampleService.class);
    }

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
        account.setId(3);
        account.setName("testUpdate");
        account.setMoney(2222.2f);
        accountService.updateAccount(account);
    }

    @Test
    public void testDelete() {
        accountService.deleteAccount(5);
    }
}
