# 微服务（Spring Cloud）

> 本次的笔记中不止是 Spring Cloud 但是也算是使用 Spring Cloud 进行引入

Spring Cloud 是 Spring 官方提供的**微服务**相关框架

其也被称为**分布式微服务架构的一站式解决方案**，是多种微服务架构落地技术的集合体，俗称微服务全家桶

> **微服务**
>
> 在 2014 年，Martin Fowler 对微服务进行了详细的总结，对其中的很多细节进行了界定，让其真正成为了一种全新的架构模式
>
> 其提倡将单一应用程序划分成一组小的服务，服务之间互相协调、互相配合，为用户提供最终价值。每个服务运行在独立的进程中，服务于服务之间采用轻量级的通信机制互相协作（通常是基于 HTTP 协议的 RESTful API）。每个服务都围绕着具体业务进行构建，并且能够独立的部署到生产环境、类生产环境等。另外，应道尽量避免统一的、集中式的服务管理机制，对具体的一个服务而言，应根据业务上下文，选择合适的语言、工具对其进行构建

> 基于**分布式**的**微服务架构**
>
> 主要需要解决的就是不同模块之间的整理，需要解决的其中的兼容性问题
>
> 其主要包括如下的多种维度
>
> - 服务注册与发现
> - 服务调用
> - 服务熔断
> - 负载均衡
> - 服务降级
> - 服务消息队列
> - 配置中心管理
> - 服务网关
> - 服务监控
> - 全链路追踪
> - 自动化构建部署
> - 服务定时任务调度操作

## 概念

### Spring Cloud 技术栈

微服务中，可能需要面对的各种技术栈

虽然 Spring Cloud 希望实现各个方面的大整合，但是以为其技术依然庞大，不太可能真正实现完全囊括，因此依然需要其他的技术配套使用

> 之前的微服务相关框架，之后会有变化（此处的大部分框架已经被替换）
>

- 服务注册与发现 `EUREKA`
- 服务负载与调用 `NETFLIX OSS RIBBON`
- 服务负载与调用 `FEGIN`
- 服务熔断降机 `HYSTRIX`
- 服务网关 `Zuul`
- 服务分布式配置 `Spring Cloud Config`
- 服务开发 `Spring Boot`

## 前置准备

在正式开始学习之前，需要首先进行的一系列准备工作

### 控制版本

为了 Spring Cloud 能够和 Spring Boot 进行整合使用，需要选择合适的版本，才能实现两者的稳定使用

> Spring Boot 1.x
>
> spring-cloud-dependencies ==Dalston.SR1==
>
> spring-boot-dependencies ==1.5.9.RELEASE==

如今的稳定版本（因为 Spring Boot 进入了 2.x 时代，并对其结构进行了巨大改动），可以笼统的描述为 `Spring Boot 2.x` 对应 `Spring Cloud H版` 

> 理论上也可以使用 spring initializer，能够自动实现版本的选择，也能控制使用合适的版本
>
> 此处主要是为了能够了解版本选择的思路，为什么会进行这样的选择

一般情况下，遵守以下的对应关系即可

| Spring Cloud | Spring Boot                      |
| ------------ | -------------------------------- |
| Hoxton       | 2.2.x, 2.3.x (Starting with SR5) |
| Greenwich    | 2.1.x                            |
| Finchley     | 2.0.x                            |
| Edgware      | 1.5.x                            |
| Dalston      | 1.5.x                            |

一般是优先查看最新稳定版，之后根据对应关系查看对应稳定版即可

> spring cloud alibaba 也提供了更加详细的版本控制
>
> https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E
>
> 组件版本
>
> | Spring Cloud Alibaba Version                    | Sentinel Version | Nacos Version | RocketMQ Version | Dubbo Version | Seata Version |
> | ----------------------------------------------- | ---------------- | ------------- | ---------------- | ------------- | ------------- |
> | 2.2.1.RELEASE or 2.1.2.RELEASE or 2.0.2.RELEASE | 1.7.1            | 1.2.1         | 4.4.0            | 2.7.6         | 1.2.0         |
> | 2.2.0.RELEASE                                   | 1.7.1            | 1.1.4         | 4.4.0            | 2.7.4.1       | 1.0.0         |
> | 2.1.1.RELEASE or 2.0.1.RELEASE or 1.5.1.RELEASE | 1.7.0            | 1.1.4         | 4.4.0            | 2.7.3         | 0.9.0         |
> | 2.1.0.RELEASE or 2.0.0.RELEASE or 1.5.0.RELEASE | 1.6.3            | 1.1.1         | 4.4.0            | 2.7.3         | 0.7.1         |
>
> 与 Spring 框架之间的版本控制
>
> | Spring Cloud Version        | Spring Cloud Alibaba Version      | Spring Boot Version |
> | --------------------------- | --------------------------------- | ------------------- |
> | Spring Cloud Hoxton.SR3     | 2.2.1.RELEASE                     | 2.2.5.RELEASE       |
> | Spring Cloud Hoxton.RELEASE | 2.2.0.RELEASE                     | 2.2.X.RELEASE       |
> | Spring Cloud Greenwich      | 2.1.2.RELEASE                     | 2.1.X.RELEASE       |
> | Spring Cloud Finchley       | 2.0.2.RELEASE                     | 2.0.X.RELEASE       |
> | Spring Cloud Edgware        | 1.5.1.RELEASE(停止维护，建议升级) | 1.5.X.RELEASE       |

> 官网提供的推荐版本信息
>
> https://start.spring.io/actuator/info
>
> 这也是 Spring 官方提供的 `spring initializer` 请求数据接口

> 为了防止学习过程中出现异常，此处首先列举最终选择的各个框架版本
>
> - spring cloud `Hoxton.SR3`
> - spring boot `2.2.5.RELEASE`
> - spring cloud alibaba `2.2.1.RELEASE`
>
> ---
>
> - java 8
> - maven 3.5+
> - mysql 5.7+

### 组件变动

Spring Cloud 的各个组件，在版本的迭代过程中，出现了组件的停更、升级、替换的情况

其中，部分组件出现了停更的情况，虽然没有停止使用，但是其中的 bugs 不再主动修复；不再接受和并请求；不在发布新版本

#### 老版本

![springcloud-前期准备-组件-早期组件结构](img/springcloud-%E5%89%8D%E6%9C%9F%E5%87%86%E5%A4%87-%E7%BB%84%E4%BB%B6-%E6%97%A9%E6%9C%9F%E7%BB%84%E4%BB%B6%E7%BB%93%E6%9E%84.png)

> 各个组件的具体变动原因等，可以查看 `相关框架` 下的各个具体的框架中，会有描述

#### 新版本【重点】

各个组件的变化

- 服务注册中心/服务注册与发现

  ~~`Eureka` 【原】停更不停用~~

  出现了多款替换产品，其中主要如下三个选择较多

  - `Zookeeper` 基本上使用老技术较多，但是因为一直有维护，因此比较稳定
  - `Consul` 新技术
  - `Nacos` 阿里开发，能够在多方面完美替换原组件【重点】

- 服务调用/服务负载与调用

  - `Ribbon` 【原】依然在维护，但是官方希望退出新的替换产品

  - `LoadBalancer` 后起之秀，但是还需要进行更多的版本迭代

    ---

  - ~~`Feign` 【原】基本上已经停用~~

  - `OpenFeign` Spring 社区上用于替换原本已经基本停用的 `Feign` 所推出的替换版本

- 服务降级/服务熔断降级

  - ~~`Hystrix` 【原】停更~~
  - `Resilience4j` 国外使用较多
  - `Sentinel` 阿里出品，国内使用较多【**推荐**】

- 服务网关

  - ~~`Zuul` 【原】停更~~
  - `gateway` 

- 服务配置/服务分布式配置

  - `Config` Spring Cloud config【不推荐】
  - `Nacos` 阿里出品【推荐，重点】

- 服务总线

  - `Bus`
  - `Nacos` 阿里出品，同样可以替换 `Bus`【重点】

> 详细内容可以查看官网
>
> Spring Cloud 和 Spring Boot

## 微服务 Cloud 聚合父工程

微服务最终的各个子项目，此处是为了之后使用以及测试各个微服务项目的功能

### 构建流程

1. 新建项目

   此处为学习项目，因此其使用空项目，其中使用 module 来开发各个小的微服务功能

   在其中设置 `pom.xml` 文件，其中指定 `package` 为 pom，用于之后的项目继承使用

2. 锁定版本

   控制依赖，在其中使用 `<dependencyManagement>` 锁定依赖的版本，在之后继承子项目中，就不再需要指定依赖的版本

   在 `<properties>` 中集中控制依赖的版本

3. 项目发布仓库

   在项目中的依赖版本控制完毕之后，使用 maven 的 install，将项目发布到仓库中
   
   > 此处如果出现异常，可以直接跳过 maven 的单元测试（一般不会真的有问题，只是 idea 比较敏感）

### 重要标志

通过构建流程中的配置，父工程的主要任务已经完成，之后就可以开始各个子模块的开发

在子模块的创建过程中，父工程的 `pom.xml` 文件也会发生变化（这是 maven 的特性）

每有一个项目成为该项目的子模块（不是导入该项目的依赖），夫工程中，会出现一个新的标签 `<modules>` 并将列举出其中的所有模块（子模块）

> 与此同时，子工程中项目的 `<groupId>` 也可以直接省略（直接使用夫工程中的 `groupId`）

## 子工程

此处将集中列出各个子工程，即各个功能的项目的实现的流程

### 整体流程

各个子工程中，一般有一个固定的流程

1. 创建项目
2. 修改POM
3. 编写YML
4. 准备主启动
5. 业务类
6. 测试
7. 小总结

之后的各个子项目也会大致按照这样的流程进行操作，最终构建各个模块

### springcloud-demo-payment-port8001

支付模块的项目过程

#### POM 文件

其中，修改 POM，就是导入模块所需的依赖，此处主要需要导入的依赖如下

```xml
<artifactId>springcloud-demo-payment-port8001</artifactId>
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>

  <!--springboot中的安全监控机制，一般和web配套使用
		可以通过访问项目的 /actuator 界面，查看关键信息
	-->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
  </dependency>

  <dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
  </dependency>

  <dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
  </dependency>

  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
  </dependency>

  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
  </dependency>

  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
  </dependency>

  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
  </dependency>

  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>
```

注意，此处导入的依赖需要根据父项目进行调整

如果在夫工程的 `<dependencyManagement>` 中列出的依赖，就必须指明版本；否则就需要在子项目中指定版本（但是这样的话控制版本就毫无意义）

特别注意，虽然夫工程中有 `spring-boot-dependencies` 依赖，但是不会使用其中的版本控制

> 如果在夫工程中 `dependencyManagement` 明确指出的依赖，**必须指定版本**

#### YML 文件

此处的 YML 文件指的就是 SpringBoot 中的*主配置文件* `application.yml`

```yml
# 开放端口 8001
server:
  port: 8001
  
spring:
  application:
    # 指定项目的名称
    name: springcloud-payment-service
  # 配置数据源
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    # serverTimezone 时区； useSSL 关闭SSL安全加密
    url: jdbc.mysql:/localhost:3306/test?serverTimezone=GMT%2B8&useSSL=false
    username: root
    password: root
    
# mybatis配置
mybatis:
  mapper-locations: classpath:mapper/*.xml
```

> 其中最好养成好习惯
>
> 1. 指定项目的端口号 `server.port`
> 2. 指定项目的名称 `spring.application.name`

#### 主启动类

> 之后的项目中省略

Spring Boot 的入口类编写，格式固定

```java
@SpringBootApplication
public class PaymentApplication {
  public static void main(String[] args) {
    SpringApplication.run(PaymentApplication.class, args);
  }
}
```

#### 业务类【重要】

项目中处理业务逻辑的类，整个项目中的重点

其中将处理大量的逻辑，同时其中有大量的操作，因此此处再次进行细分

- 建表 SQL
- entities
- dao
- service
- controller

---

项目中需要使用到的数据库表 `payment`

```mysql
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `serial` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES (1, 'test_serial_initial');

SET FOREIGN_KEY_CHECKS = 1;
```

> 此处的实体类以及对应的 mapper 方法以及 dao层中的方法，此处使用 MyBatis 实现，此处略

此处简化的项目因此方法较少，只有一个插入 `create()` 和根据 id 查询方法 `selectById(Long id)`

其中比较重要的是额外创建了一个请求的返回值类 `CommomResult<T>` 类，其中有三个属性 `code: Integer`、`message: String` 和 `data: T`

能够根据不同的返回值类型具体定义其中的泛型，其中的 `code` 能够表示请求的结果是否成功的状态信息，`message` 是其请求返回的提示信息，`data` 就是请求到的具体数据

### springcloud-demo-order-port80

客户端消费者，用于调用微服务提供者，此处开发了端口 `80`

此项目中不需要数据库连接，不需要内部的服务逻辑类，因此在之后的操作中，不需要导入数据库操作相关的依赖，以及之后的可以省略业务类，直接编写 `Controller` 控制类即可

#### 多项目通信

在微服务项目中，主要需要解决的就是多项目之间的数据通信问题，即不同的域之间的通信问题（在前端被称为跨域问题）

在之前的项目中，使用 `httpClient` 技术进行解决，在微服务中，可以使用 `restTemplate` 来实现多服务之间的横向调用

#### RestTemplate

RestTemplate 是一种提供了多种便捷访问远程 `http` 服务的方法，是一种便捷的访问 `restful` 服务模板类，是 `Spring` 提供的用于访问 `Rest` 服务的**客户端模板工具类**

##### 使用步骤

使用 RestTemplate 访

`(url, requestMap, ResponseBean.class)` Rest 请求地址、请求参数、响应数据的对象类型

### springcloud-commons-api

用于提取其他项目中重复出现的类（多个项目中都需要的某些类），将其提取出来形成一个新的项目

## 工程重构

> 进度
>
> 开发到了 `springcloud-demo-order-port80`（第二个子项目）

通过实际的开发中发现，其中的存在了一些重复的类，如响应的封装类 `CommonResult` 和订单类 `Payment`

为了能够**提取出项目中的重复部分**，不需要在各个项目中编写重复的内容

### 实现流程

为了提取出其中的重复部分，将其作为一个新的项目，将重复的部分代码转移到其中

> 此处创建了新的项目 `springcloud-commons-api`

1. 导入依赖

   这个项目的主要目的就是为了提取出其他业务工程中的重复代码，因此并不需要太多的依赖，此处只导入了 `devtools`、`lombok`和`hutool-all`（工具类）

2. 提取重复代码

   导入了相关的依赖之后，就可以将其他项目中的重复代码剪切到这个项目中

3. 发布maven本地库

   使用 maven 中的 `clean` 和 `install` 命令，将项目发布到本地库中，可以被其他的项目导入依赖并使用

4. 在使用项目中导入依赖

   在其他的项目需要使用其中的类时，可以在 `pom.xml` 中添加对应的依赖，就可以将其导入到项目中

# 相关框架

微服务实质上是使用了各种框架之间协同作用才能真正展现它的威力

因此，此处还需要分别对其中的所涉及到的重要框架进行讲解

## Eureka

### 概念

#### 服务治理

> Spring Cloud 封装了 Netflix（网飞公司） 开发的 Eureka 实现了**服务治理**

在传统的 rpc[^2] 远程调用框架中，管理每个服务与服务之间的依赖关系比较复杂，管理比较复杂，所以需要使用服务治理，管理服务与服务之间的依赖关系，可以实现**服务调用、负载均衡、容错等，实现服务发现与注册**

#### 服务注册

Eurka 采用了 CS 的设计架构，Eureka Server 作为服务注册功能的服务器，是服务注册中心。而系统中的其他微服务，使用 Eureka 的客户端连接到 Eureka Server 并维持心跳连接[^1]。通过这样的方式，系统维护人员就可以通过 Eureka Server 来监控系统中各个微服务是否正常运行。

在服务注册与发现中，有一个注册中心。

- 当系统启动的时候（服务提供者），会将自身服务器的相关信息，如服务器地址、通讯地址等以别名的方式注册到注册中心上
- 另一方（消费者/服务使用者），以该别名的方式去注册中心中获取到实际的服务通讯地址，然后再实现本地 RPC 调用 RPC 远程

框架中核心就是此处的注册中心，通过使用注册中心管理各个服务与服务之间的依赖关系（服务治理概念）。在任何 RPC 远程框架中，都会有一个注册中心中存放服务地址以及相关的信息，主要包括接口地址

---

Eureka 与 Dubbo 执行的流程比较图

![springcloud-相关框架-Eureka-Eureka与Dubbo比较-大致流程图](img/springcloud-%E7%9B%B8%E5%85%B3%E6%A1%86%E6%9E%B6-Eureka-Eureka%E4%B8%8EDubbo%E6%AF%94%E8%BE%83-%E5%A4%A7%E8%87%B4%E6%B5%81%E7%A8%8B%E5%9B%BE.png)

> 此处主要介绍 Eureka（左）
>
> 其中主要被分为三个部分，`Eureka Server`、`Eureka Provider`、`Service Consumer`
>
> - `Eureka Server`
>
>   主要实现**服务注册**、**服务发现**
>
>   - 服务注册：将服务信息注册到注册中心
>   - 服务发现：从注册中心上获取服务信息
>
>   其实质上就是为了存放 `key-value`，分别表示**服务名称**以及**服务调用地址**
>
> - `Eureka Provider`
>
>   服务的提供者，即所有真正提供各个服务的项目（各个功能点），在 Eureka 中主要按照以下的步骤来使用
>
>   1. 启动 Eureka Server 注册中心
>   2. 启动服务提供者（Eureka Provider）提供支付服务
>   3. 支付服务启动后会把自身信息（如服务地址根据别名注册到 Eureka 注册中心中）
>   4. 消费者（Service Consumer）获取调用地址后，底层实际上是利用 `HttpClient` 技术实现远程调用
>   5. 消费者获取服务地址后会缓存到本地 jvm 内存中，默认间隔 30 秒更新一次服务调用地址
>
> - `Service Consumer`
>
>   整个项目中，功能的使用者，调用了整个项目中实现的功能接口，并获取结果

### 重要组件

Eureka 中主要组件，`Eureka Server` 和 `Eureka Client`

- Eureka Server 服务注册服务

  各个微服务节点通过配置启动后，会在 `Eureka Server` 中进行注册，这样 `Eureka Server` 中的服务注册表将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观看到

- Eureka Client 通过注册中心访问

  是一个 Java 客户端，用于简化 `Eureka Server` 的交互，客户端同时也具备一个内置、使用轮询（round-robin）负载算法的负载均衡器在应用启动后，将会向 `Eureka Server` 发送心跳（默认周期为 30 秒）

  如果 `Eureka Server` 在多个心跳周期内都没有接收到某个节点的心跳，`Eureka Server` 将会从服务注册表中将这个服务节点移除（默认为 90 秒）

### 使用过程

为了能够实际感受到 `Eureka` 的使用，此处将结合该项目进行使用测试

其与之前的项目也一致，需要导入相关的依赖

> Eureka 的大版本更新，导致其依赖也发生了一定的变化
>
> ```xml
> <!--老版本 1.x 版本-->
> <dependency>
> 	<groupId>org.springframework.cloud</groupId>
>   <artifactId>spring-cloud-starter-eureka</artifactId>
> </dependency>
> 
> <!--新版本 2.x 版本-->
> <dependency>
> 	<groupId>org.springframework.cloud</groupId>
>   <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
> </dependency>
> ```
>
> ==新版本，相比老版本，更够更加直观感受到这是一个 Server 端==

#### 单机项目

构建一个单独的 Eureka Server 项目

##### 导入依赖

```xml
<dependencies>
  <!--eureka-server-->
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
  </dependency>

  <!--spring boot web actuator-->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
  </dependency>

  <!--一般通用依赖-->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
  </dependency>
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>

  <!--自定义api包-->
  <dependency>
    <groupId>com.tms.test</groupId>
    <artifactId>springcloud-commons-api</artifactId>
    <version>${project.version}</version>
  </dependency>
</dependencies>
```

##### 配置文件

在 `application.yml` 中配置 Eureka 的相关设置

```yaml
# eureka server 端口号
server:
  port: 7001

eureka:
  instance:
    # 指定 eureka 服务端的实例名称
    hostname: localhost
  client:
    # 是否将本身注册到注册中心（默认为 true，会进行注册；false，不进行注册）
    register-with-eureka: false
    # 是否获取注册（默认为 true，查询注册中心中的注册信息；false，不进行查询，表示本身就是注册中心，用于维护服务实例）
    # 表示自身就是注册中心，本身的职责就是维护服务实例，并不需要去检索服务
    fetch-registry: false
    # 设置 eureka 服务器的查询以及注册服务的请求 url
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

> 其中，通过配置，可以直接将项目设置为 Eureka 的服务器注册中心，能够在服务器中注册 Eureka Client 的信息，之后的请求可以通过 Eureka 的服务来获取其真正的地址

##### 主程序类

整个 Eureka Server 的项目入口，需要进行特别的配置

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(EurekaServerApplication.class, args);
  }
}
```

> 通过使用 `@EnableEurekaServer` 注解，指定开启的是 Eureka Server

##### 客户端修改

> Eureka Client 端，注册到 Eureka Server 中
>
> 此处主要是将 `springcloud-demo-payment-port8001`、`springcloud-demo-order-port80`  两个项目设置为 Eureka Client，注册到 Eureka 服务端

###### springcloud-demo-payment-port8001

1. 添加依赖

   在 `springcloud-demo-payment-port8001` 的 `pom.xml` 中添加依赖，设置为 Eureka 客户端

2. 修改配置文件

   > 此处，Eureka Client 注册到 Eureka Server 中，需要一个注册绑定的名称，就是使用 `application.yml`（主配置文件）中的 `spring.application.name` 进行绑定识别

   修改 `application.yml`，在其中指定 Eureka 所需的信息

   ```yaml
   # 之前的配置不变，在其基础上添加配置
   
   eureka:
     client:
       # 是否需要注册到 Eureka 注册中心（Eureka Server）【默认为true】
       register-with-eureka: true
       # 是否需要从 Eureka 注册中心来获取注册信息 【默认为 true】
       # 单节点无所谓，集群中必须设置为 true 才能配合 ribbon 使用负载均衡
       fetch-registry: true
       service-url:
         # 指定使用的 Eureka Server 的地址 
         defaultZone: http://localhost:7001/eureka
   ```

3. 修改主程序类

   在 SpringBoot 的主程序类上，添加注解 `@EnableEurekaClient` 


> 之后就可以先运行 `Eureka Server`，再运行 `springcloud-demo-payment-port8001`
>
> 在 `Eureka Server` 项目的运行界面中，会在 `Instances currently registered with Eureka` 中列出注册的所有的 `Eureka Client`
>
> 其中对应的 注册名称就是项目的配置中所指定的 `spring.application.name`

###### springcloud-demo-order-port80

> 本项目也会入驻 Eureka，将会注册到其中，因此，此处的配置过程较为类似
>
> 此处省略
>
> 在 `application.yml` 配置文件中，如果不需要该项目注册到 Eureka Server 可以设置 `eureka.client.register-with-eureka` 为 `false`【默认为 true】

#### 集群项目（服务端）

> **在微服务 RPC 远程服务调用最核心的是什么？**
>
> <u>高可用</u>，如果注册中心只有一个，只要发生故障，就会导致整个项目故障，导致服务环境不可用
>
> *解决方法*  搭建 Eureka 注册中心集群，实现负载均衡 + 故障容错

注册多个 Eureka Server 服务器，**一同实现**注册中心的功能，其实现的原理就是==互相注册，项目守望==

就是两个服务器之间相互注册，并监视对方是否正常运行

> 该项目，是参照 `springcloud-demo-eureka-server-port7001` 新建
>
> <u>其中只有 `application.yml` 配置文件，以及主程序类中有区别</u>
>
> 此处如果是超过两个 Eureka Server 的集群，只需要在之后的 `eureka.client.service-url.defaultZone` 中指定其他的所有服务器即可（每个地址间使用<u>逗号</u>分隔）

##### 客户端修改

在使用了集群的服务端之后，需要同时指定集群中所有的服务端地址

```yml
eureka:
  client:
    # 是否注册进入 Eureka Server 默认为 true
    # 如果不需要注册到 Eureka Server，就可以改为 false
    register-with-eureka: true
    # 是否从 Eureka Server 抓取已有的注册信息，默认为true
    # 如果时单节点项目没有影响，但是集群项目必须设置 true 才能配合 ribbon 使用负载均衡
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:7001/eureka,http://localhost:7002/eureka
```

#### 集群项目（服务提供者）

多个服务提供者一同提供服务（多个服务者实现相同的功能），避免**单点故障**

> 将多个服务同时注册到 Eureka Server 中（在集群的服务端环境中，需要同时指定所有的服务端）

> **注意**
>
> 此处的两个相同的服务，必须在 `spring.application.name` 中指定<u>相同的名称</u>

##### 消费者

项目中的微服务的消费者，需要去请求服务提供者所提供的服务，即各种功能

此处的服务提供者使用的是集群模式，多个项目一同工作，提供相同的功能，此处就存在地址的选择问题，需要通过 Eureka 的注册中心来获取对应的地址值。在 Eureka 的注册中心中，各个 Eureka Client 都与其 `spring.application.name` 进行绑定，因此<u>只需要使用其绑定的名称来获取值即可</u>。

> 在此处的项目中 `springcloud-demo-order-port80` 希望获取 `springcloud-demo-payment-port8001` 和 `springcloud-demo-payment-port8002` 的地址，就可以通过其 `spring.application.name`，即 **SPRINGCLOUD-PAYMENT-SERVICE** 来获取
>
> 最终请求的地址就可以直接填写 `http://SPRINGCLOUD-PAYMENT-SERVICE`

但是，指定了映射的地址，依然无法正确访问到服务，这是因为集群环境下，服务器有多个服务可供选择，而此时没有经过配置的 `RestTamplate` 无法在其中做出选择，因此最终会出现 `java.net.UnknownhostException` 异常

此时，就需要在注册的 `RestTemplate` 时（配置类中），添加注解 `@LoadBalanced` 来开启**负载均衡**，能够将请求分摊到其中的所有服务中

> 此处实质上就是使用了 ribbon 的负载均衡

### 拓展

#### 自我保护机制

> 在项目启动（注册新的客户端时），可能出现以下的信息
>
> #### **EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT. RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE.**
>
> 这就表示启动了 Eureka 的自我保护机制

##### 概述

保护模式主要用于一组客户端和 Eureka Server 之间存在网络分区场景下的保护，一旦进入保护模式，**Eureka Server 将会保护其服务注册表中的信息，不再删除服务注册表中的数据，也就是不会注销任何微服务**

简而言之，<u>如果某个时刻，某个微服务不可用了，Eureka 不会立刻清理，依旧会对改为服务信息进行保存</u>

> 使用的是 CAP 中的 AP 分支

##### 产生原因

==什么是自我保护机制？==

默认情况下，如果 Eureka Server 在一定时间内没有接收到某个微服务实例的心跳，Eureka Server 将会注销该实例（默认 90 秒）。但是在网络分区部长发生（延时、卡顿、拥挤）时，微服务与 Eureka Server 之间某个时间可能无法正常通信，以上的行为就变得非常危险了，因为微服务本身其实是健康得，此时<u>不应该注销该微服务</u>

Eureka 通过**自我保护机制**来解决这个问题，当 Eureka Server 节点在短时间内丢失过多客户端时（可能出现了网络分区故障），那么这个节点就会进入自我保护模式

*在自我保护模式中，Eureka Server 会保护服务注册表中的信息，不再注销任何服务信息*

此处使用的设计模式中就是<u>宁可保留错误的服务注册信息，也不会盲目注销任何可能健康的服务实例</u>

综上，自我保护模式是一种应对网络异常的安全保护措施，其中可以在可能出现网络异常的情况下（大量服务注销），保留所有的微服务，确保保留所有可能健康和不健康的微服务，也不会盲目注销任何健康的微服务。使用自我保护机制，可以让 Eureka 集群更加健壮、稳定（复用性增强）

==为什么会产生 Eureka 自我保护机制？==

为了防止出现 Eureka Client 依然可以正常使用，但是与 Eureka Server 网络不通的情况下，Eureka Server 直接将 Eureka Client 的服务信息剔除

##### 关闭自我保护

> 默认情况下，Eureka 开启了自我保护机制，可以通过配置的方式来关闭 Eureka 的自我保护机制

> 可以查看 `相关框架 > Eureka > 拓展 > 服务配置修改 > 关闭自我保护机制` 【[快速跳转](#关闭自我保护机制)】

###### 服务器配置

在 `application.yml` 中，配置 `eureka.server.enable-self-preservation` 为 `false`（默认为 `true`）

#### 服务配置完善

在普通配置的基础上，Eureka 的配置还是存在一些比较明显的缺陷，为了之后的使用方便，还需要进行一定的优化

1. 在 Eureka Server 中列出的注册信息，没有办法直接获取对应的 ip 值
2. 在 Eureka Server 中列出的注册信息，希望显示的服务对应的服务名称，并不需要它的域名信息

##### 关闭项目域名

<u>默认情况</u>下，在 Eureka Server 中显示的注册信息，会包括主机名称、项目的域名、注册名称和端口号

在项目的 `application.yml` 配置中，添加配置 `eureka.instance.instance-id` 指定项目显示的信息。通过这样的方式，就可以不使用 Eureka Server 的默认显示格式，转而使用 `eureka.instance.instance-id` 来显示信息

> 此处比较好用的使用方式是 `${spring.application.name}:${server.port}`
>

##### 显示 IP 信息

默认情况下，在 Eureka Server 中，显示注册的 ip 地址

在项目的 `application.yml` 配置文件中，添加配置 `eureka.instance.prefer-ip-address` 设置为 `true`（默认为 `false`）

通过这样的配置，可以在路径中显示 ip 地址

#### 服务注册信息

对于所有注册到 Eureka Server 中的微服务，可以通过服务来获取服务相关的服务信息

需要通过使用 `DiscoveryClient` 对象，来获取其中的信息

- `getService()` `return List<String>`

  返回注册中心的服务列表

- `getInstances()` `param serviceId: String` `return List<ServiceInstance>`

  根据 `serviceId` 来获取对应的服务信息

#### 服务配置修改

配置文件修改

##### 关闭自我保护机制

```yaml
eureka:
	server:
		# 默认为 true
		enable-self-preservation: false
```

##### 设置心跳时间

```yaml
eureka:
	server:
		# 设置的间隔时间为 ms
		eviction-interval-timer-in-ms: 2000
```

### 停更说明

> 官方的停更说明，可以查看 GitHub 中的说明
>
> https://github.com/Netflix/eureka/wiki

因为 Eureka 的停更，虽然依然可以继续使用，但是为了之后的长期使用，依然需要考虑转换为之后的其他的框架

可供选择的框架主要有

- Zookeeper
- Consul
- Nacos

## Zookeeper

Zookeeper 基本上使用的是 Eureka 的设计模式，基本上没有发生变化

### 概念

Zookeeper 因为基本上就是使用了与 Eureka 相同的设计架构，因此各方面都显得非常相似，其中的运行结构基本上也没有变化，被分为三个部分

- 注册中心
- 服务提供者
- 服务消费者

#### 注册中心

Zookeeper 是一种分布式协调工具，可以实现注册中心功能

Zookeeper 的服务器就取代了之前的 Eureka 服务器，Zookeeper 同样可以实现服务注册中心的功能

> 在关闭了 Linux 服务器防火墙之后，就可以启动 Zookeeper 服务器

#### 节点分类

在服务器中注册的节点，可以大致被分为两类：

- **临时节点**

  在一定的心跳时间之后，如果还是没有收到服务的响应，就会将其删除

- **持久节点**

  就算超出的了心跳时间，也不会将其删除，而是持久的保存在服务器中

通过简单的测试，Zookeeper 只是存放==临时节点==。 这样的设置，导致了多次注册的服务，每次也会分配不同的流水号。

> 同时 Zookeeper 使用自我保护机制，因此在没有接收到心跳响应就会直接清除，不会像 Eureka 一样保存一段时间。

### 使用过程

根据实际的项目，感受 Zookeeper 的使用

#### Zookeeper 安装

> 此处将Zookeeper安装到Linux CentOS7 系统下

1. 下载Zookeeper安装包

   前往Zookeeper官网，下载Zookeeper服务器

   http://zookeeper.apache.org/

2. 安装Zookeeper

   在官网下载的文件是是 `tar.gz` 格式的压缩包，使用 `tar -xvf [文件名]` 将压缩包进行解压。

   为了之后方便使用，可以修改文件夹名为 `zookeeper`。进入解压出的文件夹中 `conf` 目录，重命名其中的配置文件 `zoo_sample.cfg` 为 `zoo.cfg`

3. 配置环境变量

   修改化境配置，`vim /etc/profile` 在其中添加以下的配置

   ```bash
   #ZOOKEEPER配置
   export ZOOKEEPER_HOME=/opt/zookeeper # 此处指定的是具体安装的路径
   export PATH=$PATH:$ZOOKEEPER_HOME/bin
   ```

   修改完成后，刷新环境变量 `source /etc/profile`

4. 修改Zookeeper配置

   修改Zookeeper下的 `/conf/zoo.cfg` 文件

   - 修改其中的 `dataDir` 到安装路径下 `/opt/zookeeper/data`

#### Zookeeper 基本使用

此处将会列举简单的几个 Zookeeper 服务器的命令

```bash
# 开启zookeeper服务器
# 在配置了环境变量之后，可以使用如下的命令开启服务器；如果没有配置环境变量，就需要进入zookeeper的bin目录下，使用 ./zkServer.sh start 的方式开启服务器
zkServer.sh start

# 连接zookeeper服务器
zkCli.sh

# *************** 之后都是进入zookeeper服务器之后的命令 *********************
# 基本上，进入了zookeeper服务器的命令和linux系统的命令没有太大的差别

# 查看zookeeper版本
version

# 查看zookeeper中注册信息
# 此处必须使用 / 来显示所有的注册服务
ls [路径] # 必须使用以 / 开始的绝对路径

# 获取某个路径的具体信息
get [路径]

# 在某个路径下创建临时节点
create -e [路径] 节点名
```



### 构建项目

#### 服务提供者

> 为了与 Eureka 的项目做出区别，项目的将使用 7011 端口
>
> 其中的步骤基本与之前的 `springcloud-demo-eureka-server-port7001` 类似

其中的依赖基本上与之前的 Eureka 服务项目一致，其中只需要将 Eureka 服务器的依赖修改为 Zookeeper 的依赖

```xml
<!--zookeeper客户端-->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
</dependency>
```

项目的配置文件 `application.yml` 中，指定项目的端口号为 7011，之后还需要指定 zookeeper服务器注册的地址

```yaml
# 项目的端口号
server:
  port: 7011

spring:
  application:
  	# 项目的名字
    name: springcloud-zookeeper-service
  cloud:
    zookeeper:
      # 连接到 Linux 机器中的 Zookeeper 服务器
      connect-string: 192.168.56.200:2181
```

> 如果项目能够启动成功，会注册到服务器中的 Zookeeper 中，可以通过其中的命令，查看具体的注册信息
>
> ```bash
> # 查看是否有注册的信息，如果成功，会出现一个 services
> ls /
> # 查看其中注册的具体服务，默认的名称为spring.application.name中设定的名称
> ls /services
> # 之后可以通过具体的项目名称来获取到项目的一个流水号
> 
> # 获取到项目的注册详细信息，返回的是一个 json 字符串
> get /services/[项目名称]/[项目流水号]
> ```
>
> 

##### 特别注意

在基本项目中，可能出现的问题

###### 版本问题

在项目的启动时，可能出现因版本不一致直接抛出异常无法运行的错误

此处，主要需要解决的是项目依赖中的 `spring-cloud-starter-zookeeper-discovery` 中的 `spring-cloud-zookeeper-discovery` 与 `cureator-x-discovery` 下的 `zookeeper` 的版本差异

> 一般情况下，都是项目中的 `zookeeper` 比服务器中安装的版本更新导致的错误

在 POM 中，修改为如下的配置方式

```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
  <!--排除自带的zookeeper，使用指定版本的zookeeper-->
  <exclusions>
    <exclusion>
      <groupId>org.apache.zookeeper</groupId>
      <artifactId>zookeeper</artifactId>
    </exclusion>
  </exclusions>
</dependency>

<!--添加某个指定版本的zookeeper-->
<dependency>
	<groupId>org.apache.zookeeper</groupId>
  <artifactId>zookeeper</artifactId>
  <version>3.4.9</version>
</dependency>
```

#### 服务消费者

> 调用服务接口的项目
>
> 此处依然使用 80 端口

其中的依赖于服务提供者没有区别，依然是引入 `spring-cloud-starter-zookeeper-discovery` 

之后的配置文件 `application.yml` 中，配置也完全相同，只需要修改端口号

在主配置文件中的配置也相同，只需要添加 `@EnableDiscoveryClient` 注解

之后启动项目，应该会被注册到 Zookeeper 的 services 中，能够通过服务提供者的项目名称，从 Zookeeper 服务器中获取对应的地址。

## Consul

### 前置准备

#### 概念

> 来自官网 https://consul.io/intro/index.html

Consul 是一套开源的分布式服务开发和配置管理系统，由 HashiCorp 公司使用 <u>Go 语言</u>开发

其中提供了微服务系统中的**服务治理**、**配置中心**、**控制总线**等功能。这些功能中每个功能都能够单独使用，也可以同时使用以构建全方位的服务网络，总之 <u>Consul 提供了一种完成中的服务网格解决方案</u>。

其中主要具有如下的优点：

- 基于 raft 协议，比较简洁
- 支持健康检查，同时支持 HTTP 和 DNS 协议
- 支持跨数据中心的 WAN 集群（广域网集群）
- 提供图形界面
- 跨平台，支持 Linux、Mac、Windows

#### 功能

主要具有如下的功能点

- 服务发现

  提供 HTTP 和 DNS 两种发现方式

- 健康检查

  支持多种方式，HTTP、TCP、Docker、Shell脚本

- KV 存储

  Key-Value 键值对存储的方式

- 多数据中心

  Consul 支持多数据中心

- 可视化 Web 界面

  自带于其配套的 Web 可视化界面

#### 安装

> 可以参考
>
> - 中文教程 https://www.springcloud.cc/spring-cloud-consul.html
> - 官方教程【视频】 https://learn.hashicorp.com/consul/getting-started/install.html

> 此处使用的是 Windows 平台下的 consul 服务，理论上下载之后只会有一个 `exe` 文件

### 命令

使用 consul 的常用命令

> 此处的命令如果没有设置环境变量，就需要在安装的目录下使用

```bash
# 查看安装的版本
consul --version

# 管理员启动 consul
consul agent -dev
# 启动成功之后，就可以通过 http://localhost:8500 界面访问界面
```

> 此处查看到的 consul 界面，与 Eureka 的界面类似

### 项目实训

使用具体的项目，来感受 Consul 的使用

#### 服务提供者

1. 配置 POM

   ```xml
   <!--consul-->
   <dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-consul-discovery</artifactId>
   </dependency>
   ```

2. 编写配置文件

   <u>配置文件与之前的框架没有明显的区别</u>

   ```yml
   server:
     port: 8011
   
   spring:
     application:
       name: springcloud-consul-payment
     cloud:
       consul:
         # 注册中心
         host: localhost
         port: 8500
         discovery:
           # 注册的服务名
           service-name: ${spring.application.name}
   ```

3. 主启动类 & 控制器

   ```java
   /*com.tms.test.ConsulServicePaymentApplication*/
   @SpringBootApplication
   @EnableDiscoveryClient
   public class ConsulServicePaymentApplication {
     public static void main(String[] args) {
       SpringApplication.run(ConsulServicePaymentApplication.class, args);
     }
   }
   
   /*com.tms.test.controller.PaymentConsulController*/
   @RestController
   @Slf4j
   public class PaymentConsulController {
   
     @Value("${server.port}")
     private String serverPort;
   
     @RequestMapping("/test/consul")
     public String test() {
       return "Consul service[" + serverPort + "]";
     }
   }
   ```

> 在启动之后，其效果与 zookeeper 基本一致，在其中会出现一个 service 并以 `spring.application.name` 命名，其中可以查看具体的某个节点

#### 服务消费者

> 其中的配置与服务提供者完全一致
>
> 此处略

最终的使用方法与Zookeeper没有区别

## Ribbon

### 简介

Spring Cloud Ribbon 是基于 Netflix Ribbon 实现的一套<u>**客户端** *负载均衡*工具</u>

简而言之，Ribbon是 Netflix 发布的开源项目，主要功能是提供**客户端的软件负载均衡算法和服务调用**。Ribbon 客户端组件提供一系列完善的配置项，如连接连接超时重试等

在配置文件中，列出 Load Balancer（简称 LB）后面所有的机器，Ribbon会自动基于某种规则，如简单轮询，随即连接等方式，去连接这些机器。同时，其中也支持实现自定义的负载均衡算法

本质上，<u>Ribbon 就是负载均衡加 RestTemplate 的使用</u>

> 更多的资料可以查看官网 （GitHub界面）
>
> https://github.com/Netflix/ribbon/wiki/Getting-Started
>
> 在对应的 ReadMe 中，能够看到其中虽然还有部分在依然在大规模使用，但是整体已经进入了维护状态
>
> 其中的多项技术依然在大规模使用，说明<u>在短时间内都无法将其替换，但是整体的趋势是将转换到 Spring Cloud LoadBalancer</u>

#### 负载均衡

Load Balancer，简称 LB，简单来说就是将用户的请求分配到多个服务上，从而达到系统的**高可用**

常见的负载均衡有 Nginx、LVS、硬件 F5 等

##### Ribbon vs. Nginx

Nginx 是服务器负载均衡，客户端所有的请求都会交给 Nginx，然后由 Nginx 实现转发请求。即负载均衡，是由服务端实现的。

Ribbon 本地负载均衡，在调用微服务接口时，会在注册中心上获取注册信息服务列表之后缓存到 JVM 本地，从而在本地实现 RPC 远程服务调用技术

##### 分类

负载均衡根据其不同的实现方式，可以被分为两大类

- 集中式负载均衡

  **在服务的消费方和提供方之间使用独立的负载均衡设施**，可以是硬件，如F5，也可以是软件 Nginx，由该设施负责将所有的请求通过某种策略转发到服务的提供方

  Nginx 服务器来接收处理所有的请求，并实现请求的转发

- 进程内负载均衡

  将负载均衡的逻辑继承到消费方，消费方从服务注册中心获知那些地址可用，然后自己再通过这些地址来选择一个合适的服务器

  **Ribbon 就属于进程内负载均衡**，其本质就是一个类库，**集成于消费方进程**，消费方通过 Ribbon 来获取服务方的地址

#### 概念

根据 Ribbon 的架构，Ribbon 其实是一个软负载均衡的客户端组件，它可以与其他所需请求的客户端结合使用，和 Eureka 结合只是其中一个实例

其中的工作主要可以被分为两个步骤：

1. 选择 Eureka Server，其会优先选择同一个区域内负载较少的 server
2. 根据用户指定的策略，从 server 渠道服务注册列表中选择一个地址

> 其中 Ribbon 提供了多种策略，如**轮询**、**随机**和**根据响应时间加权**

### 负载均衡算法

在之前的 Eureka 客户端中，在使用 RestTemplate 时，就已经提到了使用其中的负载均衡，即 Ribbon。这是因为在新版的 Eureka 客户端依赖中，<u>自动引入了 Ribbon 的依赖</u>

> 因为 Ribbon 的使用其实就是结合 RestTemplate 的使用，因此，需要补充一点 RestTemplate 的使用
>
> https://docs.spring.io/spring-framework/docs/5.2.4.RELEASE/javadoc-api/org/springframework/web/client/RestTemplate.html
>
> 可以简单查看 `扩展知识 > 小型接口 > RestTemplate` 的描述

#### 修改

默认情况下，Ribbon 使用的是轮询的算法，可以通过配置的方式，对其进行修改

其中自带的负载均衡算法（或规则）都来自其`接口 IRule` 的实现类，此处列举最常见的 7 中实现

- `com.netflix.loadbalancer.RoundRobinRule` 【**默认**】

  轮询

- `com.netflix.loadbalancer.RandomRule`

  随机

- `com.netflix.loadbalancer.RetryRule`

  先按照 `RoundRobinRule` 的策略获取服务，如果获取失败就会在指定时间内进行重试，获取可用的服务

- `WeightedResponseTimeRule`

  对 `RoundRobinRule` 的拓展，响应速度越快的实例权重越大，越容易被选择

- `BestAvailableRule`

  会过滤掉由于多次访问故障而处于<u>短路跳闸状态</u>的服务，然后选择一个并发量最小的服务

- `AvailabilityFilteringRule`

  先过滤掉故障路由，并选择并发较少的实例

- `ZoneAvoiddanceRule`

  符合判断 server 所在区域的性能和 server 的可用性选择服务器

> 在官方文档中明确给出了警告：
>
> 其相关的配置类不能放在 `@ComponentScan` 所扫描的当钱包及其子包下，否者自定义的配置类会被所有的 Ribbon 客户端共享，达不到特殊定制的目的
>
> <u>简而言之，不要放在主启动类的包或以下的包，另起一个包，用于配置</u>

#### 原理

为了能够在之后可以实现自定义的负载均衡算法，需要大致了解负载均衡算法的大致原理或思路

##### 思路

`Rest接口请求次数 % 服务器居群总数 = 实际调用服务器位置下标`

每次服务重启之后，Rest 接口计数重置为 1

通过使用 `discoveryClient.getInstances([服务名])` 获取到服务名对应的所有服务器，之后根据请求的计数器来选择请求的服务器地址

##### 源码

在 Ribbon 中，是使用 IRule 来定义其中选择服务的规则，之后一个继承类 `AbstractLoadBalancerRule` 继承了 IRule，而 `AbstractLoadBalancerRule` 的子类才是真正实现了规则的类

其中定义了重要的方法 

- `choose` `param lb: ILoadBalancer, key: Object` `return Server`

  根据负载军很算法，以及对应的 `key` 值来选出最终的服务

  其中有一个重要的方法，通过使用 `ILoadBalancer` 的 `getReachableServers()` 方法，可以获取到所有可以接通（可达）的服务


> 可以结合 `RoundRobinRule` 的源码进行学习

#### 自定义

根据负载均衡算法的原理，可以使用同样的思路，来实现自定义的负载均衡算法

<u>首先，为了能够证明是使用自定义的负载军很算法而非内部自带的负载均衡算法，需要现在服务消费者中将原本的 `RestTemplate` 注册上的 `@LoadBalanced` 注解注释掉</u>

## OpenFeign

> 微服务之间的服务调用，因为 `Feign` 已经停止更新，基本上不需要再进行研究，此处直接略过，直接开始 `OpenFeign`

### 简介

> 官网解释
>
> https://cloud.spring.io/spring-cloud-static/Hoxton.SR1/reference/htmlsingle/#spring-cloud-openfeign
>
> GitHub
>
> https://github.com/spring-cloud/spring-cloud-openfeign

<u>Feign 是一个**声明式**的 **WebService 客户端**</u>，使用 Fegin 能够让编写 Web Service 客户端更加简单

使用的方法就是，==定义一个服务接口，然后在上面添加注解==。Feign 也支持可插拔式的编码器和解码器。Spring Cloud 对 Feign 进行了封装，使其支持了 Spring MVC 标准注解和 HttpMessageConverters

Feign 可以与 Eureka 和 Ribbon 组合使用以支持**负载均衡**

#### 功能

> Feign 能干什么？

<u>Feign 旨在使编写 Java Http 客户端更加容易</u>

在之前使用 Ribbon + RestTemplate 时，利用 RestTemplate 对 http 请求的封装处理，形成了一套模板化的调用方法

在实际开发中，由于对服务以来的调用可能不止一处，**往往一个接口会被多处调用，所以通常针对每个微服务自行封装一些客户端类来包装这些以来服务的调用**

所以，Feign 在此基础上做了一些封装，能够定义并实现以来服务接口的定义。在 Feign 的实现下，<u>只需要创建一个接口并使用注解的方式来进行配置</u>（之前是 Dao 接口上标注 Mapper 注解，现在是在一个微服务接口上标注一个 Feign 注解），即可完成对服务提供方的接口绑定，简化了使用 Spring Cloud Ribbon 时，自动封装服务调用客户端的开发量

> 此外，==Feign 还集成了 Ribbon==，能够通过定义服务绑定接口并且以声明式的方法，优雅而简单的实现了服务调用

#### Feign 与 OpenFeign

==Feign==

Feign 是 Spring Cloud 组件中的一个轻量级 RESTful 的 Http 服务客户端

其中内置了 Ribbon，用于实现客户端的负载均衡，去调用服务注册中心的服务。Feign 的使用方式是：使用 Feign 注解来定义接口，调用接口就可以去调用服务注册中心的服务

```xml
<!--Fegin-->
<dependency>
	<groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-feign</artifactId>
</dependency>
```

---

==OpenFeign==

OpenFeign 是 Spring Cloud 在 Feign 的基础上支持了 Spring MVC 的注解，如 `@RequestMapping` 等

OpenFeign 的 `@FeignClient` 可以解析 SpringMVC 的 `@RequestMapping` 注解下的接口，并通过动态代理的方式来产生实现类，实现类中做到了负载均衡并调用其他服务

```xml
<!--OpenFeign-->
<dependency>
	<groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

### 使用

> 通过之前的概念可知，Feign 应该使用在消费者/客户端侧
>
> 此处新建客户端 module `springcloud-demo-consumer-feign-order-port80`

#### 项目实例

此处为 `springcloud-demo-consumer-feign-order-port80` 的具体构建过程

##### 依赖

```xml
<dependencies>
  <!--openfeign-->
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
  </dependency>
  <!--eureka client-->
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
  </dependency>

  <!--springboot web-->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
  </dependency>

  <!--通用配置-->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
  </dependency>
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>

  <!--自定义接口-->
  <dependency>
    <groupId>com.tms.test</groupId>
    <artifactId>springcloud-commons-api</artifactId>
    <version>${project.version}</version>
  </dependency>
</dependencies>
```

---

##### 配置文件

> 基本上与之前的服务消费者的配置文件没有区别

```yaml
server:
  port: 80

spring:
  application:
    name: springcloud-consumer-feign-order
eureka:
  client:
    register-with-eureka: false
    service-url:
      defaultZone: http://localhost:7001/eureka,http://localhost:7002/eureka
```

---

##### 主启动类

在原项目的基础上，因为需要使用 FeignClient 的注解，必须要先启用对应的注解

```java
@SpringBootApplication
@EnableEurekaClient
// 启动 @FeignClient 注解
@EnableFeignClients
public class OrderFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignApplication.class, args);
    }
}
```

---

##### 使用注解[**重要**]

在项目中使用，只需要编写一个接口，其中的具体代码交由 Feign 进行实现

```java
@Service
// 其中需要指定具体的某个服务的名称（Eureka 注册中心注册的名称）
@FeignClient("SPRINGCLOUD-PAYMENT-SERVICE")
public interface OrderFeignService {
    /*
    可以在此处直接写上请求服务的方法头，Feign就能直接使用服务侧的对应方法来获取值
    CommonResult<Payment> getPaymentById(Long id);
     */

    // 其中还支持第二种方式，直接使用控制器层的方法，其中会根据某个具体的服务请求地址来调用方法，并实现相同的效果
    @GetMapping("/{id}")
    CommonResult<?> getPaymentById(@PathVariable("id") Long id);
}
```

> 其中，主要需要注意以下内容：
>
> - 在 `@FeignClient` 注解中，需要指定其使用的某个服务，之后的接口方法就会根据这个具体的服务来实现
>
> - 接口中，可以选择使用服务提供者（此处就对应的是两个 Payment 服务的提供者）中的 service 方法或是 controller 方法
>
>   其中 controller 方法，需要包括其中的 `@RequestMapping` 注解（包括 `@GetMapping` 和 `@PostMapping` 等）
>
> - 为了方便使用，需要加入容器

### 拓展

#### 超时控制

在 Feign 中，为了提高客户使用的效率，设置了特别的超时控制，默认情况下， 如果服务消费者访问服务提供者超过 1 秒钟没有获取响应，就会出现超时异常（Read timed out executing）

> 如果希望测试，可以在消费提供方的方法中休眠超过1秒钟，就可以触发这样的异常

为了避免这样的异常，可以在配置文件中设置 `OpenFeign` 客户端的超时控制

```yaml
ribbon:
	# 建立连接的时间，适用于网络状况正常的情况下，两端连接所用的时间（单位ms）
	ReadTimeout: 5000
	# 建立连接后从服务器读取到可用资源所用的时间（单位 ms）
	ConnectTimeout: 5000
```

#### 日志打印

Feign 在整个使用过程中，并没有对外暴露具体的过程，如果希望了解其中的细节，就需要通过日志的方式打印出来

在 Feign 中默认支持通过配置的方式，开启日志的打印。其中，会根据不同的日志打印级别来显示不同级别的过程信息：

- `NONE` 【默认】

  Feign 的默认日志打印级别，不会显示任何的日志信息

- `BASIC`

  仅仅记录请求方法、URL、响应状态码以及执行时间

- `HEADERS`

  在 `BASIC` 日志的基础上，还有请求和响应头信息

- `FULL`

  除了 `HEADERS` 中定义的信息之外，还有请求体和响应体中的具体内容

需要使用一个**配置类**

```java
@Configuration
public class FeignConfiguration {
  
  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }
}
```

> ==特别强调==
>
> 此处的 `Logger` 是 `feign.Logger`

之后，还需要在配置类中，配置 Feign 日志的监控级别（设置了打印日志的级别，但是还没有设置监控日志的级别）

```yaml
logging:
	level:
		# 指定某个具体的接口（及Feign处理的接口），以及其对应的监控级别
		com.tms.test.service.OrderFeignService: debug
```

### 



# 拓展知识

并不包含在 SpringCloud 中的知识点，本项目中可能涉及到的内容

## 小型接口

在学习过程中，需要使用到的零碎的几个小型接口，可能是某个类的使用。

### RestTemplate

> 因为 Ribbon 的使用其实就是结合 RestTemplate 的使用，因此，需要补充一点 RestTemplate 的使用
>
> https://docs.spring.io/spring-framework/docs/5.2.4.RELEASE/javadoc-api/org/springframework/web/client/RestTemplate.html

#### 常用方法

- > GET 请求

- `getForObject` `param url, clazz`

  返回对象为响应体中数据转化成的对象，基本上可以理解为 Json

- `getForEntity`

  返回对象为 ResponseEntity 对象，包含了响应中的一些重要的信息，如**响应头**、**响应状态码**、**响应体**等

  ---

- > POST 请求

- `postForObject`

- `postForEntity`

## Maven

### 依赖管理

Maven 中的 `<dependencyManagement>` 标签，其主要的功能就是在父项目中实现控制依赖的功能，一般与 `<dependencies>` 进行比较

---

`<dependencyManagement>` 元素，是 maven 提供的一种管理依赖版本号的方式，==通常会在一个组织或者项目的顶层的父POM（指将项目最终打包为POM）中看到使用 `<dependencyManagement>` 元素==

使用 pom.xml 中的 `dependencyManagement` 元素能够让所有在子项目中引用一个依赖而不需要显式的列出版本号。Maven 会沿着父子层次向上查找，直到查找到一个拥有 `dependencyManagement` 元素的项目，之后就会使用其中定义的版本号

> 简而言之
>
> 在夫项目中，使用 `<dependencyManagement>` 管理版本；在子项目中，可以直接使用 `<dependencies>`，同时不需要指定版本，会默认使用父项目中控制的版本

---

通过版本控制，主要存在如下的好处：

如果多个子项目都引用同一样依赖，则可以避免在使用每个使用的子项目中声明一个版本，这样当想升级或切换到另一个版本时，只需要在顶层的父容器/父项目中更新依赖，就能够直接控制整个项目中的依赖版本

如果在某个子项目中，需要使用另一个版本的依赖，只需要在其中额外指定一个 `version` 参数就能实现该功能

> - `dependencyManagement` 中只是声明依赖，**并不实现引入**，因此子项目需要显式的声明需要的依赖（只是规范版本，并不会直接引入依赖）
> - 如果不在子项目中声明依赖，是不会从父项目中继承下来的；只有在子项目中写了该依赖项，并且没有指定具体版本，才会从夫项目中继承该项，并且 `version` 和 `scope` 都读取自父 pom
> - 如果子项目中指定了版本号，那么会使用子项目中指定的 `jar` 版本

### 单元测试

Maven 中跳过单元测试，跳过 maven 中不必要的测试过程

通过点击 idea 中，maven 菜单，上方有一个小闪电标志

其成功的标志是，`lifecycle` 中的 `test` 出现了一个~~删除标记~~

> 之后，需要使用其中的 `lifecycle` 中的 `install` 来加入 maven 仓库，方便之后使用

## IDEA

IDEA 在 SpringCloud 项目中可能遇到的情况

### 多module运行

在 IDEA 中，如果是多 module 的项目，在运行时会自动转换为 `Run Dashborad`（替换了原本的 `Run` 窗口）

一般情况下，会自动进行这样的转换，但是可能会出现因为 IDEA 的版本不同，而导致不会自动开启的情况，可能需要手动修改 IDEA 的 `workspace.xml` 的方式来开启 `Run Dashboard` 的窗口

> 在 IDEA 2020 中改名为 `service`

### 热部署

能够在项目代码发生变化时，自动进行更新部署（重启，加载）

此处主要通过子工程中的 `springcloud-demo-payment-port8001` 进行演示

> 此处使用的是 `devtools`

### 使用流程

1. 添加依赖

   希望在项目中使用 `devtools` 热部署工具，需要首先修改项目的 `pom.xml`，在其中添加相关的依赖

   ```xml
   <dependencies>
     ...
     <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-devtools</artifactId>
       <scope>runtime</scope>
       <optional>true</optional>
     </dependency>
     ...
   </dependencies>
   ```

2. 添加插件

   为项目添加一个插件，一般直接添加到夫工程中，就不需要之后的各个项目都添加

   ```xml
   <build>
     <plugins>
       <plugin>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-maven-plugin</artifactId>
         <configuration>
           <fork>true</fork>
           <addResources>true</addResources>
         </configuration>
       </plugin>
     </plugins>
   </build>
   ```

   > 如果是 `Spring Initializer` 创建的项目，应该是默认有这个配置，是 Spring 提供的 maven 插件

3. 开启自动编译的选项

   在 IDEA 的设置中 `Build, Execution, Deployment` 中的 `Complier` 开启如下的几项设置：

   - `Automatically show first error in editor`
   - `Display notification on build completion`
   - `Build project automatically`
   - `Compile independent modules in parallel`

4. 更新编译值

   在项目中，使用 `ctrl+shift+alt+/` 的快捷键，查看其中的 `Registry`

   找到并开启其中的 

   - `compiler.automake.allow.when.app.running`
   - `actionSystem.assertFocusAccessFromEdit`

   之后直接点击 `close` （另一个键恢复默认）

5. 重启 IDEA

   重新加载设置，之后可以重启项目，如果此时对项目中的代码进行了修改，就会自动重启项目

> 通过这样的方式，能够加速项目编译阶段的调试速度（当然也会因为频繁重启项目导致电脑性能降低）
>
> ==只允许在开发阶段开启热部署==

## 注册中心

对注册中心的总结性知识

### 三个注册中心的异同

> 此处的三个注册中心为 **Eureka**、**Zookeeper**、**Consul**

| 组件名    | 语言 | CAP  | 服务健康检查 | 对外暴露接口 | Spring Cloud 集成 |
| --------- | ---- | ---- | ------------ | ------------ | ----------------- |
| Eureka    | Java | AP   | 可配置支持   | HTTP         | 已集成            |
| Consul    | Go   | CP   | 支持         | HTTP/DNS     | 已集成            |
| Zookeeper | Java | CP   | 支持         | 客户端       | 已集成            |

> **CAP 概念**
>
> CAP 理论的核心，<u>一个分布式系统不可能同时满足**一致性**、**可用性**和**分区容错性**这三个需求</u>，因此，根据 CAP 原理将 NoSQL 数据库分为满足 CA 原则、满足 CP 原则和满足 AP 原则三大类
>
> - A，Availability，可用性
> - C，Consistency，强一致性
> - P，Partition Tolerance，分区容错性
>
> <u>目前只能同时较好的满足两个</u>，其中的具体原则
>
> - CA - 单点集群，满足**一致性**和**可用性**的系统，通常在可拓展性上不太强大
> - CP - 满足**一致性**和**分区容忍性**的系统，通常性能不是很高
> - AP - 满足**可用性**和**分区容忍性**的系统，通常对一致性要求较低
>
> 因为微服务必然需要保证*分区容错性*，即必然会保证P，同时CAP不可能同时满足，因此只能是AP或者CP



[^1]: 证明自身依然在提供服务，此处为 Eureka Server 向 Service Provider 证明自身存活
[^2]: Remote Procedure Call，远程过程调用

