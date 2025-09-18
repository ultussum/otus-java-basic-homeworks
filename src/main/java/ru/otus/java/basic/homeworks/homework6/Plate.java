package ru.otus.java.basic.homeworks.homework6;

public class Plate {
    private int maxFood;
    private int currentFood;

    public int getMaxFood() {
        return maxFood;
    }

    public void setMaxFood(int maxFood) {
        this.maxFood = maxFood;
    }

    public int getCurrentFood() {
        return currentFood;
    }

    public void setCurrentFood(int currentFood) {
        this.currentFood = currentFood;
    }

    public Plate() {
        maxFood = 10;
        currentFood = maxFood;
    }

    public void info() {
        System.out.println("В тарелке: " + currentFood + " кус. еды.");
    }

    public void putFood(int plusFood) {
        if (currentFood + plusFood <= maxFood) {
            currentFood += plusFood;
            System.out.println("В тарелку добавили " + plusFood + " кус. еды, теперь в ней: " + currentFood + " кус. еды.");
        } else {
            System.out.println("В тарелку влезло только " + (plusFood - (currentFood + plusFood - maxFood)) + " кус. еды.");
            currentFood = currentFood + plusFood - (currentFood + plusFood - maxFood);
            System.out.println("Теперь в тарелке: " + currentFood + " кус. еды.");
        }
    }

    public boolean decreaseFood(Cat cat) {
        if (currentFood - cat.getAppetite() >= 0) {
            currentFood -= cat.getAppetite();
            return true;
        } else {
            return false;
        }
    }
}
