package com.linjunfeng.demo.zookeeper.api.create;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ZookeeperConstructorUsageWithSidPasswd implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, new ZookeeperConstructorUsageWithSidPasswd());
        connectedSemaphore.await();
        long sessionId = zooKeeper.getSessionId();
        byte[] passwd = zooKeeper.getSessionPasswd();

        // 测试使用非法的 sessionId 和 passwd
        zooKeeper = new ZooKeeper("localhost:2181", 5000,
                new ZookeeperConstructorUsageWithSidPasswd(), 1l, "test".getBytes());

        // 测试使用正确的 sessionId 和 passwd
        zooKeeper = new ZooKeeper("localhost:2181", 5000,
                new ZookeeperConstructorUsageWithSidPasswd(), sessionId, passwd);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event: " + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState())
            connectedSemaphore.countDown();
    }
}
