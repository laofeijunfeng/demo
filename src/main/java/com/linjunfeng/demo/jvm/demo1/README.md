### 堆溢出

配置 Java 堆的大小为 20MB，不可扩展（将堆最小值 -Xms 参数和最大值 -Xmx 参数设置一样即可），通过设置 -XX:+HeapDumpOnOutOfMemoryError 让虚拟机在出现内存溢出时 Dump 出当前的内存堆转储快照以便事后进行分析。

```jvm
-Xms20m
-Xmx20m
-XX:+HeapDumpOnOutOfMemoryError
```

代码：
```java
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> oomObjects = new ArrayList<>();
        while (true) {
            oomObjects.add(new OOMObject());
        }
    }
}
```

运行结果：
```
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3210)
	at java.util.Arrays.copyOf(Arrays.java:3181)

// ...
```

解决异常：

可先通过内存印象分析功能（如 Eclipse Memory Analyzer）对 Dump 出来的堆转储快照进行分析，确认是哪个地方出现内存泄漏。

如果是内存泄漏，可进一步通过工具查看泄漏对象到 GC Roots 的引用链，来准确的定位出泄漏代码的位置。

如果是内存不足，可通过配置虚拟机参数或增加机器物理内存来解决。