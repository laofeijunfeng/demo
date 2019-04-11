package com.linjunfeng.demo.thread.demo5;

public class Demo5_5 extends Thread {

    /**
     * 在沉睡中停止线程
     */

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("run begin");
            Thread.sleep(20000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("在沉睡中被停止！进入 catch ！" + this.isInterrupted());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Demo5_5 demo5_5 = new Demo5_5();
            demo5_5.start();
            Thread.sleep(2000);
            demo5_5.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end！");
    }
}
