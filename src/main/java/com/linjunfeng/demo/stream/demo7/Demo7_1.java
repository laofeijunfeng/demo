package com.linjunfeng.demo.stream.demo7;

import java.util.*;

public class Demo7_1 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("a", "bb", "cccc", "ddd", "eeeee", "ccc");
        Optional<String> optionalValue = words.stream().filter(s -> s.contains("fff")).findFirst();
        System.out.println(optionalValue.orElse("No word") + " contains fff");

        Optional<String> optionalString = Optional.empty();
        String result = optionalString.orElse("N/A");
        System.out.println("result: " + result);
        result = optionalString.orElseGet(() -> Locale.getDefault().getDisplayName());
        System.out.println("result: " + result);
        try {
            result = optionalString.orElseThrow(IllegalAccessError::new);
            System.out.println("result: " + result);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        optionalValue = words.stream().filter(s -> s.contains("ccc")).findFirst();
        optionalValue.ifPresent(s -> System.out.println(s + " contains ccc"));

        Set<String> results = new HashSet<>();
        optionalValue.ifPresent(results::add);
        Optional<Boolean> added = optionalValue.map(results::add);
        System.out.println("added: " + added);

        System.out.println(inverse(4.0).flatMap(Demo7_1::squareRoot));
        System.out.println(inverse(-1.0).flatMap(Demo7_1::squareRoot));
        System.out.println(inverse(0.0).flatMap(Demo7_1::squareRoot));
        Optional<Double> result2 = Optional.of(-4.0).flatMap(Demo7_1::inverse).flatMap(Demo7_1::squareRoot);
        System.out.println(result2);
    }

    private static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    private static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
