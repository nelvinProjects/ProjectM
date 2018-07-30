package Server;

import java.sql.*;

public class Database {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/ActivitiesDB?useSSL=false&serverTimezone=GMT";
    //TODO: SSL turned off
    static final String USER = "root";
    static final String PASS = "123456";

    public static Connection dbConnection;

    public void setupDB() {
        try {
            Class.forName(JDBC_DRIVER);
            dbConnection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("DATABASE CONNECTED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getMaxID(String tableName, String columnName) {
        int max = 0;
        Statement stm = null;
        String getsql = "SELECT MAX(" + columnName + ") FROM ActivitiesDB." + tableName + ";";
        try {
            stm = dbConnection.createStatement();
            ResultSet rs = stm.executeQuery(getsql);
            while (rs.next()) {
//                System.out.println(rs.getInt(1));
                max = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        max++;
        System.out.println("max " + max);
        return max;
    }

    public void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}