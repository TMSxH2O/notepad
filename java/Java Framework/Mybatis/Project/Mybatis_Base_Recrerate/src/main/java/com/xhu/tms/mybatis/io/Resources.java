package com.xhu.tms.mybatis.io;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/19
 * Description: 自行搭建了Resources类，实现Mybatis中的对应类的方法
 *              使用类加载器读取配置文件的类
 * Version: V1.0
 */
public class Resources {
    /**
     * @Author TMS_H2O
     * @Description 根据传入的参数，获取一个字节输入流
     * @Date 17:05 2020/4/19
     * @Param [filePath]
     * @return java.io.InputStream
     **/
    public static InputStream getResourceAsStream(String filePath){
        /*
         * 1.拿到当前类的字节码
         * 2.获取字节码的类加载器
         * 3.根据类加载器获取配置
         **/
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
