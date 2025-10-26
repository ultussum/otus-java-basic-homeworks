package ru.otus.java.basic.homeworks.homework14;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Homework14 {

    public static void main(String[] args) {
        double[] array = new double[100_000_000];

        System.out.println("Реализация №1");
        TimeCounter.startTime();
        for (int i = 0; i < array.length; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
        TimeCounter.endTime();

        System.out.println();

        System.out.println("Реализация №2");
        TimeCounter.startTime();
        Thread t1 = new Thread(() -> setThread(array, 0));
        Thread t2 = new Thread(() -> setThread(array, 1));
        Thread t3 = new Thread(() -> setThread(array, 2));
        Thread t4 = new Thread(() -> setThread(array, 3));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TimeCounter.endTime();
    }

    public static void setThread(double[] array, int numberThread) {
        int startIndex = numberThread * (array.length / 4);
        int endIndex = startIndex + (array.length / 4);
        if (numberThread == 3) {
            endIndex = array.length;
        }
        for (int i = startIndex; i < endIndex; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }

    }
}