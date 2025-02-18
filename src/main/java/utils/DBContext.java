/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.processing.FilerException;

/**
 *
 * @author anhkc
 */

public class DBContext {

    // Thông tin kết nối cơ sở dữ liệu
    private static String url = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=ICHIBOOKS;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456";
    // Phương thức kết nối với cơ sở dữ liệu
    protected Connection conn;

    public DBContext() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=ICHIBOOKS";
            String username = "sa";
            String password = "123456";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(url, USERNAME, PASSWORD);
    }

    //Lenh kiem tra ket noi co so du lieu
    public void testConnection() {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                System.out.println("Connected successfully!");
            } else {
                System.out.println("Connection failed!");
            }
            connection.close();
        } catch (SQLException e) {
        }
    }

    // Phương thức exeQuery cho SELECT
    public ResultSet exeQuery(String query) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        System.out.println("Connected successfully!");

        return preparedStatement.executeQuery();
    }

    public ResultSet exeQueryAlt(String query, Object[] params) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        System.out.println("Connected successfully!");

        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        return preparedStatement.executeQuery();
    }
    
    
    
    // Phương thức exeNonQuery cho INSERT, UPDATE, DELETE
    public int exeNonQuery(String query, Object[] params) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        System.out.println("Connected successfully!");
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        return preparedStatement.executeUpdate();
    }

}
