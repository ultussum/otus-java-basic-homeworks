package ru.otus.java.basic.homeworks.homework19;

public class Apple extends Fruit {
    public Apple(double weight) {
        super(weight);
    }
    @Override
    public String toString() {
        return "Apple("+getWeight()+")";
    }
}
