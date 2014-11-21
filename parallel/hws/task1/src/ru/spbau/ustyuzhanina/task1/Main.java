package ru.spbau.ustyuzhanina.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by kate on 03.11.14.
 */
public class Main {
    public static void main(String[] args) {
        String usages = "add task          : add duration \n" +
                "remove task       : remove id\n" +
                "current situation : current\n" +
                "usages            : usage\n";


        System.out.println(usages);
        final ThreadPool threadPool = new ThreadPool(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        Scanner terminalInput = new Scanner(System.in);

        Runtime.getRuntime().addShutdownHook(new Thread() {
                                                 @Override
                                                 public void run() {
                                                     threadPool.setDone(true);
                                                     ArrayList<Thread> threadList;
                                                     synchronized (threadPool) {
                                                         threadList = threadPool.getThreadsList();
                                                         threadPool.notifyAll();
                                                         threadPool.setDone(true);
                                                     }
                                                     for (Thread thread : threadList) {
                                                         try {
                                                             thread.join();
                                                         } catch (InterruptedException e) {
                                                             break;
                                                         }
                                                     }
                                                     System.out.println("catch hook");
                                                 }
                                             }
        );

        while (true) {
            try {
                String value = terminalInput.nextLine();
                if ((value.startsWith("add"))) {
                    threadPool.addTask(Integer.parseInt(value.substring(4)));
                } else if ((value.startsWith("remove"))) {
                    threadPool.cancelTask(Integer.parseInt(value.substring(7)));
                } else if (value.equals("current")) {
                    threadPool.showData();
                } else if (value.equals("usage")) {
                    System.out.println(usages);
                } else {
                    System.out.println("Your put wrong value, try next time");
                }
            } catch (NoSuchElementException ex) {
                threadPool.exit();
                break;
            }
        }
    }
}

