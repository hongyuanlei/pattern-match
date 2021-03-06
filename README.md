##模式匹配

###访问者模式

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

###分派

首先，我们先来介绍一个新的概念**[分派]**：

>根据对象的类型而对方法进行的选择，就是分派(Dispatch)，分派又分为两种，即静态分派和动态分派。


我们都知道类型有`静态类型`与`动态类型`，如果你还不知道，没有关系我在这里再讲一下：

```java
Human man = new Man();
```

在这段代码里我们创建了一个对象,`Human`是对象的`静态类型`，而`Man`是对象的`动态类型`或者`实际类型`。

####静态分派

**静态分派(Static Dispatch)**发生在编译时期，分派根据静态类型信息发生。Java使用**方法重载**实现静态分派。

再来看那个例子，在编译期编译器只知道man和women的静态类型，而不会知道它们的真实类型是什么，对于选择三个重载方法时发生了一次静态分派，所以到现在我想你应该已经明白了真相。

####动态分派

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

我们先看第一部分：

```java
 Father father = new Father();
 father.hardChoice(new IE());
```

father的静态类型是Father，真实类型也是Father,那么在编译阶段会根据方法接收者的静态类型和方法参数的静态类型决定Father.hardChoice(IE)，到了运行期方法的接收者Father会被替换成真实类型Father，所以依然会调用Father.hardChoice(IE)。

我们再看第二部分：
```java
 Father son = new Son();
 son.hardChoice(new Chrome());
```
father的静态类型是Father，真实类型也是Son,那么在编译阶段会根据方法接收者的静态类型和方法参数的静态类型决定Father.hardChoice(Chrome)，到了运行期方法的接收者Father会被替换成真实类型Son，所以依然会调用Son.hardChoice(Chrome)。

####单分派与多分派

>方法的接收者与方法的参数统称为方法的宗量。根据分派基于多少宗量，可以将分派划分为单分派和多分派两种。

上面的方法例子中，编译期会根据方法的调用者静态类型和方法参数的类型两个宗量来分派，所以Java是静态多分派语言。在运行期根据方法调用者的真实类型分派，所以Java是动态单分派语言。

####双重分派

>一个方法根据两个宗量的类型来决定执行不同的代码，这就是“双重分派”。

Java语言不支持动态多分派,所以不支持动态双分派。但是通过一些设计模式我们可以实现双重分派。

####访问者模式的结构

![](https://raw.githubusercontent.com/hongyuanlei/pattern-match/master/image/visitor-pattern-structure.jpeg)

绕了半天我们终于回到了访问者模式上了，我们再来看看访问者模式的定义吧：

>访问者模式的目的是封装一些施加于某种数据结构元素之上的操作。一旦这些操作需要修改的话，接受这个操作的数据结构则可以保持不变。

对照类图，实现Visitable接口的具体类对应到结构不变的数据，而实现Visitor接口的具体类对应到对数据的操作。

再来看看实现代码：


```java
package visitor;

public interface Visitable {
    void accept(Visitor visitor);
}
```

```java
package visitor;


public class ConcreteElementA implements Visitable{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operate01() {
        System.out.println("operate01 ConcreteElementA ...");
    }

    public void operate02() {
        System.out.println("operate02 ConcreteElementA ...");
    }
}
```

```java
package visitor;


public class ConcreteElementB implements Visitable{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operateA() {
        System.out.println("operateA ConcreteElementB ...");
    }

    public void operateB() {
        System.out.println("operateB ConcreteElementB ...");
    }
}

```

ConcreteElementA 与 ConcreteElementB 是两个具体的数据类，实现了Visitable接口就须承担可被访问者访问的责任。

```java
package visitor;

public interface Visitor {

    void visit(ConcreteElementA able);

    void visit(ConcreteElementB able);
}
```

```java
package visitor;

public class ConcreteVisitorA implements Visitor {

    @Override
    public void visit(ConcreteElementA able) {
        able.operate01();
    }

    @Override
    public void visit(ConcreteElementB able) {
        able.operateA();
    }
}
```

```java
package visitor;

public class ConcreteVisitorB implements Visitor {

    @Override
    public void visit(ConcreteElementA able) {
        able.operate02();
    }

    @Override
    public void visit(ConcreteElementB able) {
        able.operateB();
    }
}

```

ConcreteVisitorA 和 ConcreteVisitorB是两个具体的访问者，它们针对同样的数据制定了不同的操作。

```java
package visitor;

import java.util.ArrayList;
import java.util.List;

public class ObjectStructure {

    private List<Visitable> list = new ArrayList<>();

    public void add(Visitable visitable) {
        list.add(visitable);
    }

    public void action(Visitor visitor) {
        list.forEach(v -> v.accept(visitor));
    }
}
```

ObjectStructure有两个执责：

*   维护所有Visiable数据对象;
*   使用特定的Visitor执行所有的Visitable;

在访问者模式中它也并不是必须的。

```java
package visitor;

public class Client {

    public static void main(String[] args) {

        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.add(new ConcreteElementA());
        objectStructure.add(new ConcreteElementB());

        Visitor visitorA = new ConcreteVisitorA();
        objectStructure.action(visitorA);

        Visitor visitorB = new ConcreteVisitorB();
        objectStructure.action(visitorB);
    }
}

```
再来看看Client，在Client中我们创建了一个ObjectStructure，并向其添加了两个不同的数据对象，之后我们又创建了一个Visitor，并使用这个Visitor去访问所有的Visitable。

当我们执行这段代码都发生了些什么？

有两个比较重要的地方, 第一个是ObjectStructure的action方法，这里action方法会迭代所有的Visitable并使用传入的Visitor来访问它们自己。

```java
public void action(Visitor visitor) {
   list.forEach(v -> v.accept(visitor));
}

```
假设v是ConcreteElementA，visitor是ConcreteVisitorA：

在编译期，静态分派阶段，可以确定调Visitable.accept(Visitor)。

在运行期，动态分派阶段，会将Visitable替换成其真实类型ConcreteElementA，所以会调用ConcreteElementA.accept(Visitor)。

第二个是Visitable实现类中的accept方法。

```java
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
```
假设this是ConcreteElementA，Visitor是ConcreteVisitorA：

在编译期，静态分派阶段，就已经可以确定调用Visitor.visit(ConcreteElementA)。

在运行期，动态分派阶段，会将Visitor替换成其真实类型ConcreteVisitorA，所以会调用ConcreteVisitorA.visit(ConcreteElementA)。

####访问者模式的实际运用
![](https://raw.githubusercontent.com/hongyuanlei/pattern-match/master/image/visitor-pattern-dom4j.jpg)

####访问者模式实现模式匹配
![](https://raw.githubusercontent.com/hongyuanlei/pattern-match/master/image/visitor-pattern-matcher.jpg)

