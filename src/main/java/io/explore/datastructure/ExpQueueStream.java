package io.explore.datastructure;

import reactor.core.publisher.Flux;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class ExpQueueStream {
    public static void main(String[] args) throws InterruptedException {
        fillQueue();
        //takeFromQueue();
        TimeUnit.SECONDS.sleep(2);
        streamFromQueOtherThread();
        //streamFromQueue();
    }

    private static BlockingQueue<String> myQueue = new ArrayBlockingQueue<>(10);
    private  static Random r = new Random();


    private static void fillQueue() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                while (true) {
                    myQueue.put("Message at : " + LocalTime.now().toString());
                    System.out.println(Thread.currentThread().getName() + " : PUT :" + myQueue.size());
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(2500));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void streamFromQueOtherThread(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(ExpQueueStream::liveStreamFromQueue);
    }
    /* consider current and future items from queue*/
    private static void liveStreamFromQueue() {
        Stream<String> streamFromQueue = Stream.generate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(r.nextInt(400));
                System.out.println(Thread.currentThread().getName() + " : GET : " + myQueue.size());
                return myQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Interrupted.";
        });
        Flux.fromStream(streamFromQueue).log().subscribe();
    }

    private static void takeFromQueue() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            while (true) {
                try {
                    String item = myQueue.take();
                    System.out.println(Thread.currentThread().getName() + " : " + item);
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
