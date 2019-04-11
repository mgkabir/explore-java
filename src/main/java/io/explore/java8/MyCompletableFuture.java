package io.explore.java8;

import java.util.Random;
import java.util.concurrent.*;

public class MyCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //thenApplyFuture();
        thenCombineFuture();
    }

    private static void thenCombineFuture() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();

        long start = System.currentTimeMillis();

        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(MyCompletableFuture::getInteger,executor);
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(MyCompletableFuture::getInteger,executor);
        System.out.println(Thread.currentThread());

        CompletableFuture<Integer> combinedFuture = completableFuture1
                .thenCombine(completableFuture2, (cf1, cf2) -> cf1 + cf2);

        System.out.println(Thread.currentThread()+ " : thenCombineFuture : " + combinedFuture.get() + " Time taken : "+ (System.currentTimeMillis() - start));

        executor.shutdown();
    }

    private static void thenApplyFuture() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        future.complete(5);
        Integer integer = future
                .thenApply(x -> x + 1)
                .get();
        System.out.println("thenApplyFuture : " + integer);
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
