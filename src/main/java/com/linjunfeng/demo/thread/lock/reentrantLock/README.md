### 使用 ReentrantLock 类

可以使用 synchronized 关键字来实现线程之间的同步互斥，而 ReentrantLock 类也可以达到同样的效果，并且在扩展功能上更加强大，比图具有嗅探锁定、多路分支通知等功能，而且比 synchronized 更加的灵活。

类 ReentrantLock 也可以实现通知功能，但是需要借助于 Condition 对象，Condition 对象相对 wait() 和 notify() 来说更加灵活，可以实现多路复用，也就是一个 Lock 对象里创建多个 Condition 实例。而且可以实现选择性通知。consition.await() 方法调用之前需要调用 lock.lock() 代码获得同步监视器。

* [实现同步：测试1]()
* [实现同步：测试2]()

公平锁与非公平锁：锁 Lock 分为“公平锁”和“非公平锁”，公平锁表示线程获取锁的顺序是按照加锁的顺序来分配的，即FIFO，而非公平锁就是以一种获取锁的抢占机制，是随机获得锁的，这种方式可能造成某些线程一直拿不到锁。
公平锁：ReentrantLock(true)
非公平锁：ReentrantLock(false)

* [公平锁与非公平锁]()