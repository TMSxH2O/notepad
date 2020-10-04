package com.tms.test.dao;

import com.tms.test.domain.Account;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/30
 * Description:
 * Version: V1.0
 */
public interface IAccountTransferDao extends IAccountExampleDao {

    // 根据名称查询账户
    // 有唯一结果就返回  没有结果就返回null  结果不止一条就报错
    Account findAccountByName(String accountName);
}
