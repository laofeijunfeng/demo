package com.linjunfeng.demo.stream.demo10;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo10_1 {
    public static void main(String[] args) {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, List<Locale>> countryToLocales = locales.collect(Collectors.groupingBy(Locale::getCountry));
        System.out.println("countryToLocales: " + countryToLocales);

        List<Locale> swissLocales = countryToLocales.get("CH");
        System.out.println("swissLocales: " + swissLocales);

        // 一个流只能用一次！！！！
        locales = Stream.of(Locale.getAvailableLocales());
        Map<Boolean, List<Locale>> enlishAndOtherLocales = locales.collect(Collectors.partitioningBy(l -> l.getLanguage().equals("en")));
        System.out.println("enlishAndOtherLocales: " + enlishAndOtherLocales);

        List<Locale> englishLocales = enlishAndOtherLocales.get(true);
        System.out.println("englishLocales: " + englishLocales);
    }
}
