package io.explore.rxJava;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class MyFlow {
    public static void main(String[] args) throws InterruptedException{
        parallelFlow();
    }

    private static void parallelFlow(){
        Flowable.range(1,10)
                .parallel()
                .runOn(Schedulers.computation())
                .map(MyFlow::mapIt)
                .sequential()
                .blockingSubscribe(MyFlow::printIt);
    }

    private static Integer mapIt(Integer i) throws InterruptedException{
        System.out.println(Thread.currentThread()+" : "+i);
        Thread.sleep(10);
        return i*i;
    }

    private static void printIt(Integer i){
        System.out.println(Thread.currentThread()+" "+i);
    }
}
