# SSM整合

## SpringMVC与Spring整合

理论上，SpringMVC与Spring本是同源，其本就是互相依赖的，甚至SpringMVC很大部分就依赖的是Spring中的内容

但是对于大型项目时（SSM很多时候用于处理较大型的项目），其如果需要配置的内容过多，会导致很多问题，此时就应该考虑将两者的xml配置文件分为两部分，之后通过整合的方式整合到一起

整合的方式不唯一

### 整合方式一

简单整合，将其分为两个文件，因为SpringMVC必须直接被加载（因为`web.xml`中，`DispatcherServlet`需要加载配置文件），所以是在SpringMVC的配置文件中，整合Spring的配置文件

#### 过程

1. 导入配置文件

   Spring相关

   - Spring core

     `spring-beans` `spring-core` `spring-context` `spring-expression` `commons-logging`

     为了实现注解的配置，需要额外加上`spring-aop`

   - Spring aop

     基本的AOP包 `spring-aspect`

     增强的AOP包 ，即使目标对象没有实现任何的接口也可以实现动态代理`com.springsource.net.sf.cglib` `com.springsource.org.aopalliance` `com.springsource.org.aspectj.weaver`

   SpringMVC是Spring的web模块，Spring的模块运行必须依赖其核心模块IOC容器

   - Spring web

     `spring-web` `spring-webmvc`

2. 配置`web.xml`文件

   ```xml
   <!-- web.xml -->
   <!-- 配置DispatcherServlet -->
   <servlet>
       <servlet-name>springDispatcherServlet</servlet-name>
       <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
       <init-param>
           <param-name>contextConfigLocation</param-name>
           <!-- 指定SpringMVC的配置文件 -->
           <param-value>classpath:mvcConfig.xml</param-value>
       </init-param>
       <load-on-startup>1</load-on-startup>
   </servlet>
   <!-- 配置其映射的地址 -->
   <servlet-mapping>
       <servlet-name>springDispatcherServlet</servlet-name>
       <url-pattern>/</url-pattern>
   </servlet-mapping>
   ```

3. 配置SpringMVC的xml配置文件

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
   
       <!--注解扫描-->
       <context:component-scan base-package="com.tms.test"/>
   
       <!--配置视图解析器-->
       <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
           <property name="prefix" value="/WEB-INF/pages/"/>
           <property name="suffix" value=".jsp"/>
       </bean>
   
       <!-- 直接将spring的配置文件简单整合进来 -->
       <import resource="spring.xml"/>
   </beans>
   ```

#### 总结

这种方式虽然实现了整合的功能，但是并没有实际的解决文件，两个文件加载到了一起，导致的是最终相当于只加载了一次，与将两者的配置信息写在一起没有本质的区别

而且这样配置的下场，<u>两者将会共用一个容器</u>，如果其中某一边出现了问题就会让全部都直接出现异常

**不推荐使用这种简单的整合方式**

### 整合方式二

通过的是Spring提供的监听器，在服务器启动时，创建一个IOC容器，让其在此时直接去读取Spring的配置文件

#### 步骤

此处的配置方式基本上与方式一中类似，主要需要修改的是在`web.xml`的配置中

```xml
<!-- 
在web.xml的上方添加此处的配置
利用ContextLoaderListener监听器来创建Spring的相关环境
-->
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring.xml</param-value>
</context-param>

<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

此处，存在一个问题，如果Spring和SpringMVC都开启了包扫描，因为两者的容器已经实现了分离，会导致对其中的对象都出现了多次注册

因此，需要让其中扫描的部分不重合

```xml
<!-- springmvc -->
<context:component-scan base-package="com.tms.test" use-default-filters="false">
    <!-- 首先，需要设置use-default-filters属性为false，才能设置只扫描的内容-->
    <!-- 只识别被标记了@Controller注解的类 -->
    <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>

<!-- spring -->
<context:component-scan base-package="com.tms.test">
    <!-- 排除@Controller注解的类 -->
    <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```

#### 总结

通过此处的配置，成功的实现了真正意义上的两个框架的分离，分别加载了Spring与SpringMVC的配置

#### 补充知识

在此处，SpringMVC和Spring之间，存在父子容器的关系

在Spring中，存在默认情况，如果出现了多个容器（Spring旗下，目前Java领域只有Spring），Spring的IOC容器默认为父容器，其包含了其他容器，因此Spring的容器包含了SpringMVC的容器

<u>父子容器之间，父容器不能去调用子容器的内容，但子容器可以使用父容器的内容</u>

因此，SpringMVC可以注入Spring容器中的内容，但是反向就会出现异常

## SSM三框架整合

此处的整合基本上基于Spring与SpringMVC的整合之上的

MyBatis与Spring部分的整合存在以后的整合包，主要依靠其进行整合

### 整合步骤

1. 导包

   - Spring

     核心包

     `spring-beans` `spring-core` `spring-context` `spring-expression` `commons-logging`

     为了实现注解的配置，需要额外加上`spring-aop`

     AOP相关

     基本的AOP包 `spring-aspect`

     增强的AOP包 ，即使目标对象没有实现任何的接口也可以实现动态代理`com.springsource.net.sf.cglib` `com.springsource.org.aopalliance` `com.springsource.org.aspectj.weaver`

     事务控制

     `spring-tx`

     测试相关

     `spring-test`

   - SpringMVC

     `spring-web` `spring-webmvc`

     文件上传

     `commons-fileupload` `commons-io`

     jsr-303校验

     `hibernate-validator` `hibernate-validator-annotation`

     `classmate` `jboss-logging` `validation-api`

     json数据处理(ajax处理)

     `jackson-core` `jackson-databind` `jackson-annotation`

   - MyBatis

     核心包

     `mybatis`

     mysql连接

     `mysql-connector-java`

   - 其他

     日志

     `log4j`

     数据源

     `c3p0`

     Spring-MyBatis整合包

     `mybatis-spring`

2. 配置

   配置需要区分不同的框架，进行配置

   - web整体配置

     ```xml
     <!-- web.xml -->
     <!-- 在web.xml配置文件中，设置监听器，让项目刚启动就创建Spring的
     容器 -->
     <context-param>
         <param-name>contextConfigLocation</param-name>
         <param-value>classpath:spring.xml</param-value>
     </context-param>
     
     <listener>
         <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
     </listener>
     <!-- 配置SpringMVC的前端控制器 -->
     <servlet>
         <servlet-name>springDispatcherServlet</servlet-name>
         <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
         <init-param>
             <param-name>contextConfigLocation</param-name>
             <!-- 指定SpringMVC的配置文件 -->
             <param-value>classpath:ApplicationContext-MVC.xml</param-value>
         </init-param>
         <load-on-startup>1</load-on-startup>
     </servlet>
     <!-- 配置其映射的地址 -->
     <servlet-mapping>
         <servlet-name>springDispatcherServlet</servlet-name>
         <url-pattern>/</url-pattern>
     </servlet-mapping>
     
     <!-- 字符编码的拦截器 -->
     <filter>
         <filter-name>characterEncodingFilter</filter-name>
         <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
         <!--设置默认参数，将编码修改为utf-8-->
         <init-param>
             <param-name>encoding</param-name>
             <param-value>UTF-8</param-value>
         </init-param>
         <!--让拦截器可以同时处理响应中的乱码-->
         <init-param>
             <param-name>forceResponseEncoding</param-name>
             <param-value>true</param-value>
         </init-param>
         <!--默认情况下，不需要指定其作用在请求上，如果请求没有设置过编码，就可以默认执行，如果之前设置过，就不会默认执行，需要此处设置为true-->
         <init-param>
             <param-name>forceRequestEncoding</param-name>
             <param-value>true</param-value>
         </init-param>
     </filter>
     <!-- 字符转换的拦截器最好放在最前，防止其他拦截器中对输入流进行了操作导致编码转换失效 -->
     <filter-mapping>
         <filter-name>characterEncodingFilter</filter-name>
         <url-pattern>/*</url-pattern>
     </filter-mapping>
     
     <!-- 隐藏方法转换拦截器 -->
     <!-- 主要用于实现REST风格的过滤器 -->
     <filter>
         <filter-name>hiddenHttpMethodFilter</filter-name>
         <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
     </filter>
     <filter-mapping>
         <filter-name>hiddenHttpMethodFilter</filter-name>
         <url-pattern>/*</url-pattern>
     </filter-mapping>
     ```

   - Spring的配置

     ```xml
     <!-- ApplicationContext.xml -->
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
            xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">
     
         <!--导入外部配置文件-->
         <context:property-placeholder location="jdbcConfig.properties"/>
     
         <!--Spring的扫描-->
         <context:component-scan base-package="com.tms.test">
             <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
         </context:component-scan>
     
         <!--配置数据源-->
         <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
             <property name="driverClass" value="${jdbc.driverClass}"/>
             <property name="jdbcUrl" value="${jdbc.jdbcUrl}"/>
             <property name="user" value="${jdbc.username}"/>
             <property name="password" value="${jdbc.password}"/>
             <property name="maxPoolSize" value="${jdbc.maxPoolSize}"/>
             <property name="minPoolSize" value="${jdbc.minPoolSize}"/>
         </bean>
     
         <!--配置事务控制管理器-->
         <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
             <property name="dataSource" ref="dataSource"/>
         </bean>
     
         <!--配置事务-->
         <aop:config>
             <aop:pointcut id="userServicePointcut" expression="execution(* com.tms.test.service.*.*(..))"/>
             <aop:advisor advice-ref="userServiceAdvice" pointcut-ref="userServicePointcut"/>
         </aop:config>
     
         <!--配置具体的事务建议-->
         <tx:advice id="userServiceAdvice" transaction-manager="transactionManager">
             <!--配置事务的属性-->
             <tx:attributes>
                 <tx:method name="*" rollback-for="java.lang.Exception"/>
                 <tx:method name="find*" read-only="true"/>
             </tx:attributes>
         </tx:advice>
     
         <!--配置与MyBatis整合-->
         <!--可以根据配置文件，得到SqlSessionFactory-->
         <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
             <!--指定MyBatis配置文件的位置-->
             <property name="configLocation" value="classpath:MyBatis-Config.xml"/>
             <property name="dataSource" ref="dataSource"/>
             <!--指定Mapper文件的位置-->
             <property name="mapperLocations" value="classpath:mappers"/>
         </bean>
     
         <!--将每个dao接口的实现加入spring容器中-->
         <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
             <!--指定dao的包-->
             <property name="basePackage" value="com.tms.test"/>
         </bean>
         <!-- 或者可以使用MyBatis的相关标签 -->
         <!-- <mybatis-spring:scan base-package="com.tms.test"/> -->
     
     </beans>
     ```

   - SpringMVC的配置

     ```xml
     <!-- ApplicationContext-MVC.xml -->
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xmlns:mvc="http://www.springframework.org/schema/mvc"
            xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">
     
         <!--注解扫描-->
         <context:component-scan base-package="com.tms.test" use-default-filters="false">
             <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
         </context:component-scan>
     
         <!--配置视图解析器-->
         <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
             <property name="prefix" value="/WEB-INF/pages/"/>
             <property name="suffix" value=".jsp"/>
         </bean>
     
         <!--文件上传解析器-->
         <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
             <property name="defaultEncoding" value="utf-8"/>
             <property name="maxUploadSize" value="#{1024*1024*20}"/>
         </bean>
     
         <!--对静态资源放行-->
         <mvc:default-servlet-handler/>
     
         <!--SpringMVC增强-->
         <mvc:annotation-driven/>
     </beans>
     ```

   - MyBatis的配置

     MyBatis整合的关键，就是使用整合包，将其内容加入Spring管理

     ```xml
     <!-- MyBatis-Config.xml -->
     <?xml version="1.0" encoding="UTF-8" ?>
     <!DOCTYPE configuration
             PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
             "http://mybatis.org/dtd/mybatis-3-config.dtd">
     
     <configuration>
         <settings>
             <setting name="lazyLoadingEnabled" value="true"/>
             <setting name="aggressiveLazyLoading" value="true"/>
             <setting name="cacheEnabled" value="true"/>
         </settings>
     </configuration>
     ```

3. 测试



# 整合补充

##### `mybatis-spring`

MyBatis与Spring之间的整合包，需要控制其版本

对于MyBatis与Spring之间的整合，可以查看MyBatis的官方介绍 [快速跳转](http://mybatis.org/spring/zh/index.html)