package ru.otus.java.basic.homeworks.homework18.server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ClientAction {
    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;
    private boolean authenticated;
    private  List<String> roles;


    public ClientAction(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        username = "user" + socket.getPort();
        this.roles = new ArrayList<>();

        new Thread(() -> {
            System.out.println("Клиент подключился " + socket.getPort());
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
                while (authenticated) {
                    String message;
                    try {
                        message = in.readUTF();
                    } catch (EOFException | SocketException e) {
                        System.out.println("Клиент отключился: " + e.getMessage());
                        break;
                    }
                    if (message.startsWith("/")) {
                        if (message.startsWith("/exit")) {
                            sendMsg("/exitok");
                            break;
                        }
                        if (message.startsWith("/w")) {
                            String[] token = message.split(" ", 3);
                            String nameUser = token[1];
                            message = token[2];
                            server.privateMessage(message, nameUser, username);
                        }
                        if (message.startsWith("/kick")) {
                            if(!roles.contains(String.valueOf(Role.ADMIN))){
                                sendMsg("У вас нет прав на отключение пользователей");
                                continue;
                            }
                            String[] token = message.split(" ");
                            String nameUser = token[1];
                            if(server.deleteClient(nameUser)){
                                sendMsg("Пользователь "+nameUser+" отключен");
                            }else {
                                sendMsg("Пользователь не найден");
                            }

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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
