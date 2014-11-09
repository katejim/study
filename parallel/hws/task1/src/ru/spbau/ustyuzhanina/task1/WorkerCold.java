package ru.spbau.ustyuzhanina.task1;

import java.util.concurrent.TimeUnit;

/**
 * Created by kate on 08.11.14.
 */
public class WorkerCold extends WorkerThread {
    private final int timeout;

    public WorkerCold(ThreadPool threadPool, int timeout) {
        super(threadPool);
        this.timeout = timeout;
    }

    @Override
    protected Task awaitTask() throws InterruptedException {
        long time = System.currentTimeMillis();
        threadPool.wait(timeout);
        long diff = System.currentTimeMillis() - time;
        if (diff < timeout) {
            spuriousWakeUp = true;
            return null;
        }
        synchronized (threadPool) {
            if (threadPool.getTaskQueue().size() != 0) {
                return threadPool.getTaskQueue().take();
            } else {
                System.out.println("thread with id = " + Thread.currentThread().getId() + " was killed");
                threadDead = true;
                threadPool.getThreadsList().remove(Thread.currentThread());
            }
        }
        return null;
    }

    @Override
    protected void threadInterrupted() {
        synchronized (threadPool) {
            threadPool.getThreadsTasksMap().remove(currentTaskId);
            threadDead = true;
            threadPool.getThreadsList().remove(Thread.currentThread());
        }
    }
}
