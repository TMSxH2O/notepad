package com.tms.test.jdbctemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created with IntelliJ IDEA.
 * User：TMS_H2O
 * Date：2020/5/3
 * Description：JdbcTemplate的基本用法2
 * Version：V1.0
 */
public class JdbcTemplateDemo2 {

    public static void main(String[] args) {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate", JdbcTemplate.class);

        //执行操作
        jt.execute("insert into account(name, money) values('ddd', 2222)");
    }
}
