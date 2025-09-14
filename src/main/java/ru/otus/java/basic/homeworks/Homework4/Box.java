package ru.otus.java.basic.homeworks.Homework4;

public class Box {
    //    Поля
    private int width;
    private int height;
    private int depth;
    private String color;
    private String openOrClose;
    private String object;
    //    getters/setters

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOpenOrClose() {
        return openOrClose;
    }

    public void setOpenOrClose(String openOrClose) {
        this.openOrClose = openOrClose;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    //    Конструктор
    public Box(int width, int height, int depth, String color, String openOrClose, String object) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.color = color;
        this.openOrClose = openOrClose;
        this.object = object;
    }

    //    Методы
    public void infoBox() {
        System.out.println("Размер коробки: " + width + "x" + height + "x" + depth);
        System.out.println("Коробка покрашена в " + color + " цвет");
        System.out.println("Коробка " + openOrClose + " и в ней " + object);
    }

    public void doOpen() {
        openOrClose = "открыта";
        System.out.println("Коробку открыли");
    }

    public void doClose() {
        openOrClose = "закрыта";
        System.out.println("Коробку закрыли");
    }

    public void recolor() {
        color = "серый";
        System.out.println("Коробку перекрасили в " + color + " цвет");
    }

    public void putOrDump() {
        if (openOrClose.equals("открыта") && object.equals("пусто")) {
            object = "есть предмет";
            System.out.println("В коробку положили предмет");
        } else if (openOrClose.equals("открыта")  && object.equals("есть предмет") ) {
            object = "пусто";
            System.out.println("Из коробки выбросили предмет");
        } else {
            System.out.println("Коробка закрыта");
        }

    }
}
