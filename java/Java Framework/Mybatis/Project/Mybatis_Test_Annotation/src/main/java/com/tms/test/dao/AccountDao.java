package com.tms.test.dao;

import com.tms.test.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/25
 * Description:
 * Version: V1.0
 */
public interface AccountDao {


    // 查询所有的账户，同时获取每个账户所属的用户信息
    @Select("select * from account")
    @Results(id = "accountUserMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "money", column = "money"),
            @Result(property = "user", column = "uid",
                    one = @One(select = "com.tms.test.dao.UserDao.FindUserById", fetchType = FetchType.LAZY))
    })
    List<Account> FindAll();

    @Select("select * from account")
    @ResultMap("accountUserMap")
    List<Account> SelectAll();

    @Select("select * from account where uid = #{uid}")
    List<Account> FindAccountByUid(Integer uid);
}
