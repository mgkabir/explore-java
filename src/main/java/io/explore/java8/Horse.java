package io.explore.java8;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Horse {
    private String name;
    private long FINISH_TIME;
    private String track;
    private int stepSize;

    public Horse(String name) {
        this.stepSize = getRandomNumberInRange(150, 200);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Horse goRun() {
        runFast();
        return this;
    }

    private void runFast() {
        long distanceCovered = 0;
        while (distanceCovered < RacePlatform.LENGTH_OF_TRACK) {
            long sleepTime = getRandomNumberInRange(50, 100);
            takeNap(sleepTime);
            distanceCovered += stepSize;
        }
        FINISH_TIME = System.currentTimeMillis();
        track = Thread.currentThread().getName();
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

    public long getFinishTime() {
        return FINISH_TIME;
    }

    public int getStepSize() {
        return stepSize;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public String toString() {
        return "Horse{" +
                "name='" + name + '\'' +
                ", FINISH_TIME=" + FINISH_TIME +
                ", track='" + track + '\'' +
                '}';
    }
}
