package com.DB;

import lombok.SneakyThrows;

import java.sql.*;

public class DB {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "postgres";


    @SneakyThrows
    public static Connection connect() {
        Connection conn = null;
        Class.forName("org.postgresql.Driver");
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static void register(User user){
        String SQL = "insert into users (login, email, password, date_of_registration) values (?,?,?,now())";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String decryptPassword(String password){
        return User.reversPassword(password).substring(1);
    }

    public static void addToUserLogs(int login, Status status){
        String SQL = "insert into userLogs (user_id, time_log, status) values (?,now(),?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setInt(1, login);
            stmt.setString(2, status.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void authorize(String login, String password){
        if(checkIs(login)) return;
        password = User.encrypt(password);
        String SQL = "select count(*) from users where login = ? and password = ?;";
        int a = 0;
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            try(ResultSet rs = stmt.executeQuery()){
                rs.next();
                a = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        SQL = "select * from users where login = ?";
        int k = 0;
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setString(1, login);
            try(ResultSet rs = stmt.executeQuery()){
                rs.next();
                k = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(a == 1) {
            addToUserLogs(k,Status.OK);
            deleteFromUserLogs(k);
        }else{
            addToUserLogs(k,Status.FAIL);
            if(CheckUserLogs(k) >= 3){
                addToBlockedUsers(k);
            }
        }
    }

    public static int CheckUserLogs(int g){
        String SQL = "select count (*) from userlogs where user_id = ? and status = 'FAIL'";
        int k = 0;
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setInt(1, g);
            try(ResultSet rs = stmt.executeQuery()){
                rs.next();
                k = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return k;
    }

    public static void addToBlockedUsers(int id){
        String SQL = "insert into blockedusers (user_id) values (?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        deleteFromUserLogs(id);
    }

    public static void deleteFromUserLogs(int id){
        String SQL = "delete from userlogs where user_id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean checkIs(String login){
        String SQL = "select * from users where login = ?";
        int k = 0;
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setString(1, login);
            try(ResultSet rs = stmt.executeQuery()){
                rs.next();
                k = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        SQL = "select count (*) from blockedusers where user_id = ?;";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setInt(1, k);
            try(ResultSet rs = stmt.executeQuery()){
                rs.next();
                k = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(k == 1) return true;
        return false;
    }

    public static void deleteBlockedUser(String login){
        String SQL = "select * from users where login = ?";
        int k = 0;
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setString(1, login);
            try(ResultSet rs = stmt.executeQuery()){
                rs.next();
                k = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        SQL = "delete from blockedusers where user_id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setInt(1, k);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

