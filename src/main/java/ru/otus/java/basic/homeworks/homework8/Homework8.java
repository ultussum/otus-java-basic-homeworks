package ru.otus.java.basic.homeworks.homework8;

public class Homework8 {
    public static void main(String[] args) {
        String[][] arrayCheck = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"}};

        try {
            int result = sumFourByFour(arrayCheck);
            System.out.println("Сумма элементов: " + result);
        } catch (AppArraySizeException e) {
            System.err.println("Ошибка размера массива: " + e.getMessage());

        } catch (AppArrayDataException e) {
            System.out.println("Ошибка данных: " + e.getMessage());
        }
    }

    private static int sumFourByFour(String[][] arrayCheck) throws AppArraySizeException, AppArrayDataException {
        int sumNumberArray = 0;
        if (arrayCheck.length != 4) {
            throw new AppArraySizeException("Массив не состоит из 4-х строк");
        }
        for (int i = 0; i < arrayCheck.length; i++) {
            if (arrayCheck[i].length != 4) {
                throw new AppArraySizeException("Строка " + (i + 1) + " содержит " + arrayCheck[i].length + " элемент(а, ов), а не 4 элемента.");
            }
            for (int j = 0; j < arrayCheck[i].length; j++) {
                try {
                    int numberArray = Integer.parseInt(arrayCheck[i][j]);
                    sumNumberArray += numberArray;
                } catch (NumberFormatException e) {
                    throw new AppArrayDataException(arrayCheck[i][j] + " не является допустимым значением.", i, j);
                }
            }
        }
        return sumNumberArray;
    }
}
