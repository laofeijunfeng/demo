### 线程池的使用

线程池，就是一次性创建出多个线程，当一个任务到来时可以直接从线程池中获取一个线程来执行，从而省去了创建线程和销毁线程的时间，避免了频繁创建和销毁带来的资源消耗。<br/>

JDK 提供了一套 Executor 框架来更好的控制多线程，其核心就是线程池。Executor 有 ExecutorService 和 ScheduleExecutorService 两个子接口。<br/>

AbstractExecutorService 实现了 ExecutorService 接口，ThreadPoolExecutor 扩展了抽象类 AbstractExecutorService，而 Executors 类是线程池的工厂类，里面有多个静态方法创建相应的线程。<br/>

ThreadPoolExecutor 类 <br/>
```
public class ThreadPoolExecutor extends AbstractExecutorService {
    .....
    public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
            BlockingQueue<Runnable> workQueue);
 
    public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
            BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory);
 
    public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
            BlockingQueue<Runnable> workQueue,RejectedExecutionHandler handler);
 
    public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
        BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory,RejectedExecutionHandler handler);
    ...
}
```

在 Executors 中主要以下几个静态方法：
* [newFixedThreadPool(int nThreads)]()<br/>
`创建指定数目线程的线程池，当提交一个任务时，若线程池中有空闲线程，则立即执行，如果没有就暂时放入一个队列汇总等待执行。`
* [newSingleThreadExecutor()]()<br/>
`创建只有一个线程的线程池，若有多余的线程提交，会被保存到一个队列中等待执行，按照先进先出的顺序执行。`
* [newSingleThreadScheduledExecutor()]()<br/>
`创建只有一个线程的线程池，但是返回ScheduleExecutorService类，该类扩展了在给定时间执行任务的功能。`
* [newScheduledThreadPool(int corePoolSize)]()<br/>
`该类也返回ScheduleExecutorService类，可以指定线程池的线程数目。`
