package com.tms.test.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created with IntelliJ IDEA.
 * User：TMS_H2O
 * Date：2020/5/3
 * Description：JdbcTemplate的基本用法1
 * Version：V1.0
 */
public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
        //准备数据源，spring的内置数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring_test");
        ds.setUsername("root");
        ds.setPassword("root");

        //创建JdbcTemplate对象
//        JdbcTemplate jt = new JdbcTemplate();
        JdbcTemplate jt = new JdbcTemplate(ds);
        //设置数据源
//        jt.setDataSource(ds);
        //执行操作
        jt.execute("insert into account(name, money) values('bbb', 1000)");
    }
}
