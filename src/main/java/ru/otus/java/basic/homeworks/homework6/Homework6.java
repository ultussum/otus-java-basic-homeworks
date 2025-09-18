package ru.otus.java.basic.homeworks.homework6;

public class Homework6 {
    public static void main(String[] args) {
        Plate plate = new Plate();
        plate.info();
        System.out.println();
        Cat[] cat = {new Cat("Jack", 4, true),
                new Cat("Myrzik", 3, true),
                new Cat("Bobby", 2, false),
                new Cat("Swen", 6, true)};
        for (int i = 0; i < cat.length; i++) {
            cat[i].eat(plate, cat[i]);
        }
        System.out.println();
        plate.info();
        System.out.println();
        plate.putFood(3);

    }
}
