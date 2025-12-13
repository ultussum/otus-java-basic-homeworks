package ru.otus.java.basic.homeworks.homework18.server;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private int port;
    private List<ClientAction> clients;
    private AuthenticatedProvider authenticatedProvider;
    private  Provider provider;

    public Server(int port) {
        this.clients = new CopyOnWriteArrayList<>();
        this.port = port;
        this.provider = new Provider();
        authenticatedProvider = new InMemoryAuthenticatedProvider(this, this.provider);

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

    public void privateMessage(String message, String userName, String username) {
        for (ClientAction c : clients) {
            if (c.getUsername().equals(userName)) {
                c.sendMsg(username+"(отправлено тольео Вам): "+message);
            }
        }
    }

    public boolean deleteClient(String userName) {
        for (ClientAction c : clients) {
            if (c.getUsername().equals(userName)) {
                c.sendMsg("/exitok");
                return true;
            }
        }
        return false;
    }

    public AuthenticatedProvider getAuthenticatedProvider() {
        return authenticatedProvider;
    }
}
