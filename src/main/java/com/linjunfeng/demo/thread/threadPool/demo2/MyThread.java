package com.linjunfeng.demo.thread.threadPool.demo2;

public class MyThread implements Runnable {

    private int num;

    public MyThread(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("当前执行的线程：" + num);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行线程 " + num + " 结束");
    }
}
