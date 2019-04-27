package com.linjunfeng.demo.thread.singleton.demo2;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(MyObject.getInstance().hashCode());
    }
}
