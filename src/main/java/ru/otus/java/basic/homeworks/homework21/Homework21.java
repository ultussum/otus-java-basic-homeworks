package ru.otus.java.basic.homeworks.homework21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Homework21 {
    public static void main(String[] args) {
        int[] currentStep = {0};
        ExecutorService pool = Executors.newFixedThreadPool(3);
        final Object pojo = new Object();
        for (int i = 0; i < 5; i++) {
            pool.execute(() -> {
                synchronized (pojo) {
                    while (currentStep[0] % 3 != 0) {
                        try {
                            pojo.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    System.out.print("A");
                    currentStep[0]++;
                    pojo.notifyAll();
                }
            });
            pool.execute(() -> {
                synchronized (pojo) {
                    while (currentStep[0] % 3 != 1) {
                        try {
                            pojo.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    System.out.print("B");
                    currentStep[0]++;
                    pojo.notifyAll();
                }
            });
            pool.execute(() -> {
                synchronized (pojo) {
                    while (currentStep[0] % 3 != 2) {
                        try {
                            pojo.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    System.out.print("C");
                    currentStep[0]++;
                    pojo.notifyAll();
                }
            });
        }
        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
