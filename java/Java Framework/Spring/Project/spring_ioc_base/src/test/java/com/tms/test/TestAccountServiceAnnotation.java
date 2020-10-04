package com.tms.test;

import com.tms.test.dao.IAccountDao;
import com.tms.test.service.IAccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/28
 * Description: 用于测试注解方式实现的Spring配置
 * Version: V1.0
 */
public class TestAccountServiceAnnotation {

    private ApplicationContext ac;

    @Before
    public void init(){
        ac = new ClassPathXmlApplicationContext("beanAnno.xml");
    }

    @Test
    public void testSpringAccountServiceAnnotation() {
        IAccountService accountService = (IAccountService) ac.getBean("accountServiceAnnotationImpl");
        System.out.println(accountService);

        IAccountDao accountDao = ac.getBean("accountDaoAnnotationImpl1", IAccountDao.class);
        System.out.println(accountDao);

        accountService.saveAccount();
    }

    public void testSpringAccountServiceAnnotationValue(){
        IAccountService accountService = ac.getBean("accountServiceAnnotationImpl", IAccountService.class);
//        accountService
    }
}
