package com.linjunfeng.demo.thread.lock.reentrantLock.demo1;

public class Run {

    /**
     * 从结果可看出，线程之间的打印顺序是随机的，但是当前线程持有锁，则是分组打印的
     * @param args
     */

    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThread a1 = new MyThread(myService);
        MyThread a2 = new MyThread(myService);
        MyThread a3 = new MyThread(myService);
        MyThread a4 = new MyThread(myService);
        MyThread a5 = new MyThread(myService);
        a1.start();
        a2.start();
        a3.start();
        a4.start();
        a5.start();
    }
}
