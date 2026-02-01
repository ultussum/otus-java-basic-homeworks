package ru.otus.java.basic.homeworks.homework24;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final Dispatcher dispatcher;
    public static final Logger logger = LogManager.getLogger(ClientHandler.class.getName());

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.dispatcher = new Dispatcher();
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
            logger.error("Ошибка при обработке клиента: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.error("Не удалось закрыть сокет: " + e.getMessage());
            }
        }
    }
}
