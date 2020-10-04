package com.tms.test.service.impl;

import com.tms.test.dao.IAccountExampleDao;
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
public class AccountTransferService implements IAccountTransferService {

    private IAccountTransferDao accountDao;
    private TransactionManager manager;

    public void setManager(TransactionManager manager) {
        this.manager = manager;
    }

    public void setAccountDao(IAccountTransferDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * @Author TMS_H2O
     * @Description 设置线程控制
     * @Date 23:10 2020/4/30
     * @Param []
     * @return java.util.List<com.tms.test.domain.Account>
     **/
    @Override
    public List<Account> findAllAccount() {
        try{
            //1.开启事务
            manager.beginTransaction();
            //2.执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //3.提交事务
            manager.commit();
            //4.返回结果
            return accounts;
        } catch (Exception e){
            //回滚操作
            manager.rollback();
            throw new RuntimeException(e);
        }finally {
            //释放连接
            manager.release();
        }
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
        try {
            manager.beginTransaction();
            // 根据名称查询转出账户
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
            int i = 1/0;

            // 更新转入账户
            accountDao.updateAccount(target);
            manager.commit();
        }catch (Exception e){
            manager.rollback();
        }finally {
            manager.release();
        }
    }
}
