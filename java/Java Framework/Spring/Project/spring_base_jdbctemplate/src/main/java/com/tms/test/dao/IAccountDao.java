package com.tms.test.dao;

import com.tms.test.domain.Account;

/**
 * Created with IntelliJ IDEA.
 * User：TMS_H2O
 * Date：2020/5/3
 * Description：账户的持久层接口
 * Version：V1.0
 */
public interface IAccountDao {

    /**
     * @Author TMS_H2O
     * @Description 根据id查询账户
     * @Date 21:59 2020/5/3
     * @Param [id]
     * @return com.tms.test.domain.Account
     **/
    Account findAccountById(Integer id);

    //根据账户名查询账户
    Account findAccountByName(String accountName);

    //更新账户信息
    void updateAccount(Account account);
}
