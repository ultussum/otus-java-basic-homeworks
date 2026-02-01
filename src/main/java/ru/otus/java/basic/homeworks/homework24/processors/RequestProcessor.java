package ru.otus.java.basic.homeworks.homework24.processors;

import ru.otus.java.basic.homeworks.homework24.Request;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestProcessor {
    void execute(Request request, OutputStream output) throws IOException;
}
