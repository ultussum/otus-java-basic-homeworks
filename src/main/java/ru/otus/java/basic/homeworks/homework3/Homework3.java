package ru.otus.java.basic.homeworks.homework3;

public class Homework3 {
    public static void main(String[] args) {
//        System.out.println(sumOfPositiveElements(new int[][]{{1, 3, -2}, {2, 4, 1}}));
//        drawSquareStars(5);
//        diagonalsSquareArr(new int[][]{{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}});
//        System.out.println(findMax(new int[][]{{7, 1, 8}, {2, 9, 3}, {6, 4, 5}}));
//        System.out.println(sumSecondLineArr(new int[][]{{10, 2, 3}, {7, 2, 1}, {4, 5, 2}}));
    }

    private static int sumOfPositiveElements(int[][] doubleArr) {
        int sumDoubleArr = 0;
        for (int i = 0; i < doubleArr.length; i++) {
            for (int j = 0; j < doubleArr[i].length; j++) {
                if (doubleArr[i][j] > 0) {
                    sumDoubleArr += doubleArr[i][j];
                }
            }
        }
        return sumDoubleArr;
    }

    private static void drawSquareStars(int size) {
        char star = '*';
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(star);
            }
            System.out.println();
        }
    }

    private static void diagonalsSquareArr(int[][] squareArr) {
        for (int i = 0; i < squareArr.length; i++) {
            for (int j = 0; j < squareArr[i].length; j++) {
                if (i == j || i == squareArr.length - 1 - j) {
                    squareArr[i][j] = 0;
                }
                System.out.print(squareArr[i][j]);
            }
            System.out.println();
        }
    }

    private static int findMax(int[][] array) {
        int maxNumber = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[j].length - 1; j++) {
                if (maxNumber < array[i][j]) {
                    maxNumber = array[i][j];
                }
            }
        }
        return maxNumber;
    }

    private static int sumSecondLineArr(int[][] arr) {
        int sumLine = 0;
        if (arr.length < 2) {
            sumLine = -1;
        } else {
            for (int i = 0; i < arr[1].length; i++) {
                sumLine += arr[1][i];
            }
        }
        return sumLine;
    }
}
