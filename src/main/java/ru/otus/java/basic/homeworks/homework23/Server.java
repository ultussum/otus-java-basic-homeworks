package ru.otus.java.basic.homeworks.homework23;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private Dispatcher dispatcher;

    public Server(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            try (Socket socket = serverSocket.accept()) {
                byte[] buffer = new byte[8192];
                int n = socket.getInputStream().read(buffer);
                if (n < 1) {
                    return;
                }
                String rawRequest = new String(buffer, 0, n);
                Request request = new Request(rawRequest);
                dispatcher.execute(request, socket.getOutputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
