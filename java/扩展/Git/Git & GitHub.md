# Git

主要是用于代码的版本控制

## 概念

- 协同修改

  多人并行不悖的修改服务器端的同一个文件

- 数据备份

  不仅保存目录和文件的当前状态，还能保存每一个提交过的历史状态

- 版本管理

  在保存每一个版本的文件信息的时候要做到不保存重复的数据，以节省存储空间，提高运行效率

  > SVN采用的是<u>增量式管理</u>[^1]的方式，而Git采用了系统快照的方式

- 权限控制

  对团队中参与开发的人员进行权限的控制（如测试人员只读，开发人员可读写）

  对团队外开发者奉献的代码进行审核（Git独有）

- 历史记录

  查看修改人、修改时间、修改内容、日志信息

  将本地文件恢复到某一个历史状态

- 分支管理

  允许开发团队在工作过程中多条生产线同时推进任务，进一步提高效率

### 优势

1. 大部分操作操作在本地完成
2. 完整性保证
3. 尽可能添加数据而不是删除或修改数据
4. 分支操作非常快截流程
5. 与Linux命令全面兼容

### 结构

Git 的结构分析，了解其结构能够更加有利于之后的使用

其中主要被分为三个部分：

1. 本地库
2. 暂存区
3. 工作区

#### 工作区

即，集中使用 Git 代码的部分

通过使用 `git add` 代码，能够将代码添加到暂存区中临时存储

#### 暂存区

临时存储的区域，准备发送但还没有发送的区域，可以随时取消

通过使用 `git commit` 代码，能够将代码发送到本地库中存储

#### 本地库

所有发布的数据集中存储的部分，其中会保存各个历史版本

#### 结构图

通过下图能够更加清晰得理解 Git 的结构

![Git-概念-结构图](http://qeqw5fw41.bkt.clouddn.com/img/Git-%E6%A6%82%E5%BF%B5-%E7%BB%93%E6%9E%84%E5%9B%BE.png)

#### 分支管理

在版本控制过程中，使用多分支的方式同时推进不同的任务

![Git-分支-概念-实例图](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E5%88%86%E6%94%AF-%E6%A6%82%E5%BF%B5-%E5%AE%9E%E4%BE%8B%E5%9B%BE.png)

> 其中，存在几个重要的命名规则
>
> - `master` 主干，应用的主要更新
> - `feature_` 表示某个具体功能的开发分支
> - `hot_fix` 针对系统所出现的重大 bug 进行修复，特指热修复

##### 优势

使用分支所带来的好处

- 可以同时并行推进多个功能，提高了开发的效率
- 各个分支在开发的过程中，如果某个分支的开发失败，不会对其他分支有任何的影响，只需要针对错误的分支重新开始即可

##### 使用

> [跳转](#分支-code)

## 安装使用

### 安装

此处主要说明其中比较关键的几点

> 在学习阶段，安装到的是window系统中

1. 路径选择

   必须是非中文路径，并没有空格

2. 文本编辑器的选择

   一般情况下，直接选择默认的 **Vim 编辑器**即可，因为其使用的方式和Linux中一致，也方便之后的过度

   ![Git-install-选择文本编辑器](http://qeqw5fw41.bkt.clouddn.com/img/Git-install-%E9%80%89%E6%8B%A9%E6%96%87%E6%9C%AC%E7%BC%96%E8%BE%91%E5%99%A8.png)

3. 配置环境变量

   通过配置，Git安装后会自动配置环境变量

   此处，可以直接选择第一种，不会修改环境变量，但是只能在 `Git Bash` 中使用Git的命令（此处一般也够用了）

   > 第二项 【官方推荐】 ：
   >
   > 将支持在Windows的命令界面中使用，还包括某些第三方的软件
   >
   > 第三项 【风险最大】 ：
   >
   > 使用Git以及某些可选工具来执行命令行，会导致 Windows中的某些原生命令直接失效，如 `find` 和 `sort`

   ![Git-install-环境变量](http://qeqw5fw41.bkt.clouddn.com/img/Git-install-%E7%8E%AF%E5%A2%83%E5%8F%98%E9%87%8F.png)

4. 远程连接

   使用什么方式去连接远程的服务

   此处一般使用默认的方式，即 OpenSSL 的方式

   > 第二项：
   >
   > 使用 Windows 的安全连接通道，有一定的局限性

   ![Git-install-远程连接](http://qeqw5fw41.bkt.clouddn.com/img/Git-install-%E8%BF%9C%E7%A8%8B%E8%BF%9E%E6%8E%A5.png)

5. 换行符兼容

   因为 Windows 和 Linux 中的换行符并不一致，为了能够正确读取，需要进行一定的转换

   一般情况下，使用第一项，可以在读取时，将 Windows 的 LF 转换为 Linux 的 CRLF，而写出时又会反向的转换

   ![Git-install-换行符兼容](http://qeqw5fw41.bkt.clouddn.com/img/Git-install-%E6%8D%A2%E8%A1%8C%E7%AC%A6%E5%85%BC%E5%AE%B9.png)

6. 终端设置

   选择执行Git命令的终端。此处可以选择使用默认 Git Bash 终端或者 Windows 的控制台界面

   > 一般情况下，因为Git使用的是Linux的命令，因此，<u>不推荐其与 Windows 的界面进行混用</u>

   ![Git-install-终端设置](http://qeqw5fw41.bkt.clouddn.com/img/Git-install-%E7%BB%88%E7%AB%AF%E8%AE%BE%E7%BD%AE.png)

### 使用方法

此处将列举并利用实例来演示 Git 的使用

#### 命令行

此处会按照大致的使用顺序来进行介绍

##### 本地库初始化

在使用本地库之前，需要将其初始化，即创建本地库

```shell
git init
```

执行命令之后，会出现如下的状态

在当前目录下创建一个隐藏的 `.git` 目录，用于存储初始化本地库的相关文件

![Git-使用步骤-初始化本地库](http://qeqw5fw41.bkt.clouddn.com/img/Git-%E4%BD%BF%E7%94%A8%E6%AD%A5%E9%AA%A4-%E5%88%9D%E5%A7%8B%E5%8C%96%E6%9C%AC%E5%9C%B0%E5%BA%93.png)

<u>`.git` 中存放是本地库相关的子目录和文件，不要删除，也不要胡乱修改</u>

###### 设置签名

在本地库初始化之后，需要进行设置签名的操作，用于保存本用户的信息，主要是为了区分不同开发人员的身份

一般情况下，签名中包括**用户名**和**邮箱**

> 此处的邮箱和用户名并没有什么关系，甚至可以省略

<u>签名信息和登录远程库（代码托管中心）的账号和密码没有任何关系</u>

其中还需要更具不同的级别进行区分

- 项目级别/仓库级别

  仅在当前本地库生效

- 系统用户级别

  针对整个系统中使用的用户生效（如Windows的某个账号登录都适用）

> ==不同级别的优先级==
>
> 会优先使用范围小的账户，如果项目中单独指定了项目级别的签名，就会优先使用项目级别的签名，之后才会考虑系统用户级别的签名
>
> **<u>如果两者都没有，就会出错</u>**

可以使用如下的 Git 命令来设置签名

```shell
# 设置签名
# 项目级别/仓库级别
# 项目级别的设置，必须在项目的根目录下才能进行配置
git config
# 系统用户级别
git config --global

# 此处以项目级别为例
# 设置用户名
git config user.name TMS_H2O
# 设置邮箱地址
git config user.email test@foxmail.com
```

> 一次好像只能设置一项，不能直接全部设置

设置完成之后，还可以通过如下的命令进行查看

```shell
# 如果是项目/仓库级别
# 在所设置的项目中的 .git 目录下（初始化文件）
cat .git/config

# 如果是系统用户级别
# 系统用户级别的配置信息，会存放到系统用户目录下（在 Windows 中就是 C:/Users/用户名）
# cd ~ 可以直接跳转到用户根目录
# ll -a 可以查看到项目中存在一个 git 相关的文件 .gitconfig 其中存放了用户的签名
cat .gitconfig
```

##### 文件控制

###### 工作区操作

可以通过使用如下的代码查看 Git 工作区的状态

```shell
# 在某个工作区下使用，可以查看工作区的状态
git status
```

如果是一个全新的 Git 工作空间，即其中没有任何的内容，之前也没有任何的发布数据时，一般会有如下的输出

![Git-status-初始状态](http://qeqw5fw41.bkt.clouddn.com/img/Git-status-%E5%88%9D%E5%A7%8B%E7%8A%B6%E6%80%81.png)

> On branch master    当前在 master 分支上
>
> No commits yet         没有任何已提交内容
>
> nothing to commit    没有可提交的内容

如果在工作空间中添加了文件，却还没有将其添加到 Git 进行管理，就会有如下的输入

![Git-status-未添加状态](http://qeqw5fw41.bkt.clouddn.com/img/Git-status-%E6%9C%AA%E6%B7%BB%E5%8A%A0%E7%8A%B6%E6%80%81.png)

> `Untracked files:
> (use "git add <file>..." to include in what will be committed)
>   test.txt`
>
> 表示检测到了工作空间下尚未纳入 Git 管理的文件 `test.txt`
>
> `nothing added to commit but untracked files present (use "git add" to track)`
>
> 还没有未发布的内容（指的是缓冲区），但是发现了<u>未被跟踪的文件</u>（即未纳入管理的文件）

###### 缓冲区操作

主要包括对如何将文件纳入 Git 中管理，以及怎么发布以及获取

将文件纳入 Git 管理，将被 Git 跟踪

```shell
# 使 test.txt 文件被 git 追踪，实际上就是将文件加入了缓冲区
git add test.txt
```

![Git-使用步骤-缓冲区-文件跟踪](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E4%BD%BF%E7%94%A8%E6%AD%A5%E9%AA%A4-%E7%BC%93%E5%86%B2%E5%8C%BA-%E6%96%87%E4%BB%B6%E8%B7%9F%E8%B8%AA.png)

在确认工作区的基础上，可以使其中的 `test.txt` 被 Git 追踪，即将文件加入了缓冲区

此处的输出中包含提示，可以使用 `git rm --cached <file>` 命令来将文件从缓冲区中取出，即恢复到没有被 Git 追踪的状态

```shell
# 将文件从缓冲区中取出
git rm --cached <file>
```

实际上就是删除了缓冲区中的对应文件，适用于已经加入缓冲区之后，又反悔并希望删除的场景

![Git-使用步骤-缓冲区-脱离Git控制](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E4%BD%BF%E7%94%A8%E6%AD%A5%E9%AA%A4-%E7%BC%93%E5%86%B2%E5%8C%BA-%E8%84%B1%E7%A6%BBGit%E6%8E%A7%E5%88%B6.png)

> 此处的删除仅仅是从 Git 的缓冲区中删除，工作区中的文件依然存在

###### 本地库操作

将工作区中的文件加入了缓冲区，仅仅是准备对其进行存储，而真正将其存储并管理，还需要将其加入本地库中

```shell
# 将文件提交到本地库中
git commit <file>
```

<u>**此处的提交，并不一定指的是从缓冲区中加入本地库**</u>。通过控制台的提示可知，只有在<u>文件没有纳入 git 的管理</u>的情况下，才必须先使用 `git add` 命令将其加入缓冲区，再进行提交；如果<u>文件已经被跟踪</u>的情况下，就可以直接使用 `git commit` 从工作区中直接将其变化添加到本地库

![Git-使用步骤-缓冲区 to 本地库-commit步骤](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E4%BD%BF%E7%94%A8%E6%AD%A5%E9%AA%A4-%E7%BC%93%E5%86%B2%E5%8C%BA%20to%20%E6%9C%AC%E5%9C%B0%E5%BA%93-commit%E6%AD%A5%E9%AA%A4.png)

此处在输入了代码之后，会弹出本文工具（安装时选择了Vim），在其中输入注释信息，用于描述本次的提交内容

> ```
> Please enter the commit message for your changes. Lines starting with '#' will be ignored, and an empty message aborts the commit.
> ```
>
> 请输入本次提交中的修改信息。
>
> 此处的的作用类似于代码中的注释，用于描述本次提交的内容
>
> 之后使用 `git status` 可以看到之前加入缓冲区的文件因为被提交而又一次显示没有文件可提交

<u>如果文件加入了本地库之后，源文件发生了改变</u>，此处使用 `git status` 命令就能看到其提示状态已经发生了改变 `Changes not staged for commit`

![Git-使用步骤-本地库-发生变化](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E4%BD%BF%E7%94%A8%E6%AD%A5%E9%AA%A4-%E6%9C%AC%E5%9C%B0%E5%BA%93-%E5%8F%91%E7%94%9F%E5%8F%98%E5%8C%96.png)

在控制台中提示，可以使用之前的操作，即依次使用 `git add` 和 `git commit` 将文件加入缓冲区，然后提交到本地库；或者，可以直接使用 `git commit` 命令将修改的文件添加到本地库中（<u>只有那些已经被 `git` 追踪的文件才可以直接使用，因此第一次的时候必须先使用 `git add`</u>）

> 此处补充**实际场景**中的 `git commit` 使用方式：
>
> `git commit -m "描述信息" 文件名`
>
> 原本的 `git commit` 每次使用都会开启 vim 编辑器，而在其中使用 `-m` 就可以不用开启 Vim 编辑器就能直接设置修改的注释

##### 版本控制

Git 的重要使用之一就是其对文件版本的控制

###### 查看版本

```shell
# 查看历史版本
# 只能查看到更早的版本
git log

# 其中接收如下的参数
# 在一行中，以比较美观的方式显示版本信息
# 其中只包括： 版本号/hash值 提交的文字描述
git log --pretty=oneline
# 在一行中，显示简要的版本信息
# 其中显示的内容基本上与 --pretty=oneline 一致，但是其中的hash值经过了简化
git log --oneline
```

使用能够直接获取并显示版本的信息，其中只会显示更老的版本（相比起当前版本）

![Git-版本控制-查看版本](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E7%89%88%E6%9C%AC%E6%8E%A7%E5%88%B6-%E6%9F%A5%E7%9C%8B%E7%89%88%E6%9C%AC.png)

> 其中分组存储了各个版本的信息，其中主要包括<u>4个部分</u>
>
> 1. `commit` 版本信息，使用hash码进行存储
> 2. `author` 作者的信息
> 3. `date` 提交时间
> 4. `(commit message)` 显示每次发布时设置的提交的注释信息
>
> <u>如果版本过多而出现多页显示的情况，可以使用如下的命令来进行页面的跳转</u>
>
> - `空格` 向下翻页
> - `b` 向上翻页
> - `q` 退出

> **其中接收的较常见的参数**
>
> 默认情况下，会完整的显示其中的数据，如果数据过多就会占据多页，不便于查看
>
> - `--pretty=oneline`
>
>   显示的每条数据将以较好的格式在<u>一行中显示</u>
>
>   ![Git-版本控制-查看版本-pretty oneline](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E7%89%88%E6%9C%AC%E6%8E%A7%E5%88%B6-%E6%9F%A5%E7%9C%8B%E7%89%88%E6%9C%AC-pretty%20oneline.png)
>
>   其中经过处理，只会显示提交的<u>版本号</u>和<u>提交的描述</u>
>
> - `--oneline`
>
>   与`--pretty=oneline` 效果类似，都会将数据压缩到一行中显示，但是其会影响其中的版本的输出
>
>   最终显示的每条数据所对应的版本都只会显示简写的hash值（不完整的hash）
>
>   ![Git-版本控制-查看版本-oneline](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E7%89%88%E6%9C%AC%E6%8E%A7%E5%88%B6-%E6%9F%A5%E7%9C%8B%E7%89%88%E6%9C%AC-oneline.png)

```shell
# 显示所有历史版本
# 其中包括了任意的版本，即之前与之后的版本都有
git reflog
```

相比起 `git log` 命令，`git reflog` 显得更加强大，其中会显示之前和之后的所有版本（如果版本进行了回退，就会出现之后的版本）

![Git-版本控制-查看版本-reflog](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E7%89%88%E6%9C%AC%E6%8E%A7%E5%88%B6-%E6%9F%A5%E7%9C%8B%E7%89%88%E6%9C%AC-reflog.png)

> 其中显示的内容类似 `git log --oneline` 的显示，但是有一个最明显的区别
>
> 其中有 `HEAD@{0}` 这样的信息，表示其距离当前指针需要进行几次跳动（此时的指针指向的是最新的依次提交）
>
> 其中<u>最初的提交也会有特别的提示</u>，如此处的第一次提交，就使用了 `commit(initial)` 来进行提示

###### 版本移动

在知晓了版本之后，就需要进行版本的切换控制

通过使用 `git reset` 命令指定其版本就能实现版本的跳转，同时在 Git 中提供了多种方式来进行版本的移动

- 通过索引值操作【推荐】

  ```shell
  # 通过索引的方式操作版本
  git reset --hard <索引值/版本hash值>
  # 跳转指定的版本，可以双向任意跳转
  ```

  ![Git-版本控制-版本跳转-索引的方式](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E7%89%88%E6%9C%AC%E6%8E%A7%E5%88%B6-%E7%89%88%E6%9C%AC%E8%B7%B3%E8%BD%AC-%E7%B4%A2%E5%BC%95%E7%9A%84%E6%96%B9%E5%BC%8F.png)

- 使用 `^` 符号

  ==只能向之前的版本（老版本）进行跳转==

  ```shell
  # 向前回退n个版本
  # 此处的n表示 ^ 重复n次
  git reset --hard HEAD^{n}
  # 如 git reset --hard HEAD^^ 后退了两个版本
  ```

  ![Git-版本控制-版本跳转-使用^的方式](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E7%89%88%E6%9C%AC%E6%8E%A7%E5%88%B6-%E7%89%88%E6%9C%AC%E8%B7%B3%E8%BD%AC-%E4%BD%BF%E7%94%A8%5E%E7%9A%84%E6%96%B9%E5%BC%8F.png)

  通过这样的方式，向前移动了一个版本，<u>如果希望向前移动多个版本，只需要添加对应数量的 `^` 符号</u>

- 使用 `~` 符号

  ==只能向之前的版本（老版本）进行跳转==

  与 `^` 的效果类似，主要是为了简化 `^` 的使用（如果需要跳转的版本过多的情况）

  ```shell
  # 回退n个版本
  # 此处的<n>表示将使用某个数字来代替，表示回退的版本
  git reset --hard HEAD~<n>
  # 如 git reset --hard HEAD~3 表示回退3个版本
  ```


  ![Git-版本控制-版本跳转-使用~的方式](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E7%89%88%E6%9C%AC%E6%8E%A7%E5%88%B6-%E7%89%88%E6%9C%AC%E8%B7%B3%E8%BD%AC-%E4%BD%BF%E7%94%A8~%E7%9A%84%E6%96%B9%E5%BC%8F.png)

> **其中接收的参数**
>
> `git reset` 命令中接收的参数
>
> - `--soft`
>
>   只会在本地库中移动 `HEAD` 指针，而不会修改工作区和缓冲区
>
> - `--mixed`
>
>   在本地库中移动 `HEAD` 指定，同时还会修改缓存区中的文件
>
> - `--hard`
>
>   会在本地库中移动 `HEAD` 指针，同时会修改暂存区和工作区中的文件

#####  文件删除与恢复

本质上，文件的删除和恢复，可以通过将工作区中的文件删除，之后使用 `git add` 和 `git commit` 提交到本地库，实现删除的效果

![Git-文件删除和恢复-删除文件](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E6%96%87%E4%BB%B6%E5%88%A0%E9%99%A4%E5%92%8C%E6%81%A2%E5%A4%8D-%E5%88%A0%E9%99%A4%E6%96%87%E4%BB%B6.png)

只要是提交到了本地库的文件，就可以通过使用文件的历史版本控制来实现文件的恢复，即通过使用 `git reset --hard` 命令恢复历史版本

##### 比较文件差异

比较文件与历史版本之间的具体差异

```shell
# 将工作区中的文件与缓冲区中的历史版本（上一次提交）进行比较
git diff <file>
```

将<u>工作区</u>中的文件与<u>缓冲区</u>中的文件进行比较

![Git-比较文件差异-未加入缓冲区](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E6%AF%94%E8%BE%83%E6%96%87%E4%BB%B6%E5%B7%AE%E5%BC%82-%E6%9C%AA%E5%8A%A0%E5%85%A5%E7%BC%93%E5%86%B2%E5%8C%BA.png)

> 其中的区别，<u>将使用 `-` 和 `+` 来表示，同时删除为红色修改为绿色</u>
>
> 如果某一行出现了修改，Git 将理解为，删除了原本对应行的内容，并插入了新的内容

如果需要将工作区中的文件与本地库中的历史版本进行比较，可以在其中加入具体的<u>版本索引</u>或<u>HEAD指针位置</u>

![Git-比较文件差异-比较本地库历史版本](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E6%AF%94%E8%BE%83%E6%96%87%E4%BB%B6%E5%B7%AE%E5%BC%82-%E6%AF%94%E8%BE%83%E6%9C%AC%E5%9C%B0%E5%BA%93%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC.png)

> Git 中对文件的管理是**<u>==针对行==</u>**的
>
> 如果某一行不同，就意味着将要删除某一行，使用另一行来替换

如果此处不指定文件名，就会比较工作区中的所有文件

##### 分支管理

对工作区的分支进行的操作

###### 查看分支



```shell
# 查看当前工作区间下的所有分支
git branch -v
```

![Git-分支-使用-查看所有分支](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E5%88%86%E6%94%AF-%E4%BD%BF%E7%94%A8-%E6%9F%A5%E7%9C%8B%E6%89%80%E6%9C%89%E5%88%86%E6%94%AF.png)

> 查看分支，其中会显示三项信息
>
> 1. 分支名
> 2. 分支当前版本hash值（简化）
> 3. 分支当前版本的提交描述

###### 创建分支

```shell
# 添加分支
# 创建时只需要指定具体的分支名，就能完成分支的创建
git branch <branch name>
```

![Git-分支-使用-创建分支](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E5%88%86%E6%94%AF-%E4%BD%BF%E7%94%A8-%E5%88%9B%E5%BB%BA%E5%88%86%E6%94%AF.png)

###### 切换分支

```shell
# 切换分支
# 切换到指定名称的分支
git checkout <branch name>
```

![Git-分支-使用-分支切换](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E5%88%86%E6%94%AF-%E4%BD%BF%E7%94%A8-%E5%88%86%E6%94%AF%E5%88%87%E6%8D%A2.png)

###### 合并分支

分支的合并，首先需要移动到被==合并的分支==上，如 `branch1` 希望合并放到 `master` 上，就应该在 `master` 上使用命令

```shell
# 将某个分支合并到此处的分支上
git merge <branch name>
# 如，将 hot_fit 合并到 master 上
# 在master下，使用代码 git merge hot_fit
```

![Git-分支-使用-合并](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E5%88%86%E6%94%AF-%E4%BD%BF%E7%94%A8-%E5%90%88%E5%B9%B6.png)

###### 解决冲突

在分支合并时，如果<u>两个分支之中进行合并时，两个分支都对**相同文件**的**同一行**代码都进行了修改</u>，Git 就不能直接进行简单的合并

此时就需要进行手动的解决冲突，其中主要有如下的步骤：

1. 编辑文件，删除其中的特殊符号，并修改文件确保逻辑正确

2. 将冲突的文件进行提交

   ==注意==，此时的提交操作不能指定具体的文件名称，会自动提交所有的冲突文件

![Git-分支-使用-冲突解决](https://cdn.jsdelivr.net/gh/TMSxH2O/tms_img/Git-%E5%88%86%E6%94%AF-%E4%BD%BF%E7%94%A8-%E5%86%B2%E7%AA%81%E8%A7%A3%E5%86%B3.png)

##### 远程库-GitHub

Git 与远程库的使用，此处使用的是 Github

Git 与远程库的连接，需要一个地址，在 Github中的初始 ReadMe.md 中就会显示相关的信息（其中默认支持两种方式，https和ssh，此处使用 https）

###### 远程别名

在正式开始使用前的准备工作，可以简化之后的操作，是使用远程库的重要步骤

```shell
# git 提供了保存对应的远程库的命令，可以避免之后的每次使用都需要输入网址

# 查看已经保存的所有远程地址
git romote -v

# 添加地址映射，为某个地址设置别名，之后就可以直接使用别名来代替某地址
git remote add <映射名> <地址值>
```

通过这样的方式，就简化了之后地址的使用，能够更加便捷的使用地址，而不需要每次都去某处复制

###### 推送操作

将本地库的中的数据推送到远程库中

```shell
# 在 Git 中的推送是针对分支的操作
# 从本地库向远程库推送的方法，其指定的分支名都代表的是本地的分支
git push <地址/别名> <本地分支名>
```

> 如果是第一次推送，一般需要进行登录操作

> 国内访问 github.com 可能延迟很高，可以使用官方镜像
>
> https://github.com.cnpmjs.org/[用户名]/[仓库名].git

###### 克隆操作

将远程服务端上的库克隆到本机。克隆操作不单单是实现了表面上简单的效果，其中主要包括了三个步骤：

1. 完整的将远程库中的数据下载到本地
2. 创建远程库地址别名（实现 `git remote` 的效果，对方指定的远程别名都可以使用）
3. 初始化本地库（实现 `git init` 的效果）

```shell
# 克隆远程库
git clone <地址>
```

克隆的方式主要是用于邀请其他成员一同开发项目，因此需要允许其他成员的提交操作

**旧版的 GitHub 操作**。需要在 GitHub 的对应项目中，点击 Setting > Collaborators，在窗口中可以通过对方的 GitHub 账户进行邀请，使其加入项目开发中。这之后，对方的 push 操作就可以直接修改远程库中的内容了

**新版的 GitHub 操作**。与旧版类似，但是 Collaborators 被修改为了 Manage access，其中的功能相同

在邀请了对方之后，会出现一个地址，对方通过地址访问，就会出现要求界面，接收即可

###### 抓取操作

将远程库中的内容抓取到本地库中

```shell
# 抓取远程库中的内容
# 从远程获取的操作中，分支名指的是远程库中的分支名
git pull <地址/别名> <远程分支名>

# 实际上，git pull 的效果等同于 git fetch 和 git merge 的共同使用
```

因为 `pull` 的效果等同于 `fetch` 和 `merge` 的共同使用，因此此处将具体讲解 `fetch` 和 `merge` 所带来的效果，以此可以更加具体的感受它的使用流程

```shell
# 抓取操作
git fetch <地址/别名> <远程分支名>
# 通过这个方法获取之后，会发现本地的文件并未发生改变
# 如果使用了 git branch -v 命令，就能查看到此时多出了一个分支
# 一般情况下，分支的名字是 别名/master

# 合并操作
git merge <分支名>
# 将远程库中获取到的分支于本地库中的分支进行合并
```

#### 命令列表

此处集中列举各种命令，简单说明使用的方法

> 格式简单说明
>
> 其中 `[]` 的内容表示可选； `<>` 表示必要内容，具体的内容根据实际场景有所不同

##### 查看状态

```shell
git status
# 可以查看工作区、缓冲区状态
```

##### 添加操作

```shell
git add <file>
# 将工作区中的新建/修改文件添加到缓冲区
```

##### 提交操作

```shell
git commit [-m "commit message"] <file name>
# 将缓冲区中的内容提交到本地库
# 如果不适用 -m 直接指定提交描述信息，就需要在之后开启的文本编辑器中进行设置
```

##### 查看历史记录

```shell
git log [--pretty=oneline]|[--oneline]
# 查看历史版本信息，如果没有使用之后的参数，每个版本就会显示完整的信息
# 其中的 --pretty=oneline 和 --oneline 只需要二选一
```

---

```shell
git reflog
# 显示版本信息，以及版本与当前指针之间的距离
# 此处显示所有的历史版本，包括之前和之后
```

##### 版本移动

```shell
git reset --hard <索引值/版本hash值>
# 根据版本的索引值进行不同版本的跳转【推荐】
# 可以进行任意版本的移动
```

---

```shell
git reset --hard HEAD^
# 版本回退的方法
# 其中根据需要回退的版本数，在 HEAD 之后使用对应数量的 ^
# 如，需要回退2个版本 git reset --hard HEAD^^
```

---

```shell
git reset --hard HEAD~{n}
# 版本回退的方法
# 其中，根据需要退回的版本数量，决定n的值
# 如，需要回退10个版本 git reset --hard HEAD~10
```

##### 比较文件

```shell
git diff [版本] [file]
# 如果没有指定版本，此处的比较就是和缓冲区中的上一个提交进行比较
# 如果指定了版本，就是和具体的某个版本进行比较

# 此处也可以不指定文件，就表示会将工作空间下的所有文件进行比较（包括删掉，但是本地库中有保存的版本）
```

##### 分支管理

```shell
# 创建分支
git branch <branch name>

# 查看分支
# 查看当前工作空间的所有分支
git branch -v

# 切换分支
git checkout <branch name>
```

##### 远程别名

```shell
# 添加远程别名
git remote add <别名> <地址值>

# 查看所有的远程别名
git remote -v
```

##### 推送操作

```shell
# 将本地库中的文件推送到远程库
git push <地址/别名> <本地分支名>
```

##### 克隆操作

```shell
# 将远程库中的现有项目获取到本地
# 克隆操作可以自动实现项目的初始化等操作，完全获取项目目前的环境、配置的远程别名等信息
git clone <地址>
```

##### 抓取操作

```shell
# 对远程库中的某个分支进行抓取
git push <地址/别名> <远程分支名>
# 使用此命令，如果抓取的是本地库中没有的分支，会新建分支；如果是已有的分支，会进行合并

# 抓取操作（分解）
git fetch <地址/别名> <远程分支名>
# 抓取下来的分支默认为 别名/远程分支名
# 合并操作
git merge <分支名>
```

#### 常用的快捷键

`ctrl + L` 清屏