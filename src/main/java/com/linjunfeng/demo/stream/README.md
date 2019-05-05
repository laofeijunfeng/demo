### 使用流库

流提供了一种让我们可以在比集合更高的概念级别上指定计算的数据视图。通过流，我们可以说明想要完成什么任务，而非说明实现什么任务。将操作的调度留给具体实现去解决。

* [从迭代到流操作](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/stream/demo1)
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

* [流的创建](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/stream/demo2)
    * Stream.of() : 产生一个元素给定值的流；
    * Stream.empty() : 产生一个空流；
    * Stream.generate() : 产生一个无限流；
    * Stream.iterate() : 产生一个无限流；
    * collection.stream() : 由 collection 转换为流；
    * ...
    
* [filter()、map() 和 flatMap()](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/stream/demo3)
    * filter() : 产生一个流，元素与某种条件相匹配；
    * map() : 产生一个流，包含了应用该函数后所产生的所有结果；
    * flatMap() : 通过将 mapper 应用于当前流中所有元素所产生的结果连接到一起而获得的；

* [抽取子流和连接流](https://github.com/laofeijunfeng/demo/tree/master/src/main/java/com/linjunfeng/demo/stream/demo4)
    * limit() : 产生一个流，其中包含了当前流中最初的 maxSize 个元素；
    * skip() : 产生一个流，包含当前流中除了前 n 个元素之外的所有元素；
    * concat() : 产生一个流，包含作为参数传入的第一个流加上第二个流；

* [其他的流转换]()
    * distinct() : 产生一个流，包含当前流中所有不同的元素；
    * sorted() : 产生一个流，包含当前流中所有元素按照顺序排序，可通过参数传入比较条件；
    * peek() : 产生一个流，它与当前流中的元素相同，在获取其中每个元素时，会传递给 action；