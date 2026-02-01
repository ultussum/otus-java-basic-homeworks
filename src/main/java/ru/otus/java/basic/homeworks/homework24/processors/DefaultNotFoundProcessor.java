package ru.otus.java.basic.homeworks.homework24.processors;

import ru.otus.java.basic.homeworks.homework24.Request;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DefaultNotFoundProcessor implements RequestProcessor {
    @Override
    public void execute(Request request, OutputStream output) throws IOException {
        String response = "" +
                "HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><body style=\"margin: 0; display: grid; place-items: center; min-height: 100vh;\">" +
                "<img src =\"404.jpg\" style=\"width: 100%;height: 60%;\"\n></body></html>";
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}