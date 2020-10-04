# Spring

## 介绍

> ###### 框架
>
> 高度抽取可重用代码的一种设计，具有高度的通用性；是一种半成品的软件
>
> 其中主要包括多个可重用的模块的集合，形成某个领域的整体解决方案

Spring主要是一种容器框架，其主要依据是EJB，Enterprise Java Beans。可以用来管理所有的组件的框架

其拥有如下的优良特性

1. 非侵入式

   基于Spring的开发应用对象可以不依赖 Spring 的API

2. 依赖注入

   DI，Dependency Injection，反转控制（IOC）最经典的实现

3. 面向切面编程

   AOP，Aspect Oriented Programming

4. 容器

   Spring是一个容器，因为它包含并且管理应用对象的生命周期

5. 组件化

   Spring实现了使用简单的组件配置组成一个复杂的应用，在Spring中可以使用XML和Java注解组合这些对象

6. 一站式

   在IOC和AOP的基础上可以整合各种企业应用的开源框架和优秀的第三方类库

#### 结构

![Spring-结构](img/Spring-%E7%BB%93%E6%9E%84.jpg)

在上图中，每一个绿色部分都代表一个模块。需要哪个模块就只需要导入对应的包

- Test

  Spring的单元测试模块

  `spring-test`

- Core Container

  Spring的核心容器，主要指的是IOC容器，其中的黑色方框代表了每个`jar`包

  `spring-beans` `spring-core` `spring-context` `spring-expression`

- AOP + Aspects

  `spring-aop` `spring-aspects`

- Data Access/Integration

  数据访问模块，主要包括各种数据库的操作

  JDBC，Java Database Connecting，包括Java的各种数据库连接标准；ORM，Object Relation Mapping，对象关系映射；Transaction，事务相关的模块

  `spring-jdbc` `spring-orm` `spring-oxm` `spring-jms` `spring-tx`

- Web

  Spring开发Web应用的模块

  WebSocket，web开发的新技术；Servlet，原生的web相关，主要包括对应的包是`spring-web`；Web，开发web项目的；Protlet，开发web应用的组件集成

  `spring-websocket` `spring-web` `spring-webmvc` `spring-webmvc-portlet`
  
- Extra

  额外的`jar`包，虽然这次出没有标注，但是必须添加的包

  `commons-logging` 日志包，用于生成日志信息

## IOC

IOC，Inversion Of Control，控制反转

控制，主要指的是资源的获取方式，分为两种，<u>主动式和被动式</u>。主动式，所有的资源都是通过自己使用`new`构造器方法进行创建，如果在面对比较复杂的工程，会变得非常复杂；被动式，资源的获取不需要自行创建，而是借助容器进行创建和设置

容器，用于管理所有的组件，此处的组件指得是那些**有功能的类**，容器在创建过程中，能够自行处理创建过程中的各种逻辑

<u>通过IOC容器，就能实现资源的创建从主动创建，向被动接收方式转换</u>

### DI

DI，Dependency Injection，依赖注入。IOC是一种思想，DI就是它的实现原理

可以让容器知道那个组件或类，在运行的时候，需要哪些其他的类。容器通过反射的方式，向容器重准备好的对象进行注入，即为其中的属性赋值

只要是在容器管理之下的组件，都可以使用容器所提供的强大功能

### 基础过程

Spring支持两种配置方式，分别是使用配置文件的方式和使用注解的方式

#### 使用xml配置文件

1. 导入相关`jar`包

   `spring-beans` `spring-core` `spring-context` `spring-expression` `commons-logging`

2. 编写`Spring`配置

   1. 自动生成Spring的配置文件，或在官方文档中复制

   2. 使用`<bean>`标签，指定纳入容器管理的bean对象

      - 属性`name`属性名，`value`输入一个字符串经过类型转换之后为属性赋值，`ref`将其他的bean对象作为值传入

      - ------

        通过`setter`方法来实现对象的属性设置

        `<property>`中指定对象的属性名`name`与属性值`value`

      - 通过对象的有参构造器来设置其中的属性

        `<constructor-arg>`分别指定参数名`name`和参数值`value`

        - 此处的参数名可以省略，但是就必须严格按照构造器中的顺序进行指定，还可以使用`index`来指定参数索引
        - 如果存在多个构造器，其中的参数数量相同，且类型也不同时，可能出现赋值混乱，可以使用`type`指定赋值的类型

      - 使用Spring提供的`p`名称空间

        需要在`xml`配置文件中，先引入`p`命名空间才可以使用

        在`bean`标签内，直接使用`p:参数名=参数值 `来快速赋值，同样使用的是`setter`方法

        - `xmlns:p="http://www.springframework.org/schema/p"`
        - 在IDEA中希望加入名称空间，可以输入`xmlns:p=`选择对应的地址值即可

3. 创建IOC容器对象，获取对象

   - `ApplicationContext`代表IOC容器类，可以通过容器获取对象

     - `ClassPathXmlApplicationContext`继承自`ApplicationContext`，表示Spring的配置使用的是Java路径下的`xml`文件，可以直接指定配置文件地址
     - `FileSystemXmlApplicationContext`继承自`ApplicationContext`，表示对于整个系统下的路径中的`xml`文件，可以使用类加载器

   - `getBean()`获取bean对象的方法

     - `param String, class`

       通过IOC容器中的bean对象id，以及接收的对象类型，获取对象

     - `param class`

       直接传入类型，获取bean对象

       - 如果此时存在多个相同类型的bean在IOC容器中，会出现异常

##### 细节

1. IOC容器在项目运行时就将所有纳入容器管理的对象进行了创建
2. 同一个组件，在IOC容器都是单例的，创建的对象地址值都相同
3. 根据bean对象的id获取对象，如果没有指定的对象，会出现异常
4. `<porperty>`的赋值是利用的setter方法来对对象进行赋值
   - 布尔值`boolean`类型的属性，使用的是`is + xxx`的格式
5. bean对象的内部bean对象，就算设置了id，也无法获取

##### 注册补充

1. 希望将对象中的所有值设置为空`null`

   - 通常情况下， 只要不进行赋值，其中的所有值就应该为`null`
   - 如果属性值存在初始值，可以在其中使用`<null>`标签，将所有的属性设置为`null`

2. 设置自定义类型的属性值

   - 可以使用`ref`，<u>引用</u>其他的bean对象，**如果此时引用的对象发生改变，此处的属性也会改变**
   - 在`<property>`标签体中，使用`<bean>`标签创建一个对象

3. 设置集合类型的数据

   此处的集合数据类型，主要包括`List`、`Map`、`Property`等。可以将其分为`List`类型和`Map`类型，如此处的`Property`类型，包含`key`和`value`，就类似`Map`类型，只是其中的`key`和`value`都是`String`类型

   - `List`类型

     使用`<list>`标签，在其中添加值

     - 简单类型，直接使用`<value>`标签，在标签体中指定值
     - 复杂类型，可以使用`<bean>`相当于`new`了一个对象，或使用`<ref>`引用一个外部的bean对象

   - `Map`类型

     使用`<map>`标签，在其中添加值，其中的每个值使用`<entry>`标签来表示，需要指定`key`

     - 简单类型，在`<entry>`标签中设置属性`value`，属性值为需要的值
     - 复杂类型，在`<entry>`标签中设置属性`value-ref`，其中引用的bean对象，或只设定`key`，在标签体中使用`<bean>`来创建对象

   > util名称空间
   >
   > 对于某些集合数据，需要反复利用，希望多个对象中都可以引用时使用
   >
   > `xmlns:util="http://www.springframework.org/schema/util"`
   >
   > 其中包括各种集合的数据类型，如`<util:map>`、`<util:list>`。同时，它**必须指定id值**，因为util就是为了让其他的bean中引用此处的集合对象

4. 级联属性赋值

   级联属性，就是属性中的属性

   1. 在`<property>`中，使用`name="book.author"`似的方式，获取属性中的属性值，对值进行修改
      - 如果之前的属性使用的是`ref`，即引用的方式来设置属性，对其进行的修改，会导致引用对象属性值的修改

5. 通过继承实现bean配置信息的重用

   当注册了多个对象，希望它们的属性基本上都相同，只有某一项或几项数据不同，此时如果需要重复使用`<property>`进行赋值，就显得太过繁琐

   此时就可以借助继承，实现需要的效果，获取完全相同的属性。之后，对不同的属性，只需要重新使用`<property>`设置属性

   在`<bean>`标签中，使用`parent`属性，指定希望重用的对象，就相当于将其的配置信息进行了继承

6. 指明一个模板对象

   可以继承配置信息，而此时并不希望获取到这样的模板对象，就可以通过`<bean>`标签中的`abstract`属性，设定为`true`，就无法再获取这个bean对象

7. bean的创建顺序

   <u>此处主要针对使用`xml`配置文件的方式</u>

   - 默认情况下，其中的bean对象是从上到下依次创建
   - 可以在`<bean>`标签中，使用`depends-on`属性，指定在加载该对象之前，需要先加载某些对象，此处指定id值

8. bean的作用域

   <u>bean的作用域用于指定哪种类型的bean实例应该从IOC容器中返回给作用者</u>

   - `singleton` 默认情况下，bean为单例
     - 在启动时就创建对象，并存放到容器中
     - 任何时候获取到的都是最初存放到容器中的对象
   - `prototype` 多实例的
     - 在启动时不会创建对象
     - 每次获取时都会创建对象
   - `request` 在web环境下，同一次请求创建一个bean实例
   - `seession` 在web环境下，同一次会话创建一个bean实例
   - `application` 在web环境下，等同于SerlvetContext的生命周期
   
9. 配置工厂获取对象

   为了彻底隐藏对象创建的具体逻辑，使用其他的类来创建对象，使用工厂模式

   1. 静态工厂

      静态工厂指的是不需要创建工厂对象，可以直接使用其中的静态方法获取对象的工厂

      在`<bean>`中配置`class`指定工厂类，`factory-method`指定工厂中的静态方法，用方法的返回值作为bean对象

      如果指定的工厂方法有参数，需要使用`<constructor-arg>`来指定参数

   2. 实例工厂

      实例工厂指的是需要创建工厂对象，使用工厂对象中的实例方法来获取对象

      首先需要在容器中注册工厂的bean对象，以及工厂的生产对象

      在工厂的生产对象`<bean>`标签中指定`factory-bean`属性，指定实例工厂对象，之后再使用`factory-method`属性，指定实例工厂中的实例化方法

      如果有参数，依然是使用`<constructor-arg>`来指定参数

10. 指定不同生命周期时调用的方法

   bean对象的生命周期会根据其作用域的不同而不同，具体可见[bean的生命周期](#Bean的生命周期)

   可以设置其创建和销毁时自动调用的方法

   - `init-method` 

     被创建时自动调用的方法，**不能设置参数**，但是可以抛出异常

   - `destory-method` 

     被销毁时自动调用的方法，**不能设置参数**，但是可以抛出异常

11. 引用外部属性配置文件

   为了让发布后的项目可以方便的进行更改操作，其各种配置信息也应该独立开来，为了实现这样的效果，将其中的配置信息存放到外部的属性配置文件中

   在实际的项目中，像连接池的各种配置经常会使用到这样的技术

   需要使用`context`名称空间中的标签，可以用于获取外部的配置文件

   - `xmlns:context="http://www.springframework.org/schema/context"`

   - `<context:property-placeholder>`

     其中可以属性`location`指定外部的文件地址，可以使用`classpath:`表示类路径下的文件，会提示Spring是在类路径下的文件

   - 之后在所需要的地方使用`${ 变量名 }`，就可以从配置文件中获取变量值

   > 关键字
   >
   > Spring中存在一些关键字，不允许其他的配置文件中使用
   >
   > 如，`username`获取到的是系统的用户名
   >
   > 为了防止配置文件中的关键字和Spring中的关键字冲突，最好在起名的时候添加前缀，如MySQL连接池的配置文件，可以添加`jdbc.`前缀来表示
   
12. 自动装填

   此处的自动装填，主要针对自定义类型，基础数据类型的默认值与Java中相同

   自定义类型中的各个属性，如之前的`<property>`指定值的方式就是**手动赋值**。XML中的**自动赋值**，在使用`<bean>`标签时，添加属性`autowise`，其默认值为`default`表示不会自动赋值，其中存在如下几个可选值：

   - `default`或`no` 其中`default`为默认值，都表示不会自动赋值
   - `byName` 通过属性名称自动赋值，如属性名为`car`会自动在IOC容器中寻找id为`car`的bean对象，类似使用了`getBean()`方法，其中只传入了id值。找不到的情况会设定为`null`
   - `byType` 通过属性类型自动赋值，类似使用`getBean()`方法，其中传入的是类对象值。找不到的情况，会设定为`null`。如果容器中存在多个此类型的值，会报错`NoUniqueBeanDefinitionException`
   - `constructor` 按照构造器进行赋值，使用的是类中的有参构造器，按照类型进行进行装配。如果此时没有对应类型的bean对象，就会设定为`null`。如果存在多个对应类型的bean对象，<u>会转而使用id来匹配</u>，如果依然找不到，会设置为`null`

   如果处理的是`List`类型的集合，可以将容器中的所有bean对象装入其中

#### 使用注解

1. 导入`jar`包

   相比起使用配置文件的方式，需要额外导入AOP包`spring-aop`

2. 对需要纳入管理的类上添加注解

   通过对所需的bean上添加注解，可以快速将其加入IOC容器

   Spring提供了四个注解，只要在类上添加任意一个注解，都可以将其添加到IOC容器中

   - `@Component`

     组件，对于某些并不属于三层架构的组件，可以使用此注解

   - `@Controller`

     控制器，推荐将控制层（Servlet类似的文件）的组件添加此注解

   - `@Service`

     业务层，推荐将业务层的组件添加此注解

   - `@Repository`

     持久层，推荐将持久层（dao包）的组件添加此注解

   > 注解并没用硬性的规定，添加其中任意注解都可以将其添加如IOC容器中，最重要的作用是为了增强代码的可读性

3. 告知Spring自动扫描注解

   Spring中支持两种方式进行配置，使用xml配置文件以及使用Configurable配置类

   - 使用xml配置文件

     使用`context`名称空间中的`component-scan`标签，在其中的`base-package`属性，是**必须属性**，指定需要扫描的包

     - `xmlns:context="http://www.springframework.org/schema/context"`

     - `<context:component-scan base-package="[需要扫描的包]"/>`

       推荐至少指定两级目录，防止扫描`jar`包中的内容

##### 细节

1. 使用注解的方式，其加入容器中的默认id值为<u>类首字母小写</u>
2. 默认的作用域为<u>单例</u>
3. 通常情况下，注解无法解决已有类或已发布类加入容器的情况，此时可以使用注解与xml配置文件公用的方式或Configurable配置类的方式

##### 注册补充

1. 修改其基础设定

   - 修改默认名称

     使用注解的方式，其加入容器的默认名称，即没有进行指定的情况下，为<u>类的首字母小写</u>

     可以通过指定注解中的`value`值来进行修改

   - 修改bean默认作用域

     使用注解的方式，其bean默认的作用域为单例

     可以使用`@Scope`注解，指定其中的`value`值来进行修改，其中取值与xml中相同

2. xml扫描配置

   在扫描的过程中，可以用于排除某些不需要的部分或指定扫描过程中包含的部分

   在`<context:component>`中，存在两个内部标签

   - `<context:exclude-filter>`

     扫描时排除某些组件

   - `<context:include-filter>`

     扫描时只包括某些组件，<u>默认情况下为全部包含</u>

     如果需要使用此标签，需要先禁用默认`<context:component>`标签中的全部扫描情况，在`<context:component>`标签中添加属性`use-default-filter`，默认为`true`默认全部扫描，改为`faluse`不再使用默认的扫描规则

   其中存在的属性基本一致

   - `type`属性

     可以用于指定某种排除的规则，如`annotation`，如果包含目标注解，就不进行扫描

     -  `annotaion`

       指定此处使用的规则是**注解**，根据注解的信息进行包含和排除

     - `assignable`

       指定此处使用的规则是**类**，直接排除具体的某个类

     - `aspectj`

       指定此处使用的规则是**`aspectj`表达式**

     - `custom`

       指定此处使用的规则是根据**`TypeFilter`接口的实现类**，可以在实现类中编写具体的实现逻辑

     - `regex`

       指定此处使用的规则是**正则表达式**

   - `expression`属性

     用于指定某种格则的运行逻辑，如指定为`annotation`，指定是以注解为排除方式（包含类似，此处以排除为例）之后，在`expression`中就需要指定目标注解的全限定类名，以`@Controller`注解为例，此处就应该填写`org.springframework.stereotype.Controller`

3. 自动注入

   通过依赖注入的方式，实现对类属性的自动注入

   - `@Autowird`

     在需要的属性、方法或构造器上方，添加注解。方法和构造器会对其中的所有参数进行赋值

     1. 会根据属性的类型在容器中寻找bean对象
     2. 如果存在多个同类型的bean对象，会通过属性名作为id进行查询

     <u>如果IOC容器中不存在所需的bean对象，会出现异常</u>

     - `require`属性

       `@Autowird`标签标注的属性，默认一定会找到值，如果没有找到就会出现异常，停止运行

       `require`的默认值为`true`表示没有匹配就会抛出异常，`false`表示没有匹配时直接设定值为`null`

   - `@Resource`和`@Inject`

     都可以实现自动装填，与`@Autowired`类似，但是这些标签都是来自`JavaEE`中的标准，即`Java`原生的标准

     因为其是Java的原生标准，也能使用，因此<u>拓展性更强</u>

   - `@Qualifier`

     可以指定某个字符串，作为id进行查找，**不能独立使用**

     此标签的值，其他标签匹配id失败之后使用

     > 因为`@Autowird`可以直接作用在方法或构造器上，如果其中的属性也存在同样的问题，即类型存在多个，属性名不能匹配
     >
     > 为了解决这个问题，`@Qualifier`可以直接作用在参数中

4. 泛型依赖注入

   **是Spring4.0之后实现的功能**

   支持对于泛型的识别，能够更加方便地实现存在泛型的注入

   可以通过带泛型的父类来确定去具体的类型

   ```Java
   // 持久层
   // 通用方法
   @Repository
   public abstract class BaseDao<T> {
       public abstract void find();
   }
   // 两个具体的对象，假定为Test1和Test2
   @Repository
   public class Test1Dao extends BaseDao<Test1> {
   	@Override
       public void find(){
           ...
       }
   }
   @Repository
   public class Test2Dao extends BaseDao<Test2> {
       @Override
       public void find(){
           ...
       }
   }
   
   // 业务层
   // 通用方法，此处的类不需要加入容器
   public class BaseService<T> {
       @Autowired
       private BaseDao<T> baseDao;
       public void find(){
           baseDao.find();
       }
   }
   // 具体的类，方法的内部为空，唯一的作用是用于指定具体的泛型，方法的逻辑在业务层内部已经有体现
   @Service
   public class Test1Service extends BaseService<Test1> {
   }
   @Serivce
   public class Test2Service extends BaseService<Test2> {
   }
   ```

   > **注意：**
   >
   > 在业务层的通用方法中，并没有将其加入容器，理论上其中的`@Autowired`标签应该无效，但是因为其存在子类继承了该通用类，根据继承的设定，相当于子类中存在父类中的所有内容，因此，<u>标签虽然在父类中无法生效，但是在子类中可以生效</u>
   
5. 改变加载优先级

   作用与web.xml中的配置`<load-on-startup>`作用类似，但是此处主要指的是执行的顺序，以及从IOC容器中获取的顺序

   使用`@Order`标签，指定一个整数，代表优先级，越小的值优先级越高，可以影响其**类的加载顺序**

### 重点知识

#### Bean的生命周期

默认情况下，根据bean的作用域不同，其生命周期也有所不同

- 单例

  bean的默认设置

  在IOC容器启动时就会被创建，直到容器关闭才会销毁

- 多例

  在获取bean对象时才会创建，如果长时间没有被使用，由GC销毁

#### 工厂接口

FactoryBean是Spring内置的一个接口，其中是工厂类的标准或模板

如果类实现类该接口，Spring的IOC容器在创建时，就不会使用反射方式创建对象，而是通过这个接口的实现类，即工厂类来创建对象

该接口需要指定一个泛型，其表示的就是所返回的对象类型

##### 具体方法

- `getObject()` `return T`

  获取对象方法工厂的主方法，Spring会自动得调用方法

- `getObjectType()` `return Class<?>`

  Spring自动调用方法，用于确定生成的bean对象类型

- `isSingleton()` `return boolean`

  确定所生产的对象是否为单例，默认为`false`不是单例，`true`单例

##### 使用步骤

1. 编写`FactoryBean<T>`接口的实现类
2. 将实现的工厂类注册到IOC容器中
3. 直接通过工厂对象，其返回值就是工厂生产的类对象**【不是工厂对象】**

##### 注意

- `FactoryBean`这种方式的工厂模式，将其注册在IOC容器中，在启动时不会自动创建对象，都是在获取时才会创建对象，与单例多例无关

#### 后置处理器

Spring的后置处理器，主要用于在bean的初始化前后，调用方法

希望使用Spring的后置处理器，需要实现`BeanPostProcessor`接口

##### 具体方法

- `postProcessAfterInitialization()` `param Object, String` `return Object`

  在初始化之后调用方法，Spring会调用此方法并自动传入参数

  参数中，`String`是传入的bean名称，`Object`就是具体的bean对象

  返回的就是具体的bean对象，此处返回的bean对象最终就会存入IOC容器中，之后从容器中获取的bean对象都是此处的结果

- `postProcessBeforeInitialization()` `param Object, String` `return Object`

  在初始化之前调用方法，Spring会调用此方法并自动传入参数

  参数中，`String`是传入的bean名称，`Object`就是具体的bean对象

  返回的就是具体的bean对象

类似代理模式，可以在bean的前后进行操作，对其进行增强

##### 使用步骤

1. 编写`BeanPostProcessor`接口的实现类
2. 将后置处理器的实现类注册到IOC容器中
3. 后置处理器的作用对象创建时，就会自动调用其中的方法

#### SpEL

SpEL，Spring Expression Language，Spring中的表达式语言

其中的表达式语言基本上与JSP中的EL语言类似

主要用于Spring的各种配置信息中

- `#{  }`

  1. 其中支持使用各种运算符，如`#{20*12}`会返回其中算式的结果值
  2. 可以用于获取注册过的bean对象，如`#{account}`就能直接获取对象，并且能够使用`value`属性的方式对属性赋值
  3. 可以获取对象的属性，如`#{account.id}`能够直接获取到对象中的id属性值
  4. 调用<u>静态方法</u>，必须使用特殊的格式`#{T(全限定类名).静态方法名([方法参数列表])}`，如此时需要调用`com.tms.test.utils.RandomUtils`类中的`getRandom()`方法，`#{T(com.tms.test.utils.RandomUtils).getRandom()}`
  5. 调用<u>非静态方法</u>，与获取对象属性基本一致，如`#{account.toString()}`

  > **方法都支持链式使用**

#### Spring的单元测试

`Junit`本身并不提供对于Spring的支持，即在`Junit`测试类中，不允许使用Spring中的自动注入的方式获取值，但是`Junit`接收拓展，可以使用Spring中的测试模块来实现所需功能

##### 使用步骤

1. 导入Spring中的测试模块jar包

   导入`spring-test`包

2. 使用标签标注测试类

   使用`@RunWith`使用`Junit`中提供的拓展，来加载Spring的实现单元测试方法，其中接收一个值`SpringJUnit4ClassRunner.class`

   通过这样的配置，这个配置类就可以使用Spring提供的单元测试来实现

3. 加载配置文件

   `@ContextConfiguration`标签，用于指定Spring配置文件的位置，其中使用`location`属性，指定xml配置文件的位置或Configuration配置类的位置

### 总结

IOC就是一种容器，用于帮助管理组件

- 依赖注入：提供了多种方式来实现自动注入
- 如果需要使用Spring提供的IOC、AOP等操作，就必须将其纳入IOC容器的管理中

### 源码

通过分析其运行的过程，来对源码进行分析

##### 创建容器对象

使用`new`的方式创建`ClassPathXMLApplicationContext`对象，通过源码可知，其所有的构造器，最终调用的都是需要分别传入`configLocation`、`refresh`和`ApplicationContext父类`的构造器。

最常用的只传入一个`configLocation`的构造器，实际上是设置默认值`refresh=true, parent=null`

```java
public ClassPathXmlApplicationContext(
			String[] configLocations, boolean refresh, @Nullable ApplicationContext parent)
			throws BeansException {
    super(parent);
    setConfigLocations(configLocations);
    if (refresh) {
        refresh();
    }
}
```

之后，使用了`refresh()`方法，开始创建IOC容器

```java
@Override
public void refresh() throws BeansException, IllegalStateException {
    // 同步锁，保证多线程只会创建一个IOC容器
    synchronized (this.startupShutdownMonitor) {
        // 预处理方法
        prepareRefresh();

        // 创建了一个Bean的工厂，其是BeanFactory>ListableBeanFactory的子类，解析了配置文件，获取到其中的信息
        // 其中使用beanDefinitionNames封装了所有的bean对象的信息
        // 解析xml配置文件，还没有创建IOC容器
        ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

        // Prepare the bean factory for use in this context.
        prepareBeanFactory(beanFactory);

        try {
            // Allows post-processing of the bean factory in context subclasses.
            postProcessBeanFactory(beanFactory);

            // Invoke factory processors registered as beans in the context.
            invokeBeanFactoryPostProcessors(beanFactory);

            // Register bean processors that intercept bean creation.
            registerBeanPostProcessors(beanFactory);

            // Initialize message source for this context.
            // 用于支持国际化功能
            initMessageSource();

            // Initialize event multicaster for this context.
            initApplicationEventMulticaster();

            // Initialize other special beans in specific context subclasses.
            // 注册其他指定的bean，空方法，可以用于拓展
            onRefresh();

            // Check for listener beans and register them.
            registerListeners();

            // Instantiate all remaining (non-lazy-init) singletons.
            // 完成对beanfactory的初始化【单例】【重要】
            finishBeanFactoryInitialization(beanFactory);

            // Last step: publish corresponding event.
            finishRefresh();
        }

        catch (BeansException ex) {
            if (logger.isWarnEnabled()) {
                logger.warn("Exception encountered during context initialization - " +
                            "cancelling refresh attempt: " + ex);
            }

            // Destroy already created singletons to avoid dangling resources.
            destroyBeans();

            // Reset 'active' flag.
            cancelRefresh(ex);

            // Propagate exception to caller.
            throw ex;
        }

        finally {
            // Reset common introspection caches in Spring's core, since we
            // might not ever need metadata for singleton beans anymore...
            resetCommonCaches();
        }
    }
}
```

其中主要使用了`finishBeanFactoryInitialization(beanFactory)`实现了所有单例bean对象的创建

```java
protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
    // Initialize conversion service for this context.
    if (beanFactory.containsBean(CONVERSION_SERVICE_BEAN_NAME) &&
        beanFactory.isTypeMatch(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class)) {
        beanFactory.setConversionService(
            beanFactory.getBean(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class));
    }

    // Register a default embedded value resolver if no bean post-processor
    // (such as a PropertyPlaceholderConfigurer bean) registered any before:
    // at this point, primarily for resolution in annotation attribute values.
    if (!beanFactory.hasEmbeddedValueResolver()) {
        beanFactory.addEmbeddedValueResolver(strVal -> getEnvironment().resolvePlaceholders(strVal));
    }

    // Initialize LoadTimeWeaverAware beans early to allow for registering their transformers early.
    String[] weaverAwareNames = beanFactory.getBeanNamesForType(LoadTimeWeaverAware.class, false, false);
    for (String weaverAwareName : weaverAwareNames) {
        getBean(weaverAwareName);
    }

    // Stop using the temporary ClassLoader for type matching.
    beanFactory.setTempClassLoader(null);

    // Allow for caching all bean definition metadata, not expecting further changes.
    beanFactory.freezeConfiguration();

    // Instantiate all remaining (non-lazy-init) singletons.
    // 实例化所有剩余的单例实例（主要指的是通过拓展的方法处理之后的其他单实例对象）
    beanFactory.preInstantiateSingletons();
}
```

其中，主要使用`beanFactory.preInstantiateSingletons()`方法，来实现了单例bean的实例

进入了`DefaultListableBeanFactory`中的具体方法

```java
@Override
public void preInstantiateSingletons() throws BeansException {
    if (logger.isTraceEnabled()) {
        logger.trace("Pre-instantiating singletons in " + this);
    }

    // Iterate over a copy to allow for init methods which in turn register new bean definitions.
    // While this may not be part of the regular factory bootstrap, it does otherwise work fine.
    // 复制所有的beanName，保存为List对象进行存储
    List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);

    // Trigger initialization of all non-lazy singleton beans...
    // 使用for循环创建了bean对象，因此其中创建也具有一定的顺序
    for (String beanName : beanNames) {
        // 根据bean的id获取到xml相关的配置信息
        RootBeanDefinition bd = getMergedLocalBeanDefinition(beanName);
        // 判断bean非抽象，并且是单例，并且不是懒汉式
        if (!bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit()) {
            // 判断是否是FactoryBean（实现了Spring中的FactoryBean接口，作为标准的工厂类或者是配置中标识该类是工厂类，需要调用其中的方法创建产品对象）
            if (isFactoryBean(beanName)) {
                Object bean = getBean(FACTORY_BEAN_PREFIX + beanName);
                // 是否实现了Spring中提供的FactoryBean接口
                if (bean instanceof FactoryBean) {
                    final FactoryBean<?> factory = (FactoryBean<?>) bean;
                    boolean isEagerInit;
                    if (System.getSecurityManager() != null && factory instanceof SmartFactoryBean) {
                        isEagerInit = AccessController.doPrivileged((PrivilegedAction<Boolean>)
                                                                    ((SmartFactoryBean<?>) factory)::isEagerInit,
                                                                    getAccessControlContext());
                    }
                    else {
                        isEagerInit = (factory instanceof SmartFactoryBean &&
                                       ((SmartFactoryBean<?>) factory).isEagerInit());
                    }
                    if (isEagerInit) {
                        getBean(beanName);
                    }
                }
            }
            else {
                // 创建并对象【重要】
                getBean(beanName);
            }
        }
    }

    // Trigger post-initialization callback for all applicable beans...
    for (String beanName : beanNames) {
        Object singletonInstance = getSingleton(beanName);
        if (singletonInstance instanceof SmartInitializingSingleton) {
            final SmartInitializingSingleton smartSingleton = (SmartInitializingSingleton) singletonInstance;
            if (System.getSecurityManager() != null) {
                AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
                    smartSingleton.afterSingletonsInstantiated();
                    return null;
                }, getAccessControlContext());
            }
            else {
                smartSingleton.afterSingletonsInstantiated();
            }
        }
    }
}
```

使用`getBean(beanName)`方法，用于实例化bean对象，包含了创建bean对象的细节

使用的是`AbstructBeanFactory`类中的方法（刚才的`DefaultListableBeanFactory`是此处的`AbstructBeanFactory`的子类，因此上方直接使用`getBean()`方法调用的实际上是`AbstructBeanFactory`中的具体方法

```java
@Override
public Object getBean(String name) throws BeansException {
    return doGetBean(name, null, null, false);
}

/** doGetBean方法 */
protected <T> T doGetBean(final String name, @Nullable final Class<T> requiredType,
			@Nullable final Object[] args, boolean typeCheckOnly) throws BeansException {

    final String beanName = transformedBeanName(name);
    Object bean;

    // Eagerly check singleton cache for manually registered singletons.
    // 查询缓存中是否存在bean对象（缓存存放已注册的所有单例bean）
    Object sharedInstance = getSingleton(beanName);
    if (sharedInstance != null && args == null) {
        if (logger.isTraceEnabled()) {
            if (isSingletonCurrentlyInCreation(beanName)) {
                logger.trace("Returning eagerly cached instance of singleton bean '" + beanName +
                             "' that is not fully initialized yet - a consequence of a circular reference");
            }
            else {
                logger.trace("Returning cached instance of singleton bean '" + beanName + "'");
            }
        }
        bean = getObjectForBeanInstance(sharedInstance, name, beanName, null);
    }

    else {
        // Fail if we're already creating this bean instance:
        // We're assumably within a circular reference.
        if (isPrototypeCurrentlyInCreation(beanName)) {
            throw new BeanCurrentlyInCreationException(beanName);
        }

        // Check if bean definition exists in this factory.
        BeanFactory parentBeanFactory = getParentBeanFactory();
        if (parentBeanFactory != null && !containsBeanDefinition(beanName)) {
            // Not found -> check parent.
            String nameToLookup = originalBeanName(name);
            if (parentBeanFactory instanceof AbstractBeanFactory) {
                return ((AbstractBeanFactory) parentBeanFactory).doGetBean(
                    nameToLookup, requiredType, args, typeCheckOnly);
            }
            else if (args != null) {
                // Delegation to parent with explicit args.
                return (T) parentBeanFactory.getBean(nameToLookup, args);
            }
            else if (requiredType != null) {
                // No args -> delegate to standard getBean method.
                return parentBeanFactory.getBean(nameToLookup, requiredType);
            }
            else {
                return (T) parentBeanFactory.getBean(nameToLookup);
            }
        }
        // 可以作用多线程，防止多次创建
        if (!typeCheckOnly) {
            markBeanAsCreated(beanName);
        }

        try {
            // 获取当前bean的定义信息
            final RootBeanDefinition mbd = getMergedLocalBeanDefinition(beanName);
            checkMergedBeanDefinition(mbd, beanName, args);

            // Guarantee initialization of beans that the current bean depends on.
            // 获取当前bean的dependson属性（定义加载当前bean之前依赖的其他bean对象）
            String[] dependsOn = mbd.getDependsOn();
            // 如果定义了dependson，就迭代
            if (dependsOn != null) {
                for (String dep : dependsOn) {
                    if (isDependent(beanName, dep)) {
                        throw new BeanCreationException(mbd.getResourceDescription(), beanName,
                                                        "Circular depends-on relationship between '" + beanName + "' and '" + dep + "'");
                    }
                    registerDependentBean(dep, beanName);
                    try {
                        // 迭代方法，创建depends-on其中依赖的bean对象
                        getBean(dep);
                    }
                    catch (NoSuchBeanDefinitionException ex) {
                        throw new BeanCreationException(mbd.getResourceDescription(), beanName,
                                                        "'" + beanName + "' depends on missing bean '" + dep + "'", ex);
                    }
                }
            }

            // Create bean instance.
            // 如果对象是单例的，就创建bean实例
            if (mbd.isSingleton()) {
                // 使用了Lambda表达式，其中传入的相当于是ObjectFactory(函数式接口)中的getObject方法(无参、返回泛型类对象)【重要】
                // 使用的是DefaultSingletonBeanRegistry类中的方法（当前类继承自DefaultSingletonBeanRegistry）
                sharedInstance = getSingleton(beanName, () -> {
                    try {
                        // 调用AbstractBeanFactory(本类中)的createBean方法，来创建bean对象
                        return createBean(beanName, mbd, args);
                    }
                    catch (BeansException ex) {
                        // Explicitly remove instance from singleton cache: It might have been put there
                        // eagerly by the creation process, to allow for circular reference resolution.
                        // Also remove any beans that received a temporary reference to the bean.
                        destroySingleton(beanName);
                        throw ex;
                    }
                });
                bean = getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
            }

            else if (mbd.isPrototype()) {
                // It's a prototype -> create a new instance.
                Object prototypeInstance = null;
                try {
                    beforePrototypeCreation(beanName);
                    prototypeInstance = createBean(beanName, mbd, args);
                }
                finally {
                    afterPrototypeCreation(beanName);
                }
                bean = getObjectForBeanInstance(prototypeInstance, name, beanName, mbd);
            }

            else {
                String scopeName = mbd.getScope();
                final Scope scope = this.scopes.get(scopeName);
                if (scope == null) {
                    throw new IllegalStateException("No Scope registered for scope name '" + scopeName + "'");
                }
                try {
                    Object scopedInstance = scope.get(beanName, () -> {
                        beforePrototypeCreation(beanName);
                        try {
                            return createBean(beanName, mbd, args);
                        }
                        finally {
                            afterPrototypeCreation(beanName);
                        }
                    });
                    bean = getObjectForBeanInstance(scopedInstance, name, beanName, mbd);
                }
                catch (IllegalStateException ex) {
                    throw new BeanCreationException(beanName,
                                                    "Scope '" + scopeName + "' is not active for the current thread; consider " +
                                                    "defining a scoped proxy for this bean if you intend to refer to it from a singleton",
                                                    ex);
                }
            }
        }
        catch (BeansException ex) {
            cleanupAfterBeanCreationFailure(beanName);
            throw ex;
        }
    }

    // Check if required type matches the type of the actual bean instance.
    if (requiredType != null && !requiredType.isInstance(bean)) {
        try {
            T convertedBean = getTypeConverter().convertIfNecessary(bean, requiredType);
            if (convertedBean == null) {
                throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
            }
            return convertedBean;
        }
        catch (TypeMismatchException ex) {
            if (logger.isTraceEnabled()) {
                logger.trace("Failed to convert bean '" + name + "' to required type '" +
                             ClassUtils.getQualifiedName(requiredType) + "'", ex);
            }
            throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
        }
    }
    return (T) bean;
}
```

getSingleton方法

```java
public Object getSingleton(String beanName, ObjectFactory<?> singletonFactory) {
    Assert.notNull(beanName, "Bean name must not be null");
    synchronized (this.singletonObjects) {
        // 获取到bean对象
        Object singletonObject = this.singletonObjects.get(beanName);
        // 如果获取到的bean对象为空
        if (singletonObject == null) {
            if (this.singletonsCurrentlyInDestruction) {
                throw new BeanCreationNotAllowedException(beanName,
                                                          "Singleton bean creation not allowed while singletons of this factory are in destruction " +
                                                          "(Do not request a bean from a BeanFactory in a destroy method implementation!)");
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Creating shared instance of singleton bean '" + beanName + "'");
            }
            beforeSingletonCreation(beanName);
            boolean newSingleton = false;
            boolean recordSuppressedExceptions = (this.suppressedExceptions == null);
            if (recordSuppressedExceptions) {
                this.suppressedExceptions = new LinkedHashSet<>();
            }
            try {
                // 通过传入参数中的ObjectFactory工厂，使用其中的getObject方法来创建对象（使用了反射）
                singletonObject = singletonFactory.getObject();
                newSingleton = true;
            }
            catch (IllegalStateException ex) {
                // Has the singleton object implicitly appeared in the meantime ->
                // if yes, proceed with it since the exception indicates that state.
                singletonObject = this.singletonObjects.get(beanName);
                if (singletonObject == null) {
                    throw ex;
                }
            }
            catch (BeanCreationException ex) {
                if (recordSuppressedExceptions) {
                    for (Exception suppressedException : this.suppressedExceptions) {
                        ex.addRelatedCause(suppressedException);
                    }
                }
                throw ex;
            }
            finally {
                if (recordSuppressedExceptions) {
                    this.suppressedExceptions = null;
                }
                afterSingletonCreation(beanName);
            }
            if (newSingleton) {
                // 将创建的bean对象添加进Map中
                addSingleton(beanName, singletonObject);
            }
        }
        return singletonObject;
    }
}
```

通过最终的分析，可以了解到`singletonObjects`就是最后的Map中

```java
/** Cache of singleton objects: bean name to bean instance. */
private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
```

所有的单例bean对象都会被存放到`DefaultSingletonBeanRegistry`类中的属性`singletonObjects`中，使用`Map<String, Object>`来存放（存放之一，还有其他的容器用于存放对象）

###### 大概流程

主要指的是正常执行时的流程

1. ClassPathXmlApplicationContext
   1. 使用了其中的构造器，调用了其中的三个参数的构造器，其中调用了`refresh()`方法
   2. `refresh()`进入下一步
2. AbstractApplicationContext
   1. 使用其中的`finishBeanFactoryInitialization()`方法
   2. 使用使用获取到的`beanFactory`对象（`DefaultListableBeanFactory`类）中的`preInstantiateSingletons()`方法
3. DefaultListableBeanFactory
   1. 在上一步中，最后调用了此类对象的`preInstantisateSingleton()`方法，在方法后使用了`getBean()`方法，进入下一步
4. AbstractBeanFactory
   1. 首先使用了`getBean()`方法，其中调用了`goGetBean()`方法
   2. `doGetBean()`方法中，使用了`DefaultSingletonBeanRegistry`类中的`getSingleton()`方法（AbstractBeanFactory继承自DefaultSingletonBeanRegistry，使用的是其中的方法）。同时，需要传入一个函数式接口的Lambda匿名类，实现了其中的`getObject()`方法
5. DefaultSingtonBeanRegistry
   1. `getSingleton()`方法，调用了刚才使用Lambda匿名类实现的方法，创建了bean对象
   2. 使用`addSingleton()`方法，将刚才创建的bean对象加入容器
   3. bean对象使用`Map<String, Object>`来进行存储

##### 获取bean对象

使用容器对象，调用`getBean()`方法来获取对象，调用的是AbstractApplicationContext类（抽象类）中的具体方法

```java
@Override
public <T> T getBean(Class<T> requiredType) throws BeansException {
    assertBeanFactoryActive();
    // 获取bean工厂，用获取的bean工厂中的getBean方法来获取具体的bean对象
    return getBeanFactory().getBean(requiredType);
}
```

如果是<u>按类型获取bean对象</u>，获取到的bean工厂为`DefaultListableBeanFactory`，使用的是其中的`getBean()`方法

```java
@Override
public <T> T getBean(Class<T> requiredType) throws BeansException {
    // 直接调用的是另一个重载方法
   return getBean(requiredType, (Object[]) null);
}
// 最终使用方法
@Override
public <T> T getBean(Class<T> requiredType, @Nullable Object... args) throws BeansException {
    Assert.notNull(requiredType, "Required type must not be null");
    // 调用方法，获取了bean对象
    Object resolved = resolveBean(ResolvableType.forRawClass(requiredType), args, false);
    if (resolved == null) {
        throw new NoSuchBeanDefinitionException(requiredType);
    }
    return (T) resolved;
}
```

```java
@Nullable
private <T> T resolveBean(ResolvableType requiredType, @Nullable Object[] args, boolean nonUniqueAsNull) {
    // 通过类名称来试图获取bean对象，调用resolveNamedBean方法
   NamedBeanHolder<T> namedBean = resolveNamedBean(requiredType, args, nonUniqueAsNull);
   if (namedBean != null) {
      return namedBean.getBeanInstance();
   }
   BeanFactory parent = getParentBeanFactory();
   if (parent instanceof DefaultListableBeanFactory) {
      return ((DefaultListableBeanFactory) parent).resolveBean(requiredType, args, nonUniqueAsNull);
   }
   else if (parent != null) {
      ObjectProvider<T> parentProvider = parent.getBeanProvider(requiredType);
      if (args != null) {
         return parentProvider.getObject(args);
      }
      else {
         return (nonUniqueAsNull ? parentProvider.getIfUnique() : parentProvider.getIfAvailable());
      }
   }
   return null;
}
// 调用方法，试图通过类名称来获取bean对象
@Nullable
private <T> NamedBeanHolder<T> resolveNamedBean(
    ResolvableType requiredType, @Nullable Object[] args, boolean nonUniqueAsNull) throws BeansException {

    Assert.notNull(requiredType, "Required type must not be null");
    // 通过类型获取bean的名称
    String[] candidateNames = getBeanNamesForType(requiredType);
	// 如果获取到的名称不唯一
    if (candidateNames.length > 1) {
        List<String> autowireCandidates = new ArrayList<>(candidateNames.length);
        for (String beanName : candidateNames) {
            if (!containsBeanDefinition(beanName) || getBeanDefinition(beanName).isAutowireCandidate()) {
                autowireCandidates.add(beanName);
            }
        }
        if (!autowireCandidates.isEmpty()) {
            candidateNames = StringUtils.toStringArray(autowireCandidates);
        }
    }
	// 获取到的名称唯一
    if (candidateNames.length == 1) {
        // 取其中的第一个值
        String beanName = candidateNames[0];
        // 此处调用了AbstractBeanFactory中的getBean方法，开始和使用bean的id获取对象线路相同
        return new NamedBeanHolder<>(beanName, (T) getBean(beanName, requiredType.toClass(), args));
    }
    else if (candidateNames.length > 1) {
        Map<String, Object> candidates = new LinkedHashMap<>(candidateNames.length);
        for (String beanName : candidateNames) {
            if (containsSingleton(beanName) && args == null) {
                Object beanInstance = getBean(beanName);
                candidates.put(beanName, (beanInstance instanceof NullBean ? null : beanInstance));
            }
            else {
                candidates.put(beanName, getType(beanName));
            }
        }
        String candidateName = determinePrimaryCandidate(candidates, requiredType.toClass());
        if (candidateName == null) {
            candidateName = determineHighestPriorityCandidate(candidates, requiredType.toClass());
        }
        if (candidateName != null) {
            Object beanInstance = candidates.get(candidateName);
            if (beanInstance == null || beanInstance instanceof Class) {
                beanInstance = getBean(candidateName, requiredType.toClass(), args);
            }
            return new NamedBeanHolder<>(candidateName, (T) beanInstance);
        }
        if (!nonUniqueAsNull) {
            throw new NoUniqueBeanDefinitionException(requiredType, candidates.keySet());
        }
    }

    return null;
}
```

如果使用的是指定bean名称来获取对象，即bean的id值来获取，就会获取到`AbstractBeanFactory`，并调用其中的`getBean()`方法

```java
@Override
public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
   return doGetBean(name, requiredType, null, false);
}
protected <T> T doGetBean(final String name, @Nullable final Class<T> requiredType,
			@Nullable final Object[] args, boolean typeCheckOnly) throws BeansException {

    final String beanName = transformedBeanName(name);
    Object bean;

    // Eagerly check singleton cache for manually registered singletons.
    // 直接调用了DefaultSingletonBeanRegistry中的方法，通过名称获取到了存放的bean对象
    Object sharedInstance = getSingleton(beanName);
    if (sharedInstance != null && args == null) {
        if (logger.isTraceEnabled()) {
            if (isSingletonCurrentlyInCreation(beanName)) {
                logger.trace("Returning eagerly cached instance of singleton bean '" + beanName +
                             "' that is not fully initialized yet - a consequence of a circular reference");
            }
            else {
                logger.trace("Returning cached instance of singleton bean '" + beanName + "'");
            }
        }
        bean = getObjectForBeanInstance(sharedInstance, name, beanName, null);
    }

    else {
        // Fail if we're already creating this bean instance:
        // We're assumably within a circular reference.
        if (isPrototypeCurrentlyInCreation(beanName)) {
            throw new BeanCurrentlyInCreationException(beanName);
        }

        // Check if bean definition exists in this factory.
        BeanFactory parentBeanFactory = getParentBeanFactory();
        if (parentBeanFactory != null && !containsBeanDefinition(beanName)) {
            // Not found -> check parent.
            String nameToLookup = originalBeanName(name);
            if (parentBeanFactory instanceof AbstractBeanFactory) {
                return ((AbstractBeanFactory) parentBeanFactory).doGetBean(
                    nameToLookup, requiredType, args, typeCheckOnly);
            }
            else if (args != null) {
                // Delegation to parent with explicit args.
                return (T) parentBeanFactory.getBean(nameToLookup, args);
            }
            else if (requiredType != null) {
                // No args -> delegate to standard getBean method.
                return parentBeanFactory.getBean(nameToLookup, requiredType);
            }
            else {
                return (T) parentBeanFactory.getBean(nameToLookup);
            }
        }

        if (!typeCheckOnly) {
            markBeanAsCreated(beanName);
        }

        try {
            final RootBeanDefinition mbd = getMergedLocalBeanDefinition(beanName);
            checkMergedBeanDefinition(mbd, beanName, args);

            // Guarantee initialization of beans that the current bean depends on.
            String[] dependsOn = mbd.getDependsOn();
            if (dependsOn != null) {
                for (String dep : dependsOn) {
                    if (isDependent(beanName, dep)) {
                        throw new BeanCreationException(mbd.getResourceDescription(), beanName,
                                                        "Circular depends-on relationship between '" + beanName + "' and '" + dep + "'");
                    }
                    registerDependentBean(dep, beanName);
                    try {
                        getBean(dep);
                    }
                    catch (NoSuchBeanDefinitionException ex) {
                        throw new BeanCreationException(mbd.getResourceDescription(), beanName,
                                                        "'" + beanName + "' depends on missing bean '" + dep + "'", ex);
                    }
                }
            }

            // Create bean instance.
            if (mbd.isSingleton()) {
                sharedInstance = getSingleton(beanName, () -> {
                    try {
                        return createBean(beanName, mbd, args);
                    }
                    catch (BeansException ex) {
                        // Explicitly remove instance from singleton cache: It might have been put there
                        // eagerly by the creation process, to allow for circular reference resolution.
                        // Also remove any beans that received a temporary reference to the bean.
                        destroySingleton(beanName);
                        throw ex;
                    }
                });
                bean = getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
            }

            else if (mbd.isPrototype()) {
                // It's a prototype -> create a new instance.
                Object prototypeInstance = null;
                try {
                    beforePrototypeCreation(beanName);
                    prototypeInstance = createBean(beanName, mbd, args);
                }
                finally {
                    afterPrototypeCreation(beanName);
                }
                bean = getObjectForBeanInstance(prototypeInstance, name, beanName, mbd);
            }

            else {
                String scopeName = mbd.getScope();
                final Scope scope = this.scopes.get(scopeName);
                if (scope == null) {
                    throw new IllegalStateException("No Scope registered for scope name '" + scopeName + "'");
                }
                try {
                    Object scopedInstance = scope.get(beanName, () -> {
                        beforePrototypeCreation(beanName);
                        try {
                            return createBean(beanName, mbd, args);
                        }
                        finally {
                            afterPrototypeCreation(beanName);
                        }
                    });
                    bean = getObjectForBeanInstance(scopedInstance, name, beanName, mbd);
                }
                catch (IllegalStateException ex) {
                    throw new BeanCreationException(beanName,
                                                    "Scope '" + scopeName + "' is not active for the current thread; consider " +
                                                    "defining a scoped proxy for this bean if you intend to refer to it from a singleton",
                                                    ex);
                }
            }
        }
        catch (BeansException ex) {
            cleanupAfterBeanCreationFailure(beanName);
            throw ex;
        }
    }

    // Check if required type matches the type of the actual bean instance.
    if (requiredType != null && !requiredType.isInstance(bean)) {
        try {
            T convertedBean = getTypeConverter().convertIfNecessary(bean, requiredType);
            if (convertedBean == null) {
                throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
            }
            return convertedBean;
        }
        catch (TypeMismatchException ex) {
            if (logger.isTraceEnabled()) {
                logger.trace("Failed to convert bean '" + name + "' to required type '" +
                             ClassUtils.getQualifiedName(requiredType) + "'", ex);
            }
            throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
        }
    }
    return (T) bean;
}
```

## AOP

AOP，Aspect Oriented Programming，面向切面编程（OOP，Object Oriented Programming，面向对象编程），是一种新的编程思想，基于OOP的扩展

在程序的运行期间，将<u>某段代码动态的切入到指定方法的前或后进行运行的编程方式</u>，就叫做**面向切面编程**

类似动态代理，但是动态代理存在如下问题

1. 编写困难，不同代理逻辑还需要不同的而代理对象
2. 需要目标对象实现一定的接口，接口的作用是为了使用多态的方式，获取方法

<u>AOP的底层逻辑就是动态代理，简化了其中的逻辑，同时不再强制被代理对象需要实现接口</u>

### 专业术语

1. Target

   目标对象，需要进行操作的目标类或被代理类

2. Joinpoint

   连接点，表示对方法操作的某个特定位置，主要包括<u>方法执行前、方法执行后、方法异常时、方法返回前</u>

3. Pointcut

   切点，表示的是具体的所需要操作的某个连接点，并作用在目标连接点上

4. Advice

   增强或通知，AOP就是用于对于某一特定的连接点增加特定的功能，即对其进行增强，而其中用于增强的代码就是此处的增强或通知

5. Aspect

   切面，就是增强和切点的结合，其共同组成了切面

   类似动态代理中代理类，其中定义了作用的方法以及作用点

6. Weaving

   织入，使用切入点表达式，将增强添加到目标类连接点上

7. AOP proxy

   AOP代理，一个类被AOP织入后生成了一个结果类，其中包含了原类和增强逻辑的代理类

### 基础步骤

#### 使用xml配置文件

<u>Spring的IOC配置使用了注解的方式</u>

1. 导入相关`jar`包

   基本的AOP包 `spring-aspect`

   增强的AOP包 ，即使目标对象没有实现任何的接口也可以实现动态代理`com.springsource.net.sf.cglib` `com.springsource.org.aopalliance` `com.springsource.org.aspectj.weaver`

2. 编写相关的配置

3. 将目标和切面都加入IOC容器的管理

4. 配置切面信息

   此处需要使用`aop`名称空间下的`config`标签来设定AOP相关配置

   在`<aop:config>`标签内部，使用`<aop:aspect>`标签来指定切面类，其中需要使用`ref`属性来指定某个IOC容器中的bean对象

   在`<aop:aspect>`标签内部，使用`<aop:before>``<aop:after>``<aop:after-returning>``<aop:after-throwing>`来分别表示前置通知、后置通知、正常返回通知、异常通知，其中需要使用`method`属性来指定具体的切面类中的方法，以及使用`pointcut`来使用切入点表达式，具体格式与基于注解的方式实现相同

   如果其中方法希望接收返回值，也与注解的方式类似，使用`returning`属性指定参数名，用于接收方法返回值

   - `xmlns:aop="http://www.springframework.org/schema/aop"`

##### 细节

1. 使用`<aop:config>`的内部标签`<aop:pointcut>`可以指定一个在当前`<aop:config>`中通用的切入点表达式，其中需要分别指定切入点表达式的值`expression`以及之后调用时使用的`id`
2. 切面的顺序是在`<aop:config>`中的配置的顺序来决定的，同样可以在`<aop:aspect>`中使用`order`属性来决定其执行的顺序

#### 使用注解的方式

1. 导入相关的`jar`包

   与xml配置中相同

2. 编写相关的配置

   1. 将目标和切面都加入IOC容器的管理

   2. 使用`@Aspect`告知Spring那个类是切面类

   3. 在切面类中的具体方法上添加注解，告知方法的切点

      - `@Before`

        在目标方法执行之前执行

        其中需要接收一个织入表达式，才能表示其方法的具体作用范围

        此处支持**与`&&`或`||`非`!`**

        - `execution([ 切入点表达式 ])`

          其中表达式的格式为

          `访问修饰符 返回值 包名.包名...类名.方法名(参数列表)`

          execution中的通配符

          - `*` 
            - 可以匹配一个或多个字符，不包括`.`，如，MyClass可以使用My*表示。**只能匹配一层路径**
            - 可以匹配任一个参数
          - `..`
            - 可以匹配任意多级目录
            - 可以匹配任意多个参数

          其中可以进行如下省略

          `execution(public void com.tms.test.service.impl.AccountServiceImpl.saveAccount())`

          1. 访问修饰符可以直接省略，**不能使用通配符代替**
          2. 返回值剋以使用通配符表示
          3. 报名可以使用通配符表示任意包，其中有几级就需要几个通配符
          4. 可以使用`..`来省略包名之间的若干个通配符又或是参数列表
          5. 类名和方法都可以使用通配符表示

          在具体的业务开发中，一般用于精确到某个包下的所有类的所有方法，如下`* com.tms.test.service.impl.*.*(..)`

      - `@After`

        在目标方法执行之后执行

      - `@AfterReturning`

        在目标方法正常执行之后

      - `@AfterThrowing`

        在目标方法出现异常之后执行

      ```java
      try{
          @Before
          method.invoke(obj, args);
          @AfterReturning
      }catch(e){
          @AfterThrowing
      }finally{
          @After
      }
      ```

      - `@Around`

        环绕方式，于动态代理中的`InvocationHandler`中的`invoke()`类似，能够任意的控制方法的执行时间

        环绕通知方法中，必须要接收一个参数`ProceedingJoinPoint`，用于实现`invoke()`方法中类似的操作

        `ProceedingJoinPoint`中存在的方法

        - `proceed()` `param ...args` `return Object`

          与代理中的`invoke()`方法相同，代表运行切入点方法

        - `getArgs()` `return Object[]`

          获取参数列表

        - `getSignature()` `return Signature`

          获取切入点签名信息，其中包括切入点方法的各种信息

        此时，方法的返回值就将会作为切入点方法的返回值

   4. 开启基于注解的AOP功能

      需要aop名称空间下的`aspectj-autoproxy`

      - `xmlns:aop="http://www.springframework.org/schema/aop"`
      - `<aop:aspect-autoproxy/>`​其中不需要属性

3. 测试

   同样需要首先获取IOC容器，之后获取的是**被代理类的接口，不是被代理类本身**。

##### 细节

1. 获取的对象实际上就是动态代理获取到的对象`com.sun.proxy.Proxy`。AOP的底层就是动态代理

   存在切面之后，Spring会自动寻找它的目标对象，查找到目标对象之后，为其创建代理对象，存入IOC容器中的也是其代理对象

2. AOP Proxy依然支持通过id获取对象，但是获取出来的对象是接口的对象，不能向下转型

3. **如果目标没有实现接口**，那么从容器中获取到的对象就是本身的类型

   <u>必须先导入增强的AOP相关`jar`包（此处应该是cglib提供的方法）</u>

4. <u>Spring中注解方式的AOP，涉及`@After`注解的时候，存在异常</u>

   按照需求，其应该是在最后进行执行，但是因为Spring中代码的错误，它一直会在`@AfterReturning`或`@AfterThrowing`之前运行

5. Spring对于增强方法格式并不严格，如访问修饰符没有要求，返回值没有要求，但是**方法中的参数列表不能随意添加**

   Spring需要查询参数列表，并为其赋值，如果其中存在无法识别的参数，如`Object test`，就必须告知Spring此参数需要接收什么值

6. 通知方法中，其用于接收的参数类型，会根据其指定的类来决定获取值的范围，如接收的返回值指定为`double`就只会接收返回为`double`的值

7. 环绕通知优于普通的通知方法执行，环绕通知会比普通的通知方式先感受到方法执行，普通的方式会比环绕晚执行，但是必定会在环绕通知的`proceed()`方法之前执行前置方法

   而之后的过程永远都是环绕通知先感受，其他的通知方法后感受到

8. 多个界面的执行顺序，与JavaWeb中的Filter类似，都是通过类的名称进行排序执行

##### 配置补充

1. 在增强方法中获取切入点信息**【不包括返回值】**

   Spring中提供了一个接口`JoinPoint`，在将方法增强到切入点时，<u>会自动向其中添加参数</u>，因此，如果需要获取到切入点方法的具体参数，可以在增强方法中添加参数`JoinPoint`

   - `getArgs()` 获取目标方法的运行时参数列表

   - `getSignature()` 获取切入点签名信息

     - `getName()` `return String`

       获取方法名

     - `getModifiers()` `return int`

       获取方法修饰符，可以使用`Modifier.toString(int)`获取到类型字符串

     - `getDeclaringType()` `return Class`

       获取方法返回值类型

     - `getDeclaringTypeName()` `return String`

       获取方法返回值类型字符串

2. 获取切入点方法返回值

   在方法中添加任意参数，此处最好指定的是Object类型

   需要告知Spring用于接收返回值的参数，需要在具体的注解中，如`@Before`中添加一个属性`returning`，其中接收参数的名称

3. 获取切入点方法的异常

   与获取切入点的返回值类似，在增强方法中添加一个参数，最好是Exception，<u>一般的类中都抛不出Throw异常</u>

   需要告知Spring用于接收异常的参数，需要在具体的注解中，如`@Before`中添加一个属性，`throwing`指定接收的参数名称

4. 抽取可重用的切入点表达式

   在界面中，很多方法的作用范围其实相当，如果每次都需要使用接入点表达式就显得太过繁琐，因此，可以将其中重复出现的切入点表达式进行抽取

   1. 随意声明一个没有返回值的空方法用于设定抽取出的表达式
   2. 在方法上使用`@PointCut`注解，在注解属性值指定`value`的值为抽取出的切入点表达式
   3. 直接在原本使用切入点表达式的地方使用方法名来表示

#### 总结

- 注解

  使用方便，可以快速地实现各项配置

- 配置

  能够实现注解无法实现的部分功能，如配置第三方的`jar`包；信息更加集中，方便管理

<u>重要设置使用xml配置文件，不重要的使用注解的方式</u>

## JdbcTemplate

Spring中提供的数据库连接模块

#### 使用步骤

1. 导入相关`jar`包

   `spring-jdbc`、`spring-orm`、`spring-tx`三个包就是jdbc的主要功能包，剩余的`spring-oxm`、`spring-jms`是用于整合的包

2. 纳入IOC容器管理

   在xml配置文件中，使用`<bean>`将其加入IOC容器管理，其中需要接收一个`DataSource`数据原，可以使用`c3p0`中的`ComboPooledDataSource`作为数据源

3. 对数据库进行CRUD操作

   其中包括`update()``queryForObject()``query()`等方法，可以实现具体的逻辑

   其中的`rowMapper`参数，可以使用`RowMapper`接口的实现类`BeanPropertyRowMapper`，在其构造器中传入接收的类对象（`.class`对象），就可以自动将封装为目标对象

##### 细节

1. 在使用`queryForObject()`方法查询没有结果时，会直接出现异常。可以使用`try-catch`捕获异常，但不操作，直接返回`null`即可

##### 补充知识

1. 具名参数

   类似占位符参数，用于临时替代某些参数，用于之后传入具体的值。但是占位符参数存在一个问题，如果一个SQL语句中占位符参数过多时，会出现不易识别的问题，同时，占位符的替换必须清晰其中各个`?`的编号，才能准确替换

   具名参数就是为了解决占位符参数中存在的这几个具体的问题出现的

   - `:参数名`

     如`INSERT INTO employee(emp_name, salary) VALUES(:empName, :salary)`

   需要使用`NameedParameterJdbcTemplate`类，才能支持具名参数功能。其继承自`JdbcTemplate`类，因此同样在创建时需要传入`DataSource`。

   其中的很多方法，接收的参数数据类型都为`Map<String, Object>`类型，如`update()`。`Map`中的`key`作为具名参数的名，`value`为对应具名参数需要更换的值

   其中还支持将某个对象作为参数进行传入，某些方法中接收的是一个`sqlParameterSource`参数，可以使用其接口的实现类`BeanPropertySqlParameterSource`，在其构造器中可以接收一个具体的类对象，此时，必须 满足对象中的属性名与具名参数的名称一一对应，才可以进行传入

## 事务管理

在JavaEE企业级开发中，为了保证数据的完整性和一致性，必须引入数据库事务的概念，所以，事务管理是企业级应用程序开发中必不可少的技术。

### 事务的必备知识

**事务**就是一组由于逻辑上紧密关联而合并成一个整体（工作单元）的多个数据库操作，这些操作<u>要么都执行，要么都不执行</u>

#### 事务的属性

主要包括四个关键的属性，即ACID

- 原子性

  原子性，即`atomicity`，本意是<u>不可再分</u>，事务的原子性表现为一个事务中涉及到的多个操作在逻辑上缺一不可。事务的原子性要求事务中的所有操作要么都执行，要么都不执行

- 一致性

  一致性，即`consistency`，指的是数据的一致，具体是指<u>所有的数据都处于满足业务规则的一致性状态</u>。其要求一个事务中不管涉及到多少个操作，都必须保证事务执行前数据是正确的，事务执行之后，数据仍然正确。如果一个事务在执行的过程中，其中某一个或某几个操作失败了，则必须将其他所有的操作撤销，将数据恢复到执行之前的状态，这就是**回滚**

- 隔离性

  隔离性，即`isolation`，在应用程序实际运行过程中，事务往往是并发执行的，所以很有可能许多事物同时处理相同的数据，因此每个事务都应该与其他的事务隔离开来，防止数据损坏。隔离原则要求多个事务在<u>并发执行过程中不会互相干扰</u>

- 持久性

  持久性，即`durability`，持久性要求事务执行完成之后，对数据的修改<u>永久性的保存</u>下来，不会因为系统错误或其他意外情况而受到影响，通常情况下，事务对数据的修改应该被写入到持久化存储器中

#### 事务管理的分类

在Spring的事务管理中，主要包括<u>编程式事务管理</u>和<u>声明式事务管理</u>

##### 编程式事务管理

使用的是原生的JDBC API进行事务控制

1. 获取数据库连接`Connection`对象
2. 取消事务的自动提交
3. 执行操作
4. 正常完成操作时手动提交事务
5. 执行失败时回滚事务
6. 关闭相关资源

> 评价
>
> 使用原生的**JDBC API**实现事务管理是所有事务管理方式的基石，同时也是最典型的编程式事务管理。编程式事务管理需要将事务管理代码**嵌入到业务方法中**来控制事务的提交和回滚。在使用编程的方式管理事务时，必须在每个事务操作中包含额外的事务管理代码。相对于**核心业务**而言，事务管理的代码显然属于**非核心业务**，如果多个模块都使用同样模式的代码进行事务管理，显然会造成较大程度的**代码冗余**。

##### 声明式事务控制

大部分情况下，声明式事务比编程式事务管理更好，其可以将事务管理从事务方法中分离出来，以声明的方式来实现事务管理

<u>事务管理代码的**固定模式**作为一种**横切关注点**，可以通过AOP方法模块化，进而借助**Spring AOP框架**实现声明式事务管理</u>

Spring在不同的事务管理API中定义了一个**抽象层**，通过<u>配置</u>的方式使其生效，从而让应用程序开发人员<u>不必了解事务管理API底层的实现细节</u>，就可以使用Spring的事务控制管理机制

<u>只想要告知Spring那个方法是事务方法，其就可以自动的实现事务的控制</u>

编程式事务控制中真正的实现逻辑就是编程式事务管理，但是Spring中的AOP可以很方便的实现需求的方法。同时，Spring也实现了大量的事务控制切面，被命名为事务控制管理器`PlatformTransactionManager`

`PlatformTransactionManager`是Spring内置的一个接口，其下有很多的实现类，可以用于不同的事务控制，如`DataSourceTransactionManager`用于基础的数据库事务控制，`HibernateTransactionManager`用于`Hibernate`中的事务控制

### Spring中的使用方法

#### 基于注解

1. 导入AOP相关`jar`包

   `spring-aspect` `spring-aop`

2. 配置事务管理器（使用xml配置文件）

   在IOC容器中注册事务管理器，让其纳入其管理。如果是基础的数据库事务控制，就将`DataSourceTransactionManager`注册进入数据库

   为其设置参数`dataSource`，将数据源交给事务管理器控制

3. 开启基于注解的事务控制模式

   <u>需要依赖tx的名称空间</u>

   使用`<tx:annotation-driven>`标签开启注解驱动模式，其中会默认寻找`transactionManager`对象，如果注册的事务管理器名称不是默认名称，可以使用`transaction-manager`属性指定具体的某个事务管理器对象

4. 在事务方法上添加注解

   在需要进行事务控制的方法上，直接使用注解`@Transactional`，Spring就会自动实现对其的事务控制

#### 基于xml配置文件

1. 导入AOP相关的`jar`包

2. 配置事务管理器

   以上的配置过程与基于注解的配置方式完全一致

3. 配置事务方法

   需要使用`tx`名称空间和`aop`名称空间

   1. 使用`<tx:advice>`配置事务管理器，其中可以指定`id`属性，用于识别该事务管理器配置。之后，使用`transaction-manager`属性，用于指定所配置的事务管理器。

      其中需要使用内部标签`<tx:attributes>`用于指定具体的事务属性，在`<tx:attributes>`中还需要使用`<tx:method>`用于指明哪些方法是事务方法，切入点表达式是指出需要切入的方法范围，但是具体的方法还需要再此处进行配置。

      在`<tx:method>`中，可以传入方法名，或者直接使用通配符表示，此处主要使用`*`，表示匹配任意数量的任意字符串。

      之后，还可以配置具体的参数，详细配置见**事务控制的补充知识2**

   2. 使用`<aop:config>`标签，创建一个AOP配置空间，其中可以使用`<aop:pointcut>`标签来提取出其中的切入点表达式。

      之后，在内部使用`<aop:advisor>`标签，指定事务增强。其中`advice-ref`属性，指向事务管理器的配置，传入的就是`<tx:advice>`中的`id`属性。

### 补充知识

1. `@Transactional`注解

   在xml配置文件中，配置了Spring提供的事务管理器，之后开启了`<tx:annotation-driven>`事务的扫描之后，在需要进行事务控制的方法上，添加注解即可实现事务的控制

   其中还存在一些更加详细的属性，可以进行更多的配置

   - `isolation` `value Isolation`

     事务的隔离级别

     数据库为了解决某些可能出现的问题，即脏读、不可重复读和幻读问题，提出的四种隔离级别，分别用于解决这三种问题

     具体知识放在数据库的相关知识中（快速跳转）

   - `propagation` `value Propagaion`

     事务的传播行为，指的是事务的传播和事务的行为

     如果有读个事务进行嵌套运行，子事务是否与父事务共用同一个事务

     ```java
     class MyTestService{
         @Transactional
     	void methodA(){
             void methodB();
             void methodC();
         }
         @Transactional
         void methodB(){
             // 方法体
         }
         @Transactional
         void methodC(){
             // 方法体
         }
     }
     ```

     如果此处的`methodB`和`methodC`方法，在`methodA`中如果其中一个出现了异常，另外一个是否会回滚，就是此处的事务传播行为

     当事务方法被另外一个事务方法调用时，需要指定事务方法应该如何传播，也就是此处的`methodB`和`methodC`是否会互相影响

     ![Spring-事务-传播行为](img/Spring-%E4%BA%8B%E5%8A%A1-%E4%BC%A0%E6%92%AD%E8%A1%8C%E4%B8%BA.jpg)

     上图中的7中传播行为，最主要使用的就是`REQUIRED`和`REQUIRES_NEW`

     - `REQUIRED`

       如果已经存在一个现成的事务（包括当前方法的上级方法是事务），就会**使用现有的事务，否则才使用新的事务**

       在本例中，`methodA`就可以被作为这个现成的事务，`methodB`和`methodC`使用的就是`methodA`的事务，如果`methodA`中任意地方出现了异常（包括`methodB`或`methodC`出现了异常）都需要回滚，其`methodA`中的所有方法就多需要回滚

     - `REQUIRES_NEW`

       每个方法都会自己新建一个事务，**不会共用一个事务，如果有现有事务，将其务挂起**

       在本例中，如果`methodA`中出现异常，只要不是在具体的`methodB`或`methodC`方法中，就不会让其回滚，两个方法只需要关心自己的方法中有没有出现异常，单独进行回滚

       在本实例中，如果设置了两个事务都是`REQUIRES_NEW`，那么在运行到`methodB`或`methodC`方法之前，都会将`methodA`事务挂起，先处理`methodB`或`methodC`事务，处理完毕后再继续处理`methodA`事务

       如果此方法中，希望的效果是`methodC`不管出不出异常，都不会影响到`methodB`，可以将`methodB`设置为`REQUIRES_NEW`让其独立出来，`methodC`可以设置为`REQUIRED`；但是不可以反过来设置，因为Spring中的事务控制虽然可以捕获异常，但是最终又会将异常抛出来，如果此处设置`methodC`是`REQUIRES_NEW`而`methodB`是`REQUIRED`，当`methodC`出现异常，会将异常抛出，被`methodA`捕获，因为`methodB`与其共用了一个事务，因此也会回滚

     > 如果事务方法被放在另外一个事务方法中，如果设定`REQUIRED`就会直接使用父级方法中的设置属性，如timeout等设置
     >
     > `REQUIRES_NEW`不存在这个问题

     > ##### **重要**
     >
     > 如果需要使用嵌套的事务方法，其应该处于不同的类下，如果嵌套的方法直接在同一个类下，直接调用的方法，并没有使用spring的代理对象，也就并没有实现事务的控制
     >
     > 通过`@Autowired`标签标注后，获取到的对象是Spring中为我们创建的代理对象，而不是真正的对象本身，其中包括了各种封装，直接写在同一个类下就是直接调用了方法，而根本没有使用到Spring的事务控制
     >
     > 因此，如果是本类中方法的调用，就只会有一个事务（本类方法的调用相当于方法体的复制粘贴）

   - ------

     `noRollbackFor` `value Class[]`

     哪些异常不会进行回滚，接收具体的类数组

   - `noRollbackForClassName` `value String[]`

     哪些异常不会进行回滚，接收具体的全限定类名

   - `rollbackFor` `value Class[]`

     哪些异常需要进行回滚，接收具体的类数组

   - `rollbackForClassName` `value String`

     哪些异常需要进行回滚，接收具体的全限定类名

     > **默认情况下**，只会在遇到运行时异常时回滚，即`RuntimeException`及其子类才会回滚，其他的非运行时异常，如`IOException`就不会进行回滚

   - ------

     `readOnly` `value boolean`

     设置事务是否只读，默认为`false`，设置为`true`代表只读

     可以进行事务的优化，如果都是读取操作，就不需要进行提交操作，可以提高查询的速度

     <u>设置为只读之后，如果方法中出现了数据库的修改操作，会抛出异常</u>

   - `timeout` `value int`

     设置超时，超出时间后自动终止并回滚，接收具体的<u>秒数</u>

     <u>方法运行超时会抛出异常</u>

# 实例项目

### 与JavaWeb整合

Spring与基础的JavaWeb项目整合，即Servlet编写展示层进行整合，持久层使用的是JdbcTemplate来实现

#### 实现步骤

1. 导入相关`jar`包

   - Spring core

     `spring-beans` `spring-core` `spring-context` `spring-expression` `commons-logging`

     为了实现注解的配置，需要额外加上`spring-aop`

   - Spring aop

     基本的AOP包 `spring-aspect`

     增强的AOP包 ，即使目标对象没有实现任何的接口也可以实现动态代理`com.springsource.net.sf.cglib` `com.springsource.org.aopalliance` `com.springsource.org.aspectj.weaver`

   - Spring web

     Spring与JavaWeb项目之间的基础功能包

     `spring-web`

   - JdbcTemplate

     `spring-jdbc`、`spring-orm`、`spring-tx`

   - 其他 

     `mysql-connector-java` mysql连接必须jar包

     `c3p0` 数据库连接池

2. 编写Spring的xml配置文件

   1. 编写数据库连接的配置文件(properties文件)

   2. 导入数据库连接的配置文件

      `<context:property-placeholder location="classpath:配置文件名"/>`

   3. 将连接对象纳入Spring的容器管理

      分别使用`<bean>`标签，将`ComboPooledDataSource`和`JdbcTemplate`加入IOC容器

      在对象创建时，需要设置器初始值，可以使用`<property>`标签，分别使用`name`指定参数名，`value`或`ref`指定参数值

   4. 开启基于注解的扫描

      设置此配置之后，才会在扫描具体的类中的注解

      `<context:component-scan base-package="com.tms.test"/>`

      其中还可以使用`<context:exclude-filter>`和`<context:include-filter>`用于标识排除或只扫描哪些类（使用`<context:include-filter>`需要先关闭默认全部扫描，即`<context:component-scan>`的 `use-default-filter`属性设置为`false`）

3. 编写具体的逻辑代码

4. 添加注解

   - 在需要纳入容器管理的类上添加注解

     `@Controller` `@Service` `@Repository` `@Component`

   - 在需要使用的属性上添加注解

     `@Autowired`使用自动依赖注入的方式，为属性添加值

     `@Qualifier`在自动注入无法获取属性时，指定需要从IOC容器中获取的bean名称

   - 在需要的切面上添加注解

     1. 在类上添加注解

        `@Aspect` Spring会将该类作为切面类对待

     2. 在方法上添加注解

        - 简单切入注解

          `@Before` `@After` `@AfterReturning` `@AfterThrowing`

        - 环绕切入注解

          `@Around`

   - 在需要控制的事务上添加注解

     1. 在xml配置文件中配置事务管理器

        使用`<bean>`中注册`DataSourceTransactionManager`，并在属性中传入连接资源

     2. 在xml配置文件中开启事务的注解扫描

        `<tx:annotation-driven>`并使用`transaction-manager`属性指定具体的事务管理器

     3. 事务控制

        - 使用注解的方式

          对于不太重要的方法，可以推荐使用注解，更加方便快截

          `@Transactional` 将当前方法作为事务，进行控制，只要一个地方出现错误就会进行回滚

        - 使用配置的方式

          对于比较重要的，希望更加直观展示的事务控制，推按使用配置的方式

          使用`<aop:config>`创建一个AOP配置空间，其中使用`<aop:pointcut>`和`<aop:advisor>`分别指定切入点表达式和指定事务管理器配置信息

          切入点管理器配置信息使用`<tx:advice>`进行指定，其中需要使用`<tx:attribute>`指定具体的参数，在参数中需要使用`<tx:method>`指定事务方法

5. 在Serlvet中【特殊】

   Servlet默认情况下，是由`Tomcat`创建，因此就算将其纳入IOC容器管理，最后使用的Servlet对象也是Tomcat中的对象，而不是IOC容器中的对象

   因此，需要自己手动的从IOC容器中获取对象，传递给属性，而不能直接使用`@Autowired`进行自动注入

   可以创建一个工具类，其中创建IOC容器，使用容器的`getBean()`方法来获取对象并返回

   但是，如果直接使用这种方法，IOC容器的销毁并不及时，可能造成内容的泄露，因此，需要实现的目标是在项目启动时创建IOC容器，在项目关闭时销毁容器

   为了实现这样的效果，需要使用监听器，而这个监听器Spring已经提供了现有的实现

   在`web.xml`中，需要配置`ContextLoaderListener`在其中，具体配置如下

   ```xml
   <context-param>
       <param-name>contextConfigLocation</param-name>
       <param-value>classpath:SpringConfig.xml</param-value>
   </context-param>
   
   <listener>
       <!-- 使用前需要导入spring-web包 -->
       <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
   </listener>
   ```

   之后，如果需要获取ioc容器，就可以使用监听器中创建的IOC容器，使用`ContextLoader`类中的`getCurrentWebApplicationContext()`静态方法，用于获取到`WebApplicationContext`对象，而此对象就是继承与`ApplicationContext`也就是说它就是我们所需要使用的IOC容器



# 面试题

#### BeanFactory和ApplicationContext的区别

- BeanFactory

  BeanFactory是一个工厂接口，主要负责的是bean对象的获取或实例过程

  容器中存储的所有单例bean其实都存放在一个`Map<String,Object>`（SingletonObject）中

  BeanFactory是最底层的接口

  > 由此可以拓展
  >
  > Spring中最大的模式就是工厂模式，其中的所有bean创建都是由Spring代替我们进行了创建

- ApplicationContext

  ApplicationContext是一个容器接口，主要用于负责容器的相关功能的实现，可以基于beanFactory之上完成容器的功能

  容器可以从`Map`中获取这些bean，并且AOP和DI的相关实现都在ApplicationContext之下的类中

  ApplicationContext主要是留给程序员之后的拓展的接口

  <u>ApplicationContext是BeanFactory的子接口</u>


