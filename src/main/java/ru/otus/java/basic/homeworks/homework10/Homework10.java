package ru.otus.java.basic.homeworks.homework10;

import java.util.List;

public class Homework10 {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add(List.of("Степанов", "Дмитрий", "Сергеевич"), "+7911230987");
        phoneBook.add(List.of("Шилова", "Виктория", "Ивановна"), "+7921230907");
        phoneBook.add(List.of("Степанов", "Дмитрий", "Сергеевич"), "+7919030927");
        phoneBook.add(List.of("Степанов", "Евгений", "Петрович"), "+7910372827");
        phoneBook.find(List.of("Степанов"));
        phoneBook.containsPhoneNumber("+7921230907");

    }
}
