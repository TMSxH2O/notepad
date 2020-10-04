package com.tms.test;

import com.tms.test.service.IAccountService;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/28
 * Description:
 * Version: V1.0
 */
public class TestAccountService {

    @Test
    public void testSprintAccountService() {
        FileSystemXmlApplicationContext ac = new FileSystemXmlApplicationContext("F:\\JTMS\\Spring_Test_Base\\src\\main\\resources\\bean.xml");
        IAccountService accountService = (IAccountService) ac.getBean("accountService");
        System.out.println(accountService);
        accountService.saveAccount();
    }

    // 测试AccountServiceImpl1中的set注入方法
    // 注入值为简单值
    @Test
    public void testSprintAccountService1() {
        FileSystemXmlApplicationContext ac = new FileSystemXmlApplicationContext("F:\\JTMS\\Spring_Test_Base\\src\\main\\resources\\bean.xml");
        IAccountService accountService = (IAccountService) ac.getBean("accountService1");
        accountService.saveAccount();
    }

    // 测试AccountServiceImpl2中的set注入方法
    // 注入值为复杂类型/集合类型
    @Test
    public void testSprintAccountService2() {
        FileSystemXmlApplicationContext ac = new FileSystemXmlApplicationContext("F:\\JTMS\\Spring_Test_Base\\src\\main\\resources\\bean.xml");
        IAccountService accountService = (IAccountService) ac.getBean("accountService2");
        accountService.saveAccount();
    }

}
