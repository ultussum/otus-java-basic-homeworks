package ru.otus.java.basic.homeworks.homework24;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String rawRequest;
    private Method method;
    private String uri;
    private Map<String, String> params;
    private String body;
    public static final Logger logger = LogManager.getLogger(Request.class.getName());

    public String getUri() {
        return uri;
    }

    public String getParameter(String name) {
        return params.get(name);
    }

    public String getBody() {
        return body;
    }

    public boolean containsParameter(String name) {
        return params.containsKey(name);
    }

    public String getRoutingKey() {
        return method + " " + uri;
    }

    public Request(String rawRequest) {
        this.rawRequest = rawRequest;
        this.params = new HashMap<>();
        parse();
    }

    private void parse() {
        int left = rawRequest.indexOf(" ");
        int right = rawRequest.indexOf(" ", left + 1);
        method = Method.valueOf(rawRequest.substring(0, left));
        uri = rawRequest.substring(left + 1, right);
        if (uri.contains("?")) {
            String[] tokens = uri.split("[?]");
            uri = tokens[0];
            String[] keysAndValues = tokens[1].split("[&]");
            for (String o : keysAndValues) {
                String[] keyValue = o.split("[=]");
                params.put(keyValue[0], keyValue[1]);
            }
        }
        if (method == Method.POST) {
            body = rawRequest.substring(rawRequest.indexOf("\r\n\r\n") + 4);
        }
    }

    public void info(boolean showRawRequest) {
        logger.info("METHOD: " + method + "/n" +
                "URI: " + uri + "/n" +
                "PARAMS: " + params + "/n" +
                "BODY: " + body);
        if (showRawRequest) {
            logger.info(rawRequest);
        }
    }
}
