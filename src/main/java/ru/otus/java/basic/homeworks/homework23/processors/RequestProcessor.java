package ru.otus.java.basic.homeworks.homework23.processors;

import ru.otus.java.basic.homeworks.homework23.Request;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestProcessor {
    void execute(Request request, OutputStream output) throws IOException;
}
