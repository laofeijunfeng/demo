package com.linjunfeng.demo.thread.demo2;

public class Demo2_1 implements Runnable {
    @Override
    public void run() {
        System.out.println("运行中！");
    }

    public static void main(String[] args) {
        Runnable demo2_1 = new Demo2_1();
        Thread thread = new Thread(demo2_1);
        thread.start();
        System.out.println("运行结束！");
    }
}
