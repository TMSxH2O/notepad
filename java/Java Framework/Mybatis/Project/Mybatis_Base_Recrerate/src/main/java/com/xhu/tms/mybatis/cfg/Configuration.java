package com.xhu.tms.mybatis.cfg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/19
 * Description: 自定义Mybatis的配置类
 * Version: V1.0
 */
public class Configuration {
    private String driver;
    private String url;
    private String username;
    private String password;
    // mappers中的信息
    // key = namespace + id(IUserMapper中每个具体的select语句的id)
    // value = (queryString + resultType) -> 组成一个mapper
    private Map<String, Mapper> mappers = new HashMap<>();

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {
        // 此处使用追加的方式，把后面新的mappers追加到最后
        this.mappers.putAll(mappers);
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
