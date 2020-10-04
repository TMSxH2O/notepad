package com.tms.test.service.impl;

import com.tms.test.dao.IAccountDao;
import com.tms.test.dao.IAccountExampleDao;
import com.tms.test.dao.impl.AccountExampleDao;
import com.tms.test.domain.Account;
import com.tms.test.service.IAccountExampleService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/28
 * Description:
 * Version: V1.0
 */
public class AccountServiceExampleImpl implements IAccountExampleService {

    private IAccountExampleDao accountDao;

    public void setAccountDao(IAccountExampleDao accountDao) {
        this.accountDao = accountDao;
    }

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
}
