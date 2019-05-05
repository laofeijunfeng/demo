package com.linjunfeng.demo.stream.demo2;

import com.linjunfeng.demo.stream.utils.ToolUtil;

import java.math.BigInteger;
import java.util.stream.Stream;

public class Demo2_1 {
    public static void main(String[] args) {
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        ToolUtil.show("song", song);

        Stream<String> silence = Stream.empty();
        ToolUtil.show("silence", silence);

        Stream<String> echos = Stream.generate(() -> "Echo");
        ToolUtil.show("echos", echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        ToolUtil.show("random", randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
        ToolUtil.show("integers", integers);
    }
}
