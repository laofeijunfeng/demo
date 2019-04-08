package com.linjunfeng.demo.thread.demo1;

public class Demo1_1 extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("Demo1_1");
    }

    public static void main(String[] args) {
        Demo1_1 demo1_1 = new Demo1_1();
        demo1_1.start();
        System.out.println("运行结束！");
    }
}
