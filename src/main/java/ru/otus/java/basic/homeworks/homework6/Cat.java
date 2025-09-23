package ru.otus.java.basic.homeworks.homework6;

public class Cat {
    private String name;
    private int appetite;
    private boolean isHungry;

    public Cat(String name, int appetite, boolean satiety) {
        this.name = name;
        this.appetite = appetite;
        this.isHungry = satiety;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAppetite() {
        return appetite;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }

    public boolean isHungry() {
        return isHungry;
    }

    public void setHungry(boolean hungry) {
        this.isHungry = hungry;
    }

    public void eat(Plate plate) {
        if (isHungry && plate.getCurrentFood() - appetite >= 0) {
            System.out.println(name + " поел.");
            plate.decreaseFood(appetite);
            isHungry = false;
        } else if (isHungry && plate.getCurrentFood() - appetite < 0) {
            System.out.println(name + " не смог поесть, не хватило еды :(");
        } else {
            System.out.println(name + " неголодный.");
        }
    }
}
