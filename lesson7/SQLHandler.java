/**
 * Created by Алексей on 17.04.2017.
 */

package lesson7;

import java.sql.*;

public class SQLHandler {
    private static Connection conn;
    private static Statement stmt;
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:CLIENTS.db");
        } catch (Exception c) {
            System.out.println("Ошибка соединения с базой данных");
        }
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public static void disconnect() {
        try {
            conn.close();
        } catch (Exception c) {
            System.out.println("Ошибка соединения с базой данных");
        }
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public static void fillBase() {
        boolean isErrCreate = true;
        try {
            stmt = conn.createStatement();
            String sql = "CREATE TABLE CLIENTS " +
                    "(LOGIN TEXT PRIMARY KEY NOT NULL," +
                    " PASSWORD TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            isErrCreate = false;
            sql = "INSERT INTO CLIENTS" +
                    " (LOGIN,PASSWORD) " +
                    "VALUES ('user1', '111');";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO CLIENTS" +
                    " (LOGIN,PASSWORD) " +
                    "VALUES ('user2', '222');";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO CLIENTS" +
                    " (LOGIN,PASSWORD) " +
                    "VALUES ('user3', '333');";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            if (!isErrCreate) {
                System.out.println("Ошибка в методе fillBase");
            }
        }
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public static ResultSet getPasswordByLogin(String login) {
        ResultSet rs = null;
        try {
            String sql =    "SELECT PASSWORD FROM CLIENTS " +
                            "WHERE LOGIN = '" + login + "';";
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Ошибка SQL запроса");
        }
        return rs;
    }
// ==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==
    public static int changeNick (String oldNick, String newNick) {
        try {
            String sql =    "UPDATE CLIENTS " +
                            " SET LOGIN = '" + newNick + "'" +
                            " WHERE LOGIN = '" + oldNick + "';";
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Ошибка SQL запроса");
            return 0;
        }
    }
}