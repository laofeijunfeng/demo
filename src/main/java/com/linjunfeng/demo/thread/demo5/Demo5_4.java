package com.linjunfeng.demo.thread.demo5;

public class Demo5_4 extends Thread {
    /**
     * 使用抛出异常的机制停止线程
     */

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 500000; i++) {
                if (interrupted()) {
                    System.out.println("已经是停止状态了！我要退出了！");
                    throw new InterruptedException();
                }
                System.out.println("1 = " + (i + 1));
            }
            System.out.println("我在 for 下面！");
        } catch (InterruptedException e) {
            System.out.println("进了 catch 了！");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Demo5_4 demo5_4 = new Demo5_4();
            demo5_4.start();
            Thread.sleep(1000);
            demo5_4.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch!");
            e.printStackTrace();
        }
    }
}
