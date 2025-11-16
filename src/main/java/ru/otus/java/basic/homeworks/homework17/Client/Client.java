package ru.otus.java.basic.homeworks.homework17.Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private  boolean activeUser = true;

    public Client() {
        Scanner sc = new Scanner(System.in);
        try {
            socket = new Socket("localhost", 8080);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (activeUser) {
                        String message = in.readUTF();
                        if (message.startsWith("/")) {
                            if (message.startsWith("/exitok")) {
                                activeUser = false;
                                break;
                            }
                        }
                        if (message.startsWith("/authok ")) {
                            System.out.println("Удалось успешно войти в чат под именем пользователя " +
                                    message.split(" ")[1]);
                        }
                        if (message.startsWith("/regok ")) {
                            System.out.println("Удалось успешно пройти регистрацию под ником " +
                                    message.split(" ")[1]);
                        } else {
                            System.out.println(message);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    disconnect();
                }
            }).start();

            while (activeUser) {
                String message = sc.nextLine();
                try {
                    out.writeUTF(message);
                    if (message.startsWith("/exit")) {
                        activeUser = false;
                        break;
                    }
                } catch (IOException e) {
                    System.err.println("Вы отключены от чата");
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() {
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
