package ru.otus.java.basic.homeworks.homework9;

import java.util.ArrayList;
import java.util.List;

public class Staff {
    private String name;
    private int age;

    public Staff(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static List<String> listNameStaff(List<Staff> listStaff) {
        List<String> listName = new ArrayList<>();
        for (Staff st : listStaff) {
            listName.add(st.getName());
        }
        System.out.println("Имена сотрудников: " + listName);
        return listName;
    }

    public static List<String> listUnderStaff(int ageLimit, List<Staff> listStaff) {
        List<String> listName = new ArrayList<>();
        for (Staff st : listStaff) {
            if (st.getAge() >= ageLimit) {
                listName.add(st.getName());
            }
        }
        System.out.println("Сотрудники старше " + ageLimit + ": " + listName);
        return listName;
    }

    public static boolean exceedingAverageAge(int minAverage, List<Staff> listStaff) {
        int generalAge = 0;
        for (Staff st : listStaff) {
            generalAge += st.getAge();
        }
        int averageAgeStaff = generalAge / listStaff.size();
        if (averageAgeStaff <= minAverage) {
            System.out.println("Средний возраст сотрудников не превышает " + minAverage);
            return false;
        }
        System.out.println("Средний возраст сотрудников превышает " + minAverage);
        return true;
    }

    public static Staff youngStaff(List<Staff> listStaff) {
        Staff youngest = listStaff.get(0);
        for (Staff st : listStaff) {
            if (youngest.getAge() > st.getAge()) {
                youngest = st;
            }
        }
        System.out.println("Самый молодой сотрудник - " + youngest.getName() + ", этому сотруднику " + youngest.getAge());
        return youngest;
    }
}

