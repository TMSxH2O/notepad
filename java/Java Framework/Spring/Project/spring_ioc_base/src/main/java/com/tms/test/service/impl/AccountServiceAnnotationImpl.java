package com.tms.test.service.impl;

import com.tms.test.dao.IAccountDao;
import com.tms.test.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/28
 * Description:
 * Version: V1.0
 */
/*
 * 使用xml的方式实现
 * <bean id="AccountServiceAnnotationImpl" class="com.tms.test.service.impl.AccountServiceAnnotationImpl"
 *      scope="" init-method="" destroy-method="">
 *      <property name="" value/ref=""></property>
 * </bean>
 *
 * 用于创建对象的：
 *      作用和xml配置文件中编写一个<bean>标签功能一致
 *      @Component
 *          用于把当前对象存入spring容器中
 *          属性：
 *              value：用于指定bean的id。默认值为当前类名，且首字母小写
 *
 *      @Controller     一般用于表现层
 *      @Service        一般用于业务层
 *      @Repository     一般用于持久层
 *      以上三个注解的作用和属性与Component是一模一样的
 *      他们三个是Spring框架为我们提供明确的三层使用的注解，使我们的三层对象更加清晰
 *
 * 用于注入数据的
 *      作用和在xml配置中编写<property>标签的作用是一样的
 *      @Autowired
 *          自动按照类型注入，只要容器中有唯一的bean对象和要注入的变量类型匹配，就可以注入
 *          [null] 没有任何一个bean类型和要注入的变量类型匹配，则报错
 *          [found 2+]首先，比较IoC库中的key（id值），存在多个类型匹配，但其中某项与此时的变量名称对应，则依然可以成功赋值；
 *                  反之，其中没有任何一项能和此处的变量名对应时，报错
 *                  （还有一种可能性，比较key值时，就没有完全匹配，但是有类似如加一个数字，也能被识别成found 2，原因不明）
 *          位置：
 *              可以是变量上，也可以是方法上
 *          注意：
 *              在使用注入时，set方法就不是必须的了
*       @Qualifier （属性value：用于指定注入bean的id）
*           在按照类型注入的基础上，再按照名称注入
*           注：在给类成员注入时不能单独使用（要配合@Autowired使用），但是在给方法参数注入时可以
*       @Resource（name属性：用于指定bean的id）
*           按照bean的id注入（可以独立使用）
*       以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型无法使用
*       另外，集合类型的注入，只能使用xml注入来实现
*
*       @Value（value属性：用于指定数据的值）
*           用于注入基本类型和String类型的数据
*           注意：其中value属性中，可以使用Spring中的SpEL（也就是Spring的el表达式）
*           （SpEL的写法：${表达式}）
 * 用于改变作用范围的：
 *      作用和在bean标签中使用scope标签功能一致
 *      @Scope（value属性：指定范围的取值，singleton，prototype）
 *          用于指定bean的作用范围
 *
 * 改变生命周期的：
 *      作用和在bean标签中使用init-method和destroy-method作用是一样的
 *      @PreDestroy
 *          用于指定销毁方法
 *      @PostConstruct
 *          用于指定初始化方法
 **/
//@Component(value = "accountService")
@Component
public class AccountServiceAnnotationImpl implements IAccountService {

//    @Autowired
//    @Qualifier("accountDaoAnnotationImpl2")
    @Resource(name = "accountDaoAnnotationImpl1")
    private IAccountDao accountDao = null;

//    @Value("test")
//    private String myString;

//    @PostConstruct
//    public void init(){
//        System.out.println("init...");
//    }
//
//    @PreDestroy
//    public void destroy() {
//        System.out.println("destroy...");
//    }

    @Override
    public void saveAccount() {
        accountDao.saveAccount();
//        System.out.println(myString);
    }

}
