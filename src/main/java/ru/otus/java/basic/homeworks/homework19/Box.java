package ru.otus.java.basic.homeworks.homework19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box<T extends Fruit> {
    public List<T> box;

    public Box() {
        this.box = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Box" + box;
    }

    public void addFruit(T fruit) {
        box.add(fruit);
    }

    public double weight() {
        double allWeight = 0;
        for (T f : box) {
            allWeight += f.getWeight();
        }
        return allWeight;
    }
    public boolean comparisonWeight(Box<?> otherBox){
        return Math.abs(this.weight() - otherBox.weight()) < 0.001;
    }
    public void transferFruit(Box<? super T> finiteBox){
        if(this.box.isEmpty()){
            System.out.println("Коробка пуста — пересыпать нечего.");
            return;
        }
        if(this == finiteBox){
            System.out.println("Нельзя пересыпать в одну и ту же коробку!");
            return;
        }
        for (T f: box){
            finiteBox.addFruit(f);
        }
        box.clear();
        System.out.println("Фрукты пересыпаны.");
        System.out.println("Коробка, из которой пересыпали "+this);
        System.out.println("Коробка, в которую пересыпали "+finiteBox);
    }
}
