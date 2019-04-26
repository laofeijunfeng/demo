### 使用 ReentrantLock 类

可以使用 synchronized 关键字来实现线程之间的同步互斥，而 ReentrantLock 类也可以达到同样的效果，并且在扩展功能上更加强大，比图具有嗅探锁定、多路分支通知等功能，而且比 synchronized 更加的灵活。

* [实现同步：测试1](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantLock/demo1)
* [实现同步：测试2](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantLock/demo2)

类 ReentrantLock 也可以实现通知功能，但是需要借助于 Condition 对象，Condition 对象相对 wait() 和 notify() 来说更加灵活，可以实现多路复用，也就是一个 Lock 对象里创建多个 Condition 实例。而且可以实现选择性通知。consition.await() 方法调用之前需要调用 lock.lock() 代码获得同步监视器。

* [Condition：等待/通知](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantLock/demo3)
* [Condition：分组通知](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantLock/demo4)
* [生产者/消费者模式：一对一交替打印](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantLock/demo5)

公平锁与非公平锁：锁 Lock 分为“公平锁”和“非公平锁”，公平锁表示线程获取锁的顺序是按照加锁的顺序来分配的，即FIFO，而非公平锁就是以一种获取锁的抢占机制，是随机获得锁的，这种方式可能造成某些线程一直拿不到锁。<br />
公平锁：ReentrantLock(true) <br />非公平锁：ReentrantLock(false)

* [公平锁与非公平锁](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantLock/demo6)

ReentrantLock 相关的方法测试

* [int getHoldCount()](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantLock/demo7) `查询当前线程保持此锁定的个数`
* [int getQueueLength()](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantLock/demo8) `查询正在等待获取此锁定的线程估计数`
* [int getWaitQueueLength()](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantLock/demo9) `查询等待与此锁定相关的给定条件 Condition 的线程估计数`
* [boolean hasQueuedThread()](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantLock/demo10) `查询指定的线程是否正在等待此锁定`
* [boolean hasQueuedThreads()](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantLock/demo10) `查询是否有线程正在等待此锁定`
* [boolean hasWaiters()](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantLock/demo11) `查询是否有线程正在等待与此锁定有关的 condition 条件`
* [boolean isFair()]() `判断是否是公平锁，默认情况下 ReentrantLock 使用的是非公平锁`
* [boolean isHeldByCurrentThread()]() `查询当前线程是否保持此锁`
* [boolean isLocked()]() `查询此锁定是否由任意线程保持`