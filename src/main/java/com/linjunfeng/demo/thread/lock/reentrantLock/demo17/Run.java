package com.linjunfeng.demo.thread.lock.reentrantLock.demo17;

public class Run {
    public static void main(String[] args) {
        final MyService service = new MyService();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " 调用 waitMethod 时间：" + System.currentTimeMillis());
            service.waitMethod();
        };
        Thread threadA = new Thread(runnable);
        threadA.setName("A");
        threadA.start();
        Thread threadB = new Thread(runnable);
        threadB.setName("B");
        threadB.start();
    }
}
