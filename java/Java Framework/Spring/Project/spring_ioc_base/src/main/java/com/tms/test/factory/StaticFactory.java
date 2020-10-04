package com.tms.test.factory;

import com.tms.test.service.IAccountService;
import com.tms.test.service.impl.AccountServiceImpl;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/27
 * Description: 模拟一个工厂类
 * 设定：该类存放在jar包中，无法通过修改源码的方式来提供默认构造函数
 * Version: V1.0
 */
public class StaticFactory {

    public static IAccountService getAccountService(){
        return new AccountServiceImpl();
    }
}
