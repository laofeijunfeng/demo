package com.linjunfeng.demo.zookeeper.api.getData;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class GetDataApiAsyncUsage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void main(String[] args) throws Exception {
        String path = "/zk-book3";
        zk = new ZooKeeper("localhost:2181", 5000, new GetDataApiAsyncUsage());
        connectedSemaphore.await();
        zk.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zk.getData(path, true, new IDataCallback(), null);
        zk.setData(path, "123".getBytes(), -1);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath())
                connectedSemaphore.countDown();
            else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                try {
                    zk.getData(watchedEvent.getPath(), true, new IDataCallback(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class IDataCallback implements AsyncCallback.DataCallback {
        @Override
        public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
            System.out.println(i + ", " + s + ", " + new String(bytes));
            System.out.println(stat.getCzxid() + ", " + stat.getMzxid() + ", " + stat.getVersion());
        }
    }
}
