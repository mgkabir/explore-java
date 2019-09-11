package io.explore.stream;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExp {
    public static void main(String[] args) {
        infiniteStream();
        reduceExperiment();
        collectExperiment();
        statefulFunction();
        statelessFunction();
    }

    private static void infiniteStream() {
        System.out.println("-------------------------------------------------   ");
        Supplier<UUID> uuidSupplier = () -> UUID.randomUUID();

        Stream<UUID> infiniteStreamOfUUID = Stream.generate(uuidSupplier);

        List<UUID> listOfUUID = infiniteStreamOfUUID
                .limit(2)
                .collect(Collectors.toList());
        listOfUUID.forEach(StreamExp::printUpperCase);
    }

    private static void printUpperCase(UUID uuid) {
        System.out.println(uuid.toString().toUpperCase());
    }

    private static void reduceExperiment() {
        System.out.println("-------------------------------------------------   ");
        Integer sum = Stream.of("1", "2", "3", "4", "5")
                .filter(num -> Integer.valueOf(num) % 2 != 0)
                .map(Integer::valueOf)
                .reduce((num1, num2) -> num1 + num2)
                .get();
        System.out.println(String.format("Sum of Odd Numbers : %d", sum));

        System.out.println("-------------------------------------------------   ");
        int sumOfSuares = Stream.of(1, 2, 3, 4, 5)
                .filter(num -> num % 2 != 0)
                .map(num -> Math.pow(num, 2))
                .reduce(0d, (num1, num2) -> num1 + num2)
                .intValue();
        System.out.println(String.format("Sum of Squares of Odd numbers : %s", sumOfSuares));

        /* Combiner (the 3rd argument in reduce) executes ONLY for PARALLEL operation*/
        Integer reducedUsingCombiner = Stream.of(1, 2, 3, 4, 5).parallel().reduce(0, (a, b) -> {
            System.out.println("In accumulator: " + a + ", " + b);
            return a + b;
        }, (a, b) -> {
            System.out.println("In combiner: " + a + ", " + b);
            return a + b;
        });
        System.out.println(String.format("Sum of Numbers (Parallel Execution) : %s", reducedUsingCombiner));
    }

    private static void collectExperiment() {
        String collectedString = Stream.of("K", "A", "B", "I", "R").collect(Collectors.joining(" "));
        System.out.println(String.format("Collected String : %s", collectedString));

        System.out.println("-------------------------------------------------   ");

        List<String> X = Stream.of("S", "H", "A", "Y", "A", "N")
                .parallel()
                .collect(() -> new ArrayList<>(), (a, e) -> {
                    System.out.println("In accumulator: " + a + ", " + e);
                    a.add(e.toLowerCase());
                }, (a, e) -> {
                    System.out.println("In combiner: " + a + ", " + e);
                    a.addAll(e);
                });
        System.out.println("X : " + X);

        HashMap<Object, Object> X2 = Stream.of("S", "H", "A", "Y", "A", "N")
                .parallel()
                .collect(() -> new HashMap<>(),
                        (a, e) -> a.put(e.toLowerCase(), e),
                        (c1, c2) -> c1.putAll(c2));

        System.out.println("X2 : " + X2); // Duplicates removed as collector is a SET
    }

    /* STATEFUL function EATS UP all parallelism ADVANTAGE and becomes NON-DETERMINISTIC*/
    private static void statefulFunction() {
        for (int i = 0; i < 5; i++) {
            Set<String> stringSet = new HashSet<>();
            System.out.println(Stream.of("S","H","A","Y","A","N","K","A","B","I","R","L","E","E","N","A")
                    .parallel()
                    .map(
                            // stateful function
                            e -> {
                                if (stringSet.add(e))
                                    return e.toLowerCase();
                                else
                                    return "";
                            })
                    .collect(Collectors.joining()));
        }
    }

    private static void statelessFunction() {
        for (int i = 0; i < 5; i++) {
            Set<String> stringSet = new HashSet<>();
            System.out.println(Stream.of("S","H","A","Y","A","N","K","A","B","I","R","L","E","E","N","A")
                    .parallel()
                    .map(e-> e.toLowerCase())
                    .collect(Collectors.joining()));
        }
    }
}
