package com.tms.test.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/28
 * Description: 配置类，作用和bean配置文件相同
 * Version: V1.0
 */
// @Configurable    用于指定当前类是一个配置类
//          当这个类作为AnnotationConfigApplicationContext对象创建的参数时，这个注解就可以省略（但不绝对）
//          特殊情况：当配置类没有直接作为对象传入，而是使用了扫描的方式读取，此时就无法省略
//             原理：Spring扫描中，读取文件必须实在读取到了@Configurable标签的情况下才会对内容进行读取，因此必须使用标签
//          小细节：AnnotationConfigApplicationContext创建时可以一次传入多个类，使用逗号隔开即可（不是使用大括号）
// @ComponentScan   通过注解的方式指定Spring创建容器时扫描的包（其中value和basePackages效果相同）
//      等同于配置文件中的   <context:component-scan base-package="com.tms.test"/>
// @Bean        用于把当前方法的返回值作为bean对象，存入Spring的容器中
//      name属性：用于指定bean的id（默认值为当前方法的名称），存入Spring的IoC容器中
//      Spring在检查到方法带参数时，会现在容器中查找是否有bean对象匹配（唯一匹配），查找成功就可以直接注入
// @Import(类名.class)    value属性（用于指定其他配置类的字节码）
//      用于导入其他的配置类。使用import的配置后，就会形成父子关系（使用import就是父类）
// @PropertySource()    value属性（指定文件的名称和路径） 其中，classpath，表示类路径
//      用于指定properties文件的文件的位置

@Configurable
@PropertySource("classpath:jdbcConfig.properties")
@ComponentScan(basePackages = {"com.tms.test"})  // 必须是类路径（classpath）
public class SpringExampleConfig {

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    // 用于创建一个QueryRunner对象
    @Bean(name = "runner")
    @Scope(value = "prototype")
    public QueryRunner createQueryRunner(DataSource dataSource){
        return new QueryRunner(dataSource);
    }

    // 创建数据源对象
    @Bean(name = "dataSource")
    public DataSource createDataSource(){
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
//            ds.setDriverClass("com.mysql.jdbc.Driver");
//            ds.setJdbcUrl("jdbc:mysql://localhost:3306/spring_test");
//            ds.setUser("root");
//            ds.setPassword("root");
            return ds;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
