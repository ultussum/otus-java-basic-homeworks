package ru.otus.java.basic.homeworks.homework8;

public class AppArrayDataException extends RuntimeException {
    public int row;
    public int column;

    public AppArrayDataException(String message, int row, int column) {
        super("Ошибка данных в ячейке [" + row + "][" + column+"]: " + message);
        this.column = column;
        this.row = row;
    }
}
