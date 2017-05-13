package press.turngeek.db;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;

public class DBConnection {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:todo;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public static DataSource getDatasource() {
        JdbcDataSource datasource = new JdbcDataSource();
        datasource.setUrl(DB_CONNECTION);
        datasource.setUser(DB_USER);
        datasource.setPassword(DB_PASSWORD);
        return datasource;
    }



//    public static List<ToDo> selectAllTodos() {
//
//    }
//
//    public static void deleteAllToDos() {
//
//    }



}
