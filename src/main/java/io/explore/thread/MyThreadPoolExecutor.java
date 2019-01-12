package io.explore.thread;

import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPoolExecutor {

    private ThreadPoolExecutor executor = null;
    private AtomicInteger counter = new AtomicInteger();
    private AtomicInteger rCounter = new AtomicInteger();

    public static void main(String[] args) {
        MyThreadPoolExecutor threadPoolExample = new MyThreadPoolExecutor();
        threadPoolExample.createThreadPool();//To create the ThreadPool
        threadPoolExample.submitTask();//To submit the Task

        threadPoolExample.printStat();
    }

    private void createThreadPool() {
        int poolSize = 1;
        int maxPoolSize = 2;
        int queueSize = 5;
        long aliveTive = 5000;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(queueSize);
        executor = new ThreadPoolExecutor(poolSize, maxPoolSize, aliveTive,
                TimeUnit.MILLISECONDS, queue, new JobRejectionHandler()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(String.format("Queue size : %s , Active Thread : %s",executor.getQueue().size(), executor.getActiveCount()));
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                counter.addAndGet(1);
                super.afterExecute(r, t);
            }
        };
    }
    private void submitTask() {
        /*Submit AsunchronousTask to ThreadPool */
        for (int i = 1; i <= 100; i++) {
            executor.execute(new JobTask("Job" + i));
        }
    }

    private void printStat(){
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.println(String.format("Task by Executor : %d, Task by Rejection Handler : %d",counter.get(), rCounter.get()));
    }

    /*RejectionHandler to handle any rejected Task*/
    class JobRejectionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable arg0, ThreadPoolExecutor arg1){
            rCounter.addAndGet(1);
            JobTask jobTask = (JobTask) arg0;
            System.out.println("JobId:" + jobTask.getJobId() + " Running through RejectionHandler");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

/*Asyncchrounous Task Should extend TimerTask*/
class JobTask extends TimerTask {
    private String jobId = "";

    public JobTask(String jobId) {
        this.jobId = jobId;
    }
    public String getJobId() {
        return jobId;
    }
    @Override
    public void run() {
        System.out.println("JobId:" + jobId + " Running through Thread:" + Thread.currentThread().getName());
        /*Make the Task to sleep for 5 seconds,so that the task will be busy*/
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}