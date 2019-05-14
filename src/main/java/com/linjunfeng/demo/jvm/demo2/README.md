### 栈溢出

通过 -Xss 参数来设置栈容量。
```jvm
-Xss228k
```

由前文可知道虚拟机栈和本地方法栈有两种异常：
* 如果线程请求的栈深度大于虚拟机所允许的最大深度，则抛出 StackOverflowError 异常。
* 如果虚拟机在扩展栈时无法申请到足够的空间，则抛出 OutOfMemoryError 异常。

代码：
```java
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength ++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: " + oom.stackLength);
            throw e;
        }
    }
}
```

运行结果：
```
stack length: 1517
Exception in thread "main" java.lang.StackOverflowError
	at com.linjunfeng.demo.jvm.demo2.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:7)
	at com.linjunfeng.demo.jvm.demo2.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)

//...
```
由结果可知道，单线程情况下，无论是深度不足还是无法分配，都是抛出 StackOverflowError 异常。

解决异常：

在使用虚拟机默认参数下，栈深度大多数可达到 1000 ～ 2000，对于正常的方法调用是够用的。但如果因为建立多线程导致内存溢出，在不能减少线程数和更换 64 位虚拟机的情况下，只能通过减少最大堆和减少栈容量来换取更多的线程。