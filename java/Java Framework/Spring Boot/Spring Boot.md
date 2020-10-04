# Spring Boot

Spring Boot是用来简化新Spring应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。通过这种方式，Spring Boot致力于在蓬勃发展的快速应用开发领域(rapid application development)成为领导者

## 介绍

SpringBoot主要是用于简化Spring的应用开发，约定了大量配置，去繁从简，能够快速创建一个独立的、产品级别的应用。<u>SpringBoot就是对Spring整个技术栈的大整合</u>

在J2EE如今笨重的开发、繁多的配置、低下的开发效率、复杂的部署流程、第三方技术继承难度大的大背景下，推出了SpringBoot，可以实现J2EE一站式的解决方案，而之后的SpringCloud可以实现分布式整体解决方案

SpringBoot有如下的优点

- 快速创建独立运行的Spring项目以及主流框架的集成
- 使用嵌入式的Servlet容器，应用无需打成`war`包
- `starters`自动依赖与版本控制
- 大量自动配置，简化开发，还可以修改默认值
- 无需配置`xml`，无代码生成，开箱即用
- 准生产环境的运行时应用监控
- 与云计算的天然集成

### 微服务

在2014年，通过Martin Fowler的个人博客，让更多人了解并开始使用微服务 [Microservices Guide](https://martinfowler.com/microservices/)

微服务就是一种**框架风格**，其中认为，每一个应用应该就是一种小型的服务，可以通过HTTP的方式进行互通

按照微服务的风格设计的应用，其中每个功能最终都是可独立替换或独立升级的软件单元

## 使用步骤

### 基础步骤

1. 导包

   ```xml
   <!--SpringBoot父依赖-->
   <parent>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-parent</artifactId>
     <version>2.3.1.RELEASE</version>
   </parent>
   
   <dependencies>
     <!--SpringBoot web依赖-->
     <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web</artifactId>
       <version>2.3.1.RELEASE</version>
     </dependency>
   </dependencies>
   ```

2. 编写主程序

   `Spring Boot`需要一个主程序，来启动整个应用

   ```java
   @SpringBootApplication
   public class MyApplication {
     public static void main(String[] args) {
       SpringApplication.run(MyApplication.class,args);
     }
   }
   ```

   其中，需要使用`@SpringBootApplication`注解，用于标注**主程序**，告知这是一个`Spring Boot`应用

   在类中添加`main`方法，其中调用了`Spring Boot`中的`run`方法，启动主程序，其中传入第一个参数，就是主程序的`class`对象，第二个参数就是`main`方法接收的参数

3. 编写Controller层

   其他层的Java代码，如Service层、Dao层也可以直接开始编写，就能直接使用

   ```java
   @Controller
   @EnableAutoConfiguration
   public class HelloWorldController {
     @RequestMapping("/hello world")
     @ResponseBody
     public String home(){
       return "Hello World!";
     }
   }
   ```

   在HelloWorld项目中，此处的`@EnableAutoConfiguration`可以省略，也可以得到所需的效果

4. 运行

   直接运行主程序中的`main`方法，就可以启动服务器

   <u>**目前IDEA中，Model的SpringBoot项目并不能正确指向地址，原因不明**</u>

#### 补充

##### Spring Boot快速部署

Spring Boot可以实现快速的部署，而不需要将项目转换为`war`包之后放入Tomcat中管理

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
  </plugins>
</build>
```

在`pom.xml`中，添加配置，之后使用maven的`package`功能，就可以直接将项目转换为一个可以直接运行的`jar`包

```
java -jar 具体的jar包文件
```

通过以上代码，就可以直接运行`jar`包

##### Spring Initializer

Spring官方提供的快速部署向导，可以实现对Spring Boot项目的快速部署

###### 使用步骤

1. 在IDEA中，需要先在`Plugins`中安装`Spring Assistant`才可以使用
2. 创建项目时，直线选择左侧多出来的`Spring Initializr`进行创建，此处的默认地址值很可能连接不上，推荐使用`https://start.aliyun.com/`
3. 在界面中，选择所需的模块，就可以直接进入下一步
4. 完成项目模板的创建，之后的代码可以直接在标准模板上扩展

###### 补充

在`resources`文件夹中，默认创建了如下目录结构

- `static` 【文件夹】保存所有的静态资源，如所有的`js`、`css`、`images`等
- `template` 【文件夹】保存所有的模板页面，因为Spring Boot默认使用的是嵌入式的Tomcat服务器，因此不支持`jsp`页面，可以使用模板引擎，如`freemarker`、`thymeleaf`等
- `application.properties` 【配置文件】Spring Boot应用的配置文件，可以修改某些人的配置

### Web项目开发

通过Spring Initializer可以实现项目的快速构建（见基础步骤>补充>Spring Initializer）

1. 使用Spring Initializer选中所需的模块并创建Spring Boot应用
2. Spring Boot已经对场景中的配置进行了默认的配置，只需要对其中关键部分的少量信息进行配置，就可以让项目完成搭建
3. 编写其中的业务逻辑代码

#### 静态资源约束

Spring Boot中，所创建的项目，与之间JavaWeb等web项目结构略有不同，同时其中制定了静态资源需要存放的指定目录

```java
/* WebMvcAutoConfiguration */
// 添加资源映射
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
  if (!this.resourceProperties.isAddMappings()) {
    logger.debug("Default resource handling disabled");
    return;
  }
  // 从resourceProperties中获取缓存时间
  Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
  // 从resourceProperties中获取对缓存的控制
  CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
  if (!registry.hasMappingForPattern("/webjars/**")) {
    customizeResourceHandlerRegistration(registry.addResourceHandler("/webjars/**")
                                         .addResourceLocations("classpath:/META-INF/resources/webjars/")
                                         .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
  }
  // 从mvcProperties中获取静态资源映射地址
  String staticPathPattern = this.mvcProperties.getStaticPathPattern();
  if (!registry.hasMappingForPattern(staticPathPattern)) {
    customizeResourceHandlerRegistration(registry.addResourceHandler(staticPathPattern)
                                         .addResourceLocations(getResourceLocations(this.resourceProperties.getStaticLocations()))
                                         .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
  }
}

// 欢迎页面映射
@Bean
public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext,FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider) {
  WelcomePageHandlerMapping welcomePageHandlerMapping = new WelcomePageHandlerMapping(
    new TemplateAvailabilityProviders(applicationContext), applicationContext, getWelcomePage(),
    this.mvcProperties.getStaticPathPattern());
  welcomePageHandlerMapping.setInterceptors(getInterceptors(mvcConversionService, mvcResourceUrlProvider));
  welcomePageHandlerMapping.setCorsConfigurations(getCorsConfigurations());
  return welcomePageHandlerMapping;
}
// 获取欢迎界面映射中使用的方法
private Optional<Resource> getWelcomePage() {
  // 获取所有的指定静态目录
  String[] locations = getResourceLocations(this.resourceProperties.getStaticLocations());
  // 对其中的资源进行处理，并获取到第一个index.html界面
  return Arrays.stream(locations).map(this::getIndexHtml).filter(this::isReadable).findFirst();
}
```

其中进行了如下的规定

1. 所有的`/webjars/**`，即对`webjars`其下目录的请求，都会映射到`classpath:/META-INF/resources/webjars/`中对应的资源

   此处的`webjars`，指的是以`jar`包的方式引入静态资源，可以参考[官网](https://www.webjars.org/)

2. 对任意路径的方法（除了第一种情况），都会直接映射如下几个目录中`classpath:/META-INF/resources/`、`classpath:/resources/`、`classpath:/static/`、 `classpath:/public/`以及<u>当前项目的根路径</u>

   因此，只要将静态资源存放到指定的目录下，就可以作为静态资源进行加载

在Spring Boot中，请求找不到其对应的映射方法，即动态资源后，就会按照上方的顺序，查找指定的目录下是否可以找到对应的静态资源

可以在配置文件中自定义静态资源文件夹，这样的修改会导致默认的地址失效

```properties
spring.resources.static-locations=自定义的地址
```

#### 默认的配置

Spring Boot本质上是Spring相关的各种组件的大整合，其中web项目就是用的是SpringMVC来是实现，而Spring Boot主要在其中起到的作用就是对其进行了大量的自动配置

> Spring Boot的具体配置内容，可见[官方文档](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-developing-web-applications)
>
> Spring Boot Features > Developing Web Application中，进行了具体的讲解

#### Spring MVC Auto-configuration

Spring Boot provides auto-configuration for Spring MVC that works well with most applications.

The auto-configuration adds the following features on top of Spring’s defaults:

- Inclusion of `ContentNegotiatingViewResolver` and `BeanNameViewResolver` beans.

  `ContentNegotiatingViewResolver` 即`ViewResolver`视图解析器

  其中组合了所有的视图解析器，即**<u>存放在容器中的</u>**`ViewResolver`的实现类。`ContentNegotiatingViewResolver` 调用方法，获取所有的容器，并尝试使用视图解析器对其进行解析，自动将其进行组合，因此==自定义的视图解析器，可以直接添加到容器中，就可以纳入其管理==

- Support for serving static resources, including support for WebJars (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-static-content))).

  支持静态资源文件，包括支持了`webjars`

- Automatic registration of `Converter`, `GenericConverter`, and `Formatter` beans.

  自动注册了`Converter`转换器、`GenericConverter`和`Formatter`

  `Converter`即用于处理类型转换，将请求中的文本转换为接收的数据类型；`Fomatter`即格式转换器，主要用于对数据进行格式化，如日期的显示格式等，需要在著配置文件中进行配置，具体配置建议查看官方文档<u>Application Properties</u>

  在`WebMvcAutoConfiguration`中，定义了读取的方法，其通过扫描容器的方式来获取所有的转换器，因此，自定义的转换器只需要添加到容器中，主要满足条件，就会被读取并使用

- Support for `HttpMessageConverters` (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-message-converters)).

  支持对Http请求和响应的转换，如将某个对象使用json的格式进行输出

  在`WebMvcAutoConfiguration`中，将`HttpMessageConverters`存放在了一个静态内部类中，	

- Automatic registration of `MessageCodesResolver` (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-message-codes)).

  自动配置错误代码生成规则，错误代码解析器

  ```java
  @Override
  public MessageCodesResolver getMessageCodesResolver() {
    // 从Properties中获取
    if (this.mvcProperties.getMessageCodesResolverFormat() != null) {
      DefaultMessageCodesResolver resolver = new DefaultMessageCodesResolver();
      resolver.setMessageCodeFormatter(this.mvcProperties.getMessageCodesResolverFormat());
      return resolver;
    }
    return null;
  }
  ```

- Static `index.html` support.

- Custom `Favicon` support (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-favicon)).

- Automatic use of a `ConfigurableWebBindingInitializer` bean (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-web-binding-initializer)).

  主要用于Web数据绑定器，主要用于将请求中所传递的数据，绑定到JavaBean中

If you want to keep those Spring Boot MVC customizations and make more [MVC customizations](https://docs.spring.io/spring/docs/5.2.7.RELEASE/spring-framework-reference/web.html#mvc) (interceptors, formatters, view controllers, and other features), you can add your own `@Configuration` class of type `WebMvcConfigurer` but **without** `@EnableWebMvc`.

If you want to provide custom instances of `RequestMappingHandlerMapping`, `RequestMappingHandlerAdapter`, or `ExceptionHandlerExceptionResolver`, and still keep the Spring Boot MVC customizations, you can declare a bean of type `WebMvcRegistrations` and use it to provide custom instances of those components.

If you want to take complete control of Spring MVC, you can add your own `@Configuration` annotated with `@EnableWebMvc`, or alternatively add your own `@Configuration`-annotated `DelegatingWebMvcConfiguration` as described in the Javadoc of `@EnableWebMvc`.

Spring Boot中所有的Web相关自动配置，可见`org.springframework.boot.autoconfiguration.web`包下的`AutoConfiguration`类

#### Spring Boot自动配置总结

通过在Spring MVC Auto-configuration中的分析，可以总结出Spring Boot中的某些规律

1. Spring Boot会自动配置很多组件，同时会扫描用户的配置，只要存在用户自定义的配置，就会优先使用用户配置，在没有找到用户配置的情况下，才会采用自动配置。如果出现了某些会扫描容器中所有的其实现组件的情况，就会在加载默认配置的情况下，同时带上用户的配置

2. 如果希望在Spring Boot原有配置上进行增强，可以编写自己的配置类，即标注了`@Configuration`的类，其必须是`WebMvcConfiguration`的实现类，同时还不能使用`@EnablueWebMvc`注解

   > 如果使用了`@EnablueWebMvc`注解，就会关闭Spring MVC相关的所有默认配置
   >
   > 其**原理**就是在`@EnablueWebMvc`注解中，使用`@Import`注解导入了`DelegatingWebMvcConfiguration`类对象，其继承自`WebMvcConfigurationSupport`
   >
   > 而在`WebMvcAutoConfiguration`上，使用了`@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)`即只有在容器中，没有`WebMvcConfigurationSupport`是才会生效，而配置了`@EnableWebMvc`之后就会直接向容器中添加一个其子类，因此原本的Spring MVC的自动配置不再生效

   而此处的原理就在`WebMvcAutoConfiguration`中的内部类`WebMvcAutoConfigurationAdapter`上使用了`@Import(EnableWebMvcConfiguration.class)`启用了默认的获取相关配置类，其继承了`DelegatingWebMvcConfiguration`，其中存在如下方法

   ```java
   @Autowired(required = false)
   // 使用了自动配置，获取了容器中所有的WebMvcConfiguration实现类，并在之后的方法中进行了遍历读取
   public void setConfigurers(List<WebMvcConfigurer> configurers) {
     if (!CollectionUtils.isEmpty(configurers)) {
       this.configurers.addWebMvcConfigurers(configurers);
     }
   }
   ```

   通过这样的方式，就读取到了所有的相关配置，用户可以很简单就实现对的扩展

   简单总结，**<u>Spring Boot中定义了非常多的`...Configuration`接口，使得用户可以在已有的配置上进行增强</u>**

## POM配置

在基础的项目中，进行了简单的配置，但是导入了大量的`jar`包，其中包括Spring以及SpringMVC中很多用到过的`jar`包

### 父项目

在`pom.xml`中，使用了`<parent>`标签，设置了一个父项目

```xml
<!--SpringBoot父依赖-->
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>2.3.1.RELEASE</version>
</parent>
```

由名字可知，`spring-boot-starter-parent`就是`Spring Boot`的所有starter项目的父项目，而这个父项目同样依赖了`spring-boot-dependencies`父项目

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-dependencies</artifactId>
  <version>2.3.1.RELEASE</version>
</parent>
```

此处的`spring-boot-dependencies`才是对Spring Boot应用中，所有的依赖进行了管理，即对所有的依赖**版本**进行了集中管理

因此，<u>只要配置了父项目，之后设置的大部分其他依赖，就不需要设置版本，其夫项目中已经决定了版本</u>

对于部分少部分没有在父项目中规定版本的依赖还是需要自己设定版本

### 导入依赖

在配置中，导入一个依赖，`spring-boot-starter-web`

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

其中，`spring-boot-starter`指的是**Spring Boot场景启动器**，而`spring-boot-starter-web`就是针对web项目的Spring Boot场景启动器，主要作用是帮我们<u>导入了所有web项目基础的`jar`包</u>，让项目能够正常执行

Spring Boot将所有的功能场景进行了抽取，做成了很多的场景启动器，用于针对不同的场景，只需要导入对应的场景启动器，即各个`starter`，就可以直接导入所有相关的依赖

因此，<u>之后的开发只需要针对不同的场景，导入所需的场景启动器即可</u>

## 主程序类

在Spring Boot中，需要一个主程序类，即主接口类

```java
@SpringBootApplication
public class MyApplication {
  public static void main(String[] args) {
    SpringApplication.run(MyApplication.class, args);
  }
}
```

其中，使用了`@SpringBootApplication`注解，即Spring Boot应用注解，用于标注某个类为Spring Boot的主配置类

被标注的类，即主程序类，通过调用改类的`main`方法，就启动了Spring Boot应用

`@SpringBootApplication`其中的具体代码如下

```java
/* @SpringBootApplication注解源码 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
                                 @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
```

在这个注解类上，设置了很多其他的注解

- `@SpringBootConfiguration`

  Spring Boot的配置类，主要用于标注在某个类上，该类为Spring Boot的配置类

  该注解中，存在`@Configuration`注解，主要来自`Spring`中，用于标注某个类是配置类，其中可以做到类似`xml`配置文件的配置

  同时，其中使用`@Component`将其加入了容器

- `@EnableAutoConfiguration`

  开启自动配置功能，可以将之前的大量配置进行自动配置

  ```java
  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @Inherited
  @AutoConfigurationPackage
  @Import(AutoConfigurationImportSelector.class)
  public @interface EnableAutoConfiguration {
  ```

  其中，存在`@AutoConfigurationPackage`，表示自动配置包，在该注解内部使用了

  ```java
  @Import(AutoConfigurationPackages.Registrar.class)
  ```

  使用的是Spring的底层注解`@Import`，给容器中导入一个组件，即通过`AutoConfigurationPackages.Registrar`类中的方法，将主配置类所在包，以及其中的所有组件扫描到Spring容器中

  同时，其中还有`@Import(AutoConfigurationImportSelector.class)`导入了一个导包的选择器类

  最终会为容器导入很多自动配置类，即`..AutoConfiguration`，就是给容器中导入这个场景所需要的所有组件，并配置好组件。通过自动配置类，免去了手动编写配置注入功能组件等的工作

  此处在Spring Boot的1和2之间存在较大差距，在1中，`AutoConfigurationImportSelector`继承了其他的类，而本身并没有实现太多具体功能。2中，直接就在类本身中实现了`selectImport`方法

  此处主要按照Spring Boot 2.3.1为主

  在`selectImports`方法中，传入了注解的信息，并通过`getAutoConfigurationEntry`方法，其中，又通过`getCandidateConfigurations`方法来获取所有的<u>自动配置类</u>，并将其封装到一个`AutoConfigurationEntry`的对象中，并最终将对添加到容器的组件实现自动配置

  在其中，`getCandidateConfigurations`中使用了`SpringFactoriesLoader.loadFactoryNames()`方法，其中接收两个参数`EnableAutoConfiguration.class`和类加载器`ClassLoader`

  最底层是获取的`META-INF/spring.factories`目录下的文件，即`spring-boot-autoconfigure-2.3.1.RELEASE.jar`中的文件，将其作为`properties`文件进行读取

  因此，<u>Spring Boot在启动时，会从类路径下（Spring Boot的`jar`包中），获取到默认的自动配置类信息，并导入到容器中，此时，自动配置类就已经生效，可以为我们实现自动配置的功能</u>

## 配置文件

在Spring Boot中，可以支持两种格式的文件，作为其全局的配置文件。**<u>文件名称固定</u>**（之后可拓展profile，改变运行环境）

1. `application.properties`
2. `application.yml`

通过此处的配置文件，可以修改Spring Boot中的某些自动配置默认值

Spring Boot在开启时，会自动对其默认路径下进行扫描，是否存在配置文件

- `file:/config/`

- `file:/`

  使用`file:`的方式，表示的是整个项目的根目录，作为其初始目录

- `classpath:./config/`

- `classpath:/`

  使用`classpath:`的方式，表示的是类路径下的根目录，作为其初始目录

其中的加载优先级就是此处的<u>从上到下依次降低</u>，优先级高的会覆盖之下的配置，即**<u>所有的配置文件都会加载，但是其中的部分配置可以互相覆盖</u>**

可以通过命令行的方式，为项目运行添加参数，指定某个新的配置文件位置，可以将配置文件一同加载共同作用，起到互补的作用

```
--spring.config.location=文件的位置（系统中的路径）
```

### 配置信息

在配置文件中，可以通过配置修改的属性

> 具体全面的配置属性参照官方文档
>
> `Application Properties`中有详细的列举，快速跳转[【Spring Boot 2.3.1】](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/appendix-application-properties.html#common-application-properties)

# 补充知识

## yml文件

yml文件或yaml文件，指的是YAML，即YAML Ain't  Markup Lauguage，是一种类似标记语言的非标记语言

YAML语言，其以数据为中心，比json、xml等更适合作为配置文件使用

> 其具体的语法规范可见
>
> - [yml参考语法规范](http://www.yaml.org/)
> - [yaml入门|菜鸟教程](https://www.runoob.com/w3cnote/yaml-intro.html)

YAML以数据为中心，主要体现在减少了其他数据在其中的占比，如于xml配置文件进行比较

- yml文件

  简单清晰，能够使用少量的代码，表示具体的信息

- xml文件

  严谨但是其中存在大量的代码冗余，表示数据的同时，需要配置大量的标签，造成浪费

#### 基本语法

- 键值对应，使用`key: value`的方式表示一对属性，其中**冒号之后必须有空格**
- 大小写敏感
- 使用缩进表示层级关系
- 缩进不允许使用tab，只允许空格
- 缩进的空格数不重要，只要相同层级的元素左对齐即可
- `#`表示注释

#### 数据类型

其中的各个配置值可以接收的数据类型

- 字面量：普通的值，即数字、字符串、布尔值

  直接将配置的key值指定为字面量的值即可，其中不需要添加引号

  如果使用了单双引号，主要用于表示的是特殊的含义

  - `""` 双引号，其中的特殊字符会被转换为其所代表的特殊含义，如换行等
    - 如`name: "zhangsan \n lisi"`，将其输出就是两行数据，`zhangsan`和`lisi`分别在两行输出
  - `''` 单引号，不会对特殊字符进行处理，保留的是其字符本身
    - 如`name: "zhangsan \n lisi"`，将其输出就是原始数据，即`zhangsan \n lisi`

- 对象：键值对的集合，又称为映射（mapping）/ 哈希（hashes） / 字典（dictionary）

  其中的对象属性，使用层级关系进行赋值，如下

  ```yml
  # 其中child-key1和child-key2分别表示两个属性
  # 标准写法，使用缩进层级的模式
  key: 
      child-key1: value
      child-key2: value2
  ```

  其中也可以支持行内的写法，但是需要使用代码块的方式，如下

  ```yml
  # 行内写法，其表现类似json
  key: {child-key1: value, child-key2: value2}
  ```

- 数组：一组按次序排列的值，又称为序列（sequence） / 列表（list）

  其中可以使用`-`来表示该数据是数组中的一个元素

  ```yml
  # test数组中，存放了三个元素
  test:
    - A
    - B
    - C
  # 较复杂的方式
  companies:
      - # 其中的每个元素是一个对象，对象中存放了如下的属性
          id: 1
          name: company1
          price: 200W
      -
          id: 2
          name: company2
          price: 500W
  ```

  数组同样存在行内写法，通过方括号进行包裹即可

  ```yml
  # 行内写法
  test: [A,B,C]
  ```

#### 文档块

yml文件中，提供了一种特殊的方式，可以将同一个配置文件，视作多个配置文件使用

在空行开头使用`---`【三个小横杠】，可以将同一个文档划分为两个部分，之后还可以继续划分

## 数据校验

Spring Boot中，同样也内置了数据校验，即`Validation`

需要在maven中，添加如下

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

## 引用外部配置文件

#### @ConfigurationProperties

在Spring Boot中，提供了`@ConfigurationProperties`注解，可以直接将配置文件进行读取，并对类对象进行赋值

<u>需要导入`spring-boot-configuration-processor`才可以使用</u>，导入之后，在yml中进行编写也会出现提示信息

<u>如果希望使用Spring Boot的功能，必须将其加入容器中</u>

`@ConfigurationProperties`其中需要传入属性

- `prefix`

  指定配置文件中，哪个key（yml配置文件也类似键值对的方式存在）下的值进行映射

其中，使用`@ConfigurationProperties`支持松散语法绑定，如`lastName`可以对应`last-name`或`last_name`，甚至可以实现`person.lastName`（person对象中的lastName属性）与`PERSON_FIREST_NAME`对应【推荐】

#### @Value

Spring中提供的注入方式，与配置文件中`<bean>`标签等标签中设置的`value`属性效果一致，可以使用`${ 参数名 }`、`#{ SpEL }`和直接设置简单值的方式，来实现对属性值的设置

在此处，主要需要与`@ConfigurationProperties`注解进行区别，两者间的区别

- `@ConfigurationProperties`
  1. 可以一次性对类中的所有属性进行注入
  2. 支持松散语法绑定
  3. 支持数据校验
- `@Value`
  1. 一次只能为某个一个属性进行注入
  2. 支持SpEL表达式

#### @PropertySource

可以用于加载指定的配置文件，如果是`@ConfigurationProperties`，其中的所有配置信息都将存放到全局配置文件中，即`application`配置文件中

其注解的需要添加在类上，可以设置如下属性

- `value` 指定配置文件，可以设置多个配置文件

#### @ImportResource

导入Spring的配置文件，并使其配置的内容生效

需要在主配置类上，添加该注解，设置`locations`属性，指定某个Spring的xml配置文件即可【并不推荐】

##### @Configuration【补充】

Spring Boot中如果真的需要添加某个组件，推荐使用全注解的方式

设置某个类为配置类，即在某个类上添加`@Configuration`注解，之后在类中进行配置，就能实现同xml配置文件相同的效果

- `@Bean`

  标注在方法上，将方法的返回值加入Spring容器，而将方法名作为存入容器的id值

  ```java
  @Configuration
  public class MyConfig{
    /**
        会将MyBean对象存入容器中，并且设置其id为myBean01
      */
    @Bean
    public MyBean myBean01(){
      return new MyBean();
    }
  }
  ```

## 配置文件拓展

在Spring Boot中，提供了对配置文件的拓展，使其可以实现其他的增强功能

##### 获取随机值

RandomValuePropertySource，可以在配置文件中使用随机数

- `${random.value}` `${random.int}` `${random.long}`

  普通值的随机获取

- `${random.int(10)}` `${random.int[1024,65535]}`

  还可以指定其随机取值的范围

##### 占位符

可以在配置文件中引用配置过的其他属性，必须是已经加载的属性才能进行此操作，即不能直接设置还没有加载的属性值用于占位

```properties
# 引用了tms.test的属性值，添加到user.test中
tms.test=helloworld
user.test=${tms.test} by Spring Boot
```

如果此处的占位符找不到对应的值，将会原样显示，此时可以使用指定其默认值

```properties
# 为了防止找不到某个值，可以设置默认值
user.test=${tms.test:hello} by Spring Boot
```

## 改变运行环境

Spring Boot中，提供了便捷的实现不同环境的快速转换的方式，如开发环境、测试环境、发布环境等方便得转换

其提供的方法是，`Profile`的方式

#### 多Profile文件

在主配置文件的编写时，其文件名中可以设置文件名为`application-[profile]`，因此如果需要多环境的转换，就编写多个针对不同环境的`profile`文件即可

默认情况下，Spring Boot都是读取文件名为`application`的配置文件，即没有指定`profile`的文件。在`application`配置文件中，使用`spring.profiles.active`属性，指定某个`profile`文件即可

- 如同时存在多个配置文件，`application`【默认】，`application-dev`，`application-pro`，此时，设置`spring.profiles.active=dev`其就会转而使用`application-dev`配置文件

#### yml多文档块

yml配置文件中，可以利用多文档块的机制，在同一个配置文件中实现多个环境的配置

Spring Boot默认读取的是第一个文档块，因此可以将其视为`application`文件，而其他部分，需要额外使用`spring: profiles: `来指定其代码块所对应的`profile`值。最后，还是在第一个文档块中配置`spring: profiles: active: `设置激活某个环境

#### 激活指定profile

激活profile的方式，并不唯一

1. 在配置文件中，指定`spring.profiles.active`属性

2. 命令行方式进行激活，同样是通过`spring.profiles.active`来激活某个`profile`

   在IDEA中，可以编辑主程序类，在其`Program arguments`中，添加`--spring.profiles.active=[profile]`来指定某个环境

   还可以在打包之后，在运行时传入命令行参数

3. 指定虚拟机参数

   配置主程序类，在其`VM options`设置参数`-Dspring.profiles.active=[profile]`

## 外部配置文件加载

Spring Boot中支持多种外部配置方式，其中主要包括如下

1. <u>命令行参数</u>

   优先级最高的配置方式，可以覆盖其他的所有配置，主要用于发布之后简单修改少量的（越少越好）的配置

   ```
   java -jar spring-boot.jar --server.port=8085 --server.context-path=/test
   # 可以一次指定多个配置，不同的配置之间使用空格分开
   ```

2. 来自`java:comp/env`的JNDI属性

3. Java系统属性（`System.getProperties()`）

4. 操作系统环境变量

5. `RandomValuePropertySource`配置的`random.*`属性值

6. <u>`jar`包外部的`application-{profile}`配置文件</u>

7. <u>`jar`包内部的`application-{profile}`配置文件</u>

8. <u>`jar`包外部的`application`配置文件</u>

9. <u>`jar`包内部的`application`配置文件</u>

   常用的方式，其中优先加载带`profile`的配置文件，之后再加载不带`profile`的文件

   此处的`jar`包指的是项目发布之后形成的`jar`包，之后添加其他配置文件的相对于打包项目的位置

10. `@Configuration`注解类上的`@PropertySource`

11. 通过`SpringApplication.setDefaultProperties`指定的默认属性

> 具体的内容可查看官方文档
>
> Spring Boot Features > Externalized Configuration

在其中，配置同样存在优先级，数字小的优先级高，其中的<u>**高优先级配置会覆盖低优先级配置，不同的配置可以形成互补配置**</u>。

## 自动配置原理

在Spring Boot中，为了简化编码的流程，能够做到快速生产，提供了自动配置的方式，让组件可以快速直接投入使用

其自动配置在主程序类的注解`@SpringBootApplication`中，使用了`@EnableAutoConfiguration`注解来自动添加了大量的默认组件配置，其中自动导入的内容是`spring-boot-autoconfigure-2.3.1.RELEASE.jar`中的`META-INF/spring.factories`其中定义的内容。在这个`spring.factories`中保存了大量的自动配置类所对应的全限定类名

最终会根据其中的全限定类名，将其作为组件加入容器，之后添加对应组件，就会对其进行自动配置

此处以`HttpEncodingAutoConfiguration`为例。此类主要是用于解决<u>Http编码</u>问题的自动配置

```java
// 标注该类为配置类
@Configuration(proxyBeanMethods = false) 
// 启动指定类的ConfigurationProperties功能
// 将配置文件中的值与ServerProperties中的属性对应绑定，并将其添加到ioc容器
@EnableConfigurationProperties(ServerProperties.class) 

// 基于Spring的@Conditional注解进行的封装，根据某个条件，此类中的配置才会生效
// 因此，由此注解的名称可知，此注解是用于判断当前是否是web应用
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET) 
// 判断当前项目中有无CharacterEncodingFilter类，即SpringMVC中用于解决web项目乱码问题
@ConditionalOnClass(CharacterEncodingFilter.class)
// 判断配置文件中，是否存在某个配置
// matchIfMissing在配置文件中找不到时的处理，此处设置为放行
@ConditionalOnProperty(prefix = "server.servlet.encoding", value = "enabled", matchIfMissing = true)
public class HttpEncodingAutoConfiguration {
  // 映射了配置文件
  private final Encoding properties;

  // 只有一个有参构造器的情况，其中的参数通过容器获取
  public HttpEncodingAutoConfiguration(ServerProperties properties) {
    this.properties = properties.getServlet().getEncoding();
  }

  // 在容器中添加组件，而其中的属性通过ServerProperties中获取
  @Bean
  // 判断容器中是否没有对应的组件，没有才会添加
  @ConditionalOnMissingBean
  public CharacterEncodingFilter characterEncodingFilter() {
    CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
    filter.setEncoding(this.properties.getCharset().name());
    filter.setForceRequestEncoding(this.properties.shouldForce(Encoding.Type.REQUEST));
    filter.setForceResponseEncoding(this.properties.shouldForce(Encoding.Type.RESPONSE));
    return filter;
  }
}
```

`ServerProperties`中的配置

```java
@ConfigurationProperties(prefix = "server", ignoreUnknownFields = true) 
// 从配置文件中获取指定的值与bean属性进行绑定
public class ServerProperties {
    // 略
}
```

由此可见，所有的配置文件中可以配置的属性，就是在类似此处的`ServerProperties`类中的属性。这样的`Properties`类中的所需求的属性就是可以进行配置的属性

因此，配置文件中需要配置的内容，就可以依据在使用某个功能时所使用到的某个`Properties`类属性即可

在此例中，通过分析，就可以得知其中需要配置编码时，就可以使用如下配置

```properties
# 开启字符编码转换，实际并没有影响
server.servlet.encoding.enabled=true
# 设置字符转换为utf-8
server.servlet.encoding.charset=utf-8
# 设置强制转换，可以将请求和响应都进行转换
server.servlet.encoding.force=true
```

#### 补充

##### @Conditional拓展

在Spring Boot中，在Spring的`@Conditional`注解基础上，进行了封装，让其可以实现更多复杂逻辑，但最终的功能都相同，用于判断是否满足条件，只有满足条件的情况下，才会将其纳入Spring管理，即加入容器

| @Conditional扩展注解            | 作用（判断是否满足当前指定条件）                 |
| ------------------------------- | ------------------------------------------------ |
| @ConditionalOnJava              | 系统的java版本是否符合要求                       |
| @ConditionalOnBean              | 容器中存在指定Bean                               |
| @ConditionalOnMissingBean       | 容器中不存在指定Bean                             |
| @ConditionalOnExpression        | 满足SpEL表达式指定                               |
| @ConditionalOnClass             | 系统中有指定的类                                 |
| @ConditionalOnMissingClass      | 系统中没有指定的类                               |
| @ConditionalOnSingleCandidate   | 容器中只有一个指定的Bean，或者这个Bean是首选Bean |
| @ConditionalOnProperty          | 系统中指定的属性是否有指定的值                   |
| @ConditionalOnResource          | 类路径下是否存在指定资源文件                     |
| @ConditionalOnWebApplication    | 当前是web环境                                    |
| @ConditionalOnNotWebApplication | 当前不是web环境                                  |
| @ConditionalOnJndi              | JNDI存在指定项                                   |

<u>所有的自动配置类都指定了某些条件，可以用于加快项目的运行</u>

> 小技巧
>
> 如果需要知道项目运行时会加载哪些自动配置类，可以开启Spring Boot的debug模式，会显示每一步的详细信息，其中就包括自动配置报告==AUTO-CONFIGURATION REPORT==
>
> 只需要在主配置文件中，添加`debug=true`属性，就可以开启debug模式
>
> ```
> ============================
> CONDITIONS EVALUATION REPORT
> ============================
> 
> # 启用的自动配置类
> Positive matches:
> -----------------
> 
>    AopAutoConfiguration matched:
>       - @ConditionalOnProperty (spring.aop.auto=true) matched (OnPropertyCondition)
> 
>    AopAutoConfiguration.ClassProxyingConfiguration matched:
>       - @ConditionalOnMissingClass did not find unwanted class 'org.aspectj.weaver.Advice' (OnClassCondition)
>       - @ConditionalOnProperty (spring.aop.proxy-target-class=true) matched (OnPropertyCondition)
> 
> # 没有启用的自动配置类
> Negative matches:
> -----------------
> 
>    ActiveMQAutoConfiguration:
>       Did not match:
>          - @ConditionalOnClass did not find required class 'javax.jms.ConnectionFactory' (OnClassCondition)
> 
> ```

## Spring Boot中的日志框架

日志框架为了应对如今快速变化的需求，需要不断发展并在原基础上进行加强，而为了解决每次加强之后较少原项目的修改，使用了类似JDBC与数据库驱动之间的逻辑

即首先创建统一的接口层，将其作为标准，称为**日志门面**；不同的日志就通过实现其接口层，实现不同的功能，就能让其能够由良好的迭代型

此处将列举市面上较常见的Java日志框架

JUL、JCL、Jboss-logging、Logback、log4j、log4j2、slf4j……

| 日志门面（日志抽象层）                                       | 日志实现                                         |
| ------------------------------------------------------------ | ------------------------------------------------ |
| JCL（Jakarta Commons Logging）、SLF4j（Simple Logging Facade for Java）、~~Jboss-logging~~（非常少见） | Log4j、JUL（java.util.logging）、Log4j2、Logback |

> Log4j、Log4j2、Logback都是SLF4j的实现
>
> Log4j、Logback、SLF4j都是来自同一个人之手，Log4j2是Apache来重新制作的日志框架（性能优秀）

在Spring Boot中选用的就是Logback，即需要SLF4j+Logback

#### SLF4j使用

主要需要关新的是如何在系统中使用SLF4j，来在以后的开发中，实现日志的相关功能

通过日志框架的基本知识，可以得知，在项目中，需要使用到日志时，不应该直接调用其实现类中的具体方法，而是使用日志门面中提供的接口方法，从而能够具有更好的兼容性

在项目中，就需要同时导入SLF4J以及其任意实现日志包

> 具体使用可见官方文档
>
> SLF4j > [SLF4J user mannal](http://www.slf4j.org/manual.html)

##### 使用说明

SLF4J的Hello World项目

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World");
  }
}
```

SLF4J官方中使用导包简单教学，其中说明了SLF4J导包中的使用说明

![concrete-bindings](http://qeqw5fw41.bkt.clouddn.com/img/concrete-bindings.png)

每个日志实现框架都有独立的配置文件，在使用SLF4J后，还是**需要对其日志的实现编写配置文件**

##### 遗留问题

通过对日志框架的简单了解，此时还是可以发现其中仍然存在部分问题

- 如果开发了某个系统，其使用了Logback日志框架，而其依赖的Spring、Hibernate、MyBatis等框架

  Spring中使用的是commons-logging日志框架、Hibernate使用的是Jboss-logging框架

  即此时各个不同的框架之间，日志框架互相混合，就需要进行统一

统一使用某个框架的好处，就是可以减少配置的数量。不同的日志实现都需要特殊的配置文件，十分繁琐

为此，SLF4J提供了多种的适配，可以让其中的不同的日志框架最终都会转向使用SLF4J中的抽象接口，并使用SLF4J的实现，实现了系统之中的日志框架统一

![legacy](http://qeqw5fw41.bkt.clouddn.com/img/legacy.png)

如上图，以第一部分为例（左上），需要整合`commons-logging`、`log4j`和`java.util.logging`框架，并最终使用SLF4J

1. `commons-logging`使用`jcl-over-slf4j`==替换==其原本的jar包
2. `log4j`使用`log4j-over-slf4j`==替换==原本的jar包
3. `jul`（`java.util.logging`）使用`jul-to-slf4j`==替换==原本的jar包

通过这样的替换之后，就能通过这个中间的转换包，使其最终使用SLF4J来实现

> 其中的转换包中，包含了原本的jar包，并进行了简单的封装，使其最终调用的是SLF4J中的对应接口，因此才实现了转换

#### Spring Boot中的日志使用

Spring Boot中，默认使用的日志框架为`Logback`日志框架。在其中，对日志依赖处在`spring-boot-starter`中，即任意一个Spring Boot中的启动器，都会依赖的一个依赖（父依赖）

在`spring-boot-starter`中，进行了日志的配置的启动器`spring-boot-starter-logging`，其中正如之前的分析一致，使用了SLF4J的转换包，让其他的日志框架转而实现SLF4J的接口

因此，在之后的Spring Boot中，使用其他框架，其中使用非Logback日志时，只需要导入框架依赖之后，排除原本的日志依赖而是用转换包依赖即可

#### 测试使用

首先，使用`LoggerFactory`工厂类中的`getLogger()`方法，方法中需要传入一个类对象，如测试类中可以直接使用`getClass()`方法，将测试类的类对象设定为方法参数

获取到的`Logger`对象中，可以使用如下的方法，分别为**<u>不同的级别</u>**中设置参数

- `trace()`
- `debug()`
- `info()`
- `warn()`
- `error()`

> 日志的级别
>
> trace < debug < info < warn < error
>
> 在日志框架的配置中，可以通过调整输出的级别，控制将会显示的信息。只会显示设定级别以上的信息，即设置为`warn`时，只会显示`warn`和`error`的信息

#### Spring Boot中的日志配置

##### 日志输出级别

Spring Boot中，默认的设置输出为`info`级别，如果希望修改Spring Boot的默认级别，可以修改`logging.level.root`，来更改Spring Boot中的默认日志输出级别

##### 日志文件输出

Spring Boot的日志可以配置输出到某个文件中，其中提供了两种方式，分别可以指定输出到某个具体的文件，还可以指定其输出到指定的文件夹下（文件名为`spring.log`）。两种方式二选一

```properties
# 两种方式二选一
# 如果是相对路径，就是相对于当前项目的根目录
# 指定并输出到某个文件
logging.file=指定文件名
# 指定输出到某个文件夹下的spring.log文件
# 在Spring Boot 2+版本中似乎已经废弃
logging.path=指定目录
```

如果同时配置了两条数据，优先使用的是`logging.file`即具体到某个文件的方式

##### 日志输出格式

可以通过配置的方式，改变Spring Boot中日志信息输出的格式。其中控制台和日志文件中的输出格式都可以进行修改

```properties
# 修改控制台中的日志格式
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n
# 指定文件中的日志输出格式
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n
```

其中，可以指定的日志格式如下

- `%d `表示日期时间			
- `%thread` 表示线程名
- `%-5level `级别从左显示5个字符宽度
- `%logger{50}`  表示logger名字最长50个字符，否则按照句点分割
- `%msg `日志消息
- `%n` 是换行符

##### 自定义的日志

在项目的开发中，可能会出现自定义的日志配置需要与Spring Boot进行整合的情况

> 其中的具体配置方式可见官方文档，[快速跳转](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-custom-log-configuration)
>
> Spring Boot Features > Logging > Custom Log Confiuration

此时只需要将对应日志框架默认的配置文件添加到项目中，Spring Boot就不会再使用自身默认的配置，转而使用自定义的日志框架配置，在Spring Boot官方文档中，明确说明了不同的日志框架所需指定的日志名称

| Logging System          | Customization                                                |
| :---------------------- | :----------------------------------------------------------- |
| Logback                 | `logback-spring.xml`, `logback-spring.groovy`, `logback.xml`, or `logback.groovy` |
| Log4j2                  | `log4j2-spring.xml` or `log4j2.xml`                          |
| JDK (Java Util Logging) | `logging.properties`                                         |

> 其中，**<u>配置文件命名时推荐添加spring拓展名</u>**，如`logback-spring.xml`而非`logback.xml`
>
> `logback.xml`会直接被Logback日志框架识别，而使用`logback-spring.xml`命名，会先被Spring Boot获取，并对其增强，其中可以使用更多的标签，实现更多的功能
>
> `<springProfile>`标签，可以指定其标签体只会在某些环境下生效，其中可以指定`name`属性，其中可以指定其作用的环境还可以识别简单的逻辑，如
>
> ```xml
> <!-- 基本使用 -->
> <springProfile name="dev">
> 	<!-- 在标签体中，指定在dev环境中才会生效的内容 -->
> </springProfile>
> <!-- 包含简单逻辑的使用方式 -->
> <springProfile name="!dev">
> 	<!-- 在标签体中，指定非dev环境才会生效的内容 -->
> </springProfile>
> <springProfile name="dev | conf">
> 	<!-- 在标签体重，指定dev或conf环境才会生效的内容-->
> </springProfile>
> ```
>
> `<springProperty>`标签，在其配置文件中，添加配置参数，方便之后的使用
>
> ```xml
> <springProperty scope="context" name="fluentHost" source="myapp.fluentd.host"
>         defaultValue="localhost"/>
> <appender name="FLUENT" class="ch.qos.logback.more.appenders.DataFluentAppender">
>     <remoteHost>${fluentHost}</remoteHost>
>     ...
> </appender>
> ```

##### 切换不同日志

Spring Boot中，默认使用的是Logback日志框架，如果需要替换，需要首先进行分析

转换到SLF4J+log4j，按照SLF4J的官方图可知，最终将使用的是`slf4j`、`slf4j-log4j`和`log4j`三个包，因此，首先需要排除的是将`log4j`转换为`slf4j`的转换包以及`logback`，之后再分别导入缺少的包即可

转换到SLF4J+log4j2，在Spring Boot中，提供了现成的实现，`spring-boot-starter-log4j2`，如果需要使用，首先需要将Spring Boot默认的日志`spring-boot-starter-logging`全部排除，之后添加`spring-boot-starter-log4j2`即可

## 错误处理

Spring Boot的web项目中，出现错误会根据用户状态的不同，进行不同的处理

- 如果使用**浏览器**进行访问时遇到错误，会跳转到一个默认的错误界面，显示错误信息
- 如果使用**web接口测试工具**，如Postman，遇到错误时会返回json数据

#### 原理

在Spring Boot中，用于处理web项目错误的配置，可以参照`ErrorMvcAutoConfiguration`中的自动配置

其中主要为容器添加了一下的组件

- `DefaultErrorAttributes`

  用于存放默认的错误参数信息，只会在使用了模板引擎的情况下生效，将错误信息存放到错误参数中，可以将参数信息在错误界面中进行显示

  其中主要包括如下信息

  - `timestamp` 事件戳
  - `status` 状态码

  - `error` 错误提示

  - `exception` 异常对象

  - `message` 异常信息
  - `errors` JSR-303异常信息

- `BasicErrorController`

  基础的错误处理控制器，`ErrorPageCustomizer`的默认调用

  其本质就是一个Controller，其中定义了错误时的具体处理方法

  ```java
  @Controller
  // 通过配置的方式可以修改其映射的地址，默认情况下，映射的地址值为/error
  @RequestMapping("${server.error.path:${error.path:/error}}")
  public class BasicErrorController extends AbstractErrorController {
  
    // 用于产生html的错误信息
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
      HttpStatus status = getStatus(request);
      Map<String, Object> model = Collections
        .unmodifiableMap(getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.TEXT_HTML)));
      response.setStatus(status.value());
      // 获取用于决定最终跳转的错误界面的方法
      // 其中同时同时包括页面的地址以及页面的信息
      ModelAndView modelAndView = resolveErrorView(request, response, status, model);
      return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
    }
  
    // 用于产生json类型的数据
    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
      HttpStatus status = getStatus(request);
      if (status == HttpStatus.NO_CONTENT) {
        return new ResponseEntity<>(status);
      }
      Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
      return new ResponseEntity<>(body, status);
    }
  }
  ```

  > Spring Boot中区分浏览器和其他方式的方法
  >
  > 浏览器访问时，请求头中会携带`Accept`其中指定了优先接收`texxt/html`数据
  >
  > 而其他客户端，即web接口测试工具，就不会携带这样的标识

  在用于处理错误响应的方法中，最终调用了其父类（抽象类）的`resolveErrorView()`方法

  ```java
  protected ModelAndView resolveErrorView(HttpServletRequest request, HttpServletResponse response, HttpStatus status,
                                          Map<String, Object> model) {
    // 遍历获取所有的异常视图解析器
    for (ErrorViewResolver resolver : this.errorViewResolvers) {
      ModelAndView modelAndView = resolver.resolveErrorView(request, status, model);
      if (modelAndView != null) {
        return modelAndView;
      }
    }
    return null;
  }
  ```

- `ErrorPageCustomizer`

  用于系统出现错误之后的响应规则，其中定义了默认的错误处理，可以通过配置的方式，修改其指向的地址值

  其作用类似之前的web项目中，在`web.xml`中配置的错误界面，但是在Spring Boot中更加复杂，通过了其他的解析器等才最终获取到了响应的信息

- `DefaultErrorViewResolver`

  默认的错误视图解析器，其在`ErrorMvcAutoConfiguration`的内部类`DefaultErrorViewResolverConfiguration`中加入容器，用于对视图进行解析

  `BasicErrorController`中最终用于遍历获取得到的`ErrorViewResolver`对象，其中默认的解析器就是此处的默认解析器

  ```java
  // 在BasicErrorController中遍历使用的方法，用于获取最终的错误响应界面信息
  @Override
  public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
    // 用于获取错误界面，其中传入了错误码的信息
    ModelAndView modelAndView = resolve(String.valueOf(status.value()), model);
    if (modelAndView == null && SERIES_VIEWS.containsKey(status.series())) {
      modelAndView = resolve(SERIES_VIEWS.get(status.series()), model);
    }
    return modelAndView;
  }
  
  private ModelAndView resolve(String viewName, Map<String, Object> model) {
    // 指定了默认的Spring Boot错误界面，在/error/下的某个界面，其根据错误码的不同，界面也不同
    // 如，/error/404
    String errorViewName = "error/" + viewName;
    // 获取模板引擎
    TemplateAvailabilityProvider provider = this.templateAvailabilityProviders.getProvider(errorViewName,
                                                                                           this.applicationContext);
    if (provider != null) {
      // 如果模板引擎可以解析界面，就会直接使用模板引擎
      return new ModelAndView(errorViewName, model);
    }
    // 模板引擎不可用，就去寻找errorViewName对应的界面，如静态资源目录/error/404.html
    return resolveResource(errorViewName, model);
  }
  ```

#### 步骤

##### 定制错误界面

1. 一旦系统出现了错误，`ErrorPageCustomizer`就会生效，用于定制错误的响应规则，即错误时的界面跳转路径，其中获取到的是`ErrorProperties`中的错误路径，错误路径的取值来自配置文件

   ```properties
   server.error.path=指定某个错误界面
   ```

   通过`ErrorPageCustomizer`的处理，将错误发送到了某个专门用于处理错误的地址，默认情况下，Spring Boot中将其发送到了`BasicErrorController`中进行处理

2. `BasicErrorController`本质上就是一个控制器，`ErrorPageCustomizer`默认调用的异常处理类就是其映射的地址，其中同时根据不同的访问客户端进行了不同的处理，分别对浏览器以及非浏览器的请求最终调用的是不同的方法

   其中，用于返回html数据的方法，其中调用了方法，用于获取容器中的所有`ErrorViewResolver`，用于解析最终的错误界面

3. 默认将会调用的错误视图解析器为`DefaultErrorViewResolver`，其中会因为有无模板引擎而采用不同的处理方法

   在有模板引擎的情况下，会使用模板引擎，如果没有获取到对应的模板引擎，就会使用静态资源目录中的对应界面，如404错误，就会在静态资源目录下寻找error/404.html界面

   在`DefaultErrorViewResolver`中，对其进行了简化，使其不用针对每一个错误码都编写一个专门的界面，而是==针对4xx和5xx，即400+和500+分别编写两个界面即可==，如果可以匹配精确的某个界面，还是优先使用精确界面

4. <u>如果使用了模板引擎</u>，如Spring Boot默认的Thymeleaf模板引擎，就会通过`DefaultErrorAttributes`为其添加参数，用于在错误界面中进行展示

   

##### 定制错误json数据

其中可以使用多种方法来实现此需求

1. 自定义异常处理并返回定制的json数据

   ```java
   @ControllerAdvice
   public class MyExceptionHandler{
     @ResponseBody
     @ExceptionHandler(UserNotExistException.class)
     public Map<String, Object> handlerException(Exception e){
       Map<String, Object> map = new HashMap();
       map.put("code", "user not exist");
       map.put("message", e.getMessage);
       return map;
     }
   }
   ```

   通过这种方式，虽然使用了出现错误之后就会响应json数据，但是没有实现自适应的效果，即通过这样的配置导致浏览器的错误请求也变成了json数据

2. 优化的处理方法

   ```java
   @ControllerAdvice
   public class MyExceptionHandler{
     @ResponseBody
     @ExceptionHandler(UserNotExistException.class)
     // 使用注解设置response中传出的错误状态码
     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
     public Map<String, Object> handlerException(Exception e){
       Map<String, Object> map = new HashMap();
       map.put("code", "user not exist");
       map.put("message", e.getMessage);
       return map;
     }
   }
   ```

   同样并没有解决自适应问题

3. 最终的处理方法

   在2的基础上，可以将返回不是直接返回，而那时使用请求转发的方式，交给/error，进行Spring Boot默认的错误处理，其中可以实现自适应

   但是这样的的方式，使得自定义的错误信息全部都失去了意义，并没有按照所需的效果进行展示，而是使用了Spring Boot中默认的格式展示了json，仅仅是修改了数据

   通过对`ErrorMvcAutoConfiguration`的源码进行分析，其中决定最终的展示数据获取都是根据容器中的`ErrorAttributes`，而原本默认的`DefaultErrorAttributes`是在容器中没有`ErrorAttributes`的时候才会添加

   因此，需要编写一个自定义的`ErrorAttributes`的实现类并添加到容器中

   此处可以在`DefaultErrorAttributes`上进行拓展，或者直接编写一个`ErrorAttributes`的实现类

4. 直接在`DefaultErrorAttributes`上进行增强

   ```java
   @Component
   @Slf4j
   public class MyErrorAttributes extends DefaultErrorAttributes {
     @Override
     public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
       Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
       errorAttributes.remove("timestamp");
       errorAttributes.remove("path");
       errorAttributes.remove("timestamp");
   
       Set<String> strings = errorAttributes.keySet();
       for (String it : strings) {
         Object o = errorAttributes.get(it);
         log.info("key : " + it, "value : " + o);
       }
   
       return errorAttributes;
     }
   }
   ```

   这样的方式无法针对某个具体的异常进行处理，而是整体得改变了Spring Boot中的异常显示信息

> 在官方文档中对web项目的错误进行了讲解，[快速跳转](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-error-handling)
>
> Spring Boot Features > Developing Web Application > Error Handling

## 配置嵌入式Servlet容器

在Spring Boot中，使用的是嵌入式的Servlet服务器，即Tomcat服务器

#### 服务器的配置

- 使用嵌入式的服务器不同于以往的外部Tomcat服务器，可以直接得对其进行管理，如直接修改其服务器相关的配置，而Spring Boot中通过自动配置的方式可以对其中的配置进行配置，同时也能支持通过主配置文件对服务器的配置进行修改

  Spring Boot中，默认使用`server`开头的关键字，作为嵌入服务器的相关配置，而其对应的配置类为`ServerProperties`，其中定义了可以进行配置的参数

  ```properties
  # 通用的Servlet容器配置
  server.port=8085
  server.context-path=/testPath
  
  # Tomcat的专有配置，在server的基础上，使用tomcat属性
  server.tomcat.uri-encoding=UTF-8
  ```

- 其中还支持`EmbeddedServletContainerCustomizer`（Spring Boot 1.x）和`WebServerFactoryCustomizer`（Spring Boot 2.x）

  在其中存在一个抽象方法，可以定义嵌入式Servlet容器的相关规则

  使用配置文件的方式，其本质上也是使用的该方法来实现对Servlet容器的配置

#### Web三大组件

在这样的基础上，因为使用了嵌入式的服务器，没有`web.xml`这样的配置文件，只能使用Spring Boot中的配置方式来配置JavaWeb的三大组件，Servlet、Filter、Listener

- Servlet

  在编写了相关的代码之后，需要借助Spring Boot 提供的`ServletRegistrationBean`才能将Servlet正确加入Spring管理

  通过其中的构造器，可以指定注册的Servlet以及其作用的路径

- Filter

  与Servlet类似，在编写了相关的Filter代码之后，需要借助Spring Boot提供的`FilterRegistrationBean`才能将Filter加入Spring管理

  可以使用`setFilter`方法来注册Filter，之后可以使用`setUrlPatterns`方法来指定拦截的路径

- Listener

  同上，都使用了类似的配置方法，其中需要使用`ServletListenerRegistrationBean`

#### 代码实例

```java
/**
 * 用于测试Spring Boot中的服务器相关配置
 * 1）修改内嵌服务器的默认配置
 * 2）在内嵌服务器中注册Web三大组件
 */
@Configuration
public class MyServletWebConfiguration {

  /**
     * Spring Boot中三大组件的注册标准方式
     * 1）Servlet ServletRegistrationBean
     * 2）Filter FilterRegistrationBean
     * 3）Listener ServletListenerRegistrationBean
     * @return 将自定义的web三大组件注册到容器中
     */
  @Bean
  protected ServletRegistrationBean<MyServlet> myServlet(){
    return new ServletRegistrationBean<>(new MyServlet(), "/testMyServlet");
  }

  /**
     * 通过WebServerFactoryCustomizer来修改内嵌服务器的默认配置
     * @return 存入容器，修改默认配置
     */
  @Bean
  protected WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
    return (factory) -> {
      factory.setPort(8000);
    };
  }
}
```

#### 切换其他服务器

在Spring Boot中，默认使用的是内嵌的Tomcat服务器，但其同时还可以支持其他的Servlet容器

- Jetty

  在长时间连接的应用中，更加优秀

- Undertow

  不支持jsp，但是在并发功能上更加优秀

在之前的修改服务器配置中，使用了`WebServerFactoryCustomizer`，其中的抽象方法可以接收不同的参数，即`ConfigurableWebServerFactory`只是一个接口，其下有不同的实现，用于不同的服务器

- `TomcatReactiveWebServerFactory`
- `JettyReactiveWebServerFactory`
- `UndertowReactiveWebServerFactory`

因此，可知，其中的取值取决于引入了不同的服务器依赖，Spring Boot中默认使用的是Tomcat服务器，如果需要修改，只需要将原Tomcat排除，再引入其他的服务器就能实现切换

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
  <!--排除本依赖中的某些依赖-->
  <exclusions>
    <!--排除Tomcat相关组件-->
    <exclusion>
      <artifactId>spring-boot-starter-tomcat</artifactId>
      <groupId>org.springframework.boot</groupId>
    </exclusion>
  </exclusions>
</dependency>

<!--切换jetty-->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
```

#### 服务器的自动配置

在Spring Boot中，存在一个用于为服务器进行自动配置的类`EmbeddedWebServerFactoryCustomizerAutoConfiguration`

```java
/* EmbeddedWebServerFactoryCustomizerAutoConfiguration */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@EnableConfigurationProperties(ServerProperties.class)
public class EmbeddedWebServerFactoryCustomizerAutoConfiguration {
  @Configuration(proxyBeanMethods = false)
  // 检测当前项目中是否引入了Tomcat相关的依赖
  @ConditionalOnClass({ Tomcat.class, UpgradeProtocol.class })
  public static class TomcatWebServerFactoryCustomizerConfiguration {
    @Bean
    public TomcatWebServerFactoryCustomizer tomcatWebServerFactoryCustomizer(Environment environment,
                                                                             ServerProperties serverProperties) {
      return new TomcatWebServerFactoryCustomizer(environment, serverProperties);
    }
  }
}
```

此处主要以Tomcat为例，其中的

```java
/* TomcatWebServerFactoryCustomizer */
// 改类主要实现了WebServerFactoryCustomizer接口，其中主要就是实现了接口中的customize方法
@Override
public void customize(ConfigurableTomcatWebServerFactory factory) {
  // 此处使用的是ServerProperties中的配置属性
  ServerProperties properties = this.serverProperties;
  ServerProperties.Tomcat tomcatProperties = properties.getTomcat();
  PropertyMapper propertyMapper = PropertyMapper.get();
  // 可见此处的赋值都是在属性还没有进行赋值的前提下进行的
  propertyMapper.from(tomcatProperties::getBasedir).whenNonNull().to(factory::setBaseDirectory);
  propertyMapper.from(tomcatProperties::getBackgroundProcessorDelay).whenNonNull().as(Duration::getSeconds)
    .as(Long::intValue).to(factory::setBackgroundProcessorDelay);
  customizeRemoteIpValve(factory);
  ServerProperties.Tomcat.Threads threadProperties = tomcatProperties.getThreads();
  propertyMapper.from(threadProperties::getMax).when(this::isPositive)
    .to((maxThreads) -> customizeMaxThreads(factory, threadProperties.getMax()));
  propertyMapper.from(threadProperties::getMinSpare).when(this::isPositive)
    .to((minSpareThreads) -> customizeMinThreads(factory, minSpareThreads));
  propertyMapper.from(this.serverProperties.getMaxHttpHeaderSize()).whenNonNull().asInt(DataSize::toBytes)
    .when(this::isPositive)
    .to((maxHttpHeaderSize) -> customizeMaxHttpHeaderSize(factory, maxHttpHeaderSize));
  propertyMapper.from(tomcatProperties::getMaxSwallowSize).whenNonNull().asInt(DataSize::toBytes)
    .to((maxSwallowSize) -> customizeMaxSwallowSize(factory, maxSwallowSize));
  propertyMapper.from(tomcatProperties::getMaxHttpFormPostSize).asInt(DataSize::toBytes)
    .when((maxHttpFormPostSize) -> maxHttpFormPostSize != 0)
    .to((maxHttpFormPostSize) -> customizeMaxHttpFormPostSize(factory, maxHttpFormPostSize));
  propertyMapper.from(tomcatProperties::getAccesslog).when(ServerProperties.Tomcat.Accesslog::isEnabled)
    .to((enabled) -> customizeAccessLog(factory));
  propertyMapper.from(tomcatProperties::getUriEncoding).whenNonNull().to(factory::setUriEncoding);
  propertyMapper.from(tomcatProperties::getConnectionTimeout).whenNonNull()
    .to((connectionTimeout) -> customizeConnectionTimeout(factory, connectionTimeout));
  propertyMapper.from(tomcatProperties::getMaxConnections).when(this::isPositive)
    .to((maxConnections) -> customizeMaxConnections(factory, maxConnections));
  propertyMapper.from(tomcatProperties::getAcceptCount).when(this::isPositive)
    .to((acceptCount) -> customizeAcceptCount(factory, acceptCount));
  propertyMapper.from(tomcatProperties::getProcessorCache)
    .to((processorCache) -> customizeProcessorCache(factory, processorCache));
  propertyMapper.from(tomcatProperties::getRelaxedPathChars).as(this::joinCharacters).whenHasText()
    .to((relaxedChars) -> customizeRelaxedPathChars(factory, relaxedChars));
  propertyMapper.from(tomcatProperties::getRelaxedQueryChars).as(this::joinCharacters).whenHasText()
    .to((relaxedChars) -> customizeRelaxedQueryChars(factory, relaxedChars));
  customizeStaticResources(factory);
  customizeErrorReportValve(properties.getError(), factory);
}
```

在其中，Spring Boot支持两种方式来进行配置，分别时使用配置文件和编写服务器配置类，而配置文件信息的读取是默认的行为，在`TomcatWebServerFactoryCustomizer`的`customize()`方法中就有明显的体现，而服务器配置类的实现方式更加隐秘

服务器配置类对服务器配置的修改是在预期绑定的后置处理器`WebServerFactoryCustomizerBeanPostProcessor`中，此类会在任意的`WebServerFactoryCustomizer`之后进行引用，其中使用了`getWebServerFactoryCustomizerBeans()`方法，来获取到了容器中的所有`WebServerFactoryCustomizer`实现类，并获取其中的属性，再进行赋值

> 在Spring Boot 1.x中，ServerProperties就直接是EmbeddedServletContainerCustomizer的实现类，因此可以直接通过扫描容器中的类就能直接拿到

##### 详细步骤

在Spring Boot中的服务器，其底层的自动配置实现原理，此处以Tomcat为例

1. Spring Boot的主应用启动，在主程序类中的`run()`方法开始运行
2. 在`run()`方法中，调用了`refreshContext()`方法，在其上方已经使用`createApplicationContext()`创建了一个容器，而`refreshContext()`方法就对容器进行了初始化
3. 在`refreshContext()`方法中，就是最终调用了`ServletWebServerApplicationContext`中的`refresh()`方法，对容器进行了刷新，即初始化。而容器的初始化就使用了`createWebServer()`方法来创建`WebServer`
4. 在`createWebServer()`方法中，就是用了`getWebServerFactory()`方法而这个方法得到的就是在嵌入式Servlet容器，即服务器分析中所见到的，通过自动配置的方式加入容器中的可用组件（通过依赖，即加入项目的包来判断会注册那个组件，而此处的源码可知，如果注册了多个服务器会抛出异常）
5. 之后的步骤基本上与之前分析的服务器自动配置过程一致，在`getWebServerFactory()`的方法之前必然已经通过自动配置的方式将服务器加入的容器，而后就是对服务器的配置，先是通过将配置文件中的配置读取到`ServerProperties`的属性中，之后通过后置处理器`WebServerFactoryCustomizerBeanPostProcessor`获取到`TomcatWebServerFactoryCustomizer`并且调用`customize()`方法将`ServerProperties`中的属性配置到Servlet容器中，而后还会获取容器中的所有`WebServerFactoryCustomizer`实现类，并获取其中的属性，再进行赋值

#### 特点总结

嵌入式Servlet容器相比起之前的外部Servlet容器

##### 优点

简单、便携，项目最终可以直接被打包为一个可执行的jar包

##### 缺点

难以再对其进行细节的优化处理

## 配置外部Servlet容器

Spring Boot中默认存在一个嵌入式的Servlet容器，在这样的情况下，如果希望使用外部的Servlet容器而不去使用嵌入式的Servlet容器

<u>**最终的项目依然是被打包为一个war包，需要放入Tomcat服务器管理**</u>

#### 使用步骤

此处主要使用了Spring Initializer进行项目的快速创建

1. 创建项目，其中必须选择将项目打包为war项目

2. 在pom中，将Tomcat指定为provided（用于标识目标环境已存在，不需要再使用嵌入式的Tomcat服务器）

   ```xml
   <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-tomcat</artifactId>
     <scope>provided</scope>
   </dependency>
   ```

3. 在IDEA中，点击Project Structure，在其中点击此项目的web模块，设置其中的webapp目录以web.xml文件

4. 在原本Spring Boot的基础上，需要额外编写`SpringBootServletInitializer`的子类，其中必须调用`application.sources()`在方法中传入Spring Boot的主程序，项目才可以运行

   ```java
   public class ServletInitializer extends SpringBootServletInitializer {
     @Override
     protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
       // 调用方法，传入Spring Boot的主程序
       return application.sources(SpringBootLocalservletApplication.class);
     }
   }
   ```

#### 原理分析

此处通过与嵌入式Servlet容器进行对比，来进行思考

- 嵌入式

  执行Spring  Boot主程序的main方法，启动ioc容器，创建嵌入式Servlet容器

- 外部

  启动外部服务器，服务器启动Spring Boot应用，启动ioc容器

此处需要首先了解Spring的规则，在官方文档

- 在服务器启动，即web应用启动时，会创建当前web应用里面每一个jar包中的`ServletContainerInitializer`实例
- `ServletContainerInitializer`实例就依靠jar包的/WEB-INF/services文件夹下，一个命名为`javax.servlet.ServletContainerInitializer`的文件中所存放的`ServletContainerInitializer`实现类对应的全限定类名
- 其中还可以通过`@HandlesTypes`注解，，在应用启动时加载某些类

##### 运行流程

1. 启动Tomcat服务器

2. org\springframework\spring-web\5.2.4.RELEASE\spring-web-5.2.4.RELEASE.jar!\META-INF\services\javax.servlet.ServletContainerInitializer

   Spring的web模块（不是Spring Boot）中存放了规则中所指定的中间文件，用于指向`ServletContainerInitializer`实现类的全限定类名

   > 在Spring Boot 2.3.0时，指定的Spring web模块为 5.2.6

   其中存放的是`org.springframework.web.SpringServletContainerInitializer`

3. `SpringServletContainerInitializer`上 ，使用了`@@HandlesTypes(WebApplicationInitializer.class)`注解，标注了希望所有希望直接进行加载的类，并且将注解中的`value`值对应的类对象都传入`onStartup()`方法参数Set集合中，只要传入的不是接口，就会为其创建对象

   同时方法中，遍历了所有的`WebApplicationInitializer`调用了其中的`onStartup()`方法

   此时，就会调用使用步骤中提到的必须创建的`SpringBootServletInitializer`子类来创建对象，并执行`onStartup()`方法

4. `SpringBootServletInitializer`实例对象执行`onStartup()`方法时，需要使用`createRootApplicationContext(servletContext)`来创建一个容器，在这个方法的最后，就是调用了`run()`方法，进入了与之前（嵌入式）相同的逻辑

## 模板引擎

模板引擎，Template Engines，Spring Boot中支持使用模板引擎的方式来实现动态html界面的效果

#### Template Engines

As well as REST web services, you can also use Spring MVC to serve dynamic HTML content. Spring MVC supports a variety of templating technologies, including Thymeleaf, FreeMarker, and JSPs. Also, many other templating engines include their own Spring MVC integrations.

Spring Boot includes auto-configuration support for the following templating engines:

- [FreeMarker](https://freemarker.apache.org/docs/)
- [Groovy](https://docs.groovy-lang.org/docs/next/html/documentation/template-engines.html#_the_markuptemplateengine)
- [Thymeleaf](https://www.thymeleaf.org/)
- [Mustache](https://mustache.github.io/)

## 数据访问

Spring Boot中，对于数据访问层，不论是SQL还是NOSQL，都是默认采用了整合Spring Data的方式来进行统一的管理，添加大量的自动配置，屏蔽了很多的设置。引入了各种`...Template`和`...Repository`来简化对数据访问层的操作

在具体使用时，可以只进行简单的配置，就可以直接进行使用。此处，将针对SQL、NoSQL、信息以及检索等部分进行测试

#### 基本的数据访问

1. 在Spring Boot的基础上，配置`spring-boot-starter-jdbc`

   在Spring Initializer中，在选中web的基础上，额外选中SQL中的JDBC API以及MySQL Driver

   ```xml
   <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-jdbc</artifactId>
   </dependency>
   <dependency>
     <groupId>mysql</groupId>
     <artifactId>mysql-connector-java</artifactId>
     <scope>runtime</scope>
   </dependency>
   ```

2. 配置`application`配置文件中，配置连接信息

   具体包括，连接账户、连接密码、连接url、连接驱动

   ```properties
   # 配置数据库连接信息
   spring.datasource.username=root
   spring.datasource.password=root
   spring.datasource.url=jdbc:mysql://47.92.163.46:3306/spring_jdbc
   # 在Spring Boot 2.3.0中会出现提示此驱动已过时，建议使用com.mysql.cj.jdbc.Driver，而且可以不需要手动配置
   # spring.datasource.driver-class-name=com.mysql.jdbc.Driver
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

3. 测试

   ```java
   @Test
   void contextLoads() throws SQLException {
     // 输出，测试是否能够获取到数据源
     System.out.println("[Spring JDBC]DataSource："+dataSource);
   
     // 根据数据源，获取一条连接
     Connection connection = dataSource.getConnection();
     System.out.println("[Spring JDBC]DataSource.Connection："+connection);
     connection.close();
   }
   ```

   显示的结果如下

   获取的数据源信息显示：`[Spring JDBC]DataSource：HikariDataSource (null)`
   根据数据源所获取到的连接信息：`[Spring JDBC]DataSource.Connection：HikariProxyConnection@1414431049 wrapping com.mysql.cj.jdbc.ConnectionImpl@7fb66650`

#### 原理分析

Spring Boot最大的特点就是使用了自动配置的方式，因此其中必须要关系其中数据存放的位置具体是哪里

在`Application`配置文件中的信息，有关Spring JDBC的配置，最终是在`DataSourceProperties`类中进行存放

而从基础数据访问的过程最后测试中可以得知，此时使用的DataSource为`HikariDataSource`（在Spring Boot 1.x中使用的是`org.apache.tomcat.jdbc.pool.DataSource`，如果在2.x版本也想使用就需要使在`pom.xml`指定`spring-boot-starter-data-jdbc`），即`com.zaxxer.hikari.HikariDataSource`

自动配置的详细配置，可以查看`org.springframework.boot.autoconfigure.jdbc`包中的具体类

其中，`DataSourceAutoConfiguration`表示自动配置`DataSource`的类，而`DataSourceConfiguration`则决定了其中使用了的数据源

##### 配置数据源

在`DataSourceConfiguration`中，会根据导包情况，来配置数据源

在Spring Boot 2.3.0中，`spring-boot-starter-jdbc`默认使用的是`HikariCP`的连接池

其中还可以支持`org.apache.tomcat.jdbc.pool.DataSource`、`org.apache.commons.dbcp2.BasicDataSource`的数据源

同时，其中还可以通过配置，使用其他的数据源，即自定义的其他数据源，必须在`Application`配置文件中使用`spring.datasource.type`来指定具体的全限定类名

这个配置还可以用于注册了多个数据源时，来指定具体使用某个数据源时使用，如同时可以引入了`HikariDataSource`和`BasicDataSource`两个数据源时

```properties
# 使用HikariDataSource作为数据源
spring.datasource.type=com.zaxxer.hikari.HikariDataSource
# 使用BasicDataSource作为数据源
spring.datasource.type=org.apache.commons.dbcp2.BasicDataSource
```

> 此处具体指定的值，在`DataSourceConfiguration`的具体各个数据源对应的内部类上使用注解进行了标识

```java
/* DataSourceConfiguration */
// Spring Boot 2.3.0中默认的数据源
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(HikariDataSource.class)
@ConditionalOnMissingBean(DataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.zaxxer.hikari.HikariDataSource",
                       matchIfMissing = true)
static class Hikari {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    HikariDataSource dataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = createDataSource(properties, HikariDataSource.class);
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }
}

// 使用自定义的数据源
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean(DataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type")
static class Generic {

   @Bean
   DataSource dataSource(DataSourceProperties properties) {
       // 使用了建造器模式，利用了反射的模式创建了数据源，并且绑定了相关的属性
      return properties.initializeDataSourceBuilder().build();
   }

}
```

#### DataSourceInitializer

Spring Boot中设定的某种通过配置，可以每次创建数据源时就会直接执行配置的SQL语句的具体实现类

```java
/* DataSourceInitializer */
// 依据schema.sql，创建创建表格
boolean createSchema() {
  List<Resource> scripts = getScripts("spring.datasource.schema", this.properties.getSchema(), "schema");
  if (!scripts.isEmpty()) {
    if (!isEnabled()) {
      logger.debug("Initialization disabled (not running DDL scripts)");
      return false;
    }
    String username = this.properties.getSchemaUsername();
    String password = this.properties.getSchemaPassword();
    runScripts(scripts, username, password);
  }
  return !scripts.isEmpty();
}

// 依据data.sql，初始化数据，在表中添加数据的操作
void initSchema() {
  List<Resource> scripts = getScripts("spring.datasource.data", this.properties.getData(), "data");
  if (!scripts.isEmpty()) {
    if (!isEnabled()) {
      logger.debug("Initialization disabled (not running data scripts)");
      return;
    }
    String username = this.properties.getDataUsername();
    String password = this.properties.getDataPassword();
    // 运行建表语句
    runScripts(scripts, username, password);
  }
}

/** 
 * 具体的从类路劲下寻找sql文件的方法，其中fallback就是其中的
 * 关键字，如在本类中，主要定义类2两个文件（或者说4个）
 *  1） schema.sql 或 schema-all.sql
 *  2） data.sql 或data-all.sql
 * 1是用于创建表，2是用于插入数据
 */
private List<Resource> getScripts(String propertyName, List<String> resources, String fallback) {
  if (resources != null) {
    return getResources(propertyName, resources, true);
  }
  String platform = this.properties.getPlatform();
  List<String> fallbackResources = new ArrayList<>();
  fallbackResources.add("classpath*:" + fallback + "-" + platform + ".sql");
  fallbackResources.add("classpath*:" + fallback + ".sql");
  return getResources(propertyName, fallbackResources, false);
}
```

> 如果使用这样的方式失败，可以在`Application`配置文件中，添加如下的配置
>
> `spring.datasource.initialization-mode=always`
>
> 开启数据源的initialization mode 默认开启

可以指定读取某个自定义的sql文件，只需要在配置文件中，指定`spring.datasource.schema`配置，指向某个配置文件即可

#### JdbcTemplate

在Spring Boot的jdbc中，会在同时引入了DataSource和JdbcTemplate之后就使用`JdbcTemplateAutoConfiguration`来自动配置了一个`JdbcTemplateConfiguration`来注册一个JdbcTemplate到容器中

#### 整合Druid数据源

Druid数据库，是阿里旗下的一款数据库

Spring Boot整合Druid的步骤基本上与之前分析的步骤一致，只需要导入Druid相关的依赖，之后通过配置指定使用的数据源为Druid（自定义数据源）

此处主要将会列出几个整合过程中可能遇到的坑点

1. 导入的依赖

   此时可以导入`druid`依赖，加入的就是默认的依赖，需要自行编写相关的`Configuration`类，用于获取`Application`中有关Druid的配置

   而此处也可以使用Druid和Spring Boot的中间包，其中提供了实现的`Configuration`类，可以免去重复编写的过程

2. Druid本身有依赖的Servlet和Filter

   Druid中有内置的Servlet用于访问Druid的后台以及对其相关请求的拦截

   在高版本（版本尚不明）中，通过Druid和Spring Boot的中间包，可以直接通过配置的方式实现对Servlet和Filter的配置，可以减少其中可能出现的错误，提升开发的效率

   > 其中，详细的配置，可以查看`StatViewServlet`与`WebStatFilter`，又或者直接查看官方Spring Boot与Druid连接的相关文档【[快速跳转](https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter)】
   >
   > 高版本可以直接靠配置文件实现各种配置

3. Druid的filters配置

   在Druid的相关配置中，有一个很特殊的配置，用于配置相关的拦截，一般情况下，需要设置`stat,wall,log4j`的放行，只有这样，才能在Druid的后台中看到相关的监控数据

   其中`log4j`表示需要使用`log4j`的相关依赖，此时可以导入`log4j`的相关包【**不推荐**】或者直接使用`log4j`转换为`slf4j`的转换包`log4j-over-slf4j`

   **<u>Spring Boot默认使用的是`log4j-to-slf4j`会出现异常，很坑</u>**

```yml
spring:
  datasource:
    #   数据源基本配置
    username: root
    password: root
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://47.92.163.46:3306/spring_jdbc?serverTimezone=GMT%2B8
      username: root
      password: root
#      没有此处的filters，Druid的SQL监控器获取获取到数据
      filters: stat,wall,log4j
#
      initial-size: 5
      max-active: 10
      min-idle: 5
      max-wait: 30000
      pool-prepared-statements: true
      max-pool-prepared-statement-per-connection-size:  20
      validation-query: SELECT 1 FROM DUAL
      validation-query-timeout: 60000
      test-on-borrow: false
      test-on-return: false
      test-while-idle: true
      time-between-eviction-runs-millis:  60000
      min-evictable-idle-time-millis: 100000
      stat-view-servlet:
        login-username: admin
        login-password: 123123
        url-pattern: "/druid/*"
#        druid后台开启必须的配置
        enabled: true
      web-stat-filter:
        enabled: true
        url-pattern: "/*"
        exclusions: "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"
```

> 详细的配置可以查看此[文档](https://blog.csdn.net/weixin_38187317/article/details/81562571)， 对各个配置项都进行了较直观的注解

```java
/**
 * Druid的配置类
 * 其中使用了监控是否引入的监控，可以减少在不必要时的资源浪费，提升了该类的通用性
 */
@Configuration
@ConditionalOnClass(DruidDataSource.class)
@ConditionalOnProperty(name = "spring.datasource.druid", havingValue = "com.alibaba.druid.pool.DruidDataSource",
                       matchIfMissing = true)
public class DruidConfiguration {

  /**
     * 通过Druid中的建造器类，获取数据源，并加入了容器
     * 而此数据源在容器中的名称为此方法名
     * @return Druid的数据源
     */
  @Bean
  @ConfigurationProperties("spring.datasource.druid")
  public DataSource druidDataSource() {
    return DruidDataSourceBuilder.create().build();
  }
}
```

## Spring Data JPA

Spring Boot中，可以使用Spring官方提供的持久层组件Spring Data JPA，其使用的是ORM的思想，默认的底层使用Hibernate实现

详细的简介见[Spring Data](..\Spring Data\Spring Data.md)，其中包括缓存的使用以及缓存中间件的用法

## Spring Boot启动配置原理

Spring Boot是对Spring旗下的各个组件的一次大整合，提升了使用的效率，但也隐去了其中的很多细节，为了在之后的使用中能够更好的介入其运行的具体流程，必须对其启动时的运行流程、配置原理、自动配置原理进行逐一分析

#### 启动配置原理

在Spring Boot中，存在如下几个重要的**事件回调机制**

- `ApplicationContextInitializer`
- `SpringApplicationRunListener`
- `ApplicationRunner`
- `CommandLineRunner`

##### 启动流程

1. 在<u>主程序类</u>中，通过`run()`方法，创建了一个`SpringApplication`对象

   其中创建SpringApplication的构造器方法如下

   ```java
   /* SpringApplication */
   // 构造器中，第二个参数是主程序类的类对象集合
   public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
      this.resourceLoader = resourceLoader;
      Assert.notNull(primarySources, "PrimarySources must not be null");
       // 将主程序类保存在参数中
      this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
       // 调用方法，判断是否是web项目
       // 其中就是检测了项目中是否存在Web应用相关的环境配置类
      this.webApplicationType = WebApplicationType.deduceFromClasspath();
       // 为initializer赋值的方法
       // 获取了默认的配置文件META-INF/spring.factories中，所有的ApplicationContextInitializer
      setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
       // 使用类似的方法，获取到了所有的ApplicationListener
      setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
       // 决定最终的主程序类
       // 在主程序中，可以传入多个类对象，此处可以通过是否含有main方法来判断其中的某个作为主程序类
      this.mainApplicationClass = deduceMainApplicationClass();
   }
   ```

2. 调用了所构建的SpringApplication对象的`run()`方法

   ```java
   public ConfigurableApplicationContext run(String... args) {
     StopWatch stopWatch = new StopWatch();
     stopWatch.start();
     ConfigurableApplicationContext context = null;
     Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
     configureHeadlessProperty();
     // 获取SpringApplicationRunListeners
     // 其中同样是使用了getSpringFactoriesInstances方法，获取的是META-INF/spring.factories
     SpringApplicationRunListeners listeners = getRunListeners(args);
     // 回调所有获取到的SpringApplicationRunListener的starting方法
     listeners.starting();
     try {
       // 封装命令行参数
       ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
       // 准备环境【重要】
       ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
       configureIgnoreBeanInfo(environment);
       // 打印Banner（控制台图标）
       Banner printedBanner = printBanner(environment);
       // 创建ApplicationContext容器
       // 其中会根据项目是否为web项目等进行判断，2.x之后web项目还会区分Servlet和Reactive
       context = createApplicationContext();
       exceptionReporters = getSpringFactoriesInstances(SpringBootExceptionReporter.class,
                                                        new Class[] { ConfigurableApplicationContext.class }, context);
       // 准备环境【重要】
       prepareContext(context, environment, listeners, applicationArguments, printedBanner);
       // 刷新容器，即容器的初始化，具体步骤可以查看Spring中有详细介绍
       // 如果是web应用，还会创建嵌入的Servlet容器（服务器）
       refreshContext(context);
       // 在Spring Boot 1.x中，在此方法内调用了callRunners方法，而在Spring Boot 2.3.0中，将此方法设置为空方法，而callRunners方法放在了下方【特别注意】
       afterRefresh(context, applicationArguments);
       // 保存项目的启动状态
       stopWatch.stop();
       if (this.logStartupInfo) {
         new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
       }
       // 调用了所有的SpringApplicationRunListener的started方法
       listeners.started(context);
       // 从容器中获取了所有的ApplicationRunner和CommandLineRunner
       // 其中先调用ApplicationRunner再调用CommandLineRunner（决定了优先级）
       callRunners(context, applicationArguments);
     }
     catch (Throwable ex) {
       handleRunFailure(context, ex, exceptionReporters, listeners);
       throw new IllegalStateException(ex);
     }
   
     try {
       listeners.running(context);
     }
     catch (Throwable ex) {
       handleRunFailure(context, ex, exceptionReporters, null);
       throw new IllegalStateException(ex);
     }
     // 返回容器
     return context;
   }
   
   /* 环境准备方法，其中包括
   	1.获取或创建环境
   	2.确认环境
   	3.回调执行监听器的环境准备
   */
   private ConfigurableEnvironment prepareEnvironment(SpringApplicationRunListeners listeners,
                                                      ApplicationArguments applicationArguments) {
     // Create and configure the environment
     ConfigurableEnvironment environment = SpringgetOrCreateEnvironment();
     configureEnvironment(environment, applicationArguments.getSourceArgs());
     ConfigurationPropertySources.attach(environment);
     // 其中循环使用了SpringApplicationRunListener的环境准备方法
     listeners.environmentPrepared(environment);
     // 将环境绑定到SpringApplication上
     bindToSpringApplication(environment);
     if (!this.isCustomEnvironment) {
       environment = new EnvironmentConverter(getClassLoader()).convertEnvironmentIfNecessary(environment,
                                                                                              deduceEnvironmentClass());
     }
     ConfigurationPropertySources.attach(environment);
     return environment;
   }
   
   /* 准备容器，主要是一些容器的配置
   	1.将之前获取的环境保存到了容器中
   	2.使用后置处理器来处理容器
   	3.获取所有的initializer，遍历执行其中的initialize方法（此处的initializer是在创建SpringApplication时获取到的）
   	4.调用所有的SpringApplicationRunListener的contextPerpared方法
   	5.将命令行配置注册到容器中
   	6.加载source，使用load方法
   	7.调用所有SpringApplicationRunListener的contextLoaded方法
   */
   private void prepareContext(ConfigurableApplicationContext context, ConfigurableEnvironment environment,
                               SpringApplicationRunListeners listeners, ApplicationArguments applicationArguments, Banner printedBanner) {
     // 将环境配置保存到容器中
     context.setEnvironment(environment);
     // 容器的后置处理
     postProcessApplicationContext(context);
     // 遍历执行所有的initializer中的initialize方法
     applyInitializers(context);
     // 使用所有的SpringApplicationRunListener的contextPrepared方法
     listeners.contextPrepared(context);
     if (this.logStartupInfo) {
       logStartupInfo(context.getParent() == null);
       logStartupProfileInfo(context);
     }
     // 获取容器中的bean工厂，之后将之前的命令行配置注册到容器中
     ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
     beanFactory.registerSingleton("springApplicationArguments", applicationArguments);
     if (printedBanner != null) {
       beanFactory.registerSingleton("springBootBanner", printedBanner);
     }
     if (beanFactory instanceof DefaultListableBeanFactory) {
       ((DefaultListableBeanFactory) beanFactory)
       .setAllowBeanDefinitionOverriding(this.allowBeanDefinitionOverriding);
     }
     if (this.lazyInitialization) {
       context.addBeanFactoryPostProcessor(new LazyInitializationBeanFactoryPostProcessor());
     }
     // Load the sources
     Set<Object> sources = getAllSources();
     Assert.notEmpty(sources, "Sources must not be empty");
     load(context, sources.toArray(new Object[0]));
     // 调用所有的SpringApplicationRunListener的contextLoaded方法
     listeners.contextLoaded(context);
   }
   ```

##### 使用监听机制

通过对具体流程的分析，可知此处有两个部分

- 需要配置在META-INF/spring.factories

  ApplicationContextInitializer

  SpringApplicationRunListener

- 需要加入IOC容器

  ApplicationRunner

  CommandLineRunner

###### ApplicationContextInitializer

这是一个接口，同时还接受一个泛型，可以通过查看Spring Boot中对其默认的某些实现，来找出所需的泛型

![ApplicationContextInitializer-默认实现类](http://qeqw5fw41.bkt.clouddn.com/img/ApplicationContextInitializer-%E9%BB%98%E8%AE%A4%E5%AE%9E%E7%8E%B0%E7%B1%BB.png)

其中，一般使用`ConfigurableApplicationContext`作为泛型，在之后的方法中，Spring Boot会自动为其传入`ConfigurableApplicationContext`对象，即IOC容器，可以在方法中使用

###### SpringApplicationRunListener

是一个接口，通过之前对具体的创建步骤的分析，可以得知其会在ApplicationContextInitializer之后，Spring来读取`spring.factories`文件并将所有指定的SpringApplicationRunListener进行处理

其中有多个抽象方法

- `starting()`

  容器开始创建之前运行

- `environmentPrepared()` `param ConfigurableEnviorment`

  环境准备后会调用的方法，其中可以获取具体的环境信息

- `contextPrepared()` `param CofigurableApplicationContext`

  容器准备后会调用的方法，通过通过自动传入的容器对象，获取容器的信息

- `started()` `param ConfigurableApplicationContext`

  在`contextPrepared`之后进行调用

- `running()` `param CofigurableApplicationContext`

  在最后进行调用

同时，此实现类中还需要一个带参构造器，用于接收SpringApplication和args数组

```java
/* 构造器示例 */
public MySpringApplicationRunListener(SpringApplication application, String[]  args){
    System.out.println("[MySpringApplicationRunListener] constructor");
}
```

==此处是META-INF/spring.factories中的配置方法==

```properties
# Initializers
org.springframework.context.ApplicationContextInitializer=\
com.tms.test.processer.MyApplicationContextInitializer

# Spring Application Run Listener
org.springframework.boot.SpringApplicationRunListener=\
com.tms.test.processer.MySpringApplicationRunListener
```

###### ApplicationRunner

只需要继承ApplicationRunner接口，并实现其中的方法即可

之后，将实现类注册进入IOC容器

###### CommandLineRunner

只需要继承CommandLineRunner接口，并实现其中的方法

之后，将实现类注册进入IOC容器

![Spring Boot Processer-实际流程](img/Spring%20Boot%20Processer-%E5%AE%9E%E9%99%85%E6%B5%81%E7%A8%8B.png)

## Spring Boot starters

通过对Spring Boot的使用，可以了解到Spring Boot之所以强大，就是Spring官方针对不同的业务场景，都有其starter，即不同的**场景启动器**，能够实现快速的环境部署

但是，不同的业务场景千奇百怪，此时可能会需要用到一些自定义的场景启动器

#### 原理分析

为了能够实现自定义的starters，应该首先对其原理进行分析

1. 希望自定义一个场景启动器，必须了解其中所需的依赖是什么

2. 如何编写自动配置

   场景启动器最大的作用就是能够对其中的场景进行自动的配置

   其中，对于**自动配置类**，需要注意如下几点

   - `@Configuration` 用于指定某个类是配置类

   - `@ConditionalOnXXX` 

     ![@Conditional注解](img/@Conditional%E6%B3%A8%E8%A7%A3.png)

     用于限定该配置类在满足了某个特定的条件之后才会生效

   - `@AutoConfigureOrder`或`@AutoConfigureAfter`

     指定自动配置类的启动顺序

   - `@Bean`

     之后，就只需要在类中使用该注解将需要加载的对象添加到容器中

   之后，对于自动配置类类同样重要的**Properties类**，需要注意以下的注解

   - `@ConfigurationProperties`

     指定将哪些配置绑定到属性中

   - `@EnableConfigurationProperties`

     在==自动配置类的内部类==上，可以使用此注解，在将某个对象添加到容器中时，应用某些Properties类，将其属性进行绑定

     此注解只能使用在类上，因此，使用时就是将Properties中的属性与内部类中的属性进行绑定

   最后，就是启动自动配置类

   自动配置类，作为一个场景启动器的入口，希望能够加入Spring Boot中使用，就需要将其注册到Spring Boot中

   Spring Boot中的自动配置类的默认读取，都是在类路径下的META-INF/spring.factories文件中，列举了所有需要启动时加载的自动配置类

   ```properties
   # META-INF/spring.factories
   # Auto Configure
   org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
   org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
   org.springframework.boot.autoconfigure.aop.AopAutoConfiguration
   # 在之后加入自定义的自动配置类，作为自定义starters的入口类
   ```

3. 模式

   Spring Boot中的starters，也有一些规范的模式

   - 启动器 starter

     启动器模块是一个空的jar文件，仅仅是用于辅助性依赖管理，这些依赖可以自动装配其他类库

     通俗而言，其中本身并没有任何的逻辑代码，只是用于导入其中所需的自动配置模块

     在启动器中，只需要编辑其中的`pom.xml`文件，并指向自定义的autoconfigure项目坐标

     - 命名规则

       - 官方命名空间

         前缀：spring-boot-starter-

         如，spring-boot-starter-web，其中并没有逻辑代码，而是在依赖中再导入了其他的包

       - 自定义的命名空间

         后缀：-spring-boot-starter

         如：mybatis-spring-boot-starter

> 具体实例可以查看test_project/spring_boot_starter_tmsh2o_test项目，以及其自定义starters：tmsh2o_spring_boot_starter和tmsh2o_spring_boot_starter_autoconfigure

## 信息服务

在很多应用中，可以使用消息服务中间件来提升系统异步通信、扩展解耦能力

信息服务中有两个重要的概念，**<u>消息代理</u>**（message broker）和**<u>目的地</u>**（destination）。当信息发送者发送消息以后，将由信息代理接管，消息代理保证信息传递到指定的目的地

消息队列主要由两种形式的目的地

1. **队列（queue）**

   点对点信息通信（point-to-point）

   信息发送者发送消息，消息代理将其放入一个队列中，消息接收者从队列中获取信息内容，消息被读取后移出队列

   **<u>每个消息只有唯一的发送者和接受者（消息队列），但并不代表只能有一个接收者</u>**

2. **主题（topic）**

   发布（publish）/订阅（subscribe）信息通信

   发送者/发布者发送消息到主题，多个接收者/订阅者监听这个主题，会在消息到达时同时接收到消息

### 消息服务的规范

此处列举两个最常见的消息服务的规范

#### AMQP

AMQP，Advanced Message Queuing Protocol，高级消息队列协议，也是一个消息代理的规范，兼容JMS

RabbitMQ是AMQP的实现

#### MQTT

MQTT，Message Queuing Telemetry Transport，消息队列遥测传输

由IBM开发，广泛用于物联网公司，轻量、简单、开放且易于实现，常用于计算能力有限、宽带低、网络不可靠的远程通讯应用场景

#### JMS

JMS，Java Message Service，Java消息服务

基于JVM消息代理的规范。ActiveMQ、HornetMQ是JMS实现

### RebbitMQ

RebbitMQ是一个由erlang开发的AMQP开源实现

#### 核心概念

##### Message

消息，消息是不具名的，是通过消息头和消息体组成。其中消息体是不透明的，消息头由一组可选属性组成，主要包括routing-key（路由键）、priority（优先级）、delivery-mode（是否需要持久保存）等

##### Publisher

消息的生产者，也是一个向交换器发布消息的客户端应用程序

##### Exchange

交换器，用于接收生产者发送的消息并将这些消息加入服务器中的队列

Exchange由4种类型

- direct【默认】
- fanout
- topic
- headers

不同类型的Exchange转发消息的策略有所区别

##### Queue

消息队列，用于保存消息发送给消费者，其主要拥有如下的特点

- 是一个消息的容器，也是消息的终点
- 一个消息可以投入一个或多个队列
- 消息一直会在队列中，知道消费者/接收者连接到这个队列并将其取走

##### Binding

绑定，用于消息队列和交换器之间的关联

一个绑定就是基于路由键将交换器和消息队列连接起来的路由规则，所以可以将交换器理解成一个由绑定构成的路由表

Exchange和Queue的绑定可以是多对多的关系

##### Connection

网络连接，如TCP连接

##### Channel

信道，多路复用链接中的一条独立的双向数据通道

信道是建立在真实的TCP连接内的虚拟连接，AMQP命令是通过信道发送出去的，不管是发布信息、订阅队列还是接收消息，这些动作都是通过信道完成

<u>因为对于操作系统来说建立和销毁TCP都是非常昂贵的开销，所以引入了信道的概念，以复用一条TCP连接</u>

##### Consumer

消息的消费者，表示一个从消息队列中取得消息的客户端应用程序

##### Virtual Host

虚拟主机，表示一批交换器、消息队列和相关对象。虚拟主机是共享相同的身份认证和加密环境的独立服务器域。

每个Virtual Host本质上就是一个RabbitMQ服务器，拥有自己的队列、交换器、绑定和权限机制。

==vhost是AMQP的概念基础，必须连接时指定RabbitMQ默认的vhost是`/`==

##### Broker

表示消息队列服务器实体

##### 概念图

![AMQP-RabbitMQ-结构图](img/AMQP-RabbitMQ-%E7%BB%93%E6%9E%84%E5%9B%BE.png)

#### 运行机制

对AMQP或RabbitMQ中的某些机制，进行更加详细的讲解

##### 信息路由

AMQP中消息的路由过程和Java开发者熟悉的JMS存在一定的差异，**AMQP中增加了Exchange和Binding**

生产者把消息发送到Exchange上，消息最终到达队列并被消费者接收，而Binding决定了交换器的消息应该发送到哪个队列

![AMQP-RabbitMQ-消息路由](img/AMQP-RabbitMQ-%E6%B6%88%E6%81%AF%E8%B7%AF%E7%94%B1.png)

通过上图进行分析

1. Produce/Publisher发送消息到RabbitMQ，由RabbitMQ选择一个合适的交换器
2. 交换器中有很多队列，而此时决定了消息将会存放到哪个队列的是消息中携带的头信息中指定的路由键（routing-key）来决定，会结合指定的Binding策略，将消息加入到指定的队列中
3. 存放到队列中的消息，就可以等待消费者进行获取

##### 交换器类型

通过核心概念可知，Exchange分发消息时根据类型的不同分发策略有所区别，有四个类型：direct、fanout、topic、headers

> 其中，headers匹配的是AMQP消息的header而不是路由键，headers交换器和direct交换器完全一致，但性能差很多，目前几乎用不到了，所以直接略过

###### direct Exchange

消息中的路由键（routing key）如果和Binding中的binding key一致，交换器会将消息发到对应的队列中。

路由键和队列名完全匹配，如一个队列绑定到交换器要求路由键为`dog`，则只转发routing key标记`dog`的消息，不会转发`dog.puppy`，也不会转发`dog.guard`等

这是完全匹配、**<u>单播</u>**的模式

![AMQP-RabbitMQ-Direct Exchange](img/AMQP-RabbitMQ-Direct%20Exchange.png)

###### Fanout Exchange

每个发送到fanout类型交换器的消息都会被分到所有绑定的队列中

fanout交换器不会处理路由键，只是简单的将队列绑定到交换器上，每个发送到交换器的消息都会被转发到与该交换器绑定的所有队列上。其效果类似子网广播，每台子网内的主机都获得了一份复制的消息

fanout类型转发消息是最快的

![AMQP-RabbitMQ-Fanout Exchange](img/AMQP-RabbitMQ-Fanout%20Exchange.png)

###### Topic Exchange

topic交换器通过模式匹配来分配消息的路由键属性，将路由键和莫格模式进行匹配，此时队列需要绑定到一个模式上

其中是通过将路由键和绑定键的字符串切分成单词，这些**<u>单词之间用点隔开</u>**

其中支持两个通配符

- `#` 匹配0或多个单词
- `*` 匹配1个单词

> ==此处的通配符匹配的是单词，特别注意==

![AMQP-RabbitMQ-Topic Exchange](img/AMQP-RabbitMQ-Topic%20Exchange.png)

### 使用步骤

在Spring Boot中整合RabbitMQ的使用步骤

1. 导入相关依赖

   使用的是`spring-boot-starter-amqp`，来导入连接RabbitMQ的核心包

   为了方便之后的测试，可以导入`spring-rabbit-test`来进行测试

2. 进行配置

   ```yml
   spring:
     rabbitmq:
       host: 47.92.163.46
       # username、password、port等都有默认配置
   ```

3. 测试

   在测试类中，直接自动注入自动配置类中默认加入容器的`RabbitTemplate`对象

   - 使用对象的`send()`方法来发送数据

     `send()` `param exchange(String), routingKey(String), Message `

     其中，需要创建Message对象，在其中设定消息头、信息体等信息

   - 使用对象的`convertAndSend()`方法来发送数据

     `convertAndSend()` `param exchange(String), routingKey(String), message(Object)`

     可以直接传入消息对象，交给具体的方法进行后续的处理，进行序列化操作，再传给RabbitMQ

     ------

   - 使用`receive()`方法从RabbitMQ中获取信息

     `receivce()` `param queueName(String)` `return Message`

     通过指定队列名获取信息

   - 使用`receiveAndConvert()`方法获取RabbitMQ中的信息

     `receiveAndConvert()` `param queueName(String)` `return Object`

     通过指定队列名获取信息通过转换后的对象

4. 设定方法监听事件

   指定某方法去监听消息队列中的变化，并在其发生变化时获取到其中更新的信息，并调用方法

   Spring Boot中，提供了基于注解的方式实现，大幅减少了使用的中间过程

   1. 首先，需要在主程序上开启基于注解的RabbitMQ监听

      使用`@EnableRabbit`注解，开启Rabbit相关注解的扫描

   2. 在需要监听的方法上添加`@RabbitListener()`注解

      该注解可以使用`queues`属性指定监听的队列

      方法需要使用某个参数来接收监听事件之后获取到的值

   ```java
   // 只要监听到tms队列中传入了数据，就会获取值，将值传入方法并进行调用
   @RabbitListener(queues = "tms")
   public void rabbitListener(Object obj){
     System.out.println("获取到来自[tms]的数据："+obj);
   }
   ```

#### 细节补充

##### 自动配置

Spring Boot中整合RabbitMQ进行的自动配置，使用的是`RabbitAutoConfiguration`，在其中自动配置了Rabbit的连接工厂

- 连接工厂的配置使用的是`RabbitProperties`中的数据，其中封装了RabbitMQ的各种配置信息
- 其中，自动注册了`RabbitTemplate`，用于向RabbitMQ发送和接收消息
- 自动注册了`AmqpAdmin`，用于管理RabbitMQ系统组件和相关的功能，如创建队列，创建交换机等

##### 配置对象转化

默认情况下，需要发送到RabbitMQ中的数据，将会采用的是JDK中的序列化机制进行转换，为了增强数据的可读性，可以通过配置的方式修改转换为json数据进行发送

```java
/**
 * 默认情况下，需要传递的复杂类型的值，会使用jdk的序列化来转换
 * 为了增加所传递数据的可读性，可以通过配置的方式，修改转换为json格式来发送
 * 只需要替换默认的MessageConverter即可
 */
@Configuration
public class AMQPConfiguration {
  /**
     * 替换使用转换为json的数据转换方式
     * 此处的Jackson2JsonMessageConverter是MessageConverter下的实现类之一
     * @return Jackson2JsonMessageConverter
     */
  @Bean
  MessageConverter messageConverter(){
    return new Jackson2JsonMessageConverter();
  }
}
```

##### 代码管理RabbitMQ

在之前的使用步骤中，所使用到的Exchange和Queue都是通过管理界面进行创建并直接使用的，此处也可以利用AmqpAdmin来进行管理组件

使用AmqpAdmin可以实现控制界面中实现的各种操作，在没有对应的exchange和队列时，实现自动的创建等功能

## 任务

Spring Boot能够便利的创建各种任务

如，异步任务，实现多线程的访问，而不会造成阻塞，提升了应用的效率；定时任务，间隔固定时间或是每天定时启动

### 异步任务

此处将介绍通过注解的使用方式

1. 在主程序类上，开启异步任务注解

   使用`@EnableAsync`注解

2. 在具体的方法上添加注解，标记方法为异步任务，会开启另一个线程，不会阻塞当前线程

   使用`@Async`注解

### 定时任务

可以设定循环间隔，系统运行期间循环调用

1. 在主程序类上添加注解，开启定时任务注解

   使用`@EnableScheduling`注解

2. 在具体的方法上添加注解，决定方法循环执行的事件，其中需要指定一个特殊的表达式，用于表示循环时间

   使用`@Scheduled`注解，其中必须指定`cron`属性

   其中，需要接收6个字符串，分别表示每分钟的第几秒、每小时的第几分、每天的第几个小时、每个月的第几天、每年的哪一月、每周的哪一天

   如，`0 * * * * 0-7`，表示每分钟0秒都会运行方法

   但是，其中可以通过横线来表示持续时间，上例中，`0-7`表示星期一到星期天整周

   ![Spring Boot corn语句](img/Spring%20Boot%20corn%E8%AF%AD%E5%8F%A5.png)

# 开发小实例

## 登录检查

Spring Boot中的登陆检查，与之前的Spring MVC相同，依然是利用拦截器进行检查

需要编写一个`HandlerInterceptor`接口的实现类，其中实现具体的方法（在Spring Boot 2.3.1中，接口中都使用了`default`修饰符，因此不会出现提示）

可以在登录中，将登录的用户数据存入到session中，而后就可以在拦截器中，查找session中是否有用户的信息，如果为空就说明用户未登录，就应该重定向到登陆界面，同时，如果已经登录，就应该对请求进行放行

此处是一个用户登录拦截的简单实例，主要需要实现`HandlerInterceptor`实现其中的方法，此处只需要实现其中的`preHandler`方法，其他的方法在接口中都提供了default的默认实现

```java
/**
 * 用户登录拦截器，用于处理用户未登录状态下对其他界面的访问
 * 1）如果未登录，登录其他界面，会重定向到登陆界面
 * 2）如果已登录，会直接放行
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession session = request.getSession();
    if (session.getAttribute("user") != null){
      response.sendRedirect("index.html");
      return false;
    }else {
      return true;
    }
  }
}
```

> 如果有需要，可以在检测到错误访问之后，在登陆界面中添加错误消息，或者也可以转向某个用于处理错误的方法，这样就可以实现跳转的同时传递某些信息

编写了拦截器之后，它并不会默认运行，还需要将其纳入Spring Boot的统一管理，需要使用到`WebMvcConfiguration`接口，实现其中的方法来将其添加到容器中即可

```java
/**
 * 自定义的Web配置类，用于在Spring Boot的原有配置之上，进行加强
 * 此处主要用于将自定义的某些组件添加到容器中，使用Spring Boot的统一管理，在项目的运行时就会直接调用
 */
@Configuration
public class MyConfiguration implements WebMvcConfigurer {
  /**
     * 将自定义的拦截器注册到容器中，纳入Spring的统一管理，会自动进行调用
     * @param registry Spring Boot自定注入的一个用于注册相关组件的参数
     */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // addInterceptor 方法，用于在容器中添加拦截器
    // addPathPatterns 方法，用于指定拦截器的作用地址，此处的/**表示的是任意路径下的任意请求
    // excludePathPatterns 方法，用于排除拦截器的某些作用地址，此处的/index.html和/对登录界面进行了放行，同时/user/login是登录请求，也必须放行
    registry.addInterceptor(new LoginHandlerInterceptor())
      .addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login");
  }
}
```

在配置时，并不需要在意对静态资源的映射问题，在Spring Boot中，默认解决了此处的问题

## Rest风格

使用方法基本上与原Spring MVC一致，同时`HiddenHttpMethodFilter`在Spring Boot的web模块中默认配置

因此如果需要使用，只需要在表单中添加一个隐藏的`<input>`标签，其中`name`属性设定为`_method`，`value`中指定其Http请求的方法即可