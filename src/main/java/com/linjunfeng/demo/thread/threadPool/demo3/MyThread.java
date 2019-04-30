package com.linjunfeng.demo.thread.threadPool.demo3;

public class MyThread implements Runnable {

    private int num;

    public MyThread(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("当前执行的线程为：" + num);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程：" + num + " 执行结束");
    }
}
