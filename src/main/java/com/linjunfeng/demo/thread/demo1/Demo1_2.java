package com.linjunfeng.demo.thread.demo1;

public class Demo1_2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                int time = (int) (Math.random() + 1000);
                Thread.sleep(time);
                System.out.println("run = " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Demo1_2 demo1_2 = new Demo1_2();
            demo1_2.setName("demo");
            demo1_2.start();
            for (int i = 0; i < 10; i++) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("main = " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
