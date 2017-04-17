package press.turngeek.db;

import press.turngeek.todos.ToDo;

import java.sql.*;
import java.util.List;

public class DBConnection {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            Statement st = dbConnection.createStatement();
            st.execute("create table todo(id BIGINT auto_increment, description VARCHAR(255), created DATE)");
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }



//    public static List<ToDo> selectAllTodos() {
//
//    }
//
//    public static void deleteAllToDos() {
//
//    }



}
