package ru.otus.java.basic.homeworks.homework23;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private Dispatcher dispatcher;
    private ExecutorService executorService;

    public Server(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
        this.executorService = Executors.newFixedThreadPool(2);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            while (true){
                try{
                    Socket socket = serverSocket.accept();
                    executorService.submit(new ClientHandler(socket, dispatcher));
                } catch (IOException e) {
                    System.err.println("Ошибка при приёме подключения: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
