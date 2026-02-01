package ru.otus.java.basic.homeworks.homework24;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.java.basic.homeworks.homework24.data_object.ItemsStorage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private ExecutorService executorService;
    public static final Logger logger = LogManager.getLogger(Server.class.getName());

    public Server(int port) {
        this.port = port;
        this.executorService = Executors.newFixedThreadPool(2);
        ItemsStorage.init();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Сервер запущен на порту: " + port);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    executorService.submit(new ClientHandler(socket));
                } catch (IOException e) {
                    logger.error("Ошибка при приёме подключения: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
