### 使用 ReentrantReadWriteLock 类

ReentrantLock 类具有完全互斥排他的效果，同一时间只能有一个线程执行 ReentrantLock.lock() 方法后面的任务。这样做保证了线程安全性，但是效率低下。<br /><br />
而读写锁 ReentrantReadWriteLock 表示有两个锁，一个是读操作共享锁，另一个是写操作的排他锁。<br /><br />
读锁之间不互斥，读锁与写锁、写锁与写锁之间互斥。在没有线程执行写入操作时，可以多个线程进行读操作，而同一时间只能允许一个线程进行写操作。

* [读读共享](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantReadWriteLock/demo1)
* [写写互斥](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantReadWriteLock/demo2)
* [读写互斥](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantReadWriteLock/demo3)
* [写读互斥](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/thread/lock/reentrantReadWriteLock/demo4)
