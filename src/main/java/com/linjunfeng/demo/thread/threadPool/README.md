### 线程池的使用

线程池，就是一次性创建出多个线程，当一个任务到来时可以直接从线程池中获取一个线程来执行，从而省去了创建线程和销毁线程的时间，避免了频繁创建和销毁带来的资源消耗。<br/>

JDK 提供了一套 Executor 框架来更好的控制多线程，其核心就是线程池。Executor 有 ExecutorService 和 ScheduleExecutorService 两个子接口。<br/>

AbstractExecutorService 实现了 ExecutorService 接口，ThreadPoolExecutor 扩展了抽象类 AbstractExecutorService，而 Executors 类是线程池的工厂类，里面有多个静态方法创建相应的线程。<br/>

ThreadPoolExecutor 类 <br/>
```
/**
* ThreadPoolExecutor 继承了 AbstractExecutorServicem，AbstractExecutorService 是一个抽象类，它实现了 ExecutorService 接口，而 ExecutorService 又是继承了 Executor 接口；
*/
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
构造器中各个参数的含义：
> * corePoolSize：核心池的大小，在创建了线程池后，默认情况下，线程池中并没有任何线程，而是等待有任务到来才创建线程去执行任务，除非调用了 prestartAllCoreThreads() 或者 prestartCoreThread() 方法预创建线程，即在没有任务到来之前就创建 corePoolSize 个线程或者一个线程。默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，当线程池中的线程数目达到 corePoolSize 后，就会把到达的任务放到缓存队列当中；
> * maximumPoolSize：线程池最大线程数，这个参数也是一个非常重要的参数，它表示在线程池中最多能创建多少个线程；
> * keepAliveTime：表示线程没有任务执行时最多保持多久时间会终止。默认情况下，只有当线程池中的线程数大于 corePoolSize 时，keepAliveTime 才会起作用；但是如果调用了 allowCoreThreadTimeOut(boolean) 方法，在线程池中的线程数不大于 corePoolSize 时，keepAliveTime 参数也会起作用，直到线程池中的线程数为0；
> * unit：参数keepAliveTime的时间单位；
> * workQueue：一个阻塞队列，用来存储等待执行的任务；一般来说，这里的阻塞队列有以下几种选择：
>   > ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小；<br/>
      LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；<br/>
      SynchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务；<br/>
      ArrayBlockingQueue 和 PriorityBlockingQueue 使用较少，一般使用 LinkedBlockingQueue 和 Synchronous。线程池的排队策略与 BlockingQueue 有关；
> * threadFactory：线程工厂，主要用来创建线程；
> * handler：表示当拒绝处理任务时的策略，有以下四种取值：
>   > ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出 RejectedExecutionException 异常；
      ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常；
      ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）；
      ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务；

线程池状态
> 在ThreadPoolExecutor中定义了一个volatile变量，另外定义了几个static final变量表示线程池的各个状态：
```
volatile int runState;
static final int RUNNING    = 0;
static final int SHUTDOWN   = 1;
static final int STOP       = 2;
static final int TERMINATED = 3;
```
> runState表示当前线程池的状态，它是一个volatile变量用来保证线程之间的可见性；<br/>
  当创建线程池后，初始时，线程池处于RUNNING状态；<br/>
  如果调用了shutdown()方法，则线程池处于SHUTDOWN状态，此时线程池不能够接受新的任务，它会等待所有任务执行完毕；<br/>
  如果调用了shutdownNow()方法，则线程池处于STOP状态，此时线程池不能接受新的任务，并且会去尝试终止正在执行的任务；<br/>
  当线程池处于SHUTDOWN或STOP状态，并且所有工作线程已经销毁，任务缓存队列已经清空或执行结束后，线程池被设置为TERMINATED状态。

在 Executors 中主要以下几个静态方法：
* [ThreadPoolExecutor()]()<br/>
* [newFixedThreadPool(int nThreads)]()<br/>
`创建指定数目线程的线程池，当提交一个任务时，若线程池中有空闲线程，则立即执行，如果没有就暂时放入一个队列汇总等待执行。`
* [newSingleThreadExecutor()]()<br/>
`创建只有一个线程的线程池，若有多余的线程提交，会被保存到一个队列中等待执行，按照先进先出的顺序执行。`
* [newSingleThreadScheduledExecutor()]()<br/>
`创建只有一个线程的线程池，但是返回ScheduleExecutorService类，该类扩展了在给定时间执行任务的功能。`
* [newScheduledThreadPool(int corePoolSize)]()<br/>
`该类也返回ScheduleExecutorService类，可以指定线程池的线程数目。`
