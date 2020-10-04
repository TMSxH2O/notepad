package com.xhu.tms.mybatis.utils;

import com.xhu.tms.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/19
 * Description: 用于创建数据源的工具类
 * Version: V1.0
 */
public class DataSourceUtil {
    
    /**
     * @Author TMS_H2O
     * @Description 用于获取一个连接
     * @Date 18:15 2020/4/19
     * @Param [cfg]
     * @return java.sql.Connection
     **/
    public static Connection getConnection(Configuration cfg){
        try {
            Class.forName(cfg.getDriver());
            return DriverManager.getConnection(cfg.getUrl(),
                    cfg.getUsername(), cfg.getPassword());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
