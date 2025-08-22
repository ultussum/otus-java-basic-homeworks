package ru.otus.java.basic.homeworks.homework2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static java.util.Arrays.*;

public class Homework2 {
    public static void main(String[] args) {
//        printQuantity(5, "word");
//        sumNumbersIsMoreThanFive(new int[]{3, 6, 1, 8});
//        fillingInAnEmptyArray(6, new int[3]);
//        increasingTheArrayCells(2, new int[]{3, 8, 5, 6});
//        comparingTheSumsOfTheHalves(new int[]{3, 8, 1, 9, 5, 2});
//        SumOfThreeArrays();
//        findingThePointOfEqualityOfTheHalvesOfTheArray();
//        checkingTheSorting();
//        flippingAnArray();
    }

    //    Основные задачи
    private static void printQuantity(int quantity, String word) {
        for (int i = 1; i <= quantity; i++) {
            System.out.println(word);
        }
    }

    private static void sumNumbersIsMoreThanFive(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] > 5) {
                sum = arr[i] + sum;
            }
        System.out.println(sum);
    }

    private static void fillingInAnEmptyArray(int value, int[] emptyArr) {
        for (int i = 0; i < emptyArr.length; i++) {
            emptyArr[i] = value;
        }
        System.out.println(Arrays.toString(emptyArr));
    }

    private static void increasingTheArrayCells(int increaseArgument, int[] arrayToEnlarge) {
        for (int i = 0; i < arrayToEnlarge.length; i++) {
            arrayToEnlarge[i] += increaseArgument;
        }
        System.out.println(Arrays.toString(arrayToEnlarge));
    }

    private static void comparingTheSumsOfTheHalves(int[] wholeArray) {
        int sumFirstHalfArr = 0;
        int sumSecondHalfArr = 0;
        for (int i = 0; i < wholeArray.length / 2; i++) {
            sumFirstHalfArr = sumFirstHalfArr + wholeArray[i];
        }
        for (int i = wholeArray.length / 2; i < wholeArray.length; i++) {
            sumSecondHalfArr = sumSecondHalfArr + wholeArray[i];
        }
        if (sumFirstHalfArr > sumSecondHalfArr) {
            System.out.println("Сумма левой половины больше");
        } else {
            System.out.println("Сумма правой половины больше");
        }
    }

    //        Задачи под звёздочкой
    private static void SumOfThreeArrays() {
        int[] fistArr = {1, 2, 3}, secondArr = {2, 2}, thirdArr = {1, 1, 1, 1, 1};
        int[] maxSizeFistAndSecond = new int[(int) Math.max(fistArr.length, secondArr.length)];
        int[] maxSizeAllAndSThird = new int[(int) Math.max(maxSizeFistAndSecond.length, thirdArr.length)];
        for (int i = 0; i < maxSizeFistAndSecond.length; i++) {
            if (fistArr.length > i && secondArr.length > i) {
                maxSizeFistAndSecond[i] = fistArr[i] + secondArr[i];
            } else if (fistArr.length < secondArr.length) {
                maxSizeFistAndSecond[i] += secondArr[i];
            } else if (secondArr.length < fistArr.length) {
                maxSizeFistAndSecond[i] += fistArr[i];
            }
        }
        for (int i = 0; i < maxSizeAllAndSThird.length; i++) {
            if (maxSizeFistAndSecond.length > i && thirdArr.length > i) {
                maxSizeAllAndSThird[i] = maxSizeFistAndSecond[i] + thirdArr[i];
            } else if (maxSizeFistAndSecond.length < thirdArr.length) {
                maxSizeAllAndSThird[i] += thirdArr[i];
            } else if (thirdArr.length < maxSizeFistAndSecond.length) {
                maxSizeAllAndSThird[i] += maxSizeFistAndSecond[i];
            }
        }
        System.out.print(Arrays.toString(maxSizeAllAndSThird));
    }

    private static void findingThePointOfEqualityOfTheHalvesOfTheArray() {
        int[] arrForSearch = {1, 1, 1, 1, 1, 5};
        int totalSumOfTheArr = 0, sumOfTheLeftPartOfTheArr = 0, firstPoint = 0, secondPoint = 0;
        boolean flag = false;
        for (int i = 0; i < arrForSearch.length; i++) {
            totalSumOfTheArr += arrForSearch[i];
        }
        if (totalSumOfTheArr % 2 == 0) {
            while (!flag) {
                for (int i = 0; i < arrForSearch.length - 1; i++) {
                    if (totalSumOfTheArr / 2 != sumOfTheLeftPartOfTheArr) {
                        sumOfTheLeftPartOfTheArr += arrForSearch[i];
                        firstPoint = i;
                        secondPoint = i + 1;
                    } else {
                        flag = true;
                    }
                }
            }
            System.out.println("Точка находится между элементами с индексами " + firstPoint + " и " + secondPoint);
        } else {
            System.out.println("Точки нет!");
        }
    }

    private static void checkingTheSorting() {
        int[] arr = {1, 2, 3, 4};
        int[] sortArr = Arrays.copyOf(arr, arr.length);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выбирете направление сортировки: убывание, возрастание");
        String sortingDirection = scanner.next();

        switch (sortingDirection) {
            case "убывание":
                Arrays.sort(sortArr);
                for (int i = 0; i < sortArr.length / 2; i++) {
                    int storage = sortArr[i];
                    sortArr[i] = sortArr[sortArr.length - 1 - i];
                    sortArr[sortArr.length - 1 - i] = storage;
                }
                if (Arrays.equals(arr, sortArr)) {
                    System.out.println("По убыванию");
                } else {
                    System.out.println("Не по убыванию");
                }
                break;
            case "возрастание":
                Arrays.sort(sortArr);
                if (Arrays.equals(arr, sortArr)) {
                    System.out.println("По возрастанию");
                } else {
                    System.out.println("Не по возрастанию");
                }
                break;

        }
    }

    private static void flippingAnArray() {
        int[] arr1 = {1, 2, 3, 4};
        for (int i = 0; i < arr1.length / 2; i++) {
            int storage = arr1[i];
            arr1[i] = arr1[arr1.length - 1 - i];
            arr1[arr1.length - 1 - i] = storage;
        }
        System.out.println(Arrays.toString(arr1));
    }
}