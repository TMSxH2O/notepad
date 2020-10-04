package com.tms.test;

import com.tms.test.config.AccountConfig;
import com.tms.test.service.IAccountService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User：TMS_H2O
 * Date：2020/5/3
 * Description：
 * Version：V1.0
 */
public class AccountTestAnnotation {

    public static void main(String[] args) {
        // 获取容器
//        ApplicationContext ac = new ClassPathXmlApplicationContext("beanAnnotation.xml");
        ApplicationContext ac = new AnnotationConfigApplicationContext(AccountConfig.class);//完全不使用xml来实现
        // 获取对象
        IAccountService service = ac.getBean("accountService", IAccountService.class);
        // 执行方法
        service.saveAccount();
    }
}
