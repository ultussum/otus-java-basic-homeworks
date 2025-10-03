package ru.otus.java.basic.homeworks.homework7;

public class Bicycle extends GeneralTransport {
    private int endurance;
    private final int MAX_DIST = 15;

    public int getPetrol() {
        return endurance;
    }

    public void setPetrol(int endurance) {
        this.endurance = endurance;
    }

    public Bicycle() {
        super(TransportType.BICYCLE);
        this.endurance = 3;
    }

    @Override
    public boolean canMove(TerrainType terrainType) {
        return terrainType != TerrainType.SWAMP;
    }

    @Override
    public boolean move(int distant, TerrainType terrainType) {
        if (!canMove(terrainType)) {
            System.out.println("Велосипед не может двигаться по местности " + terrainType.getTerrainRu());
            return false;
        }
        if (distant > MAX_DIST || endurance < distant) {
            System.out.println("Не может передвигаться! Слишком большое расстояние или закончились силы.");
            return false;
        }
        endurance -= distant;
        System.out.println("Велосипед проехал по местности" + terrainType.getTerrainRu() + " " + distant + " км. Сил осталось: " + endurance);
        return true;
    }
}
