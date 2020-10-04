package com.tms.test.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/27
 * Description: 是一个创建Bean对象的工厂，创建service和dao对象的(整个过程，被成为工厂模式的思路）
 * Bean：在计算机英语中，有可重用组件的含义
 * JavaBean（不等于实体类，范围大于实体类）：
 * 用Java语言编写的可重用组件
 * 步骤：
 * 1）需要一个配置文件配置service和dao
 * 2）通过配置文件中配置的内容，反射创建对象
 * 配置文件的内容：
 * 唯一标志（key）：全限定类名（key=value）
 * 两种配置文件的方式：
 * 1）xml文件
 * 2）properties文件
 * Version: V1.0
 */
public class BeanFactory {

    // 定义一个Properties对象
    private static Properties properties;

    // 定义一个Map，用于创建我们要创建的对象，我们把它称为容器
    private static Map<String, Object> beans;

    // 使用静态代理块为Properties对象赋值
    static {
        try {
            // 初始化对象
            properties = new Properties();
            // 获取properties文件的流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            properties.load(in);
            beans = new HashMap<String, Object>();
            // 取出配置文件中所有的key
            Enumeration keys = properties.keys();
            // 遍历枚举
            while(keys.hasMoreElements()){
                // 取出每个key
                String key = keys.nextElement().toString();
                // 根据key获取value
                String beanPath = properties.getProperty(key);
                Object value = Class.forName(beanPath).newInstance();
                // 把key和value存入容器中
                beans.put(key, value);
            }
        } catch (Exception e) {
            // 初始化异常
            throw new ExceptionInInitializerError("初始化Properties异常");
        }
    }

    // 根据bean名称获取对象
    public static Object getBean(String beanName){
        return beans.get(beanName);
    }

//    /**
//     * @Author TMS_H2O
//     * @Description 根据bean的名称，获取bean对象
//     * @Date 15:32 2020/4/27
//     * @Param [beanName]
//     * @return T
//     **/
//    public static Object getBean(String beanName){
//        Object bean = null;
//        try {
//            String beanPath = properties.getProperty(beanName);
//            bean = Class.forName(beanPath).newInstance();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return bean;
//    }
}
