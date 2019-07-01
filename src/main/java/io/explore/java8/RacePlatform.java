package io.explore.java8;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class RacePlatform {

    public static final long LENGTH_OF_TRACK = 10_000;
    private long start = System.currentTimeMillis();

    public static void main(String[] args) throws Exception {

        RacePlatform platform = new RacePlatform();

        List<CompletableFuture<Horse>> futures = platform.initialize();

        //platform.doRaceFirst(futures);
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
        Comparator<Horse> byFinishTime = (horse1, horse2) -> (int) (horse1.getFinishTime() - horse2.getFinishTime());

        List<Horse> orderedList = CompletableFuture.allOf(arrayOfCompletableFutures)
                .thenApply(aVoid -> futures
                        .stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()))
                .get()
                .stream()
                .sorted(byFinishTime).collect(Collectors.toList());
        createNewLine(1);
        System.out.printf("\t\t\t Name (Step length)\t\t\t\t Time Taken \t\t\t\t\t\t Lane ");
        Iterator<Horse> horseIt = orderedList.iterator();
        while (horseIt.hasNext()) {
            Horse horse = horseIt.next();
            LocalDateTime fTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(horse.getFinishTime() - start), ZoneId.systemDefault());
            System.out.printf("\n\t\t\t %s (%s) \t\t\t\t\t %s \t\t\t %s", horse.getName(), horse.getStepSize(), fTime.format(DateTimeFormatter.ofPattern("m : s : n")), horse.getTrack());
        }
        createNewLine(1);
    }

    private List<CompletableFuture<Horse>> initialize() {

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

        List<CompletableFuture<Horse>> futures = new ArrayList<>();
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse 1").goRun(), fixedThreadPool));
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse 2").goRun()));
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse 3").goRun()));
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse 4").goRun(), fixedThreadPool));
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse 5").goRun()));
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse 6").goRun()));
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse 7").goRun()));
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse 8").goRun(), cachedThreadPool));
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse 9").goRun()));
        futures.add(CompletableFuture.supplyAsync(() -> new Horse("Horse10").goRun()));

        cachedThreadPool.shutdown();
        fixedThreadPool.shutdown();
        return futures;
    }

    private static void printResult(Horse champ) {
        createNewLine(1);
        System.out.println("\t\t\t-----------------------------------------------------------");
        System.out.printf("\t\t\t|              Fastest horse :     %s                  | \n", champ.getName().toUpperCase());
        System.out.println("\t\t\t-----------------------------------------------------------");
        createNewLine(1);
    }


    private static void createNewLine(int num) {
        for (int i = 0; i <= num; ++i) System.out.println();
    }
}