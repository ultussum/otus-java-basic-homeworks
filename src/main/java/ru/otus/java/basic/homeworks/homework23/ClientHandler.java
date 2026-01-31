package ru.otus.java.basic.homeworks.homework23;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket socket;
    private final Dispatcher dispatcher;

    public ClientHandler(Socket socket, Dispatcher dispatcher) {
        this.socket = socket;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[8192];
            int n = socket.getInputStream().read(buffer);
            if (n < 1) {
                return;
            }
            String rawRequest = new String(buffer, 0, n);
            Request request = new Request(rawRequest);
            dispatcher.execute(request, socket.getOutputStream());

        } catch (IOException e) {
            System.err.println("Ошибка при обработке клиента: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Не удалось закрыть сокет: " + e.getMessage());
            }
        }
    }
}
