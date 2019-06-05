package com.linjunfeng.demo.zookeeper.api.authNode;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class AuthSampleGet {
    final static String PATH = "/zk-boot-auth_test";

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper1 = new ZooKeeper("localhost:2181", 5000, null);
        zooKeeper1.addAuthInfo("digest", "foo:true".getBytes());
        zooKeeper1.create(PATH, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        System.out.println("不添加权限获取：");
        ZooKeeper zooKeeper2 = new ZooKeeper("localhost:2181", 50000, null);
        zooKeeper2.getData(PATH, false, null);

        System.out.println("添加了正确权限：");
        ZooKeeper zooKeeper3 = new ZooKeeper("localhost:2181", 50000, null);
        zooKeeper3.addAuthInfo("digest", "foo:true".getBytes());

        System.out.println("添加了错误权限：");
        ZooKeeper zooKeeper4 = new ZooKeeper("localhost:2181", 50000, null);
        zooKeeper4.addAuthInfo("digest", "foo:false".getBytes());
    }
}
