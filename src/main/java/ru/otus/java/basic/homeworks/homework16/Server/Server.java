package ru.otus.java.basic.homeworks.homework16.Server;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private int port;
    private List<ClientAction> clients;
    private AuthenticatedProvider authenticatedProvider;

    public Server(int port) {
        this.clients = new CopyOnWriteArrayList<>();
        this.port = port;
        authenticatedProvider = new InMemoryAuthenticatedProvider(this);

    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер готов к работе...");
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientAction(socket, this);
            }
        } catch (IOException e) {
            System.out.println("Сервер не поднялся!");
        }
    }

    public void connectClient(ClientAction clientAction) {
        clients.add(clientAction);
    }

    public void disconnectClient(ClientAction clientAction) {
        System.out.println("Пользователь " + clientAction.getUsername() + " отключился");
        clients.remove(clientAction);
    }

    public boolean isUsernameBusy(String username) {
        for (ClientAction c : clients) {
            if (c.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastMessage(String message) {
        for (ClientAction c : clients) {
            c.sendMsg(message);
        }
    }

    public void privetMessage(String message, String userName) {
        for (ClientAction c : clients) {
            if (c.getUsername().equals(userName)) {
                c.sendMsg(message);
            }
        }
    }
    public AuthenticatedProvider getAuthenticatedProvider() {
        return authenticatedProvider;
    }
}
