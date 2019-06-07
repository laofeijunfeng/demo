# 目录
* [使用Zookeeper](#使用Zookeeper)
    * [Java客户端API的使用](#Java客户端API的使用)
        * [创建会话](#创建会话)
        * [创建节点](#创建节点)
        * [删除节点](#删除节点)
        * [读取数据](#读取数据)
        * [更新数据]()
        * [检测节点]()
        * [权限控制]()

# 使用Zookeeper

## Java客户端API的使用

### 创建会话
客户端可以通过创建一个 Zookeeper 实例来连接服务器，zookeeper 连接是一个异步的过程，所以当程序执行完，会话处于的生命周期的"CIONNECTING"的状态。

当会话真正创建完毕，服务端会向对应的客户端发起一个事件通知。

创建一个基本会话：
```java
public class ZookeeperConstructorUsageSimple implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper(
                "localhost:2181",   // 服务器列表，如果多个服务器可用逗号分开
                5000,               // 会话超时时间，如果在此期间没有进行有效的心跳检测，则会话会失效
                new ZookeeperConstructorUsageSimple()   // 事件监听器
                );
        // 构造方法还有其他三个参数：
        // boolean canbeReadOnly 是否支持只读模式
        // sessionId 和 sessionPasswd 会话的 Id和密钥，这两个参数能唯一确定一个会话，可实现客户端的复用
        System.out.println(zooKeeper.getState());
        try {
            connectedSemaphore.await();
        } catch (InterruptedException e) {
            System.out.println("Zookeeper session eatablished.");
        }
    }

    /**
     * 负责处理来自 Zookeeper 服务端的 Watcher 通知
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event: " + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState())
            connectedSemaphore.countDown();
    }
}
```

执行结果：
```jshelllanguage
CONNECTING
Receive watched event: WatchedEvent state:SyncConnected type:None path:null
```

使用 sessionId 和 sessionPasswd 创建和复用会话：
```java
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
```

执行结果：
```jshelllanguage
Receive watched event: WatchedEvent state:SyncConnected type:None path:null
Receive watched event: WatchedEvent state:Expired type:None path:null
Receive watched event: WatchedEvent state:SyncConnected type:None path:null
```

### 创建节点

创建节点支持同步和异步两种方式，但都不支持递归创建，也就是无法在不存在但父节点下创建自节点，如果节点已存在，则会抛出异常。

使用同步创建一个节点：
```java
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
                // PERSISTENT持久
                // PERSISTENT_SEQUENTIAL持久顺序 
                // EPHEMERAL临时 
                // EPHEMERAL_SEQUENTIAL临时顺序
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
```

执行结果：
```jshelllanguage
Success create znode: /zk-test-ephemeral-
Success create znode: /zk-test-ephemeral-0000000002
```

使用异步创建节点：
```java
public class ZookeeperCreateApiAsyncUsage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, new ZookeeperCreateApiAsyncUsage());
        connectedSemaphore.await();

        zooKeeper.create("/zk-test-ephemeral-", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                new IStringCallBack(), "I am context");

        zooKeeper.create("/zk-test-ephemeral-", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                new IStringCallBack(), "I am context");

        zooKeeper.create("/zk-test-ephemeral-", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,
                new IStringCallBack(), "I am context");

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            connectedSemaphore.countDown();
        }
    }

    static class IStringCallBack implements AsyncCallback.StringCallback {
        @Override
        public void processResult(int i, String s, Object o, String s1) {
            System.out.println("Create path result: [" + i + ", " + s + ", " + o + ", real path name: " + s1);
        }
    }
}
```

执行结果：
 ```jshelllanguage
Create path result: [0, /zk-test-ephemeral-, I am context, real path name: /zk-test-ephemeral-
Create path result: [-110, /zk-test-ephemeral-, I am context, real path name: null
Create path result: [0, /zk-test-ephemeral-, I am context, real path name: /zk-test-ephemeral-0000000004
```

### 删除节点

删除节点，只允许删除子叶节点，如果节点存在至少一个子叶节点，则无法直接删除，必须先删除所有子叶节点。

### 读取数据

使用同步获取数据：
```java
public class GetDataApiSyncUsage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        String path = "/zk-book2";
        zk = new ZooKeeper("localhost:2181", 5000, new GetDataApiSyncUsage());
        connectedSemaphore.await();
        zk.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(new String(zk.getData(path, true, stat)));
        System.out.println(stat.getCzxid() + ", " + stat.getMzxid() + ", " + stat.getVersion());
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
                    System.out.println(new String(zk.getData(
                            watchedEvent.getPath(), // 节点路径
                            true,                   // 是否注册一个 Watcher，如果为 true，则会使用默认 Watcher
                            stat                    // 数据信息状态
                            )));
                    // 该构造函数还可以在第二个参数增加一个数据变化监视器 Watcher
                    System.out.println(stat.getCzxid() + " , " + stat.getMzxid() + ", " + stat.getVersion());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

输出结果：
```jshelllanguage
123
20, 20, 0
123
20 , 21, 1
```

