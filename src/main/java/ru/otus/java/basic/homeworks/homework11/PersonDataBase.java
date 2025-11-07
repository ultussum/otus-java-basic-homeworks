package ru.otus.java.basic.homeworks.homework11;


import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PersonDataBase {
    private Map<Long, Person> personMap;
    private static final Set<Position> MANAGER_POSITION = EnumSet.of(
            Position.MANAGER,
            Position.DIRECTOR,
            Position.BRANCH_DIRECTOR,
            Position.SENIOR_MANAGER);

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
        return MANAGER_POSITION.contains(position);
    }

    public boolean isEmployee(Long id) {
        Person person = findById(id);
        return !MANAGER_POSITION.contains(person.getPosition());
    }
}
