package ru.otus.java.basic.homeworks.homework5;

public class Homework5 {
    public static void main(String[] args) {
        Cat cat = new Cat("Беглс", 1, 0, 10);
        Dog dog = new Dog("Барбос", 2, 3, 20);
        Horse horse = new Horse("Спринт", 5, 5, 30);
        cat.info();
        cat.run(5);
        cat.swim(3);
        dog.info();
        dog.run(4);
        dog.swim(8);
        horse.info();
        horse.run(20);
        horse.swim(10);

    }
}
