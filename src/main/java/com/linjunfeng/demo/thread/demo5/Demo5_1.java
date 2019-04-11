package com.linjunfeng.demo.thread.demo5;

public class Demo5_1 extends Thread{
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            System.out.println("i = " + (i + 1));
        }
    }

    public static void main(String[] args) {
        try {
            Demo5_1 demo5_1 = new Demo5_1();
            demo5_1.start();
            Thread.sleep(2000);
            demo5_1.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
