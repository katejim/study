package ru.spbau.ustyuzhanina.task1;

/**
 * Created by kate on 08.11.14.
 */
public class WorkerHot extends WorkerThread {
    protected WorkerHot(ThreadPool threadPool) {
        super(threadPool);
    }

    @Override
    protected Task awaitTask() throws InterruptedException {
        threadPool.wait();
        if (threadPool.getTaskQueue().size() != 0){
            return threadPool.getTaskQueue().take();
        }
        return null;
    }

    @Override
    protected void threadInterrupted() {
        threadPool.getThreadsTasksMap().remove(currentTaskId);
    }
}
