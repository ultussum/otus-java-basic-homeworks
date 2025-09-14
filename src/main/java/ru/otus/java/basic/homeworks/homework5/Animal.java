package ru.otus.java.basic.homeworks.homework5;

public abstract class Animal {
    String name;
    int runningSpeed;
    int swimmingSpeed;
    int endurance;

    public Animal(String name, int runningSpeed, int swimmingSpeed, int endurance) {
        this.name = name;
        this.runningSpeed = runningSpeed;
        this.swimmingSpeed = swimmingSpeed;
        this.endurance = endurance;
    }

    public void info() {
        System.out.println("Имя: " + name);
        System.out.println("Скорость бега: " + runningSpeed);
        System.out.println("Скорость плавания: " + swimmingSpeed);
        System.out.println("Выносливость: " + endurance);
    }

    public int run(int distance) {
        int time = 0;
        if (endurance - distance > 0) {
            time = distance / runningSpeed;
            endurance -= distance;
            System.out.println(name + " пробежал за " + time + " сек., выносливости осталось " + endurance + " ед.");
        } else {
            time = -1;
            System.out.println(name + " устал");
        }
        return time;
    }

    public abstract int swim(int distance);
}
