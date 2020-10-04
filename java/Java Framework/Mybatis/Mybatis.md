# MyBatis

MyBatis是一个支持定制的SQL、存储过程以及高级映射的优秀的持久层框架。其中避免了几乎所有的JDBC代码和手动设置参数以及获取结果集。

MyBatis可以使用简答的xml或注解的方式来配置和原始映射，将接口和Java的POJO（Plain Old Java Object，普通的Java对象）映射成数据库中的记录

> 原生的SQL语句，是直接编辑在代码中，如果SQL语句出现了问题，就需要修改代码，不能对其进行灵活的修改

> ###### 与Hibernate框架的对比
>
> Hibernate也是一个持久层框架，即数据库交互层框架，使用的是ORM思想，即Object Relation Mapping，对象关系映射思想
>
> 其中只需要编写实例对象，在对象上添加注解，就会自动的将其转换并存放到数据库中
>
> **缺陷**
>
> 其中使用了黑箱的方式，实现了业务的各种逻辑，其中的SQL语句使用的是半封闭的方式，实现自定义SQL十分困难
>
> Hibernate是全映射框架，做部分字段映射很困难且繁琐

## 简介

MyBatis将重要的步骤抽取出来，可以进行人工配置，其他步骤实现了自动化。同时，其中的具体配置都可以使用配置文件，可以实现解耦

完全解决了数据库的优化问题，而MyBatis的底层就是对原生JDBC的一个简单封装，轻量级框架

MyBatis既可以将Java代码与SQL语句分离开来，还不会失去自动化功能，是一个半自动的持久层框架

### 历史

原是Apache的一个开源项目`iBatis`, 2010年6月这个项目由`Apache Software Foundation` 迁移到了`Google Code`，随着开发团队转投Google Code旗下， iBatis3.x正式更名为MyBatis ，代码于2013年11月迁移到Github（下载地址见后）。

iBatis一词来源于“internet”和“abatis”的组合，是一个基于Java的持久层框架。 iBatis提供的持久层框架包括SQL Maps和Data Access Objects（DAO）

## 具体使用

此处将包含MyBatis的各种使用方式，包括最基本的环境搭建以及之后各种细节功能的拓展

### 基础步骤

1. 导包

   导入Mybaits相关的jar包，在MyBatis 3.4之后的版本，其依赖的各种jar包不需要再额外导入，其内部都包括其各个包的数据

   `mysql-connector-java` `MyBatis`

   建议导入日志包，可以在MyBatis的关键步骤进行日志打印

    `log4j`

   如果导入了日志文件，需要在依赖类路径下编写一个`log4j.xml`配置文件

2. 写配置

   Mybati中，需要多个配置文件，其中分别存放对MyBatis的主配置信息，以及对持久层中的具体方法执行逻辑

   - 主配置文件

     ```xml
     <?xml version="1.0" encoding="UTF-8" ?>
     <!DOCTYPE configuration
             PUBLIC "-//MyBatis.org//DTD Config 3.0//EN"
             "http://MyBatis.org/dtd/MyBatis-3-config.dtd">
     
     <configuration>
         <environments default="development">
             <environment id="development">
                 <transactionManager type="JDBC"/>
                 <dataSource type="POOLED">
                     <!-- 此处都是使用的引用外部文件的方式-->
                     <property name="driver" value="${database.driver}"/>
                     <property name="url" value="${database.url}"/>
                     <property name="username" value="${database.username}"/>
                     <property name="password" value="${database.password}"/>
                 </dataSource>
             </environment>
         </environments>
     
     </configuration>
     ```

   - 具体方法配置文件

     通过编写配置文件，指定接口中方法的具体执行方法，其效果相当于编写了实现类

     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <!DOCTYPE mapper
         PUBLIC "-//MyBatis.org//DTD Mapper 3.0//EN"
         "http://MyBatis.org/dtd/MyBatis-3-mapper.dtd">
     <!-- 设定名称空间，指定其作用的具体接口 -->
     <mapper namespace="${PACKAGE_NAME}.${NAME}">
         <!-- 设置对象映射 -->
     	<resultMap type="org.apache.ibatis.submitted.rounding.User" id="usermap">
     		<id column="id" property="id"/>
     		<result column="name" property="name"/>
     		<result column="funkyNumber" property="funkyNumber"/>
     		<result column="roundingMode" property="roundingMode"/>
     	</resultMap>
     	<!-- 查询方法 -->
         <!-- 其中id指定的是接口中的具体方法，resultMap指定的是返回类型-->
     	<select id="getUser" resultMap="usermap">
     		select * from users
     	</select>
         <!-- 插入方法-->
     	<insert id="insert">
     	    insert into users (id, name, funkyNumber, roundingMode) values (
     	    	#{id}, #{name}, #{funkyNumber}, #{roundingMode}
     	    )
     	</insert>
     
     	<resultMap type="org.apache.ibatis.submitted.rounding.User" id="usermap2">
     		<id column="id" property="id"/>
     		<result column="name" property="name"/>
     		<result column="funkyNumber" property="funkyNumber"/>
     		<result column="roundingMode" property="roundingMode" typeHandler="org.apache.ibatis.type.EnumTypeHandler"/>
     	</resultMap>
     	<select id="getUser2" resultMap="usermap2">
     		select * from users2
     	</select>
     	<insert id="insert2">
     	    insert into users2 (id, name, funkyNumber, roundingMode) values (
     	    	#{id}, #{name}, #{funkyNumber}, #{roundingMode, typeHandler=org.apache.ibatis.type.EnumTypeHandler}
     	    )
     	</insert>
     
     </mapper>
     ```

   - 注册方法配置文件

     单纯的编写了方法的配置文件，其并没有真正实现功能，还需要将其纳入MyBatis进行管理

     ```xml
     <!-- 在MyBatis的全局配置文件中进行配置 -->
     <mappers>
         <mapper resource="UserMapping.xml"/>
     </mappers>
     ```

3. 具体使用

   1. 根据全局配置文件，创建出`SqlSessionFactory`对象

      ```java
      String config = "MyBatis-config.xml";
      InputStream resource = Resources.getResourceAsStream(config);
      SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resource);
      ```

   2. 使用`SqlSessionFactory`获取`SqlSession`对象

      `SqlSession`，即与数据库的一次会话

      ```java
      SqlSession sqlSession = factory.openSession();
      ```

      其原理与获取连接类似，即原生jdbc中的`getConnection()`方法

   3. 使用`SqlSession`操作数据库

      使用其中的方法，来获取到持久层接口的实现

      ```java
      UserDao userDao = sqlSession.getMapper(UserDao.class);
      User user = userDao.getUserById(41);
      System.out.println(user);
      ```

### 细节知识

1. 通过`SqlSessionFactory`工厂获取到的`SqlSession`对象是通过动态代理获取到的代理对象

2. `SqlSessionFactory`和`SqlSession`的区别

   `SqlSessionFactory`是一个工厂类，主要的作用就是来获取`SqlSession`对象，并不需要多个工厂对象

   `SqlSession`对象相当于一次与数据库的会话，因此，每一次会话都应该创建一个新的对象
   
3. 在SQL配置文件中，方法的参数调用默认为`#{参数名}`，如果遇到了不确定参数名或者需要使用另设参数名的时候，可以在对应的参数前添加注解`@Param`，其中的`value`属性就可以指定自定义的参数名

4. 在很多的动态SQL语句中，存在`test`属性，用于指定判定条件，其中变量名是直接使用参数的变量名，而其中的布尔逻辑判断（与或非）最好使用数据库中的`and`、`or`或`not`，否则需要使用转义之后的码

   `test`属性中，除了传入的参数，还存在两个隐藏的变量

   - `_parameter`

     代表了传入的参数，其中根据传入的参数不同，其代表的是传入的所有参数

     - 传入一个参数：`_parameter`直接代表所传入的参数
     - 传入多个参数：`_parameter`代表的是封装传入参数的`Map`对象

   - `_databaseId`

### 补充知识

#### 事务提交

在默认情况下，MyBatis中的事务都是非自动提交，其中的各项事务需要手动进行提交

- 手动提交

  默认情况下，都是手动提交，其中的所有数据更新有关的操作，都需要最终使用手动提交

  在获取的`SqlSession`中，存在方法`commit()`，可以实现事务的提交

- 自动提交

  在`SqlSessionFactory`创建`SqlSession`对象时，使用的`openSession()`方法，其中可以传入一个`boolean`类型的参数，默认情况为`false`非自动提交，设定`true`自动提交

#### 封装集合

在MyBatis中，查询的多条数据需要将其封装到集合中进行存放，其中`List`和`Map`的封装方式略有不同

- `List`

  如果结果封装`List`集合，`resultType`属性只需要指定元素的类型即可，会将对象中的数据按照指定的类型封装成一个`List`集合

- `Map`

  `Map`集合中分两种情况，如果结果唯一和结果不唯一

  - 结果唯一

    其中`resultType`属性可以直接设置为`map`（小写，内置的映射简写）

    会选择第一个符合指定`key`类型的字段作为`key`值，其他部分作为`value`值

  - 结果不唯一

    结果集中得到了多个对象，就需要手动指定`key`的值。其中，可以直接设置`resultType`为返回集合中元素的类型

    在`dao`包下接口的抽象方法上，<u>添加注解`@MapKey`指定封装时，将哪个字段作为`key`进行封装</u>

#### 自定义返回映射

对于某些表，其中的字段名就算开启了驼峰命名法依然无法实现映射，此时必须使用映射，加大了其中的复杂程度

这个问题就可以使用`<resultMap>`标签来解决

- `type` 指定结果类的全限定类名
- `id` 指定之后引用该映射时使用的名称

其中，使用如下内部标签

- `<id>` 用于标注哪一个字段是主键

- `<result>` 用于标注普通列的配置

  其中的属性和配置都类似

  包括如下属性：

  - `column` 指定对应的数据库字段名
  - `property` 结果类中的属性名

- `<association>` 多表联合

  拥有类中的某个自定义类型的属性封装时，**推荐使用**

  > `<result>`标签也可以实现自定义类型属性的封装，只需要指定`property`属性时，使用`属性.内部属性`的方式，如`User`类中存在一个`Account`类的属性，可以指定`property=account.id`这样的方式进行映射

  - `property` 指定对应的属性名

  - `javaType` 指定属性的类型

  - `select` 用于指定分步查询方法，将会调用指定的SQL，并将结果封装到该属性中。如，已经有一个`select`标签实现了所需的功能，可以直接设定属性值为全限定类名加上方法对应的id值，如`com.tms.test.dao.AccountDao.findById`

    <u>如果对应的方法需要传入某个参数，需要使用`column`属性，指定需要传入的参数名</u>

    **这种方式会降低性能**

    **可以使用懒加载的方式，来提升性能**

    在MyBatis全局配置文件中，使用`<setting>`标签，将`lazyLoadingEnabled`设置为`true`，开启全局懒加载模式；同时，还需要将`aggressiveLazyEnabled`设置为`false`

    > 如果在开启了全局懒加载模式，可以在`<association>`标签中，使用`fetchType`属性，设置为`eager`（其取值为`eager`和`lazy`）

    `<collection>`属性中也有类似的使用，可以使用分布查询

    <u>**推荐复杂查询依然使用连接查询，而简单查询分离成另一个简单方法，以此来提高性能**</u>

  其内部的使用与`<resultMap>`的内部类似，可以使用`<id>`和`<property>`等标签，分别指定该自定义类型的属性的封装方法

- `<collection>` 定义集合属性

  在类中存在某个属性为集合时，使用该标签进行封装

  - `property`
  - `ofType` 指定集合中元素的类型

  在标签体中，指定元素具体的规则，与`<association>`标签类似

### 配置文件

在MyBatis中，主要存在两个重要的配置文件，即全局配置文件和SQL映射文件

其中，全局配置文件主要用于指导MyBatis正确运行的一些配置；而SQL映射文件相当于`Dao`包中的接口实现

#### 全局配置文件

其配置文件将包含了会影响到MyBatis行为的设置和属性信息，其具体的结构如下（详细信息可以见官方文档）：

configuration（配置）

- [properties（属性）](https://MyBatis.org/MyBatis-3/zh/configuration.html#properties)

  主要用于引用外部的配置文件。为了防止具体的主配置文件中的配置信息被固定，可以使用外部文件的方式，减少了耦合

  使用`<properties>`标签，其中可以指定某个外部的配置文件，来获取其中的参数值

  - `resource` 引用类路径下的文件，其根目录是类路径
  - `url` 引用磁盘路径或网络路径的资源

  之后，可以使用`${ 参数名 }`来引用某个参数值

- [settings（设置）](https://MyBatis.org/MyBatis-3/zh/configuration.html#settings)

  用于调整MyBatis中的某些重要配置信息，会改变MyBatis的运行时行为，其中的具体配置信息见官方文档

  - `mapUnderscoreToCamelCase` `true|false[默认]`

    是否开启驼峰命名自动映射，即从经典数据库列名 `user_address` 映射到经典 Java 属性名 `userAddress`

  使用`<settings>`标签，在其中使用`<setting>`标签，来设置具体的某个配置项。在`<setting>`标签中，存在属性`name`和`value`，分别用于指定某个配置项的名称以及配置项的值

- [typeAliases（类型别名）](https://MyBatis.org/MyBatis-3/zh/configuration.html#typeAliases)

  可以为常用的Java类设置别名，简化代码

  使用`<typeAliases>`标签，定义一个别名配置空间

  - `<typeAlias>`标签

    在`<typeAliases>`，可以使用`<typeAlias>`标签。而在这个标签中，可以设定其属性`type`，指定某个具体的全限定类名，**默认情况下，其别名就是类名**，不需要带包名

    也可以使用`alias`属性，指定某个具体的别名，来代表某个类

  - `<package>`标签

    `<typeAliases>`的内部标签，用于快速指定某个包下的别名

    **默认同样为别名**

    如果希望在其中的某个类上取别名，可以在类上添加`@Alias`注解，在注解中使用`value`属性指定其别名

  <u>推荐，少使用别名</u>。使用别名会让代码的可读性下降，无法直观的了解其对应的某个类

  <u>MyBatis中存在内置的别名，不应该占用其内置的别名（或关键字），具体内置别名见官方文档</u>

- [typeHandlers（类型处理器）](https://MyBatis.org/MyBatis-3/zh/configuration.html#typeHandlers)

  类型处理器，主要用于格式的转换，如果配置了自定义的类型处理器，需要在此处进行配置

- [objectFactory（对象工厂）](https://MyBatis.org/MyBatis-3/zh/configuration.html#objectFactory)

- [plugins（插件）](https://MyBatis.org/MyBatis-3/zh/configuration.html#plugins)

  MyBatis中的一个重要功能，通过插件的方式，来修改MyBatis的核心行为

  插件是通过动态代理的机制，可以介入MyBatis的四大组件中的任意一个方法进行执行

- environments（环境配置）

  环境配置区域，可以配置多个环境

  - environment（环境变量）

    - transactionManager（事务管理器）
    - dataSource（数据源）

    之后的数据源和事务管理器都是交给Spring中进行具体的配置

- [databaseIdProvider（数据库厂商标识）](https://MyBatis.org/MyBatis-3/zh/configuration.html#databaseIdProvider)

  用于交换不同数据库时，处理不同数据库之间的兼容问题

  `<databaseIdProvider>`标签，其中存在属性`type=DB_VENDOR`，只有**唯一值**

  在其内部，使用`<property>`内部标签，其中存在属性`name`用于指明数据库标识、`value`用于为其自定义名称

- [mappers（映射器）](https://MyBatis.org/MyBatis-3/zh/configuration.html#mappers)

  用于指定`dao`包下接口的映射文件，只有在此处进行注册之后，才能纳入使用

  - 其中使用`<mapper>`内部标签，来指定SQL映射文件，该标签存在三种不同的配置方式

    - `resource` 用于指定类路径下的某个具体的映射文件

    - `url` 用于指定系统或网络路径的某个映射文件

    - `class` 用于直接应用接口的全类名，来指定映射文件，此时，映射文件必须和所映射的接口文件必须在同目录下，且名称必须相同

      此处，还可以用于指定使用注解方式的文件（没有对应的映射文件，所有的SQL语句都直接使用注解的方式进行了配置）

  - 其中还可以使用`<package>`标签，实现映射文件的批量注册。该标签中存在属性`name`用于标注`dao`包所有在的包名

#### SQL映射文件

MyBatis中通过配置文件的方式实现`dao`包中各种接口的方法

其中主要包括了如下的标签

- `cache` – 该命名空间的缓存配置

  - `eviction` 

    指定缓存回收策略，根据不同的策略进行缓存的回收

    - `LRU` 最近最少使用的对象，优先被移除
    - `FIFO` 先进先出，按照对象进入缓存的顺序来进行移除
    - `SOFT` 软引用，移除基于垃圾回收器状态和软引用规则的对象
    - `WEAK` 弱引用，更积极地移除基于垃圾回收器状态和弱引用规则的对象

    默认情况下，使用的是`LRU`

  - `flushInterval` 刷新间隔，其中指定<u>毫秒数</u>

    默认情况，没有刷新间隔，缓存只会在调用语句时进行更新

  - `size` 引用数量，其中指定一个<u>正整数</u>

    代表缓存最多存储多少个对象，<u>如果指定的数量过大会导致内存的溢出</u>

  - `readOnly` 只读，使用一个布尔值`true/false`

    开启只读之后，会给所有的调用者返回缓存对象的相同实例，但这些对象不能进行修改，<u>可以提高性能，但是不安全</u>

    如果只读的状态还是进行了数据修改操作，之后从缓存中获取到的依然是修改之前的值

- `cache-ref` – 引用其它命名空间的缓存配置

  对二级缓存进行配置，如果需要使用外部的非关系数据库作为缓存，也是需要在此处进行配置

- ------

  `resultMap` – 描述如何从数据库结果集中加载对象，是最复杂也是最强大的元素。

- ~~`parameterMap`~~ – 老式风格的参数映射。<u>此元素已被废弃</u>，并可能在将来被移除！请使用行内参数映射。文档中不会介绍此元素。

- `sql` – 可被其它语句引用的可重用语句块。如果之后需要使用某个被抽取出的`sql`语句，使用`<include>`标签，在其中使用`refid`属性指定所引用的`<sql>`标签id

- ------

  `insert` – 映射插入语句。

- `update` – 映射更新语句。

- `delete` – 映射删除语句。

  增删改标签，其中的属性都类似，此处一并进行介绍

  - `id` 命名空间的唯一标识符，指定某个方法，因此MyBaits不支持方法重载

  - ~~`parameterType`~~ 并不需要额外指定，MyBatis可以自己通过推断的方式来获取参数的类型

  - `flushCache` 每次调用都会清空本地缓存和二级缓存，默认为`true`会清空，`false`不会清空

  - `timeout` 设置在抛出一场之前，去驱动程序等待数据库返回请求的秒数。默认为`unset`使用依赖注入

  - `statementType` `STATEMENT`，`PREPARED`或`CALLABLE`中的任意一个。让MyBatis分别使用`Statement`，`PreparedStatement`或`CallableStatement`。默认值为`PREPARED`

  - `useGeneratedKeys` 【只能在insert和update使用】命令MyBatis使用JDBC的`getGeneratedKeys`方法来取出数据库内部生成的主键，如MySQL这样的关系数据库管理系统的自增字段，默认为`false`

  - `keyProperty` 【只能在insert和update使用】唯一标记一个属性，MyBatis会通过`getGeneratedKeys`的返回值或者通过insert语句的`selectKey`子元素设置它的键值，默认为unset

    > 通常，`useGeneratedKeys` 和`keyProperty` 一同使用，MyBatis可以获取到其中自增id值，并通过`keyProperty`指定将自增id封装给某个属性

  - `keyColumn` 【只能在insert和update使用】通过生成的键值设置表中的列名，这个设置仅在某些数据库，如`PostgreSQL`是必须的，当逐渐不再是表中第一列的时候需要设置。如果希望得到多个生成的列，也可以是逗号分隔的属性名称列表

  - `databaseId` 如果配置了`databaseIdProvider`，MyBatis会加载所有不带`databaseId`或匹配当前`databaseId`的语句；如果带或者不带的语句都有，则不带的语句会被忽略

- `select` – 映射查询语句

  查询标签，其具体的逻辑与增删改逻辑相差较大，单独介绍

  - `id` 在命名空间的唯一标识符，可以用于指定某个方法

  - `paramterType` 

    设定参数的类型

  - ~~`parameterMap`~~ 

    用于引用外部的`parameterMap`标签中的配置，已废弃

  - `resultType`

    指定返回值类型

  - `resultMap` 

    用于引用外部`resultMap`标签中的配置

  - `flushCache` 

    每次调用都会清空本地缓存和二级缓存。默认为`false`

  - `useCache` 

    将本条语句的结果使用二级缓存进行存储。默认为`true`

  - `timeout` 

    设置该语句没有出现异常的最大正常执行等待时间。默认为`unset`依赖数据库驱动

  - `fetchSize` 

    给出一个驱动建议，尝试让驱动程序每次批量返回的结果行数等于此处设置的值。默认为`unset`依赖驱动

  - `StatementType` 

    与之前增删改中一致

  - `resultSetType` 

    `FORWARD_ONLY`，`SCROLL_SENSITIVE`，`SCROLL_INSERTIVE`或`DEFAULT`（等价于`unset`）中的任意一个，默认为`unset`依赖数据库驱动

  - `databaseId`

    与之前的增删改中一致

  - `resultOrdered`

    这个设置仅针对嵌套结果 select 语句：如果为 true，将会假设包含了嵌套结果集或是分组，当返回一个主结果行时，就不会产生对前面结果集的引用。 这就使得在获取嵌套结果集的时候不至于内存不够用。默认值：`false`

  - `resultSets`

    这个设置仅适用于多结果集的情况。它将列出语句执行后返回的结果集并赋予每个结果集一个名称，多个名称之间以逗号分隔

##### selectKey标签

在`<insert>`标签内部，存在标签`<selectKey>`，可以用于查询某个键，常规用法为获取某些不支持自动生成主键的JDBC驱动，用于获取其自增id值

在`<selectKey>`标签中，存在如下属性

- `keyProperty`

  设置`selectKey`的返回结果值将被设置到的目标属性，如果结果值不唯一，可以使用逗号分隔

- `keyColumn`

  返回结果集中生成列属性的字段名，如果生成列不唯一，可以使用逗号分隔

- `resultType`

  指定结果的类型，通常情况下MyBatis可以自行推断。如果返回的列不唯一，可以设置其为`Object`或`Map`

- `order`

  可以设定`BEFORE`或`AFTER`。会决定`selectKey`中执行在之后的插入语句之前或者之后

- `statmentType`

  和前面一样，MyBatis 支持 `STATEMENT`，`PREPARED` 和 `CALLABLE` 类型的映射语句，分别代表 `Statement`, `PreparedStatement` 和 `CallableStatement` 类型

### 动态SQL语句

动态 SQL 是 MyBatis 的强大特性之一。可以使用更加便捷的方式实现SQL语句的拼接操作，方便得实现所需的逻辑

其中，包括如下的标签

- `if`

  用于进行简单的判断，类似与`if`判断类似

  其中包含一个**必要属性**`test`，设置判断的条件，如果判断的结果为`true`就会执行之后的SQL语句

- `choose` (`when`, `otherwise`)

  `<choose>`标签，其效果类似`switch`语句，使用`<choose>`语句包括，其中使用`<when>`和`<otherwise>`标签来进行判断

  `<when>`标签中，需要使用`test`来指定判定判断条件

- `trim`

  截取字符串，可以对其中的整体进行判断

  - `prefix`

    为标签内部的整体添加一个前缀

  - `prefixOverrides`

    对标签内部的整体进行判断，删除多余的前缀

  - `suffix`

    为标签内部的整体添加一个后缀

  - `suffixOverrides`

    对标签内部的整体进行判断，删除多余的后缀

  基于`<trim>`标签，出现了几个专用于处理某个表达式的标签

  - `where`

    `<where>` 标签，不再需要自行添加SQL语句中的`where`部分，同时能够动态的进行截取，让SQL自然进行连接，不会出错

    在其中可以使用各种判断，动态添加字符串，无需关心是否需要使用`and`的判断（无脑在SQL语句前添加`and`）

    <u>基于`<trim>`标签进行的实现</u>

  - `set`

    可以动态的为SQL语句添加`set`表达式，如`update table_name set param_name=param_value, param_name=param_value`此处的`set`

    可以动态解决其中多余的逗号连接等问题

- `foreach`

  遍历标签，在`<foreach>`标签内，存在很多属性，用于指定循环的条件索引等信息

  - `collection`

    指定需要进行遍历的参数名

  - `separator`

    指定分隔方式，使用什么字符串进行分隔

  - `index`

    指定索引，使用一个变量来存储当前的索引

  - `item`

    当前遍历出的元素，设置变量名，可以在其中使用变量名来获取当前元素

  - `open`和`close`

    对整体添加开头字符串和末尾字符串

##### 动态SQL的拓展

- `bind`

  自定义一个变量，之后可以直接引用

  这个标签在其他标签内部，如`<select>`、`<update>`、`<delect>`、`<insert>`标签的内部使用

  ```xml
  <select id="selectBlogsLike" resultType="Blog">
    <bind name="pattern" value="'%' + _parameter.getTitle() + '%'" />
    SELECT * FROM BLOG
    WHERE title LIKE #{pattern}
  </select>
  ```

- `script`

  要在带注解的映射器接口类中使用动态 SQL，可以使用 *script* 元素。比如:

  ```java
     @Update({"<script>",
        "update Author",
        "  <set>",
        "    <if test='username != null'>username=#{username},</if>",
        "    <if test='password != null'>password=#{password},</if>",
        "    <if test='email != null'>email=#{email},</if>",
        "    <if test='bio != null'>bio=#{bio}</if>",
        "  </set>",
        "where id=#{id}",
        "</script>"})
      void updateAuthorValues(Author author);
  ```

### OGNL表达式

OGNL，Object Graph Navigation Language，对象导航图语言。是一种强大的表达式语言，通过OGNL可以非常方便的来操作对象属性，其类似EL、SpEL等表达式语言

在MyBatis中，很多的`test`属性中就支持使用OGNL来方便操作

其中进行了如下的规定

```java
package com.tms.test;
public class User{
	public String name();
	public String getName(){
		// 方法体
	}
}
```

- 对象属性的访问

  `user.name`

- 调用方法

  `user.getName()`

- 调用静态属性/方法

  `@java.lang.Math@PI`

  `@java.util.UUID@randomUUID()`

- 调用构造方法

  `new com.tms.test.User()`

- 运算符

  `+` `-` `*` `/` `%`

- 逻辑运算符

  `in` `not in` `>` `>=` `<` `<=` `==` `!=`

  **在xml中，某些特殊符号需要使用转义字符代替**

- 访问集合伪属性

  此处的伪属性指的是集合中实际上并不存在的某些属性，是对某些方法的简称，可以更加方便对某些方法进行调用

  | 类型           | 伪属性        | 伪属性对应的  Java  方法                   |
  | -------------- | ------------- | ------------------------------------------ |
  | List、Set、Map | size、isEmpty | List/Set/Map.size(),List/Set/Map.isEmpty() |
  | List、Set      | iterator      | List.iterator()、Set.iterator()            |
  | Map            | keys、values  | Map.keySet()、Map.values()                 |
  | Iterator       | next、hasNext | Iterator.next()、Iterator.hasNext()        |

### 数据库缓存

对于关联性数据库而言，频繁的访问将会对其造成极大的压力，而很多时候，所访问的很多数据并不会频繁发生变化，而这样的数据又经常都要使用，就应该将其缓存到内存中，便于之后的使用。这样既提高了执行的效率，又减轻了数据库的压力

在所有的数据库框架中，都存在缓存的思想，能够临时得存放一些数据

在MyBatis中，根据这样数据的不同需求，将缓存分为了两个级别

#### 一级缓存

用于存放线程级别的缓存，作用的是本地的缓存，是`SqlSession`级别的缓存

在MyBatis中，是SqlSession级别的缓存，是默认开启的。所有查询的数据，都会被存放到缓存中，使用`Map`进行存储

<u>**如果之后进行了数据更新操作，就会清空一级缓存**</u>

**<u>只有同一个SqlSession中，才能使用同一个一级缓存，不同的SqlSession之间存在不同的一级缓存</u>**

在MyBatis中，一级缓存使用的是`Cache`接口的实现类`PerpetualCache`，其中使用的是用`Map`属性对其进行存储

根据源码可知，其中存放的是具体方法、环境、方法参数等信息，因此如果调用的同一个方法，且参数相同，一般情况下，可以从缓存中获取到之前的值

<u>**一级缓存不能关闭，只能清空**</u>

#### 二级缓存

用于存放全局范围的缓存，是名称空间级别的缓存（每一个dao有一个对应的缓存），默认不开启，需要经过配置之后才会开启

在MyBatis中，提供了二级缓存的接口以及其实现，缓存的实现，需要实现`Serializable`接口

**<u>二级缓存在`SqlSession`关闭或提交之后才会更新</u>**

##### 使用步骤

1. 在MyBatis的全局配置文件中，开启二级缓存，使用`<setting>`标签，设置`cacheEnabled`配置项为`true`

   设置开启全局缓存开关，表示可以使用二级缓存，但是还并没有开启二级缓存

2. 在需要使用二级缓存的映射文件处使用`<cache>`标签配置缓存

   该标签中还能使用其他的非关系数据库，需要使用`type`属性进行具体的配置

   如，MyBatis整合redis数据库，需要先引入maven坐标`mybatis-redis`，之后在此处设置为`type="org.mybatis.caches.redis.RedisCache"`

**<u>注意，需要使用二级缓存的`JavaBean`对象，必须实现了`Serialiable`接口</u>**

#### 补充

- 如果出现一级缓存和二级缓存都可以进行处理的情况，优先使用的哪个缓存？

  会**优先查询二级缓存**，如果二级缓存中没有数据，就会查询一级缓存，如果一级缓存中也没有，就会查询数据库，将查询数据库中的数据同时存放到一级缓存中，直到`SqlSession`关闭之后就会将一级缓存中的数据存放到二级缓存中

## 拓展

### MBG

MBG，MyBatis Generator，MyBatis代码生成器

MyBatis官方提供的一个提供的一个代码生成器，经过配置之后，可以自动实现代码的逆向生成，即通过数据库的表来实现Java代码

> [官方文档](http://mybatis.org/generator/)

在maven项目中，需要导入相关的坐标

```xml
<!-- pom.xml -->
<build>
    <plugins>
        <!--MyBatis逆向工程，MBG-->
        <plugin>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-maven-plugin</artifactId>
            <version>1.4.0</version>
            <configuration>
                 <!-- 在此处指定具体的MBG配置文件 -->
                <configurationFile>${basedir}/src/main/resources/generator.xml</configurationFile>
            </configuration>
        </plugin>
    </plugins>
</build>
```

MBG所需的xml配置文件，用于指定生成时所需的相关配置信息

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE generatorConfiguration PUBLIC
        "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd" >
<generatorConfiguration>
    <!-- !!!! Driver Class Path !!!! -->
    <classPathEntry
            location="F:\Resource\maven-lib\mysql\mysql-connector-java\5.1.6\mysql-connector-java-5.1.6.jar"/>

    <!--主要配置标签-->
    <context id="context" targetRuntime="MyBatis3Simple">
        <!-- !!!! Database Configurations !!!! -->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver" connectionURL="jdbc:mysql://47.92.163.46:3306/spring_jdbc?serverTimezone=GMT%2B8"
                        userId="root" password="root"/>

        <!-- pojo生成 -->
        <!-- targetPackage属性指定生成的类存放的包，targetProject指定类路径相对项目整体的路径 -->
        <javaModelGenerator targetPackage="com.tms.test.bean" targetProject="./src/main/java">
            <property name="enableSubPackages" value="false"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>


        <!-- Mapper映射文件配置 -->
        <sqlMapGenerator targetPackage="mappers" targetProject="./src/main/resources">
            <property name="enableSubPackages" value="false"/>
        </sqlMapGenerator>

        <!-- dao包接口配置 -->
        <javaClientGenerator targetPackage="com.tms.test.dao" targetProject="./src/main/java" type="XMLMAPPER">
            <property name="enableSubPackages" value="false"/>
        </javaClientGenerator>

        <!-- 配置数据库中，需要进行转换的表的信息 -->
        <!-- tableName属性指定表名，domainObjectName指定生成的pojo类的名称 -->
        <table tableName="test" enableCountByExample="true" enableDeleteByExample="true" enableSelectByExample="true"
               enableUpdateByExample="true"/>
    </context>
</generatorConfiguration>
```

### PageHelper

MyBatis提供的官方插件，可以更方便得实现分页的功能

1. 导包

   具体需要导入的包可见MyBatis的GitHub

   ```xml
   <dependency>
       <groupId>com.github.pagehelper</groupId>
       <artifactId>pagehelper</artifactId>
       <version>最新版本</version>
   </dependency>
   ```

2. 配置

   在MyBatis的配置文件中，对其进行配置

   ```xml
   <plugins>
       <!-- com.github.pagehelper为PageHelper类所在包名 -->
       <plugin interceptor="com.github.pagehelper.PageInterceptor">
           <!-- 使用下面的方式配置参数，后面会有所有的参数介绍 -->
           <property name="param1" value="value1"/>
   	</plugin>
   </plugins>
   ```

   也可以直接在Spring的配置文件中进行配置

   ```xml
   <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
     <!-- 注意其他配置 -->
     <property name="plugins">
       <array>
         <bean class="com.github.pagehelper.PageInterceptor">
           <property name="properties">
             <!--使用下面的方式配置参数，一行配置一个 -->
             <value>
               params=value1
             </value>
           </property>
         </bean>
       </array>
     </property>
   </bean>
   ```

3. 使用

   其中提供了多种的使用方法

   ```java
   //第一种，RowBounds方式的调用
   List<User> list = sqlSession.selectList("x.y.selectIf", null, new RowBounds(0, 10));
   
   //第二种，Mapper接口方式的调用，推荐这种使用方式。
   PageHelper.startPage(1, 10);
   List<User> list = userMapper.selectIf(1);
   
   //第三种，Mapper接口方式的调用，推荐这种使用方式。
   PageHelper.offsetPage(1, 10);
   List<User> list = userMapper.selectIf(1);
   
   //第四种，参数方法调用
   //存在以下 Mapper 接口方法，你不需要在 xml 处理后两个参数
   public interface CountryMapper {
       List<User> selectByPageNumSize(
               @Param("user") User user,
               @Param("pageNum") int pageNum, 
               @Param("pageSize") int pageSize);
   }
   //配置supportMethodsArguments=true
   //在代码中直接调用：
   List<User> list = userMapper.selectByPageNumSize(user, 1, 10);
   
   //第五种，参数对象
   //如果 pageNum 和 pageSize 存在于 User 对象中，只要参数有值，也会被分页
   //有如下 User 对象
   public class User {
       //其他fields
       //下面两个参数名和 params 配置的名字一致
       private Integer pageNum;
       private Integer pageSize;
   }
   //存在以下 Mapper 接口方法，你不需要在 xml 处理后两个参数
   public interface CountryMapper {
       List<User> selectByPageNumSize(User user);
   }
   //当 user 中的 pageNum!= null && pageSize!= null 时，会自动分页
   List<User> list = userMapper.selectByPageNumSize(user);
   
   //第六种，ISelect 接口方式
   //jdk6,7用法，创建接口
   Page<User> page = PageHelper.startPage(1, 10).doSelectPage(new ISelect() {
       @Override
       public void doSelect() {
           userMapper.selectGroupBy();
       }
   });
   //jdk8 lambda用法
   Page<User> page = PageHelper.startPage(1, 10).doSelectPage(()-> userMapper.selectGroupBy());
   
   //也可以直接返回PageInfo，注意doSelectPageInfo方法和doSelectPage
   pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(new ISelect() {
       @Override
       public void doSelect() {
           userMapper.selectGroupBy();
       }
   });
   //对应的lambda用法
   pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(() -> userMapper.selectGroupBy());
   
   //count查询，返回一个查询语句的count数
   long total = PageHelper.count(new ISelect() {
       @Override
       public void doSelect() {
           userMapper.selectLike(user);
       }
   });
   //lambda
   total = PageHelper.count(()->userMapper.selectLike(user));
   ```

