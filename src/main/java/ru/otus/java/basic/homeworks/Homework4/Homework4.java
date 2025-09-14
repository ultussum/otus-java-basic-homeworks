package ru.otus.java.basic.homeworks.Homework4;

import java.time.Year;

public class Homework4 {
    public static void main(String[] args) {
        User[] users = {new User("Иванова", "Анна", "Сергеевна", 1995, "ann.ivanova95@gmail.com"),
                new User("Петров", "Дмитрий", "Александрович", 1984, "d.petrov87@mail.ru"),
                new User("Сидорова", "Мария", "Владимировна", 2001, "m.sidorova01@yandex.ru"),
                new User("Козлов", "Михаил", "Игоревич", 1992, "mikhail.kozlov92@gmail.com"),
                new User("Васильева", "Екатерина", "Петровна", 1978, "ekaterina.vasileva78@mail.ru"),
                new User("Морозов", "Андрей", "Николаевич", 1998, "a.morozov98@yandex.ru"),
                new User("Андреева", "Ольга", "Сергеевна", 2003, "olga.andreeva03@gmail.com"),
                new User("Николаев", "Сергей", "Александрович", 1989, "sergey.nikolaev89@mail.ru"),
                new User("Воробьёва", "Анастасия", "Михайловна", 2000, "anastasia.vorobyeva00@yandex.ru"),
                new User("Соколов", "Павел", "Дмитриевич", 1994, "p.sokolov94@gmail.com"),};
//        for (int i = 0; i < users.length; i++) {
//            users[i].infoUser();
//        }
//        overForty(users);
        Box box = new Box(11, 21, 12, "белый", "открыта", "пусто");
//        box.infoBox();
//        box.doOpen();
//        box.doClose();
//        box.recolor();
//        box.putOrDump();

    }

    private static void overForty(User[] users) {
        for (int i = 0; i < users.length; i++) {
            if (2025 - users[i].getYearBirth() > 40) {
                System.out.println("ФИО: " + users[i].getSurname() + " " + users[i].getName() + " " + users[i].getPatronymic());
                System.out.println("Год рождения: " + users[i].getYearBirth());
                System.out.println("Email: " + users[i].getEmail());
                System.out.println();
            }
        }
    }
}
