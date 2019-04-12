package io.explore.java8;

import java.util.concurrent.CompletableFuture;

public class RacePlatform {

    public static void main(String[] args) {
        CompletableFuture<Horse> horse1 = CompletableFuture.supplyAsync(() -> {
            return new Horse("Horse1").goRun();
        });

        CompletableFuture<Horse> horse2 = CompletableFuture.supplyAsync(() -> {
            return new Horse("Horse2").goRun();
        });

        CompletableFuture<Horse> horse3 = CompletableFuture.supplyAsync(() -> {
            return new Horse("Horse3").goRun();
        });

        CompletableFuture<Horse> horse4 = CompletableFuture.supplyAsync(() -> {
            return new Horse("Horse4").goRun();
        });

        CompletableFuture<Horse> horse5 = CompletableFuture.supplyAsync(() -> {
            return new Horse("Horse5").goRun();
        });

        CompletableFuture.anyOf(horse1, horse2, horse3, horse4, horse5)
                .thenApply(speedyOne -> (Horse) speedyOne)
                .thenAccept(RacePlatform::printResult)
                .join();
    }

    private static void printResult(Horse firstHorse){
        createNewLine(2);
        System.out.println("\t\t\t-----------------------------------------------------------");
        System.out.printf("\t\t\t|              Fastest horse :     %s                  | \n", firstHorse.getName().toUpperCase());
        System.out.println("\t\t\t-----------------------------------------------------------");
        createNewLine(1);
    }

    private static void createNewLine(int num) {
        for (int i = 0; i <= num; ++i) System.out.println();
    }
}
