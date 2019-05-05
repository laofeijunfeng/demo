Spring Boot 各种 Demo 练习与测试
====

<br/>

### 一些简单的数学问题练习：

* [java 二进制与十进制的互相转换](https://github.com/laofeijunfeng/demo/commit/681894f2b82071a0b1dd8dcc43f9b61fb8e9ef35)
* [java 使用二分法求整数的平方根](https://github.com/laofeijunfeng/demo/commit/089f58423e3bbe91b738fdeb0d0bd704c571fb10)
* [java 数学归纳法的证明](https://github.com/laofeijunfeng/demo/commit/dbbaabf3e4342a84483323022e778b4581689b6a)
* [java 使用递归算不同的排列方式](https://github.com/laofeijunfeng/demo/commit/28b99b18e1f4ed1049c7956e1ce9bf9697c9b40a)
* [java 使用递归解决田忌赛马的选择问题](https://github.com/laofeijunfeng/demo/commit/8f4af5fd054ab7961844934de21ed8303fce01d1)
* [java 拓展：密码匹配](https://github.com/laofeijunfeng/demo/commit/42858ef2937b3f23531c3e1901d8ac6fa874ae8f)
* [java 使用递归计算不同的组合方式](https://github.com/laofeijunfeng/demo/commit/ceba4cad2440ca28e66388048025a5b724487aa4)
* [java 利用树的广度优先搜索查询人际关系](https://github.com/laofeijunfeng/demo/commit/af699e768891916274f0c8a4daa2eadbdbf862db)

<br/>

### Spring Boot 框架练习：

Thymeleaf 模板引擎的简单使用

* [模版引入](https://github.com/laofeijunfeng/demo/commit/73823dbed5d3dbeb7d11f87dbbb4faa65d48c6ee)
* [整体布局](https://github.com/laofeijunfeng/demo/commit/65258796a4a1e5651c53c89832ef752219784cbd)
* [变量传递](https://github.com/laofeijunfeng/demo/commit/15719570e9a4346e146beeca63f62e754a99e84d)

Mqtt 的基本使用
* [客户端配置](https://github.com/laofeijunfeng/demo/commit/07e66e1c09c676a05da601cbd3aea3eb9f9217a4)
* [订阅与发布](https://github.com/laofeijunfeng/demo/commit/2e3b3b46f9861e11d51b805cad631465f78f468c)

<br/>

### JAVA 基础

#### [流库](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/stream)

<br/>

### 多线程

#### 实现方式

* [继承 Thread 类](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/demo1)
* [实现 Runnable 接口](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/demo2)

#### 停止线程

* [isAlive 方法](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/demo4)
* [interrupt 标记线程](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo5/Demo5_1.java)
* [interrupted() 和 isInterrupted()](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo5/Demo5_2.java)
* [break 停止线程](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo5/Demo5_3.java)
* [抛出异常的机制停止线程](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo5/Demo5_4.java)
* [在沉睡中停止线程](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo5/Demo5_5.java)
* [return 停止线程](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo5/Demo5_6.java)
* [suspend() 和 resume()](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo6/Demo6_1.java)
* [yield() 放弃资源](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo6/Demo6_2.java)

#### 线程安全

* [synchronized 与实例变量](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo7/Demo7_1.java)
* [synchronized 与方法](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo7/Demo7_2.java)
* [synchronized 可重入锁](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo7/Demo7_4.java)
* [synchronized 类继承的锁重入](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo7/Demo7_5.java)
* [synchronized 抛出异常，锁自动释放](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo7/Demo7_6.java)
* [synchronized 继承不具备同步性](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo7/Demo7_7.java)
* [synchronized 与代码块](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo8/Demo8_1.java)
* [synchronized 对象监视器-this](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo8/Demo8_2.java)
* [脏读：异步的方式返回](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo8/Demo8_4.java)
* [对象锁 与 Class锁](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo8/Demo8_5.java)
* [内置类与同步](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo8/Demo8_6.java)
* [volatile 可见性](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo8/Demo8_8.java)
* [volatile 不具备同步性和原子性](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo8/Demo8_9.java)
* [AtomicInteger 保证原子性操作](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo8/Demo8_10.java)
* [synchronized 可见性](https://github.com/laofeijunfeng/demo/blob/master/src/main/java/com/linjunfeng/demo/thread/demo8/Demo8_11.java)

#### 等待/通知机制

* [wait() 与 notify()](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/communication/waitAndNotify)
* [join()](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/communication/join)
* [类 ThreadLocal](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/communication/join)
* [类 InheritableThreadLocal](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/communication/inheritableThreadLocal)

#### Lock

* [类 ReentrantLock](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantLock)
* [类 ReentrantReadWriteLock](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantReadWriteLock)

#### 单例模式与多线程

* [立即加载/“饿汉模式”](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/singleton/demo1)
* [延迟加载/“懒汉模式”](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/singleton/demo2)
* [静态内置类实现单例模式](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/singleton/demo3)
* [序列化和反序列化的单例模式](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/singleton/demo4)
* [static代码块实现单例模式](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/singleton/demo5)

#### [线程池](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/threadPool)

#### [面试题]()