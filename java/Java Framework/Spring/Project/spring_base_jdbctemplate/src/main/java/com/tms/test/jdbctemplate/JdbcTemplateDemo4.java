package com.tms.test.jdbctemplate;

import com.tms.test.dao.IAccountDao;
import com.tms.test.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created with IntelliJ IDEA.
 * User：TMS_H2O
 * Date：2020/5/3
 * Description：JdbcTemplate的基本用法2
 * Version：V1.0
 */
public class JdbcTemplateDemo4 {

    public static void main(String[] args) {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取对象
        IAccountDao accountDao = ac.getBean("accountDao", IAccountDao.class);

        //执行操作
        Account account = accountDao.findAccountById(1);
        System.out.println(account);
    }
}
