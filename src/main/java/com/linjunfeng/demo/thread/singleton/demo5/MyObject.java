package com.linjunfeng.demo.thread.singleton.demo5;

public class MyObject {
    private static MyObject instance = null;
    private MyObject() {}
    static {
        instance = new MyObject();
    }
    public static MyObject getInstance() {
        return instance;
    }
}
