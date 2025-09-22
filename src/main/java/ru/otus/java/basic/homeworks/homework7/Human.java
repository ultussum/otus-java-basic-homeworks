package ru.otus.java.basic.homeworks.homework7;

public class Human {
    private String name;
    private Transport currentTransport;
    private int enduranceHum;

    public String getName() {
        return name;
    }

    public Transport getCurrentTransport() {
        return currentTransport;
    }

    public int getEnduranceHum() {
        return enduranceHum;
    }

    public void setEnduranceHum(int enduranceHum) {
        this.enduranceHum = enduranceHum;
    }

    public Human(String name, int enduranceHum) {
        this.name = name;
        this.currentTransport = null;
        this.enduranceHum = enduranceHum;
    }

    public void sitDownHum(Transport transport, Human human) {
        if (currentTransport != null) {
            System.out.println(name + " уже использует транспорт.");
            return;
        }
        currentTransport = transport;
        currentTransport.sitDown(human);
    }

    public void standUpHum(Transport transport, Human human) {
        if (currentTransport == null) {
            System.out.println(name + " не исплользует транспорт сейчас.");
            return;
        }
        currentTransport.standUp(human);
        currentTransport = null;
    }

    public boolean move(int distance, TerrainType terrainType) {
        if (currentTransport != null) {
            return currentTransport.move(distance, terrainType);
        } else {
            return moveOnFoot(distance, terrainType);
        }
    }

    public boolean moveOnFoot(int distance, TerrainType terrainType) {
        if (enduranceHum < distance) {
            System.out.println(name + " устал и не может пройти расстояние.");
            return false;
        }
        enduranceHum -= distance;
        System.out.println(name + " прошёл местность " + terrainType.getTerrainRu() + " " + distance + " км. Осталось сил: " + enduranceHum);
        return true;
    }
}
