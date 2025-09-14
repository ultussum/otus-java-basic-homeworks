package ru.otus.java.basic.homeworks.Homework4;

public class User {
    //    Поля
    private String surname;
    private String name;
    private String patronymic;
    private int yearBirth;
    private String email;

    //    getters/setters
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //    Конструктор
    public User(String surname, String name, String patronymic, int yearBirth, String email) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.yearBirth = yearBirth;
        this.email = email;
    }

    //    Методы
    public void infoUser() {
        System.out.println("ФИО: " + surname + " " + name + " " + patronymic);
        System.out.println("Год рождения: " + yearBirth);
        System.out.println("Email: " + email);
        System.out.println();
    }

}
