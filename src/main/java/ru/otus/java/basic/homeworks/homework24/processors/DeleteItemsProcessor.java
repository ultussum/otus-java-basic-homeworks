package ru.otus.java.basic.homeworks.homework24.processors;

import com.google.gson.Gson;
import ru.otus.java.basic.homeworks.homework24.Request;
import ru.otus.java.basic.homeworks.homework24.data_object.Item;
import ru.otus.java.basic.homeworks.homework24.data_object.ItemsStorage;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DeleteItemsProcessor implements RequestProcessor{
    @Override
    public void execute(Request request, OutputStream output) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        ItemsStorage.deleteItem(id);
        String response = "" +
                "HTTP/1.1 204 No Content\r\n" +
                "Content-Type: application/json\r\n" +
                "\r\n" ;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
