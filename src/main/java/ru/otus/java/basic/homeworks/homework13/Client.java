package ru.otus.java.basic.homeworks.homework13;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try (Socket socket = new Socket("localhost", 8080)) {
                ExampleClient client = new ExampleClient(
                        socket.getInputStream(),
                        socket.getOutputStream()
                );
                System.out.println("Введи пример для расчёта (символы вводятся через пробел):");
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("exit")) {
                    client.send(userInput);
                    break;
                }
                client.send(userInput);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
