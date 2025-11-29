package ru.otus.java.basic.homeworks.homework17.Server;

public interface AuthenticatedProvider {
    void initialize();

    boolean authenticate(ClientAction clientAction, String login, String password);

    boolean register(ClientAction clientAction, String login, String password, String username);
}
