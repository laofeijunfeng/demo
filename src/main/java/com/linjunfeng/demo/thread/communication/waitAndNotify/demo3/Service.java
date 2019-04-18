package com.linjunfeng.demo.thread.communication.waitAndNotify.demo3;

public class Service {
    public void testMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("begin wait()");
                lock.wait();
                System.out.println("end wait()");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("出现异常：" + e.getMessage());
        }
    }
}
