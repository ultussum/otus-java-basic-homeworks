package ru.otus.java.basic.homeworks.homework12;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Homework12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        listFile();
        String nameFile = scanner(scanner, "Укажите имя файла");
        fileContents(nameFile);
        String newRecord = scanner(scanner, "Введите строку для записи в файл");
        writeLine(nameFile, newRecord);
    }

    private static void writeLine(String nameFile, String newRecord) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(nameFile, true))) {
            out.write((System.lineSeparator()+newRecord).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileContents(String nameFile) {
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(nameFile))) {
            int n = in.read();
            while (n != -1) {
                System.out.print((char) n);
                n = in.read();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void listFile() {
        File dir = new File(".");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    System.out.println(file.getPath());
                }
            }
        }
    }

    private static String scanner(Scanner scanner, String massageInput) {
        System.out.println(massageInput);
        return scanner.nextLine();
    }
}
