package com.linjunfeng.demo.thread.communication.join.demo2;

public class ThreadB extends Thread{
    @Override
    public void run() {
        try {
            ThreadA a = new ThreadA();
            a.start();
            a.join();
            System.out.println("线程 B 在 run end 处打印了");
        } catch (InterruptedException e) {
            System.out.println("线程 B 在 catch 处打印了");
            e.printStackTrace();
        }
    }
}
