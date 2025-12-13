package ru.otus.java.basic.homeworks.homework18.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Provider {
    private Connection connection;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/otus-db";
    private static final String DB_USER = "postgres";
    private static final String DB_PAS = "alexeeva0601";
    private static final String SEARCH_USER_QUERY = "select users.username, role.name from users " +
            "join users_to_role ur on users.id = ur.users_id " +
            "join role on role.id = ur.role_id " +
            "where users.email = ? and users.password = ?;";
    private  static final  String CHECK_LOGIN_QUERY = "select username from users "+
            "where username = ?";
    private  static final  String CHECK_USERNAME_QUERY = "select email from users "+
            "where email = ?";
    private static final String USER_INSERT = "insert into users (email, password, username) values (?,?,?)";
    private static final String USER_ROLE_INSERT = "insert into users_to_role (users_id, role_id) values (?, ?)";
    private static final int ID_DEFAULT_ROLE = 2;

    public Provider() {
        connectDataBase();
    }

    public void connectDataBase() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PAS);
            System.out.println("Подключение к БД установлено");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к БД", e);
        }
    }

    public HashMap<String, List<String>> searchUser(String login, String password) {
        HashMap<String, List<String>> user = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(SEARCH_USER_QUERY)) {
            ps.setString(1, login);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String username = rs.getString("username");
                    String role = rs.getString("name");
                    if (!user.containsKey(username)) {
                        user.put(username, new ArrayList<>());
                    }
                    user.get(username).add(role);
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка поиска пользователя: " + e.getMessage());
        }
        return user;
    }
    public boolean checkUniqueLogin(String login){
        try (PreparedStatement ps = connection.prepareStatement(CHECK_LOGIN_QUERY)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return false;
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Ошибка проверки логина: " + e.getMessage());
            return false;
        }
    }

    public boolean checkUniqueUsername(String username) {
        try (PreparedStatement ps = connection.prepareStatement(CHECK_USERNAME_QUERY)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return false;
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Ошибка проверки имени: " + e.getMessage());
            return false;
        }
    }

    public HashMap<String, List<String>> createUser(String login, String password, String username) {
        HashMap<String, List<String>> user = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(USER_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, username);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int userId = rs.getInt("id");
                    try (PreparedStatement psRole = connection.prepareStatement(USER_ROLE_INSERT)) {
                        psRole.setInt(1, userId);
                        psRole.setInt(2, ID_DEFAULT_ROLE);
                        psRole.executeUpdate();
                    }
                }
            }
            user.put(username, new ArrayList<>(List.of("user")));
        } catch (SQLException e) {
            System.out.println("Ошибка создания пользователя: " + e.getMessage());
        }
        return user;
    }

}
