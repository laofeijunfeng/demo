package com.linjunfeng.demo.thread.Demo4;

public class Demo4_2 extends Thread {
    @Override
    public void run() {
        System.out.println("run = " + this.isAlive());
    }

    public static void main(String[] args) throws InterruptedException {
        Demo4_2 demo4_2 = new Demo4_2();
        System.out.println("begin == " + demo4_2.isAlive());
        demo4_2.start();
        Thread.sleep(1000);
        System.out.println("end == " + demo4_2.isAlive());
    }
}
