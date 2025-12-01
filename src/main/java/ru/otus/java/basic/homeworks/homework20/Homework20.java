package ru.otus.java.basic.homeworks.homework20;

import java.io.*;
import java.util.Scanner;

public class Homework20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nameFile = scanner(scanner, "Укажите имя файла");
        String symbolsSequence = scanner(scanner, "Введите последовательность символов");

        try {
            System.out.println("Количество совпадений: " + searchSymbolsSequence(nameFile, symbolsSequence));
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    public static int searchSymbolsSequence(String nameFile, String symbolsSequence) throws IOException {
        if (symbolsSequence.isEmpty()) {
            return 0;
        }
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(nameFile, java.nio.charset.StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int index = 0;
                while ((index = line.indexOf(symbolsSequence, index)) != -1) {
                    count++;
                    index += symbolsSequence.length();
                }
            }
        }
        return count;
    }

    private static String scanner(Scanner scanner, String massageInput) {
        System.out.println(massageInput);
        return scanner.nextLine();
    }
}
