目录
====
* [java 内存区域与内存溢出异常](#java-内存区域与内存溢出异常)
    * [写在前面](#写在前面)
        * [常见面试题](#常见面试题)
    * [一 概述](#概述)
    * [二 运行时数据区域](#运行时数据区域)
        * [2.1 程序计数器](#程序计数器)
        * [2.2 Java 虚拟机栈](#Java-虚拟机栈)
        * [2.3 本地方法栈](#本地方法栈)
        * [2.4 Java 堆](#Java-堆)
        * [2.5 方法区](#方法区)
        * [2.6 运行时常量池](#运行时常量池)
        * [2.7 直接内存](#直接内存)
    * [三 hotspot 虚拟机对象探秘](#hotspot-虚拟机对象探秘)
        * [3.1 对象的创建](#对象的创建)
            * [3.2.1 类加载检查](#类加载检查)
            * [3.2.2 分配内存](#分配内存)
            * [3.2.3 初始化零值](#初始化零值)
            * [3.2.4 设置对象头](#设置对象头)
            * [3.2.5 执行 init 方法](#执行-init-方法)
        * [3.2 对象的内存布局](#对象的内存布局)
        * [3.3 对象的访问定位](#对象的访问定位)
            * [3.3.1 句柄](#句柄)
            * [3.3.2 指针](#指针)
            * [3.3.3 区别](#区别)
        * [3.4 OutOfMemoryError异常测试]()
            * [3.4.1 java 堆溢出](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/jvm/demo1)
            * [3.4.2 java 栈溢出](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/jvm/demo2)

# java 内存区域与内存溢出异常

## 写在前面

### 常见面试题
* 介绍下 Java 内存区域
* Java 对象创建过程（5 步）
* 对象访问定问的方式（2 种）

## 概述
对于 Java 程序员来说，在虚拟机自动内存管理机制下，不再需要像 C/C++程序开发程序员这样为内一个 new 操作去写对应的 delete/free 操作，不容易出现内存泄漏和内存溢出问题。正是因为 Java 程序员把内存控制权利交给 Java 虚拟机，一旦出现内存泄漏和溢出方面的问题，如果不了解虚拟机是怎样使用内存的，那么排查错误将会是一个非常艰巨的任务。

## 运行时数据区域
java 虚拟机所管理的内存包含以下几个运行时数据区域：
<div align="center">  
<img src="https://raw.githubusercontent.com/laofeijunfeng/demo/master/images/jvm/20190513155216.png" width="600px"/>
</div>

### 程序计数器
线程私有

程序计数器是一块较小的空间，可看作是当前线程锁执行的字节码的行号指示器。字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令，分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖这个计数器来完成。

每个线程都拥有各自的程序计数器，来使得虚拟机的多线程轮流切换时互不干扰。

如果执行 java 方法时，计数器记录的是正在执行虚拟机字节码指令的地址，如果是 Native 方法，则为空。

注意：此内存区域是唯一一个在 java 虚拟机规范中没有规定任何 OutOfMemoryError 的内存区域。

### Java 虚拟机栈
线程私有

虚拟机栈描述的是 java 方法执行的内存模型：每个方法在执行时会创建一个栈帧用于存储局部变量表、操作数栈、动态链接、方法出口等信息。每个方法从调用到执行完成就对应一个栈帧入栈到出栈的过程。

一般人们把 java 内存分为堆内存和栈内存，其中栈就是指虚拟机栈。

局部变量表主要存放了编译器可知的各种数据类型（boolean、byte、char、short、int、float、long、double）、对象引用（reference 类型，它不同于对象本身，可能是一个指向对象起始地址的引用指针，也可能是指向一个代表对象的句柄或其他与此对象相关的位置）和 returnAddress 类型（指向了一条字节码指令的地址）。

**虚拟机栈会出现两种异常：**
* StackOverFlowError：线程请求的栈深度大于虚拟机所允许的深度。
* OutOfMemoryError：如果虚拟机栈可以动态扩展，而扩展时无法申请到足够的内存。

### 本地方法栈
和虚拟机栈相似，区别是虚拟机栈为 Java 方法服务，而本地方法栈为 Native 方法服务。

### Java 堆
堆是 Java 虚拟机所管理内存中最大的一块，几乎所有的对象实例和数组都在这里分配。

堆是垃圾收集器管理的主要区域，**从垃圾回收的角度**，由于现在收集器基本都采用分代垃圾收集算法，所以 Java 堆还可以细分为：新生代和老年代：再细致一点有：Eden 空间、From Survivor、To Survivor 空间等。**进一步划分的目的是更好地回收内存，或者更快地分配内存**。
**从内存分配的角度看**，线程共享的 Java 堆中可能划分多个线程私有的分配缓冲区（TLAB）。

堆可以处于物理上不连续，逻辑上连续即可。

### 方法区
和堆一样，也是线程共享的区域。用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。

垃圾收集器在这个区域回收的目标主要是针对常量池的回收和对类型的卸载，是比较少出现的。

方法区无法满足内存分配需求时，则会抛出 OutOfMemoryError 异常。

### 运行时常量池
运行时常量池是方法区的一部分。用于存放编译期生成的各种字面量和符号引用。

<div align="center">  
<img src="https://raw.githubusercontent.com/laofeijunfeng/demo/master/images/jvm/20190513171215.png" width="600px"/>
</div>
—— 图片来源：https://blog.csdn.net/wangbiao007/article/details/78545189

### 直接内存
直接内存并不是虚拟机运行时数据区的一部分，也不是虚拟机规范中定义的内存区域，但是这部分内存也被频繁地使用。而且也可能导致 OutOfMemoryError 异常出现。

JDK1.4 中新加入的 NIO(New Input/Output) 类，引入了一种基于通道（Channel） 与缓存区（Buffer） 的 I/O 方式，它可以直接使用 Native 函数库直接分配堆外内存，然后通过一个存储在 Java 堆中的 DirectByteBuffer 对象作为这块内存的引用进行操作。这样就能在一些场景中显著提高性能，因为避免了在 Java 堆和 Native 堆之间来回复制数据。

本机直接内存的分配不会收到 Java 堆的限制，但是，既然是内存就会受到本机总内存大小以及处理器寻址空间的限制。

## hotspot 虚拟机对象探秘

### 对象的创建
对象创建过程：
<div align="center">
<img src="https://raw.githubusercontent.com/laofeijunfeng/demo/master/images/jvm/20190513175025.jpg" width="600px"/>
</div>

#### 类加载检查
虚拟机遇到一条 new 指令时，首先检查这个指令的参数是否能在常量池中定位到一个类的符号引用，并且检查这个符号引用代表的类是否已被加载、解析和初始化过。如果没有，则需先执行相应的类加载过程。

#### 分配内存
加载检查后，虚拟机将为新生对象分配内存。分配的方式有“指针碰撞”和“空闲列表”两种，选择哪一种分配方式由 Java 堆是否规整决定，而 Java 堆是否规整则由垃圾收集器是否带有压缩整理功能决定。
<div align="center">
<img src="https://raw.githubusercontent.com/laofeijunfeng/demo/master/images/jvm/20190514100730.png" width="600px"/>
</div>

对象在虚拟机中创建非常频繁，在并发的情况下也不是线程安全的，此情况的解决方案有两种：
* CAS + 失败重试：对内存分配的动作进行同步处理，保证更新的原子性；
* TLAB：把内存分配的动作按照线程划分在不同的空间中进行，即在 java 堆中预先分配一小块内存，称为本地线程分配缓冲（TLAB），各个线程在各自的 TLAB 上分配，用完再分配新的，这时才需同步锁定。可通过 -XX:+/-UseTLAB 来设定是否使用 TLAB；

#### 初始化零值
这一步操作保证对象的实例字段在 Java 代码中可以不赋值就直接使用，程序能访问到这些字段的数据类型所对应的零值。如果使用 TLAB，这一过程可以提前到 TLAB 分配时进行。

#### 设置对象头
初始化零值完成之后，虚拟机要对对象进行必要的设置，例如这个对象是那个类的实例、如何才能找到类的元数据信息、对象的哈希吗、对象的 GC 分代年龄等信息。 这些信息存放在对象头中。 另外，根据虚拟机当前运行状态的不同，如是否启用偏向锁等，对象头会有不同的设置方式。

#### 执行 init 方法
执行 new 指令后会接着执行 init 方法，把对象按照程序员的意愿进行初始化，这样一个真正可用的对象才算完全产生出来。

### 对象的内存布局
对象在 HotSpot 虚拟机中存储的布局可以分为 3 块区域：**对象头**、**实例数据**和**对齐填充**。

#### 对象头
对象头主要包含**运行时数据**和**类型指针**两部分信息。
<div align="center">
<img src="https://raw.githubusercontent.com/laofeijunfeng/demo/master/images/jvm/20190514105705.png" width="600px"/>
</div>

#### 实例数据
这部分是对象真正存储的有效信息，包括定义的以及从父类继承下来的。这部分的存储顺序会受到虚拟机分配策略参数和字段在 Java 源码中定义顺序的影响。

#### 对齐填充
对齐填充并不是必然存在的。由于 HotSpot VM 的自动内存管理系统要求对象起始地址必须是 8 字节的整数倍，对齐填充使对象头正好是 8 字节的倍数。

### 对象的访问定位
Java 程序需要通过栈上的 reference 数据来操作堆上的具体对象。而 reference 类型定位对象的方式主要有**句柄**和**直接指针**两种。

#### 句柄
<div align="center">
<img src="https://raw.githubusercontent.com/laofeijunfeng/demo/master/images/jvm/20190514111255.png" width="600px"/>
</div>

#### 指针
<div align="center">
<img src="https://raw.githubusercontent.com/laofeijunfeng/demo/master/images/jvm/20190514111459.png" width="600px"/>
</div>

#### 区别
<div align="center">
<img src="https://raw.githubusercontent.com/laofeijunfeng/demo/master/images/jvm/20190514111954.png" width="600px"/>
</div>