/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kt2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author giahuy
 */
public class Connect_Database {
        public static Connection connect(){
        Connection connection = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://localhost:1433;Database=QLvpp;user=sa;password=123";
            connection = DriverManager.getConnection(URL);
        }catch(SQLException | ClassNotFoundException e){
            System.out.println("Lỗi kết nối tới cơ sở dữ liệu: " + e.getMessage());
        }
        return connection;
    }
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Lỗi đóng kết nối: " + e.getMessage());
            }
        }
    }
}
