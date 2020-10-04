package com.tms.test.dao.impl;

import com.tms.test.dao.IAccountDao;
import com.tms.test.domain.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User：TMS_H2O
 * Date：2020/5/3
 * Description：账户的持久层实现类
 * Version：V1.0
 */
public class AccountDao implements IAccountDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account findAccountById(Integer id) {
        List<Account> accounts = jdbcTemplate.query("select * from account where id = ?",
                new BeanPropertyRowMapper<Account>(Account.class), id);
        return accounts.isEmpty()?null:accounts.get(0);
    }

    @Override
    public Account findAccountByName(String accountName) {
        List<Account> accounts = jdbcTemplate.query("select from * account where name = ?",
                new BeanPropertyRowMapper<Account>(Account.class), accountName);
        if (accounts.isEmpty()){
            return null;
        }
        if (accounts.size() > 1){
            throw new RuntimeException("结果不唯一");
        }
        return accounts.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        jdbcTemplate.update("update account set name=?, money=? where id=?",
                account.getName(), account.getMoney(), account.getId());
    }
}
