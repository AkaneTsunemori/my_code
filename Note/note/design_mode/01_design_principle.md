-   开闭原则：一个软件实体如类、模块和函数应该 **对修改封闭，对扩展开放**。
-   单一职责原则：一个类只做一件事，一个类应该 **只有一个引起它修改的原因**。
-   里氏替换原则：**子类应该可以完全替换父类**。也就是说在使用继承时，只扩展新功能，而不要破坏父类原有的功能。
-   依赖倒置原则：**细节应该依赖于抽象**，抽象不应依赖于细节。**把抽象层放在程序设计的高层，并保持稳定**，  
    程序的 **细节变化由低层的实现层** 来完成。
-   迪米特法则：又名「最少知道原则」，**一个类不应知道自己操作的类(成员变量)的细节**，换言之，只和朋友谈话，不和朋友的朋友谈话。
-   接口隔离原则：**客户端不应依赖它不需要的接口**。如果一个接口在实现时，部分方法由于冗余被客户端空实现，  
    则应该将接口拆分，让 实现类只需依赖自己需要的接口方法。
    

对设计原则的个人理解:

**开闭原则**和**依赖倒置原则**: 都体现在对顶层接口的设计上, 要做到足够抽象, 具体修改或者扩展需要通过实现类或继承的接口定义额外的功能

单一职责原则:对于类的定义要考虑他的职责范围,不属于该类管理的功能,坚决不要进行实现,否则会导致程序语义上的困扰,导致代码难以维护

里氏替换原则: 很好理解,子类作为父类的扩展必然是拥有父类的方法实现的,所以自然可以替换, 在代码中也经常用一个接口类接受一个具体类的对象

最少知道原则: 和单一职责原则的优点一致,也是为了代码可读性,使用的成员变量的实现细节封装的越多越好, 便于从整体上把握整个代码逻辑

接口隔离原则: 体现在顶层的接口设计上,接口或者功能的拆分,具体实现类应该具有怎样的特质
-   个人理解如果冗余的方法也可以被某个父类空实现然后由子类具体实现,这个体现了一种模版方式,也可以增加框架的扩展性