package ru.spbau.ustyuzhanina.task1;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Kate on 03.11.14.
 */
public class ThreadPool {
    private final int hotThreadCount;
    private final int timeout;

    private volatile boolean done = false;
    private static int tasksCounter = 0;

    private ArrayList<Thread> threadsList = new ArrayList<>();
    private BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();
    private Map<Integer, Thread> threadsTasksMap = new ConcurrentHashMap<>();


    public ThreadPool(int hotThreadCount, int timeout) {
        this.hotThreadCount = hotThreadCount;
        this.timeout = timeout;
        for (int i = 0; i < hotThreadCount; ++i) {
            Thread thread = new Thread(new WorkerHot(this));
            synchronized (threadsList) {
                if (!done) {
                    threadsList.add(thread);
                }
            }
            thread.start();
        }
    }

    public int addTask(int duration) {
        Task task = new Task(tasksCounter++, duration);
        taskQueue.add(task);
        synchronized (this) {
            if (threadsList.size() != threadsTasksMap.size()) {
                this.notify();
                return task.getId();
            }
        }

        synchronized (this) {
            if (threadsList.size() == threadsTasksMap.size()) {
                Thread newThread = new Thread(new WorkerCold(this, timeout));
                threadsList.add(newThread);
                System.out.println("thread with id " + newThread.getId() + " was added");
                newThread.start();
            }
        }

        System.out.println("task with this id = " + task.getId() + " was add to taskList");

        //чтобы поток успел заснуть перед тем как вызовется notify
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            this.notify();
        }

        return task.getId();
    }

    private Task findTask(int id) {
        for (Task task : taskQueue) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public void cancelTask(int id) {
        Thread thread = threadsTasksMap.get(id);
        System.out.println("task with id = " + id + " was canceled on thread with id = " + thread.getId());
        if (thread != null) {
            thread.interrupt();
        } else {
            //cancel unassigned tasks
            Task task = findTask(id);
            if (task == null) {
                System.out.println("no such task with id = " + id + " in taskList");
                return;
            } else {
                synchronized (this) {
                    taskQueue.remove(task);
                }
            }
        }
    }

    public void showData() {

        synchronized (this) {
            System.out.println("Thread in threadpool");
            for (Thread thread : threadsList) {
                System.out.println(thread.getId());
            }
        }
        System.out.println("Task number mapped on Thread in threadpool");
        for (Integer el : threadsTasksMap.keySet()) {
            System.out.println(el + " " + threadsTasksMap.get(el).getId());
        }
    }

    public void exit() {
        synchronized (threadsList) {
            for (Thread thread : threadsList) {
                System.out.println(thread.getId());
                thread.interrupt();
            }
            threadsList.clear();
            done = true;
        }
        System.out.println("threads were interrupted");
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public ArrayList<Thread> getThreadsList() {
        return threadsList;
    }

    public BlockingQueue<Task> getTaskQueue() {
        return taskQueue;
    }

    public Map<Integer, Thread> getThreadsTasksMap() {
        return threadsTasksMap;
    }
}
