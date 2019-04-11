package com.linjunfeng.demo.thread.demo5;

public class Demo5_2 extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            System.out.println("i = " + (i + 1));
        }
    }

    public static void main(String[] args) {
        /*try {
            Demo5_2 demo5_2 = new Demo5_2();
            demo5_2.start();
            Thread.sleep(2000);
            Thread.currentThread().interrupt();
            System.out.println("是否停止 1 ？ = " + demo5_2.interrupted());
            System.out.println("是否停止 2 ？ = " + demo5_2.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end!");*/

        try {
            Demo5_2 demo5_2 = new Demo5_2();
            demo5_2.start();
            Thread.sleep(1233);
            demo5_2.interrupt();
            System.out.println("是否停止 1 ？ = " + demo5_2.isInterrupted());
            System.out.println("是否停止 2 ？ = " + demo5_2.isInterrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
