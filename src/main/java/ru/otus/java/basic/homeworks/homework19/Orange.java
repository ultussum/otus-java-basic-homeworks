package ru.otus.java.basic.homeworks.homework19;

public class Orange extends Fruit{
    public Orange(double weight) {
        super(weight);
    }
    @Override
    public String toString() {
        return "Orange("+getWeight()+")";
    }
}
