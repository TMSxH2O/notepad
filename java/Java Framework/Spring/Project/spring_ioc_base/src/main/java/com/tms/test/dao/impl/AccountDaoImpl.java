package com.tms.test.dao.impl;

import com.tms.test.dao.IAccountDao;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/27
 * Description: 用户的持久层实现类
 * Version: V1.0
 */
public class AccountDaoImpl implements IAccountDao {

    @Override
    public void saveAccount() {
        System.out.println("保存了账户");
    }
}
