package com.tms.test.service.impl;

import com.tms.test.service.IAccountService;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/27
 * Description:
 * Version: V1.0
 */
public class AccountServiceImpl1 implements IAccountService {

    private String name;
    private Integer age;
    private Date birthday;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public void saveAccount() {
//        accountDao.saveAccount();
        System.out.println("正在调用saveAccount……");
        System.out.println("name:" + name + "\nage:" + age + "\nbirthday:" + birthday);
    }
}
