package ru.spbau.ustyuzhanina.task1;

/**
 * Created by kate on 03.11.14.
 */
public class Task implements Runnable {
    public final int id;
    private int duration;

    public Task(int id, int duration) {
        this.id = id;
        this.duration = duration;
    }

    @Override
    public void run() {
        long endTime = System.currentTimeMillis() + duration * 1000l;

        while (System.currentTimeMillis() < endTime && !Thread.currentThread().isInterrupted()) {
            Math.sqrt(2);
        }

//        if (Thread.currentThread().isInterrupted()) {
//            System.out.println("task with " + id + " interrupted");
//        }
    }

    public int getId() {
        return id;
    }
}
