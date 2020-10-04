package com.tms.test;

import com.tms.test.domain.Account;
import com.tms.test.service.IAccountTransferService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User：TMS_H2O
 * Date：2020/5/3
 * Description：
 * Version：V1.0
 */
public class TestAccountAOPExample {

    private IAccountTransferService accountService;

    @Before
    public void init() {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("beanAopExample.xml");
        //得到业务对象
        accountService = ac.getBean("accountService", IAccountTransferService.class);
    }

    @Test
    public void testTransfer() {
        accountService.transfer("aaa", "ccc", 100f);
    }

    @Test
    public void testFindALl() {
        List<Account> accounts = accountService.findAllAccount();
        for (Account a : accounts)
            System.out.println(a);
    }
}
