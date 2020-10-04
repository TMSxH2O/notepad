package com.xhu.tms.dao;

import com.xhu.tms.domain.QueryVO;
import com.xhu.tms.domain.User;
import com.xhu.tms.mybatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/16
 * Description: 用户的持久层接口
 * Version: V1.0
 */
public interface IUserDao {
    /**
     * @Author TMS_H2O
     * @Description 查询所有User（代理模式）
     * @Date 23:50 2020/4/16
     * @Param []
     * @return java.util.List<User>
     **/
    List<User> selectAll();
    // 此处还可以使用批注的方法，在方法上使用@Select中间加伤SQL语句，可以不配置Mapper文件的情况下做到同样的效果
    /*
     * @Select("select * from user")
     * List<User> findAll();
     **/
    //这种方法，还需要更改Mybatis的总配置文件中的Mapper设定
    //将其改成class的方法
    /**
     * @Author TMS_H2O
     * @Description 查询所有User（自定义实现类）
     * @Date 21:22 2020/4/19
     * @Param []
     * @return java.util.List<com.xhu.tms.domain.User>
     **/
    @Select("select * from user")
    List<User> findAll();

    /**
     * @Author TMS_H2O
     * @Description 查询所有操作
     * @Date 22:02 2020/4/21
     * @Param []
     * @return java.util.List<com.tms.test.domain.User>
     **/
    List<User> FindAll();
    //此处还可以使用批注的方法,在方法上使用@Select,在后面跟上执行的SQL语句
    //这样可以在不需要配置Mapper文件的情况下做到同样的效果
    /*
     * @Select("select * from user" )
     * List<User> findAll();
     **/
    //这种方法，还需要更改Mybatis的总配置文件中的Mapper设定
    //将其改成class的方法

    /**
     * @Author TMS_H2O
     * @Description 存储用户信息
     * @Date 22:26 2020/4/21
     * @Param [user]
     * @return void
     **/
    void SaveUser(User user);

    /**
     * @Author TMS_H2O
     * @Description 更新用户信息
     * @Date 22:27 2020/4/21
     * @Param [user]
     * @return void
     **/
    void UpdateUser(User user);

    /**
     * @Author TMS_H2O
     * @Description 根据id删除用户
     * @Date 22:32 2020/4/21
     * @Param
     * @return
     **/
    void DeleteUser(Integer id);

    /**
     * @Author TMS_H2O
     * @Description 根据id查询用户
     * @Date 22:41 2020/4/21
     * @Param [id]
     * @return com.tms.test.domain.User
     **/
    User FindUserId(Integer id);

    /**
     * @Author TMS_H2O
     * @Description 根据名称，模糊查询用户信息
     * @Date 22:47 2020/4/21
     * @Param [username]
     * @return java.util.List<com.tms.test.domain.User>
     **/
    List<User> FindUserByName(String username);

    /**
     * @Author TMS_H2O
     * @Description 获取总用户数
     * @Date 22:53 2020/4/21
     * @Param []
     * @return int
     **/
    int FindTotal();

    /**
     * @Author TMS_H2O
     * @Description 根据QueryVO查询中的条件查询用户
     * @Date 15:47 2020/4/22
     * @Param [vo]
     * @return int
     **/
    List<User> FindUserByVO(QueryVO vo);
}
