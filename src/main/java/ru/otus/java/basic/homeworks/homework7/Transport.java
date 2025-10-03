package ru.otus.java.basic.homeworks.homework7;

public interface Transport {
    boolean canMove(TerrainType terrainType);

    boolean move(int distant, TerrainType terrainType);

    void sitDown(Human human);

    void standUp(Human human);
}
