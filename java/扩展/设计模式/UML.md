# UML

UML，Unified Modeling Language，统一建模语言，是一种用于软件系统分析和设计的语言工具。可以帮助软件开发人员进行思考和记录思路的结果

UML 本身是一套符号的规定，就像是数学符号和化学符号一样，这些符号用于描述软件模型中的各个元素和它们之间的关系，比如类、接口、实现、泛化、依赖、组合、聚合等

## UML 分类

UML 图中包括各种分类

1. 用例图（use case）
2. 静态结构图：类图、对象图、包图、组件图、部署图
3. 动态行为图：交互图（时序图和协作图）、状态图、活动图

其中，类图是用于描述类与类之间的关系的，是 UML 图的核心

## UML 类图

UML 类图，是用于<u>描述系统中类本身的组成和类之间的各种**静态关系**</u>

类之间的关系经过总结，主要包括如下的关系：

1. **依赖**
2. **泛化/继承**
3. **实现**
4. **关联**
5. **聚合**
6. **组合**

这些关系在 UML 类图中的具体表示方式

![UML-类图-关联](img/UML-%E7%B1%BB%E5%9B%BE-%E5%85%B3%E8%81%94.png)

### 关系详解

此处将对 UML 类图中所涉及到的关系进行更加详细的介绍

#### 依赖

**一个类依赖于另一个类的定义**，即类与类之间的连接

一般来说，依赖关系在 Java 语言中体现为<u>成员变量、局部变量、方法形参、方法返回值、局部变量或者是静态方法的调用</u>

#### 泛化

泛化关系是**继承关系**，指的是<u>一个类（称为子类、子接口）继承另外一个类（称为父类、父接口）的功能，并可以增加自己额外的功能</u>

在 Java 中通过关键字 `extends` 明确标识

==泛化可以理解为<u>依赖关系</u>的特例==

#### 实现

实现关系指的是**类为某个抽象的方法实现具体的功能**

<u>一个 `class` 类实现 `interface` 接口（可以实现多个接口）的功能</u>

在 Java 中此关系通过关键字 `implements` 明确标识

==实现可以立即为<u>依赖关系</u>的特例==

#### 关联

关联关系指的是**类与类之间的连接，通过<u>成员变量</u>的方式，实现了类与类之间的属性和方法的调用**

- 关联可以是双向的，也可以是单向的
- 两个类是一个层次，并不存在部分与整体之间的关系

==实现可以立即为<u>依赖关系</u>的特例==

#### 聚合

聚合关系表示的是**较强的关联关系，聚合是整体与个体之间的关系，即 `has-a` 关系**。整体和部分可以分开，起具有关联的导航性和多重性

如，一台电脑由键盘、显示器、鼠标等组成，而组成电脑的各个配件可以从电脑上分离出来

![聚合关系图](http://www.plantuml.com/plantuml/png/SoWkIImgAStDuKhEIImkLd3EpoqeBKajKgZcKiZDBorEjLBm1L4WNbucK0B8Xp2uQhd0CWgwklbmRLnGILH1XzIy5A0L0000)

==聚合关系是<u>关联关系</u>的特例==

#### 组合

组合关系表达的是整体与部分的关系，但是整体和部分不可以分开

**其是在<u>聚合的基础</u>上，为了强调其中的个体和整体不可分离，而进一步强化的概念**

在 Java 代码中的体现为，其作为属性的同时，直接创建对象

如，一个人，必然会有一个头，可能有身份证。这样的例子中，人头必然组成人，但是身份证仅仅是聚合关系

![组合关系图](http://www.plantuml.com/plantuml/png/SoWkIImgAStDuKhEIImkLWX8BIhEprEevbAeJijCpKcfLV18JKnHo00HjWfvgULWleOcbq9cYdDYKOgLWgSBYEQgvShBBqbLACfCpoZXUhfkrfETdKzsD3pPiUh9hbtFPxKytxYRwsdhjYSxraBbThKzsR44AFrquxnd3SiXjZoTr0Cr3geM8lnYuOhdItO1pNcwPClxFJrFTlG1sKzsz3xjN_-qe-tvijtlzypc0kk3gNsnQ_MJtNjVh6y4AWUgUh9xAKGJH3bNQbwA0iYrN5nWytLrjQ3-XfW24ic7YJW05GE31m00)

==组合关系是<u>聚合关系</u>的特例==



