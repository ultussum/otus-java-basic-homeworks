package ru.otus.java.basic.homeworks.homework22;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Homework22 {

    public static boolean checkOneAndTwo(int[] array) {
        int indexOne = -1, indexTwo = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] !=1 && array[i]!=2){
                return false;
            }
            if (array[i] == 1){
                indexOne =i;
            }if(array[i]==2){
                indexTwo = i;
            }
        }
        if (indexOne==-1 || indexTwo ==-1){
            return false;
        }
        return true;
    }

    public static int[] afterLastUnit(int[] array) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                index = i;
            }
        }
        if (index != -1) {
            int newLength = array.length - index - 1;
            int[] result = new int[newLength];
            for (int i = 0; i < newLength; i++) {
                result[i] = array[index+1 + i];
            }
            return result;
        } else {
            throw new RuntimeException("В массиве нет единиц!");
        }
    }
}
