package com.tms.test.jdbctemplate;

import com.tms.test.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User：TMS_H2O
 * Date：2020/5/3
 * Description：JdbcTemplate的基本用法3   CRUD操作
 * Version：V1.0
 */
public class JdbcTemplateDemo3 {

    public static void main(String[] args) {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate", JdbcTemplate.class);

        //执行操作
        //保存
//        jt.update("insert into account(name, money) values(?, ?)", "eee", 4444);

        //更新
//        jt.update("update account set name=?, money=? where id=?", "ccc_test", 9999f, 3);

        //删除
//        jt.update("delete from account where id=?", 3);

        //查询所有
//        List<Account> list = jt.query("select * from account where money > ?",
//                new AccountRowMapper(), 900f);
//        List<Account> list = jt.query("select * from account where money > ?",
//                new BeanPropertyRowMapper<Account>(Account.class), 900f);

        //查询一个
//        List<Account> list = jt.query("select * from account where id = ?",
//                new BeanPropertyRowMapper<Account>(Account.class), 1);
//        System.out.println(list.isEmpty()?"没有数据":list.get(0));

        //查询返回一行/一列（使用整合函数，但不加group by子句）
        long count = jt.queryForObject("select count(*) from account where money > ?",Long.class, 1000f);
        System.out.println(count);
//        for (Account a : list)
//            System.out.println(a);
    }
}

/**
 * @Author TMS_H2O
 * @Description 定义Account的封装策略
 * @Date 21:43 2020/5/3
 * @Param
 * @return
 **/
class AccountRowMapper implements RowMapper<Account>{

    /**
     * @Author TMS_H2O
     * @Description 把结果集中的数据封装到Account中，然后Spring把没有个Account加到集合中
     * @Date 21:43 2020/5/3
     * @Param [resultSet, i]
     * @return com.tms.test.domain.Account
     **/
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setMoney(resultSet.getFloat("money"));
        return account;
    }
}