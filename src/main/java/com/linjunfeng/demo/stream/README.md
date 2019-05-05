### 使用流库

流提供了一种让我们可以在比集合更高的概念级别上指定计算的数据视图。通过流，我们可以说明想要完成什么任务，而非说明实现什么任务。将操作的调度留给具体实现去解决。

* [从迭代到流操作]()
    > 在处理集合时，我们通常会迭代遍历元素，并且在每个元素上执行操作。
    ```
    List<String> words = Arrays.asList("aaa", "bbb", "ccc", "dd");
    long count = 0;
    for (String w : words) {
        if (w.length() > 2)
            count++;
    }
    System.out.println("通过遍历数组得到的 count = " + count);
    ```
    > 而使用流时，相同的操作则看起来如下：
    ```
    List<String> words = Arrays.asList("aaa", "bbb", "ccc", "dd");
    count = words.stream().filter(w -> w.length() > 2).count();
    System.out.println("通过流操作得到的 count = " + count);
    ```
    > 从表面上看，流的操作和集合很像，都可以转换和获取数据。但是他们之间存在着明显的差异：
    >> 1、流并不存储元素。这些元素可能存储在底层的集合中，或者是按需生成的；<br/>
        2、流操作不会修改数据源；<br/>
        3、流的操作是尽可能惰性执行的；

* [流的创建]()
    * Stream.of() : 产生一个元素给定值的流；
    * Stream.empty() : 产生一个空流；
    * Stream.generate() : 产生一个无限流；
    * Stream.iterate() : 产生一个无限流；
    * collection.stream() : 由 collection 转换为流；
    * ...
    
    