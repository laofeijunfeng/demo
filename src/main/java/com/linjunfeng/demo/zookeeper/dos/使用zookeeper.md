# 目录
* [使用Zookeeper](#使用Zookeeper)
    * [Java客户端API的使用](#Java客户端API的使用)
        * [创建会话](#创建会话)
        * [创建节点](#创建节点)
        * [删除节点](#删除节点)
        * [读取数据](#读取数据)
        * [更新数据](#更新数据)
        * [检测节点是否存在](#检测节点是否存在)
        * [权限控制](#权限控制)
    * [开源客户端](#开源客户端)
        * [curator](#curator)
            * [创建会话](#Curator创建会话)
            * [创建节点](#Curator创建节点)
            * [删除节点](#Curator删除节点)
            * [读取节点数据](#Curator读取节点数据)
            * [更新节点数据](#Curator更新节点数据)
            * [异步接口](#Curator异步接口)

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

getData 借口和 getChildren 借口基本相同，主要的不同点是在注册的 Watcher，zookeeper 支持的数据格式只有 byte[]。

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

从结果可以看到，第一次输出的是主动调用 getData 借口获取的数据，而第二次则是节点数据更变后，服务端通过 Watcher 事件通知客户端，客户端再调用 getData 获取数据。

而从第二次获取数据可以看出，不管是节点数据变化，还是节点版本变化，都是属于节点数据变化，同样都会触发 NodeDateChange 通知。

使用异步获取节点数据内容：
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
                    System.out.println(new String(zk.getData(watchedEvent.getPath(), true, stat)));
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
0, /zk-book3, 123
40, 40, 0
0, /zk-book3, 123
40, 41, 1
```

### 更新数据

同步更新数据：
```java
public class SetDataApiSyncUsage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void main(String[] args) throws Exception{
        String path = "/zk-book4";
        zk = new ZooKeeper("localhost:2181", 5000, new SetDataApiSyncUsage());
        connectedSemaphore.await();
        zk.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zk.getData(path, true, null);
        Stat stat = zk.setData(path, "456".getBytes(), -1);
        System.out.println(stat.getCzxid() + ", " + stat.getMzxid() + ", " + stat.getVersion());

        Stat stat2 = zk.setData(
                path,               // 节点路径
                "456".getBytes(),   // 更新的数据
                stat.getVersion()); // 更新节点的版本 
                // 此构造器还有两个参数
                // cb 注册一个异步会调函数
                // ctx 用于传递上下文信息的对象
        System.out.println(stat2.getCzxid() + ", " + stat2.getMzxid() + ", " + stat2.getVersion());
        try {
            zk.setData(path, "456".getBytes(), stat.getVersion());
        } catch (KeeperException e) {
            System.out.println("Error: " + e.code() + ", " + e.getMessage());
        }
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath())
                connectedSemaphore.countDown();
        }
    }
}
```

输出结果：
```jshelllanguage
44, 45, 1
44, 46, 2
Error: BADVERSION, KeeperErrorCode = BadVersion for /zk-book4
```

由结果可以看出，最后一次更新报了版本错误，在代码中可以看到，第二次更新依旧是使用一样的版本号，所以会发生此错误。

zookeeper 中 setData 的 version 参数是由 CAS 原理衍生出来的。

CAS 的意思是：对于值 V，每次更新前都会比对其值是否是预期值 A，只有符合预期，才会将 V 原子化的更新到值 B。

在 zookeeper 更新数据时，可以传入 version 这个参数，该参数可以对应 CAS 中的"预期值"，表明是对该 version 的更新。如果在更新时，服务器发现该节点的 version 已经被其他客户端更新时，则无法更新，也是因为如此可以有效的避免一些分布式的并发问题。

使用异步更新数据：
```java
public class SetDataApiAsyncUsage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void main(String[] args) throws Exception {
        String path = "/zk-book5";
        zk = new ZooKeeper("localhost:2181", 5000, new SetDataApiAsyncUsage());
        connectedSemaphore.await();
        zk.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zk.setData(path, "456".getBytes(), -1, new IStatCallback(), null);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath())
                connectedSemaphore.countDown();
        }
    }

    private static class IStatCallback implements AsyncCallback.StatCallback {
        @Override
        public void processResult(int i, String s, Object o, Stat stat) {
            if (i == 0)
                System.out.println("SUCCESS");
        }
    }
}
```

输出结果：
```jshelllanguage
SUCCESS
```

### 检测节点是否存在

该接口主要检测指定节点是否存在，返回值是一个 stat 对象。如果在调用时注册 Watcher 的话，还可以对节点进行监听，如果由发生 创建、删除、更新时，则可以收到相对应的通知。

使用同步调用接口：
```java
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
```

输出结果：
```jshelllanguage
Node(/zk-book6)Created
Node(/zk-book6)DataChanged
Node(/zk-book6)Deleted
```

在通过 exists 检测是否存在指定节点时，同时注册一个 Watcher。当创建、更新、删除节点时，就可以收到相对应的通知。

### 权限控制

在实际的应用中，搭建一个 zookeeper 集群，统一为若干应用提供服务。在这种情况下，不同的应用之间往往是不会存在共享数据的使用场景，因此需要解决不同应用之间的权限问题。

Zookeeper 提供了 ACL 权限控制机制，通过设置节点的 ACL，如果客户端符合 ACL 控制，则可以操作节点，不符合则不可以。zookeeper 提供多种权限控制模式：world、auth、digest、ip 和 super。下面主要测试 digest 这个模式。

```java
public class AuthSampleGet {
    final static String PATH = "/zk-boot-auth_test";

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper1 = new ZooKeeper("localhost:2181", 5000, null);
        zooKeeper1.addAuthInfo(
                "digest",               // 权限控制模式
                "foo:true".getBytes()   // 具体的权限信息
                );
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
```

## 开源客户端

### curator

Curator 解决了很多 Zookeeper 客户端非常底层的细节开发工作，不仅包括断线重连、反复注册 Watcher 和 NodeExistsException 异常等，还对原生的 Zookeeper API 进行封装，提供了一套更加易用易读的 Fluent 风格的 API。

测试实例使用的 maven 版本：

```xml
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-framework</artifactId>
    <version>2.4.0</version>
</dependency>
```

#### Curator创建会话

具体步骤：
* 使用 CuratorFrameworkFactory 的工厂方法创建客户端；
* 然后调用 CuratorFramework 的 start() 方法开启动会话；

使用 Curator 创建会话：
```java
public class CreateSessionSample {
    public static void main(String[] args) throws InterruptedException {
        // 先定义自己的重试机制
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(
                1000,   // 初次休眠时间
                3);     // 最大重试次数
        // 创建会话
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                "localhost:2181",  // 服务器列表
                5000,              // 会话超时事件 
                3000,              // 连接超时时间
                retryPolicy        // 重试机制
                );
        client.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
```

使用 Fluent 风格的 API 接口创建会话：
```java
public class CreateSessionSampleFluent {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("base")  // 定义命名空间，该客户端对节点的操作都是基于该目录下
                .build();
        client.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
```

#### Curator创建节点

```java
public class CreateNodeSample {
    static String path = "/zk-book/c1";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("localhost:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.create().creatingParentsIfNeeded()   // 该方法可实现递归创建节点
                .withMode(CreateMode.EPHEMERAL)     // 设定属性，默认为持久节点
                .forPath(
                        path,                       // 节点路径
                        "init".getBytes()           // 节点内容，默认为空
                        );
    }
}
```

#### Curator删除节点

```java
public class DelDataSample {
    static String path = "/zk-book/c1";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("localhost:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, "init".getBytes());
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath(path);
        client.delete().guaranteed()         // 强制删除节点
                .deletingChildrenIfNeeded()  // 递归的删除子节点
                .withVersion(stat.getVersion()).forPath(path);
    }
}
```

guaranteed() 方法可确保在由于网络等问题导致删除失败的情况下，只要客户端还存在，则在后台由重试机制，直到删除成功。

#### Curator读取节点数据

```java
public class getDataSample {
    static String path = "/zk-book";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("localhost:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, "init".getBytes());
        Stat stat = new Stat();
        System.out.println(new String(client.getData()
                .storingStatIn(stat)        // 存储节点的状态信息
                .forPath(path)));
    }
}
```

#### Curator更新节点数据

```java
public class SetDataSample {
    static String path = "/zk-book";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("localhost:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception{
        client.start();
        client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, "init".getBytes());
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath(path);
        System.out.println("Success set node for : " + path + ", new version : "
                + client.setData().withVersion(stat.getVersion()).forPath(path).getVersion());
        try {
            client.setData().withVersion(stat.getVersion()).forPath(path);
        } catch (Exception e) {
            System.out.println("Fail set node due to + " + e.getMessage());
        }
    }
}
```

测试输出结果如下：
```jshelllanguage
Success set node for : /zk-book, new version : 1
Fail set node due to + KeeperErrorCode = BadVersion for /zk-book
```

从结果可以看出，第二次使用过期的版本信息，则会更新失败。

#### Curator异步接口

Curator 引入了 BackgroundCallback 接口来处理异步返回的信息。BackgroundCallback 接口只有一个 processResult 方法，该方法在操作完成后被异步调用。

异步创建节点：
```java
public class CreateNodeBackgroundSample {
    static String path = "/zk-book";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("localhost:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();
    static CountDownLatch semaphore = new CountDownLatch(2);
    static ExecutorService tp = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        client.start();
        System.out.println("Main thread : " + Thread.currentThread().getName());

        // 此处传入自定义的 Executor
        client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground((curatorFramework, curatorEvent) -> {
                    System.out.println("event[code: " + curatorEvent.getResultCode() + ", type: " + curatorEvent.getType() + "]");
                    System.out.println("Thread of processResult: " + Thread.currentThread().getName());
                    semaphore.countDown();
                }, tp).forPath(path, "init".getBytes());

        // 此处没有传入自定义的 Executor
        client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground((curatorFramework, curatorEvent) -> {
                    System.out.println("event[code: " + curatorEvent.getResultCode() + ", type: " + curatorEvent.getType() + "]");
                    System.out.println("Thread of processResult: " + Thread.currentThread().getName());
                    semaphore.countDown();
                }).forPath(path, "init".getBytes());
        semaphore.await();
        tp.shutdown();
    }
}
```

可以看到输出结果：
```jshelllanguage
Main thread : main
event[code: -110, type: CREATE]
Thread of processResult: main-EventThread
event[code: 0, type: CREATE]
Thread of processResult: pool-3-thread-1
```

程序使用异步接口 inBackground 来创建节点，第一次返回 0 ，表示成功；第二次返回 -110，表示节点已经存在。 