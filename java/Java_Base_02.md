# Java

> 始 2020/8/3

## Java 网络

### Java NIO

在Java开发并发项目的需求越来越明显时，NIO这种支持高并发的IO流，已经变得越来越重要

> Java NIO在开发之初（Java 1.4）设计并不算优秀，这也是其最初使用较少的原因
>
> 在Java 7中，对NIO进行了重新的设计，让其变得更加合理，同时因为需求的增加，NIO逐渐成为如今Java的**必备知识**之一

Java NIO，Java New IO，是一套全新的Java输入输出API，用于==替换==标准的Java IO API

NIO与原本的IO有相同的作用和目的，但是使用的方式完全不同，NIO支持面向缓冲区的、基于通道的IO操作。**<u>NIO将以更加高效的方式进行文件的读写操作</u>**

|          | IO                        | NIO                           |
| :------- | ------------------------- | ----------------------------- |
| 原理     | 面向流（Stream Oriented） | 面向缓冲区（Buffer Oriented） |
| 是否阻塞 | 阻塞IO（Blocking IO）     | 非阻塞IO（Non Blocking IO）   |
| 基于网络 | （无）                    | 选择器（Selectors）           |

#### 概念

NIO 主要由三个核心组成：Channel（通道）、Buffer（缓冲区）、Selector（选择器）。传统的IO基于字节流和字符流进行操作，而NIO基于Channel和Buffer进行操作，数据总算是从通道读取到缓冲区，或者从缓冲区写入到通道中。Selector用于监听多个通道的事件（如：连接打开、数据到达等）。因此，单个线程可以监听多个数据通道

#### 三大核心

此处将分别介绍NIO中的三大核心，并通过实际例子来感受其实际的使用过程

##### Buffer

Buffer，缓冲区，在 Java NIO 中负责数据的存取。其底层使用的是数组来存放值，同时根据不同的数据类型，需要使用不同的缓冲区来进行存储（boolean除外）

- ByteBuffer
- CharBuffer
- DoubleBuffer
- FloatBuffer
- IntBuffer
- LongBuffer
- ShortBuffer

各个缓冲区的使用方法基本一致，需要获取对应的缓冲区对象，只需要使用对应的缓存区类中的静态方法来获取对象

###### 基本概念

缓冲区在原本的基础上，缓冲区还被分为 <u>**直接缓冲区和非直接缓冲区**</u>

- 非直接缓冲区

  通过使用 `allocate()` 方法来分配缓冲区

  将缓冲区建立在 JVM 的内存中

- 直接缓冲区

  通过使用 `allocateDirect()` 方法来分配直接缓冲区

  将缓存区直接建立在物理内存中，==可以用于提高效率==

  > 但是有风险，通过这样的方式使用，会导致<u>数据超出GC的控制范围</u>，必须自行控制内存释放，不然会造成严重内存泄漏问题
  >
  > 同时，<u>分配和取消直接缓冲区对系统所带来的开销，也高于非直接缓冲区</u>

是否是直接缓冲区可以通过使用 `isDirect()` 方法来进行确认

非直接缓冲区的原理如下图：

![NIO-Buffer-非直接缓冲区](http://qeqw5fw41.bkt.clouddn.com/img/NIO-Buffer-%E9%9D%9E%E7%9B%B4%E6%8E%A5%E7%BC%93%E5%86%B2%E5%8C%BA.png)

为了系统的安全，系统中的数据不能直接传输到应用程序中，应该Java运行在JVM中，因此此处也就是系统与JVM之间的传输过程

系统将数据，首先通过自定义的接口，传输到内核地址空间中，通过其中转，**<u>复制</u>**到了JVM的用户地址空间中，才能被JVM中的应用所读取

<u>这个复制的过程造成非直接缓冲区不必要的系统资源消耗</u>

通过查看底层源码，`allocate()` 方法是在堆内存中开辟空间，作为缓存

---

直接缓冲区原理如下图：

![NIO-Buffer-直接缓冲区](http://qeqw5fw41.bkt.clouddn.com/img/NIO-Buffer-%E7%9B%B4%E6%8E%A5%E7%BC%93%E5%86%B2%E5%8C%BA.png)

相比非直接缓冲区，省略了中间复制的过程，转而使用的是物理内存的方式，来提高了效率

通过这样的方式所映射的物理内存，执行的时间是不定的，需要GC来始放连接，系统也需要进行文件的操作，而这两个过程都是不可控的（GC执行的时间和系统的处理的时间）

相对的，<u>这样的内存消耗较大；同时这样的内存使用超出了JVM的控制，不太安全</u>

一般在某个资源需要长时间使用时再考虑使用直接缓冲区

###### 主要方法

`allocate()`/`allocateDirect()` `param capacity(int)` `return Buffer`

用于获取一个具体的缓冲区对象

创建时，需要直接指定缓冲区中的 `capacity` 容量大小

---

`put()` `param 数据(数组)` `return this(对象本身)`

存入数据到缓冲区中，返回的值为对象本身，因此支持链式使用

`get()` 

获取缓冲区中的数据

其中由多种方式来获取，如，指定返回的长度，使用一个数组来进行接收，根据使用的方法不同，返回值也会有所不同（总体而言和输入流中获取的方法类似）

`clear()`

清空缓冲区

在清空缓冲区的同时，会将 `position`、`limit`、`capacity` 调整到最初状态

==虽然方法的描述是清空缓存区，但是数据依然存在，但是因为limit、position被移动，导致无法得到正确的数据==

`compact()`

清理缓冲区

与清空缓存区的效果类似，一般是读取完Buffer中的数据之后调用，为下一次的写入做准备。会将未读取的数据重新拼接到数组的开头，之后添加的数据会在其之后添加

> `clear()` vs. `compact()`
>
> - 前者
>
>   直接将所有属性回到初始值，无论是否已经读取完成
>
> - 后者
>
>   会因为没有读取完毕，将未读取的数据（`position` 和 `limit` 之间的部分），放到数组的开头
>
>   将 `position` 放到未读取部分之后（`limit` 减 `position`的结果），其他三项依然回到初始值

`hasRemaining()` / `remaining()`

前者用于判断当前缓冲区，是否还有数据没有进行读取，即判断 `position` 是否等于 `limit` 

后者用于获取当前缓冲区中可以操作的数据数量

---

`flip()`

切换为读取模式

具体的效果为，移动 `position`，到数组的起点（下标0），转而将 `limit` 移动到 `position` 原本的位置

> 读写模式切换
>
> ![read and write mode](http://qeqw5fw41.bkt.clouddn.com/img/NIO-Read%20and%20Write%20Mode.png)

`rewind()`

将 `position` 移动到数组开头位置（下标0）

<u>使用该方法主要用于重新（重复）读取之前已经读过的缓冲区中的数据</u>

`mark()` / `reset()`

两个方法一般一同使用，`remark()` 将 `position` 记录到 `mark` 中保存；在有需要的时候，通过调用 `reset()` 方法，将 `mark` 中的值重新传到 `position` 中

通过该方法的使用，类似实现类似书签的效果，实现部分数据的重复读取（`rewind()` 方法是所有的值都需要重新读取）

###### 核心属性

`capacity` 容量，表示缓冲区中最大存取数据的容量。一旦声明（获取到对象）就不能再修改

在获取缓冲区对象时，就需要指定缓冲区的大小，并锁定

可以通过使用 `capacity()` 方法来获取容量值

---

`limit` 界限，表示缓冲区中，可以操作数据的大小。指定了 limit 之后，在其外（容量之内）的数据，无法进行读写

默认情况下，其中的 `limit` 默认情况下，等于 `capacity` 的值

可以通过使用 `limit()` 方法来进行获取

---

`position` 位置，表示缓冲区中，正在操作数据的位置（类似工作指针）

该值的大小会根据输入输出操作而不断变化

可以通过使用 `position()` 方法来进行获取

> 其中，三者的值，必须满足如下的大小关系
>
> position <= limit <= capacity

---

`mark` 标记，主要用于 `position` 跳转的依据，通过调用方法，设置标记或者标记跳转

默认为0，通过调用 `mark()` 方法，将当前的 `position` 值传入并保存到  `mark` 中，之后使用 `reset()` 方法，就能直接将 `mark` 的值重新赋给 `position`

*类似书签的使用*

##### Channel

Channel，通道，在 `java.nio.channels` 包中进行定义，用过作为IO源与目标打开的连接

通道类似IO中的**流**，但是Channel本身并不能实现数据的方位，Channel只能与Buffer进行交互

在Channel接口，也存在不同的实现，此处是几个最常用的实现

- FileChannel 【本地文件】

  ---

- SocketChannel 【TCP 客户端】

- ServerSocketChannel 【TCP 服务端】

- DatagramChannel 【UDP】

###### 基本概念

在操作系统中，通道的实现原理，随着技术的进步，其实现的方式也开始转变

最早期的操作系统，如果IO接口时，直接使用CPU来进行操作，而这样的使用方式，导致CPU的被长时间的占用，从而降低的了操作系统的运行效率

![NIO-Channel-原理-最早期](http://qeqw5fw41.bkt.clouddn.com/img/NIO-Channel-%E5%8E%9F%E7%90%86-%E6%9C%80%E6%97%A9%E6%9C%9F.png)

因为发现了这其中的问题，随着技术的发展，改进后的IO接口使用变得更加合理

在内存和IO 接口之间，设置了DMA[^1]，在用户读写请求期间，CPU不会进行干预，而是全权交给DMA进行处理

然而，使用DMA在处理大量的读写请求时，依然会因为DMA的处理能力限制，创建大量IO接口，而导致系统的性能依然收到影响

![NIO-Channel-原理-改进后](http://qeqw5fw41.bkt.clouddn.com/img/NIO-Channel-%E5%8E%9F%E7%90%86-%E6%94%B9%E8%BF%9B%E5%90%8E.png)

最终（到目前），通过不断改进，开始使用了一种全新的模式，在DMA的基础上，内存中得读写操作还能交给一个特殊的通道来进行处理

> DMA 此时也在，并没有废弃

此处的通道，是一个独立的处理器，专门用于IO操作，能够大幅提高IO操作的效率。比起DMA，能够更加独立的完成IO操作，而完全不需要CPU介入

![NIO-Channel-原理-最终](http://qeqw5fw41.bkt.clouddn.com/img/NIO-Channel-%E5%8E%9F%E7%90%86-%E6%9C%80%E7%BB%88.png)

###### 主要方法

`getChannel()` `return Channel`

获取通道的对象的方式一，对于支持通道的类提供的方法，通过对应的类对象就能获取到其对应的通道

其中支持通道的类，又可以被分为两类。

1. 本地的IO操作

   `FileInputStream`、`FileOutputStream`、`RandomAccessFile`

2. 网络的IO操作

   `Socket`、`ServerSocket`、`DatagramSocket`

`open()` `param Path, ...OpenOption` `return Channel`

> 在 Java 7 的 NIO2 中定义的静态方法

静态方法，通过对应的通道，获取通道对象

[`Files`] `newByteChannel()` `param  Path, options, FileAttribute`

通过调用`Files`工具类中的对应方法，来获取对应的通道对象

---

`transferFrom()` `param ReadableByteChannel, position, count(size)`

从另一个通道中获取数据。其中需要传入可读的字节通道、开始获取的位置、获取的大小

`transferTo()` `param position, count, WriteableByteChannel`

将通道中的数据传到另一个通道中。其中需要传入开始传输的位置、传输的大小、可写的字节通道

##### Selector

Selector，选择器，是 SelectableChannel 的多路复用器，<u>用于监控 SelectableChannel 的 IO 状况</u>，也就是说，<u>利用Selector可使一个单独的线程管理多个Channel</u> 

![NIO-Selector-SelectableChannel结构图](http://qeqw5fw41.bkt.clouddn.com/img/NIO-Selector-SelectableChannel%E7%BB%93%E6%9E%84%E5%9B%BE.png)

**<u>Selector 是非阻塞的核心</u>**

###### 基本概念

传统的IO流都是阻塞式的。也就是说，当一个线程调用 `read()` 或 `write()` 时，该线程会被阻塞，知道一些数据被读取或写入，该线程在此期间不能执行其他任务。因此，在完成网络通信IO操作时，由于线程会被阻塞，所以服务器端必须为每个客户都提供一个独立的线程进行处理，当服务器端需要处理大量客户端时，性能就会急剧下降

Java NIO 是非阻塞模式的。当线程从某通道进行读写数据时，若没有数据可用时，该线程可以进行其他任务。线程通常将非阻塞IO的空闲时间用于其他通道上执行IO操作，所以单独的线程可以管理多个输入和输出通道。因此，NIO可以让服务端使用一个或有限几个线程来同时处理连接到服务器端的所有客户端

###### 常用方法

`keys()` `return Set<SelectionKey>`

获取所有的 `SelectionKey` 集合。代表注册在该 `Selector` 上的 `Channel`

`selectedKeys()` `return Set<SelectionKey>`

被选择的 `Selectionkey` 集合，返回此 `Selector` 中已经被选择的键集

---

`select()` `param timeout(int)【可选】` `return int`

返回需要处理的 `Channel` 的数量

监听所有注册的 `Channel` ，当其中有需要处理的 IO 操作时，该方法返回并将应得的 `SelectionKey` 加入被选择的 `SelectionKey` 集合中，该方法返回这些 `Channel` 的数量

`selectNow()` `return int`

执行一个立即返回的 `select()` 操作，该方法不会阻塞线程

`wakeup()` `return Selector`

使一个还未返回的 `select()` 方法立即返回

---

`close()`

关闭选择器

###### 具体使用

Selector的使用大致步骤

1. 首先需要创建一个 `Selector` 对象

   `Selector`中的静态方法 `open()` 返回一个 `Selector`对象

2. 将通道注册到选择器中

   通过调用通道对象中的 `register()` 方法，将该通道注册到某个选择器中

   `register()` `param Selector, SelectionKey(int)` `return SelectionKey`

   该方法，需要指定两个参数：<u>容器传入哪个选择器</u> 和 <u>选择器对通道的监听事件</u>

   此处的 `int` 值，主要是指的 `Selectioney` 中的四个常量

   - `OP_READ`
   - `OP_WRITE`
   - `OP_CONNECT`
   - `OP_ACCEPT`

   如果此处需要监听不止一个事件，就需要使用<u>位或运算符</u> `|` （不是 `||`，这个可以在前者为 `false` 的前提下依然执行后者）

   如 `SelectionKey.OP_READ|SelectionKey.OP_WRITE`

###### SelectionKey

SelectionKey，选择键，用于表示 SelectableChannel 和 Selector 之间的注册关系

每次向选择器中注册通道时，就会选择一个事件（选择键）。选择键包括两个表示数值的操作集。操作集的每一位都表示该键的通道所支持的一类可选择操作

`channel()` `return SelectableChannel`

获取注册通道，通过 `SelectKey` 对象，反向获取注册时绑定该选择键的对象

同时，每个选择键所获取到的 `SelectableChannel` 也理论上不同（此处不包括注册了多种选择键的情况，某种意义上也算是只绑定了一个选择键）

在 `Selector` 会因为是否已经满足了运行条件（已经就绪）进行调用不同的`SelectionKey`

---

`selector()` `return Selector`

获取所绑定的选择器

---

`isReadable()` / `isWritable` / `isConnectable()` / `isAcceptable()`

检测 Channel 中的读/写/连接/接收状态是否已经就绪

#### 使用示例

此处将会随着NIO学习程度的进步而逐渐加强难度

##### 基本文件复制

通过 Buffer 和 Channel 的使用，就可以直接实现简单的文件复制操作

###### 实现方式一

通过非直接缓冲区来实现文件的复制操作

```java
/**
 * 测试NIO中的Channel
 * 用于文件复制（使用非直接缓冲区）
 * 文件大小: 1130720476
 * 花费时间: 11347
 */
@Test
public void channelTest1() {
  FileInputStream inputStream = null;
  FileOutputStream outputStream = null;
  FileChannel inputStreamChannel = null;
  FileChannel outputStreamChannel = null;
  try {
    long start = System.currentTimeMillis();
    inputStream = new FileInputStream("F:/1.mp4");
    outputStream = new FileOutputStream("F:/2.mp4");

    // 通过对象的 getChannel 方法，获取通道
    // 虽然 Channel 双向但是那是针对一个文件的，此处涉及到了两个文件，因此需要使用两个通道
    inputStreamChannel = inputStream.getChannel();
    outputStreamChannel = outputStream.getChannel();

    // Channel 本身不能操作文件，需要使用Buffer
    // 分配一个指定大小的非直接缓冲区
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    // 开始读写数据
    // 1. 将输入通道中的数据存入缓存区中
    while (inputStreamChannel.read(byteBuffer) != -1){
      // 将缓冲区切换为读数据模式
      byteBuffer.flip();

      // 2. 将缓冲区中的数据写入输出通道
      outputStreamChannel.write(byteBuffer);

      // 清空缓冲区
      byteBuffer.clear();
    }

    long end = System.currentTimeMillis();
    System.out.println("文件大小: " + inputStreamChannel.size());
    System.out.println("花费时间: " + (end - start));
  } catch (IOException e) {
    e.printStackTrace();
  } finally {
    // 关闭通道和输入输出流
    try {
      if (outputStreamChannel != null) {
        outputStreamChannel.close();
      }
      if (inputStreamChannel != null) {
        inputStreamChannel.close();
      }
      if (outputStream != null){
        outputStream.close();
      }
      if (inputStream != null){
        inputStream.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    // 关闭流
  }
}
```

###### 实现方式二

通过直接缓冲区来实现文件的复制操作

```java
/**
 * 文件复制（使用物理地址映射的方式）
 * 文件大小: 1130720476
 * 花费时间: 978
 */
@Test
public void channelTest2() {
  FileChannel inChannel = null;
  FileChannel outChannel = null;
  try {
    long start = System.currentTimeMillis();

    // 其中，可以输入一个 OpenOption ，存在三个实现（enum枚举类），常见使用 StandardOpenOption
    // 通过FileChannel中的静态方法，获取一个Channel对象
    inChannel = FileChannel.open(Paths.get("f:/1.mp4"), StandardOpenOption.READ);
    // StandardOpenOption 中常见使用 READ\WRITE\CREATE（创建文件，如果存在就覆盖）\CREAT_NEW（不存在就创建，存在就报错）
    // 注意：此处的模式应该添加在必要的写和创建的基础上，添加读模式。因为之后输出缓冲区的获取是通过通道的，而其中可以获取的只有读写模式，因此此处必须也能支持读写
    outChannel = FileChannel.open(Paths.get("f:/3.mp4"), StandardOpenOption.READ, StandardOpenOption.WRITE,
                                  StandardOpenOption.CREATE);

    // 获取物理内存中的映射地址 （这种方式，只有ByteBuffer能够支持）
    // 其中需要指定 MapMode，position，size ，本质上与使用 allocateDirect效果一致
    // 此处表示【使用只读模式，从下标0开始读取到inChannel最后】
    MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
    MappedByteBuffer outBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

    // 使用物理地址映射的方式，不需要使用通道进行传输，可以直接通过缓冲区就实现文件的操作
    byte[] bytes = new byte[1024];
    while (inBuffer.hasRemaining()){
      int len = Math.min(inBuffer.remaining(), 1024);
      inBuffer.get(bytes, 0, len);
      outBuffer.put(bytes, 0, len);
    }

    long end = System.currentTimeMillis();
    System.out.println("文件大小: " + inBuffer.limit());
    System.out.println("花费时间: " + (end - start));
  } catch (IOException e) {
    e.printStackTrace();
  } finally {
    // 关闭通道
    try {
      if (inChannel != null){
        inChannel.close();
      }
      if (outChannel != null) {
        outChannel.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
```

> ==特别注意==
>
> 使用直接缓冲区的方式，与非直接缓冲区存在很大的差别
>
> 1. 直接缓冲区的文件操作并<u>不需要使用 Channel 来获取其中的数据</u>，直接就可以通过缓冲区来实现文件的操作
> 2. 使用直接缓冲区时，缓冲区**只能**选择字节缓冲区 `ByteBuffer`

###### 实现方式三

通过使用通道之间的传输来实现文件的复制

```java
/**
 * 通道之间传输的方法
 * 使用的是直接缓冲区的方式
 * 文件大小: 1130720476
 * 花费时间: 7119
 */
@Test
public void channelTest3() {
  FileChannel inChannel = null;
  FileChannel outChannel = null;

  try {
    long start = System.currentTimeMillis();

    inChannel = FileChannel.open(Paths.get("f:/1.mp4"), StandardOpenOption.READ);
    outChannel = FileChannel.open(Paths.get("f:/3.mp4"), StandardOpenOption.READ, StandardOpenOption.WRITE,
                                  StandardOpenOption.CREATE);

    inChannel.transferTo(0, inChannel.size(), outChannel);
    // 使用transferFrom可以实现相同的效果，知识方向相反
    // inChannel.transferFrom(inChannel, 0, inChannel.size());

    long end = System.currentTimeMillis();
    System.out.println("文件大小: " + inChannel.size());
    System.out.println("花费时间: " + (end - start));
  } catch (IOException e) {
    e.printStackTrace();
  } finally {
    try {
      if (inChannel != null) {
        inChannel.close();
      }
      if (outChannel != null) {
        outChannel.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
```

###### 总结

通过使用两种不同的缓冲区进行对比，可以明显看出使用直接缓冲区的方式效率更高

但是，测试中，==直接缓冲区的方式对系统性能的影响非常明显，而非直接缓冲区就相较而言更小==

##### 分散和聚集

分散读取，Scattering Reads，将通道中的数据分散到多个缓冲区中；聚集写入，Gathering Writes，将多个缓冲区中的数据，聚集到通道中

其中，<u>分散读取时按照缓冲区的顺序，使用Channel中的数据将各个缓冲区依次填满；相对的，聚集写入时也会按照顺序读取缓冲区中的数据，并输出到通道中</u>

实际使用时，只是将缓冲区都存放到数组中，一同进行数据的读取和写入操作

```java
/**
 * 测试通道中的分散和聚集
 */
@Test
public void channelTest4() {
    RandomAccessFile readFile = null;
    FileChannel readFileChannel = null;
    RandomAccessFile writeFile = null;
    FileChannel writeFileChannel = null;

    try {
        // 分散读取
        // 本次测试使用RandomAccessFile来获取通道，并使用
        readFile = new RandomAccessFile("1.txt", "r");
        readFileChannel = readFile.getChannel();

        // 为了测试分散，创建多个缓冲区，来依次获取通道中的数据
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(100);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);

        // 创建一个数组，来存放两个缓冲区
        ByteBuffer[] byteBuffers = {byteBuffer1, byteBuffer2};
        readFileChannel.read(byteBuffers);

        for (ByteBuffer byteBuffer : byteBuffers) {
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
            System.out.println("==============================");
            // 使用array()读取数据，并不需要使用rewind()方法移动position
        }

        // 聚集写入
        writeFile = new RandomAccessFile("2.txt", "rw");
        writeFileChannel = writeFile.getChannel();

        // 将所读取到数据的所有缓冲区中的数据，写入到目标文件中
        writeFileChannel.write(byteBuffers);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        // 关闭通道
        try {
            if (readFileChannel != null) {
                readFileChannel.close();
            }
            if (writeFileChannel != null) {
                writeFileChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

##### 阻塞与非阻塞

NIO的一大特色就是支持非阻塞的文件传输，为了切实感受到其具体的使用，此处将分别使用阻塞式的NIO和非阻塞的NIO来进行网络的文件传输

###### 阻塞式

首先是客户端，用于读取本地的文件，并发送到服务端。之后，还需要读取服务端响应的数据

```java
/**
 * 测试阻塞的IO传输
 * 此处将会使用的是网络的通道来进行传输
 * 客户端
 */
@Test
public void blockingClientTest() {
  SocketChannel socketChannel = null;
  FileChannel fileChannel = null;
  try {
    System.out.println("客户端启动……");
    // 获取通道
    socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9000));
    // 从本地读取文件 1.png ，发送到服务端
    fileChannel = FileChannel.open(Paths.get("1.png"), StandardOpenOption.READ);

    // 分配指定大小的缓冲区
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    while (fileChannel.read(byteBuffer) != -1) {
      // 切换为读取模式
      byteBuffer.flip();
      // 将缓冲区中的数据写入到输出通道中
      socketChannel.write(byteBuffer);
      // 清空缓冲区
      byteBuffer.clear();
    }

    System.out.println("客户端发送完成……");
    // 向服务器发送信息，表示发送已经结束
    socketChannel.shutdownOutput();

    // 接收来自服务端的反馈
    int len;
    while ((len = socketChannel.read(byteBuffer)) != -1) {
      byteBuffer.flip();
      // 显示来自服务端的信息
      System.out.println(new String(byteBuffer.array(), 0, len, StandardCharsets.UTF_8));
      byteBuffer.clear();
    }

    System.out.println("客户端关闭");
  } catch (IOException e) {
    e.printStackTrace();
  } finally {
    // 关闭通道
    try {
      if (socketChannel != null) {
        socketChannel.close();
      }
      if (fileChannel != null) {
        fileChannel.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
```

服务端，用于接收来自客户端的文件，并将其保存到客户端，并向客户端相应信息

```java
/**
 * 测试阻塞的IO传输
 * 服务端
 */
@Test
public void blockingServerTest() {
  ServerSocketChannel serverSocketChannel = null;
  SocketChannel socketChannel = null;
  FileChannel fileChannel = null;
  try {
    System.out.println("服务端启动……");
    // 与客户端对应
    // 获取服务端通道
    serverSocketChannel = ServerSocketChannel.open();
    // 指定服务器通道绑定的端口号
    serverSocketChannel.bind(new InetSocketAddress(9000));

    // 获取客户端连接的通道
    socketChannel = serverSocketChannel.accept();
    // 客户端的地址，这里可以表示服务器接收到了来自那里的数据
    System.out.println("remote address: " + socketChannel.getRemoteAddress());
    // 客户端发送到哪个地址，这里指的是服务器端路径
    System.out.println("local address: " + socketChannel.getLocalAddress());
    System.out.println("获取来自客户端的数据，开始处理……");

    // 创建文件通道，用于将来自客户端的文件保存到本地
    fileChannel = FileChannel.open(Paths.get("4.png"),
                                   StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
    // 分配指定大小的缓冲区
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    // 读取/接收来自客户端的数据
    while (socketChannel.read(byteBuffer) != -1) {
      // 切换读取模式
      byteBuffer.flip();
      // 将数据写入输出的文件通道中
      fileChannel.write(byteBuffer);
      // 清空缓存
      byteBuffer.clear();
    }

    System.out.println("客户端文件写入完成……");
    // 结束获取数据的操作
    socketChannel.shutdownInput();

    // 响应信息给客户端
    byteBuffer.put("服务端接收文件完成".getBytes(StandardCharsets.UTF_8));
    // put 之后，需要使用flip切换为读取模式，将其写入响应中
    byteBuffer.flip();
    socketChannel.write(byteBuffer);

    System.out.println("服务端关闭");
  } catch (IOException e) {
    e.printStackTrace();
  } finally {
    // 关闭通道
    try {
      if (serverSocketChannel != null) {
        serverSocketChannel.close();
      }
      if (socketChannel != null) {
        socketChannel.close();
      }
      if (fileChannel != null) {
        fileChannel.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
```

> ==重点==
>
> 在使用阻塞式的网络IO中，简单的发送接收数据都很好理解，但是比较困难的点体现在：<u>如何在服务端接收完成数据之后，向客户端发送响应？客户端如何接收响应？</u>
>
> 首先，因为是阻塞式，导致服务端如果在没有接收到特殊信号的情况下，<u>不能确定客户端的数据是否已经完成了传输</u>，使得服务端无法停止接收数据的动作而导致方法无法继续进行。因为服务端没有继续进行的情况下，客户端也就因为没法获取到来自服务端的数据而同样无法运行，发生了类似**死锁**的现象
>
> 在阻塞的情况下，希望解决这样的情况，只能使用方法 `shutdownInput()` 或者 `shutdownOutput()` 方法来标识接收或发送已结束

###### 非阻塞式

使用非阻塞的方式，服务端必须借助选择器，来监控所有的Channel，当其满足条件之后，就会调用并执行后续的方法

客户端，能够向服务端发送数据

```java
/**
 * 非阻塞式的IO传输
 * 客户端
 */
@Test
public void unblockingClientTest() {
  System.out.println("客户端启动");

  SocketChannel socketChannel = null;

  try {
    // 获取通道
    socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9000));
    // 切换成非阻塞模式
    socketChannel.configureBlocking(false);

    // 分配指定大小的缓冲区
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    // 发送信息到服务端
    byteBuffer.put(("测试数据: " +System.currentTimeMillis()).getBytes());
    byteBuffer.flip();
    socketChannel.write(byteBuffer);

    System.out.println("客户端数据发送完成");
  } catch (IOException e) {
    e.printStackTrace();
  } finally {
    try {
      if (socketChannel != null) {
        socketChannel.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  System.out.println("客户端关闭");
}
```

服务端，能够接收多个客户端向客户端的请求，并对其消息进行处理

```java
/**
 * 非阻塞的IO传输
 * 服务端
 */
@Test
public void unblockingServerTest() throws IOException {
  System.out.println("服务端启动");

  // 获取服务端通道
  ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
  // 设置非阻塞模式
  serverSocketChannel.configureBlocking(false);
  // 绑定连接
  serverSocketChannel.bind(new InetSocketAddress(9000));

  // 获取选择器
  Selector selector = Selector.open();
  // 将通道注册到选择器上
  // 该方法需要接收两个参数，分别表示：选择器对象 和 监听事件（在对应时间发生时，才会调用调用非阻塞的通道）
  // 如果需要监听多个状态，可以使用 位或运算符 "|"
  // 此处将监听的时接收事件
  serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

  // 轮询式的获取选择器上已经准备就绪的事件（通道）
  while (selector.select() > 0) {
    // 获取当前选择器中所有的注册的选择键（已经注册的监听事件），中已经就绪的事件
    Iterator<SelectionKey> selectionKey = selector.selectedKeys().iterator();

    while (selectionKey.hasNext()) {
      // 获取准备就绪的事件
      SelectionKey s = selectionKey.next();
      // 判断具体的事件类型
      // 如果是接收事件
      if (s.isAcceptable()) {
        // 通过通道获取来自客户端的数据
        SocketChannel socketChannel = serverSocketChannel.accept();
        // 此处获取到的客户端通道，必须转换为非阻塞模式
        socketChannel.configureBlocking(false);

        // 将该通道注册到选择器上
        socketChannel.register(selector, SelectionKey.OP_READ);

        System.out.println("与客户端连接成功");
      }
      // 是否是读就绪
      else if (s.isReadable()) {
        // 获取当前选择器上的读就绪状态的通道
        SocketChannel channel = (SocketChannel) s.channel();

        // 读取数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 读取来自客户端的信息
        int len;
        while ((len = channel.read(byteBuffer)) != -1){
          byteBuffer.flip();
          System.out.println(new String(byteBuffer.array(), 0, len));
          byteBuffer.clear();
        }
      }

      // 取消选择键，防止重复执行
      selectionKey.remove();
    }
  }
  System.out.println("服务端结束");
}
```

> ==重点==
>
> 使用非阻塞式的IO接口，必须借助Selector进行管理，让其中涉及到的通道都纳入其中管理
>
> 此处比较容易忽略的细节，因为使用了轮询的方式（整个外层循环无法跳出，因此服务端不会停止），其中会使用循环的方式试图获取连接，而获取连接处（客户端代码 34 ~ 44行），因为有客户端连接，而使得选择器中的连接状态通过，此处获取到的客户端数据通道应该也使用Selector进行管理
>
> 之后，在内层循环的最后，将迭代器的当前项（处理完成）删除，防止重复处理同一个请求
>

##### UDP测试

之前的阻塞与非阻塞中，使用的是TCP的方式发送数据，此处将测试使用UDP的方式，即使用 `DatagramChannel`

客户端，可以循环发送数据

```java
/**
 * 测试NIO的非阻塞UDP传输
 * 客户端
 */
@Test
public void unblockingUDPSendTest() {
    System.out.println("客户端启动");
    DatagramChannel datagramChannel = null;

    try {
        // 获取UDP通道
        datagramChannel = DatagramChannel.open();

        // 开启非阻塞模式
        datagramChannel.configureBlocking(false);

        // 获取缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 实时输入数据
        System.out.println("请输入数据：");
        Scanner scanner = new Scanner(System.in);

        // 只要有输入，就会执行
        while (scanner.hasNext()) {
            String s = scanner.next();
            byteBuffer.put((new Date().toString() + ": " + s).getBytes());
            byteBuffer.flip();
            // 发送数据
            datagramChannel.send(byteBuffer, new InetSocketAddress("localhost", 9000));
            
            byteBuffer.clear();
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (datagramChannel != null) {
                datagramChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    System.out.println("客户端关闭");
}
```

服务端，轮询获取数据，并显示在控制台

```java
/**
 * 测试NIO的非阻塞UDP传输
 * 服务端
 */
@Test
public void unblockingUDPReceiveTest() {
    System.out.println("服务端启动");
    DatagramChannel datagramChannel = null;
    try {
        // 获取UDP通道
        datagramChannel = DatagramChannel.open();
        // 切换非阻塞模式
        datagramChannel.configureBlocking(false);
        // 绑定服务端接收信息的端口号
        datagramChannel.bind(new InetSocketAddress(9000));

        // 获取选择器
        Selector selector = Selector.open();
        // 将通道绑定到选择器中
        datagramChannel.register(selector, SelectionKey.OP_READ);

        // 使用轮询方式，查看选择器中是否有注册的通道
        while (selector.select() > 0) {
            // 获取所有的选择键
            Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
            while (selectionKeyIterator.hasNext()) {
                // 获取当前选择键
                SelectionKey selectionKey = selectionKeyIterator.next();

                if (selectionKey.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                    datagramChannel.receive(byteBuffer);
                    byteBuffer.flip();
                    System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
                    byteBuffer.clear();
                }
            }
            // 取消选择键
            selectionKeyIterator.remove();
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (datagramChannel != null) {
                datagramChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    System.out.println("服务端关闭");
}
```

##### 通道测试

使用通道来进行通信

```java
/**
 * 测试管道通信
 */
@Test
public void pipeClientTest() {
    Pipe.SourceChannel sourceChannel = null;
    Pipe.SinkChannel sinkChannel = null;
    try {
        // 获取管道
        Pipe pipe = Pipe.open();

        // 将缓冲区中的数据写入管道
        // 获取管道的内部通道
        sinkChannel = pipe.sink();
        // 设置非阻塞
        sinkChannel.configureBlocking(false);

        // 获取指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 用户输入
        Scanner scanner = new Scanner(System.in);

        // 循环获取用户输入
        while (scanner.hasNext()) {
            String s = scanner.next();

            byteBuffer.put((new Date() + ": " + s).getBytes());
            byteBuffer.flip();
            sinkChannel.write(byteBuffer);

            byteBuffer.flip();
            sourceChannel = pipe.source();
            int len = sourceChannel.read(byteBuffer);
            System.out.println(new String(byteBuffer.array(), 0, len));
            byteBuffer.clear();
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (sinkChannel != null) {
                sinkChannel.close();
            }
            if (sourceChannel != null) {
                sourceChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

> 通道的方式，就是单向的数据传递
>

[^1]: DMA，Direct Memory Access，直接存储通道