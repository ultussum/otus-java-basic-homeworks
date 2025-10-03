package ru.otus.java.basic.homeworks.homework7;

public enum TerrainType {
    DENSE_FOREST("Густой лес"), PLAIN("Равнина"), SWAMP("Болото");
    private String terrainRu;

    public String getTerrainRu() {
        return terrainRu;
    }

    TerrainType(String terrainRu) {
        this.terrainRu = terrainRu;
    }
}
