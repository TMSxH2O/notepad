package com.tms.test;

import com.tms.test.domain.Account;
import com.tms.test.service.IAccountTransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/30
 * Description:
 * Version: V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beanTransfer.xml")
public class TestAccountTransfer {

    @Autowired
    @Qualifier("proxyAccountService")
    private IAccountTransferService as;

    @Test
    public void testTransfer(){
        as.transfer("aaa", "ccc", 100f);
    }

    @Test
    public void testFindAll() {
        // 3.执行方法
        List<Account> accounts = as.findAllAccount();
        for(Account account : accounts)
            System.out.println(account);
    }
}
