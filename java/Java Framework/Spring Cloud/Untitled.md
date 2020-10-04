## Hystrix

### 概述

> 官网资料
>
> https://github.com/Netflix/Hystrix/wiki/How-to-Use

#### 分布式系统面临的问题

在正式开始学习 Hystrix 之前，需要了解目前分布式系统所面临问题：**<u>复杂的分布式体系结构中的应用程序有数十个依赖的关系，每个关系在某个时候都可能面临不可避免地失败</u>**

简而言之，分布式系统的复杂性上升，其中的某个节点及某个模块出现异常之后，就会导致整个系统都出现异常，及==服务雪崩==

> ==服务雪崩==
>
> 多个微服务之间调用的时候，假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其他的微服务，这就是所位的**扇出**（服务调用越来越多，波及面越来越广的现象）。如果扇出的链路上某个微服务的调用相应时间过长或者不可用，对微服务A的调用就会占用越来越多的系统资源，进而引起系统崩溃，这就是所谓的<u>**雪崩效应**</u>
>
> 对于高流量的应用来说，单一的后端依赖可能会导致所有服务器上的所有资源都在几秒钟内饱和，比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份队列、线程和其他系统资源紧张，导致整个系统发生更多的级联故障。这些都表示需要对故障和延迟进行隔离和管理，一边单个依赖关系的失败，不能取消整个应用程序或系统
>
> 所以，通常当发现某个模块下的某个实例失败后，如果这个模块还会接收流量，然后这个出现问题的模块还调用了其他的模块，这样就会发生级联故障，也被称为雪崩

Hystrix 就是为了解决这样的问题而产生的

#### 简介

Hystrix 是一个用于处理分布式系统的**延迟**和**容错**的开源库，在分布式系统中，许多以来不可避免的会出现调用失败，如超时、异常等，Hystrix 能够保证一个依赖出现问题的情况下，**不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性**

==断路器==本身是一种开关装置，当某个服务电源发生故障之后，通过断路器的故障监控（类似熔断保险丝），**向调用方返回一个符合预期、可处理的备选响应（FallBack），而不是长时间的等待或者抛出调用方法无法处理的异常**，这样就<u>保证了服务调用方的线程不会被长时间、不必要的占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩</u>

### 概念

在 Hystrix 中所提到的几个重要的概念

- 服务降机 `fallback`
- 服务熔断 `break`
- 服务限流 `flowlimit`

#### 服务降级

**fallback，服务器忙，请稍后再试，不让客户端等待并返回一个友好提示**

可以让服务请求者在面对长时间的等待时，直接返回一个友好的提示，停止其等待

一般可以在以下的情况时，会触发服务降级：

- 程序运行异常
- 超时
- 服务熔断
- 线程池/信号量打满

#### 服务熔断

**break，在服务的访问量达到上限时触发熔断机制，直接拒绝后续访问（可以类比保险丝），之后会随着访问量的下降慢慢恢复链路**

<u>某种意义上，也属于服务降级</u>

#### 服务限流

flowlimit，在高并发的场景中，如请求数量波动明显的场景（如，商品秒杀），防止请求拥挤，采用排队的方式，固定时间放行指定数量的请求，保证整个过程有序进行

### 具体使用



#### 项目构建

##### 服务提供者

> 新建 module `springcloud-demo-hystrix-payment-port8001`

> ==此处为了简化操作，将 Eureka 的服务注册中心恢复成单机版==（7001）

###### 依赖

与之前的服务提供者基本一致，只是多了一个 Hystrix 依赖

```xml
<!--hystrix-->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```

###### 配置文件

```yaml
server:
  port: 8001

spring:
  application:
    name: springcloud-hystrix-payment

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      # 此处为了简化操作，将 eureka 注册中心恢复成了单机项目，因此只需要指定 7001 即可
      defaultZone: http://localhost:7001/eureka
      # 如果是集群项目，还是需要指定所有的服务注册中心
```

###### 主启动类

```java
@SpringBootApplication
@EnableEurekaClient
public class PaymentHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixApplication.class, args);
    }
}
```

###### 服务层

> 此处为了简化开发，没有创建接口，而是直接创建了一个服务层类

```java
@Service
public class PaymentService {

    /**
     * 正常访问，必然能够成功的你发给发
     */
    public String PaymentInfoOK(Integer id) {
        return Thread.currentThread().getName() + "[PaymentInfoOK] id=" + id;
    }

    /**
     * 用于模拟超时的情况
     */
    public String PaymentInfoTimeout(Integer id) {
        int time = 3;
        try {
            // 睡3秒
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName() + "[PaymentInfoTimeout(" + time + "')] id=" + id;
    }
}
```

在类中定义了两个方法，分别代表了正常的**访问情况**以及**有延迟**的情况

###### 控制层

> 分别调用服务层的两个方法

```java
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String PaymentInfoOK(@PathVariable("id") Integer id) {
        String result = paymentService.PaymentInfoOK(id);
        log.info(result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String PaymentInfoTimeout(@PathVariable("id") Integer id) {
        String result = paymentService.PaymentInfoTimeout(id);
        log.info(result);
        return result;
    }
}
```

##### 服务消费者

> 在单纯使用**服务提供者**的场景下，高并发的测试中，项目的所有接口都出现了较大的延迟但是依然可以正常地访问
>
> 为了进一步测试，此处引入**服务消费者**

> 构建 module `springcloud-demo-consumer-feign-hystrix-order-port80`

###### 依赖

```xml
<dependencies>
  <!--hystrix-->
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
  </dependency>

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

###### 配置文件

```yaml
server:
  port: 80
  
eureka:
  client:
    register-with-eureka: false
    service-url: 
      defaultZone: http://localhost:7001/eureka
```

###### 主启动类

```java
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OrderFeignHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignHystrixApplication.class, args);
    }
}
```

###### 业务层

```java
@Service
@FeignClient("SPRINGCLOUD-HYSTRIX-PAYMENT")
public interface OrderService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String PaymentInfoOK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String PaymentInfoTimeout(@PathVariable("id") Integer id);
}
```

###### 控制层

```java
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String PaymentInfoOK(@PathVariable("id") Integer id) {
        String result = orderService.PaymentInfoOK(id);
        log.info(result);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String PaymentInfoTimeout(@PathVariable("id") Integer id) {
        String result = orderService.PaymentInfoTimeout(id);
        log.info(result);
        return result;
    }
}
```

#### 高并发测试

目前的项目（只有服务提供者），在非高并发场景下，两个方法都能够正常访问；相对的，如果在高并发场景下，则会有所不同

此处为了模拟高并发场景，使用了 Apache 提供的测试工具，`Apache JMeter` 能够在其中设置高并发的请求测试，向此处的接口快速发出请求

> 本次的测试使用线程池，具体参数如下：
>
> **200**个线程，在 **1** 秒内，都发出 **100** 次请求（每个线程，及总共 **20000** 个请求）

结果，在高并发的情况下，<u>就算是没有休眠的接口访问也开始变慢，但是两个接口依然可以成功访问</u>

这是因为 Tomcat（服务器）中的默认工作线程都被高并发的请求占用，导致没有足够的线程来分解压力和处理

此时，因为只有一个服务提供者，是采用了直接访问的方式测试接口，在高并发的场景下，已经出现了较大的延迟导致服务消费者也只能进入等待状态，造成了消费者性能的浪费

---

添加了服务消费者之后，其中使用了 Feign 来实现对服务提供者的请求，对其中延迟超过一秒的接口请求，导致了 `Read timed out` 异常

在高并发的场景下， 就算是之前的 `OK` 接口（即没有休眠的接口，也因为请求量的上升导致了延迟极速提升），也可能出现 `Read timed out` 异常，而 Hystrix 正是用于处理这样的问题的诞生的

#### 解决

目前的项目中，主要可能出现如下的两个方面问题：

- 超时导致服务器变慢 - 超时不再等待
- 出错/异常 - 出错要有兜底

---

通过这样的思路，来对项目中的问题进行解决

- 服务提供者（8001）超时了，服务消费者（80）不能一直等待，转而使用服务降级
- 服务提供者（8001）down机/异常，服务消费者（80）不能一直卡死等待，必须有服务降级
- 服务提供者（8001）正常运行，服务消费者（80）出现故障或是自身设定的等待时间较短（比服务提供者正常运行的提供时间还短），自己处理降级

##### 具体实现

在了解解决的思路之后，开始准备真正的解决问题

其中，需要使用到 Hystrix 的注解 `@HystrixCommand`

对于不同的角色（服务提供者/服务消费者）有不同的解决

###### 服务提供者

通过之前的分析，其中需要**<u>设置自身调用超时时间的峰值，峰值内可以正常运行，超过了就需要有兜底的方法处理，做服务降级 `fallback`</u>**

1. 在**主业务类**上添加注解 `@HystrixCommand`，用于标识出现异常之后的处理

   在方法上，需要指定 `fallback` 的对应方法，以及调用 `fallback` 方法的时机

   ```java
   @HystrixCommand(fallbackMethod = "paymentInfoFallback", commandProperties = {
     // 如果超过 3000ms/3s，就会执行fallback方法
     @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
   })
   public String paymentInfoTimeout(Integer id) {
     // 设定正常境况下，这个方法会执行3秒钟
     // int time = 3;
   
     // 设置异常情况，方法执行超过3秒，会触发方法上的指定的fallback方法
     int time = 5;
     try {
       // 睡3秒
       TimeUnit.SECONDS.sleep(time);
     } catch (InterruptedException e) {
       e.printStackTrace();
     }
     return Thread.currentThread().getName() + "[paymentInfoTimeout(" + time + "')]id=" + id;
   }
   
   /**
        * 兜底的方法，在 paymentInfoTimeout 方法超时（超过3秒）后自动调用
        */
   public String paymentInfoFallback(Integer id) {
     return Thread.currentThread().getName() + "[paymentInfoTimeout]:[fallback]id=" + id;
   }
   ```

   > `@HystrixCommand` 中需要指定以下的属性
   >
   > 1. 需要指定 `fallbackMethod` 属性为某个具体的方法，用于表示其实现兜底的具体方法
   >
   > 2. 还需要指定 `commandProperties` 其中来决定何时调用 `fallback` 的方法
   >
   >    在其中接收的是 `HystrixProperty` 的数组，因此使用了大括号，每个 `HystrixProporty` 接收的是一个键值对