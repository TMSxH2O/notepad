package com.tms.test.service;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/05/01
 * Description: 账户的业务层接口
 * Version: V1.0
 */
public interface IAccountService {

    // 模拟保存用户
    void saveAccount();

    // 模拟更新账户
    void updateAccount(int i);

    // 删除账户
    int deleteAccount(int i);
}
