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
        String buffer = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(nameFile, java.nio.charset.StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String combined = buffer + line;
                int index = 0;
                while ((index = combined.indexOf(symbolsSequence, index)) != -1) {
                    count++;
                    index += symbolsSequence.length();
                }
                int keepLength = Math.max(0, symbolsSequence.length() - 1);
                buffer = combined.substring(Math.max(0, combined.length() - keepLength));
            }
        }
        return count;
    }

    private static String scanner(Scanner scanner, String massageInput) {
        System.out.println(massageInput);
        return scanner.nextLine();
    }
}
