package com.linjunfeng.demo.thread.lock.reentrantLock.demo6;

public class RunNotFair {

    /**
     * 打印结果是乱序的，先 start() 不一定获得锁
     * @param args
     */

    public static void main(String[] args) {
        final Service service = new Service(false);
        Runnable runnable = () -> {
            System.out.println("* 线程 " + Thread.currentThread().getName() + "运行了");
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
