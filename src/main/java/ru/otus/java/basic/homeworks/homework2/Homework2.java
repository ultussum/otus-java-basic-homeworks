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
//        sumOfThreeArrays(new int[]{1, 2, 3}, new int[]{2, 2}, new int[]{1, 1, 1, 1, 1});
//        findingPointEquaArray(new int[]{1, 1, 1, 1, 1, 5});
//        checkingTheSorting(new int[]{1, 2, 3, 4});
//        flippingAnArray(new int[]{1, 2, 3, 4});
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
                sum += arr[i];
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
            System.out.println("Сумма правой половины больше или равна");
        }
    }

    //        Задачи под звёздочкой
    private static void sumOfThreeArrays(int[] fistArr, int[] secondArr, int[] thirdArr) {
        int[] maxSizeArr = new int[(int) Math.max(thirdArr.length, Math.max(fistArr.length, secondArr.length))];
        for (int i = 0; i < fistArr.length; i++) {
            maxSizeArr[i] += fistArr[i];
        }
        for (int i = 0; i < secondArr.length; i++) {
            maxSizeArr[i] += secondArr[i];
        }
        for (int i = 0; i < thirdArr.length; i++) {
            maxSizeArr[i] += thirdArr[i];
        }
        System.out.print(Arrays.toString(maxSizeArr));
    }

    private static void findingPointEquaArray(int[] arrForSearch) {
        int totalSumArr = 0, sumLeftPart = 0;
        for (int i = 0; i < arrForSearch.length; i++) {
            totalSumArr += arrForSearch[i];
        }
        if (totalSumArr % 2 != 0) {
            System.out.println("Точки нет!");
            return;
        }
        for (int i = 0; i < arrForSearch.length - 1; i++) {
            sumLeftPart += arrForSearch[i];
            if (totalSumArr / 2 == sumLeftPart) {
                System.out.println("Точка находится между элементами с индексами " + i + " и " + (i + 1));
                break;
            }
        }
    }

    private static void checkingTheSorting(int[] arr) {
        String resultSort = "";
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[(i + 1)] && arr[i] < arr[(i - 1)]) {
                resultSort = "По убыванию";
            }
            if (arr[i] < arr[(i + 1)] && arr[i] > arr[(i - 1)]) {
                resultSort = "По возрастанию";
            } else {
                resultSort = "Рандомный порядок";
                break;
            }
        }
        System.out.println(resultSort);
    }

    private static void flippingAnArray(int[] arr1) {
        for (int i = 0; i < arr1.length / 2; i++) {
            int storage = arr1[i];
            arr1[i] = arr1[arr1.length - 1 - i];
            arr1[arr1.length - 1 - i] = storage;
        }
        System.out.println(Arrays.toString(arr1));
    }
}