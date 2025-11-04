package ru.otus.java.basic.homeworks.homework16.Server;

import java.io.*;
import java.net.Socket;

public class ClientAction {
    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;
    private boolean authenticated;


    public ClientAction(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        username = "user" + socket.getPort();

        new Thread(() -> {
            System.out.println("Клиент подключился " + socket.getPort());
            sendMsg("Вы подключились с ником: " + username);
            try {
                while (true) {
                    sendMsg("Перед работой с чатом необходимо выполнить аутентификацию '/auth login password'" +
                            " или регистрацию '/reg login password username'");
                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (message.startsWith("/exit")) {
                            sendMsg("/exitok");
                            break;
                        }
                        // /auth login password
                        if (message.startsWith("/auth ")) {
                            String[] token = message.split(" ");
                            if (token.length != 3) {
                                sendMsg("Неверный формат команды /auth");
                                continue;
                            }
                            if (server.getAuthenticatedProvider()
                                    .authenticate(this, token[1], token[2])) {
                                authenticated = true;
                                break;
                            }
                            continue;
                        }
                        // /reg login password username
                        if (message.startsWith("/reg ")) {
                            String[] token = message.split(" ");
                            if (token.length != 4) {
                                sendMsg("Неверный формат команды /reg");
                                continue;
                            }
                            if (server.getAuthenticatedProvider()
                                    .register(this, token[1], token[2], token[3])){
                                authenticated = true;
                                break;
                            }
                        }
                    }
                }
                while (true) {
                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (message.startsWith("/exit")) {
                            sendMsg("/exitok");
                            break;
                        }
                        if (message.startsWith("/w")) {
                            String[] token = message.split(" ", 3);
                            String nameUser = token[1];
                            message = username + ": " +token[2];
                            server.privetMessage(message, nameUser);
                        }
                    } else {
                        server.broadcastMessage(username + ": " + message);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                disconnect();
            }
        }).start();
    }

    public void sendMsg(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void disconnect() {
        server.disconnectClient(this);
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
