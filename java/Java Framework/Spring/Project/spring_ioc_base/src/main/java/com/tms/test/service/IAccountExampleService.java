package com.tms.test.service;

import com.tms.test.domain.Account;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/28
 * Description: Spring实例项目（1）
 * Version: V1.0
 */
public interface IAccountExampleService {

    /**
     * @Author TMS_H2O
     * @Description 查询所有账户信息
     * @Date 21:43 2020/4/28
     * @Param []
     * @return java.util.List<com.tms.test.domain.Account>
     **/
    List<Account> findAllAccount();

    /**
     * @Author TMS_H2O
     * @Description 通过id查询账户
     * @Date 21:44 2020/4/28
     * @Param []
     * @return com.tms.test.domain.Account
     **/
    Account findAccountById(Integer id);

    /**
     * @Author TMS_H2O
     * @Description 保存账户
     * @Date 21:46 2020/4/28
     * @Param [account]
     * @return void
     **/
    void saveAccount(Account account);

    /**
     * @Author TMS_H2O
     * @Description 更新账户信息
     * @Date 21:47 2020/4/28
     * @Param [account]
     * @return void
     **/
    void updateAccount(Account account);

    /**
     * @Author TMS_H2O
     * @Description 通过id删除账户
     * @Date 21:52 2020/4/28
     * @Param [accountId]
     * @return void
     **/
    void deleteAccount(Integer accountId);
}
