package ru.otus.java.basic.homeworks.homework1;

import java.util.Scanner;

public class Homework1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число от 1 до 5");
        int n = scanner.nextInt();

        switch (n){
            case 1:
                greetings();
                break;
            case 2:
                checkSign(1, 1,1);
                break;
            case 3:
                selectColor();
                break;
            case 4:
                compareNumbers();
                break;
            case 5:
                addOrSubtractAndPrint(5, 2, false);
                break;
        }
    }

    public static void greetings() {
        System.out.println("Hello");
        System.out.println("World");
        System.out.println("from");
        System.out.println("Java");
    }

    public static void checkSign(int a , int b, int c) {
        if (a + b + c >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void selectColor() {
        int data = (int)(Math.random()*10);
        if (data <= 10){
            System.out.println("Красный");
        }
        if (data > 10 && data <= 20){
            System.out.println("Желтый");
        }
        if (data > 20){
            System.out.println("Зелёный");
        }
    }

    public static void compareNumbers() {
        int a = (int)(Math.random()*10);
        int b = (int)(Math.random()*10);
        if (a >= b){
            System.out.println("a >= b");
        }else{
            System.out.println("a < b");
        }
    }

    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        if (increment){
            System.out.println(initValue + delta);
        }else{
            System.out.println(initValue - delta);
        }
    }
}