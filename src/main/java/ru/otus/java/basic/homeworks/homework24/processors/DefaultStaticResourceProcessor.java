package ru.otus.java.basic.homeworks.homework24.processors;

import ru.otus.java.basic.homeworks.homework24.Request;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;


public class DefaultStaticResourceProcessor implements RequestProcessor {
    @Override
    public void execute(Request request, OutputStream output) throws IOException {
        String filename = request.getUri().substring(1);
        Path filePath = Paths.get("static/", filename);
        byte[] fileData = Files.readAllBytes(filePath);

        String response = "" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-Length: " + fileData.length + "\r\n" +
                "\r\n";
        output.write(response.getBytes(StandardCharsets.UTF_8));
        output.write(fileData);
    }
}
