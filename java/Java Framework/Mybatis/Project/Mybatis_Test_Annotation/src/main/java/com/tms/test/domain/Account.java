package com.tms.test.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/25
 * Description:
 * Version: V1.0
 */
public class Account implements Serializable {

    private Integer id;
    private Integer uid;
    private Double money;
    // 用户的映射
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                '}';
    }
}
