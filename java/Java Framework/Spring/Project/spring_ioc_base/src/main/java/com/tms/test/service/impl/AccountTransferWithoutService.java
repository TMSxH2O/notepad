package com.tms.test.service.impl;

import com.tms.test.dao.IAccountTransferDao;
import com.tms.test.domain.Account;
import com.tms.test.service.IAccountTransferService;
import com.tms.test.utils.TransactionManager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/30
 * Description:
 * Version: V1.0
 */
public class AccountTransferWithoutService implements IAccountTransferService {

    private IAccountTransferDao accountDao;

    public void setAccountDao(IAccountTransferDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * @return java.util.List<com.tms.test.domain.Account>
     * @Author TMS_H2O
     * @Description 设置线程控制
     * @Date 23:10 2020/4/30
     * @Param []
     **/
    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccount(accountId);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        Account source = accountDao.findAccountByName(sourceName);
        // 根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        // 转出账户减钱
        source.setMoney(source.getMoney() - money);
        // 转入账户加钱
        target.setMoney(target.getMoney() + money);
        // 更新转出账户
        accountDao.updateAccount(source);

        // 手动添加异常
        int i = 1 / 0;

        // 更新转入账户
        accountDao.updateAccount(target);
    }
}
