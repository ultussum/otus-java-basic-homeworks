package ru.otus.java.basic.homeworks.homework13;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Сервер начал свою работу");
            while (true) {
                Socket client = serverSocket.accept();
                DataInputStream dis = new DataInputStream(client.getInputStream());
                DataOutputStream dos = new DataOutputStream(client.getOutputStream());

                String userInput = dis.readUTF();
                System.out.println("userInput = " + userInput);
                if (userInput.equalsIgnoreCase("exit")) {
                    System.out.println("клиент разрывает связь");
                    client.close();
                    continue;
                }

                    dos.writeUTF(exampleSolution(userInput));
                    dos.flush();
                    System.out.println("result= " + exampleSolution(userInput));

            }
        } catch (IOException e) {
            System.out.println("Сервер не поднялся");
        }
    }

    private static String exampleSolution(String userInput) {
        List<String> example = new ArrayList<>();
        String result = "";
        System.out.println("Начинаем вычисления!");
        String[] past = userInput.split(" ");
        for (String p : past) {
            if (!p.isEmpty()) {
                example.add(p);
            }
        }
        Operations.checkAndExecution(example);
        result = example.get(0);
        return result;
    }
}
