目录
====
* java
    * [stream](#流库)
    * [多线程](#多线程)
    * [jvm](#JVM)
* springboot
    * [Thymeleaf](#Thymeleaf模板引擎的简单使用)
    * [Mqtt](#Mqtt的基本使用)
* noSql
    * [redis](#Redis)
* 其他
    * [一些简单的数学联系](#一些简单的数学问题练习)
    * [日常测试](#日常测试)

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

##### Thymeleaf模板引擎的简单使用

* [模版引入](https://github.com/laofeijunfeng/demo/commit/73823dbed5d3dbeb7d11f87dbbb4faa65d48c6ee)
* [整体布局](https://github.com/laofeijunfeng/demo/commit/65258796a4a1e5651c53c89832ef752219784cbd)
* [变量传递](https://github.com/laofeijunfeng/demo/commit/15719570e9a4346e146beeca63f62e754a99e84d)

##### Mqtt的基本使用
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

### JVM


### 日常测试

* [多线程下使用 SimpleDateFormat 问题](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/others/demo1)

### Redis

#### 基础
`Redis 有 5 种基础数据结构，分别为：string (字符串)、list (列表)、set (集合)、hash (哈希) 和 zset (有序集合)；`

* string (字符串) ： Redis 所有的数据结构都是以唯一的 key 字符串作为名称，然后通过这个唯一 key 值来获取相应的 value 数据。不同类型的数据结构的差异就在于 value 的结构不一样。<br/>Redis 的字符串是动态字符串，是可以修改的字符串，内部结构实现上类似于 Java 的 ArrayList，采用预分配冗余空间的方式来减少内存的频繁分配，当字符串长度小于 1M 时，扩容都是加倍现有的空间，如果超过 1M，扩容时一次只会多扩 1M 的空间。需要注意的是字符串最大长度为 512M。
* list (列表) ： Redis 的列表相当于 Java 的 LinkedList，注意它是链表而不是数组。这意味着 list 的插入和删除操作非常快，时间复杂度为 O(1)，但是索引定位很慢，时间复杂度为 O(n)。当列表弹出了最后一个元素之后，该数据结构自动被删除，内存被回收。
* hash (字典) ： Redis 的字典相当于 Java 的 HashMap，它是无序字典。内部实现结构上同 Java 的 HashMap 也是一致的，同样的数组 + 链表二维结构。第一维 hash 的数组位置碰撞时，就会将碰撞的元素使用链表串接起来。不同的是，Redis 的字典的值只能是字符串，另外它们 rehash 的方式不一样，redis 采用了渐进式 rehash 策略。
* set (集合) : Redis 的集合相当于 Java 的 HashSet，它内部的键值对是无序的唯一的。它的内部实现相当于一个特殊的字典，字典中所有的 value 都是一个值 NULL。当集合中最后一个元素移除之后，数据结构自动删除，内存被回收。
* zset (有序集合) : Redis 的有序集合类似于 Java 的 SortedSet 和 HashMap 的结合体，不仅保证了内部 value 的唯一性，还给每个 value 赋予一个 score，代表这个 value 的排序权重。内部实现用的是一种叫做「跳跃列表」的数据结构。