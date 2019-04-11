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
        int corePoolSize = 1;
        int maxPoolSize = 5;
        int queueSize = 10;
        long keepAliveTime = 0;

        Semaphore semaphore = new Semaphore(queueSize + maxPoolSize + corePoolSize + 100);
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(queueSize);

        ThreadPoolExecutor.CallerRunsPolicy rejectedTaskHandler = new ThreadPoolExecutor.CallerRunsPolicy() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                System.out.println(" ..................Job getting handled by rejectedTaskHandler ....................... "+((JobTask)r).getJobId());
                super.rejectedExecution(r, e);
                rCounter.addAndGet(1);
            }
        };

        executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,
                TimeUnit.MILLISECONDS, queue, rejectedTaskHandler) {


            @Override
            public void execute(Runnable command) {
                try {
                    semaphore.acquire();
                    System.out.println(String.format("Queue size : %s , Active Thread : %s, Remaining permits %d "
                            , executor.getQueue().size(), executor.getActiveCount(), semaphore.availablePermits()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.execute(command);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                counter.addAndGet(1);
                super.afterExecute(r, t);
                semaphore.release();
            }
        };
    }

    private void submitTask() {
        /*Submit AsunchronousTask to ThreadPool */
        for (int i = 1; i <= 50; i++) {
            executor.execute(new JobTask("Job" + i));
        }
    }

    private void printStat() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.println(String.format("Task by Executor : %d, Task by Rejection Handler : %d", counter.get(), rCounter.get()));
    }

    /*RejectionHandler to handle any rejected Task*/
    /*class JobRejectionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable arg0, ThreadPoolExecutor arg1) {
            rCounter.addAndGet(1);
            JobTask jobTask = (JobTask) arg0;
            System.out.println("JobId:" + jobTask.getJobId() + " .......... Running through RejectionHandler .............................................. !!!!! .........");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/

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
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}