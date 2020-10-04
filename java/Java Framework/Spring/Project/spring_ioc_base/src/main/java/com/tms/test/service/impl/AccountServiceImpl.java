package com.tms.test.service.impl;

import com.tms.test.dao.IAccountDao;
import com.tms.test.dao.impl.AccountDaoImpl;
import com.tms.test.factory.BeanFactory;
import com.tms.test.service.IAccountService;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/27
 * Description:
 * Version: V1.0
 */
public class AccountServiceImpl implements IAccountService {

//    private IAccountDao accountDao;

    // 用来测试注入（重点注意注入的数据种类）
    // 经常变化的数据，不适合用于注入的方式
    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl() {
    }

    public AccountServiceImpl(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void saveAccount() {
//        accountDao.saveAccount();
        System.out.println("正在调用saveAccount……");
        System.out.println("name:" + name + "\nage:" + age + "\nbirthday:" + birthday);
    }
//
//    public void init(){
//        System.out.println("init:AccountService");
//    }
//
//    public void destroy(){
//        System.out.println("destroy:AccountService");
//    }

//    private IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");

//    private int i = 0;
//
//    @Override
//    public void saveAccount() {
//        accountDao.saveAccount();
//        i++;
//        System.out.println(i);
//    }
}
