package com.tms.test.dao;

import com.tms.test.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/25
 * Description: @Select @Insert @Update @Delete
 * Version: V1.0
 */
public interface UserDao {

    // 用于解决实体类中属性名和数据库中无法对应的问题
    // 和mapper配置文件中的配置类似，下面使用@ResultMap调用这里的id
    /**
     * 特别注意！！
     * 这个@Results必须在方法上调用第一次之后，才能使用@ResultMap
     * 或者说，因为@Results标签必须对应一个方法，所以只有一个Map设置的时候就不需要使用@ResultMap了
     **/
//    @Results(id = "userMap", value = {
//            @Result(id = true, property = "id", column = "id"),
//            @Result(property = "username", column = "username")
//    })
    // 查询所有用户
    @Select(value = "select * from user")
//    @ResultMap(value = {"userMap"})   标准写法
    List<User> FindAll();

    @Select("select * from user where id = #{id}")
    User FindUserById(Integer id);

//    @Select("select * from user where username like #{username}")
    @Select("select * from user where username like '%${value}%'")
    List<User> FindUserByName(String username);

    @Select("select * from user")
    @Results(id = "userAccountMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "address", column = "address"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "accounts", column = "id",
                    many = @Many(select = "com.tms.test.dao.AccountDao.FindAccountByUid", fetchType = FetchType.LAZY))
    })
    List<User> FindUserAndAccount();
}


