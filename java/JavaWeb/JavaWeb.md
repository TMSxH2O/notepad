# JavaWeb

Web应用程序中，主要涉及两种架构

- C/S架构

  客户端/服务器端

- B/S架构

  浏览器/服务器端

## B/S架构详解

#### 资源分类

将界面中所传输的资源分成两类，其中包括静态资源和动态资源。

##### 静态资源

使用静态网页技术开发的资源。如：文本、图片、音频、视频、HTML、CSS、JavaScript。

###### 特点

- 所有用户访问，得到的结果是一样的。（不同的浏览器解析出来的效果可能不同，但是从服务器拿到的资源都是一样的）

##### 动态资源

使用动态网页技术发布的资源。如：jsp、servlet、php、asp

###### 特点

- 与静态资源不同，因为不同的用户请求，得到的结果也可能不同。
- 用户请求动态资源，服务器会运行对应的资源，将最后的**结果转换为静态资源**进行返回。

###### 总结

JavaWeb主要希望学习的其中的动态资源，但是因为最后返回的都是静态资源，所以还必须先学习其中的静态资源。

此处主要的目标就是<u>HTML、CSS、JavaScript</u>。

## 前端部分

### HTML

**HTML**（超文本标记语言——HyperText Markup Language）是构成 Web 世界的一砖一瓦。它定义了网页内容的含义和结构。除 HTML 以外的其它技术则通常用来描述一个网页的表现与展示效果（如 [CSS](#CSS)），或功能与行为（如 [JavaScript](#JavaScript)）。

“超文本”（hypertext）是指连接单个网站内或多个网站间的网页的链接。链接是网络的一个基本方面。只要将内容上传到互联网，并将其与他人创建的页面相链接，你就成为了万维网的积极参与者。

HTML不是一种编程语言，而是一种**标记语言**（Markup Language）。

用于搭建基础网页，展示页面的内容，是最基础的网页开发语言。

> HTML5
>
> 主要为了可以服务移动端，加入很多新的标签，同时不<u>再建议使用</u>之前很多的标签属性。此处所说的属性主要指某些用于<u>修改样式</u>的标签。但是依然可以使用，这是因为很多**浏览器**为了能够实现向下兼容。

HTML文件后缀名为`html`或者`htm`，两种方式效果完全相同。

#### 主要标签

##### 文件标签

构成html最基本的标签

- `html` 文档的跟标签
- `head` 头标签。用于指定html文档的一些属性，引入外部的资源
- `title` 标题标签
- `body` 体标签
- `<!DOCTYPE>` html5中定义该文档是html文档的方式 `<!DOCTYPE html>`

##### 文本标签

和文本有关的标签

- `<!-- -->` html中的注释

- `<h1>...<h6>`  标题标签，自带换行效果
- `<p>` 段落标签，自带换行效果，并且会与相邻模块之间存在一行间隙（多一此换行）
- `<br> ` 换行
- `<hr>` 水平分割线

##### 图片标签

用于图片的展示

- `<img>` 图片标签

> 路径
>
> 1. 相对路径
>
>    ” . “	 表示当前文件目录，默认情况，可以省略
>
>    ” .. "	表示上级目录
>
> 2. 绝对路径
>
>    使用URL的方式，指定服务器上的资源，或者网路上的某个唯一资源

##### 列表标签

- `<ol>` 有序列表
  - `type` 规定在列表中使用的标记类型。默认为1. 2.，可以修改为：1；A；a；Ⅰ；i
- `<ul>` 无序列表
- `<li>` 列表项

##### 表格标签

- `<table>` 定义表格
  - `border` 指定表格边框的宽度
  - `width` 规定表格的宽度
  - `cellpadding` 规定单元边沿与其内容之间的空白
  - `cellspacing` 规定单元格之间的空白
- `<tr>` 定义行
- `<td>` 定义单元格
  - `colspan` 合并列
  - `rowspan` 合并行
- `<th>` 定义表头单元格，默认<u>居中加粗</u>（与`<td>`同级）【与`<td>`中的属性也相同】
- `<caption>` 表示标题
- `<thead>`  表示表格的头部分
- `<tbody>` 表示表格的体部分
- `<tfoot>` 表示表格的脚部分

其中的`<caption>`到`<tfoot>`都<u>类似</u>语义标签，没有实际的样式，只是为了增强代码的可读性，但是这几个标签都是在HTML5之前就已经存在的标签。

##### 链接标签

- `<a>` 定义锚，或者说超链接。
  - `href` 规定链接指向的页面的 URL
  - `target` 规定在何处打开链接文档
    - `_self` 在当前页面打开【默认】
    - `_blank` 在新标签打开

> `<a>` 使用时会默认出现跳转的功能，如果只是希望它实现某些功能而不希望其进行跳转，可以在href中使用`javascript:void(0)`。

##### 块标签

- `<div>` 块元素。默认长度为一整行。通用型的流内容容器，在不使用CSS的情况下，其对内容或布局没有任何影响
- `<span>` 行内元素。默认长度会根据内容的长度进行变化。短语内容的通用行内容器，并没有任何特殊语义

`<div>` 和`<span>`元素很类似，但是前者是块元素，后者是行内元素

##### 表单标签

表示文档的一个区域，可以获取其中所包含的所有交互元件的数据，用于向服务器发送数据或提交信息。

- `<form>` 定义表单，块级元素，前后都会折行（换行）

  - `action` 指定接收数据的URL
  - `method` 指定提交的方法，一共有7种，其中2种最常用
    - get 
      1. 请求的参数会在地址栏中显示
      2. 请求参数的参数长度有限
    - post 
      1. 请求参数不会在地址栏中显示，会封装到请求体中。
      2. 参数长度没有限制
  - `name` 指定表单的名称。表单中的具体每一项希望传递值的元件必须指定`name`属性，否则无法传值。【表单本身的`name`不会影响传值】

  ------

  表单项

- `<input>` 输入控件。

  - `type` 规定input元素的类型

    - text 文本输入框**【默认】**

    - password 密码输入框

    - ------

      button 按钮

    - file 选择文件

    - submit 提交按钮，会将表单中的输入发送向`action`指定的URL

    - image 使用图片作为<u>提交按钮</u>

      - 使用`src` 属性，指定图片

    - color 取色器

    - reset 重置按钮，可以清除表单中的所有数据

    - data 选择日期【年月日】

    - datatime 选择日期，包括时间【年月日 时分秒】

    - email 输入邮箱【自动完成格式校验】

    - number 输入数字【只能输入数字，不能输入其他数据】

    - ------

      checkbox 复选框

    - radio 单选框

      - `name` 单选框或者复选框之间希望实现关联，那么对应的`name`应该相同
      - `value` 决定每个选项的值
      - `checked` 设置默认选中

    - ------

      hidden 隐藏域，虽然不会显示，但是其中的数据依然会被提交

  - `placeholder` 在文本框类型的输入框中，显示提示信息。如text、password类型的输入框中，都适用。

- `<select>` 下拉列表

- `<option>` 下拉列表项【`<select>`的内部标签】

- `<textarea>` 文本域

  - `cols` 指定列数，每一行有多上个字符
  - `rows` 默认显示行数，达到指定行数之后会自动扩行，出现滚动条

##### 语义化标签

在HTML5中加入，并没有实际的效果，只是为了增加代码的<u>可读性</u>。之后可以搭配CSS进行使用。

- `<header>` 页面的头，页眉
- `<footer>` 页面的脚，页脚

### CSS

CSS 是 **Cascading Style Sheet**  的缩写。译作「层叠样式表单」。是用于(增强)控制网页样式并允许将样式信息与网页内容分离的一种标记性语言。 

层叠，意味着多个样式可以作用在同一个html的元素上

用于美化页面，以及页面的布局。

#### 使用CSS的意义

1. 功能强大
2. 将内容的展示和样式控制分离，可以降低耦合度，更容易进行分工合作，提高了开发的效率

#### CSS的使用

CSS和html结合的方式

1. 内联样式

   在标签内使用`style`属性，指定CSS的代码【不推荐】

   作用在当前标签上

2. 内部样式

   在`<head>`标签内，定义一个`<style>`标签，在标签内部使用CSS代码。

   作用范围针对当前页面

3. 外部样式

   1. 定义一个外部的CSS资源文件

   2. 在`<head>`标签内，定义一个`<link>`标签（内部使用href属性）来引入外部的CSS文件

      > 此处也可以使用`<style>`标签，在内部使用@import来引入css文件**【不常用】**

#### 格式

```css
选择器 {
    属性名1: 属性值1;
    属性名2: 属性值2;
    ...
}
```

##### 选择器

用于筛选作用的目标元素

> 注意：每一对属性，需要使用分号隔开，最后的分号可以省略

###### 分类

1. 基本选择器

   1. id选择器

   2. 元素选择器

   3. 类选择器

   ```css
/* id选择器 优先级最高 */
   #myID {
	color : red;
   }
/* 类选择器 优先级其次 */
   .myClass {
       text-align:center;
   }
   /* 元素选择器 优先级最低*/
   div {
       font-size : 12px;
   }
   /* 限制越严格的选择器，优先级越高 */
   ```
   
2. 扩展选择器

   1. `*`选择器：选择所有的元素
   2. 并集选择器：可以同时设置多个元素，使用逗号隔开
   3. 子选择器：筛选父元素中的下级标签
      - 父元素后使用空格隔开后跟子元素名，可以匹配父元素下的所有子元素（包括孙子等元素）
      - 父元素后使用大于号连接子元素，只能匹配父元素下的子标签
   4. 属性选择器：筛选元素中实现指定属性的元素
   5. 伪类选择器：选择一些元素具有的状态
      - link：初始化状态
      - visited：被访问过的状态
      - active：正在访问的状态
      - hover：鼠标悬浮的状态
   - focus：选中的状态
      - first-child：符合条件的第一个元素
   
   ```css
   /* 并集选择器 */
   div， p{
       ...
   }
   /* 子选择器，可以匹配父元素下所有的子或孙子标签（不用邻级） */
   div p{
       ...
   }
   /* 子选择器，只能匹配父节点下的子标签（必须邻级） */
   div > p{
       ...
   }
   /* 属性选择器 property属性名 value属性值 */
   div[property = "value"] {
       ...
   }
   /* 伪类选择器 元素:状态 */
   a:link {
       ...
   }
   ```

##### 属性

通过一对属性名和属性值，指定元素不同的样式。

###### 分类

1. 文字

   - color
   - font-size
   - text-align
   - line-height

2. 边框

   - border 复合属性。可以分别设置不同的方向的属性（四个方向）

3. 背景

   - background 复合属性

4. 尺寸

   - width
   - height

5. 盒子模型【控制布局】

   - margin 外边距

   - padding 内边距

     默认情况下，内边距的修改会修改大小（或者说实际的大小其实上没有变，但是多出了留空的内边框而以）

   - box-sizing 设置块属性【可以通过此处的设置border-box，从而在设置内边距的时候块大小不会发生变化】

   - float 浮动，能够改变div自动换行的特性，尽量往同一行内显示

6. 排布

   - vertical-align 水平布局，需要在表格中使用
   
7. 透明

   - opacity 指定元素的不透明度，会影响其中的下级元素

### JavaScript

JavaScript是一种解释型的编程语言，符合ECMAScript[^1]标准。 JavaScript是高层次的，往往只是在实时编译，多范型。它具有卷曲支架的语法，动态类型，基于原型的面向对象的，和第一级的功能。在这个路径中，您将学习JavaScript的基本知识，以及更高级的主题如许诺，异步编程，代理和反射。

主要用于客户端的脚本语言[^2]，运行在浏览器中，借助浏览器进行解析运行。

增强用户与HTML页面的交互过程，控制HTML元素，让页面有一些动态的效果，增强用户的体验。

#### 使用的方式

1. 内部定义

   使用`<script>`标签，在标签体中输入JavaScript的代码。

2. 外部引入

   使用`<script>`标签，通过src属性引入外部的js文件

> 注意：
>
> 1. `<script>`标签可以定义在HTML的任意位置，但是定义的位置会影响加载的顺序。
> 2. `<script>`标签可以定义多个

#### 注释

与Java相同

#### 数据类型

- 原始数据类型

  ECMAScript中定义的五种原始数据类型

  - `number` 数字，其中包括整数、小数和NaN[^3]（not a number，不是数字的数字）
  - `string` 字符串
  - `boolean` 布尔型，true和false
  - `null` 空
  - `undefined` 未定义，变量没有初始化时，默认为`undefined`

- 引用数据类型

  与Java中的引用数据类型类似，是对象。

> Java是强类型语言，而JavaScript是弱类型语言。
>
> - 强类型：在申请空间的时候就会指定存放的数据类型，并且之后不能存放非指定的数据
> - 弱类型：单纯的申请空间， 但是其中所能存放的数据类型并不确定

> 类型的转换
>
> - 转为number
>
>   - string
>
>     纯数字转number会直接转为字面值；否则，转换为NaN
>
>   - boolean
>
>     true为1，false为0
>
> - 转为string
>
>   与Java中相同
>
> - 转为boolean
>
>   - string
>
>     除了空字符串（“”，长度为0的字符串），其他都是true
>
>   - null 和 undefined
>
>     对应false
>
>   - 对象
>
>     所有对象都是true
>
>     【利用这个特性，可以直接将对象用于判断，防止空指针异常】

#### 运算符

- 一元运算符

  `++`  `--`  `+（正号）`  `-（负号）`

  `++` `--` 与Java中相同

  `+` `-` 用于表示正负数

- 算数运算符

  `+`  `-`  `*`  `/`  `%`  ...

- 赋值运算符

  `=`  `+=`  `-=`  ...

- 比较运算符

  `>`  `<`  `>=`  `<=`  `==`  `===（全等于）`

  默认在比较时，如果类型不同，会自动转换

  `===` 在比较前，先比较类型，类型不同，也是false

- 逻辑运算符

  `&&`  `||`  `!`

- 三元运算符

  `? : `

#### 变量

```javascript
// 使用var定义
var a = "test1";

// 不使用var定义
b = "test2";
```

1. 使用var定义变量

   此时定义的是一个**局部**变量

2. 不使用var定义变量【不建议】

   此时定义的是一个**全局**变量

#### 对象

对象的定义与Java相同。

分为ECMAScript对象、BOM对象和DOM对象。

##### ECMAScript

- Function

  函数**对象**，类似Java中的方法。

  - 创建

    ```javascript
    /* 创建方式1【不常见】
    params 参数 传入的参数列表
    functionBody 方法体 方法具体执行的方法
    */
    var fun1 = new Function (...params, functionBody);
    /* 创建方式2
    与Java中方法的定义类似
    fun1 指定的方法对象名
    params 传入的参数列表
    functionBody 方法体
    */
    function fun2(params) {
        functionBody;
    }
    /* 创建方式3
    fun3 指定的方法对象名
    */
    var fun3 = function(params){
        functionBody;
    }
    ```

  - 属性

    - `length` 显示参数数量
  - `arguments` 内置的数组，用于接收传入方法的所有参数【因为`arguments`的存在，方法中甚至不需要定义形参，可以借助`arguments`调用传入的参数，也不需要进行方法的重载】
  
  - 特点

    1. 方法定义时，形参的类型不需要写，返回值类型同理
  2. 因为JavaScript中方法也是一种对象，因此可以直接对方法进行覆盖
    3. 当<u>方法传入参数的数量不一致依然不会报错</u>，少于方法参数的数量时，缺少值的参数就会被定义为`undefined`；多于方法参数的数量时，超出的值不会被形参接收，但是方法中有定义的属性`arguments`，会封装所有传入的参数【因为此特性的存在，JS中不存在方法的重载】
  
  - 调用

    与Java中方法的调用相同相同，可以传入参数，可以接收返回值

    - 方法名(参数列表)

- Array

  数组对象

  - 创建

    ```JavaScript
    /* 创建方法1
    array[] 传入的参数列表
    如果只有一个参数，且传入的值为正整数时，优先使用方法2，将其中识别为长度
    */
    var arr1 = new Array(array[]);
    /* 创建方法2
    length 默认的数组长度
    如果创建之后没有传值，之后的直接显示不会报错，显示的效果就是length-1个逗号（undefined显示的效果为空字符串）
    */
    var arr2 = new Array(length);
    /* 创建方式3
    */
    var arr3 = [val1, val2, val3...];
    ```

  - 方法

    - `join()` `param 分隔符`【默认使用<u>逗号</u>作为分隔符】

      把数组的所有元素放入一个**字符串**。元素通过指定的分隔符进行分隔

    - `pop()` `return 元素值`

      删除并返回数组的最后一个元素

    - `push()` `params 插入元素` `return 插入之后的长度`

      向数组的末尾添加一个或更多的元素，并返回新的长度

    - `reverse()`

      颠倒数组中元素的顺序

  - 属性

    `length` 数组的长度

  - 特点

    1. 元素的类型时可变的（类似Java中的Object数组，无法控制其中的具体类型）
    2. 数组的长度是可变的，直接方法超出默认长度的值，就会自动扩容，值为`undefined`。
       在此之上，如果默认长度为2，直接为下标9的位置赋值，此时数组的长度就会发生改变，变成10；但是，如果只是访问了下标超过1的值，此时<u>数组的长度不会发生改变</u>。

- Date

  日期对象，包括很多的日期处理时间方法

  - 创建

    ```javascript
    /* 创建方法
    没有传入参数的情况，以当前系统时间作为对象
    milliseconds 以从1970年1月1日至今的毫秒数（时间戳）
    dataString 使用字符串的形式传递的时间信息
    year,month,day,hours,minutes,seconds,milliseconds 分别设置时间的年月日时分秒毫秒，其中每个数据必须是正整数
    */
    var date = new Date();
    ```

  - 方法

    ​		**设置时间**

    - `setFullYear()` `params year, month[可选], day[可选]` `return milliseconds`

      设置具体时间，可以设置年月日信息，其中year必填。

    - `setUTCFullYear()`  `params year, month[可选], day[可选]` `return milliseconds`

      设置具体时间，根据世界时从Date返回一个时间戳

      ------

      **获取时间**

    - `toLocaleString()` `return string`

      2020/6/7 下午11:14:15

    - `toLocaleDateString()` `return string`

      2020/6/7

    - `toLocaleTimeString()` `return string`

      下午11:14:15

    - `getTime()` `return milliseconds`

      返回时间戳

  - 属性

  - 特点

  - 调用

- Math

  数学的方法类

  - 创建

    该对象是一个工具类，不需要创建，可以直接使用其中的方法。

  - 方法

    - `random()` `return 浮点数`

      返回0~1之间的随机数【左闭右开】

    - `ceil()` `param 浮点数` `return 整数`

      对数字进行上取整。

    - `floor()` `param 浮点数` `return 整数`

      对数字进行下取整

    - `round()` `param 浮点数` `return 整数`

      对数字进行四舍五入

  - 属性

    `PI` 返回圆周率

- RegExp

  正则表达式

  - 创建

    ```javascript
    /* 创建方法1
    pattern 一个字符串，用于指明正则表达式的模式或其他的正则表达式
    attributs 【可选】包含属性 "g"、"i" 和 "m"，分别用于指定全局匹配、区分大小写的匹配和多行匹配。ECMAScript 标准化之前，不支持 m 属性。如果 pattern 是正则表达式，而不是字符串，则必须省略该参数。
    */
    var reg1 = new RegExp(pattern, attributes);
    /* 创建方式2
    regax 表示具体的正则表达式【不能用引号】
    */
    var reg2 = /regax/;
    ```

  - 格式

    1. 单个字符

       1. 方括号

          用于查询某个范围类的字符

          - `[abc]` 查询匹配括号内的任意一个字符
          - `[^abc]`  查询任何不属于方括号内的字符
          - `[a-z]` `[0-9]` 指定一个范围内的任意字符匹配

       2. 元字符

          元字符，Metacharacter，是拥有特殊含义的字符

          - `.` 查询单个字符【除换行和行结束符】
          - `\w` 查询单词字符，等价于`[a-z0-9A-Z_]`
          - `\d` 查询数字

    2. 量词

       用于重复判断多次单个字符的情况

       - `?` 表示0次或1次

       - `*` 表示0次或以上

       - `+` 表示至少有1次

       - `{n}` 表示重复n次

       -  `{m, n}` `{m, }` `{, n}` 表示一定范围之内的循环【如果缺少n（后项）就是至少m次；如果缺少m（前项）就是最多n次】【闭区间，左右值都可以取到】

       - ------

         `$` 匹配结尾

       - `^` 匹配开始

    3. 修饰词

       - `i` 执行对大小写不敏感的匹配
       - `g` 执行全局匹配
       - `m` 执行多行匹配

  - 方法

    - `test()` `param 字符串` `return 布尔值`

      检验所传入的字符串，并返回true或false

- Global

  顶层函数（全局函数）不需要对象可以直接调用方法。

  - 方法

    - `encodeURI()` `param 字符串` `return 字符串`

      对字符串进行URI编码，返回编码后的字符串

    - `decodeURI()` `param 字符串` `return 字符串`

      对`encodeURI()` 方法进行过URI编码的字符串进行解码

    - `encodeURIComponent()`  `param 字符串` `return 字符串`

      对字符串进行URI编码，返回编码后的字符串

    - `encodeURIComponent()` `param 字符串` `return 字符串`

      对`encodeURI()` 方法进行过URI编码的字符串进行解码

      > 两组URI编码和解码区别
      >
      > 第一组编码时，只会对其中的非单词字符进行编码
      >
      > 第二组编码时，会对除英文、数字和点号之外的所有字符进行编码

    - `parseInt()` `param 字符串` `return 数字`

      将字符串转换为数字，可以逐个字符进行转换，直到遇到第一个非数字字符时停止，将之前的数字字符转换成数字进行返回；如果字符串的最开始就不是数字，就会被转换成`NaN` 

    - `isNaN()` `param 某个值` `return 布尔值`

      判断是否为`NaN`类型。【`NaN`不能用常规方式进行比较，直接进行比较，永远为`false`】

    - `eval()` `param 字符串`

      解析字符串，作为脚本进行执行

      ```javascript
      var jscode = "alert('test')";
      // 将字符串当作代码脚本进行执行
      eval(jscode);
      ```

- String、Number、Boolean

  原始数据的封装类，基本上与Java相同，且其中没有特别的方法，不多赘述

##### BOM

BOM，Browser Object Model，浏览器对象模型

- `Navigator` 浏览器【略】

- `Window` 窗口

  类似工具类，不需要创建，可以直接调用方法，甚至可以直接使用其中的方法，不需要指明`Window`**【全局对象】**

  - 属性

    主要包含其他的BOM对象，

    - `history` 

    - `location`

    - `Screen` 【只读】

    - `Navigator` 【只读】

    - ------

      `document` DOM对象

  - 方法

    - `alert()` `param 字符串`

      弹出一个<u>警告框</u>，其中显示输入的文字

    - `confirm()`  `param 字符串` `return 布尔值`

      弹出一个<u>确认框</u>，其中显示所输入的文字，返回true或false

    - `prompt()` `param 字符串` `return 字符串`

      弹出一个<u>输入框</u>，其中输入的字符串将作为提示信息在输入框上显示，返回用户输入的字符串。

    - ------

      `open()` `param URL, name[可选], features[可选], replace[可选]` `return 新建的窗口对象`

      打开一个新的浏览器窗口或者查找一个已经命名的窗口。

      `URL`用于指定打开的网页资源；`name`用指定新窗口的特征列表，主要用于指定打开的位置`_blank`为新窗口，默认为新标签页面；`features`用一连串的逗号分割，表示新窗口的特点的属性，如高度、宽度等；`replace`布尔值可以设定是否要用目标URL替换浏览记录中的当前条目

    - `close()` 

      关闭浏览器对象的窗口，因此**需要使用对应的对象来调用方法**

    - ------

      `setTimeout()` `param 方法代码, 毫秒数` 

      指定毫秒数之后，执行方法代码【理论上执行一次，但是可以利用迭代的方式，反复调用方法，实现一直执行】

    - `clearTimeout()` `param Timeout的ID值`

      关闭指定ID对应的`setTimeout()`方法中生成的定时器

    - `setInterval()` `param 方法代码, 毫秒数` 

      指定毫秒数之后，执行方法代码【循环执行，直到使用`clearInterval()`方法关闭】

    - `clearInterval()` `param Interval的ID值`

      关闭指定ID对应的`setInterval()`方法中生成的定时器

- `Location` 地址栏

  Location包含当前URL信息

  - 创建

    ```javascript
    // 借助window来获取
    var loc1 = window.location;
    // window可以省略
    var loc2 = location;
    ```

  - 属性

    - `href` 设置或者返回完整的URL【可以通过修改当前界面的URL来实现网页跳转的功能】

  - 方法

    - `reload()`

      重新加载对象对应的文档，刷新界面。

- `History` 历史记录

  包含当前用户通过<u>当前窗口</u>访问过的URL。【不等于浏览器的历史记录】

  - 创建

    ```javascript
    // 借助window来获取
    var h1 = window.history;
    var h2 = history;
    ```

  - 属性

    `length` 返回当前窗口的历史记录的数量

  - 方法

    - `back()` 

      加载`history`列表中的前一个URL

    - `forward()` 

      加载`history`列表的下一个URL

    - `go()` `param 编号|URL`

      加载历史列表中的某个具体的页面，可以通过对应页面的编号，或者直接使用输入URL。

- `Screen`  显示器【略】

> 理论上BOM中还包括DOM，即Document对象，但是因为DOM非常重要，因此被分离出来，单独成为了一块

##### DOM

DOM，Document Object Model，文档对象模型。将标记语言文档的各个组成部分，封装成对象，可以使用这些对象，对标记语言文档进行CRUD的动态操作。

> W3C DOM标准被分为了3个不同的部分：
>
> 1. 核心 DOM：针对任何结构化文档的标准模型
>
>    - Document 文档对象
>
>    - Element 元素对象
>
>    - Attribute 属性对象
>
>    - Text 文本对象
>
>    - Comment 注释对象
>
>    - ------
>
>      Node 节点对象【父对象，其他都继承自Node】
>
> 2. XML DOM：针对XML文档的标准模型
>
> 3. HTML DOM：针对HTML文档的标准模型

###### 核心 DOM

- Document 文档对象

  - 创建

    ```JavaScript
    // 借助window进行创建
    var d1 = window.document;
    var d2 = document;
    ```

  - 属性

    - `URL` 返回当前文档的URL
    - `cookie` 设置或返回当前文档有关的cookie

  - 方法

    - `getElementById()` `param 元素ID` `return 元素对象`

      通过id获取元素对象

    - `getElementsByName()` `param name的值`  `return 元素对象数组`

      通过name属性获取元素对象数组

    - `getElementsByTagName()` `param 标签名称` `return 元素对象数组`

      通过标签名称获取元素对象数组

    - `getElementsByClassName()` `param class的值` `return 元素对象数组`

      通过class属性获取元素对象数组

    - ------

      `createAttrbute()` `param 属性名` `return 属性对象`

      创建新的属性节点

    - `createComment()` `param 字符串` `return 注释对象`

      创建注释对象。其中的内容是输入的字符串

    - `createElement()` `param 元素名` `return 元素对象`

      创建元素节点

    - `createTextNode()` `param 字符串` `return 文本对象`

      创建文本节点

    - ------

      `open()` `param 文档类型[字符串，可选], 'replace'[可选]`

      打开一个新文档，并擦除之前文档中的内容。其中文档类型如“text/html”，`raplace`设置之后，可以引起新文档可以继承父文档中的历史条目。

    - `close()` 

      用于关闭由`open()`方法打开的输出流。

    - `write()/writeln()` `param 字符串`

      向文档中写入HTML表达式或者JavaScript代码。`writeln()`方法会在表达式之后添加一个换行符。

- Element 元素对象

  - 创建

    ```JavaScript
    // 创建方法1
    var e1 = document.getElementById("myId");
    // 创建方法2
    var e2[] = document.getElementsByName("myName");
    // 创建方法3
    var e3[] = document.getElementsByTagName("input");
    // 创建方法4
    var e4 = document.createElement("input");
    ```

  - 方法

    - `setAttribute()` `param 属性名, 属性值`

      创建或者改变某个属性

    - `removeAttribute()` `param 属性名`

      删除某个指定的属性

- Node 节点对象

  其他对象都继承自Node，其他所有的对象都可以被认为是一个节点

  - 方法

    - `appendChild()` `param 节点对象`

      向节点的子节点列表结尾添加一个子节点

    - `removeChild()` `param 节点对象`

      删除当前节点的指定子节点

    - `replaceChild()` `param 新节点对象, 旧节点对象`

      用新节点代替一个子节点

  - 属性

    - `parentNode` 返回节点的父节点

###### HTML DOM

- `innerHTML` 获取或设置标签体属性，其中可以接收JavaScript代码

  【Element中的属性】

- `innerWidth` 获取**浏览器界面宽度**【Window中的属性】

- `innerHeight` 获取**浏览器界面高度**【Window中的属性】

- 修改元素样式

  可以修改像CSS中的样式

  ```JavaScript
  var div = document.getElementById("myDiv");
  /* 方式1
  修改元素样式，使用元素中的style属性
  */
  div.style.border = "1px solid red";
  // 特殊，有些属性是多个单词使用‘-’连接，如font-size会变成fontSize，拼成一个单词
  div.style.fontSize = "20px";
  /* 方式2
  修改元素样式，适用于已经有现成的多个CSS样式的情况
  修改元素的class属性值，让其指向其他CSS对应的class
  */
  // 假设原本的class值为div1，需要修改成div2
  div.className = "div2";
  ```


#### 事件

组件执行了某些操作之后，触发某些代码的执行。

> **事件**：就是指明某些的操作，如，单击，双击，键盘按下和鼠标移动等。
>
> **事件源**：被操作的组件，如，按钮，文本输入框等。
>
> **监听器**：具体要执行的<u>代码</u>。
>
> **注册监听**：将事件、事件源和监听器三者组合在一起。当事件源发生了某些事件，就会触发监听器代码。

##### 常见的事件

- `onclick` 单击事件

- `ondblclick` 双击事件

- ------

  `onfocus` 获得焦点

- `onblur` 失去焦点

  - 一般用于表单校验

- ------

  `onload` 加载事件

  - 可以用于避免页面没有加载之前获取元素

- ------

  `onmouseover` 鼠标移动在元素上

- `onmouseout` 鼠标从元素上移动开

- `onmousedown` 鼠标按下

- `onmouseup` 鼠标按键松开

- `onmousemove` 鼠标移动

- ------

  `onkeydown` 键盘按下

- `onkeyup` 键盘松开

- `onkeypress` 键盘被按下并松开

- ------

  `onchange` 域内容被改变

  - 一般用于下拉列表的改变，带动下级的联动

- `onselect` 文本被选中

- ------

  `onsubmit` 表单提交按钮被点击

  - 可以阻止表单的提交，其中的方法存在返回值的布尔值true【默认，提交】或false【阻止提交】

  ```html
  <!-- 方式1 -->
  <form action="#" id="myform">
      ...
      <input type="submit" value="提交">
  </form>
  <script>
  	document.getElementById("myform").onsubmit = function(){
  		// 阻止表单的提交【正确】
          return false;
      }
  </script>
  
  <!-- 方式2 -->
  <!-- 单纯的调用方法并不能成功的阻止表单的提交，如果希望用这种方式来实现相同的效果，需要在onsubmit中使用 return f(); -->
  <form action="#" id="myform" onsubmit="f()">
      ...
      <input type="submit" value="提交">
  </form>
  <script>
  	function f(){
  		// 阻止表单的提交【失败】
          return false;
      }
  </script>
  ```

- `onreset` 表单重置按钮被点击

##### 事件中的属性

- `altKey` 返回事件触发时，`ALT`是否被按下

- `ctrlKey` 返回事件触发时，`CTRL`是否被按下

- `metaKey` 返回事件触发时，`META`是否被按下【系统键，window系统就是windows键】

- `shiftKey` 返回事件触发时，`SHIFT`是否被按下

- `button` 返回事件触发时，哪个鼠标按钮被点击

- `keyCode` 返回事件触发时，哪个键盘按键被点击

  - **注意**： 其中keyCode=13是回车键，可以用于捕获回车按键直接提交

- ------

  `clientX` 返回事件触发时，鼠标指针的水平坐标

- `clientY` 返回事件触发时，鼠标指针的垂直坐标

- `screenX` 返回事件发生时，鼠标指针相对于屏幕的水平坐标

- `screenY` 返回事件发生时，鼠标指针相对于屏幕的垂直坐标

- ------

  `relatedTarget` 返回与事件的目标节点相关的节点

##### 事件的创建方法

```html
<!-- 方式一
直接将代码放在便签中
如此处的onclick就是一种方式
缺点：耦合度较高，不易维护
-->
<img src='test.jpg' onclick="alert('test');">
<!-- 方式二
在时间属性中绑定JS中的函数
-->
<img id="bulb" src='test.jpg'>
<!-- 需要先加载元素，在进行操作，因此放在下方 -->
<script>
    var bulb = document.getElementById('bulb');
    bulb.onclick = function(){
        alert("test1");
        alert('test2');
    }
    // 需要调用属性的时候，可以使用一个参数来接收JS自动传入的属性
    // 就算不接受也会传入
    bulb.onmousedown = function(event){
        // 显示鼠标点击时，使用的是哪个键
        alert(event.button);
    }
</script>
```

#### AJAX

AJAX，Asynchronous JavaScript And XML，异步的JavaScript和XML。可以在无需重新加载页面的情况下，对网页中的部分进行更新

> ###### 同步和异步
>
> 此处是服务器和客户端的之间通信技术，与线程之间的同步和异步略有区别
>
> - 同步
>
>   客户端向服务器端发送请求，在获取到服务器端响应之前，不能再进行其他操作
>
> - 异步
>
>   客户端向服务器端发送请求，在获取到服务器端响应之前，不需要进行等待，可以进行其他的操作

##### 实现方式

1. JavaScript实现方式【了解】

   ```JavaScript
   // 创建Ajax核心的对象
   var xmlhttp;
   if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
     xmlhttp=new XMLHttpRequest();
   }
   else { // code for IE6, IE5
     xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
   }
   
   // 发送请求
   /* 参数列表
   第一个参数：指定请求的方式，如“GET”、“POST”等
   	“GET”方式：传递的参数在URL后
   第二个参数：指定请求的URL
   第三个参数：指定是否同步，true为异步，false为同步
   */
   xmlhttp.open("GET", "/test?name=test1", true);
   
   // 发送请求
   // 如果使用的不是GET方式请求，需要在此处指明传递的参数
   // xmlhttp.send("name=test1");
   xmlhttp.send();
   
   // 接收并处理服务器的响应数据
   // 获取方式：xmlhttp.responseText，通过此方法就可以获取到响应体中的内容
   // 什么时候获取？需要在服务器响应成功之后获取
   // 当xmlhttp对象的就绪状态改变时，触发事件onreadstatechange
   xmlhttp.onreadystatechange=function(){
       //判断就绪状态是否为4，并判断响应状态码是否为200
       /* 就绪状态
       0: 请求未初始化 
   	1: 服务器连接已建立 	
   	2: 请求已接收 
   	3: 请求处理中 
   	4: 请求已完成，且响应已就绪 
       */
     if (xmlhttp.readyState==4 && xmlhttp.status==200){
         // 获取服务器的响应结果
       document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
     }
   }
   
   ```

2. JQuery实现方式

   - `$.ajax()`或`JQuery.ajax()`

     标准的`ajax`请求方法

     ```javascript
     $.ajax({
         url: "testAjax", // 请求的路径
         type: "POST", // 请求的方式
         // 请求的参数
         // 方式1 data: "username=test1&password=123", 
         // 方式2 使用JSON的方式 【推荐】
         data: {
             "username":"test1",
             "passowrd":"123"
         },
         dataType: "json", // 指定预期的服务器返回数据类型
         success: function(data){ // 请求执行成功之后的回调函数
             // 一般会指定方法的参数
             alert(data);
         },
         error: function(data){ // 请求出错的回调函数
             
         }
     });
     ```

   - `$.get()` 和 `$.post()`

     发送`get/post`请求

     `param url, [data], [callback], [type]`

     - `url` 请求路径
     - `data` 请求参数
     - `callback` 回调函数
     - `type` 预期响应类型

#### JSON

JSON，JavaScript Object Notation，JavaScript对象表示法

现在多用于存储和交换数据的语法，可以用于多种语言之间进行数据的交互

##### 格式

- 基本规则
  - 数据按照键值对的方式进行存储
    - 键：可以用引号也可以省略
    - 值：每个键对应的值
      1. 数组（整数和浮点数）
      2. 字符串（需要使用引号）
      3. 逻辑值（true 或 false）
      4. 数组（使用方括号）
      5. 对象（使用花括号）
      6. null
  - 数据由逗号进行分隔
  - 花括号用于保存对象
  - 方括号用于保存数组

```json
{
    "name":"test", 
    "age":20,
    "gender":"male",
    "child":[
    	"child1",
        "child2"
    ],
    "parents":[
        {
            "name": "p1"
        }, 
        {
            "name": "p2"
        }
    ]
}
```

> 推荐使用花括号作为最外层，虽然基本的规则中并未对其进行限制，但是为了显示其为一个JSON对象，最好使用花括号

##### 获取数据

- `Json对象.键名`
- `Json对象["键名"]`
- `Json数组对象[索引]`

##### 转换

为了满足一定的业务需求，通常需要实现Java对象与JSON对象之间的转换

主要借助的是JSON的解析器，如`Jsonlib`、`Gson`、`fastJson`、`jackson`

###### JSON对象转Java对象

1. 导入`Jackson`相关的`jar`

2. 创建`Jackson`核心对象`ObjectMapper`

3. 调用`ObjectMapper`的相关方法进行转换

   - `readValue()` `param Json字符串, 类.class`

     通过传入所需转换的`Json`字符串和转换的<u>类对象</u>，会根据`Json`中的键名来对应设置属性值

###### Java对象转JSON对象

1. 导入`Jackson`相关的`jar`

2. 创建`Jackson`核心对象`ObjectMapper`

3. 调用`ObjectMapper`的相关方法进行转换

   - `writeValueAsString()` `param 对象`

     将Java对象转换为Json对象，作为字符串输出

   - `writeValue()` `param 各种输出方式, 对象`

     通过指定输出方式以及需要转换的对象，将对象转换为`Json`对象

     可以指定的输出方式，`File`将转换结果输出到字符串；`Writer`将转换结果使用字符流输出；`OutputStream`将转换结果使用字节流输出

###### 复杂的Java对象转换

主要指的是`List`集合与`Map`集合的转换，使用的都是相同的操作方法

- `List`集合

  返回的是一个Json集合，最外层是方括号，包含了多个对象

- `Map`集合

  返回的效果类似引用类对象的转换，都是`Key`和`Value`一一对应

##### 注解

- `@JsonIgnore` 

  排除属性，被排除后的属性在转换的过程中就会跳过

- `@JsonFormat` 

  属性值的格式化，指定属性的格式

### XML

XML，Extensible Markup Language，可扩展标记语言。

> 可扩展：标签可以进行自定义

> XML于HTML关系
>
> - 相同【略】
>
>   都来自W3C，万维网联盟
>
> - 区别
>
>   1. XML标签是自定义的，HTML的标签是预定义的
>   2. XML语法严格，HTML语法松散【由于浏览器解析能力加强】a
>   3. XML是存储数据的，HTML是展示数据的

#### 基本语法

1. XML文档的后缀名为 `.xml`
2. XML文档的第一行必须定义为文档声明
3. XML文档有且仅有一个根标签
4. 属性值必须使用引号包含，标签必须有结束标签【也可以定义单标签】

```xml
<?xml version='1.0' ?>
<users>
	<user id='1'>
        <name>zhangsan</name>
        <age>13</age>
        <gender>male</gender>
        <br/>
    </user>
    <user id='2'>
    	<name>lisi</name>
        <age>39</age>
        <gender>female</gender>
    </user>
</users>
```

#### 组成部分

##### 文档声明

`<?xml 属性列表 ?>`其中的`<?xml`之间都不能有空格。

##### 属性列表

- `version` 版本号用于指明XML版本号**【必须】**
- `encoding` 编码方式，默认`ISO-8859=1`
- `standalone` 是否独立，是否会约束其他文件，取值`yes`或`no`**【一般省略】**

##### 元素

可以自行定义的元素（或标签）

###### 命名规则

- 名称可以含字母、数字以及其他的字符
- 名称不能以数字或者标点符号开始
- 名称不能以字符 “xml”（或者 XML、Xml）开始
- 名称不能包含空格

##### 名称空间

用于防止标签重复

具体的使用效果类似前缀的方式，如

```xml
<!-- 在book标签中，出现了多个name标签，而他们的作用又各不相同，虽然可以通过上级标签的方式来进行识别，但是以后类似的标签都需要进行此操作，让我们的操作更加复杂 -->
<book>
	<name>西游记</name>
    <price>20.0</price>
    <author>
    	<name>吴承恩</name>
        <gender>男</gender>
    </author>
</book>

<!-- 使用命名空间的方式 -->
<book>
	<b:name>西游记</b:name>
    <price>20.0</price>
    <author>
    	<a:name>吴承恩</a:name>
        <gender>男</gender>
    </author>
</book>
```



##### CDATA区

可以将数据直接展示，不需要使用替换符来替代如大于号、小于号、AND符号等。

- **格式** `<![CDATA[ 具体的数据 ]]>`

#### 约束

规定XML文档的书写规则

##### 分类

1. DTD：简单的约束技术
2. Schema：复杂的约束技术

##### DTD

```DTD
<!-- 根元素students，其中定义子元素student，可以传入0~n个 -->
<!ELEMENT students (student*) >
<!-- 元素student，其中定义子元素name、age、sex。
每个属性都只能出现一次，且必须按照顺序出现 -->
<!ELEMENT student (name,age,sex) >
<!-- 元素name，其中接收字符串，下同 -->
<!ELEMENT name (#PCDATA) >
<!ELEMENT age (#PCDATA) >
<!ELEMENT sex (#PCDATA) >
<!-- student元素的属性，叫number，是ID（唯一），且必须输入 -->
<!ATTLIST student number ID #REQUIRED >
```

###### 引入方式

1. 内部dtd：将约束规则定义在xml文档中

   `<!DOCTYPE 根标签名 [ dtd的具体内容 ]>`

2. 外部dtd：将约束的规则定义在外部的dtd中，引入到xml中

   - 本地：`<!DOCTYPE 根标签名 SYSTEM “dtd文档的位置”>`
   - 网络：`<!DOCTYPE 根标签名 PUBLIC "dtd文件名" “dtd文档的URL路径”>`

###### 缺陷

虽然可以指定标签中的数据类型，但是却不能具体的对其中的值进行限制

##### Schema

```html
<?xml version="1.0"?>
<xsd:schema xmlns="http://www.itcast.cn/xml"
        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
        targetNamespace="http://www.itcast.cn/xml" elementFormDefault="qualified">
    <!-- 定义一个元素students，类型为自定义类型studentsType -->
    <xsd:element name="students" type="studentsType"/>
    <!-- 定义一个复合的自定义类型studentsType -->
    <xsd:complexType name="studentsType">
        <!-- 内容按顺序出现 -->
        <xsd:sequence>
            <!-- 定义元素student，类型为自定义类型studentType，取值最小为0，没有最大值 -->
            <xsd:element name="student" type="studentType" minOccurs="0" maxOccurs="unbounded"/>
        </xsd:sequence>
    </xsd:complexType>
    <xsd:complexType name="studentType">
        <xsd:sequence>
            <!-- 指定name元素的类型为字符串 -->
            <xsd:element name="name" type="xsd:string"/>
            <!-- 指定age元素的类型为自定义类型ageType -->
            <xsd:element name="age" type="ageType" />
            <xsd:element name="sex" type="sexType" />
        </xsd:sequence>
        <!-- 设置student的参数number，类型为自定义类型numberType，值不能为空 ，设置为ID-->
        <xsd:attribute name="number" type="numberType" id="ID" use="required"/>
    </xsd:complexType>
    <!-- 定义一个简单的自定义类型sexType -->
    <xsd:simpleType name="sexType">
        <!-- 指明基本的数据格式为字符串 -->
        <xsd:restriction base="xsd:string">
            <!-- 设置为枚举，只能从“male”和“female”中取值 -->
            <xsd:enumeration value="male"/>
            <xsd:enumeration value="female"/>
        </xsd:restriction>
    </xsd:simpleType>
    <xsd:simpleType name="ageType">
        <xsd:restriction base="xsd:integer">
            <!-- 设置取值范围0~256 -->
            <xsd:minInclusive value="0"/>
            <xsd:maxInclusive value="256"/>
        </xsd:restriction>
    </xsd:simpleType>
    <xsd:simpleType name="numberType">
        <xsd:restriction base="xsd:string">
            <!-- 使用正则表达式，指明了其中数据的格式必须是“s_”开头，后面跟4位数字 -->
            <xsd:pattern value="s_\d{4}"/>
        </xsd:restriction>
    </xsd:simpleType>
</xsd:schema> 
```

###### 引入方式

1. 填写约束中的根元素
2. 引入xsi前缀，`xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"`
3. 引入xsd文件命名空间， `xsi:schemaLocation="http://www.itcast.cn/xml  student.xsd"` 其中`http://www.itcast.cn/xml`相当于给文件命名，`student.xsd`是真正的文件路径
4. 为每一个xsd约束声明一个前缀,作为标识  `xmlns="http://www.itcast.cn/xml"` ，如果没有这个过程，就需要在之后的所有元素之前，添加刚才为文件所设定的名称。`xmlns`表示默认，还可以使用`xmlns:前缀名`的方式，用于处理引入多个约束的情况，标签前需要添加上此处的前缀名。

#### 操作XML数据

- 写入：将内存中的数据保存到XML文档中，持久化存储

- 解析：将文档中的数据读取到内存中

  - DOM

    将标志语言文档一次性加载进内存，在内存中形成一颗DOM树

    - 优点

      操作方便，可以对文档进行CRUD的所有操作

    - 缺点

      会占用大量内存

  - SAX

    逐行读取，基于事件驱动地完成数据的获取

    - 优点

      占用内存小

    - 缺点

      只能实现读取操作，不能增删改

#### XML常见地解析器

- JAXP【不推荐】

  sun公司提供的解析器，支持DOM和SAX两种方式

- dom4j

- jsoup

  一款Java的HTML解析器，但是同样可以用于解析XML

- PULL

  Android操作系统内置的解析器，SAX的方式

##### Jsoup

jsoup 是一款Java 的HTML解析器，可直接解析某个URL地址、HTML文本内容。它提供了一套非常省力的API，可通过DOM，CSS以及类似于jQuery的操作方法来取出和操作数据。

###### 步骤

1. 导入相关jar包
2. 获取Document对象
3. 获取对应的Element对象
4. 获取数据

```java
// 获取配置文件的地址
String path = this.getClass.getClassLoader().getResource("student.xml").getPath();
// 解析xml文档，加载文档进入内存，获取DOM树
Document document = Jsoup.parse(new File(path), "utf-8");
// 获取元素对象，于js中的方法几乎完全一样
Elements elements = document.getElementsByTag("name");

// 元素的数量
elements.size();
// 获取第一个元素
Element elment = elements.get(0);
// 获取数据
String name = element.text();
```

###### 对象

- `Jsoup`

  工具类，可以用来解析html或xml文档，返回Document对象

  - `parse()` `param File, 字符编码` `return Document对象`

    解析xml或html文件，将其转换成DOM树

  - `parse()` `param html代码` `return Document对象`

    解析传入的xml或html字符串或代码的。

  - `parse()` `param URL, 毫秒数` `return Document对象`

    解析网络中指定路径下的html或xml文档。其中的毫秒数，为设置的超时时间。

- `Document`

  文档类，代表内存中的DOM树【继承自Element】

  - `getElementsByTag()` `param tagName` `return Elements`

    根据元素/标签名称获取元素**集合**

  - `getElementsByAttribute()` `param 属性名` `return Element`

    根据属性名称获取元素**集合**

  - `getElementsByAttributeValue()` `param 属性名, 属性值` `return Elements`

    根据属性名和属性值来获取对象**集合**

  - `getElementById()` `param ID值` `return Element`

    根据ID值来获取对象

- `Element`

  元素类，获取名称、属性、数据等。

  ​		获取子元素对象

  - `getElementsByTag()` `param tagName` `return Elements`

    根据元素/标签名称获取元素**集合**

  - `getElementsByAttribute()` `param 属性名` `return Element`

    根据属性名称获取元素**集合**

  - `getElementsByAttributeValue()` `param 属性名, 属性值` `return Elements`

    根据属性名和属性值来获取对象**集合**

  - `getElementById()` `param ID值` `return Element`

    根据ID值来获取对象

    ------

    获取属性【继承自Node，使用其中的方法，自身的attr用于设置属性值】

  - `attr()` `param 属性名` `return 字符串`

    根据属性名获取属性值

    ------

    获取文本内容

  - `text()` `return 字符串`

    获取标签体，包括子标签，的纯文本内容

  - `html()` `return 字符串`

    获取标签体，包括子标签，的文本内容，包括标签信息（html信息）

- `Elements`

  元素`Element`的集合，继承了`ArrayList<Element>`

- `Node`

  节点类，是`Document`和`Element`的父类

###### 快捷索引

1. select选择器

   `select()` `param cssQuery` `return Elements`

   `cssQuery`是传入的CSS选择器，具体的语法需要查看`Selector`类中规定的格式，与CSS中的选择器类似。

2. XPath语法

   XPath 使用路径表达式来选取 XML 文档中的节点或节点集。节点是通过沿着路径 (path) 或者步 (steps) 来选取的。

   > 使用Jsoup的XPath需要导入额外的jar包
   >
   > Document类并不支持XPath，需要使用JXDocument类，可以直接在构造器中传入Document对象即可

   具体语法可以查询W3C中的XPath手册， [W3school.CHM](..\..\document\W3school.CHM) 

### 前端框架

#### Bootstrap

Bootstrap，来自 Twitter，是目前最受欢迎的前端框架。Bootstrap 是基于 HTML、CSS、JavaScript的，它简洁灵活，使得 Web 开发更加快捷。

定义了很多的CSS样式和JS插件，让开发人员可以直接使用这些样式和插件，来快速实现精美的页面效果。

支持响应式布局，可以让同一套页面可以兼容不同分辨率下的设备。

##### 快速入门

1. 下载Bootstrap
2. 在项目中引入Bootstrap文件
3. 创建html页面，引入必要的资源文件【具体页面，可见 [简单模板](HelloWorld_Bootstrap.html)】

```html
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <h1>你好，世界！</h1>

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="js/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
```

##### 响应式布局

动态适配不同分辨率的设备。

Bootstrap中使用了**栅格系统**来实现，将每行平均分为<u>12个格子</u>，可以指定元素占多少个格子。

###### 具体步骤

1. 定义容器。相当于使用table。
   - 容器分类
     - `container` 每种设备有自己的固定宽度，具体值可见下表中的 `.container最大宽度`值。【除了手机端，都默认存在留白】
     - `container-fluid ` 默认100%宽度
2. 定义行。相当于tr。
   - `row`
3. 定义元素。相当于td。
   此时，需要指定该元素，在不同的设备之上，所占的不同格子数。
   - `col-设备代号-格子数目`
     - `设备代号` 
       1. `xs` 超小屏幕/手机
       2. `sm` 小屏幕/平板
       3. `md` 中等屏幕/桌面显示器
       4. `lg` 大屏幕/大桌面显示屏

![bootstrap-栅格参数](img/bootstrap-%E6%A0%85%E6%A0%BC%E5%8F%82%E6%95%B0.jpg)

> **注意**
>
> 1. 一行中占用格子数超过12，那么超出的部分会自动换行。
> 2. 栅格类属性，可以**向上兼容**【定义手机之后，其他的都默认使用手机样式】

##### CSS样式和JS插件

- 按钮

  - `btn` 【基础】将其他元素转换为按钮模板形式出现

  - ------

    `btn-default` 默认样式【白底黑字，有鼠标移动悬浮效果】

  - `btn-primary` 首选项【深蓝底白字】

  - `btn-success` 成功【绿底白字】

  - `btn-info` 一般信息【淡蓝底白字】

  - `btn-warning` 警告【橘底白字】

  - `btn-danger` 危险【红底白字】

  - `btn-link` 连接【超链接样式，蓝色，鼠标悬停之后有下划线】

  > **注意**
  >
  > 1. 虽然按钮类可以作用在`<a>`和`<button>`元素上，但是，导航和导航条只支持`<button>`元素。
  > 2. 如果 `<a>` 元素被作为按钮使用 -- 并用于在当前页面触发某些功能 -- 而不是用于链接其他页面或链接当前页面中的其他部分，那么，务必为其设置 `role="button"` 属性。
  > 3. **强烈建议尽可能使用 `<button>` 元素**来获得在各个浏览器上获得相匹配的绘制效果。

  ```html
  <!-- 基础使用 -->
  <a class="btn btn-default" href="#" role="button">Link</a>
  <button class="btn btn-default" type="submit">Button</button>
  <input class="btn btn-default" type="button" value="Input">
  <input class="btn btn-default" type="submit" value="Submit">
  <!-- 其他样式 -->
  <!-- Standard button -->
  <button type="button" class="btn btn-default">（默认样式）Default</button>
  <button type="button" class="btn btn-primary">（首选项）Primary</button>
  <button type="button" class="btn btn-success">（成功）Success</button>
  <button type="button" class="btn btn-info">（一般信息）Info</button>
  <button type="button" class="btn btn-warning">（警告）Warning</button>
  <button type="button" class="btn btn-danger">（危险）Danger</button>
  <button type="button" class="btn btn-link">（链接）Link</button>
  ```

- 图片

  在 Bootstrap 版本 3 中，通过为图片添加 `.img-responsive` 类可以让图片支持响应式布局。其实质是为图片设置了 `max-width: 100%;`、 `height: auto;` 和 `display: block;` 属性，从而让图片在其父元素中更好的缩放。

  如果需要让使用了 `.img-responsive` 类的图片水平居中，使用 `.center-block` 类，不要用 `.text-center`。

  - `img_responsive` 让图片可以动态变化大小

  - ------

    `img-rounded` 图片带圆角

  - `img-circle` 图片裁剪为圆形显示

  - `img-thumbnail` 图片外边框

  ```html
  <!-- 响应式图片 -->
  <img src="..." class="img-responsive" alt="Responsive image">
  <!-- 图片形状 -->
  <img src="..." alt="..." class="img-rounded">
  <img src="..." alt="..." class="img-circle">
  <img src="..." alt="..." class="img-thumbnail">
  ```

- 表格

  为任意 `<table>` 标签添加 `.table` 类可以为其赋予基本的样式 — 少量的内补（padding）和水平方向的分隔线。

  - `table` 【基础】赋予表格一些基础的样式

  - ------

    `table-striped` 条纹状表格，在表格体中，每隔一行显示斑马条纹

  - `table-bordered` 带边框的表格，为表格和其中的各个单元格添加边框

  - `table-condensed` 紧缩表格，让表格显得更加紧凑，单元格内边距减半

  - ------

    `table-hover` 鼠标悬停效果，让鼠标悬停在时对应一行会做出响应

  - ------

    `table-responsive` 响应式表格，其会在小屏幕设备上（小于768px）水平滚动。当屏幕大于 768px 宽度时，水平滚动条消失

  ```html
  <!-- 基础实例 -->
  <table class="table">
    ...
  </table>
  <!-- 调整样式 -->
  <table class="table table-striped">
    ...
  </table>
  <table class="table table-bordered">
    ...
  </table>
  <table class="table table-condensed">
    ...
  </table>
  <!-- 鼠标效果 -->
  <table class="table table-hover">
    ...
  </table>
  <!-- 响应表格 -->
  <div class="table-responsive">
    <table class="table">
      ...
    </table>
  </div>
  ```

- 表单

  单独的表单控件会被自动赋予一些全局样式

  - `form-control` 表单中元素，为其添加全局样式

  - `form-group` 表单中的容器，可以让其中的`<label>`元素和其他控件更好的排列

  - `form-inline` 内联表单，让内容尽可能压缩进一行中。【**只适用于视口（viewport）至少在 768px 宽度时（视口宽度再小的话就会使表单折叠）**】

  - `form-horizontal` 水平排列的表单，可以将 `label` 标签和控件组水平并排布局。

    ------

    被支持的控件

  - 输入框： 包括大部分表单控件、文本输入域控件，还支持所有 HTML5 类型的输入控件： `text`、`password`、`datetime`、`datetime-local`、`date`、`month`、`time`、`week`、`number`、`email`、`url`、`search`、`tel` 和 `color`。

  - 文本域：支持多行文本的表单控件。可根据需要改变 `rows` 属性。

  - 多/单选框：

    多选框（checkbox）用于选择列表中的一个或多个选项，而单选框（radio）用于从多个选项中只选择一个。

    - 默认：多行显示。
    - 内联：通过将 `.checkbox-inline` 或 `.radio-inline` 类应用到一系列的多选框（checkbox）或单选框（radio）控件上，可以使这些控件排列在一行。

  - 下拉列表：默认，无需另行设置【略】

    - 多选，可以通过添加`multiple`

  - 静态控件：如果需要在表单中将一行纯文本和 `label` 元素放置于同一行，为 `<p>` 元素添加 `.form-control-static` 类即可。

  ```html
  <!-- 基本实例 -->
  <form>
    <div class="form-group">
      <label for="exampleInputEmail1">Email address</label>
      <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
    </div>
    <div class="form-group">
      <label for="exampleInputPassword1">Password</label>
      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
    </div>
    <div class="form-group">
      <label for="exampleInputFile">File input</label>
      <input type="file" id="exampleInputFile">
      <p class="help-block">Example block-level help text here.</p>
    </div>
    <div class="checkbox">
      <label>
        <input type="checkbox"> Check me out
      </label>
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
  
  <!-- 水平排列 -->
  <form class="form-horizontal">
    <div class="form-group">
      <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
      <div class="col-sm-10">
        <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
      </div>
    </div>
    <div class="form-group">
      <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
      <div class="col-sm-10">
        <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <div class="checkbox">
          <label>
            <input type="checkbox"> Remember me
          </label>
        </div>
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">Sign in</button>
      </div>
    </div>
  </form>
  ```

- 导航

  ```html
  <ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">Home</a></li>
    <li role="presentation"><a href="#">Profile</a></li>
    <li role="presentation"><a href="#">Messages</a></li>
  </ul>
  ```

- 导航条

  ```html
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
          <!-- 移动端的导航条压缩按钮 -->  
          <span class="sr-only">Toggle navigation</span>
          <!-- 决定其中的横线数量 -->
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Brand</a>
      </div>
  
      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
          <li><a href="#">Link</a></li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">Action</a></li>
              <li><a href="#">Another action</a></li>
              <li><a href="#">Something else here</a></li>
              <li role="separator" class="divider"></li>
              <li><a href="#">Separated link</a></li>
              <li role="separator" class="divider"></li>
              <li><a href="#">One more separated link</a></li>
            </ul>
          </li>
        </ul>
        <form class="navbar-form navbar-left">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="Search">
          </div>
          <button type="submit" class="btn btn-default">Submit</button>
        </form>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#">Link</a></li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">Action</a></li>
              <li><a href="#">Another action</a></li>
              <li><a href="#">Something else here</a></li>
              <li role="separator" class="divider"></li>
              <li><a href="#">Separated link</a></li>
            </ul>
          </li>
        </ul>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>
  ```

- 分页条

  用于一页之内无法容纳或是希望分多页时的显示页码效果

  ```html
  <nav aria-label="Page navigation">
    <ul class="pagination">
      <li>
        <a href="#" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
      <li class="active"><a href="#">1</a></li>
      <li><a href="#">2</a></li>
      <li><a href="#">3</a></li>
      <li><a href="#">4</a></li>
      <li><a href="#">5</a></li>
      <li>
        <a href="#" aria-label="Next">
          <span aria-hidden="true">&raquo;</span>
        </a>
      </li>
    </ul>
  </nav>
  ```

  > 建议将 active 或 disabled 状态的链接（即 `<a>` 标签）替换为 `<span>` 标签，或者在向前/向后的箭头处省略`<a>` 标签，这样就可以让其保持需要的样式而不能被点击。

- 轮播图

  ```html
  <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
      <li data-target="#carousel-example-generic" data-slide-to="1"></li>
      <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>
  
    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="..." alt="...">
        <div class="carousel-caption">
          ...
        </div>
      </div>
      <div class="item">
        <img src="..." alt="...">
        <div class="carousel-caption">
          ...
        </div>
      </div>
      ...
    </div>
  
    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
  ```

#### JQuery

JQuery是一个JavaScript的框架，主要用于简化JavaScript代码

##### 快速入门

1. 下载JQuery

   > **JQuery版本**
   >
   > JQuery经过长时间的发展，主要分为了3个大版本
   >
   > 1. 兼容ie678，**使用最为广泛的**，官方只做bug维护，功能不再新增。因此一般项目来说，使用1.x版本就可以了，最终版本：1.12.4
   > 2. 不兼容ie678，很少人使用，官方只做bug维护，功能不再新增。如果不考虑兼容版本低的浏览器可以使用2.x，最终版本：2.2.4
   > 3. 不兼容ie678，只支持最新的浏览器。除非特殊要求，一般不会使用3.x版本的，很多老的jQuery插件不支持这个版本。目前该版本是官方主要更新维护的版本。截至2018年6月13日，最新版本：3.3.1

2. 导入JQuery和js文件

##### JQuery对象

JQuery提供了最基本的获取元素方法，`$([ 选择器 ])`此时获取到的对象就是JQuery对象

JQuery对象和JavaScript对象方法并不通用，相比起JavaScript，JQuery中对方法进行了更加便利的处理，使用时更加方便

###### JQuery对象和JavaScript对象的转换

- `JQuery > JavaScript`

  `JQ对象[索引]`或`JQ对象.get(索引)`

  类似数组或集合获取其中元素的方式

- `JavaScript > JQuery`

  `$([ JS对象 ])`

  只需要将所需进行转化的JJavaScript传入上方法，就可以将其转换为JQuery对象

##### 基本语法

1. 事件绑定

   ```javascript
   // 方式1
   // 获取myInput按钮，为其绑定单击事件
   $("#myInput").click(function(){
       ...
   });
   // 方式2
   // 使用on/off方法，分别对事件进行绑定或解绑
   $("#myInput").on("click", function(){
       ...
   });
   $("#myInput").off("click"); // 对元素的事件进行解绑，如果不指定具体的事件，会将元素上的所有事件解绑
   // 方式3
   // 切换事件，其中可以指定多个方法
   $("#myInput").toggle(fn1, fn2...);  
   // toggle方法在JQuery 1.9之后被删除了
   
   // 如果指定事件时，没有传入方法，其中会执行默认的方法，如focus()默认效果会将光标移动到指定的输入框，submit()默认为表单提交
   ```

2. 入口函数

   会在HTML或DOM文档完成之后执行的操作

   ```javascript
   // 入口函数，防止代码在元素加载之前进行执行
   $(function(){
       ...
   });
   ```

   > **`window.onload`和入口函数的区别**
   >
   > `window.onload`只能定义一次，之后再定义其中的值会被覆盖；入口函数可以定义多次

3. 样式控制

   ```JavaScript
   // 样式控制，控制的方式与CSS类似
   // 方式1 使用CSS中的各种属性
   $("#myDiv").css("background-color", "red");
   // 方式2 使用DOM中的各种属性，这样的属性在高级IDE中会有提示
   $("#myDiv").css("backgroundColor", "red");
   ```

##### 选择器

1. 基本选择器

   - 标签选择器（元素选择器）

     `$("html标签名")` 获得所有匹配标签名称的元素

   - id选择器

      `$("#id的属性值")` 获得与指定id属性值匹配的元素

   - 类选择器

      `$(".class的属性值") `获得与指定的class属性值匹配的元素

   - 并集选择器：

      `$("选择器1,选择器2....") `获取多个选择器选中的所有元素

2. 层级选择器

   - 后代选择器

      `$("A B ") `选择A元素内部的所有B元素，包括孙子元素

   - 子选择器

     `$("A > B") `选择A元素内部的B子元素，不包括孙子元素

3. 属性选择器

   - 属性名称选择器 

     `$("A[属性名]") `包含指定属性的选择器

   - 属性选择器

     `$("A[属性名='值']") `包含指定属性等于指定值的选择器

   - 复合属性选择器

     `$("A[属性名='值'][...]") `包含多个属性条件的选择器

     - `$("A[属性名!='值']")` 值不等
     - `$("A[属性名^='值']")` 以值开头
     - `$("A[属性名$='值']")` 以值结尾
     - `$("A[属性名*='值']")` 包含值

4. 过滤选择器

   - 首元素选择器 

     `:first` 获得选择的元素中的第一个元素

   - 尾元素选择器 

     `:last`获得选择的元素中的最后一个元素

   - 非元素选择器

     `:not(selector)` 不包括指定内容的元素

   - 偶数选择器

     `:even` 偶数，从 0 开始计数

   - 奇数选择器

     `:odd` 奇数，从 0 开始计数

   - 等于索引选择器

     `:eq(index)` 指定索引元素

   - 大于索引选择器 

     `:gt(index) ` 大于指定索引元素

   - 小于索引选择器 

     `:lt(index) `小于指定索引元素

   - 标题选择器

     `:header` 获得标题（h1~h6）元素，固定写法

5. 表单过滤选择器

   - 可用元素选择器 

     `:enabled` 获得可用元素

   - 不可用元素选择器 

     `:disabled` 获得不可用元素

   - 选中选择器 

     `:checked` 获得单选/复选框选中的元素

   - 选中选择器 

     `:selected` 获得下拉框选中的元素

     获取的应该是`<select>`中的`<option>`的选择个数

##### 具体方法

###### DOM操作

- 内容操作

  - `html()`

    获取或设置元素体中的内容，可以设置HTML内容

  - `text()`

    获取或设置元素体中的文本内容，只能指定纯文本内容

  - `val()`

    获取或设置元素的`value`属性值

- 属性操作

  - 通用属性操作

    - `attr()` 获取或设置元素的属性

    - `removeAttr()` 删除属性

    - ------

      `prop()` 获取或设置元素的属性

    - `removeProp()` 删除属性

    > 两种方式的区别
    >
    > 1. 如果操作的是元素的**固有属性**，推荐使用`prop()`
    > 2. 如果操作的是元素的**自定义属性**，建议使用`attr()`

  - 对class属性操作

    - `addClass()` 添加class属性值

    - `removeClass()` 删除class属性值

    - `toggleClass()` 切换class 属性

      判断元素上是否存在指定的属性，如果本身存在，会将其删除；反之，会添加属性

  - 对CSS样式操作

    - `css()` 获取或修改CSS属性

- CRUD操作

  ​		父子关系的增加操作

  - `append()` 追加元素到内部末尾

  - `prepend()` 追加元素到内部开头

  - `appendTo()` 将对象添加到目标元素的内部末尾

  - `prependTo()` 将对象添加到目标元素的内部开头

    ------

    同级或兄弟之间的添加操作

  - `after()` 添加元素到元素之后

  - `before()` 添加元素到元素之前

  - `insertAfter()` 将对象添加到目标元素的后面

  - `insertBefore()` 将对象添加到目标元素的前面

  > 如果直接用页面中现成的元素希望进行追加，会直接将原本的元素移动到目标位置，而非复制

  - ------

    `remove()` 移除元素

  - `empty()` 清空元素之下的所有后代元素

  - ------

    `clone()` 克隆元素

###### 动画

- 默认显示和隐藏方式

  - `show()` `param speed, easing, fn`
  - `hide()` `param speed, easing, fn`
  - `toggle()` `param speed, easing, fn`

  > 具体的效果类似收缩的效果，向左上角收缩

- 滑动显示和隐藏方式

  - `slideDown()` `param speed, easing, fn`
  - `slideUp()` `param speed, easing, fn`
  - `slideToggle()` `param speed, easing, fn`

  > 具体效果类似下拉菜单的效果，向上收缩

- 淡入淡出显示和隐藏方式

  - `fadeIn()` `param speed, easing, fn`
  - `fadeOut()` `param speed, easing, fn`
  - `fadeToggle()` `param speed, easing, fn`

  > 直接原大小，直接变淡消失

> 参数
>
> `speed` 表示动画的速度，其中包含三个预设的值`slow`、`normal`、`fast`，还可以指定具体的<u>毫秒值</u>
>
> `easing` 表示切换的效果，默认值为`swing`，还包括参数`linear`
>
> - `swing` 表示动画执行时的效果为，“慢快慢”的节奏
> - `linear` 表示动画执行使用线性速度
>
> `fn` 在动画完成时执行的函数 

- 自定义的动画

  **默认情况下，元素的位置都是静态的，无法移动，如果希望元素可以进行移动，需要将元素的CSS属性指定为relative、fixed或absolute。**

  - `animate()` `param {params}, speed, callback`

    创建自定义的动画效果，其中`params`参数可以指定动画的CSS属性

###### 遍历

- `each()` `param callback`

  对象通过调用方法，对内容进行遍历，遍历获取到的是JavaScript对象

  `callback`为传入某个方法，方法可以接收两个参数`index`和`element`

  - `index` 索引值
  - `element` 元素值

- `JQuery.each()`或`$.each()` `param object, callback`

  与对象直接调用`each()`方法类似，需要指定需遍历的对象

> 如果希望控制循环，实现如`break`和`continue`的效果，可以使用`return`一个布尔值
>
> 如果返回`false`表示结束循环，效果等价于`break`；如果返回`false`表示跳过本轮循环，效果等同于`continue`

- `for...of`

  【JQuery 3.0之后提供的方式】

  ```javascript
  // 类似java中的增强循环
  // li表示索引对象，代表容器中的元素
  // urs代表一个集合，此处主要表示<ul>
  for(li of uls){
      ...
  }
  ```

- 支持JS中基础的`for`循环方式

  ```javascript
  for(int i = 0; i < uls.length; i++){
      ...
  }
  ```

##### 插件

增强JQuery的功能

###### 实现方式

- `$.fn.extend()` `param object`

  增强通过JQuery获取对象的方法，对象方法

- `$.extend()` `param object`

  增强JQuery对象本身的方法，全局方法

###### 使用步骤

```JavaScript
// $.fn.extend()
// 需求：定义对象方法，让JQuery对象中添加check()和uncheck()方法
$.fn.extend({
    check:function(){
        
    },
    uncheck:function(){
    
	}
});
// 通过对象进行调用
$("#myBtn").check();
```

```javascript
// $.extend()
// 需求：定义全局方法，增加min()和max()方法
$.extend({
    min:function(a, b){
        
    },
    max:function(a, b){
        
    }
});
// 通过全局对象进行调用
$.max(1, 3);
```



## 后端部分

主要包括tomcat、Servlet等知识

### web服务器

服务器，指的是安装了服务器软件的**计算机**。

服务器软件，可以用来接收用户的请求，处理请求，并做出响应。

web服务器软件，专指的是部署web项目，让用户通过浏览器来访问的项目

**常见的Java相关web服务器软件**

- webLogic

  Oracle公司，大型的JavaEE服务器，支持所有的JavaEE规范，收费

- webSphere

  IBM公司，大型的JavaEE服务器，支持所有的JavaEE规范，收费

- JBOSS

  JBOSS公司，大型的JavaEE服务器，支持所有的JavaEE规范，收费

- Tomcat

  Apache基金组织，中小型的JavaEE服务器，仅仅支持少量的JavaEE规范，开源免费

#### Tomcat

###### 目录结构

- `bin` 核心运行文件
- `conf` 配置文件
- `lib` 依赖jar包
- `logs` 日志文件
- `temp` 临时文件
- `webapps` web项目
- `work` 运行时数据

###### 项目部署

1. 将项目放入webapps下

   - 将具体的编译后项目复制粘贴进入webapps根目录下
   - 将项目打包后的war放入webapps根目录下

2. 设置`conf/server.xml`文件

   ```xml
   <!-- 在Host标签内 -->
   <Host ... >
       <!--
   	docBase 项目路径
   	path tomcat访问时的虚拟路径
   	-->
       <Context docBase="projectPath" path="path"/>
   </Host>
   ```

3. 添加配置文件**【推荐】**

   在`conf/Catalina/`下创建xml配置文件，<u>此处的命名就是之后对应的虚拟路径</u>

   ```xml
   <!-- 只需要指定项目路径即可，文件名就是虚拟路径 -->
   <Context docBase="projectPath"/>
   ```

###### IDEA中的配置

- IDEA会为每一个Tomcat部署的项目单独建立一份配置文件

  具体地址，可以查看控制台log中：`Using CATALINA_BASE:`后的路径

- <u>工作空间项目</u>和<u>Tomcat部署的web项目</u>

  Tomcat真正访问的是<u>Tomcat部署的web项目</u>，其中对应的是<u>工作空间项目</u>下web目录下的内容，工作空间中其他的java文件，经过遍历后的字节码文件会被放在`WEB-INF/classes`文件中

  `WEB-INF`下的目录不能被浏览器直接访问，包括重定向
  
- 中文乱码

  `JAVA_OPTION` `-Dfile.encoding=utf-8`

#### Servlet

Servlet就是一种接口，其中定义了Java类被浏览器访问到（借助Tomcat）的规则

Servlet就是运行在服务器上的一个小程序，借助Tomcat进行调用

##### Servlet3.0前

###### 步骤

1. 创建JavaEE项目

2. 定义一个实现Servlet接口的类

3. 实现Servlet接口中的方法

4. 配置Servlet

   ```xml
   <!-- web.xml 中进行配置 -->
   <servlet>
       <!-- 用一个名称来代替具体的Servlet类 -->
   	<servlet-name>myServlet</servlet-name>
       <!-- 指定具体的Servlet类，使用全限定类名 -->
       <servlet-class>com.tms.test.ServletDemo1</servlet-class>
   </servlet>
   <!-- 设置Servlet的映射地址 -->
   <servlet-mapping>
       <!-- 指定对应的Servlet了，使用刚才指定的servlet-name -->
   	<servlet-name>myServlet</servlet-name>
       <!-- 设置访问Servlet对应的地址值 -->
       <url-pattern>/testServlet</url-pattern>
   </servlet-mapping>
   ```

###### 执行过程

1. 当服务器接收到客户端浏览器的请求后，会解析请求URL路径，获取访问的Servlet的资源路径
2. 查找web.xml文件，是否有对应的 `<url-pattern>` 标签体内容
3. 如果有，则会找到对应的 `<servlet-class>` 全限定类名
4. tomcat会利用反射的方式，将字节码文件加载进内存，并创建其对象
5. 调用Servlet中的方法

###### 方法

- `init()` `param ServletConfig` `throws ServletException`

  初始化方法，在Servlet被创建时执行，只会执行一次

- `getServletConfig()` `return ServletConfig`

  获取Servlet配置对象

- `service()` `param ServletRequest, ServletResponse` `throws ServletException, IOException`

  服务方法，每一次的Servlet访问都会执行，会执行多次

- `getServletInfo()` `return String`

  获取Servlet中的信息，版本、作者等。

- `destroy()`

  销毁方法，在Servlet生命周期结束之前执行（服务器正常关闭时执行）

  一般用于释放资源

###### 生命周期

1. 被创建

   执行`init()`方法，只会执行一次

   默认情况下，在第一次访问时创建。可以通过配置`web.xml`，设置创建的时机。

   ```xml
   <!-- web.xml中进行配置 -->
   <servlet>
   	<servlet-name>myServlet</servlet-name>
       <servlet-class>com.tms.test.ServletDemo1</servlet-class>
       <!-- 配置加载的时机
    	其中的值小于0时，只有在第一次访问时才会创建
   	其中的值为0~5的整数时，可以根据服务器开启时创建，其中的值为优先级，值越小优先级越高
   	-->
       <load-on-startup>5</load-on-startup>
   </servlet>
   ```

   同一个服务器只会创建一个Servlet，说明其使用**单例模式**

   > Servlet使用单例模式，为了防止出现异常，应该尽可能不要在Servlet中定义成员变量，而使用局部变量的方式，避免出现异常。

2. 提供服务

   执行`service()`方法，每一次调用都会执行

3. 被销毁

   执行`destroy()`方法，只会执行一次

##### Servlet3.0后

与Servlet3.0之前最大的区别，支持注解配置，不需要使用web.xml进行配置。

可以直接完全使用注解的方式，完全不使用web.xml进行创建。

###### 步骤

基本上与Servlet之前相同，但是可以无需创建web.xml文件。

1. 创建JavaEE项目，可以无需创建web.xml文件

2. 定义一个实现Servlet接口的类

3. 实现Servlet接口中的方法

4. 在Servlet类上使用注解进行配置

   ```java
   // @WebServlet(urlPatterns="/testServlet")
   // 指明资源的路径
   @WebServlet("/testServlet")
   public class MyServlet implements Servlet{
       ...
   }
   ```

> urlPatterns配置方式
>
> 1. `/xxx`
> 2. `/xxx/xx`
> 3. `*.do` 使用自定义的后缀名（`.do`可以修改成其他任意的后缀）。在使用后缀以及`*`通配符的方式，**不能在前面添加斜杠**
>
> `*`代表通配符，可以使用任何地址值进行访问，但是访问的优先级最低（只有其他所有类似的地址都不匹配时，才会考虑）

##### Servlet体系结构

如果使用类来实现Servlet接口，就会不得不去完成其中的所有抽象方法，但是很多时候，其中的方法并不需要实现，此时就需要使用Servlet相关的抽象类来实现这样的需求。

###### 结构效果

- `Servlet` 接口
  - `GenericServlet` 抽象类
    - `HttpServlet` 抽象类

###### GenericServlet

实现了Servlet接口，将其中的其他方法使用了默认的**空实现**，只将`service()`设置为抽象方法。

如果之后定义Servlet类时，如果不需要修改其他方法，可以考虑继承`GenericServlet`

###### HttpServlet

对HTTP协议的一种封装，简化操作

总结使用Servlet类实现方法一般的情况，可以看出其中的必要步骤

获取请求数据：

​	通常情况下，都需要从请求中，获取具体的请求数据

​	这其中，需要判断请求的方式，根据其中不同的请求方式来获取数据

​	`doGet()` 如果是使用get请求时，会自动调用

​	`doPost()` 如果是使用post请求时，会自动调用

#### JSP

JSP，Java Server Pages，Java服务器端界面。

是一种特殊的界面，其中即可以指定HTML页面元素，也能定义java代码。<u>最主要的作用是用于简化书写。</u>

JSP本质上就是一个Servlet类，在类中映射了HTML元素，使用了大量的`write()`方法，动态生成了一个HTML文件。

##### 注释

```JSP
<!-- 方式1
最多对HTML标签进行注解，不能JSP中的特有标签进行注释
如 <h1> ... </h1>
在输出到客户端的页面源代码中依然保留
-->
<%-- 方式2【推荐使用】
可以对JSP中的所有标签进行注释，包括HTML标签
如 <% ... %>
在输出到客户端的页面源代码中不会保留
--%>
```

##### JSP脚本

JSP中定义Java代码的方式

```jsp
<!-- 方式1
主要的Java逻辑代码
定义的Java代码，最终会存在service方法中，因此，此处的Java代码就相当于是service方法中的代码
-->
<% //Java代码 %>

<!-- 方式2 
定义变量或方法
定义的Java代码，最终会存在JSP类的下，因此，主要存放类中可以定义的内容，如定义成员变量、成员方法等
-->
<%! //Java代码 %>

<!-- 方式3 
输出变量
编译之后，其中的内容会存在out.print()中
-->
<%= //Java代码 %>
```

##### JSP的内置对象

在JSP页面中，不需要获取与创建的对象，就是其内置对象。

1. `request` `HttpServletRequest`

2. `response` `HttpServletResponse`

3. `out` `JspWriter`

   字符输出流对象，JSP中通过`ServletContext.getOut()`方法进行获取。其效果类似`response.getWriter()` 中获取的输出流

   但是`response.getWriter()`的输出永远在`out.write()`的输出之前

4. `pageContext` `PageContext`

   域对象，在当前页面间共享数据

   可以用于获取其他的所有对象

5. `session` `HttpSession`

   服务器端会话对象，用于处理一次会话中的多次请求之间的数据共享

6. `application` `ServletContext`

   整个服务器内的数据共享，单例。

7. `page` `Object`

   当前Servlet对象

8. `config` `ServletConfig`

   Servlet配置对象

9. `exception` `Throwable` 必须声明为错误页面时才能使用 

   异常对象，包括各种异常的输出操作

##### 指令

用于配置JSP页面，导入资源文件

```jsp
<!-- name和value分别对应其属性名和属性值，每组键值对之间使用空格分隔 -->
<%@ [ 指令名称 ] name1=value1 name2=value2 ... %>
```

- `page`

  配置JSP页面的

  - `contentType`

    相当于`response.setContentType()`，主要用于指定响应体的MIME类型以及字符集

    其中，字符集对于大部分的IDE工具来说，可以同时指定页面的字符集，但是也有直接用于指定页面的字符集的参数`pageEncoding`

  - `import`

    导包，一般情况下推荐单独使用一个指令中来实现导包

  - `errorPage`

    指定错误页面，如果当前页面中出现异常，会跳转到错误页面

  - `isErrorPage`

    标识当前界面是不是错误界面，`true`或`false`。默认值为`false`

    指定为错误界面之后，新增了内置对象`exception`。可以用于获取错误信息等。

- `include`

  指明页面包含的内容，导入页面的资源文件

  主要用于提取页面中的重复部分，将其提取出来，将其作为资源进行导入

  - `file`

    指定需要导入的页面资源文件

- `taglib`

  导入资源

  主要指的是外部的`jar`文件，引入标签库

  - `prefix`

    指定自定义的前缀

  - `url`

    指定标签库的URL值



##### EL表达式

EL，Expression Language，表达式语言

主要用于替换和简化JSP页面中的Java代码的编写

```jsp
<!-- 基本的语法 -->
${ 表达式 }
```

> **注意**
>
> JSP中默认可以识别EL表达式，如果需要展示像EL表达式的数据可以通过如下方法
>
> 1. 在`page`指令中，可以设置`isELIgnored`值为`true`，会忽略页面中的所有EL表达式。默认值为`false`
> 2. 在EL表达式的`$`符号之前使用斜线`\`，可以忽略本条EL表达式

###### 使用方式

1. 运算

   其中支持基本的运算

   - 运算符

     - 算数运算符  `+` `-` `*` `/` `%`

       其中的`/`可以使用`div`来表示，`%`可以使用`mod`来表示

     - 比较运算符  `>` `<` `>=` `<=` `==` `!=`

     - 逻辑运算符  `&&` `||` `!`

       其中`&&`可以使用`and`代替，`||`可以使用`or`来代替，`!`可以使用`not`来代替

     - 空运算符 `empty`

       用于判断对象是否为null或者长度是否为0。如果为null或长度为0，返回`true`；反之，返回`false`

       `${empty 数组名}`

2. 获取值

   - **EL表达式只能从与对象中获取值**

     1. `${域名称.键名}` 从指定的域中获取指定键对应的值

        - `pageScope` 代表`pageContext`域
        - `requestScope` 代表`request`域
        - `sessionScope` 代表`session`域
        - `applicationScope` 代表`ServletContext`域，或`application`域

        > 如果没有获取到值，会显示空字符串，不会显示null

     2. `${键名}` 表示依次从最小的域开始查找，是否有对应的值，直到查找到为止

   - **EL表达式用于获取引用类型中的具体数据**

     通过的是对象的`getter`和`setter`方法，将其方法中的`set`和`get`删除，获取的是方法名的剩余部分，再将剩余部分的首字母小写，作为最后的获取属性

     如，`getName()`方法被转化之后得到的是`Name`，再小写`name`，就可以通过`name`调用`getName()`方法

     ```jsp
     <!-- 假定已经存在一个自定义类User，是一个标准的JavaBean，其中有属性name -->
     <%
     	// 创建User对象
     	User user = new User();
     	user.setName("张三");
     	// 将User对象加入request域
     	requeset.setAttribute("user", user);
     %>
     <!-- 使用EL表达式，获取User对象中的name值 -->
     ${requestScope.user.name}
     ```

   - **EL表达式用于获取List集合中的具体数据**

     `${域名称.键名[索引]}`

     本质上与数组相同，通过传入索引值获取到位置上的值

     > **注意**
     >
     > 如果出现索引的下标越界，EL表达式进行了优化，并不会出现异常，并且返回的是空字符串

   - **EL表达式用于获取Map集合中的具体数据**

     - `${域名称.键名.key值}`

     - `${域名称.键名["key值"]}`

       通过对应的Key值获取到对应的值

3. 隐式对象

   EL表达式中无需创建，可以直接使用的对象

   - `pageContext`

     可以用于获取JSP中其他的8个内置对象

##### JSTL

JSTL，JavaServer Pages Tag Library，JSP的标准标签库。主要是是由Apache组织提供的开源免费JSP标签

可以用于简化或替换JSP页面上的JSP代码

###### 使用方法

1. 导入JSTL相关的jar包

2. 通过`taglib`指令引入标签库

   `url`的主要取值	`http://java.sun.com/jsp/jstl/core`；一般将其前缀设定为`c`

3. 使用标签

###### 常用的标签

- `if` 相当于Java代码中的`if`语句

  - `<c:if>` `param test[必须]`

    其中`test`用于接收boolean值，如果为`true`就会显示标签体中的内容；反之，结果为`false`，不会显示

    没有对应的else方法，如果需要，可以定义一个结果相反的判定条件

- `choose` 相当于Java代码中的`switch`语句

  - `when` `param test[必须]`

    内部标签，类似`case`

  - `otherwise` 内部标签，类似`default`

  与`switch`语句类似，但是在判定条件的设置位置上有不同

  相比起`switch`更像是连续的`if else`判断，不再是针对同一个变量进行的多次判断，可以随意设置其中的判断条件，如果都不满足，就会执行最后的`otherwise`

- `foreach` 相当于Java代码中的`for`语句

  1. 重复一定的次数

     - `param begin, end, var, step` 【必须】

       `begin`开始值，`end`结束值，`var`临时变量，`step`步长

     - `param varStatus`

       表示循环状态，可以用于获取循环的`index`元素索引和`count`次数

  2. 遍历容器

     - `param items, var` 【必须】

       `items`容器对象，`var`临时变量

     - `param varStatus`

       表示循环状态，可以用于获取循环的`index`元素索引和`count`次数

#### HTTP

HTTP，Hyper Text Transfer Protocol，超文本传输协议。定义了客户端和服务器端传输数据时的格式。

##### 特点

1. HTTP是基于`TCP/IP`的高级协议
2. 默认的端口号是`80`
3. 基于请求/响应模型的（一次请求只会响应一次）
4. 每次请求都是独立的，不能交互数据

> 历史版本
>
> - 1.0
> - 1.1
>
> 其中唯一的区别，因为HTTP使用的是`TCP/IP`协议，因此其中的数据传入需要建立连接。
>
> 在`1.0`中，每次的响应都会建立连接，其中单独的请求结束就会断开。定义了三种请求方法： GET, POST 和 HEAD方法。
>
> 在`1.1`中，可以复用连接，不会每次请求都断开连接。新增了五种请求方法：OPTIONS, PUT, DELETE, TRACE 和 CONNECT 方法。
>
> ![HTTP请求协议](img/HTTP%E8%AF%B7%E6%B1%82%E5%8D%8F%E8%AE%AE.jpg)

##### 请求消息数据格式

1. 请求行

   - GET

   ```
   请求方式  请求url  请求协议/版本
   GET /search?hl=zh-CN&source=hp&q=domety&aq=f&oq= HTTP/1.1
   ```

   - POST

   ```
   POST /search HTTP/1.1
   ```

2. 请求头

   客户端告知服务器某些信息

   - 常见的请求头

     - `User-Agent`

       告知服务器，所使用的浏览器信息。服务器可以用于判断浏览器，解决不同浏览器之间的兼容性问题。

     - `Referer` 

       告知服务器，请求的网站来源

       可以用于防盗链，也可以用来统计流量来源

     - `Connection`

       声明连接状态，`1.1`之后支持持续连接，`keep-alive`
       
     - `token`

       令牌，随机获取，能够用于防止表单重复提交或是人机校验，比较常见的使用就是账户登录

   - GET

   ```
   请求头名称: 请求头的值1, 请求头的值1
   Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/vnd.ms-excel, application/vnd.ms-powerpoint,   
   application/msword, application/x-silverlight, application/x-shockwave-flash, */*    
   Referer: <a href="http://www.google.cn/">http://www.google.cn/</a>  
   Accept-Language: zh-cn    
   Accept-Encoding: gzip, deflate    
   User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; TheWorld)    
   Host: <a href="http://www.google.cn">www.google.cn</a>    
   Connection: Keep-Alive    
   Cookie: PREF=ID=80a06da87be9ae3c:U=f7167333e2c3b714:NW=1:TM=1261551909:LM=1261551917:S=ybYcq2wpfefs4V9g;NID=31=ojj8d-IygaEtSxLgaJmqSjVhCspkviJrB6omjamNrSm8lZhKy_yMfO2M4QMRKcH1g0iQv9u-2hfBW7bUFwVh7pGaRUb0RnHcJU37y-  
   FxlRugatx63JLv7CWMD6UB_O_r   
   ```

   - POST

   ```
   Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/vnd.ms-excel, application/vnd.ms-powerpoint,   
   application/msword, application/x-silverlight, application/x-shockwave-flash, */*    
   Referer: <a href="http://www.google.cn/">http://www.google.cn/</a> 
   Accept-Language: zh-cn    
   Accept-Encoding: gzip, deflate    
   User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; TheWorld)    
   Host: <a href="http://www.google.cn">www.google.cn</a>    
   Connection: Keep-Alive    
   Cookie: PREF=ID=80a06da87be9ae3c:U=f7167333e2c3b714:NW=1:TM=1261551909:LM=1261551917:S=ybYcq2wpfefs4V9g;   
   NID=31=ojj8d-IygaEtSxLgaJmqSjVhCspkviJrB6omjamNrSm8lZhKy_yMfO2M4QMRKcH1g0iQv9u-2hfBW7bUFwVh7pGaRUb0RnHcJU37y-  
   FxlRugatx63JLv7CWMD6UB_O_r    
   ```

3. 请求空行

   使用一个空行，用于分隔请求头和请求体

4. 请求体

   存储传入的数据内容

   - GET

     如果使用Get方式，没有请求体，其中的数据只用在URL地址中显示

   - POST

   ```
   数据名 = 数据值（多组数据之间，使用&分隔）
   hl=zh-CN&source=hp&q=domety
   ```

##### 响应信息数据格式

1. 响应行

   ```
   协议/版本 响应状态码 [响应码描述]
   HTTP/1.1 200
   ```

   > 状态码
   >
   > 服务器告知服务器本次请求和响应的状态。
   >
   > 其中都是由3位数字组成
   >
   > 1. 1xx
   >
   >    信息，服务器收到请求，需要请求者继续执行操作。
   >
   >    服务器接收服务器信息，但是没有接收完成，等待一段时间之后，发送100+状态码。
   >
   > 2. 2xx
   >
   >    成功，操作成功被接收并处理
   >
   > 3. 3xx
   >
   >    重定向，需要进一步操作以完成请求
   >
   >    客户端发送的请求，需要使用其他的资源才能实现，客户端会返回300+状态码，让客户端转向其他资源进行请求。
   >
   >    - `302` 重定向。请求的资源已被临时的移动到新URI，返回信息会包括新的URI，浏览器会定向到新URI。
   >    - `304` 访问缓存。所请求的资源未修改，服务器返回此状态码时，不会返回任何资源。客户端通常会缓存访问过的资源，通过提供一个头信息指出客户端希望只返回在指定日期之后修改的资源
   >
   > 4. 4xx
   >
   >    客户端错误，请求包含语法错误或无法完成请求
   >
   >    - `403` 服务器理解请求客户端的请求，但是拒绝执行此请求。
   >    - `404` 请求路径没有对应资源。服务器无法根据客户端的请求找到资源（网页）。通过此代码，网站设计人员可设置"您所请求的资源无法找到"的个性页面
   >    - `405` 代表请求方式没有对应的方法，客户端请求中的方法被禁止
   >
   > 5. 5xx
   >
   >    服务器错误，服务器在处理请求的过程中发生了错误
   >
   >    一般是代码中存在错误
   >
   >    - `500` 服务器内部错误，无法完成请求
   >
   >    

2. 响应头

   ```
   头名称 : 值
   Set-Cookie: JSESSIONID=318536A6FFE39B3DE409B1EB2DD9DB8C; Path=/; HttpOnly
   Content-Type: text/html;charset=UTF-8
   Content-Length: 134
   Date: Sat, 13 Jun 2020 10:02:45 GMT
   Keep-Alive: timeout=20
   Connection: keep-alive
   ```

   - Content-Type

     服务器告知客户端本次响应体数据格式以及编码格式。

   - Content-disposition

     服务器告知客户端以什么格式打开响应体数据

     - in-line

       在当前界面内打开【默认值】

     - `attachment;filename=[ 文件名 ]`

       以附件形式打开响应体，文件下载

3. 响应空行

   空行，用于分隔响应头和响应体。

4. 响应体

   具体传输的数据

#### 数据传输

服务器与客户端之间的数据传输，在Tomcat服务器下，需要借助`request`和`response`对象来实现。

`request`和`response`对象都是由Tomcat服务器创建的。`request`用于存储请求消息，`response`用于存储响应信息。

##### request

用于存储客户端请求的信息

- `ServletRequest` 接口
  - `HttpServletRequest` 接口
    - RequestFacade 实现类

`最后创建出的`request`对象为org.apache.catalina.connector.RequestFacade`。该对象是由Tomcat进行创建，因此查询不到具体的包位置。

1. 获取请求信息数据

   ```
   请求方式  请求url  请求协议/版本
   GET /test?name=test1 HTTP/1.1
   ```

   - 获取请求行

     - `getMethod()` `return String`

       获取**请求方式**，如`GET`、`POST`请求等

     - `getContextPath()` `return  String`

       获取**虚拟路径**，如果web项目的地址没有设置成`/`，就会显示成对应的值。此处将项目的地址值设置为了`/`，因此获取值为空字符串。

     - `getServletPath()` `return String`

       获取**客户端请求地址**，返回请求的地址值，如此处的`/test`。

     - `getRequestURI()` `return String`

       获取**请求URI**，就是<u>虚拟路径</u>和<u>客户端请求地址</u>的组合，此处为`/test`

     - `getRequestURL()` `return String`

       获取**请求URL**，URL就是在URI的基础之上，添加了协议以及ip和端口号，类似浏览器中输入的地址，此处为`http://localhost:8080/test`

     - `getQueryString()` `return String`

       获取**GET请求方式的参数**，如此处的`name=test1`。

     - `getProtocol()` `return String`

       获取**协议和版本号**，此处为`HTTP/1.1`

   - 获取请求头

     - `getHeader()` `param name` `return String`

       获取**请求头**。通过传入指定的请求头名，获取请求头信息

     - `getHeaderNames()` `return Enumeration<String>`

       获取**所有请求头名**。返回类似String的枚举值，其中的方法类似迭代器。

   - 获取请求体

     GET请求中没有请求体，请求体中封装的是请求的参数。

     1. 获取流对象

        - `getReader()` `return BufferedReader`

          获取**字符输入流**

        - `getInputStream()` `return ServletInputStrem`

          获取**字节输入流**

     2. 从流对象中获取数据

        利用IO流中的知识，获取其中的数据。

   - 获取客户端相关信息

     - `getRemoteAddr()` `return String`

       获取**客户访问的IP地址**

2. 其他功能

   - 获取请求参数【通用】

     - `getParameter()` `param name` `return String`

       根据参数名，获取参数。如果此时参数中有个多个值，只会显示第一个值。

     - `getParameterValues()` `param name` `return String[]`

       根据参数名称，获取参数数组的值。某些时候，一个name不止有一个值，可以通过此方法获取其中所有的值。例如，复选框时。

     - `getParameterNames()` `return Enumeration<String>`

       获取所有参数的名称

     - `getParameterMap()` `return Map<String, String[]>`

       获取所有的参数以及其对应的值

   - 请求转发

     服务器内部的一种资源跳转方式。是一种服务器行为，在处理用户的请求过程中，借助了其他的资源，但是这其中的转发过程用户并不能察觉。主要体现为地址栏不会发生变化，同时只能访问当前服务器内部的资源，

     `getRequestDispatcher()` `param path` `return RequestDispatcher`

     传入一个path，指定转发的地址值。 

   - 共享数据

     - 域对象

       一个有作用范围的对象，可以在范围内共享数据

       - request域

         代表一次请求的范围

     `setAttribute()` `param name, object`

     在request域中存储数据，分别设置了参数名以及参数值

     `getAttribute()` `param name` `return Object`

     根据参数名，获取参数值

     `removeAttribute()` `param name`

     根据参数名，移除之前设置的域参数

   - 获取ServletContext

     `getServletContext()` `return ServletContext`

   - 中文乱码

     在POST等使用请求体的请求方式中，如果传递中文，会出现乱码。

     在获取参数之前，可以设置request的编码来解决乱码问题

     `request.setCharacterEncoding("utf-8")`

##### response

用于存储服务器返回客户端的数据

- ServletResponse 接口
  - HttpServletResponse 接口
    - ResponseFacade 实现类

Tomcat在接收请求时自动创建，其中用于存放响应数据。

1. 设置响应信息

   1. 设置相应行

      ```
      协议/版本 响应状态码 [响应码描述]
      HTTP/1.1 200
      ```

      `setStatus()` `param 响应码`

      设置具体的响应码

   2. 设置响应头

      `setHeader()` `param name, value` 

      设置响应头，传入对应的name和value数据

   3. 设置响应体

       设置响应体中具体传输的数据，与请求体类似，数据都使用流的方式封装

      获取输出流

      - 字符输出流

        `getWriter()` `return PrintWriter`

      - 字节输出流

        `getOutputStream()` `return ServletOutputStream`

2. 重定向

   客户端请求服务器的服务，此时存在其他的已经实现的方法可以完成客户端的请求，就可以将客户端的请求重定向到其他的接口中。

   此时，就可以通过设置响应的状态码，以及设置响应头中的`location`信息，指向需要的目标URL即可。

   1. 只用传统的方式实现重定向

      ```java
      // 设置响应状态码为302，即临时重定向
      response.setStatus(302);
      // 设置响应头中的location属性，指定目标的URL地址，此处与请求转发不同，可以使用服务器外的数据
      // 此时使用的地址值需要指定虚拟地址
      response.setHeader("location", "/test2");
      ```

   2. 使用简单的重定向方法

      ```java
      // HttpServletResponse中提供的方法，实际的操作与第一种方式相同
      response.sendRedirect("/test2");
      ```
      
   
3. 中文乱码

   响应体因为使用了与请求体类似的设计，因此其在进行数据传输时存在中文乱码的可能性，即服务器发送的响应体并没有按照设想中的字符编码进行解析。

   **注意，必须在获取流之前进行设置**

   1. 传统的方式解决

      此处需要进行两个步骤

      1. 指定响应体中的字符编码【可省略】

         没有指定时的字符编码默认为ISO-8859-1

      2. 告知服务器使用的字符编码**【重要】**

         客户端的字符编码默认使用ANSI，没有设定响应体的字符编码，此时指定同样可以改变服务器端发送的响应体中的字符编码

      ```Java
      // 设定response响应体中使用的编码
      response.setCharaterEncoding("utf-8");
      // 设置浏览器使用的字符编码
      resposne.setHeader("content-type", "text/html;charset=utf-8");
      ```

   2. 简单方式解决

      ```java
      response.setContentType("text/html;charset=utf-8");
      ```

##### ServletContext

代表了整个web应用，可以与程序的容器来通信。此处主要指与服务器进行通信。

因为对应了整个web应用，因此创建时符合单例模式。

主要包括如下的功能

1. 获取MIME

   MIME，指的是互联网通信过程中定义的一种文件数据类型

   ```
   上级类型/下级类型
   text/html	image/jpeg
   ```

   `getMimeType()` `param file` `return String`

   通过文件名，获取对应的MIME类型

2. 域对象

   可以进行数据的共享。与之前的request域类似。

   可以用于共享所有用户基于本服务器请求的数据

   - `setAttribute()` `param name, object`

     在request域中存储数据，分别设置了参数名以及参数值

   - `getAttribute()` `param name` `return Object`

     根据参数名，获取参数值

   - `removeAttribute()` `param name`

     根据参数名，移除之前设置的域参数

   > **注意**
   >
   > ServletContext中存储数据需要谨慎，其生命周期与服务器的开启和关闭相同，因此其中的数据存在时间非常之长。
   >
   > 如果在其中存放了大量的数据，会给服务器造成大量的压力。

3. 获取文件的真实路径

   此处的文件路径主要指文件在服务器端的真实地址。

   web项目中的数据主要会被分为两个部分，本地的工作空间和部署到Tomcat服务器中的文件数据。

   此处的主要用于获取Tomcat服务器中真实的路径

   - `getRealPath()` `param path` `return String`

     传入一个表示资源地址值的`path`参数，会返回最终资源真实的地址值。

     > 此处传入的path参数，与资源所在的位置有关
     >
     > - 在web目录下
     >
     >   与浏览器地址的值相同，可以`/`可以代表web目录的根目录
     >
     > - 在java目录下
     >
     >   java目录下的内容最终会在编译之后整合到web目录的`WEB-INF/classes`目录之下
     >
     >   同样可以使用ClassLoader来获取其真实的目录

4. 获取初始化参数

   可以用于服务器创建时，获取用户设置的默认参数

   - `getInitParameter()` `param 字符串`

     根据参数名，获取参数

###### 创建

- 通过request对象进行获取

  `getServletContext()` `return ServletContext`

- 通过HttpServlet获取

  继承了HttpServlet类之后，都可以直接通过调用HttpServlet中的方法来直接获取ServletContext对象

  `getServletContext()` `return ServletContext`

##### 补充知识

###### 请求转发

请求转发是服务器行为，即用户向服务器发送了一次http请求，该请求可能会经过多个信息资源处理以后返回给用户，各个信息资源使用请求转发机制互相转发请求，从用户的感官上来看，是感觉不到请求转发的。

- 从第一次发送请求到最后一次发送请求的过程中，web容器只创建一次request和response对象，新的页面继续处理同一个请求。
- 其本质是服务器将request对象在页面之间进行了相互的传递。
- 可以共用request对象信息。
- 服务器内部进行的转发
- 只有一次请求
- 地址栏不会发生变化
- 必须是在同一台服务器下完成

###### 重定向

请求是客户端行为（客户端跳转）。服务器端在响应第一次请求的时候，让浏览器再向另外一个URL发出请求，从而达到转发的目的。它本质上是两次HTTP请求，对应两个request对象。

- 有两次请求
- 地址栏会发生改变
- HttpServletRequest不能可以在这两次请求中共享数据
- 可以共享context、session域的数据
- 可以在不同服务器下完成

###### 路劲

在Servlet中的地址写法

- 相对路径

  依据当前目录，确定唯一的资源

  不以`/`开头的地址值

  - `.` 表示当前目录
  - `..` 代表上级目录

  `./index.html` 或 `test.html`

- 绝对路径

  通过绝对路径确定唯一资源，URL或URI。

  使用URI的时候，需要使用`/`开头，确定Tomcat服务器的根目录。

  `http://localhost:8080/index.html` 或 `/index.html`

  > 判断应该添加虚拟目录？
  >
  > 可以通过本次跳转的发起者进行判断
  >
  > - 客户端发起
  >
  >   由客户端的请求触发的跳转行为
  >
  >   需要添加虚拟目录
  >
  > - 服务器发起
  >
  >   由服务器直接发起的跳转行为，如请求转发，服务器内部进行处理和转发
  >
  >   不需要添加虚拟目录

  此时的虚拟路径，可以借助动态的方式获取，来避免今后修改虚拟地址导致需要大量的修改代码的情况

  **建议使用**`getContextPath()`方法，动态获取虚拟路径。

- 

#### 会话技术

会话，指浏览器从第一次向服务器发送请求，一直到其中一方断开连接为止的过程，叫做一次会话。每一次的会话都可以包括多次的请求。

主要用于解决一次会话内的多次请求之间的资源共享，用于联动多次请求。

1. 客户端会话技术：Cookie
2. 服务器端会话技术：Session

##### Cookie

Cookie，一种客户端会话技术，数据存放在客户端。

其中的数据，会随着每次的请求一同发送至服务器端，但是最终的数据依然存放在客户端。

###### 步骤

1. 创建Cookie对象，绑定数据

   ```java
   // 创建Cookie对象，分别指定其中的name和value值
   Cookie cookie = new Cookie(name, value);
   ```

2. 发送Cookie对象

   通过response对象，将创建的Cookie对象传入其中

   - `addCookie()` `param Cookie`

     将Cookie对象加入与响应一同返回到客户端

3. 获取Cookie对象，获取数据

   通过request对象来获取Cookie

   - `getCookies()` `return Cookie[]`

     获取所有的Cookie对象数组

###### 原理

1. Cookie的生成

   通过服务器创建Cookie并响应给客户端时，会通过`set-cookie`**响应头**将Cookie发送给客户端。

   此时的客户端浏览器解析到特定的响应头之后，会将其存放到浏览器缓存中。

2. Cookie的使用

   对于浏览器，将Cookie存放到缓存之后。以后的每次请求都会自动地在**请求头**中添加Cookie数据，一同发送往服务器端。

   服务区端可以通过请求头来获取Cookie的数据，进行之后的操作。

###### 补充

- 多个Cookie的创建

  Cookie的一次创建只能设置一对键值对，如果需要设置多对数据，需要创建多个Cookie对象。

  之后调用多次`addCookie()`方法，将Cookie对象分别传输进去即可

  **注意**，如果设置多个相同的name，会其中的值用逗号隔开，放在同一个name之下；如果是不同的name，每组键值对之间使用分号进行分隔。
  
- Cookie的保存时间

  在默认情况下，在浏览器关闭之后，Cookie会被清空。

  - `setMaxAge()` `param 秒数[int]`

    设置Cookie对象的保存时间，在其中传入一个整数

    - 正数

      持久化存储，告知浏览器，需要将数据存储到磁盘中，设置的是**秒数**。

      需要重新开启浏览器，如设置为30秒，指的是关闭浏览器30秒之后重新打开，Cookie已经被清除，如果浏览器一直开启，并不会被清除。

    - 负数

      默认值，在浏览器关闭时清除

    - 零

      **删除指定的Cookie**

- Cookie存储中文

  **在Tomcat8之前，Cookie明确不允许存储中文信息**

  如果需要在Tomcat8之前存储中文数据，需要对其进行转码，一般采用URL编码。

- Cookie的共享问题

  - 在**同一个Tomcat服务器**中，部署了多个web项目，这些web项目中的Cookie能否进行共享？

    默认情况下，Cookie不能进行共享

    `setPath()` `param path`

    设置Cookie的作用范围，默认情况下只针对当前的虚拟目录（项目级别的映射目录）

    如果需要全局共享，可以设置为`/`，表示在所有的web项目中都可以进行使用。

  - 在**不同的Tomcat服务区之间**，能否实现Cookie的共享？

    默认情况下，不能实现跨服务器之间及进行共享。

    如果需要实现不同的服务器之间实现共享，首先还需要满足条件，其一级域名相同，才能可能实现共享。

    `setDomain()` `param path`

    通过指定其一级域名，将其设置为域名之间共享。

    如`setDomain(".baidu.com")`，那么`tieba.baidu.com`和`news,baidu.com`就可以进行共享。

###### 特点与作用

特点

1. Cookie存储数据在客户端浏览器
2. 浏览器对于单个Cookie的大小有限制（4kb左右），以及对于同一个域名下的总Cookie数量也有一定的限制（20个左右）

作用

1. Cookie一般用于存储少量不太重要的数据
2. 在不登陆的情况下，服务器对客户端进行身份的识别

##### Session

Session，一种服务器端会话技术，在一次会话的多次请求间共享数据，将数据保存在服务器端的`HttpSession`对象中

###### 步骤

1. 获取Session对象

   `getSession()` `return HttpSession`

   通过`request`对象，获取Session对象

2. Session的数据设置

   - `getAttribute()` `param name` `return Object`

     根据参数名称，获取参数值

   - `setAttribute()` `param name, Object`

     设置参数的键值对，存储在Session对象中

   - `removeAttribute()` `param name`

     根据参数名称，删除对应的键值对

###### 原理

Session的实现是借助Cookie来实现的。

Session的主要特点，对于同一次会话中所获取到的对象都是相同的，但是并非使用了单例模式，因为服务器应该是使用了多线程的方式，同一时间处理多个用户的请求，此时后台依然能够对应某个用户获取到对应的session

1. Session的生成

   Session对象在生成时，会有一个对应的ID值，而这样的值，会作为Cookie传递给客户端，并存放在浏览器中，用于识别唯一对应的Session对象。

   `JSESSIONID=[ 对应session对象的ID值 ]`

2. Session的获取

   将Session的唯一识别ID发送到客户端之后，本次会话中，客户端的每次请求都会将其加入响应头发送给服务器

   服务器使用`request`获取的Session对象，就是本次会话唯一的Session对象

###### 补充

- 客户端关闭，而服务器不关闭，两次获取的Session对象是同一个吗？

  默认情况下不同

  通过Session对象的原理可以，Session在服务器中创建，会有一个唯一的ID值，用于代表对应的Session对象，将其作为了Cookie发送给了客户端浏览器

  ```java
  Cookie cookie = new Cookie("JSESSIONID", session.getId());
  cookie.setMaxAge(60 * 60);// 设置最大存活时间为60秒
  response.addCookie(cookie,);
  ```

  **注意**，需要在Session对象创建的时候进行设置，否则根本无法确定是否获取的是同一个Session对象

- 客户端没有关闭，服务器进行了重启，两次获取的Session对象是同一个吗？

  不是同一个，服务器关闭之后，其中的资源都会被释放。

  虽然可以确定其中的Session对象会释放，即不能获取相同的对象或ID值，但是其中更加重要的是Session对象中存储的资源，不能让其丢失

  - Session钝化

    在服务器正常关闭之前，将Session对象序列化到磁盘上

  - Session活化

    在服务器启动后，将Session对象文件重新反序列化到内存中，转换为Session对象

  通过钝化和活化，虽然Session对象的地址值不同，但是其中的内容相同，包括Session对象的唯一ID值，因此仍然可以获得对应的值

  > IDEA不会自动实现钝化与活化的过程，需要原生Tomcat
  >
  > 在正常的关闭Tomcat服务器之后，Tomcat会自动将其中的Session对象序列化到`.ser`文件中。
  >
  > 之后重新启动Tomcat服务器，可以自动进行反序列化，同时将文件删除
  >
  > IDEA中，虽然同样可以实现钝化的过程，但是在服务器重新启动时，会将其中的work目录删除，而不是读取。在这样的过程中，Session的序列化对象被删除，也就无法实现活化的过程。

- 生命周期

  Session对象在第一次使用`request.getSessoin()`方法时被创建，知道被销毁。

  销毁的情况

  - 服务器关闭

  - 主动销毁

    获取Session对象之后，使用方法将其主动销毁

    - `invalidate()`

      Session对象的自我销毁

  - 默认失效时间

    Session在服务器中，有默认的自动销毁时间，为**30分钟**

    定时清理Session对象，以此节省服务器中的资源

    可以查看tomcat的文档中，`conf\web.xml`中的`<session-config>`标签下的`<session-timeout>`标签，定义了其自动清理的时间

###### 特点

1. Session用于存储一次会话中的多次请求中的数据，存放到服务器中
2. Session可以存储任意类型，任意大小的数据

##### 区别

1. Session存放在服务器端，Cookie存放在客户端
2. Session中存放的数据没有大小限制，Cookie有大小限制
3. Session的数据安全，Cookie中的数据相对不安全

#### Filter

Filter，过滤器，能够对每次的请求进行过滤，其中能够绝对本次请求的结果，如被拦截，或者被增强等功能

一般用于完成某些通用的验证，如登录验证，需要在登录之后才能对具体内容进行访问；统一编码进行处理；敏感字符过滤等

##### 使用步骤

1. 定义一个实现了Filter的类

   此处的Filter是`javax.servlet.Filter`类

2. 重写其中的方法

   - `init()` `param FilterConfig`

   - `doFilter()` `param ServletRequest, ServletResponse, FilterChain`

     其中提供了`request`和`response`对象，具体使用方法与之前相同

     `FilterChain`对象，其中提供了过滤器的各种操作，包括

     - `doFilter()` `param ServletRequest, ServletResponse`

       **放行方法**

   - `destroy()`

3. 配置拦截路径

   - 使用`web.xml`

     ```xml
     <!-- 配置filter文件与对应的filter名 -->
     <filter>
     	<filter-name>myFilter</filter-name>
         <filter-class>com.tms.test.FilterDemo</filter-class>
     </filter>
     <!-- 配置filter的对应地址 -->
     <filter-mapping>
     	<filter-name>myFilter</filter-name>
         <!-- 设置需要进行拦截的地址 -->
         <!-- 
     	全拦截		 /* 可以表示所有的地址访问都会经过过滤器
         具体拦截	/index.html 表示在访问某个具体的目录时会被调用
     	目录拦截	/user/* 访问/user目录下的所有资源都会被拦截
     	后缀拦截	*.html 所有访问特定后缀资源时，会被调用
     	-->
         <url-pattern>/*</url-pattern>
         <!-- <dispatcher> 指定拦截方法 -->
     </filter-mapping>
     ```

   - 使用注解

     `@WebFilter("[ 地址 ]")`

     - `value`或`urlPatterns`

       指定拦截地址

     - `dispatcher`

       指定需要拦截的方法

       - `REQUEST` 【默认】浏览器直接请求资源
       - `FORWARD` 请求转发进行访问
       - `INCLUDE` 包含访问资源
       - `ERROR` 错误跳转资源
       - `ASYNC` 异步访问资源

##### 生命周期

- 创建

  在服务器启动时，被创建

- 提供服务

  在生命周期内，每次被调用都会调用`doFilter()`方法

- 死亡

  在服务器正常关闭时被销毁

##### 过滤器链

多个过滤器对请求依次进行处理

###### 执行顺序

确定多个过滤器执行的先后顺序

- 使用注解配置的情况下，如果有多个Filter过滤器同时拦截到某个请求，会对其类名进行字符串的比较，值小的先执行

  如，AFilter和BFilter，一定是AFilter先执行

- 使用`web.xml`配置文件的情况下，`<filter-mapping>`在上者先执行

###### 拦截顺序

请求在经过多个过滤器时，经过的顺序和返回的顺序是相反的

#### Listener

Listener，监听器，与Servlet、Filter并称为web的三大组件

- 事件

  某个操作或事件

- 事件源

  事件的发生地点

- 监听器

  一个对象，包含具体的逻辑代码

- 注册监听

  将事件、事件源、监听器绑定在一起。当事件源上发生某个事件之后，调用监听器，执行其中的方法

##### 使用步骤

1. 定义一个类，实现监听器接口

2. 重写其中的方法

3. 对监听器进行配置

   - 使用`web.xml`配置

     ```xml
     <!-- 配置监听器文件 -->
     <listener>
     	<listener-class>com.tms.test.MyContextListener</listener-class>
     </listener>
     ```

   - 使用注解配置

     `@WebListener`指定当前类为监听器类

##### 监听器接口

- `ServletContextListener`

  主要用于ServletContext对象的创建和销毁

  - `contextInitialized()` `param ServletContextEvent`

    ServletContext被创建时调用此方法

    可以通过`ServletContextEvent`对象来获取`ServletContext`对象，用于加载资源

  - `contextDestroyed()` `param ServletContextEvent`

    ServletContext被销毁之前调用此方法

### redis

redis是一款高性能的NOSQL系列的非关系型数据库

> 关系/非关系数据库【简】
>
> 1. 关系数据库
>
>    关系数据库主要包括例如MySQL、Oracle等数据库
>
>    1. 数据之间有关联关系，通过表的方式进行存储
>    2. 数据存储在硬盘的文件上，需要借助IO的方式进行存取操作，效率较低
>
> 2. 非关系数据库
>
>    非关系数据库，NOSQL，主要包括redis、hbase等数据库
>
>    1. 数据之间没有关联关系，通过`Key-Value`键值对的方式进行存储
>    2. 数据存储在内存中，存取操作更加迅速

#### NOSQL

NoSQL，Not Only SQL，非关系数据库，意即**不仅仅是SQL**，是一项全新的数据库理念，泛指非关系型的数据库

随着互联网web2.0[^4]网站的兴起，传统的关系数据库在应付web2.0网站，特别是超大规模和高并发的SNS类型的web2.0纯动态网站已经显得力不从心，暴露了很多难以克服的问题，而非关系型的数据库则由于其本身的特点得到了非常迅速的发展。NoSQL数据库的产生就是为了解决大规模数据集合多重数据种类带来的挑战，尤其是大数据应用难题

##### 特点

非关系数据库的特点

###### 优点

1. 成本

   nosql数据库简单易部署，基本都是开源软件，不需要像使用oracle那样花费大量成本购买使用，相比关系型数据库价格便宜

2. 查询速度

   nosql数据库将数据存储于缓存之中，关系型数据库将数据存储在硬盘中，自然查询速度远不及nosql数据库

3. 存储数据的格式

   nosql的存储格式是key,value形式、文档形式、图片形式等等，所以可以存储基础类型以及对象或者是集合等各种格式，而数据库则只支持基础类型

4. 扩展性

   关系型数据库有类似join这样的多表查询机制的限制导致扩展很艰难。

###### 缺点

1. 维护的工具和资料有限，因为nosql是属于新的技术，不能和关系型数据库10几年的技术同日而语
2. 不提供对sql的支持，如果不支持sql这样的工业标准，将产生一定用户的学习和使用成本
3. 不提供关系型数据库对事务的处理

##### 比较

非关系数据库与关系数据库之间的直接比较

###### 非关系数据库的优势

1. 性能NOSQL是基于键值对的，可以想象成表中的主键和值的对应关系，而且不需要经过SQL层的解析，所以性能非常高
2. 可扩展性同样也是因为基于键值对，数据之间没有耦合性，所以非常容易水平扩展

###### 关系数据库的优势

1. 复杂查询可以用SQL语句方便的在一个表以及多个表之间做非常复杂的数据查询
2. 事务支持使得对于安全性能很高的数据访问要求得以实现。对于这两类数据库，对方的优势就是自己的弱势，反之亦然

##### 总结

根据其特点以及与关系数据库之间的比较

关系型数据库与NoSQL数据库并非对立而是**互补**的关系，即通常情况下使用关系型数据库，在适合使用NoSQL的时候使用NoSQL数据库

让NoSQL数据库对关系型数据库的不足进行弥补

<u>一般会将数据存储在关系型数据库中，在nosql数据库中备份存储关系型数据库的数据</u>

###### redis的应用场景

- 缓存（数据查询、短连接、新闻内容、商品内容等等）
- 聊天室的在线好友列表
- 任务队列。（秒杀、抢购、12306等等）
- 应用排行榜
- 网站访问统计
- 数据过期处理（可以精确到毫秒
- 分布式集群架构中的session分离

#### 文件

- `redis.windows.conf` 配置文件
- `redis-cli.exe` redis的客户端
- `redis-server.exe` redis的服务器端

#### 命令操作

##### 数据结构

redis中的数据都是采用键值对的方式进行存储，其中的Key都是字符串，value包括五种数据类型

1. 字符串类型 string

2. 哈希类型 hash

   类似Map的格式

3. 列表类型 list

   链表

4. 集合类型 set

   不允许重复，无序

5. 有序集合类型 sortedset

   不允许重复，有序

##### 数据操作

###### String

- 存储 `set key value`
- 获取 `get key`
- 删除 `del key`

###### Hash

- 存储 `hset key field value`

- 获取 

  - `hget key field` 获取某个字段
  - `hgetall myhash` 获取其中的所有字段

- 删除 `hel key feld`

  其中的`field`是指定的某个字段名

  ```
  // 存储某个hash对象，在其中存储两条字段，username和password
  hset myhash username test1
  hset myhash password 123
  
  // 获取myhash对象，分别获取其中的两个字段
  hget myhash username
  hget myhash password
  // 一次性获取myhash中所有字段的方法
  hgetall myhash
  // 显示的效果为，一行字段名，一行值的方式
  // "username"
  // "test1"
  // "password"
  // "123"
  
  // 删除myhash对象中的某个字段
  hdel myhash username
  ```

###### List

Redis列表是一个简单的字符串列表，按照插入顺序进行排序，可以<u>添加到元素头部或尾部</u>

- 存储
  - `lpush mylist value` 从头部（左）添加
  - `rpush mylist value` 从尾部（右）添加
- 获取
  - `lrang key start end` 范围获取（左开始）
    - 全部的值可以指定 `start=0, end=-1`
- 删除
  - `lpop key` 从头部（左）出栈，并返回值
  - `rpop key` 从尾部（右）出栈，并返回值

###### Set

- 存储 `sadd key value` 
  - 可以一次性传入多个值`value`
- 获取 `smenbers key` 获取所有元素
- 删除 `srem key` 删除某个元素

###### SoredSet

通过对每一个元素绑定一个double类型的浮点数，利用绑定的值，对其进行排序

- 存储

  -  `zadd key score value`

    在存储时，不止需要指定键值对，还需要指定对应的浮点数`score`，用于之后的排序

    如果时重复的值`value`，可以修改其对应的浮点数`score`

- 获取

  - `zrange key start end`

    获取范围内的值，默认使用升序排列（按照`score`值升序）

  - `zrange key start end withscores`

    在获取值的同时，获取对用的浮点数`score`

- 删除

  - `zrem key value`

###### 其他常见命令

- `keys *` 根据获取所有键值`key`
- `type key` 获取键值`key`对应的类型
- `del key` 删除指定的键值对

#### 持久化操作

redis是一个内存数据库，当redis服务器重启，其中的数据会丢失，为了保存其中的数据，将其持久的保存到文件中，这样的过程，就叫**持久化**

##### RDB

经过一定的间隔事件，检测key的变化情况，然后将其数据持久化

<u>redis的默认方式，可能出现数据丢失</u>

###### 使用方式

1. 编辑`redis.windows.conf`文件

   ```
   #   after 900 sec (15 min) if at least 1 key changed
   #   如果在900秒之内，有至少1个key发生了改变，就会进行持久化
   save 900 1
   #   after 300 sec (5 min) if at least 10 keys changed
   save 300 10
   #   after 60 sec if at least 10000 keys changed
   save 60 10000
   ```

2. 启动服务器，指定配置文件`redis.windows.conf`

3. 会生成持久化文件，后缀名为`.rdb`

##### AOF

日志记录的方式，可以记录每一条命令的操作。在每一次的操作之后，将其中的数据进行持久化

<u>不推荐，对性能影响较大</u>

###### 使用方式

1. 编辑`redis.windows.conf`文件

   ```
   #   默认为no不启动，yes为启动AOF的持久化机制
   appendonly no 
   
   
   # appendfsync always 默认关闭，每一次操作就会进行持久化
   appendfsync everysec 隔一秒钟进行持久化
   # appendfsync no 不进行持久化
   ```

2. 启动服务器，指定配置文件

3. 会生成持久化文件，后缀名为`.aof`

#### Jedis

Java操作redis数据库的工具

##### 使用步骤

1. 下载`jedis`的`jar`包
2. 使用
   1. 获取连接
      - 使用构造器，指定对应的`Host`和端口号`port`
   2. 操作数据
      - `set()` `param key, value`添加一条数据
   3. 关闭连接
      - `close()`断开连接

##### 具体方法

根据不同的数据结构进行存储

###### 构造器

- 空参构造

  默认设置`host=localhost`和`port=6379`

- `param host, port`

###### String

- `set()` `param key, value`

- `setex()` `param key, Integer, value`

  设置一个有过期时间的数据，`Integer`是过期的时间，单位为<u>秒</u>

- `get()` `param key` `return String`

###### Hash

- `hset()` `param key, field, value`
- `hget()` `param key, field` `return String`
- `hgetAll()` `param key` `return Map<String, String>`

###### List

- `lpush()/rpush()` `param key, ...value`

- `lrange()` `param key, start, end` `return List<String>`

  获取范围内的值，`start=0, end=-1`代表所有值

- `lpop()/rpop()` `param key` `return String`

   删除首/尾值，并获取值

###### Set

- `sadd()` `param key, ...value`
- `smenbers()` `param key` `return Set<String>`

###### SortedSet

- `zadd()` `param key, score, value`
  - 虽然不接受相同的key值，但是可以多次设置，用于修改`score`值，来修改其顺序
- `zrange()` `param key, start, end` `return Set<String>`

##### Jedis连接池

1. 创建`JedisPool`连接池对象

   - 无参构造器，使用默认构造器，其中使用的都是默认的设置

   - `param JedisPoolConfig, host, port`

     `Jedis`连接池的配置对象，其中包括大量的配置方法

     - `setMaxTotal()` `param Integer`

       设置最大连接数

     - `setMaxIdle()` `param Integer`

       设置最大的空闲连接

2. 调用方法`getResource()`方法获取`Jedis`连接

### 开发模式

借助一些比较科学的开发模式，可以让项目变得更加可读，让开发出的项目更加合理

##### MVC

MVC是一种开发的典范（或认为是开发模式），在JavaWeb项目中使用之后，让程序的开发更加合理。

MVC是几个单词的简略，M指的是Model，模型；V指的是View，视图；C指的是Controller，控制器。在程序设计时，将程序划分为了这三个部分

- 优
  - 耦合性低，方便维护，有益于分工合作
  - 重用性高
- 缺
  - 让项目的架构变得复杂，对开发人员的要求变高

##### Controller

控制器，之后在项目中主要通过Servlet来实现，主要用于以下功能

1. 获取客户端的输入
2. 调用Model模型
3. 将数据交给View视图进行展示

##### Model

模型，之后在项目中主要借助JavaBean来实现。最主要的功能是用于实现具体的业务逻辑操作，如，查询数据库、封装对象等

##### View

视图，主要用于展示数据，一般通过JSP来实现

##### 三层架构

三层架构是一种软件设计架构，与MVC类似，可以让程序变得更加合理

##### 界面层

界面层，或web层、表现层，包含用户将看到的界面，以及用户向服务器发送请求的媒介

##### 业务层

业务层，或service层、业务逻辑层，主要用于处理具体的业务逻辑，但不会处理最底层的实现

##### 持久层

持久层，或dao层、数据访问层，主要用于操作数据存储文件的各种操作

### 小实例

#### 验证码

验证码主要用于登录或类似防止恶意脚本攻击服务器，需要使用随机生成的验证码来尽可能减少这种可能性。

1. 图片创建

   `BufferedImage`对象，可以指定其中图片的宽高属性，还能指定图片的类型，如RGB图片（通过调用类中定义的常量）

   > 使用此处2D图片获取的到的画笔对象为`sun.java2d.SunGraphics2D`，继承自`Graphics2D`可以利用多态将其转换为`Graphics2D`进行操作。

2. 图片美化

   通过具体的方法实现图片的美化

   - `Graphics` 画笔类，抽象类，与实际使用中的画笔类似，能实现相同的功能。

   - `Graphcis2D` 画笔类的具体实现类，用于处理2D图片

     - `setColor()` `param Color`

       指定画笔的颜色，之后的所有操作颜色根据此处设置

     - `fillRect()` `param x, y, width, height` 

       填充方法，指定一定的范围进行图片的填充。

       `x，y`设定填充的起始坐标，指定填充矩形的左上角；`width, height`设定填充矩形的长宽。

     - `drawString()` `param 字符串, x, y`

       画笔方法，将字符串绘制到图片上

       `x, y`指定字符串出现的具体位置

     - `rotate()` `param theta, x, y`

       指定旋转的角度，以及使用`x, y`来指定旋转的中心，其中的值都为`double`型。

       **注意**，每次画笔旋转之后，最好使用相同的方法转回来，不然不好控制角度。

     - `drawLine()` `param x1, y1, x2, y2`

       绘制直线，通过`x1, y1`和`x2, y2`确定两点坐标

3. 在创建验证码时，将其中随机生成的验证码存储到Session中，用于之后的验证

4. 图片输出

   `ImageIO`存在静态方法`write`，可以指定传入IO流中的图片对象，还能指定图片的后缀名，还能指定绑定的输出流。

   > 图片被加载后会作为缓存存在浏览器中，之后的更新图片操作就会不再生效
   >
   > 1. 可以设置关闭浏览器缓存
   >
   >    ```java
   >    response.setHeader("Pragma","No-cache");
   >    response.setHeader("Cache-Control","no-cache");
   >    response.setDateHeader("Expires", 0);
   >    ```
   >
   > 2. 在请求地址中加入无用的数据
   >
   >    不同的请求地址，让浏览器将其作为一个全新的数据重新进行请求。如，此时可以使用地址之后加时间戳的方式来实现每次点击的请求都不相同。

###### 案例细节

1. 每一次的验证码应该只能作用一次

   防止登录成功之后，使用后退，返回到登陆界面，此时的验证码必定是已知的验证码。又出现了恶意攻击的可能性

   为了避免这种情况，需要在每次获取了Session中的验证码值之后，就将其删除

2. 验证的返回结果

   验证的返回结果可以通过request域来进行存储，默认情况下只会作用与同一个请求，正好可以用于返回验证的结果

#### 文件下载

因为浏览器的可以对部分资源进行解析，如果是可以解析的资源，浏览器会对其直接进行展示，但是这并不符合需求。

可以使用响应头中设置资源的打开方式

`content-disposition:attachment;filename=[ 文件名 ]`

因为需要进行响应头的设置，自然也不能再直接使用超链接直接指向文件的方式，需要借助Servlet对文件进行请求下载。

1. 获取文件名称

2. 使用字节流加载进入内存

3. 指定响应头

   此处需要指定两个响应头

   - `content-type` 

     用于指定响应体中传输的数据具体类型

     **注意**，此处所需求的文件类型不定，因此需要动态的确定其中的数据类型，就需要借助之前的`getMimeType()`方法，来获取MIME类型。

   - `content-disposition:attachment;filename=[ 文件名 ]`

     用于设置文件使用附件方式传输，而不需要进行解析

4. 将文件数据写入到响应体中

> 中文文件名错误
>
> 直接传入的中文文件名会错误的显示
>
> 需要根据不同的浏览器，采用不同的字符编码进行解析。

#### 记录上一次访问的时间

能够判断是否是第一次访问，如果是第一次进行访问，就能返回首次使用的提示信息；反之，如果不是第一次访问，就会进行再次使用的提示信息

分析需求可知，需要在用户没有进行登录操作的前提之下，就判断其登录的时间，即不进行后台的数据库的判断，就能实现对其登录记录的判断。因此，需要使用Cookie来进行完成。

> Cookie在Tomcat 8 之后，虽然对中文有了支持，但是对于特殊字符还是不支持
>
> 如，空格、换行等符号，都会出现问题。
>
> 此处因为涉及到了时间显示，因此其中可能会出现空格的使用，会报错。
>
> **可以使用URL编码的方式来解决**

###### URLEncoder

URLEncoder类，用于处理URL编码

- `encode()` `param str, 字符编码`

  输入需要编码的字符串，以及其中字符串对应的字符编码。

- `decode()` `param str, 字符编码`

  输入需要解码的字符串，以及其中字符串对应的字符编码。

#### 拦截违规操作

拦截用户不符合需求的操作，如并未登录的情况下，直接对服务器的资源进行访问；以及使用各种敏感词汇等

##### 登录拦截

- 如果没有登录直接访问服务器资源，会自动跳转到登录界面
- 如果没有登录，访问的是登录界面，就放行
- 如果已经登录，就允许访问服务器的其他资源

通过如上的分析，可以将其设定为如下逻辑

1. 判断访问的界面

   如果访问的直接就是登录界面，那就放行，允许访问

   如果访问的地址不是登录相关界面，就执行下方判断

2. 判断是否已经登录

   如果已经登录，放行，允许访问

   如果没有登录，将跳转到登陆界面

> **注意**
>
> 此处需要处理登录界面中的静态资源加载问题，如果没有设置允许对静态资源的访问， 会严重影响登录界面展示的效果，甚至造成最终无法登录

##### 敏感词汇拦截

对所有提交的数据进行拦截，将其中的数据进行处理

request中并不存在修改参数的方法，如果需要修改参数的值，只能重新获取request对象，并对`getParameter()`进行增强

对数据进行了修改之后，对数据放行

此处可以利用动态代理的方式，对`HttpServletRequest`中所有可以获取到参数值的方法进行增强，把其中获取到的值进行修改后返回

# 外部资料

- [W3school离线文档](..\..\document\W3school.CHM)
- [JavaEE 8_API文档](..\..\document\JavaEE API 8\index.html)  [JavaEE 7 离线文档](..\..\document\JavaEE7-api.chm) 

# 学习进度

黑马   p308





[^1]: 无需进行编译，可以直接进行执行的语言
[^2]: ECMA，欧洲计算机制造商协会，制定的一套标准，用于统一所有的客户端脚本语言
[^3]: 用于JavaScript的自动类型转换时，非纯数字的字符串如果希望转换为数字，就会变成NaN类型
[^4]: 主要代表动态的网站，以人为中心的页面