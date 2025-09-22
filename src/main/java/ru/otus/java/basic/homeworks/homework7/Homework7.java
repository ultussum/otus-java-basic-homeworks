package ru.otus.java.basic.homeworks.homework7;

public class Homework7 {
    public static void main(String[] args) {
        Human human = new Human("Ярослав", 10);
        Transport[] transports = {new Car(), new Horse(), new Bicycle(human), new Rover()};

        human.move(5, TerrainType.DENSE_FOREST);
        human.move(5, TerrainType.SWAMP);
        human.move(5, TerrainType.PLAIN);
        System.out.println();

        for (Transport t : transports) {
            human.sitDownHum(t, human);
            human.move(15, TerrainType.DENSE_FOREST);
            human.move(10, TerrainType.SWAMP);
            human.move(20, TerrainType.PLAIN);
            human.standUpHum(t, human);
            System.out.println();
        }

    }
}
