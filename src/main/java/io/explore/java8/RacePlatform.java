package io.explore.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class RacePlatform {

    public static void main(String[] args) throws Exception {

        RacePlatform platform = new RacePlatform();
        List<CompletableFuture<Horse>> futures = platform.initialize();

        platform.doRaceFirst(futures);
        platform.doRaceAll(futures);

    }

    private void doRaceFirst(List<CompletableFuture<Horse>> futures) {
        CompletableFuture[] arrayOfCompletableFutures = futures.toArray(new CompletableFuture[0]);

        CompletableFuture.anyOf(arrayOfCompletableFutures)
                .thenApply(speedyOne -> (Horse) speedyOne)
                .thenAccept(RacePlatform::printResult)
                .join();
    }

    private void doRaceAll(List<CompletableFuture<Horse>> futures) throws InterruptedException, java.util.concurrent.ExecutionException {
        CompletableFuture[] arrayOfCompletableFutures = futures.toArray(new CompletableFuture[0]);
        Comparator<Horse> byFinishTime = (horse1, horse2) -> (int)(horse1.getFinishTime() - horse2.getFinishTime());

        CompletableFuture.allOf(arrayOfCompletableFutures)
                .thenApply(aVoid -> futures
                        .stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()))
                .get()
                .stream()
                .sorted(byFinishTime)
                .forEachOrdered(System.out::println);
    }

    private List<CompletableFuture<Horse>> initialize() {

        List<CompletableFuture<Horse>> futures = new ArrayList<>();
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse1").goRun()));
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse2").goRun()));
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse3").goRun()));
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse4").goRun()));
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse5").goRun()));
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse6").goRun()));
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse7").goRun()));

        return futures;
    }

    private static void printResult(Horse firstHorse) {
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
