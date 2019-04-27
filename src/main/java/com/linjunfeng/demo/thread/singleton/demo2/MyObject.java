package com.linjunfeng.demo.thread.singleton.demo2;

public class MyObject {
    private static MyObject myObject;
    private MyObject() {}
    public static MyObject getInstance() {
        // 延迟加载
        /*if (myObject == null) {
            try {
                // 模式加载前所需要做的工作的时间
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myObject = new MyObject();
        }*/

        // 使用 DCL 双检查锁机制可实现多线程环境中延迟加载的单例模式
        if (myObject == null) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (MyObject.class) {
                if (myObject == null)
                    myObject = new MyObject();
            }
        }
        return myObject;
    }
}
