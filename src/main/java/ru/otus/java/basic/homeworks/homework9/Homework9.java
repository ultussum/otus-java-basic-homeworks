package ru.otus.java.basic.homeworks.homework9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.otus.java.basic.homeworks.homework9.Staff.*;

public class Homework9 {
    public static void main(String[] args) {
        arithmeticProgression(1, 10);
        sumMoreFive(Arrays.asList(2, 6, 4, 8, 10));
        replacingElements(8, Arrays.asList(1, 2, 3, 4));
        increasingElements(2, Arrays.asList(1, 2, 3, 4));
        System.out.println();
        List<Staff> listStaff = new ArrayList<>(Arrays.asList(
                new Staff("Jack", 37),
                new Staff("Molly", 25),
                new Staff("Demon", 19),
                new Staff("Rebeka", 40)));
        listNameStaff(listStaff);
        listUnderStaff(29, listStaff);
        exceedingAverageAge(28, listStaff);
        youngStaff(listStaff);

    }

    private static void increasingElements(int plus, List<Integer> listPlus) {
        for (int i = 0; i < listPlus.size(); i++) {
            listPlus.set(i, listPlus.get(i) + plus);
        }
        System.out.println("Список после увеличения элементов на " + plus + ": " + listPlus);
    }

    private static void replacingElements(int number, List<Integer> listSet) {
        for (int i = 0; i < listSet.size(); i++) {
            listSet.set(i, number);
        }
        System.out.println("Список после замены: " + listSet);
    }

    private static int sumMoreFive(List<Integer> listForSum) {
        int sum = 0;
        for (int i = 0; i < listForSum.size(); i++) {
            if (listForSum.get(i) > 5) {
                sum += listForSum.get(i);
            }
        }
        System.out.println("Сумма элементов, которые больше 5, равна: " + sum);
        return sum;
    }

    private static List<Integer> arithmeticProgression(int min, int max) {
        List<Integer> listProgress = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            listProgress.add(i);
        }
        System.out.println("Последовательные значения от " + min + " до " + max + ": " + listProgress);
        return listProgress;
    }
}
