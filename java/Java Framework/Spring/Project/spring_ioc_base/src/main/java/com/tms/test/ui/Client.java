package com.tms.test.ui;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.tms.test.dao.IAccountDao;
import com.tms.test.factory.BeanFactory;
import com.tms.test.service.IAccountService;
import com.tms.test.service.impl.AccountServiceImpl;
import javafx.application.Application;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/27
 * Description: 模拟一个表现层，用于调用业务层
 * Version: V1.0
 */
public class Client {

    // 获取spring的IoC核心容器，并根据id获取对象
    /*
     * 获取spring的ioc核心容器，并根据id获取对象
     *      ClassPathApplicationContext：可以加载类配置路径下的配置文件，要求配置文件必须在类路劲下；否则，无法读取
     *      FileSystemXmlApplicationContext：可以加载磁盘任意路径下的配置文件（必须有访问权限）
     *      AnnotationConfigApplicationContext：用于读取注解创建容器的。
     * 核心容器的两个接口引发的问题：
     * ApplicationContext（单例对象；是BeanFactory的子接口，功能更加齐全，使用更多）：
     *      在构建核心容器时，创建对象采用的策略是采用立即加载的方式，也就是说，只要一读取完配置文件，马上就创建配置文件中配置的的对象
     * BeanFactory（多例对象）：
     *      在构建核心容器时，创建对象采用的策略是采用延迟加载的方式，在使用对象时才会创建对象
     **/
    @Test
    public void test() {
        // 1.获取核心容器对象
//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        FileSystemXmlApplicationContext ac = new FileSystemXmlApplicationContext("F:\\JTMS\\Spring_Test_Base\\src\\main\\resources\\bean.xml");
        // 2.根据id获取bean对象（两种方式)
        IAccountService accountService = (IAccountService) ac.getBean("accountService");
//        IAccountDao accountDao = ac.getBean("accountDao", IAccountDao.class);
        System.out.println(accountService);
//        System.out.println(accountDao);
        accountService.saveAccount();

        // 手动始放
        ac.close();
    }

//    public static void main(String[] args) {
////        IAccountService as = new AccountServiceImpl();
//        for (int i=0; i<5; i++) {
//            IAccountService as = (IAccountService) BeanFactory.getBean("accountService");
//            System.out.println(as);
//            as.saveAccount();
//        }
//    }
}
