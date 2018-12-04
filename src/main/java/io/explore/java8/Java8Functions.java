package io.explore.java8;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class Java8Functions {
    public static void main(String[] args) {
        simpleFunctionExample();
        composeExample();

        Supplier<Double> doubleSupplier = Math::random;
        System.out.println(squareMyNumber(doubleSupplier));
    }

    /*Supplier function example*/
    private static double squareMyNumber(Supplier<Double> value) {
        return Math.pow(value.get(), 2);
    }

    private static void simpleFunctionExample() {
        List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");

        stooges.replaceAll(stooge -> stooge.toUpperCase()); // (Unary) Operator applied

        Map<String, Integer> nameMap = new HashMap<>();
        stooges.forEach(name -> {
            nameMap.computeIfAbsent(name, String::length);
        });

        /*print the value(s) now*/
        nameMap.forEach((k, v) -> {
            System.out.println(k + " <= Length => " + v);
        });
    }

    /* compose functions */
    private static void composeExample() {
        Function<Integer, String> intToString = Objects::toString;
        Function<String, String> quote = str ->  "'" + str + "'";

        Function quoteIntToStr = quote.compose(intToString);

        System.out.println(quoteIntToStr.apply(5));
    }

}