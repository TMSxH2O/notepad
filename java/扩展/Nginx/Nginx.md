# Nginx

*Nginx* (engine x) 是一个高性能的HTTP和反向代理web服务器，同时也提供了IMAP/POP3/SMTP服务。Nginx是由伊戈尔·赛索耶夫为俄罗斯访问量第二的Rambler.ru站点（俄文：Рамблер）开发的，第一个公开版本0.1.0发布于2004年10月4日。

其将源代码以类BSD许可证的形式发布，因它的稳定性、丰富的功能集、示例配置文件和低系统资源的消耗而闻名。2011年6月1日，nginx 1.0.4发布。

Nginx是一款轻量级的Web 服务器/反向代理服务器及电子邮件（IMAP/POP3）代理服务器，在BSD-like 协议下发行。其特点是占有内存少，并发能力强，事实上nginx的并发能力在同类型的网页服务器中表现较好，中国大陆使用nginx网站用户有：百度、京东、新浪、网易、腾讯、淘宝等。

## 基础概念

`Nginx` 是一个高性能的HTTP和反向代理**服务器**，特点是<u>占有内存少，并发能力强。</u>

Nginx 可以作为静态页面的 web服务器，同时还支持CGI协议的动态语言，比如 perl、php等，但是不支持Java。 Java程序只能通过与Tomcat配合完成。

Nginx <u>专为性能优化而开发</u>，性能是其嫉妒重要的考量，实现上非常注重效率，能经受高负载的考验，有研究表明其支持高达 50,000 个并发连接数。

Nginx <u>以事件驱动的方式编写</u>，所以有非常好的性能，同时也是一个非常高效的反向代理、负载平衡。其拥有匹配 Lighttpd 的性能，同时还没有 Lighttpd 的内存泄漏问题，而且 Lighttpd 的 mod_proxy 也有一些问题并且很久没有更新。

现在，Igor 将源代码以类 BSD 许可证的形式发布。Nginx 因为它的稳定性、丰富的模块库、灵活的配置和低系统资源的消耗而闻名．业界一致认为它是 `Apache2.2`＋`mod_proxy_balancer` 的轻量级代替者，不仅是因为响应静态页面的速度非常快，而且它的模块数量达到 Apache 的近 2/3。对 proxy 和 rewrite 模块的支持很彻底，还支持 mod_fcgi、ssl、vhosts ，适合用来做 mongrel clusters 的前端 HTTP 响应。

### 概念

在 `Nginx` 的概念中提到了很多的名词，此处将进行集中整理以及简单解决其含义，并在之后具体使用并表现这些概念

#### 反向代理

为了能够更好的理解反向代理，需要结合正向代理进行讲解

> **正向代理简介**
>
> 正向代理的功能，类似一个跳板机，代理并访问外部的资源。如，VPN就是利用正向代理的方式，实现了对外网资源的访问
>
> 此处，可以将局域网外的 Internet 想象成一个巨大的资源库，则局域网中的客户端想要访问 Internet，就需要通过代理服务器去访问，这样的代理就被称为正向代理
>
> ![Nginx-概念-正向代理](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Nginx-%E6%A6%82%E5%BF%B5-%E6%AD%A3%E5%90%91%E4%BB%A3%E7%90%86.png)
>
> Nginx 不仅可以进行反向代理，实现负载均衡，还能作为正向代理来进行上网等功能

反向代理中，<u>客户端对代理是无感知的</u>，如正向代理的概念中，客户因为无法访问外部资源而自行配置使用了正向代理，才成功实现了访问。而在反向代理中，并不需要客户的任何配置就能直接使用。对客户而言，仅仅是向反向代理服务器发送了请求，其就能自行选择目标，并获取到所需的数据

![Nginx-概念-反向代理](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Nginx-%E6%A6%82%E5%BF%B5-%E5%8F%8D%E5%90%91%E4%BB%A3%E7%90%86.png)

在反向代理中，反向代理服务器本身就是一个服务器和目标服务器对外本身就是一个服务器，暴露的是代理服务器的地址，而隐藏了真实服务器的IP地址

<u>简单理解，正向代理基于**用户**，而反向代理基于**服务器**</u>

#### 负载均衡

客户端发送多个请求到服务器，服务器处理请求，有一些可能需要与数据库进行交互，服务器处理完毕后，再返回结果给客户端

这样的架构模式对于早期的系统相对单一，并发请求相对较少的情况下是比较合适的，成本也低。但是随着信息数量的不断增长，访问量和数据量的飞速增长，以及系统业务的复杂度增加，这种架构会造成服务器对应客户端的请求日益缓慢。在<u>并发量特别大</u>时，容易造成服务器的崩溃。这是由于服务器性能的瓶颈所造成的，而应该如何解决这种问题？

- 最简单粗暴的方法就是升级服务器的配置，如提高了服务器 CPU 执行频率，加大内存等提高机能的物理性能来解决问题。但是随着<u>摩尔定律</u>[^1]失效，硬件的性能提升已经不能满足日益提升的需求。因此，在这样的基础上，还需要其他的方案将服务器的性能进一步提升
- 在物理配置上已经无法满足服务器的需求后，为了解决现有的问题，即<u>单个服务器的性能已经达到了瓶颈，那么转而采用多个服务器的方式来实现</u>，这也提出了集群的概念。这就是所谓的**<u>负载均衡</u>**

> 看起来和反向代理似乎有点类似，但是反向代理主要作用就是代理，即用于访问外部的资源，而负载均衡是为了处理服务器内部，使用多个本地服务器形成一个集群，为同一个种请求服务

随着互联网信息爆炸性增长，负载均衡已经不再是一个陌生的话题。顾名思义，负载均衡即是将负载分摊到不同的服务单元，即保证服务的可用性，又保证响应足够快，给用户更好的体验

快速增长的访问量和数据流量催生了各式各样的负载均衡产品，很多专业的负载均衡硬件提供了很好的功能，但却价格不菲，这使得负载均衡软件大受欢迎，nginx 就是其中之一。在 linux 下有 nginx、LVS、Haproxy 等服务都可以提供负载均衡服务

 <u>nginx 提供了几种分配方式（策略）：</u>

1. 轮询【**默认**】

   每个请求按时间顺序逐一分配到不同的后端服务器，如果后端服务器异常，就会自动被剔除

2. 权重

   默认权重为 `1`，权重越高的服务器被分配的客户请求越多

   某种意义上也是基于轮询，其中的分配多出了权重来增大某个服务器被分配的可能性，可以解决后端服务器性能不均的情况

   其使用的方法就是在原本指定服务器集群时，额外指定各个服务器的权重（weight）

   ```shell
   # 设置集群，并指定权重
   upstream server_pool {
     # 为两个服务器指定权重，8080 分配的用户将比 8081 多一倍（大致数量，并非绝对）
   	server 127.0.0.1:8080 weight=10;
   	server 127.0.0.1:8081 weight=5;
   }
   ```

3. ip hash

   每个请求将按照访问 ip 的 hash 结果进行分配，每个客户的请求在经过第一次的确定，之后的访问都将前往某个绑定的服务器，<u>可以用于解决 session 问题</u>

   使用只需要在集群中使用 `ip_hash` 标识开启 ip hash 策略即可

   ```shell
   # 设置集群，使用 ip hash 策略
   upstream server_pool {
   	ip_hash;
   	server 127.0.0.1:8080;
   	server 127.0.0.1:8081;
   }
   ```

   > 如果使用轮询的方式，每次的请求都可能被分配到不同的服务器，这样的方式，将导致 session 域很可能失效

4. fair 【第三方】

   如其名，将根据后端服务器的响应时间来进行分配，响应短的优先

   使用方法和 ip hash 类似，只需要指定开启即可

   ```shell
   # 设置集群，使用 fair 策略
   upstream server_pool {
   	server 127.0.0.1:8080;
   	server 127.0.0.1:8081;
   	fair;
   }
   ```

> 在 nginx 1.3 之后的版本，支持 ip hash 和 weight 的同时使用（影响 ip hash 的第一次选择）

#### 动静分离

为了加快网站的加载速度，可以将动态界面和静态界面由不同的服务器来解析，以此加速解析速度，降低原本单个服务器的压力

如，将动态资源交给 Tomcat 服务器进行解析响应，而静态资源交给静态文件资源处理器进行处理响应

<img src="https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Nginx-%E6%A6%82%E5%BF%B5-%E5%8A%A8%E9%9D%99%E5%88%86%E7%A6%BB.png" alt="Nginx-概念-动静分离" style="zoom: 80%;" />

动静分离并不能简单理解为将动态界面和静态界面进行简单的物理分离（写到不同的界面），重点是<u>需要将两者的请求进行分离</u>，如之前的将静态页面请求放到 nginx 中，而动态界面请求放到 tomcat 中

动静分离从实现的角度来说，一般有两种

1. 纯粹将静态文件独立成单独域名，放到独立的服务器上，这也是**<u>目前较主流的方案</u>**
2. 将动态资源和静态资源混合一同发布，通过 nginx 来进行划分

具体的实现是通过在 location 块中指定不同的后缀名实现不同的请求转发。通过 `expires` 参数配置，可以使浏览器缓存过期时间，减少与服务器之前的请求和流量

具体 `expires` 定义：是给一个资源设定一个过期时间，也就是说无需去服务端验证，直接通过浏览器自身确认是否过期即可，所以不会产生额外的流量

这样的方式并<u>不适合经常会发生变动的资源</u>，因此，如果是经常更新的文件，就不建议使用 `expires` 进行缓存

如果缓存中有<u>对应的资源并且资源没有过期</u>，可以向服务器确定资源是否发生改变，如果没有发生改变，就会响应 `304` 表示可以使用浏览器中的资源；如果发生了改变，就会返回 `200`

## 安装和使用

Nginx 是一个服务器的软件，此处的说明都是基于 Linux 系统的安装和使用

### 安装

> Linux CentOS 7
>
> Nginx 1.18.0 （Stable version）
>
> pcre 8.44
>
> openssl 1.1.1
>
> zlib 1.2.7

#### 版本选择

可以在官网上下载安装包

http://nginx.org/en/download.html

其中提供了不同的版本，主要有：

- Mainline version

  正在开发的主要版本

- Stable version

  稳定版本，主要用于实际的生产环境

- Legacy version

  历史版本

#### 相关依赖

pcre http://www.pcre.org/

openssl http://www.openssl.org/source/

zlib http://www.zlib.net/

#### 安装方式

Linux 中的软件安装主要有如下三种方式

1. 通过 yum 安装

   yum 的形式类似 npm 安装，简单快捷，能够自动安装相关的依赖

2. 通过 rpm 安装

   rpm 安装与 yum 类似，但是安装的木块来源不是 yum 官方镜像，而是本地资源

3. 通过源码安装

   源码安装需要下载源码然后本地编译，可以实现个性化定义，适用于对 Linux 了解较多的进阶用户

#### 安装步骤

进入 Linux 系统，首先需要获取对应的文件，此处将依次安装 pcre、opennssl、zlib，最后安装 Nginx

- pcre 安装

  1. 文件准备

     ```shell
     # 进入系统的某个目录，准备将在其中安装文件
     cd /usr/src
     # 此处可以使用 wget 命令的方式直接在 linux 中下载对应的安装包
     # 如 wget https://ftp.pcre.org/pub/pcre/pcre-8.44.tar.gz
     # 也可以将下载好的文件直接发送到 linux 中
     ```

     ![Nginx-安装步骤-pcre-文件准备-文件下载](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Nginx-%E5%AE%89%E8%A3%85%E6%AD%A5%E9%AA%A4-pcre-%E6%96%87%E4%BB%B6%E5%87%86%E5%A4%87-%E6%96%87%E4%BB%B6%E4%B8%8B%E8%BD%BD.png)

     ```shell
     # 使用命令，将 tar.gz 文件解压
     tar -xvf pcre-8.44.tar.gz
     # 之后会在当前目录中多出一个目录 pcre-8.44
     ```

     ![Nginx-安装步骤-pcre-文件准备-文件解压](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Nginx-%E5%AE%89%E8%A3%85%E6%AD%A5%E9%AA%A4-pcre-%E6%96%87%E4%BB%B6%E5%87%86%E5%A4%87-%E6%96%87%E4%BB%B6%E8%A7%A3%E5%8E%8B.png)

  2. 安装软件

     ```shell
     # 首先进入软件的目录
     cd pcre-8.44
     
     # 执行命令确认文件状态
     ./configure
     # 此方法执行可能出现异常 error，需要先安装 gcc 和 gcc-c++
     # yum install -y gcc gcc-c++
     
     # 安装软件/编译软件
     make && make install
     ```

     ![Nginx-安装步骤-pcre-安装](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Nginx-%E5%AE%89%E8%A3%85%E6%AD%A5%E9%AA%A4-pcre-%E5%AE%89%E8%A3%85.png)

     > 其中主要需要注意，`./configure` 命令需要使用 `gcc` 或者 `gcc-c++`

     ```shell
     # 安装完成之后，通过查看 pcre 的版本的命令，来确定是否安装成功
     pcre-config --version
     ```

- 安装 openssl

  理论上的步骤都基本相同，因此此处会较简略的进行介绍（无配图）

  1. 文件准备

     ```shell
     # 下载文件
     wget https://www.openssl.org/source/openssl-1.1.1g.tar.gz
     
     # 解压文件
     tar -xvf openssl-1.1.1g.tar.gz
     ```

  2. 安装文件

     ```shell
     # 进入解压目录
     cd openssl-1.1.1g
     
     # 确认文件状态
     ./config
     ```

     此处为正确的状态

     ![Nginx-安装步骤-openssl-确认状态](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Nginx-%E5%AE%89%E8%A3%85%E6%AD%A5%E9%AA%A4-openssl-%E7%A1%AE%E8%AE%A4%E7%8A%B6%E6%80%81.png)

     ```shell
     # 开始安装
     make && make install
     
     # 验证安装
     ssh -V
     ```

- zlib 安装

  此处将使用 yum 命令来直接进行安装

  ```shell
  # 直接使用 yum 命令进行安装
  # 如果希望查看将要安装到哪里，可以使用 whereis 命令加依赖的方式
  # 其中的 openssl gcc-c++ 因为之前已经安装，可以不加
  yum -y install make zlib zlib-devel libtool openssl openssl-deve gcc-c++
  ```

- 安装 nginx 

  此处使用的方法依然是本地文件的方式

  1. 获取文件

     > 具体的文件选择，可以查看 [版本选择](#版本选择)
     >
     > 此处使用的是 Stable version

  2. 准备文件

     ```shell
     # 解压文件
     tar -xvf nginx-1.18.0.tar.gz
     ```

  3. 安装软件

     ```shell
     # 进入解压出的目录中
     cd nginx-1.18.0
     
     # 验证文件状态
     ./configure
     
     # 在验证成功的情况下，安装软件
     make && make install
     ```

### 使用

在 Nginx 安装完成之后，会在 `/usr/local` 目录下创建目录 `nginx`，其中有一个目录为 `sbin` 目录，类似 Tomcat 的 `bin` 目录，存放的是项目的主要运行文件

之后的 Nginx 的启动，就是使用 `/usr/local/nginx/sbin` 目录下的 `nginx` 文件进行启动

```shell
# 进入目录
cd /usr/local/nginx/sbin

# 启动 nginx
./nginx
# 理论上启动文件之后，nginx 就启动成功了
# 可以使用 ps -ef | grep nginx 进行确认
```

此时，就应该可以通过服务器的 `80` 端口进行访问了（http的默认端口，可以忽略）

> 其中指定端口的文件在 `/usr/local/nginx/conf` 目录中的 `nginx.conf` 文件
>
> 其中的格式类似 json，在 `http > server > listene` 处进行了指定（默认为 `80`）

#### 常用命令

在 Nginx 的具体使用中，经常会使用到的命令

> **使用 Nginx 操作命令的前提条件**
>
> 必须进入 Nginx 的目录 `/usr/local/nginx/sbin`
>
> 也可以通过配置环境变量的方式，在 `/etc/profile` 文件中进行配置，添加该路径即可（Linux 中的环境变量配置方法并不唯一，此处只列举一个）
>
> ```shell
> # 修改 /etc/profile 文件
> vim /etc/profile
> # 具体的指定方法就是在文件的最后，指定环境变量
> # 以此处指定 Nginx 为例
> # PATH=$PATH:/usr/local/nginx/sbin
> # export PATH
> 
> # 在指定之后，还需要加载一次文件
> # 因为此文件实在登录时会加载一次，之后的修改需要手动进行加载
> source /etc/profile
> 
> # 之后可以使用 nginx -V 查看nginx版本进行测试
> ```
>
> 【[Linux下设置和查看环境变量](https://www.cnblogs.com/qiuhong10/p/7815943.html)】

##### 查看信息

```shell
# 查看nginx版本号
nginx -V
```

---

```shell
# 验证配置文件是否正确
nginx -t
# 会自动验证 nginx 的配置文件经过修改之后是否正确，如果有错，会显示具体的错误
```

##### 运行操作

```shell
# 开启nginx
nginx
# 直接开启nginx服务，之后可以通过 ps -ef | grep nginx 查看进程
```

---

```shell
# 关闭nginx
nginx -s stop
```

---

```shell
# 重新加载配置文件
# 不会关闭服务器，热部署
nginx -s reload
```

#### 配置文件

Nginx 的文件布置与 Tomcat 类似，其中的配置文件被集中存放到了 `/usr/local/nginx/conf` 目录中

目前，最需要关注的文件只有其中的 `nginx.conf` 文件，此处将对此文件进行详细的介绍

`nginx.conf` 中，根据作用的不同，将被分为三个部分：

- 全局块
- events 块
- http 块

<img src="https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Nginx-%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6-%E7%BB%93%E6%9E%84%E5%9B%BE.png" alt="Nginx-配置文件-结构图" style="zoom:80%;" />

##### 全局块

从配置文件的开始到 events 块之间的内容，主要会设置一些影响 nginx 服务器整体运行的配置命令

其中的配置内容包括：

- 配置运行 nginx 服务器的用户（组）
- 允许生成的 worker process 数
- 进程 PID 存放路径
- 日志存放路径和类型
- 配置文件的引入
- ……

```shell
# 将对其中的配置重点部分进行详细分析

# 表示 nginx 服务器并发处理服务器的关键配置，worker_processes 的值越大，表示支持的并发数量越多，但是实际的并发数量将受到硬件和软件等因素的限制
worker_processes  1;
```

##### events 块

events 块中主要涉及到的指令是影响 nginx 服务器与用户的网络连接

常用的配置包括：

- 是否开启对多 worker processes 下的网络连接进行序列化
- 是否运行同时接收多个网络连接
- 选取哪种事件模型来处理连接请求
- 每个 work process 可以支持的最大连接数量
- ……

```shell
# events 块的重点配置

# events 块
events{
  # 配置了每个 worker process 所支持的最大连接数
	worker_connections  1024
}
```

##### http 块

在 nginx 的配置中，可能需要修改最多的部分，其中包括代理、缓存和日志等绝大多数功能和第三方模块的配置都将在此处进行配置

同时，http 块还可以被划分为 **http 全局块**、**server 块**

###### http 全局块

http 全局块配置的指令包括文件引入、MIME-TYPE定义、日志自定义、连接超时时间、单链接请求数上限等

###### server 块

在 server 块中的配置都与虚拟主机有关。虚拟主机从用户的角度来看，和独立的一个硬件主机是完全一样的，该技术的产生是为了节省互联网服务器的硬件成本

<u>每个 http 块可以包括多个 server 块，而每个 server 块就相当于一个虚拟主机</u>

server 块中还可以进行划分

- **全局 server 块**

  最常见的配置是本虚拟主机的监听配置和本虚拟主机的名称或 IP 配置

  ```shell
  # http 块
  http{
  	# server 块
  	server{
  		# 监听端口号 80
    	listen       80;
    	# 主机名称
      server_name  localhost;
  	}
  }
  ```

- **location 块**

  <u>一个 server 块中可以配置多个 location 块</u>

  主要的作用是基于 nginx 服务器接收到的请求字符串（如 server_name/uri_string），对虚拟主机名称（IP 别名）之外的字符串（如 uri_string）进行匹配，对特定的请求进行处理。以此实现以下的功能：

  - 地址定响
  - 数据缓存
  - 应答控制
  - ……

  ```shell
  # http 块
  http {
  	# server 块
  	server {
  		# location 块
  		location / {
      	root   html;
      	index  index.html index.htm;
      }
  	}
  }
  ```

  此处将对 location 块中指定地址前的配置进行具体的讲解
  
  通过之后的使用，可以看到其中支持对地址进行匹配与控制（略微类似 Vue Router），此处的地址匹配可以<u>通过不同的标识来选择是否开启正则匹配</u>
  
  > 可以结合之后的实例进行理解【[跳转](#反向代理-实例2)】
  
  - `=` 用于不会有正则表达式的 uri 前，要求请求字符串与 uri 严格匹配，如果匹配成功，就停止继续向下搜索并立即处理该请求
  - `~` 用于表示 uri 包含正则表达式，并且区分大小写
  - `~*` 用于表示 uri 包含正则表达式，并且不区分大小写
  - `^~` 用于不包含正则表达式的 uri 前，要求 ngxin 服务器找到标识 uri 和请求字符串匹配率最高的 location 后，立即使用此 location 处理请求，而不再使用 location 块中的正则 uri 和请求字符串做匹配
  
  > 如果 uri 包含正则表达式，则必须要有 `~` 或者 `~*`标识

#### 补充

在 Nginx 使用过程中，可能需要会需要注意的问题

##### 开发端口

在 Linux 中，可能因为防火墙的设置，导致无法看到界面，需要设置防火墙开启对应的端口

> 如果是使用的 aliyun（阿里云）
>
> 在 **云服务器ECS** 中，设置实例的安全组，指定开启所需的端口
>
> 如果希望更加安全，可以通过 https://www.ipaddress.com/ 查询本机的外网IP 

```shell
# 查看防火墙的状态
systemctl status firewalld
# 如果之后的信息中显示了 active(running) 表示正在运行；inactive(dead) 表示防火墙已关闭
# 防火墙开启命令 systemctl start firewalld
# 防火墙关闭命令 systemctl stop firewalld

# 查看开放的端口号
firewall-cmd --list-all

# 设置开放的端口号
# 设置开发了 http 服务
firewall-cmd --add-service=http --permanent
# 设置开放了 80 端口
sudo firewall-cmd --add-port=80/tcp --permanent

# 重启防火墙
firewall-cmd --reload
```

## 配置实例

此处将结合 nginx 的概念进行配置，使其能够体现概念中的具体功能

### 反向代理

在服务器的内部，实现代理跳转不同的服务器的技术

> 具体概念，可以查看基础概念中的反向代理 【[跳转](#基础概念-反向代理)】

#### 示例一

> 通过反向代理的概念，其最终效果大概应该如下
>
> <u>在浏览器中通过访问 nginx 服务器，直接跳转到 Linux 中所部署的 Tomcat 服务器主页面中</u>

##### 准备工作

- 安装 nginx 和 tomcat 服务器

  nginx 服务器的可以查看之前的[安装步骤](#安装步骤)

  此处的 tomcat 直接使用的是 docker 安装（version 9.0.37）

  ```shell
  # 创建 tomcat 容器
  docker run -d --network host --name mytomcat tomcat
  
  # 如果已经创建过，可以直接启动
  docker start mytomcat
  ```

  > 之后需要解决 tomcat 欢迎界面404的问题，可以查看 docker 中的使用实例

##### 具体配置

修改 nginx 中的配置文件 `nginx.conf`

在其中的 http 域下的 server 域中，修改默认 location 域的信息

```shell
location / {
  root   html;
  # 添加以下的配置
  # 代理跳转（反向代理）跳转到该网址
  proxy_pass  http://localhost:8080;
  index  index.html index.htm;
}
```

![Nginx-使用示例-反向代理-1-修改配置文件](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Nginx-%E4%BD%BF%E7%94%A8%E7%A4%BA%E4%BE%8B-%E5%8F%8D%E5%90%91%E4%BB%A3%E7%90%86-1-%E4%BF%AE%E6%94%B9%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6.png)

在配置完成之后，如果服务器正在运行，需要重启服务器（热部署）

```shell
# 重新加载配置文件
nginx -s reload

# 如果服务器还没有启动，就直接启动
# nginx
```

#### 示例二

> 在示例一的基础上，希望实现对具体的某个网址实现跳转
>
> 如：
>
> `http://47.92.163.46/test`>`http://47.92.163.46:8080`
>
> `http://47.92.163.46/edu`>`http://47.92.163.46:8081`

##### 准备工作

此处因为 tomcat 使用的是 docker 因此，只需要再创建一个容器即可

为了防止示例一对本次测试的误导，在实例一的容器 `webapps` 目录下，创建 `test` 目录，并在其中创建 `index.html`

```html
<!-- test / index.html -->
<h1>test page</h1>
```

在创建的第二个容器中，为其 `webapps` 目录下，创建目录 `edu`，并在其中创建 `index.html`

```shell
# 创建第二个容器
# docker run -d -p 8081:8080 --name test-tomcat tomcat
# 此处如果直接使用 -p 的方式来开放端口，会导致 linux 中访问docker 不能直接使用localhost来进行访问，可以在容器中查看容器的ip地址
# 还是可以使用 --network host 指定容器使用linux系统的网络来运行，只需要修改其中的tomcat配置文件
# 其中可能会出现因为 docker 容器中没有vim的文件

# 容器中安装 vim
# 同步源索引
apt-get update
# 安装
apt-get install vim
```

```html
<!-- edu / index.html -->
<h1>edu page</h1>
```

> 最终使用的方案，两个tomcat的容器都使用的host网络运行
>
> 并分别在其中创建了 `test` 和 `edu` 项目

##### 具体配置

与示例一相同，依然是需要修改 nginx 的配置文件 `nginx.conf`

在默认的server 域（监听 `80` 端口的server 域）中，额外配置两个 location 域，用于处理地址中存在 `/test/` 和 `/edu/` 的情况

使其分别跳转到对应的 tomcat 服务器

```shell
http {
	server {
	  # 其中使用 ~ 开启的正则表达式
	  # 匹配地址中存在 /test/ 的请求
    location ~ /test/ {
    	proxy_pass http://localhost:8080;
    }

		# 匹配地址中存在 /edu/ 的请求
    location ~ /edu/ {
    	proxy_pass http://localhost:8081;
    }
	}
}
```

![Nginx-使用示例-反向代理-2-修改配置文件](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Nginx-%E4%BD%BF%E7%94%A8%E7%A4%BA%E4%BE%8B-%E5%8F%8D%E5%90%91%E4%BB%A3%E7%90%86-2-%E4%BF%AE%E6%94%B9%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6.png)

最后，通过不同的网址就可以实现访问

`http://47.92.163.46/test/index.html`

`http://47.92.163.46/edu/index.html`

> **<u>特别注意：</u>**
>
> 此处的网址如果只是写 `/test/` 最后会出现404错误
>
> 似乎这样转发的方式不会有默认开启其中的 `index.html` 的设定，因此必须在之后指定需要访问的具体文件

### 负载均衡

在物理上的提升已经不能满足服务器的需求，转而使用多个服务器形成集群，来处理巨量的请求的技术

> 具体概念，可以查看基础概念中的负载均衡

#### 示例

> 希望实现如下的效果
>
> 对服务器的 `http://47.92.163.46/test/index.html` 的访问，进行负载均衡，将其平均分配到 `8080` 和 `8081` 端口中

##### 准备工作

根据需求，此处可以直接在之前反向代理的示例二的基础上，进行配置

需要两个 tomcat 服务器的 `webapps` 目录下都需要一个 `test` 目录，并且其中有 `index.html` 文件

此处为了能够更好的区分两个服务器的 `index.html` 文件，在其中多显示一条信息，标识两个服务器的端口号

##### 具体配置

在准备工作完成之后，开始对 nginx 服务器进行配置，此处主要有两步

1. 在 http 全局块中配置开启负载均衡
2. 在对应的 location 块中将 负载均衡配置到其中

```shell
# 在 htpp 全局块中配置服务器集群
http {
	upstream <自定义集群名称> {
		# server <服务器的ip地址>;
		server 127.0.0.1:8080;
	}
	
	server {
		location <地址> {
		  # 使用服务器集群
			proxy_pass http://<集群名称>;
		}
	}
}
```

![Nginx-使用示例-负载均衡-1-修改配置文件](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Nginx-%E4%BD%BF%E7%94%A8%E7%A4%BA%E4%BE%8B-%E8%B4%9F%E8%BD%BD%E5%9D%87%E8%A1%A1-1-%E4%BF%AE%E6%94%B9%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6.png)

通过这样的配置，之后对`http://47.92.163.46/test/index.html`的访问都将交由两个服务器一同进行处理，并且每次刷新都交换了服务器

> 其中应该使用的是轮询的方法来选择服务器，此处无法感受到服务器的选择，因为两个服务器都没有被占用，此处应该会有确认服务器是否空闲的操作

### 动静分离

根据资源类型，将静态和动态的请求进行分离

> 具体概念，查看基础概念中的动静分离

#### 示例

> 希望将系统中的静态请求和动态的资源请求分别交给 nginx 和 tomcat 来处理

##### 准备工作

在 linux 系统中准备静态资源，用于之后的访问

在系统的根目录下，创建 `data` 目录用于之后存放静态资源，在其中创建 `html_data` 目录来存放静态界面资源，`image_data` 目录来存放图片资源

##### 具体配置

通过配置，指向准备的静态资源

在 nginx 配置文件中，指定两个特定的请求，分别控制图片和 html 的请求，使其前往系统目录中读取文件

```shell
# 在 location 中配置
http {
	server {
	  # 用于处理 html 的请求
		location /html_data {
			# 映射的是系统的目录
			root /data/;
			# 开启了资源结构界面
			autoindex on;
		}
		
		# 用于处理图片的请求
		location /image_data {
			root /data/;
			autoindex on;
		}
	}
}
```

![Nginx-使用示例-动静分离-1-配置静态资源](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Nginx-%E4%BD%BF%E7%94%A8%E7%A4%BA%E4%BE%8B-%E5%8A%A8%E9%9D%99%E5%88%86%E7%A6%BB-1-%E9%85%8D%E7%BD%AE%E9%9D%99%E6%80%81%E8%B5%84%E6%BA%90.png)

> **<u>特别注意</u>**
>
> 其中，指定 location 的映射地址时，最好不加后半 `/`
>
> `location /html_data/ {...}` **错**
>
> `location /html_data {...}` **对**
>
> 如果加上后半的 `/` 可能导致其资源结构界面无法访问

最终，应该可以通过 `http://47.92.163.46/html_data/` 和 `http://47.92.163.46/image_data/`，访问到两个显示资源结构的界面

![Nginx-使用示例-动静分离-1-访问结构页面-效果图](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Nginx-%E4%BD%BF%E7%94%A8%E7%A4%BA%E4%BE%8B-%E5%8A%A8%E9%9D%99%E5%88%86%E7%A6%BB-1-%E8%AE%BF%E9%97%AE%E7%BB%93%E6%9E%84%E9%A1%B5%E9%9D%A2-%E6%95%88%E6%9E%9C%E5%9B%BE.png)

### 高可用

<u>高可用是在负载均衡的基础上，为了解决其中可能出现的问题提出的</u>

在负载均衡中，实现了将请求分发到其它服务器上进行处理从而实现负载的分离效果。在这个过程中，一个用于分发的 nginx 就显得极其重要，它能够实现将请求具体分发到不同服务器的功能

<u>如果这个用于处理请求的 nginx 服务器出现了重大错误而导致宕机</u>，负载均衡的整个过程就都失败了，此时需要使用高可用集群来解决

其使用的思想就是，使用了两台用于处理负载均衡的 nginx 服务器，将其设定为：**主服务器，master 服务器**；**从服务器，备份服务器，backup 服务器**

通常都是主服务器在运行，备份服务器监视主服务器的状态，如果主服务器出现了错误导致宕机，备份服务器就能接替主服务器的功能，并继续运行

![Nginx-使用示例-高可用-概念图](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Nginx-%E4%BD%BF%E7%94%A8%E7%A4%BA%E4%BE%8B-%E9%AB%98%E5%8F%AF%E7%94%A8-%E6%A6%82%E5%BF%B5%E5%9B%BE.png)

> 为了实现高可用，需要借助 `keepalived` 软件，其功能类似路由
>
> 通过这个软件，能够设置虚拟ip，将不同的服务器纳入其中管理，而对外暴露虚拟ip进行访问

#### 示例

> 需要两台 nginx 服务器，能够共同实现负载均衡的功能，在关闭其中任意一台都不会对其整体的运行造成影响

##### 准备工作

根据需求，首先需要两台 nginx 服务器，还需要在其中安装 `keepalived`，之后为整体设置虚拟 ip









[^1]: 英特尔联合创始人戈登·摩尔(Gordon Moore)在 1965 年提出的，即集成电路上可容纳的元器件的数量每隔 18 至 24 个月就会增加一倍，性能也将提升一倍