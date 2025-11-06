package ru.otus.java.basic.homeworks.homework15.Server;

public class ServerRun {
    public static final int PORT = 8080;

    public static void main(String[] args) {
        new Server(PORT).start();
    }
}
