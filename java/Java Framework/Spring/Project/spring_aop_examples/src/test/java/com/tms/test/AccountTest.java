package com.tms.test;

import com.tms.test.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/05/01
 * Description:
 * Version: V1.0
 */
public class AccountTest {

    public static void main(String[] args) {
        // 获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 获取对象
        IAccountService service = ac.getBean("accountService", IAccountService.class);
//        // 执行方法
        service.saveAccount();
//        service.updateAccount(1);
//        service.deleteAccount(1);
    }
}
