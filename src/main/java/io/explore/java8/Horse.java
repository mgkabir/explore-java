package io.explore.java8;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Horse {
    private String name;
    private static final long TARGET = 1_000;

    public Horse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Horse goRun(){
        runFast();
        return this;
    }

    private void runFast() {
        long total = 0;
        while(total < TARGET){
            long sleepTime = getRandomNumberInRange(5,10);
            System.out.println(Thread.currentThread()+ " : "+this.getName()+" : "+total);
            takeNap(sleepTime);
            total += sleepTime;
        }
    }

    private void takeNap(long sleepTime) {
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
