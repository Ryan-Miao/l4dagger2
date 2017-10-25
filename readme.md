


[![](https://img.shields.io/badge/dagger-2.12-green.svg?style=flat)](https://github.com/google/dagger)
![](https://img.shields.io/badge/Java-1.8-orange.svg)
[![Build Status](https://travis-ci.org/Ryan-Miao/l4dagger2.svg?branch=master)](https://travis-ci.org/Ryan-Miao/l4dagger2)
[![codecov](https://codecov.io/gh/Ryan-Miao/l4dagger2/branch/master/graph/badge.svg)](https://codecov.io/gh/Ryan-Miao/l4dagger2)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/winsnow/l4dagger/blob/master/LICENSE)



![](http://oe20lp6p0.bkt.clouddn.com/blog/2017/dagger/demo-uml.svg)

# 前言
在为dropwizard选择DI框架的时候考虑了很久。Guice比较成熟，Dagger2主要用于Android。虽然都是google维护的，但Dagger2远比guice更新的频率高。再一个是，Dagger2不同于guice的运行时注入，编译时生成代码的做法很好。提前发现问题，更高的效率。

还是那句话，百度到的dagger2资料看着一大堆，大都表层，而且和Android集成很深。很少有单独讲Dagger2的。不得已，去看官方文档。

# HelloWorld
官方的example是基于maven的，由于maven天然结构的约定，compile的插件生成可以和maven集成的很好。而我更喜欢gradle，gradle随意很多，结果就是编译结构需要自己指定。


demo source： https://github.com/Ryan-Miao/l4dagger2

结构如下：

```
.
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── readme.md
├── settings.gradle
└── src
    └── main
        ├── java
        │   └── com
        │       └── test
        │           └── l4dagger2
        │               └── hello
        │                   ├── CoffeeApp.java
        │                   ├── CoffeeMaker.java
        │                   ├── DripCoffeeModule.java
        │                   ├── ElectricHeater.java
        │                   ├── Heater.java
        │                   ├── Pump.java
        │                   ├── PumpModule.java
        │                   └── Thermosiphon.java
        ├── resources
        └── webapp

11 directories, 15 files

```

## 加载依赖

build.gradle

```java
plugins {
    id "net.ltgt.apt" version "0.12"
    id "net.ltgt.apt-idea" version "0.12"
    id "net.ltgt.apt-eclipse" version "0.12"
}


repositories {
    mavenLocal()
    maven {
        url "http://maven.aliyun.com/nexus/content/groups/public/"
    }
    mavenCentral()
}

group 'com.test'
version '1.0-SNAPSHOT'

apply plugin: 'java'
apply plugin: 'war'
apply plugin: 'idea'

sourceCompatibility = 1.8


dependencies {
    compile 'com.google.dagger:dagger:2.12'
    apt 'com.google.dagger:dagger-compiler:2.12'

    testCompile group: 'junit', name: 'junit', version: '4.12'
}

```
**Note that**：
- `plugins`插件需要放到最开头。然后，由于设计编译时生成sourceSet类，针对IDE需要添加对应的插件。
- `dagger2`生成的类放在`build/generated/source/apt/main`




## Coding Time
接下来的内容就和官方的demo一样了。

com.test.l4dagger2.hello.CoffeeApp
```java
public class CoffeeApp {
    @Singleton
    @Component(modules = { DripCoffeeModule.class })
    public interface CoffeeShop {
        CoffeeMaker maker();
    }

    public static void main(String[] args) {
        CoffeeShop coffeeShop = DaggerCoffeeApp_CoffeeShop.builder().build();
        coffeeShop.maker().brew();
    }
}

```

com.test.l4dagger2.hello.DripCoffeeModule
```java
@Module(includes = PumpModule.class)
class DripCoffeeModule {
    @Provides
    @Singleton
    Heater provideHeater() {
        return new ElectricHeater();
    }
}
```

com.test.l4dagger2.hello.PumpModule

```
@Module
abstract class PumpModule {
    @Binds
    abstract Pump providePump(Thermosiphon pump);
}
```
com.test.l4dagger2.hello.Pump
```java
interface Pump {
    void pump();
}
```
com.test.l4dagger2.hello.Thermosiphon
```java
class Thermosiphon implements Pump {
    private final Heater heater;

    @Inject
    Thermosiphon(Heater heater) {
        this.heater = heater;
    }

    @Override public void pump() {
        if (heater.isHot()) {
            System.out.println("=> => pumping => =>");
        }
    }
}

```
com.test.l4dagger2.hello.Heater
```java
interface Heater {
    void on();
    void off();
    boolean isHot();
}
```
com.test.l4dagger2.hello.ElectricHeater
```java
class ElectricHeater implements Heater {
    boolean heating;

    @Override public void on() {
        System.out.println("~ ~ ~ heating ~ ~ ~");
        this.heating = true;
    }

    @Override public void off() {
        this.heating = false;
    }

    @Override public boolean isHot() {
        return heating;
    }
}
```
com.test.l4dagger2.hello.CoffeeMaker
```java

class CoffeeMaker {
    private final Lazy<Heater> heater; // Create a possibly costly heater only when we use it.
    private final Pump pump;

    @Inject
    CoffeeMaker(Lazy<Heater> heater, Pump pump) {
        this.heater = heater;
        this.pump = pump;
    }

    public void brew() {
        heater.get().on();
        pump.pump();
        System.out.println(" [_]P coffee! [_]P ");
        heater.get().off();
    }
}
```


针对`DaggerCoffeeApp_CoffeeShop`不识别问题，运行编译后就可以了。

```
sh gradlew build
```

## 结果
Run main method
```
~ ~ ~ heating ~ ~ ~
=> => pumping => =>
 [_]P coffee! [_]P 

```


## 用法分析

Dagger暴露的最外层为component，而Component的注入来自module。Component之间不能互相注入，module之间可以互相注入。
![](http://oe20lp6p0.bkt.clouddn.com/blog/2017/dagger/dagger%E8%B0%83%E7%94%A8%E5%B1%82%E6%AC%A1.svg)


### 注入原理
编译时扫描注解，生成对应的builder和factory。这点和spring不同，spring是运行时通过反射生成instance。另一个问题就是由于是静态工厂，那么就不能动态绑定了。不过可以通过其他的手段弥补。

以下来自[详解Dagger2](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0519/2892.html)

- @Inject: 通常在需要依赖的地方使用这个注解。换句话说，你用它告诉Dagger这个类或者字段需要依赖注入。这样，Dagger就会构造一个这个类的实例并满足他们的依赖。
- @Module: Modules类里面的方法专门提供依赖，所以我们定义一个类，用@Module注解，这样Dagger在构造类的实例的时候，就知道从哪里去找到需要的 依赖。modules的一个重要特征是它们设计为分区并组合在一起（比如说，在我们的app中可以有多个组成在一起的modules）。
- @Provide: 在modules中，我们定义的方法是用这个注解，以此来告诉Dagger我们想要构造对象并提供这些依赖。
@Component: Components从根本上来说就是一个注入器，也可以说是@Inject和@Module的桥梁，它的主要作用就是连接这两个部分。 
- Components可以提供所有定义了的类型的实例，比如：我们必须用@Component注解一个接口然后列出所有的@Modules组成该组件，如 果缺失了任何一块都会在编译的时候报错。所有的组件都可以通过它的modules知道依赖的范围。
- @Scope: Scopes可是非常的有用，Dagger2可以通过自定义注解限定注解作用域。后面会演示一个例子，这是一个非常强大的特点，因为就如前面说的一样，没 必要让每个对象都去了解如何管理他们的实例。在scope的例子中，我们用自定义的@PerActivity注解一个类，所以这个对象存活时间就和 activity的一样。简单来说就是我们可以定义所有范围的粒度(@PerFragment, @PerUser, 等等)。
- Qualifier: 当类的类型不足以鉴别一个依赖的时候，我们就可以使用这个注解标示。例如：在Android中，我们会需要不同类型的context，所以我们就可以定义 qualifier注解“@ForApplication”和“@ForActivity”，这样当注入一个context的时候，我们就可以告诉 Dagger我们想要哪种类型的context。



### 1. 入口
```java
@Singleton
@Component(modules = { DripCoffeeModule.class })
public interface CoffeeShop {
    CoffeeMaker maker();
}
```
dagger中Component就是最顶级的入口，dagger为之生成了工厂类`DaggerCoffeeApp_CoffeeShop`, 目标是构建`CoffeeMaker`， 在`CoffeeMaker`中使用了`Injection`，那么依赖要由工厂类来提供。工厂类是根据`modules`的参数来找依赖绑定的。

本例中，指向了`DripCoffeeModule`，意思是`CoffeeMaker`的依赖要从这个module里找。



### 2. 依赖管理
module看起来似乎和spring里的configuration有点相似，负责声明bean。**而且同样支持继承，子module拥有父亲的元素**。 这点和spring的context也很像，子context可以从父context里获取instance。对应的Java里的继承也同样，子类可以使用父类的属性和方法。


这里可以把`DripCoffeeModule`当做父类，而`PumpModule`为子类。
![](http://oe20lp6p0.bkt.clouddn.com/blog/2017/dagger/module-extend.svg)

但是， **引用注入的时候却和spring相反** ！

在spring里，子context拥有所有的bean，所以在子context里可以注入任何bean。而父context只能注入自己声明的bean。

而在dagger2的这个module里，module可以看做是一个打包。最外层的包显然包含了所有的bean。因此，在`CoffeeShop`中引入的是父module `DripCoffeeModule`。在子module `PumpModule`中的`Thermosiphon`可以注入声明在`DripCoffeeModule`里的`Heater`实例。

当然，造成这个问题的原因是生成的时候的顺序有关。调整下顺序，把`PumpModule`引入Component里，然后，把`DripCoffeeModule` include到`PumpModule`里。此时一样没啥问题，只是掉了个。不同的是，父子对调导致Pump变成了父亲的元素，Heater成了子类的元素。然而，一样可以将heater注入到Pump。为啥？等看了源码再了解，这里先搞定用法scop。*猜测会不会是在创建Pump的时候发现缺少Heater，然后压栈，去子module里找声明，找到后，弹出栈*。


Anyway，注入demo的注入就是这么简单。module起到定义bean的范围的作用，module之间只要连接就是互通的，可以相互注入。



## 参考
- http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0519/2892.html













