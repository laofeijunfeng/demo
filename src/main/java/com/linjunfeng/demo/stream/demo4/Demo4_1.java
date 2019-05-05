package com.linjunfeng.demo.stream.demo4;

import com.linjunfeng.demo.stream.utils.ToolUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Demo4_1 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("aaa", "bbb", "ccc", "dd");

        Stream<Double> randoms = Stream.generate(Math::random).limit(10);
        ToolUtil.show("limit 使用的结果", randoms);

        Stream<String> skipWords = words.stream().skip(2);
        ToolUtil.show("skip 使用的结果", skipWords);

        Stream<String> combined = Stream.concat(ToolUtil.letters("hello"), ToolUtil.letters("world"));
        ToolUtil.show("concat 使用的结果", combined);
    }
}
