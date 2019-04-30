package com.linjunfeng.demo.thread.threadPool.demo1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
        for (int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程此中线程数目：" + executor.getPoolSize() +
                    "，队列中等待执行的任务数目：" + executor.getQueue().size() +
                    "，已执行完毕的任务数目：" + executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}
