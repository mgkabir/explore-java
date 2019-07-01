package io.explore.reactor;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HotStream {
    public static void main(String[] args) throws InterruptedException {

        Flux<Object> flux = Flux.create(fluxSink -> {
            while (true) {
                fluxSink.next(System.currentTimeMillis());
            }
        });

        ConnectableFlux<Object> connectableFlux = flux
                .sample(Duration.ofSeconds(1)) // Throttling
                .publish();

        /*multiple subscribers */
        connectableFlux
                .subscribe(item -> {
                    System.out.println(Thread.currentThread().getName() + " -- S1 -- " + item);
                });
        connectableFlux
                .subscribe(item -> {
                    System.out.println(Thread.currentThread().getName() + " -- S2 -- " + item);
                });
        connectableFlux
                .subscribe(item -> {
                    System.out.println(Thread.currentThread().getName() + " -- S3 -- " + item);
                });
        connectableFlux
                .subscribe(item -> {
                    System.out.println(Thread.currentThread().getName() + " -- S4 -- " + item);
                });

        TimeUnit.SECONDS.sleep(2);

        connectableFlux.connect(); // does not emmit until connect

    }


}
