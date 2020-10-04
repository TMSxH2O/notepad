package com.tms.test.service;

import com.tms.test.domain.Account;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/30
 * Description:
 * Version: V1.0
 */
public interface IAccountTransferService extends IAccountExampleService {

    /**
     * @Author TMS_H2O
     * @Description 转账方法
     * @Date 22:26 2020/4/30
     * @Param [sourceName 转出账户, targetName 转入账户, money 转账金额]
     * @return void
     **/
    void transfer(String sourceName, String targetName, Float money);
}
