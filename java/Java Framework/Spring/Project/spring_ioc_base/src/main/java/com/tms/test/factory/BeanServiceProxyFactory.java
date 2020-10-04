package com.tms.test.factory;

import com.tms.test.domain.Account;
import com.tms.test.service.IAccountTransferService;
import com.tms.test.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/05/01
 * Description: 用于创建Service的代理对象的工厂
 * Version: V1.0
 */
public class BeanServiceProxyFactory {

    private IAccountTransferService service;
    private TransactionManager manager;

    public void setManager(TransactionManager manager) {
        this.manager = manager;
    }

    public void setService(IAccountTransferService service) {
        this.service = service;
    }

    /**
     * @Author TMS_H2O
     * @Description 获取Service的代理对象
     * @Date 14:48 2020/5/1
     * @Param []
     * @return com.tms.test.service.IAccountTransferService
     **/
    public IAccountTransferService getService() {
        Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(),
                new InvocationHandler() {
            /**
             * @Author TMS_H2O
             * @Description 添加事务的支持
             * @Date 14:51 2020/5/1
             * @Param [proxy, method, args]
             * @return java.lang.Object
             **/
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object rtValue = null;
                        try{
                            //1.开启事务
                            manager.beginTransaction();
                            //2.执行操作
                            rtValue = method.invoke(service, args);
                            //3.提交事务
                            manager.commit();
                            //4.返回结果
                            return rtValue;
                        } catch (Exception e){
                            //回滚操作
                            manager.rollback();
                            throw new RuntimeException(e);
                        }finally {
                            //释放连接
                            manager.release();
                        }

                    }
                });
        return service;
    }
}
