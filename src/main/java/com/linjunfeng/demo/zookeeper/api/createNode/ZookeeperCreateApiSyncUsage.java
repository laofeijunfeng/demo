package com.linjunfeng.demo.zookeeper.api.createNode;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

public class ZookeeperCreateApiSyncUsage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, new ZookeeperCreateApiSyncUsage());
        connectedSemaphore.await();
        String path1 = zooKeeper.create(
                "/zk-test-ephemeral-",  // 节点路径
                "".getBytes(),               // 创建后的初始内容
                ZooDefs.Ids.OPEN_ACL_UNSAFE, // acl 策略
                CreateMode.EPHEMERAL
                // 节点类型：
                // PERSISTENT持久 PERSISTENT_SEQUENTIAL持久顺序 EPHEMERAL临时 EPHEMERAL_SEQUENTIAL临时顺序
        );
        System.out.println("Success create znode: " + path1);

        String path2 = zooKeeper.create(
                "/zk-test-ephemeral-",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Success create znode: " + path2);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState())
            connectedSemaphore.countDown();
    }
}
