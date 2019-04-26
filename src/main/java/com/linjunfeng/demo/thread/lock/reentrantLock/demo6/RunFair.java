package com.linjunfeng.demo.thread.lock.reentrantLock.demo6;

public class RunFair {

    /**
     * 打印结果是基本有序的，这是公平锁的特点
     * @param args
     */

    public static void main(String[] args) {
        final Service service = new Service(true);
        Runnable runnable = () -> {
            System.out.println("* 线程 " + Thread.currentThread().getName() + " 运行了");
            service.serviceMethod();
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}
