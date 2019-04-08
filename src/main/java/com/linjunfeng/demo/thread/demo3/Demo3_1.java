package com.linjunfeng.demo.thread.demo3;

public class Demo3_1 extends Thread {

    private int count = 5;

    public Demo3_1(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        while (count > 0) {
            count --;
            System.out.println("由 " + currentThread().getName() + " 计算，count = " + count);
        }
    }

    public static void main(String[] args) {
        Demo3_1 a = new Demo3_1("A");
        Demo3_1 b = new Demo3_1("B");
        Demo3_1 c = new Demo3_1("C");
        a.start();
        b.start();
        c.start();
    }
}
