package ru.otus.java.basic.homeworks.homework24.processors;

import com.google.gson.Gson;
import ru.otus.java.basic.homeworks.homework24.Request;
import ru.otus.java.basic.homeworks.homework24.data_object.Item;
import ru.otus.java.basic.homeworks.homework24.data_object.ItemsStorage;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


public class GetItemsProcessor implements RequestProcessor {
    @Override
    public void execute(Request request, OutputStream output) throws IOException {
        Gson gson = new Gson();
        String itemsJson = "";
        if(request.containsParameter("id")){
            Item item = ItemsStorage.searchItem(Long.parseLong(request.getParameter("id")));
            if(item == null){
                String response = "" +
                        "HTTP/1.1 404 Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "\r\n" +
                        "<html><body style=\"margin: 0; display: grid; place-items: center; min-height: 100vh;\">" +
                        "<img src =\"404.jpg\" style=\"width: 100%;height: 60%;\"\n></body></html>";
                output.write(response.getBytes(StandardCharsets.UTF_8));
                return;
            }
            itemsJson = gson.toJson(item);
        }else {
            itemsJson = gson.toJson(ItemsStorage.getItems());
        }
        String response = "" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "\r\n" +
                itemsJson;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
