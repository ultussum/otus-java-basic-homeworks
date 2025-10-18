package ru.otus.java.basic.homeworks.homework11;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SortArray {
    public static void bubbleSort(int array[]) {
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            boolean sorted = false;
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int savedValue = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = savedValue;
                    sorted = true;
                }
            }
            if (!sorted) {
                break;
            }
        }
        System.out.println("Сортировка 'пузырьком': "+Arrays.toString(array));
    }

    public static void quickSort(int array[]) {
        if (array == null || array.length <= 1) {
            return;
        }
        quickSortRecursive(array, 0, array.length-1);
        System.out.println("Сортировка 'разделяй и властвуй': " + Arrays.toString(array));
    }
    public static void quickSortRecursive(int array[], int left, int right){
        if(left>= right){
            return;
        }
        int fulcrumIndex = section(array, left, right);
        quickSortRecursive(array, left, fulcrumIndex-1);
        quickSortRecursive(array, fulcrumIndex+1,right);
    }
    public static int section(int array[], int left, int right){
        int fulcrum = array[right];
        int i = left-1;
        for (int j = left; j < right; j++) {
            if (array[j]<=fulcrum){
                i++;
                int savedValue = array[i];
                array[i]=array[j];
                array[j]=savedValue;
            }
        }
        int savedValue = array[i+1];
        array[i+1]=array[right];
        array[right]=savedValue;
        return i+1;
    }
}
