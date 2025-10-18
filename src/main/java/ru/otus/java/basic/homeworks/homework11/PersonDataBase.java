package ru.otus.java.basic.homeworks.homework11;


import java.util.HashMap;
import java.util.Map;

public class PersonDataBase {
    private Map<Long, Person> personMap;

    public PersonDataBase() {
        this.personMap = new HashMap<>();
    }

    public Person findById(Long id) {
        return personMap.get(id);
    }

    public void add(Person person) {
        personMap.put(person.getId(), person);
        System.out.println("Добавлен сотрудник " + person.getName() + " на должность " + person.getPosition());
    }

    public boolean isManager(Person person) {
        Position position = person.getPosition();
        return position == Position.MANAGER ||
                position == Position.DIRECTOR ||
                position == Position.BRANCH_DIRECTOR ||
                position == Position.SENIOR_MANAGER;
    }

    public boolean isEmployee(Long id) {
        Person person = findById(id);
        return !isManager(person);
    }
}
