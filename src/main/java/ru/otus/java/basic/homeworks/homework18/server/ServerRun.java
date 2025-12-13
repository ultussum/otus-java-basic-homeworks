package ru.otus.java.basic.homeworks.homework18.server;

public class ServerRun {
    public static final int PORT = 8080;

    public static void main(String[] args) {
        new Server(PORT).start();
    }
}
