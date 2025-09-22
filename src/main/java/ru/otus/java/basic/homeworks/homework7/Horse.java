package ru.otus.java.basic.homeworks.homework7;

public class Horse extends GeneralTransport {
    private int endurance;

    public int getPetrol() {
        return endurance;
    }

    public void setPetrol(int endurance) {
        this.endurance = endurance;
    }

    public Horse() {
        super(TransportType.HORSE);
        this.endurance = 20;
    }

    @Override
    public boolean canMove(TerrainType terrainType) {
        return terrainType != TerrainType.SWAMP;
    }

    @Override
    public boolean move(int distant, TerrainType terrainType) {
        if (!canMove(terrainType)) {
            System.out.println("Лошадь не может двигаться по местности " + terrainType.getTerrainRu());
            return false;
        }
        if (distant > 20 || endurance < distant) {
            System.out.println("Не может передвигаться! Слишком большое расстояние или закончились силы.");
            return false;
        }
        endurance -= distant;
        System.out.println("Лошадь проскакала по местности " + terrainType.getTerrainRu() + " " + distant + " км. Сил осталось: " + endurance);
        return true;
    }
}
