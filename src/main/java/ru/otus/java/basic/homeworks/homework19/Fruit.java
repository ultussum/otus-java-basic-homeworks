package ru.otus.java.basic.homeworks.homework19;

abstract class Fruit {
    private double weight;

    public Fruit(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Fruit(" + weight + ")";
    }
}
