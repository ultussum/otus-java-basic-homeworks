package ru.otus.java.basic.homeworks.homework19;

public class Homework19 {
    public static void main(String[] args) {

        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Box<Fruit> fruitBox = new Box<>();

        Apple apple1 =  new Apple(0.2);
        Apple apple2 =  new Apple(0.15);
        Apple apple3 =  new Apple(0.09);
        Orange orange1 = new Orange(0.08);
        Orange orange2 = new Orange(0.1);
        Orange orange3 = new Orange(0.02);

        appleBox.addFruit(apple1);
        appleBox.addFruit(apple3);
        System.out.println("Добавлены яблоки в коробку для яблоков: "+appleBox);
        System.out.println("Коробка весит " + appleBox.weight() + " кг.");;

        orangeBox.addFruit(orange1);
        orangeBox.addFruit(orange2);
        System.out.println("Добавлены апельсины в коробку для апельсинов: "+orangeBox);
        System.out.println("Коробка весит " + orangeBox.weight() + " кг.");

        fruitBox.addFruit(apple2);
        fruitBox.addFruit(orange3);
        System.out.println("Добавлены фрукты в коробку для фруктов: "+fruitBox);
        System.out.println("Коробка весит " + fruitBox.weight() + " кг.");

        System.out.println("Вес коробки с яблоками равен коробке с апельсинами? "+appleBox.comparisonWeight(orangeBox));

      appleBox.transferFruit(fruitBox);

    }
}
