package io.explore.java8;

import java.util.Random;
import java.util.concurrent.*;

public class MyCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenApplyFuture();

        thenCombineFuture();

    }

    private static void thenCombineFuture() {

        final long start = System.currentTimeMillis();

        ExecutorService executor = Executors.newCachedThreadPool();



        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(MyCompletableFuture::getInteger,executor);
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(MyCompletableFuture::getInteger,executor);
        CompletableFuture<Integer> completableFuture3 = CompletableFuture.supplyAsync(MyCompletableFuture::getInteger,executor);

        System.out.println(Thread.currentThread());

        completableFuture1
                .thenCombine(completableFuture2, (integer1, integer2) -> integer1 + integer2)
                .thenCombine(completableFuture3, (integer2, integer3) -> integer2 + integer3)
                .thenAccept(result-> System.out.println(Thread.currentThread()+ " : thenCombineFuture : " + result + " Time taken : "+ (System.currentTimeMillis() - start)))
                .join();

        System.out.println("-----------------------------------------------------");
        executor.shutdown();
    }

    private static void thenApplyFuture() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        future.complete(5);
        Integer integer = future
                .thenApply(x -> x + 1)
                .get();
        System.out.println("thenApplyFuture : " + integer);
        System.out.println("-----------------------------------------------------");
    }


    private static Integer getInteger() {
        int randomNum = new Random().nextInt(1000);
        System.out.println(Thread.currentThread() + " : getInteger : "+randomNum);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return randomNum;
    }
}
