# Shiro

Shiro，是Apache公司推出的简单的Java安全框架，可以做到不依赖任何容器运行在JavaSE和JavaEE项目中。它最主要的作用就是对访问系统的用户进行身份的认证、授权、会话管理、加密等操作

简而言之，就是用于对访问系统的人，判断其是否处于登录状态，是否有访问某个模块的权限等。还能便捷得进行登录过程的加密，对整个会话中的登录状态等存储

可以简化项目中登录、权限控制等模块的管理

## 简介

Shiro是一个强大的简单易用的Java安全框架，主要用来更便捷的认证，授权，加密，会话管理。Shiro首要的和最重要的目标就是容易使用并且容易理解。

Shiro是一个有许多特性的全面的安全框架，下面这幅图可以了解Shiro的特性：

![Shiro-结构图](img/Shiro-%E7%BB%93%E6%9E%84%E5%9B%BE.png)

可以看出shiro除了基本的认证，授权，会话管理，加密之外，还有许多额外的特性

通过下图，可以理解到Shiro的三大核心概念，Subject、SecurityManger和Realm之间的访问逻辑，直观的感受到它们的交互

![Shiro-架构图](img/Shiro-%E6%9E%B6%E6%9E%84%E5%9B%BE.png)

Subject：翻译为主角，当前参与应用安全部分的主角。可以是用户，可以试第三方服务，可以是cron 任务，或者任何东西。主要指一个正在与当前软件交互的东西。
 所有Subject都需要SecurityManager，当你与Subject进行交互，这些交互行为实际上被转换为与SecurityManager的交互

SecurityManager：安全管理员，Shiro架构的核心，它就像Shiro内部所有原件的保护伞。然而一旦配置了SecurityManager，SecurityManager就用到的比较少，开发者大部分时间都花在Subject上面。
 请记得，当你与Subject进行交互的时候，实际上是SecurityManager在背后帮你举起Subject来做一些安全操作。

Realms：Realms作为Shiro和你的应用的连接桥，当需要与安全数据交互的时候，像用户账户，或者访问控制，Shiro就从一个或多个Realms中查找。
 Shiro提供了一些可以直接使用的Realms，如果默认的Realms不能满足你的需求，你也可以定制自己的Realms

![Shiro-架构图-plus](img/Shiro-%E6%9E%B6%E6%9E%84%E5%9B%BE-plus.png)

> 作者：Real_man
> 链接：https://www.jianshu.com/p/5a35d0100a71
> 来源：简书
> 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

## 核心组件

Shiro通过其中提供的核心组件，来实现其中的重要功能

- SecurityManager

  Shiro的核心，负责安全认证和授权

- Subject

  Shiro中的一个抽象概念，简单理解，其中主要包含了用户的信息

- Realm

  定义规则，通过接口，实现特定的验证和授权逻辑

- ShiroFilterFactoryBean

  过滤器工厂。Shiro的本质上就是基于自定义的注册规则，借由此工厂来创建并注册的Filter来执行

- UsernamePasswordToken

  Shiro用于封装用户的登录信息，可以使用用户的登录信息，来创建令牌（Token）

  即，可以用于创建用户的合法性，以此来进行放行

- AuthenticationInfo

  ==用户==的角色信息集合，认证时使用

- AuthorizationInfo

  ==角色==的权限信息，授权时使用

### 必要知识

为了更加清晰权限控制的使用，需要了解一定的规范

权限控制中，一般分为三个部分

1. 用户
2. 角色
3. 权限

通过，为角色赋予权限，给用户赋予角色的方式，来**<u>间接</u>**的控制用户权限

### 简单总结

此处以用户登录为例

1. 用户发出请求后，被包装为了Token
2. 将Token中的用户信息交给Subject，为其注入属性
3. 使用SecurityManager来操作具体的Subject
4. SecurityManager通过自定义的AuthenticationInfo和AuthorizationInfo来判断校验结果
5. 执行的流程是通过Realm来进行决定

## 使用步骤

此处的使用是Shiro与Spring Boot的整合

> 虽然Shiro已经提供了直接与Spring Boot的整合包，但是此处将会先使用Shiro与Spring的整合包来进行介绍

1. 导入依赖

   使用的是Spring Initializer来创建项目

   为了方便测试，引入了Web模块，之后导入shiro-spring依赖

2. 配置

   自定义shiro的逻辑，此处主要存在三个部分

   1. 自定义Realm
   
      通过实现AuthorizingRealm接口，实现其中的抽象方法，来实现具体的逻辑
   
      在Realm中，主要需要指定，如何来判断登录过程，以及如何来得到访问者的角色和权限信息
   
      1. 判断登录过程
   
         通过方法传入的参数，获取到用户登陆时输入的用户、密码信息
   
         通过数据库查询用户的登录信息，与用户登录输入的信息进行比较
   
         如果返回为`null`表示没有对应的用户，Shiro会抛出`UnknownAccountException`异常；如果通过用户名，可以查到对应的用户，可以创建一个`SimpleAuthenticationInfo`对象，传入用户（User对象）、密码和Realm名（getName()方法）
   
         在`SimpleAuthenticationInfo`中，会进行密码的校验，如果密码错误，会抛出`IncorrectCredentialsException`异常；如果密码正确，就会将用户信息正式存储到Shiro中，表示认证成功
   
      2. 获取角色和权限信息
   
         通过方法传入的参数，使用其中的`getPrimaryPrincipal()`方法可以获取到当前的用户信息（其实就是刚才`SimpleAuthenticationInfo`中传入的用户对象）
   
         结果可以返回一个`SimpleAuthorizationInfo`对象，其中需要使用`setRole()`方法和`addStringPermissions()`方法，分别用来设置用户的角色和权限
   
      > 具体的使用方法，可以查看下方的Realm实例【[快速跳转](#Realm实例)】
   
   2. 编写配置类
   
      在配置类中，需要总体性的串联整个Shiro（因为使用的与Spring的中间包，都需要手动进行配置）
   
      其中主要必须配置如下几个部分
   
      - 自定义的Realm对象
   
      - SecurityManager用于管理Realm
   
      - ShiroFilterFactoryBean，用于串联整个Shiro框架
   
        其中，需要将SecurityManager加入其中进行管理
   
        还需要设置权限的控制（通过`setFilterChainDefinitionMap()`方法，传入一个Map对象，其中设定对不同请求的拦截）
   
        其中一般还需要设置权限不足的响应界面（一般用于跳转到登录界面）；以及未授权的界面（指的是用户登录之后，因权限不足，而无法进行访问的界面）
   
   3. 编写认证和授权规则
   
      此处具体的配置，就将配置到ShiroFilterFactoryBean中
   
      ***认证过滤器***
   
      在Shiro中，定义了特定的认证过滤器，通过配置其认证的级别，来决定是否对其进行放行
   
      - `anon` 无需认证，游客也可以访问
      - `authc` 必须认证之后才能访问
      - `authcBase` 需要通过HTTPBasic认证之后，才能访问
      - `user` 不一定是通过认证，只要是曾经被Shiro认证就可以访问（RememberMe）
   
      ***授权过滤器***
   
      通过认证之后，对其进行授权操作
   
      - `perms` 必须拥有某个权限才能访问
      - `roles` 必须拥有某个角色才能访问
      - `port` 必须是指定的端口才能访问
      - `rest` 请求必须是基于RESTful风格，POST、PUT、GET、DELETE
      - `ssl` 必须是安全的url请求，使用HTTPS协议
   
      > 为了方便理解，记录一个简单实例【[快速跳转](#认证和授权的使用)】
   
3. 控制器

   其中，与Shiro最重要的就是登录方法

   需要获取Subject对象，创建一个`AuthenticationToken`对象（常使用其实现类`UsernamePasswordToken`），并传入用户名和密码

   之后使用subject的`login()`方法，传入`AuthenticationToken`对象，进行登录认证（其中的具体逻辑就是使用Realm中的`doGetAuthenticationInfo()`方法）

   之后对其他界面的访问，如果是在`ShiroFilterFactoryBean`中配合过的路径，就会进行权限的判断（角色和权限，使用Realm中的`doGetAuthorizationInfo`方法）

### 补充

#### Realm实例

```java
/**
 * 在该类中，具体的指明了如何来实现访问控制
 * 1）登录控制       认证
 * 2）角色/权限控制   授权
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 角色的权限信息，用于授权
     * 如，登录之后，能够进行某些操作
     * @param principalCollection 其中保存了登录的用户信息
     * @return 封装了用户权限信息的对象
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前登录的用户信息
//        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
//        System.out.println(primaryPrincipal.getClass());
        User user = (User) principalCollection.getPrimaryPrincipal();

        // 设置角色
        Set<String> roles = new HashSet<>();
        roles.add(user.getRole().getRoleName());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        // 设置权限
        info.addStringPermissions(user.getRole().getPermissions());

        return info;
    }

    /**
     * 用户的角色信息，用于认证
     * 如，登录认证，将通过此类进行判断
     * @param authenticationToken 用户信息
     * @return 封装了用户的角色信息的对象
     * @throws AuthenticationException 用户角色封装异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 用户登陆时传入的用户名
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.selectByUsername(username);
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(
                // 传入用户，用于用户名
                user,
                // 传入密码，用于验证是否正确
                user.getPassword(),
                // 传入RealmName
                getName());
    }
}
```

#### Shiro配置实例

```java
/**
 * Shiro的配置类
 */
@Configuration
public class ShiroConfiguration {

    /**
     * 其中配置了自定义的账户检测
     * @return 自定义Realm对象
     */
    @Bean
    UserRealm userRealm(){
        return new UserRealm();
    }

    /**
     * 主要的作用是将Realm纳入其中管理
     * @param realm 自动注入自定义的Realm对象
     * @return 自定义的SecurityManager
     */
    @Bean
    SecurityManager securityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    /**
     * ShiroFilterFactoryBean的核心组件
     * 1）将SecurityManager纳入管理
     * 2）配置对不同响应所需的权限
     * 一般情况下，还需要设置未认证时的跳转界面以及未授权的界面
     * ShiroFilterFactoryBean如其名称，是特定的配置，能够自动生成处理特定规则的Filter，对不同的请求进行拦截
     * @param securityManager Spring自动注入容器中的SecurityManager
     * @return 返回自定义的ShiroFilterBean
     */
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置权限
        Map<String, String> map = new HashMap<>();
        map.put("/index", "anon");
        map.put("/main", "authc");
        map.put("/manage", "perms[manage]");
        map.put("/administration", "roles[admin]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        // 设置未认证的跳转界面（登录界面）
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 未授权的界面（登录之后，用户的权限不足而不能进行访问的界面）
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorizedUrl");

        return shiroFilterFactoryBean;
    }
}
```

#### 认证和授权的使用

首先，简单介绍实例中的需求

> 存在三个界面，main.html、manage.html、administration.html
>
> 同时，设置了访问的权限
>
> 1. 必须<u>登录</u>才能访问main.html
> 2. 当前用户必须拥有manage的<u>权限</u>才能访问manage.html
> 3. 当前用户必须拥有admin的<u>角色</u>才能访问administration.html

在ShiroFilterFactoryBean中，对权限的配置，通过的是一个`Map<String, String>`来接收

通过需求，可以得到如下的Map对象

```java
Map<String, String> map = new HashMap<>();
map.put("/main", "authc");
map.put("/manage", "perms[manage]");
map.put("/administration", "role[admin]");
shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
```

通过上方的方法，将Map对象设置到ShiroFilterFactoryBean中，并加入Spring容器，就完成了对权限的基本配置，之后的及具体判断方法，还需要在Realm中进行详细配置

## 拓展

### Shiro整合Thymeleaf

在Thymeleaf的基础上，可以更加便利的实现对用户的对应的角色和权限，是否能够访问而显示标签体中的内容。效果类似Thymeleaf中的`th:if`属性，只要返回为`true`，就会显示标签体中的内容；反之，如果返回false，就不会显示标签体中的内容

#### 使用步骤

1. 导入依赖

   首先需要导入Shiro和Thymeleaf的中间包

   ```xml
   <!--thymeleaf shiro-->
   <dependency>
       <groupId>com.github.theborakompanioni</groupId>
       <artifactId>thymeleaf-extras-shiro</artifactId>
       <version>2.0.0</version>
   </dependency>
   ```

2. 配置

   在配置类中，配置ShiroDialect对象

   只有配置了Shiro方言对象之后，才能在页面中使用对应的标签属性

   ```java
   @Bean
   ShiroDialect shiroDialect(){
       return new ShiroDialect();
   }
   ```

3. 使用

   与Thymeleaf类似，首先导入命名空间

   ```xml
   <html lang="en"
         xmlns:th="http://www.thymeleaf.org"
         xmlns:shiro="http://www.pollix.at/thymeleaf/shiro">
   ```

   分别配置了Thymeleaf和Thymeleaf-extras-shiro的命名空间

   其中最常用的是如下方法

   - `shiro:hashasPermission` 有无对应的权限

   - `shiro:hasRole` 有无对应的角色

   - `shiro:hasAllPermissions` 有无对应的权限，接收数组

   - `shiro:hasRole` 有无对应角色，接收数组

     ------

     *这部分方法不需要接收值*

   - `shiro:authenticated` 是否已授权（登录）

   - `shiro:unauthenticated` 是否还未授权（登录）

   - `shiro:user` 是否已授权（包括RememberMe的用户）

   - `shiro:guest` 是否还未授权（包括RememberMe的判断）

