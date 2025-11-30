package ru.otus.java.basic.homeworks.homework17.Server;

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

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/otus-db";
    private static final String DB_USER = "postgres";
    private static final String DB_PAS = "alexeeva0601";
    private static final String USER_QUERY = "select * from users";
    private static final String USER_ROLE_QUERY = "select r.id, r.name from role r " +
            "join users_to_role ur on r.id = ur.role_id " +
            "where ur.users_id = ?";
    private static final String USER_INSERT = "insert into users (email, password, username) values (?,?,?)";
    private static final String USER_ROLE_INSERT = "insert into users_to_role (users_id, role_id) values (?, ?)";
    private List<User> users;
    private Server server;
    private Connection connection;

    public InMemoryAuthenticatedProvider(Server server) {
        this.server = server;
        this.users = new CopyOnWriteArrayList<>();
        initialize();
    }

    private String getUsernameByLoginAndPassword(String login, String password) {
        for (User u : users) {
            if (u.login.equalsIgnoreCase(login) && u.password.equals(password)) {
                return u.username;
            }
        }
        return null;
    }

    private List<Role> getUserRole(String username) {
        for (User u : users) {
            if (u.username.equalsIgnoreCase(username)) {
                return u.roles;
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
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PAS);
            System.out.println("Подключение к БД установлено");
            creatingListUsers();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к БД", e);
        }
    }

    private void creatingListUsers() {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(USER_QUERY);
            while (rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("email");
                String password = rs.getString("password");
                String username = rs.getString("username");
                List<Role> roles = creatingListUsersRoles(id);
                users.add(new User(id, login, password, username, roles));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка загрузки пользователей: " + e.getMessage());
        }
    }

    private List<Role> creatingListUsersRoles(int userId) {
        List<Role> userRoles = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(USER_ROLE_QUERY)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nameRole = rs.getString("name");
                    userRoles.add(new Role(id, nameRole));
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка загрузки ролей: " + e.getMessage());
        }
        return userRoles;
    }

    @Override
    public boolean authenticate(ClientAction clientAction, String login, String password) {
        String authUsername = getUsernameByLoginAndPassword(login, password);
        String authRole = String.valueOf(getUserRole(authUsername));
        if (authUsername == null) {
            clientAction.sendMsg("Некорректный логин/пароль");
            return false;
        }
        if (server.isUsernameBusy(authUsername)) {
            clientAction.sendMsg("Указанная учетная запись уже используется");
            return false;
        }
        clientAction.setUsername(authUsername);
        clientAction.setRole(authRole);
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
        try (PreparedStatement ps = connection.prepareStatement(USER_INSERT, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, login);
            ps.setString(2,password);
            ps.setString(3, username);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()) {
                    int userId = rs.getInt("id");
                    try (PreparedStatement psRole = connection.prepareStatement(USER_ROLE_INSERT)) {
                        psRole.setInt(1, userId);
                        psRole.setInt(2, 2);
                        psRole.executeUpdate();
                    }
                    List<Role> userRole = creatingListUsersRoles(userId);
                    users.add(new User(userId, login, password, username, userRole));
                }
            }

        } catch (SQLException e) {
            System.out.println("Ошибка создания пользователя: " + e.getMessage());
        }
        clientAction.setUsername(username);
        server.connectClient(clientAction);
        clientAction.sendMsg("/regok " + username);
        return true;
    }
}