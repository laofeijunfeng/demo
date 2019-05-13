### 多线程下使用 SimpleDateFormat

一般我们使用SimpleDateFormat的时候会把它定义为一个静态变量，避免频繁创建它的对象实例。
```java
public class Demo1_1 {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Date date) {
        return sdf.format(date);
    }

    public static Date prase (String strDate) throws ParseException {
        return sdf.parse(strDate);
    }

    public static void main(String[] args) {
        System.out.println(sdf.format(new Date()));
    }
}
```
但如果使用多线程的情况下，则会出现异常，比如下面使用多线程的情况：
```java
public class Demo1_2 extends Thread {
    @Override
    public void run() {
        try {
            Date date = Demo1_1.prase("2019-01-01 00:00:00");
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            Demo1_2 demo1_2 = new Demo1_2();
            demo1_2.start();
        }
    }
}
```
结果打印如下：
```
Tue Jan 01 00:00:00 CST 2019
Thu Jan 01 00:00:00 CST 2201
Tue Jan 01 00:00:00 CST 2019
```
还会抛出传入字段为空或者格式不正确的异常。

#### 多线程不安全原因

查看 SimpleDateFormat 类的源码可以知道，SimpleDateFormat的format方法实际操作的就是Calendar。因为我们声明SimpleDateFormat为static变量，那么它的Calendar变量也就是一个共享变量，可以被多个线程访问。<br/>

假设线程A执行完calendar.setTime(date)，把时间设置成2019-01-02，这时候被挂起，线程B获得CPU执行权。线程B也执行到了calendar.setTime(date)，把时间设置为2019-01-03。线程挂起，线程A继续走，calendar还会被继续使用(subFormat方法)，而这时calendar用的是线程B设置的值了，而这就是引发问题的根源，出现时间不对，线程挂死等等。<br/>

#### 解决方案
* 每次调用直接创建新的 SimpleDateFormat 对象；
```java
public class Demo1_1 {
    // ...
    
    public static Date prase (String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(strDate);
    }
    
    // ...
}
```
* 使用 synchronized 关键字进行修饰；
```java
public class Demo1_1 {
    // ...
    
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date prase (String strDate) throws ParseException {
        synchronized (sdf) {
            return sdf.parse(strDate);
        }
    }
    
    // ...
}
```