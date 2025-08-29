package ru.otus.java.basic.homeworks.homework3;

public class Homework3 {
    public static void main(String[] args) {
//        sumOfPositiveElements(new int[][] {{1, 3, -2}, {2, 4, 1}});
//        drawSquareStars(5);
//        diagonalsSquareArr(new int[][]{{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}});
//        findMax(new int[][]{{7, 1, 8}, {2, 9, 3}, {6, 4, 5}});
//        sumSecondLineArr(new int[][]{{10, 2, 3}, {7, 2, 1}, {4, 5, 2}});

    }

    private static void sumOfPositiveElements(int[][] doubleArr) {
        int sumDoubleArr = 0;
        for (int i = 0; i < doubleArr.length; i++) {
            for (int j = 0; j < doubleArr[i].length; j++) {
                if (doubleArr[i][j] > 0) {
                    sumDoubleArr += doubleArr[i][j];
                }
            }
        }
        System.out.println(sumDoubleArr);
    }

    private static void drawSquareStars(int size) {
        char star = '*';
        char[][] drawSquare = new char[size][size];
        for (int i = 0; i < drawSquare.length; i++) {
            for (int j = 0; j < drawSquare[i].length; j++) {
                if (i == 0 || j == drawSquare.length - 1 || i == drawSquare.length - 1 || j == 0) {
                    drawSquare[i][j] = star;
                } else {
                    drawSquare[i][j] = ' ';
                }
                System.out.print(drawSquare[i][j]);
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

    private static void findMax(int[][] array) {
        int maxNumber = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[j].length - 1; j++) {
                if (maxNumber < array[i][j]) {
                    maxNumber = array[i][j];
                }
            }
        }
        System.out.println(maxNumber);
    }

    private static void sumSecondLineArr(int[][] arr) {
        int sumLine = 0;
        if (arr.length < 2) {
            sumLine = -1;
        } else {
            for (int i = 0; i < arr[1].length; i++) {
                sumLine += arr[1][i];
            }
        }
        System.out.println(sumLine);
    }
}
