package com.linjunfeng.demo.thread.lock.reentrantLock.demo2;

public class Run {

    /**
     * 调用 lock.lock() 代码的线程就持有了“对象监视器”，其他线程只有等待锁被释放时再次争抢。
     * @param args
     */

    public static void main(String[] args) {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadAA aa = new ThreadAA(service);
        aa.setName("AA");
        aa.start();
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
        ThreadBB bb = new ThreadBB(service);
        bb.setName("BB");
        bb.start();
    }
}
