package ru.otus.java.basic.homeworks.homework10;

import java.util.List;

public class Homework10 {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Степанов Дмитрий Сергеевич", "+7911230987");
        phoneBook.add("Шилова Виктория Ивановна", "+7921230907");
        phoneBook.add("Степанов Дмитрий Сергеевич",  "+7919030927");
        phoneBook.add("Степанов Евгений Петрович", "+7910372827");
        phoneBook.find("Евгений");
//        phoneBook.containsPhoneNumber("+7921230907");

    }
}
