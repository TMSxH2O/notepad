## 工厂模式

工厂设计模式，顾名思义，就是用来生产对象的，在java中，万物皆对象，这些对象都需要创建，如果创建的时候直接new该对象，就会对该对象耦合严重，假如我们要更换对象，所有new对象的地方都需要修改一遍，这显然违背了软件设计的开闭原则

如果我们使用工厂来生产对象，我们就只和工厂打交道就可以了，彻底和对象解耦，如果要更换对象，直接在工厂里更换该对象即可，达到了与对象解耦的目的；所以说，工厂模式最大的优点就是：**解耦**

> 作者：星星_点灯
> 链接：https://www.jianshu.com/p/38493eb4ffbd
> 来源：简书
> 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

对其进行划分可以分为一下三类

1. 简单工厂
2. 工厂方法
3. 抽象工厂

### 简单工厂

定义一个工厂方法，依据传入的参数，生成对应的产品对象

先将产品类抽象出来，比如，苹果和梨都属于水果，抽象出来一个水果类Fruit，苹果和梨就是具体的产品类，然后创建一个水果工厂，分别用来创建苹果和梨；代码如下：

```Java
// 水果接口
public interface Fruit{
    void getName();
}

// 苹果类 实现类
public class Apple implements Fruit{
	@Override
    public void getName(){
        // 苹果
    }
}

// 梨子类 实现类
public class Pear implements Fruit {
	@Override
    public void getName(){
        // 梨子
    }
}

// 工厂类
public class FruitFactory {
	public Fruit createFruit(String type){
        if("apple".equals(type)){
            return new Apple();
        } else if ("pear".equals(type)){
            return new Pear();
        }
        return null;
    }
}

// 测试方法
public Class SimpleFactoryTest{
    public static void main(String[] args){
        FruitFactory factory = new FruitFactory();
        Apple apple = (Apple) factory.createFruit("apple");
        Pear pear = (Pear) factory.createFruit("pear");
    }
}
```

按照如上流程，就算是实现了简答的工厂设计模式，但是其中存在十分严重的问题

如果之后再添加新的水果，依然需要修改工厂类，违反了开闭原则

### 工厂方法

将工厂提取成一个接口或抽象类，具体生产什么产品由子类决定

与简单工厂相同，将产品类抽象出来，并且本次需要将工厂类也一并抽取出来，将其中具体会生产的产品交给子类来决定。依然使用上方的例子，代码如下

```java
// 工厂接口
public interface FruitFactory {
	// 生产水果的方法
    Fruit createFruit();
}

// 苹果工厂实现类
public class AppleFactory implements FruitFactory(){
    @Override
    public Fruit createFruit(){
        return new Apple();
    }
}

// 梨子工厂实现类
public class PearFactory implements FruitFactory() {
	@Override
    public Fruit createFruit(){
        return new Pear();
    }
}

// 测试方法
public class FactoryTest{
    public static void main(String[] args){
        FruitFactory appleFactory = new AppleFactory();
        FruitFactory pearFactory = new PearFactory();
        Apple apple = (Apple) factory.createFruit("apple");
        Pear pear = (Pear) factory.createFruit("pear");
    }
}
```

通过这样的方法，实现了工厂模式，实现了解耦，也遵循了开闭原则，但是根本问题还是没有解决，在之后需求的变化中，需要创建非常多的工厂

### 抽象工厂

为创建一组相关或者是相互依赖的对象提供的一个接口，而不需要指定它们的具体类

抽象工厂和工厂方法的模式基本一样，区别在于，工厂方法是生产一个具体的产品，而抽象工厂可以用来生产一组相同，有相对关系的产品；重点在于一组，一批，一系列；举个例子，假如生产小米手机，小米手机有很多系列，小米note、红米note等；假如小米note生产需要的配件有825的处理器，6英寸屏幕，而红米只需要650的处理器和5寸的屏幕就可以了；用抽象工厂来实现：

```java
// CPU接口和实现类
public interface CPU{
    void run();
    class CPU650 implements CPU{
        @Override
        public void run(){
            ...
        }
    }
    class CPU825 implements CPU{
        @Override
        public void run(){
            ...
        }
    }
}

// 屏幕接口和实现类
public interface Screen {
    void size();
    class Screen5 implements Screen {
        @Override
        public void size(){
            ...
        }
    }
    class Screen6 implements Screen {
        @Override
        public void size(){
            ...
        }
    }
}

// 工厂接口
public interface PhoneFactory {
    CPU getCPU();
    Screen getScreen();
}

// 实现类，小米工厂
public class XiaoMiFactory implements PhoneFactory {
    @Override
    public CPU getCPU(){
        return new CPU.CPU825();
    }
    @Override
    public Screen getScreen(){
        return new Screen.Screen6();
    }
}

// 实现类，红米工厂
public class HongMiFactory implements PhoneFactory {
    @Override
    public CPU getCPU(){
        return new CPU.CPU650();
    }
    @Override
    public Screen getScreen(){
        return new Screen.Screen5();
    }
}
```

以上例子可以看出，抽象工厂可以解决一系列的产品生产的需求，对于大批量，多系列的产品，用抽象工厂可以更好的管理和扩展

### 总结

1. 对于简单工厂和工厂方法来说，两者的使用方式实际上是一样的，如果对于产品的分类和名称是确定的，数量是相对固定的，推荐使用简单工厂模式
2. 抽象工厂用来解决相对复杂的问题，适用于一系列、大批量的对象生产