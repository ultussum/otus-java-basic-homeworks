package ru.otus.java.basic.homeworks.homework5;

public class Horse extends Animal {
    public Horse(String name, int runningSpeed, int swimmingSpeed, int endurance) {
        super(name, runningSpeed, swimmingSpeed, endurance);
    }

    @Override
    public int swim(int distance) {
        int time = 0;
        if (endurance - 4 * distance >= 0) {
            time = distance / swimmingSpeed;
            endurance = endurance - 4 * distance;
            System.out.println(name + " пробежал за " + time + " сек., выносливости осталось " + endurance + " ед.");
        } else {
            time = -1;
            System.out.println(name + " устал");
        }
        return time;
    }
}
