##模式匹配

####访问者模式

最近学习Scala的模式匹配，看了一些资料就看到了原来访问者模式和模式匹配有很密切的关系，所以打算先看看什么是访问者模式。我想很多人可能和我一样写了几年代码但是却从来没有使用过访问者模式，或者都没有听说过这个设计模式，不过没有关系，因为它真的很少会使用到。

我们先看一下访问者模式的定义：

>访问者模式的目的是封装一些施加于某种数据结构元素之上的操作。一旦这些操作需要修改的话，接受这个操作的数据结构则可以保持不变。

也许你会听不太懂是什么意思，不要着急我们先看一个例子：

```java
package dispatch;

public class StaticDispatch {

    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Women extends Human {

    }

    public void sayHello(Human guy) {
        System.out.println("Hello, guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("Hello, gentleman!");
    }

    public void sayHello(Women guy) {
        System.out.println("Hello, lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();

        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(women);
    }
}

```

你觉得会打印出什么呢？是否会有些不能确定？其实如果你使用InteiJ IDEA打开这段代码，即使不用运行这段代码，IDE都能告诉你结果是什么。

有些聪明的小伙伴，见到我这么问他们，都会猜到会打印出:

```
Hello, guy!
Hello, guy!
```
 
要不然我不会拿出来问，小伙伴们确实是很聪明，他们猜对了。但是呢，我还是会告诉你们为什么会是样子。

首先，我们先来介绍一个新的概念**[分派]**：

>根据对象的类型而对方法进行的选择，就是分派(Dispatch)，分派又分为两种，即静态分派和动态分派。


我们都知道类型有`静态类型`与`动态类型`，如果你还不知道，没有关系我在这里再讲一下：

```java
Human man = new Man();
```

在这段代码里我们创建了一个对象,`Human`是对象的`静态类型`，而`Man`是对象的`动态类型`或者`实际类型`。

**静态分派(Static Dispatch)**发生在编译时期，分派根据静态类型信息发生。Java使用**方法重载**实现静态分派。

再会出看那个例子，在编译期编译器只知道man和women的静态类型，而不会知道它们的真实类型是什么，对于选择三个重载方法时发生了一次静态分派，所以到现在我想你应该已经明白了真相。

那我们再看看动态分派。

**动态分派(Dynamic Dispatch)**发生在运行时期，动态分派动态地置换掉某个方法。Java使用**方法重写**实现静态分派。

我们再看一个例子吧。

```java
package dispatch;

public class DynamicDispatch {

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {

        @Override
        protected void sayHello() {
            System.out.println("Man say hello!");
        }
    }

    static class Woman extends Human {

        @Override
        protected void sayHello() {
            System.out.println("Woman say hello!");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Woman();

        man.sayHello();
        women.sayHello();

        man = new Woman();
        man.sayHello();
    }
}

```
我想这个例子没有难度，大多数人想都不用想都已经知道答案，因为我们平时一直在使用。方法调用发生在运行时期，它使用对象的真实类型。

这里还有一个例子，我们来做一个小结：

```java
package dispatch;

public class Dispatch {

    static class IE {

    }

    static class Chrome {

    }

    public static class Father {

        public void hardChoice(IE browser) {
            System.out.println("Father choose IE");
        }

        public void hardChoice(Chrome browser) {
            System.out.println("Father choose Chrome");
        }
    }

    public static class Son extends Father {

        @Override
        public void hardChoice(IE browser) {
            System.out.println("Son choose IE");
        }

        @Override
        public void hardChoice(Chrome browser) {
            System.out.println("Son choose Chrome");
        }
    }


    public static void main(String[] args) {
        Father father = new Father();
        father.hardChoice(new IE());

        Father son = new Son();
        son.hardChoice(new Chrome());

    }
}
```







