# JVM

> by https://www.bilibili.com/video/BV1PJ411n7xZ

## 序

### 推荐书目

1. 官方介绍【规范，不太推荐，一般用于查询不用于学习】

   `The Java Virtual Machine Specification`

   > 全英文，需要一定的基础
   >
   > 推荐 Java8、Java11

2. 中文参考书

   - `Java虚拟机规范`（官方 SE 8的翻译版，不太对剑）
   - `深入理解Java虚拟机` 周志明 著 （2、3版）

### 虚拟机选择

JVM 的选择，此处选择的是默认的 `HotSpot` 虚拟机

## 简介

> TIOBE 权威网站，每个月更新各个语言的排行信息 https://www.tiobe.com/tiobe-index/

JVM 的最大特点就是面向字节码文件，主要满足了其标准，就能直接进行处理运行

因为 JVM 的特性，导致之后的多语言混合编程必然是未来的趋势。在其中的代码都能够调用各自语言的接口，并最终生成一个满足 JVM 的规范，就能直接都交给 JVM 进行处理

各个语言之间的交互将不再存在任何困难，就像是使用自己的原生 API 一样方便，因为其最终都会运行在一个虚拟机上

这样的特性，<u>推动 Java 虚拟机从 **Java 语言的虚拟机** 向 **多语言虚拟机** 的方法发展</u>

### 虚拟机

所谓虚拟机（Virtual Machine），就是一台虚拟计算机。可以将其理解为一款如那件，用来执行一系列虚拟计算机指令

大体上，虚拟机可以被分为两类：

- 系统虚拟机

  对物理计算机的仿真，提供了一个可运行完整操作系统的软件平台

  如，Visual Box、VMware 就属于系统虚拟机

- 程序虚拟机

  专门用于执行单个计算机程序而设计

  最典型的代表就是 Java 虚拟机，在 Java 虚拟机中执行的指令就可以被称为 Java 字节指令

无论是系统虚拟机还是程序虚拟机，在上面运行的软件就被限制于虚拟机提供的资源中

#### 架构模型

在主流的虚拟机中，一般有两种架构模型，<u>栈的指令集架构</u>和<u>寄存器的指令集架构</u>

- 栈的指令集架构

  > JVM 使用的架构模型

  - 设计和实现更简单，是用于资源受限的系统
  - 避开了寄存器的分配难题：使用了零地址指令分配
  - 指令流中的指令大部分是<u>零地址指令</u>，其执行过程依赖于操作栈。指令集更小，编译器更容易实现
  - 不需要硬件支持，可以执行好，更好实现跨平台

- 寄存器的指令集架构

  > 典型的应用是 ×86 的二进制指令集：如传统的 PC 以及 Android 的 `Davlik` 虚拟机

  - 指令集架构则完全依赖硬件，可移植性差
  - 性能优秀和执行更高效
  - 花费更少的指令去完成一项操作
  - 在大部分情况下，基于寄存器架构的指令往往都以<u>一地址指令、而地址指令和三地址指令</u>为主

> ##### JVM 架构模型
>
> **由于跨平台的设计，Java 的指令是根据栈来设计的。**不同平台 CPU 架构不同，所以不能设计基于寄存器的
>
> <u>优点是跨平台，指令集小，编译容易实现；缺点是性能下降，实现同样的功能需要更多的指令</u>
>
> 虽然嵌入式平台已经不是 Java 程序的主流平台了，准确的说应该是 `HotSpot VM` 的宿主环境已经不局限于嵌入式平台



### JVM

**Java 虚拟机是一个执行符合 JVM 标准的字节码文件的虚拟计算机**，拥有独立的运算机制，<u>其运行的字节码文件并不只针对 Java 字节码文件</u>

JVM 平台的各种语言可以共享 Java 虚拟机带来的<u>跨平台性</u>、<u>优秀的垃圾回收机制</u>以及<u>可靠的即时编译器</u>

Java 技术的核心就是 Java 虚拟机（JVM，Java Virtual Machine），因为所有的 Java 程序都运行在 Java 虚拟机内部

#### 作用

**Java 虚拟机就是二进制字节码的运行环境**，负责装载字节码到内部，解释或编译为对应平台上的机器指令进行执行

每一条 Java 指令，Java 虚拟机规范中都有详细定义，如怎么取操作数，怎么处理操作数，处理结果放在哪里

#### 特点

- 一次编译，到处运行
- 自动内存管理
- 自动垃圾回收功能

#### 总体结构

目前 `HotSpot VM` 是目前市面上性能虚拟机的代表之一，其采用<u>解释器与即时编译器并存的架构</u>

如今的 Java 程序性能因为虚拟机的发展，逐渐达到了 C/C++ 的程度

![JVM-概念-总体结构图](img/JVM-%E6%A6%82%E5%BF%B5-%E6%80%BB%E4%BD%93%E7%BB%93%E6%9E%84%E5%9B%BE.png)

> 1. 其中的通过输入字节码文件 `Class Files` 进入 JVM
>
> 2. 首先会经过的就是类装载器 `Class Loader`，其目的就是将字节码文件加载到内容中，生成一个大的 `Class` 对象
>
> 3. 借助 `Class Loader` 的装载，产生了 `Class` 的实例，其中存在运行时数据区 `Runtime Data Area`，用于存放和处理其中需要的数据。根据功能作用的不同，被分为五个部分：
>
> 	- 方法区 `Method Area`
> 	- 堆 `Heap`
> 	- Java 栈 `Java Stack`（之后更名为虚拟机栈）
> 	- 本地方法栈 `Native Method Stack`
> 	- 程序计数器 `Program Counter Register`
> 	
> 	<u>其中的方法区和堆是多线程共享的；相对的，Java 栈、本地方法栈、程序计数器就是各个线程唯一的</u>
> 	
> 4. 之后还有用于处理项目的执行引擎 `Execution Engine`，其中大致包括<u>解释器、JIT 即时编译器以及垃圾回收器</u>
>
>    对于计算机而言，字节码文件不等于机器指令，需要通过执行引擎的解释执行
>
> 5. 本地方法接口 `Native Interface`

> 这是网上较为常见的一张结构图，其中的部分结构进行了省略

下图为 JVM 的详细结构图

![JVM-概念-总体结构图-详细](img/JVM-%E6%A6%82%E5%BF%B5-%E6%80%BB%E4%BD%93%E7%BB%93%E6%9E%84%E5%9B%BE-%E8%AF%A6%E7%BB%86.png)

#### 生命周期

JVM 的生命周期变化

与常规的生命周期一致，分为创建、服务、销毁

##### 虚拟机的启动

Java 虚拟机的启动是通过引导类加载器 `bootstrap class loader` 创建一个初始类 `initial class` 来完成，这个类是由虚拟机的具体实现指定的

##### 虚拟机的执行

一个运行的 Java 虚拟机有一个清晰的任务：<u>执行 Java 程序</u>

程序开始执行才会执行，程序结束时就会停止

**执行一个所谓的 Java 程序时，真真正正执行的是一个叫做 Java 虚拟机的进程**

##### 虚拟机的退出

主要有如下的几种情况：

- 程序正确执行结束
- 程序在执行过程中遇到了异常或错误而异常终止
- 由于操作系统出现错误而导致 Java 虚拟机进程终止
- 某线程调出 `Runtime` 类或 `System` 类的 `exit()` 方法，又或者 `Runtime` 类的 `halt()` 方法，并且 Java 安全管理器也允许这次 `exit` 或 `halt` 操作
- 除此之外，JNI（Java Native Interface）规范描述了用 JNI Invocation API 来加载或卸载 Java 虚拟机时，Java 虚拟机的退出情况

在源码中，`exit()` 方法接收一个数字，如果数组非0，就会自动调用 `halt()` 方法

#### 常见的 Java 虚拟机

主要列举了如今最常见的三款 Java 虚拟机，此外还有其他的 Java 虚拟机

> 此外，还有之前已经被淘汰的版本，如 `classis VM`、 `exact VM`

##### HotSpot 虚拟机

最初是由 `Longview Technologies` 的小公司设计。该公司在 `1997` 年被 `Sun` 公司收购；`2009` 年，`Sun` 公司又被 `Oracle` 公司收购

从 JDK1.3 开始，`HotSpot VM` 就被作为默认虚拟机

###### 特点

1. 相比起另外两款商用虚拟机，`HotSpot` 拥有方法区

2. 其中主要使用了热点代码技术

   热点代码技术就是通过计数器找到最具编译价值的代码，触发即时编译或栈上替换
   
   通过编译器与解释器协同工作，在最优化的程序响应时间与最佳执行性能中取得平衡

#####  JRockit 虚拟机

> 来自 BEA

专注于服务器端的应用。可以不太关注程序启动速度，因此 <u>`JRockit` 内部不包含解析器实现，全部代码都靠即时编译器编译后执行</u>

***JRockit JVM 是世界上最快的 JVM***（运行最快，启动较慢）

###### 优势

`JRockit` 面向延迟敏感性应用的解决方案 `JRockit Real Time` 提供以毫秒或微秒级的 `JVM` 响应时间，适合财务、军事指挥、电信网络的需要

`MissionControl` 服务套件，是一组以极低的开销来监控、管理和分析生产环境的应用程序的工具

##### J9 虚拟机

J9，IBM Technology for Java Virtual Machine，简称 IT4J，内部代号为 J9

市场定位于 `HotSpot` 接近，服务器端、桌面应用、嵌入式等多用途 VM，被广泛用于 IBM 的各种 Java 产品

是目前最有影响力的三大商用服务器之一，也<u>号称是世界上最快的 Java 虚拟机</u>

## 过程分析

将对 JVM 运行的各个过程，所实现的功能，其目的以及大致的原理进行分析总结

> 此处主要需要参考之前的 JVM > 简介 > JVM > 总结结构 处的详细结构图进行分析
>
> ![JVM-概念-总体结构图-详细](img/JVM-%E6%A6%82%E5%BF%B5-%E6%80%BB%E4%BD%93%E7%BB%93%E6%9E%84%E5%9B%BE-%E8%AF%A6%E7%BB%86.png)

### 类加载器子系统

类加载器子系统，Class Loader SubSystem，负责从文件系统或者网络中加载 `class` 文件，`class` 文件在文件开头有特定的文件标识

`ClassLoader` 只负责 `class` 文件的加载，至于其是否可以运行，将由 `Execution Engine` 决定

加载类信息存放于一块被称为**方法区**的内存空间。

> 除了类的信息外，方法区还会存放运行时常量信息，可能还包括字符串字面量和数字常量（这部分常量信息是 `class` 文件中常量池部分的内存映射）

![JVM-过程分析-类加载子系统](img/JVM-%E8%BF%87%E7%A8%8B%E5%88%86%E6%9E%90-%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%AD%90%E7%B3%BB%E7%BB%9F.png)

#### 阶段划分

通过上图，可以得知，类的加载可以被分为三个阶段，加载阶段、链接阶段、初始化阶段三个阶段

> 此处的加载阶段与类的加载并不相同，其只是类加载中的一个部分

##### 加载阶段

主要完成了三个任务

1. 通过一个类的全限定类名来获取此类的二进制流
2. 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构
3. <u>在内存中生成一个代表此类的 `java.lang.Class` 对象</u>，作为方法区这个类的各种数据的访问接口

其中，所加载的 `.class` 文件主要有以下的方式

- 本地系统直接加载
- 通过网络获取，如：Web Applet
- 从 zip 压缩包中读取，成为之后的 jar、war格式的技术
- 运行时计算生成，使用最多的是<u>动态代理技术</u>
- 由其他文件生成，如：JSP 应用
- 从专有数据库中提取 `.class` 文件【较少见】
- 从加密文件中获取，如：防止 `.class` 文件被反编译的保护措施

##### 链接阶段

其中主要进行以下的三个方面的操作

###### 验证（Verify）

用于确保 `class` 文件的字节流中包含信息符合当前虚拟机要求，保证加载类的正确性，不会危害虚拟机自身安全

主要包括四个方面的验证：

- 文件格式验证
- 源数据验证
- 字节码验证
- 符号引用验证

###### 准备（Prepared）

为类变量分配内存并且设置该类变量的初始值，即零值

此处不包含使用 `final` 修饰的 `static`，因为 `final` 在编译的时候就会分配了，准备阶段会显式初始化

此处不会为实例变量分配初始化，类变量会分配到方法区中，而实例变量会随着对象一起分配到 `Java` 堆中

> 简单理解，会加载所有的变量，并为其设置初始值，为之后的配置值做好准备

###### 解析（Resolve）

将常量池中的符号引用转换为直接引用的过程

事实上，解析操作往往伴随着 JVM 在执行完初始化之后再执行

符号引用就是一堆符号来表述所引用的目标。符号引用的字面量形式明确定义在《Java 虚拟机规范》的 `class` 文件格式中。直接引用就是直接指向目标的指针、相对偏移量或一个简介定位到目标的句柄

解析动作主要针对类、接口、字段、类方法、接口方法或方法类型等。对应常量池中 `CONSTANT_Class_info`、`CONSTANT_Fieldref_info`、`CONSTANT_Methodref_info`等

##### 初始化阶段

初始化阶段就是执行类构造器方法 `<clinit>()` 的过程

此方法不需要定义，是 `javac` 编译器自动收集类中的所有类变量的赋值动作和静态代码块中的语句和并得到

构造器方法中指令按语句在源文件中出现的顺序执行

`<clinit>()` 不同于类的构造器（关联：构造器是虚拟机视角下的 `<init>()`）

若该类具有父类，JVM 会保证子类的 `<clinit>()` 执行前，父类的 `<clinit>()` 已经执行完毕

> 此处的 `<clinit>()` 方法是 JVM 自动生成的方法，其依据类中的变量赋值和静态代码块初始化来生成
>
> 在这步完成之后，才算是真正完成了类的初始化

#### 类加载器分类

JVM 中支持两种大类型的类加载器，分别为**引导类加载器（Bootstrap ClassLoader）**和**自定义类加载器（User-Defined ClassLoader）**

从概念上讲，自定义类加载器一般指的是程序中由开发人员自定义的类加载器。在 Java 虚拟机规范中没有这样的定于，但是<u>将所有 `ClassLoader` 的派生类都被划分为自定义类加载器</u>，因此，此处的==自定义类加载器并不代表用户自定义的类加载器==

常见的类加载器

![JVM-过程分析-类加载子系统-常见的类加载器](img/JVM-%E8%BF%87%E7%A8%8B%E5%88%86%E6%9E%90-%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%AD%90%E7%B3%BB%E7%BB%9F-%E5%B8%B8%E8%A7%81%E7%9A%84%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%99%A8.png)

- `Bootstrap Class Loader` 引导类加载器
- `Extension Class Loader` 拓展类加载器
- `System Class Loader` 系统/应用程序类加载器

> 因为自定义类加载器并不代表用户自定义的类加载器，因此，之前的 <u>`ExtClassLoader` 和 `AppClassLoader` 此处也可以被划分为自定义类加载器</u>
>
> 特别注意，==此处的类加载器最好将其关系理解为等级关系，越上的类加载器表示的级别越高==（双亲委派）

> 引导类加载器，一般用于加载 Java 环境（JRE）中的 `jre/lib/rt.jar`，即 Java 的核心库
>
> 拓展类加载器，用于加载 Java 目录中的 `jre/lib/ext` 下的额外包

##### 引导类加载器

引导类加载器，Bootstrap Class Loader

该加载器通过 **C/C++语言** 实现，嵌套在 JVM 内部，主要用于加载 Java 的核心库 `JAVA_HOME/jre/lib` 中的 `rt.jar`、`resources.jar`或者`sun.boot.class.path` 路劲下的内容，主要用于提供 JVM 本身所需要的各个类

不是继承自 `java.lang.ClassLoader`，没有上级的类加载器；该加载器本身是加载<u>扩展类加载器</u>和<u>应用程序类（系统类）加载器</u>的上级类加载器

处于安全考虑，`Bootstrap` 启动类加载器只会加载包名为 `java`、`javax`、`sun`等开头的类

> 可以通过使用 `sun.misc.Launcher` 中的静态方法 `getBootstrapClassPath()` 方法，来得到引导类加载器默认加载的所有路径

##### 扩展类加载器

扩展类加载器，Extension Class Loader，简写 Ext Class Loader

通过 Java 语言编写，在 `sun.misc.Launcher$ExtClassLoader` 实现（内部类），间接继承了 `ClassLoader` 类

<u>通过引导类加载器进行加载</u>，引导类加载器是其上级类加载器

从 `java.ext.dirs` 系统属性所指定的目录中加载类库，或从 JDK 的安装目录的 `jre/lib/ext` 子目录（扩展目录）下加载类库。如果用户创建的jar文件放在了指定的目录，也会被扩展类加载器加载

> 可以通过使用 `System.getProperty()` 来获取参数

##### 系统类加载器

系统类加载器，System Class Loader，AppClassLoader

通过 Java 语言编写，在 `sun.misc.Launcher$AppClassLoader` 实现，间接继承了 `ClassLoader` 类

<u>通过引导类加载器进行加载</u>，扩展类加载器是其上级类加载器

主要用于负责环境变量 `classpath` 或者系统属性 `java.class.path` 所指定的路径下的类库

**该类是程序中大部分类的默认类加载器**

> 可以通过使用 `ClassLoader` 中的静态方法 `getSystemClassLoader()` 方法来获取该类加载器

##### 代码测试

具体感受系统自带的集中类加载器，其作用范围

```java
// 引导类加载器
System.out.println("Bootstrap Class Loader");
for (URL url : Launcher.getBootstrapClassPath().getURLs()) {
  System.out.println(url);
}

// 拓展类加载器
System.out.println("\nExtension Class Loader");
String extDirs = System.getProperty("java.ext.dirs");
for (String s : extDirs.split(";")) {
  System.out.println(s);
}
```

##### 自定义类加载器

用户自定义的类加载器，用于定制类的加载方式

一般自定义的类加载器都用于实现以下的效果：

- 隔离加载类

  可以处理不同 jar 包之间，其引用中，出现了同一个全限定类名的包（两个 jar 分别依赖了不同的文件，但是全限定类名相同）

  > 双清委派机制

- 修改类加载的方式

- 扩展加载源

  拓展加载的文件或目录

- 防止源码泄露

  通过自定义的类加载器，能够对自定义加密的字节码文件进行解密

###### 实现

1. 通过直接或间接的方式继承 `java.lang.ClassLoader` 类，实现自定义的类加载器，以满足部分特殊的要求

   > 在 JDK 1.2 之前，在自定义类加载器时，都需要去继承 `ClassLoader` 类并重写其中的 `loadClass()` 方法，从而实现自定义的类加载器
   >
   > 在 JDK 1.2 之后，已经不建议用户去覆盖 `loadClass()` 方法，而是建议把自定义的类加载逻辑写在 `findClass()` 方法中

2. 在编写自定义类加载器时，如果没有太过复杂的需求，可以直接继承 `URLClassLoader` 类，这样就可以避免自己去编写 `findClass()` 方法以及获取字节码流的方式使自定义类加载器编写更加简洁

#### 拓展

在类加载子系统中的一些额外内容

##### 判断相同类

在 JVM 中，表示两个 `class` 对象是否为同一个类存在两个必要条件

- 类的完整类名必须一致，包括包名
- 加载这个类的类加载器必须相同（代表的是类加载实例对象）

在 JVM 中，即视两个类对象来源同一个 `class` 文件，被同一个虚拟机所加载，但只要加载的类加载器实例对象不同，那么两个类就并不相同

##### Java 类使用方式

Java 程序对类的使用，主要可以被分为**主动加载**和**被动使用**

###### 主动使用

1. 创建类的实例

2. 访问某个类或接口的静态变量，或者对该静态变量赋值

3. 调用类的静态方法

4. 反射（如，`Class.forName("全限定类名")`

5. 初始化一个类的子类

6. Java 虚拟机启动时被标名为启动类的类

7. JDK 7 开始提供的动态语言支持：

   `java.lang.invoke.MethodHandle` 实例的解析结果

   `REF_getStatic`、`REF_putStatic`、`REF_invokeStatic` 句柄对应的类没有初始化，则初始化

### 运行时数据区

运行时数据区，Runtime Data Area

## 特性

### 双亲委派机制

JVM 的一种保护机制，避免类的重复加载。其采用了按需加载的方式，也能同时减少项目的资源使用

#### 工作原理

如果一个类加载器收到了类加载请求，不会在第一时间直接加载，而是先确定是否已经加载过了目标对象，如果没有，就会向上委托上级的父类加载器执行

父类加载器接收到委托之后，也会使用同样的流程，首先确定是否已经加载了目标，如果没有，就会继续向上委托，直到最顶层的引导类加载器

如果出现了都没有加载过的请求，就会首先判断自身（会从最上层往下判断）能否加载目标类，如果不能，就向下委派

![JVM-特性-双亲委派机制](img/JVM-%E7%89%B9%E6%80%A7-%E5%8F%8C%E4%BA%B2%E5%A7%94%E6%B4%BE%E6%9C%BA%E5%88%B6.png)

> 如，在项目中，创建了一个 `java.lang.String` 类
>
> 通过 `new` 的方式获取的对象一定不是自定义的 `String` 对象，而是 Java 核心类库中的 `String`
>
> 这是因为，第一次加载的时候，最上层的引导类加载器已经对 `java.lang.String` 进行了加载，之后按照双亲委派机制，在引导类加载器的判断时，发现已经对该全限定类名进行了加载
>
> 为了防止重复加载，就不会加载自定义的 `String` 类
>
> 
>
>
> 更加直观的感受，在定义的 `java.lang.String` 中编写一个 `main()` 方法，并执行，会出现错误信息提示，找不到 `main()` 方法
>
> 这就是因为双亲委派机制，最终使用的应该是核心类库中的 `String`，其中应该并没有 `main()` 方法，因此出现了错误

#### 特别注意

1. 类加载器通过判断其管理路径进行加载

   因为这样的特性，如果自定义了一个类，与 Java 核心类库中的类文件没有冲突，但是包名使用核心类库的包名

   如 `java.lang.MyTest`，虽然没有与核心类库中的文件冲突，但是因为其包名指定为 `java.lang`，在引导类加载器的作用范围内，因此引导类加载器会尝试加载该类

   最终会因为 <u>JVM 中权限的控制导致出现报错，提示不能使用该类名</u>（沙箱安全机制）

### 沙箱安全机制

Java 安全模型的核心就是 Java 沙箱安全机制

沙箱就是限制程序的运行环境，使代码在特定的运行范围内，并且严格限制代码对本地系统的资源访问，通过这样的方式来保证对代码的有效隔离，防止对本地系统造成破坏

杀向主要的作用就是来限制对系统资源的访问。主要包括，CPU、内存、文件系统、网络等，不同级别的沙箱对资源访问的限制也不太一样

## 拓展

### Java 自带的常见命令

在 jdk 的 `bin` 中自带的有一些执行文件，可以通过某些命令来进行使用

- `jps` 查看本机 Java 进程信息
- `jstack` 打印线程的栈信息
- `jmap` 打印内存映射，值作堆 dump 文件
- `jstat` 性能监控文件
- `jhat` 内存分析工具
- `jconsole` 简单的可视化控制台
- `jvisualvm` 功能强大的控制台

### VM 参数

```shell
# Java 虚拟机初始分配内存大小
# 此处举例设置默认1024m（其中不能有空格分隔）
-Xms1024m

# Java 虚拟机最大分配的内存大小
# 此处举例设置最大1024m
-Xmx1024m

# 打印GC垃圾回收信息
-XX:+PrintGCDetails

# 在项目出现了OOM错误时，生成堆快照（Heap Dump文件）
-XX:+HeapDumpOnOutOfMemoryError

# 设定新生区到老年区的需经历多少次幸存（GC后幸存）
# 默认值为15
-XX:MaxTenuringThreshold=15

# 设定新生代中的Eden区和Survivor区
# 可以控制进入老年代的速度
# 默认值为8，表示Eden占新生代中的8/10，From幸存区和To幸存区分别占新生代的1/10
-XX:SurvivorRatio=8

# 设定 GC 回收机制
# 串行回收【默认】
-XX:+UseSerialGC
# 并行回收【> 新生代】
-XX:+UseParNewGC
# Parallel收集器【> 新生代】
-XX:+UseParallelGC
# Parallel收集器【新旧同时生效】
-XX:+UseParallelOldGC
# CMS收集器【> 老年代】
-XX:+UseConcMarkSweepGC
# 在Full GC之后，进行以此整理，可能造成停顿变长
-XX:+UseCMSCompactAtFullCollection
```

1. 

### 常见面试题

