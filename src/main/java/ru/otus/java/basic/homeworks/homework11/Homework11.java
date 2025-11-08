package ru.otus.java.basic.homeworks.homework11;

import java.util.Map;

public class Homework11 {
    public static void main(String[] args) {
        PersonDataBase personDataBase = new PersonDataBase();
        Person person1 = new Person("Артем", Position.BRANCH_DIRECTOR, 1000L);
        Person person2 = new Person("Василий", Position.QA, 1001L);
        Person person3 = new Person("Галина", Position.ENGINEER, 1002L);

        personDataBase.add(person1);
        personDataBase.add(person2);
        personDataBase.add(person3);

        System.out.println("Найден сотрудник: "+personDataBase.findById(1000L).getName());
        System.out.println("Относится к руководящей должности: "+personDataBase.isManager(person2));
        System.out.println("Является исполнительской должностью: "+personDataBase.isEmployee(1002L));

        int[] array = {3,4,2,1};
        SortArray.bubbleSort(array);
        SortArray.quickSort(array);

    }
}
