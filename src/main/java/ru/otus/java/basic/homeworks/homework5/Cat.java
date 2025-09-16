package ru.otus.java.basic.homeworks.homework5;

public class Cat extends Animal {
    public Cat(String name, int runningSpeed, int swimmingSpeed, int endurance) {
        super(name, runningSpeed, swimmingSpeed, endurance);
    }

    @Override
    public int swim(int distance) {
        System.out.println(name+" не умеет плавать");
        return 0;
    }
}
