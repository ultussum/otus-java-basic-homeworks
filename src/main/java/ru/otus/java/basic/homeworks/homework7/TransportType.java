package ru.otus.java.basic.homeworks.homework7;

public enum TransportType {
    ROVER("Вездеход"), CAR("Машина"), HORSE("Лошадь"), BICYCLE("Велосипед");
    private String nameTypeRu;

    public String getNameTypeRu() {
        return nameTypeRu;
    }

    TransportType(String nameTypeRu) {
        this.nameTypeRu = nameTypeRu;
    }
}
