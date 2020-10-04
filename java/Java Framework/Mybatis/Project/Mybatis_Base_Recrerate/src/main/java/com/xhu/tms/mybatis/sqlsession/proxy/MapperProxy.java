package com.xhu.tms.mybatis.sqlsession.proxy;

import com.xhu.tms.mybatis.cfg.Mapper;
import com.xhu.tms.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/19
 * Description:
 * Version: V1.0
 */
public class MapperProxy implements InvocationHandler {

    // map的key是全限定类名+方法名（namespace.id）
    private Map<String, Mapper> mappers;
    private Connection conn;

    public MapperProxy(Map<String, Mapper> mappers, Connection conn) {
        this.mappers = mappers;
        this.conn = conn;
    }

    /**
     * @return java.lang.Object
     * @Author TMS_H2O
     * @Description 用于对方法进行增强，这里的增强就是调用selectList()方法
     * 此处的增强就是调用selectList方法
     * @Date 18:01 2020/4/19
     * @Param [proxy, method, args]
     **/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1.获取方法名
        String methodName = method.getName();
        // 2.获取方法所在类的名称
        String className = method.getDeclaringClass().getName();
        // 3.组合key
        String key = className + "." + methodName;
        // 4.获取mappers中的Mapper对象
        Mapper mapper = mappers.get(key);
        // 5.判断是否有mapper
        if (mapper == null)
            throw new IllegalArgumentException("传入的参数有误");
        // 6.调用工具类，执行查询所有
        return new Executor().selectList(mapper, conn);
    }
}
