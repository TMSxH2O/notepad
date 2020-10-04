# SpringMVC

由Spring来实现MVC，来简化web开发的框架

在Spring的结构中也有所体现，Spring中的web模块，主要就是此处的SpringMVC

## 简介

- <u>Spring为展现层提供的基于展现层提供的基于MVC涉及理念的优秀的Web框架</u>，是目前最主流的MVC框架之一
- Spring3.0后全面超越了原本的Struts2，成为了最优秀的MVC框架
- SpringMVC通过**一套MVC注解**，让POJO成为了处理请求的控制器，而无需实现任何接口
- **支持REST风格**的URL请求
- 采用了<u>松散耦合</u>可插拔组件结构，比其他MVC框架更具扩展性和灵活性

SpringMVC的业务逻辑

![SpringMVC-流程图](img/SpringMVC-%E6%B5%81%E7%A8%8B%E5%9B%BE.jpg)

## 基础步骤

对于最基础的SpringMVC的使用步骤

1. 导包

   SpringMVC是Spring的web模块，Spring的模块运行必须依赖其核心模块IOC容器

   - Spring核心

     `spring-beans` `spring-core` `spring-context` `spring-expression` `commons-logging`

   - Spring注解

     `spring-aop`

   - Spring web

     `spring-web` `spring-webmvc`

2. 项目准备

   1. 配置视图解析器

      由SpringMVC的业务逻辑可知，其需要一个视图解析器，用于拦截所有请求，并智能派发

      视图解析器本身其实是一个Servlet，因此，应该在`web.xml`中进行配置，用于拦截所有请求

      ```xml
      <servlet>
          <servlet-name>springDispatcherServlet</servlet-name>
          <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
          <init-param>
              <param-name>contextConfigLocation</param-name>
              <param-value>SpringMVC配置文件的位置</param-value>
          </init-param>
          <!-- 加载优先级；值越小优先级越高 -->
          <load-on-startup>1</load-on-startup>
      </servlet>
      <servlet-mapping>
          <servlet-name>springDispatcherServlet</servlet-name>
          <url-pattern>需要进行拦截的网址</url-pattern>
      </servlet-mapping>
      ```

   2. 编写测试界面和跳转界面

      编写一个简单界面，其中使用超链接的方式访问控制器，如果访问成功，就会跳转到成功界面

      成功界面存放到`/WEB-INF/pages`文件夹下，防止用户直接获取

   3. 配置控制器

      编写一个控制器，用于获取请求，并跳转界面

      1. 告知Spring这个是一个控制器，并将其纳入IOC容器管理，使用`@Controller`

         这个注解平时不能随便使用，使用之后，Spring就会将其识别为控制器类，并且其中可以使用一些控制器类才能使用的特殊注解

      2. 添加请求映射

         使用类或方法上添加请求映射`@RequestMapping`，其中使用属性`value`接收一个字符串，作为此类或方法的映射

         如果在类上添加了注解，在方法其中的方法之前就需要先添加类的映射地址，视图解析器找到对应的地址之后，就会调用响应的方法

      3. 设置请求响应行为

         方法的返回值，会作为请求转发的地址使用，此时可以指定请求成功的返回值为`/WEB-INF/pages/success.jsp`。如果请求成功了，就会跳转到`success.jsp`界面

         可以通过配置的方式简化请求转发地址的编写

         在SpringMVC的xml配置文件中，对视图解析器进行配置，让其可以直接实现地址的拼接，避免了每次都需要编写冗长的地址值

         1. 使用`<bean>`标签，将视图解析器`InternalResourceViewResolver`加入IOC容器管理
         2. 配置视图解析器属性，`prefix`属性，前半部分地址，在此处，可以配置为`/WEB-INF/pages`；`suffix`属性，后半部分地址，在此处，可以配置为`.jsp`
         3. 在之后的返回值，就会先交给视图解析器进行拼接，再进行请求转发

3. 测试

   如果项目正常执行的效果

   点击测试界面中的超链接，就会跳转到对应的`@RequestMapping`对应的方法，方法返回值经过视图解析器的请求转发，跳转到了跳转界面

   最后的地址栏依然显示的是超链接请求的地址，即对应的控制器方法，最后的界面显示为`success.jsp`中的内容

## 细节补充

包括了方法中的很多扩展，如获取某个值，值的问题解决等

### 获取值

主要包括了各种方法获取值的方法拓展，用于将请求中的各种信息在方法中获取，并处理

#### 前端配置器的默认配置

如果在`web.xml`中配置视图解析器时，没有指定`<init-param>`，即SpringMVC配置文件的位置，其中存在一个<u>默认值</u>`/WEB-INF/springDispatcherServlet-servlet.xml`

其中的`springDispatcherServlet`就是`<servlet>`标签中的`<servlet-name>`

如果没有指定自定的文件，就会寻找默认的文件，如果找不到就会出现IO异常

#### 拦截路径的配置细节

在`web.xml`中的`<url-pattern>`标签中的配置，存在四种<u>匹配机制</u>

1. 精确匹配

   标识一个具体的路径，其中没有使用任何的通配符来代替，之后的Servlet作用范围也是只针对某一个具体的URL地址

2. 路劲匹配

   以`/`开头，并且以`/*`结尾的字符串，用于路径的匹配

   如`/user/*`表示匹配了`/user`下的所有的路径

3. 扩展名匹配

   以`*.`开头的字符串，用于扩展名匹配

   只会对使用了某些后缀名的请求进行拦截

4. 缺省匹配

   使用`/`表示任意路径匹配

多种匹配同时使用时，会**按照作用范围从小到大的顺序进行匹配**，即精<u>确匹配、路径匹配、扩展名匹配、缺省匹配</u>的顺序进行匹配

> `/`和`/*`的区别
>
> `/`表示的是Servlet中的一种特殊的匹配模式，该模式有且仅有一个实例，优先度最低，不会覆盖任何的`<url-pattern>`设置，只能替换Servlet容器中的default Servlet设置，该模式可以匹配所有的请求，但是会对`*.jsp	`的请求放行
>
> `/*`表示的是路径匹配模式，其优先级仅次于精确匹配，可以覆盖掉所有的拓展名匹配，其会对所有的请求进行拦截，包括所有的静态资源
>
> **大致原理**
>
> tomcat服务器中，存在一个默认的`web.xml`，其他所有的`web.xml`配置都是继承于它
>
> 其中配置了`DefaultServlet`和`JspServlet`，其中`DefaultServlet`作用的`url-pattern`是`/`，而`JspServlet`作用的`url-pattern`是`*.jsp`和`*.jspx`
>
> 其中，`DefaultServlet`是Tomcat中用于处理静态资源，即除了`.jsp`和`.jspx`之外的如`.html`、`.css`等文件
>
> 如果在项目中，使用的作用范围为`/`就就只会覆盖`DefaultServlet`的配置，其中的`JspServlet`就依然可以用于处理`.jsp`文件，不会出现拦截的现象，而如果使用了`/*`就是路径匹配，导致`.jsp`也会交给SpringMVC的前端配置器进行管理，而其默认情况对所有的资源都不放行，因此，都会出现`404`错误

#### 方法的请求映射

SpringMVC中的`@RequestMapping`可以对类或方法指定一个**映射的地址值**，用于视图解析器`DispatcherServlet`来调用

其中不允许出现多个映射同一个请求的情况，即最终的方法地址值相同（如果类上也有映射地址值，最终方法对应的地址值需要添加上对应的地址值，可以用于区分）

`@RequestMapping`中还存在其他属性，用于详细配置

- `value` 【基本属性】用于指定映射地址值

  - 其中还支持更多更加高级的映射使用方式，如`Ant`风格的资源地址

    - `?` 用于匹配一个任意字符
    - `*` 用于匹配任意数量的任意字符
    - `**` 用以匹配多层路径，<u>包括0层</u>

    > 不包括`/`

    如，`/user/*/testAnt`可以匹配`/user/aaa/testAnt`和`/user/abc/testAnt`；`/user/**/testAnt`可以匹配`/user/testAnt`和`/user/aa/bb/testAnt`；`/user/test???`可以匹配`/user/testAnt`和`/user/testaaa`

    使用`Ant`风格，其优先级根据作用的精确性进行排序，越精确越优先

  - 其中，还支持占位符的方式

    在映射地址中，使用`{变量名}`来代替某个字符串，可以用于表示与`*`类似的功能

    可以使用`@PathVariable`来标注某个参数，用于获取此时占位符所替换的值，`@PathVariable`中需要指定其`value`的值为需要获取的占位符变量名

    ```java
    @RequestMapping(value="/user/{id}")
    public String myMethod(@PathVariable("id")String id){
        System.out.println(id);
        return "success";
    }
    ```

- `method` 

  限定请求的方式，如`GET`、`POST`等请求方式`method=RquestMethod.POST`

  默认情况下，所有的请求方式都可以获取。如果设定之后，使用其他的请求方式获取，就会出现405错误`Request method XXX not supported`，不支持某种请求

- `params`

  规定请求的参数，其中接收一个`String[]`，其中还支持一些简单的表达式

  - `params="paramName"` 要求请求中必须含有名称为`paramName`的参数
  - `params="!paramName"` 要求请求中必须没有包含名称为`paramName`的参数
  - `params="paramName!=value"` 要求请求中必须包含`paramName`参数，并且其中的值必须不等于`value`
  - `params={"paramName1=value1", "paramName2"}` 要求请求中必须包含`paramName1`和`paramName2`，且其中的`paramName1`的值必须为`value1`

- `headers`

  规定请求头，和`params`类似，其中依然可以使用简单的表达式

  如，`headers="User-Agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36"`（不支持模糊匹配，必须使用完全匹配）

- `consumes`

  指定接收内容的类型，即请求头中的`Content-Type`

- `produces`

  指定响应返回的内容类型，即想用头中的`Content-Type`

  如`produces="text/html;charset=utf-8"`

#### 获取请求参数

SpringMVC中，此时实现的代码并非是Servlet，而是即将被Servlet调用的方法，因此不能直接在方法上简单接收`HttpServletRequest`的方式来获取参数

SpringMVC中提供了多种方式来获取具体的参数值

1. 默认方式获取请求参数

   在方法中，指定参数用于获取某个参数值，其参数名，就是希望获取到的请求中的参数

   如在参数中指定了`username`，则参数`username`的接收的值就是请求中传入的对应参数值。如果没有传入值，获取的参数值就是<u>默认值</u>

2. 使用注解

   Spring中提供的注解`@RequestParam`，用于获取请求中的参数。其中可以设置属性`value`为希望获取的参数名

   <u>默认情况下，如果使用了`@RequestParam`，请求中就必须携带指定的参数</u>。可以修改其中的`required`的值为`false`表示不是必须携带

   - `value` 获取的参数名
   - `required` 该参数是否必须
   - `defaultValue` 指定该参数的默认值

#### 获取请求头数据

在JavaWeb项目中，通常需要获取请求头数据，如`User-Agent`等来进行后续操作

SpringMVC中，提供了获取请求头的方式。使用`@RequestHeader`标签，设置其中的`value`属性为需要获取的请求头信息。其实现的效果类似JavaWeb项目的Servlet中，使用Request对象的`getHeader()`方法

在方法的中，使用某个参数来接收请求头信息，在参数前添加`@RequestHeader`标签来进行标记

默认情况下，如果获取的请求头中没有对应的值，就会出现异常，其中的属性与获取请求参数中完全一致，即`value`、`required`和`defaultValue`，作用和使用方法也完全相同

##### 使用增强之后

在使用了`<mvc:annotation-driven>`之后，可以在参数中使用`HttpEntity<String>`来接收所有请求头的集合

#### 获取Cookie值

对于基础的JavaWeb的项目中，需要使用`request.getCookies()`来获取所有的Cookie数组，如果需要获取的是某个一个Cookie，就需要循环遍历，并且判断获取对应的值

在SpringMVC中，可以使用`@CookieValue`注解，用于指定参数用于获取Cookie对象。默认情况下，如果没有获取对应的Cookie对象，就会抛出异常

其中的属性与之前的请求头、参数值相同，`value`、`required`和`defaultValue`。

#### 获取参数并封装对象

如果请求参数传入的是一个具体对象的参数，可以使用对象来对数据进行直接封装，极大的简化了操作，不需要分别接收参数，然后创建并封装对象

SpringMVC提供了便捷的封装请求对象的方式，在方法中，指定一个参数，用于获取对象。其类必须满足其中的属性名与其请求参数名对应，其中传入参数的方法是对应的`Setter`方法，其调用的原则依然是`Setter`方法去掉`set`并将之后的首字母小写，作为接收的属性名，调用了对应的`Setter`方法

其中还支持多层注入（级联注入），即类中某个属性是其他的自定义类对象，只要格式正确，也能直接实现注入功能，如类中存在属性`Address address`，其`Address`中又有属性`city`，如果传入的参数名为`address.city`也能自动实现注入的功能

#### 方法使用原生API

对于SpringMVC默认方法，其并非一个真正意义上的Servlet方法，而是将要交由Servlet方法调用的内部方法，因此如果需要使用原本Servlet方法的`HttpServletRequest`、`HttpServletResponse`和`HttpSession`等对象，可以直接在方法上添加参数即可

其中具体包括如下Servlet API

- `HttpServletRequest`
- `HttpServletResponse`
- `HttpSession`
- `java.security.Principal` https有关的安全协议
- `Locale` 国际化有关的区域信息
- `InputStream` 来自`request`对象的`InputStream`字节输入流，`request.getInputStream()`
- `OutputStream` 来自`response`对象的`OutputStream`字节输出流，`response.getOutputStream()`
- `Reader` 来自`request`对象的`Reader`字符输入流，`request.getReader()`
- `Writer` 来自`response`对象的`Writer`字符输出流，`request.getWriter()`

#### 解决中文乱码

根据不同的地方出现的乱码解决各不相同

- 请求乱码

  客户端发送到服务器端的数据出现了乱码

  - GET

    GET请求的参数传入是来自请求的URL中，如果此时出现了乱码，<u>需要修改的Tomcat中的基础配置文件</u>

    `server.xml`文件中，修改`<Connector>`标签处，添加属性`URIEncoding=UTF-8`即可彻底解决

  - POST

    `POST`包括其他http的请求方式，如`DELETE`

    在获取请求参数之前，使用`request.setCharacterEncoding("UTF-8")`，但是在SpringMVC中，输入流的获取等都是其中已经配置完成的设计，并不能使用原生Servlet的方法进行简单的实现，此时就需要使用`Filter`来实现所需要的功能，而SpringMVC提供了所需的Filter实现类`CharacterEncodingFilter`

- 响应乱码

  服务器端返回客户端的数据中出现了乱码

  原生的Serlvet中，可以直接使用`response.setCharacterEncoding("UTF-8")`修改响应中的编码

Spring中提供了实现的拦截器，用于处理编码的转换，具体配置如下

```xml
<filter>
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <!--设置默认参数，将编码修改为utf-8-->
    <init-param>
      <param-name>encoding</param-name>
      <param-value>utf-8</param-value>
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
<filter-mapping>
    <filter-name>characterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

**建议，将此拦截器的`<filter-mapping>`排在第一执行，防止覆盖**

很多其他的Filter中，使用了request中的某些方法，已经获取到了其中的输出流，因此，如果不是第一个执行`CharacterEncodingFilter`，就会失效

### 输出值

主要包括各种数据的输出扩展，用于将数据交给界面展示，此处不包括原生Servlet中的各种方式传递数据，即在参数中获取Response对象，向界面中传递值

#### 设置参数返回数据

Spring中提供了Map、Model以及ModelMap，在方法的参数中使用，可以直接将数据封装，并返回给客户端

指定某个参数，如`Map<String, Object>`用于存放数据，而这样的参数就会被<u>存放在`request`域中</u>

- `Map<String, Object>` 使用`key`作为参数名，`value`就存放的是参数值，一般情况下`key`为String，`value`为Object

- `Model` 在`org.springframework.ui`包下的一个接口，其中可以通过各种方法来进行参数的设置

  - `addAttribute()` `param String, Object` 用于添加一个参数
  - `addAllAttribute()` `param Map<String, Object>` 可以一次性传入多个参数

  其中的参数同样<u>存放在`request`域中</u>

- `ModelMap` 同样在`org.springframework.ui`包下的`Model`的实现类，其中的方法也类似

  最后也会被<u>存放在`request`域中</u>

> 此处的三个参数最后的接收的`org.springframework.validation.support.BindingAwareModelMap`对象
>
> 因此，这三种方式，内部的实现都是一致的，同时`BindingBinAwareModelMap`中保存的所有数据最终都是存放在请求域（`request`域）中
>
> 其中获取到的`BindingBinAwareModelMap`都是同一个对象，即单例

#### 设置返回值返回数据

通过设置方法的返回值，并在返回值中设置返回的数据

使用`ModelAndView`作为方法的返回值，其中包含了很多的方法，用于详细设置返回的效果

- `setViewName()` `param String`

  指定视图名，其中传入的视图名就是最终交给视图解析器的字符串，视图解析器对其进行拼接之后，作为请求转发的URL进行使用

  其作用与方法返回一个String的效果相同

- `addObject()` `param String, Object`

  在对象中添加参数，其中需要指定参数的名称和参数的值

  其中存放的值，最终<u>存放在请求域中</u>

#### 临时将数据存放在Session域

Spring中提供了一种方式将数据存放在Sesson域中的方法，使用`@SessionAttributes`

<u>**此注解只能使用在类上**</u>

- `value`属性

  在`@SessionAttributes`中可以指定属性`value`，其中可以接收一个`String[]`，其中可以指定参数名称，指定过的名称，在其添加请求域中的同时，也会**临时**存放到Session域中

  如`@sessionAttributes("msg")`，在这个控制器类中，如果使用了如Map、Model或者ModelAndView的方式存放的参数，只要其中存放的数据名称是`msg`，就会在复制一份到Session中

- `type`属性

  可以使用`type`属性，指定需要复制的参数类型，如`type=String.class`，只要传递的参数是String类型的数据，就会再复制到Session域中

<u>推荐少使用`@SessionAttributes`，最好使用原生API</u>

### 界面跳转

视图解析器中，没有提及的部分细节操作

#### 其他目录转发

在xml配置文件中，配置过了视图解析器之后，如果希望最终跳转的界面并不是指定的目录，可以使用URL的相对目录的方式实现

- `..` 上级目录，如在配置的`prefix`为`/WEB-INF/pages`的前提下，在方法的返回值处，设置为`../../index.jsp`就可以跳转到根目录下

#### 返回值前缀

SpringMVC中，方法的返回值可以设定特殊的前缀，来进行更多的操作，其中，使用了前缀的字符串，其中**字符串都不会进行拼串操作，且作用在根目录**

- `forward:` 设置当前的结果使用**请求转发**的方式，如`forward:/index.jsp`使用请求转发的方式，到当前项目根目录下的`index.jsp`界面
- `redirect:` 设置当前结果使用**重定向**的方式，同时，<u>相比起原生Servlet中的重定向方法，不需要添加项目名（项目的地址）</u>，如`redirect:/index.jsp`使用重定向的方式跳转到`index.jsp`界面

<u>使用添加前缀的方式，可以实现控制器方法之间的互相转发</u>

这种方式的转发和重定向操作，就可以不经过视图解析器，实现跳转操作

#### 配置请求界面

如果某些请求中，希望实现的是单纯的界面跳转功能，即<u>方法中并没有具体的业务逻辑代码，直接返回一个值</u>，作为视图名进行跳转

SpringMVC中提供了便捷的操作，用于简化此过程，可以使用配置的方式，减少方法的编写

使用`<mvc:view-controller>`标签，相当于一个默认的简单方法，只有返回值，其中有必须属性`path`其中此跳转对应的地址值，还有一个属性`view-name`指定视图名，指定映射的视图

#### 开启mvc增强

开启SpringMVC的注解驱动

`<mvc:annotation-driven>`

### 其他

#### 自定义解析器

需要分别实现ViewResolver和View接口，分别用于实现解析器和视图的具体实现

其中，自定义的视图解析器（ViewResolver）最好实现了`Ordered`接口，用于进行排序，不然默认的`InternalResourceViewResolver`对所有的情况都可以进行默认解析，即自定义的前缀等也会直接创建`IntervalResourceView`的对象进行视图解析

在`Ordered`中的定义的优先级，其数字越小代表优先级越高，`InternalResourceViewResolver`的默认优先级是最低的

#### 自定义类型转换

在对属性对象的输入输出进行格式化的过程中，从其本质上来讲仍然属于**类型转换**的范畴

Spring在格式化模块中定义了一个可以时间的`ConversionService`接口`FormattingConversionService`实现类，该实现类拓展了`GenericConversionService`，因此它<u>既具有类型转换的功能，又具有格式化的功能</u>

`FormattingConversionService`拥有一个`FormattingConversionServiceFactoryBean`工厂类，用于在Spring中构建前者

其中具有`Converter`，即转换器，执行转换功能，如果需要实现自定义的类型转换，就去实现自定义的`Converter<S,T>`接口，其中有两个泛型，需要传入两个数据类型，分别表示转换前的数据类型以及转换后的数据类型

##### 具体步骤

1. 实现`Converter`接口，写一个自定义的类型转换器，其中需要指定两个泛型，分别表示原数据类型以及转换成的数据类型

2. 实现接口的方法`convert`，将参数通过某些逻辑转换为最终所需的数据类型并返回

3. 配置`ConversionService`，将`Converter`加入其中管理

   使用`<bean>`标签，配置`ConversionServiceFactoryBean`工厂，用于创建特定的`ConversionService`对象

   其中，再使用`<property>`内部标签，设置属性`converters`，其中可以设置多个值，使用`<set>`将自定义的`Converter`加入其中即可

4. 告知Spring所使用的`ConversionService`

   在`<mvc:annotation-driven>`标签中，设置其`conversion-service`属性，并指定刚配置的`ConversionService`对象即可

#### 静态资源放行

使用标签`<mvc:default-servlet-handler>`，会对请求的资源进行检测，如果访问的是静态资源就放行，否则交给`DispatcherServlet`处理

但是，可能出现动态资源，即控制器方法等请求无法访问，此时可能需要使用`<mvc:annotation-driven>`标签

- 在使用了`<mvc:default-servlet-handler>`标签之后，默认情况下，将`DispatcherServlet`中的`handlerMapping`属性内部的具体对象进行了替换，所替换的`handlerMapping`将所有的请求交给了`Tomcat`的默认处理，因此，此时的动态资源不能进行访问

#### `annotation-driven`标签

其通过的是`BeanDefinitionParser`的子类`AnnotationDrivenBeanDefinitionParser`，进行解析，其中主要是在检测到该标签时，注册添加了大量的组件，主要注册的有以下三个bean对象

1. `RequestMappingHandlerMapping`
2. `RequestMappingHandlerAdapter`
3. `ExceptionHandlerExceptionResolver`

其标签还可以提供如下支持

- 支持指定`ConversionService`实例，用于对数据进行类型的转换

  详细内容可见<u>静态资源放行</u>中的介绍

- 开启支持使用`@NumberFormat`、`@DateTimeFormat`注解完成数据类型的格式化

  如果希望使用这些标签，就不应该配置自定义类型的格式化转换器，如果希望同时使用自定义类型的格式转换器，就不应该使用`ConversionServiceFactoryBean`来加载，而是使用`FormattingConversionServiceFactoryBean`，其中同时支持格式转换以及格式化的功能

  - `@DateTimeFormat` 其中使用`pattern`属性来指定具体的格式，与`Java`原生的`SimpleDateFormat`中的指定方式类似，如`yyyy-MM-dd`
  - `@NumberFormat` 其中使用`pattern`属性来指定具体的格式，其中的数字用`#`来表示，只能一次代替一位数字

- 开启支持使用`@Valid`注解对JavaBean实例进行`JSR 303`[^1]验证

  前端的界面校验并不安全，对于重要的数据必须要在后端再次进行校验

  1. 配置检测标准

     可以使用，如`Hibernate Validator`来实现，其中存在大量注解，可以通过注解的方式，提示对某个属性的值进行某种检验，以下列出较常用的几个注解

     - `@NotEmpty` 属性非空
     - `@Length` 指定其值的长度，其中可以使用`min`和`max`分别指定其最小和最大长度
     - `@Email` 该属性必须满足邮箱格式
     - `@Past` `@Future` 分别用于检测日期属性，其必须是过去的时间或未来的时间

  2. 在配置完成之后，需要告知Spring在对象封装时需要进行校验，使用`@Valid`注解，添加到需要校验的对象上，如参数对象前添加注解

  3. 如果需要获取数据校验的结果，可以在校验对象之后添加参数`BindingResult`对象，用于对于封装对象校验的结果，**其中不能有任何参数进行分隔**

     在`BindingResult`对象中，可以使用方法

     - `hasErrors()` `return Boolean`

       获取是否有校验错误，`true`表示有错，`false`表示正确

     - `getFieldErrors()` `return List<FieldError>`

       获取所有的错误信息

       其中的`FieldError`类中，存在方法，可以用于获取错误的提示、错误的字段

       - `getDefaultMessage()` 获取信息提示
       - `getField()` 获取出错的字段名

- 开启支持使用`@RequestBody`和`@ResponseBody`注解

  主要用于响应ajax

  - `@RequestBody` 获取请求体中的信息
  - `@ResponseBody` 将返回的数据封装到响应体中

#### 文件下载

文件下载的流程，相比起上传简单很多

首先需要导入`commons-io`包

之后需要获取到文件的输出流，此处主要需要通过请求传入的文件名来识别用户所需的文件

此处，需要指定响应头，可以创建一个`HttpHeader`对象，其中可以使用`set()`方法，设置其响应头`Content-Disposition`以及响应体`attachment;filename=文件名`；或者，也可以使用`setContentDispositionFormData()`方法，直接设置其为`attachment`，并在第二个参数中设置其下载的文件名称

最后，设置方法的返回，使用`ResponseEntity<byte[]>`，需要创建一个其对象，构造器中需要传入三个参数，分别设置响应体内容、响应头内容和响应状态码

- `body` 此处传入的就通过输出流读取到的文件`byte[]`
- `header` 将创建的`HttpHeader`对象传入
- `statusCode` 设置响应的状态码，可以使用`HttpStatus`中的静态属性

#### 文件上传

文件上传的功能，此处，需要区分单文件以及多文件的上传

##### 单文件上传

1. 导包

   `commons-fileupload`和`commons-io`

2. 配置SpringMVC的xml配置文件

   将文件上传的转换器使用`<bean>`标签进行注册，`org.springframework.web.multipart.commons.CommonsMultipartResolver`

   **同时，此处必须注册的对象id值必须为`multipartResolver`否则无法生效**

   其中，可以配置文件上传的具体属性，如`maxUploadSize`文件最大上传大小，`defaultEncoding`文件上传的编码

3. 实现文件上传的功能

   分别实现前端的界面以及后端控制器的代码

   - 前端

     在文件上传处，处理的是多个请求，因此在表单中，需要进行特殊配置

     在`<form>`标签中，设置属性`enctype="multipart/form-data"`，其中的文件上传请求才能正确的被发送

     上传图片用的是`<input>`标签，设置其属性`type="file"`

   - 后端

     此处主要还需要进行配置的是控制器，在其中的方法中，需要配置其中的方法参数

     设置一个参数`MultipartFile`，此处一般使用`@RequestParam`用于指定参数接收的请求参数值

     在`MultipartFile`中，存在方法，可以获取文件的具体信息

     - `getName()` 获取文件的名称

     - `getOriginalFilename()` 获取初始文件的名称

     - `transferTo()` 文件存储方法，其中需要接收一个`File`对象

       此处最好在创建之前，判断目录是否存在，即`file.getParentFile().exists()`方法，判断其目录是否存在，不存在，就创建目录`file.getParentFile().mkdirs()`

       特别注意，此处需要指定的是存储文件的信息，因此需要<u>指定存储的具体目录的以及最终存储的文件名称</u>

###### 多文件上传

大致的流程相同

控制器中的方法参数，使用的是`MultipartFile[]`，之后需要使用循环遍历的方式，来实现对所有文件的存储

#### 拦截器

允许运行目标方法之前，进行一些拦截操作，类似`Filter`，可以为`Filter`的上位版

实现拦截器，主要需要的是实现拦截器的接口，SpringMVC中，拦截器的接口为`HandlerInterceptor`其中主要包括如下方法

- `preHandle()` `param HttpServletRequest, HttpServletResponse, Object` `return boolean`

  在目标方法之前进行调用，可以通过返回值控制方法是否执行

- `postHandle()` `param HttpServletRequest, HttpServletResponse, Object, ModelAndView`

  在目标方法调用之后执行

- `afterCompletion` `param HttpServletRequest, HttpServletResponse, Object, Exception`

  目标相应之后，在请求完成之后，解析页面时

其正常执行的流程，`preHandle`方法、目标方法、`postHandle`方法、解析界面、`afterCompletion`方法

- 如果`preHandle`不放行，之后所有流程不执行
- 如果目标方法异常，`postHandle`方法不执行，但是<u>`afterCompletion`方法依然执行</u>

在实现了具体的拦截器之后，需要将其注册进入Spring中，才能进入管理

在Spring的xml配置文件中，使用`<mvc:interceptors>`标签，声明一个拦截器配置空间，声明其中的所有注册对象为拦截器，其中可以注册多个拦截器，其中有多种方式可以注册拦截器

1. 在该标签内部使用`<bean>`标签来注册拦截器，相当于配置的是默认的拦截器，**默认情况下，可以拦截所有的请求**

2. 在标签的内部使用`<mvc:interceptor>`标签来注册拦截器，其中可以对拦截器进行具体的配置，其中还是需要使用`<bean>`标签表明具体的拦截器

   此外，还可以使用`<mvc:mapping>`标签，表示拦截的具体地址，其中使用`path`属性来指定拦截的地址

#### 异常处理

SpringMVC中有一套处理异常的方案，与JavaWeb类似，都存在发现异常之后的错误页面

通过`HandlerExceptionResolver`处理程序的异常，包括`Handler`映射、数据绑定以及目标方法执行时发生的异常

SpringMVC提供了`HandlerExceptionResolver`的实现类

如果使用了增强标签`<mvc:annotation-driven>`，就可以使用`@ExceptionHandler`注解，用于设置异常方法

- 在异常方法上添加注解`@ExceptionHandler`，其中可以指定需要捕获的异常类型，如`ArithmeticException.class`，其中接收的是数组，可以设定多个异常类型，其会捕获其所有的子类

  该方法可以设置参数`Exception`，用于接收抛出的异常信息，**该方法只能设置此参数**。如果希望将数据存放到`request`域中，可以使用`ModelAndView`作为返回值

  <u>该方法上不应该添加`@RequestMapping`标签</u>

  **可以将所有的异常方法提取出来，集中到某个类中处理**，该类必须添加`@ControllerAdvice`注解，来告知Spring该类中存放的是异常处理方法

  如果其他的控制器中出现了异常，且类中没有可用的异常处理方法，就会寻找这样的异常处理类

- 在异常类上，添加`@ResponseStatus`注解，其中，`result`属性标识错误原因，最终将会在以界面上展示，`value`表示返回的状态码

  <u>虽然可以直接在控制器方法上添加该注解，但是会导致对方法的任何访问，不论是否存在异常都会出现跳转异常界面，因此最好还是标注在自定义的异常类上</u>

- 配置SpringMVC的xml配置文件，在其中注册`SimpleMappingExceptionResolver`对象

  其中需要设置属性`ExceptionMapping`，该属性接收的是一个`Properties`对象，其中使用`<props>`标签，其中使用`<prop>`标签，指定属性`key`标识异常的全限定类名，`value`为跳转的视图名

  其中还有属性`exceptionAttribute`用于指定错误信息的取出对象，默认情况下，错误对象为`exception`

<u>以上的三种方式，其优先顺序从上到下依次下降</u>



## 源码分析

根据其大致的执行流程，进行源码分析

首先，需要分析的是DispatcherServlet，它是用于接收所有的请求，其继承树为`DispatcherServlet`>`FrameworkServlet`>`HttpServletBean`>`HttpServlet`

由JavaWeb中的学习，既然继承了`HttpServlet`其中必然实现了`doGet`和`doPost`方法，其具体的实现在`FrameworkServlet`中，通过调用了其他的方法，进行了转换

```java
/** FrameworkServlet */
@Override
protected final void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
   // 调用了其他的方法，doPost方法于此处相同 
   processRequest(request, response);
}
// 被调用的方法
protected final void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

    long startTime = System.currentTimeMillis();
    Throwable failureCause = null;

    LocaleContext previousLocaleContext = LocaleContextHolder.getLocaleContext();
    LocaleContext localeContext = buildLocaleContext(request);

    RequestAttributes previousAttributes = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes requestAttributes = buildRequestAttributes(request, response, previousAttributes);

    WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
    asyncManager.registerCallableInterceptor(FrameworkServlet.class.getName(), new RequestBindingInterceptor());

    initContextHolders(request, localeContext, requestAttributes);

    try {
        // 使用try专门包括的方法，代表其极其重要
        doService(request, response);
    }
    catch (ServletException | IOException ex) {
        failureCause = ex;
        throw ex;
    }
    catch (Throwable ex) {
        failureCause = ex;
        throw new NestedServletException("Request processing failed", ex);
    }

    finally {
        resetContextHolders(request, previousLocaleContext, previousAttributes);
        if (requestAttributes != null) {
            requestAttributes.requestCompleted();
        }
        logResult(request, response, failureCause, asyncManager);
        publishRequestHandledEvent(request, response, startTime, failureCause);
    }
}
// 在FrameworkServlet中是一个抽象方法，需要其子类即DispatcherServlet中进行实现
protected abstract void doService(HttpServletRequest request, HttpServletResponse response)
    throws Exception;
```

之后就是DispatcherServlet中的主要代码

```java
/* DispatcherServlet */
protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
    this.logRequest(request);
    Map<String, Object> attributesSnapshot = null;
    if (WebUtils.isIncludeRequest(request)) {
        attributesSnapshot = new HashMap();
        Enumeration attrNames = request.getAttributeNames();

        label95:
        while(true) {
            String attrName;
            do {
                if (!attrNames.hasMoreElements()) {
                    break label95;
                }

                attrName = (String)attrNames.nextElement();
            } while(!this.cleanupAfterInclude && !attrName.startsWith("org.springframework.web.servlet"));

            attributesSnapshot.put(attrName, request.getAttribute(attrName));
        }
    }

    request.setAttribute(WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.getWebApplicationContext());
    request.setAttribute(LOCALE_RESOLVER_ATTRIBUTE, this.localeResolver);
    request.setAttribute(THEME_RESOLVER_ATTRIBUTE, this.themeResolver);
    request.setAttribute(THEME_SOURCE_ATTRIBUTE, this.getThemeSource());
    if (this.flashMapManager != null) {
        FlashMap inputFlashMap = this.flashMapManager.retrieveAndUpdate(request, response);
        if (inputFlashMap != null) {
            request.setAttribute(INPUT_FLASH_MAP_ATTRIBUTE, Collections.unmodifiableMap(inputFlashMap));
        }

        request.setAttribute(OUTPUT_FLASH_MAP_ATTRIBUTE, new FlashMap());
        request.setAttribute(FLASH_MAP_MANAGER_ATTRIBUTE, this.flashMapManager);
    }

    try {
        // 专门使用try进行包裹，说明是重要方法
        this.doDispatch(request, response);
    } finally {
        if (!WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted() && attributesSnapshot != null) {
            this.restoreAttributesAfterInclude(request, attributesSnapshot);
        }
    }
}

// 被调用的方法
protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpServletRequest processedRequest = request;
    HandlerExecutionChain mappedHandler = null;
    boolean multipartRequestParsed = false;
    WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);

    try {
        try {
            ModelAndView mv = null;
            Object dispatchException = null;

            try {
                // 检查是否多部件，与文件上传有关，将其封装为另一个请求
                processedRequest = this.checkMultipart(request);
                // 修改是否为文件上传请求，如果是文件请求，此处就不等
                multipartRequestParsed = processedRequest != request;
                // 根据当前请求，决定使用哪个处理器进行处理，同时还能获取到其中的执行链
                // 获取到的内容中，包含拦截器
                mappedHandler = this.getHandler(processedRequest);
                if (mappedHandler == null) {// 如果没有找到处理器可以处理当前请求
                    this.noHandlerFound(processedRequest, response);
                    return;
                }
				// 拿到可以执行类中方法的适配器，即反射工具
                HandlerAdapter ha = this.getHandlerAdapter(mappedHandler.getHandler());
                String method = request.getMethod();
                boolean isGet = "GET".equals(method);
                if (isGet || "HEAD".equals(method)) {
                    long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
                    if ((new ServletWebRequest(request, response)).checkNotModified(lastModified) && isGet) {
                        return;
                    }
                }
				// 执行所有拦截器的preHandle方法
                if (!mappedHandler.applyPreHandle(processedRequest, response)) {
                    return;
                }

                // 执行了处理器
                // 通过适配器来执行处理器中的方法，最终返回一个ModelAndView
                mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
                if (asyncManager.isConcurrentHandlingStarted()) {
                    // 判断当前方法是否异步，是就直接放行
                    return;
                }
				// 如果没有视图名，就使用默认的视图名
                this.applyDefaultViewName(processedRequest, mv);
                mappedHandler.applyPostHandle(processedRequest, response, mv);
            } catch (Exception var20) {
                dispatchException = var20;
            } catch (Throwable var21) {
                dispatchException = new NestedServletException("Handler dispatch failed", var21);
            }
			// 界面实现了请求转发
            // 根据方法最终执行封装的ModelAndView转发到对应的页面
            this.processDispatchResult(processedRequest, response, mappedHandler, mv, (Exception)dispatchException);
        } catch (Exception var22) {
            this.triggerAfterCompletion(processedRequest, response, mappedHandler, var22);
        } catch (Throwable var23) {
            this.triggerAfterCompletion(processedRequest, response, mappedHandler, new NestedServletException("Handler processing failed", var23));
        }

    } finally {
        if (asyncManager.isConcurrentHandlingStarted()) {
            if (mappedHandler != null) {
                mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
            }
        } else if (multipartRequestParsed) {
            this.cleanupMultipart(processedRequest);
        }

    }
}
```

其中的实现了两个阶段，分别是调用了方法以及实现了页面的跳转

> 小细节
>
> 1. getHandler方法
>
>    通过调用此方法获取到的是一个处理器，而实际获取到的是可以用于处理该类的处理链
>
>    在传入过来的request中（`processedRequest`内部存在属性`requestDispatcherPath`，是包装的请求标识，在此例中，是`/helloworld`）
>
>    ```java
>    /* DispatcherServlet */
>    @Nullable
>    protected HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
>       if (this.handlerMappings != null) {
>           // 遍历处理器映射，其中存放了已有的所有处理器的具体信息用于判断是否适用于此状况
>          for (HandlerMapping mapping : this.handlerMappings) {
>             HandlerExecutionChain handler = mapping.getHandler(request);
>             if (handler != null) {
>                return handler;
>             }
>          }
>       }
>       return null;
>    }
>    ```
>
>    其中的`handlerMapping`存放的各个处理器可以处理的请求数据，在IOC容器创建时就被存放到`handlerMapping`中的`handlerMap`中，此时只需要遍历扫描一次是否可以处理即可
>
> 2. getHandlerAdapter方法
>
>    在获取到了处理器之后，还需要获取到处理器对应的适配器，用于执行目标的方法
>
>    其中的运行逻辑基本上与获取处理器的方法相同
>
>    ```
>    /* DispatcherServlet */
>    protected HandlerAdapter getHandlerAdapter(Object handler) throws ServletException {
>       if (this.handlerAdapters != null) {
>          for (HandlerAdapter adapter : this.handlerAdapters) {
>             // 判断当前的适配器是否可以用于处理器
>             if (adapter.supports(handler)) {
>                return adapter;
>             }
>          }
>       }
>       throw new ServletException("No adapter for handler [" + handler +
>             "]: The DispatcherServlet configuration needs to include a HandlerAdapter that supports this handler");
>    }
>    ```
>
>    ```java
>    /* HttpRequestHandlerAdapter */
>    @Override
>    public boolean supports(Object handler) {
>        // 判断其是否实现了HttpRequestHandler接口
>       return (handler instanceof HttpRequestHandler);
>    }
>    ```
>
> 3. SpringMVC的九大组件
>
>    DispatcherServlet中的几个引用类型的属性，就是SpringMVC的九大组件
>
>    SpringMVC在工作时，关联的位置都是交由组件进行完成
>
>    ```java
>    // 多部件解析器，与文件上传有关
>    private MultipartResolver multipartResolver;
>    // 区域信息解析器，与国际化有关
>    private LocaleResolver localeResolver;
>    // 主题解析器，与主题有关，主题更换
>    private ThemeResolver themeResolver;
>    // 处理器映射信息
>    private List<HandlerMapping> handlerMappings;
>    // 处理器适配器信息
>    private List<HandlerAdapter> handlerAdapters;
>    // 处理器异常解析器
>    private List<HandlerExceptionResolver> handlerExceptionResolvers;
>    // 请求到视图的转换器
>    private RequestToViewNameTranslator viewNameTranslator;
>    // FlashMap的管理器，允许SpringMVC中允许重定向携带数据的功能
>    private FlashMapManager flashMapManager;
>    // 视图解析器
>    private List<ViewResolver> viewResolvers;
>    ```
>
>    所有的组件都是接口，表示的是一种标准或规范，提供了强大的拓展性
>
> 4. 内部属性的注入
>
>    在DispatcehrServlet中，实现了`onRefresh`方法（Spring的IOC容器创建时允许的拓展方法）
>
>    ```java
>    /* DispatcherServlet */
>    @Override
>    protected void onRefresh(ApplicationContext context) {
>        // 初始化SpringMVC的九大组件
>       initStrategies(context);
>    }
>    
>    // 初始策略方法，分别对九大组件进行了初始化
>    protected void initStrategies(ApplicationContext context) {
>        initMultipartResolver(context);
>        initLocaleResolver(context);
>        initThemeResolver(context);
>        initHandlerMappings(context);
>        initHandlerAdapters(context);
>        initHandlerExceptionResolvers(context);
>        initRequestToViewNameTranslator(context);
>        initViewResolvers(context);
>        initFlashMapManager(context);
>    }
>    
>    // 初始处理器信息
>    private void initHandlerMappings(ApplicationContext context) {
>        this.handlerMappings = null;
>    
>        if (this.detectAllHandlerMappings) {
>            // Find all HandlerMappings in the ApplicationContext, including ancestor contexts.
>            Map<String, HandlerMapping> matchingBeans =
>                BeanFactoryUtils.beansOfTypeIncludingAncestors(context, HandlerMapping.class, true, false);
>            if (!matchingBeans.isEmpty()) {
>                this.handlerMappings = new ArrayList<>(matchingBeans.values());
>                // We keep HandlerMappings in sorted order.
>                AnnotationAwareOrderComparator.sort(this.handlerMappings);
>            }
>        }
>        else {
>            try {
>                HandlerMapping hm = context.getBean(HANDLER_MAPPING_BEAN_NAME, HandlerMapping.class);
>                this.handlerMappings = Collections.singletonList(hm);
>            }
>            catch (NoSuchBeanDefinitionException ex) {
>                // Ignore, we'll add a default HandlerMapping later.
>            }
>        }
>    
>        // Ensure we have at least one HandlerMapping, by registering
>        // a default HandlerMapping if no other mappings are found.
>        if (this.handlerMappings == null) {
>            this.handlerMappings = getDefaultStrategies(context, HandlerMapping.class);
>            if (logger.isTraceEnabled()) {
>                logger.trace("No HandlerMappings declared for servlet '" + getServletName() +
>                             "': using default strategies from DispatcherServlet.properties");
>            }
>        }
>    }
>    ```
>
>    

#### 调用方法

调用处理器，其实是调用了`AbstractHandlerMethodAdapter`中的对应方法

```java
/* AbstractHandlerMethodAdapter */
@Override
@Nullable
public final ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
   
   return handleInternal(request, response, (HandlerMethod) handler);
}
```

其中调用的方法是其实现类`RequestMappingHandlerAdapter`中的具体方法

```java
/* RequestMappingHandlerAdapter */
@Override
protected ModelAndView handleInternal(HttpServletRequest request,
      HttpServletResponse response, HandlerMethod handlerMethod) throws 	Exception {

   ModelAndView mav;
   checkRequest(request);

   // Execute invokeHandlerMethod in synchronized block if required.
   if (this.synchronizeOnSession) {
      HttpSession session = request.getSession(false);
      if (session != null) {
         Object mutex = WebUtils.getSessionMutex(session);
         synchronized (mutex) {
            mav = invokeHandlerMethod(request, response, handlerMethod);
         }
      }
      else {
         // No HttpSession available -> no mutex necessary
         mav = invokeHandlerMethod(request, response, handlerMethod);
      }
   }
   else {
      // 实现了具体的方法，并返回一个ModelAndView
      mav = invokeHandlerMethod(request, response, handlerMethod);
   }

   if (!response.containsHeader(HEADER_CACHE_CONTROL)) {
      if (getSessionAttributesHandler(handlerMethod).hasSessionAttributes()) {
         applyCacheSeconds(response, this.cacheSecondsForSessionAttributeHandlers);
      }
      else {
         prepareResponse(response);
      }
   }
   return mav;
}

// 用于调用控制器中的方法的方法
@Nullable
protected ModelAndView invokeHandlerMethod(HttpServletRequest request,
	HttpServletResponse response, HandlerMethod handlerMethod) throws 	Exception {

    ServletWebRequest webRequest = new ServletWebRequest(request, response);
    try {
        // 获取对象的工厂
        WebDataBinderFactory binderFactory = getDataBinderFactory(handlerMethod);
        ModelFactory modelFactory = getModelFactory(handlerMethod, binderFactory);
		// 反射方式获取到的方法
        ServletInvocableHandlerMethod invocableMethod = createInvocableHandlerMethod(handlerMethod);
        if (this.argumentResolvers != null) {// 方法参数非空
            invocableMethod.setHandlerMethodArgumentResolvers(this.argumentResolvers);
        }
        if (this.returnValueHandlers != null) {
            invocableMethod.setHandlerMethodReturnValueHandlers(this.returnValueHandlers);
        }
        invocableMethod.setDataBinderFactory(binderFactory);
        invocableMethod.setParameterNameDiscoverer(this.parameterNameDiscoverer);

        ModelAndViewContainer mavContainer = new ModelAndViewContainer();
        mavContainer.addAllAttributes(RequestContextUtils.getInputFlashMap(request));
        modelFactory.initModel(webRequest, mavContainer, invocableMethod);
        mavContainer.setIgnoreDefaultModelOnRedirect(this.ignoreDefaultModelOnRedirect);

        AsyncWebRequest asyncWebRequest = WebAsyncUtils.createAsyncWebRequest(request, response);
        asyncWebRequest.setTimeout(this.asyncRequestTimeout);

        WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
        asyncManager.setTaskExecutor(this.taskExecutor);
        asyncManager.setAsyncWebRequest(asyncWebRequest);
        asyncManager.registerCallableInterceptors(this.callableInterceptors);
        asyncManager.registerDeferredResultInterceptors(this.deferredResultInterceptors);

        if (asyncManager.hasConcurrentResult()) {
            Object result = asyncManager.getConcurrentResult();
            mavContainer = (ModelAndViewContainer) asyncManager.getConcurrentResultContext()[0];
            asyncManager.clearConcurrentResult();
            LogFormatUtils.traceDebug(logger, traceOn -> {
                String formatted = LogFormatUtils.formatValue(result, !traceOn);
                return "Resume with async result [" + formatted + "]";
            });
            invocableMethod = invocableMethod.wrapConcurrentResult(result);
        }

        // 执行了方法
        invocableMethod.invokeAndHandle(webRequest, mavContainer);
        if (asyncManager.isConcurrentHandlingStarted()) {
            return null;
        }

        return getModelAndView(mavContainer, modelFactory, webRequest);
    }
    finally {
        webRequest.requestCompleted();
    }
}
```

其中调用的是

```java
/* ServletInvocableHandlerMethod */
public void invokeAndHandle(ServletWebRequest webRequest, ModelAndViewContainer mavContainer,
      Object... providedArgs) throws Exception {
   // 进行了方法调用，并接收返回值	
   Object returnValue = invokeForRequest(webRequest, mavContainer, providedArgs);
   setResponseStatus(webRequest);

   if (returnValue == null) {
      if (isRequestNotModified(webRequest) || getResponseStatus() != null || mavContainer.isRequestHandled()) {
         disableContentCachingIfNecessary(webRequest);
         mavContainer.setRequestHandled(true);
         return;
      }
   }
   else if (StringUtils.hasText(getResponseStatusReason())) {
      mavContainer.setRequestHandled(true);
      return;
   }

   mavContainer.setRequestHandled(false);
   Assert.state(this.returnValueHandlers != null, "No return value handlers");
   try {
      this.returnValueHandlers.handleReturnValue(
            returnValue, getReturnValueType(returnValue), mavContainer, webRequest);
   }
   catch (Exception ex) {
      if (logger.isTraceEnabled()) {
         logger.trace(formatErrorForReturnValue(returnValue), ex);
      }
      throw ex;
   }
}
```

```java
/* InvocableHandlerMethod */
// 方法执行具体方法
@Nullable
public Object invokeForRequest(NativeWebRequest request, @Nullable ModelAndViewContainer mavContainer,
  Object... providedArgs) throws Exception {

    Object[] args = getMethodArgumentValues(request, mavContainer, providedArgs);
    if (logger.isTraceEnabled()) {
        logger.trace("Arguments: " + Arrays.toString(args));
    }
    return doInvoke(args);
}
// 获取方法参数
protected Object[] getMethodArgumentValues(NativeWebRequest request, @Nullable ModelAndViewContainer mavContainer,
  Object... providedArgs) throws Exception {

    MethodParameter[] parameters = getMethodParameters();
    if (ObjectUtils.isEmpty(parameters)) {
        return EMPTY_ARGS;
    }

    Object[] args = new Object[parameters.length];
    for (int i = 0; i < parameters.length; i++) {
        MethodParameter parameter = parameters[i];
        parameter.initParameterNameDiscovery(this.parameterNameDiscoverer);
        args[i] = findProvidedArgument(parameter, providedArgs);
        if (args[i] != null) {
            continue;
        }
        if (!this.resolvers.supportsParameter(parameter)) {
            throw new IllegalStateException(formatArgumentError(parameter, "No suitable resolver"));
        }
        try {
            args[i] = this.resolvers.resolveArgument(parameter, mavContainer, request, this.dataBinderFactory);
        }
        catch (Exception ex) {
            // Leave stack trace for later, exception may actually be resolved and handled...
            if (logger.isDebugEnabled()) {
                String exMsg = ex.getMessage();
                if (exMsg != null && !exMsg.contains(parameter.getExecutable().toGenericString())) {
                    logger.debug(formatArgumentError(parameter, exMsg));
                }
            }
            throw ex;
        }
    }
    return args;
}
// 方法的执行
@Nullable
protected Object doInvoke(Object... args) throws Exception {
    ReflectionUtils.makeAccessible(getBridgedMethod());
    try {
        // 利用反射获取到了方法对象，进行执行，其中传入的是bean对象和获取到的参数
        return getBridgedMethod().invoke(getBean(), args);
    }
    catch (IllegalArgumentException ex) {
        assertTargetBean(getBridgedMethod(), getBean(), args);
        String text = (ex.getMessage() != null ? ex.getMessage() : "Illegal argument");
        throw new IllegalStateException(formatInvokeError(text, args), ex);
    }
    catch (InvocationTargetException ex) {
        // Unwrap for HandlerExceptionResolvers ...
        Throwable targetException = ex.getTargetException();
        if (targetException instanceof RuntimeException) {
            throw (RuntimeException) targetException;
        }
        else if (targetException instanceof Error) {
            throw (Error) targetException;
        }
        else if (targetException instanceof Exception) {
            throw (Exception) targetException;
        }
        else {
            throw new IllegalStateException(formatInvokeError("Invocation failure", args), targetException);
        }
    }
}
```

#### 界面跳转

```java
/* DispatcherServlet */
private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,
      @Nullable HandlerExecutionChain mappedHandler, @Nullable ModelAndView mv,
      @Nullable Exception exception) throws Exception {

   boolean errorView = false;

   if (exception != null) {
      if (exception instanceof ModelAndViewDefiningException) {
         logger.debug("ModelAndViewDefiningException encountered", exception);
         mv = ((ModelAndViewDefiningException) exception).getModelAndView();
      }
      else {
         Object handler = (mappedHandler != null ? mappedHandler.getHandler() : null);
         mv = processHandlerException(request, response, handler, exception);
         errorView = (mv != null);
      }
   }

   // Did the handler return a view to render?
    // 读取ModelAndView，并实现请求转发
   if (mv != null && !mv.wasCleared()) {
       // 渲染方法，将ModelAndView以及request和response对象都传入其中
      render(mv, request, response);
      if (errorView) {
         WebUtils.clearErrorRequestAttributes(request);
      }
   }
   else {
      if (logger.isTraceEnabled()) {
         logger.trace("No view rendering, null ModelAndView returned.");
      }
   }

   if (WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted()) {
      // Concurrent handling started during a forward
      return;
   }

   if (mappedHandler != null) {
      // Exception (if any) is already handled..
      mappedHandler.triggerAfterCompletion(request, response, null);
   }
}

// 读取ModelAndView方法
protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // Determine locale for request and apply it to the response.
    Locale locale =
        (this.localeResolver != null ? this.localeResolver.resolveLocale(request) : request.getLocale());
    response.setLocale(locale);
	// View与ViewResolver（SpringMVC九大组件之一）关系，ViewResolver中存在唯一方法用于获取View
    View view;
    String viewName = mv.getViewName();
    if (viewName != null) {
        // ViewResolver接口中定义的方法，用于获取View对象
        // 其中使用循环遍历，获取所有配置过的视图解析器
        // 如果其中为空，默认使用的就是InternalResourceViewResolver
        view = resolveViewName(viewName, mv.getModelInternal(), locale, request);
        if (view == null) {
            throw new ServletException("Could not resolve view with name '" + mv.getViewName() +
                                       "' in servlet with name '" + getServletName() + "'");
        }
    }
    else {
        // No need to lookup: the ModelAndView object contains the actual View object.
        view = mv.getView();
        if (view == null) {
            throw new ServletException("ModelAndView [" + mv + "] neither contains a view name nor a " +
                                       "View object in servlet with name '" + getServletName() + "'");
        }
    }

    // Delegate to the View object for rendering.
    if (logger.isTraceEnabled()) {
        logger.trace("Rendering view [" + view + "] ");
    }
    try {
        if (mv.getStatus() != null) {
            response.setStatus(mv.getStatus().value());
        }
        // 跳转界面
        view.render(mv.getModelInternal(), request, response);
    }
    catch (Exception ex) {
        if (logger.isDebugEnabled()) {
            logger.debug("Error rendering view [" + view + "]", ex);
        }
        throw ex;
    }
}
```

其中主要包括两步，获取View对象和跳转界面，跳转界面的方法是AbstractView（抽象类）中的方法

##### 获取View

```java
/* DispatcherServlet */
@Nullable
protected View resolveViewName(String viewName, @Nullable Map<String, Object> model,
      Locale locale, HttpServletRequest request) throws Exception {

   if (this.viewResolvers != null) {
      for (ViewResolver viewResolver : this.viewResolvers) {
          // 根据视图名，创建视图对象View
         View view = viewResolver.resolveViewName(viewName, locale);
         if (view != null) {
            return view;
         }
      }
   }
   return null;
}
```

其中的具体方法调用的是`AbstractCachingViewResolver`中的方法

```java
/* AbstractCachingViewResolver */
@Override
@Nullable
public View resolveViewName(String viewName, Locale locale) throws Exception {
   if (!isCache()) {
      return createView(viewName, locale);
   }
   else {
      Object cacheKey = getCacheKey(viewName, locale);
       // viewAccessCache试图通道缓存，用于存放使用过的视图解析器
      View view = this.viewAccessCache.get(cacheKey);
      if (view == null) {
         synchronized (this.viewCreationCache) {
            view = this.viewCreationCache.get(cacheKey);
            if (view == null) {
               // 如果缓存中没有视图解析器，创建View对象
               view = createView(viewName, locale);
               if (view == null && this.cacheUnresolved) {
                  view = UNRESOLVED_VIEW;
               }
               if (view != null && this.cacheFilter.filter(view, viewName, locale)) {
                  this.viewAccessCache.put(cacheKey, view);
                  this.viewCreationCache.put(cacheKey, view);
               }
            }
         }
      }
      else {
         if (logger.isTraceEnabled()) {
            logger.trace(formatKey(cacheKey) + "served from cache");
         }
      }
      return (view != UNRESOLVED_VIEW ? view : null);
   }
}
```

其中创建视图的方法使用的是`UrlBasedViewResolver`中的方法

```java
@Override
protected View createView(String viewName, Locale locale) throws Exception {
   // If this resolver is not supposed to handle the given view,
   // return null to pass on to the next resolver in the chain.
   if (!canHandle(viewName, locale)) {
      return null;
   }

   // Check for special "redirect:" prefix.
    // 使用了前缀，且是重定向时，创建的时RedirectView对象
   if (viewName.startsWith(REDIRECT_URL_PREFIX)) {
      String redirectUrl = viewName.substring(REDIRECT_URL_PREFIX.length());
      RedirectView view = new RedirectView(redirectUrl,
            isRedirectContextRelative(), isRedirectHttp10Compatible());
      String[] hosts = getRedirectHosts();
      if (hosts != null) {
         view.setHosts(hosts);
      }
      return applyLifecycleMethods(REDIRECT_URL_PREFIX, view);
   }

   // Check for special "forward:" prefix.
    // 使用了前缀，且是请求转发时，创建的是InternalResolverView对象
   if (viewName.startsWith(FORWARD_URL_PREFIX)) {
      String forwardUrl = viewName.substring(FORWARD_URL_PREFIX.length());
      InternalResourceView view = new InternalResourceView(forwardUrl);
      return applyLifecycleMethods(FORWARD_URL_PREFIX, view);
   }

   // 使用其父类（AbstractCachingViewResolver中的方法
   return super.createView(viewName, locale);
}
```

```java
/* AbstractCachingViewResolver */
@Nullable
protected View createView(String viewName, Locale locale) throws Exception {
   return loadView(viewName, locale);
}
```

```java
/* UrlBasedViewResolver */
@Override
protected View loadView(String viewName, Locale locale) throws Exception {
   AbstractUrlBasedView view = buildView(viewName);
   View result = applyLifecycleMethods(viewName, view);
   return (view.checkResource(locale) ? result : null);// 判断恒等于true
}
// 获取最终返回的View对象
protected View applyLifecycleMethods(String viewName, AbstractUrlBasedView view) {
    ApplicationContext context = getApplicationContext();
    if (context != null) {
        Object initialized = context.getAutowireCapableBeanFactory().initializeBean(view, viewName);
        if (initialized instanceof View) {
            return (View) initialized;
        }
    }
    return view;
}
```

##### 渲染界面

使用了View对象，用于渲染界面

```java
/* AbstractView */
@Override
public void render(@Nullable Map<String, ?> model, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

   if (logger.isDebugEnabled()) {
      logger.debug("View " + formatViewName() +
            ", model " + (model != null ? model : Collections.emptyMap()) +
            (this.staticAttributes.isEmpty() ? "" : ", static attributes " + this.staticAttributes));
   }

   Map<String, Object> mergedModel = createMergedOutputModel(model, request, response);
   prepareResponse(request, response);
    // 跳转界面方法
   renderMergedOutputModel(mergedModel, getRequestToExpose(request), response);
}

// 对请求进行封装的方法
protected HttpServletRequest getRequestToExpose(HttpServletRequest originalRequest) {
    if (this.exposeContextBeansAsAttributes || this.exposedContextBeanNames != null) {
        WebApplicationContext wac = getWebApplicationContext();
        Assert.state(wac != null, "No WebApplicationContext");
        return new ContextExposingHttpServletRequest(originalRequest, wac, this.exposedContextBeanNames);
    }
    // 最初始的请求方式（传入的参数，不进行处理）
    return originalRequest;
}
```

其中的界面跳转调用的是视图解析器`InternalResourceView`中的具体方法（`InternalResourceView`继承了`AbstractView`，因此其中使用了部分抽象类中的实现方法）

```java
/* InternalResourceView */
@Override
protected void renderMergedOutputModel(
      Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

   // Expose the model object as request attributes.
   exposeModelAsRequestAttributes(model, request);

   // Expose helpers as request attributes, if any.
   exposeHelpers(request);

   // Determine the path for the request dispatcher.
   String dispatcherPath = prepareForRendering(request, response);

   // Obtain a RequestDispatcher for the target resource (typically a JSP).
   RequestDispatcher rd = getRequestDispatcher(request, dispatcherPath);
   if (rd == null) {
      throw new ServletException("Could not get RequestDispatcher for [" + getUrl() +
            "]: Check that the corresponding file exists within your web application archive!");
   }

   // If already included or response already committed, perform include, else forward.
   if (useInclude(request, response)) {
      response.setContentType(getContentType());
      if (logger.isDebugEnabled()) {
         logger.debug("Including [" + getUrl() + "]");
      }
      rd.include(request, response);
   }

   else {
      // Note: The forwarded resource is supposed to determine the content type itself.
      if (logger.isDebugEnabled()) {
         logger.debug("Forwarding to [" + getUrl() + "]");
      }
      rd.forward(request, response);
   }
}
```

通过分析界面跳转的具体过程，可知，<u>视图解析器主要是用于获取视图，而真正实现界面跳转的是视图</u>

#### 拦截器

对其分析，需要按照其运行的流程进行分析

##### preHandle

经过`DispatcherServlet`中的`doDispatch`方法，其中使用了

```java
/* DispatcherServlet.doDispatch() */
// 执行了所有的预处理方法
if (!mappedHandler.applyPreHandle(processedRequest, response)) {
   return;
}
```

```java
/* HandlerExecutionChain */
boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // 获取处理链中的所有的拦截器
   HandlerInterceptor[] interceptors = getInterceptors();
   if (!ObjectUtils.isEmpty(interceptors)) {
      for (int i = 0; i < interceptors.length; i++) {
         HandlerInterceptor interceptor = interceptors[i];
          // 执行preHandle方法，并将其返回值作为判定
         if (!interceptor.preHandle(request, response, this.handler)) {
             // 如果执行失败，直接就执行afterCompletion方法
            triggerAfterCompletion(request, response, null);
             // 只要有一个拦截器返回了false就返回false
             // 就会停止目标方法的执行
            return false;
         }
          // 将所有放行的拦截器进行保存
         this.interceptorIndex = i;
      }
   }
   return true;
}
```

##### postHandle

在`DispatcherServlet`中的`doDispatch`方法中，如果所有的拦截器中`preHandle`方法都是放行，就会继续向下执行，执行目标方法，之后开始执行`postHandle`方法

```java
/* DispatcherServlet.doDispatch */
// 执行所有的postHandle方法
mappedHandler.applyPostHandle(processedRequest, response, mv);
```

```java
/* HandlerExecutionChain */
void applyPostHandle(HttpServletRequest request, HttpServletResponse response, @Nullable ModelAndView mv)
      throws Exception {
	// 获取所有的拦截器
   HandlerInterceptor[] interceptors = getInterceptors();
   if (!ObjectUtils.isEmpty(interceptors)) {
       // 逆序
      for (int i = interceptors.length - 1; i >= 0; i--) {
         HandlerInterceptor interceptor = interceptors[i];
          // 依次执行所有的postHandle方法
         interceptor.postHandle(request, response, this.handler, mv);
      }
   }
}
```

##### afterCompletion

页面渲染时进行执行的方法，其在`DispatcherServlet`的`doDispatch`方法中，最终的`try-catch-finally`中，被封装到其`finally`中，因此如果之前的`prehandle`放行，就一定会执行到此方法

正常情况下，即放行的情况下，其在页面的渲染完成之后，就会执行

```java
/* DispatcherServlet */
	// 调用方法来处理doDispatch方法的结果
	processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
// 所有的异常情况捕获之后都会执行afterCompletion方法
catch (Exception ex) {
    triggerAfterCompletion(processedRequest, response, mappedHandler, ex);
}
catch (Throwable err) {
    triggerAfterCompletion(processedRequest, response, mappedHandler,
                           new NestedServletException("Handler processing failed", err));
}
finally {
   ...
}

// 处理结果的方法，其中执行了afterCompletion方法
private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,
			@Nullable HandlerExecutionChain mappedHandler, @Nullable ModelAndView mv,
			@Nullable Exception exception) throws Exception {

    boolean errorView = false;

    if (exception != null) {
        if (exception instanceof ModelAndViewDefiningException) {
            logger.debug("ModelAndViewDefiningException encountered", exception);
            mv = ((ModelAndViewDefiningException) exception).getModelAndView();
        }
        else {
            Object handler = (mappedHandler != null ? mappedHandler.getHandler() : null);
            mv = processHandlerException(request, response, handler, exception);
            errorView = (mv != null);
        }
    }

    // Did the handler return a view to render?
    if (mv != null && !mv.wasCleared()) {
        render(mv, request, response);
        if (errorView) {
            WebUtils.clearErrorRequestAttributes(request);
        }
    }
    else {
        if (logger.isTraceEnabled()) {
            logger.trace("No view rendering, null ModelAndView returned.");
        }
    }

    if (WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted()) {
        // Concurrent handling started during a forward
        return;
    }

    if (mappedHandler != null) {
        // 没有异常的情况下执行的afterCompletion方法
        mappedHandler.triggerAfterCompletion(request, response, null);
    }
}
```

```java
/* HandlerExecutionChain */
void triggerAfterCompletion(HttpServletRequest request, HttpServletResponse response, @Nullable Exception ex)
			throws Exception {
	// 获取所有的拦截器
    HandlerInterceptor[] interceptors = getInterceptors();
    if (!ObjectUtils.isEmpty(interceptors)) {
        // 遍历放行了preHandle方法的拦截器
        for (int i = this.interceptorIndex; i >= 0; i--) {
            HandlerInterceptor interceptor = interceptors[i];
            try {
                // 执行afterCompletion方法
                interceptor.afterCompletion(request, response, this.handler, ex);
            }
            catch (Throwable ex2) {
                logger.error("HandlerInterceptor.afterCompletion threw exception", ex2);
            }
       }
    }
}
```

# REST

REST，Representational State Transfer，表述性状态传递，表示了**资源在表现层的状态转变**。

资源，Resources，<u>网络上的一个实体，或者说网络上的一个具体的信息</u>。可以是一段文字、一张图片、一首歌曲、一种服务，总之就是一个具体的存在。可以用一个URI，即统一资源定位符，来指向目标资源，<u>每种资源都有一个特定的URI</u>。希望获取这个资源，只需要访问其URI即可，因此**URI即为每一个资源的独一无二的识别符**。

表现层，Representation，把资源具体呈现出来的信息，叫做表现层。如，文本可以使用txt来进行展示，也可以私用html、xml、json等各种方式进行展示。

状态转化，State Transfer，没发出一个请求，就代表了客户端和服务器的一次交互过程。http协议，就是一次无状态协议，即所有的状态都保存到服务器端。如果客户端想要操作服务器，必须通过某种手段，让服务端发生**状态转化**。而这种转化是建立在表现层上的，所有就是**表现层状态转化**。具体来说，在http协议中，四个表示操作方式的动词，即`GET`、`POST`、`PUT`、`DELETE`，其分别对应**四种基本操作**，<u>`GET`用于获取资源、`POST`用于新建资源、`PUT`用于更新资源、`DELETE`用于删除资源</u>

#### 总结

通过REST思想（或者应该被称为框架风格），就应该对同一个URL地址，采用不同的请求方式，即`GET`或`POST`其实现的功能各不相同，可以减少非常大量的URL值，让URL地址值变得更加简洁，但又同样清晰明了

如，对于`/user`，使用`GET`方式请求，就是获取其中的值。而使用`POST`就是插入新的值。相应的，`PUT`是更新、`DELETE`是删除。

REST推荐使用`/资源名/资源标识符`，其中资源名最好使用复数，如操作用户列表，使用`/users/p1`表示分页的第一页用户，相应的，使用`/users/p2`表示第二页等

#### 实现思路

希望将REST思想实际的使用，还存在一些问题，从html界面中，理论上只能发送两种请求，即`GET`和`POST`，其他的如`PUT`和`DELETE`请求都无法发送

为了解决这个问题Spring提供了支持，需要使用`HiddenHttpMethodFilter`过滤器

```xml
<!-- web.xml中进行配置 -->
<!-- Servlet Filters =================================== -->
<filter>
    <filter-name>hiddenHttpMethodFilter</filter-name>
    <!-- Spring提供的过滤器，用于加强隐藏的请求方法 -->
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>

<!-- Filter mappings =================================== -->
<filter-mapping>
    <filter-name>hiddenHttpMethodFilter</filter-name>
    <!-- 配置拦截所有路径 -->
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

需要使用表单，使用`POST`类型的请求，在表单中携带一个`_method`的参数，在参数中指定其他隐藏的请求类型，如下例，此处以`DELETE`方式演示

```html
<form action="/textDelete">
    <input name="_method" value="delete" hiddern
</form>
```

表现层的代码实现

```java
public class UserController{
    // 获取uid对应的用户
    @RequestMapping(value="/users/{uid}", method=ReuqestMethod.GET)
    public String getUser(@PathVariable("uid")String uid){
        System.out.println("get"+uid);
        return "success";
    }
    
    // 添加对应uid的用户，一般情况下uid不需要指定
    @RequestMapping(value="/users", method=ReuqestMethod.POST)
    public String addUser(){
        System.out.println("post"+uid);
        return "success";
    }
    
    // 删除对应uid的用户
    @RequestMapping(value="/users/{uid}", method=ReuqestMethod.DELETE)
    public String deleteUser(@PathVariable("uid")String uid){
        System.out.println("delete"+uid);
        return "success";
    }
    
    // 更新对应uid的用户
    @RequestMapping(value="/users/{uid}", method=ReuqestMethod.PUT)
    public String putUser(@PathVariable("uid")String uid){
        System.out.println("put"+uid);
        return "success";
    }
}
```

#### 部分源码分析

`HiddenHttpMethodFilter`中的`doFilter()`方法，被其父类经过封装后，改造为了`doFilterInternal()`方法

```java
public static final String DEFAULT_METHOD_PARAM = "_method";
private String methodParam = "_method";

protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    HttpServletRequest requestToUse = request;
    // 如果表单使用的是POST请求
    if ("POST".equals(request.getMethod()) && request.getAttribute("javax.servlet.error.exception") == null) {
        // 此处获取methodParam方法参数，指向的是成员变量，也就是请求中的"_method"参数
        String paramValue = request.getParameter(this.methodParam);
        // 判断参数是有对应的值
        if (StringUtils.hasLength(paramValue)) {
            // 参数值转大写
            String method = paramValue.toUpperCase(Locale.ENGLISH);
            // 比较请求的方式是否是一个可行的请求方法
            if (ALLOWED_METHODS.contains(method)) {
                // 如果是可行的请求方法，就将原本的请求进行替换，重新创建了一个新的请求，并重新设定了请求方法
                // 调用的是其内部类中的方法
                requestToUse = new HiddenHttpMethodFilter.HttpMethodRequestWrapper(request, method);
            }
        }
    }
	// 放行方法
    filterChain.doFilter((ServletRequest)requestToUse, response);
}

// HiddenHttpMethodFilter的内部类，实现了一个对HttpServletRequest的简单封装类
private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {
    private final String method;

    public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
        super(request);
        this.method = method;
    }
	// 重写了HttpServletRequest中的getMethod()方法
    public String getMethod() {
        return this.method;
    }
}
```

#### 异常

在高版本Tomcat中，即Tomcat8.0及以上版本，可以在此简单项目中可能出现最后的成功界面中出错，即jsp中不接收除`GET`、`POST`和`HEAD`请求之外的其他请求

这是因为在Controller中，使用的是请求转发的方式，最后的请求方式会继续放松往最后的`success.jsp`界面，而Tomcat8及以上就不再运行jsp接收其他请求类型了

此时，可以在jsp界面中，指定其为错误界面，即`isErrorPage="true"`，就可以简单的实现网页的展示了。



[^1]: 是一套JavaBean参数校验的标准，定义了很多常用的校验注解