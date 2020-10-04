package com.tms.test.service.impl;

import com.tms.test.dao.IAccountExampleDao;
import com.tms.test.domain.Account;
import com.tms.test.service.IAccountExampleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/28
 * Description:
 * Version: V1.0
 */
@Service
public class AccountServiceExampleAnnotationImpl implements IAccountExampleService {

    @Resource(name = "accountExampleAnnotationDao")
    private IAccountExampleDao accountDao;

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
