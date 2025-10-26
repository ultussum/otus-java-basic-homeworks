package ru.otus.java.basic.homeworks.homework14;

public class TimeCounter {
    private static long time;

    public static void startTime() {
        time = System.nanoTime();
    }

    public static void endTime() {
        double timeResult = (System.nanoTime() - time) / 1000000.0;
        System.out.println("Выполнено за " + timeResult+ " мс.");
    }
}
