# Docker

Docker是一个开源的**<u>应用容器引擎</u>**，基于Go语言并遵循Apache2.0协议开源

Docker可以让开发者打包他们的应用以及依赖包到一个轻量级、可移植的容器中，然后发布到任何流行的linux机器上，也可以实现虚拟化

容器是完全使用<u>沙箱</u>机制，相互之间不会有任何接口，更重要的是容器性能开销极低

Docker**支持将软件编译为一个镜像**，然后去镜像中各种软件做好配置，将镜像发不出去，其他使用者可以直接使用这个镜像。运行中的镜像被称为容器，容器启动是非常迅速的，类似windows中的ghost操作系统，安装之后就可以直接使用

## 简介

Docker类似虚拟机或者说类似系统安装时的镜像系统，虽然并不相同，其中的思路类似

Docker的核心就==是能够将一个个的软件编译为镜像==，而运行中的镜像，被称为**容器**

### 核心概念

- images

  镜像，Docker镜像是用于创建Docker容器的模板

- Container

  容器，是独立运行的一个或一组应用

- Client

  客户端，通过命令行的方式或其他工具使用Docker

  用于连接Docker主机，并进行操作

- Host

  主机，一个物理或虚拟的及其用于执行Docker守护进程和容器

  简单理解，就是安装了Docker程序的机器，只要将Docker安装到任意系统的计算机，都可以被称为Docker主机

- Registry

  仓库，用于保存镜像，可以理解为代码控制中的代码仓库

  Docker Hub提供了庞大的镜像集合供使用

## 使用步骤

此处主要讲解的是Linux安装Docker的过程

1. 安装Docker

   在Linux系统中，首先需要查看CentOS系统的内核版本，必须高于3.10

   ```shell
   # 查看系统内核版本
   uname -r
   # 如果内核版本不到3.10，就需要对其进行升级【选做】
   yum update
   ```

   正式开始安装Docker，首先需要进行联网，之后使用如下的代码，安装网络上的Docker

   ```shell
   # 安装docker
   yum install docker
   # 启动docker
   systemctl start docker
   # 可以使用查看docker版本号的命令来确认其是否已经启动
   # docker -v
   # 设置docker服务开机启动
   systemctl enable docker
   
   # 停止docker的命令
   # systemctl stop docker
   ```

2. 去Docker仓库找到软件对应的镜像

   具体使用的命令可以查看常用命令中的检索、拉取、查看等命令

   其中具体下载的内容以及版本号，见https://hub.docker.com/

3. 使用Docker运行对应镜像，这个镜像会生成一个Docker容器

   在安装了镜像之后，只需要运行镜像，就能产生它的容器（每次运行都会产生一个容器）

4. 对容器的启动和停止就是对软件的启动和停止

## 常用命令

此处将会列举docker中，在Linux环境下的常用操作

### 镜像操作

#### 检索

使用docker hub，检索所需的镜像详细信息，如镜像的TAG

```shell
# 检索某个关键字
docker search keyword
# e.g. docker search redis
```

推荐，还需要使用docker hub官方来进行搜索，其中可以查看更多重要的信息

#### 拉取

根据镜像名，获取对应的镜像

```shell
# 获取某个镜像
docker pull images-name [:tag]
# 之后可以使用:tag来指定版本，默认是latest
# 具体可以使用的标签可以查看docker hub网页
```

#### 查看列表

查看所有的本地镜像

```shell
# 查看所有的本地镜像
docker images
```

#### 删除镜像

删除某个指定的镜像

```shell
# 删除某个指定的本地镜像
docker rmi images-id
```

### 容器操作

主要包括各种镜像运行之后，对其生成的容器的各种操作

#### 运行镜像

运行镜像，生成容器

```shell
# 运行镜像
docker run [--name container-name] [-d] image-name
# --name用于指定生成的容器名称，-d表示该容器后台运行
# e.g. docker run --name mytomcat -d redis
# 此处image-name后如果刚才拉取的镜像使用了tag，就同样需要指定tag
```

但是直接使用这种方式的容器，通常并不能直接使用，如Tomcat，之后直接通过`ip:8080`也不能访问，此处还需要额外指定端口映射

默认情况下，其虽然有一个端口，但是不能使用（只是一个假的端口），必须手动为其分配一个Linux系统的端口才能使用

```shell
# 同样是运行端口的命令，其中添加-p用于指定映射系统的某个端口号
docker run [--name container-name] [-d] [-p portnumber] image-name
# e.g. docker run --name mytomcat -d -p 8080:8080 tomcat
```

> 如果端口映射之后是存在问题，可能是linux中的防火墙问题
>
> ```shell
> # 查看防火强状态
> service firewalld status
> # 关闭防火墙
> service firewalld stop
> ```

> Docker Tomcat可能会出现默认的Tomcat欢迎界面为404
>
> 这是因为Docker中的Tomcat进行了修改，其中默认的项目并没有存放在`webapps/`目录下，而是存放到了`webapps.dist`目录下，可以将原本的`webapps`目录删除，然后将`webapps.dist`重命名为`webapps`即可
>
> [快速链接](https://blog.csdn.net/weixin_44928809/article/details/103990182)

#### 容器列表

查看容器

```shell
# 查询容器列表
docker ps
# 其中会显示容器的基本信息，包括容器的id、对应的镜像、对应的端口以及刚才自定义的container-name
docker ps -a
# 可以显示所有的容器，包括已经退出的容器
```

#### 停止容器

停止某个指定的运行中容器

```shell
# 停止运行中容器
docker stop container-id
# 也可以使用自定义的容器名称
# docker stop container-name，但是更加推荐指定容器的名称
```

#### 启动容器

启动过一次的容器就可以直接再次启动，而不是运行镜像，**每次运行镜像都会生成一个新的容器**

这个的效果类似启动软件

```shell
# 启动停止中的容器
docker start container-id
```

#### 删除容器

删除已有的容器

```shell
# 删除容器
docker rm container-id
```

#### 容器日志

查看容器中产生的日志信息

```shell
# 产看容器的日志信息
docker logs container-id
```

### 其他命令

其他更多的命令可以查看[Docker官方文档](https://docs.docker.com/)

同时，很多的容器也有特定的命令，可以通过查看docker hub中，每个具体的镜像的文档，也有不同的配置

## 安装实例

### MySQL

安装Docker的过程省略

```shell
# 搜索docker中的镜像
docker search mysql
# 下载mysql镜像，此处制定了版本为5.7
docker pull mysql:5.7
# 启动运行mysql镜像，生成容器
# docker run --name mysql01 -d mysql 错误用法，会直接出现错误并退出
# 在官方的镜像文档中有说明，必须指定mysql的一个必要属性，才可以使用，分别时
# 正确的运行镜像方法
docker run --name mysql01 -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7
# 之后可以使用docker ps [-a]来查看是否成功运行
```

### Tomcat

```shell
# 下载 tomcat 镜像，没有指定，默认为latest
docker pull tomcat
# 运行镜像
# 其中通过使用 --network host 让tomcat使用本地的网络环境运行
docker run -d --name mytomcat --network host tomcat
```

在 docker 中安装的 Tomcat 服务器直接访问没有初始项目，因此会出现404，如果希望调出初始项目，需要经过如下的配置

404 出现的原因是因为 docker 中封装的项目都是将 `webapps` 目录的内容进行了重命名，因此无法直接读取。只需要将 `webapps.dist` 重命名为 `webapps` 即可

```shell
# 进入 docker 中 tomcat 的交互模式
docker exec -it <容器id> /bin/bash

# 将 webapps.dist 修改为 webapps
rmdir webapps
mv webapps.dist webapps
```

