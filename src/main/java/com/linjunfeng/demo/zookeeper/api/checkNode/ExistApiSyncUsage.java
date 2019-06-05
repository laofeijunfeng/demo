package com.linjunfeng.demo.zookeeper.api.checkNode;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

public class ExistApiSyncUsage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void main(String[] args) throws Exception {
        String path = "/zk-book6";
        zk = new ZooKeeper("localhost:2181", 5000, new ExistApiSyncUsage());
        connectedSemaphore.await();
        zk.exists(path, true);
        zk.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.setData(path, "123".getBytes(), -1);

        zk.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.delete(path + "/c1", -1);
        zk.delete(path, -1);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath())
                    connectedSemaphore.countDown();
                else if ((Event.EventType.NodeCreated == watchedEvent.getType())) {
                    System.out.println("Node(" + watchedEvent.getPath() + ")Created");
                    zk.exists(watchedEvent.getPath(), true);
                } else if (Event.EventType.NodeDeleted == watchedEvent.getType()) {
                    System.out.println("Node(" + watchedEvent.getPath() + ")Deleted");
                    zk.exists(watchedEvent.getPath(), true);
                } else if (Event.EventType.NodeDataChanged == watchedEvent.getType()) {
                    System.out.println("Node(" + watchedEvent.getPath() + ")DataChanged");
                    zk.exists(watchedEvent.getPath(), true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
