package ru.otus.java.basic.homeworks.homework18.server;

import java.sql.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryAuthenticatedProvider implements AuthenticatedProvider {
    private class User {
        private int id;
        private String login;
        private String password;
        private String username;
        private List<Role> roles;

        public User(int id, String login, String password, String username, List<Role> roles) {
            this.id = id;
            this.login = login;
            this.password = password;
            this.username = username;
            this.roles = new ArrayList<>();
        }

        public User() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<Role> getRoles() {
            return roles;
        }

        public void setRoles(List<Role> roles) {
            this.roles = roles;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(username, user.username) && Objects.equals(roles, user.roles);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, login, password, username, roles);
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", login='" + login + '\'' +
                    ", password='" + password + '\'' +
                    ", username='" + username + '\'' +
                    ", roles=" + roles +
                    '}';
        }
    }

    private class Role {
        private int id;
        private String name;

        public Role(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Role() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Role role = (Role) o;
            return id == role.id && Objects.equals(name, role.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return "Role{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    private List<User> users;
    private Server server;
    private Provider provider;

    public InMemoryAuthenticatedProvider(Server server, Provider provider) {
        this.server = server;
        this.provider = provider;
        this.users = new CopyOnWriteArrayList<>();
    }

    @Override
    public void initialize() {

    }

    @Override
    public boolean authenticate(ClientAction clientAction, String login, String password) {
        HashMap<String, List<String>> user = provider.searchUser(login, password);
        if (user.isEmpty()) {
            clientAction.sendMsg("Некорректный логин/пароль");
            return false;
        }
        String authUsername = user.keySet().iterator().next();
        List<String> roles = user.get(authUsername);
        if (server.isUsernameBusy(authUsername)) {
            clientAction.sendMsg("Указанная учетная запись уже используется");
            return false;
        }
        clientAction.setUsername(authUsername);
        clientAction.setRoles(roles);
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
        if (provider.checkUniqueLogin(login)) {
            clientAction.sendMsg("Такой логин уже занят");
            return false;
        }
        if (provider.checkUniqueUsername(username)) {
            clientAction.sendMsg("Такое имя пользователя уже занято");
            return false;
        }
        HashMap<String, List<String>> user = provider.createUser(login, password, username);
        String regUsername = user.keySet().iterator().next();
        List<String> roles = user.get(regUsername);
        clientAction.setUsername(regUsername);
        clientAction.setRoles(roles);
        server.connectClient(clientAction);
        clientAction.sendMsg("/regok " + username);
        return true;
    }
}