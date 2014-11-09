package ru.spbau.ustyuzhanina.task1;

import java.util.concurrent.TimeUnit;

/**
 * Created by kate on 08.11.14.
 */
abstract public class WorkerThread implements Runnable {
    protected final ThreadPool threadPool;

    public volatile boolean threadDead = false;
    protected int currentTaskId;
    protected Task currentTask;
    protected boolean spuriousWakeUp = false;


    protected WorkerThread(ThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    protected abstract Task awaitTask() throws InterruptedException;

    protected abstract void threadInterrupted();

    @Override
    public void run() {
        while (!threadDead && !threadPool.isDone()) {
            try {
                synchronized (threadPool) {
                    currentTask = awaitTask();
                    if (spuriousWakeUp){
                        spuriousWakeUp = false;
                        continue;
                    }
                    if (currentTask != null) {
                        currentTaskId = currentTask.id;
                        threadPool.getThreadsTasksMap().put(currentTask.getId(), Thread.currentThread());
                        System.out.println("task with id = " + currentTask.getId() + " was started with thread " + Thread.currentThread().getId());
                    }
                }
                if (currentTask != null) {
                    currentTask.run();
                    threadPool.getThreadsTasksMap().remove(currentTask.getId());
                    System.out.println("task with id = " + currentTask.getId() + " was finished by thread " + Thread.currentThread().getId());
                }
            } catch (InterruptedException e) {
                threadInterrupted();
            }
        }
    }
}