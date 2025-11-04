package ru.otus.java.basic.homeworks.homework16.Server;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryAuthenticatedProvider implements AuthenticatedProvider {
    private class User {
        private String login;
        private String password;
        private String username;

        public User(String login, String password, String username) {
            this.login = login;
            this.password = password;
            this.username = username;
        }
    }

    private List<User> users;
    private Server server;

    public InMemoryAuthenticatedProvider(Server server) {
        this.server = server;
        this.users = new CopyOnWriteArrayList<>();
        users.add(new User("qwe", "qwe", "qwe1"));
        users.add(new User("asd", "asd", "asd1"));
        users.add(new User("zxc", "zxc", "zxc1"));
    }

    private String getUsernameByLoginAndPassword(String login, String password) {
        for (User u : users) {
            if (u.login.equalsIgnoreCase(login) && u.password.equals(password)) {
                return u.username;
            }
        }
        return null;
    }

    private boolean isLoginAlreadyExists(String login) {
        for (User u : users) {
            if (u.login.equalsIgnoreCase(login)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUsernameAlreadyExists(String username) {
        for (User u : users) {
            if (u.username.equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void initialize() {
        System.out.println("used InMemoryAuthenticatedProvider");
    }

    @Override
    public boolean authenticate(ClientAction clientAction, String login, String password) {
        String authUsername = getUsernameByLoginAndPassword(login, password);
        if (authUsername == null) {
            clientAction.sendMsg("Некорректный логин/пароль");
            return false;
        }
        if (server.isUsernameBusy(authUsername)) {
            clientAction.sendMsg("Указанная учетная запись уже используется");
            return false;
        }
        clientAction.setUsername(authUsername);
        server.connectClient(clientAction);
        clientAction.sendMsg("/authok " + authUsername);
        return true;
    }

    @Override
    public boolean register(ClientAction clientAction, String login, String password, String username) {
        if (login.length() < 4) {
            clientAction.sendMsg("Логин должен содержать 4+ символов");
            return false;
        }
        if (password.length() < 3) {
            clientAction.sendMsg("Пароль должен содержать 4+ символов");
            return false;
        }
        if (isLoginAlreadyExists(login)) {
            clientAction.sendMsg("Такой логин уже занят");
            return false;
        }
        if (isUsernameAlreadyExists(username)) {
            clientAction.sendMsg("Такое имя пользователя уже занято");
            return false;
        }
        users.add(new User(login, password, username));
        clientAction.setUsername(username);
        server.connectClient(clientAction);
        clientAction.sendMsg("/regok " + username);
        return true;
    }
}
