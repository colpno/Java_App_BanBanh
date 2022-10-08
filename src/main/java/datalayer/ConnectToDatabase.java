package datalayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import config.configDB;

import java.sql.ResultSet;

public class ConnectToDatabase {
    private Connection conn;
    private Statement stmt;

    private String url = "jdbc:mysql://localhost:3306/" + configDB.getDatabaseName();
    private String username = configDB.getUsername();
    private String password = configDB.getPassword();

    public void getConnection() throws ClassNotFoundException, SQLException {
        if (conn == null || conn.isClosed()) {
            conn = CreateNewConnection();
        }
    }

    private Connection CreateNewConnection() throws ClassNotFoundException, SQLException {
        // Class.forName("com.mysql.jdbc.Driver");
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        conn = DriverManager.getConnection(url, username, password);
        stmt = conn.createStatement();
        return conn;
    }

    public ResultSet makeQuery(String sql) throws SQLException {
        return stmt.executeQuery(sql);
    }

    public void makeUpdate(String sql) throws SQLException {
        stmt.executeUpdate(sql);
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }

}