package ru.otus.java.basic.homeworks.homework7;

public class Car extends GeneralTransport {
    private int petrol;

    public int getPetrol() {
        return petrol;
    }

    public void setPetrol(int petrol) {
        this.petrol = petrol;
    }

    public Car() {
        super(TransportType.CAR);
        this.petrol = 40;
    }

    @Override
    public boolean canMove(TerrainType terrainType) {
        return terrainType == TerrainType.PLAIN;
    }

    @Override
    public boolean move(int distant, TerrainType terrainType) {
        if (!canMove(terrainType)) {
            System.out.println("Машина не может двигаться по местности " + terrainType.getTerrainRu());
            return false;
        }
        if (distant > 60 || petrol < distant) {
            System.out.println("Не может передвигаться! Слишком большое расстояние или закончился бензин.");
            return false;
        }
        petrol -= distant;
        System.out.println("Машина проехала по местности " + terrainType.getTerrainRu() + " " + distant + " км. Топлива осталось: " + petrol);
        return true;
    }

}
